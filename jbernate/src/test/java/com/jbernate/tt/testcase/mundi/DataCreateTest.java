package com.jbernate.tt.testcase.mundi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.service.CmService;
import com.jbernate.tt.testcase.service.Crud11TestService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class DataCreateTest {

	@Autowired CmService cmService;
	@Autowired Crud11TestService service;
	
	@Test
	public void crud1n1TableTest() {
		// 테스트 역할 생성
		/*
		CmRoleMgr role = new CmRoleMgr();
		role.setCoCd( ConstUtil.ID_SITE_MUNDI );
		role.setRoleNm( "개발계정" );
		role.setDspSeq( new BigDecimal( 99 ) );
		role.setRemk( "개발계정" );
		cmService.create( null, role );
		*/
		
		/*
		// 테스트 로그인 계정 생성
		CmUserMgr user = new CmUserMgr();
		user.setRoleSeq( new CmRoleMgr( new BigDecimal( 3 ) ) );
		user.setCoCd( ConstUtil.ID_SITE_MUNDI );
		user.setLoginId( "develop" );
		user.setPwd( SecuUtil.getSha256( "mundi!234" ));
		user.setUserNm( "개발계정" );
		user.setUseYn( "Y" );
		cmService.create( null, user );
		*/
	}
}