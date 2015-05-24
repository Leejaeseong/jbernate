app.controller('bodyCtrl',function($scope, $ekathuwa, $q) {
	
	/* 확인 Modal */
	$scope.modalConfirmYn = function ( msg, type ) {
		$scope.modalConfirmYnMsg = msg;
		$scope.modalConfirmYnType = type;
		$ekathuwa.modal({
            id: "modalConfirmYn",
            scope: $scope,
            templateURL: "../../template/common/ModalConfirmYn.html"
        });
    };
    
    // 확인 Modal OK 이벤트 하위로 전달
    $scope.modalConfirmYnOk = function() {
	    $scope.$broadcast('modalConfirmYnOk', {
	    	modalConfirmYnType : $scope.modalConfirmYnType
	    });
    };
    // 확인 Modal NO 이벤트 하위로 전달
    $scope.modalConfirmYnNo = function() {
	    $scope.$broadcast('modalConfirmYnNo', {
	    	modalConfirmYnType : $scope.modalConfirmYnType
	    });
    };

});