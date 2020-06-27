app.controller('userController', function ($scope,$controller, userService) {

    // 引入order控制器
    $controller('orderController', {$scope: $scope});
    $scope.entity = {'sex':'1'};
    $scope.smsCode = "";
    $scope.message = "点击获取短信验证码";
    $scope.pageNum = 1;
    $scope.pwdInfo = {};
    $scope.repeatPwd = "";

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
    };

    $scope.sendCode = function () {
        let tel = $scope.entity.tel;
        if(tel === undefined || tel === null || tel === ""){
            alert("请输入手机号");
        }else {
            userService.createCode(tel).success(function (response) {
                if(response.status){
                    $scope.message = response.message;
                }else {
                    alert(response.message);
                }
            })
        }
    };

    $scope.regist = function () {

        if($scope.entity.password !== $scope.retPassword){
            alert("两次输入密码不一致，请检查后重新输入!");
            return;
        }else if($scope.smsCode === undefined || $scope.smsCode === null || $scope.smsCode === ""){
            alert("请输入手机验证码");
            return;
        }

        userService.add($scope.entity,$scope.smsCode).success(function (response) {
            alert(response.message);
            if(response.status){
                $scope.entity = {'sex':1};
                $scope.retPassword = '';
                location.href = "/user/home-index.html";
            }
        })
    };

    $scope.setSex = function (sex) {
        $scope.entity.sex = sex;
    };

    $scope.getResetPwdCode = function () {
        userService.getResetPwdCode().success(function (response) {
            if(response.status){
                $scope.message = response.message;
            }else {
                alert(response.message);
            }
        })
    };

    $scope.resetPassword = function () {
        if($scope.checkInfo()){
            userService.resetPassword($scope.pwdInfo).success(function (response) {
                alert(response.message);
                if(response.status){
                    $scope.pwdInfo = {};
                }
            })
        }else {
            alert("验证失败,可能是：密码填写不一致,密码为空,验证码为空?");
        }
    };

    $scope.checkInfo = function () {
        return $scope.pwdInfo.password !== undefined && $scope.pwdInfo.password !== ""
                && $scope.pwdInfo.checkCode !== undefined && $scope.pwdInfo.checkCode !== ""
                && $scope.pwdInfo.password === $scope.repeatPwd;
    }

});