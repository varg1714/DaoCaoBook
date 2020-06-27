//控制层
app.controller('categoryController', function ($scope, $controller, categoryService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        categoryService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    };

    //分页
    $scope.findPage = function (page, rows) {
        categoryService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        categoryService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = categoryService.update($scope.entity); //修改
        } else {
            serviceObject = categoryService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    $scope.entity = {'parentid':$scope.pageLevel};
                    //重新查询
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    };

    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        categoryService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    };

    // 定义搜索对象
	$scope.searchEntity = {parentid:0};

    // 定义该分类父类对象
	$scope.parentEntity = {};
	// 获取需修改的分类，并设置其父分类属性
	$scope.getUpdateEntity= function (id,parentid) {
		$scope.entity = $scope.findOne(id);
		$scope.parentEntity = {'id':parentid};
	};

    $scope.setAddEntity= function (entity) {
        $scope.entity = entity;
    };

	$scope.$watch('entity.parentid',function (newValue) {
		if(newValue !== undefined && newValue !== '' && newValue !== 0){
			categoryService.findOne(newValue).success(function (response) {
				$scope.parentEntity = response;
			})
		}else if(newValue === 0 || newValue === "0"){
            $scope.parentEntity = {'id':0};
        }
	});

    //搜索
    $scope.search = function (page, rows) {
        categoryService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    $scope.findByParentId = function (parentId) {
        categoryService.findByParentId(parentId).success(function (response) {
            $scope.list = response;
        })
    };

    $scope.pageLevel = 0;

    $scope.setLevel = function (level) {
        $scope.pageLevel = level;
    };

    $scope.selectList = function (p_entity) {

        if ($scope.pageLevel === 0) {
            $scope.entity1 = null;
			$scope.searchEntity = {'parentid':0};
		} else {
            $scope.entity1 = p_entity;
            $scope.searchEntity = {'parentid':p_entity.id};
		}

        $scope.reloadList();
    };

});
