package com.jbernate.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.common.dao.CommonCrudDao;
import com.jbernate.common.service.CommonCrudService;

/**
 * 공통 CRUD 서비스 구현체
 */
@Service
@Transactional
public class CommonCrudServiceImpl implements CommonCrudService{

	@Autowired CommonCrudDao commonCrudDao;
	
	/**
	 * 삽입
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	@Override
	public Object create( Object entity ) {
		return commonCrudDao.create( entity );
	}
}