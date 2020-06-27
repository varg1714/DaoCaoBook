app.service('loginService',function($http){

    this.login = function (user) {
        return $http.post("/login.html",user);
    }
});