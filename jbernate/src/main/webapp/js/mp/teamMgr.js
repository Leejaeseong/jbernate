app.controller('ctrlTeamMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 팀관리 컨트롤러
	
	// 변수 선언
	$scope.dataTeamTempEntity	= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	//$scope.dataTeamSave			= null;	// 저장 데이터 배열
	$scope.min_i_seq			= con_i_min_seq;	// 신규 데이터인 경우 최소 값
	
	// 그리드 정의
	$scope.gridTeamMgr = collectProp( con_option_grid ,{
			  data: 'dataTeamMgr'
			, selectedItems: []
			, columnDefs: [		{ field: "seq"		, displayName: "No"			, width: 120, pinned: true }
			 	             , 	{ field: "pgmNm"	, displayName: "프로그램명"	, width: 120 }
			                 , 	{ field: "remk"		, displayName: "비고"		, width: 120 }
							]
			, primaryKey : "seq"
	});
	
	// 감지 : data loaded
	$scope.$on('ngGridEventData', function(){
		// 데이터 Clear
		$scope.dataTeamTempEntity	= null;			// 임시 저장변수( 그리드 수정 시작/후 비교용 )
		//$scope.dataTeamSave			= new Array();	// 저장 데이터 배열
		
        // $scope.gridTeamMgr.selectRow(0, true); // 첫번째 Row 선택
    });

	// 이벤트 : 그리드 수정 시작
	$scope.$on('ngGridEventStartCellEdit', function(data) {
		// 셀 내용을 저장하여 변경 여부 확인
		$scope.dataTeamTempEntity = angular.copy(data.targetScope.row.entity);
		//console.log( "pgmNm", $scope.dataTeamTempEntity.pgmNm );
	});
	
	// 이벤트 : 그리드 수정 종료
	$scope.$on('ngGridEventEndCellEdit', function(data) {
		// console.log( "수정 후", $scope.dataTeamMgr );
		if( !angular.equals($scope.dataTeamTempEntity , data.targetScope.row.entity ) ) {	// 수정이 이루어지면 변경 변수에 담기
			 /* CASE1. CRUD 없음 + seq 값이 있음 	: 처음 수정하는 것 	=> CRUD U 추가
			 	CASE2. CRUD 없음 + seq 값이 없음 	: 행 추가 			=> CRUD I 추가
			 	CASE3. CRUD U or I					: 또 수정 			=> seq로 데이터 찾아 데이터 교체
			 */
			 // CASE1. CRUD 없음 + seq 값이 있음 	: 처음 수정하는 것 	=> CRUD U 추가
			 if( !data.targetScope.row.entity.hasOwnProperty("CRUD") && data.targetScope.row.entity.hasOwnProperty("seq") ) {
				 data.targetScope.row.entity.CRUD = "U";
				 //$scope.dataTeamSave.push( angular.copy( data.targetScope.row.entity ) );
				 //$scope.dataTeamSave.push( data.targetScope.row.entity );
			 } 
			 // CASE2. CRUD 없음 + seq 값이 없음 	: 행 추가 			=> CRUD I 추가
			 else if( !data.targetScope.row.entity.hasOwnProperty("CRUD") && !data.targetScope.row.entity.hasOwnProperty("seq") ) {
				 //data.targetScope.row.entity.CRUD = "I";
				 //data.targetScope.row.entity.seq = $scope.min_i_seq++;
				 //$scope.dataTeamSave.push( data.targetScope.row.entity );
				 //$scope.dataTeamSave.push( angular.copy( data.targetScope.row.entity ) );
			 } 
			 /*// CASE3. CRUD U or I					: 또 수정 			=> seq로 데이터 찾아 데이터 교체
			 else if( data.targetScope.row.entity.hasOwnProperty("CRUD") ) {
				 //$scope.dataTeamSave.push( data.targetScope.row.entity );
				 angular.forEach($scope.dataTeamSave, function(value, key) {
					 if( value.seq == data.targetScope.row.entity.seq ) {
						 console.log( value.seq, data.targetScope.row.entity.seq );
						 //value = data.targetScope.row.entity;
					 }
				 });
			 }*/
		 }
	});
	
	// 조회 버튼 클릭
	$scope.chkTeamMgrSearch = function( dta, type, res ) {
		// 데이터 변경 여부 확인
		if( $filter('filter')($scope.dataTeamMgr, {CRUD:'U'} ) || $filter('filter')($scope.dataTeamMgr, {CRUD:'I'} ) || $filter('filter')($scope.dataTeamMgr, {CRUD:'D'} ) ) {
			$scope.modalConfirmYn( con_msg_cof_exist_data_change, 'tpSearchConfirm' );
		} else {
			$scope.teamMgrSearch();
		}
	};
	// Data 조회 통신 함수
	$scope.teamMgrSearch = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00009/test.json').success(function(data, status, headers, config){
			// 데이터 초기화
			$scope.dataTeamTempEntity	= null;			// 임시 저장변수( 그리드 수정 시작/후 비교용 )
			$scope.dataTeamSave			= new Array();	// 저장 데이터 배열
			
			// 통신
			$scope.dataTeamMgr = data.viewData;	// 데이터 바인딩
			
		}).error(function(data, status, headers, config) {
		    alert( con_msg_err_load_data );
		});
	};
	
	// 확인 모달 OK 이벤트 listen
	$scope.$on( 'modalConfirmYnOk', function( event, data ) {
		//console.log( "OK", data.modalConfirmYnType );
		$scope.teamMgrSearch();
	});
	// 확인 모달 NO 이벤트 listen	
	$scope.$on( 'modalConfirmYnNo', function( event, data ) {
		//console.log( "NO", data.modalConfirmYnType );
	});
	
	// 행추가
	$scope.addTeamMgrRow = function( event, data ) {
		$scope.dataTeamMgr.push( { CRUD:"I", seq:$scope.min_i_seq++ } );
	};
	
	// 행삭제
	$scope.delTeamMgrRow = function( event, data ) {
		//console.log( "$scope.gridTeamMgr.selectedItems", $scope.gridTeamMgr.selectedItems );
		//$scope.gridTeamMgr.selectAll(true);
		//console.log( $scope.gridTeamMgr.selectedItems );
		
		/*
		_.each($scope.mySelections, function (person) {
			//Real remove (i.e from datastore)
			$scope.myData = _.filter($scope.myData, function(element){ return element.name != person.name;});
		});
		$scope.mySelections.splice(0, $scope.mySelections.length);
		*/
		
		//console.log( data );
		
		/*
		angular.forEach($scope.dataTeamMgr, function(value, key) {
			console.log( value.selected );
		});
		*/
		
		//console.log( $scope.gridTeamMgr.selectedItems );
		
		angular.forEach($scope.gridTeamMgr.selectedItems, function( value, key ) {
			//$scope.gridTeamMgr = $filter( $scope.gridTeamMgr, function( element ){ return element.seq != value.seq; } );
			/*
			console.log( 
					$filter( 
							$scope.gridTeamMgr, function( element ){ 
								return element.seq != value.seq; 
							} 
					)
			);
			*/
			
			/*$scope.dataTeamMgr = $filter('filter')($scope.dataTeamMgr, function( element ) {
				return element.seq != value.seq;
			} );*/
			
			$scope.dataTeamMgr = $filter('filter')($scope.dataTeamMgr, function( element ) {
				return element.seq != value.seq;
			} );
			
		});
		//$scope.mySelections.splice(0, $scope.gridTeamMgr.selectedItems.length );
		//$scope.gridTeamMgr.selectedItems.splice(0, $scope.gridTeamMgr.selectedItems.length );
		//$scope.gridTeamMgr.selectedItems = [];
		console.log( $scope.dataTeamMgr, $scope.gridTeamMgr.selectedItems );
	};
	
	/*
	$scope.$watch('dataTeamMgr', function (newVal, oldVal) { 
		console.log( "watch",newVal, oldVal );
	});
	*/
	
});