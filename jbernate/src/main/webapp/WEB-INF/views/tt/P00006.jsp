<%--
	기능 : Angular js 기본 테스트	
--%>
<%@page import="com.jbernate.cm.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	int no = 1;
%>

<div>
	<button type="checkbox" class="btn btn-primary" ng-click="toggleContentView()">Toggle</button>
	
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewExpression">Expression</button>
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewAlert">Alert</button>
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewDatepicker">Datepicker</button>
</div>

<table ng-controller="tableCtrl" border="1">
<tr>
	<td class="header" width="5%">No</td>
	<td class="header" width="15%">주제</td>
	<td class="header" width="40%">내용</td>
	<td class="header" width="40%">비고</td>
</tr>

<tr ng-show="viewExpression">
	<td><%=no++%></td>
	<td>Expression</td>
	<td>1 + 1 = {{ 1 + 1 }}</td>
	<td ng-non-bindable>1 + 1 = {{ 1 + 1 }}</td>
</tr>

<tr ng-show="viewAlert">
	<td><%=no++%></td>
	<td>Alert</td>
	<td>
		<div ng-controller="alertCtrl">
		  <alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</alert>
		  <button class='btn btn-default' ng-click="addAlert()">Add Alert</button>
		</div>
	</td>
	<td ng-non-bindable>&nbsp;</td>
</tr>

<tr ng-show="viewDatepicker">
	<td><%=no++%></td>
	<td>Datepicker</td>
	<td>
		<div ng-controller="datepickerCtrl">
		    <pre>Selected date is: <em>{{dt | date:'fullDate' }}</em></pre>
		
		    <h4>Inline</h4>
		    <div style="display:inline-block; min-height:290px;">
		        <datepicker ng-model="dt" min-date="minDate" show-weeks="true" class="well well-sm"></datepicker>
		    </div>
		
		    <h4>Popup</h4>
		    <div class="row">
		        <div class="col-md-6">
		            <p class="input-group">
		              <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min-date="minDate" max-date="'2015-06-22'" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
		              <span class="input-group-btn">
		                <button type="button" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
		              </span>
		            </p>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-md-6">
		            <label>Format:</label> <select class="form-control" ng-model="format" ng-options="f for f in formats"><option></option></select>
		        </div>
		    </div>
		
		    <hr />
		    <button type="button" class="btn btn-sm btn-info" ng-click="today()">Today</button>
		    <button type="button" class="btn btn-sm btn-default" ng-click="dt = '2009-08-24'">2009-08-24</button>
		    <button type="button" class="btn btn-sm btn-danger" ng-click="clear()">Clear</button>
		    <button type="button" class="btn btn-sm btn-default" ng-click="toggleMin()" tooltip="After today restriction">Min date</button>
		</div>
	</td>
	<td ng-non-bindable>&nbsp;</td>
</tr>

</table>

<script type="text/javascript">
	// body에서 공통으로 사용할 사항 정의
	app.controller( "bodyCtrl", function( $scope, $log ) {
		$scope.toggleContentView = function() {
			$log.debug( $scope.viewAlert );
		};
	} );
	
	// table에서 공통으로 사용할 사항 정의
	app.controller( "tableCtrl", function( $scope ) {
	} );
	
	/* ▣ [ Button expand toggle ] ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
	function btnExpToggle( $scope ) {
		alert( $scope.viewCont );
	}
	
	/* ▣ [ Alert ] ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
	app.controller( "alertCtrl", function( $scope ) {
		$scope.alerts = [
   			{ type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.' },
   			{ type: 'success', msg: 'Well done! You successfully read this important alert message.' }
   		];
   		
   		$scope.addAlert = function() {
   			$scope.alerts.push({msg: 'Another alert!'});
   		};
   		
   		$scope.closeAlert = function(index) {
   			$scope.alerts.splice(index, 1);
   		};
	} );

	/* ▣ [ Datepicker ] ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
	var datepickerCtrl = function ($scope) {
		$scope.today = function() {
			$scope.dt = new Date();
		};
		$scope.today();
		
		$scope.clear = function () {
			$scope.dt = null;
		};
		
		// Disable weekend selection
		$scope.disabled = function(date, mode) {
			return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		};
		
		$scope.toggleMin = function() {
			$scope.minDate = $scope.minDate ? null : new Date();
		};
		$scope.toggleMin();
		
		$scope.open = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			
			$scope.opened = true;
		};
		
		$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
		};
		
		$scope.initDate = new Date('2016-15-20');
		$scope.formats = ['yyyy-MM-dd', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		$scope.format = $scope.formats[0];
	};	
</script>