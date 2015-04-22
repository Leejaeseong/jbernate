<%@page import="com.jbernate.cm.util.StrUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@page import="com.jbernate.cm.util.ChkUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="./P00010.jsp" %>
	
    <%=ChkUtil.chkLogin( session ) ? "<meta http-equiv=\"refresh\" content=\"0; url=../Main/load\"></meta>" : ""%>
</head>

<body>

	<form id="frm" method="post" action="/cm/Session/submit">
	아이디<input name="id" type="text" /><br/>
	비밀번호<input name="pw" type="password" /><br/>
	<input type="submit" value="로그인" />
	</form>
	
	<br/>사용자명 = <%=StrUtil.nvl( session.getAttribute( "userNm" ) )%>

</body>
</html>