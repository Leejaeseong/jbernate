/** Left menu route */
//1. Module의 config API를 사용하면 서비스 제공자provider에 접근할 수 있다. 여기선 $route 서비스 제공자를 인자로 받아온다.
//2. 로딩바 관련 인터셉터 작업
app.config(function ($routeProvider, $httpProvider) {	
	$routeProvider	//$routeProvider의 when 메소드를 이용하면 특정 URL에 해당하는 라우트를 설정한다. 이때 라우트 설정객체를 전달하는데 <ng-view>태그에 삽입할 탬플릿에 해당하는 url을 설정객체의 templateUrl 속성으로 정의한다.
		.when('/Main', {templateUrl: '/template/Main.html'})
		.when('/Team', {templateUrl: '/template/mp/Team.html'})
		.when('/User', {templateUrl: '/template/mp/User.html'})
		.when('/GrpPrd', {templateUrl: '/template/mp/GrpPrd.html'})
		.when('/Prd', {templateUrl: '/template/mp/Prd.html'})
		.when('/Hopt', {templateUrl: '/template/mp/Hopt.html'})
	    .when('/Test2', {templateUrl: '/template/test/Test2.html', controller: 'userListCtrl'})	//라우트 설정객체에 controller 속성을 통하여 해당 화면에 연결되는 컨트롤러 이름을 설정할 수 있다.
	    .otherwise({redirectTo: '/Team'});	//otherwise 메소드를 통하여 브라우저의 URL이 $routeProivder에서 정의되지 않은 URL일 경우에 해당하는 설정을 할 수 있다.

	// 로딩 인터셉터 inject
	$httpProvider.responseInterceptors.push('globalLodingInterceptor');
	var globalLoadingFunction = function spinnerFunction(data, headersGetter) {
	    $("#globalLoadingImgId").show();
	    return data;
	  };
	$httpProvider.defaults.transformRequest.push(globalLoadingFunction);
	
})
;