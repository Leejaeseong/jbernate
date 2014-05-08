package com.jbernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DatabaseUtil {

	/**
	 * Hibernate 세션 얻기
	 * @return
	 */
	public static Session getHibernateSession( SessionFactory sessionFactory ) {
		return sessionFactory.getCurrentSession();
	}
}