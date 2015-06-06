app.controller('ctrlUserPopMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 사용자관리 컨트롤러
	
	$scope.teamDataSelectBox = [];
	// 팀목록 가져오기
	$http.post('../../mp/P00009/load.json'
			, { "searchType" : "teamSelectBox" }
	).success(function(data, status, headers, config){
		// 통신
		$scope.teamDataSelectBox = data.viewData;	// 데이터 바인딩
	}).error(function(data, status, headers, config) {
	    $scope.modalAlert( con_msg_err_load_data );
	});
	
	$scope.cellSelectEditableTemplate = 	'<select '
		+ 	'	ng-options="l.seq as l.teamNm for l in teamDataSelectBox" '
		+ 	'	data-placeholder="-- Select One --" '
		+ 	'	ng-model="COL_FIELD" '
		+ 	'	ng-class="\'colt\' + $index" '
		+ 	'	ng-cell-input '
		+ 	'	style="height:26px;" '
		+	'	ng-disabled="true"></select>';
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "userNm"		, displayName: "*이름"		, width: 120 }
						     , 	{ field: "loginId" 		, displayName: "*아이디"	, width: 120 }
						     , 	{ field: "teamSeq"		, displayName: "*팀"		, width: 120
						    	  	, enableCellEdit :false
						    	 	, cellTemplate : $scope.cellSelectEditableTemplate
						    	}
						     , 	{ field: "wrkStDt"		, displayName: "*업무시작일"	, width: 120
						    	 	, cellFilter: 'date:\'yyyy-MM-dd\''
						        }
						     , 	{ field: "contact"		, displayName: "연락처"			, width: 120 }
						     , 	{ field: "wrkRegion"	, displayName: "근무지"			, width: 120 }
						     , 	{ field: "remk"			, displayName: "비고"			, width: 120 }
						  ];
	
	// 그리드 정의
	$scope.gridUserMgr = collectProp( con_option_grid ,{
			  data: 'dataUserMgr'
			, columnDefs: 'columnDefs'
			, showSelectionCheckbox	: false
			, selectWithCheckboxOnly: false
			, enableCellEditOnFocus	: false
			, enablePinning			: false
			, rowTemplate: 	  '<div ng-click="onClickRow(row)" '
							+ ' 	ng-style="{ \'cursor\': row.cursor }" '
							+ '		ng-repeat="col in renderedColumns" '
							+ '		ng-class="col.colIndex()" '
							+ '		class="ngCell {{col.cellClass}}">'
							+ '		<div class="ngVerticalBar" '
							+ '			ng-style="{height: rowHeight}" '
							+ '			ng-class="{ ngVerticalBarVisible: !$last }">'
							+ '		&nbsp;'
							+ '		</div>'
							+ '		<div ng-cell></div>'
							+ '</div>'
	});
	
	// row click event
	$scope.onClickRow = function( rowItem ) {
		$scope.popupSelectRow( rowItem );
	};
	
	// 조회 버튼 클릭
	$scope.chkUserMgrSearch = function( data, type, res ) {
		$scope.userMgrSearch();
	};
	// Data 조회 통신 함수
	$scope.userMgrSearch = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00010/load.json'
					, { 
						  "searchUserNm" 	: $scope.userSearchUserNm
						, "searchLoginId" 	: $scope.userSearchLoginId		
						, "searchTeam" 		: $scope.userSearchTeam
					}
			).success(function(data, status, headers, config){

				// 조회 데이터 가공
				angular.forEach(data.viewData, function( value, key ) {
					// 팀정보 리스트박스화
					value.teamNm = value.teamSeq.teamNm;
					value.teamSeq = value.teamSeq.seq;
				});
			
				
				$scope.dataUserMgr = data.viewData;	// 데이터 바인딩
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};	

});