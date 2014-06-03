package com.jbernate.tt.testcase.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.tt.domain.table.Tt11Master;
import com.jbernate.tt.domain.table.Tt11Slave1;
import com.jbernate.tt.testcase.service.Crud11TestService;

/**
 * 테스트
 */
@Service
@Transactional
public class Crud11TestServiceImpl implements Crud11TestService{

	@Autowired CmService cmService;
	
	/**
	 * 테스트케이스에서 Transaction 테스트
	 * 
	 * Test클래스에서 설정한 defaultRollback 여부에 상관없이 commit 또는 exception 발생시 롤백 되어 버림
	 * 
	 * 테스트케이스에서 Exception 발생 시 Rollback 안 됨
     * @Transactional 및 @Transactional( rollbackFor=Exception.class, propagation = Propagation.REQUIRED ) 을 주어도 안됨
     * 
     * 테스트케이스에서는 일단 AbstractTransactionalJUnit4SpringContextTests?를 사용하지 않는 이상 무조건 rollback이냐 commit이냐만 선택이 가능한 듯
	 */
	@Override
	public void test() {		
		/// Master 추가 ////////////////////////////////////////////////////////////////////////////////
		Tt11Master master = new Tt11Master();
		master.settDate( new Date() );
		master.settVarchar( "테스트 = " + new Date().toString() );
		master.settClob( "테스트~~~~~~~~~~~~~~~~~~~~~~~~" );
		master.settBlob( new byte[]{'t','e','s','t','~','~','~','~','~','~'} );
		long seq = (Long)cmService.create( null, master );
		LogUtil.trace( "master 추가된 데이터 = " + seq );

		// Slave1 추가 ////////////////////////////////////////////////////////////////////////////////
		Tt11Slave1 slave1 = new Tt11Slave1();
		slave1.settDate( new Date() );
		slave1.settVarchar( "테스트 = " + new Date().toString() );
		slave1.settClob( "테스트~~~~~~~~~~~~~~~~~~~~~~~~" );
		slave1.settBlob( new byte[]{'t','e','s','t','~','~','~','~','~','~'} );
		slave1.setSeq( new Tt11Master( seq ) );
		cmService.create( null, slave1 );
	}

}