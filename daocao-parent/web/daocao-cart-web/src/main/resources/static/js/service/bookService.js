app.service('bookService',function($http){

    //读取列表数据绑定到表单中
    this.findAll=function(){
        return $http.get('../user/findAll');
    };

    this.addGoods = function(book){
        return $http.post('../book/add',book);
    };

    this.uploadFile=function(){
        var formData=new FormData();
        formData.append("file",file.files[0]);
        return $http({
            method:'POST',
            url:"../upload",
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });
    };

    this.getCategoryList = function(parentId){
        return $http.get('../book/getCategory?parentId='+parentId);
    };

});