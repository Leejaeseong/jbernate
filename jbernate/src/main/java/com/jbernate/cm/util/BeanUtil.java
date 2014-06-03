package com.jbernate.cm.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.jbernate.cm.bean.OrderBean;
import com.jbernate.cm.bean.Sb;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;

/**
 * Bean 관련 Util
 */
public class BeanUtil {

	/**
	 * Where절이 하나인 경우 편리하게 처리하기 위한 Util
	 * @param colNm		컬럼이름
	 * @param colVal	값
	 * @param c			조건 타입
	 * 					EQ 			: equal
	 * 					GE 			: greater then
	 * 					LE 			: lesser then
	 * 					LIKEANY 	: like ( MatchMode.ANYWHERE )
	 * 					LIKEPRE 	: like ( MatchMode.START )
	 * 					LIKEPOST 	: like ( MatchMode.END )
	 * 					IN			: colVal[]
	 * 					ISNULL		: isNull
	 * 					ISNOTNULL	: isNotNull
	 * 					BETWEEN		: between colVal.split( "," )[ 0 ] and colVal.split( "," )[ 1 ]
	 * @return	조건이 추가된 List<WhereBean>
	 */
	public static List<WhereBean> oneWhere( String colNm, Object colVal, Clause c ) {
		List<WhereBean> list = new ArrayList<WhereBean>();
		WhereBean wb = new WhereBean(colNm, colVal, c );
		list.add( wb );
		return list;
	}
	
	/**
	 * Order절이 하나인 경우 편리하게 처리하기 위한 Util
	 * @param colNm		정렬 컬럼 이름
	 * @param t			정렬 타입
	 * 					ASC		: ascend
	 * 					DESC	: descend
	 * @return	정렬 항목 추가된 List<WhereBean>
	 */
	public static List<OrderBean> oneOrder( String colNm, OrderBean.Type t ) {
		List<OrderBean> list = new ArrayList<OrderBean>();
		OrderBean ob = new OrderBean( colNm, t );
		list.add( ob );
		return list;
	}
	
	/**
	 * 공통적으로 모델에 삽입하는 항목 설정
	 * @param thiz		호출하는 Class
	 * @param session	HttpSession
	 * @param model		Model
	 * @param request	HttpServletRequest
	 * @return			공통 정보가 추가된 model
	 */
	public static Model getCommonModel(
			Object thiz
			, HttpSession session
			, Model model
			, HttpServletRequest request ) {
		
		model.addAttribute( "pgmId"		, ControllerUtil.getViewName( thiz.getClass() ) );
		model.addAttribute( "submitUrl"	, "/" + ControllerUtil.getViewName( thiz.getClass() ) + "/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_SUBMIT );
		
		return model;
	}
}