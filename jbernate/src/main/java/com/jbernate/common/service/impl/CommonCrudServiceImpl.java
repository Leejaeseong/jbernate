package com.jbernate.common.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.common.bean.OrderBean;
import com.jbernate.common.bean.WhereBean;
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
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	@Override
	public Object create( HttpServletRequest request, Object entity ) {	return commonCrudDao.create( request, entity );	}
	
	/**
	 * 수정
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void update( HttpServletRequest request, Object entity ) {	commonCrudDao.update( request, entity );	}
	
	/**
	 * 삭제
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void delete( HttpServletRequest request, Object entity ) {	commonCrudDao.delete( request, entity );	}
	
	// 조회 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 조회
	 * @param request	HttpServletRequest	
	 * @param entity	객체
	 * @return			List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity ){
		return this.list( request, entity, null, null, 0, 0 );
	}
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param cPage			현재 페이지 번호
	 * @param cntPerPage	한 페이지 당 데이터 출력 개수
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, int cPage, int cntPerPage ){
		return this.list( request, entity, null, null, cPage, cntPerPage );
	}
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, List<WhereBean> wbList ){
		return this.list( request, entity, wbList, null, 0, 0 );
	}
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @param cPage			현재 페이지 번호
	 * @param cntPerPage	한 페이지 당 데이터 출력 개수
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, int cPage, int cntPerPage ){
		return this.list( request, entity, wbList, null, cPage, cntPerPage );
	}
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @param obList		정렬 목록 List<OrderBean>
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, List<OrderBean> obList ){
		return this.list( request, entity, wbList, obList, 0, 0 );
	}
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @param obList		정렬 목록 List<OrderBean>
	 * @param cPage			현재 페이지 번호
	 * @param cntPerPage	한 페이지 당 데이터 출력 개수
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, List<OrderBean> obList, int cPage, int cntPerPage ){
		return commonCrudDao.list( request, entity, wbList, obList, cPage, cntPerPage );
	}
	/**
	 * 조회 : 사용자 정의 쿼리로 직접 조회
	 * @param request		HttpServletRequest	
	 * @param query			사용자 직접 쿼리 조회
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, String query ) {
		return commonCrudDao.list( request, query );
	}
}