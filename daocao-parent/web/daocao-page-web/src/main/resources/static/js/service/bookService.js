app.service('bookService',function($http){

    this.findGoods = function(id){
        return $http.get('../getGoods/'+id);
    };

    this.getCategoryList = function(parentId){
        return $http.get('../book/getCategory?parentId='+parentId);
    };

    this.getBookEval = function (id,pageNum) {
        return $http.get("/getBookEval?id="+id+"&pageNum="+pageNum);
    };

    this.getRecommendBooks = function () {
        return $http.get("/getRecommendBooks");
    };
    this.getUsername = function () {
        return $http.get("http://localhost:8082/user/getUserName",{'withCredentials': true});
    };
});