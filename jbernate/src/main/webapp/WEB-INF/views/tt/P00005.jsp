<%--
	기능 : Form 기능 테스트 - One table	
--%>
<%@page import="com.jbernate.cm.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form:form id="frm" modelAttribute="sb" action="${submitUrl}" method="post">
	Common Information 	<br/>
	pgmId = ${pgmId}	<br/>	
	<hr/><br/>
	
	sb.ttOneTable.seq = ${sb.ttOneTable.seq}	<br/>
	<form:input path="ttOneTable.tVarchar"	cssClass="frm.input"/>	<br/>
	<form:input path="ttOneTable.tClob"/>		<br/>
	<form:input path="ttOneTable.tBlob"/>		<br/>
	
	<hr/><br/>
	
	<input type="submit"/>
	
	<hr/><br/>
	
	<form:errors path="ttOneTable.tVarchar"/>	<br/>
	<form:errors path="ttOneTable.tClob"/>		<br/>
	<form:errors path="ttOneTable.tBlob"/>		<br/>
	
</form:form>