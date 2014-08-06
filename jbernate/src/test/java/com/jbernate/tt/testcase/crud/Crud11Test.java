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

import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.tt.domain.table.Tt11Master;
import com.jbernate.tt.domain.table.Tt11Slave1;
import com.jbernate.tt.testcase.service.Crud11TestService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class Crud11Test {

	@Autowired CmService cmService;
	@Autowired Crud11TestService service;
	
	@Test
	public void crud1n1TableTest() {
		//service.test();
		
		/// Master 추가 ////////////////////////////////////////////////////////////////////////////////
		Tt11Master master = new Tt11Master();
		master.settDate( new Date() );
		master.settVarchar( "테스트 = " + new Date().toString() );
		master.settClob( "테스트~~~~~~~~~~~~~~~~~~~~~~~~" );
		master.settBlob( new byte[]{'t','e','s','t','~','~','~','~','~','~'} );
		long seq = (Long)cmService.create( null, master );
		LogUtil.trace( "master 추가된 데이터 = " + seq );

		// Master 추가된 데이터 get ////////////////////////////////////////////////////////////////////		
		master = (Tt11Master)cmService.get( null, new Tt11Master( seq ) );
		LogUtil.trace( "master get 데이터 = " + master.getSeq() );
		
		// Slave1 추가 ////////////////////////////////////////////////////////////////////////////////
		Tt11Slave1 slave1 = new Tt11Slave1();
		slave1.settDate( new Date() );
		slave1.settVarchar( "테스트 = " + new Date().toString() );
		slave1.settClob( "테스트~~~~~~~~~~~~~~~~~~~~~~~~" );
		slave1.settBlob( new byte[]{'t','e','s','t','~','~','~','~','~','~'} );
		slave1.setSeq( master );
		cmService.create( null, slave1 );
		
		// Slave1 list로 조회( get은 안됨 ) ////////////////////////////////////////////////////////////
		@SuppressWarnings("unchecked")
		List<Tt11Slave1> list = cmService.list( null, slave1, BeanUtil.oneWhere( "seq.seq", seq, WhereBean.Clause.EQ ) );
		LogUtil.trace( "slave1 list 데이터 = " + list.get( 0 ).getSeq().getSeq() );
		
		// Slave1 수정 ////////////////////////////////////////////////////////////////////////////////
		slave1.settVarchar( "수정" );
		
		// Slave1 삭제 ////////////////////////////////////////////////////////////////////////////////
		cmService.delete( null, slave1 );
		
		// Master 삭제 ////////////////////////////////////////////////////////////////////////////////
		cmService.delete( null, master );
		
		// Slave1 예전 데이터 get /////////////////////////////////////////////////////////////////////
		// ※ 1:1 관계에서 master insert => slave insert => slave.get( 현재 세션 Master insert 객체 )은 실패한다, 굳이 필요하다면 list로 값 얻기가 가능
		// 그러나 다른 세션의 값은 조회 가능( slave.get( new Tt11master( 기존에 삽입되어있는 DB seq ) )
		slave1 = (Tt11Slave1)cmService.get( null, new Tt11Slave1( new Tt11Master( 4L ) ) );
		if( slave1 != null ) LogUtil.trace( "slave1 get 기존데이터 = " + slave1.getSeq().getSeq() );
	}
}