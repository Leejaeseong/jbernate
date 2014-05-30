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
import com.jbernate.cm.service.CmCrudService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.tt.domain.table.Tt1nMaster;
import com.jbernate.tt.domain.table.Tt1nSlave1;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class Crud1nTest {

	@Autowired CmCrudService cService;
	
	@Test
	public void crud1n1TableTest() {
		// Master 추가 ////////////////////////////////////////////////////////////////////////////////
		Tt1nMaster master = new Tt1nMaster();
		master.settDate( new Date() );
		master.settVarchar( "테스트 = " + new Date().toString() );
		master.settClob( "테스트~~~~~~~~~~~~~~~~~~~~~~~~" );
		master.settBlob( new byte[]{'t','e','s','t','~','~','~','~','~','~'} );
		long seq = (Long)cService.create( null, master );
		
		// Slave 추가 ////////////////////////////////////////////////////////////////////////////////
		Tt1nSlave1 slave1 = new Tt1nSlave1();
		slave1.setFkMaster( new Tt1nMaster( seq ) );
		slave1.settDate( new Date() );
		slave1.settVarchar( "테스트 = " + new Date().toString() );
		slave1.settClob( "테스트~~~~~~~~~~~~~~~~~~~~~~~~" );
		slave1.settBlob( new byte[]{'t','e','s','t','~','~','~','~','~','~'} );
		seq = (Long)cService.create( null, slave1 );
		
		// Slave 검색 하여 Master까지 출력 ///////////////////////////////////////////////////////////
		@SuppressWarnings("unchecked")
		List<Tt1nSlave1> list = cService.list( null, slave1, BeanUtil.oneWhere( "seq", seq, WhereBean.Clause.EQ ), BeanUtil.oneOrder( "seq", OrderBean.Type.DESC ) );
		if( list.size() > 0 ) {
			slave1 = list.get( 0 );
			LogUtil.trace( "seq = " + slave1.getSeq() );
			LogUtil.trace( "fkMaster.seq = " + slave1.getFkMaster().getSeq() );
			
			// 수정 ////////////////////////////////////////////////////////////////////////////
			slave1.setModDate( new Date() );
			cService.update( null, slave1 );
		}
		
		// 삭제 ////////////////////////////////////////////////////////////////////////////////
		cService.delete( null, slave1 );
	}
}