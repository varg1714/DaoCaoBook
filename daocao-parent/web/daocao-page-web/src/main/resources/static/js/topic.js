
layer.msg_topic = layer.msg;

layer.msg = function (msg, json) {
    json = json ? json : {shift: 8, time: 2000};
    layer.msg_topic(msg, json);
};

var topic = {
    cid: '', tid: '', pid: '', to: '', is_join: 0, load_status: '', send_type: '', content: '',
    has_fu_text: false,
    btn_form_submit: null,
    has_new_login_mod: false,
    has_new_form_mod: false,
    comment_type: 'circle',
    url: function () {
        return 'index.php?ca=Sns_Topic.' + (topic.send_type == 'reply' ? 'Reply' : 'AddTopic');
    },
    //发帖
    add: function (cid, has_fu_text) {
        topic.cid = cid;
        topic.send_type = 'circle';
        topic.has_fu_text = has_fu_text;
        topic.form();
    },
    //编辑帖
    edit: function (cid, tid, has_fu_text, content, source) {
        topic.cid = cid;
        topic.tid = tid;
        topic.send_type = 'circle';
        topic.has_fu_text = has_fu_text;
        topic.content = content;
        topic.comment_type = source;
        topic.form(1);
    },
    //回复
    reply: function (cid, tid, pid, to) {
        topic.cid = cid;
        topic.tid = tid;
        topic.pid = pid;
        topic.to = to;
        topic.send_type = 'reply';
        topic.form();
    },
    //加入圈子提示
    join_confirm: function (callback) {
        var url = 'index.php?ca=Sns_Topic.ChangeJoin&type=join&cid=' + topic.cid;
        var msg = '加入圈子才能发言哦！';
        layer.msg(msg, {
            time: 0, shift: 5, btn: ['加入', '取消'],
            yes: function(index){
                //layer.close(index);
                topic.join('join', topic.cid, callback);
            }
        });
    },
    //登录
    login: function () {
        layer.closeAll();
        //检查登录
        var html = '<div class="comTan" id="unlogin_msg_view" style="display: block;"><a class="close"> <img src="static/images/book/close.jpg" alt=""/></a>未登录,请<a style="color: red;" onclick="$(this).attr(\'href\', $(\'#noLoginUserBox a:eq(0)\').attr(\'href\'));">登录</a>后发起操作</div>';
        if (1 || topic.has_new_login_mod) {  //新登录提示
            html = '<div class="noLogin" style="display: block;" id="unlogin_msg_view">\
                        <span class="off"><s></s></span>\
                        <p>未登录，请登录后操作</p>\
                        <span class="loginBtn">登录</span>\
                    </div>';
        }
        layer.open({
            type: 1, shift: 5, title: false, closeBtn: false, shadeClose: true,
            content: html,
            success: function (obj, index) {
                $('#unlogin_msg_view a:eq(0), #unlogin_msg_view .off').unbind('click').bind('click', function () {
                    layer.close(index);
                });
                $('#unlogin_msg_view .loginBtn').unbind('click').bind('click', function () {
                    $('#unlogin_box').click();
                });
            }
        });
    },
    //表单
    form: function (edit) {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            topic.login();
            return;
        }
        if (topic.is_join != 1) {
            topic.join_confirm(1);
            return;
        }
        var obj_tmp_comment_form = $('#tmp_comment_form');
        var tmp_comment_form = obj_tmp_comment_form.clone();
        //改版
        if (topic.has_new_form_mod) {
            var html = '<div class="pubTan" id="comment_form" style="display: block;overflow: hidden;">' + tmp_comment_form.html() + '</div>';
            layer.open({
                type: 1, shift: 5, title: false, closeBtn: false, move: false, fix: false,
                content: html,
                area: [obj_tmp_comment_form.width(), obj_tmp_comment_form.height()],
                success: function (obj, index) {
                    //关闭
                    $('#comment_form .off').unbind('click').bind('click', function () {
                        layer.close(index);
                    });
                    //富文本
                    if (topic.has_fu_text == 1) {
                        var layer_msg = layer.msg('初始化编辑器，请稍等...', {shift: 5, shade: 0.1});
                        $('#comment_form iframe').attr('src', function () {
                            return $(this).attr('_src');
                        }).bind('load', function () {
                            setTimeout(function () {
                                layer.close(layer_msg);
                            }, 500);
                            topic.init_edit_table();
                        });
                    }
                    //切换
                    $('#comment_form [name="comment_type"]').unbind('click').bind('click', function () {
                        $(this).addClass('disCur').siblings('span').removeClass('disCur');
                        if ($(this).data('value') == 'comment') {
                            topic.comment_type = 'comment';
                            $('#comment_form .comStar').show();
                            if (topic.has_fu_text) {
                                $('#comment_form iframe').hide();
                            }
                            $('#comment_form textarea[name="comment_txt"]').show().addClass('comTxt');
                        } else {
                            topic.comment_type = 'circle';
                            $('#comment_form .comStar').hide();
                            if (topic.has_fu_text) {
                                $('#comment_form iframe').show();
                                $('#comment_form textarea[name="comment_txt"]').hide();
                            } else {
                                $('#comment_form textarea[name="comment_txt"]').show().removeClass('comTxt');
                            }
                        }
                    });
                    topic.bind_comment_vote();
                }
            });
        } else {
            //表单层
            var layer_obj = layer.open({
                type: 1, fix: false, move: false, shift: 5,
                title: topic.send_type == 'reply' ? '回复' : '发布',
                area: [obj_tmp_comment_form.width(), obj_tmp_comment_form.height()],
                content: '<div class="comTan" style="display: block" id="comment_form">' + tmp_comment_form.html() + '</div>',
                success: function () {
                    if (topic.has_fu_text == 1) {
                        var layer_msg = layer.msg('初始化编辑器，请稍等...', {shade: 0.1});
                        $('#comment_form iframe').attr('src', function () {
                            return $(this).attr('_src');
                        }).bind('load', function () {
                            setTimeout(function () {
                                layer.close(layer_msg);
                            }, 500);
                            topic.init_edit_table();
                        });
                    }
                },
                cancel: function () {
                    layer.closeAll('tips');
                }
            });
        }
        topic.btn_form_submit = $('#comment_form [name="comment_submit"]');
        if (edit) {
            topic.btn_form_submit.text('保存修改');
        }
        topic.btn_form_submit.css('cursor', 'pointer').bind('selectstart', function () {return false;}).unbind('click').bind('click', function () {
            if ($(this).hasClass('disbled')) {
                return false;
            }
            var comment_form = $('#comment_form');
            var comment_type = topic.comment_type;
            var comment_txt_obj = comment_form.find('textarea[name="comment_txt"]');
            var content = comment_txt_obj.val();
            var pics = [], raw = '';
            if (topic.has_fu_text == 1) {
                comment_txt_obj = topic.iframe_edittable.view_contenteditable;
                content = topic.iframe_edittable.get_content();
                pics = content.pics.join('|');
                if (content.pics.length > topic.pic_max_number) {
                    topic.comment_form_msg('最多可以上传20张图片，请重新编辑。');
                    return false;
                }
                raw = content.raw;
                content = content.txt;
            }
            var defaultValue = comment_txt_obj.attr('_title');
            if (content == '' || defaultValue == content || defaultValue == raw) {
                topic.comment_form_msg('写点什么吧');
                //layer.tips('写点什么吧', comment_txt_obj, {tips: [3, '#d88049'], tipsMore: false, time: 3000});
                comment_txt_obj.focus();
                return false;
            }
            var vote = $('#comment_form input[name="comment_vote"]').val();
            var cid = topic.cid;
            var tid = topic.tid;
            var pid = topic.pid;
            var to = topic.to;
            var name = comment_form.find('input[name="comment_name"]').val();
            if (name == comment_form.find('input[name="comment_name"]').attr('title')) {
                name = '';
            }
            layer.closeAll('tips');
            $('#comment_form .remind').hide();
            var layer_msg = layer.msg('发布中，请稍等...', {shift: 5, shade: 0.1});
            $.post(topic.url(), {'cid': cid, 'tid': tid, 'pid': pid, 'comment_type': comment_type, 'to': to, 'name': name, 'content': content, 'pics': pics, 'vote': vote},
                function (data, state) {
                    layer.close(layer_msg);
                    if (data.flag == 'content') {
                        layer.tips(data.info, comment_txt_obj, {shift: 5, tips: [3, '#d88049'], tipsMore: false, time: 3000});
                    } else if (data.flag == 'join') {
                        layer.msg(data.info);
                    } else if (data.flag == 'login') {
                        topic.login();
                    } else {
                        if (data.code == 0) {
                            layer.closeAll();
                            layer.msg('发布成功!', {shade: 0.1, time: 100000});
                            setTimeout('window.location.reload();', 1000);
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                },
                'json'
            );
        });
    },
    //点赞
    like: function () {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            topic.login();
            return;
        }
        var $this = $(this);
        if ($this.data('status') == 'loading') return;
        $this.data('status', 'loading');
        var tid = $this.data('tid');
        var cid = $this.data('cid');
        var liked = $this.data('liked');
        var avatar = $this.data('avatar');
        var num = $this.data('num');
        var url = 'index.php?ca=Sns_Topic.Like&tid=' + tid + '&cid=' + cid;
        if (liked == 0) {
            num ++;
            $this.data('liked', 1)
                .children('i').html(num).end()
                .children('img').attr('src', function () {
                    return $(this).attr('src').replace('zan', 'r_zan');
                });
        } else {
            num --;
            $this.data('liked', 0)
                .children('i').html(num == 0 ? '&nbsp;': num).end()
                .children('img').attr('src', function () {
                    return $(this).attr('src').replace('r_zan', 'zan');
                });
        }
        $this.data('num', num);
        $.get(url, function (ret) {
            $this.data('status', 'complate');
            if (ret.status == 1) {
                /*if (avatar) {
                    var has_user = 0;
                    $this.prevAll('div').children('span').each(function (i, span) {
                        if ($(span).children('img').attr('alt') == ret.userName) {
                            has_user = 1;
                        }
                    });
                    if (liked) {
                        if ($this.prevAll('div').children('span').length == 1 && has_user) {
                            $this.prevAll().hide(100);
                        } else {
                            $this.parent().find('img[alt="'+ret.userName+'"]').parent().hide(100);
                        }
                    } else {
                        if (has_user) {
                            $this.prevAll('div').prepend($this.parent().find('img[alt="'+ret.userName+'"]').parent().show(100));
                        } else {
                            var new_avatar = $('<img></img>');
                            new_avatar.attr('src', ret.user_avatar ? ret.user_avatar : 'static/images/common/touxiang.png');
                            new_avatar.attr('alt', ret.userName);
                            $this.prevAll('div').prepend($('<span></span>').append(new_avatar).show(100)).show();
                        }
                        $this.prevAll().show(100).end().prevAll('em');
                    }
                    $this.prevAll('em').children('font').html(ret.like_num);
                } else {
                    //$this.children('em').html(ret.like_num);
                }*/
            } else if (ret.status == -1) {
                topic.login();
                return;
            } else {
                layer.msg(ret.msg);
            }
        });
    },

    join: function (type, cid, callback) {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            topic.login();
            return;
        }
        if (topic.load_status == 'loading') return;
        topic.load_status = 'loading';
        var url = 'index.php?ca=Sns_Topic.ChangeJoin&type=' + type + '&cid=' + cid;
        //var layer_msg = layer.msg('请稍后...', {time: 1000});
        $.get(url, function (ret) {
            //layer.close(layer_msg);
            topic.load_status = 'complate';
            if (ret.status == -1) {
                //layer.close(layer_msg);
                topic.login();
                return;
            }
            $('a[btn="join_circle"][data-type="'+(type=='join'?'quit':'join')+'"]').show();
            $('a[btn="join_circle"][data-type="'+type+'"]').hide();
            topic.is_join = type == 'join' ? 1 : 1;
            if (type == 'join') {
                layer.msg('已加入圈子', {time: 1000});
            } else {
                layer.msg('已退出圈子', {time: 1000});
            }
            callback && topic.form();
        });
    },

    bind: function () {
        //加入圈子
        $('a[btn="join_circle"]').click(function () {
            var type = $(this).data('type');
            var cid = $(this).data('cid');
            topic.join(type, cid);
        });

        //评论
        $('[btn="topic_add"]').click(function () {
            var $this = $(this);
            var cid = $this.data('cid');
            var has_fu_text = $this.data('has_fu_text');
            topic.add(cid, has_fu_text);
        });

        $('#btn_topic_edit').on('click', function () {
            var $this = $(this);
            var cid = $this.data('cid');
            var tid = $this.data('tid');
            var has_fu_text = $this.data('has_fu_text');
            var source = $this.data('source');
            var content = $this.find('textarea').val();
            topic.edit(cid, tid, has_fu_text, content, source);
        });

        //点赞
        $('a[btn="like"]').live('click', topic.like);

        //投票
        $('a[btn="topic_vote"]').on('click', topic.vote);

        //收藏
        $('a[btn="topic_collect"]').on('click', topic.collect);

        //举报
        $('a[btn="topic_jubao"]').on('click', topic.jubao);

        //评分
        $('span[btn="topic_pingfen"]').on('click', topic.pingfen);

        //分享
        $('a[btn="topic_share"]').on('click', topic.share);

        //删除
        $('a[btn="topic_delete"]').on('click', topic.delete);

        //回复
        $('a[btn="reply"]').click(function () {
            var $this = $(this);
            var cid = $this.data('cid');
            var tid = $this.data('tid');
            var pid = $this.data('pid');
            var to = $this.data('to');
            topic.reply(cid, tid, pid, to);
        });

        topic.bind_reply();

        //hover
        $('.circle li, .comment li').bind({
            'mouseout': function () {
                $(this).removeClass('hover');
            },
            'mouseover': function () {
                $(this).addClass('hover');
            }
        });
    },

    iframe_edittable: null, btn_insert_pic: null, pic_upload_url: null, pic_max_number: 20, pic_max_size: 500,
    init_edit_table: function () {
        var iframe = $('#comment_form iframe');
        topic.btn_insert_pic = $('#comment_form .btn_insert_pic');
        topic.iframe_edittable = iframe[0].contentWindow.edittable;
        topic.iframe_edittable.init_edit_table();
        topic.pic_upload_url = topic.btn_insert_pic.data('upload_url');
        topic.pic_uploading_img = topic.btn_insert_pic.data('loading_img');
        topic.pic_upload_fail_img = topic.btn_insert_pic.data('fail_img');
        if (typeof(WebUploader) != undefined) {
            topic.bind_upload();
        }
        topic.iframe_edittable.insert_text(topic.content);
    },

    edit_table_img_length: function () {
        return topic.iframe_edittable.view_contenteditable.find('img').length;
    },

    bind_upload: function () {
        var $swf_url = 'static/js/webuploader/Uploader.swf', $upload_url = topic.pic_upload_url;

        topic.iframe_edittable.view_contenteditable.bind('DOMNodeInserted', function () {
            //topic.check_edit_table_img_length();
        });

        //console.log(topic.view_contenteditable.selector);
        var uploader = WebUploader.create({
            auto: true,
            swf: $swf_url,
            server: $upload_url,
            pick: {
                id: topic.btn_insert_pic.selector,
                multiple: false
            },
            resize: false,
            //runtimeOrder: 'flash',
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/jpg,image/jpeg,image/png,image/bmp,image/gif'
            }
        });
        uploader.on('beforeFileQueued', function(file) {
            if (topic.edit_table_img_length() >= topic.pic_max_number) {
                layer.msg('最多可以上传20张图片。', {shade: false});
                return false;
            }
            if (file.size > topic.pic_max_size*1024) {
                layer.msg('图片大小请控制在500KB以内。', {shade: false});
                return false;
            }
        });
        uploader.on('uploadStart', function(file) {
            var text = '<div><img src="' + topic.pic_uploading_img + '" mod="loading" fid="' + file.id + '"/></div>';
            topic.iframe_edittable.insert_text(text);
            topic.btn_insert_pic.addClass('disbled');
            topic.btn_form_submit.addClass('disbled');
            topic.iframe_edittable.disable_view(1);
        });
        uploader.on('uploadSuccess', function(file, response) {
            if (response.code == 100) {
                var src = response.raw;
                var small_url = response.small_url;
                topic.iframe_edittable.replace_pic(file.id, small_url, src);
                topic.btn_insert_pic.removeClass('disbled');
                topic.btn_form_submit.removeClass('disbled');
            } else {
                var error = response.raw;
                layer.msg(error, {shade: false});
                topic.iframe_edittable.upload_fail(file.id, topic.pic_upload_fail_img);
            }
        });
        uploader.on('uploadError', function(file) {
            topic.iframe_edittable.upload_fail(file.id, topic.pic_upload_fail_img);
        });
        uploader.on('uploadComplete', function() {
            uploader.reset();
            topic.iframe_edittable.disable_view(0);
        });
    },

    comment_form_msg: function (msg) {
        if (topic.has_new_form_mod) {
            $('#comment_form .remind').show().find('i').html(msg);
        } else {
            layer.msg(msg, {shade: false});
        }
    },

    bind_comment_vote: function () {
        var star_src = $('#comment_form [mod="comment_vote"]').data('star');
        $('#comment_form [mod="comment_vote"] img').css('cursor', 'pointer').bind({
            'mouseover': function () {
                $(this).attr('src', function () {
                    return star_src.replace('(star)', 'yStar');
                }).prevAll('img').attr('src', function () {
                    return star_src.replace('(star)', 'yStar');
                }).end().nextAll('img').attr('src', function () {
                    return star_src.replace('(star)', 'gStar');
                });
            },
            'mouseout': function () {
                var vote = $('#comment_form input[name="comment_vote"]').val();
                $(this).parent().find('img:eq(' + (vote - 1) + ')').mouseover();
            },
            'click': function () {
                var index = $(this).index();
                $('#comment_form input[name="comment_vote"]').val(index + 1);
            }
        });
    },

    //举报
    jubao: function () {
        var cid = $(this).data('cid');
        var tid = $(this).data('tid');
        var mod = $('div[mod="topic_jubao"]').clone();
        var div = $('<div></div>');
        div.append(mod).children().css('display', 'block');
        function submit_jubao() {
            var btn = this;
            if ($(btn).attr('status') == 'loading') return;
            $(btn).attr('status', 'loading');
            var kind = 1;
            $(btn).parent().find('li').each(function () {
                if ($(this).find('span').hasClass('click')) {
                    kind = $(this).index() + 1;
                    return false;
                }
            });
            var desc = $(btn).prev().val();
            var layer_loading = layer.load();
            $.post('index.php?ca=Sns_Topic.Jubao', {'cid': cid, 'tid': tid, 'kind': kind, 'desc': desc},
                function (ret, state) {
                    $(btn).attr('status', 'complete');
                    layer.closeAll();
                    if (ret.status == 1) {
                        layer.msg('已举报');
                        //setTimeout('window.location.reload();', 1000);
                    } else if (ret.status == -1) {
                        topic.login();
                    } else {
                        layer.msg(ret.msg);
                    }
                },
                'json'
            );
        }
        layer.open({
            type: 1, shift: 5, title: false, closeBtn: false, move: false, fix: false,
            area: ['666px', '503px'],
            content: div.html(),
            success: function (obj, index) {
                $(obj).find('.hd s').on('click', function () {
                    layer.close(index);
                }).end().find('li').on('click', function () {
                    $(this).find('span').addClass('click').end().siblings().find('span').removeClass('click');
                    if ($(this).index() == 5) {
                        $(obj).find('textarea').focus();
                    }
                }).end().find('.jbbtn').on('click', submit_jubao);
            }
        });
    },

    //评分
    pingfen: function () {
        var tid = $(this).data('tid');
        var mod = $('div[mod="topic_pingfen"]').clone();
        var div = $('<div></div>');
        div.append(mod).children().css('display', 'block');
        function submit_pingfen() {
            var btn = this;
            if ($(btn).attr('status') == 'loading') return;
            $(btn).attr('status', 'loading');
            var ul = $(btn).prev();
            var fluency = ul.find('select[name=fluency] :selected').val();
            var writing_style = ul.find('select[name=writing_style] :selected').val();
            var completeness = ul.find('select[name=completeness] :selected').val();
            var creative = ul.find('select[name=creative] :selected').val();
            var remark = ul.find('input[name=remark]').val();
            var layer_loading = layer.load();
            $.post('index.php?ca=Sns_Topic.Pingfen', {'tid': tid, 'fluency': fluency, 'writing_style': writing_style, 'completeness': completeness, 'creative': creative, 'remark': remark},
                function (ret, state) {
                    $(btn).attr('status', 'complete');
                    layer.closeAll();
                    if (ret.status == 1) {
                        layer.msg('已评分');
                        setTimeout('window.location.reload();', 1000);
                    } else if (ret.status == -1) {
                        topic.login();
                    } else {
                        layer.msg(ret.msg);
                    }
                },
                'json'
            );
        }
        layer.open({
            type: 1, shift: 5, title: false, closeBtn: false, move: false, fix: false,
            area: ['395px', '280px'],
            content: div.html(),
            success: function (obj, index) {
                $(obj).find('.hd s').on('click', function () {
                    layer.close(index);
                }).end().find('.fzbtn').on('click', submit_pingfen);
            }
        });
    },

    //删除
    delete: function () {
        var cid = $(this).data('cid');
        var tid = $(this).data('tid');
        var mod = $('div[mod="reply_delete"]').clone();
        var div = $('<div></div>');
        div.append(mod).children().css('display', 'block').html(function () {
            return $(this).html().replace('该条回复', '');
        });
        function submit_delete() {
            var btn = this;
            if ($(btn).attr('status') == 'loading') return;
            $(btn).attr('status', 'loading');
            var layer_loading = layer.load();
            $.post('index.php?ca=Sns_Topic.TopicDelete', {'tid': tid},
                function (ret, state) {
                    $(btn).attr('status', 'complete');
                    layer.closeAll();
                    if (ret.status == 1) {
                        layer.msg('已删除');
                        setTimeout("window.location.href = 'index.php?ca=Sns_Topic.Index&cid=" + cid + "';", 1000);
                    } else if (ret.status == -1) {
                        topic.login();
                    } else {
                        layer.msg(ret.msg);
                    }
                },
                'json'
            );
        }
        layer.open({
            type: 1, shift: 5, title: false, closeBtn: false, move: false, fix: false,
            content: div.html(),
            success: function (obj, index) {
                $(obj).find('s,.qx').on('click', function () {
                    layer.close(index);
                }).end().find('.sc').on('click', submit_delete);
            }
        });
    },

    //分享
    share: function () {
        var mod = $('div[mod="topic_share"]');
        var div = $('<div></div>');
        div.append(mod.clone()).children().css('display', 'block');
        layer.open({
            type: 1, shift: 5, title: false, closeBtn: false, move: false, fix: false,
            area: [mod.width() + 20 + 'px', mod.height() + 20 + 'px'],
            content: div.html(),
            success: function (obj, index) {
                $(obj).find('s').on('click', function () {
                    layer.close(index);
                }).end().find('img:eq(0)').attr('src', function () {
                    return $(this).data('src');
                });
                $(obj).find('a[btn="topic_share_tan"]').on('click', function (event) {
                    var code = $(this).data('code');
                    var turl = $(this).data('turl');
                    var url = $(this).parent().parent().data('url');
                    var pic = $(this).parent().parent().data('pic');
                    var title = $(obj).find('textarea:eq(0)').val();
                    var desc = $(obj).find('textarea:eq(1)').val();
                    var p = {};
                    if (code == 'qzone' || code == 'qq') {
                        p = {url: url, desc: title, summary: desc, title: title, site: '掌阅iReader书城', pics: pic, style: '202', width: 105, height: 31};
                    }
                    else {
                        var txt = '#首届掌阅文学创作大赛# 【' + title + '】 ' + desc + ' @掌阅iReader';
                        p = {appkey: 2119043080, url: url, title: txt};
                    }
                    var s = [];
                    for (var i in p) {
                        s.push(i + '=' + encodeURIComponent(p[i] || ''));
                    }
                    var $url = turl + '?' + s.join('&');
                    var height = 600, width = 800, top = window.screen.height / 2 - height / 2 - 30, left = window.screen.width / 2 - width / 2;
                    window.open($url, 'share_' + code, 'height=' + height + ', width=' + width + ', top=' + top + ', left=' + left + ', toolbar=no, menubar=no, scrollbars=no');
                });
            }
        });
    },

    //投票
    vote: function () {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            topic.login();
            return;
        }
        var $this = $(this);
        if ($this.data('status') == 'loading') return;
        $this.data('status', 'loading');
        var tid = $this.data('tid');
        var num = $this.data('num');
        if ($this.hasClass('ytp')) {
            return false;
        }
        var layer_loading = layer.load();
        var url = 'index.php?ca=Essaycontest.Vote&topic_id=' + tid;
        $.get(url, function (ret) {
            $this.data('status', 'complate');
            layer.closeAll();
            if (ret.status == 1 || ret.msg.indexOf('已投票过') != -1) {
                var txt = '已投票';
                if ($this.data('num') != undefined) {
                    txt += '(' + (ret.status == 1 ? num + 1 : num) + ')';
                }
                $this.addClass('ytp').html(txt);
            } else if (ret.status == -1) {
                topic.login();
                return;
            } else {
                layer.msg(ret.msg);
            }
        });
    },

    //收藏
    collect: function () {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            topic.login();
            return;
        }
        var $this = $(this);
        if ($this.data('status') == 'loading') return;
        $this.data('status', 'loading');
        var tid = $this.data('tid');
        var act = 'add';
        if ($this.hasClass('ysc')) {
            act = 'del';
            $this.removeClass('ysc').children('img').attr('src', function () {
                return $(this).attr('src').replace('r_sc', 'sc');
            });
        } else {
            $this.addClass('ysc').children('img').attr('src', function () {
                return $(this).attr('src').replace('sc', 'r_sc');
            });
        }
        var url = 'index.php?ca=Sns_Topic.Collect&topic_id=' + tid + '&act=' + act;
        $.get(url, function (ret) {
            $this.data('status', 'complate');
            if (ret.status == 1) {
            } else if (ret.status == -1) {
                topic.login();
                return;
            } else {
                layer.msg(ret.msg);
            }
        });
    },

    bind_reply: function () {
        //huifu
        $('span[btn="topic_reply"]').on('click', function () {
            var reply_view = $(this).parent().next();
            reply_view.show().find('a[btn="concel_topic_reply"]').on('click', function () { //取消
                reply_view.hide();
            }).end().find('textarea').focus();
        });
        //发布
        $('a[btn="submit_topic_reply"]').on('click', function () {
            var btn = this;
            var cid = $(btn).data('cid');
            var tid = $(btn).data('tid');
            var pid = $(btn).data('pid');
            var to = $(btn).data('to');
            var type = $(btn).data('type');
            var reply_id = $(btn).data('reply_id');
            var txt = '', default_txt = '', encode = 0;
            if (type == 'footer') {
                var eidtor = UE.getEditor('editor_content');
                txt = eidtor.getContent();
            } else {
                txt = $(btn).parent().prev().val();
                encode = 1;
            }
            var url = $(btn).data('url');
            topic.submit_reply(cid, tid, txt, default_txt, pid, to, reply_id, encode, url);
        });
        //删除
        $('a[btn="reply_delete"]').on('click', function () {
            var tid = $(this).data('tid');
            var rid = $(this).data('rid');
            var mod = $('div[mod="reply_delete"]').clone();
            var div = $('<div></div>');
            div.append(mod).children().css('display', 'block');
            function submit_delete() {
                var btn = this;
                if ($(btn).attr('status') == 'loading') return;
                $(btn).attr('status', 'loading');
                var layer_loading = layer.load();
                $.post('index.php?ca=Sns_Topic.ReplyDelete', {'tid': tid, 'rid': rid},
                    function (ret, state) {
                        $(btn).attr('status', 'complete');
                        //layer.closeAll();
                        if (ret.status == 1) {
                            setTimeout('window.location.reload();', 1000);
                        } else if (ret.status == -1) {
                            topic.login();
                        } else {
                            layer.msg(ret.msg);
                        }
                    },
                    'json'
                );
            }
            layer.open({
                type: 1, shift: 5, title: false, closeBtn: false, move: false, fix: false,
                content: div.html(),
                success: function (obj, index) {
                    $(obj).find('s,.qx').on('click', function () {
                        layer.close(index);
                    }).end().find('.sc').on('click', submit_delete);
                }
            });
        });
        //编辑
        $('a[btn="reply_edit"]').on('click', function () {
            var content = $(this).prev().val();
            var reply_id = $(this).data('rid');
            $('#btn_submit_put_chapter').data('reply_id', reply_id);
            var editor = UE.getEditor('editor_content');
            editor.setContent(content);
            $('#concel_reply_edit').show();
            $('body, html').animate({scrollTop: $('#editor_content').offset().top}, 300);
        });
        //取消编辑
        $('#concel_reply_edit').on('click', function () {
            $('#btn_submit_put_chapter').data('reply_id', 0);
            $(this).hide();
            UE.getEditor('editor_content').setContent('');
        });
        //存草稿
        if ($('#btn_save_draft').length > 0) {
            $('#btn_save_draft').on('click', function () {
                topic.save_draft(true);
            });
        }
    },

    //存草稿
    save_draft: function (type) {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            if (type == true) {
                topic.login();
            }
            return;
        }
        var btn = $('#btn_save_draft');
        var cid = btn.data('cid');
        var tid = btn.data('tid');
        var eidtor = UE.getEditor('editor_content');
        var txt = eidtor.getContent();
        var editor_obj = $('#editor_content');
        if (type == true && (!eidtor.hasContents() || txt == '<p id="initContent">请输入内容</p>')) {
            layer.msg('写点什么吧', {shift: 8, time: 2000, fix: false, offset: [(editor_obj.offset().top-$(window).scrollTop()+editor_obj.height()/2-25)+'px', (editor_obj.offset().left+editor_obj.width()/2-50)+'px']});
            return false;
        }
        if (txt == '<p id="initContent">请输入内容</p>' || (type == false && topic.draft_prev_save_content == txt)) {
            return false;
        }
        if (topic.reply_submit_loading == 'loading' || topic.reply_submit_loading == 'complete') {
            return false;
        }
        topic.draft_prev_save_content = txt;
        if(btn.data('status') == 'loading' && type != 'save') return;
        btn.data('status', 'loading');
        $.post('index.php?ca=Sns_Topic.DraftSave', {'cid': cid, 'tid': tid, 'content': txt},
            function (ret) {
                btn.data('status', 'complete');
                if (type == true) {
                    layer.msg('已保存至草稿', {fix: false, offset: [(editor_obj.offset().top-$(window).scrollTop()+editor_obj.height()/2-25)+'px', (editor_obj.offset().left+editor_obj.width()/2-50)+'px']});
                }
            }
        );
    },

    //提交回复
    submit_reply: function (cid, tid, txt, default_txt, pid, to, reply_id, encode, url) {
        if (typeof(USER_IS_LOGIN) == undefined || USER_IS_LOGIN == 0) {
            topic.login();
            return;
        }
        var editor_obj = $('#editor_content');
        var _top = (editor_obj.offset().top-$(window).scrollTop()+editor_obj.height()/2-25)+'px';
        var _left = (editor_obj.offset().left + editor_obj.width()/2 - 50);
        if (txt == '' || txt == default_txt || txt == '<p id="initContent">请输入内容</p>') {
            if (encode) {
                layer.msg('写点什么吧');
            } else {
                layer.msg('写点什么吧', {shift: 8, time: 2000, fix: false, offset: [_top, _left + 'px']});
            }
            return;
        }
        if (topic.reply_submit_loading == 'loading') return;
        topic.reply_submit_loading = 'loading';
        var layer_loading = layer.load();
        topic.send_type = 'reply';
        if (pid) {
            topic.onlyowner = 0;
        }
        $.post(topic.url(), {'cid': cid, 'tid': tid, 'pid': pid, 'to': to, 'reply_id': reply_id, 'content': txt, 'encode': encode, 'isReverse': topic.isReverse, 'onlyowner': topic.onlyowner},
            function (ret, state) {
                topic.reply_submit_loading = 'complete';
                layer.close(layer_loading);
                if (ret.status == 1) {
                    if (encode) {
                        layer.msg('发布成功!');
                    } else {
                        layer.msg('发布成功!', {shade: 0.1, shift: 8, time: 100000, fix: false, offset: [(editor_obj.offset().top-$(window).scrollTop()+editor_obj.height()/2-25)+'px', _left + 'px']});
                    }
                    url += '&page=' + (reply_id > 0 ? topic.current_page : ret.max_page);
                    setTimeout('window.location.href = "' + url + '&mt=' + Math.random() + '#reply_id_' + ret.reply_id + '";', 2000);
                } else if (ret.status == -1) {
                    topic.login();
                } else {
                    if (encode) {
                        layer.msg(ret.msg);
                    } else {
                        layer.msg(ret.msg, {shift: 8, time: 2000, fix: false, offset: [(editor_obj.offset().top-$(window).scrollTop()+editor_obj.height()/2-25)+'px', (_left-50) + 'px']});
                    }
                }
            },
            'json'
        );
    }
};
