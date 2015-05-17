<%--
	기능 : Layout test
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.jbernate.cm.util.*"%>
<%@ include file="../cm/Header.jsp"%>		<!-- Header -->
<%@ include file="../cm/Nav.jsp"%>			<!-- Top navigator -->
<%@ include file="../cm/SectionMenu.jsp"%>	<!-- Left navigator -->
<%=ChkUtil.chkLogin( session ) ? "" : "<meta http-equiv=\"refresh\" content=\"0; url=../Login/load\"></meta>"%>

	<!-- Main contents -->
	<ng-view></ng-view>
	
<%@ include file="../cm/Footer.jsp"%>	<!-- Footer -->