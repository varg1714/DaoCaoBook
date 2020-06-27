app.controller("addressController", function ($scope, addressService, userService) {

    $scope.address = {};
    $scope.addressList = [];

    $scope.showName = function () {
        userService.showName().success(
            function (response) {
                $scope.loginName = response;
            }
        );
    };

    $scope.addAddress = function () {
        if ($scope.checkAddress()) {
            alert("请填写完整的地址信息");
            return;
        }
        addressService.addAddress($scope.address).success(function (response) {
            alert(response.message);
            if (response.status) {
                $scope.address = {};
                $scope.getAddress();
            }
        })
    };

    $scope.getAddress = function () {
        addressService.getAddress().success(function (response) {
            if (response.status) {
                $scope.addressList = response.entity;
            } else {
                alert(response.message);
            }
        })
    };

    $scope.setDefaultAddress = function(id){
        addressService.setDefaultAddress(id).success(function (response) {
            alert(response.message);
            if(response.status){
                $scope.getAddress();
            }
        })
    };

    $scope.checkAddress = function () {
        let address = $scope.address;
        return address.contact === undefined || address.contact === null || address.contact === ""
            || address.province === undefined || address.province === null || address.province === ""
            || address.city === undefined || address.city === null || address.city === ""
            || address.town === undefined || address.town === null || address.town === ""
            || address.address === undefined || address.address === null || address.address === ""
            || address.phone === undefined || address.phone === null || address.phone === ""
    }

    $scope.init = function () {
        $scope.showName();
        $scope.getAddress();
    }
});