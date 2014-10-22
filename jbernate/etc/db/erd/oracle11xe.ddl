DROP TABLE TT_ONE_TABLE CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 테스트_단일테이블 */
/**********************************/
CREATE TABLE TT_ONE_TABLE(
		SEQ                           		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL ,
		DEL_FLAG                      		VARCHAR2(1)		 NULL ,
		CRE_DATE                   		DATE		 NULL ,
		CRE_ID                     		NUMBER(16)		 NULL ,
		CRE_OBJ                    		VARCHAR2(100)		 NULL ,
		CRE_IP                     		VARCHAR2(64)		 NULL ,
		MOD_DATE                      		DATE		 NULL ,
		MOD_ID                        		NUMBER(16)		 NULL ,
		MOD_OBJ                       		VARCHAR2(100)		 NULL ,
		MOD_IP                        		VARCHAR2(64)		 NULL 
);
CREATE SEQUENCE  TT_ONE_TABLE_S  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
COMMENT ON TABLE TT_ONE_TABLE is '테스트_단일테이블';
COMMENT ON COLUMN TT_ONE_TABLE.SEQ is '순번';
COMMENT ON COLUMN TT_ONE_TABLE.T_VARCHAR is '문자형';
COMMENT ON COLUMN TT_ONE_TABLE.T_DATE is '날짜';
COMMENT ON COLUMN TT_ONE_TABLE.T_CLOB is 'CLOB형';
COMMENT ON COLUMN TT_ONE_TABLE.T_BLOB is 'BLOB형';
COMMENT ON COLUMN TT_ONE_TABLE.DEL_FLAG is '삭제여부';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_DATE is '생성일';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_ID is '생성자';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_OBJ is '생성객체';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_IP is '생성자IP';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_DATE is '수정일';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_ID is '수정자';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_OBJ is '수정객체';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_IP is '수정자IP';


ALTER TABLE TT_ONE_TABLE ADD CONSTRAINT IDX_TT_ONE_TABLE_PK PRIMARY KEY (SEQ);

/**********************************/
/* View Name: 테스트_단일테이블  */
/**********************************/
CREATE OR REPLACE VIEW TT_ONE_TABLE_V(
	SEQ
		, V_VARCHAR
		, V_DATE
		, V_CLOB
		, V_BLOB
		, DEL_FLAG
		, CRE_DATE
		, CRE_ID
		, CRE_OBJ
		, CRE_IP
		, MOD_DATE
		, MOD_ID
		, MOD_OBJ
		, MOD_IP 
)
AS
	SELECT 
		SEQ
		, T_VARCHAR
		, T_DATE
		, T_CLOB
		, T_BLOB
		, DEL_FLAG
		, CRE_DATE
		, CRE_ID
		, CRE_OBJ
		, CRE_IP
		, MOD_DATE
		, MOD_ID
		, MOD_OBJ
		, MOD_IP 
	FROM TT_ONE_TABLE
;


/**********************************/
/* Table Name: 테스트_1대N테이블_마스터 */
/**********************************/
CREATE TABLE TT_1N_MASTER(
		SEQ                           		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL ,
		DEL_FLAG                      		VARCHAR2(1)		 NULL ,
		CRE_DATE                   		DATE		 NULL ,
		CRE_ID                     		NUMBER(16)		 NULL ,
		CRE_OBJ                    		VARCHAR2(100)		 NULL ,
		CRE_IP                     		VARCHAR2(64)		 NULL ,
		MOD_DATE                      		DATE		 NULL ,
		MOD_ID                        		NUMBER(16)		 NULL ,
		MOD_OBJ                       		VARCHAR2(100)		 NULL ,
		MOD_IP                        		VARCHAR2(64)		 NULL 
);
CREATE SEQUENCE  TT_1N_MASTER_S  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
COMMENT ON TABLE TT_1N_MASTER is '테스트_1대N테이블_마스터';
COMMENT ON COLUMN TT_1N_MASTER.SEQ is '순번';
COMMENT ON COLUMN TT_1N_MASTER.T_VARCHAR is '문자형';
COMMENT ON COLUMN TT_1N_MASTER.T_DATE is '날짜';
COMMENT ON COLUMN TT_1N_MASTER.T_CLOB is 'CLOB형';
COMMENT ON COLUMN TT_1N_MASTER.T_BLOB is 'BLOB형';
COMMENT ON COLUMN TT_1N_MASTER.DEL_FLAG is '삭제여부';
COMMENT ON COLUMN TT_1N_MASTER.CRE_DATE is '생성일';
COMMENT ON COLUMN TT_1N_MASTER.CRE_ID is '생성자';
COMMENT ON COLUMN TT_1N_MASTER.CRE_OBJ is '생성객체';
COMMENT ON COLUMN TT_1N_MASTER.CRE_IP is '생성자IP';
COMMENT ON COLUMN TT_1N_MASTER.MOD_DATE is '수정일';
COMMENT ON COLUMN TT_1N_MASTER.MOD_ID is '수정자';
COMMENT ON COLUMN TT_1N_MASTER.MOD_OBJ is '수정객체';
COMMENT ON COLUMN TT_1N_MASTER.MOD_IP is '수정자IP';
ALTER TABLE TT_1N_MASTER ADD CONSTRAINT IDX_TT_1N_MASTER_PK PRIMARY KEY (SEQ);

