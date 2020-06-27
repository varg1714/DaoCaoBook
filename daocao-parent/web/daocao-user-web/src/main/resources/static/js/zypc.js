
var zypc = {};

//登录提示
zypc.login_alert = function () {
    html = '<div class="noLogin" style="display: block;" id="unlogin_msg_view">\
                <span class="off"><s></s></span>\
                <p>未登录，请登录后操作</p>\
                <span class="loginBtn">登录</span>\
            </div>';
    layer.open({
        type: 1, shift: 5, title: false, closeBtn: false, shadeClose: true,
        content: html,
        success: function (obj, index) {
            $('#unlogin_msg_view').find('.off').on('click', function () {
                layer.close(index);
            })
            .end().find('.loginBtn').on('click', function () {
                $('#unlogin_box').click();
            });
        }
    });
};

//搜索
zypc.search = {
    'form': null,
    'keyword_input': null,
    'suggestion_url': function (kw) {
        return 'index.php?ca=search.GetSuggestion&keyword=' + kw;
    },
    'init': function () {
        //输入
        this.keyword_input = $('#search_form input[name="keyword"]');
        this.keyword_input.bind({
            'blur focus click': this.change_input_status,
            'input propertychange': this.suggestion,
            //键盘上下箭头按键
            'keydown': function (e) {
                if (e.keyCode == 40 || e.keyCode == 38) {
                    var index = $('#search_suggestion_box li.hover').index();
                    var maxIndex = $('#search_suggestion_box li').length - 1;
                    switch (e.keyCode) {
                        case 40: index ++; break;   //下
                        case 38: index --; break;   //上
                    }
                    index = index < 0 ? maxIndex : (index > maxIndex ? 0 : index);
                    var keyword = $('#search_suggestion_box li:eq(' + index + ')').mouseover().text();
                    zypc.search.keyword_input.val(keyword);
                    return false;
                }
            }
        });

        //表单提交
        this.form = $('#search_form');
        this.form.bind('submit', this.submit);
        $('#btn_submit_search_form').bind('click', this.submit);

        //空白处点击隐藏suggestion
        $('#search_suggestion_box').bind('click', function (e) {
            $('#search_suggestion_box').show();
            e.stopPropagation();
        });
        $(document).bind('click', function () {
            $('#search_suggestion_box').hide();
        });
    },
    'change_input_status': function (e) {
        if ((e.type == 'focus' || e.type == 'click') && zypc.search.keyword_input.val() != '') {
            if ($('#search_suggestion_box li').length > 0) {
                $('#search_suggestion_box').show();
                e.stopPropagation();
            } else {
                zypc.search.suggestion();
                e.stopPropagation();
            }
        } else {
        }
    },
    'submit': function () {
        if (zypc.search.keyword_input.val() != '') {
            window.location.href = zypc.search.form.attr('action') + '&keyword=' + zypc.search.keyword_input.val();
        }
    },
    'suggestion': function () {
        var kw = zypc.search.keyword_input.val();
        if (kw == '') {
            $('#search_suggestion_box').hide();
            return;
        }
        $.get(zypc.search.suggestion_url(encodeURIComponent(kw)), function (ret) {
            if (ret.keywords) {
                if (zypc.search.keyword_input.val() == kw) {
                    $("#search_suggestion_box").html(ret.keywords).show();
                    $("#search_suggestion_box li").bind({
                        'click': function () {
                            zypc.search.keyword_input.val($(this).text());
                            zypc.search.form.submit();
                        },
                        'mouseover': function () {
                            $(this).addClass('hover').siblings('li').removeClass('hover');
                        }
                    });
                }
            }
        });
    }
};
//搜索end

//登录状态
function load_login_status() {
    var url = 'index.php?ca=Common.AjaxUserInfo&v=2&t=' + (new Date().getTime());
    $.get(url, function(res) {
        if(res == '') {
            $('#login_box').hide();
            $('#unlogin_box').show()
        } else {
            $('#unlogin_box').hide();
            $('#login_box').show().find('i:eq(0)').html(res);
        }
    });
    $('#login_box').bind({
        'mouseover': function () {
            $(this).find('ul').show();
        },
        'mouseout': function () {
            $(this).find('ul').hide();
        }
    }).find('li').bind({
        'mouseover': function () {
            $(this).addClass('hover');
        },
        'mouseout': function () {
            $(this).removeClass('hover');
        }
    });
}
//登录状态 end

zypc.btn_back_top = {
    'content_width': 1000,
    'init': function () {
        var btn = $('#btn_back_top');
        var self = this;
        $(window).bind({
            'resize': function () {
                btn.css('left', self.getLeft());
            },
            'scroll': function () {
                var top = $(window).scrollTop();
                if (top > 0) {
                    btn.css('left', self.getLeft());
                    self.btnShow();
                } else {
                    self.btnHide();
                }
            }
        });
        btn.bind('click', function () {
            $('body, html').animate({scrollTop: 0}, 200);
        });
    },
    'getLeft': function () {
        var b_width = $(window).width();
        return (b_width + this.content_width)/2 + 20;
    },
    'btnHide': function () {
        $('#btn_back_top').stop(false, true).fadeOut(300);
    },
    'btnShow': function () {
        $('#btn_back_top').fadeIn(300);
    }
};

(function (zypc){

    $(document).ready(function () {

        //footer限制位置
        $('.v1_foot').css({'marginTop': function () {
            var $top = $(this).position().top;
            var $height = $(window).height();
            return $height - $top > 0 ? $height - $top : 0;
        }});

        load_login_status();

        zypc.search.init();

        zypc.btn_back_top.init();

        //登录、注册返回地址，需要两层urlencode
        $('#unlogin_box').parent('a').attr('href', function () {
            this.href += '&goUrl=' + encodeURIComponent(encodeURIComponent(window.location.href));
        });

    });

})(zypc);