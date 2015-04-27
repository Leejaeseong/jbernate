<%--
	기능 : Layout test
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.jbernate.cm.util.*"%>
<%@ include file="../cm/P00010.jsp"%>	<!-- Header -->
<%@ include file="../cm/P00011.jsp"%>	<!-- Top navigator -->
<%@ include file="../cm/P00012.jsp"%>	<!-- Left navigator -->

	<!-- 개별 Resource -->
	<script type="text/javascript" src="../../js/test/controller/P00008.js"></script>
	<link rel="stylesheet" type="text/css" href="../../css/test/P00008.css" />

	
		<div class="gridStyle" ng-grid="gridOptions" ng-controller="P00008.ctl.grd.test1">
        </div>	
        
        
	<!-- 
		<div ng-controller="P00008.ctl.alert.test1">
		  <alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</alert>
		  <button class='btn btn-default' ng-click="addAlert()">Add Alert</button>
		</div>
	 -->
	
<%@ include file="../cm/P00009.jsp"%>	<!-- Footer -->