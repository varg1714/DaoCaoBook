app.controller("indexController",function ($scope,loginService) {
    $scope.getName = function () {
        loginService.getUserName().success(function (response) {
            $scope.username = response;
        })
    }
});