/** Header controller */
function headerController( $scope, $location, $window ) {
	/** logout */
	$scope.headerClickLogout = function() {
		//$location.path('./Logout/load').replace();
		//$location.url( "../../Logout/load" );
		//alert( $location.path() );
		//$scope.apply();
		//$location.url( "/cm/Logout/load" );
		//alert( $location.url() );
		$window.location.assign('/cm/Logout/load');
	};	
}