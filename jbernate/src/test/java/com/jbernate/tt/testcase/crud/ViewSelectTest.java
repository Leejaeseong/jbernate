package com.jbernate.tt.testcase.crud;

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
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.tt.domain.view.TtOneTableV;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class ViewSelectTest {

	@Autowired CmService cmService;
	
	@Test
	public void viewSelectTest() {
		TtOneTableV entity = new TtOneTableV();
		@SuppressWarnings("unchecked")
		List<TtOneTableV> list = cmService.list( null, entity, BeanUtil.oneWhere( "seq", 1L, WhereBean.Clause.EQ ), BeanUtil.oneOrder( "seq", OrderBean.Type.DESC ) );
		
		LogUtil.trace( "list.size() = " + list.size() );
		if( list.size() > 0 ) {
			entity = list.get( 0 );
			LogUtil.trace( "entity.getSeq() = " 			+ entity.getSeq() );
			LogUtil.trace( "entity.getvDate() = " 			+ entity.getvDate() );
			LogUtil.trace( "entity.getvVarchar() = " 		+ entity.getvVarchar() );
			LogUtil.trace( "entity.getvClob().length() = " 	+ entity.getvClob().length() );
			LogUtil.trace( "entity.getvBlob().length() = " 	+ entity.getvBlob().length );
		}
	}
}