app.controller('ctrlPerBrandMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 팀/제품그룹/분기별 요율 관리 컨트롤러
	
	// 변수 선언
	$scope.yyyy 			= new Date().format("%Y");	// 조회 화면에 출력되는 모델
	$scope.selRow			= null;						// 선택된 로우를 기억( 사용자 팝업 후 데이터 적용 용도 )
	
	$scope.dataTempEntity	= null;				// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	$scope.max_i_seq		= con_i_max_seq;	// 신규 데이터인 경우 최대 값
	$scope.gridData			= new Array();		// 그리드 데이터
	$scope.excelData		= new Array();		// 엑셀 데이터
	
	$scope.yyyyDataSelectBox 	= new Array();	// 년도목록
	$scope.cycleDataSelectBox 	= new Array();	// 분기목록
	$scope.teamDataSelectBox 	= [];			// 팀목록
	$scope.prdgrpDataSelectBox 	= [];			// 제품그룹목록
	
	// 년도목록 설정하기
	for( var i = 2015; i <= new Date().format("%Y"); i++ ) {
		$scope.yyyyDataSelectBox.push( { yyyy : i.toString() } );
	};
	
	// 분기목록 설정하기
	$scope.cycleDataSelectBox.push( { cycleCd : "1", cycleNm : "1분기" } );	// 1분기
	$scope.cycleDataSelectBox.push( { cycleCd : "2", cycleNm : "2분기" } );	// 2분기
	$scope.cycleDataSelectBox.push( { cycleCd : "3", cycleNm : "3분기" } );	// 3분기
	$scope.cycleDataSelectBox.push( { cycleCd : "4", cycleNm : "전체" } );	// 전체분기
	
	// 팀목록 가져오기
	$http.post('../../mp/P00009/load.json'
			, { "searchType" : "teamSelectBox" }
	).success(function(data, status, headers, config){
		// 통신
		$scope.teamDataSelectBox = data.viewData;	// 데이터 바인딩
	}).error(function(data, status, headers, config) {
	    $scope.modalAlert( con_msg_err_load_data );
	});
	// 제품그룹목록 가져오기
	$http.post('../../mp/P00011/load.json'
			, { "searchType" : "prdgrpSelectBox" }
	).success(function(data, status, headers, config){
		// 통신
		$scope.prdgrpDataSelectBox = data.viewData;	// 데이터 바인딩
	}).error(function(data, status, headers, config) {
	    $scope.modalAlert( con_msg_err_load_data );
	});
	
	// 팀 컬럼 템플릿
	$scope.cellTeamTemplate = 		'<select '
								+ 	'	ng-options="l.teamCd as l.teamNm for l in teamDataSelectBox" '
								+ 	'	data-placeholder="-- Select One --" '
								+ 	'	ng-model="COL_FIELD" '
								+ 	'	ng-class="\'colt\' + $index" '
								+ 	'	ng-cell-input '
								+ 	'	style="height:26px;" '
								+	'	ng-change="emitEndCellEdit()" '
								+	'></select>';
	// 제품그룹 컬럼 템플릿
	$scope.cellPrdgrpTemplate = 	'<select '
								+ 	'	ng-options="l.seq as l.prdgrpNm for l in prdgrpDataSelectBox" '
								+ 	'	data-placeholder="-- Select One --" '
								+ 	'	ng-model="COL_FIELD" '
								+ 	'	ng-class="\'colt\' + $index" '
								+ 	'	ng-cell-input '
								+ 	'	style="height:26px;" '
								+	'	ng-change="emitEndCellEdit()" '
								+	'></select>';
	// 분기 컬럼 템플릿
	$scope.cellCycleTemplate = 	'<select '
								+ 	'	ng-options="l.cycleCd as l.cycleNm for l in cycleDataSelectBox" '
								+ 	'	data-placeholder="-- Select One --" '
								+ 	'	ng-model="COL_FIELD" '
								+ 	'	ng-class="\'colt\' + $index" '
								+ 	'	ng-cell-input '
								+ 	'	style="height:26px;" '
								+	'	ng-change="emitEndCellEdit()" '
								+	'></select>';
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "seq"		, displayName: "No"			, width:  40, pinned: true, enableCellEdit :false }
							 , 	{ field: "teamSeq"	, displayName: "*팀"		, width: 150
									, enableCellEdit :false
									, cellTemplate : $scope.cellTeamTemplate
								}
							 , 	{ field: "prdgrpSeq"	, displayName: "*제품그룹"		, width: 150
								, enableCellEdit :false
								, cellTemplate : $scope.cellPrdgrpTemplate
								}
							 , 	{ field: "cycle"	, displayName: "*분기"		, width: 150
								 , enableCellEdit :false
								 , cellTemplate : $scope.cellCycleTemplate
							 }
							 , 	{ field: "percentage"	, displayName: "*요율" 	, width: 100 }
						     , 	{ field: "remk"			, displayName: "비고"	, width: 120 }
						  ];
	
	// 그리드 정의
	$scope.grid = collectProp( con_option_grid ,{
			  data: 'gridData'
			, selectedItems: []
			, columnDefs: 'columnDefs'			
	});
	
	// Data 조회 통신 함수
	$scope.search = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00020/load.json'
					, { 
						  "searchYyyy" 		: $scope.yyyy
						, "searchTeamSeq"	: $scope.searchTeamSeq
						, "searchPrdgrpSeq"	: $scope.searchPrdgrpSeq						
					}
			).success(function(data, status, headers, config){
				$scope.setExcelData( data.viewData );	// 엑셀 데이터 설정
				
				// 조회 데이터 가공
				angular.forEach(data.viewData, function( value, key ) {
					// 데이터를 리스트박스화
					value.teamSeq 	= value.teamSeq.teamCd;
					value.prdgrpSeq = value.prdgrpSeq.seq;				
				});
				
				$scope.gridData = data.viewData;	// 데이터 바인딩
				$scope.initData();					// 초기화
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};
	
	// 저장 데이터 전송		
	$scope.save = function( event, data ) {
		$http.post( '../../mp/P00020/submit.json'
				, { 
						"saveData" 	: $scope.getSaveData()
					, 	"yyyy" 		: $scope.yyyy 
				} 
		 ).success(function() {
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
						chkBlank( value.teamSeq )			// 팀SEQ
					|| 	chkBlank( value.prdgrpSeq )			// 제품그룹SEQ
					|| 	chkBlank( value.cycle )				// 분기
					|| 	chkBlank( value.percentage )		// 요율
				)
			) {	
				valOk = false;
			}
		});
		
		return valOk;		
	};

	// 엑셀
	$scope.getHeader = function () {
		console.log( "$scope.excelData", $scope.excelData );
		return ["분기", "요율"];
	};
	$scope.setExcelData = function( viewData ) {
		var tmp = new Object();
		$scope.excelData = new Array();
		angular.forEach( viewData, function( value, key ) {
			tmp = new Object();
			tmp.cycle = value.cycle;
			tmp.percentage = value.percentage;
			$scope.excelData.push( tmp );
		});
	};
	
	// 시작 시에 조회 수행
	$scope.search();
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 공통화 영역
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** 수정 불필요 
	 	그리드 내의 셀렉트 박스 변경 이벤트 등록 */
	$scope.emitEndCellEdit = function() { 
		if( !this.row.entity.hasOwnProperty( "CRUD" ) ) {
			this.row.entity.CRUD = "U";	// 셀렉트 박스 변경 이벤트는 감지가 안되기에 수동으로 플래그 변경
		}
	};

	/** 수정 불필요 
	 	감지 : data loaded */
	$scope.$on('ngGridEventData', function(){
		// 데이터 Clear
		$scope.dataTempEntity	= null;			// 임시 저장변수( 그리드 수정 시작/후 비교용 )
    });

	/** 수정 불필요 
	 	이벤트 : 그리드 수정 시작 */
	$scope.$on('ngGridEventStartCellEdit', function(data) {
		// 셀 내용을 저장하여 변경 여부 확인
		$scope.dataTempEntity = angular.copy(data.targetScope.row.entity);
	});
	
	/** 수정 불필요 
	 	이벤트 : 그리드 수정 종료 */
	$scope.$on('ngGridEventEndCellEdit', function(data) {
		if( !angular.equals($scope.dataTempEntity , data.targetScope.row.entity ) ) {	// 수정이 이루어지면 변경 변수에 담기
			 // CASE1. CRUD 없음 + seq 값이 있음 	: 처음 수정하는 것 	=> CRUD U 추가
			 if( !data.targetScope.row.entity.hasOwnProperty("CRUD") && data.targetScope.row.entity.hasOwnProperty("seq") ) {
				 data.targetScope.row.entity.CRUD = "U";
			 } 
		 }
	});
	
	/** 수정 불필요 
	 	조회 버튼 클릭 */
	$scope.chkSearch = function( data, type, res ) {
		// 데이터 변경 여부 확인
		if( $scope.chkSaveData() ) {
			$scope.modalConfirmYn( con_msg_cof_exist_data_change, 'tpSearchConfirm' );
		} else {
			$scope.search();
		}
	};
	
	/** 수정 불필요 
	 	데이터 초기화 */
	$scope.initData = function() {
		$scope.dataTempEntity	= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
		$scope.grid.selectAll(false);	// 전체 선택 해제
	};
	
	/** 수정 불필요 
	 	확인 모달 OK 이벤트 listen */
	$scope.$on( 'modalConfirmYnOk', function( event, data ) {
		if( data.modalConfirmYnType == "tpSearchConfirm" ) { 		// 검색
			$scope.search();
		} else if( data.modalConfirmYnType == "tpSaveConfirm" ) { 	// 저장
			$scope.save();
		}
	});
	
	/** 수정 불필요 
	 	행추가 */
	$scope.addRow = function( event, data ) {
		$scope.gridData.push( { CRUD:"I", seq:$scope.max_i_seq-- } );
	};
	
	/** 수정 불필요 
	 	행삭제 */
	$scope.delRow = function( event, data ) {
		angular.forEach($scope.grid.selectedItems, function( value, key ) {
			value.CRUD = "D";
			$scope.gridData = $filter('filter')($scope.gridData, function( element ) {
				return element.seq != value.seq;
			} );
		});
	};
	
	/** 수정 불필요 
	 	저장 */
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
	
	/** 수정 불필요 
	 	Check savedata
	 	true : exist save data
		false : no save data */
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
	
	/** 수정 불필요 
	 	저장 데이터 얻기 */
	$scope.getSaveData = function() {
		return JSON.stringify( 
				angular.copy( $filter('filter')($scope.gridData, {CRUD:'U'} ) )
				.concat( angular.copy( $filter('filter')($scope.gridData, {CRUD:'I'} ) )
				.concat( angular.copy( $filter('filter')($scope.grid.selectedItems, {CRUD:'D'} ) ) )
			  )
		);
	};
	
});