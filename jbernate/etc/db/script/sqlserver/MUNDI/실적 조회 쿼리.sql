--||■■■[1CYCLE 인센티브 계산 : 월 = 17]■■■ ACTUAL 컬럼에 삽입
/*
UPDATE RESULT_ALL 
  SET USE_YN = 'N' 
    , UPD_DT = GETDATE()
    , UPD_ID = 'PR_MAKE_RESULT DELETE'
    , UPD_IP = 'localhost'
WHERE TEAM_SEQ = 
  AND PRDGRP_SEQ =
  AND 
  AND USE_YN = 'Y'
;
*/

--|| ▣▣▣ 해당 Cycle의 %를 LAUNCH / GROWTH VALUE 구함
SELECT 
  R.VALUE
FROM RATE_MGR R WITH(NOLOCK)
INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
   ON G.RATE_CD = R.RATE_CD
  AND G.SEQ = 14
WHERE R.YYYYMM BETWEEN '201505' AND '201508'
  AND R.ACHIEV = 222
  AND R.USE_YN = 'Y'
;

--|| ▣▣▣ 근무일수% = ( ROUND( ( CYCLE FULL DAY - 입사일 차이 ) * 100 / CYCLE FULL DAY, 1 ) )
SELECT
( C.USER_WRK_DAY * 100 / FULL_WRK_DAY ) AS WRK_PER
FROM (
    SELECT
        U.WRK_ST_DT
      , CAST( '20150901' AS DATETIME ) AS DT
      , DATEDIFF( day, '20150501', '20150831' ) AS FULL_WRK_DAY
      , (
          CASE WHEN '20150701' > '20150501' THEN DATEDIFF( day, '20150701', '20150831' )
              ELSE DATEDIFF( day, '20150501', '20150831' )
          END
      ) AS USER_WRK_DAY
    FROM USER_MGR U WITH(NOLOCK)
    WHERE U.USE_YN = 'Y'
    AND U.EMP_CD = 'TEST_EMP_CD_1'
) C
;
SELECT FORMAT( WRK_ST_DT, 'yyyyMMdd', 'ko-KR' ) FROM USER_MGR WITH(NOLOCK);


--|| ▣▣▣ 팀/브랜드/Cycle별 Incentive percentage
SELECT
  PERCENTAGE
FROM PER_BRAND_MGR WITH(NOLOCK)
WHERE TEAM_SEQ = 5
  AND PRDGRP_SEQ = 12
  AND YYYY = '2015'
  AND CYCLE = 1
  AND USE_YN = 'Y'
;

--|| ▣▣▣ Cycle별 Incentive percentage
SELECT
  PERCENTAGE
FROM PER_YEAR_MGR WITH(NOLOCK)
WHERE YYYY = '2015'
  AND CYCLE = 1
  AND USE_YN = 'Y'
;

SELECT * FROM PER_YEAR_MGR WITH(NOLOCK);

SELECT * FROM RATE_MGR WITH(NOLOCK);
SELECT * FROM PRDGRP_MGR WITH(NOLOCK);

--|| ▣▣▣ 실적 조회
SELECT
    U.EMP_CD
  , U.USER_NM
  , G.PRDGRP_NM
  , R.YYYYMM
  , SUM( R.GOAL )   AS GOAL
  , SUM( R.ACTUAL ) AS ACTUAL
FROM RESULT_ALL R WITH(NOLOCK)
INNER JOIN USER_MGR U WITH(NOLOCK)
  ON  U.SEQ = R.USER_SEQ
 AND  U.USE_YN = 'Y'
INNER JOIN PRDGRP_MGR G WITH(NOLOCK)
  ON G.SEQ = R.PRDGRP_SEQ
 AND G.USE_YN = 'Y'
INNER JOIN HOSPT_MGR H WITH(NOLOCK)
  ON H.SEQ = R.HOSPT_SEQ
 AND H.USE_YN = 'Y'  
WHERE SUBSTRING( R.YYYYMM, 1, 4 ) = '2015'
  AND R.USE_YN    = 'Y'
  AND PRDGRP_SEQ  = 12
GROUP BY
    R.YYYYMM
  , U.EMP_CD
  , U.USER_NM
  , G.PRDGRP_NM
ORDER BY
    U.USER_NM ASC
  , R.YYYYMM ASC
;

--|| ▣▣▣ 실적 조회 > PIVOT
SELECT 'YYYYMM' AS YYYMM, [201504], [201505], [201506]
FROM (
    SELECT
        R.YYYYMM
      , N'홍길동' AS USER_NM
      , R.GOAL
    FROM RESULT_ALL R WITH(NOLOCK)
) AS SOURCETABLE
PIVOT(
  AVG( GOAL )
  FOR YYYYMM IN ( [201504], [201505], [201506] )
) AS PIVOTTABLE
;

-------------------

DECLARE @PRDGRP_NM nvarchar(2000)
SET @PRDGRP_NM = ''
SELECT @PRDGRP_NM = @PRDGRP_NM + '[' + PRDGRP_NM + '],'
   FROM (
            SELECT DISTINCT PRDGRP_NM FROM PRDGRP_MGR G WITH(NOLOCK)
            WHERE USE_YN = 'Y'
  ) A
;

PRINT @PRDGRP_NM;

