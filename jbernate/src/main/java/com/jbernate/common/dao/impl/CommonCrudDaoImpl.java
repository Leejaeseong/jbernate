package com.jbernate.common.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbernate.common.bean.OrderBean;
import com.jbernate.common.bean.WhereBean;
import com.jbernate.common.dao.CommonCrudDao;
import com.jbernate.common.util.DatabaseUtil;

/**
 * 공통 CRUD DAO 인터페이스
 */
@Repository
public class CommonCrudDaoImpl implements CommonCrudDao{

	@Autowired SessionFactory sessionFactory;
	
	/**
	 * 삽입
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 * @return			삽입 후 PK 반환
	 */
	@Override
	public Object create( HttpServletRequest request, Object entity ) {	return DatabaseUtil.getHibernateSession( sessionFactory ).save( entity );	}
	
	/**
	 * 수정
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 */
	@Override
	public void update( HttpServletRequest request, Object entity ) {	DatabaseUtil.getHibernateSession( sessionFactory ).update( entity );	}
	
	/**
	 * 삭제
	 * @param request	HttpServletRequest
	 * @param entity	객체
	 * 삭제 갯수 반환되는 함수는 Deprecated 됨
	 */
	@Override
	public void delete( HttpServletRequest request, Object entity ) {	DatabaseUtil.getHibernateSession( sessionFactory ).delete( entity );	}
	
	// 조회 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	public List list( HttpServletRequest request, String query ) {
		return DatabaseUtil.getHibernateSession( sessionFactory ).createQuery( query ).list();		
	}
	
	/**
	 * 조건문 추가
	 * @param criteria	Criteria
	 * @param wbList	List<WhereBean>
	 * @return
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
	 * @return
	 */
	private Criteria addOrderCriteria( Criteria criteria, List<OrderBean> obList ) {
		for( OrderBean ob : obList ) {
			if( ob.getType().equals( "DESC" ) ) 		criteria.addOrder( Property.forName( ob.getColNm() ).desc() );
			else if( ob.getType().equals( "ASC" ) ) 	criteria.addOrder( Property.forName( ob.getColNm() ).asc() );
		}
		return criteria;
	}
	
}