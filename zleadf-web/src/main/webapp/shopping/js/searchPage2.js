//获取url中的值
var getParam = function(name){
    var search = document.location.search;
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if(null != matcher){
        try{
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        }catch(e){
            try{
                items = decodeURIComponent(matcher[1]);
            }catch(e){
                items = matcher[1];
            }
        }
    }
    return items;
};
$("#head").load("head.html");
//加载品牌，系列，分页，类型
$.ajax({
    type:"get",//提交请求的方式
    cache:true,//是否有缓存
    url:"/query/term?t_="+Math.random(),//访问servlet的路径
    dataType:"json",//没有这个，将把后台放会的json解析成字符串
    async:true,//是否异步
    error:function(request) {//请求出错
    },
    success:function(res) {//获得返回值
        if(res.code==1||res.success==true){
            var tpl="";
            $.each(res.data.brands,function(i,item){
                tpl+='<li id=tp'+item.id+'>';
                tpl+='<span>'+item.name+'</span>';
                tpl+='</li>';
            });
            $("#ppl").html(tpl);
            var apl="";
            $.each(res.data.crmPrdLists,function(i,item){
                apl+='<li id=ap'+item.id+'>';
                apl+='<span>'+item.name+'</span>';
                apl+='</li>';
            });
            $("#xl").html(apl);
            var spl="";
            $.each(res.data.cats,function(i,item){
                spl+='<li id=sp'+item.id+'>';
                spl+='<span>'+item.name+'</span>';
                spl+='</li>';
            });
            $("#xhao").html(spl);
            var fpl="";
            $.each(res.data.crmPrdModels,function(i,item){
                fpl+='<li id=fp'+item.id+'>';
                fpl+='<span>'+item.name+'</span>';
                fpl+='</li>';
            });
            $("#fnei").html(fpl);

        }
    }
})
var resou=window.sessionStorage.getItem("resou");
var yeshu="";
var pp="";
var xl="";
var xh="";
var fen="";
var ye=1;
var zong=10;
//判断是从哪调过来的
//resou没有值，代表从url或者是跳转页面
if (resou==""||resou==undefined||resou==null){
    //判断是不是选择了品牌
    pp=getParam("b");
    if (pp==""||pp==undefined||pp==null){
        //判断是不是选择了系列
        xl=getParam("l");
        if ( xl==""||xl==undefined||xl==null)
        {
            //判断是不是选择了分类
            fen=getParam("c");
            if (fen==""||fen==undefined||fen==null){
                //是跳转页面,什么也没传,要查所有
                $("#chichi").html("");
                $.ajax({
                    type:"get",//提交请求的方式
                    cache:true,//是否有缓存
                    url: "/query/goods?t_=" + Math.random(),
                    dataType:"json",//没有这个，将把后台放会的json解析成字符串
                    async: false,
                    error:function(request) {//请求出错
                        alert("请求出错");
                    },
                    success:function(res) {//获得返回值
                        if(res.code==1){
                            if(res.data.count==0)
                            {
                                //搜索没有数据！
                                alert("搜索没有数据!");
                                window.location.href="searchWu.html";
                            }
                            else
                            {
                                yeshu=Math.ceil(res.data.goods.length/10);

                            }
                        }
                        else{

                            alert(res.message);
                            if (res.message=="搜索商品数据失败")
                            {
                                //搜索搜索商品数据失败
                                window.location.href="searchWu.html";
                            }
                            else if (res.message=="用户未登录")
                            {
                                //用户未登录
                                window.location.href="Land.thml";
                            }
                            else
                            {
                                //用户未关联工厂！
                                window.location.href="Associated_factory1.html";
                            }
                        }
                    }
                })
                $('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){

                        Nochaxun(cur,10);

                    }});
                Nochaxun(1,10);


            }
            else
            {
                //选择了分类
                $.ajax({
                    type:"get",//提交请求的方式
                    cache:true,//是否有缓存
                    url:"/query/goods?c="+fen+"&t_="+Math.random(),//访问servlet的路径
                    dataType:"json",//没有这个，将把后台放会的json解析成字符串
                    async: false,
                    error:function(request) {//请求出错
                        alert("请求出错");
                    },
                    success:function(res) {//获得返回值
                        if(res.code==1){
                            if(res.data.count==0)
                            {
                                //搜索没有数据！
                                alert("搜索没有数据!");
                                window.location.href="searchWu.html";
                            }
                            else
                            {
                                yeshu=Math.ceil(res.data.goods.length/10);

                            }
                        }
                        else{

                            alert(res.message);
                            if (res.message=="搜索商品数据失败")
                            {
                                //搜索搜索商品数据失败
                                window.location.href="searchWu.html";
                            }
                            else if (res.message=="用户未登录")
                            {
                                //用户未登录
                                window.location.href="Land.thml";
                            }
                            else
                            {
                                //用户未关联工厂！
                                window.location.href="Associated_factory1.html";
                            }
                        }
                    }
                });

                $('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){

                        chaxunfen(fen,cur,10);
                    }});
                chaxunfen(fen,1,10);
            }
        }
        else
        {
            //选择了系列
            $.ajax({
                type:"get",//提交请求的方式
                cache:true,//是否有缓存
                url:"/query/goods?l="+xl+"&t_="+Math.random(),//访问servlet的路径
                dataType:"json",//没有这个，将把后台放会的json解析成字符串
                async: false,
                error:function(request) {//请求出错
                    alert("请求出错");
                },
                success:function(res) {//获得返回值
                    if(res.code==1){
                        if(res.data.count==0)
                        {
                            //搜索没有数据！
                            alert("搜索没有数据!");
                            window.location.href="searchWu.html";
                        }
                        else
                        {
                            yeshu=Math.ceil(res.data.goods.length/10);

                        }
                    }
                    else{

                        alert(res.message);
                        if (res.message=="搜索商品数据失败")
                        {
                            //搜索搜索商品数据失败
                            window.location.href="searchWu.html";
                        }
                        else if (res.message=="用户未登录")
                        {
                            //用户未登录
                            window.location.href="Land.thml";
                        }
                        else
                        {
                            //用户未关联工厂！
                            window.location.href="Associated_factory1.html";
                        }
                    }
                }
            });

            $('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){

                    chaxunxl(xl,cur,10);
                }});
            chaxunxl(xl,1,10);
        }
    }
    else{
        //选择了品牌
        $.ajax({
            type:"get",//提交请求的方式
            cache:true,//是否有缓存
            url: "/query/goods?b=" + pp + "&t_=" + Math.random(),
            dataType:"json",//没有这个，将把后台放会的json解析成字符串
            async: false,
            error:function(request) {//请求出错
                alert("请求出错");
            },
            success:function(res) {//获得返回值
                if(res.code==1){
                    if(res.data.count==0)
                    {

                        //搜索没有数据！
                        alert("搜索没有数据!");
                        window.location.href="searchWu.html";
                    }
                    else
                    {

                        yeshu=Math.ceil(res.data.goods.length/10);

                    }
                }
                else{

                    alert(res.message);
                    if (res.message=="搜索商品数据失败")
                    {
                        //搜索搜索商品数据失败
                        window.location.href="searchWu.html";
                    }
                    else if (res.message=="用户未登录")
                    {
                        //用户未登录
                        window.location.href="Land.thml";
                    }
                    else
                    {
                        //用户未关联工厂！
                        window.location.href="Associated_factory1.html";
                    }
                }
            }
        });

        $('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){

                chaxunpp(pp,cur,10);
            }});
        chaxunpp(pp,1,10);
    }
}
else
{
    //resou有值，代表是热搜
    $("#chichi").html("关于“"+resou+"”的搜索结果");

    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?key=" + resou + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async: false,
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            if(res.code==1){
                if(res.data.count==0)
                {

                    //搜索没有数据！
                    alert("搜索没有数据!");
                    window.location.href="searchWu.html";
                }
                else
                {

                    yeshu=Math.ceil(res.data.goods.length/10);

                }
            }
            else{

                alert(res.message);
                if (res.message=="搜索商品数据失败")
                {
                    //搜索搜索商品数据失败
                    window.location.href="searchWu.html";
                }
                else if (res.message=="用户未登录")
                {
                    //用户未登录
                    window.location.href="Land.thml";
                }
                else
                {
                    //用户未关联工厂！
                    window.location.href="Associated_factory1.html";
                }
            }
        }
    });

    $('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){

            chaxunkey(resou,cur,10);
        }});
    chaxunkey(resou,1,10);

}
// 点击搜索
$("#search_icon").click(function () {
    resou=$("#ttpid").val();

    if(resou==""||resou==undefined||resou==null){
        alert("输入框不能为空！");
    }
    else{
        $("#chichi").html("关于“"+resou+"”的搜索结果");
        $.ajax({
            type:"get",//提交请求的方式
            cache:true,//是否有缓存
            url: "/query/goods?key=" + resou + "&t_=" + Math.random(),
            dataType:"json",//没有这个，将把后台放会的json解析成字符串
            async:false,
            error:function(request) {//请求出错
                alert("请求出错");
            },
            success:function(res) {//获得返回值
                if(res.code==1){
                    if(res.data.count==0)
                    {

                        //搜索没有数据！
                        alert("搜索没有数据!");
                        window.location.href="searchWu.html";
                    }
                    else
                    {

                        yeshu=Math.ceil(res.data.goods.length/10);

                    }
                }
                else{

                    alert(res.message);
                    if (res.message=="搜索商品数据失败")
                    {
                        //搜索搜索商品数据失败
                        window.location.href="searchWu.html";
                    }
                    else if (res.message=="用户未登录")
                    {
                        //用户未登录
                        window.location.href="Land.thml";
                    }
                    else
                    {
                        //用户未关联工厂！
                        window.location.href="Associated_factory1.html";
                    }
                }
            }
        });
        $("#page").paging({pageNo:1,totalPage:yeshu,callback:function(cur){

                chaxunkey(resou,cur,10);

            }});

        chaxunkey(resou,1,10);
    }

});

