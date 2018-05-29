function doSuccess(response, $scope) {

    console.log("Positive response");
    response = response.data;
    console.log(response);

    if (!isEmpty(response)) {
        $scope.compile.exitValue = response.compile.exitValue;

        if ($scope.compile.exitValue === 0) {
            $scope.compile.output = "Complied Fine";
        } else {
            $scope.compile.output = response.compile.output;
        }

        $scope.execute.exitValue = response.execution.exitValue;
        $scope.execute.output = response.execution.output;
    }
    $scope.isDisabled = false;

}

function doFailiure(errResponse, $scope) {
    console.log("Negative response");
    $scope.error = errResponse.data.errorMessage;
    console.log($scope.error);
    $scope.isDisabled = false;
}

function isEmpty(obj) {
    for (var prop in obj) {
        if (obj.hasOwnProperty(prop))
            return false;
    }
    return JSON.stringify(obj) === JSON.stringify({});
}
