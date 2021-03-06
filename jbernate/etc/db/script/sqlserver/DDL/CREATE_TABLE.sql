
DROP TABLE CM_USER_DTL
go

DROP TABLE CM_USERMENU_MAP
go

DROP TABLE CM_USER_MGR
go

DROP TABLE CM_MLTLANG_MGR
go

DROP TABLE CM_MENUBTN_MAP
go

DROP TABLE CM_COMCD_MGR
go

DROP TABLE CM_ROLEROLEGRP_MAP
go

DROP TABLE CM_ROLE_MGR
go

DROP TABLE CM_ROLEGRPMENUBTN_MAP
go

DROP TABLE CM_ROLEGRPMENU_MAP
go

DROP TABLE CM_MENU_MGR
go

DROP TABLE CM_PGM_MGR
go

DROP TABLE CM_ROLEGRP_MGR
go

DROP TABLE CM_MENUBTN_MGR
go

CREATE TABLE CM_COMCD_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	COM_GRP              varchar(4)  NULL ,
	ETC01                nvarchar(128)  NULL ,
	ETC02                nvarchar(128)  NULL ,
	ETC03                nvarchar(128)  NULL ,
	ETC04                nvarchar(128)  NULL ,
	ETC05                nvarchar(128)  NULL ,
	ETC06                nvarchar(128)  NULL ,
	ETC07                nvarchar(128)  NULL ,
	ETC08                nvarchar(128)  NULL ,
	ETC09                nvarchar(128)  NULL ,
	ETC10                nvarchar(128)  NULL ,
	COM_CD               varchar(30)  NULL ,
	GRP_NM               nvarchar(128)  NULL ,
	CD_NM                nvarchar(128)  NULL 
)
go

ALTER TABLE CM_COMCD_MGR
	ADD CONSTRAINT XPKCM_공통코드 PRIMARY KEY (SEQ ASC)
go


CREATE TABLE CM_MENU_MGR
( 
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	MENU_NM              nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	PGM_SEQ              numeric(16)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	UP_SEQ               numeric(16)  NULL ,
	DSP_SEQ              numeric(16)  NULL ,
	CO_CD                varchar(30)  NOT NULL 
)
go

ALTER TABLE CM_MENU_MGR
	ADD CONSTRAINT XPKCM_메뉴관리 PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_MENUBTN_MAP
( 
	INS_DT               datetime  NULL ,
	MENU_SEQ             numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	MENUBTN_SEQ          numeric(16)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	DSP_SEQ              numeric(16)  NULL 
)
go

ALTER TABLE CM_MENUBTN_MAP
	ADD CONSTRAINT XPKCM_메뉴버튼MAP PRIMARY KEY (SEQ ASC)
go


CREATE TABLE CM_MENUBTN_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	BTN_NM               nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL 
)
go

ALTER TABLE CM_MENUBTN_MGR
	ADD CONSTRAINT XPKCM_메뉴버튼관리 PRIMARY KEY (SEQ ASC)
go


CREATE TABLE CM_MLTLANG_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	KEYWORD              varchar(1024)  NULL ,
	LNG_TP               varchar(5)  NULL ,
	VALUE                nvarchar(1024)  NULL ,
	REMK                 nvarchar(256)  NULL 
)
go

ALTER TABLE CM_MLTLANG_MGR
	ADD CONSTRAINT XPKCM_다국어관리 PRIMARY KEY (SEQ ASC)
go


CREATE TABLE CM_PGM_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	PGM_NM               nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL 
)
go

ALTER TABLE CM_PGM_MGR
	ADD CONSTRAINT XPKCM_프로그램관리 PRIMARY KEY (SEQ ASC)
go


CREATE TABLE CM_ROLE_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	ROLE_NM              nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	CO_CD                varchar(30)  NOT NULL ,
	DSP_SEQ              numeric(16)  NULL 
)
go

ALTER TABLE CM_ROLE_MGR
	ADD CONSTRAINT XPKCM_권한관리 PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_ROLEGRP_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	ROLEGRP_NM           nvarchar(128)  NULL ,
	REMK                 nvarchar(256)  NULL ,
	CO_CD                varchar(30)  NOT NULL ,
	DSP_SEQ              numeric(16)  NULL 
)
go

ALTER TABLE CM_ROLEGRP_MGR
	ADD CONSTRAINT XPKCM_권한그룹관리 PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_ROLEGRPMENU_MAP
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	ROLEGRP_SEQ          numeric(16)  NOT NULL ,
	MENU_SEQ             numeric(16)  NOT NULL 
)
go

ALTER TABLE CM_ROLEGRPMENU_MAP
	ADD CONSTRAINT XPKCM_권한그룹_메뉴MAP PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_ROLEGRPMENUBTN_MAP
( 
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	DSP_SEQ              numeric(16)  NULL ,
	MENUBTN_SEQ          numeric(16)  NOT NULL ,
	ROLEGRPMENUMAP_SEQ   numeric(16)  NOT NULL 
)
go

