package com.jbernate.cm.bean;

import java.io.Serializable;

import com.jbernate.mundi.domain.table.UserMgr;

/**
 * Session Bean
 */
public class Sb implements Serializable{

	private static final long serialVersionUID = -4226643179532090253L;
	
	private UserMgr userMgr;

	public UserMgr getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*
	private TtOneTable 			ttOneTable;
	private List<TtOneTable> 	ttOneTableList;
	
	private Tt11Master 			tt11Master;
	private List<Tt11Master>	tt11MasterList;
	
	private Tt11Slave1 			tt11Slave1;
	private List<Tt11Slave1>	tt11Slave1List;
	
	private Tt1nMaster			tt1nMaster;
	private List<Tt1nMaster>	tt1nMasterList;
	
	private Tt1nSlave1			tt1nSlave1;
	private List<Tt1nSlave1>	tt1nSlave1List;
	
	public TtOneTable getTtOneTable() {
		return ttOneTable;
	}
	public void setTtOneTable(TtOneTable ttOneTable) {
		this.ttOneTable = ttOneTable;
	}
	public List<TtOneTable> getTtOneTableList() {
		return ttOneTableList;
	}
	public void setTtOneTableList(List<TtOneTable> ttOneTableList) {
		this.ttOneTableList = ttOneTableList;
	}
	public Tt11Master getTt11Master() {
		return tt11Master;
	}
	public void setTt11Master(Tt11Master tt11Master) {
		this.tt11Master = tt11Master;
	}
	public List<Tt11Master> getTt11MasterList() {
		return tt11MasterList;
	}
	public void setTt11MasterList(List<Tt11Master> tt11MasterList) {
		this.tt11MasterList = tt11MasterList;
	}
	public Tt11Slave1 getTt11Slave1() {
		return tt11Slave1;
	}
	public void setTt11Slave1(Tt11Slave1 tt11Slave1) {
		this.tt11Slave1 = tt11Slave1;
	}
	public List<Tt11Slave1> getTt11Slave1List() {
		return tt11Slave1List;
	}
	public void setTt11Slave1List(List<Tt11Slave1> tt11Slave1List) {
		this.tt11Slave1List = tt11Slave1List;
	}
	public Tt1nMaster getTt1nMaster() {
		return tt1nMaster;
	}
	public void setTt1nMaster(Tt1nMaster tt1nMaster) {
		this.tt1nMaster = tt1nMaster;
	}
	public List<Tt1nMaster> getTt1nMasterList() {
		return tt1nMasterList;
	}
	public void setTt1nMasterList(List<Tt1nMaster> tt1nMasterList) {
		this.tt1nMasterList = tt1nMasterList;
	}
	public Tt1nSlave1 getTt1nSlave1() {
		return tt1nSlave1;
	}
	public void setTt1nSlave1(Tt1nSlave1 tt1nSlave1) {
		this.tt1nSlave1 = tt1nSlave1;
	}
	public List<Tt1nSlave1> getTt1nSlave1List() {
		return tt1nSlave1List;
	}
	public void setTt1nSlave1List(List<Tt1nSlave1> tt1nSlave1List) {
		this.tt1nSlave1List = tt1nSlave1List;
	}
	*/
		
}