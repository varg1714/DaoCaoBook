/* globals _preventDefault */
/* globals WeixinJSBridge */

// iframe交互
// 通信格式 { type: 函数名，约定以set开头, params: obj参数对象 }
// 已有方法setSrc、setTitle、setHeight、setClose
// 阻止默认行为用
function _preventDefault(e) {
    e.preventDefault()
}
var iframeUi = {
    $ifr: $('[data-js=iframe]'),
    $ifrMask: $('.iframe_mask'),
    $ifrC: $('[data-js=iframe_c]'),
    $ifrScroll: $('[data-js=iframe_scroll]'),
    $ifrClose: $('[data-js=iframe_close]'),
    $ifrTit: $('[data-js=iframe_tit]'),
    isMobileSafari: /iP(ad|hone|od).*Safari/i.test(navigator.userAgent),
    targetOri: '*',
    // 设置iframe地址
    setSrc: function(url) {
        this.$ifr.attr('src', url);
        this.$ifrC.css('opacity', 1);
        this.$ifrMask.show();
        this.$ifrC.css('bottom', 0);
        $('html').addClass('no_scroll2');
        $(document).on('touchmove', _preventDefault)
    },
    // 设置iframe标题
    setTitle: function(obj) {
        this.$ifrTit.html(obj.title)
    },
    // 设置iframe相关高度
    setHeight: function(obj) {
        var _height = 540;
        if (this.isMobileSafari) {
            // iOS下iframe高自动等于其内容高，不受控制
            // 通过iframe父级容器scroll模拟iframe滚动
            this.$ifr.css('height', obj.height)
        } else {
            // 设置和父级同高，方便做scrollTo效果
            this.$ifr.css('height', _height)
        }
        this.$ifrScroll.css('height', _height);

        // iPhone 6旋屏后上次overflow状态会保留，auto需时时设置
        if (this.isMobileSafari) {
            if (_height < obj.height) {
                this.$ifrScroll.css('overflow', 'auto')
            } else {
                this.$ifrScroll.css('overflow', 'hidden')
            }
        }
    },
    setClose: function() {
        this.$ifrMask.hide();
        $('html').removeClass('no_scroll2');
        $(document).off('touchmove', _preventDefault);
        // 恢复初始高度
        this.$ifrC.css('bottom', '-620px')
        this.$ifr.css('height', '');
        //清空iframe内容
        $('.iframe').attr("src","about:blank");
        this.$ifrScroll.css('height', '540px');
        this.$ifrC.css('opacity', 0);
        this.$ifrTit.html('正在处理。。。');
    },
    postToIfr: function(obj) {
        this.ifrWin.postMessage(obj, this.targetOri)
    },
    init: function() {
        var self = this;
        this.ifrWin = this.$ifr[0].contentWindow || this.$ifr[0].contentDocument.parentWindow;

        // 监听iframe通信
        window.addEventListener('message', function(e) {

            var _ori = e.origin || e.originalEvent.origin;
            // type为约定方法，params为约定数据
            if ($.type(self[e.data.type]) === 'function') {
                self[e.data.type](e.data.params)
            }
        });

        // 监听旋屏，因为iframe自身无法感知，延迟300ms通知iframe
        window.addEventListener('orientationchange', function() {
            setTimeout(function() {
                self.postToIfr({
                    isOrientaionChange: true
                })
            }, 300)
        })

        this.$ifrClose.on('click', function() {
            self.setClose()
        })
    },

    weixinPay: function(data) {
        switch (data.type) {

            case "weixinwap":
            case "weixinh5":
                setTimeout(function() {
                    iframeUi.setSrc(data.goUrl)
                }, 5000);

                location.href = data.wxPayUrl;
                break;
            case "weixin":

                WeixinJSBridge.invoke('getBrandWCPayRequest', data.wxPayParams, function(res) {
                    switch (res.err_msg) {
                        case 'get_brand_wcpay_request:ok':
                            iframeUi.setSrc(data.goUrl)
                            break;
                    }
                });
                break;
        }
    }
}

iframeUi.init();
