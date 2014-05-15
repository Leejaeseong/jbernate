DROP TABLE TT_ONE_TABLE CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: �׽�Ʈ_�������̺� */
/**********************************/
CREATE TABLE TT_ONE_TABLE(
		SEQ                           		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL 
);

COMMENT ON TABLE TT_ONE_TABLE is '�׽�Ʈ_�������̺�';
COMMENT ON COLUMN TT_ONE_TABLE.SEQ is '����';
COMMENT ON COLUMN TT_ONE_TABLE.T_VARCHAR is '������';
COMMENT ON COLUMN TT_ONE_TABLE.T_DATE is '��¥';
COMMENT ON COLUMN TT_ONE_TABLE.T_CLOB is 'CLOB��';
COMMENT ON COLUMN TT_ONE_TABLE.T_BLOB is 'BLOB��';


ALTER TABLE TT_ONE_TABLE ADD CONSTRAINT IDX_TT_ONE_TABLE_PK PRIMARY KEY (SEQ);

/**********************************/
/* View Name: �׽�Ʈ_�������̺�  */
/**********************************/
CREATE OR REPLACE VIEW TT_ONE_TABLE_V(
	SEQ
		, V_VARCHAR
		, V_DATE
		, V_CLOB
		, V_BLOB
)
AS
	SELECT 
		SEQ
		, T_VARCHAR
		, T_DATE
		, T_CLOB
		, T_BLOB
	FROM TT_ONE_TABLE
;