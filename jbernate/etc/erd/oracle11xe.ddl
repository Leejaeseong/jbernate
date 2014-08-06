DROP TABLE TT_ONE_TABLE CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: �׽�Ʈ_�������̺� */
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
COMMENT ON TABLE TT_ONE_TABLE is '�׽�Ʈ_�������̺�';
COMMENT ON COLUMN TT_ONE_TABLE.SEQ is '����';
COMMENT ON COLUMN TT_ONE_TABLE.T_VARCHAR is '������';
COMMENT ON COLUMN TT_ONE_TABLE.T_DATE is '��¥';
COMMENT ON COLUMN TT_ONE_TABLE.T_CLOB is 'CLOB��';
COMMENT ON COLUMN TT_ONE_TABLE.T_BLOB is 'BLOB��';
COMMENT ON COLUMN TT_ONE_TABLE.DEL_FLAG is '��������';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_DATE is '������';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_ID is '������';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_OBJ is '������ü';
COMMENT ON COLUMN TT_ONE_TABLE.CRE_IP is '������IP';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_DATE is '������';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_ID is '������';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_OBJ is '������ü';
COMMENT ON COLUMN TT_ONE_TABLE.MOD_IP is '������IP';


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
/* Table Name: �׽�Ʈ_1��N���̺�_������ */
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
COMMENT ON TABLE TT_1N_MASTER is '�׽�Ʈ_1��N���̺�_������';
COMMENT ON COLUMN TT_1N_MASTER.SEQ is '����';
COMMENT ON COLUMN TT_1N_MASTER.T_VARCHAR is '������';
COMMENT ON COLUMN TT_1N_MASTER.T_DATE is '��¥';
COMMENT ON COLUMN TT_1N_MASTER.T_CLOB is 'CLOB��';
COMMENT ON COLUMN TT_1N_MASTER.T_BLOB is 'BLOB��';
COMMENT ON COLUMN TT_1N_MASTER.DEL_FLAG is '��������';
COMMENT ON COLUMN TT_1N_MASTER.CRE_DATE is '������';
COMMENT ON COLUMN TT_1N_MASTER.CRE_ID is '������';
COMMENT ON COLUMN TT_1N_MASTER.CRE_OBJ is '������ü';
COMMENT ON COLUMN TT_1N_MASTER.CRE_IP is '������IP';
COMMENT ON COLUMN TT_1N_MASTER.MOD_DATE is '������';
COMMENT ON COLUMN TT_1N_MASTER.MOD_ID is '������';
COMMENT ON COLUMN TT_1N_MASTER.MOD_OBJ is '������ü';
COMMENT ON COLUMN TT_1N_MASTER.MOD_IP is '������IP';
ALTER TABLE TT_1N_MASTER ADD CONSTRAINT IDX_TT_1N_MASTER_PK PRIMARY KEY (SEQ);

/**********************************/
/* Table Name: �׽�Ʈ_1��N���̺�_���� */
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
COMMENT ON TABLE TT_1N_SLAVE1 is '�׽�Ʈ_1��N���̺�_����';
COMMENT ON COLUMN TT_1N_SLAVE1.SEQ is '����';
COMMENT ON COLUMN TT_1N_SLAVE1.FK_MASTER is 'TT_1N_MASTER�� ����';
COMMENT ON COLUMN TT_1N_SLAVE1.T_VARCHAR is '������';
COMMENT ON COLUMN TT_1N_SLAVE1.T_DATE is '��¥';
COMMENT ON COLUMN TT_1N_SLAVE1.T_CLOB is 'CLOB��';
COMMENT ON COLUMN TT_1N_SLAVE1.T_BLOB is 'BLOB��';
COMMENT ON COLUMN TT_1N_SLAVE1.DEL_FLAG is '��������';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_DATE is '������';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_ID is '������';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_OBJ is '������ü';
COMMENT ON COLUMN TT_1N_SLAVE1.CRE_IP is '������IP';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_DATE is '������';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_ID is '������';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_OBJ is '������ü';
COMMENT ON COLUMN TT_1N_SLAVE1.MOD_IP is '������IP';
ALTER TABLE TT_1N_SLAVE1 ADD CONSTRAINT IDX_TT_1N_SLAVE1_PK PRIMARY KEY (SEQ);
ALTER TABLE TT_1N_SLAVE1 ADD CONSTRAINT IDX_TT_1N_SLAVE1_FK0 FOREIGN KEY (FK_MASTER) REFERENCES TT_1N_MASTER (SEQ);

/**********************************/
/* Table Name: �׽�Ʈ_1��1���̺�_������ */
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
COMMENT ON TABLE TT_11_MASTER is '�׽�Ʈ_1��1���̺�_������';
COMMENT ON COLUMN TT_11_MASTER.SEQ is '����';
COMMENT ON COLUMN TT_11_MASTER.T_VARCHAR is '������';
COMMENT ON COLUMN TT_11_MASTER.T_DATE is '��¥';
COMMENT ON COLUMN TT_11_MASTER.T_CLOB is 'CLOB��';
COMMENT ON COLUMN TT_11_MASTER.T_BLOB is 'BLOB��';
COMMENT ON COLUMN TT_11_MASTER.DEL_FLAG is '��������';
COMMENT ON COLUMN TT_11_MASTER.CRE_DATE is '������';
COMMENT ON COLUMN TT_11_MASTER.CRE_ID is '������';
COMMENT ON COLUMN TT_11_MASTER.CRE_OBJ is '������ü';
COMMENT ON COLUMN TT_11_MASTER.CRE_IP is '������IP';
COMMENT ON COLUMN TT_11_MASTER.MOD_DATE is '������';
COMMENT ON COLUMN TT_11_MASTER.MOD_ID is '������';
COMMENT ON COLUMN TT_11_MASTER.MOD_OBJ is '������ü';
COMMENT ON COLUMN TT_11_MASTER.MOD_IP is '������IP';
ALTER TABLE TT_11_MASTER ADD CONSTRAINT IDX_TT_11_MASTER_PK PRIMARY KEY (SEQ);

/**********************************/
/* Table Name: �׽�Ʈ_1��1���̺�_����1 */
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
COMMENT ON TABLE TT_11_SLAVE1 is '�׽�Ʈ_1��1���̺�_����1';
COMMENT ON COLUMN TT_11_SLAVE1.SEQ is '����';
COMMENT ON COLUMN TT_11_SLAVE1.T_VARCHAR is '������';
COMMENT ON COLUMN TT_11_SLAVE1.T_DATE is '��¥';
COMMENT ON COLUMN TT_11_SLAVE1.T_CLOB is 'CLOB��';
COMMENT ON COLUMN TT_11_SLAVE1.T_BLOB is 'BLOB��';
COMMENT ON COLUMN TT_11_SLAVE1.DEL_FLAG is '��������';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_DATE is '������';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_ID is '������';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_OBJ is '������ü';
COMMENT ON COLUMN TT_11_SLAVE1.CRE_IP is '������IP';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_DATE is '������';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_ID is '������';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_OBJ is '������ü';
COMMENT ON COLUMN TT_11_SLAVE1.MOD_IP is '������IP';
ALTER TABLE TT_11_SLAVE1 ADD CONSTRAINT IDX_TT_11_SLAVE1_PK PRIMARY KEY (SEQ);
ALTER TABLE TT_11_SLAVE1 ADD CONSTRAINT IDX_TT_11_SLAVE1_FK0 FOREIGN KEY (SEQ) REFERENCES TT_11_MASTER (SEQ);