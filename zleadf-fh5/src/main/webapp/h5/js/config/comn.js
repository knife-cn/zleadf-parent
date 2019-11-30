var appSupport = {};
var obj = {};
var order = {

};
appSupport.cm = {
	//每次横竖屏都刷新页面
	hengshuping: function() {
		window.location.reload();
	},
	//动态设置rem
	settingRem: function() {
		//监听是竖屏还是横屏
		window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", appSupport.cm.hengshuping, false);
		!(function(doc, win) {
			var docEle = doc.documentElement, //获取html元素
				event = "onorientationchange" in window ? "orientationchange" :
				"resize", //判断是屏幕旋转还是resize;
				fn = function() {
					var width = docEle.clientWidth;
					width && (docEle.style.fontSize = 100 * (width / 750) + "px"); //设置html的fontSize，随着event的改变而改变。
				};
			win.addEventListener(event, fn, false);
			doc.addEventListener("DOMContentLoaded", fn, false);

		}(document, window));
	},
	//获取跳转页面url参数
	queryString: function(val) {
		var uri = window.location.search;
		var re = new RegExp("" + val + "=([^&?]*)", "ig");
		return((uri.match(re)) ?
			(uri.match(re)[0].substr(val.length + 1)) :
			null);
	},
	paramsFormat: function() {
		var search = location.search;
		var paramsObj = {};
		if(search) {
			search = search.split('?')[1]
			var params = search.split('&');
			for(var i in params) {
				var key = params[i].split('=')[0];
				var value = params[i].split('=')[1];
				paramsObj[key] = value;
			}
		}
		return paramsObj;
	},
	//在同一个页面里更新页面
	goToHtml: function(url) {
		$.ajax({
			url: url, //这里是静态页的地址
			type: "get", //静态页用get方法，否则服务器会抛出405错误
			success: function(data) {
				$(".wrap").html(data);
			}
		})
	},
	//请求秘钥
	//	getKey: function(url,pswd) {
	//		$.ajax({
	//			url: url, //这里是静态页的地址
	//			type: "get", //静态页用get方法，否则服务器会抛出405错误
	//			success: function(data,pswd) {
	//				var newsPSWD = encryptByDES(pswd,key);
	//		var e = new RegExp("\\+", "g");
	//		pswd = newsPSWD.replace(e, "[j]");
	//		return pswd;
	//			}
	//		})
	//	},
	//get请求
	//	var url="text.json";
	//	appSupport.cm.getAjaxFunction(url,alertFunction);
	//	function alertFunction(data){
	//		console.log(data);
	//	}
	getAjaxFunction: function(url, callback) {
		$.ajax({
			url: url,
			type: "get",
			async: true,
			dataType: "json",
			success: function(data) {
				callback(data);
			},
			error: function() {
				var id = "#errorMsg";
				var msg = "网络异常，请刷新重试！"
				appSupport.cm.ajaxErrorFunction(id, msg);
			}
		})
	},
	formatDate: function (value, fmt) {
	    var date = new Date(value);
	    var o = {
	      'M+': date.getMonth() + 1, //月份
	      'd+': date.getDate(), //日
	      'h+': date.getHours(), //小时
	      'm+': date.getMinutes(), //分
	      's+': date.getSeconds(), //秒
	      'w+': date.getDay(), //星期
	      'q+': Math.floor((date.getMonth() + 3) / 3), //季度
	      'S': date.getMilliseconds() //毫秒
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
	    for (var k in o) {
	      if (k === 'w+') {
	        if (o[k] === 0) {
	          fmt = fmt.replace('w', '周日');
	        } else if (o[k] === 1) {
	          fmt = fmt.replace('w', '周一');
	        } else if (o[k] === 2) {
	          fmt = fmt.replace('w', '周二');
	        } else if (o[k] === 3) {
	          fmt = fmt.replace('w', '周三');
	        } else if (o[k] === 4) {
	          fmt = fmt.replace('w', '周四');
	        } else if (o[k] === 5) {
	          fmt = fmt.replace('w', '周五');
	        } else if (o[k] === 6) {
	          fmt = fmt.replace('w', '周六');
	        }
	      } else if (new RegExp('(' + k + ')').test(fmt)) {
	        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
	      }
	    }
	    return fmt;
	  },

	//post请求
	postAjaxFunction: function(url, region, callback) {
		$.ajax({
			url: url,
			type: "post",
			data: region,
			async: true,
			dataType: "json",
			success: function(data) {
				callback(data);
			},
			error: function() {
				var id = "#errorMsg";
				var msg = "网络异常，请刷新重试！"
				appSupport.cm.ajaxErrorFunction(id, msg);
			}
		})
	},
	//请求函数失败调用函数
	ajaxErrorFunction: function(id, errorMessage) {
		appSupport.cm.errorMessageShow(id, errorMessage);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(id);
		}, 1000);
	},
	//	数据输出页面
	//	var aa=1;var bb=2;var cc=3;var dd=4;var ee=5;
	//	var dataId=['#a','#b','#c','#d','#e'];
	//	var dataHtml=[aa,bb,cc,dd,ee];
	erchSetData: function(dataId, dataHtml) {
		for(var i = 0; i < dataId.length; i++) {
			$(dataId[i]).html(dataHtml[i]);
		}
	},
	//验证信息
	//		var dataHtml=['','12','我的'];
	//		var errorMessage=["1值不能为空","2值不能为空","3值不能为空"];
	//		appSupport.cm.checkMessage(dataHtml,errorMessage);
	checkMessage: function(dataHtml, errorMessage, id) {
		for(var i = 0; i < dataHtml.length; i++) {
			if(dataHtml[i] == '') {
				appSupport.cm.errorMessageShow(id, errorMessage[i]);
				setTimeout(function() {
					appSupport.cm.errorMessageHide(id);
				}, 1000);
				return false;
			}
		}
	},
	//错误信息展示
	errorMessageShow: function(id, msg) {
		$(id).show().html(msg);
	},
	//错误信息消失
	errorMessageHide: function(id) {
		$(id).hide(500);
	},
	//加密
	hash: function(pswd) {
		var key = "6Tg2OaZhdpB";
		var newsPSWD = encryptByDES(pswd, key);
		var e = new RegExp("\\+", "g");
		pswd = newsPSWD.replace(e, "[j]");
		return pswd;
	},
	//加载动画
	lodingShow: function() {
		$("#loding").show().css("background", "none");
	},
	//关闭动画
	lodingHide: function() {
		$("#loding").hide();
	},
	//确认对话框
	dialog: function(content, ok, cancel) {
		var dialog = document.createElement('div');
		dialog.className = 'ui-dialog';
		var dialogContent = document.createElement('div')
		dialogContent.className = 'ui-dialog-content';
		dialogContent.innerHTML = '<div class="dialog-header">' + content + '</div>'
		var okBtn = document.createElement('button');
		okBtn.innerHTML = '确定';
		if(typeof ok === 'function') {
			okBtn.onclick = ok;
		}
		var cancelBtn = document.createElement('button');
		cancelBtn.innerHTML = '取消';
		if(typeof cancel === 'function') {
			cancelBtn.onclick = cancel;
		} else {
			cancelBtn.onclick = function() {
				document.body.removeChild(dialog);
			}
		}
		var dislogButtons = document.createElement('div');
		dislogButtons.className = 'dialog-buttons';
		document.body.appendChild(dialog);
		dialog.appendChild(dialogContent);
		dialogContent.appendChild(dislogButtons);
		dislogButtons.appendChild(okBtn);
		dislogButtons.appendChild(cancelBtn);
		$(dialog).addClass('show');
	}
};
appSupport.cm.settingRem();

