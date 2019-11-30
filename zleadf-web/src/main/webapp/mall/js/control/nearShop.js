$(function() {
	getTypeList();
	getList();
});

function tabChange(id,index){
	$("#list").empty();
	pageNumber=0;
	var storeType=id;
	getList(id);
	$("#typeList li").removeClass("changeColor");
	$("#storeType"+id).addClass("changeColor");
}

function getTypeList(val){
	var html='';
	var storeType=val;
	$.ajax({
		url: '../ajeasy/shopList/catListQuery.html',
        type: 'post',
        data: {
       	 'storeType':storeType
        },
        success: function(res) {
        	if(res.data.length>0){
        		for(var i=0;i<res.data.length;i++){
        			html+='<li id="storeType'+res.data[i].zcid+'" onclick="tabChange('+res.data[i].zcid+','+i+')">';
	   				html+='<img src="http://116.62.124.171'+res.data[i].zcimg+'" alt=""/>'+
	   					  '<span>'+res.data[i].zcname+'</span>';
	   				html+='</li>';
        		}
        		$("#typeList").html(html);
        	}	
        }
	})
}

var pageNumber = 0;
var size = 5;
function getList(val){
	var html='';
	var storeType=val;
	$('.list').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	pageNumber++;
        // 拼接HTML
        var html = '';
        $.ajax({
            type: 'post',
            url: '../ajeasy/shopList/shopListQuery.html?pageNumber='+pageNumber+'&size='+size,
            data: {
              	 'storeType':storeType
                },
            success: function(res){
            	if(res.data.length>0){
    				for(var i=0;i<res.data.length;i++){
    					html+='<li onclick="gotodetail('+res.data[i].id+')">';
                		html+='<div class="list-img">';
    	     		 		  if(res.data[i].logo==null || res.data[i].logo==""){
    	     		 		  	html+='<img src="img/hand-pic.png" alt=""/>';
    	     		 		  }else{
    	     		 			html+='<img src="http://116.62.124.171'+res.data[i].logo+'" alt=""/>';
    	     		 		  }
    	     		 		  html+='</div>';
    	     		 		  html+='<div class="list-info">'+
               		 		  '<p class="list-info-1 sj">'+res.data[i].shopName+'</p>'+
               		 		  '<p class="list-info-2">'+
               		 		  '<img src="img/img-wuxing.png" alt=""/>'+
               		 		  '</p>'+
               		 		  '<p class="list-info-3">'+
               		 		  '<img src="img/icon-dizhitubiao.png" alt=""/>';
    	     		 		  if(res.data[i].address.length>16){
    	     		 			 html+='<span>'+res.data[i].address.substring(0, 16)+"..."+'<span>';
    	     		 		  }else{
    	     		 			 html+='<span>'+res.data[i].address+'<span>';
    	     		 		  }
    	     		 		  html+='</p>';
    	     		 		  html+='<p class="list-info-4">'+
               		 		  '<img src="img/icon-gouwudai.png" alt=""/>'+
               		 		  '<span>'+res.data[i].buyernum+'人购买</span>'+
               		 		  '</p>'+
               		 		  '</div>';
    	     		 	html+='</li>';  
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
                    $('#list').append(html);
                    // 每次数据插入，必须重置
                    me.resetload();
                },1000);
            },
            error: function(xhr, type){
                alert('加载错误');
                // 即使加载出错，也得重置
                me.resetload();
            }
        })
    	}
    })
}

function gotodetail(id){
	window.location.href = "nearShopDetails.jsp?id="+id;
}