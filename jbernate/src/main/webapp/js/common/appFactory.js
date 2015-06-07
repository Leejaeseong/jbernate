/** 로딩 인터셉터 선언 */
app.factory('globalLodingInterceptor', function ($q, $window) {
	return function (promise) {
		return promise.then(		
			  // 성공
			  function (response) {
					$("#globalLoadingImgId").hide();
					return response;
			  	}
  			  // 실패
			, function (response) {
					$("#globalLoadingImgId").hide();
					return $q.reject(response);
				}
		);
	};
});