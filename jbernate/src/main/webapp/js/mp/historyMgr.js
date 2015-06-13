app.controller('ctrlHistoryMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 변경이력 관리 컨트롤러
	
	// 변수 선언
	$scope.stYyyymmdd 		= new Date().format("%Y-%m-%d");	// 조회 시작 일자
	$scope.edYyyymmdd 		= new Date().format("%Y-%m-%d");	// 조회 종료 일자
	
	$scope.gridData			= new Array();		// 그리드 데이터
	
	$scope.prdgrpDataSelectBox 	= [];			// 제품그룹목록
	$scope.prdDataSelectBox 	= [];			// 제품목록
	$scope.hosptDataSelectBox 	= [];			// 병원목록
	
	// 제품그룹목록 가져오기
	$scope.prdgrpQ = $http.post('../../mp/P00011/load.json'
			, { "searchType" : "prdgrpSelectBox" }
	).success(function(data, status, headers, config){
		// 통신
		$scope.prdgrpDataSelectBox = data.viewData;	// 데이터 바인딩
		
		if( $scope.prdgrpDataSelectBox.length > 0 ) {
			$scope.searchPrdgrpSeq = $scope.prdgrpDataSelectBox[0].seq;
			// 제품목록 가져오기
			$scope.getPrdList();
		}
	}).error(function(data, status, headers, config) {
	    $scope.modalAlert( con_msg_err_load_data );
	});
	// 제품목록 가져오기
	$scope.getPrdList = function() {
		$http.post('../../mp/P00012/load.json'
				, {   "searchType" 		: "prdSelectBox"
					, "searchPrdgrpSeq" : $scope.searchPrdgrpSeq
				  }
		).success(function(data, status, headers, config){
			// 통신
			$scope.prdDataSelectBox = data.viewData;	// 데이터 바인딩
			$scope.search();
		}).error(function(data, status, headers, config) {
		    $scope.modalAlert( con_msg_err_load_data );
		});
	};
	// 병원목록 가져오기
	$http.post('../../mp/P00013/load.json'
			, { "searchType" : "hosptSelectBox" }
	).success(function(data, status, headers, config){
		// 통신
		$scope.hosptDataSelectBox = data.viewData;	// 데이터 바인딩
	}).error(function(data, status, headers, config) {
	    $scope.modalAlert( con_msg_err_load_data );
	});

	// datepicker
	$scope.disabled = function(date, mode) {
		//return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		return false;
	};
	$scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	
	    $scope.opened = true;
	};

	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "seq"							, displayName: "No"			, width:  40, pinned: true, enableCellEdit :false }
	                     	 , 	{ field: "userSeq.loginId"				, displayName: "아이디"		, width: 120 }
							 , 	{ field: "userSeq.userNm"				, displayName: "담당자"		, width: 120 }
							 , 	{ field: "actualSeq.prdSeq.prdNm"		, displayName: "제품"		, width: 120 }
							 , 	{ field: "actualSeq.hosptSeq.hosptNm"	, displayName: "병원"		, width: 120 }
							 , 	{ field: "actualSeq.wholesalNm"			, displayName: "도매명"		, width: 120 }
							 , 	{ field: "actualSeq.salLocNm"			, displayName: "매출처"		, width: 120 }
							 , 	{ field: "actualSeq.branchNm"			, displayName: "지점"		, width: 120 }
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
						  "searchStYyyymm" 		: $scope.dateToStYyyymmdd()
						, "searchEdYyyymm" 		: $scope.dateToEdYyyymmdd()
						/*
						, "searchPrdgrpSeq"		: $scope.searchPrdgrpSeq
						, "searchPrdSeq"		: $scope.searchPrdSeq
						, "searchHosptSeq"		: $scope.searchHosptSeq
						, "searchUserNm"		: $scope.searchUserNm
						, "searchWholesalNm"	: $scope.searchWholesalNm	// 도매명
						, "searchSalLocNm"		: $scope.searchSalLocNm		// 매출처
						, "searchBranchNm"		: $scope.searchBranchNm		// 지점
						*/
					}
			).success(function(data, status, headers, config){
				$scope.gridData 	= data.viewData;	// 데이터 바인딩
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};
	
	$scope.dateToStYyyymmdd = function() {
		if( typeof $scope.stYyyymmdd == "string" )	 	return $scope.stYyyymmdd;
		else if( typeof $scope.stYyyymmdd == "object" )	return $scope.stYyyymmdd.format("%Y-%m-%d");
	};
	$scope.dateToEdYyyymmdd = function() {
		if( typeof $scope.edYyyymmdd == "string" )	 	return $scope.edYyyymmdd;
		else if( typeof $scope.edYyyymmdd == "object" )	return $scope.edYyyymmdd.format("%Y-%m-%d");
	};
	
});