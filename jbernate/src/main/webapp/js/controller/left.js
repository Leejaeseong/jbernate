/** Left menu controller */
function leftMenuController( $scope, $location ) {
	/** Left menu class( activate ) */
	$scope.getLeftMenuClass = function(path) {
		if ($location.path().substr(0, path.length) == path) {
	      return "act-left-menu";
	    } else {
	      return "";
	    }
	};
	
	/** 권한에 따른 메뉴 숨김 */
	$scope.conIsAdmin = con_is_admin;

}