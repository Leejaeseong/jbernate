<%--
	jqGrid json 데이터
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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