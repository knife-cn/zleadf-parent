mui.ready(function () {
    var api = '';
    var n=0;
    var modelData = {
        "data": []
    };
    var submitData = {
        "key": localStorage.goods_key?localStorage.goods_key:'',
        "b": localStorage.goods_b?localStorage.goods_b:'',
        "l": localStorage.goods_l?localStorage.goods_l:'',
        "m": localStorage.goods_m?localStorage.goods_m:'',
        "c": localStorage.goods_c?localStorage.goods_c:''
    };
    document.getElementById('searchInput').value = submitData.key;
    // 初始化数据
    // mui.ready(function () {
    //     mui.ajax(api + '/zlead/shopmgr/shopGoodsList', {
    //         type: 'get', //HTTP请求类型
    //         dataType: 'json',
    //         data:{
    //             'pageNum':1,
    //             'size':10
    //         },
    //         success: function (res) {
    //
    //             if(res){
    //                 var html = template('modelGoodsListAll',res);
    //                 $('.goodsList').append(html)
    //
    //             }
    //         },
    //         error: function (err) {
    //             console.log(err)
    //         }
    //     });
    // });

    mui.init({
        pullRefresh: {
            container: '#offCanvasContentScroll',
            // down: {
            //     style: 'circle',
            //     callback: pulldownRefresh
            // },
            up: {
                auto: true,
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });
    function pullupRefresh() {
        n+=1;
        if (!(submitData.key || submitData.b || submitData.l || submitData.m || submitData.c)) {
            mui.ajax(api + '/zlead/shopmgr/shopGoodsList', {
                type: 'get', //HTTP请求类型
                dataType: 'json',
                data:{
                    'pageNum':n,
                    'size':10
                },
                success: function (res) {

                    if(res.data&&res.data.result.length>0){
                        mui('#offCanvasContentScroll').pullRefresh().endPullupToRefresh(false);
                        var html = template('modelGoodsListAll',res);
                        $('.goodsList').append(html);

                    }else {
                        mui('#offCanvasContentScroll').pullRefresh().endPullupToRefresh(true);
                    }
                },
                error: function (err) {
                    console.log(err)
                }
            });

        } else {
            //筛选商品
            choose(submitData);
        }

    }

    function choose(submitData) {
        var data = submitData;
        // /query/goods?key=关键词&b=品牌&l=系列&m=型号&c=分类&p=1&s=20
        mui.ajax(api + '/query/goods?key=' + data.key + '&b=' + data.b + '&l=' + data.l + '&m=' + data.m + '&c=' + data.c + '&p=1&s=20', {
            // mui.ajax('data.json', {
            type: 'get', //HTTP请求类型
            dataType: 'json',
            success: function (res) {
                if (res.data.length > 0) {
                    var arr = res.data.goods;
                    var temData = {
                        "data": arr
                    }
                    mui('#offCanvasContentScroll').pullRefresh().endPullupToRefresh(false);
                    var templateResult = template("modelGoodsList", temData);
                    $('.goodsList').append(html);
                    // document.getElementById("goodsList").innerHTML = templateResult;
                }else{
                    console.log(res.message);
                    mui('#offCanvasContentScroll').pullRefresh().endPullupToRefresh(true);
                }

                // mui('.goodsListUl').on('tap', 'li', function () {
                //     console.log(this.getAttribute('data-id'))
                //     var id = this.getAttribute('data-id');
                //     mui.openWindow({
                //         url: 'goodsDetails.html?shopId=' + id
                //     });
                // });


                // mui('.navBottom').on('tap', 'li#customerNav', function () {
                //     mui.openWindow({
                //         url: 'wbIndex.html'
                //     });
                // });
                // mui('.navBottom').on('tap', 'li#wbIndexNav', function () {
                //                 //     mui.openWindow({
                //                 //         url: 'wbIndex.html'
                //                 //     });
                //                 // });


            },
            error: function (err) {
                console.log("e", err)
            }
        });
    }
    // /zlead/hgoods/hgoods

    // if (!(submitData.key || submitData.b || submitData.l || submitData.m || submitData.c)) {
    //     //获取全部商品
    //     mui.ajax(api + '/zlead/shopmgr/shopGoodsList', {
    //         // mui.ajax('data.json', {
    //         type: 'get', //HTTP请求类型
    //         dataType: 'json',
    //         success: function (res) {
    //             // var arr = res.data;
    //             if (res) {
    //                 console.log(111, res)
    //                 var arr = [];
    //                 for (var i = 0; i < res.data.length; i++) {
    //                     if (res.data[i]) {
    //                         arr.push(res.data[i]);
    //                     }
    //                 }
    //
    //                 var temData = {
    //                     "data": arr
    //                 }
    //                 var templateResult = template("modelGoodsListAll", temData);
    //                 document.getElementById("goodsList").innerHTML = templateResult;
    //             }
    //
    //             mui('.goodsListUl').on('tap', 'li', function () {
    //                 console.log(this.getAttribute('data-id'))
    //                 var id = this.getAttribute('data-id');
    //                 mui.openWindow({
    //                     url: 'goodsDetails.html?shopId=' + id
    //                 });
    //             });
    //
    //
    //             mui('.navBottom').on('tap', 'li#customerNav', function () {
    //                 mui.openWindow({
    //                     url: 'wbIndex.html'
    //                 });
    //             });
    //             mui('.navBottom').on('tap', 'li#wbIndexNav', function () {
    //                 mui.openWindow({
    //                     url: 'wbIndex.html'
    //                 });
    //             });
    //
    //
    //         },
    //         error: function (err) {
    //             console.log(err)
    //         }
    //     });
    // }else {
    //     //筛选商品
    //     choose(submitData);
    //
    // }




    //渲染搜索条件
    chooseItem();
    function chooseItem() {
        mui.ajax(api + '/query/term', {
            type: 'get', //HTTP请求类型
            dataType: 'json',
            success: function (res) {
                // var temData = res;
                var temData={
                    data:{
                        '品牌':[],
                        '品牌':[],
                        '品牌':[],
                        '品牌':[]
                    }
                };
                temData.data['品牌']=res.data.brands;
                temData.data['系列']=res.data.crmPrdLists;
                temData.data['型号']=res.data.crmPrdModels;
                temData.data['分类']=res.data.cats;

                console.log('term', temData);

                var templateResult = template("modelChooseTerm", temData);
                document.getElementById("chooseTerm").innerHTML = templateResult;


                console.log(localStorage.goods_b,localStorage.goods_l,localStorage.goods_m,localStorage.goods_c)
                $("#offCanvasSideScroll .itemUl li").each(function () {
                    var text = $(this).find('a').text();
                    console.log(222,text)
                    if(localStorage.goods_b&&(text == localStorage.goods_b)) {
                        $(this).addClass('active')
                    }
                    if(localStorage.goods_l&&(text == localStorage.goods_l)) {
                        $(this).addClass('active')
                    }
                    if(localStorage.goods_m&&(text == localStorage.goods_m)) {
                        $(this).addClass('active')
                    }
                    if(localStorage.goods_c&&(text == localStorage.goods_c)) {
                        $(this).addClass('active')
                    }
                })
            },
            error: function (err) {
                console.log(err)
            }
        });
    }


    // 主页面及侧边导航页面  内容溢出后滚动
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();

    //侧导航显示
    // mui('.mui-off-canvas-wrap').offCanvas().show();

// 点击菜单按钮 侧边当行显示
    mui(".mui-slide-in").on('tap', '.classifyBtn', function () {
        mui('.mui-off-canvas-wrap').offCanvas().show();
    })

//点击侧边导航 选项


    //input搜索
    document.getElementById('searchInput').addEventListener('click',function () {
        document.getElementById('searchInput').value ='';
    })
    document.getElementById('searchInput').addEventListener('keyup', function (event) {
        if (event.keyCode == 13) {
            // submitData.key = document.getElementById('searchInput').value;
            localStorage.setItem('goods_key',document.getElementById('searchInput').value);

            // var arrUrl = window.location.href.split("?");
            // var para = arrUrl[0];
            // window.location.href = para + "?key=" + submitData.key;

            window.location.reload();
        }
    })
    //点击分类按钮
    mui('#offCanvasSideScroll').on('tap', '.itemUl li', function () {
        // console.log(this.innerText,this.getAttribute('class'))
        var parentNode = this.parentNode.children;
        var itemType = this.getAttribute("data-item");

        if (!this.getAttribute('class')) {
            for (var i = 0; i < parentNode.length; i++) {
                parentNode[i].setAttribute('class', '');
            }
            this.setAttribute('class', 'active');

            if (itemType == "品牌") {
                localStorage.setItem('goods_b',this.innerText)
                console.log(111,localStorage.getItem("goods_b"),this.innerText);
            }
            if (itemType == "系列") {
                localStorage.setItem('goods_l',this.innerText)
            }
            if (itemType == "型号") {
                localStorage.setItem('goods_m',this.innerText)
            }
            if (itemType == "分类") {
                localStorage.setItem('goods_c',this.innerText)
            }
        } else {
            this.setAttribute('class', '');

            if (itemType == "品牌") {
                localStorage.setItem('goods_b','');
            }
            if (itemType == "系列") {
                localStorage.setItem('goods_l','');
            }
            if (itemType == "型号") {
                localStorage.setItem('goods_m','');
            }
            if (itemType == "分类") {
                localStorage.setItem('goods_c','');
            }
        }
    })
    //选择完筛选条件  点击确定按钮 关闭弹窗
    mui(".confirmBtnDv").on('tap', '#comfirmBtn', function () {
        mui('.mui-off-canvas-wrap').offCanvas().close();
        // var arrUrl = window.location.href.split("?");
        // var para = arrUrl[0];
        // window.location.href = para + "?key=" + submitData.key + "&b=" + submitData.b + "&l=" + submitData.l + "&m=" + submitData.m+ "&c=" + submitData.c+ '&p=1&s=20';
        window.location.reload()

    })
    //点击取消按钮
    mui(".confirmBtnDv").on('tap', '#cancleBtn', function () {
        var ul = document.getElementsByClassName("itemUl");
        // var li = ul.getElementsByTagName("li");

        for (var i = 0; i < ul.length; i++) {
            var li = ul[i].getElementsByTagName("li");
            for (var j = 0; j < li.length; j++) {
                li[j].setAttribute('class', '');

            }
        }
        localStorage.goods_key='';
        localStorage.goods_b = '';
        localStorage.goods_l = '';
        localStorage.goods_m = '';
        localStorage.goods_c = '';

        window.location.reload()
    })


    function getQueryString(name, needdecoed) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var lh = window.location.search;
        if (needdecoed) {
            lh = decodeURI(window.location.search)
        }
        var r = lh.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
});

