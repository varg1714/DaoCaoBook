app.controller('userController', function ($scope, userService) {

    $scope.entity = {'sex':'1'};
    $scope.smsCode = "";

    $scope.showName = function () {
        userService.showName().success(
            function (response) {
                $scope.loginName = response;
            }
        );
    };

    $scope.updateInfo = function () {
        userService.update($scope.entity,$scope.smsCode).success(function (response) {
            alert(response.message);
            if(response.success){
                $scope.entity = {'sex':'1'};
            }
        })
    }

});