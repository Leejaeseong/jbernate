app.controller('ctrlHistoryPopMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 사용자관리 컨트롤러
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "loginId"		, displayName: "아이디"		, width: 120 }
							 , 	{ field: "userNm"		, displayName: "담당자"		, width: 120 }
						     , 	{ field: "insDt"		, displayName: "*변경일"	, width: 200
						    	 	, cellFilter: 'date:\'yyyy-MM-dd HH:mm:ss\''
						        }
						     , 	{ field: "remk"			, displayName: "비고"		, width: 120 }
						  ];
	
	// 그리드 정의
	$scope.grid = collectProp( con_option_grid ,{
			  data: 'gridData'
			, columnDefs: 'columnDefs'
			, showSelectionCheckbox	: false
			, selectWithCheckboxOnly: false
			, enableCellEditOnFocus	: false
			, enablePinning			: false
	});
	
	// Data 조회 통신 함수
	$scope.search = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00019/load.json'
					, { 
						  "searchActualSeq"	: $scope.modalParamData
					}
			).success(function(data, status, headers, config){
				// 조회 데이터 가공
				angular.forEach(data.viewData, function( value, key ) {
					// 팀정보 리스트박스화
					value.userNm 	= value.userSeq.userNm;
					value.loginId 	= value.userSeq.loginId;
				});
				
				$scope.gridData = data.viewData;	// 데이터 바인딩
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};	
	
	// 시작 시 조회 수행
	$scope.search();

});