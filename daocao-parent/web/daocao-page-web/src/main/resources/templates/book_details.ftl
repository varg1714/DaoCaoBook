        <!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta property="wb:webmaster" content="80c43c1dcc7669d4">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="ie-stand">

    <title>${book.name}</title>
    <meta name="keywords" content="${book.name}">
    <meta name="description" content="${book.name}，稻草为您第一时间更新，电脑手机均可阅读；">

    
    <link type="text/css" rel="stylesheet" href="./css/global.css">
    <link type="text/css" rel="stylesheet" href="./css/common.css">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="./js/zypc.js"></script>

    <script src="plugins/angularjs/angular.min.js"></script>
    <script src="plugins/angularjs/pagination.js"></script>
    <link rel="stylesheet" href="plugins/angularjs/pagination.css">
    <script src="js/base.js"></script>
    <script src="js/service/bookService.js"></script>
    <script src="js/controller/itemContrloller.js"></script>
</head>

<body ng-app="daocao" ng-controller="itemController" ng-init="init(${book.id})">
<#assign bookimages=book.bookimages?eval />
<script type="text/javascript">var USER_IS_LOGIN = 0;</script>
<style>::-ms-clear, ::-ms-reveal{display: none;}</style><!--ie x号-->

<!--公共头部 开始-->
<div class="blankHead zwfl_head">
    <div class="n1_header" style="box-shadow: none;">
        <div class="n1_header_wrap">
            <h1>
                <a href="javascript:;"><img src="./img/web-logo.gif" alt="logo" title="稻草书城"></a>
            </h1>
            <div class="n1_headerR">
                <ul class="n1_nav">
                    <li><a href="javascript:;">首页</a></li>
                    <li><a href="javascript:;">分类</a></li>
                    <li><a href="javascript:;">排行</a></li>
                    <!--
                    <li><a href="javascript:;">出版</a></li>
                    <li><a href="javascript:;">男生</a></li>
                    <li><a href="javascript:;">女生</a></li>
                    -->
                    <li>
                        <a href="http://localhost:8082/user/home-index.html">个人中心</a>
                        <!--<img src="./img/new.png" alt="new">-->
                    </li>
                    <!--<li><a href="http://www.ireader.com/index.php?ca=Activity_Comic.index&pca=bookdetail.index" target="_blank">征稿</a></li>-->
                    <li><a href="javascript:;" target="_blank">下载</a></li>
                </ul>
                <div class="n1_search">
                    <form method="get" action="http://www.ireader.com/index.php?ca=search.index&pca=bookdetail.index" onsubmit="return false;" id="search_form" name="search_form">
                    <input type="text" name="keyword" value="" placeholder="书名/作者名/ISBN/出版社" autocomplete="off">
                    <!-- class="big_search"-->
                        <s id="btn_submit_search_form"></s>
                    </form>
                    <ul class="list" id="search_suggestion_box"></ul>
                </div>
                <div class="n1_login">
                    <a href="http://localhost:8085/cart.html" target="_blank"><span class="n1_login_beffor" id="unlogin_box" style="display: none;">登录</span></a>
                    <div class="n1_login_affer" id="login_box" style="display: block;">
                        <a href="javascript:;"><i>东流</i><s></s></a>
                        <ul>
                            <li><a href="http://localhost:8085/cart.html" title="我的购物车" target="_blank"><s class="myBook"></s>我的购物车</a></li>
                            <li><a href="http://localhost:8082/user/goods_edit.html" title="我要出售" target="_blank"><s class="n1_recharge"></s>我要出售</a></li>
                            <li><a href="javascript:;" title="退出登录"><s class="quit"></s>退出</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--公共头部 结束-->


