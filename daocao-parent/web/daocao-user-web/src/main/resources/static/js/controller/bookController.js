app.controller('bookController', function ($scope, $location,bookService, userService) {

    $scope.books = [];
    $scope.pageNum = 1;
    $scope.entity={number:0};
    $scope.cateName = "";
    $scope.submited = false;

    $scope.init = function () {

        bookService.getCategoryList(0).success(function (response) {
            // alert("category:"+response);
            if (response.length > 0) {
                $scope.categoryList1 = response;
            }
        });

        let id =  $location.search()['id'];
        // 判断是修改书籍信息还是新上架书籍
        if(id === undefined){
            // 新上架书籍
            $scope.imageEntity = "";
            $scope.bookImages = [];
            $scope.categoryList2 = [];
            $scope.year = "";
            $scope.month = "";
            $scope.entity={number:0};
        }else {
            bookService.findOne(id).success(function (response) {
                $scope.entity = response;
                // 修改书籍信息
                //($scope.entity.bookimages);
                if($scope.entity !== undefined && $scope.entity.bookimages !== undefined){
                    if($scope.entity.bookimages !== ""){
                        $scope.entity.bookimages = JSON.parse($scope.entity.bookimages);
                        $scope.bookImages = $scope.entity.bookimages;
                    }else {
                        $scope.bookImages = [];
                    }
                    // console.log("图片:");
                    for(let cate of $scope.categoryList1){
                        if(cate.id === $scope.entity.booktype1){
                            $scope.cateName = cate.name;
                            break;
                        }
                    }
                    let date = new Date($scope.entity.publishdate);
                    $scope.year = date.getFullYear();
                    $scope.month = date.getMonth();
                    $scope.getCategoryList2($scope.entity.booktype1);
                }
            })
        }
    };

    $scope.showName = function () {
        userService.showName().success(
            function (response) {
                $scope.loginName = response;
            }
        );
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
        if(!$scope.submited){
            if ($scope.checkEntity()) {
                // 防止重复提交请求
                $scope.submited = true;

                if ($scope.bookImages !== undefined && $scope.bookImages.length > 0) {
                    $scope.entity.bookimages = JSON.stringify($scope.bookImages);
                }else {
                    $scope.entity.bookimages = "";
                }
                $scope.setPublishDate();

                if($scope.entity.id === undefined){
                    // 新增商品
                    // alert($scope.bookImages.length);

                    bookService.addGoods($scope.entity).success(function (response) {
                        alert(response.message);
                        if(response.success){
                            $scope.init();
                        }
                        $scope.submited = false;
                    })
                }else {
                    // 修改商品
                    bookService.updateBaseInfo($scope.entity).success(function (response) {
                        alert(response.message);
                        $scope.submited = false;
                    })
                }

            } else {
                alert("请注意所有必填信息都是要填写的哦!");
            }
        }else {
            alert("请勿重复提交请求哦");
        }

    };

    $scope.uploadFile = function () {
        bookService.uploadFile().success(function (response) {
            if (response.success) {
                $scope.imageEntity = response.message;
            } else {
                alert(response.message);
            }
        }).error(function () {
            alert("上传发生错误");
        });
    };

    $scope.addBookImages = function () {
        if($scope.imageEntity !== ""){
            $scope.bookImages.push($scope.imageEntity);
        }
    };

    $scope.removeBookImages = function (index) {
        $scope.bookImages.splice(index, 1);
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
        if (!($scope.year === '' || $scope.year === undefined)) {
            $scope.entity.publishdate = $scope.year;
            if (!($scope.month === '' || $scope.month === undefined)) {
                if ($scope.month < 10) {
                    $scope.entity.publishdate = $scope.year + "-0" + $scope.month + "-10";
                } else {
                    $scope.entity.publishdate = $scope.year + "-" + $scope.month + "-10";
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
    };

    $scope.getSellingBook = function (isSellOut) {
        bookService.getSellingBook(isSellOut, $scope.pageNum).success(function (response) {
            if (response.status) {
                $scope.books = response.entity.rows;
                $scope.pageCount = response.entity.totalPage;
                for (let book of $scope.books) {
                    if(book.bookimages !== undefined && book.bookimages !== ""){
                        book.bookimages = JSON.parse(book.bookimages);
                    }
                }
            };
            $scope.showName();
        })
    };

    /**
     * 分页查询
     * @param page
     * @param type 对页码是增加还是赋值 true 增加 false 赋值
     */
    $scope.search = function (page, type) {
        // 在现有页码上增加新页码
        if (type) {
            if (page + $scope.pageNum > 0 && page + $scope.pageNum <= $scope.pageCount) {
                $scope.pageNum = page + $scope.pageNum;
                $scope.getSellingBook(false);
            }
        } else {
            // 设置新的页码
            if (page > 0 && page <= $scope.pageCount) {
                $scope.pageNum = page;
                $scope.getSellingBook(false);
            }
        }

    };

    $scope.setBookId = function(id){
        $scope.bookId = id;
    };

    $scope.refillBook = function (isSellOut) {
        bookService.refillBook($scope.entity.id,$scope.entity.number).success(function (response) {
            alert(response.message);
            if(response.status){
                $scope.getSellingBook(isSellOut);
            }
        })
    };

});