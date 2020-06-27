app.service("searchService",function ($http) {
    this.search = function (map) {
        return $http.post("/search",map);
    };

    this.getUsername = function () {
        return $http.get("http://localhost:8082/user/getUserName");
    };

});