<link type="text/css" rel="stylesheet" href="./css/sjxqy.css">
<style>
@charset "UTF-8";.iframe_mask{height:100%;background-color:rgba(0,0,0,.5);position:fixed;top:0;left:0;bottom:0;right:0;z-index:1200;display:none}.iframe_c{background:#fff;position:fixed;bottom:-600px;left:0;right:0;z-index:1201;-webkit-transition:all .3s;transition:all .3s}.iframe_head{height:40px;padding:0 5px 0 20px;line-height:40px;border-bottom:#eee 1px solid}.iframe_head h3{font-size:16px;color:#222}.iframe_head .icon_close{width:40px;height:40px;text-align:center;font-size:26px;line-height:40px;color:#999;float:right}.iframe_scroll{height:540px;overflow:auto;-webkit-overflow-scrolling:touch}.iframe{width:100%;height:150px;border:0;overflow-y:scroll;display:block}
</style>
<div class="iframe_mask"></div>
<div class="iframe_c" data-js="iframe_c">
    <div class="iframe_head">
        <div class="icon_close" data-js="iframe_close">&times</div>
        <h3 data-js="iframe_tit">正在处理。。。</h3>
    </div>
    <div class="iframe_scroll" data-js="iframe_scroll">
        <iframe class="iframe" src="" data-js="iframe"></iframe>
    </div>
</div>
<!--用手机看 结束-->
<div class="howLook" mod="sao_step">
    <h3>即刻使用手机阅读</h3>
    <ul>
        <li>
            <img src="./img/howLook01.jpg" alt="第1步">
            <span>第1步</span>
            <p>打开稻草客户端，</p>
            <p>点击右上角搜索图标</p>
        </li>
        <li class="step02">
            <img src="./img/howLook02.jpg" alt="第2步">
            <span>第2步</span>
            <p>点击搜索框中右侧的</p>
            <p>扫描图标</p>
        </li>
        <li>
            <div style="background: url(./image/howLook03.jpg) no-repeat; width: 214px; height: 118px;">
                <img alt="第3步" src="./img/index-bookdetail.AppOpenQr_10124.jpg" style="width: 110px; margin-left: 55px; background-color: white; height: 110px; position: relative; top: 4px;">
            </div>
            <span>第3步</span>
            <p>扫描二维码</p>
        </li>
    </ul>
</div>
<!--用手机看 结束-->
<!--发表 开始-->
<div class="pubTan" style="display: none;" id="tmp_comment_form">
    <input type="hidden" name="comment_vote" value="0">
    <div class="tanTop">
        <span class="dis disCur" name="comment_type" data-value="topic">发讨论<s></s></span>
        <span class="com" name="comment_type" data-value="comment" style="display: none;">写书评<s></s></span>
        <s class="off"></s>
    </div>
    <div class="tanCon">
        <span class="headPic"><img src="" alt=""></span>
        <div class="tanConR">
            <div class="comStar" mod="comment_vote" style="display: none;" data-star="//images/sjxq/(star).png">
                <img src="./img/gStar.png" alt="1星">
                <img src="./img/gStar.png" alt="2星">
                <img src="./img/gStar.png" alt="3星">
                <img src="./img/gStar.png" alt="4星">
                <img src="./img/gStar.png" alt="5星">
            </div>
                    <div class="tanTxtBox" onclick="if( $('.tanTxt').val() =='') { $('.tip').css('display','none');$('.tanTxt').focus();}">
                <div class="tip">
                    <p>建议讨论：</p>
                    <p>写与这本书有关的呗</p>
                    <p>友好的讨论情节、人物设置等等，不要骂人~~</p>
                    <p>其他你想说的</p>
                </div>
                <textarea onfocus="if( $(this).val() =='') { $('.tip').css('display','none');}" onblur="if($(this).val() =='')  { $('.tip').css('display','block');}" class="tanTxt" name="comment_txt" style="resize:none;overflow:hidden;"></textarea>
            </div>
                        <div class="tanConB">
                            <span class="remind" style="display: none;"><s></s><i></i></span>
                <span class="pub" name="comment_submit">发表</span>
            </div>
        </div>
    </div>
</div>
<!--发表 结束-->

<!--书籍内容 开始-->
<div class="content">

    <div class="bookInfor">
        <div class="bookL">
            <s>${category.cate1}</s>
            <#if category.cate2??>
                <s style="margin-top: 40px;">${category.cate2}</s>
            </#if>
            <a>
                <#if (bookimages?size>0)>
                    <img src="${bookimages[0]}" alt="${book.name}" title="${book.name}">
                </#if>
            </a>
            <img src="./img/shadow.gif" alt="" class="shadow">
        </div>
        <div class="bookR">
                    <div class="bookinf01">
                <div class="bookname">
                    <h2><a>${book.name}</a></h2>
                </div>
                <p>
                    <div style="margin-bottom: 4px;">
                        <span class="author" style="margin-right: 12px;width: 180px;">书籍作者：${book.author!'-'}</span>
                        <span style="margin-right: 12px;width: 240px;">出版集团：${book.publisher!'-'}</span>
                        <span style="margin-right: 12px;width: 180px;">出版日期：${(book.publishdate?string('yyyy-MM'))!'-'}</span>
                    </div>
                    <div style="margin-bottom: 4px;">
                        <span style="margin-right: 12px;width: 180px;">出版价格：${book.publishprice!'-'}</span>
                        <span style="margin-right: 12px;width: 240px;">书籍版次：${book.version!'-'}</span>
                        <span style="margin-right: 12px;width: 180px;">书籍ISBN: ${book.isbn!'-'}</span>
                    </div>
                    <div>
                        <span style="margin-right: 12px;width: 180px;">装订类型：${book.bindtype!'-'}</span>
                        <span style="margin-right: 12px;width: 240px;">纸张大小：${book.papersize!'-'}</span>
                        <span style="margin-right: 12px;width: 180px;">书籍页数：${book.pagenumber!'-'}</span>
                    </div>
                </p>
            </div>
            <div class="bookinf02">
                <div class="left" style="width: 200px;">
                    <p>
                        <i class="price">价格：${book.sellprice!'-'}元</i>
                        <i class="price">邮费：${book.postage!'-'}元</i>
                        <i class="price">在售数量：${book.number!'-'}本</i>
                    </p>
                    <a data-js="readNow" class="readNow" ><span class="tryRead" ng-click="addToCart(false,${book.id})">加入购物车</span></a>
                    <span class="buyBook" ng-click="addToCart(true,${book.id})">购买</span>
                    <input type="hidden" id="getStartChapterUrl" value="">
                </div>
                <div class="right">
					<span class="look_phone" mod="look_phone_pic">
						<img src="./img/look_phone.jpg" alt="扫一扫">
					</span>
					<span class="look_ewm" mod="look_phone_pic">
						<img src="./img/index-bookdetail.AppOpenQr_10124.jpg" alt="扫一扫" style="height: 83px;width: 83px; margin: -4px;">
					</span>
                </div>
            </div>
            <div class="bookinf03">
                <p>${book.description}</p>
                <div class="shareWrap">
                    <div class="share" style="display: none;">
                        <a href="javascript:;" class="weibo">
                            <img src="./img/weibo.png" _hover_src="//images/rank/weiboHov.png" alt="weibo">
                        </a>
                        <a href="javascript:;">
                            <img src="./img/qq.png" _hover_src="//images/rank/qqHov.png" alt="qq">
                        </a>
                    </div>
                    <span><a href="javascript:;" btn="desc_show_more">展开<s></s></a></span>
                </div>
            </div>
        </div>
    </div>

    <!--书圈 开始-->
    <div class="sqCon">
        <div class="bookCir">
            <div class="title">
                <p>
                    <span class="shuquan">书圈</span>
                                    <span class="line">|</span>
                    <span>共{{evals.totalPage}}页，{{evals.total}}条</span>
                                </p>
                                <a href="javascript:;" class="comBtn" btn="topic_add" data-has_fu_text="0" data-cid="book_10123797">
                    <s></s>
                    <span>我来说两句</span>
                </a>
                            </div>

            <div class="allCom" style="display: block;">
                <ul >
                    <li ng-repeat="eval in evals.rows">
                        <span class="headImg">
                            <img src="./img/4035.jpg" alt="Lovae">
                        </span>
                        <div class="allCom">
                            <div class="ComMan">
                                <p>{{eval.nickName}}</p>
                                <span class="lv">LV{{eval.userLevel}}</span>
                                <span>{{eval.evaluationDate}}</span>
                            </div>
                            <div class="ComStar">
                                <img src="./img/yStar.png" ng-repeat="x in getNumber(eval.evalType)">
                                <img src="./img/gStar.png" ng-repeat="x in getNumber(5 - eval.evalType)">
                            </div>
                            <div class="comTxt">
                                <span>
                                    <s>书评</s>
                                    <i><a href="javascript:;" target="_blank">{{eval.evaluation}}</a></i>
                                </span>
                            </div>
                            <div class="attend">
                                <a href="javascript:;" btn="like" data-num="0" data-liked="0" data-tid="58613485" data-cid="book_10123797" class="zan click">
                                    <!--<s class="zan"></s>-->
                                    <img src="./img/zan.gif" alt="赞">
                                    <i>&nbsp;</i>
                                </a>
                                <span class="line"></span>
                                <a href="javascript:;" class="huifu" target="_blank">
                                    <img src="./img/hf.gif" alt="回复">
                                    <i>&nbsp;</i>
                                </a>
                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>
                            </div>
                        </div>
                    </li>
<!--                    <li>-->
<!--                        <span class="headImg">-->
<!--                            <img src="./img/4036.jpg" alt="活着">-->
<!--                        </span>-->
<!--                        <div class="allCom">-->
<!--                            <div class="ComMan">-->
<!--                                <p>活着</p>-->
<!--                                <span class="lv">LV4</span>-->
<!--                                                                <span>2016-11-26</span>-->
<!--                            </div>-->
<!--                                                    <div class="ComStar">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                        </div>-->
<!--                                                    <div class="comTxt">-->
<!--                                <span>-->
<!--                                    <s>书评</s>-->
<!--                                    <i><a href="javascript:;" target="_blank">非常好，非常有感情。</a></i>-->
<!--                                                                </span>-->
<!--                                                        </div>-->
<!--                            <div class="attend">-->
<!--                                <a href="javascript:;" btn="like" data-num="0" data-liked="0" data-tid="51455127" data-cid="book_10123797" class="zan click">-->
<!--                                    &lt;!&ndash;<s class="zan"></s>&ndash;&gt;-->
<!--                                    <img src="./img/zan.gif" alt="赞">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="line"></span>-->
<!--                                <a href="javascript:;" class="huifu" target="_blank">-->
<!--                                    <img src="./img/hf.gif" alt="回复">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <span class="headImg">-->
<!--                            <img src="./img/touxiang.png" alt="战神">-->
<!--                        </span>-->
<!--                        <div class="allCom">-->
<!--                            <div class="ComMan">-->
<!--                                <p>战神</p>-->
<!--                                <span class="lv">LV8</span>-->
<!--                                                                <span>2016-07-15</span>-->
<!--                            </div>-->
<!--                                                    <div class="disTxt">-->
<!--                                <span>-->
<!--                                    <s>讨论</s>-->
<!--                                    <i><a href="javascript:;" target="_blank">。_。。_°—°</a></i>-->
<!--                                                                </span>-->
<!--                                                        </div>-->
<!--                            <div class="attend">-->
<!--                                <a href="javascript:;" btn="like" data-num="1" data-liked="0" data-tid="29750054" data-cid="book_10123797" class="zan click">-->
<!--                                    &lt;!&ndash;<s class="zan"></s>&ndash;&gt;-->
<!--                                    <img src="./img/zan.gif" alt="赞">-->
<!--                                    <i>1</i>-->
<!--                                </a>-->
<!--                                <span class="line"></span>-->
<!--                                <a href="javascript:;" class="huifu" target="_blank">-->
<!--                                    <img src="./img/hf.gif" alt="回复">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <span class="headImg">-->
<!--                            <img src="./img/getAvatar-t-2708997619-10_100_10.jpg" alt="t-2708997619-">-->
<!--                        </span>-->
<!--                        <div class="allCom">-->
<!--                            <div class="ComMan">-->
<!--                                <p>t-2708997619-</p>-->
<!--                                <span class="lv">LV3</span>-->
<!--                                                                <span>2016-05-25</span>-->
<!--                            </div>-->
<!--                                                    <div class="ComStar">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/gStar.png">-->
<!--                                                            <img src="./img/gStar.png">-->
<!--                                                        </div>-->
<!--                                                    <div class="comTxt">-->
<!--                                <span>-->
<!--                                    <s>书评</s>-->
<!--                                    <i><a href="javascript:;" target="_blank">不咋样</a></i>-->
<!--                                                                </span>-->
<!--                                                        </div>-->
<!--                            <div class="attend">-->
<!--                                <a href="javascript:;" btn="like" data-num="0" data-liked="0" data-tid="23853579" data-cid="book_10123797" class="zan click">-->
<!--                                    &lt;!&ndash;<s class="zan"></s>&ndash;&gt;-->
<!--                                    <img src="./img/zan.gif" alt="赞">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="line"></span>-->
<!--                                <a href="javascript:;" class="huifu" target="_blank">-->
<!--                                    <img src="./img/hf.gif" alt="回复">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <span class="headImg">-->
<!--                            <img src="./img/touxiang.png" alt="战神">-->
<!--                        </span>-->
<!--                        <div class="allCom">-->
<!--                            <div class="ComMan">-->
<!--                                <p>战神</p>-->
<!--                                <span class="lv">LV8</span>-->
<!--                                                                <span>2016-05-14</span>-->
<!--                            </div>-->
<!--                                                    <div class="disTxt">-->
<!--                                <span>-->
<!--                                    <s>讨论</s>-->
<!--                                    <i><a href="javascript:;" target="_blank">。。。。。*%47</a></i>-->
<!--                                                                </span>-->
<!--                                                        </div>-->
<!--                            <div class="attend">-->
<!--                                <a href="javascript:;" btn="like" data-num="1" data-liked="0" data-tid="22835086" data-cid="book_10123797" class="zan click">-->
<!--                                    &lt;!&ndash;<s class="zan"></s>&ndash;&gt;-->
<!--                                    <img src="./img/zan.gif" alt="赞">-->
<!--                                    <i>1</i>-->
<!--                                </a>-->
<!--                                <span class="line"></span>-->
<!--                                <a href="javascript:;" class="huifu" target="_blank">-->
<!--                                    <img src="./img/hf.gif" alt="回复">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <span class="headImg">-->
<!--                            <img src="./img/touxiang.png" alt="战神">-->
<!--                        </span>-->
<!--                        <div class="allCom">-->
<!--                            <div class="ComMan">-->
<!--                                <p>战神</p>-->
<!--                                <span class="lv">LV8</span>-->
<!--                                                                <span>2016-05-14</span>-->
<!--                            </div>-->
<!--                                                    <div class="disTxt">-->
<!--                                <span>-->
<!--                                    <s>讨论</s>-->
<!--                                    <i><a href="javascript:;" target="_blank">°_°</a></i>-->
<!--                                                                </span>-->
<!--                                                        </div>-->
<!--                            <div class="attend">-->
<!--                                <a href="javascript:;" btn="like" data-num="1" data-liked="0" data-tid="22834990" data-cid="book_10123797" class="zan click">-->
<!--                                    &lt;!&ndash;<s class="zan"></s>&ndash;&gt;-->
<!--                                    <img src="./img/zan.gif" alt="赞">-->
<!--                                    <i>1</i>-->
<!--                                </a>-->
<!--                                <span class="line"></span>-->
<!--                                <a href="javascript:;" class="huifu" target="_blank">-->
<!--                                    <img src="./img/hf.gif" alt="回复">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <span class="headImg">-->
<!--                            <img src="./img/CmQUNlYCMf2EHNknAAAAACmzgM098360.jpg" alt="暗香">-->
<!--                        </span>-->
<!--                        <div class="allCom">-->
<!--                            <div class="ComMan">-->
<!--                                <p>暗香</p>-->
<!--                                <span class="lv">LV12</span>-->
<!--                                <span class="vip">VIP</span>                                <span>2016-04-19</span>-->
<!--                            </div>-->
<!--                                                    <div class="ComStar">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/yStar.png">-->
<!--                                                            <img src="./img/gStar.png">-->
<!--                                                        </div>-->
<!--                                                    <div class="comTxt">-->
<!--                                <span>-->
<!--                                    <s>书评</s>-->
<!--                                    <i><a href="javascript:;" target="_blank">我只听到美美的语句，有点没逻辑性，就好像一个满是装饰的蛋糕上，都不知道让人从哪下口！</a></i>-->
<!--                                                                </span>-->
<!--                                                        </div>-->
<!--                            <div class="attend">-->
<!--                                <a href="javascript:;" btn="like" data-num="1" data-liked="0" data-tid="20785080" data-cid="book_10123797" class="zan click">-->
<!--                                    &lt;!&ndash;<s class="zan"></s>&ndash;&gt;-->
<!--                                    <img src="./img/zan.gif" alt="赞">-->
<!--                                    <i>1</i>-->
<!--                                </a>-->
<!--                                <span class="line"></span>-->
<!--                                <a href="javascript:;" class="huifu" target="_blank">-->
<!--                                    <img src="./img/hf.gif" alt="回复">-->
<!--                                    <i>&nbsp;</i>-->
<!--                                </a>-->
<!--                                <span class="up" btn="content_hide_more" style="display: none;"><i>收起</i><s></s></span>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </li>-->
                </ul>
                <div class="load">
                    <p class="loadMore" ng-if="evals.total === 0"><a href="javascript:;" >暂无评价哦</a></p>
                    <p class="loadMore" ng-if="evals.total > 0 && evals.total <= 15"><a href="javascript:;" >到底啦</a></p>
                    <p class="loadMore" ng-if="evals.total > 15"><a href="javascript:;" target="_blank">查看更多</a></p>
                    <!--<p class="loading"><s></s>加载更多...</p>-->
                </div>
            </div>

        </div>

        <!--右侧内容 开始-->
        <div class="conR">
                    <div class="seen">
                <div class="seeTit">
                    <span class="title">看过的人还看</span>
                                    </div>
                <div class="seeWrap" mod="relate_book_list">
                    <ul>
                        <li>
                            <a href="javascript:;" target="_blank">
                                <img src="./img/CmQUOV3XlxiEZX65AAAAAMf8Ebw891861.jpg" alt="簪中录合集">
                            </a>
                            <p class="bookNume">
                                <a href="javascript:;" target="_blank">簪中录合集</a>
                            </p>
                            <p class="author">侧侧轻寒</p>
                            <span><s></s>2325</span>
                             <a href="javascript:;" target="_blank">
                                <img src="./img/CmQUOV2JwxyENFgtAAAAAB1f2Us03432.jpg" alt="霍乱时期的爱情（1982年诺贝尔文学奖得主马尔克斯的“爱情百科全书”）">
                            </a>
                            <p class="bookNume">
                                <a href="javascript:;" target="_blank">霍乱时期的爱情（1982年诺贝尔文学奖得主马尔克斯的“爱情百科全书”）</a>
                            </p>
                            <p class="author">马尔克斯</p>
                            <span><s></s>206</span>
                            </li>
                        <li>
                            <a href="javascript:;" target="_blank">
                                <img src="./img/CmRae1uE7LyESI0gAAAAAKL40E017259.jpg" alt="金庸作品集">
                            </a>
                            <p class="bookNume">
                                <a href="javascript:;" target="_blank">金庸作品集</a>
                            </p>
                            <p class="author">金庸</p>
                            <span><s></s>330</span>
                                                                                                        <a href="javascript:;" target="_blank">
                                <img src="./img/CmQUN1X04PWEXsd7AAAAALweKkk23288.jpg" alt="三生三世枕上书（上）迪丽热巴高伟光主演">
                            </a>
                            <p class="bookNume">
                                <a href="javascript:;" target="_blank">三生三世枕上书（上）迪丽热巴高伟光主演</a>
                            </p>
                            <p class="author">唐七</p>
                            <span><s></s>315</span>
                            </li>
                    </ul>
                </div>
            </div>
        
                    <div class="showRight">
                <h3>同类热销榜</h3>
                <ul>
                                    <li class="onShow" onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>1</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="傅少的心尖宠">傅少的心尖宠</a>
                                </span>
                                <span class="num"><s></s>4157</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>1</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="傅少的心尖宠">
                                            <img src="./img/CmQUOV54kjyEQXl1AAAAAD8_mIE680301.jpg" alt="傅少的心尖宠">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="傅少的心尖宠">傅少的心尖宠</a></p>
                                        <span class="num"><s></s>4157</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>2</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="靠近你，淹没我">靠近你，淹没我</a>
                                </span>
                                <span class="num"><s></s>4519</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>2</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="靠近你，淹没我">
                                            <img src="./img/CmQUOV1o946ESiF0AAAAACk0GH0254311.jpg" alt="靠近你，淹没我">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="靠近你，淹没我">靠近你，淹没我</a></p>
                                        <span class="num"><s></s>4519</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>3</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="你比月色动人">你比月色动人</a>
                                </span>
                                <span class="num"><s></s>5922</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>3</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="你比月色动人">
                                            <img src="./img/CmQUOV6T7caEYsQ2AAAAAOXTIHE63912.jpg" alt="你比月色动人">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="你比月色动人">你比月色动人</a></p>
                                        <span class="num"><s></s>5922</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>4</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="嫤语书年">嫤语书年</a>
                                </span>
                                <span class="num"><s></s>7411</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>4</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="嫤语书年">
                                            <img src="./img/CmQUN1X2dvGEI7aGAAAAALDImm4901421.jpg" alt="嫤语书年">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="嫤语书年">嫤语书年</a></p>
                                        <span class="num"><s></s>7411</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>5</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="簪中录合集">簪中录合集</a>
                                </span>
                                <span class="num"><s></s>9992</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>5</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="簪中录合集">
                                            <img src="./img/CmQUOV3XlxiEZX65AAAAAMf8Ebw891861.jpg" alt="簪中录合集">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="簪中录合集">簪中录合集</a></p>
                                        <span class="num"><s></s>9992</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>6</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="全世界都想要的他，属于我">全世界都想要的他，属于我</a>
                                </span>
                                <span class="num"><s></s>3102</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>6</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="全世界都想要的他，属于我">
                                            <img src="./img/CmQUOV1bxAiEN2p-AAAAAIm6Eok349141.jpg" alt="全世界都想要的他，属于我">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="全世界都想要的他，属于我">全世界都想要的他，属于我</a></p>
                                        <span class="num"><s></s>3102</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>7</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="秦非得已">秦非得已</a>
                                </span>
                                <span class="num"><s></s>2897</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>7</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="秦非得已">
                                            <img src="./img/CmQUOV2defeEKXf_AAAAAI-PE0Y983011.jpg" alt="秦非得已">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="秦非得已">秦非得已</a></p>
                                        <span class="num"><s></s>2897</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>8</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="青青陌上桑-1[精品]">青青陌上桑-1[精品]</a>
                                </span>
                                <span class="num"><s></s>6144</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>8</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="青青陌上桑-1[精品]">
                                            <img src="./img/CmQUOF59aqGEKY2LAAAAAIcqVO021380.jpg" alt="青青陌上桑-1[精品]">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="青青陌上桑-1[精品]">青青陌上桑-1[精品]</a></p>
                                        <span class="num"><s></s>6144</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>9</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="分手妻约-2">分手妻约-2</a>
                                </span>
                                <span class="num"><s></s>2263</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>9</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="分手妻约-2">
                                            <img src="./img/CmQUOV2dhwaEWK7YAAAAAB9r16078495.jpg" alt="分手妻约-2">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="分手妻约-2">分手妻约-2</a></p>
                                        <span class="num"><s></s>2263</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                    <li onmouseover="$(this).addClass('onShow').siblings().removeClass('onShow');">
                        <dl class="close">
                            <dt>10</dt>
                            <dd>
                                <span class="bookName">
                                    <a href="javascript:;" target="_blank" title="难得有情人[精品]">难得有情人[精品]</a>
                                </span>
                                <span class="num"><s></s>2168</span>
                            </dd>
                        </dl>
                        <dl class="open">
                            <dt>10</dt>
                            <dd>
                                <div>
                                    <span class="bookPic">
                                        <a href="javascript:;" target="_blank" title="难得有情人[精品]">
                                            <img src="./img/CmQUOF6mO-6EeyijAAAAAF8vA1g35236.jpg" alt="难得有情人[精品]">
                                        </a>
                                    </span>
                                    <div class="bookCon">
                                        <p><a href="javascript:;" target="_blank" title="难得有情人[精品]">难得有情人[精品]</a></p>
                                        <span class="num"><s></s>2168</span>
                                    </div>
                                </div>
                            </dd>
                        </dl>
                    </li>
                                </ul>
            </div>
        
        </div>
        <!--右侧内容 结束-->
    </div>
    <!--书圈 结束-->

</div>
<!--书籍内容 结束-->

<link href="./css/webuploader.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="./js/webuploader.min.js"></script>
<script type="text/javascript" src="./js/layer.js"></script>
<script type="text/javascript" src="./js/book.js"></script>
<script type="text/javascript" src="./js/topic.js"></script>
<script type="text/javascript" src="./js/iframe.js"></script>

<script type="text/javascript">

    (function () {
        //topic.is_join = "0";
            topic.is_join = 1;
        topic.has_new_login_mod = true;
        topic.has_new_form_mod = true;
        topic.bind();
            $('[btn]').bind('selectstart', function () {return false;});
    })();

    //余额足够时购买完回调
    iframeUi.setCallback = function (param){
        buyBook = $(".buyBook").length;
        if(param.action == 'closeOrder' && buyBook == 1){
            alert("购买成功");
            iframeUi.setClose();
            location.href = $(".readNow").attr("href");
        }
    }

</script>

    
<!--公共底部 开始-->
<div class="v1_foot">
    <div class="download">
        <h3>获取稻草iReader</h3>
        <div class="loadLeft">
            <a href="javascript:;" target="_blank" title="ios下载">
                <span class="icon"><s></s></span>
                <p class="explain">下载iOS版</p>
            </a>
        </div>
        <div class="loadRight">
            <a href="javascript:;" target="_blank" title="android下载">
                <span class="icon"><s></s></span>
                <p class="explain">下载Android版</p>
            </a>
        </div>
    </div>
    <div class="aboutcompany">
        <p class="link">
            <a href="javascript:;" target="_blank">稻草官网</a>
            <a href="index1.html" target="_blank">稻草小说</a>
            <a href="javascript:;" target="_blank">稻草书城触屏版</a>
            <a href="javascript:;" target="_blank">得间免费小说</a>
            <a href="javascript:;" target="_blank">企业服务</a>
            <a href="javascript:;" target="_blank">红薯中文</a>
            <a href="javascript:;" target="_blank">趣阅中文</a>
            <a href="javascript:;" target="_blank">iCiyuan轻小说</a>
            <a href="javascript:;" target="_blank">魔情言情</a>
            <a href="javascript:;" target="_blank">神起网</a>
            <a href="javascript:;" target="_blank">有乐中文网</a>
            <a href="javascript:;" target="_blank">若看文学</a>
            <a href="javascript:;" target="_blank">喵阅读</a>
            <a href="javascript:;" target="_blank">书香云集</a>
            <a href="javascript:;" target="_blank">联系我们</a>
            <a href="javascript:;" target="_blank">商务合作</a>
            <a href="javascript:;" target="_blank">关于我们</a>
            <a href="javascript:;" target="_blank">法律声明</a>
        </p>
        <p><a href="javascript:;" target="_blank">京ICP备11008516号</a><a href="javascript:;" target="_blank">网络出版服务许可证（总）网出证（京）字第141号</a><a href="files/icp.jpg" target="_blank">京ICP证090653号</a><a href="javascript:;" target="_blank"><img style="margin-right:5px; display:inline; vertical-align:middle; margin-top:-2px" src="./img/web_record.png">京公网安备11010502030452</a></p>
        <p>2015 All Rights Reserved 稻草科技股份有限公司 版权所有</p>
        <p>不良信息举报：jubao@zhangyue.com 举报电话：010-59845699</p>
    </div>
</div>
<!--公共底部 结束-->


<!--返回顶部 结束-->
<span class="goTop" id="btn_back_top" style="display: none;"><img src="./img/go_top.png" alt="返回顶部"></span>
<!--返回顶部 结束-->
</body>
</html>
