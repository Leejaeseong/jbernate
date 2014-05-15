package com.jbernate.common.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Database관련 필요 Util
 */
public class DatabaseUtil {

	/**
	 * Hibernate 세션 얻기
	 * @return
	 */
	public static Session getHibernateSession( SessionFactory sessionFactory ) {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Entity로 해당 테이블의 Criteria 얻기
	 * @param entity			Criteria를 얻고자 하는 테이블 객체
	 * @param sessionFactory	SessionFactory
	 * @return					Criteria
	 */
	public static Criteria getCriteria( Object entity, SessionFactory sessionFactory ) {
		String tabName = entity.getClass().getName();
		if( tabName != null && tabName.length() > 0 ) tabName = tabName.substring( tabName.lastIndexOf( "." ) + 1 );
		return DatabaseUtil.getHibernateSession( sessionFactory ).createCriteria( entity.getClass(), StringUtil.firstLowStr( tabName ) );
	}
	
}