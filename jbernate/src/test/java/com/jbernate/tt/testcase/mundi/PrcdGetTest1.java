package com.jbernate.tt.testcase.mundi;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.service.CmService;
import com.jbernate.mp.dao.MpPrcdDao;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class PrcdGetTest1 {

	@Autowired CmService cmService;
	@Autowired MpPrcdDao mpPrcdDao;	
	
	@Test
	public void crud1n1TableTest() {
		mpPrcdDao.getResultCol( "201506", new BigDecimal( 55 ), null, null );
	}
}