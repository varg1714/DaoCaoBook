//服务层
app.service('userService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../user/findAll');
	};
	//分页
	this.findPage=function(page,rows){
		return $http.get('../user/findPage?page='+page+'&rows='+rows);
	};
	//查询实体
	this.findOne=function(id){
		return $http.get('../user/findOne?id='+id);
	};
	//增加
	this.add=function(entity,smsCode){
		return  $http.post('user/regist?smsCode='+smsCode,entity);
	};
	//修改
	this.update=function(entity,smsCode){
		return  $http.post('../user/updateBaseInfo?smsCode='+smsCode,entity );
	};
	//删除
	this.dele=function(ids){
		return $http.get('../user/delete?ids='+ids);
	};
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../user/search?page='+page+"&rows="+rows, searchEntity);
	};

	// 发送验证码
    this.createCode=function(tel){
        return  $http.get('user/getSmsCode?tel='+tel );
    };

	//读取列表数据绑定到表单中
	this.showName=function(){
		return $http.get('../user/getUserName');
	};

	this.getResetPwdCode = function () {
		return $http.get("/user/getResetPwdCode");
	};

	this.resetPassword = function (info) {
		return $http.post("/user/resetPassword",info);
	};

});
