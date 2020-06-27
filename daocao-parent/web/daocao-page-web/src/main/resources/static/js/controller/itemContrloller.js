app.controller("itemController", function ($scope, $http, $location, bookService) {

    $scope.num = 1;
    $scope.entity = {};
    $scope.date = "";

    $scope.testBook = {
        "id": 2,
        "name": "本草纲目",
        "publishdate": "2002-07",
        "author": "李时珍",
        "publisher": "中古古文研究所",
        "version": "4",
        "isbn": "16513213",
        "publishprice": 199.00,
        "sellprice": 45.00,
        "bindtype": "精装",
        "papersize": "A4",
        "pagenumber": 145,
        "booktype1": 3,
        "booktype2": 6,
        "seller": "123456",
        "issell": "1",
        "number": 76,
        "bookimages": ["http://192.168.25.133/group1/M00/00/00/wKgZhV61hL2AKdAkAADSZMlr4ak714.jpg"],
        "description": "中草药各种属性介绍。非常详细的一本书籍，是中国史上浓墨重彩的一笔。",
        "postage": 10.00,
        "isaudit": "1"
    };
    $scope.evals = [];

    $scope.increGoods = function (x) {
        if (($scope.num + x) >= 1 && ($scope.num + x <= $scope.entity.number)) {
            $scope.num += x;
        }
    };

    $scope.addToCart = function (order,id) {
        // alert("商品id:" + id + "价格：" + $scope.entity.sellprice + "数量：" + $scope.num);
        $http.get("http://localhost:8085/cart/addGoods?bookId=" + id + "&num=" + $scope.num,
            {'withCredentials': true}).success(function (response) {
            alert(response.message);
            if (order && response.success) {
                location.href = "http://localhost:8085/cart.html";
            }
        })
    };

    $scope.init = function (bookId) {
        let id = $location.search()['id'];
        if (id !== undefined) {
            // 获取书籍信息
            bookService.findGoods(id).success(function (response) {
                $scope.entity = response;
                if ($scope.entity.bookimages !== undefined) {
                    $scope.entity.bookimages = JSON.parse($scope.entity.bookimages);
                } else {
                    $scope.entity.bookimages = [];
                }
                if ($scope.entity.publishdate !== undefined) {
                    let date = new Date($scope.entity.publishdate);

                    $scope.date = date.getFullYear() + "-" + date.getMonth();
                }
            });

            // 获取该书籍相关评价
            bookService.getBookEval(id, 1).success(function (response) {
                if (response.status) {
                    $scope.evals = response.entity;
                } else {
                    alert(response.message);
                }
            })
        }else if(bookId !== undefined){
            // 获取该书籍相关评价
            bookService.getBookEval(bookId, 1).success(function (response) {
                if (response.status) {
                    $scope.evals = response.entity;
                } else {
                    alert(response.message);
                }
            })
        }
    };

    $scope.getNumber = function (num) {
        if (num > 0) {
            let array = [];
            for (let i = 0; i < num; i++) {
                array.push(i);
            }
            return array;
        }
        return null;
    }
});