package com.jbernate.tt.testcase.crud;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.LogUtil;

/**
 * 수동 쿼리 실행 시 특정 Entity로 casting이 안되고
 * 아래 소스 내용처럼 Object[] 형으로 반환받습니다
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = false )
@Transactional
public class NativeQuerySelectTest {

	@Autowired CmService cmService;
	
	@Test
	public void nativeQuerySelectTest() {
		
		// 파라미터 생성
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put( "p1", 1 );
		
		@SuppressWarnings("unchecked")
		List<Object> list = cmService.queryList( null, "SELECT * FROM TT_ONE_TABLE where 1 = :p1", hash );
		
		LogUtil.trace( "list.size() = " + list.size() );
		
		for( int i = 0; i < list.size(); i++ ) {
			Object[] entity = (Object[])list.get( i );
			for( int j = 0; j < entity.length; j++ ) {
				LogUtil.trace( "entity[" + i + "][" + j + "] = " + entity[ j ] );			
			}			
		}
	}
}