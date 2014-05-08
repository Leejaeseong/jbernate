package com.jbernate.test.testcase;

import java.util.Date;

import org.hibernate.validator.util.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.common.service.CommonCrudService;
import com.jbernate.test.domain.TtOneTable;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class CommonCrudTest {

	@Autowired CommonCrudService commonCrudService;
	
	@Test
	public void createTest() {
		TtOneTable entity = new TtOneTable();
		entity.settVarchar( "테스트 : " + new Date().toString() );
		entity.settDate( new Date() );
		
		String clob = "";		
		for( int i = 0; i < 10000; i++ ) { clob += "테스트"; }		
		entity.settClob( clob );
		
		int bLen = 10000;
		byte[] b = new byte[ bLen ];
		for( int i = 0; i < bLen; i++ ) { b[ i ] = 'A'; }
		entity.settBlob( b );
	
		commonCrudService.create( entity );
	}
}