package com.jbernate.tt.testcase.crud;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.service.CmService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class DateTest {

	@Autowired CmService cmService;
	
	@Test
	public void crud1n1TableTest() {
		ArrayList wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "loginId", "admin", Clause.EQ ) );
		wbList.add( new WhereBean( "pwd", "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b", Clause.EQ ) );
		wbList.add( new WhereBean( "accStDt", new Date(), Clause.LE ) );
		wbList.add( new WhereBean( "accEdDt", new Date(), Clause.GE ) );
		wbList.add( new WhereBean( "useYn", "1", Clause.EQ ) );
		//List<CmUserMgr> list = cmService.list( null, new CmUserMgr(), wbList );
		//LogUtil.trace( "" + list.size() );
	}
}