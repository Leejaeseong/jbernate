app.controller('ctrlTeamMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 팀관리 컨트롤러
	
	// 변수 선언
	$scope.dataTeamTempEntity	= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	$scope.max_i_seq			= con_i_max_seq;	// 신규 데이터인 경우 최대 값
	$scope.dataTeamMySelection	= [];
	
	// 그리드 정의
	$scope.gridTeamMgr = collectProp( con_option_grid ,{
			  data: 'dataTeamMgr'
			, selectedItems: []
			, columnDefs: [		{ field: "seq"		, displayName: "No"				, width: 120, pinned: true, enableCellEdit :false }
			 	             , 	{ field: "pgmNm"	, displayName: "*프로그램명"	, width: 120 }
			                 , 	{ field: "remk"		, displayName: "비고"			, width: 120 }
						  ]
	
	});
	
	// 감지 : data loaded
	$scope.$on('ngGridEventData', function(){
		// 데이터 Clear
		$scope.dataTeamTempEntity	= null;			// 임시 저장변수( 그리드 수정 시작/후 비교용 )
    });

	// 이벤트 : 그리드 수정 시작
	$scope.$on('ngGridEventStartCellEdit', function(data) {
		// 셀 내용을 저장하여 변경 여부 확인
		$scope.dataTeamTempEntity = angular.copy(data.targetScope.row.entity);
	});
	
	// 이벤트 : 그리드 수정 종료
	$scope.$on('ngGridEventEndCellEdit', function(data) {
		if( !angular.equals($scope.dataTeamTempEntity , data.targetScope.row.entity ) ) {	// 수정이 이루어지면 변경 변수에 담기
			 // CASE1. CRUD 없음 + seq 값이 있음 	: 처음 수정하는 것 	=> CRUD U 추가
			 if( !data.targetScope.row.entity.hasOwnProperty("CRUD") && data.targetScope.row.entity.hasOwnProperty("seq") ) {
				 data.targetScope.row.entity.CRUD = "U";
			 } 
		 }
	});
	
	// 조회 버튼 클릭
	$scope.chkTeamMgrSearch = function( dta, type, res ) {
		// 데이터 변경 여부 확인
		if( $scope.chkSaveData() ) {
			$scope.modalConfirmYn( con_msg_cof_exist_data_change, 'tpSearchConfirm' );
		} else {
			$scope.teamMgrSearch();
		}
	};
	// Data 조회 통신 함수
	$scope.teamMgrSearch = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00009/test.json').success(function(data, status, headers, config){
			// 통신
			$scope.dataTeamMgr = data.viewData;	// 데이터 바인딩
			
			// 데이터 초기화
			$scope.dataTeamTempEntity			= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
			$scope.gridTeamMgr.selectAll(false);
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
		if( $scope.dataTeamMgr ) {
			$scope.dataTeamMgr.push( { CRUD:"I", seq:$scope.max_i_seq-- } );
		}
	};
	
	// 행삭제
	$scope.delTeamMgrRow = function( event, data ) {
		console.log( "1", $scope.gridTeamMgr.selectedItems );
		
		angular.forEach($scope.gridTeamMgr.selectedItems, function( value, key ) {
			value.CRUD = "D";
			$scope.dataTeamMgr = $filter('filter')($scope.dataTeamMgr, function( element ) {
				return element.seq != value.seq;
			} );
		});
	};
	
	// 저장
	$scope.saveTeamMgr = function( event, data ) {

		// 저장 데이터 존재여부 판단
		if( !$scope.chkSaveData() ) {
			alert( con_msg_val_no_data_to_save );
			return;
		}
		
		// Validation
		if( !$scope.saveValTeamMgr() ) {
			alert( con_msg_val_essential );
			return;
		}
		

		// 저장 데이터 전송
		
		$http.post( '../../mp/P00009/testSave.json', $scope.getSaveData() ).success(function() {
			console.log("SAVE success !!!!");
		}).error(function() {
			console.log("SAVE Error !!!!");
		});
		
	};
	
	// Validation
	$scope.saveValTeamMgr = function() {
		var valOk = true;
		
		angular.forEach($scope.dataTeamMgr, function( value, key ) {
			if( value.hasOwnProperty( "CRUD" )		// CRUD 플래그가 있는것만 검사 
					&&  chkBlank( value.pgmNm ) 
			) {	
				valOk = false;
			}
		});
		
		return valOk;		
	};
	
	
	// Check savedata
	// true : exist save data
	// false : no save data
	$scope.chkSaveData = function() {
		if( 
			( $scope.gridTeamMgr.selectedItems && $filter('filter')($scope.gridTeamMgr.selectedItems, {CRUD:'D'} ) && $filter('filter')($scope.gridTeamMgr.selectedItems, {CRUD:'D'} ).length > 0 )
			||
			( $filter('filter')($scope.dataTeamMgr, {CRUD:'U'} ) && $filter('filter')($scope.dataTeamMgr, {CRUD:'U'} ).length > 0 )
			|| 
			( $filter('filter')($scope.dataTeamMgr, {CRUD:'I'} ) && $filter('filter')($scope.dataTeamMgr, {CRUD:'I'} ).length > 0 )
		) {
			return true;
		}else{
			return false;
		}
	};
	
	$scope.getSaveData = function() {
		return JSON.stringify( 
				angular.copy( $filter('filter')($scope.dataTeamMgr, {CRUD:'U'} ) )
				.concat( angular.copy( $filter('filter')($scope.dataTeamMgr, {CRUD:'I'} ) )
				.concat( angular.copy( $filter('filter')($scope.gridTeamMgr.selectedItems, {CRUD:'D'} ) ) )
			  )
		);
	};
	
});