//
//	// 点击品牌
//	$("#ppl li").click(function () {
//
//		pp=$(this).html();
//		if(resou==""||resou==null||resou==undefined)
//		{
//			//只查询品牌
//			$.ajax({
//				type:"get",//提交请求的方式
//				cache:true,//是否有缓存
//				url:"/query/goods?b="+pp+"&t_="+Math.random(),
//				dataType:"json",//没有这个，将把后台放会的json解析成字符串
//				async: false,
//				error:function(request) {//请求出错
//					alert("请求出错");
//				},
//				success:function(res) {//获得返回值
//					if(res.code==1){
//						if(res.data.count==0)
//						{
//
//							//搜索没有数据！
//							alert("搜索没有数据!");
//							window.location.href="searchWu.html";
//						}
//						else
//						{
//
//							yeshu=Math.ceil(res.data.goods.length/10);
//
//						}
//					}
//					else{
//
//						alert(res.message);
//						if (res.message=="搜索商品数据失败")
//						{
//							//搜索搜索商品数据失败
//							window.location.href="searchWu.html";
//						}
//						else if (res.message=="用户未登录")
//						{
//							//用户未登录
//							window.location.href="Land.thml";
//						}
//						else
//						{
//							//用户未关联工厂！
//							window.location.href="Associated_factory1.html";
//						}
//					}
//				}
//			});
//
//			$('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){
//
//					chaxunpp(pp,cur,10);
//			}});
//			chaxunpp(pp,1,10)
//		}
//		else
//		{
//			// 查询了关键词与品牌
//			$.ajax({
//				type:"get",//提交请求的方式
//				cache:true,//是否有缓存
//				url:"/query/goods?key="+resou+"&b="+pp+"&t_="+Math.random(),
//				dataType:"json",//没有这个，将把后台放会的json解析成字符串
//				async: false,
//				error:function(request) {//请求出错
//					alert("请求出错");
//				},
//				success:function(res) {//获得返回值
//					if(res.code==1){
//						if(res.data.count==0)
//						{
//							//搜索没有数据！
//							alert("搜索没有数据!");
//							window.location.href="searchWu.html";
//						}
//						else
//						{
//
//							yeshu=Math.ceil(res.data.goods.length/10);
//
//						}
//					}
//					else{
//
//						alert(res.message);
//						if (res.message=="搜索商品数据失败")
//						{
//							//搜索搜索商品数据失败
//							window.location.href="searchWu.html";
//						}
//						else if (res.message=="用户未登录")
//						{
//							//用户未登录
//							window.location.href="Land.thml";
//						}
//						else
//						{
//							//用户未关联工厂！
//							window.location.href="Associated_factory1.html";
//						}
//					}
//				}
//			});
//			$('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){
//					chaxunkeypp(resou,pp,cur,10);
//				}});
//			chaxunkeypp(resou,pp,1,10);
//
//		}
//
//	})

