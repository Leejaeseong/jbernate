package com.jbernate.common.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbernate.common.dao.CommonCrudDao;
import com.jbernate.util.DatabaseUtil;

/**
 * 공통 CRUD DAO 인터페이스
 */
@Repository
public class CommonCrudDaoImpl implements CommonCrudDao{

	@Autowired SessionFactory sessionFactory;
	
	/**
	 * 삽입
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	@Override
	public Object create( Object entity ) {
		return DatabaseUtil.getHibernateSession( sessionFactory ).save( entity );
	}
}