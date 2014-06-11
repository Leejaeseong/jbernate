package com.jbernate.cm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbernate.cm.bean.OrderBean;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.DbUtil;
import com.jbernate.cm.util.LogUtil;

/**
 * 공통 CRUD DAO 인터페이스
 */
@Repository
public class CmDaoImpl implements CmDao{

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
		return DbUtil.getHibernateSession( sessionFactory ).save( entity );	
	}
	
	/**
	 * 수정
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void update( HttpServletRequest request, Object entity ) {
		entity = setUpdateCmCol( request, entity );	// 공통 컬럼 설정
		DbUtil.getHibernateSession( sessionFactory ).update( entity );
	}
	
	/**
	 * 삭제
	 * 	DEL_FLAG 컬럼이 존재하면 DEL_FLAG를 바꾸는 update를 수행하고
	 *  DEL_FLAG 컬럼이 없으면 삭제를 수행
	 * 삭제 갯수 반환되는 함수는 Deprecated 됨
	 * 
	 * 삭제 시 get을 하는 이유는 객체 값에 seq만 넘어온 경우 DEL_FLAG 값 변경 시 다른 값들이 지워질 수 있기 때문
	 * get 시 1:1관계에서는 오류가 발생할 수 있는데( insert 후 바로 삭제 하는 경우 ) 이 때에는 merge 실행 후 삭제나 DEL_FLAG값을 변경하여 준다
	 * 	=> merge가 실행되기 때문에 삭제 전에 객체에 값을 설정한 경우 그 값이 update될수 있음에 주의
	 *  
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void delete( HttpServletRequest request, Object entity ) {
		Object obj = entity;	// get에 실패할 경우 대비한 entity 복사
		entity = this.get( request, entity );
		if( entity == null ) {	// get에 실패한 경우 merge 실행
			entity = DbUtil.getHibernateSession( sessionFactory ).merge( obj );
		}
		
		try {
			if( entity.getClass().getDeclaredMethod( "setDelFlag", new String().getClass() ) != null ){
				Method m = entity.getClass().getDeclaredMethod( "setDelFlag", new String().getClass() );
				m.invoke( entity, new String( "1" ) );
				this.update( request, entity );
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// DEL_FLAG 컬럼이 없는 경우 실 데이터 삭제
			LogUtil.warn( "DEL_FLAG column is not exist at delete function");
			DbUtil.getHibernateSession( sessionFactory ).delete( entity );
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
			m = entity.getClass().getDeclaredMethod( "setCreObj", new String().getClass() );	m.invoke( entity, ConstUtil.ID_DB_OBJECTOWN );						// 생성객체 설정
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
			m = entity.getClass().getDeclaredMethod( "setModObj", new String().getClass() );	m.invoke( entity, ConstUtil.ID_DB_OBJECTOWN );						// 수정객체 설정
			m = entity.getClass().getDeclaredMethod( "setModIp", new String().getClass() );		m.invoke( entity, request.getRemoteAddr() );							// 수정자 IP 설정
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LogUtil.warn( "Common update column is not exist");
		}
		return entity;
	}
	// 조회 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 시퀀스만으로 오브젝트 조회
	 * 
	 * ▣ 시퀀스가 인공키( Long형 )이면 바로 검색이 되지만
	 *		, 1:1 관계처럼 테이블 객체이면 한번 더 탐색해서 찾아야 함
	 * ▣ 결국 최종 seq만 얻어낼 수 있다면 get으로 값 가져올 수 있음
	 *	  , 1:1관계에서 한 세션 내에서는 Master+Slave insert 후 Slave를 바로 get으로 가져오지 못함, 굳이 필요하다면 list로 값 얻기가 가능
	 *		( insert/update를 하였다는건 slave정보를 가지고 있는 상태인데 또 get을 하는 형태의 app구성은 불필요 하기도 함 ) 
	 * ▣ ※ 1:1:1... 관계는 Master - Slave1( pk = Master ) - Slave2( pk = Master ) 형태만 지원...
	 *	  					 Master - Slave1( pk = Master ) - Slave2( pk = Slave1 ) 형태는 필요도 없고, DB 구성도 저런 방식은 지양
	 *
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
			
			if( m.getReturnType() != java.lang.Long.class ) {
				for( int i = 0; i < ConstUtil.LIMIT_LOOP_CNT; i++ ) {	// 1:1:1... Join인 경우 루프를 돌며 seq값을 찾음 
					Method tempM = obj.getClass().getDeclaredMethod( "getSeq" );
					if( tempM.getReturnType() == java.lang.Long.class ){
						obj = tempM.invoke( obj );	// seq값 추출
						break;
					}
				}
			}else {
				// seq값이 없으면( 1:1 관계가 아니면 ) seq값 추출
				if( obj == null )	obj = m.invoke( entity );
			}
			
			return DbUtil.getHibernateSession( sessionFactory ).get( entity.getClass(), (Serializable)obj );
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LogUtil.error( "Sequence column is not exist");
		}
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
		Criteria criteria = DbUtil.getCriteria( entity, sessionFactory );		// 테이블 Criteria 얻기
		
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
	 * @return				List<Object> : Bean에 매핑되지 않은 순수 Object형태임
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List queryList( HttpServletRequest request, String query ) {
		return DbUtil.getHibernateSession( sessionFactory ).createQuery( query ).list();		
	}
	/**
	 * 조회 : Native 쿼리로 직접 조회( DB 직접 쿼리 )
	 * Query에 .addEntity( 클래스.class )를 하면 객체를 매핑시킬 수 있으나 순수 DB Query 함수용도이므로 생략 함
	 * @param request	HttpServletRequest
	 * @param query		Navie DB 직접 쿼리 : 파라미터 부분은 ":파라미터명" 형태로 붙여야 함
	 * @param param		파라미터
	 * @return			List<Object> : Bean에 매핑되지 않은 순수 Object형태임
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List queryList( HttpServletRequest request, String query, HashMap param ) {
		Query q = DbUtil.getHibernateSession( sessionFactory ).createSQLQuery( query );
		
		// 파라미터가 있다면 매핑 시작 /////////////////////////////////////////
		if( param != null ){	
			Iterator iter = param.keySet().iterator();
			while( iter.hasNext() ) {
				Object obj = iter.next();
				if( param.get( obj ) != null ) {
					q.setParameter( obj.toString(), param.get( obj ) );
				}
			}
		}
		// 파라미터가 있다면 매핑 끝 ///////////////////////////////////////////
		
		return q.list();
	}
	
	/**▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	 * ▣ 유틸 부분  ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	 * ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	 */
	
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