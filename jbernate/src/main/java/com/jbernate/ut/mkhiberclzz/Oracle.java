package com.jbernate.ut.mkhiberclzz;

import java.util.HashMap;
import java.util.List;

import com.jbernate.cm.service.CmService;

public class Oracle {

	private List<Object> list = null;
	
	@SuppressWarnings("unchecked")
	public Oracle( CmService cmService, String tPath, String tPrefix, String mkdbArr ) {
		// 파라미터 생성
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put( "p1", mkdbArr );
		
		this.list = cmService.queryList( null, ""
				+ "	SELECT "
				+ "		DB_NM"			// 배열 0
				+ "	,	TAB_NM"			// 배열 1
				+ "	,	TAB_DESC"		// 배열 2
				+ "	,	COL_NM "		// 배열 3
				+ "	,	COL_DESC"		// 배열 4
				+ "	,	COL_TYPE"		// 배열 5	( NUMBER, VARCHAR..., DATE, CLOB, BLOB )
				+ "	,	COL_LEN"		// 배열 6
				+ "	,	COL_PREC"		// 배열 7
				+ "	,	COL_SCALE"		// 배열 8
				+ "	,	COL_NULL"		// 배열 9	( Y/N )
				+ "	,	COL_SEQ"		// 배열 10
				+ "	,	IS_PK"			// 배열 11	( Y/N )
				+ "	,	R_CNT"			// 배열 12	( 2 = 1:1, 1 = 1:N, 0 = 관계없음 )
				+ "	,	REF_TAB_N_COL "	// 배열 13	( TT_TEST_B,SEQ 즉 테이블,컬럼 형태 )
				+ "	FROM MN_MKJAVA_META_VI "
				+ "	WHERE :p1 LIKE '%' || DB_NM || '%'", hash );
		
		//LogUtil.trace( "list.size() = " + list.size() );
		
		/*for( int i = 0; i < list.size(); i++ ) {
			Object[] entity = (Object[])list.get( i );
			for( int j = 0; j < entity.length; j++ ) {
				LogUtil.trace( "entity[" + i + "][" + j + "] = " + entity[ j ] );			
			}			
		}*/
	}
	
	public List<Object> getList() {	return list; }
}