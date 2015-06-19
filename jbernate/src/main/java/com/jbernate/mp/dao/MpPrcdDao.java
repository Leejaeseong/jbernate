package com.jbernate.mp.dao;

/**
 * 먼디파머 > 프로시저 실행 인터페이스
 */
public interface MpPrcdDao {

	/** 실적 확정>집계 실행 */
	Object[] execMakeResult( final String yyyymm );
	
}