<%--
	기능 : jqGrid json 데이터
	비고 : jqGrid 기본 레이아웃이 로딩된 후 Json 데이터를 통해 데이터를 가져올 수 있는지 확인
	페이지 로딩 URL : /tt/P00004/load ( BasicController )
	데이터 로딩 URL : /tt/P00004/list ( BasicController )
--%>
<%@page import="com.jbernate.cm.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table id="list"></table>
<div id="pager"></div>

<script type="text/javascript">
	jQuery("#list").jqGrid({
		url:'/tt/T00004/list.json?viewNm=TT_ONE_TABLE_V'
		,datatype: "json"
		,jsonReader : { 
			//page: "page" 
			//total: "total" 
			root: "viewData" 
			//records: function(obj){return obj.length;}
			//repeatitems: false
			//id: "id"
		} 
		,colNames:['SEQ','Varchar2형', 'Date형', 'Clob형',' Blob형']
		,colModel:[
		     		{name:'seq'			,index:'seq'		, width:55}
		     		,{name:'vVarchar'	,index:'vVarchar'	, width:90}
		     		,{name:'vDate'		,index:'vDate'		, width:90}
		     		,{name:'vClob'		,index:'vClob'		, width:90}
		     		,{name:'vBlob'		,index:'vBlob'		, width:90}		     				
		]
		,rowNum:10
		,rowList:[10,20,30]
		,pager: '#pager'
		,sortname: 'seq'
		,viewrecords: true
		,sortorder: "desc"
		,caption:"JSON Example"
	});
	
	jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
</script>