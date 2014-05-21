package com.jbernate.tt.testcase.temp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jbernate.cm.util.LogUtil;
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
		
		LogUtil.println( str instanceof String );

		Object obj			= new Object();
		obj = str;		
		LogUtil.println( obj instanceof Class );
		
		obj = table;
		LogUtil.println( obj instanceof Class );
		
		try{
			String clzNm	= "com.jbernate.tt.domain.view.TtOneTableV";
			Class clazz = Class.forName( clzNm );
			LogUtil.println( clazz );
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}
}