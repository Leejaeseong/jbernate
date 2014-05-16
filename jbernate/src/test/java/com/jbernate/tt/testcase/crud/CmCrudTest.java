package com.jbernate.tt.testcase.crud;

import java.util.ArrayList;
import java.util.Date;
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
import com.jbernate.cm.service.CmCrudService;
import com.jbernate.cm.util.LoggerUtil;
import com.jbernate.tt.domain.table.TtOneTable;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class CmCrudTest {

	@Autowired CmCrudService commonCrudService;
	
	@Test
	public void crudTest() {
		// 추가 ////////////////////////////////////////////////////////////////////////////////
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
	
		long seq = (Long)commonCrudService.create( null, entity );
		LoggerUtil.trace( "Created seq = " + seq );
		
		// 조회 ////////////////////////////////////////////////////////////////////////////////
		List<WhereBean> wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "seq", seq, WhereBean.Clause.EQ ) );
		List<OrderBean> obList = new ArrayList<OrderBean>();
		obList.add( new OrderBean( "seq" , OrderBean.Type.DESC ) );
		
		@SuppressWarnings("unchecked")
		List<TtOneTable> list = commonCrudService.list( null, entity, wbList, obList );
		
		LoggerUtil.trace( "list.size() = " + list.size() );
		if( list.size() > 0 ) {
			entity = list.get( 0 );
			
			// 수정 ////////////////////////////////////////////////////////////////////////////
			entity.settDate( new Date() );
			commonCrudService.update( null, entity );
		}
		
		// 삭제 ////////////////////////////////////////////////////////////////////////////////
		commonCrudService.delete( null, entity );
	}
}