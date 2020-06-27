// JavaScript order  add fengwei by 2014-04-08
(function($){
	
	/**
	 * 订单对象
	 */
	function  order(){
		//this.init(config);
		this.tile = "订单";
		this.height = "";
		this.width = "";
	}

	/**  static **/
	order.maskBgId = "order_box_mask_id";//用于订单弹框内容Id，插入数据使用。
	order.divId = "order_box_main_id";//用于订单弹框Id;
	order.divConId = "order_box_content_id";// 用于订单弹框内容Id，插入数据使用。
	order.ajaxTime = 10000;//用于订单弹框内容的时间，10s
	order.pathIndex = location.pathname;
	order.path = order.pathIndex.substr(0, order.pathIndex.lastIndexOf("/")+1);
	order.testParam =location.search.split("?")[1].replace(/ca\=/,"");//测试时使用的
	order.count = 10;//用于订单弹框内容的时间，10s
	order.prototype = {
		
		/**
		 *
		 */
		init : function(){},
		/**
		 * 重新设置配置文件
		 */
		setConfig : function(Config){
			for(var i in Config){
				this[i] = Config[i];
			}
		},
		/**
		 * 用于订单的弹框处理
		 * 第一次模板展示
		 */
		box : function (bid, cid){
			
			
			//if(USER_IS_LOGIN==0) return this.goLogin();
			cid = cid|| 0;
			if(!bid)return alert("图书ID不能为空");
			
			
			var html = '<div class="mask" id="'+order.maskBgId+'"></div>'+
					   '<div id="'+order.divId+'" class="maskCon buyMask">'+
							'<div class="headBg">'+
								'<div class="maskHead cf">'+
									'<h3 class="fl">'+this.tile+'</h3>'+
									'<span class="close fr" onclick="orderObject.close();"></span>'+
								'</div>'+
							'</div>'+
							'<div id="'+order.divConId+'"  ></div>'+
						'</div>';
			
			$(document.body).prepend(html);
			this.boxAjax(this.getUrl({"ca":"Order_Book.Index", "bid":bid, "cid":cid, "url":encodeURIComponent(location.href)}));
			
		},
		
		/**
		 *  其余订单走ajax获取模板
		 *  @param String url 请求数据的URL
		 *  @param String divId 插入数据的Id
		 */
		boxAjax : function (url, divId){
			
			divId = divId || order.divConId;
		
			$.ajax({
				type : "get",
				url  : url,
				cache:false,
				beforeSend: function(){
					$("#"+divId).html("<div><img src='"+order.path+"/static/images/loading.gif' style='width:32px;height:32px' ></div>");
				},
				success: function(data){
					$("#"+divId).html(data)
				}
			})
		},
		
		/**
		 * 提示
		 * @param object conf
		 * @param string title 提示语	
		 * @param string btnName 按钮名称 
		 * @param string callback 点击按钮的回调函数
		 * @param array callbackParam 点击按钮的回调函数 参数
		 * @param string cancelName 取消按钮名称 
		 * @param string cancelcallback 点击取消按钮的回调函数 
		 */
		tip : function(conf){
			var title = conf.title || "";
			var btnName = conf.btnName || "确定";
			var callback = conf.callback || "order.close";
			var callbackParam = conf.callbackParam || [];
			
			var cancelName = conf.cancelName || "取消";
			var cancelcallback = conf.cancelcallback ||  "";
			
			
			
			
			var html = '<div class="buySection buyMaskCon leapBuy">'+
							'<p>'+title+'</p>'+
							'<div class="ensureBuy cf">'+
								'<div class="fl btn">'+
									'<span class="btnChild" id="order_box_tip_btn">'+btnName+'</span>'+
								'</div>'+
                				'<div class="fr btn1">'+
									'<span class="btnChild"  id="order_box_tip_cancel">'+cancelName+'</span>'+
								'</div>'+
							'</div>'+
						'</div>';
			$("#"+order.divConId).html(html)
			
			var o = this;
			$("#order_box_tip_btn").click(function(){
					callback ? callback.apply(this, callbackParam) : o.close();
			});
			
			$("#order_box_tip_cancel").click(function(){
				cancelcallback ?  cancelcallback() : o.close();
			});
			
		},
		
		/**
		 * 去批量购买的页面
		 */
		goBatch : function(obj){
			var url = $(obj).data("url");
			this.boxAjax(url);
		},
		/**
		 * 关闭弹框
		 */
		close : function(){
			$("#"+order.divId).remove();
			$("#"+order.maskBgId).remove();
		},
		
		/**
		 * 购买全本
		 */
		buy : function(obj){
			var O = this;
			var url  = $(obj).data("url");
			$.ajax({
				url  : url,
				type : "get",
				cache:false,
				dataType:"json",
				success: function(msg){
					if(msg.status==1){
						O.tip({
							"title" : msg.message, 
							"btnName" : "下载客户端",
							"callback" : O.goUrl,
							"callbackParam" : [order.pathIndex+"?ca=Client.Index"], 
							"cancelName" : "关闭"
						})
					}else{
						O.tip({
							"title" : msg.message, 
							"btnName" : "确定",
							"callback" : O.close,
							"cancelName" : "关闭"
						})
					}
				}
			})
		},
		
		/**
		 * 批量购买
		 */
		batchSelect :  function (obj){
			$(obj).parent().children().removeClass("favorable");
			$(obj).addClass("favorable");
			this.boxAjax($(obj).data("url"), "batch_select_number");
		},
		
		/**
		 * 批量购买
		 * @param Object data{
		 *	   bid,
		 *	   cid,
		 *	   count,
		 *	   orderId,
		 *	   price,
		 *	   endid,
		 *	   discount,
		 *	 }
		 */
		batchBuy :  function (obj){ 
			var O = this;
			var data  = $(obj).data("json");

			$.ajax({
				url  : order.pathIndex+"?ca=Order_Order.BatchBuy",
				type : "get",
				cache:false,
				dataType:"json",
				data: data,
				success: function(msg){
					if(msg.status==1){
						O.tip({
							"title" : msg.message, 
							"btnName" : "下载客户端",
							"callback" : O.goUrl,
							"callbackParam" : [order.pathIndex+"?ca=Client.Index"], 
							"cancelName" : "关闭"
						})
					}else{
						O.tip({
							"title" : msg.message, 
							"btnName" : "确定",
							"callback" : O.close,
							"cancelName" : "关闭"
						})
					}
				}
			})
		},
		
		/**
		 * 选择起始章节页面的方法
		 */
		startChapter : function(){
			var order = this;
			return {
				next : function(bid){
					order.boxAjax($("#start_chapter_li li.hover").data("url"));
				},
				select : function(obj){
					$(obj).parent().children().removeClass("hover");
					$(obj).addClass("hover");
				}
			}
		},
		
		/**
		 * 充值
		 */
		recharge : function(){
			location.href = order.pathIndex+"?ca=Recharge_Sms.Index";
		},
		/**
		 * 获取URL
		 * @param String action
		 * @param Object param
		 */
		getUrl : function( param){
			param = $.param(param);
			return url = order.pathIndex+"?"+param;
		},
		
		/**
		 * 按钮置灰，不可点击
		 */
		addButtonGray : function(){
		
		},
		
		goLogin : function(url){
			url = encodeURIComponent(url || location.href);
			
			location.href = order.pathIndex+"?ca=Passport.Login&goUrl="+url;
		},
		goUrl : function(url){
			location.href = url;
		},
		
		goDownload : function(url){
			this.goUrl(order.pathIndex+'?ca=Client.Index');
		},
		/**
		 * 清除置灰
		 */
		removeButtonGray:function(){
		
		}
	}
	window.orderObject = new order();
})(jQuery)