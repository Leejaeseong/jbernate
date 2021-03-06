USE [CM]
GO
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[MN_MKJAVA_META_VI]') AND type IN ( N'V' ))
BEGIN
DROP VIEW [dbo].[MN_MKJAVA_META_VI];
END
GO
CREATE VIEW MN_MKJAVA_META_VI AS
SELECT 
    COL.TABLE_CATALOG                             AS DB_NM      --// DB명 AS DB_NM
  , COL.TABLE_NAME                                AS TAB_NM     --// 테이블명 AS TAB_NM
  , N''                                           AS TAB_DESC   --// 테이블설명 AS TAB_DESC : 차후 추가
  , COL.COLUMN_NAME                               AS COL_NM     --// 컬럼명 AS COL_NM
  , N''                                           AS COL_DESC   --// 컬럼설명 AS COL_DESC : 차후 추가
  , UPPER( DATA_TYPE )                            AS COL_TYPE   --// 컬럼타입 AS COL_TYPE
  , CONVERT( NUMERIC, CHARACTER_MAXIMUM_LENGTH )  AS COL_LEN    --// 컬럼크기 AS COL_LEN
  , CONVERT( NUMERIC, NUMERIC_PRECISION )         AS COL_PREC   --// 컬럼PRECISION AS COL_PREC
  , CONVERT( NUMERIC, NUMERIC_SCALE )             AS COL_SCALE  --// 컬럼SCALE AS COL_SCALE
  , SUBSTRING( IS_NULLABLE, 1, 1 )                AS COL_NULL   --// 컬럼NULLABLE AS COL_NULL ( Y/N )
  , CONVERT( NUMERIC, ORDINAL_POSITION )          AS COL_SEQ    --// 컬럼순서 AS COL_SEQ
  , ISNULL( PKT.IS_PK, 'N' )                      AS IS_PK      --// PK여부 AS IS_PK ( Y/N )
  , CONVERT( NUMERIC, 0 )                         AS R_CNT      -- 0 : 관계 컬럼 없음, 1 : ManyToOne, 2 : OneToOne => 사용 안 함
  , object_name( FKS.referenced_object_id ) + ',' + COL_NAME( FKS.referenced_object_id, FKS.referenced_column_id ) AS REF_TAB_N_COL --// 관계테이블,컬럼명 AS REF_TAB_N_COL( TT_TEST_B,SEQ 즉 테이블,컬럼 형태 )
  , ROW_NUMBER() OVER( ORDER BY COL.TABLE_CATALOG ) AS SEQ        --// SEQUENCE
FROM INFORMATION_SCHEMA.COLUMNS COL WITH(NOLOCK)
LEFT OUTER JOIN(
  SELECT  'Y' AS IS_PK
          , i.name AS IndexName
          , ic.OBJECT_ID
          , OBJECT_NAME(ic.OBJECT_ID)           AS TAB_NM
          , COL_NAME(ic.OBJECT_ID,ic.column_id) AS COL_NM
  FROM    sys.indexes AS i WITH(NOLOCK) 
    INNER JOIN sys.index_columns AS ic WITH(NOLOCK) 
      ON  i.OBJECT_ID = ic.OBJECT_ID
     AND  i.index_id = ic.index_id
  WHERE   i.is_primary_key = 1
) AS PKT
   ON PKT.TAB_NM  = COL.TABLE_NAME
  AND PKT.COL_NM  = COL.COLUMN_NAME
LEFT OUTER JOIN sys.foreign_key_columns AS FKS WITH(NOLOCK)
   ON object_name(FKS.parent_object_id) = COL.TABLE_NAME
  AND COL_NAME( FKS.parent_object_id, FKS.parent_column_id ) = COL.COLUMN_NAME
;