<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
<meta name="baidu-site-verification" content="EtMHBfc7G8" />
<%@include file="/front/resource.jsp" %>

<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
	if(null != object){
		out.println((String)object);
	}
%>
</head>
<body id="this_is_the_home_page">
<%@include file="/front/head.jsp" %>

<script type="text/javascript" src="js/jquery_002.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/JavaScript">
$(function(){

    homeNav();
    function homeNav(){
        var PicTotal = $('#banner_list').find('li').length; // 总共几张
        var CurrentIndex = 0;                               // 当前要显示的索引
        var ToDisplayPicNumber = 1;                         // 定时器下一次执行用到的索引
        var timer = null;                                   // 定时器对象
        var hideIndex = 0;                                  // 要隐藏的索引
        var iTime = 6000;                                   // 间隔时间
        
        //移上移下显示隐藏
        $(".banner").hover(function(){
            clearTimeout(timer);
            $('.next_btn, .prev_btn').show();
        },function(){
            $('.next_btn, .prev_btn').hide();
            timer = setTimeout(PicNumClick, iTime);
            //离开从新赋值
            ToDisplayPicNumber = (CurrentIndex + 1 == PicTotal ? 0 : CurrentIndex + 1);
        });
        //下一个
        $('.next_btn').bind('click',function(){
            CurrentIndex = (CurrentIndex + 1 == PicTotal ? 0 : CurrentIndex + 1); 
            DisplayPic();
        });
        //上一个
        $('.prev_btn').bind('click',function(){
            CurrentIndex = (CurrentIndex - 1 < 0 ? PicTotal-1 : CurrentIndex -1); 
            DisplayPic();
        });
        //中间按钮
        $('#circle_btns').find('a').bind('click',function(){
            CurrentIndex = $(this).index();
            DisplayPic();
        });
        //图片切换效果
        function DisplayPic() {
            clearTimeout(timer);
            if(CurrentIndex == hideIndex){return;}
            $('#banner_list').find('li').eq(CurrentIndex).css({'opacity':1,'z-index':2});
            $('#banner_list').find('li').eq(hideIndex).css({'z-index':5}).stop(true,true).animate({'opacity':0}, 300 , function(){
                hideIndex = CurrentIndex;
                $(this).css({'z-index':1});
            });
            $('.circle_btns').find('a').eq(CurrentIndex).addClass('cur').siblings().removeClass('cur');
        }
        //循环调用自身
        function PicNumClick() {
            $("#circle_btns a").eq(ToDisplayPicNumber).trigger("click");
            ToDisplayPicNumber = (ToDisplayPicNumber + 1) % PicTotal;
            timer = setTimeout(PicNumClick,iTime);
        }
        setTimeout(PicNumClick, iTime);
    }

    //新闻资讯
    $(".tab_tit a").click(function(){
      var tit_index = $(this).index();
      $(".list_ctn .tab_ctn").eq(tit_index).show().siblings(".tab_ctn").hide();
    })
});

</script>

