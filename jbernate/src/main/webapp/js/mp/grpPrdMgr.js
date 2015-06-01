app.controller('ctrlGrpPrdMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 제품 그룹 관리 컨트롤러
	
	// 변수 선언
	$scope.dataTempEntity	= null;				// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	$scope.max_i_seq		= con_i_max_seq;	// 신규 데이터인 경우 최대 값
	$scope.gridData			= new Array();		// 그리드 데이터
	
	// 그리드 내의 셀렉트 박스 변경 이벤트 등록
	$scope.emitEndCellEdit = function() { 
		if( !this.row.entity.hasOwnProperty( "CRUD" ) ) {
			this.row.entity.CRUD = "U";	// 셀렉트 박스 변경 이벤트는 감지가 안되기에 수동으로 플래그 변경
		}
	};

	// RATE 셀렉트박스
	$scope.rateDataSelectBox = [
                            	{code: '1'	, title: 'LAUNCH'}
                            , 	{code: '2'	, title: 'GROWTH'}
                           ];

	// RATE 컬럼 템플릿
	$scope.cellRateTemplate = 	'<select '
							+ 	'	ng-options="l.code as l.title for l in rateDataSelectBox" '
							+ 	'	data-placeholder="-- Select One --" '
							+ 	'	ng-model="COL_FIELD" '
							+ 	'	ng-class="\'colt\' + $index" '
							+ 	'	ng-cell-input '
							+ 	'	style="height:26px;" '
							+	'	ng-change="emitEndCellEdit()" '
							+	'></select>';
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "seq"			, displayName: "No"				, width:  40, pinned: true, enableCellEdit :false }
						     , 	{ field: "prdgrpNm"		, displayName: "*제품그룹명"	, width: 300 }
						     , 	{ field: "rateCd"		, displayName: "*RATE"			, width: 120
						    	 	, enableCellEdit :false
						    	 	, cellTemplate : $scope.cellRateTemplate
						     	}
						    , 	{ field: "remk"			, displayName: "비고"			, width: 120 }
						  ];
	
	// 그리드 정의
	$scope.grid = collectProp( con_option_grid ,{
			  data: 'gridData'
			, selectedItems: []
			, columnDefs: 'columnDefs'
	});
	
	// 감지 : data loaded
	$scope.$on('ngGridEventData', function(){
		// 데이터 Clear
		$scope.dataTempEntity	= null;			// 임시 저장변수( 그리드 수정 시작/후 비교용 )
    });

	// 이벤트 : 그리드 수정 시작
	$scope.$on('ngGridEventStartCellEdit', function(data) {
		// 셀 내용을 저장하여 변경 여부 확인
		$scope.dataTempEntity = angular.copy(data.targetScope.row.entity);
	});
	
	// 이벤트 : 그리드 수정 종료
	$scope.$on('ngGridEventEndCellEdit', function(data) {
		if( !angular.equals($scope.dataTempEntity , data.targetScope.row.entity ) ) {	// 수정이 이루어지면 변경 변수에 담기
			 // CASE1. CRUD 없음 + seq 값이 있음 	: 처음 수정하는 것 	=> CRUD U 추가
			 if( !data.targetScope.row.entity.hasOwnProperty("CRUD") && data.targetScope.row.entity.hasOwnProperty("seq") ) {
				 data.targetScope.row.entity.CRUD = "U";
			 } 
		 }
	});
	
	// 조회 버튼 클릭
	$scope.chkSearch = function( data, type, res ) {
		// 데이터 변경 여부 확인
		if( $scope.chkSaveData() ) {
			$scope.modalConfirmYn( con_msg_cof_exist_data_change, 'tpSearchConfirm' );
		} else {
			$scope.search();
		}
	};
	
	// Data 조회 통신 함수
	$scope.search = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00011/load.json'
					, { 
						  "searchPrdgrpNm" 	: $scope.searchPrdgrpNm
						, "searchRateCd" 	: $scope.searchRateCd
					}
			).success(function(data, status, headers, config){
				
			$scope.gridData = data.viewData;	// 데이터 바인딩
			$scope.initData();	// 초기화
			
			//if( !$scope.gridData ) $scope.gridData = new Array();	// 데이터가 없어도 객체는 생성되도록 함
			//console.log( "search", $scope.gridData );
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};
	
	// 데이터 초기화
	$scope.initData = function() {
		$scope.dataTempEntity	= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
		$scope.grid.selectAll(false);	// 전체 선택 해제
	};
	
	// 확인 모달 OK 이벤트 listen
	$scope.$on( 'modalConfirmYnOk', function( event, data ) {
		if( data.modalConfirmYnType == "tpSearchConfirm" ) { 		// 검색
			$scope.search();
		} else if( data.modalConfirmYnType == "tpSaveConfirm" ) { 	// 저장
			$scope.save();
		}
	});
	
	// 행추가
	$scope.addRow = function( event, data ) {
		$scope.gridData.push( { CRUD:"I", seq:$scope.max_i_seq-- } );		
	};
	
	// 행삭제
	$scope.delRow = function( event, data ) {
		angular.forEach($scope.grid.selectedItems, function( value, key ) {
			value.CRUD = "D";
			$scope.gridData = $filter('filter')($scope.gridData, function( element ) {
				return element.seq != value.seq;
			} );
		});
	};
	
	// 저장
	$scope.chkSave = function( event, data ) {

		// 저장 데이터 없음
		if( !$scope.chkSaveData() ) {
			$scope.modalAlert( con_msg_val_no_data_to_save );
			return;
		}
		
		// Validation
		if( !$scope.saveValData() ) {
			$scope.modalAlert( con_msg_val_essential );
			return;
		}
		
		// 저장하시겠습니까?
		$scope.modalConfirmYn( con_msg_cof_save, 'tpSaveConfirm' );
				
	};
	
	// 저장 데이터 전송		
	$scope.save = function( event, data ) {
		$http.post( '../../mp/P00011/submit.json', $scope.getSaveData() ).success(function() {
			$scope.modalAlert( con_msg_save_ok );
			$scope.search();
		}).error(function() {	// 오류
			$scope.modalAlert( con_msg_save_fail );
		});
	};
	
	// Validation
	$scope.saveValData = function() {
		var valOk = true;
		
		angular.forEach($scope.gridData, function( value, key ) {
			if( (		value.hasOwnProperty( "CRUD" )			// CRUD 플래그가 있는것만 검사
					&&	value.hasOwnProperty( "CRUD" )!= "D" )	// 삭제는 필수값 검사 불필요
					&&
				(
						chkBlank( value.prdgrpNm )				// 제품그룹명
					|| 	chkBlank( value.rateCd )				// RATE코드
				)
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
			( $scope.grid.selectedItems && $filter('filter')($scope.grid.selectedItems, {CRUD:'D'} ) && $filter('filter')($scope.grid.selectedItems, {CRUD:'D'} ).length > 0 )
			||
			( $filter('filter')($scope.gridData, {CRUD:'U'} ) && $filter('filter')($scope.gridData, {CRUD:'U'} ).length > 0 )
			|| 
			( $filter('filter')($scope.gridData, {CRUD:'I'} ) && $filter('filter')($scope.gridData, {CRUD:'I'} ).length > 0 )
		) {
			return true;
		}else{
			return false;
		}
	};
	
	$scope.getSaveData = function() {
		return JSON.stringify( 
				angular.copy( $filter('filter')($scope.gridData, {CRUD:'U'} ) )
				.concat( angular.copy( $filter('filter')($scope.gridData, {CRUD:'I'} ) )
				.concat( angular.copy( $filter('filter')($scope.grid.selectedItems, {CRUD:'D'} ) ) )
			  )
		);
	};
	
});