SET @PRDGRP_NM = LEFT(@PRDGRP_NM, LEN(@PRDGRP_NM) - 1)
EXEC ('SELECT 
          R.GOAL
        , G.PRDGRP_NM
        FROM RESULT_ALL R WITH(NOLOCK)
        INNER JOIN PRDGRP_MGR G
           ON G.SEQ = R.PRDGRP_SEQ
          AND R.USE_YN = ''Y''
        PIVOT ( SUM(R.GOAL) FOR G.PRDGRP_NM IN (' + @PRDGRP_NM + ')
        ) AS PVT')
GO

------------------------

SELECT 
          R.GOAL
        , G.PRDGRP_NM
        FROM RESULT_ALL R WITH(NOLOCK)
        INNER JOIN PRDGRP_MGR G
           ON G.SEQ = R.PRDGRP_SEQ
          AND R.USE_YN = 'Y'
        PIVOT ( SUM(R.GOAL) FOR G.PRDGRP_NM IN ( [제품그룹테스트_1] )
        ) AS PVT

/*--|| ▣▣▣ RATE UPDATE
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
  WHERE R.YYYYMM = '201506'
;
*/

/*--|| ▣▣▣ 목표조회
SELECT * FROM RESULT_ALL R WITH(NOLOCK)
INNER JOIN(
SELECT
     G.YYYY
   , G.TEAM_SEQ
   , G.PRDGRP_SEQ
   , G.USER_SEQ
   , SUM(
      CASE  WHEN SUBSTRING( '201506', 5, 2 ) = '01' THEN G.MON_1
            WHEN SUBSTRING( '201506', 5, 2 ) = '02' THEN G.MON_2
            WHEN SUBSTRING( '201506', 5, 2 ) = '03' THEN G.MON_3
            WHEN SUBSTRING( '201506', 5, 2 ) = '04' THEN G.MON_4
            WHEN SUBSTRING( '201506', 5, 2 ) = '05' THEN G.MON_5
            WHEN SUBSTRING( '201506', 5, 2 ) = '06' THEN G.MON_6
            WHEN SUBSTRING( '201506', 5, 2 ) = '07' THEN G.MON_7
            WHEN SUBSTRING( '201506', 5, 2 ) = '08' THEN G.MON_8
            WHEN SUBSTRING( '201506', 5, 2 ) = '09' THEN G.MON_9
            WHEN SUBSTRING( '201506', 5, 2 ) = '10' THEN G.MON_10
            WHEN SUBSTRING( '201506', 5, 2 ) = '11' THEN G.MON_11
            WHEN SUBSTRING( '201506', 5, 2 ) = '12' THEN G.MON_12
            ELSE 0
      END
   ) AS GOAL
   FROM GOAL_MGR G WITH(NOLOCK)
   WHERE USE_YN = 'Y'
   GROUP BY
      G.YYYY
    , G.TEAM_SEQ
    , G.PRDGRP_SEQ
    , G.USER_SEQ
) AS G
   ON G.TEAM_SEQ    = R.TEAM_SEQ
  AND G.PRDGRP_SEQ  = R.PRDGRP_SEQ
  AND G.USER_SEQ    = R.USER_SEQ
  AND G.YYYY        = SUBSTRING( R.YYYYMM, 1, 4 )
;
*/

/*--|| ▣▣▣ 실적조회
SELECT 
    A.YYYYMM                    -- 년월
  , T.SEQ AS TEAM_SEQ           -- 팀_시퀀스
  , G.SEQ AS PRDGRP_SEQ         -- 제품그룹_시퀀스
  , A.HOSPT_SEQ                 -- 병원_시퀀스
  , A.USER_SEQ                  -- 사용자_시퀀스
  -- GOAL
  , SUM( A.SAL_AMT ) AS ACTUAL  -- 달성
  -- PERCENTAGE
  -- RATE
  -- PAYROLL
  , '' AS REMK                  -- 비고
  , 'Y' AS USE_YN               -- 사용여부
  , GETDATE() AS INS_DT         -- 삽입일
  , 'PRCD' AS INS_ID            -- 삽입자
  , 'localhost' AS INS_IP       -- 삽입자IP
  , GETDATE() AS UPD_DT         -- 수정일
  , 'PRCD' AS UPD_ID            -- 수정자
  , 'localhost' AS UPD_IP       -- 수정자IP
  /*
  , A.SEQ           -- 시퀀스
  , A.WHOLESAL_NM   -- 도매명
  , A.SAL_LOC_NM    -- 매출처
  , A.ADDR          -- 주소
  , A.MFDS_NO       -- 식약처번호
  , A.ZIP_CD        -- 우편번호
  , A.STANDARD      -- 규격
  , A.SAL_CNT       -- 수량
  , A.UNIT_PRC      -- 단가
  , A.SAL_AMT       -- 금액
  , A.BRANCH_NM     -- 지점
  , A.PACK_DESC     -- 완포장_소분
  , A.BOX_CNT       -- 수량_BOX
  , A.CONV_CNT      -- 환산수량
  , A.MOD_YN        -- 수정가능여부
  , A.REMK          -- 비고
  , A.USE_YN        -- 사용여부
  */
  FROM ACTUAL_MGR A WITH(NOLOCK)        -- 실적관리
  INNER JOIN PRD_MGR P WITH(NOLOCK)     -- 제품관리
    ON P.SEQ = A.PRD_SEQ
  INNER JOIN PRDGRP_MGR G WITH(NOLOCK)  -- 제품그룹관리
    ON G.SEQ = P.PRDGRP_SEQ
  INNER JOIN USER_MGR U WITH(NOLOCK)    -- 사용자관리
    ON U.SEQ = A.USER_SEQ
  INNER JOIN TEAM_MGR T WITH(NOLOCK)    -- 팀관리
    ON T.SEQ = U.TEAM_SEQ
  WHERE A.YYYYMM = '201506'
  GROUP BY  A.YYYYMM
          , T.SEQ
          , G.SEQ
          , A.HOSPT_SEQ
          , A.USER_SEQ
          , A.PRD_SEQ
;
*/