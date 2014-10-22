package com.jbernate.tt.domain.table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.jbernate.cm.util.ConstUtil;

@Entity( name = "com.jbernate.tt.domain.table.TtTest1" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
@Table( name = "TT_TEST_1" )
public class TtTest1 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public TtTest1(){};
	
	public TtTest1( int test1 ){
		this.test1 = test1;
	}
	
	// Sequence
	@Id
	@Column( name = "TEST_1", columnDefinition = "INT" )
	private int test1;
	
	// 문자형
	@Column( name = "TEST_2", columnDefinition = "VARCHAR2(100) NULL", length = 100 )
	private String test2;

	public int getTest1() {
		return test1;
	}

	public void setTest1(int test1) {
		this.test1 = test1;
	}

	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}
	
}