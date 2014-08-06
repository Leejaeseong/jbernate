<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="com.jbernate.cm.util.*" %>
<%
	//response.setStatus( HttpServletResponse.SC_OK);
	String ePgm	= "";
	String eMsg = "[Error Code] : 404";
	
	if( StrUtil.chkBlank( request.getAttribute( "exceptionProgramId" ) ) ) {	// BaseExceptionResolver에서 넘어온 경우 오류 Program ID 출력
		ePgm = "[Program ID] : " + request.getAttribute( "exceptionProgramId" );
	}
	if( exception != null ) {
		eMsg = "[Error msg] : " + StrUtil.cutString( exception.toString(), ConstUtil.LIMIT_JS_ALERT_STRING_CNT );
	}
	pageContext.setAttribute( "ePgm", ePgm );
	pageContext.setAttribute( "eMsg", eMsg );
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
	alert( "서버 오류가 발생하였습니다.\r\n"
			+ "문제가 지속될 경우 관리자에게 연락 부탁드립니다\r\n"
			+ "${ePgm}\r\n"
			+ "${eMsg}"
	);
	</script>
	<font color="red">
		Error occured
	</font>	
</body>

</html>