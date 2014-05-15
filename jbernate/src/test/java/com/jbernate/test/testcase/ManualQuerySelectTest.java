package com.jbernate.test.testcase;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.common.service.CommonCrudService;
import com.jbernate.common.util.LoggerUtil;

/**
 * 수동 쿼리 실행 시 특정 Entity로 casting이 안되고
 * 아래 소스 내용처럼 Object[] 형으로 반환받습니다
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class ManualQuerySelectTest {

	@Autowired CommonCrudService commonCrudService;
	
	@Test
	public void manualQuerySelectTest() {
		@SuppressWarnings("unchecked")
		List<Object> list = commonCrudService.list( null, "SELECT '테스트 입니다' AS vVarchar, 1 AS seq FROM TtOneTableV" );
		
		for( int i = 0; i < list.size(); i++ ) {
			Object[] entity = (Object[])list.get( i );
			for( int j = 0; j < entity.length; j++ ) {
				LoggerUtil.trace( "entity[" + i + "][" + j + "] = "	+ entity[ j ] );			
			}			
		}
	}
}