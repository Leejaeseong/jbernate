/****** Object: Procedure [dbo].[PR_RESULT]   Script Date: 2015-04-03 오후 2:29:44 ******/
USE [MUNDI];
GO
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[PR_RESULT]') AND type IN ( N'P' ))
BEGIN
DROP PROCEDURE [dbo].[PR_RESULT];
END
GO
SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO
CREATE PROCEDURE [dbo].[PR_RESULT]
(
        @ac_yyyymm        VARCHAR(6)  = ''               --// 1.집계 년월
      , @ac_team_seq      INT         = 0                --// 2.팀 시퀀스
      , @ac_hospt_seq     INT         = 0                --// 3.병원 시퀀스
)
AS
/*******************************************************************************
    System Name   : MUNDI
    Module Name   : 먼디파머 집계 데이터 조회
    Program Name  : PR_RESULT_COLUMN
    Description   : 먼디파머 집계 데이터 조회
    
    Table Info.   : RESULT_ALL : 집계 결과

    In  Parameter : 1.[필수]집계 년월

    Out Parameter : 1.오류코드(0:정상,E:오류), 2.오류정보
    
    ****************************************************************************
    Execution     : EXEC dbo.PR_RESULT + 매개변수 설정 참조
    ****************************************************************************

    Modify History :
      DATE        AUTHOR             DESC.
    ----------  -------------  ------------------------------------------
    2015/06/24     ⓙⓢⓛ        Initial Release
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
    ;
    
    /*************************************************
        지역 변수 선언
    **************************************************/
    DECLARE   @V_PRDGRP_NM NVARCHAR(128)  -- 제품그룹명
                    , @V_IDX INT                  -- 월
                    , @V_FLD_IDX INT              -- 필드명
                    , @V_STR NVARCHAR(MAX)        -- PIVOT STRING
                    , @V_SQL NVARCHAR(MAX)        -- 쿼리
            ;
            
BEGIN
    SET NOCOUNT ON                                      -- 특별한 이유가 없다면 무조건 설정 합니다
    
    BEGIN
        BEGIN TRY
            
            DECLARE CURSOR_01 CURSOR LOCAL FOR

                SELECT DISTINCT PG.PRDGRP_NM FROM GOAL_MGR G WITH(NOLOCK)
                INNER JOIN PRDGRP_MGR PG WITH(NOLOCK)
                   ON PG.SEQ    = G.PRDGRP_SEQ
                  AND PG.USE_YN = 'Y'
                WHERE G.YYYY      = SUBSTRING( @ac_yyyymm, 1, 4 )
                  AND G.TEAM_SEQ  = @ac_team_seq
                  AND G.USE_YN    = 'Y'

            OPEN CURSOR_01;

            FETCH NEXT FROM CURSOR_01  INTO @V_PRDGRP_NM;

            SET @V_STR = '';
            WHILE @@FETCH_STATUS = 0 BEGIN    --//WHILE1

                SELECT @V_IDX = 1;
                WHILE @V_IDX <= 12
                BEGIN
                  IF LEN( @V_STR ) > 0
                  BEGIN
                    SET @V_STR = @V_STR + ',';
                  END
                  SET @V_STR = @V_STR +  'FIELD' + CONVERT(VARCHAR, @V_FLD_IDX );
                  SET @V_FLD_IDX = @V_FLD_IDX + 1;
                  SET @V_STR = @V_STR + ',FIELD' + CONVERT(VARCHAR, @V_FLD_IDX );
                  SET @V_FLD_IDX = @V_FLD_IDX + 1;
                  SET @V_STR = @V_STR + ',FIELD' + CONVERT(VARCHAR, @V_FLD_IDX );
                  SET @V_FLD_IDX = @V_FLD_IDX + 1;
                  SET @V_IDX = @V_IDX + 1;
                END;

             -- 다음행 삽입
             FETCH NEXT FROM CURSOR_01  INTO @V_PRDGRP_NM;
            END; --//WHILE1 END

            --//CURSOR CLOSE
            CLOSE CURSOR_01;
            DEALLOCATE CURSOR_01;

            SET @V_SQL = N'
                              SELECT
                                  EMP_CD
                                , USER_NM
                                , ' + @V_STR + '
                              FROM(
                                  SELECT
                                      EMP_CD
                                    , USER_NM
                                    , PRDGRP_NM + '';'' + COL + '';'' + SUBSTRING( YYYYMM, 5, 2 ) AS COL
                                    , VALUE
                                  FROM ( 
                                      SELECT
                                          U.EMP_CD
                                        , U.USER_NM
                                        , G.PRDGRP_NM
                                        , R.YYYYMM
                                        , 0 AS GOAL
                                        , 0 AS ACTUAL
                                        , 0 AS PERCENTAGE
                                      FROM RESULT_ALL R WITH(NOLOCK)
                                      INNER JOIN USER_MGR U WITH(NOLOCK)
                                        ON  U.SEQ = R.USER_SEQ
                                       AND  U.USE_YN = ''Y''
                                      INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
                                        ON G.SEQ = R.PRDGRP_SEQ
                                       AND G.USE_YN = ''Y''
                                      INNER JOIN HOSPT_MGR H WITH(NOLOCK)
                                        ON H.SEQ = R.HOSPT_SEQ
                                       AND H.USE_YN = ''Y''  
                                      WHERE SUBSTRING( R.YYYYMM, 1, 4 ) = ''' + SUBSTRING( @ac_yyyymm, 1, 4 ) + '''
                                        AND R.USE_YN    = ''Y''
                                      GROUP BY
                                          R.YYYYMM
                                        , U.EMP_CD
                                        , U.USER_NM
                                        , G.PRDGRP_NM
                                  ) D
                                  UNPIVOT(
                                    VALUE
                                    FOR COL IN ( GOAL, ACTUAL, PERCENTAGE )
                                  ) UNPIV
                              ) S
                              PIVOT(
                                  SUM( VALUE )
                                  FOR COL IN ( ' + @V_STR + ' )
                              ) PIV
            ';

            EXECUTE( @V_SQL );
            
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
    RETURN;
    

SET NOCOUNT OFF;

END

GO