/**********************************/
/* Table Name: 테스트_1대N테이블_하위 */
/**********************************/
CREATE TABLE TT_1N_SLAVE1(
		SEQ                           		NUMBER(16)		 NULL ,
		FK_MASTER  		               		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL ,		
		DEL_FLAG                      		VARCHAR2(1)		 NULL ,
		CRE_DATE                   		DATE		 NULL ,
		CRE_ID                     		NUMBER(16)		 NULL ,
		CRE_OBJ                    		VARCHAR2(100)		 NULL ,
		CRE_IP                     		VARCHAR2(64)		 NULL ,
		MOD_DATE                      		DATE		 NULL ,
		MOD_ID                        		NUMBER(16)		 NULL ,
		MOD_OBJ                       		VARCHAR2(100)		 NULL ,
		MOD_IP                        		VARCHAR2(64)		 NULL 
);
CREATE SEQUENCE  TT_1N_SLAVE1_S  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
COMMENT ON TABLE TT_1N_SLAVE1 is '테스트_1대N테이블_하위';
COMMENT ON COLUMN TT_1N_SLAVE1.SEQ is '순번';
COMMENT ON COLUMN TT_1N_SLAVE1.FK_MASTER is 'TT_1N_MASTER의 순번';
COMMENT ON COLUMN TT_1N_SLAVE1.T_VARCHAR is '문자형';
COMMENT ON COLUMN TT_1N_SLAVE1.T_DATE is '날짜';
COMMENT ON COLUMN TT_1N_SLAVE1.T_CLOB is 'CLOB형';
COMMENT ON COLUMN TT_1N_SLAVE1.T_BLOB is 'BLOB형';
COMMENT ON COLUMN TT_1N_SLAVE1.DEL_FLAG is '삭제여부';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_DATE is '생성일';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_ID is '생성자';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_OBJ is '생성객체';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_IP is '생성자IP';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_DATE is '수정일';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_ID is '수정자';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_OBJ is '수정객체';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_IP is '수정자IP';
ALTER TABLE TT_1N_SLAVE1 ADD CONSTRAINT IDX_TT_1N_SLAVE1_PK PRIMARY KEY (SEQ);
ALTER TABLE TT_1N_SLAVE1 ADD CONSTRAINT IDX_TT_1N_SLAVE1_FK0 FOREIGN KEY (FK_MASTER) REFERENCES TT_1N_MASTER (SEQ);

