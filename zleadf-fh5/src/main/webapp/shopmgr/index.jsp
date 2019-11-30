<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8"/>
    <!-- 宽度设置为设备实际宽度，初始化倍数为1，最小倍数为1，最大倍数为1，用户缩放为否 -->
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
    <!-- 删除默认的苹果工具栏和菜单栏 -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- 苹果手机顶部为黑色 -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- 屏蔽浏览器自动识别数字为电话号码 -->
    <meta name="fromat-detecition" content="telephone=no"/>


    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/splash/splash-icon.png">
    <link rel="apple-touch-startup-image" href="images/splash/splash-screen.png"
          media="screen and (max-device-width: 320px)"/>
    <link rel="apple-touch-startup-image" href="images/splash/splash-screen_402x.png"
          media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)"/>
    <link rel="apple-touch-startup-image" sizes="640x1096" href="images/splash/splash-screen_403x.png"/>
    <link rel="apple-touch-startup-image" sizes="1024x748" href="images/splash/splash-screen-ipad-landscape"
          media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)"/>
    <link rel="apple-touch-startup-image" sizes="768x1004"
          href="images/splash/splash-screen-ipad-portrait.png"
          media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)"/>
    <link rel="apple-touch-startup-image" sizes="1536x2008"
          href="images/splash/splash-screen-ipad-portrait-retina.png"
          media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
    <link rel="apple-touch-startup-image" sizes="1496x2048"
          href="images/splash/splash-screen-ipad-landscape-retina.png"
          media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

    <title>classical</title>

    <link href="styles/style.css" rel="stylesheet" type="text/css">
    <link href="styles/framework.css" rel="stylesheet" type="text/css">
    <link href="styles/owl.carousel.css" rel="stylesheet" type="text/css">
    <link href="styles/owl.theme.css" rel="stylesheet" type="text/css">
    <link href="styles/colorbox.css" rel="stylesheet" type="text/css">

    <%--<script src="../../../../www.paultrifa.com/analytics/slideby.js"></script>--%>
    <script type="text/javascript" src="scripts/jquery.js"></script>
    <script type="text/javascript" src="scripts/jqueryui.js"></script>
    <script type="text/javascript" src="scripts/owl.carousel.min.js"></script>
    <script type="text/javascript" src="scripts/colorbox.js"></script>
    <script type="text/javascript" src="scripts/snap.js"></script>
    <script type="text/javascript" src="scripts/contact.js"></script>
    <script type="text/javascript" src="scripts/custom.js"></script>
    <script type="text/javascript" src="scripts/framework.js"></script>
    <script type="text/javascript" src="scripts/framework.launcher.js"></script>
    <script type="text/javascript" src="js/library/banner.js"></script>
    <script type="text/javascript" src="js/config/url.js?v=1"></script>
    <script type="text/javascript" src="js/config/comn.js?v=1"></script>
    <script type="text/javascript" src="js/config/swiper-4.1.6.min.js"></script>
    <script type="text/javascript" src="js/config/zepto.min.js"></script>
    <script type="text/javascript" src="js/config/swiper.animate1.0.3.min.js"></script>
</head>
<body>
<input type="hidden" id="memberId" value="${memberId}">
<input type="hidden" id="memberType" value="${memberType}">
<input type="hidden" id="username" value="${username}">
<input type="hidden" id="password" value="${password}">
<div id="preloader">
    <div id="status">
        <p class="center-text">
            Loading the content...
            <em>Loading depends on your connection speed!</em>
        </p>
    </div>
</div>
<div class="main-im">
    <div id="open_im" class="open-im">&nbsp;</div>
    <div class="im_main" id="im_main">
        <a href="" target="_blank" class="im-qq qq-a hqq" title="在线QQ客服">
            <div class="qq-hover-c"><img class="img-qq" src="http://demo.lanrenzhijia.com/2015/service0119/images/qq.png"></div>
            <span > QQ在线咨询</span>
        </a>
        <a href="javascript:void(0)" target="_blank" class="im-qq qq-a" title=" 在线微信客服">
            <div class="qq-hover-c"><img class="img-qq" src="http://demo.lanrenzhijia.com/2015/service0119/images/weixing-ma.jpg"></div>
            <span> 微信在线咨询</span>
        </a>
    </div>
