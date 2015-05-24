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