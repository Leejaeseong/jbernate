var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope) {
    $scope.myData = [{name: "Moroni", age: 50},
                     {name: "Tiancum", age: 43},
                     {name: "Jacob", age: 27},
                     {name: "Nephi", age: 29},
                     {name: "Enos", age: 34}];
    $scope.gridOptions = { 
    		data: 'myData', 
    		enableCellSelection: true,
    		enableCellEditOnFocus: true,
    		enableRowSelection: false,
    		columnDefs: [{field: 'name', displayName: '이름', enableCellEdit: true}, {field:'age', displayName:'나이'}]
    };
    
    $scope.saveClick = function(){
    	console.log( $scope.myData );
    };
});