package com.jbernate.tt.testcase.crud;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.bean.OrderBean;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.DateUtil;
import com.jbernate.mundi.domain.table.LogModHistory;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class WhereSelectTest {

	@Autowired CmService cmService;
	
	@Test
	public void whereSelectTest() {
		LogModHistory entity = new LogModHistory();
		@SuppressWarnings("unchecked")
		
		ArrayList wbList = new ArrayList<WhereBean>();
		//wbList.add( new WhereBean( "actualSeq.prdSeq.prdgrpSeq.seq", new BigDecimal( 12 ), Clause.EQ ) );
		//wbList.add( new WhereBean( "insDt", new Date()"2015-06-10 00:00:00", Clause.EQ ) );
		List al = new LinkedList();
		al.add( DateUtil.strToDt( "2015-06-10 00:00:00", "yyyy-MM-dd HH:mm:ss" ) );
		al.add( DateUtil.strToDt( "2015-06-10 23:59:59", "yyyy-MM-dd HH:mm:ss" ) );
		wbList.add( new WhereBean( "insDt", al, Clause.BETWEEN ) );
		
		List<LogModHistory> list = cmService.list( null, entity, wbList, BeanUtil.oneOrder( "seq", OrderBean.Type.DESC ) );
		
		System.out.println( "list.size() = " + list.size() );
		if( list.size() > 0 ) {
			entity = list.get( 0 );
			System.out.println( "entity.getSeq() = " + entity.getSeq() );
			//System.out.println( "entity.getPrdSeq().getSeq() = " + entity.getPrdSeq().getSeq() );
			//System.out.println( "entity.getPrdSeq().getPrdgrpSeq().getSeq() = " + entity.getPrdSeq().getPrdgrpSeq().getSeq() );
		}
	}
}