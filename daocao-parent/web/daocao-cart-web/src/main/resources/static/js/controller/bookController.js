app.controller('bookController', function ($scope, bookService) {

    $scope.init = function(){

        $scope.entity = {'booktype1':'1'};
        $scope.imageEntity = "";
        $scope.bookImages = [];
        $scope.categoryList1 = [];
        $scope.categoryList2 = [];
        $scope.year = "";
        $scope.month = "";

        bookService.getCategoryList(0).success(function (response) {
            // alert("category:"+response);
            if(response.length > 0){
                $scope.categoryList1 = response;
            }
        })
    };

    $scope.getCategoryList2 = function (parentId) {
        $scope.entity.booktype1 = parentId;
        $scope.entity.booktype2 = "";
        $scope.categoryList2 = [];
        bookService.getCategoryList(parentId).success(function (response) {
                $scope.categoryList2 = response;
        });

    };

    $scope.addGoods = function () {
        if($scope.checkEntity()){
            // alert($scope.bookImages.length);
            if($scope.bookImages.length > 0){
                $scope.entity.bookimages = JSON.stringify($scope.bookImages);
                alert($scope.entity.bookimages);
            }
            $scope.setPublishDate();
            bookService.addGoods($scope.entity).success(function (response) {
                alert(response.message);
                $scope.init();
            })
        }else {
            alert("请注意所有必填信息都是要填写的哦!");
        }
    };

    $scope.uploadFile=function(){
        bookService.uploadFile().success(function(response) {
            if(response.success){
                $scope.imageEntity = response.message;
            }else{
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });
    };

    $scope.addBookImages = function(){
        $scope.bookImages.push($scope.imageEntity);
    };

    $scope.removeBookImages = function(index){
        $scope.bookImages.splice(index,1);
    };

    $scope.test = function () {
        // let parse = ["http://192.168.25.133/group1/M00/00/00/wKgZhV6UtSyAL8uRAAGDgg1ofAM623.jpg","http://192.168.25.133/group1/M00/00/00/wKgZhV6UtTqAALuxAAL6vMZcGkA812.jpg"];
       // alert(parse[0]);
       //  let fromJson = angular.fromJson(parse);
        // alert(fromJson[0]);
        // let json = angular.toJson(parse);
        // alert(json);
        // alert("year==null?"+($scope.year ==='' || $scope.year === undefined));
        // alert("month==null?"+($scope.month ==='' || $scope.month === undefined));
        // alert("year==0?"+($scope.year ==='0'));
        // alert("month==0?"+($scope.month ==='0'));
        // alert($scope.checkEntity());
    };

    $scope.setPublishDate = function () {
        if(!($scope.year ==='' || $scope.year === undefined)){
            $scope.entity.publishdate = $scope.year;
            if(!($scope.month ==='' || $scope.month === undefined)){
                if($scope.month < 10){
                    $scope.entity.publishdate = $scope.year+"-0"+$scope.month+"-10";
                }else{
                    $scope.entity.publishdate = $scope.year+"-0"+$scope.month+"-10";
                }
            }
        }
    };

    $scope.checkEntity = function () {
        let entity = $scope.entity;
        return entity.name !== undefined && entity.name !== ""
                && entity.booktype1 !== undefined && entity.booktype1 !== ""
                && entity.author !== undefined && entity.author !== ""
                && entity.number !== undefined && entity.number > 0
                && entity.sellprice !== undefined && entity.sellprice > 0
                && entity.postage !== undefined && entity.postage >= 0
                && entity.description !== undefined && entity.description !== "";
    }

});