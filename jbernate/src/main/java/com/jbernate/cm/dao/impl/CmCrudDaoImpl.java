package com.jbernate.cm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbernate.cm.bean.OrderBean;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.dao.CmCrudDao;
import com.jbernate.cm.util.ConstantUtil;
import com.jbernate.cm.util.DatabaseUtil;
import com.jbernate.cm.util.LogUtil;

/**
 * 공통 CRUD DAO 인터페이스
 */
@Repository
public class CmCrudDaoImpl implements CmCrudDao{

	@Autowired SessionFactory sessionFactory;
	
	/**
	 * 삽입
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	@Override
	public Object create( HttpServletRequest request, Object entity ) {
		entity = setCreateCmCol( request, entity );	// 공통 컬럼 설정
		return DatabaseUtil.getHibernateSession( sessionFactory ).save( entity );	
	}
	
	/**
	 * 수정
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void update( HttpServletRequest request, Object entity ) {
		entity = setUpdateCmCol( request, entity );	// 공통 컬럼 설정
		DatabaseUtil.getHibernateSession( sessionFactory ).update( entity );
	}
	
	/**
	 * 삭제
	 * 	DEL_FLAG 컬럼이 존재하면 DEL_FLAG를 바꾸는 update를 수행하고
	 *  DEL_FLAG 컬럼이 없으면 삭제를 수행
	 * 삭제 갯수 반환되는 함수는 Deprecated 됨
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void delete( HttpServletRequest request, Object entity ) {
		entity = this.get( request, entity );
		/*
		@SuppressWarnings("unchecked")
		List<Tt11Slave1> list = this.list( request, entity, BeanUtil.oneWhere( "seq.seq", 94L, WhereBean.Clause.EQ ) );
		if( list.size() > 0 ){
			entity = list.get( 0 );
		}
		
		entity = this.get( request, entity );
		entity = new Tt11Slave1( new Tt11Master( 95L ) ); 
		 */
		
		try {
			if( entity.getClass().getDeclaredMethod( "setDelFlag", new String().getClass() ) != null ){
				Method m = entity.getClass().getDeclaredMethod( "setDelFlag", new String().getClass() );
				m.invoke( entity, new String( "1" ) );
				this.update( request, entity );
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogUtil.warn( "DEL_FLAG column is not exist at delete function");
			DatabaseUtil.getHibernateSession( sessionFactory ).delete( entity );
		}
	}
	
