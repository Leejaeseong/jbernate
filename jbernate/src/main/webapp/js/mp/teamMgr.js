app.controller('ctrlTeamMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 팀관리 컨트롤러
	
	// 변수 선언
	$scope.dataTeamTempEntity	= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	$scope.min_i_seq			= con_i_min_seq;	// 신규 데이터인 경우 최소 값
	
	// 그리드 정의
	$scope.gridTeamMgr = collectProp( con_option_grid ,{
			  data: 'dataTeamMgr'
			, selectedItems: []
			, columnDefs: [		{ field: "seq"		, displayName: "No"			, width: 120, pinned: true, enableCellEdit :false }
			 	             , 	{ field: "pgmNm"	, displayName: "*프로그램명"	, width: 120 }
			                 , 	{ field: "remk"		, displayName: "비고"		, width: 120 }
							]
			, primaryKey : "seq"
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
			//$scope.dataTeamSave			= new Array();	// 저장 데이터 배열
			
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
		if( $scope.dataTeamMgr ) {
			$scope.dataTeamMgr.push( { CRUD:"I", seq:$scope.min_i_seq++ } );
		}
	};
	
	// 행삭제
	$scope.delTeamMgrRow = function( event, data ) {
		angular.forEach($scope.gridTeamMgr.selectedItems, function( value, key ) {
			$scope.dataTeamMgr = $filter('filter')($scope.dataTeamMgr, function( element ) {
				return element.seq != value.seq;
			} );
			
		});
	};
	
	// 저장
	$scope.saveTeamMgr = function( event, data ) {
	
		// Validation
		if( !$scope.saveValTeamMgr() ) {
			alert( con_msg_val_essential );
			return;
		}
		
	};
	
	// Validation
	$scope.saveValTeamMgr = function() {
		var valOk = true;
		
		angular.forEach($scope.dataTeamMgr, function( value, key ) {
			if( chkBlank( value.pgmNm ) ) {
				valOk = false;
			}
		});
		
		return valOk;		
	};
	
});