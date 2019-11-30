$("#head").load("head.html");
var zongye = "";
var myres; //全局参数
var page = 1;
var pageType = -1;

function xiaoxi(pageNumber, pageSize, tai) {

    // var url = "/zlead/sys/message/notice/" + pageNumber + "/" + pageSize + "/" + tai + "";
    var url = "/zlead/sys/message/notice/" + encodeURI(pageNumber) + "/" + encodeURI(pageSize) + "/" + encodeURI(tai) + "?t_=" + Math.random();

    $.ajax({
        type: "get",
        url: url,
        data: {},
        dataType: "json",
        async: false,
        success: function(res) {
            myres = res;
            if (res.code == 1) {
                if (res.data == '无系统消息') {
                    $('.xiaoxi_zero').show()
                    $('.xiaoxi').hide()
                } else {
                    $('.xiaoxi_zero').hide();
                    $('.xiaoxi').show();
                    var maxPage = Math.ceil(res.data.count / 20);
                    createPage(tai, maxPage);
                    if (pageNumber < maxPage) {
                        $('.xiaoxi_left').children('div').show();
                    } else {
                        $('.xiaoxi_left').children('div').hide();
                    }
                }
            } else {
                alert("请先登录！");
            }
        }
    });
}
xiaoxi(1, 20, pageType);

// 渲染页面

function createPage(type, maxPage) {

    // 开始渲染消息数据：
    var str1 = $('.xiaoxi_left').children('ul').html();
    var str = template('xiaoxi_left', { list: myres.data.data, type: type });
    $('.xiaoxi_left').children('ul').html(str1 + str);

    if ($('.xiaoxi_left').find('li').length == 0) {
        $('.xiaoxi_zero').show()
        $('.xiaoxi').hide()
    } else {
        $('.xiaoxi_zero').hide()
        $('.xiaoxi').show()
    }

    // 绑定事件：
    $('.xiaoxi_left').find('li').click(function() {
        if ($(this).hasClass('xiaoxi_active')) {
            $(this).removeClass('xiaoxi_active')
            $('.xiaoxi_right').hide()
        } else {
            // $(this).addClass('xiaoxi_active').siblings('.xiaoxi_active').remove();
            // $('.xiaoxi_right').show().children('h5').html($(this).children('p').html())


            if (type == 0) {
                $(this).addClass('xiaoxi_active').siblings('.xiaoxi_active').remove();
                for (var i = 0; i < myres.data.data.length; i++) {
                    if (myres.data.data[i].id == $(this).attr('myid')) {
                        myres.data.data[i].status = 1;
                    }
                }
                // 此处请求后台数据，更改消息的状态：参数：id=$(this).attr('myid')
                $.ajax({
                    type: "get",
                    url: "/zlead/sys/message/update/" + $(this).attr('myid'),
                    // data: JSON.stringify({ id: $(this).attr('myid') }),
                    dataType: "json",
                    async: false,
                    success: function(res) {}
                });
            }
        }
        if (type == 0) {
            if ($(this).attr('myclick') == 1) {
                //修改头部消息栏总数量-1 
                myres.data.count--;
                if (myres.data.count > 99) {
                    $('#ss1').html('99+')
                } else if (myres.data.count <= 0) {
                    $('#ss1').hide()
                } else {
                    $('#ss1').html(myres.data.count)
                }
                $(this).attr('myclick', 999)
            }
        }
    })
}


if (zongye <= 1) {} else {
    $('#page2').paging({
        pageNo: 1,
        totalPage: zongye,
        callback: function(cur) {
            xiaoxi(cur, 20, -1);
        }
    });
}
// 全部消息
$("#quanx").click(function() {
    page = 1;
    pageType = -1;
    $('.xiaoxi_left').children('ul').html('');
    xiaoxi(page, 20, pageType);
});
// 已读消息
$("#yix").click(function() {
    page = 1;
    pageType = 1;
    $('.xiaoxi_left').children('ul').html('');

    xiaoxi(page, 20, pageType);

});
// 未读消息
$("#weix").click(function() {
    page = 1;
    pageType = 0;
    $('.xiaoxi_left').children('ul').html('');
    xiaoxi(page, 20, pageType);

});
// 加载更多
$('.xiaoxi_left').children('div').click(function() {
    $(this).unbind();
    page = page + 1;
    xiaoxi(page, 20, pageType);
})

$('.neirou_top').children('span').click(function() {
    $(this).addClass('active').siblings('span').removeClass('active')
})