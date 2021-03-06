package com.jbernate.tt.testcase.temp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jbernate.cm.util.StrUtil;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config-test.xml" } )
//@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
//@Transactional
public class StringTest {

	@Test
	public void makeJavaNameRuleTest() {
		Assert.assertEquals( StrUtil.makeJavaNameRule( "aa_bb_cc" ), "aaBbCc" );
		Assert.assertEquals( StrUtil.makeJavaNameRule( "aa_bb_" ), "aaBb" );
		Assert.assertEquals( StrUtil.makeJavaNameRule( "_" ), "" );
	}
	
}