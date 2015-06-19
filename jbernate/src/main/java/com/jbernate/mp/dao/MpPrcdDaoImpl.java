package com.jbernate.mp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		
}