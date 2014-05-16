<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import ="com.jbernate.cm.util.*" %>
<% 
	//response.setStatus( HttpServletResponse.SC_OK);
	String eMsg = "<<< [Error Code] >>>\r\n404";
	if( exception != null ) {
		eMsg = "<<< [Error Code] >>>\r\n" + StringUtil.cutString( exception.getCause().toString(), ConstantUtil.LIMIT_JS_ALERT_STRING_CNT );
	}
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<title>Error</title>
</head>

<body>
	<script type="text/javascript">
	alert( "서버와의 데이터 통신 중 \r\n오류가 발생하였습니다.\r\n"
			+ "문제가 지속될 경우 관리자에게\r\n"
			+ "연락 부탁드립니다\r\n"
			+ "<%=eMsg%>" );	
</script>
	Error occured
	
</body>

</html>