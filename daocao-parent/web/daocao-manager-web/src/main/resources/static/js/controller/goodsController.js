 //控制层 
app.controller('goodsController' ,function($scope,$controller,goodsService,itemCatService, $location, typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(){
		let id = $location.search()['id'];
		if (id !== undefined) {
			goodsService.findOne(id).success(
				function (response) {
					$scope.entity = response;
					editor.html($scope.entity.goodsDesc.introduction);
					$scope.entity.goodsDesc.itemImages = JSON.parse($scope.entity.goodsDesc.itemImages);
					$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.entity.goodsDesc.customAttributeItems);
					$scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems);

					// 转换每一个商品的规格选项
					for(let i = 0; i < $scope.entity.itemList.length; i++){
						$scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec);
					}
				}
			);
		}
	};
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	};
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.seleIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	};
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};

	$scope.status = ['未申请', '审核中', '已审核', '已驳回'];

	$scope.itemCatList = [];
	$scope.findItemCatList = function () {
		itemCatService.findAll().success(function (response) {
			for (let data of response) {
				$scope.itemCatList[data.id] = data.name;
			}
		})
	};

	$scope.selectItemCat1List = function () {
		itemCatService.findByParentId(0).success(function (response) {
			$scope.cateList1 = response;
		})
	};

	$scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
		if (newValue !== undefined) {
			itemCatService.findByParentId(newValue).success(function (response) {
				$scope.cateList2 = response;
				$scope.cateList3 = {};
			})
		}


	});

	$scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {

		if (newValue !== undefined) {
			itemCatService.findByParentId(newValue).success(function (response) {
				$scope.cateList3 = response;
			})
		}

	});

	$scope.$watch('entity.goods.category3Id', function (newValue, oldValue) {
		if (newValue !== undefined) {
			itemCatService.findOne(newValue).success(function (response) {
				$scope.entity.goods.typeTemplateId = response.typeId;
			})
		}


	});

	$scope.$watch('entity.goods.typeTemplateId', function (newValue, oldValue) {

		if (newValue !== undefined) {
			typeTemplateService.findOne(newValue).success(function (response) {
				$scope.typeTemplate = response;
				$scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
				if ($location.search()['id'] === undefined) {
					$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
				}
			});

			typeTemplateService.findSpecList(newValue).success(function (response) {
				$scope.specList = response;
			});
		}

	});

	$scope.isChecked = function (attributeName, attributeValue) {
		let items = $scope.entity.goodsDesc.specificationItems;
		let objectByKey = $scope.searchListElement(items, "attributeName", attributeName);
		if (objectByKey == null) {
			return false;
		} else {
			for (let i = 0; i < objectByKey.attributeValue.length; i++) {
				if (objectByKey.attributeValue[i] === attributeValue) {
					return true;
				}
			}
			return false;
		}
	};

	$scope.updateStatus = function (status) {
		goodsService.updateStatus($scope.seleIds,status).success(function (response) {
			if(response.success){
				$scope.reloadList();
				$scope.selectIds = [];
			}else {
				alert(response.message);
			}
		})
	}
});	
