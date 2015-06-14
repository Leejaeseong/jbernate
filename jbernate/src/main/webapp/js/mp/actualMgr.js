app.controller('ctrlActualMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 실적 관리 컨트롤러
	
	// 변수 선언
	$scope.yyyymm 			= new Date().format("%Y-%m");	// 조회 화면에 출력되는 모델
	$scope.lastYyyymm 		= new Date().format("%Y-%m");	// 마지막 Yyyymm DB데이터
	$scope.isLast			= true;
	$scope.modCheckToggle	= true;
	$scope.conIsAdmin		= con_is_admin;		// 관리자 여부
	
	$scope.selRow			= null;				// 선택된 로우를 기억( 사용자 팝업 후 데이터 적용 용도 )
	$scope.userPop			= null;				// 사용자 조회 팝업 객체
	
	$scope.dataTempEntity	= null;				// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	$scope.max_i_seq		= con_i_max_seq;	// 신규 데이터인 경우 최대 값
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

	// 수정가능 여부 선택 창
	$scope.cellModYnTemplate = '<div style="height:30px;">'
								+	'<input '
								+	'	ng-class="\'colt\' + col.index" '
								+	'	style="margin-top:9px;" '
								+	'	ng-model="COL_FIELD" '
								+	'	ng-true-value=\"Y\"  '
								+	'	ng-false-value=\"N\" '
								+	'	type="checkbox" '
								+	'	/></div>';
	// 제품 컬럼 템플릿
	$scope.cellPrdTemplate = 	'<select '
								+ 	'	ng-options="l.prd as l.prdNm for l in prdDataSelectBox" '
								+ 	'	data-placeholder="-- Select One --" '
								+ 	'	ng-model="COL_FIELD" '
								+ 	'	ng-class="\'colt\' + $index" '
								+ 	'	ng-cell-input '
								+ 	'	style="height:26px; width:100%" '
								+	'	ng-disabled="!conIsAdmin" '
								+	'	ng-change="emitEndCellEdit()" '
								+	'></select>';
	// 병원 컬럼 템플릿
	$scope.cellHosptTemplate = 	'<select '
								+ 	'	ng-options="l.hospt as l.hosptNm for l in hosptDataSelectBox" '
								+ 	'	data-placeholder="-- Select One --" '
								+ 	'	ng-model="COL_FIELD" '
								+ 	'	ng-class="\'colt\' + $index" '
								+ 	'	ng-cell-input '
								+ 	'	style="height:26px; width:100%;" '
								+	'	ng-disabled="!conIsAdmin" '
								+	'	ng-change="emitEndCellEdit()" '
								+	'></select>';
	// 변경이력 컬럼 템플릿
	$scope.cellHistoryTemplate = '<img src="../../img/history.gif" width="25px" height="25px" style="margin-left:28px; margin-top:2px;" />'
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "modYn"	, displayName: "수정"		, width:  50, pinned: true
									, visible : $scope.conIsAdmin
									, enableCellEdit :false
									, cellTemplate : $scope.cellModYnTemplate
									, headerCellTemplate: "<div style=\"height:30px;vertical-align:bottom;\"> \
													                {{col.displayName}} \
													                <input type=\"checkbox\" \
													                		style=\"margin-top:11px;\" \
													                		ng-click=\"modToggle()\" \"> \
													        </div>"
								}
	                     	,	{ field: "seq"			, displayName: "No"			, width:  40, pinned: true, enableCellEdit :false }
	                     	, 	{ field: "loginId"		, displayName: "아이디"		, width: 120, enableCellEdit : false }
							, 	{ field: "userNm"		, displayName: "*담당자"	, width: 150
						   	 		, enableCellEdit : false
						    	}
							, 	{ field: "history"		, displayName: "변경이력" 	, width: 80
									, enableCellEdit : false
									, cellTemplate : $scope.cellHistoryTemplate
								}
							, 	{ field: "wholesalNm"	, displayName: "도매명"		, width: 120, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "prdSeq"		, displayName: "*제품"		, width: 150
									, enableCellEdit :false
									, cellTemplate : $scope.cellPrdTemplate
								}
							, 	{ field: "salLocNm"		, displayName: "매출처"		, width: 120, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "addr"			, displayName: "주소"		, width: 120, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "mfdsNo"		, displayName: "식약처번호"	, width: 120, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "zipCd"		, displayName: "우편번호"	, width: 120, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "standard"		, displayName: "규격" 		, width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "unitPrc"		, displayName: "*단가" 		, width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "salCnt"		, displayName: "*수량" 		, width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "salAmt"		, displayName: "*금액" 		, width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "boxCnt"		, displayName: "포장수량"	, width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "convCnt"		, displayName: "환산수량"	, width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "packDesc"		, displayName: "완포장/소분", width: 100, enableCellEdit : $scope.conIsAdmin }
							, 	{ field: "hosptSeq"		, displayName: "*병원"		, width: 150
									, enableCellEdit :false
									, cellTemplate : $scope.cellHosptTemplate
								}
							, 	{ field: "branchNm"		, displayName: "지점"		, width: 120, enableCellEdit : $scope.conIsAdmin }
						    , 	{ field: "remk"			, displayName: "비고"		, width: 120, enableCellEdit : $scope.conIsAdmin }
						  ];
	
	// 그리드 정의
	$scope.grid = collectProp( con_option_grid ,{
			  data: 'gridData'
			, selectedItems: []
			, columnDefs: 'columnDefs'
			, rowTemplate: 	  '<div ng-click="onClickRow(row, col.field)" '
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
			//, plugins: [new ngGridCsvExportPlugin()]
	});
	
	// 수정 전체 클릭
	$scope.modToggle = function() {
		for (var i = 0; i < $scope.gridData.length; i++) {
			if( $scope.modCheckToggle ) {
				$scope.gridData[i].modYn = "Y";
			}else {
				$scope.gridData[i].modYn = "N";
			}
        }
		$scope.modCheckToggle = !$scope.modCheckToggle;
	};
	
	// 컬럼 클릭
	$scope.onClickRow = function( rowItem, field ) {
		if( field == "userNm" ) {	// 담당자
			if( $scope.conIsAdmin ) {	// 관리자인 경우 사용자 조회 팝업
				$scope.selRow = rowItem.entity;
				$scope.userPop = $scope.modalTempURL( "../../template/mp/UserPop.html", 1000, 500, null );
			} else {
				if( rowItem.entity.modYn == "Y" ) {	// 수정이 가능한 상태라면 
					$scope.selRow = rowItem.entity;
					$scope.modalConfirmYn( con_msg_conf_appoint_user, 'tpAppointConfirm' );
				}else {
					$scope.modalAlert( con_msg_not_granted_update_row );
				}
			}
		}else if( field == "history" ) {	// 변경이력
			$scope.modalTempURL( "../../template/mp/HistoryPop.html", 700, 500, rowItem.entity.seq );
		}
	};
	
	// Data 조회 통신 함수
	$scope.search = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00016/load.json'
					, { 
						  "searchYyyymm" 		: $scope.dateToYyyymm()
						, "searchPrdgrpSeq"		: $scope.searchPrdgrpSeq
						, "searchPrdSeq"		: $scope.searchPrdSeq
						, "searchHosptSeq"		: $scope.searchHosptSeq
						, "searchUserNm"		: $scope.searchUserNm
						, "searchWholesalNm"	: $scope.searchWholesalNm	// 도매명
						, "searchSalLocNm"		: $scope.searchSalLocNm		// 매출처
						, "searchBranchNm"		: $scope.searchBranchNm		// 지점
					}
			).success(function(data, status, headers, config){
				
				// 조회 데이터 가공
				angular.forEach(data.viewData, function( value, key ) {
					// 데이터를 리스트박스화
					value.prdSeq 	= value.prdSeq.prdCd;
					value.hosptSeq 	= value.hosptSeq.hosptCd;
					value.userNm	= value.userSeq.userNm;
					value.loginId	= value.userSeq.loginId;
					value.userSeq	= value.userSeq.empCd;
				});
				
				$scope.gridData 	= data.viewData;	// 데이터 바인딩
				$scope.lastYyyymm	= data.lastYyyymm;	// 마지막 DB일자
				$scope.initData();						// 초기화
				
				$scope.yyyymm = $scope.dateToYyyymm();
				$scope.isLast = $scope.yyyymm >= $scope.lastYyyymm;
				
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};
	
	// 저장 데이터 전송		
	$scope.save = function( event, data ) {
		$http.post( '../../mp/P00016/submit.json'
				, { 
						"saveData" 	: $scope.getSaveData()
					, 	"yyyymm" 	: $scope.yyyymm 
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
		
		// TODO 필수값 확인 필요
		angular.forEach($scope.gridData, function( value, key ) {
			if( (		value.hasOwnProperty( "CRUD" )			// CRUD 플래그가 있는것만 검사
					&&	value.hasOwnProperty( "CRUD" )!= "D" )	// 삭제는 필수값 검사 불필요
					&&
				(
						chkBlank( value.hosptSeq )			// 병원SEQ
					|| 	chkBlank( value.userSeq )			// 담당자SEQ
					|| 	chkBlank( value.prdSeq )			// 제품
					|| 	chkBlank( value.unitPrc )			// 단가			
					|| 	chkBlank( value.salCnt )			// 수량
					|| 	chkBlank( value.salAmt )			// 금액
				)
			) {	
				valOk = false;
			}
		});
		
		return valOk;		
	};
	
	// 사용자 팝업에서 데이터를 선택
	$scope.$on('popupSelectRow', function( target, rowItem ){
		$scope.selRow.userSeq 	= rowItem.entity.seq;
		$scope.selRow.loginId 	= rowItem.entity.loginId;
		$scope.selRow.userNm 	= rowItem.entity.userNm;
		if( !$scope.selRow.hasOwnProperty( "CRUD" ) ) $scope.selRow.CRUD = "U";	// 변경 플래그 체크 후 강제 삽입
		$q.when( $scope.userPop ).then(function (m) {
			m.modal('hide');
		});
	});
	
	// datepicker 반환 형태 고정( object, 즉. 월 변경 시 date형으로 반환 됨 )
	$scope.dateToYyyymm = function() {
		if( typeof $scope.yyyymm == "string" )	 	return $scope.yyyymm;
		else if( typeof $scope.yyyymm == "object" )	return $scope.yyyymm.format("%Y-%m");
	};
	
	/** 확인 모달 OK 이벤트 listen */
	$scope.$on( 'modalConfirmYnOk', function( event, data ) {
		if( data.modalConfirmYnType == "tpSearchConfirm" ) { 			// 검색
			$scope.search();
		} else if( data.modalConfirmYnType == "tpSaveConfirm" ) { 		// 저장
			$scope.save();
		} else if( data.modalConfirmYnType == "tpAppointConfirm" ) {	// 담당자 지정
			console.log( $scope.selRow );
			$scope.selRow.loginId 	= con_user_login_id;
			$scope.selRow.userNm	= con_user_nm;
			$scope.selRow.userSeq	= con_user_seq;
		}
	});
	
	/*
	// 시작 시에 조회 수행 => 제품셀렉트박스 데이터 가져 온 후 조회 함
	$q.when( $scope.prdgrpQ ).then(function (m) {
		$scope.search();
	});
	*/
	
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