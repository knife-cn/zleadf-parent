$(function() {
	function ttp(pag,zong){
		$.ajax({
			type: "get", //提交请求的方式
			cache: true, //是否有缓存
			url: "/zlead/attr/findPageAct/"+pag+"/"+zong+"", //访问servlet的路径
			dataType: "json", //没有这个，将把后台放会的json解析成字符串
			data: {
	
			}, //把内容序列化
			async: true, //是否异步
			error: function(request) { //请求出错
			},
			success: function(res) { //获得返回值
				if(res.code == 1) {
					var lengthh=res.data.data.length;
					var str = "";
					for(var i = 0; i < lengthh;i++) {
						str+='<a href="acDetails?actId='+res.data.data[i].actId+'">';
						str += '<li>';
						str += '<div class="houd_conimg">';
						str += '<img src="' + res.data.data[i].image + '"/>';
						str += '</div>';
						str += '<p class="shuom">活动说明</p>';
						if (res.data.data[i].actContent.length>25){
							str += '<span class="shouspan">' + res.data.data[i].staticCont.substr(0,25) + '...</span>';
						}
						else {
							str += '<span class="shouspan">' + res.data.data[i].staticCont + '</span>';
						}

						str += '</li>';
						str += '</a>';
					}
					$("#houd_conul").html(str);
					if (lengthh==0)
					{

					}
					else {

						$('#page3').paging({pageNo:1,totalPage:Math.ceil(lengthh/12),callback:function(cur){
								ttp(cur,12);
							}});
					}

					$('#page3').paging({pageNo:1,totalPage:12,callback:function(cur){
							ttp(cur,12);
						}});

				}
	
			}
		})
	}
	ttp(1,12);
	
	 
	
})