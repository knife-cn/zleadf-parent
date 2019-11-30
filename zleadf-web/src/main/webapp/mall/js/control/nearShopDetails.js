$(function() {
	getdetail();
	getRecommendDetail();
})

function getdetail(id){
	var id=appSupport.cm.queryString("id");
	$.ajax({
		url:'../ajeasy/shopList/shopDetails',
		type: 'post',
        data: {
        	'id':id
        },
        success: function(data) {
        	if(data.data.logo==null || data.data.logo==""){
        		$('#logo').attr('src','../jsp/mobile/shopfront/pages/images/banner-pic.png');
        	} else{
        		$('#logo').attr('src','http://116.62.124.171'+data.data.logo);
        	}
        	$('#name').html(data.data.shopName);
        	$('#address').html(data.data.address);
        	$('#buyernum').html(data.data.buyernum);
        }
	})
}

function getRecommendDetail(){
	var id=appSupport.cm.queryString("id");
	var html='';
	$.ajax({
		url:'../ajeasy/shopList/shopGoodList',
		type: 'post',
        data: {
        	'id':id
        },
        success: function(res) {
        	if(res.data.length>0){
        		for(var i=0;i<res.data.length;i++){
        			html+='<li id="'+res.data[i].id+'" onclick="goBuy('+res.data[i].id+','+i+')">';
    				html+='<div class="list-info">';
    				html+='<div class="list-info-img">'+
         		 		  '<img src="http://116.62.124.171'+res.data[i].logo+'" alt=""/>'+
         		 		  '</div>'+
         		 		  '<div class="list-info-title">'+
           		 		  '<p>'+res.data[i].name+'</p>'+
           		 		  '</div>'+
           		 		  '<div class="list-info-peole">'+
           		 		  '<img src="img/img-quan.png" alt=""/>'+
           		 		  '<span style="margin-left:0.12rem;">已有<label>'+res.data[i].sales+'</label>人购买</span>'+
    					  '</div>'+
    					  '<div class="list-info-price">'+
    					  '<p>'+
    					  '<span class="list-price">RMB<label>'+ res.data[i].price+'</label></span>'+
    					  '</p>'+
    					  '</div>'+
    					  '</div>'+
    					  '</li>';  
        		}
        		$(".listUl").html(html);
        	}else{
        		$(".list").hide();
        	}
        }
	})
}

function goBuy(id){
	var goodsId=id;
	window.location.href = "goodsDetails.jsp?goodsId="+goodsId;
}
	