DROP TABLE TT_ONE_TABLE CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 테스트_단일테이블 */
/**********************************/
CREATE TABLE TT_ONE_TABLE(
		SEQ                           		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL 
);

COMMENT ON TABLE TT_ONE_TABLE is '테스트_단일테이블';
COMMENT ON COLUMN TT_ONE_TABLE.SEQ is '순번';
COMMENT ON COLUMN TT_ONE_TABLE.T_VARCHAR is '문자형';
COMMENT ON COLUMN TT_ONE_TABLE.T_DATE is '날짜';
COMMENT ON COLUMN TT_ONE_TABLE.T_CLOB is 'CLOB형';
COMMENT ON COLUMN TT_ONE_TABLE.T_BLOB is 'BLOB형';



ALTER TABLE TT_ONE_TABLE ADD CONSTRAINT IDX_TT_ONE_TABLE_PK PRIMARY KEY (SEQ);