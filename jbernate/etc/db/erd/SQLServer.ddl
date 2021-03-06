USE [TT]
DROP TABLE TT_ONE_TABLE;

/**********************************/
/* Table Name: 테스트_단일테이블 */
/**********************************/
CREATE TABLE TT_ONE_TABLE(
		SEQ                           NUMERIC(16)		  ,
		T_VARCHAR                     VARCHAR(200)		NULL ,
		T_DATE                        DATE		        NULL ,
		T_CLOB                        VARCHAR(MAX)    NULL ,
		T_BLOB                        NVARCHAR(MAX)   NULL ,
		DEL_FLAG                      VARCHAR(1)		  NULL ,
		CRE_DATE                   		DATE		        NULL ,
		CRE_ID                     		NUMERIC(16)		  NULL ,
		CRE_OBJ                    		VARCHAR(100)		NULL ,
		CRE_IP                     		VARCHAR(64)		  NULL ,
		MOD_DATE                      DATE		        NULL ,
		MOD_ID                        NUMERIC(16)		  NULL ,
		MOD_OBJ                       VARCHAR(100)		NULL ,
		MOD_IP                        VARCHAR(64)		  NULL ,
    CONSTRAINT [PK_TT_ONE_TABLE]
    PRIMARY KEY CLUSTERED( 
      [SEQ]
    )
);
CREATE SEQUENCE TT_ONE_TABLE_S  MINVALUE 1 NO MAXVALUE INCREMENT BY 1 START WITH 1 CACHE 20;

/**********************************/
/* View Name: 테스트_단일테이블  */
/**********************************/
CREATE VIEW TT_ONE_TABLE_V AS
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
		SEQ                           NUMERIC(16)		,
		T_VARCHAR                     VARCHAR(200)	NULL ,
		T_DATE                        DATE		      NULL ,
		T_CLOB                        VARCHAR(MAX)	NULL ,
		T_BLOB                        NVARCHAR(MAX)	NULL ,
		DEL_FLAG                      VARCHAR(1)		NULL ,
		CRE_DATE                   		DATE		      NULL ,
		CRE_ID                     		NUMERIC(16)		NULL ,
		CRE_OBJ                    		VARCHAR(100)	NULL ,
		CRE_IP                     		VARCHAR(64)		NULL ,
		MOD_DATE                      DATE		      NULL ,
		MOD_ID                        NUMERIC(16)		NULL ,
		MOD_OBJ                       VARCHAR(100)	NULL ,
		MOD_IP                        VARCHAR(64)		NULL ,
    CONSTRAINT [PK_TT_1N_MASTER]
    PRIMARY KEY CLUSTERED( 
      [SEQ]
    )
);
CREATE SEQUENCE  TT_1N_MASTER_S  MINVALUE 1 NO MAXVALUE INCREMENT BY 1 START WITH 1 CACHE 20;

/**********************************/
/* Table Name: 테스트_1대N테이블_하위 */
/**********************************/
CREATE TABLE TT_1N_SLAVE1(
		SEQ                           NUMERIC(16)   ,
		FK_MASTER  		               	NUMERIC(16)	  ,
		T_VARCHAR                     VARCHAR(200)  NULL ,
		T_DATE                        DATE		      NULL ,
		T_CLOB                        VARCHAR(MAX)	NULL ,
		T_BLOB                        NVARCHAR(MAX)	NULL ,		
		DEL_FLAG                      VARCHAR(1)		NULL ,
		CRE_DATE                   		DATE		      NULL ,
		CRE_ID                     		NUMERIC(16)		NULL ,
		CRE_OBJ                    		VARCHAR(100)	NULL ,
		CRE_IP                     		VARCHAR(64)		NULL ,
		MOD_DATE                      DATE		      NULL ,
		MOD_ID                        NUMERIC(16)		NULL ,
		MOD_OBJ                       VARCHAR(100)	NULL ,
		MOD_IP                        VARCHAR(64)		NULL ,
    CONSTRAINT [PK_TT_1N_SLAVE1]
    PRIMARY KEY CLUSTERED( 
      [SEQ]
    ) ,
    CONSTRAINT FK_TT_1N_SLAVE1_MASTER FOREIGN KEY (FK_MASTER) 
    REFERENCES TT_1N_MASTER(SEQ) 
);
CREATE SEQUENCE  TT_1N_SLAVE1_S  MINVALUE 1 NO MAXVALUE INCREMENT BY 1 START WITH 1 CACHE 20;

/**********************************/
/* Table Name: 테스트_1대1테이블_마스터 */
/**********************************/
CREATE TABLE TT_11_MASTER(
		SEQ                           NUMERIC(16)		,
		T_VARCHAR                     VARCHAR(200)  NULL ,
		T_DATE                        DATE		      NULL ,
		T_CLOB                        VARCHAR(MAX)	NULL ,
		T_BLOB                        NVARCHAR(MAX)	NULL ,
		DEL_FLAG                      VARCHAR(1)		NULL ,
		CRE_DATE                   		DATE		      NULL ,
		CRE_ID                     		NUMERIC(16)		NULL ,
		CRE_OBJ                    		VARCHAR(100)	NULL ,
		CRE_IP                     		VARCHAR(64)		NULL ,
		MOD_DATE                      DATE		      NULL ,
		MOD_ID                        NUMERIC(16)		NULL ,
		MOD_OBJ                       VARCHAR(100)	NULL ,
		MOD_IP                        VARCHAR(64)		NULL ,
    CONSTRAINT [PK_TT_11_MASTER]
    PRIMARY KEY CLUSTERED( 
      [SEQ]
    )
);
CREATE SEQUENCE  TT_11_MASTER_S  MINVALUE 1 NO MAXVALUE INCREMENT BY 1 START WITH 1 CACHE 20;

/**********************************/
/* Table Name: 테스트_1대1테이블_하위1 */
/**********************************/
CREATE TABLE TT_11_SLAVE1(
		SEQ                           NUMERIC(16)		,
		T_VARCHAR                     VARCHAR(200)  NULL ,
		T_DATE                        DATE		      NULL ,
		T_CLOB                        VARCHAR(MAX)	NULL ,
		T_BLOB                        NVARCHAR(MAX)	NULL ,
		DEL_FLAG                      VARCHAR(1)		NULL ,
		CRE_DATE                   		DATE		      NULL ,
		CRE_ID                     		NUMERIC(16)		NULL ,
		CRE_OBJ                    		VARCHAR(100)	NULL ,
		CRE_IP                     		VARCHAR(64)		NULL ,
		MOD_DATE                      DATE		      NULL ,
		MOD_ID                        NUMERIC(16)		NULL ,
		MOD_OBJ                       VARCHAR(100)	NULL ,
		MOD_IP                        VARCHAR(64)		NULL ,
    CONSTRAINT [PK_TT_11_SLAVE1]
    PRIMARY KEY CLUSTERED( 
      [SEQ]
    ) ,
    CONSTRAINT FK_TT_11_SLAVE1_MASTER FOREIGN KEY (SEQ) 
    REFERENCES TT_11_MASTER(SEQ) 
);
CREATE SEQUENCE  TT_11_SLAVE1_S  MINVALUE 1 NO MAXVALUE INCREMENT BY 1 START WITH 1 CACHE 20;