	/**
	 * create 시 공통 컬럼 값 설정
	 * @param request	HttpServletRequest
	 * @param entity	객체( 테이블/뷰 )
	 * @return			entity에 값 설정하여 반환
	 */
	private Object setCreateCmCol( HttpServletRequest request, Object entity ) {
		if( request == null ) return entity;
		Method m = null;
		try {			
			m = entity.getClass().getDeclaredMethod( "setCreDate", new Date().getClass() );		m.invoke( entity, new Date() );											// 생성일 설정
			m = entity.getClass().getDeclaredMethod( "setCreId", new String().getClass() );		m.invoke( entity, request.getSession().getAttribute( "loginSeq" ) );	// 생성자 설정 
			m = entity.getClass().getDeclaredMethod( "setCreObj", new String().getClass() );	m.invoke( entity, ConstantUtil.ID_DB_OBJECTOWN );						// 생성객체 설정
			m = entity.getClass().getDeclaredMethod( "setCreIp", new String().getClass() );		m.invoke( entity, request.getRemoteAddr() );							// 생성자 IP 설정
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LogUtil.warn( "Common create column is not exist");
		}
		return entity;
	}
	/**
	 * update 시 공통 컬럼 값 설정
	 * @param request	HttpServletRequest
	 * @param entity	객체( 테이블/뷰 )
	 * @return			entity에 값 설정하여 반환
	 */
	private Object setUpdateCmCol( HttpServletRequest request, Object entity ) {
		if( request == null ) return entity;
		Method m = null;
		try {			
			m = entity.getClass().getDeclaredMethod( "setModDate", new Date().getClass() );		m.invoke( entity, new Date() );											// 수정일 설정
			m = entity.getClass().getDeclaredMethod( "setModId", new String().getClass() );		m.invoke( entity, request.getSession().getAttribute( "loginSeq" ) );	// 수정자 설정
			m = entity.getClass().getDeclaredMethod( "setModObj", new String().getClass() );	m.invoke( entity, ConstantUtil.ID_DB_OBJECTOWN );						// 수정객체 설정
			m = entity.getClass().getDeclaredMethod( "setModIp", new String().getClass() );		m.invoke( entity, request.getRemoteAddr() );							// 수정자 IP 설정
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LogUtil.warn( "Common update column is not exist");
		}
		return entity;
	}
	// 조회 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 시퀀스만으로 오브젝트 조회
	 * @param request	HttpServletRequest
	 * @param entity	객체( 순번(PK) 가 설정된 상태의 객체 )
	 * @return			PK로 검색된 Object 객체
	 */
	@Override
	public Object get( HttpServletRequest request, Object entity ) {
		Method m = null;
		try {
			m = entity.getClass().getDeclaredMethod( "getSeq" );
			Object obj = m.invoke( entity );
			return DatabaseUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), (Serializable)obj );
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LogUtil.error( "Sequence column is not exist");
			e.printStackTrace();
		}
		
		/*
		Method m = null;
		long seq = 0;
		try {
			m = entity.getClass().getDeclaredMethod( "getSeq" );
			Object obj = entity;
			
			// 시퀀스가 인공키( Long형 )이면 바로 검색이 되지만
			// , 1:1 관계처럼 테이블 객체이면 객체를 계속 참조하여 찾아야 함
			// 결국 최종 seq만 얻어낼 수 있다면 get으로 값 가져올 수 있음
			if( m.getReturnType() != java.lang.Long.class ) {
				for( int i = 0; i < 100; i++ ) {	// 1:1 Join 깊이가 최대 100개까지 처리
					obj = m.invoke( obj );
					Method tempM = obj.getClass().getDeclaredMethod( "getSeq" );
					if( tempM.getReturnType() == java.lang.Long.class ){
						seq = (Long)tempM.invoke( obj );	// seq값 추출
						break;
					}
				}
			}
			
			// seq값이 없으면( 1:1 관계가 아니면 ) seq값 추출
			if( seq == 0 )	seq = (Long)m.invoke( entity );
			
			return DatabaseUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), new Long( seq ) );
			
			//return DatabaseUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), (Serializable)m.invoke( obj ) );
			
			//return DatabaseUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), 4L );			
			//return DatabaseUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), (Serializable)new Tt11Master( 4L ).getSeq() );
			//return DatabaseUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), 4L );
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LogUtil.error( "Sequence column is not exist");
			e.printStackTrace();
		}
		 */
		return null;
	}
	
	
	/**
	 * 조회
	 * @param request	HttpServletRequest	
	 * @param entity	객체
	 * @return			List
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
	 * @return				List
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
	 * @return				List
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
	 * @return				List
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
	 * @return				List
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, List<OrderBean> obList ){
		return this.list( request, entity, wbList, obList, 0, 0 );
	}
	/**
	 * 조회
	 * DEL_FLAG 를 자동으로 제외하고 조회 됨
	 * @param request		HttpServletRequest	
	 * @param entity		객체
	 * @param wbList		조건문 목록 List<WhereBean>
	 * @param obList		정렬 목록 List<OrderBean>
	 * @param cPage			현재 페이지 번호
	 * @param cntPerPage	한 페이지 당 데이터 출력 개수
	 * @return				List
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List list( HttpServletRequest request, Object entity, List<WhereBean> wbList, List<OrderBean> obList, int cPage, int cntPerPage ){
		Criteria criteria = DatabaseUtil.getCriteria( entity, sessionFactory );		// 테이블 Criteria 얻기
		
		if( wbList != null )	criteria = addWhereCriteria( criteria, wbList );	// 조건항목 존재 시 조건 Criteria 추가
		if( obList != null )	criteria = addOrderCriteria( criteria, obList );	// 정렬항목 존재 시 정렬 Criteria 추가
		
		// DEL_FLAG 체크
		criteria = addCommonWhereCriteria( criteria );
		
		// 페이징 처리 : 페이지 파라미터 존재 시
		if( cPage > 0 )	criteria.setFirstResult( ( cPage - 1 ) * cntPerPage ).setMaxResults( cntPerPage ).list();
		
		return (List)criteria.list();
	}
	/**
	 * 조회 : 사용자 정의 쿼리로 직접 조회
	 * @param request		HttpServletRequest	
	 * @param query			사용자 직접 쿼리 조회
	 * @return				List<Object>
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List queryList( HttpServletRequest request, String query ) {
		return DatabaseUtil.getHibernateSession( sessionFactory ).createQuery( query ).list();		
	}
	
	/**
	 * 조회 시 공통으로 처리할 조건절 추가
	 * 	- 조회 시 DEL_FLAG 가 존재한다면 DEL_FLAG IS NULL 인것만 조회
	 * @param criteria	Criteria
	 * @return			공통 조건문이 추가된 Criteria
	 */
	private Criteria addCommonWhereCriteria( Criteria criteria ) {
		try {
			Class<? extends Criteria> clazz = criteria.getClass();
			if( clazz.getDeclaredMethod( "getDelFlag" ) != null ){
				criteria.add( Restrictions.isNull( "delFlag" ) );
			}
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
			LogUtil.warn( "DEL_FLAG column is not exist at addCommonWhereCriteria function");
		}
		return criteria;
	}
	
	/**
	 * 조건문 추가
	 * @param criteria	Criteria
	 * @param wbList	List<WhereBean>
	 * @return			조건문이 추가된 Criteria
	 */
	private Criteria addWhereCriteria( Criteria criteria, List<WhereBean> wbList ) {
		for( WhereBean wb : wbList ) {
			if( wb.getClause().equals( "EQ" ) ){ 				criteria.add( Restrictions.eq( 			wb.getColNm(), wb.getColVal() ) 																			);
			}else if( wb.getClause().equals( "LIKEANY" ) ){ 	criteria.add( Restrictions.like( 		wb.getColNm(), wb.getColVal().toString(), MatchMode.ANYWHERE ) 												);
			}else if( wb.getClause().equals( "LIKEPRE" ) ){ 	criteria.add( Restrictions.like( 		wb.getColNm(), wb.getColVal().toString(), MatchMode.START ) 												);
			}else if( wb.getClause().equals( "LIKEPOST" ) ){	criteria.add( Restrictions.like( 		wb.getColNm(), wb.getColVal().toString(), MatchMode.END ) 													);
			}else if( wb.getClause().equals( "GE" ) ){ 			criteria.add( Restrictions.ge( 			wb.getColNm(), wb.getColVal() ) 																			);
			}else if( wb.getClause().equals( "IN" ) ) {			criteria.add( Restrictions.in( 			wb.getColNm(), (Object[])wb.getColVal() ) 																	);
			}else if( wb.getClause().equals( "ISNULL" ) ){ 		criteria.add( Restrictions.isNull( 		wb.getColNm() ) 																							);
			}else if( wb.getClause().equals( "ISNOTNULL" ) ){ 	criteria.add( Restrictions.isNotNull( 	wb.getColNm() ) 																							);
			}else if( wb.getClause().equals( "BETWEEN" ) ){ 	criteria.add( Restrictions.between(		wb.getColNm(), wb.getColVal().toString().split( "," )[ 0 ], wb.getColVal().toString().split( "," )[ 1 ] ) 	);
			}
		}
		return criteria;
	}
	/**
	 * 정렬문 추가
	 * @param criteria	Criteria
	 * @param obList	List<OrderBean>
	 * @return			정렬문이 추가된 Criteria
	 */
	private Criteria addOrderCriteria( Criteria criteria, List<OrderBean> obList ) {
		for( OrderBean ob : obList ) {
			if( ob.getType().equals( "DESC" ) ) 		criteria.addOrder( Property.forName( ob.getColNm() ).desc() );
			else if( ob.getType().equals( "ASC" ) ) 	criteria.addOrder( Property.forName( ob.getColNm() ).asc() );
		}
		return criteria;
	}
	
}