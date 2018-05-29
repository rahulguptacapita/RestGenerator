'use strict';

angular.module("myapp", []).controller("HelloController", ['$scope', '$http', '$q', function($scope, $http, $q) {

    fetchLanguages().then(function(r) {
    	$scope.languages = r.data;
    }, function(e) {
    	console.log('will do nothing');
    });
    
    $scope.selectedLanguage = "";
    
    $scope.themes = ['GET', 'POST', 'PUT', 'DELETE', 'GETBYID'];
    
    
    $scope.projectname = "TST100";
    
    
    $scope.methods = {
    		selected: {}
    };
    
    
    var program = {
        "lang": "nodejs",
        "vesion": "8",
        "device": "postman",
        "doesExecute": true
    };
    
    function fetchLanguages() {
    	return  $http.get("table");
    }
    
    $scope.selectedLanguageChanged = function(newValue, oldValue) {
    	
    }
    
    $scope.selectedThemeChanged = function(newValue, oldValue) {    	
    	
    }   

    function setDefault() {
        $scope.compile = {};
        $scope.execute = {};
        $scope.error = "";
    }
    
    $scope.clearfunc = function() {
    	setDefault();
    	myCodeMirror.setValue("");
	}

    $scope.defaultfunc = function() {
    	setDefault();
    	var code = getdefaultCodeByLanguage($scope.selectedLanguage);
        changeCode(code);
    }

    $scope.myfunc = function() {
    	
    	$scope.isDisabled = true;
        
    	var arr = Object.keys($scope.methods.selected);
    	
//    	var nondup = $scope.methods.selected.filter(function(item, pos) {
//    	    return a.indexOf(item) == pos;
//    	})
    	
    	var program = {
    		"projectname" : $scope.projectname,
    		"tables" : [
    			{
    				"requests" : arr,
    				"tablename" : $scope.selectedLanguage
    			}
    		]	
    	}
    	
        $http.post("run", program).then(function(response) {
        	$scope.isDisabled = false;
        	console.log('success');
        }, function(errResponse) {
        	$scope.isDisabled = false;
        	console.log('failure');
        });
    }
    ;
}
]);
