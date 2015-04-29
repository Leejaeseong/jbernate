<!DOCTYPE html>

<%@page import="com.jbernate.cm.util.ChkUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
	
		<%@include file="./WEB-INF/view/cm/Header.jsp" %>
		
		<%-- 세션없으면 로그인 페이지로 분기 --%>
		<%-- 
		<%=!ChkUtil.chkLogin( session ) ? "<meta http-equiv=\"refresh\" content=\"0; url=./Login\"></meta>" : ""%>
		--%>
		<%=
		/*
			!ChkUtil.chkLogin( session ) 
				?	"<meta http-equiv=\"refresh\" content=\"0; url=cm/Session/load\"></meta>" 
				: 	"<meta http-equiv=\"refresh\" content=\"0; url=cm/Main/load\"></meta>"
		*/
		"<meta http-equiv=\"refresh\" content=\"0; url=cm/Main/load\"></meta>"
		%>
		
		
	</head> 
	<body>
		
	</body>
</html>