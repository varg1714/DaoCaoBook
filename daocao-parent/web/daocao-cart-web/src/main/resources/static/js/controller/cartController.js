app.controller('cartController', function ($scope,$controller, cartService, addressService) {

    $controller('baseController', {$scope: $scope});//继承

    $scope.cartList = [];
    $scope.simpleAddress = {};

    //查询购物车列表
    $scope.findCartList = function () {
        cartService.findCartList().success(
            function (response) {
                $scope.cartList = response;
                for (let cart of $scope.cartList) {
                    for (let orderItem of cart.orderItems) {
                        orderItem.goodsimage = JSON.parse(orderItem.goodsimage);
                        // alert(orderItem.goodsimage);
                    }
                }
                $scope.totalGoods = cartService.sum($scope.cartList);
            }
        );
    };

    $scope.findOrderList = function () {
        cartService.getOrderList().success(
            function (response) {
                $scope.orderList = response;
                for (let cart of $scope.orderList) {
                    for (let orderItem of cart.orderItems) {
                        orderItem.goodsimage = JSON.parse(orderItem.goodsimage);
                    }
                }
                $scope.totalGoods = cartService.sum($scope.orderList);
            }
        );
    };

    $scope.findAddressList = function () {
        addressService.getAddress().success(
            function (response) {
                $scope.addressList = response;
                for (let address of $scope.addressList) {
                    if (address.isDefault === '1') {
                        $scope.defaultAddress = address;
                        break;
                    }
                }
            }
        );
    };

    $scope.selectAddress = function (address) {
        $scope.defaultAddress = address;
    };

    $scope.isSelected = function (address) {
        return $scope.defaultAddress === address;
    };

    // 订单类型
    $scope.orderItem = {paymentType: '1'};

    $scope.selePayType = function (paymentType) {
        $scope.orderItem.paymentType = paymentType;
    };

    $scope.order = function () {
        let address = $scope.defaultAddress;
        if(address === undefined || address === null){
            alert("请选择收货地址!");
        }
        let simpleAddress = $scope.simpleAddress;
        simpleAddress.tel = address.phone;
        simpleAddress.contact = address.contact;
        simpleAddress.address = address.province+address.city+address.town+address.address;
        cartService.saveOrder(simpleAddress).success(function (response) {
            if(response.success){
                alert(response.message);
                location.href = "paysuccess.html";
            }else {
                alert("购买失败，请稍后重试");
            }
        });
    };

    $scope.address = {};

    $scope.addAddress = function () {
        if ($scope.addressCheck()) {
            addressService.addAddress($scope.address).success(function (response) {
                // 获取新的地址
                $scope.findAddressList();
                alert(response.message);
            })
        } else {
            alert("请将地址信息填写完整哦!");
        }

    };

    /**
     * 判断地址提交表单状态合法性
     * @returns {boolean}
     */
    $scope.addressCheck = function () {
        let address = $scope.address;
        return !((address.contact === undefined) || (address.contact === null) || (address.contact === "")
            || (address.province === undefined) || (address.province === null) || (address.province === "")
            || (address.city === undefined) || (address.city === null) || (address.city === "")
            || (address.town === undefined) || (address.town === null) || (address.town === "")
            || (address.address === undefined) || (address.address === null) || (address.address === "")
            || (address.phone === undefined) || (address.phone === null) || (address.phone === ""));
    }

    /**
     * 增加或减少该商家商品数量
     * @param id
     * @param num
     */
    $scope.increGoods = function (id,num) {
        cartService.addGoodsToCartList(id,num).success(function (response) {
            if(response.success){
                $scope.findCartList();
            }else {
                alert(response.message);
            }
        });
        // 修改选中的购物车信息
        // let index = $scope.searchListIndex($scope.selectIds,'id',id);
        // $scope.selectIds.splice(index,1);
        // $scope.selectIds.push({'id':id,'num':num});
        let temp = $scope.searchListElement($scope.selectIds,'id',id);
        temp.num = temp.num + num;
    };

    /**
     * 将该商家全部商品添加到结算订单或移除
     * @param $event
     * @param seller
     */
    $scope.addSellerList = function ($event,seller) {
        if($event.target.checked){
            for (let item of seller){
                let index = $scope.searchListIndex($scope.selectIds,'id',item.goodsid);
                if(index === -1){
                    $scope.selectIds.push({'id':item.goodsid,'num':item.goodscount});
                }
            }
        }else {
            for (let item of seller){
                let index = $scope.searchListIndex($scope.selectIds,'id',item.goodsid);
                // console.log("item: "+index);
                $scope.selectIds.splice(index,1);
                // $scope.selectIds.push({'id':item.goodsid,'num':item.goodscount});
            }
        }
        // $scope.isChecked();
    };

    /**
     * 判断某件商品是否被选中
     * @param goodsId 需判断的商品ID
     * @returns {boolean} 选中状态
     */
    $scope.isChecked = function (goodsId) {
        let indexOf = $scope.searchListIndex($scope.selectIds,'id',goodsId);
        // console.log("index:"+indexOf+" goodsid:"+goodsId);
        if(indexOf !== -1){
            return true;
        }
        return false;
    };

    /**
     * 判断某个商家的全部商品是否被选中
     * @param seller 需判断的商家商品列表
     * @returns {boolean} 选中状态
     */
    $scope.isSellerChecked = function (seller) {

        for (let item of seller){
            let index = $scope.searchListIndex($scope.selectIds,'id',item.goodsid);
            if(index === -1){
                return false;
            }
        }

        return true;
    }

    /**
     * 将购物车列表中所有商家的全部商品添加到结算订单或移除
     * @param $event 监听器
     * @param cartList 购物车列表
     */
    $scope.addCartList = function ($event) {
        // 选中购物车全部商品
        if($event.target.checked){
            for (let cart of $scope.cartList){
                for(let item of cart.orderItems){
                    let index = $scope.searchListIndex($scope.selectIds,'id',item.goodsid);
                    if(index === -1){
                        $scope.selectIds.push({'id':item.goodsid,'num':item.goodscount});
                    }
                }
            }
        }else {
            $scope.selectIds = [];
        }
    };

    /**
     * 判断购物车所有商品是否全部被选中
     * @param cartList 购物车列表
     * @returns {boolean} 选中状态
     */
    $scope.isCartListChecked = function () {

        for (let cart of $scope.cartList){
            for(let item of cart.orderItems){
                let index = $scope.searchListIndex($scope.selectIds,'id',item.goodsid);
                if(index === -1){
                    return false
                }
            }
        }

        return true;
    };

    $scope.submitOrder = function () {
        if($scope.selectIds.length === 0){
            alert("请先勾选需要购买的商品哦!");
        }else {
            cartService.addOrderList($scope.selectIds).success(function (response) {
                if(response.success){
                    location.href = "getOrderInfo.html";
                }else {
                    alert(response.message);
                }
            })
        }
    };

    $scope.deleteOrderList = function () {
        cartService.deleteOrderList().success(function (response) {
            if(response.success){
                alert("取消成功，您可以到购物车页面重新进行下单了!");
                $scope.findOrderList();
            }else {
                alert("取消失败，稍后再试试看吧!");
            }
        })
    }

});