//// 点击系列
//	$("#xl li").click(function () {
//		xl=$(this).html();
//		//判断是否有关键词
//		if(resou==""||resou==undefined||resou==null){
//			//判断是否有品牌
//			if(pp==""||pp==undefined||pp==null){
//				//没有关键词，没有品牌 只查系列
//				$.ajax({
//					type:"get",//提交请求的方式
//					cache:true,//是否有缓存
//					url:"/query/goods?l="+xl+"&t_="+Math.random(),
//					dataType:"json",//没有这个，将把后台放会的json解析成字符串
//					async: false,
//					error:function(request) {//请求出错
//						alert("请求出错");
//					},
//					success:function(res) {//获得返回值
//						if(res.code==1){
//							if(res.data.count==0)
//							{
//								//搜索没有数据！
//								alert("搜索没有数据!");
//								window.location.href="searchWu.html";
//							}
//							else
//							{
//								yeshu=Math.ceil(res.data.goods.length/10);
//
//							}
//						}
//						else{
//
//							alert(res.message);
//							if (res.message=="搜索商品数据失败")
//							{
//								//搜索搜索商品数据失败
//								window.location.href="searchWu.html";
//							}
//							else if (res.message=="用户未登录")
//							{
//								//用户未登录
//								window.location.href="Land.thml";
//							}
//							else
//							{
//								//用户未关联工厂！
//								window.location.href="Associated_factory1.html";
//							}
//						}
//					}
//				});
//
//				$('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){
//						chaxunxl(xl,cur,10);
//					}});
//				chaxunxl(xl,1,10);
//
//			}
//			else
//			{
//				//没有关键词，有品牌，查品牌和系列
//
//			}
//		}
//		else{
//			//判断是否有品牌
//			if(pp==""||pp==undefined||pp==null){
//				// 有关键字，没有品牌  只查系列
//				$.ajax({
//					type:"get",//提交请求的方式
//					cache:true,//是否有缓存
//					url:"/query/goods?l="+xl+"&t_="+Math.random(),
//					dataType:"json",//没有这个，将把后台放会的json解析成字符串
//					async: false,
//					error:function(request) {//请求出错
//						alert("请求出错");
//					},
//					success:function(res) {//获得返回值
//						if(res.code==1){
//							if(res.data.count==0)
//							{
//								//搜索没有数据！
//								alert("搜索没有数据!");
//								window.location.href="searchWu.html";
//							}
//							else
//							{
//								yeshu=Math.ceil(res.data.goods.length/10);
//
//							}
//						}
//						else{
//
//							alert(res.message);
//							if (res.message=="搜索商品数据失败")
//							{
//								//搜索搜索商品数据失败
//								window.location.href="searchWu.html";
//							}
//							else if (res.message=="用户未登录")
//							{
//								//用户未登录
//								window.location.href="Land.thml";
//							}
//							else
//							{
//								//用户未关联工厂！
//								window.location.href="Associated_factory1.html";
//							}
//						}
//					}
//				});
//
//				$('#page').paging({pageNo:1,totalPage:yeshu,callback:function(cur){
//
//						chaxunxl(xl,cur,10);
//					}});
//				chaxunxl(xl,1,10);
//			}
//			else
//			{
//				// 有关键字，有品牌  要查关键字，品牌和系列
//			}
//		}
//
//
//	})