<!--主体部分content bof-->
<style type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,select,p,blockquote,th,td,hr { margin: 0px; padding: 0px;}
body { font-size: 12px; line-height: 1.5; font-family: 微软雅黑; background: #fff;}
h1,h2,h3,h4,h5,h6 { font-family: 微软雅黑; font-size: 100%; font-weight:normal;}
input,textarea,select,button { font-size: 12px;,font-weight: normal;}
input[type='button'],input[type='submit'],select,button { cursor: pointer;}
table { border-spacing: 0;}
address,caption,cite,code,dfn,em,th,var { font-style: normal; font-weight: normal;}
li { list-style: none;}
caption,th { text-align: left;}
q:before,q:after { content: "";}
abbr,acronym { border: 0 none; font-variant: normal;}
sup { vertical-align: text-top;}
sub { vertical-align: text-bottom;}
fieldset,img,a img,iframe { border-style: none; border-width:0;}
img { vertical-align: middle; vertical-align: middle; outline: none;}
textarea { overflow-y:auto; }
legend { color: #000;}
a { text-decoration: none; outline: none; cursor: pointer;}
a:link,a:visited { text-decoration: none;}
hr {height: 0;}
.clear { clear: both;}
.error { color:#f00; background:url("../images/error.png") 0 0 no-repeat; height:16px; display:inline-block; padding-left:22px;}

/*轮播图*/
.banner_box{ width:100%; height:600px; overflow:hidden;}
.banner_box .banner{ width:100%; height:600px; overflow:hidden; position:relative;}
.banner_box .banner_list{ width:100%; height:600px; position:relative;}
.banner_list li{ width:100%; height:600px; position:absolute; left:0; top:0; opacity:0; filter:alpha:opacity(0);}
.banner_list .bm1{ background:url(images/forum_banner1.png) 50% 0 no-repeat;}
.banner_list .bm2{ background:url(images/forum_banner2.png) 50% 0 no-repeat;}
.banner_list .bm6 a { display: inline-block; width: 100%; height: 600px;}
.banner_list .bm7{ background:url(images/banner_bg7.png) 50% 0 no-repeat;}
.banner_btn,.banner_link { width: 1400px; margin: 0 auto; position: relative;}
.banner_box .prev_btn, .banner_box .next_btn{display:block; width:31px; height:90px; position:absolute; top:255px; display:none; z-index:999; /*opacity:0.5; filter:alpha:opacity(50);*/}
.banner_box .prev_btn{ left:0px; background:url(images/sy_btns.png) 0 0px no-repeat;}
.banner_box .next_btn{ right:0px; background:url(images/sy_btns.png) -39px 0px no-repeat;}
.banner_box .prev_btn:hover{ background:url(images/sy_btns.png) 0 -94px no-repeat;}
.banner_box .next_btn:hover{ background:url(images/sy_btns.png) -39px -94px no-repeat;}
.banner_box .circle_btns{/*width:100%;*/width:190px; height:30px; position:absolute; bottom:0; left:50%; text-align:center; z-index:9; margin-left:-100px;}
.banner_box .circle_btns a{ display:inline-block; width:20px; height:20px; background:url(images/sy_btns.png) 0 -190px no-repeat; margin-right:10px; }
.banner_box .circle_btns .a1{ margin-right:0;}
.banner_box .circle_btns .cur{ background-position: -50px -190px;}
.banner_link a { position: absolute;}
.banner_link a.reg1 { top: 240px; left: 57px; display: inline-block; width: 180px; height: 64px; line-height: 64px; text-align: center; font-size: 30px; color: #fefefe; background: #a8df14;}
.banner_link a.reg2 { top: 77px; left: 734px; font-size: 24px; color: #fefefe;}

.forum_info { padding-top: 50px; overflow: hidden;}
.forum_info h1 { width: 100%; background: url("images/tit_bg.png") center center repeat-x; color: #515151; font-size: 46px; text-align: center; line-height: 46px;}
.forum_info h1 span { display: inline-block; padding: 0px 46px; background: #fff;}
.forum_info p.tips { font-size: 19px; color: #515151; text-align: center; margin-top: 10px; margin-bottom: -20px;}

.info_list { width: 980px; margin: 50px auto;}
.news_info .info_list dl { margin-bottom: 20px; overflow: hidden;}
.news_info .info_list dt { font-size: 22px; color: #575757; font-weight: bold; float: left; display: inline; width: 150px;}
.news_info .info_list dd { margin-left: 150px; margin-bottom: 10px; color: #555;}
.news_info .info_list .dd1 { font-size: 20px;}
.news_info .info_list .dd1 a { color: #555;}
.news_info .info_list .dd1 a:hover { text-decoration: underline;}
.news_info .info_list .dd2 { font-size: 14px; border-bottom: 1px #cfcdce dashed; padding-bottom: 30px;}
.news_info .info_list .tab_tit { text-align: center;}
.news_info .info_list .tab_tit a { display: inline-block; width: 36px; height: 36px; line-height: 36px; border: 1px #fff solid; border-radius: 2px; background: #fff; font-size: 16px; color: #555; }
.news_info .info_list .tab_tit .active { color: #28b092; border-color: #28b092;}
.img_display { background: url("images/forum_bg.png") center center repeat;}
.img_display h1 span { background: url("images/forum_bg.png") center center repeat;}

.tnw-gallery {
  overflow: hidden;
  margin: 50px 0 48px;
}
.tnw-gallery ul {
  padding: 20px;
  *zoom: 1;
  -moz-transform: translate3d(0, 0, 0);
  -webkit-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0)
}
.tnw-gallery ul:after {
  content: "";
  display: table;
  clear: both
}
.tnw-gallery ul li {
  float: left;
  padding: 0 10px;
}
.tnw-gallery ul li img {
  display: block;
  width: 160px;
  -moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  -moz-transform: scale(0.9, 0.9);
  -ms-transform: scale(0.9, 0.9);
  -webkit-transform: scale(0.9, 0.9);
  transform: scale(0.9, 0.9);
  -moz-transition: all 0.2s ease;
  -o-transition: all 0.2s ease;
  -webkit-transition: all 0.2s ease;
  transition: all 0.2s ease
}
.tnw-gallery ul li img:hover {
  -moz-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  -webkit-transform: scale(1, 1);
  transform: scale(1, 1)
}
.touch .tnw-gallery {
  overflow: scroll;
  overflow-y: hidden;
  -webkit-overflow-scrolling: touch
}
.touch .tnw-gallery ul li img,
.touch .tnw-gallery ul li img:hover {
  -moz-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  -webkit-transform: scale(1, 1);
  transform: scale(1, 1);
  -moz-transition: none;
  -o-transition: none;
  -webkit-transition: none;
  transition: none
}
@media screen and (min-width:768px) {
  .tnw-gallery ul {
    padding: 20px 32px
  }
  .tnw-gallery ul li img {
    width: 200px
  }
}
@media screen and (min-width:1024px) {
  .tnw-gallery ul {
    padding: 20px 40px
  }
  .tnw-gallery ul li img {
    width: 270px
  }
}

.venue-pic .title{
  margin-bottom: 0;
}
.venue-pic .tnw-gallery {
  margin-bottom: 0;
}

/* Magnific Popup CSS */
.mfp-bg {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1042;
  overflow: hidden;
  position: fixed;
  background: #fff;
  opacity: 1;
}

.mfp-wrap {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1043;
  position: fixed;
  outline: none !important;
  -webkit-backface-visibility: hidden; }

.mfp-container {
  text-align: center;
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  padding: 0 8px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box; }

.mfp-container:before {
  content: '';
  display: inline-block;
  height: 100%;
  vertical-align: middle; }

.mfp-align-top .mfp-container:before {
  display: none; }

.mfp-content {
  position: relative;
  display: inline-block;
  vertical-align: middle;
  margin: 0 auto;
  text-align: left;
  z-index: 1045; }

.mfp-inline-holder .mfp-content, .mfp-ajax-holder .mfp-content {
  width: 100%;
  cursor: auto; }

/*.mfp-ajax-cur {
  cursor: progress; }

.mfp-zoom-out-cur, .mfp-zoom-out-cur .mfp-image-holder .mfp-close {
  cursor: -moz-zoom-out;
  cursor: -webkit-zoom-out;
  cursor: zoom-out; }

.mfp-zoom {
  cursor: pointer;
  cursor: -webkit-zoom-in;
  cursor: -moz-zoom-in;
  cursor: zoom-in; }

.mfp-auto-cursor .mfp-content {
  cursor: auto; }

.mfp-close, .mfp-arrow, .mfp-preloader, .mfp-counter {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none; }*/

.mfp-loading.mfp-figure {
  display: none; }

.mfp-hide {
  display: none !important; }

.mfp-preloader {
  color: #cccccc;
  position: absolute;
  top: 50%;
  width: auto;
  text-align: center;
  margin-top: -0.8em;
  left: 8px;
  right: 8px;
  z-index: 1044; }
  .mfp-preloader a {
    color: #cccccc; }
    .mfp-preloader a:hover {
      color: white; }

.mfp-s-ready .mfp-preloader {
  display: none; }

.mfp-s-error .mfp-content {
  display: none; }

.mfp-close, .mfp-arrow {
  overflow: visible;
  cursor: pointer;
  background: url(images/mfp.png) no-repeat 50% 32px;
  border: 0;
  -webkit-appearance: none;
  display: block;
  outline: none;
  padding: 0;
  z-index: 1046;
  -webkit-box-shadow: none;
  box-shadow: none; }
button::-moz-focus-inner {
  padding: 0;
  border: 0; }

.mfp-close {
  width: 55px;
  height: 55px;
  line-height: 44px;
  position: absolute;
  right: 0;
  top: -20px;
  text-decoration: none;
  text-align: center;
  padding: 0 0 18px 10px;
  color: white;
  font-style: normal;
  font-size: 28px;
  font-family: Arial, Baskerville, monospace; }


.mfp-close-btn-in .mfp-close {
  color: #333333;
  overflow: hidden;
   }

.mfp-image-holder .mfp-close, .mfp-iframe-holder .mfp-close {
  color: #fff;
  text-align: right;
  padding-right: 6px;
  width: 100%;
  background-position: right -500px;
}
  .mfp-close:hover, .mfp-close:focus {
    background-position: 100% -630px;
}

.mfp-counter {
  position: absolute;
  top: 0;
  right: 0;
  color: #818181;
  line-height: 2;
}

.mfp-arrow {
  position: absolute;
  opacity: 0.65;
  margin: 0;
  top: 50%;
  margin-top: -55px;
  padding: 0;
  width: 90px;
  height: 110px;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0); }
  .mfp-arrow:active {
    margin-top: -54px; }
  .mfp-arrow:hover, .mfp-arrow:focus {
    opacity: 1; }
  .mfp-arrow:before, .mfp-arrow:after, .mfp-arrow .mfp-b, .mfp-arrow .mfp-a {
    content: '';
    display: block;
    width: 0;
    height: 0;
    position: absolute;
    left: 0;
    top: 0;
    margin-top: 35px;
    margin-left: 35px;
    border: medium inset transparent; }
  .mfp-arrow:after, .mfp-arrow .mfp-a {
    border-top-width: 13px;
    border-bottom-width: 13px;
    top: 8px; }
  .mfp-arrow:before, .mfp-arrow .mfp-b {
    border-top-width: 21px;
    border-bottom-width: 21px; }

button.mfp-arrow-left {
  left: 0;
}
button.mfp-arrow-left:hover {
  left: 0;
  background-position: 50% -98px;
}
  .mfp-arrow-left .mfp-a {
    /*border-right: 17px solid white;*/
    margin-left: 31px; }
 .mfp-arrow-left .mfp-b {
    margin-left: 25px;
    border-right: 27px solid #3f3f3f; }

button.mfp-arrow-right {
  right: 0;
  background-position: 40px -242px;
}
button.mfp-arrow-right:hover {
  background-position: 40px -362px;
}
  .mfp-arrow-right .mfp-a {
    /*border-left: 17px solid white;*/
    margin-left: 39px; }
  .mfp-arrow-right .mfp-b {
    border-left: 27px solid #3f3f3f; }

.mfp-iframe-holder {
  padding-top: 40px;
  padding-bottom: 40px; }
  .mfp-iframe-holder .mfp-content {
    line-height: 0;
    width: 100%;
    max-width: 900px; }
  .mfp-iframe-holder .mfp-close {
    top: -40px; }

.mfp-iframe-scaler {
  width: 100%;
  height: 0;
  overflow: hidden;
  padding-top: 56.25%; }
  .mfp-iframe-scaler iframe {
    position: absolute;
    display: block;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    -webkit-box-shadow: 0 0 8px rgba(0, 0, 0, 0.6);
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.6);
    background: black; }

/* Main image in popup */
img.mfp-img {
  width: auto;
  max-width: 100%;
  height: auto;
  display: block;
  line-height: 0;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 40px 0 40px;
  margin: 0 auto; }

/* The shadow behind the image */
.mfp-figure {
  line-height: 0; }
  .mfp-figure:after {
    content: '';
    position: absolute;
    left: 0;
    top: 40px;
    bottom: 40px;
    display: block;
    right: 0;
    width: auto;
    height: auto;
    z-index: -1;
    -webkit-box-shadow: 0 0 8px rgba(0, 0, 0, 0.6);
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.6);
    background: #444444; }
  .mfp-figure small {
    color: #bdbdbd;
    display: block;
    font-size: 12px;
    line-height: 14px; }
  .mfp-figure figure {
    margin: 0; }

.mfp-bottom-bar {
  margin-top: -36px;
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  cursor: auto; }

.mfp-title {
  text-align: left;
  line-height: 40px;
  color: #818181;
  word-wrap: break-word;
  padding-right: 36px; 
}

.mfp-image-holder .mfp-content {
  max-width: 100%; }

.mfp-gallery .mfp-image-holder .mfp-figure {
  cursor: pointer; }

.mfp-with-zoom .mfp-container,
.mfp-with-zoom.mfp-bg {
  opacity: 0;
  -webkit-backface-visibility: hidden;
  /* ideally, transition speed should match zoom duration */
  -webkit-transition: all 0.3s ease-out;
  transition: all 0.3s ease-out;
}

.mfp-with-zoom.mfp-ready .mfp-container {
    opacity: 1;
}
.mfp-with-zoom.mfp-ready.mfp-bg {
    opacity: 0.8;
}

.mfp-with-zoom.mfp-removing .mfp-container,
.mfp-with-zoom.mfp-removing.mfp-bg {
  opacity: 0;
}

@media screen and (max-width: 800px) and (orientation: landscape), screen and (max-height: 300px) {
  /**
       * Remove all paddings around the image on small screen
       */
  .mfp-img-mobile .mfp-image-holder {
    padding-left: 0;
    padding-right: 0; }
  .mfp-img-mobile img.mfp-img {
    padding: 0; }
  .mfp-img-mobile .mfp-figure {
    /* The shadow behind the image */ }
    .mfp-img-mobile .mfp-figure:after {
      top: 0;
      bottom: 0; }
    .mfp-img-mobile .mfp-figure small {
      display: inline;
      margin-left: 5px; }
  .mfp-img-mobile .mfp-bottom-bar {
    background: rgba(0, 0, 0, 0.6);
    bottom: 0;
    margin: 0;
    top: auto;
    padding: 3px 5px;
    position: fixed;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box; }
    .mfp-img-mobile .mfp-bottom-bar:empty {
      padding: 0; }
  .mfp-img-mobile .mfp-counter {
    right: 5px;
    top: 3px; }
  .mfp-img-mobile .mfp-close {
    top: 0;
    right: 0;
    width: 35px;
    height: 35px;
    line-height: 35px;
    background: rgba(0, 0, 0, 0.6);
    position: fixed;
    text-align: center;
    padding: 0; }



}

@media screen and (max-width: 768px) {
  .mfp-image-holder .mfp-close, .mfp-iframe-holder .mfp-close {
    background-size: 30px;
    background-position: right -280px;
  }
    .mfp-close:hover, .mfp-close:focus {
      background-position: right -356px;
  }
}

@media all and (max-width: 900px) {
  .mfp-arrow {
    -webkit-transform: scale(0.75);
    -ms-transform: scale(0.75);
    transform: scale(0.75); }
  .mfp-arrow-left {
    -webkit-transform-origin: 0;
    -ms-transform-origin: 0;
    transform-origin: 0; }
  .mfp-arrow-right {
    -webkit-transform-origin: 100%;
    -ms-transform-origin: 100%;
    transform-origin: 100%; }
  .mfp-container {
    padding-left: 6px;
    padding-right: 6px; } }

.mfp-ie7 .mfp-img {
  padding: 0; }
.mfp-ie7 .mfp-bottom-bar {
  width: 600px;
  left: 50%;
  margin-left: -300px;
  margin-top: 5px;
  padding-bottom: 5px; }
.mfp-ie7 .mfp-container {
  padding: 0; }
.mfp-ie7 .mfp-content {
  padding-top: 44px; }
.mfp-ie7 .mfp-close {
  top: 0;
  right: 0;
  padding-top: 0; }

/**
 * Transition
 */

.mfp-fade.mfp-bg {
    opacity: 0;
    -webkit-transition: all 250ms ease-out;
            transition: all 250ms ease-out;
}

.mfp-fade.mfp-bg.mfp-ready {
    opacity: 1;
}

.mfp-fade.mfp-bg.mfp-removing {
    opacity: 0;
}

.mfp-fade.mfp-wrap .mfp-content {
    opacity: 0;
    -webkit-transition: all 50ms ease-out;
            transition: all 250ms ease-out;
}

.mfp-fade.mfp-wrap.mfp-ready .mfp-content {
    opacity: 1;
}

.mfp-fade.mfp-wrap.mfp-removing .mfp-content {
    opacity: 0;
}
.zoom-anim-dialog {text-align: center;}
.zoom-anim-dialog iframe { display: inline-block;}


/*寻宝活动*/
.active_info .info_list .list_left { float: left; display: inline; }
.active_info .info_list .list_right { float: right; display: inline;}
.active_info .info_list dl { border:1px #dfdfdf solid;position: relative; margin-bottom: 48px;}
.active_info .info_list dd { position: absolute; bottom: 0px; left: 0px; width: 100%; height: 77px; background: #000; opacity: 0.6;}
.active_info .info_list .p1 { font-size: 26px; color: #50c093; margin-left: 10px; position: absolute; bottom: 35px;}
.active_info .info_list .p2 { font-size: 20px; color: #fff; margin-left: 10px; position: absolute; bottom: 5px;}

.guest_info { background: url("images/forum_bg.png") center center repeat;}
.guest_info h1 span { background: url("images/forum_bg.png") center center repeat;}
.guest_info .info_list { margin-top: 70px;}
.guest_info dl { width: 140px; text-align: center; float: left; display: inline; margin: 0 12px 30px 12px;}
.guest_info dl dt,.partner_info dl dd { margin-bottom: 10px;}
.guest_info dl .dd1 { font-size: 16px; color: #50c093;}
.guest_info dl .dd2 { font-size: 13px; color: #5d5d5d; font-style: italic;}

.active_table .info_list { margin-top: 70px;margin-bottom: 70px;}

.interaction_info { background: url("images/forum_bg.png") center center repeat; padding-bottom: 80px;}
.interaction_info h1 span { background: url("images/forum_bg.png") center center repeat;}
.interaction_info .list_left { float: left; display: inline; width: 490px;}
.interaction_info .list_right { float: right; display: inline; width: 463px;}
.interaction_info dl { margin-bottom: 20px;}
.interaction_info dl { margin-bottom: 40px;}
.interaction_info dl dt { float: left; display: inline; margin-right: 20px;}
.interaction_info dl dd { margin-left: 191px; margin-bottom: 15px; color: #555;}
.interaction_info dl .dd1 { font-size: 20px;}
.interaction_info dl .dd1 a { color: #555;}
.interaction_info dl .dd1 a:hover { text-decoration: underline;}
.interaction_info dl .dd2 { font-size: 14px;}
.interaction_info dl .dd3 { font-size: 14px; color: #50c093;}
.interaction_info dl .dd3 .date { color: #555; float: right; display: inline;}
</style>

<div class="forum_box">
  <div class="banner_box" id="banner_box">
        <div class="banner">
            <div class="banner_btn">
              <a style="display: none;" href="javascript:void(0)" class="prev_btn" id="prev_btn"></a>
              <a style="display: none;" href="javascript:void(0)" class="next_btn" id="next_btn"></a>
            </div>
            <ul class="banner_list" id="banner_list">
              <li class="bm1" style="opacity: 1; z-index: 2;"></li>
              <li class="bm2"><a href="http://beijing.thegmic.cn/" style="width:100%; display:block; height:332px;"></a></li>
            </ul>
            <div class="circle_btns" id="circle_btns">
                 <a href="javascript:void(0)" class="cur"></a>
                 <a href="javascript:void(0)" class="a1"></a>
            </div>
        </div>
  </div>

  <!--新闻资讯bof-->
  <div class="forum_info news_info">
    <h1><span>新闻资讯</span></h1>
    <div class="info_list">
      <div class="list_ctn">
        <div class="tab_ctn">
          <dl>
            <dt>2015.4.29</dt>
            <dd class="dd1"><a href="http://money.china.com/fin/sxy/201504/29/1701897.html?qq-pf-to=pcqq.c2c">凝聚产业力量 IN+创新高峰论坛聚焦移动通讯新趋势</a></dd>
            <dd class="dd2">移动互联网正进入一个全新技术产业周期，汇聚具有生态主导力的关键性企业正在形成强大的产业联盟，才能对产业在更宏观的角度进行布局。</dd>
          </dl>
          <dl>
            <dt>2015.4.28</dt>
            <dd class="dd1"><a href="http://money.china.com/fin/kj/201504/28/3261198.html">君联选中移动通讯行业再战互联网 参与快传近亿元B轮融资</a></dd>
            <dd class="dd2">2015全球移动互联网大会（GMIC）召开首日，快传融合通讯开放平台宣布B轮融资成功，此次融资是由君联资本领投，成为跟投，融资金额近亿元。</dd>
          </dl>
          <dl>
            <dt>2015.4.25</dt>
            <dd class="dd1"><a href="http://www.cctime.com/html/2015-4-25/20154252049509406.htm">GMIC2015看点提前曝光 ——众大咖云集“IN+创新高峰论坛”</a></dd>
            <dd class="dd2">4月28日，2015全球移动互联网大会（GMIC）即将在北京国家会议中心拉开帷幕。在GMIC的众多分会中， In+创新高峰论坛
的主题似乎与GMIC更有渊源！</dd>
          </dl>
        </div>
        <!--<div class="tab_ctn" style="display:none;">
          <dl>
            <dt>2015.4.25</dt>
            <dd class="dd1">GMIC2015看点提前曝光 ——众大咖云集“IN+创新高峰论坛”</dd>
            <dd class="dd2">4月28日，2015全球移动互联网大会（GMIC）即将在北京国家会议中心拉开帷幕。在GMIC的众多分会中， In+创新高峰论坛
的主题似乎与GMIC更有渊源！</dd>
          </dl>
          <dl>
            <dt>2015.4.25</dt>
            <dd class="dd1">GMIC2015看点提前曝光 ——众大咖云集“IN+创新高峰论坛”</dd>
            <dd class="dd2">4月28日，2015全球移动互联网大会（GMIC）即将在北京国家会议中心拉开帷幕。在GMIC的众多分会中， In+创新高峰论坛
的主题似乎与GMIC更有渊源！</dd>
          </dl>-->
        </div>
      </div>
      <!--<div class="tab_tit">
        <a href="javascript:void(0)" class="active">1</a>
        <a href="javascript:void(0)">2</a>
      </div>--> 
    </div>
  </div>
  <!--新闻资讯eof-->

  <!--大会图片bof-->
  <div class="forum_info img_display">
    <h1><span>大会图片</span></h1>
    <div class="tnw-gallery">
                <ul>                    
                    <li><a href="images/forum_img5.png"><img alt="" src="images/forum_img5s.png"></a></li>
                    <li><a href="images/forum_img6.png"><img alt="" src="images/forum_img6s.png"></a></li>
                    <li><a href="images/forum_img7.png"><img alt="" src="images/forum_img7s.png"></a></li>
                    <li><a href="images/forum_img8.png"><img alt="" src="images/forum_img8s.png"></a></li>
                    <li><a href="images/forum_img9.png"><img alt="" src="images/forum_img9s.png"></a></li>
                    <li><a href="images/forum_img12.png"><img alt="" src="images/forum_img12s.png"></a></li>
                    <li><a href="images/forum_img14.png"><img alt="" src="images/forum_img14s.png"></a></li>
                    <li><a href="images/forum_img15png"><img alt="" src="images/forum_img15s.png"></a></li>
                    <li><a href="images/forum_img16.png"><img alt="" src="images/forum_img16s.png"></a></li>
                    <li><a href="images/forum_img17.png"><img alt="" src="images/forum_img17s.png"></a></li>
                    <li><a href="images/forum_img18.png"><img alt="" src="images/forum_img18s.png"></a></li>
                    <li><a href="images/forum_img19.png"><img alt="" src="images/forum_img19s.png"></a></li>
                    <li><a href="images/forum_img24.png"><img alt="" src="images/forum_img24s.png"></a></li>
                    <li><a href="images/forum_img25.png"><img alt="" src="images/forum_img25s.png"></a></li>
                    <li><a href="images/forum_img20.png"><img alt="" src="images/forum_img20s.png"></a></li>
                    <li><a href="images/forum_img21.png"><img alt="" src="images/forum_img21s.png"></a></li>
                    <li><a href="images/forum_img22.png"><img alt="" src="images/forum_img22s.png"></a></li>
                    <li><a href="images/forum_img23.png"><img alt="" src="images/forum_img23s.png"></a></li>
                    <li><a href="images/forum_img26.png"><img alt="" src="images/forum_img26s.png"></a></li>
                    <li><a href="images/forum_img27.png"><img alt="" src="images/forum_img27s.png"></a></li>
                    <li><a href="images/forum_img28.png"><img alt="" src="images/forum_img28s.png"></a></li>
                    <li><a href="images/forum_img29.png"><img alt="" src="images/forum_img29s.png"></a></li>
                    <li><a href="images/forum_img30.png"><img alt="" src="images/forum_img30s.png"></a></li>
                    <li><a href="images/forum_img31.png"><img alt="" src="images/forum_img31s.png"></a></li>
                    <li><a href="images/forum_img32.png"><img alt="" src="images/forum_img32s.png"></a></li>
                    <li><a href="images/forum_img33.png"><img alt="" src="images/forum_img33s.png"></a></li>
                    <li><a href="images/forum_img34.png"><img alt="" src="images/forum_img34s.png"></a></li>
                    <li><a href="images/forum_img35.png"><img alt="" src="images/forum_img35s.png"></a></li>
                    <li><a href="images/forum_img36.png"><img alt="" src="images/forum_img36s.png"></a></li>
                    <li><a href="images/forum_img37.png"><img alt="" src="images/forum_img37s.png"></a></li>
                    <li><a href="images/forum_img38.png"><img alt="" src="images/forum_img38s.png"></a></li>
                    <li><a href="images/forum_img39.png"><img alt="" src="images/forum_img39s.png"></a></li>
                    <li><a href="images/forum_img43.png"><img alt="" src="images/forum_img43s.png"></a></li>
                    <li><a href="images/forum_img44.png"><img alt="" src="images/forum_img44s.png"></a></li>
                    <li><a href="images/forum_img45.png"><img alt="" src="images/forum_img45s.png"></a></li>
                    <li><a href="images/forum_img46.png"><img alt="" src="images/forum_img46s.png"></a></li>
                    <li><a href="images/forum_img47.png"><img alt="" src="images/forum_img47s.png"></a></li>
                    <li><a href="images/forum_img48.png"><img alt="" src="images/forum_img48s.png"></a></li>
                    <li><a href="images/forum_img49.png"><img alt="" src="images/forum_img49s.png"></a></li>
                    <li><a href="images/forum_img50.png"><img alt="" src="images/forum_img50s.png"></a></li>
                    <li><a href="images/forum_img51.png"><img alt="" src="images/forum_img51s.png"></a></li>
                    <li><a href="images/forum_img52.png"><img alt="" src="images/forum_img52s.png"></a></li>
                    <li><a href="images/forum_img53.png"><img alt="" src="images/forum_img53s.png"></a></li>
                    <li><a href="images/forum_img54.png"><img alt="" src="images/forum_img54s.png"></a></li>
                    <li><a href="images/forum_img55.png"><img alt="" src="images/forum_img55s.png"></a></li>
                    <li><a href="images/forum_img56.png"><img alt="" src="images/forum_img56s.png"></a></li>
                    <li><a href="images/forum_img57.png"><img alt="" src="images/forum_img57s.png"></a></li>
                    <li><a href="images/forum_img58.png"><img alt="" src="images/forum_img58s.png"></a></li>
                    <li><a href="images/forum_img59.png"><img alt="" src="images/forum_img59s.png"></a></li>
                    <li><a href="images/forum_img60.png"><img alt="" src="images/forum_img60s.png"></a></li>
                    <li><a href="images/forum_img61.png"><img alt="" src="images/forum_img61s.png"></a></li>
                    <li><a href="images/forum_img62.png"><img alt="" src="images/forum_img62s.png"></a></li>
                    <li><a href="images/forum_img63.png"><img alt="" src="images/forum_img63s.png"></a></li>
                    <li><a href="images/forum_img64.png"><img alt="" src="images/forum_img64s.png"></a></li>
                    <li><a href="images/forum_img65.png"><img alt="" src="images/forum_img65s.png"></a></li>
                </ul>
            </div>
  </div>
  <!--大会图片eof-->


  <!--寻宝馆活动一揽bof-->
  <div class="forum_info active_info">
    <h1><span>寻宝馆活动一览</span></h1>
    <p class="tips">地址：国家会议中心一楼Q40&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：2015.4.28-4.30</p>
    <div class="info_list">
      <div class="list_left">
        <dl>
          <dt><img src="images/forum_img1.png"></dt>
          <dd>
          </dd>         
            <p class="p1">活动一</p>
            <p class="p2">领取寻宝图，集齐七枚印章，换取大礼包</p>
        </dl>
        <dl>
          <dt><img src="images/forum_img3.png"></dt>
          <dd>
          </dd>
            <p class="p1">活动三</p>
            <p class="p2">萌宠、大白召唤，拍立得现场合影取照</p>
        </dl>
      </div>
      <div class="list_right">
        <dl>
          <dt><img src="images/forum_img2.png"></dt>
          <dd>
          </dd>
            <p class="p1">活动二</p>
            <p class="p2">云吞吞形象发布，扫“我”领回家</p>
        </dl>
        <dl>
          <dt><img src="images/forum_img4.png"></dt>
          <dd>
          </dd>
            <p class="p1">活动四</p>
            <p class="p2">鼓励师弱爆啦，享受按摩SPA爽歪歪</p>
        </dl>
      </div>
    </div>
  </div>
  <!--寻宝馆活动一揽eof-->

  <!--活动嘉宾bof-->
  <div class="forum_info guest_info">
    <h1><span>IN+创新高峰论坛嘉宾</span></h1>
    <div class="info_list">
      <dl style="margin-left:5px;">
        <dt><img src="images/forum_guest1.png"></dt>
        <dd class="dd1">沙重九</dd>
        <dd class="dd2">君联资本 执行董事</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest2.png"></dt>
        <dd class="dd1">常 洁</dd>
        <dd class="dd2">GSMA亚太区市场总监</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest3.png"></dt>
        <dd class="dd1">罗志坚</dd>
        <dd class="dd2">快传联合创始人</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest4.png"></dt>
        <dd class="dd1">刘 威</dd>
        <dd class="dd2">快传联合创始人</dd>
      </dl> 
      <dl>
        <dt><img src="images/forum_guest5.png"></dt>
        <dd class="dd1">贾俊杰</dd>
        <dd class="dd2">快传联合创始人</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest6.png"></dt>
        <dd class="dd1">黄海波</dd>
        <dd class="dd2">美洽创始人</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest7.png"></dt>
        <dd class="dd1">陈本峰</dd>
        <dd class="dd2">云适配创始人兼CEO</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest8.png"></dt>
        <dd class="dd1">齐俊元</dd>
        <dd class="dd2">Teambition创始人兼CEO</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest9.png"></dt>
        <dd class="dd1">王海良</dd>
        <dd class="dd2">云之家高级产品经理</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest10.png"></dt>
        <dd class="dd1">徐启胜</dd>
        <dd class="dd2">康美医疗产品总监</dd>
      </dl>
      <dl>
        <dt><img src="images/forum_guest11.png"></dt>
        <dd class="dd1">陆海涛</dd>
        <dd class="dd2">UCloud VP</dd>
      </dl>
      <dl style="margin-left:0px;">
        <dt><img src="images/forum_guest12.png"></dt>
        <dd class="dd1">刘延飞</dd>
        <dd class="dd2">OneAPM VP</dd>
      </dl>
    </div>
  </div>
  <!--活动嘉宾eof-->

  <!--活动议程bof-->
  <div class="forum_info active_table">
    <h1><span>IN+创新高峰论坛议程</span></h1>
    <p class="tips">地址：国家会议中心303A+B&nbsp;&nbsp;&nbsp;&nbsp;时间：2015.4.28</p>
    <div class="info_list">
      <img src="images/active_table.png">
    </div>
  </div>
  <!--活动议程eof-->

  <!--论坛互动bof-->
  <div class="forum_info interaction_info">
    <h1><span>IN+创新高峰论坛互动</span></h1>
    <div class="info_list">
      <div class="list_left">
        <dl>
          <dt><img src="images/forum_img10.png"></dt>
          <dd class="dd1"><a href="http://club.tech.sina.com.cn/viewthread.php?tid=10938830&extra=page%3D1&page=1">《4.28北京GMIC来了！小伙伴们约起！快传土豪君在送票啦》</a></dd>
          <dd class="dd2">全球移动互联网那么大，你想去看哪？不管去看哪，把握行业动态！</dd>
          <dd class="dd3">新浪论坛<span class="date">2015.4.23</span></dd>
        </dl>
        <dl>
          <dt><img src="images/forum_img13.png"></dt>
          <dd class="dd1"><a href="http://bbs.qianlong.com/thread-9024260-1-1.html">《818那些不可貌相的融资企业》</a></dd>
          <dd class="dd2">上市年之后，融资也开始弥漫，不要说什么高大上的BAT，身边各种不起眼的“小喽喽”也走上了融资之路。。。</dd>
          <dd class="dd3">京华论坛<span class="date">2015.4.29</span></dd>
        </dl>
        <dl>
          <dt><img src="images/forum_img40.png"></dt>
          <dd class="dd1"><a href="http://bbs.hexun.com/post_48_8028868_1_d.html">今年的GMIC干货有多少？4句话总结In+未来风向</a></dd>
          <dd class="dd2">GMIC在每个互联网人眼中就像是一个风向标，走在行业前端。每年的大会都会有大佬们。。。</dd>
          <dd class="dd3">和讯论坛<span class="date">2015.4.29</span></dd>
        </dl>
      </div>
      <div class="list_right">
        <dl>
          <dt><img src="images/forum_img11.png"></dt>
          <dd class="dd1"><a href="http://club.tech.sina.com.cn/viewthread.php?tid=10953648&pid=37644190&extra=page%3D1&frombbs=1&qq-pf-to=pcqq.c2c">《去GMIC大会的In+创新高峰论坛，脑子和兜都能满满的》</a></dd>
          <dd class="dd2">全球移动互联网那么大，你想去看哪？不管去看哪，把握行业动态！</dd>
          <dd class="dd3">新浪论坛<span class="date">2015.4.25</span></dd>
        </dl>
        <dl>
          <dt><img src="images/forum_img41.png"></dt>
          <dd class="dd1"><a href="http://www.xici.net/d216013952.htm?qq-pf-to=pcqq.c2c">《武林萌主大碰撞，萌化你的小心脏！》</a></dd>
          <dd class="dd2">3月火了大白，4月火了GMIC的云吞吞！从游戏的神经病猫，到银屏的大白。。。</dd>
          <dd class="dd3">西祠胡同<span class="date">2015.4.29</span></dd>
        </dl>
        <dl>
          <dt><img src="images/forum_img42.png"></dt>
          <dd class="dd1"><a href="http://club.tech.sina.com.cn/thread-11018809-1-1.html"> GMIC第一天轮番上演了都市、励志、伦理、偶像剧</a></dd>
          <dd class="dd2">昨天看快传的微信公众平台发的微信，真的叫人心醉啊，一个GMIC大会。。。</dd>
          <dd class="dd3">新浪论坛<span class="date">2015.4.29</span></dd>
        </dl>
      </div>
    </div>
  </div>
  <!--论坛互动eof-->

  <!--合作伙伴bof-->
  <div class="forum_info active_table">
    <h1><span>合作伙伴</span></h1>
    <div class="info_list">
      <img src="images/partners.png">
    </div>
  </div>
  <!--合作伙伴eof-->


</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot_sk.jsp" %>
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

</body>
</html>
