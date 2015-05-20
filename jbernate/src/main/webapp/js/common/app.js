/* ▣ Bootstrap ( UI bootstrap ) ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
var app			= angular.module('rootApp', [ 'ui.bootstrap','ngGrid', 'ngRoute', 'ngResource' ] );

/** Left menu route */
//1. Module의 config API를 사용하면 서비스 제공자provider에 접근할 수 있다. 여기선 $route 서비스 제공자를 인자로 받아온다.
//2. 로딩바 관련 인터셉터 작업
app.config(function ($routeProvider, $httpProvider) {	
	$routeProvider	//$routeProvider의 when 메소드를 이용하면 특정 URL에 해당하는 라우트를 설정한다. 이때 라우트 설정객체를 전달하는데 <ng-view>태그에 삽입할 탬플릿에 해당하는 url을 설정객체의 templateUrl 속성으로 정의한다.
		.when('/Main', {templateUrl: '/template/Main.html'})
		.when('/Team', {templateUrl: '/template/mp/Team.html'})
	    .when('/Test2', {templateUrl: '/template/test/Test2.html', controller: 'userListCtrl'})	//라우트 설정객체에 controller 속성을 통하여 해당 화면에 연결되는 컨트롤러 이름을 설정할 수 있다.
	    .otherwise({redirectTo: '/Main'});	//otherwise 메소드를 통하여 브라우저의 URL이 $routeProivder에서 정의되지 않은 URL일 경우에 해당하는 설정을 할 수 있다.

	// 로딩 인터셉터 inject
	$httpProvider.responseInterceptors.push('globalLodingInterceptor');
	var globalLoadingFunction = function spinnerFunction(data, headersGetter) {
	    $("#globalLoadingImgId").show();
	    return data;
	  };
	$httpProvider.defaults.transformRequest.push(globalLoadingFunction);
	
}).controller('userListCtrl',function($scope) {	//사용자 관리화면의 컨트롤러를 정의한다. 이 컨트롤러는 URL이 ‘/userList’일 경우에만 적용이 된다. 이전의 템플릿 코드에서 별도로 ng-controller 지시자를 사용하지 않고 $routeProvider에서 라우트를 정의할 때 해당 컨트롤러와 연결되는 화면을 정의하였다.
	$scope.userList = [{
		name : '미나',
	    email : 'mina@gmail.com',
	    regDate : '2012-03-12'
	}, {
		name : '제시카',
		email : 'jess@gmail.com',
		regDate : '2012-03-12'
	}];
}).controller('ctrlTeamMgr',function($scope, $http) {	// 팀관리 컨트롤러
	$scope.gridTeamMgr = {
				data: 'dataTeamMgr'
			, 	enablePinning: true
			,	columnDefs: [{ field: "pgmNm", width: 120, pinned: true },
			                 { field: "remk", width: 120 }
							]
			
	};
	
	$http.post('../../mp/P00009/test.json').success(function(data, status, headers, config){
		
		$scope.dataTeamMgr = data.viewData;
		
	}).error(function(data, status, headers, config) {
	    alert( msg_err_load_data );
	});
	
	/*
	$http.get('https://api.github.com/users/mralexgray/repos').success(function(data) {
        $scope.persons = data;
    });

    $scope.gridOptions = { data: 'persons',
      columnDefs: [
      {field: 'name', displayName: 'Name'},
      {field: 'url', displayName: 'Url'}
      ]
    };
	*/
	
})
;

/** 로딩 인터셉터 선언 */
app.factory('globalLodingInterceptor', function ($q, $window) {
	return function (promise) {
		return promise.then(function (response) {
			$("#globalLoadingImgId").hide();
			return response;
		}, function (response) {
			$("#globalLoadingImgId").hide();
			return $q.reject(response);
		});
	};
});