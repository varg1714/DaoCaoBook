 //控制层 
app.controller('loginController' ,function($scope,userService,loginService){

	$scope.entity = {'sex':1};
	$scope.loginEntity = {};

	$scope.regist = function () {

		if($scope.entity.password !== $scope.retPassword){
			alert("两次输入密码不一致，请检查后重新输入!");
			return;
		}

		userService.add($scope.entity,$scope.smsCode).success(function (response) {
			alert(response.message);
			if(response.success){
				$scope.entity = {'sex':1};
				$scope.retPassword = '';
				location.href = "/login.html";
			}
		})
	};

	$scope.createCode = function () {
		userService.createCode($scope.entity.phone).success(function (response) {
			alert(response.message);
		})
	};

	$scope.setSex = function (sex) {
		$scope.entity.sex = sex;
	};
	$scope.login = function () {
		let loginEntity = $scope.loginEntity;
		if(loginEntity.username === undefined || loginEntity.username ===""
			|| loginEntity.password === undefined || loginEntity.password === ""){
			alert("请输入账号和密码!")
		}else {
			loginService.login(loginEntity);
		}
	};

});	
