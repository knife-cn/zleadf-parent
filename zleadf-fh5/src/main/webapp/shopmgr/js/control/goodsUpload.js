$(function() {
	init();
	/*var input = document.getElementById("demo_input"); 
    var result= document.getElementById("result"); 
    if ( typeof(FileReader) === 'undefined' ){
    	var msg = "抱歉，你的浏览器不支持 FileReader，请使用现代浏览器操作！";
 		appSupport.cm.errorMessageShow(errorMsg, msg);
 		setTimeout(function() {
 			appSupport.cm.errorMessageHide(errorMsg);
 		}, 1000);
         return false; 
        input.setAttribute('disabled','disabled'); 
    }else{
        input.addEventListener('change',readFile,false);
    } */
})	

/*function readFile(){
    var file = this.files[0]; 
    //判断是否为图片
    if(!/image\/\w+/.test(file.type)){
        var msg = "请确保文件为图像类型！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
        return false; 
    }
    var reader = new FileReader(); 
    reader.readAsDataURL(file); 
    reader.onload = function(e){ 
            $(".img_area_img").attr('src',this.result);
            uploadImg();
    }
}*/

function uploadImg() {
	$.ajaxFileUpload({
		url : '../ajeasy/fdfs/uploading',
		secureuri : false,
		fileElementId : 'demo_input',//上传控件的id
		dataType : 'json',
		success : function(res) {
			if(res.code==1){
				var msg = "上传图片成功！";
				appSupport.cm.errorMessageShow(errorMsg, msg);
				setTimeout(function() {
					appSupport.cm.errorMessageHide(errorMsg);
					$(".img_area_img").attr('src','http://116.62.124.171/group1/'+res.data);
				}, 1000);
				$(".backDate").val(res.data);
			}else if(res.code==2){
				var msg = "上传失败！";
				appSupport.cm.errorMessageShow(errorMsg, msg);
				setTimeout(function() {
					appSupport.cm.errorMessageHide(errorMsg);
				}, 1000);
			}
		},
	});
	return false;
}

function init(){
	var baseUrl=localStorage.getItem("weburl");
	var url=baseUrl+'/zlead/tgoodsBg/catList';
	var html='';
	$.ajax({
		url:url,
		type:'post',
		success:function(res){
			if(res.code==1){
				if(res.data.length>0){
					for(var i =0;i<res.data.length;i++){
						html+='<option value="'+res.data[i].id+'">'+res.data[i].name+'</option>';
					}
					$(".allianceDiv-input").html(html)
				}
			}
		}
	})
}

function validateForm(){
	var fullName = $("#fullName").val();
    if(fullName==''){
    	var msg = "请输入商品名称！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var price = $("#price").val();
    if(price==''){
    	var msg = "请输入会员价格！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var marketPrice = $("#marketPrice").val();
    if(marketPrice==''){
        var msg = "请输入市场价格！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var stock = $("#stock").val();
    if(stock==''){
    	var msg = "请输入库存！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var salesNum = $("#salesNum").val();
    if(salesNum==''){
    	var msg = "请输入销量！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var catId = $("#catId").val();
    if($("#catId").val() == null||$("#catId").val() == "") {
		var msg ="请选择商品分类";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var itemNumber = $("#itemNumber").val();
    if(itemNumber==''){
        var msg = "请输入商品货号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var firstImg = $("#firstImg").val();
    if(firstImg==''){
        var msg = "请上传图片！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var isMarketable =$('input[name="isMarketable"]:checked').val();
    if(isMarketable=="" || isMarketable== null || isMarketable==undefined){
        var msg = "请选择是否上架！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var imgs = $("#imgs").val();
    var baseUrl=localStorage.getItem("weburl");
    $.ajax({
        url:baseUrl+'/zlead/enterpriseBg/enterpriseGoodsSave',
        type:"post",
        dataType:"json",
        data:{
        	"fullName":fullName,
        	"price":price,
			"marketPrice":marketPrice,
        	"stock":stock,
        	"salesNum":salesNum,
        	"catId":catId,
        	"itemNumber":itemNumber,
        	"firstImg":firstImg,
			"imgs":imgs,
        	"isMarketable":isMarketable,
        	"pointType":2
        },
        success : function(res) {
            if(res.code==1) {
            	var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.history.back();
                }, 1500);
            }else{
            	var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
                $("#validate").attr("onclick","validateForm()");
            }
        }
    });
}
