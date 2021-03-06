SELECT
    /*D.EMP_CD AS [EMP_CD]
  , D.USER_NM AS [USER_NM]
  , D.PRDGRP_NM AS [PRDGRP_NM]
  , D.YYYYMM  AS [YYYYMM]
  , D.GOAL  AS [GOAL]
  , D.ACTUAL  AS [ACTUAL]
  */
    EMP_CD
  , USER_NM
  , YYYYMM
  , [제품그룹테스트_1]
FROM ( 
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
) D
PIVOT(
    SUM( GOAL )
    FOR PRDGRP_NM IN ( [제품그룹테스트_1] )
) PVTRTN
;

--------------

SELECT
    EMP_CD
  , USER_NM
  --, YYYYMM
  , [제품그룹테스트_1;GOAL;04]
  , [제품그룹테스트_1;ACTUAL;04]
  , [제품그룹테스트_1;PERCENTAGE;04]
  , [제품그룹테스트_1;GOAL;05]
  , [제품그룹테스트_1;ACTUAL;05]
  , [제품그룹테스트_1;PERCENTAGE;05]
  , [제품그룹테스트_1;GOAL;06]
  , [제품그룹테스트_1;ACTUAL;06]
  , [제품그룹테스트_1;PERCENTAGE;06]
FROM(
    SELECT
        EMP_CD
      , USER_NM
      --, YYYYMM
      , PRDGRP_NM + ';' + COL + ';' + SUBSTRING( YYYYMM, 5, 2 ) AS COL
      , VALUE
    FROM ( 
        SELECT
            U.EMP_CD
          , U.USER_NM
          , G.PRDGRP_NM
          , R.YYYYMM
          , CAST( SUM( R.GOAL ) AS NUMERIC(22,3) )  AS GOAL
          , CAST( SUM( R.ACTUAL ) AS NUMERIC(22,3) )  AS ACTUAL
          --, SUM( R.GOAL ) AS GOAL
          --, SUM( R.ACTUAL ) AS ACTUAL
          , CAST
                 (
                  ROUND( 
                        ( CAST( SUM( ACTUAL ) AS DECIMAL ) * 100 / CAST( SUM( GOAL ) AS DECIMAL ) )
                  , 0  ) AS NUMERIC(22,3) 
        ) AS PERCENTAGE
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
    ) D
    UNPIVOT(
      VALUE
      FOR COL IN ( GOAL, ACTUAL, PERCENTAGE )
      --FOR COL IN ( GOAL, ACTUAL )
    ) UNPIV
) S
PIVOT(
    SUM( VALUE )
    FOR COL IN (    [제품그룹테스트_1;GOAL;04]
                  , [제품그룹테스트_1;ACTUAL;04]
                  , [제품그룹테스트_1;PERCENTAGE;04]
                  , [제품그룹테스트_1;GOAL;05]
                  , [제품그룹테스트_1;ACTUAL;05]
                  , [제품그룹테스트_1;PERCENTAGE;05]
                  , [제품그룹테스트_1;GOAL;06]
                  , [제품그룹테스트_1;ACTUAL;06]
                  , [제품그룹테스트_1;PERCENTAGE;06])
) PIV
;

------------------------------------------------------------

DECLARE   @V_PRDGRP_NM NVARCHAR(128)  -- 제품그룹명
        , @V_IDX INT                  -- 월
        , @V_STR NVARCHAR(MAX)        -- PIVOT STRING
        , @V_SQL NVARCHAR(MAX)        -- 쿼리
;

DECLARE CURSOR_01 CURSOR LOCAL FOR

    SELECT DISTINCT PG.PRDGRP_NM FROM GOAL_MGR G WITH(NOLOCK)
    INNER JOIN PRDGRP_MGR PG WITH(NOLOCK)
       ON PG.SEQ    = G.PRDGRP_SEQ
      AND PG.USE_YN = 'Y'
    WHERE G.YYYY      = '2015'
      AND G.TEAM_SEQ  = 55
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
      SET @V_STR = @V_STR +  '[' + @V_PRDGRP_NM + ';' + 'GOAL'        + ';' + CONVERT( VARCHAR, FORMAT( @V_IDX , '0#' ) ) + ']';
      SET @V_STR = @V_STR + ',[' + @V_PRDGRP_NM + ';' + 'ACTUAL'      + ';' + CONVERT( VARCHAR, FORMAT( @V_IDX , '0#' ) ) + ']';
      SET @V_STR = @V_STR + ',[' + @V_PRDGRP_NM + ';' + 'PERCENTAGE'  + ';' + CONVERT( VARCHAR, FORMAT( @V_IDX , '0#' ) ) + ']';
      SET @V_IDX = @V_IDX + 1;
      --PRINT @V_STR;
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
                            , CAST( SUM( R.GOAL ) AS NUMERIC(22,3) )  AS GOAL
                            , CAST( SUM( R.ACTUAL ) AS NUMERIC(22,3) )  AS ACTUAL
                            , CAST
                                   (
                                    ROUND( 
                                          ( CAST( SUM( ACTUAL ) AS DECIMAL ) * 100 / CAST( SUM( GOAL ) AS DECIMAL ) )
                                    , 0  ) AS NUMERIC(22,3) 
                          ) AS PERCENTAGE
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
                          WHERE SUBSTRING( R.YYYYMM, 1, 4 ) = ''2015''
                            AND R.USE_YN    = ''Y''
                            AND PRDGRP_SEQ  = 12
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