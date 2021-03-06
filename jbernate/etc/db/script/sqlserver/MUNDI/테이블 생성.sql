
DROP TABLE RATE_MGR
go

DROP TABLE RATE_MGR_EXL_TMP
go

DROP TABLE GOAL_MGR
go

DROP TABLE USER_MGR
go

DROP TABLE TEAM_MGR
go

DROP TABLE GOAL_MGR_EXL_TMP
go

DROP TABLE ACTUAL_MGR
go

DROP TABLE HOSPT_MGR
go

DROP TABLE PRD_MGR
go

DROP TABLE PRDGRP_MGR
go

DROP TABLE LOG_MOD_HISTORY
go

DROP TABLE RESULT_ALL
go

CREATE TABLE ACTUAL_MGR
( 
	SEQ                  numeric(16)  NOT NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	PRDGRP_SEQ           numeric(16)  NOT NULL ,
	WHOLESAL_NM          nvarchar(128)  NULL ,
	PRD_SEQ              numeric(16)  NOT NULL ,
	SAL_LOC_NM           nvarchar(128)  NULL ,
	ADDR                 nvarchar(256)  NULL ,
	ZIP_CD               varchar(30)  NULL ,
	SAL_CNT              numeric(22,3)  NULL ,
	UNIT_PRC             numeric(22,3)  NULL ,
	SAL_AMT              numeric(22,3)  NULL ,
	BRANCH_NM            nvarchar(128)  NULL ,
	YYYYMM               varchar(6)  NULL ,
	MOD_YN               varchar(1)  NOT NULL ,
	HOSPT_SEQ            numeric(16)  NULL 
)
go

ALTER TABLE ACTUAL_MGR
	ADD CONSTRAINT XPK?ㅼ쟻愿由?PRIMARY KEY (SEQ ASC)
go

CREATE TABLE GOAL_MGR
( 
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	YYYY                 varchar(4)  NULL ,
	TEAM_SEQ             numeric(16)  NOT NULL ,
	USER_SEQ             numeric(16)  NOT NULL ,
	PRDGRP_SEQ           numeric(16)  NOT NULL ,
	HOSPT_SEQ            numeric(16)  NOT NULL ,
	MON_1                numeric(22,3)  NULL ,
	MON_2                numeric(22,3)  NULL ,
	MON_3                numeric(22,3)  NULL ,
	MON_4                numeric(22,3)  NULL ,
	MON_5                numeric(22,3)  NULL ,
	MON_6                numeric(22,3)  NULL ,
	MON_7                numeric(22,3)  NULL ,
	MON_8                numeric(22,3)  NULL ,
	MON_9                numeric(22,3)  NULL ,
	MON_10               numeric(22,3)  NULL ,
	MON_11               numeric(22,3)  NULL ,
	MON_12               numeric(22,3)  NULL 
)
go

ALTER TABLE GOAL_MGR
	ADD CONSTRAINT XPK紐⑺몴愿由?PRIMARY KEY (SEQ ASC)
go


CREATE TABLE GOAL_MGR_EXL_TMP
( 
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	YYYY                 varchar(4)  NULL ,
	TEAM_SEQ             numeric(16)  NOT NULL ,
	USER_SEQ             numeric(16)  NOT NULL ,
	PRDGRP_SEQ           numeric(16)  NOT NULL ,
	HOSPT_SEQ            numeric(16)  NOT NULL ,
	MON_1                numeric(22,3)  NULL ,
	MON_2                numeric(22,3)  NULL ,
	MON_3                numeric(22,3)  NULL ,
	MON_4                numeric(22,3)  NULL ,
	MON_5                numeric(22,3)  NULL ,
	MON_6                numeric(22,3)  NULL ,
	MON_7                numeric(22,3)  NULL ,
	MON_8                numeric(22,3)  NULL ,
	MON_9                numeric(22,3)  NULL ,
	MON_10               numeric(22,3)  NULL ,
	MON_11               numeric(22,3)  NULL ,
	MON_12               numeric(22,3)  NULL 
)
go

ALTER TABLE GOAL_MGR_EXL_TMP
	ADD CONSTRAINT XPK紐⑺몴愿由??묒?_TMP PRIMARY KEY (SEQ ASC)
go



CREATE TABLE HOSPT_MGR
( 
	SEQ                  numeric(16)  NOT NULL ,
	PRDGRP_CD            varchar(30)  NULL ,
	PRDGRP_NM            nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	ADDR                 nvarchar(256)  NULL 
)
go

ALTER TABLE HOSPT_MGR
	ADD CONSTRAINT XPK蹂묒썝愿由?PRIMARY KEY (SEQ ASC)
go


CREATE TABLE LOG_MOD_HISTORY
( 
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	RESULT_SEQ           numeric(16)  NOT NULL 
)
go

ALTER TABLE LOG_MOD_HISTORY
	ADD CONSTRAINT XPK蹂寃쎌씠??PRIMARY KEY (SEQ ASC)
go



CREATE TABLE PRD_MGR
( 
	PRD_CD               varchar(30)  NULL ,
	PRD_NM               nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	PRDGRP_SEQ           numeric(16)  NOT NULL 
)
go

