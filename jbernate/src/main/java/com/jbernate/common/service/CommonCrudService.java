package com.jbernate.common.service;

/**
 * 공통 CRUD 서비스 인터페이스
 */
public interface CommonCrudService {

	/**
	 * 삽입
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	Object create( Object entity );
}