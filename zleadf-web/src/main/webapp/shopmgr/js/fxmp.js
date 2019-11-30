$(function(){
    var url = location.href.split('#').toString();//url不能写死
    $.ajax({
        type : "get",
        url : "../../cert/wechatParam",
        dataType : "json",
        async : false,
        data:{"url":url},
        success : function(data) {
            wx.config({
                debug: false,////生产环境需要关闭debug模式
                appId: data.appid,//appId通过微信服务号后台查看
                timestamp: data.timestamp,//生成签名的时间戳
                nonceStr: data.nonceStr,//生成签名的随机字符串
                signature: data.signature,//签名
                jsApiList: [//需要调用的JS接口列表
                    'checkJsApi',//判断当前客户端版本是否支持指定JS接口
                    'onMenuShareTimeline',//分享给好友
                    'onMenuShareAppMessage'//分享到朋友圈
                    // "translateVoice"
                ]
            });
        },
        error: function(xhr, status, error) {
            //alert(status);
        }
    })
});
wx.ready(function () {
    var shopId=appSupport.cm.queryString("shopId");
    var protocol = window.location.protocol;
    var host = window.location.host;
    //获取路径
    var pathName=window.document.location.pathname;
    //截取，得到项目名称
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var link = protocol+'//'+host+projectName+'/company/businessCard.action?shopId='+shopId;
    if (projectName==="/company"){
        projectName=""
    }
    var title = "宏古科技";
    var introduce = "来自宏古科技";
    var icon = protocol + '//' + host +projectName+ '/image/logo.png';
    $.ajax({
        url: '../zlead/enterprise/shopInfo',
        type: 'post',
        data: {
            "shopId":shopId
        },
        success: function(res) {
            if (res.code == 1) {
                if (res.data.shop.companyName!=null) {
                    title = res.data.shop.companyName+" "+res.data.shop.contactName;
                }
                 if (res.data.shop.contactPhone!=null) {
                     introduce = "职位:"+res.data.shop.position+" "+"电话:"+res.data.shop.contactPhone+" "+"qq:"+res.data.shop.contactQQ+" "+"地址:"+res.data.shop.companyAddress;
                 }
                 if (res.data.shop.shopLogo!=null) {
                     icon = "http://116.62.124.171/group1/"+res.data.shop.shopLogo;
                }
            }
            //分享朋友圈
            wx.onMenuShareTimeline({
                title: title,
                link: link,
                imgUrl: icon,// 自定义图标
                // imgUrl: "http://116.62.124.171/group1/M00/00/0B/rBCWGVvcK2KAZGpRAABa4MUbU8g397.jpg",// 自定义图标
                trigger: function (res) {
                    // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回.
                    //alert('click shared');
                },
                success: function (res) {
                    //alert('shared success');
                    //some thing you should do
                },
                cancel: function (res) {
                    //alert('shared cancle');
                },
                fail: function (res) {
                    //alert(JSON.stringify(res));
                }
            });
            //分享给好友
            wx.onMenuShareAppMessage({
                title: title, // 分享标题
                desc: introduce, // 分享描述
                link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: icon, // 自定义图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });


        }});


    wx.error(function (res) {
        alert(res.errMsg);
    });
});
