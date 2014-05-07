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