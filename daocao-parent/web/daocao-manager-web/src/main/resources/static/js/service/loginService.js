app.service("loginService",function ($http) {
    this.getUserName = function () {
        return $http.get("../loginControl/getUserName");
    }
})