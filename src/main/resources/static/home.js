'use strict';

angular.module("myapp", []).controller("HelloController", ['$scope', '$http', '$q', function($scope, $http, $q) {

    fetchLanguages().then(function(r) {
    	$scope.languages = r.data;
    }, function(e) {
    	console.log('will do nothing');
    });
    
    $scope.buildDisabled = true;
    
    $scope.selectedLanguage = "";
    
    $scope.themes = ['GET', 'POST', 'PUT', 'DELETE', 'GETBYID'];
    
    $scope.projectname = "TST100";
    
    $scope.methods = {
    		selected: {}
    };
    
    $scope.languagesSel = {
    		selected: {}
	};
	

	$scope.logs = "";
    
    
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

    
    function makeChoicesNull() {
    	$scope.methods.selected = {};
    	$scope.languagesSel.selected = {};
    }
    
    
    $scope.myBuildFunc = function() {

    	$http.get("builddeploy"+ "?buildurl="+ $scope.buildCodePath).then(function(response) {
    		console.log(' started building ');
    	}, function(errResponse) {
    		console.log(' error on building ');
    	});
	}
	

	setInterval(function(){
		
		$http.get("output").then(function(response) {
			$scope.logs = response.data.message;
    	}, function(errResponse) {
			$scope.logs = errResponse.data.message;
    		console.log(' error on building ');
    	});

	}, 3000);


    
    
    $scope.myfunc = function() {
    	
        
    	var arr = Object.keys($scope.methods.selected);
    	var lanSel = Object.keys($scope.languagesSel.selected);
    	
    	if(!$scope.projectname || 0 === $scope.projectname.length ) {
    		makeChoicesNull();
    		window.alert("project name is empty");
    		return;
    	}
    	
    	if(arr.length == 0) {
    		makeChoicesNull();
    		window.alert("No method selected ");
    		return;
    	}
    	
    	if(lanSel.length == 0) {
    		makeChoicesNull();
    		window.alert("No table selected");
    		return ;
    	}
    	
    	$scope.isDisabled = true;
    	
    	var a = [];
    	
    	lanSel.forEach(function (l) {
    		a.push({
    			"requests" : arr,
				"tablename" : l
    		})
    	});
    	
    	
    	var program = {
    		"projectname" : $scope.projectname,
    		"tables" : a
    	}
    	
        $http.post("run", program).then(function(response) {
        	$scope.isDisabled = false;
        	$scope.buildDisabled = false;
        	$scope.buildCodePath = response.data.projectname;
        	console.log('success');
        	makeChoicesNull();
        	window.alert("Code generated Successfully");
        	
        }, function(errResponse) {
        	$scope.isDisabled = false;
        	console.log('failure');
        	makeChoicesNull();
        	window.alert("Something went wrong, error : " + errResponse);
        });
    };
}
]);
