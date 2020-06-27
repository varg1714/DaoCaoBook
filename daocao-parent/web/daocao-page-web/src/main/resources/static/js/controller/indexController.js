app.controller("indexController",function ($scope,bookService) {

    $scope.keywords = "";
    $scope.username = "";

    $scope.getRecommendBooks = function () {
        bookService.getRecommendBooks().success(function (response) {
            if(response.status){
                $scope.books = response.entity.rows;
                for(let book of $scope.books){
                    if(book.bookimages !== undefined){
                        book.bookimages = JSON.parse(book.bookimages);
                    }else {
                        book.bookimages = [];
                    }
                }
            }
        })
    };

    $scope.searchBook = function () {
        if($scope.keywords !== ""){
            location.href = "http://localhost:8084/booksearch.html#?keywords="+$scope.keywords;
        }
    }

    $scope.getUsername = function () {
        bookService.getUsername().success(function (response) {
            $scope.username = response;
        })
    }
});