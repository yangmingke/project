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
});

//公告
// $(function(){
// 	$('.message h1').click(function(){
// 		if($('.message_cont').is(":visible")==false){
// 			$('.message_cont').slideDown('slow');
// 		}else{
// 			$('.message_cont').slideUp('slow');
// 		}
// 	})
// })
</script>
<!-- 公告 
<style type="text/css">
.message{margin:0 auto;width:100%;height:auto;}
.message h1{text-align:center;height:30px;line-height:30px;cursor:pointer;background:#fffdca;color:#900000;}
.message .message_cont{display:none;}
.message_cont{height:300px;background:#a60000;width:auto;}
.message_img{margin:0 auto;width:980px;background:url(../images/ttt.jpg) no-repeat;height:300px;}
</style>
-->
</head>
<body id="this_is_the_home_page">
<!-- 公告
	<div class="message">
		<h1>2015年春节期间安排</h1>
		<div class="message_cont">
			<div class="message_img">
			</div>
		</div>
	</div>
 -->
<%@include file="/front/head.jsp" %>
<!--主体部分content bof-->
<div class="middle middle_4">
	  <div class="banner_box" id="banner_box">
  <div class="banner">
            <div class="banner_btn">
              <a style="display: none;" href="javascript:void(0)" class="prev_btn" id="prev_btn"></a>
              <a style="display: none;" href="javascript:void(0)" class="next_btn" id="next_btn"></a>
            </div>
            <ul class="banner_list" id="banner_list">
<!-- 				<li class="bm6" style="opacity: 1; z-index: 2;"> -->
<%-- 				<a href="<%=path%>/activity/codeDesc.jsp"></a> --%>
<!-- 				</li> -->
                <!--<li class="bm2"><a href="http://bbs.flypaas.com/forum.php?mod=viewthread&tid=166&extra=page=1" style="width:100%; display:block; height:332px;"></a></li>-->
                <li class="bm8" style="opacity: 1; z-index: 2;"><a href="http://mp.weixin.qq.com/s?__biz=MzA5MzkyNzEwNw==&mid=206260972&idx=1&sn=d14eb221e0d27f03fa235f4a38232c74#rd" style="width:100%; display:block; height:332px;"></a></li>
				        <li class="bm7"></li>
                <li class="bm1"></li>                
                <li class="bm3">
                  <div class="banner_link">
                    <a href="<%=path%>/user/toSign" class="reg1">立即注册</a>
                  </div>
                </li>
                <li class="bm4"></li>
                <li class="bm5">
                  <div class="banner_link">
                    <a href="<%=path%>/user/toSign" class="reg2">加入快传平台>>></a>
                  </div>
                </li>               
            </ul>
            <div class="circle_btns" id="circle_btns">
                 <a href="javascript:void(0)" class="cur"></a>
                 <a href="javascript:void(0)"></a>
                 <a href="javascript:void(0)"></a>
                 <a href="javascript:void(0)"></a>
                 <a href="javascript:void(0)"></a>
                 <a href="javascript:void(0)" class="a1"></a>
            </div>            
        </div>
  </div>
  
	<div class="items_tit items_tit_4">
    <div class="tit_wrapper">
      <ul>
         <s:if test="#session.user!=null">
	        <li class="app"><a href="<%=path%>/app/appManager" id="appEnter"><span>应用接入</span></a></li>
	        <li class="mobile_app"><a href="<%=path%>/app/appManager" id="moveAppEnter"><span>移动应用接入</span></a></li>
         </s:if>
         <s:else>
       		<li class="app"><a href="javascript:void(0)" id="appEnter"><span>应用接入</span></a></li>
       		<li class="mobile_app"><a href="javascript:void(0)" id="moveAppEnter"><span>移动应用接入</span></a></li>
       		<script type="text/javascript">
         		bindLoginBox("appEnter");
         		bindLoginBox("moveAppEnter");
         	</script>
         </s:else>
        <li class="api"><a href="http://docs.flypaas.com/doku.php"><span>API文档</span></a></li>
        <li class="join"><a href="<%=path%>/user/toSign"><span>加入快传平台</span><i>&nbsp;</i></a></li>
      </ul>
    </div>
  </div>
  <div class="clear"></div>
  <div class="items_ctn">
    <div class="ctn_wrapper">
      <dl class="dl_left">
        <dt class="create">&nbsp;<span style="line-height:20px;">创建一个需要整合快传能力的应用</span></dt>
        <dd>
          <p style="margin-bottom:38px;"> <b><a href="http://docs.flypaas.com/doku.php">开始使用该平台</a></b> <span>浏览文档和管理您的应用程序</span> </p>
        </dd>
        <dd>
          <p style="margin-bottom:0px;"> <b><a href="<%=path%>/about/cooperation">答疑</a></b> <span>获得与@快传团队和开发人员的联系</span> </p>
        </dd>
      </dl>
      <dl class="dl_mid">
        <dt>&nbsp;<span class="app">移动应用开发工具包</span></dt>
        <dd>技术让通讯更简单。100%原生的IOS和Android SDK，让移动应用轻松接入，获取通讯能力，并能提供更多。</dd>
        <p class="link"><a href="<%=path%>/product_service/download">下载移动应用SDK</a></p>
      </dl>
      <dl class="dl_right">
        <dt>&nbsp;<span class="api">REST的API</span></dt>
        <dd>REST API可以帮助您轻松获取通讯能力，使用任何客户端在任何编程语言下进行交互、编写及测试。可使您将我们的通讯能力与您现有的应用融合一体。</dd>
        <p class="link"><a href="http://docs.flypaas.com/doku.php?id=rest_api介绍">请参阅我们REST API</a></p>
      </dl>
    </div>
  </div>
  <div class="display_box voice_advantage plat_ability">
    <div class="display_tit">
      <h1><span class="platform">平台能力</span></h1>
    </div>
    <div class="display_ctn">
      <dl>
        <dt><img src="<%=path%>/front/images/plat_ab1.png" style="padding-top:20px;" /></dt>
        <dd><b><a href="<%=path %>/freetrial/multmediasms">多媒体信息</a></b></dd>
        <dd>基于IP网络的通讯方式，支持两人或多人之间的文字…</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/front/images/plat_ab2.png" style="padding-top:20px;" /></dt>
        <dd><b><a href="<%=path%>/freetrial/callinout">双向回拨</a></b></dd>
        <dd>通过PC、WEB或移动客户端经IP网络发起通话请求...</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/front/images/plat_ab3.png" style="padding-top:20px;" /></dt>
        <dd><b><a href="<%=path%>/freetrial/VoIP">互联网语音</a></b></dd>
        <dd>基于IP网络实现的点对点实时语音通话方式...</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/front/images/plat_ab4.png" style="padding-top:20px;" /></dt>
        <dd><b><a href="<%=path%>/doc/smsCode">语音验证码</a></b></dd>
        <dd>通过语音呼叫,用户接听电话后将听到语音播报验证码...</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/front/images/plat_ab5.png" style="padding-top:20px;" /></dt>
        <dd><b><a href="<%=path%>/freetrial/toll">落地电话</a></b></dd>
        <dd>通过IP网络呼叫，被叫方经传统电话线路接听的通话方式...</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/front/images/plat_ab6.png" style="padding-top:20px;" /></dt>
        <dd><b><a href="<%=path%>/freetrial/telemarket">视频通话</a></b></dd>
        <dd>基于IP网络提供点对点视频通话服务，针对不同网络情况做了...</dd>
      </dl>
    </div>
  </div> 
   
  <div class="display_box app_scene solution_scene">
    <div class="display_tit">
      <h1><span class="doc">解决方案</span></h1>
    </div>
    <div class="display_ctn">
      <dl class="dl1" onclick="window.open('<%=path%>/solution/freeVoice>')">
        <dt>免费通话解决方案</dt>
        <dd>为第三方提供免费的语音通话功能</dd>
        <img src="<%=path%>/front/images/solution1.png" width="214" height="119" alt="免费通话解决方案" />
      </dl>
      <dl class="dl2" onclick="window.open('<%=path%>/solution/ip')">
        <dt>IP客服解决方案</dt>
        <dd>让客户服务变得更加实时高效</dd>
        <img src="<%=path%>/front/images/solution2.png" width="263" height="126" alt="免费通话解决方案" />
      </dl>
      <dl class="dl3 last" onclick="window.open('<%=path%>/solution/corporateCommt')">
        <dt>企业通讯解决方案</dt>
        <dd>让企业办公变得更具移动性和时效性</dd>
        <img src="<%=path%>/front/images/solution3.png" width="218" height="117" alt="企业通讯解决方案" />
      </dl>
      <dl class="dl4" onclick="window.open('<%=path%>/solution/interRoaming')">
        <dt>国际漫游解决方案</dt>
        <dd>让国际漫游费降到最低</dd>
        <img src="<%=path%>/front/images/solution4.png" width="274" height="152" alt="国际漫游解决方案" />
      </dl>
      <dl class="dl5" onclick="window.open('<%=path%>/solution/secureCommt')">
        <dt>安全通讯解决方案</dt>
        <dd>防监听让通话安全可靠</dd>
        <img src="<%=path%>/front/images/solution5.png" width="223" height="101" alt="安全通讯解决方案" />
      </dl>
      <dl class="dl6 last" onclick="window.open('<%=path%>/solution/hiddenNum')">
        <dt>隐号匿名解决方案</dt>
        <dd>拒绝骚扰藏匿真号</dd>
        <img src="<%=path%>/front/images/solution6.png" width="125" height="81" alt="隐号匿名解决方案" />
      </dl>
    </div>
  </div>    
</div>
<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/front/foot_sk.jsp" %>
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 
<script type="text/javascript">
$(function(){
	$("#h_menu_1").css("color","#05c040");
})
</script>
</body>
</html>
