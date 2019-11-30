//$(function(){
	init();
//})

var pageNum = 0;
var size = 5;
function init(){
	var html='';
    var shopId=appSupport.cm.queryString("shopId"); 
    $('.list-2').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	pageNum++;
        // 拼接HTML
		$.ajax({
			url: '/zlead/adsmgr/querylist',
	        type: 'post',
	        data: {
	        	"pageNum":pageNum,
	        	"size":size,
                "shopId":shopId
	        },
	        success: function(res) {
	        	if(res.code==1){
	        		if(res.data.length>0){
		        		for(var i=0;i<res.data.length;i++){
		        			html+='<ul>';
		        			html+='<li style="width:15%">'+res.data[i].title+'</li>';
		        			html+='<li style="width:18%">'+appSupport.cm.formatDate(res.data[i].createDate, 'yyyy-MM-dd')+'</li>';
		        			/*html+='<li style="width:20%">'+res.data[i].isTop+'</li>';*/
		        			if(res.data[i].isTop==true){
		        				html+='<li style="width:20%">置顶</li>';
		        			}else{
		        				html+='<li style="width:20%">未置顶</li>';
		        			}
		        			if(res.data[i].status==0){
		        				html+='<li style="width:20%">未发布</li>';
		        			}else if(res.data[i].status==1){
		        				html+='<li style="width:20%">已发布</li>';
		        			}else if(res.data[i].status==2){
		        				html+='<li style="width:20%">删除</li>';
		        			}
		        			html+='<li style="width:27%">';
		        			html+='<span class="liAdit">修改</span>';
		        			html+='<span class="liClose">关闭</span>';
		        			html+='</li>';
		        			html+='</ul>';
		        		}
		        	}else if(res.data.length==0){
		        		// 锁定
	                    me.lock();
	                    // 无数据
	                    me.noData();
		        	}
	        	}else if(res.code==2){
	        		me.lock();
                    // 无数据
                    me.noData();
	        	}
        		// 为了测试，延迟1秒加载
                //setTimeout(function(){
                    // 插入数据到页面，放到最后面
                    //$('#list').html(html);
                    // 每次数据插入，必须重置
                    //me.resetload();
               // },1000);
	        },
	        error: function(xhr, type){
                // 即使加载出错，也得重置
                me.resetload();
            }
		})
    }
	})
}
 
function gotoAddAds(){
    var shopId = appSupport.cm.queryString("shopId");
	window.location.href = "addads.html?shopId="+shopId;
}