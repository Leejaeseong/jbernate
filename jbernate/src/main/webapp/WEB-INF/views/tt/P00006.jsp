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
	<button type="checkbox" class="btn btn-primary" ng-click="toggleContentView()">{{toggleText}}</button>
	
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewCont.viewExpression" 	ng-init="viewCont.viewExpression=false">Expression</button>
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewCont.viewAlert" 		ng-init="viewCont.viewAlert=false">Alert</button>
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewCont.viewDatepicker"	ng-init="viewCont.viewDatepicker=false">Datepicker</button>
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewCont.viewModal"		ng-init="viewCont.viewModal=false">Modal</button>
	<button type="button" class="btn btn-primary" btn-checkbox ng-model="viewCont.viewPoup"			ng-init="viewCont.viewPopup=false">Popup</button>
</div>

<table ng-controller="tableCtrl" border="1">
<tr>
	<td class="header" width="5%">No</td>
	<td class="header" width="15%">주제</td>
	<td class="header" width="40%">내용</td>
	<td class="header" width="40%">비고</td>
</tr>

<tr ng-show="viewCont.viewExpression">
	<td><%=no++%></td>
	<td>Expression</td>
	<td>1 + 1 = {{ 1 + 1 }}</td>
	<td ng-non-bindable>1 + 1 = {{ 1 + 1 }}</td>
</tr>

<tr ng-show="viewCont.viewAlert">
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

<tr ng-show="viewCont.viewDatepicker">
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

<tr ng-show="viewCont.viewModal">
	<td><%=no++%></td>
	<td>Modal</td>
	<td>
		<div ng-controller="modalCtrl">
		<%-- 
		    <script type="text/ng-template" id="P00007.html">
        		<div class="modal-header">
            		<h3 class="modal-title"><b>I'm a modal!</b></h3>
        		</div>
        		<div class="modal-body">
            		<ul>
                		<li ng-repeat="item in items">
                    		<a ng-click="selected.item = item">{{ item }}</a>
                		</li>
            		</ul>
            		Selected: <b>{{ selected.item }}</b>
				</div>
        		<div class="modal-footer">
            		<button class="btn btn-success" ng-click="ok()">OK</button>
            		<button class="btn btn-primary" ng-click="more()">More</button>
            		<button class="btn btn-warning" ng-click="cancel()">Cancel</button>
        		</div>
    		</script>
		--%>		

		    <button class="btn btn-default" ng-click="open()">Open me!</button>
		    <button class="btn btn-default" ng-click="open('lg')">Large modal</button>
		    <button class="btn btn-default" ng-click="open('sm')">Small modal</button>
		    <div ng-show="selected">Selection from a modal: {{ selected }}</div>
		</div>
	</td>
	<td ng-non-bindable>
		중첩 Modal 생성 시 Confirm 값 받아오는 부분이 이상해 질 수 있으므로<br/>
		단일 Alert또는 Confirm 용도로만 사용( Outside window 클릭하여 취소 가능 )
	</td>
</tr>

<tr ng-show="viewCont.viewPopup">
	<td><%=no++%></td>
	<td>Popup</td>
	<td>
		
	</td>
	<td ng-non-bindable>&nbsp;</td>
</tr>

</table>

<script type="text/javascript">
	/* ▣ [ Body ] ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
	app.controller( "bodyCtrl", function( $scope, $log ) {
		$scope.toggleText = "Open all";
		$scope.isExpand = false;	// 전체 보기 상태값 저장

		$scope.testList = [];
		
		// 전체 보기 토글
		$scope.toggleContentView = function() {	
			angular.forEach( $scope.viewCont, function( value, key ) {
				$scope.viewCont[key] = !$scope.isExpand;
				$scope.testList.push( value );
			} );
			
			$scope.isExpand = !$scope.isExpand;
			
			if( $scope.isExpand ) 	$scope.toggleText = "Close all";
			else					$scope.toggleText = "Open all";

			$log.debug( $scope.title );
			
		}; // end 전체 보기 토글
		
	} ); // end app.controller( "bodyCtrl", function( $scope, $log ) {
	
	/* ▣ [ Table ] ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
	app.controller( "tableCtrl", function( $scope ) {
	} );
	
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

	/* ▣ [ Modal ] ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
	var modalCtrl = function ($scope, $modal, $log) {

	  	$scope.items = ['item1', 'item2', 'item3'];
	
	  	$scope.open = function (size) {
	
		    var modalInstance = $modal.open({
					templateUrl: '/tt/P00007/load'
				,	controller: ModalInstanceCtrl
				,	size: size
				,	resolve: {
						items: function () {
							return $scope.items;
						}
					}
				//,	backdrop : 'static'	// prevent click event out of modal
			});
		
		    modalInstance.result.then(function (selectedItem) {
				$scope.selected = selectedItem;
		    }, function () {
				$log.info('Modal dismissed at: ' + new Date());	// include cancel
		    });
		};
	};
	
	// Please note that $modalInstance represents a modal window (instance) dependency.
	// It is not the same as the $modal service used above.
	var ModalInstanceCtrl = function ($scope, $modalInstance, items) {
		$scope.items = items;
	  	$scope.selected = {
	    	item: $scope.items[0]
  		};

		$scope.ok = function () {
			$modalInstance.close($scope.selected.item);
		};
	
		$scope.more = function () {
			$modalInstance.close($scope.selected.item);
		};
		
		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};
	};
</script>