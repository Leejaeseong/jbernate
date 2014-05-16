package com.jbernate.tt.testcase.temp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.util.LoggerUtil;
import com.jbernate.cm.util.StringUtil;
import com.jbernate.tt.domain.table.TtOneTable;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config-test.xml" } )
//@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
//@Transactional
public class IdentObjectTest {

	@Test
	public void identObjectTest() {
		String str 			= "aaa";
		TtOneTable table 	= new TtOneTable();
		
		LoggerUtil.println( str instanceof String );

		Object obj			= new Object();
		obj = str;		
		LoggerUtil.println( obj instanceof Class );
		
		obj = table;
		LoggerUtil.println( obj instanceof Class );
		
		try{
			String clzNm	= "com.jbernate.tt.domain.view.TtOneTableV";
			Class clazz = Class.forName( clzNm );
			LoggerUtil.println( clazz );
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}
}