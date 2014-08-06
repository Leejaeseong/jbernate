package com.jbernate.cm.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Database관련 필요 Util
 */
public class DbUtil {

	/**
	 * Hibernate 세션 얻기
	 * @return
	 */
	public static Session getHibernateSession( SessionFactory sessionFactory ) {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Entity로 해당 테이블/뷰의 Criteria 얻기
	 * @param entity			Criteria를 얻고자 하는 테이블/뷰 객체, Class 또는 String
	 * @param sessionFactory	SessionFactory
	 * @return					Criteria
	 */
	public static Criteria getCriteria( Object entity, SessionFactory sessionFactory ) {
		String tvName = "";
		
		/*// 테스트 
		tvName = "TtOneTable";
		entity = "com.jbernate.tt.domain.table." + tvName;
		try {
			return DatabaseUtil.getHibernateSession( sessionFactory ).createCriteria( Class.forName( (String) entity ), StringUtil.firstLowStr( tvName ) );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}*/
		
		Class<?> clazz = null;
		if( entity instanceof String ){
			tvName = (String)entity;
			try{
				LogUtil.trace(ConstUtil.ID_PACKAGE_PREFIX 
							+ "." + ( StrUtil.nvl( tvName ).length() > 2 ? tvName.substring( 0, 2 ).toLowerCase() : "" )
							+ ".domain.view." 
							+ tvName);
				
				clazz 	= Class.forName( 
							ConstUtil.ID_PACKAGE_PREFIX 
							+ "." + ( StrUtil.nvl( tvName ).length() > 2 ? tvName.substring( 0, 2 ).toLowerCase() : "" )
							+ ".domain.view." 
							+ tvName );
			} catch ( Exception e ) {
					e.printStackTrace();
			}
		}else{
			tvName = entity.getClass().getName();
			clazz = entity.getClass();
		}
				
		// 패키지명을 포함하면 끝의 클래스 이름만 추출하여 Alias로 활용
		if( StrUtil.chkBlank( tvName ) && tvName.indexOf( "." ) != -1 ) tvName = tvName.substring( tvName.lastIndexOf( "." ) + 1 );	
		
		return DbUtil.getHibernateSession( sessionFactory ).createCriteria( clazz, StrUtil.firstLowStr( tvName ) );
		
		/*
		Class clazz = null;
		if( entity instanceof String ){
			tabName = (String)entity;
			try{
				clazz 	= (Class) Class.forName( tabName, true, null );
			} catch ( Exception e ) {
					e.printStackTrace();
			}
		}else{
			tabName = entity.getClass().getName();
			clazz = entity.getClass();
		}
				
		if( tabName != null && tabName.length() > 0 ) tabName = tabName.substring( tabName.lastIndexOf( "." ) + 1 );
		return DatabaseUtil.getHibernateSession( sessionFactory ).createCriteria( clazz, StringUtil.firstLowStr( tabName ) );
		*/
		
	}
	
	/**
	 * 테이블 또는 뷰 이름을 Entity명으로 변환 해 줌
	 * ex) TT_ONE_TABLE => TtOneTable
	 * @param objName	테이블 또는 뷰 이름
	 * @return			Entity 명
	 */
	public static String getEntityName( String objName ) {
		return StrUtil.firstUpperStr( StrUtil.makeJavaNameRule( objName.toLowerCase() ) );
	}
	
}