app.controller('bodyCtrl',function($scope, $ekathuwa, $q) {
	
	/* 알림 Modal */
	$scope.modalAlert = function ( msg ) {
		$scope.modalAlertMsg = msg;
		$ekathuwa.modal({
            id: "modalAlert",
            scope: $scope,
            templateURL: "../../template/common/ModalAlert.html"
        });
    };
    
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
    
    // 템플릿URL Modal
    $scope.modalTempURL = function ( urlPath, width, height ) {
        return $ekathuwa.modal({
              id: "modalTemplId"
            , scope: $scope
            , header:false
            , bodyTemplateURL: urlPath
            , contentStyle: "width:"+width+"px;height:"+height+"px;"
        });
    };
    
    // 팝업에서 데이터 선택 이벤트
    $scope.popupSelectRow = function( rowItem ){
    	console.log( "popupSelectRow", rowItem );
    	$scope.$broadcast('popupSelectRow', rowItem );
    };
    
});