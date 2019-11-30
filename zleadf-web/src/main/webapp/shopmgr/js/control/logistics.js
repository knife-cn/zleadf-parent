$(function() {
	init();
})

function init(){
	var orderSn=appSupport.cm.queryString("orderSn");
	var html='';
    var htmlStr='';
	$.ajax({
		url: '../../zlead/logistics/realTimeLogisticsInfo',
        type: 'post',
        data: {
       	 'orderSn':orderSn
        },
        success: function(res) {
			if(res.data.orderStatus==2){
                $("#orderStatus").html("待收货");
			}else if(res.data.orderStatus==3){
                $("#orderStatus").html("已完成");
			}
            $("#comName").html(res.data.comName);
            $("#expressNu").html(res.data.expressNu);
            if(res.code==1){
                if(res.data.expressList.length>0){
                    for(var i=0;i<res.data.expressList.length;i++){
                        html+='<ul>';
                        html+='<li>'+res.data.expressList[i].time+'</li>';
                        html+='</ul>';
                        /*html+='<p>'+res.data.expressList[i].context+'</p>';*/
                    }
                    $(".logistics-div-1").html(html);
                    for(var i=0;i<res.data.expressList.length;i++){
                        htmlStr+='<ul>';
                        htmlStr+='<li>'+res.data.expressList[i].context+'</li>';
                        htmlStr+='</ul>';
                    }
                    $(".logistics-div-2").html(htmlStr);
                }else{
                    $(".logisticsInfo").html("<p style='text-align:center;line-height:3rem;'>暂无物流信息</p>");
				}
            }else{
                $(".logisticsInfo").html("<p style='text-align:center;line-height:3rem;'>暂无物流信息</p>");
			}
        }
	})
}