obj.goLogin = function() {
    var shopId=appSupport.cm.queryString("shopId");
	var memberId = localStorage.getItem("memberId");
	if(memberId == null || memberId == undefined || memberId == '') {
		window.location.href = "login.jsp?shopId="+shopId;
	}
}

//跳转页面
function linkTo(url) {
	location.href = url;
}
//校验登录跳转野蛮
function authLinkTo(url) {
    var shopId=appSupport.cm.queryString("shopId");
	var memberId = localStorage.getItem("memberId");
	if(memberId == null || memberId == undefined || memberId == '') {
		window.location.href = "/login.jsp?shopId="+shopId;
	} else {
		location.href = url;
	}
}
//判断是否微信登陆
//obj.isWeiXin=function(){
//	var ua = window.navigator.userAgent.toLowerCase();
//		console.log(ua); //mozilla/5.0 (iphone; cpu iphone os 9_1 like mac os x) applewebkit/601.1.46 (khtml, like gecko)version/9.0 mobile/13b143 safari/601.1
//		if(ua.match(/MicroMessenger/i) == 'micromessenger') {
//			return true;
//		} else {
//			return false;
//		}
//}
//$(function(){
//	if(obj.isWeiXin()) {
//		console.log(" 是来自微信内置浏览器")
//	} else {
//		$("body").html("请在微信端打开页面");
//	}
//})
//判断是否登录
$(function() {
	//后退刷新页面
	var isPageHide = false;
	window.addEventListener('pageshow', function() {
		if(isPageHide) {
			window.location.reload();
		}
	});
	window.addEventListener('pagehide', function() {
		isPageHide = true;
	});
	//判断是否跳转购物车
    $(".footer li").on("click", function() {
        var memberType = localStorage.getItem("memberType");
        var index = $(this).index();
        var storeType = localStorage.getItem("storeType");
        var shopId=appSupport.cm.queryString("shopId");
        if (!shopId){
            shopId=$("#shopId").val();
        }
        var baseUrl=localStorage.getItem("weburl");
        if (!shopId){
            if(index == 0) {
                window.location.href = baseUrl+"/company/portalIndex.action";
            } else if(index == 1) {
                if(storeType == 4){
                    window.location.href =baseUrl+"/company/mallMerchants.jsp";
                }else{
                    window.location.href = baseUrl+"/company/portalService.action";
                }
            } else if(index == 2) {
                window.location.href = baseUrl+"/company/portalInformation.action";
            } else if(index == 3) {
                window.location.href = baseUrl+"/company/agentAccount.action";
            }
        }else {
            if (index == 0) {
                window.location.href = baseUrl + "/company/index.action?shopId=" + shopId;
            } else if (index == 1) {
                if (storeType == 4) {
                    window.location.href = baseUrl + "/company/mallMerchants.jsp";
                } else {
                    window.location.href = baseUrl + "/company/agentMall.action?shopId=" + shopId;
                }
            } else if (index == 2) {
                window.location.href = baseUrl + "/company/newsList.action?shopId=" + shopId;
            } else if (index == 3) {
                window.location.href = baseUrl + "/company/agentAccount.action?shopId=" + shopId;
            }
        }
    })
	//返回
	$(".go-back").on("click", function() {
		window.history.back();
	})
	
	//返回1
	$(".go-back-1").on("click", function() {
		window.history.back();
	})
	
	//返回2
	$(".go-back-2").on("click", function() {
		// window.location.href = "index.jsp";
        window.history.back();
	})
})

function shopCar() {
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId=$("#shopId").val();
    }
    if (!shopId) {
		$(".accountList-li2").hide();
	}
}