ALTER TABLE PRD_MGR
	ADD CONSTRAINT XPK?쒗뭹愿由?PRIMARY KEY (SEQ ASC)
go


CREATE TABLE PRDGRP_MGR
( 
	SEQ                  numeric(16)  NOT NULL ,
	PRDGRP_CD            varchar(30)  NULL ,
	PRDGRP_NM            nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	RATE_CD              varchar(1)  NOT NULL 
)
go

ALTER TABLE PRDGRP_MGR
	ADD CONSTRAINT XPK?쒗뭹洹몃９愿由?PRIMARY KEY (SEQ ASC)
go


CREATE TABLE RATE_MGR
( 
	SEQ                  numeric(16)  NOT NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	RATE_CD              varchar(1)  NOT NULL ,
	ACHIEV               numeric(22,3)  NULL ,
	VALUE                numeric(22,3)  NULL ,
	YYYYMM               varchar(6)  NULL 
)
go

ALTER TABLE RATE_MGR
	ADD CONSTRAINT XPKRATE愿由?PRIMARY KEY (SEQ ASC)
go


CREATE TABLE RATE_MGR_EXL_TMP
( 
	SEQ                  numeric(16)  NOT NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	RATE_CD              varchar(1)  NOT NULL ,
	ACHIEV               numeric(22,3)  NULL ,
	VALUE                numeric(22,3)  NULL ,
	YYYYMM               varchar(6)  NULL 
)
go

ALTER TABLE RATE_MGR_EXL_TMP
	ADD CONSTRAINT XPKRATE愿由??묒?_TMP PRIMARY KEY (SEQ ASC)
go


CREATE TABLE RESULT_ALL
( 
	PRD_CD               varchar(30)  NULL ,
	PRD_NM               nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	PRDGRP_SEQ           numeric(16)  NOT NULL ,
	TEAM_SEQ             numeric(16)  NULL ,
	USER_SEQ             numeric(16)  NULL ,
	YYYYMM               varchar(6)  NULL ,
	GOAL                 numeric(22,3)  NULL ,
	ACTUAL               numeric(22,3)  NULL ,
	PERCENTAGE           numeric(22,3)  NULL ,
	HOSPT_SEQ            numeric(16)  NULL ,
	RATE                 numeric(22,3)  NULL ,
	PAYROLL              numeric(22,3)  NULL 
)
go

ALTER TABLE RESULT_ALL
	ADD CONSTRAINT XPK寃곌낵 PRIMARY KEY (SEQ ASC)
go


CREATE TABLE TEAM_MGR
( 
	SEQ                  numeric(16)  NOT NULL ,
	TEAM_CD              varchar(30)  NULL ,
	TEAM_NM              nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL 
)
go

ALTER TABLE TEAM_MGR
	ADD CONSTRAINT XPK?愿由?PRIMARY KEY (SEQ ASC)
go


CREATE TABLE USER_MGR
( 
	SEQ                  numeric(16)  NOT NULL ,
	REMK                 nvarchar(256)  NULL ,
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	USER_NM              nvarchar(128)  NULL ,
	LOGIN_ID             varchar(30)  NULL ,
	LOGIN_PWD            varchar(128)  NULL ,
	WRK_ST_DT            datetime  NULL ,
	CONTACT              nvarchar(256)  NULL ,
	WRK_REGION           nvarchar(256)  NULL ,
	ROLE_CD              varchar(1)  NOT NULL ,
	TEAM_SEQ             numeric(16)  NOT NULL 
)
go

ALTER TABLE USER_MGR
	ADD CONSTRAINT XPK?ъ슜?먭?由?PRIMARY KEY (SEQ ASC)
go



ALTER TABLE ACTUAL_MGR
	ADD CONSTRAINT R_47 FOREIGN KEY (PRDGRP_SEQ) REFERENCES PRDGRP_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE ACTUAL_MGR
	ADD CONSTRAINT R_48 FOREIGN KEY (PRD_SEQ) REFERENCES PRD_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE ACTUAL_MGR
	ADD CONSTRAINT R_49 FOREIGN KEY (HOSPT_SEQ) REFERENCES HOSPT_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE GOAL_MGR
	ADD CONSTRAINT R_43 FOREIGN KEY (TEAM_SEQ) REFERENCES TEAM_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE GOAL_MGR
	ADD CONSTRAINT R_44 FOREIGN KEY (USER_SEQ) REFERENCES USER_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE GOAL_MGR
	ADD CONSTRAINT R_45 FOREIGN KEY (PRDGRP_SEQ) REFERENCES PRDGRP_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE GOAL_MGR
	ADD CONSTRAINT R_46 FOREIGN KEY (HOSPT_SEQ) REFERENCES HOSPT_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE LOG_MOD_HISTORY
	ADD CONSTRAINT R_50 FOREIGN KEY (RESULT_SEQ) REFERENCES RESULT_ALL(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE PRD_MGR
	ADD CONSTRAINT R_41 FOREIGN KEY (PRDGRP_SEQ) REFERENCES PRDGRP_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE USER_MGR
	ADD CONSTRAINT R_42 FOREIGN KEY (TEAM_SEQ) REFERENCES TEAM_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go
