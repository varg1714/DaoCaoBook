app.service("orderService",function ($http) {

    this.findOrders = function (status,pageNum) {
        return $http.get("/order/findOrders?status="+status+"&pageNum="+pageNum);
    };

    this.orderEvaluate = function (order) {
        return $http.post("/order/orderEvaluate",order);
    };

    this.orderReceive = function (id) {
        return $http.post("/order/orderReceive?id="+id);
    };

    this.getSellerOrders = function (status,pageNum) {
        return $http.get("/order/findSellerOrder?type="+status+"&pageNum="+pageNum);
    };

    this.orderSend= function (order) {
        return $http.post("/order/orderSend",order);
    };

});