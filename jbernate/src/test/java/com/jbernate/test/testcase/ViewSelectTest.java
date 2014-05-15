package com.jbernate.test.testcase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.common.bean.OrderBean;
import com.jbernate.common.bean.WhereBean;
import com.jbernate.common.service.CommonCrudService;
import com.jbernate.common.util.LoggerUtil;
import com.jbernate.test.domain.view.TtOneTableV;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class ViewSelectTest {

	@Autowired CommonCrudService commonCrudService;
	
	@Test
	public void viewSelectTest() {
		TtOneTableV entity = new TtOneTableV();
		
		List<WhereBean> wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "seq", 1L, WhereBean.Clause.EQ ) );
		List<OrderBean> obList = new ArrayList<OrderBean>();
		obList.add( new OrderBean( "seq" , OrderBean.Type.DESC ) );
		
		@SuppressWarnings("unchecked")
		List<TtOneTableV> list = commonCrudService.list( null, entity, wbList, obList );
		
		LoggerUtil.trace( "list.size() = " + list.size() );
		if( list.size() > 0 ) {
			entity = list.get( 0 );
			LoggerUtil.trace( "entity.getSeq() = " 				+ entity.getSeq() );
			LoggerUtil.trace( "entity.getvDate() = " 			+ entity.getvDate() );
			LoggerUtil.trace( "entity.getvVarchar() = " 		+ entity.getvVarchar() );
			LoggerUtil.trace( "entity.getvClob().length() = " 	+ entity.getvClob().length() );
			LoggerUtil.trace( "entity.getvBlob().length() = " 	+ entity.getvBlob().length );
		}
	}
}