// 只有品牌与系列
function chaxunkeypp(resou,pp,ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?key=" + resou + "&b=" + pp + "&p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<p class="porut_pa">'+item.full_name+'</p>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            //加入购物车
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}

// 只有关键词与品牌
function chaxunkeypp(resou,pp,ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?key=" + resou + "&b=" + pp + "&p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<p class="porut_pa">'+item.full_name+'</p>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            //加入购物车
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}

// 什么也没有，查所有
function Nochaxun(ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<p class="porut_pa">'+item.full_name+'</p>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            //加入购物车
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}

// 只有分类 当前页 显示条数
function chaxunfen(fen,ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?c=" + fen + "&p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<p class="porut_pa">'+item.full_name+'</p>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            //加入购物车
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}
// 只有系列 当前页 显示条数
function chaxunxl(xl,ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?l=" + xl + "&p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<p class="porut_pa">'+item.full_name+'</p>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            //加入购物车
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}
// 只有品牌 当前页 显示条数
function chaxunpp(pp,ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?b=" + pp + "&p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<p class="porut_pa">'+item.full_name+'</p>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}
// 只有关键字 当前页 显示条数
function chaxunkey(resou,ye,zong){
    $.ajax({
        type:"get",//提交请求的方式
        cache:true,//是否有缓存
        url: "/query/goods?key=" + resou + "&p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        error:function(request) {//请求出错
            alert("请求出错");
        },
        success:function(res) {//获得返回值
            var tpp="";
            $.each(res.data.goods,function(i,item){
                tpp+='<li>';
                tpp+='<div class="porut_img">';
                tpp+='<img src="../../shopping/img/index/'+item.first_img+'" />';
                tpp+='<div class="porut_mo">';
                tpp+='<div class="porut_ku">';
                tpp+='<div class="porut_kua" id="mam'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="maima">立即购买</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<div class="porut_kub" id="jgo'+item.id+'">';
                tpp+='<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                tpp+='<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                tpp+='<div class="gmtk">';
                tpp+='<div class="gma">';
                tpp+='<span class="gogo">加入购物车</span>';
                tpp+='</div>';
                tpp+='<div class="xjt">';
                tpp+='<img src="../../shopping/img/searchPage/xjt.png" />';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='</div>';
                tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp+='<a href="details.html?id='+item.id+'"><p class="porut_pa">'+item.full_name+'</p></a>';
                tpp+='</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId=$(this).attr("id").substring(3);
                alert(goodsId);
                jiago(goodsId);
            })
            //加入购物车
            $(".porut_kub").click(function () {
                var gogo=$(this).attr("id").substring(3);
                alert(gogo);
                var buyType=0;

                gocart(gogo,buyType);
            })

        }
    })
}
// 立即够买
function jiago(goodsId) {
    $.ajax({
        type:"post",//提交请求的方式
        cache:true,//是否有缓存
        url: "/zlead/order/newConfirmOrder",//访问servlet的路径
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        data:{
            goodsId:goodsId,
            buyNum:1
        },
        success:function(res) {
            if (res.code==1){
                var data= res.data
                var datasss = JSON.stringify(data)
                localStorage.setItem("datas",datasss)

            }
            window.location = "orderList.html"
        }
    })
}
//加入购物车
function gocart(goodsId,buyType) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/shopcart/addShoppingCart", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            goodsId:goodsId,
            count:1,
            buyType:buyType
        },

        async: true, //是否异步
        success: function(res) {
            // alert("加入购物车成功！");
        },
        error: function(request) { //请求出错
            alert("加入购物车出错!");
        }
    })
}


// 点击事件
$("#btn").click(function(){
//  		  $("#er").show()
    $('#mco').css('height','auto');
    $(this).hide();
    $(this).siblings("#btn1").show();


})

$("#btn1").click(function() {
    $('#mco').css('height','66px');


    $(this).siblings("#btn").show();

    $(this).hide();

})
$("#btna").click(function(){

    $('#mcone').css('height','auto');

    $(this).hide();

    $(this).siblings("#btna1").show();

})



$("#btna1").click(function() {
    $('#mcone').css('height','66px');


    $(this).siblings("#btna").show();

    $(this).hide();

})
$("#btnb").click(function(){

    $('#mctwo').css('height','auto');

    $(this).hide();

    $(this).siblings("#btnb1").show();

})



$("#btnb1").click(function() {
    $('#mctwo').css('height','66px');


    $(this).siblings("#btnb").show();

    $(this).hide();

})


$("#btnc").click(function(){

    $('#mcthree').css('height','auto');

    $(this).hide();

    $(this).siblings("#btnc1").show();

})

$("#btnc1").click(function() {
    $('#mcthree').css('height','66px');

    $(this).siblings("#btnc").show();

    $(this).hide();

})