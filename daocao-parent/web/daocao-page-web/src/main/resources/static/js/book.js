if (typeof zypc == 'undefined') {
    zypc = {};
}
(function (zypc) {

    $(function () {


        //购买弹层
        $('[btn="buy_book"]').bind('click', function () {
            var mod_buy_book = $('[mod="buy_book"]');
            var width = mod_buy_book.width();
            var height = mod_buy_book.height();
            layer.open({type: 1, shift: 5, title: false, closeBtn: false, shadeClose: true,
                area: [width + 'px', height + 'px'],
                content: '<div class="buyTan" id="mod_buy_book" style="display: block">' + mod_buy_book.html() + '</div>',
                success: function (obj, index) {
                    $('#mod_buy_book .off s').unbind('click').bind('click', function () {
                        layer.close(index);
                    });
                }
            });
        });

        //扫一扫步骤提示
        $('[mod="look_phone_pic"], [mod="sao_step"]').bind({
            'mouseover': function () {
                zypc.timeout_sao_step_hide && clearTimeout(zypc.timeout_sao_step_hide);
                if ($(this).attr('mod') == 'sao_step') {
                    $('[mod="sao_step"]').show();
                } else {
                    zypc.timeout_sao_step_show = setTimeout(function () {
                        var btn = $('[mod="look_phone_pic"].look_ewm');
                        var mod_sao_step = $('[mod="sao_step"]');
                        var offsettop = btn.offset().top + btn.height();
                        var offsetleft = btn.offset().left - mod_sao_step.width() + btn.width();
                        mod_sao_step.css({position: "absolute", 'top': offsettop, 'left': offsetleft, 'z-index': 2}).fadeIn(200);
                    }, 200);
                }
            },
            'mouseout': function () {
                zypc.timeout_sao_step_show && clearTimeout(zypc.timeout_sao_step_show);
                zypc.timeout_sao_step_hide = setTimeout(function () {
                    $('[mod="sao_step"]').fadeOut(200);
                }, 200);
            }
        });


        //帖子内容展开更多
        $('[btn="content_show_more"]').bind('click', function () {
            var btn = $(this);
            if ($(this).is('img')) {
                btn = $(this).parent().parent().prev().find('a[btn="content_show_more"]:eq(0)');
            }
            var content = btn.nextAll('textarea').val();
            btn.prev('i').hide().end()
                .next('i').find('a').html(content).end().show().end()
                .parent().next().hide().end().end()
                .parent().parent().addClass('showAll')
                .next().find('.up').show()
                .end().find('[btn="content_hide_more"]').unbind('click').bind('click', function () {
                    $(this).hide();
                    btn.prev('i').show().end()
                        .next('i').hide().end()
                        .parent().next().show().end().end()
                        .parent().parent().removeClass('showAll')
                        .next().find('.up').hide().end();
                });
        });

        //看过的人还看 更多
        $('div[btn="relate_book_show_more"] span').bind('click', function () {
            var btn = $(this);
            var mod = $('div[mod="relate_book_list"]:eq(0)');
            var act = btn.hasClass('btnL') ? 'prev' : 'next';
            var ul = mod.children('ul');
            var li_width = ul.children('li:eq(0)').outerWidth(true);
            var li_length = ul.children('li').length;
            var page = ul.data('page') || 1;
            var max_page = Math.ceil(li_length/2);
            if (act != 'prev') {
                page += 1;
            } else {
                page -= 1;
            }
            console.log(max_page + ' ' + page + ' ' + li_width + ' ' + li_length);
            ul.css({'position': 'relative', 'width': li_length*li_width});
            if (page > 0 && page <= max_page) {
                ul.data('page', page).animate({'left': -(page-1)*2*li_width}, 200);
            }
            var disable_btn = page <= 1 ? '.btnL' : (page >= max_page ? '.btnR' : '');
            btn.parent().children('span').css('opacity', 1);
            if (disable_btn != '') {
                btn.parent().children(disable_btn).css('opacity', 0.5);
            }
        });

        //简介展开、收起
        $('a[btn="desc_show_more"]').bind('click', function () {
            if ($(this).hasClass('all')) {
                $(this).removeClass('all').html('展开<s></s>')
                    .parent().parent().prev().removeClass('showAll');
            } else {
                $(this).addClass('all').html('收起<s></s>')
                    .parent().parent().prev().addClass('showAll');
            }
        });

        //没有帖子情况下的按钮
        $('[btn="topic_add_empty"]').css('cursor', 'pointer').bind('click', function () {
            $('[btn="topic_add"]').click();
        });

        //购买
        $('[data-js=viewall]').on('click',function(){
            if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
                zypc.login_alert();
                return;
            }
            var getStartChapterUrl = $("#getStartChapterUrl").val();
            $.ajax({
                url: getStartChapterUrl,
                dataType: 'json',
                success: function(data) {
                    if(data.code == 1){
                        iframeUi.setSrc(data.body.goUrl);
                    } else{
                        alert(data.msg);
                    }
                },
                error: function() {
                    alert("网络繁忙，请稍后再试");
                }
            })
        })
    });

})(zypc);
