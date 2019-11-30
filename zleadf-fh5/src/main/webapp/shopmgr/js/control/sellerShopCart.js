$(function() {
	var allPrice = $("#totalPrice").text(0); //商品总价
	changeTab();
});

//展示购物车信息
function showShopCar(evt) {
    var shopId=appSupport.cm.queryString("shopId");
	$(".carList").empty();
	if(evt.code==1){
		if(evt.data.length == 0) {
	  		var str = "";
	  		str += "<img src='img/shopCar-null.png'>";
	  		$(".carList").append(str);
	  	} else {
	  	//购物车不为空
	  		var htmlStr = "";
	  		for(var i = 0; i < evt.data.length; i++) {
	  			if ($(".pointType").val()==3){
	  				evt.data[i].shopName="代理商城";
				}
	  			htmlStr+="<div class='shop-group-item'>";
	  			htmlStr+="<div class='carList-name'>";
	  			htmlStr+="<input type='checkbox' class='check goods-check shopCheck'>";
	  			htmlStr+="<span>"+evt.data[i].shopName+"<span>";
	  			htmlStr+="</div>";
	  			htmlStr+="<ul>";
	  				for(var j = 0; j < evt.data[i].goodsList.length; j++){
	  					var goodsName = evt.data[i].goodsList[j].goodsName;
	  					htmlStr+="<li class='shopListLi'>";
	  					htmlStr+="<div class='shop-info'>";
	  					htmlStr+="<input style='margin-top:0.655rem;' type='checkbox' class='check goods-check goodsCheck'>";
	  					htmlStr+="<div class='carList_eords'>";
	  					htmlStr+="<div class='carList_img'>";
	  					htmlStr+="<img src='http://116.62.124.171"+evt.data[i].goodsList[j].goodsImg+"' alt=''>";
	  					htmlStr+="</div>";
	  					htmlStr+="<div class='carList_info'>";
	  					if(goodsName.length>12){
	  						htmlStr+="<p class='carList_info_title'>"+goodsName.substring(0, 12)+"..."+"</p>";
	  				 	  }else{
	  				 		 htmlStr+="<p class='carList_info_title'>"+goodsName+"<p>";
	  				 	  }
	  					htmlStr+="<p class='carList_info_type'>";
	  					htmlStr+="<img src='img/img-quan.png' alt=''>";
	  					htmlStr+="<span>默认规格</span>";
	  					htmlStr+="</p>";
	  					htmlStr+="<div class='carList_info_choose'>";
	  					htmlStr+="<span class='carList_info_choose_span'><label class='carList_info_choose_label'>"+evt.data[i].goodsList[j].goodsPrice+"</label>RMB</span>";
	  					htmlStr+="<div class='number'>";
	  					htmlStr+="<input class='add' type='button' value='+'/>";
	  					htmlStr+="<input class='amount' type='text' onkeyup='value=value.replace(/[^0-9]/g,'')' value='"+evt.data[i].goodsList[j].count+"'/>";
	  					htmlStr+="<input class='min' type='button' value='-'/>";
	  					htmlStr+="<input class='stock' type='hidden' value='"+evt.data[i].goodsList[j].stock+"'/>";
	  					htmlStr+="<input class='id' type='hidden' value='"+evt.data[i].goodsList[j].goodsId+"'/>";
	  					htmlStr+="<input class='shoppingCartId' type='hidden' value='"+evt.data[i].goodsList[j].id+"'/>";
	  					htmlStr+="</div>";
	  					htmlStr+="</div>";
	  					htmlStr+="</div>";
	  					htmlStr+="<div class='carList_price'>";
	  					htmlStr+="<p class='carList_price_1'>"+(evt.data[i].goodsList[j].goodsPrice)*(evt.data[i].goodsList[j].count)+"</p>";
	  					htmlStr+="<p class='carList_price_2'>RMB</p>";
	  					htmlStr+="</div>";
	  					htmlStr+="</div>";
	  					htmlStr+="</li>";
	  				}
	  			htmlStr+="</ul>";
	  			htmlStr+="<input type='hidden' class='ShopTotal'>";
	  			htmlStr+="</div>";
	  		}
	  		
	  		$(".carList").html(htmlStr);
	  		
	  		//数量减
	  		$(".min").click(function() {
	  			var t = $(this).parent().find('.amount');
	  			var d = $(this).parent().find('.stock');
	  			var e = $(this).parent().parent().parent().parent().find('.carList_info_choose_label');
	  			var f = $(this).parent().parent().parent().parent().find('.carList_price_1');
	  			var tvalue=Number(t.val());
	  			var dvalue=Number(d.val());
	  			var evalue=Number(e.text());
	  			var fvalue=Number(f.text());
	  			if (tvalue > 1) {
	  				tvalue -= 1;
	  				t.val(tvalue);
	  				f.text(tvalue * evalue);
	  			}else if(tvalue == 1){
	  				tvalue = 1;
	  				t.val(tvalue);
	  				f.text(tvalue * evalue);
	  			}
	  			TotalPrice();
	  		});
	  		
	  		// 数量加
	  		$(".add").click(function() {
	  			var t = $(this).parent().find('.amount');
	  			var d = $(this).parent().find('.stock');
	  			var e = $(this).parent().parent().parent().parent().find('.carList_info_choose_label');
	  			var f = $(this).parent().parent().parent().parent().find('.carList_price_1');
	  			var tvalue=Number(t.val());
	  			var dvalue=Number(d.val());
	  			var evalue=Number(e.text());
	  			var fvalue=Number(f.text());
	  			if (tvalue < dvalue) {
	  				tvalue += 1;
	  				t.val(tvalue);
	  				f.text(tvalue * evalue);
	  			}else if(tvalue > dvalue){
	  				tvalue=dvalue;
		  			t.val(dvalue);
		  			f.text(tvalue * evalue);
	  			}
	  			TotalPrice();
	  		});
	  		
	  		  //点击商品按钮
	  		  $(".goodsCheck").click(function() {
	  			var goods = $(this).closest(".shop-group-item").find(".goodsCheck"); //获取本店铺的所有商品
	  		    var goodsC = $(this).closest(".shop-group-item").find(".goodsCheck:checked"); //获取本店铺所有被选中的商品
	  		    var Shops = $(this).closest(".shop-group-item").find(".shopCheck"); //获取本店铺的全选按钮
	  		    if (goods.length == goodsC.length) { //如果选中的商品等于所有商品
	  		      Shops.prop('checked', true); //店铺全选按钮被选中
	  		      if ($(".shopCheck").length == $(".shopCheck:checked").length) { //如果店铺被选中的数量等于所有店铺的数量
	  		        $("#AllCheck").prop('checked', true); //全选按钮被选中
	  		        TotalPrice();
	  		      } else {
	  		        $("#AllCheck").prop('checked', false); //else全选按钮不被选中 
	  		        TotalPrice();
	  		      }
	  		    } else { //如果选中的商品不等于所有商品
	  		      Shops.prop('checked', false); //店铺全选按钮不被选中
	  		      $("#AllCheck").prop('checked', false); //全选按钮也不被选中
	  		      // 计算
	  		      TotalPrice();
	  		    }
			  });
			  // 点击店铺按钮
			  $(".shopCheck").click(function() {
			    if ($(this).prop("checked") == true) { //如果店铺按钮被选中
			      $(this).parents(".shop-group-item").find(".goods-check").prop('checked', true); //店铺内的所有商品按钮也被选中
			      if ($(".shopCheck").length == $(".shopCheck:checked").length) { //如果店铺被选中的数量等于所有店铺的数量
			        $("#AllCheck").prop('checked', true); //全选按钮被选中
			        TotalPrice();
			      } else {
			        $("#AllCheck").prop('checked', false); //else全选按钮不被选中
			        TotalPrice();
			      }
			    } else { //如果店铺按钮不被选中
			      $(this).parents(".shop-group-item").find(".goods-check").prop('checked', false); //店铺内的所有商品也不被全选
			      $("#AllCheck").prop('checked', false); //全选按钮也不被选中
			      TotalPrice();
			    }
			  });
			  
			  //点击删除按钮
			  $(".detCar").click(function() {
				  var shoppingCartIdStr = "";
				  $(".shop-group-item").each(function() {
					  $(this).find(".goodsCheck").each(function() {
						  if($(this).is(":checked")) {
							 var id = $(this).parents(".shop-info").find(".shoppingCartId").val(); 
							 shoppingCartIdStr += id + ",";
						  }
					  }) 
				  })
				if(shoppingCartIdStr != "") {
					var url = "../ajeasy/shoppingCart/deleteShoppingCart";
					var shoppingCartIdStr = shoppingCartIdStr.substring(0,shoppingCartIdStr.length-1)
					var rigon = {
						"shoppingCartIdStr": shoppingCartIdStr
					}
					appSupport.cm.postAjaxFunction(url, rigon, showModify);

				} else {
					var msg = "至少选择一个商品";
					appSupport.cm.errorMessageShow(errorMsg, msg);
					setTimeout(function() {
						appSupport.cm.errorMessageHide(errorMsg);
					}, 1000);
				}
			  })
			  
			  function showModify(evt) {
					if(evt.success == true) {
						init();
					}
			  }
			  
			  // 点击全选按钮
			  $("#AllCheck").click(function() {
			    if ($(this).prop("checked") == true) { //如果全选按钮被选中
			      $(".goods-check").prop('checked', true); //所有按钮都被选中
			      TotalPrice();
			    } else {
			      $(".goods-check").prop('checked', false); //else所有按钮不全选
			      TotalPrice();
			    }
			    $(".shopCheck").change(); //执行店铺全选的操作
			  });
			  
			  //计算
			  function TotalPrice() {
			      var allprice = 0; //总价
			  	$(".shop-group-item").each(function() { //循环每个店铺
			  		  var oprice = 0; //店铺总价
			  		  $(this).find(".goodsCheck").each(function() { //循环店铺里面的商品
			  			  if ($(this).is(":checked")) { //如果该商品被选中
			  			      var num = parseInt($(this).parents(".shop-info").find(".amount").val()); //得到商品的数量
			  			      var price = parseFloat($(this).parent(".shop-info").find(".carList_info_choose_label").text()); //得到商品的单价
			  			      var total = price * num; //计算单个商品的总价
			  			      oprice += total; //计算该店铺的总价
			  			    }
			  		  $(this).closest(".shop-group-item").find(".ShopTotal").val(oprice.toFixed(2)); //显示被选中商品的店铺总价
			  		  });
			  		  var oneprice = parseFloat($(this).find(".ShopTotal").val()); //得到每个店铺的总价
			  		  allprice += oneprice; //计算所有店铺的总价
			  	});
			  	$("#totalPrice").text(allprice.toFixed(2)); //输出全部总价
			  }	
				var pointType = $(".pointType").val();

			  //结算
			  $(".buy").click(function(){
				  var goodsId = "";
				  var num = "";
				  $(".shop-group-item").each(function() {
					  $(this).find(".goodsCheck").each(function() {
						  if($(this).is(":checked")) {
							 var id = $(this).parents(".shop-info").find(".id").val(); 
							 goodsId += id + ",";
							 var count =$(this).parents(".shop-info").find(".amount").val();
							 num += count + ",";
						  }
					  }) 
				  })
				 if(goodsId != "" || num != "" ) {
					 /*var orderInfo = [{
						 goodsId: goodsId,
						 num:num
					 }];*/
					 /*var orderInfo = [{
						 goodsId: goodsId
					}];*/
					/* goodsId = JSON.stringify(goodsId); 
					 num = JSON.stringify(num);*/
					 var goodsId = goodsId.substring(0,goodsId.length-1)
					 var num = num.substring(0,num.length-1);
					 
					$.ajax({
						url: '../ajeasy/order/newConfirmOrder3',
				        type: 'post',
				        data: {
				        	"goodsId":goodsId,
				        	"buyNum":num,
				        	"pointType":pointType
				        },
				        success: function(res) {
				        	if(res.code==1){
				        		window.location.href = "affirmOrder.jsp?goodsId="+goodsId+"&buyNum="+num+"&pointType="+pointType+"&status="+2;
				        	}
				        }
					})
				 } else {
					var msg = "未选择商品";
					appSupport.cm.errorMessageShow(errorMsg, msg);
					setTimeout(function() {
						appSupport.cm.errorMessageHide(errorMsg);
					}, 1000);
				 }
			  })
	  	}
	}else if(evt.code==2){
		window.location.href = "login.jsp?shopId="+shopId;
	}else if(evt.code==510){
		window.location.href = "login.jsp?shopId="+shopId;
	}
}

/*function init(index) {
	var index=index;
	if(index==1 || index== undefined){
		var pointType = 2;
	}
	else{
		var pointType = 4;
	}
	var url = "../ajeasy/shoppingCart/shoppingCartGoods";
	var data = {
			"type":0,
			"pointType":pointType
	};
	appSupport.cm.postAjaxFunction(url, data, showShopCar);
}*/

function changeTab(obj, index) {
    $(obj).parent().find('li').removeClass('active');
    $(obj).addClass('active');
    var pointType = 3;
    if (index == 1) {
    	 pointType = 3;
    }else if(index == 2){
    	 pointType = 6;
    }
    $(".pointType").val(pointType);
    var url = "../ajeasy/shoppingCart/shoppingCartGoods";
	var data = {
			"type":0,
			"pointType":pointType
	};
	appSupport.cm.postAjaxFunction(url, data, showShopCar);
 }
