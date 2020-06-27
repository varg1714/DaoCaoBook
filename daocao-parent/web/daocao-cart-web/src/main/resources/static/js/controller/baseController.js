app.controller("baseController",function ($scope) {
    $scope.reloadList=function(){
        //切换页码
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };

    $scope.selectIds = [];
    $scope.seleId = function ($event,id,num) {
        if($event.target.checked){
            $scope.selectIds.push({'id':id,'num':num});
        }else{
            let index = $scope.searchListIndex($scope.selectIds,'id',id);
            $scope.selectIds.splice(index,1);
        }
    };

    $scope.jsonToString = function (jsonString,key) {
        let value = "";
        let parse = JSON.parse(jsonString);

        if(parse.length > 0){
            value += parse[0][key];
            for(let i = 1; i < parse.length; i++){
                value += ","+parse[i][key];
            }
        }
        return value;
    };
    $scope.searchListElement = function (list,key,value) {
        for(let i = 0; i < list.length; i++){
            if(list[i][key] === value){
                return list[i];
            }
        }
        return null;
    };

    $scope.searchListIndex = function (list,key,value) {
        for(let i = 0; i < list.length; i++){
            if(list[i][key] === value){
                return i;
            }
        }
        return -1;
    };
});