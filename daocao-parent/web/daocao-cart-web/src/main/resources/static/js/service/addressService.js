app.service('addressService',function($http){

    this.addAddress = function (address) {
        return $http.post("/address/addAddress",address);
    };

    this.getAddress = function () {
        return $http.get("/address/getAddress");
    }
});