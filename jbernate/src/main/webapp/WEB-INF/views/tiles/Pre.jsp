<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="jbernateApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		
		<title>jbernate</title>
		
		<!-- jquery file include before angular.js -->
		<!-- jquery > core -->
		<script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
		
		<!-- jquery > ui -->
		<link href="/css/ui-lightness/jquery-ui-1.10.4.css" rel="stylesheet">
		<script src="/js/jquery-ui-1.10.4.js" type="text/javascript"></script>
		
		<!-- jquery > grid -->
		<link rel="stylesheet" type="text/css" media="screen" href="/css/ui-lightness/jquery-ui-1.10.4.min.css" />	
		<link rel="stylesheet" type="text/css" media="screen" href="/css/ui-lightness/ui.jqgrid.css" />	
		<script src="/js/jqgrid-i18n/grid.locale-kr.js" type="text/javascript"></script>
		<script src="/js/jquery.jqGrid.min.js" type="text/javascript"></script>
		
		<!-- angularjs > core -->
		<script src="/js/angular-1.2.17.js" type="text/javascript"></script>

		<!-- angularjs > i18n -->
		<script src="/js/angular-i18n/angular-locale_ko-kr.js" type="text/javascript"></script>

		<!-- angularjs > bootstrap -->
		<script src="/js/ui-bootstrap-tpls-0.11.0.min.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.css" /> 
		
		<!-- common -->
		<link rel="stylesheet" type="text/css" media="screen" href="/css/common.css" />
		<script src="/js/common.js" type="text/javascript"></script>		
		
	</head>
	
	<body ng-controller="bodyCtrl">