ALTER TABLE CM_ROLEGRPMENUBTN_MAP
	ADD CONSTRAINT XPKCM_ROLEGRPMENUBTN_MAP PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_ROLEROLEGRP_MAP
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	ROLE_SEQ             numeric(16)  NOT NULL ,
	ROLEGRP_SEQ          numeric(16)  NOT NULL ,
	REMK                 nvarchar(256)  NULL 
)
go

ALTER TABLE CM_ROLEROLEGRP_MAP
	ADD CONSTRAINT XPKCM_권한_권한그룹MAP PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_USER_DTL
( 
	INS_DT               datetime  NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	ENG_USER_NM          nvarchar(128)  NULL 
)
go

ALTER TABLE CM_USER_DTL
	ADD CONSTRAINT XPKCM_사용자상세정보 PRIMARY KEY (SEQ ASC)
go



CREATE TABLE CM_USER_MGR
( 
	INS_DT               datetime  NULL ,
	SEQ                  numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	CO_CD                varchar(30)  NOT NULL ,
	LOGIN_ID             varchar(30)  NULL ,
	PWD                  varchar(256)  NULL ,
	PWD_EX_DT            datetime  NULL ,
	ROLE_SEQ             numeric(16)  NOT NULL ,
	USER_NM              nvarchar(128)  NULL ,
	USE_YN               varchar(1)  NOT NULL ,
	ACC_ST_DT            datetime  NULL ,
	ACC_ED_DT            datetime  NULL 
)
go

ALTER TABLE CM_USER_MGR
	ADD CONSTRAINT XPKCM_사용자정보 PRIMARY KEY (SEQ ASC)
go




CREATE TABLE CM_USERMENU_MAP
( 
	INS_DT               datetime  NULL ,
	MENU_SEQ             numeric(16)  NOT NULL ,
	INS_ID               varchar(30)  NULL ,
	INS_OBJ              nvarchar(128)  NULL ,
	INS_IP               varchar(64)  NULL ,
	UPD_DT               datetime  NULL ,
	UPD_ID               varchar(30)  NULL ,
	UPD_OBJ              nvarchar(128)  NULL ,
	UPD_IP               varchar(64)  NULL ,
	USER_SEQ             numeric(16)  NOT NULL ,
	SEQ                  numeric(16)  NOT NULL 
)
go

ALTER TABLE CM_USERMENU_MAP
	ADD CONSTRAINT XPKCM_사용자_메뉴MAP PRIMARY KEY (SEQ ASC)
go




ALTER TABLE CM_MENU_MGR
	ADD CONSTRAINT R_PGM_MENU FOREIGN KEY (PGM_SEQ) REFERENCES CM_PGM_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE CM_MENU_MGR
	ADD CONSTRAINT R_28 FOREIGN KEY (UP_SEQ) REFERENCES CM_MENU_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_MENUBTN_MAP
	ADD CONSTRAINT R_MENUBTN_MENUBTNMAP FOREIGN KEY (MENUBTN_SEQ) REFERENCES CM_MENUBTN_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE CM_MENUBTN_MAP
	ADD CONSTRAINT R_MENU_MENUBTNMAP FOREIGN KEY (MENU_SEQ) REFERENCES CM_MENU_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_ROLEGRPMENU_MAP
	ADD CONSTRAINT R_ROLEGRP_ROLEGRPMENU FOREIGN KEY (ROLEGRP_SEQ) REFERENCES CM_ROLEGRP_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE CM_ROLEGRPMENU_MAP
	ADD CONSTRAINT R_31 FOREIGN KEY (MENU_SEQ) REFERENCES CM_MENU_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_ROLEGRPMENUBTN_MAP
	ADD CONSTRAINT R_35 FOREIGN KEY (MENUBTN_SEQ) REFERENCES CM_MENUBTN_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE CM_ROLEGRPMENUBTN_MAP
	ADD CONSTRAINT R_36 FOREIGN KEY (ROLEGRPMENUMAP_SEQ) REFERENCES CM_ROLEGRPMENU_MAP(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_ROLEROLEGRP_MAP
	ADD CONSTRAINT R_32 FOREIGN KEY (ROLE_SEQ) REFERENCES CM_ROLE_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE CM_ROLEROLEGRP_MAP
	ADD CONSTRAINT R_33 FOREIGN KEY (ROLEGRP_SEQ) REFERENCES CM_ROLEGRP_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_USER_DTL
	ADD CONSTRAINT R_USER_DTL FOREIGN KEY (SEQ) REFERENCES CM_USER_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_USER_MGR
	ADD CONSTRAINT R_ROLE_USER FOREIGN KEY (ROLE_SEQ) REFERENCES CM_ROLE_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE CM_USERMENU_MAP
	ADD CONSTRAINT R_USER_USERMENUMAP FOREIGN KEY (USER_SEQ) REFERENCES CM_USER_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE CM_USERMENU_MAP
	ADD CONSTRAINT R_MENU_USERMENUMAP FOREIGN KEY (MENU_SEQ) REFERENCES CM_MENU_MGR(SEQ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go
