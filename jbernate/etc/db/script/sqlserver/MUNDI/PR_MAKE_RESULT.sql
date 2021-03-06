/****** Object: Procedure [dbo].[PR_MAKE_RESULT]   Script Date: 2015-04-03 오후 2:29:44 ******/
USE [MUNDI];
GO
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[PR_MAKE_RESULT]') AND type IN ( N'P' ))
BEGIN
DROP PROCEDURE [dbo].[PR_MAKE_RESULT];
END
GO
SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO
CREATE PROCEDURE [dbo].[PR_MAKE_RESULT]
(
      @ac_yyyymm        VARCHAR(6)      = ''               --// 1.집계 년월
    , @rn_err_cd        NUMERIC(10)     = 0    OUTPUT      --// 1.오류코드(0:정상,E:오류)
    , @rc_err_msg       NVARCHAR(MAX)  = ''    OUTPUT      --// 2.오류정보
)
AS
/*******************************************************************************
    System Name   : MUNDI
    Module Name   : 먼디파머 데이터 집계
    Program Name  : PR_MAKE_RESULT
    Description   : 먼디파머 데이터 집계
    
                    인센티브 계산 공식
                      해당 Cycle의 %를 LAUNCH / GROWTH VALUE 구함
                      *
                      급여
                      *
                      근무일수% = ( ROUND( ( CYCLE FULL DAY - 입사일 차이 ) * 100 / CYCLE FULL DAY, 1 ) )
                      *
                      팀/브랜드/Cycle별 Incentive percentage
                      *
                      Cycle별 Incentive percentage
            

    Table Info.   : RESULT_ALL : 집계 결과    

    In  Parameter : 1.[필수]집계 년월

    Out Parameter : 1.오류코드(0:정상,E:오류), 2.오류정보
    
    Logic : 
            - 실적관리 테이블(ACTUAL_MGR) 데이터 조회
                - CLAUSE : YYYYMM
                - GROUP BY : 년월, 팀_시퀀스, 병원_시퀀스, 사용자_시퀀스, 제품코드
            - 목표관리 테이블( GOAL_MGR ) 데이터 조회
            - RATE관리 테이블( RATE_MGR ) 데이터 조회
            - MERGE 수행
                - KEY1 : 년월
                - KEY2 : 팀_시퀀스
                - KEY3 : 제품그룹_시퀀스
                - KEY4 : 병원_시퀀스
                - KEY5 : 사용자_시퀀스
                - KEY6 : 제품코드                    
                
    ****************************************************************************
    Execution     : EXEC dbo.PR_MAKE_RESULT + 매개변수 설정 참조
    ****************************************************************************

    Modify History :
      DATE        AUTHOR             DESC.
    ----------  -------------  ------------------------------------------
    2015/06/19     ⓙⓢⓛ        Initial Release
*******************************************************************************/
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED    -- 필요하다면 고립화 수준을 낮춥니다.
    SET XACT_ABORT ON                                   -- 프로시저 내에서 트랜잭션을 처리한다면 설정 합니다.

    /*************************************************
        공통 변수 선언
    **************************************************/
    DECLARE   @ProcName     NVARCHAR(200)  = OBJECT_NAME(@@PROCID)              --// 프로세스ID
            , @StepID       VARCHAR(30)    = '0'                                --// STEP번호
            , @ErrorNum     NUMERIC(10)    = 0                                  --// 오류코드
            , @ErrorMsg     NVARCHAR(1000) = ''                                 --// 오류정보
            
    /*************************************************
       지역 변수 선언(배치작업실행)
    **************************************************/
    DECLARE   @V_PAY  NUMERIC(22,3) = 40000000 --// 급여
            
            
