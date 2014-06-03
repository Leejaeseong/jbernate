package com.jbernate.cm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jbernate.cm.bean.OrderBean;
import com.jbernate.cm.bean.WhereBean;

/**
 * 공통 CRUD 서비스 인터페이스
 */
public interface CmService {

	/**
	 * 삽입
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	Object create( HttpServletRequest request, Object entity );
	
	/**
	 * 수정
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	void update( HttpServletRequest request, Object entity );
	
	/**
	 * 삭제
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	void delete( HttpServletRequest request, Object entity );
	
	// 조회 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 시퀀스만으로 오브젝트 조회
	 * @param request	HttpServletRequest
	 * @param entity	객체( 순번(PK) 가 설정된 상태의 객체 )
	 * @return			PK로 검색된 Object 객체
	 */
	Object get( HttpServletRequest request, Object entity );
	
	/**
	 * 조회
	 * @param request	HttpServletRequest	
	 * @param entity	객체
	 * @return			List<Object>
	 */
	@SuppressWarnings("rawtypes")
	List list( HttpServletRequest request, Object entity );
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param cPage			현재 페이지 번호
	 * @param cntPerPage	한 페이지 당 데이터 출력 개수
	 * @return				List<Object>
	 */
	@SuppressWarnings("rawtypes")
	List list( HttpServletRequest request, Object entity, int cPage, int cntPerPage );
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @return				List<Object>
	 */
	@SuppressWarnings("rawtypes")
	List list( HttpServletRequest request, Object entity, List<WhereBean> wbList );
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @param cPage			현재 페이지 번호
	 * @param cntPerPage	한 페이지 당 데이터 출력 개수
	 * @return				List<Object>
	 */
	@SuppressWarnings("rawtypes")
	List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, int cPage, int cntPerPage );
	/**
	 * 조회
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @param obList		정렬 목록 List<OrderBean>
	 * @return				List<Object>
	 */
	@SuppressWarnings("rawtypes")
	List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, List<OrderBean> obList );
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
	@SuppressWarnings("rawtypes")
	List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, List<OrderBean> obList, int cPage, int cntPerPage );
	/**
	 * 조회 : 사용자 정의 쿼리로 직접 조회
	 * @param request		HttpServletRequest	
	 * @param query			사용자 직접 쿼리 조회
	 * @return				List<Object>
	 */
	@SuppressWarnings("rawtypes")
	List queryList( HttpServletRequest request, String query );	
}