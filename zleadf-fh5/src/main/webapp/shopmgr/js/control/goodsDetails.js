$(function() {
	bannerShow();
	details();
	$(".amount").val(1);
})

//banner图
function bannerShow() {
	var goodsId=appSupport.cm.queryString("goodsId");
	$.ajax({
		url: '../../zlead/tgoods/goodsDetail',
        type: 'post',
        data: {
        	"goodsId":goodsId
        },
        success: function(res) {
        	 if (res.data && res.data.imgArr.length > 0) {
    	        var _banner = '';
    	        for (var i=0; i<res.data.imgArr.length; i++) {
    	        	_banner+='<li><img src="http://116.62.124.171/group1/'+ res.data.imgArr[i] +'" alt=""></li>';
    	        }
    	        $('#slider-inner').html(_banner);
    	        setTimeout(function () {
    	          $('.default-slider').unslider({
    	            autoplay: true
    	          });
    	        }, 10);
    	      }else {
    	        $('#slider-inner').hide();
    	      }
        }
	})
}
	
function details(){
	var goodsId=appSupport.cm.queryString("goodsId");
	$.ajax({
		url: '../../zlead/tgoods/goodsDetail',
        type: 'post',
        data: {
        	"goodsId":goodsId
        },
        success:function(res){
        	if(res.data.code=1){
        		$(".details-1").html(res.data.fullName);
        		$(".details-2-label").html(res.data.price);
        		if(res.data.prodType==0){
        			$(".details-2-jf").hide();
        		}else{
        			$("#details-2-2").html(res.data.pointPrice);
        		}
        		if(res.data.prodType==1){
        			$(".details-2-price").hide();
        		}else{
        			$(".details-2-price").show();
        		}
        		/*if(res.data.prodType==0 || res.data.prodType==1 || res.data.prodType==5){
        			$(".shopCar").hide();
        		}else{
        			$(".shopCar").show();
        		}*/
        		$(".details-1").html(res.data.fullName);
        		$("#sales").html(res.data.sales);
        		$("#stock").html(res.data.stock);
        		$("#shopId").val(res.data.shopId);
        		$("#prodType").val(res.data.prodType);
        		if(res.data.intro=="" || res.data.intro==null){
        			$(".title").hide();
        		}else{
        			$(".detailsImg").html(res.data.intro);
        		}
        	}
        }
	})
}

function addCar(){
	var goodsId=appSupport.cm.queryString("goodsId");
	var memberId = localStorage.getItem("memberId");
	var count=$(".amount").val();
	var shopId=$("#shopId").val();
	var data = {
		"goodsId":goodsId,
		"memberId":memberId,
		"count":count,
		"shopId":shopId,
		"buyType":0
	}
	var url = "../../zlead/tshoppingcart/addShoppingCart";
	appSupport.cm.postAjaxFunction(url,data,firstCar);
	/*if(memberId==null){
		window.location.href = "login.jsp";
	}else{
		
	}*/
}

function buy(){
    var shopId=appSupport.cm.queryString("shopId");
	var memberId = localStorage.getItem("memberId");
	var goodsId=appSupport.cm.queryString("goodsId");
	var buyNum=$(".amount").val();
	var url = "../../zlead/torder/newConfirmOrder";
	$.ajax({
		url : url,
		type: 'post',
		data : {
				"goodsId":goodsId,
				"buyNum":buyNum
			},
		success:function(res){
			if(res.code==1){
				buyGoods(res);
			}else if(res.code==2){
				window.location.href = "login.jsp?shopId="+shopId;
			}else if(res.code==510){
				window.location.href = "login.jsp?shopId="+shopId;
			}
		}
	})
	/*if(code == 2){
		window.location.href = "login.jsp";
	}else{
		appSupport.cm.postAjaxFunction(url,data,buyGoods);
	}*/
}

function buyGoods(res) {
    var shopId=appSupport.cm.queryString("shopId");
	var goodsId=appSupport.cm.queryString("goodsId");
	var buyNum=$(".amount").val();
	var prodType=$("#prodType").val();
	if(res.code == 1) {
		setTimeout(function() {
			window.location.href = "affirmOrder.jsp?goodsId="+goodsId+"&buyNum="+buyNum+"&prodType="+prodType+"&status="+1;
		}, 1000);
	}else if(res.code == 2){
		var msg = res.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
			window.location.href = "login.jsp?shopId="+shopId;
		}, 1000);
	}else if(res.code == 510){
		var msg = res.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
			window.location.href = "login.jsp?shopId="+shopId;
		}, 1000);
	} else {
		var msg = res.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
			window.location.reload();
		}, 1000);
	}
}

function firstCar(evt) {
    var shopId=appSupport.cm.queryString("shopId");
	if(evt.code == 1) {
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
	}else if(evt.code == 510){
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
			window.location.href = "login.jsp?shopId="+shopId;
		}, 1000);
	}else if(evt.code == 2){
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
			window.location.href = "login.jsp?shopId="+shopId;
		}, 1000);
	} else {
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
			window.location.reload();
		}, 1000);
	}
}

$(function() {
	function addShopCar() {
		$(document).on('focusout', function() {
			var thisLength = $("#stock").html().length;
			var addNum = $("#stock").html();
			pValue = Number($(".amount").val());
			if(pValue > addNum) {
				pValue = addNum;
				$(".amount").val(pValue);
			} else if(pValue == 0) {
				pValue = 1;
				$(".amount").val(pValue);
			}
		});
		$(".add").on("click", function() {
			var thisLength = $("#stock").html().length;
			var addNum =$("#stock").html();
			pValue = Number($(".amount").val());
			if(pValue < addNum) {
				pValue += 1;
				$(".amount").val(pValue);
				if(pValue == addNum) {
					pValue = addNum;
					$(".amount").val(pValue);
				}
			} else if(pValue == addNum) {
				pValue = addNum;
				$(".amount").val(pValue);
			}
		})
		$(".min").on("click", function() {
			pValue = Number($(".amount").val());
			if(pValue == 1) {
				pValue = 1;
				$(".amount").val(pValue);
			} else if(pValue == 2) {
				pValue -= 1;
				$(".amount").val(pValue);
			} else {
				pValue -= 1;
				$(".amount").val(pValue);
			}
		})
	}
	addShopCar();
})
