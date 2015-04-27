<%--
	기능 : 공통 헤더	
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ page import="com.jbernate.cm.util.*"%>
<!DOCTYPE html>
<html style="height: 100%;" ng-app="rootApp">
<head>
	<meta charset="UTF-8">
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="pragma" content="no-cache"/>
	
	<title><%=PropertyUtil.get( "common", "site.title" )%></title>
	
    <script type="text/javascript" src="../../js/resource/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="../../js/resource/angular-1.2.17.js"></script>
    <script type="text/javascript" src="../../js/resource/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="../../js/resource/bootstrap/ui-bootstrap-tpls-0.12.1.js"></script>
	<script type="text/javascript" src="../../js/resource/ng-grid.debug.js"></script>
    <script type="text/javascript" src="../../js/app.js"></script>
    
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/bootstrap/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="../../css/ng-grid.css" />
    
    <link rel="stylesheet" type="text/css" media="screen" href="../../css/common.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/layout.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/font-awesome-4.3.0/css/font-awesome.min.css" />
	
</head>
<body style="overflow:auto">

	<header>
		<div style="float:left; text-align: left;width: 50%;">
			<img src="../../img/logo.gif">
		</div>
		<div style="float:right; text-align: right; width: 50%">
			<i class="fa fa-user" style="padding-top: 37px; clear: right " ></i>홍길동[관리자] 
			<button type="button" class="btn btn-primary" ng-model="singleModel">
	        	로그아웃
	    	</button>		
		</div>
	</header>	
