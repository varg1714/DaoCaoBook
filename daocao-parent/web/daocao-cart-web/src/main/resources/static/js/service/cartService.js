app.service('cartService',function($http){
    //购物车列表
    this.findCartList=function(){
        return $http.get('/cart/getCartGoods');
    };

    this.addGoodsToCartList=function(bookId,num){
        return $http.get('cart/addGoods?bookId='+bookId+'&num='+num);
    };
    
    this.sum = function (cartList) {
        let totalGoods = {totalNum:0,totalMoney:0.0};
        for (let cart of cartList) {
            totalGoods.totalNum += cart.totalNum;
            totalGoods.totalMoney += cart.totalMoney;
        }
        return totalGoods;
    };

    this.findAddressList=function(){
        return $http.get('address/findById');
    };

    this.saveOrder = function (order) {
        return $http.post("/cart/saveOrder",order);
    };

    this.addOrderList = function (orderList) {
        return $http.post("/cart/addOrderList",orderList);
    };

    this.getOrderList = function () {
        return $http.get("/cart/getOrderList");
    };

    this.deleteOrderList = function () {
        return $http.delete("/cart/deleteOrderList");
    }

});