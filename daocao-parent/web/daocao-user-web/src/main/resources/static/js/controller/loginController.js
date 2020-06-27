 //控制层 
app.controller('loginController' ,function($scope,userService,loginService){

	$scope.loginEntity = {};



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
