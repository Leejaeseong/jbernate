app.controller('ctrlUserMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 사용자관리 컨트롤러
	
	// 변수 선언
	$scope.dataUserTempEntity	= null;				// 임시 저장변수( 그리드 수정 시작/후 비교용 )
	$scope.max_i_seq			= con_i_max_seq;	// 신규 데이터인 경우 최대 값
	
	// 그리드 내의 셀렉트 박스 변경 이벤트 등록
	$scope.emitEndCellEdit = function() { 
		if( !this.row.entity.hasOwnProperty( "CRUD" ) ) {
			this.row.entity.CRUD = "U";	// 셀렉트 박스 변경 이벤트는 감지가 안되기에 수동으로 플래그 변경
		}
	};
	
	// 그리드 컬럼 템플릿
	/*
	$scope.cellSelectEditableTemplate =   ' <ul class="dropdown-menu" role="menu">'
									    + '    <li><a href="#">Action</a></li>'
									    + '    <li><a href="#">Another action</a></li>'
									    + '    <li><a href="#">Something else here</a></li>'
									    + '    <li class="divider"></li>'
									    + '    <li><a href="#">Separated link</a></li>'
									    + '  </ul>';
	*/
	/*
	$scope.teamDataSelectBox = [
	                            	{code: 'active'	, title: '액티브'}
	                            , 	{code: 'passive', title: '패시브'}
	                            , 	{code: 5		, title: '테스트팀'}
	                           ];
	*/
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
	
	//$scope.cellSelectEditableTemplate = ' <select ng-class="\'colt\' + col.index" ng-input="COL_FIELD" ng-model="COL_FIELD" ng-options="id as name for (id, name) in statuses" ng-blur="updateEntity(row)" />'
	/*$scope.cellSelectEditableTemplate = '	<div>'
										+ '  	<select ng-model="teamDataSelectBox[ row.rowIndex ].myStatus">'
										+ '    	<option ng-repeat="st in statuses">{{st}}</option>'
										+ '    	</select>'
										+ ' </div>';
	*/
	$scope.cellPwdTemplate = 	'<input ng-class="\'colt\' + col.index" ng-model="COL_FIELD" type="password"/>';
	$scope.cellSelectEditableTemplate = 	'<select '
										+ 	'	ng-options="l.seq as l.teamNm for l in teamDataSelectBox" '
										+ 	'	data-placeholder="-- Select One --" '
										+ 	'	ng-model="COL_FIELD" '
										+ 	'	ng-class="\'colt\' + $index" '
										+ 	'	ng-cell-input '
										+ 	'	style="height:26px;" '
										+	'	ng-change="emitEndCellEdit()" '
										+	'></select>';
	$scope.cellIdTemplate =   '<input ng-class="\'colt\' + col.index" '
							+ '			ng-model="COL_FIELD" '
							+ '			ng-edit-cell-if="row.getProperty( \'CRUD\' ) != null && row.getProperty( \'CRUD\' ) == \'I\'" ' 
							+ '			></input>'
							+ ' <div style="margin-top:4px; margin-left:4px;" ng-edit-cell-if="row.getProperty( \'CRUD\' ) == null || row.getProperty( \'CRUD\' ) != \'I\'">{{COL_FIELD}}</div>';
	
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "seq"			, displayName: "No"				, width:  40, pinned: true, enableCellEdit :false }
						     , 	{ field: "userNm"		, displayName: "*이름"			, width: 120 }
						     , 	{ field: "loginId"		
						    	 	, displayName: "*아이디"		
						    	 	, width: 120
						    	 	, cellTemplate : $scope.cellIdTemplate
						    	 	, enableCellEdit :false
						     	}
						     , 	{ field: "loginPwd"		
						    	 	, displayName: "*비밀번호"
						    	 	, width: 120
						    	 	//, editableCellTemplate : $scope.cellPwdTemplate
						    	 	, cellTemplate : $scope.cellPwdTemplate
						    	 	//, enableFocusedCellEdit:true
						    	}
						     , 	{ field: "teamSeq"		, displayName: "*팀"			, width: 120
						    	    //, editableCellTemplate: '../../template/nggrid/Selectbox.html'
						    	 	//, editableCellTemplate : $scope.cellSelectEditableTemplate
						    	 	, enableCellEdit :false
						    	 	, cellTemplate : $scope.cellSelectEditableTemplate
						    	 	//, cellTemplate: '<div style="margin-top:4px;margin-left:4px;">{{row.entity.teamNm}}</div>'
						    	 	//, cellTemplate: '<div style="margin-top:4px;margin-left:4px;">{{row.entity.teamSeq.teamNm}}</div>'
						    	 	//, editableCellTemplate: 'ui-grid/dropdownEditor'
						    	 	//, editDropdownOptionsArray: $scope.teamDataSelectBox
						    	 	//, editDropdownIdLabel: 'code'
						    	 	//, editDropdownValueLabel: 'title'
						    	}
						     , 	{ field: "wrkStDt"		, displayName: "*업무시작일"	, width: 120
						    	 	//, cellTemplate : '<div><datepicker ng-model="dt" min-date="minDate" show-weeks="true" class="well well-sm" custom-class="getDayClass(date, mode)"></datepicker></div>'
						    	 	//, cellFilter: 'date'
						    	 	, cellFilter: 'date:\'yyyy-MM-dd\''
						    	 	//, enableCellEdit :false
						    	 	//, cellTemplate : '<input type="text" pattern="/^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;" ng-pattern-restrict />'
						    	 	//, editableCellTemplate: '<input ng-class="\'colt\' + col.index" datepicker-popup is-open="true" ng-model="COL_FIELD" />'
						    	 	//, enableCellEdit: true
						    	 	//, type: 'date'
						    	 	//, editableCellTemplate: '<input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min-date="minDate" max-date="\'2015-06-22\'" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />'
						        }
						     , 	{ field: "contact"		, displayName: "연락처"			, width: 120 }
						     , 	{ field: "wrkRegion"	, displayName: "근무지"			, width: 120 }
						     , 	{ field: "remk"			, displayName: "비고"			, width: 120 }
						  ];
	
	// 그리드 정의
	$scope.gridUserMgr = collectProp( con_option_grid ,{
			  data: 'dataUserMgr'
			, selectedItems: []
			//, rowTemplate: '<div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" style="overflow: visible"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>'
			, columnDefs: 'columnDefs'
			//, cellEditableCondition : 'row.getProperty(\'CRUD\')!=null'	// row 전체에 대한 editable condition
	});
	
	// 감지 : data loaded
	$scope.$on('ngGridEventData', function(){
		// 데이터 Clear
		$scope.dataUserTempEntity	= null;			// 임시 저장변수( 그리드 수정 시작/후 비교용 )
    });

	// 이벤트 : 그리드 수정 시작
	$scope.$on('ngGridEventStartCellEdit', function(data) {
		// 셀 내용을 저장하여 변경 여부 확인
		$scope.dataUserTempEntity = angular.copy(data.targetScope.row.entity);
	});
	
	// 이벤트 : 그리드 수정 종료
	$scope.$on('ngGridEventEndCellEdit', function(data) {
		if( !angular.equals($scope.dataUserTempEntity , data.targetScope.row.entity ) ) {	// 수정이 이루어지면 변경 변수에 담기
			 // CASE1. CRUD 없음 + seq 값이 있음 	: 처음 수정하는 것 	=> CRUD U 추가
			 if( !data.targetScope.row.entity.hasOwnProperty("CRUD") && data.targetScope.row.entity.hasOwnProperty("seq") ) {
				 data.targetScope.row.entity.CRUD = "U";
			 } 
		 }
	});
	
	// 조회 버튼 클릭
	$scope.chkUserMgrSearch = function( data, type, res ) {
		// 데이터 변경 여부 확인
		if( $scope.chkSaveData() ) {
			$scope.modalConfirmYn( con_msg_cof_exist_data_change, 'tpSearchConfirm' );
		} else {
			$scope.userMgrSearch();
		}
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
					value.loginPwd = "";	// 조회 데이터 비밀번호 제거
					
					// 팀정보 리스트박스화
					value.teamNm = value.teamSeq.teamNm;
					value.teamSeq = value.teamSeq.seq;
					
				});
			
				$scope.dataUserMgr = data.viewData;	// 데이터 바인딩
				$scope.initData();
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};
	
	// 데이터 초기화
	$scope.initData = function() {
		$scope.dataUserTempEntity	= null;	// 임시 저장변수( 그리드 수정 시작/후 비교용 )
		$scope.gridUserMgr.selectAll(false);
	};
	
	// 확인 모달 OK 이벤트 listen
	$scope.$on( 'modalConfirmYnOk', function( event, data ) {
		if( data.modalConfirmYnType == "tpSearchConfirm" ) { 		// 검색
			$scope.userMgrSearch();
		} else if( data.modalConfirmYnType == "tpSaveConfirm" ) { 	// 저장
			$scope.saveUserMgr();
		}
	});
	// 확인 모달 NO 이벤트 listen	
	$scope.$on( 'modalConfirmYnNo', function( event, data ) {
		//console.log( "NO", data.modalConfirmYnType );
	});
	
	// 행추가
	$scope.addUserMgrRow = function( event, data ) {
		if( $scope.dataUserMgr ) {
			$scope.dataUserMgr.push( { CRUD:"I", seq:$scope.max_i_seq-- } );
		}
	};
	
	// 행삭제
	$scope.delUserMgrRow = function( event, data ) {
		angular.forEach($scope.gridUserMgr.selectedItems, function( value, key ) {
			value.CRUD = "D";
			$scope.dataUserMgr = $filter('filter')($scope.dataUserMgr, function( element ) {
				return element.seq != value.seq;
			} );
		});
	};
	
	// 저장
	$scope.chkSaveUserMgr = function( event, data ) {

		// 저장 데이터 없음
		if( !$scope.chkSaveData() ) {
			$scope.modalAlert( con_msg_val_no_data_to_save );
			return;
		}
		
		// Validation
		if( !$scope.saveValUserMgr() ) {
			$scope.modalAlert( con_msg_val_essential );
			return;
		}
		
		// 저장하시겠습니까?
		$scope.modalConfirmYn( con_msg_cof_save, 'tpSaveConfirm' );
				
	};
	
	// 저장 데이터 전송		
	$scope.saveUserMgr = function( event, data ) {
		$http.post( '../../mp/P00010/submit.json', $scope.getSaveData() ).success(function() {
			$scope.modalAlert( con_msg_save_ok );
			$scope.userMgrSearch();
		}).error(function() {	// 오류
			$scope.modalAlert( con_msg_save_fail );
		});
	};
	
	// Validation
	$scope.saveValUserMgr = function() {
		var valOk = true;
		
		angular.forEach($scope.dataUserMgr, function( value, key ) {
			if( (		value.hasOwnProperty( "CRUD" )						// CRUD 플래그가 있는것만 검사
					&&	value.hasOwnProperty( "CRUD" )!= "D" )				// 삭제는 필수값 검사 불필요
					&&
				(
						chkBlank( value.userNm )							// 사용자명
					||  chkBlank( value.loginId )							// 로그인아이디
					||	( value.CRUD == "I" && chkBlank( value.loginPwd ) )	// 행추가이고, 로그인 비밀번호
					||	chkBlank( value.teamSeq )							// 팀
					|| 	chkBlank( value.wrkStDt )							// 업무시작일
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
			( $scope.gridUserMgr.selectedItems && $filter('filter')($scope.gridUserMgr.selectedItems, {CRUD:'D'} ) && $filter('filter')($scope.gridUserMgr.selectedItems, {CRUD:'D'} ).length > 0 )
			||
			( $filter('filter')($scope.dataUserMgr, {CRUD:'U'} ) && $filter('filter')($scope.dataUserMgr, {CRUD:'U'} ).length > 0 )
			|| 
			( $filter('filter')($scope.dataUserMgr, {CRUD:'I'} ) && $filter('filter')($scope.dataUserMgr, {CRUD:'I'} ).length > 0 )
		) {
			return true;
		}else{
			return false;
		}
	};
	
	$scope.getSaveData = function() {
		return JSON.stringify( 
				angular.copy( $filter('filter')($scope.dataUserMgr, {CRUD:'U'} ) )
				.concat( angular.copy( $filter('filter')($scope.dataUserMgr, {CRUD:'I'} ) )
				.concat( angular.copy( $filter('filter')($scope.gridUserMgr.selectedItems, {CRUD:'D'} ) ) )
			  )
		);
	};
	
});