/**********************************/
/* Table Name: 테스트_1대1테이블_마스터 */
/**********************************/
CREATE TABLE TT_11_MASTER(
		SEQ                           		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL ,
		DEL_FLAG                      		VARCHAR2(1)		 NULL ,
		CRE_DATE                   		DATE		 NULL ,
		CRE_ID                     		NUMBER(16)		 NULL ,
		CRE_OBJ                    		VARCHAR2(100)		 NULL ,
		CRE_IP                     		VARCHAR2(64)		 NULL ,
		MOD_DATE                      		DATE		 NULL ,
		MOD_ID                        		NUMBER(16)		 NULL ,
		MOD_OBJ                       		VARCHAR2(100)		 NULL ,
		MOD_IP                        		VARCHAR2(64)		 NULL 
);
CREATE SEQUENCE  TT_11_MASTER_S  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
COMMENT ON TABLE TT_11_MASTER is '테스트_1대1테이블_마스터';
COMMENT ON COLUMN TT_11_MASTER.SEQ is '순번';
COMMENT ON COLUMN TT_11_MASTER.T_VARCHAR is '문자형';
COMMENT ON COLUMN TT_11_MASTER.T_DATE is '날짜';
COMMENT ON COLUMN TT_11_MASTER.T_CLOB is 'CLOB형';
COMMENT ON COLUMN TT_11_MASTER.T_BLOB is 'BLOB형';
COMMENT ON COLUMN TT_11_MASTER.DEL_FLAG is '삭제여부';
COMMENT ON COLUMN TT_11_MASTER.CRE_DATE is '생성일';
COMMENT ON COLUMN TT_11_MASTER.CRE_ID is '생성자';
COMMENT ON COLUMN TT_11_MASTER.CRE_OBJ is '생성객체';
COMMENT ON COLUMN TT_11_MASTER.CRE_IP is '생성자IP';
COMMENT ON COLUMN TT_11_MASTER.MOD_DATE is '수정일';
COMMENT ON COLUMN TT_11_MASTER.MOD_ID is '수정자';
COMMENT ON COLUMN TT_11_MASTER.MOD_OBJ is '수정객체';
COMMENT ON COLUMN TT_11_MASTER.MOD_IP is '수정자IP';
ALTER TABLE TT_11_MASTER ADD CONSTRAINT IDX_TT_11_MASTER_PK PRIMARY KEY (SEQ);

/**********************************/
/* Table Name: 테스트_1대1테이블_하위1 */
/**********************************/
CREATE TABLE TT_11_SLAVE1(
		SEQ                           		NUMBER(16)		 NULL ,
		T_VARCHAR                     		VARCHAR2(200)		 NULL ,
		T_DATE                        		DATE		 NULL ,
		T_CLOB                        		CLOB		 NULL ,
		T_BLOB                        		BLOB		 NULL ,
		DEL_FLAG                      		VARCHAR2(1)		 NULL ,
		CRE_DATE                   		DATE		 NULL ,
		CRE_ID                     		NUMBER(16)		 NULL ,
		CRE_OBJ                    		VARCHAR2(100)		 NULL ,
		CRE_IP                     		VARCHAR2(64)		 NULL ,
		MOD_DATE                      		DATE		 NULL ,
		MOD_ID                        		NUMBER(16)		 NULL ,
		MOD_OBJ                       		VARCHAR2(100)		 NULL ,
		MOD_IP                        		VARCHAR2(64)		 NULL 
);
CREATE SEQUENCE  TT_11_SLAVE1_S  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
COMMENT ON TABLE TT_11_SLAVE1 is '테스트_1대1테이블_하위1';
COMMENT ON COLUMN TT_11_SLAVE1.SEQ is '순번';
COMMENT ON COLUMN TT_11_SLAVE1.T_VARCHAR is '문자형';
COMMENT ON COLUMN TT_11_SLAVE1.T_DATE is '날짜';
COMMENT ON COLUMN TT_11_SLAVE1.T_CLOB is 'CLOB형';
COMMENT ON COLUMN TT_11_SLAVE1.T_BLOB is 'BLOB형';
COMMENT ON COLUMN TT_11_SLAVE1.DEL_FLAG is '삭제여부';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_DATE is '생성일';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_ID is '생성자';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_OBJ is '생성객체';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_IP is '생성자IP';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_DATE is '수정일';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_ID is '수정자';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_OBJ is '수정객체';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_IP is '수정자IP';
ALTER TABLE TT_11_SLAVE1 ADD CONSTRAINT IDX_TT_11_SLAVE1_PK PRIMARY KEY (SEQ);
ALTER TABLE TT_11_SLAVE1 ADD CONSTRAINT IDX_TT_11_SLAVE1_FK0 FOREIGN KEY (SEQ) REFERENCES TT_11_MASTER (SEQ);