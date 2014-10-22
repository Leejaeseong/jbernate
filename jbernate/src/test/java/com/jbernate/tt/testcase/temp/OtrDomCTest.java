package com.jbernate.tt.testcase.temp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.service.CmService;
import com.jbernate.tt.domain.table.TtTest1;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class OtrDomCTest {

	@Autowired CmService cmService;
	
	@Test
	public void OtrDomCTest() {
		TtTest1 test =  new TtTest1();
		test.setTest1( 1 );
		test.setTest2( "test1" );
		cmService.create( null, test );
	}
}