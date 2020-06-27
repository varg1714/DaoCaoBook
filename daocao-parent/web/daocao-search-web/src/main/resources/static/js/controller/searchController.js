app.controller("searchController", function ($scope, $location,searchService) {

    $scope.searchMap = {'keywords': '',  'category': '', 'page':1,'pageSize':10, 'sortRule':"",'sortField':""};
    $scope.keywords = "";

    $scope.addSortField = function (sortRule,sortField) {
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sortRule = sortRule;
        $scope.search();
    };

    $scope.loadKeyWords = function () {
        let search = $location.search()['keywords'];
        if(search != null){
            $scope.searchMap.keywords = search;
            $scope.searchBooks();
        }
    };

    $scope.searchBooks = function () {
        searchService.search($scope.searchMap).success(function (response) {
            $scope.books = response.rows;
            $scope.keywords = $scope.searchMap.keywords;
            $scope.total = response.total;
            $scope.totalPage = response.totalPage;
            for(let i = 0; i < $scope.books.length; i++){
                $scope.books[i].bookimages = JSON.parse($scope.books[i].bookimages);
            }
        })
    };

    $scope.setPage = function (x) {
        if(($scope.searchMap.page + x) > 0 && ($scope.searchMap.page + x <= $scope.totalPage)){
            $scope.searchMap.page = $scope.searchMap.page + x;
            $scope.searchBooks();
        }
    }

});