/**
 * Created by vcc on 2016/12/15.
 * 目标功能：在指定的父容器内渲染代码，可设定大小，手机端具有滑动效果,使用前需要调用Jquery库，面向对象实现代码
 */
//轮播图构造函数
function Carousel(opts){
    //混入继承
    defaultopts={
        index: 0,
        carousleTime:5000,
    }
    //数据接收并添加到this中
    $.extend(this,$.extend(defaultopts,opts));
    //定时器添加到this中
    this.timerId = null;
    //索引放到变量的属性中
    //this.index = 0;
    //页面初始化
    this.init();
}
//替换原型
Carousel.prototype = {
    //指向构造函数
    constructor:Carousel,
    //初始化
    init: function () {
        //渲染代码
        this.renderDOM();
        //绑定事件
        this.beindEvents();
    },
    //渲染代码
    renderDOM: function () {
        var _this = this;
        //基础代码容器
        var baseDomStr = '<div id="js_slider" class="w-slider">' +
            '<div class="slider-picture">' +
            '<div class="slider-main" id="slider_main_block"></div>' +
            '</div>' +
            '<div class="slider-ctrl" id="slider_ctrl"></div>' +
            '</div>';
        //生成Jquer变量,添加到this中
        this.$baseDomStr = $(baseDomStr);
        //图片插入
        $.each(this.data, function (i, v) {
            _this.addPicture(v.href, v.img);
        })
        //生成小圆
        this.addCircle();
        //将生成后的数据插入到页面中
        $(this.parentDom).append(this.$baseDomStr);
        //添加样式
        this.addStyle();
    },
    //绑定事件
    beindEvents: function () {
        var _this = this;
        var index = this.index;
        //自动播放
        this.autoPlay(index+1);
        $(".slider-ctrl-con").eq(index).addClass("slider-ctrl-con-active");
        $(".slider-main-img").eq(index).css("opacity",1).siblings().css("opacity",0);
        //点击和鼠标进入事件
        $(".slider-ctrl-con").on("mouseenter", function () {
            clearInterval(_this.timerId);
            index = $(this).index();
            _this.changePicture(index)();
        }).on("mouseleave", function () {
            _this.autoPlay(index+1);
        });
        //移动端滑动改变图片
        this.mobileSwipe();
    },
    //添加一张图片
    addPicture: function (href,src) {
        var addStr = '<div class="slider-main-img"><a href="[[href]]"><img src="[[src]]" alt=""/></a></div>';
        addStr = addStr.replace('[[href]]',href).replace('[[src]]',src);
        this.$baseDomStr.find(".slider-main").append(addStr);
    },
    //动态生成小圆圈
    addCircle: function () {
        var _this = this;
        $.each(this.data, function () {
            _this.$baseDomStr.find("#slider_ctrl").append($("<span class='slider-ctrl-con'></span>"));
        });
    },
    //自动轮播
    autoPlay: function (index) {
        var _this = this;
        var index = index;
        _this.timerId = setInterval(function () {
            index = _this.changePicture(index)();
            index++;
        },this.carousleTime);
    },
    //图片切换(索引变更)
    changePicture: function (index) {
        return function () {
            //索引判断
            if(index>$(".slider-main-img").length-1){index=0;};
            if(index<0){index=$(".slider-main-img").length-1;};
            //图片变更
            $(".slider-main-img").eq(index).stop().animate({opacity:1},800).siblings().stop().animate({opacity:0},700);
            //小圆点变更
            $(".slider-ctrl-con").removeClass("slider-ctrl-con-active").eq(index).addClass("slider-ctrl-con-active");
            return index;
        };
    },
    //滑动事件
    mobileSwipe: function () {
        var _this = this;
        var index = 0;
        var startX,moveX,distanceX=0;
        var dom = $(".w-slider")[0];
        dom.addEventListener("touchstart", function (e) {
            //移动端清除PC端的鼠标进入进出事件
            $(".slider-ctrl-con").off("mouseenter mouseleave");
            clearInterval(_this.timerId);
            startX = e.touches[0].clientX;
            index = $(".slider-ctrl-con-active").index();
        });
        dom.addEventListener("touchmove", function (e) {
            moveX = e.touches[0].clientX;
            distanceX = moveX - startX;
        });
        dom.addEventListener("touchend", function () {
            if(Math.abs(distanceX)>10){
                _this.changePicture(distanceX>0?--index:++index)();
                _this.autoPlay(index*1+1);
            };
            //小圆点点击变更图片
            $(".slider-ctrl-con").on("click",function(){
                clearInterval(_this.timerId);
                index = $(this).index();
                _this.changePicture(index)();
                _this.autoPlay(index);
            });
            distanceX=0
        });
    },
    //设定样式
    addStyle: function () {
        var _this = this;
        var img = $(".slider-main-img img");
        img[0].onload = function () {
            if(_this.imgWidth){
                img.width(_this.imgWidth);
                $(_this.parentDom).width(_this.imgWidth);
                $(_this.parentDom).height($(this).height());
            }else{
                $(_this.parentDom).width($(this).width());
                $(_this.parentDom).height($(this).height());
            }

        }
    },
}