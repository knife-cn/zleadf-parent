<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <!-- 宽度设置为设备实际宽度，初始化倍数为1，最小倍数为1，最大倍数为1，用户缩放为否 -->
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
    <!-- 删除默认的苹果工具栏和菜单栏 -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- 苹果手机顶部为黑色 -->
    <meta name="apple-mobile-web-status-bar-style" content="block"/>
    <!-- 屏蔽浏览器自动识别数字为电话号码 -->
    <meta name="fromat-detecition" content="telephone=no"/>
    <title>宏古</title>
    <link rel="stylesheet" href="./css/common.css?v=1.1"/>
    <link rel="stylesheet" href="./css/allianceList.css?v=1.1"/>
    <script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="./js/config/dropload.min.js?v=1.1"></script>
    <script type="text/javascript" src="./js/library/banner.js"></script>
    <script type="text/javascript" src="./js/config/url.js?v=1"></script>
    <script type="text/javascript" src="./js/config/comn.js?v=1"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/company/zlead/assets/js/layer/layer.js"></script>
</head>
<body>
<div id="errorMsg"></div>
<div class="orderInfo">
    <div class="orderTitle">
        <img src="img/icon-back.png" class="fl go-back-1">
        <span>文章评论审核</span>
    </div>


    <div class="list-1">
        <ul>
            <li style="width:20%">评论者</li>
            <li style="width:20%">评论时间</li>
            <li style="width:35%">评论内容</li>
            <li style="width:25%">状态</li>
        </ul>
    </div>

    <div class="list-2">
        <div id="list"></div>
    </div>

</div>
</body>
<script>
    $(function(){
        init(-1);
    })

    var pageNum = 0;
    var size = 10;
    function init(){
        var html='';
        // $('.list-2').dropload({
        //     scrollArea : window,
        //     loadDownFn : function(me){
        //         pageNum++;
        //         // 拼接HTML
                $.ajax({
                    url: '${pageContext.request.contextPath}/zlead/comment/list',
                    type: 'post',
                    data: {
                        "pageNum":pageNum,
                        "size":size
                    },
                    success: function(res) {
                        if(res.code==1){
                            if(res.data.length>0){
                                for(var i=0;i<res.data.length;i++){
                                    if (res.data[i].isAudit==0) {
                                        html += '<ul>';
                                        if (res.data[i].username.length > 3) {
                                            html += '<li style="width:22%">' + res.data[i].username.substring(0, 3) + "..." + '</li>';
                                        } else {
                                            html += '<li style="width:22%">' + res.data[i].username + '</li>';
                                        }
                                        html += '<li style="width:22%">' + appSupport.cm.formatDate(res.data[i].createDate, 'yyyy-MM-dd') + '</li>';
                                        if (res.data[i].replyMsg != null && res.data[i].replyMsg.length > 3) {
                                            html += '<li style="width:31%">' + res.data[i].replyMsg.substring(0, 6) + "..." + '</li>';
                                        } else {
                                            html += '<li style="width:31%">' + res.data[i].replyMsg + '</li>';
                                        }
                                        html += '<li style="width:25%">';

                                        html+="<span id="+"'"+res.data[i].id+"'"+" onclick='commentPass("+res.data[i].id+")'>通过</span>"+'&nbsp;';
                                        // if (res.data[i].isAudit==1){
                                        //     html+='<span style="color: #31b0d5;float: left" >通过</span>'+'&nbsp;';
                                        //     html+="<span >取消</span>";
                                        // } else if (res.data[i].isAudit==2) {
                                        //     html+="<span style='float: left'>通过</span>"+'&nbsp;';
                                        //     html+="<span style='color: crimson'>取消</span>";
                                        // }else
                                    }
                                        // html+="<span onclick='commentPassNot("+res.data[i].id+")'>取消</span>";

                                    html+='</li>';
                                    html+='</ul>';
                                }
                            }else{
                                // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                            }
                            // 为了测试，延迟1秒加载
                            setTimeout(function(){
                                // 插入数据到页面，放到最后面
                                $('#list').html(html);
                                // 每次数据插入，必须重置
                                me.resetload();
                            },1000);
                        }else if(res.code==2){
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                        }
                    },
                    error: function(xhr, type){
                        // 即使加载出错，也得重置
                        me.resetload();
                    }
                })
            }
    //     })
    // }

    function gotoGoodsUpload(){
        window.location.href = "goodsUpload.jsp";
    }

    function commentPass(id) {
		var baseUrl=localStorage.getItem("weburl");
		var weburl=baseUrl+"/zlead/comment/auditArticle";
        $.ajax({
            type: 'post',
            url: weburl,
            data: {
                "articleId":id,
                "status":1
            },
            success: function (res) {
                location.reload();
                // location.reload();
                // if (res != null) {
                //     layer.confirm('审核成功，可在评论列表中查看', {
                //         btn: ['确定', '取消']
                //     }, function () {
                //         location.reload();
                //     });
                // } else {
                //     layer.confirm('审核失败', {
                //         btn: ['确定', '取消']
                //     }, function () {
                //         location.reload();
                //     });
                // }

            }
        })
    }

    function commentPassNot(id) {
        $.ajax({
            type: 'post',
            data: {
                "articleId": id,
                "status":2
            },
            url: '../../zlead/comment/auditArticle',
            success: function (res) {
                if (res != null) {
                    layer.confirm('取消成功，此消息不在评论列表中出现', {
                        btn: ['确定', '取消']
                    }, function () {
                        location.reload();
                    });
                } else {
                    layer.confirm('审核失败', {
                        btn: ['确定', '取消']
                    }, function () {
                        location.reload();
                    });
                }
            }
        })
    }


</script>
</html>
