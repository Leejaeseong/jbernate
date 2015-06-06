package com.jbernate.tt.testcase.crud;

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
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.LogUtil;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class CrudOneTableTest {

	@Autowired CmService cmService;
	
	@Test
	public void crudOneTableTest() {
		/*
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
	
		long seq = (Long)cmService.create( null, entity );
		LogUtil.trace( "Created seq = " + seq );
		
		// 조회 ////////////////////////////////////////////////////////////////////////////////
		@SuppressWarnings("unchecked")
		List<TtOneTable> list = cmService.list( null, entity, BeanUtil.oneWhere( "seq", seq, WhereBean.Clause.EQ ), BeanUtil.oneOrder( "seq", OrderBean.Type.DESC ) );
		
		LogUtil.trace( "list.size() = " + list.size() );
		if( list.size() > 0 ) {
			entity = list.get( 0 );
			
			// 수정 ////////////////////////////////////////////////////////////////////////////
			entity.settDate( new Date() );
			cmService.update( null, entity );
		}
		
		// 삭제 ////////////////////////////////////////////////////////////////////////////////
		cmService.delete( null, entity );
		*/
	}
	
	//@Test
	public void crudOneTableGetTest() {
		//Object obj = cmService.get( null, new TtOneTable( 102L ) );
	}
	
	//@Test
	public void crudOneTableDeleteTmpTest() {
		/*
		TtOneTable entity = new TtOneTable( 102L );
		cmService.delete( null, entity );
		*/
	}
	
	//@Test
	public void crudOneTableSelectTmpTest() {
		/*
		TtOneTable entity = new TtOneTable();
		@SuppressWarnings("unchecked")
		List<TtOneTable> list = cmService.list( null, entity );
		LogUtil.trace( "size = " + list.size() );
		*/
	}
}