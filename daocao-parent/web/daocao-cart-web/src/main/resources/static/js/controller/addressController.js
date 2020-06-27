app.controller("addressController",function ($scope,addressService) {

    $scope.address = {};

    $scope.addAddress = function () {
        addressService.addAddress($scope.address).success(function (response) {
            alert(response.message);
        })
    };

    $scope.test = function () {
        let address = $scope.address;
        console.log("contact == undefined: "+(address.contact === undefined));
        console.log("contact == null: "+(address.contact === null));
        console.log("contact == '': "+(address.contact === ""));
        console.log("contact == province:"+(address.province === undefined));
        console.log("contact == province:"+(address.province === undefined));
        console.log("contact == province:"+(address.province === undefined));
    }

});