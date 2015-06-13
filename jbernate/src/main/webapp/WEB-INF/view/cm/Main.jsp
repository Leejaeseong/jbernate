<%--
	기능 : Layout test
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.jbernate.cm.util.*"%>
<%@ page import="com.jbernate.mp.util.*"%>
<%@ include file="../cm/Header.jsp"%>		<!-- Header -->

<%-- 세션없으면 로그인 페이지로 분기 --%>
<%=
	!ChkUtil.chkLogin( session ) ? "<meta http-equiv=\"refresh\" content=\"0; url=cm/Login/load\"></meta>" : "" 
%>

<script lang="javascript" charset="utf-8">
	// 상수 설정
	<%
		if( session.getAttribute( "roleCd" ) != null ) {	
			if( StrUtil.chkStrIn( session.getAttribute( "roleCd" ).toString()
					, MpConstUtil.MP_ROLE_ADMIN		// 관리자 계정	 
					, MpConstUtil.MP_ROLE_DEVELOP	// 개발 계정	 
				) ) {
				out.print( "con_is_admin = true;" );
			}
			
			out.print( "con_user_seq 		= " 	+ session.getAttribute( "userSeq" ).toString() + ";" );
			out.print( "con_user_login_id 	= '"	+ session.getAttribute( "loginId" ).toString() + "';" );
			out.print( "con_user_nm 		= '" 	+ session.getAttribute( "userNm" ).toString() + "';" );
		}
	%>
</script>

<%@ include file="../cm/Nav.jsp"%>			<!-- Top navigator -->
<%@ include file="../cm/Left.jsp"%>			<!-- Left navigator -->
<%=ChkUtil.chkLogin( session ) ? "" : "<meta http-equiv=\"refresh\" content=\"0; url=../Login/load\"></meta>"%>

	<!-- Main contents -->
	<ng-view></ng-view>
	
<%@ include file="../cm/Footer.jsp"%>	<!-- Footer -->