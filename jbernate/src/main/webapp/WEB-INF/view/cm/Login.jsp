<%@page import="com.jbernate.cm.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="rootApp">
<head>
    <%=ChkUtil.chkLogin( session ) ? "<meta http-equiv=\"refresh\" content=\"0; url=../Main/load\"></meta>" : ""%>
    <% 
    	pageContext.setAttribute( "successMsg", request.getAttribute( ConstUtil.FORMAT_MODEL_SUCCESS ) );
    %>
    
    <script type="text/javascript" src="../../js/resource/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="../../js/resource/angular-1.2.17.js"></script>
    <script type="text/javascript" src="../../js/resource/angular-route.min.js"></script>
    <script type="text/javascript" src="../../js/resource/angular-resource.min.js"></script>
    <script type="text/javascript" src="../../js/resource/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="../../js/resource/bootstrap/ui-bootstrap-tpls-0.12.1.js"></script>
	<script type="text/javascript" src="../../js/resource/ng-grid.debug.js"></script>
    <script type="text/javascript" src="../../js/common/app.js"></script>
	<script type="text/javascript" src="../../js/util/cookie.js"></script>
	<script type="text/javascript" src="../../js/util/validUtil.js"></script>
	<script type="text/javascript" src="../../js/common/login.js"></script>
    
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/bootstrap/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="../../css/ng-grid.css" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/layout.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../../css/font-awesome-4.3.0/css/font-awesome.min.css" />
	
    <style type="text/css">
		.loginImgCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: -280px 0 0 -458px;
			border-color	: gray;
			border-width	: 1px;
			border-style	: none;
		}
		.inputIdLabelCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: -50px 0 0 -200px;
		}
		.inputIdInputCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: -55px 0 0 -120px;
		}
		.inputPwLabelCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: 0px 0 0 -200px;
		}
		.inputPwInputCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: -5px 0 0 -120px;
		}
		.buttonLoginCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: -55px 0 0 110px;
		}
		.checkRemIdCss{
			width			: 915px;
			height			: 551px;
			position		: absolute;
			left			: 50%;
			top				: 50%;
			margin			: 25px 0 0 110px;
		}
	</style>
	
	<script language="javascript">
		function clickSubmit(){
			if( $( "#loginId" ).val() == "" ) {
				alert( "아이디를 입력해 주세요" );
				return;
			} else if( $( "#pwd" ).val() == "" ) {
				alert( "비밀번호를 입력해 주세요" );
				return;
			}
			$( "#loginFrm" ).submit();
		}
		
		if( "<c:out value='${successMsg}' />" != "" && "<c:out value='${successMsg}' />" == "false" ) {
			alert( "계정 정보가 올바르지 않습니다" );
		}
		
	</script>
</head>

<body style="overflow: hidden;" ng-controller="loginController">
	
	<form id="loginFrm" name="loginFrm" method="post" action="/cm/Login/submit">
		<div class="loginImgCss">
			<img src="../../img/login.gif"/>
		</div>
		<div class="inputIdLabelCss">
			<h2>
				<b>ID</b>
			</h2>
		</div>
		<div class="inputIdInputCss">
			<h2>
				<input type="text" name="loginId" size="10" maxlength="30" ng-keypress="loginEnter($event)"	ng-model="modelLoginId" autofocus/>
			</h2>
		</div>
		
		<div class="inputPwLabelCss">
			<h2>
				<b>PW</b>
			</h2>
		</div>
		<div class="inputPwInputCss">
			<h2>
				<input type="password" id="pwd" name="pwd" size="10" maxlength="30" ng-keypress="loginEnter($event)" ng-model="modelPwd"/>
			</h2>
		</div>
		<div class="buttonLoginCss">
			<h2>
				<button type="button" class="btn btn-info btn-lg" onclick="clickSubmit();">
		        	LOGIN
		    	</button>
			</h2>
		</div>
		<div class="checkRemIdCss">
			<input type="checkbox" name="cbRemId" style="vertical-align: top;" ng-model="modelCbRemId"/> save id
		</div>
	</form>

</body>
</html>