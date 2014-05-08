package com.jbernate.common.dao;

/**
 * 공통 CRUD DAO 인터페이스
 */
public interface CommonCrudDao {

	/**
	 * 삽입
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	Object create( Object entity );
}