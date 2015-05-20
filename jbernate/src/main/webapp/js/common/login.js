/** login controller */
function loginController( $scope, $location, $window, $element ) {
	/** enter key press */
	$scope.loginEnter = function(keyEvent) {
		if( keyEvent.which === 13 ){
			clickSubmit();	// 로그인 출력
		}
	};
	
	if( !chkBlank( getCookie( "loginSaveId" ) ) ) {	// 쿠키가 있으면
		/*
		var obj = $element.find('cbRemId');
		
		var keys = Object.keys(obj);

		for (var i = 0; i < keys.length; i++) {
		    var val = obj[keys[i]];
		    console.log( "i = " + i );
		    console.log( val );
		    // use val
		}
		//console.log( "111" );
		//console.log( obj[0] );
		console.log( obj.css( "color" ) );
		
		// console.log( $element.find('cbRemId') );
		// $element.find('cbRemId').checked = true;
		//console.log( "2 = " + $scope.modelCboxRemId );
		*/
		//console.log( $scope.modelCbRemId );
		
		$scope.modelCbRemId = true;
		$scope.modelLoginId = getCookie( "loginSaveId" );
	}else {
		$scope.modelCbRemId = false;
	}
	
	$scope.modelLoginId = "develop";
	$scope.modelPwd 	= "mundi!234";
	
	//$scope.modelLoginId.focus();
	//$scope.modelCbRemId = false;
}