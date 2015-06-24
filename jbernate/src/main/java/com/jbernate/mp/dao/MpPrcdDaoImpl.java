package com.jbernate.mp.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbernate.cm.util.NumUtil;
import com.jbernate.cm.util.StrUtil;

/**
 * 프로시저 실행 구현체
 */
@Repository
public class MpPrcdDaoImpl implements MpPrcdDao{

	@Autowired SessionFactory sessionFactory;
	
	/** 실적 확정>집계 실행 */
	@Override
	public Object[] execMakeResult( final String yyyymm ) {
		final Object[] rData = { null, null };
		sessionFactory.getCurrentSession().doWork(new Work() {
	        public void execute(Connection connection) throws SQLException {
	        	CallableStatement call = connection.prepareCall( "EXEC PR_MAKE_RESULT ?, ?, ?" );
				call.setString( 1, yyyymm );
				call.registerOutParameter( 2, Types.INTEGER );
				call.registerOutParameter( 3, Types.VARCHAR );
				call.execute();
				
				rData[0] = call.getBigDecimal( 2 );	// ERR_CD
				rData[1] = call.getString( 3 );		// ERR_MSG
				
				call.close();
	        }
		});
		
		return rData;
	}
	
	/** 실적 조회 > 개인별 > 컬럼목록 */
	@Override
	public HashMap<String, List> getResultCol( final String yyyymm, final BigDecimal teamSeq, final String hosptSeq, final String userSeq ) {
		final HashMap<String, List> hm = new HashMap<String, List>();
		final List cList = new LinkedList<String>();
		final List dList = new LinkedList<String>();
		sessionFactory.getCurrentSession().doWork(new Work() {
	        public void execute(Connection connection) throws SQLException {
	        	CallableStatement call = connection.prepareCall( "EXEC PR_RESULT ?, ?, ?, ? " );
	        	call.setString( 	1, yyyymm );
	        	call.setBigDecimal( 2, teamSeq );
	        	call.setObject( 3, hosptSeq );
	        	call.setObject( 4, userSeq );
	        	call.execute();
				
				// 컬럼 갯수
				int colCnt = call.getResultSet().getMetaData().getColumnCount();
				ResultSet rs = call.getResultSet();
				LinkedHashMap hm = null;
				boolean isFirst = true;
				while( rs.next() ) {
					hm = new LinkedHashMap();
					for( int i = 1; i <= colCnt; i++ ) {	// index는 1부터 시작 함
						if( isFirst ) {	// 컬럼 계산
							cList.add( call.getResultSet().getMetaData().getColumnName( i ) );
						}
						hm.put( "field" + i, StrUtil.nvlZero( rs.getObject( i ) ) );
					}
					dList.add( hm );
					isFirst = false;
				}
				rs.close();
				call.close();
	        }
		});
		hm.put( "column", cList );
		hm.put( "data", dList );
		return hm;
	}
		
}