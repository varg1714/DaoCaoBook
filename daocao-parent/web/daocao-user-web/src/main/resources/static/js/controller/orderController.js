app.controller("orderController", function ($scope, orderService,userService) {

    $scope.orders = [];
    $scope.orderStatus = ["未付款", "待发货", "已发货", "待评价", "订单完成"];
    $scope.sellerOrderStatus = ["出售中", "待发货", "运输中", "待收货", "已完成", "已售罄"];
    $scope.orderEvaluateEntity = {};
    $scope.orderSendEntity = {};

    $scope.findOrders = function (status) {
        orderService.findOrders(status, $scope.pageNum).success(function (response) {
            if (response.status) {
                $scope.orders = response.entity.rows;
                for (let record of $scope.orders) {
                    for (let ordetItem of record.orderItems) {
                        ordetItem.goodsimage = JSON.parse(ordetItem.goodsimage);
                    }
                }
            } else {
                alert(response.message);
            }
            // console.log($scope.orders);
        });
        $scope.showName();
    };

    $scope.setStar = function (star) {
        $scope.orderEvaluateEntity.evltype = star;
    };

    $scope.orderEvaluate = function () {
        orderService.orderEvaluate($scope.orderEvaluateEntity).success(function (response) {
            alert(response.message);
            if (response.status) {
                $scope.orderEvaluateEntity = {};
                $scope.findOrders(3);
            }
        })
    };

    $scope.orderReceive = function (id) {
        orderService.orderReceive(id).success(function (response) {
            alert(response.message);
            if (response.status) {
                $scope.findOrders(2);
            }
        })
    };

    $scope.getSellerOrders = function (status) {
        orderService.getSellerOrders(status, $scope.pageNum).success(function (response) {
            if (response.status) {
                $scope.orders = response.entity.rows;
                for (let record of $scope.orders) {
                    for (let ordetItem of record.orderItems) {
                        ordetItem.goodsimage = JSON.parse(ordetItem.goodsimage);
                    }
                }

            } else {
                alert(response.message);
            }
            $scope.showName();
        })
    };

    $scope.orderSend = function () {
        orderService.orderSend($scope.orderSendEntity).success(function (response) {
            alert(response.message);
            if (response.status) {
                $scope.orderSendEntity = {};
                $scope.findOrders(1);
            }
        })
    }

    $scope.showName = function () {
        userService.showName().success(
            function (response) {
                $scope.loginName = response;
            }
        );
    };

});