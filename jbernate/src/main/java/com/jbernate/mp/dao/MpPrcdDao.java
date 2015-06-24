package com.jbernate.mp.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 먼디파머 > 프로시저 실행 인터페이스
 */
public interface MpPrcdDao {

	/** 실적 확정>집계 실행 */
	Object[] execMakeResult( final String yyyymm );
	
	/** 실적 조회 > 개인별 > 컬럼목록 */
	HashMap<String, List> getResultCol( final String yyyymm, final BigDecimal teamSeq, final String hosptSeq, final String userSeq );	
	
}