</div>
<div class="all-elements">
    <div id="sidebar" class="page-sidebar">
        <div class="page-sidebar-scroll">
            <div class="sidebar-section">
                <p>导航</p>
                <a href="#" class="sidebar-close"></a>
            </div>
            <div class="sidebar-header">
                <a href="#" class="sidebar-logo"></a>
            </div>

            <div class="navigation-items">
                <div class="nav-item">
                    <a href="" class="home-nav">主页</a>
                </div>
                <div class="nav-item">
                    <a href="" class="features-nav">产品</a>
                </div>
                <div class="nav-item">
                    <a href="" class="media-nav">新闻</a>
                </div>
                <div class="nav-item">
                    <a href="" class="contact-nav">关于</a>
                </div>
                <div class="nav-item">
                    <a href="" class="my-nav">我的</a>
                </div>
                <div class="nav-item">
                    <a href="#" class="close-nav">Close</a>
                </div>
                <div class="sidebar-decoration"></div>

                <div class="sidebar-section">
                    <p>联系方式</p>
                    <a href="#" class="sidebar-updates"></a>
                </div>
                <div class="sidebar-notifications">
                    <div class="sidebar-green">
                        <img src="img/contactIphone.png">
                    </div>
                    <div class="sidebar-decoration"></div>
                    <div class="sidebar-green">
                        <h3>微信</h3>
                        <img src="img/erweima.png">
                    </div>
                    <div class="sidebar-decoration"></div>
                </div>
            </div>
        </div>
    </div>


    <div id="content" class="page-content">

        <div class="content-header">
            <a href="#" class="deploy-sidebar"></a>
            <a href="#" class="content-logo"></a>
            <p class="bread-crumb">开创白板时代</p>
        </div>

        <div class="content">
                <div class="container">
                    <div class="slider-controls" data-snap-ignore="true">
                    </div>
                    <%--<a href="#" class="next-slider"></a>--%>
                    <%--<a href="#" class="prev-slider"></a>--%>
                </div>

            <div class="decoration"></div>
            <div class="container no-bottom">
                <div class="section-title">
                    <h4 class="abtitle"></h4>
                    <em class="abauthor"></em>
                    <%--<strong><img src="images/misc/icons/leaf.png" width="20" alt="img"></strong>--%>
                </div>
                <p class="abcontent"></p>
            </div>
            <div class="decoration"></div>
            <div class="useInfo_title">
                <span>使用领域</span>
            </div>
                <div class="container">
                    <a href="#" class="next-quote"></a>
                    <a href="#" class="prev-quote"></a>
                    <div class="quote-slider" data-snap-ignore="true">

                    </div>
                </div>

                <div class="decoration"></div>


            <div class="recommended" onclick="gotoDetails()">
                <input class="recommendedId" type="hidden" value="">
                <div class="recommended-1">

                </div>
                <div class="recommended-2">
                    <img src="img/buy.png">
                </div>
            </div>

            <div class="decoration"></div>
            <div class="contact">
                <div class="contactDiv">
                    <div class="mobile">
                        <img src="img/contactIphone.png">
                    </div>
                    <div class="erweima">
                        <img src="img/erweima.png">
                    </div>
                </div>
            </div>
            <%--<div class="content-footer">--%>
                <%--<p class="copyright-content">Copyright 2013.<br> All rights reserved HongGu</p>--%>
                <%--<a href="#" class="go-up-footer"></a>--%>
                <%--<div class="clear"></div>--%>
            <%--</div>--%>
            <div class="footer1">
                <ul>
                    <li class="active">
                        <img src="img/nav-menu1-1.png" alt=""/>
                        <span>主页</span>
                    </li>
                    <li>
                        <img src="img/nav-menu2.png" alt=""/>
                        <span>产品</span>
                    </li>
                    <li>
                        <img src="img/nav-menu3.png" alt=""/>
                        <span>新闻</span>
                    </li>
                    <li>
                        <img src="img/nav-menu4.png" alt=""/>
                        <span>我的</span>
                    </li>
                </ul>
            </div>

        </div>
    </div>

</div>

</body>
</html>
























