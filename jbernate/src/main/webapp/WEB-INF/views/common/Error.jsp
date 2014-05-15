<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<% 
	//response.setStatus( HttpServletResponse.SC_OK); 
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<title>Error</title>
</head>

<body>
	
	Error occured
	
</body>
<%
	System.out.println( exception.getMessage() );
%>
</html>