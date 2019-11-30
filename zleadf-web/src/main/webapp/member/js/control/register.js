$(function() {
	// initAreaSelect('expressArea', function(expressArea) {
	// 	$("#expressArea dl").html(expressArea);
	// })
    new verificationCode($(".yanzhengma"),{type:3}).init();

})
var InterValObj; //timer变量，控制时间//间隔函数，1秒执行
var curCount = 60; //当前剩余秒数
function SetRemainTime() {
	if(curCount == 0) {
		window.clearInterval(InterValObj); //停止计时器
		$('#validate').val('重新获取');
		curCount = 60;
	} else {
		curCount--;
		$("#validate").val(curCount + "秒");
	}
}

//发送验证码
function sendMsg(){
	var phone=$("#phone").val();
	if(phone == "") {
		var msg = "请输入手机号";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	} else {
		var url ="../ajeasy/login/sendMsg.html";
		var data = {
			phone: phone
		}
		appSupport.cm.postAjaxFunction(url, data, callback);
	}
}

//验证码发送成功回调
function callback(evt) {
	if(evt.code == "1") {
		var msg = "验证码发送成功";
		var code=evt.data;
		$("#codeMsg").html(code);
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		$("#validate").val("获取验证码");
		InterValObj = window.setInterval(SetRemainTime, 1000);
	} else if(evt.code != "1") {
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	}
}

function upperCase(){
	var code=$("#code").val();
	var codeMsg=$("#codeMsg").text();
	if(code != codeMsg){
		var msg = "验证码错误！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	}
}

//注册账号
function checkOut(){
	var phone=$("#phone").val();
	/*var parentId=$("#parentId").val();*/
	var password=$("#password").val();
	/*var code=$("#code").val();*/
	/*var codeMsg=$("#codeMsg").text();*/
	/*if(code == '') {
		var msg = "请输入验证码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(code !=codeMsg) {
		var msg = "验证码错误！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(parentId == '') {
		var msg = "请输入推荐人！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else */if(password == '') {
		var msg = "请输入密码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}
	if (!registeryz()){
        var msg = "验证码错误！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 500);
	}
	else {
		var data = {
			phone: phone,
			passWord: password,
			/*parentId: parentId,*/
			provinceName: $("#prive").val(),
			cityName: $("#city").val(),
			countyName: $("#trxo").val()
		}
        var baseUrl=localStorage.getItem("weburl");
		var url = baseUrl+"/zlead/login/registered";
		appSupport.cm.postAjaxFunction(url, data, rigSuccessFunction);
	}
}

//注册成功回调
function rigSuccessFunction(evt) {
	if(evt.code == "1") {
		var msg = "注册成功！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			goToLogin();
		}, 2000);
	}else{
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	}
}

function goToLogin() {
	var phone=$("#phone").val();
	var password=$("#password").val();
    var baseUrl=localStorage.getItem("weburl");
	var url = baseUrl+"/zlead/login/login";
			var data = {
				account: phone,
				password: password

			};
		appSupport.cm.postAjaxFunction(url, data, goToIndex);			
}
function goToIndex(evt){
    var baseUrl=localStorage.getItem("weburl");
    localStorage.setItem("memberId", evt.data.memberId);
    var shopId = appSupport.cm.queryString("shopId");
    if(shopId==null || shopId=='null')
    	shopId=localStorage.getItem("shopId");
    /*localStorage.setItem("hasPayPassword", evt.data.hasPayPassword);
    localStorage.setItem("userPhone", evt.data.phone);
    localStorage.setItem("userNo", evt.data.userNo);
    localStorage.setItem("memberType", evt.data.memberType);
    localStorage.setItem("level", evt.data.level);*/
    if(shopId)
		window.location.href = baseUrl+"/company/index.action?shopId="+shopId;
	else
		window.location.href = baseUrl+"/company/index.action?shopId=10";
}
function registeryz(){
    var yaninput=$("#yaninput").val().toLowerCase();
    var yzcode=$("#yzcode").val().toLowerCase();
    if(yaninput != yzcode){
    	return false;
    }else {
        return true;
    }
}
function yanzheng(){
    if(!registeryz()){
        var msg = "验证码错误！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 500);
    }
}
class verificationCode {
        constructor(DOM,option){
            var defaultOPtion = {
                num:4,
                type:1,
                lineNum:20
            };
            this.DOM = DOM;
            this.option = $.extend({},defaultOPtion,option);
            this.dataArr1 = ['48-57'];
            this.dataArr2 = ['65-90','97-122'];
            this.dataArr3 = ['48-57','65-90','97-122'];
            this.width = DOM.width();
            this.height = DOM.height();
            this.canvasDom = $(`<canvas id="myCanvas"  width='${this.width}' height='${this.height}'></canvas>`);
        }
        init(){
            this.DOM.html(this.canvasDom[0]);
            //创建canvas对象
            this.initCanvas();
            this.clickInit();
        }
        clickInit(){
            this.DOM.click(()=>{
                this.initCanvas();
        })
        }
        initCanvas(){
            let that = this,
                ctx = that.canvasDom[0].getContext('2d');
            //改变填充颜色
            ctx.fillStyle='#ddd';
            ctx.fillRect(0,0,that.width,that.height);
            //生成干扰线条
            if(that.option.lineNum && that.option.lineNum>0){
                for(let j=0 ; j<that.option.lineNum ; j++){
                    ctx.strokeStyle='#fff';
                    ctx.beginPath();
                    ctx.moveTo(that.randNum(that.width),that.randNum(that.height));
                    ctx.lineTo(that.randNum(that.width),that.randNum(that.height));
                    ctx.lineWidth=0.5;
                    ctx.closePath();
                    ctx.stroke();
                }
            }
            //填充文字
            ctx.fillStyle='red';
            ctx.font='bold 20px Arial';
            ctx.fillText(this.rand(),25,25);
        }
        rand(){
            //生成随机字符串
            let that = this,
                scope = that.randScope(),
                code = "";
            //根据传入的个数 生成随机数
            for(let i = 0 ; i < that.option.num ; i++){
                code += scope[that.randNum(scope.length)];
            }
            $("#yzcode").val(code);
            return code;
        }
        randScope(){
            //生成随机数的范围
            let data = "";
            $.each(this["dataArr"+this.option.type],(i,v)=>{
                let a = v.split("-");
            for(let i = a[0]*1 ; i<=a[1]*1 ; i++){
                data += String.fromCharCode(i);
            }
        })
            return data;
        }
        randNum(length){
            let num = parseInt(Math.random()*length)
            return num;
        }

}