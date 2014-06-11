<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>

<%-- noTemplate으로 설정 시 빈 레이아웃 적용( 팝업 ) --%>
<% if( request.getAttribute( "noLayoutTemplate" ) == null ) { %>
<t:insertAttribute name="pre"/>
<t:insertAttribute name="left"/>
<% } %>

<t:insertAttribute name="body"/>

<%-- noTemplate으로 설정 시 빈 레이아웃 적용( 팝업 ) --%>
<% if( request.getAttribute( "noLayoutTemplate" ) == null ) { %>
<t:insertAttribute name="post"/>
<% } %>