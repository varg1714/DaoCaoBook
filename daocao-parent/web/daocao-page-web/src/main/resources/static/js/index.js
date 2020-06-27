if (typeof zypc == 'undefined') {
    zypc = {};
}
(function (zypc) {

    //轮播
    zypc.recommend_book_lunbo = {
        'currentLI': 0,
        'bind': function () {
            //轮播小图hover
            $('#recommend_book_lunbo_ul li').bind({
                'mouseover': function () {
                    zypc.recommend_book_lunbo.toggleLi(1, $(this));
                }
            }).eq(0).mouseover();
            //轮播
            $('#recommend_book_lunbo').find('.btnLeft,.btnRight').bind({'click': this.togglePage, 'selectstart': function () {return false;}});
            //自动轮播
            zypc.recommend_book_lunbo.autoLB();
            //停止
            $('#recommend_book_current_book, #recommend_book_lunbo').bind({
                'mouseover': function () {
                    zypc.recommend_book_lunbo.autoLBstop = 1;
                },
                'mouseout': function () {
                    zypc.recommend_book_lunbo.autoLBstop = 0;
                }
            });
            //
            $max = Math.floor($('#recommend_book_lunbo_ul li').length/4);
            for (var i = 0; i < $max; i ++) {
                $li = $('<li></li>');
                $('#recommend_book_lunbo ol').append($li);
                if (i == 0) $li.addClass('cirOn');
            }
            $('#recommend_book_lunbo ol li').bind('click', function () {
                var $page = $(this).index();
                $('#recommend_book_lunbo_ul').data('page', $page + 2);
                $('#recommend_book_lunbo .btnLeft').click();
            });
        },
        'toggleLi': function (big, li) {
            if (big == 1) {
                zypc.recommend_book_lunbo.toggleLiTimeOut && clearTimeout(zypc.recommend_book_lunbo.toggleLiTimeOut);
                zypc.recommend_book_lunbo.toggleLiTimeOut = setTimeout(function () {
                    li.find('img').stop(true).animate({'height': 168, 'width': 126, 'marginTop': 0}, 200)
                        .end().siblings().find('img').stop(true).animate({'height': 156, 'width': 117, 'marginTop': 5}, 200);
                    zypc.recommend_book_lunbo.toggleBig(li.index());
                }, 100);
            } else {
                //li.find('img').animate({'height': 156, 'width': 117, 'marginTop': 5}, 200);
            }
        },
        'autoLB': function () {
            setInterval(function () {
                if (zypc.recommend_book_lunbo.autoLBstop && zypc.recommend_book_lunbo.autoLBstop == 1) {
                    return false;
                }
                var $ul = $('#recommend_book_lunbo_ul');
                var page = $ul.data('page') || 1;
                var pageSize = 4;
                var maxLi = ((page - 1) * pageSize) + pageSize;
                var currentLi = zypc.recommend_book_lunbo.currentLI;
                currentLi ++;
                currentLi = currentLi >= maxLi ? maxLi - pageSize : currentLi;
                //zypc.recommend_book_lunbo.toggleBig(currentLi);
                var li = $('#recommend_book_lunbo_ul li:eq(' + currentLi + ')');
                zypc.recommend_book_lunbo.toggleLi(1, li);
            }, 2000);
        },
        'toggleBig': function (currentLI) {
            if (currentLI == zypc.recommend_book_lunbo.currentLI) {
                return;
            }
            zypc.recommend_book_lunbo.currentLI = currentLI;
            var $li = $('#recommend_book_lunbo_ul li:eq(' + currentLI + ')');
            var name = $li.find('img').attr('title');
            var href = $li.find('a').attr('href');
            var desc = $li.find('span:eq(0)').html();
            $('#recommend_book_current_book').fadeOut(100, function () {
                $(this).fadeIn(100)
                    .find('a').attr({'href': href, 'title': name}).end()
                    .find('i').html(name).end()
                    .find('p').html(desc);
            })
        },
        'togglePage': function () {
            var btn = $(this);
            var $ul = $('#recommend_book_lunbo_ul');
            var pageSize = 4;
            var maxPage = Math.floor($ul.find('li').length/pageSize);
            var page = $ul.data('page') || 1;
            var pageWidth = $ul.find('li.little:eq(0)').outerWidth(true) * pageSize;
            if (btn.data('btn') == 'next') {
                page ++;
            } else if (btn.data('btn') == 'prev') {
                page --;
            }
            var newPage = page < 1 ? maxPage : (page > maxPage ? 1 : page) ;
            $ul.data('page', newPage);
            pageWidth = pageWidth * (newPage - 1);
            //console.log(pageWidth + ' ' + page);
            $ul.css('position', 'absolute').fadeOut(100, function () {
                $(this).css('left', -pageWidth).fadeIn(300);
                $(window).scroll(); //不加这个图片出不来
            });//.stop(true).animate({'left': -pageWidth});
            $('#recommend_book_lunbo ol li:eq(' + (newPage - 1) + ')').addClass('cirOn').siblings('li').removeClass('cirOn');
            zypc.recommend_book_lunbo.toggleLi(1, $('#recommend_book_lunbo_ul li:eq(' + ((newPage - 1) * pageSize) + ')'));
        }
    };

    $(function () {

        //榜单toggle大小
        $('.showRight li').mouseover(function () {
            $(this).addClass('onShow').siblings('li').removeClass('onShow');
            $(window).scroll(); //不加这个图片出不来
        });

        zypc.recommend_book_lunbo.bind();
    });

})(zypc);