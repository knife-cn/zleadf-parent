$(function () {
    init();
})

var pageNum = 0;
var size = 2 ;

function init() {
    var html = '';
    $('.newsInfoList').dropload({
        scrollArea: window,
        loadDownFn: function (me) {
            pageNum++;
            // 拼接HTML
            $.ajax({
                //url: '../joinShop/salesOrder',
                type: 'post',
                url: '../zlead/enterpriseBg/enterpriseArticleList',
                data: {
                    "size": size,
                    "pageNum": pageNum
                },
                success: function (res) {

                    if (res.code == 1) {
                        if (res.data.result.length > 0) {
                            for (var i = 0; i < res.data.result.length; i++) {
                                html += '<ul class="news">';
                                html += '<li class="list">';
                                html +='<div>';
                                if(res.data.result[i].title.length>=8){
                                    html += '<span class="listINfo-2-span mt col-sm-6">' + res.data.result[i].title.substring(0,4)+'..</span>';
                                }else {
                                    html += '<span class="listINfo-2-span mt col-sm-6">' + res.data.result[i].title + '</span>';
                                }

                                if(res.data.result[i].createDate ==null){
                                    html += '<span class="listINfo-2-p mt col-sm-6">' + '</span>';
                                }else{
                                    html += '<span class="listINfo-2-p mt col-sm-6">'+''+timestampToTime(res.data.result[i].createDate) + '</span>';
                                }
                                html +='</div>';
                                html += '<div>';
                                if (res.data.result[i].content.length>=20) {
                                    html += '<div class="listINfo-2-span col-sm-6"><div class="contentImg"><img style="max-height: 200px;max-width: 100%;" src="'+ 'http://116.62.124.171/group1/' + res.data.result[i].thumbnail +'"/></div></div>';
                                } else {
                                     html += '<div class="listINfo-2-span col-sm-6"><div class="contentImg">'+res.data.result[i].content + '</div></div>';
                                }
                                html += '</div>';
                                html += '<div class="caozuo">';
                                html+='<span class="liAdit-1" style="float: right;margin-top: 10px;margin-right:15px" onclick="updateNews('+res.data.result[i].id+ ')">修改</span>';
                                html+='<span class="liDet" style="float: right;margin-top: 10px" onclick="deleteNews('+res.data.result[i].id+ ')">删除</span>';
                                html +='</div>';
                                html += '</li>';
                                html += '</ul>';
                            }
                        }
                        //清除相同内容的数据
                        $("#newsList").children().remove();
                        // 插入数据到页面，放到最后面
                        $('#newsList').append(html);

                        // 每次数据插入，必须重置
                        me.resetload();
                    } else if(res.code== 2) {
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                        $('#newsList').append("<p style='text-align:center;font-size:0.26rem;line-height:0.5rem;'>暂无更多新闻信息</p>");
                    }
                },

                error: function (xhr, type) {
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            })
        }
    })
}
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y+M+D+h+m+s;
}

function deleteNews(id){
    $.ajax({
        type:'post',
        url:'../zlead/enterpriseBg/enterpriseDeleteArticle',
        data:{"id":id},
        success:function(res){
            if (res.code == 1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                    init();
                    window.location.href = 'newsList1.jsp';
                }, 1000);
            } else {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        }
    })
}
function updateNews(id){
    window.location.href = 'newsUpdate.jsp?id='+id;
}

function addNews(){
    window.location.href = 'addNews.jsp';
}