BEGIN
    SET NOCOUNT ON                                      -- 특별한 이유가 없다면 무조건 설정 합니다
    
    /*************************************************
     ■■■[STEP-10] 작업 수행
    **************************************************/
    BEGIN
        BEGIN TRY
            
            --||■■■[백업 수행]■■■
            SET @StepID = '1';      --// STEP번호
            INSERT INTO BAK_RESULT_ALL(
                REMK              
              , INS_DT             
              , INS_ID              
              , INS_IP              
              , UPD_DT           
              , UPD_ID             
              , UPD_IP             
              , USE_YN           
              , PRDGRP_SEQ   
              , TEAM_SEQ       
              , USER_SEQ       
              , YYYYMM          
              , GOAL              
              , ACTUAL           
              , HOSPT_SEQ     
              , BAK_DT           
              , SEQ          
              , PER_RATE
              , PER_WRK
              , PER_GRP
              , PER_YEAR
            ) SELECT
                REMK              
              , INS_DT             
              , INS_ID              
              , INS_IP              
              , UPD_DT           
              , UPD_ID             
              , UPD_IP             
              , USE_YN           
              , PRDGRP_SEQ   
              , TEAM_SEQ       
              , USER_SEQ       
              , YYYYMM          
              , GOAL              
              , ACTUAL           
              , HOSPT_SEQ     
              , GETDATE()
              , SEQ
              , PER_RATE
              , PER_WRK
              , PER_GRP
              , PER_YEAR
            FROM RESULT_ALL WITH(NOLOCK)
            WHERE YYYYMM = @ac_yyyymm
            ;
            
            --||■■■[백업된 원본 데이터 삭제]■■■
            SET @StepID = '2';      --// STEP번호
            
            DELETE 
              FROM RESULT_ALL
              WHERE YYYYMM = @ac_yyyymm
            ;
            
            --||■■■[실적데이터로부터 MERGE 수행]■■■            
            SET @StepID = '3';      --// STEP번호
            MERGE INTO RESULT_ALL AS R
            USING (
              SELECT 
                  A.YYYYMM                    -- 년월
                , T.SEQ AS TEAM_SEQ           -- 팀_시퀀스
                , G.SEQ AS PRDGRP_SEQ         -- 제품그룹_시퀀스
                , A.HOSPT_SEQ                 -- 병원_시퀀스
                , A.USER_SEQ                  -- 사용자_시퀀스
                --, A.PRD_SEQ                 -- 제품_시퀀스
                --, MAX( P.PRD_NM )           -- 제품명
                , SUM( A.SAL_AMT ) AS ACTUAL  -- 달성
                , '' AS REMK                  -- 비고
                , 'Y' AS USE_YN               -- 사용여부
                , GETDATE() AS INS_DT         -- 삽입일
                , 'PRCD' AS INS_ID            -- 삽입자
                , 'localhost' AS INS_IP       -- 삽입자IP
                , GETDATE() AS UPD_DT         -- 수정일
                , 'PRCD' AS UPD_ID            -- 수정자
                , 'localhost' AS UPD_IP       -- 수정자IP
                FROM ACTUAL_MGR A WITH(NOLOCK)        -- 실적관리
                INNER JOIN PRD_MGR P WITH(NOLOCK)     -- 제품관리
                  ON P.SEQ    = A.PRD_SEQ
                 AND P.USE_YN = 'Y'
                INNER JOIN PRDGRP_MGR G WITH(NOLOCK)  -- 제품그룹관리
                  ON G.SEQ = P.PRDGRP_SEQ
                 AND G.USE_YN = 'Y'
                INNER JOIN USER_MGR U WITH(NOLOCK)    -- 사용자관리
                  ON U.SEQ = A.USER_SEQ
                 AND U.USE_YN = 'Y'
                INNER JOIN TEAM_MGR T WITH(NOLOCK)    -- 팀관리
                  ON T.SEQ = U.TEAM_SEQ
                 AND T.USE_YN = 'Y'
                WHERE A.YYYYMM = @ac_yyyymm
                  AND A.USE_YN = 'Y'
                GROUP BY  A.YYYYMM
                        , T.SEQ
                        , G.SEQ
                        , A.HOSPT_SEQ
                        , A.USER_SEQ
                        --, A.PRD_SEQ
            ) S ON (
                  S.YYYYMM      = R.YYYYMM
              AND S.TEAM_SEQ    = R.TEAM_SEQ
              AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
              AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              AND S.USER_SEQ    = R.USER_SEQ
              AND S.USE_YN      = 'Y'
              --AND S.PRD_SEQ     = R.PRD_SEQ              
            )
            WHEN MATCHED THEN
              UPDATE
                SET R.ACTUAL = R.ACTUAL + S.ACTUAL
                  , R.UPD_DT = S.UPD_DT
                  , R.UPD_ID = S.UPD_ID
                  , R.UPD_IP = S.UPD_IP
            WHEN NOT MATCHED THEN
              INSERT(
                  YYYYMM      -- 년월
                , TEAM_SEQ    -- 팀_시퀀스
                , PRDGRP_SEQ  -- 제품그룹_시퀀스
                , HOSPT_SEQ   -- 병원_시퀀스
                , USER_SEQ    -- 사용자_시퀀스
                , ACTUAL      -- 달성
                , REMK        -- 비고
                , USE_YN      -- 사용여부
                , INS_DT      -- 삽입일
                , INS_ID      -- 삽입자
                , INS_IP      -- 삽입자IP
              ) VALUES (
                  S.YYYYMM      -- 년월
                , S.TEAM_SEQ    -- 팀_시퀀스
                , S.PRDGRP_SEQ  -- 제품그룹_시퀀스
                , S.HOSPT_SEQ   -- 병원_시퀀스
                , S.USER_SEQ    -- 사용자_시퀀스
                , S.ACTUAL      -- 달성
                , S.REMK        -- 비고
                , S.USE_YN      -- 사용여부
                , S.INS_DT      -- 삽입일
                , S.INS_ID      -- 삽입자
                , S.INS_IP      -- 삽입자IP
              )
            ;
            
            --||■■■[목표데이터로부터 MERGE 수행]■■■            
            SET @StepID = '4';      --// STEP번호
            MERGE INTO RESULT_ALL AS R
            USING (
                SELECT
                   G.YYYY
                 , G.TEAM_SEQ
                 , G.PRDGRP_SEQ
                 , G.USER_SEQ
                 , G.HOSPT_SEQ
                 , SUM(
                    CASE  WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '01' THEN G.MON_1
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '02' THEN G.MON_2
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '03' THEN G.MON_3
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '04' THEN G.MON_4
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '05' THEN G.MON_5
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '06' THEN G.MON_6
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '07' THEN G.MON_7
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '08' THEN G.MON_8
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '09' THEN G.MON_9
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '10' THEN G.MON_10
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '11' THEN G.MON_11
                          WHEN SUBSTRING( @ac_yyyymm, 5, 2 ) = '12' THEN G.MON_12
                          ELSE 0
                    END
                 ) AS GOAL
                 FROM GOAL_MGR G WITH(NOLOCK)
                 WHERE G.USE_YN = 'Y'
                 GROUP BY
                    G.YYYY
                  , G.TEAM_SEQ
                  , G.PRDGRP_SEQ
                  , G.USER_SEQ
                  , G.HOSPT_SEQ
            ) S ON (
                  S.YYYY        = SUBSTRING( R.YYYYMM, 1, 4 )
              AND S.TEAM_SEQ    = R.TEAM_SEQ
              AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
              AND S.USER_SEQ    = R.USER_SEQ
              AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              AND R.USE_YN      = 'Y'
            )
            WHEN MATCHED THEN
              UPDATE 
                SET R.GOAL = R.GOAL + S.GOAL
            ;
            
            --||■■■[현재 데이터로부터 %값 계산]■■■
            /* -- 실제 데이터 조회 시 계산( StepID = '5'는 병원이 다른 경우 %계산 제대로 되지 않음 )
            SET @StepID = '5';      --// STEP번호
            UPDATE RESULT_ALL
              SET   PERCENTAGE = CAST( 
                                        ROUND( 
                                                ( CAST( ACTUAL AS DECIMAL ) * 100 / CAST( GOAL AS DECIMAL ) )
                                        , 0  ) AS INT 
                                      )
              WHERE YYYYMM  = @ac_yyyymm
                AND USE_YN  = 'Y'
            ;
            */
            
            --||■■■[RATE값 계산]■■■
            /* -- 실제 데이터 조회 시 계산( StepID = '6'은 병원이 다른 경우 계산 제대로 되지 않음 )
            SET @StepID = '6';      --// STEP번호
            UPDATE R
            SET R.RATE = RT.VALUE
            FROM RESULT_ALL R
            INNER JOIN
              PRDGRP_MGR G
                ON G.SEQ = R.PRDGRP_SEQ
            INNER JOIN
              RATE_MGR RT
                ON RT.RATE_CD = G.RATE_CD
               AND RT.YYYYMM  = R.YYYYMM
               AND RT.ACHIEV  = R.PERCENTAGE
            WHERE R.YYYYMM = @ac_yyyymm
              AND R.USE_YN  = 'Y'
            ;
            */
            
            --||■■■[1CYCLE 합계 계산 : 월 = 13]■■■
            SET @StepID = '7';      --// STEP번호
            IF SUBSTRING( @ac_yyyymm, 5, 2 ) = '04'
            BEGIN
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , SUM( GOAL ) AS GOAL
                    , SUM( ACTUAL ) AS ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM IN ( 
                        SUBSTRING( @ac_yyyymm, 1, 4 ) + '01'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '02'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '03'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '04'
                    )
                    AND USE_YN = 'Y'
                  GROUP BY
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '13'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN MATCHED THEN
                UPDATE 
                  SET R.GOAL    = S.GOAL
                    , R.ACTUAL  = S.ACTUAL
                    , R.UPD_DT  = GETDATE()
                    , R.UPD_ID  = 'PRCD'
                    , R.UPD_IP  ='localhost'
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '13'      -- 년월
                  , S.TEAM_SEQ    -- 팀_시퀀스
                  , S.PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ   -- 병원_시퀀스
                  , S.USER_SEQ    -- 사용자_시퀀스
                  , S.GOAL        -- 목표
                  , S.ACTUAL      -- 달성
                  , '1CYCLE TOTAL'-- 비고
                  , 'Y'           -- 사용여부
                  , GETDATE()     -- 삽입일
                  , 'PRCD'        -- 삽입자
                  , 'localhost'   -- 삽입자IP
                )
              ;
              
              --||■■■[1CYCLE 인센티브용 데이터 계산 : 월 = 17]■■■ ACTUAL 컬럼에 삽입
              SET @StepID = '8';      --// STEP번호
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , GOAL
                    , ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '13'
                    AND USE_YN = 'Y'                  
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '17'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '17'      -- 년월
                  , S.TEAM_SEQ          -- 팀_시퀀스
                  , S.PRDGRP_SEQ        -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ         -- 병원_시퀀스
                  , S.USER_SEQ          -- 사용자_시퀀스
                  , 0                   -- 목표
                  , 0                   -- 달성
                  , '1CYCLE INCENTIVE'  -- 비고
                  , 'Y'                 -- 사용여부
                  , GETDATE()           -- 삽입일
                  , 'PRCD'              -- 삽입자
                  , 'localhost'         -- 삽입자IP
                )
              ;
              UPDATE R
                SET
                  R.PER_RATE = (
                      SELECT 
                        RT.VALUE
                      FROM RATE_MGR RT WITH(NOLOCK)
                      INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
                         ON G.RATE_CD = RT.RATE_CD
                        AND G.SEQ = R.PRDGRP_SEQ
                      WHERE RT.YYYYMM BETWEEN SUBSTRING( @ac_yyyymm, 1, 4 ) + '01' AND SUBSTRING( @ac_yyyymm, 1, 4 ) + '04'
                        AND RT.ACHIEV = (
                                          SELECT  CAST(
                                                        ROUND( 
                                                              ( CAST( SUM( SR.ACTUAL ) AS DECIMAL ) * 100 / CAST( SUM( SR.GOAL ) AS DECIMAL ) )
                                                        , 0  ) AS NUMERIC(22,3) 
                                                  )
                                          FROM RESULT_ALL SR WITH(NOLOCK)
                                          WHERE SR.YYYYMM     = SUBSTRING( @ac_yyyymm, 1, 4 ) + '13'
                                            AND SR.TEAM_SEQ   = R.TEAM_SEQ
                                            AND SR.PRDGRP_SEQ = R.PRDGRP_SEQ
                                            AND SR.HOSPT_SEQ  = R.HOSPT_SEQ
                                            AND SR.USER_SEQ   = R.USER_SEQ
                                        )
                        AND RT.USE_YN = 'Y'
                  ) 
                  , R.PER_WRK =
                  (
                      SELECT
                      ( C.USER_WRK_DAY * 100 / FULL_WRK_DAY ) AS WRK_PER
                      FROM (
                          SELECT
                              DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0101', SUBSTRING( @ac_yyyymm, 1, 4 ) + '0430' ) AS FULL_WRK_DAY
                            , (
                                CASE WHEN FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ) > SUBSTRING( @ac_yyyymm, 1, 4 ) + '0101' THEN DATEDIFF( day, FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ), SUBSTRING( @ac_yyyymm, 1, 4 ) + '0430' )
                                    ELSE DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0101', SUBSTRING( @ac_yyyymm, 1, 4 ) + '0430' )
                                END
                            ) AS USER_WRK_DAY
                          FROM USER_MGR U WITH(NOLOCK)
                          WHERE U.SEQ     = R.USER_SEQ
                            AND U.USE_YN  = 'Y'
                      ) C
                  )
                  , R.PER_GRP =
                  (
                      SELECT
                        PB.PERCENTAGE
                      FROM PER_BRAND_MGR PB WITH(NOLOCK)
                      WHERE PB.TEAM_SEQ   = R.TEAM_SEQ
                        AND PB.PRDGRP_SEQ = R.PRDGRP_SEQ
                        AND PB.YYYY       = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PB.CYCLE      = 1
                        AND PB.USE_YN     = 'Y'
                  )
                  , R.PER_YEAR =
                  (
                      SELECT
                        PY.PERCENTAGE
                      FROM PER_YEAR_MGR PY WITH(NOLOCK)
                      WHERE PY.YYYY     = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PY.CYCLE    = 1
                        AND PY.USE_YN   = 'Y'
                  )
                FROM RESULT_ALL R
                WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '17'
              ;
              
            END; --// IF SUBSTRING( @ac_yyyymm, 5, 2 ) = '04'
            
            --||■■■[2CYCLE 합계 계산 : 월 = 14]■■■
            SET @StepID = '9';      --// STEP번호
            IF SUBSTRING( @ac_yyyymm, 5, 2 ) = '08'
            BEGIN
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , SUM( GOAL ) AS GOAL
                    , SUM( ACTUAL ) AS ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM IN ( 
                        SUBSTRING( @ac_yyyymm, 1, 4 ) + '05'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '06'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '07'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '08'
                    )
                    AND USE_YN = 'Y'
                  GROUP BY
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '14'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ     = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN MATCHED THEN
                UPDATE 
                  SET R.GOAL    = S.GOAL
                    , R.ACTUAL  = S.ACTUAL
                    , R.UPD_DT  = GETDATE()
                    , R.UPD_ID  = 'PRCD'
                    , R.UPD_IP  ='localhost'
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '14'      -- 년월
                  , S.TEAM_SEQ    -- 팀_시퀀스
                  , S.PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ   -- 병원_시퀀스
                  , S.USER_SEQ    -- 사용자_시퀀스
                  , S.GOAL        -- 목표
                  , S.ACTUAL      -- 달성
                  , '2CYCLE TOTAL'-- 비고
                  , 'Y'           -- 사용여부
                  , GETDATE()     -- 삽입일
                  , 'PRCD'        -- 삽입자
                  , 'localhost'   -- 삽입자IP
                )
              ;
              
              --||■■■[2CYCLE 인센티브 계산 : 월 = 18]■■■ ACTUAL 컬럼에 삽입
              SET @StepID = '10';      --// STEP번호
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , GOAL
                    , ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '14'
                    AND USE_YN = 'Y'                  
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '18'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '18'      -- 년월
                  , S.TEAM_SEQ          -- 팀_시퀀스
                  , S.PRDGRP_SEQ        -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ         -- 병원_시퀀스
                  , S.USER_SEQ          -- 사용자_시퀀스
                  , 0                   -- 목표
                  , 0                   -- 달성
                  , '2CYCLE INCENTIVE'  -- 비고
                  , 'Y'                 -- 사용여부
                  , GETDATE()           -- 삽입일
                  , 'PRCD'              -- 삽입자
                  , 'localhost'         -- 삽입자IP
                )
              ;
              UPDATE R
                SET
                  R.PER_RATE = (
                      SELECT 
                        RT.VALUE
                      FROM RATE_MGR RT WITH(NOLOCK)
                      INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
                         ON G.RATE_CD = RT.RATE_CD
                        AND G.SEQ = R.PRDGRP_SEQ
                      WHERE RT.YYYYMM BETWEEN SUBSTRING( @ac_yyyymm, 1, 4 ) + '05' AND SUBSTRING( @ac_yyyymm, 1, 4 ) + '08'
                        AND RT.ACHIEV = (
                                          SELECT  CAST(
                                                        ROUND( 
                                                              ( CAST( SUM( SR.ACTUAL ) AS DECIMAL ) * 100 / CAST( SUM( SR.GOAL ) AS DECIMAL ) )
                                                        , 0  ) AS NUMERIC(22,3) 
                                                  )
                                          FROM RESULT_ALL SR WITH(NOLOCK)
                                          WHERE SR.YYYYMM     = SUBSTRING( @ac_yyyymm, 1, 4 ) + '14'
                                            AND SR.TEAM_SEQ   = R.TEAM_SEQ
                                            AND SR.PRDGRP_SEQ = R.PRDGRP_SEQ
                                            AND SR.HOSPT_SEQ  = R.HOSPT_SEQ
                                            AND SR.USER_SEQ   = R.USER_SEQ
                                        )
                        AND RT.USE_YN = 'Y'
                  ) 
                  , R.PER_WRK =
                  (
                      SELECT
                      ( C.USER_WRK_DAY * 100 / FULL_WRK_DAY ) AS WRK_PER
                      FROM (
                          SELECT
                              DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0501', SUBSTRING( @ac_yyyymm, 1, 4 ) + '0831' ) AS FULL_WRK_DAY
                            , (
                                CASE WHEN FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ) > SUBSTRING( @ac_yyyymm, 1, 4 ) + '0501' THEN DATEDIFF( day, FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ), SUBSTRING( @ac_yyyymm, 1, 4 ) + '0831' )
                                    ELSE DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0501', SUBSTRING( @ac_yyyymm, 1, 4 ) + '0831' )
                                END
                            ) AS USER_WRK_DAY
                          FROM USER_MGR U WITH(NOLOCK)
                          WHERE U.SEQ     = R.USER_SEQ
                            AND U.USE_YN  = 'Y'
                      ) C
                  )
                  , R.PER_GRP =
                  (
                      SELECT
                        PB.PERCENTAGE
                      FROM PER_BRAND_MGR PB WITH(NOLOCK)
                      WHERE PB.TEAM_SEQ   = R.TEAM_SEQ
                        AND PB.PRDGRP_SEQ = R.PRDGRP_SEQ
                        AND PB.YYYY       = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PB.CYCLE      = 1
                        AND PB.USE_YN     = 'Y'
                  )
                  , R.PER_YEAR =
                  (
                      SELECT
                        PY.PERCENTAGE
                      FROM PER_YEAR_MGR PY WITH(NOLOCK)
                      WHERE PY.YYYY     = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PY.CYCLE    = 1
                        AND PY.USE_YN   = 'Y'
                  )
                FROM RESULT_ALL R
                WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '18'
              ;
              
            END; --// IF SUBSTRING( @ac_yyyymm, 5, 2 ) = '08'
            
            --||■■■[3CYCLE 합계 계산 : 월 = 15]■■■
            SET @StepID = '11';      --// STEP번호
            IF SUBSTRING( @ac_yyyymm, 5, 2 ) = '12'
            BEGIN
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , SUM( GOAL ) AS GOAL
                    , SUM( ACTUAL ) AS ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM IN ( 
                        SUBSTRING( @ac_yyyymm, 1, 4 ) + '09'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '10'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '11'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '12'
                    )
                    AND USE_YN = 'Y'
                  GROUP BY
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '15'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN MATCHED THEN
                UPDATE 
                  SET R.GOAL    = S.GOAL
                    , R.ACTUAL  = S.ACTUAL
                    , R.UPD_DT  = GETDATE()
                    , R.UPD_ID  = 'PRCD'
                    , R.UPD_IP  ='localhost'
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '15'      -- 년월
                  , S.TEAM_SEQ    -- 팀_시퀀스
                  , S.PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ   -- 병원_시퀀스
                  , S.USER_SEQ    -- 사용자_시퀀스
                  , S.GOAL        -- 목표
                  , S.ACTUAL      -- 달성
                  , '3CYCLE TOTAL'-- 비고
                  , 'Y'           -- 사용여부
                  , GETDATE()     -- 삽입일
                  , 'PRCD'        -- 삽입자
                  , 'localhost'   -- 삽입자IP
                )
              ;
            
            --||■■■[FULL YEAR 합계 계산 : 월 = 16]■■■
              SET @StepID = '12';      --// STEP번호
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , SUM( GOAL ) AS GOAL
                    , SUM( ACTUAL ) AS ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM IN ( 
                        SUBSTRING( @ac_yyyymm, 1, 4 ) + '01'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '02'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '03'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '04'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '05'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '06'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '07'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '08'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '09'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '10'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '11'
                      , SUBSTRING( @ac_yyyymm, 1, 4 ) + '12'
                    )
                    AND USE_YN = 'Y'
                  GROUP BY
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '16'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN MATCHED THEN
                UPDATE 
                  SET R.GOAL    = S.GOAL
                    , R.ACTUAL  = S.ACTUAL
                    , R.UPD_DT  = GETDATE()
                    , R.UPD_ID  = 'PRCD'
                    , R.UPD_IP  ='localhost'
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '16'      -- 년월
                  , S.TEAM_SEQ        -- 팀_시퀀스
                  , S.PRDGRP_SEQ      -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ       -- 병원_시퀀스
                  , S.USER_SEQ        -- 사용자_시퀀스
                  , S.GOAL            -- 목표
                  , S.ACTUAL          -- 달성
                  , 'FULL YEAR TOTAL' -- 비고
                  , 'Y'               -- 사용여부
                  , GETDATE()         -- 삽입일
                  , 'PRCD'            -- 삽입자
                  , 'localhost'       -- 삽입자IP
                )
              ;
              
              --||■■■[3CYCLE 인센티브 계산 : 월 = 19]■■■ ACTUAL 컬럼에 삽입              
              SET @StepID = '13';      --// STEP번호
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , GOAL
                    , ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '15'
                    AND USE_YN = 'Y'                  
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '19'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '19'      -- 년월
                  , S.TEAM_SEQ          -- 팀_시퀀스
                  , S.PRDGRP_SEQ        -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ         -- 병원_시퀀스
                  , S.USER_SEQ          -- 사용자_시퀀스
                  , 0                   -- 목표
                  , 0                   -- 달성
                  , '3CYCLE INCENTIVE'  -- 비고
                  , 'Y'                 -- 사용여부
                  , GETDATE()           -- 삽입일
                  , 'PRCD'              -- 삽입자
                  , 'localhost'         -- 삽입자IP
                )
              ;
              UPDATE R
                SET
                  R.PER_RATE = (
                      SELECT 
                        RT.VALUE
                      FROM RATE_MGR RT WITH(NOLOCK)
                      INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
                         ON G.RATE_CD = RT.RATE_CD
                        AND G.SEQ = R.PRDGRP_SEQ
                      WHERE RT.YYYYMM BETWEEN SUBSTRING( @ac_yyyymm, 1, 4 ) + '09' AND SUBSTRING( @ac_yyyymm, 1, 4 ) + '12'
                        AND RT.ACHIEV = (
                                          SELECT  CAST(
                                                        ROUND( 
                                                              ( CAST( SUM( SR.ACTUAL ) AS DECIMAL ) * 100 / CAST( SUM( SR.GOAL ) AS DECIMAL ) )
                                                        , 0  ) AS NUMERIC(22,3) 
                                                  )
                                          FROM RESULT_ALL SR WITH(NOLOCK)
                                          WHERE SR.YYYYMM     = SUBSTRING( @ac_yyyymm, 1, 4 ) + '15'
                                            AND SR.TEAM_SEQ   = R.TEAM_SEQ
                                            AND SR.PRDGRP_SEQ = R.PRDGRP_SEQ
                                            AND SR.HOSPT_SEQ  = R.HOSPT_SEQ
                                            AND SR.USER_SEQ   = R.USER_SEQ
                                        )
                        AND RT.USE_YN = 'Y'
                  ) 
                  , R.PER_WRK =
                  (
                      SELECT
                      ( C.USER_WRK_DAY * 100 / FULL_WRK_DAY ) AS WRK_PER
                      FROM (
                          SELECT
                              DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0901', SUBSTRING( @ac_yyyymm, 1, 4 ) + '1231' ) AS FULL_WRK_DAY
                            , (
                                CASE WHEN FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ) > SUBSTRING( @ac_yyyymm, 1, 4 ) + '0901' THEN DATEDIFF( day, FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ), SUBSTRING( @ac_yyyymm, 1, 4 ) + '1231' )
                                    ELSE DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0901', SUBSTRING( @ac_yyyymm, 1, 4 ) + '1231' )
                                END
                            ) AS USER_WRK_DAY
                          FROM USER_MGR U WITH(NOLOCK)
                          WHERE U.SEQ     = R.USER_SEQ
                            AND U.USE_YN  = 'Y'
                      ) C
                  )
                  , R.PER_GRP =
                  (
                      SELECT
                        PB.PERCENTAGE
                      FROM PER_BRAND_MGR PB WITH(NOLOCK)
                      WHERE PB.TEAM_SEQ   = R.TEAM_SEQ
                        AND PB.PRDGRP_SEQ = R.PRDGRP_SEQ
                        AND PB.YYYY       = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PB.CYCLE      = 1
                        AND PB.USE_YN     = 'Y'
                  )
                  , R.PER_YEAR =
                  (
                      SELECT
                        PY.PERCENTAGE
                      FROM PER_YEAR_MGR PY WITH(NOLOCK)
                      WHERE PY.YYYY     = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PY.CYCLE    = 1
                        AND PY.USE_YN   = 'Y'
                  )
                FROM RESULT_ALL R
                WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '19'
              ;
              
              --||■■■[FULL YEAR 인센티브 계산 : 월 = 20]■■■ ACTUAL 컬럼에 삽입
              SET @StepID = '14';      --// STEP번호
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , GOAL
                    , ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '16'
                    AND USE_YN = 'Y'                  
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '20'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '20'      -- 년월
                  , S.TEAM_SEQ              -- 팀_시퀀스
                  , S.PRDGRP_SEQ            -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ             -- 병원_시퀀스
                  , S.USER_SEQ              -- 사용자_시퀀스
                  , 0                       -- 목표
                  , 0                       -- 달성
                  , 'FULL YEAR INCENTIVE'   -- 비고
                  , 'Y'                     -- 사용여부
                  , GETDATE()               -- 삽입일
                  , 'PRCD'                  -- 삽입자
                  , 'localhost'             -- 삽입자IP
                )
              ;
              UPDATE R
                SET
                  R.PER_RATE = (
                      SELECT 
                        RT.VALUE
                      FROM RATE_MGR RT WITH(NOLOCK)
                      INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
                         ON G.RATE_CD = RT.RATE_CD
                        AND G.SEQ = R.PRDGRP_SEQ
                      WHERE RT.YYYYMM BETWEEN SUBSTRING( @ac_yyyymm, 1, 4 ) + '09' AND SUBSTRING( @ac_yyyymm, 1, 4 ) + '12'
                        AND RT.ACHIEV = (
                                          SELECT  CAST(
                                                        ROUND( 
                                                              ( CAST( SUM( SR.ACTUAL ) AS DECIMAL ) * 100 / CAST( SUM( SR.GOAL ) AS DECIMAL ) )
                                                        , 0  ) AS NUMERIC(22,3) 
                                                  )
                                          FROM RESULT_ALL SR WITH(NOLOCK)
                                          WHERE SR.YYYYMM     = SUBSTRING( @ac_yyyymm, 1, 4 ) + '16'
                                            AND SR.TEAM_SEQ   = R.TEAM_SEQ
                                            AND SR.PRDGRP_SEQ = R.PRDGRP_SEQ
                                            AND SR.HOSPT_SEQ  = R.HOSPT_SEQ
                                            AND SR.USER_SEQ   = R.USER_SEQ
                                        )
                        AND RT.USE_YN = 'Y'
                  ) 
                  , R.PER_WRK =
                  (
                      SELECT
                      ( C.USER_WRK_DAY * 100 / FULL_WRK_DAY ) AS WRK_PER
                      FROM (
                          SELECT
                              DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0101', SUBSTRING( @ac_yyyymm, 1, 4 ) + '1231' ) AS FULL_WRK_DAY
                            , (
                                CASE WHEN FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ) > SUBSTRING( @ac_yyyymm, 1, 4 ) + '0101' THEN DATEDIFF( day, FORMAT( U.WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ), SUBSTRING( @ac_yyyymm, 1, 4 ) + '1231' )
                                    ELSE DATEDIFF( day, SUBSTRING( @ac_yyyymm, 1, 4 ) + '0101', SUBSTRING( @ac_yyyymm, 1, 4 ) + '1231' )
                                END
                            ) AS USER_WRK_DAY
                          FROM USER_MGR U WITH(NOLOCK)
                          WHERE U.SEQ     = R.USER_SEQ
                            AND U.USE_YN  = 'Y'
                      ) C
                  )
                  , R.PER_GRP =
                  (
                      SELECT
                        PB.PERCENTAGE
                      FROM PER_BRAND_MGR PB WITH(NOLOCK)
                      WHERE PB.TEAM_SEQ   = R.TEAM_SEQ
                        AND PB.PRDGRP_SEQ = R.PRDGRP_SEQ
                        AND PB.YYYY       = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PB.CYCLE      = 1
                        AND PB.USE_YN     = 'Y'
                  )
                  , R.PER_YEAR =
                  (
                      SELECT
                        PY.PERCENTAGE
                      FROM PER_YEAR_MGR PY WITH(NOLOCK)
                      WHERE PY.YYYY     = SUBSTRING( @ac_yyyymm, 1, 4 )
                        AND PY.CYCLE    = 1
                        AND PY.USE_YN   = 'Y'
                  )
                FROM RESULT_ALL R
                WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '20'
              ;
              
              --||■■■[인센티브 총합 계산 : 월 = 21]■■■ 월( 17 ~ 20 ) 값을 더함 ACTUAL 컬럼에 삽입
              SET @StepID = '15';      --// STEP번호
              MERGE INTO RESULT_ALL AS R
              USING (
                  SELECT
                      YYYYMM
                    , TEAM_SEQ
                    , PRDGRP_SEQ
                    , USER_SEQ
                    , HOSPT_SEQ
                    , GOAL
                    , ACTUAL
                  FROM RESULT_ALL WITH(NOLOCK)
                  WHERE YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '16'
                    AND USE_YN = 'Y'                  
              ) S ON (
                    R.YYYYMM = SUBSTRING( @ac_yyyymm, 1, 4 ) + '21'
                AND S.TEAM_SEQ    = R.TEAM_SEQ
                AND S.PRDGRP_SEQ  = R.PRDGRP_SEQ
                AND S.USER_SEQ    = R.USER_SEQ
                AND S.HOSPT_SEQ   = R.HOSPT_SEQ
              )
              WHEN NOT MATCHED THEN
                INSERT(
                    YYYYMM      -- 년월
                  , TEAM_SEQ    -- 팀_시퀀스
                  , PRDGRP_SEQ  -- 제품그룹_시퀀스
                  , HOSPT_SEQ   -- 병원_시퀀스
                  , USER_SEQ    -- 사용자_시퀀스
                  , GOAL        -- 목표
                  , ACTUAL      -- 달성
                  , REMK        -- 비고
                  , USE_YN      -- 사용여부
                  , INS_DT      -- 삽입일
                  , INS_ID      -- 삽입자
                  , INS_IP      -- 삽입자IP
                ) VALUES (
                    SUBSTRING( @ac_yyyymm, 1, 4 ) + '21'      -- 년월
                  , S.TEAM_SEQ              -- 팀_시퀀스
                  , S.PRDGRP_SEQ            -- 제품그룹_시퀀스
                  , S.HOSPT_SEQ             -- 병원_시퀀스
                  , S.USER_SEQ              -- 사용자_시퀀스
                  , 0                       -- 목표
                  , 0                       -- 달성
                  , 'INCENTIVE TOTAL'       -- 비고
                  , 'Y'                     -- 사용여부
                  , GETDATE()               -- 삽입일
                  , 'PRCD'                  -- 삽입자
                  , 'localhost'             -- 삽입자IP
                )
              ;
              
            END; --// IF SUBSTRING( @ac_yyyymm, 5, 2 ) = '12'
            
            IF (@ErrorNum = 0)
            BEGIN
                SET @ErrorMsg = 'Successful completion of ['+CAST(@@ROWCOUNT AS VARCHAR(10))+']';
            END
            
        END TRY
        BEGIN CATCH
            --// 오류정보
            SELECT @ErrorNum = ERROR_NUMBER(), @ErrorMsg = @StepID+'.'+ERROR_MESSAGE();

            SET @StepID = '11';      --// STEP번호
        END CATCH
    END
    
    IF (@ErrorNum = 0)
    BEGIN
        SET @ErrorMsg = @StepID+'.'+N'Was successfully completed.';       --// 처리결과
    END   
            
    /* RETURN VALUE 설정 */    
    SET @rn_err_cd      = @ErrorNum;        --// [RET]1.오류번호
    SET @rc_err_msg     = @ErrorMsg;        --// [RET]2.처리결과
    
    RETURN 0;
    

SET NOCOUNT OFF;

END

GO