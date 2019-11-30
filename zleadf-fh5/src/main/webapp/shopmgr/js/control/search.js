$(function() {
	$("#search").on("input", function() {
		var queryInfo = $(this).val();
		if(queryInfo.length != 0) {
			var url = "../ajeasy/goods/firstPageQuery";
			var data = {
				"queryInfo": queryInfo
			};
			appSupport.cm.postAjaxFunction(url, data, productWords);
		}
	})

	function productWords(res) {
		if(res.code == 1) {
			var html = '';
			var length = res.data.length;
			for(var i=0;i<res.data.length;i++){
				html+='<li id="storeType'+res.data[i].id+'" onclick="toDetails('+res.data[i].id+','+i+')">';
				html+='<div class="list-info">';
        		html+='<div class="list-info-img">';
     		 		  html+='<img src="http://116.62.124.171'+res.data[i].firstImg+'" alt=""/>';
     		 		  html+='</div>';
     		 		  html+='<div class="list-info-title">';
     		 		  if(res.data[i].fullName.length>14){
     		 			html+='<p >'+res.data[i].fullName.substring(0, 14)+"..."+'</p>';
     		 		  }else{
     		 			html+='<p >'+res.data[i].fullName+'</p>';
     		 		  }
     		 		html+='</div>';
     		 		html+='<div class="list-info-peole">';
     		 		html+=	'<img src="img/img-quan.png" alt=""/>';
     		 		html+=	'<span style="margin-left:0.12rem;">已有<label>'+res.data[i].sales+'</label>人购买</span>';
     		 		html+='</div>';
					 html+='<div class="list-info-price">';
					  if(res.data[i].pointType==0){//积分购
						  html+='<p>';
						  html+='<span class="list-price">RMB<label>'+ res.data[i].pointPrice+'</label></span>';
						  html+='<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>';
						  html+='<label class="integral">'+ res.data[i].point+'</label>';
						  html+='</span>';
						  html+='</p>';
					  }else if(res.data[i].pointType==1){//积分换
						  html+='<p>';
						  html+='<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>';
						  html+='<label class="integral">'+ res.data[i].point+'</label>';
						  html+='</span>';
						  html+='</p>';
					  }else if(res.data[i].pointType==2){//共享商城
						  html+='<p>';
						  html+='<span class="list-price">RMB<label>'+ res.data[i].price+'</label></span>';
						  html+='</p>';
					  }
					  html+='</div>';
					  html+='</div>';
					  html+='</li>';  
    		}
			$("#listUl").html(html);
		}
	}
})

function toDetails(id){
	var goodsId=id;
	window.location.href = "goodsDetails.jsp?goodsId=" + goodsId;
}