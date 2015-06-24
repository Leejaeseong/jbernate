app.controller('ctrlResultMgr',function($scope, $http, $ekathuwa, $q, $filter) {	// 변경이력 관리 컨트롤러
	
	// 변수 선언
	$scope.yyyy 			= new Date().format("%Y");	// 조회 화면에 출력되는 모델
	
	$scope.gridData			= new Array();		// 그리드 데이터
	
	$scope.prdgrpDataSelectBox 	= [];			// 제품그룹목록
	$scope.prdDataSelectBox 	= [];			// 제품목록
	$scope.hosptDataSelectBox 	= [];			// 병원목록
	
	$scope.yyyyDataSelectBox 	= new Array();	// 년도목록
	$scope.teamDataSelectBox 	= [];			// 팀목록
	$scope.hosptDataSelectBox 	= [];			// 병원목록
	
	// 년도목록 설정하기
	for( var i = 2015; i <= new Date().format("%Y"); i++ ) {
		$scope.yyyyDataSelectBox.push( { yyyy : i.toString() } );
	};
	
	// 팀목록 가져오기
	$http.post('../../mp/P00009/load.json'
			, { "searchType" : "teamSelectBox" }
	).success(function(data, status, headers, config){
		// 통신
		$scope.teamDataSelectBox = data.viewData;	// 데이터 바인딩
		$scope.searchTeamSeq = data.viewData[0].seq;		
	}).error(function(data, status, headers, config) {
	    $scope.modalAlert( con_msg_err_load_data );
	});
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
	
	//$scope.aggregateTemplate = "<div ng-click="row.toggleExpand()" ng-style="{'left': row.offsetleft}" class="ngAggregate"><span class="ngAggregateText">{{row.label CUSTOM_FILTERS}} ({{row.totalChildren()}} {{AggItemsLabel}})</span><div class="{{row.aggClass()}}"></div></div>
	
	var myHeaderCellTemplate = '<div class="ngHeaderSortColumn {{col.headerClass}}" ng-style="{\'cursor\': col.cursor}" ng-class="{ \'ngSorted\': !noSortVisible }">' +
							    '<div ng-click="col.sort($event)" ng-class="\'colt\' + col.index" class="ngHeaderText"><table><tr><td></td>{{col.displayName.split(";")[0]}}</tr><tr><td>{{col.displayName.split(";")[1]}}</td></tr><tr><td>{{col.displayName.split(";")[2]}}</td></tr></table></div>' +
							    '<div class="ngSortButtonDown" ng-show="col.showSortButtonDown()"></div>' +
							    '<div class="ngSortButtonUp" ng-show="col.showSortButtonUp()"></div>' +
							    '<div class="ngSortPriority">{{col.sortPriority}}</div>' +
							    '<div ng-class="{ ngPinnedIcon: col.pinned, ngUnPinnedIcon: !col.pinned }" ng-click="togglePin(col)" ng-show="col.pinnable"></div>' +
							    '</div>' +
							    '<div ng-show="col.resizable" class="ngHeaderGrip" ng-click="col.gripClick($event)" ng-mousedown="col.gripOnMouseDown($event)"></div>';
								
	// 컬럼 정의
	$scope.columnDefs = [		{ field: "userSeq.loginId"				, displayName: "아이디"		, width: 120, categoryDisplayName: 'address' }
							 , 	{ field: "userSeq.userNm"				, displayName: "담당자"		, width: 120, categoryDisplayName: 'address' }
							 , 	{ field: "actualSeq.prdSeq.prdNm"		, displayName: "제품"		, width: 120, categoryDisplayName: 'address' }
							 , 	{ field: "actualSeq.hosptSeq.hosptNm"	, displayName: "병원"		, width: 120 }
							 , 	{ field: "actualSeq.wholesalNm"			, displayName: "도매명"		, width: 120 }
							 , 	{ field: "actualSeq.salLocNm"			, displayName: "매출처"		, width: 120 }
							 , 	{ field: "actualSeq.branchNm"			, displayName: "지점"		, width: 120 }
						     , 	{ field: "insDt"		, displayName : "*변경일,테스트", width: 200
						    	 	, cellFilter: 'date:\'yyyy-MM-dd HH:mm:ss\''
						    	 	, headerCellTemplate: myHeaderCellTemplate
						        }
						     , 	{ field: "remk"			, displayName: "비고"		, width: 120 }
						  ];
	
	// 그리드 정의
	$scope.grid = collectProp( {
			  data: 'gridData'
			, columnDefs: 'columnDefs'
			, headerRowHeight : 70
			, showSelectionCheckbox	: false
			, selectWithCheckboxOnly: false
			, enableCellEditOnFocus	: false
			, enablePinning			: false
	});
	
	// Data 조회 통신 함수
	$scope.search = function( data, type, res ) {
		
		// 통신 시작
		$http.post('../../mp/P00017/load.json'
					, { 
							"searchYyyy" 		: $scope.yyyy
						,	"searchTeamSeq" 	: $scope.searchTeamSeq
						,	"searchHosptSeq"	: $scope.searchHosptSeq
						,	"searchUserSeq"		: con_user_seq
						,	"isAdmin"			: con_is_admin
					}
			).success(function(data, status, headers, config){
				//$scope.gridData 	= data.viewData;		// 데이터 바인딩
				
				// 컬럼 바인딩
				$scope.columnDefs = new Array();
				for( var i = 0; i < data.cData.length; i++ ) {
					var obj = new Object();
					obj.field = "field" + ( i + 1 );
					//obj.field = data.cData[i];
					obj.displayName = data.cData[i];
					obj.width = "150px";
					obj.headerCellTemplate = myHeaderCellTemplate;
					$scope.columnDefs.push( obj );
				}
				console.log( data.cData );
				console.log( data.data );
				$scope.gridData = data.data;				
				
		}).error(function(data, status, headers, config) {	// 오류
		    $scope.modalAlert( con_msg_err_load_data );	
		});
	};
	
});