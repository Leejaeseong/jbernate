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
    <script type="text/javascript" src="../../js/resource/angular-route.min.js"></script>
    <script type="text/javascript" src="../../js/resource/angular-resource.min.js"></script>
    <script type="text/javascript" src="../../js/resource/angular-sanitize.min.js"></script>
    <script type="text/javascript" src="../../js/resource/ng-csv.js"></script>
	<script type="text/javascript" src="../../js/resource/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="../../js/resource/bootstrap/ui-bootstrap-tpls-0.12.1.js"></script>
	<!--  
	<script type="text/javascript" src="../../js/resource/ui-grid-unstable.js"></script>
	-->
	<script type="text/javascript" src="../../js/resource/ng-grid.debug.js"></script>
    <script type="text/javascript" src="../../js/resource/ekathuwa.min.js"></script>
    
    <script type="text/javascript" src="../../js/common/app.js"></script>
    <script type="text/javascript" src="../../js/common/appConfig.js"></script>
    <script type="text/javascript" src="../../js/common/appFactory.js"></script>
    <script type="text/javascript" src="../../js/common/bodyCtrl.js"></script>
    <script type="text/javascript" src="../../js/common/constant.js"></script>
    <script type="text/javascript" src="../../js/common/grid.js"></script>
    <script type="text/javascript" src="../../js/controller/header.js"></script>
    <script type="text/javascript" src="../../js/controller/left.js"></script>
    
    <script type="text/javascript" src="../../js/util/validUtil.js"></script>
    <script type="text/javascript" src="../../js/util/arrayUtil.js"></script>
    <script type="text/javascript" src="../../js/util/dateUtil.js"></script>
    
    <script type="text/javascript" src="../../js/mp/teamMgr.js"></script>		<!-- 팀관리 			-->
    <script type="text/javascript" src="../../js/mp/userMgr.js"></script>		<!-- 사용자관리 		-->
    <script type="text/javascript" src="../../js/mp/grpPrdMgr.js"></script>		<!-- 제품그룹관리 		-->
    <script type="text/javascript" src="../../js/mp/prdMgr.js"></script>		<!-- 제품관리 			-->
    <script type="text/javascript" src="../../js/mp/hosptMgr.js"></script>		<!-- 병원관리 			-->
    
    <script type="text/javascript" src="../../js/mp/rateMgr.js"></script>		<!-- RATE관리 			-->
    <script type="text/javascript" src="../../js/mp/goalMgr.js"></script>		<!-- 목표관리 			-->
    <script type="text/javascript" src="../../js/mp/perBrandMgr.js"></script>	<!-- 팀/브랜드/분기별 요율관리 -->
    <script type="text/javascript" src="../../js/mp/perYearMgr.js"></script>	<!-- 분기별 요율관리 -->
    <script type="text/javascript" src="../../js/mp/actualMgr.js"></script>		<!-- 실적관리 			-->
    
    <script type="text/javascript" src="../../js/mp/resultMgr.js"></script>		<!-- 실적조회 			-->    
    <script type="text/javascript" src="../../js/mp/historyMgr.js"></script>	<!-- 변경이력조회		-->
    
    <script type="text/javascript" src="../../js/mp/userPopMgr.js"></script>	<!-- 사용자관리 팝업 	-->
    <script type="text/javascript" src="../../js/mp/historyPopMgr.js"></script>	<!-- 변경이력조회 팝업	-->
    
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/bootstrap/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="../../css/ng-grid.css" />
	
    <!--  
    <link rel="stylesheet" type="text/css" media="screen" href="../../css/common.css" />
    -->
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/layout.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/font-awesome-4.3.0/css/font-awesome.min.css" />
	
</head>
<body ng-controller="bodyCtrl">
	<img id="globalLoadingImgId" ng-src="../../img/ajax-loader.gif" style="display:none;z-index:99999;" />
	
	<header class="north-back" ng-controller="headerController">
		<div style="float:left; text-align: left;width: 50%;">
			<a href="/cm/Main/load"><img src="../../img/logo.gif"></a>
		</div>
		<div style="float:right; text-align: right; width: 50%">
			<i class="fa fa-user" style="padding-top: 37px; clear: right " ></i><%=session.getAttribute( "userNm" ) + " [" + session.getAttribute( "teamNm" ) + "] "%> 
			<button type="button" class="btn btn-primary" ng-model="singleModel" ng-click="headerClickLogout()">
	        	로그아웃
	    	</button>		
		</div>
	</header>