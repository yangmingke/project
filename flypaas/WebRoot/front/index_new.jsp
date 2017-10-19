<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
    <%@include file="/front/resource1.jsp"%>
    <script type="text/javascript" src="<%=path%>/front/js1/jquery.animateNumber.min.js"></script>
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

    //数字动画
    var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
    $("#span1").animateNumber({
        number : 30000,
        numberStep: comma_separator_number_step
    },1500)

    $("#span2").animateNumber({
        number : 6000,
        numberStep: comma_separator_number_step
    },1500)

    $("#span3").animateNumber({
        number : 20000,
        numberStep: comma_separator_number_step
    },1500)
});

</script>
	
</head>
<body id="b-01">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content" id="item-01">
    	<div class="ft_banner banner_box" id="banner_box">
            <div class="banner">
            <ul class="banner_list" id="banner_list">                 
                <li class="bm1" style="opacity: 1; z-index: 2;">
                    <div class="bm_txt bm1_txt">
                        <h1>更简单 、更快速</h1>
                        <h2>The Simple,The Faster</h2>
                        <p class="p1">提供开放平台标准接口服务，自助集成语音、短信、即时通信能力</p>
                        <p class="p2">为用户提供更有价值的服务体验</p>
                    </div>
                </li>
                <li class="bm2">
                    <div class="bm_txt bm2_txt">
                        <h1>我是谁？你看不见我</h1>
                        <h2>Who am I ？</h2>
                        <p class="p1">始于沟通，专注融合通讯开放平台，</p>
                        <p class="p2">为千亿市值企业提供极致数据服务，融入各个沟通领域，</p>
                        <p class="p3">为你提供语音、短信、即时通讯、视频服务。</p>
                    </div>
                </li>
                <!--<li class="bm3"></li>
                <li class="bm4"></li>
                <li class="bm5"></li>       -->        
            </ul>
            <div class="circle_btns" id="circle_btns">
                 <a href="javascript:void(0)" class="cur"></a><!--<a href="javascript:void(0)"></a><a href="javascript:void(0)"></a><a href="javascript:void(0)"></a>--><a href="javascript:void(0)" class="a1"></a>
            </div>
            <div class="slide_btns">
                <a href="javascript:void(0)" class="prev_btn" id="prev_btn"></a>
                <a href="javascript:void(0)" class="next_btn" id="next_btn"></a>
            </div>-->
        </div>
    		<!--<div class="banner_bg">&nbsp;</div>-->
    	</div>
    	<div class="item_box steps_box">
		    <div class="item_box_wp about_img">
		        <%-- <h5><img src="<%=path%>/front/images1/next_steps.png" /></h5> --%>
		                <div class="container flex">
		                    <div class="flex__item"></div>
		                    <div class="network__wrold__info flex__item">
		                        <i class="icon icon__earth__white"></i>
		                        <h2 class="video__custom__title">公司简介</h2>
		                        <p class="video__custom__desc">深圳市快传技术有限公司成立于2015年12月，是全球第一家专注于实时音视频通讯加速的科技公司, 我们的目标是为开发者提供端到端延迟低于300毫秒的实时音视频通讯能力。公司通过flyPaaS开放平台，以B2B模式聚合海量网络节点资源，形成完善的实时音视频通讯加速网络，为开发者提供云加速SDK。</p>
		                        <p class="video__custom__desc">创始团队曾参与构建亿级用户的音视频通讯平台，拥有十余年音视频处理和通讯组网经验。</p>
		                    </div>
		                </div>
		    </div>
		</div>
    	<div class="item_box box1">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>融合通讯开放平台</h1>
                    <h2>为开发者提供基于API接口的分布式云计算通讯服务</h2>
                </div>
                <div class="index_1" align="center">
                    <ul>
                        <!-- <li class="li1">
                            <dl>
                                <dt>
                                    <a href="#" class="unhover">
                                        <i>&nbsp;</i>
                                        <span class="txt">短信</span>
                                    </a>
                                    <a href="/freetrial/VoiceVerificationCode" class="hover">
                                        <i>&nbsp;</i>
                                        <span class="txt">查看详情>></span>
                                    </a>
                                </dt>
                                <dd>高到达率的发送运营商短信服务，集成至现有服务中满足身份认证、帐号信息安全维护等通知类服务</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt>
                                    <a href="#" class="unhover">
                                        <i>&nbsp;</i>
                                        <span class="txt">IM</span>
                                    </a>
                                    <a href="/product_service/im" class="hover">
                                        <i>&nbsp;</i>
                                        <span class="txt">查看详情>></span>
                                    </a>
                                </dt>
                                <dd>满足亿级海量用户使用，支持单聊、群聊，并且支持自定义可扩展的语义消息媒体接口</dd>
                            </dl>
                        </li> -->
                        <li class="li3">
                            <dl>
                                <dt>
                                    <a href="#" class="unhover">
                                        <i>&nbsp;</i>
                                        <span class="txt">语音通话</span>
                                    </a>
                                    <!-- <a href="/product_service/voice" class="hover"> -->
                                    <a href="javascript:void(0)" onclick="document.getElementById('item-02').scrollIntoView();" class="hover">
                                        <i>&nbsp;</i>
                                        <span class="txt">查看详情>></span>
                                    </a>
                                </dt>
                                <dd>跨平台的互联网语音、运营商电话服务，可通过API快速集成至移动应用或企业服务中，为沟通提供便捷性。</dd>
                            </dl>
                        </li>
                        <li class="li4 last">
                            <dl>
                                <dt>
                                    <a href="#" class="unhover">
                                        <i>&nbsp;</i>
                                        <span class="txt">视频</span>
                                    </a>
                                    <!-- <a href="/freetrial/telemarket" class="hover"> -->
                                    <a href="javascript:void(0)" onclick="document.getElementById('item-03').scrollIntoView();" class="hover">
                                        <i>&nbsp;</i>
                                        <span class="txt">查看详情>></span>
                                    </a>
                                </dt>
                                <dd>低延迟率、高清远程一对一视频、多人视频服务，支持VP8、H264高清视频模式</dd>
                            </dl>
                        </li>
                    </ul>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box2">
    		<div class="item_box_wp">
    			<div class="index_2">
                    <span class="txt txt1 current">简单</span>
                    <span class="txt txt2">可靠</span>
                    <span class="txt txt3">专注</span>
                    <span class="txt txt4">全球</span>
                    <p class="txt_desc">
                        <img src="<%=path%>/front/images1/img8.png">
                        <span class="span1"><em>标准RESTFul API接口支持多平台接入，支持多终端SDK（iOS、Android、Windows），提供基于WebRTC标准的浏览器接入语音平台</em></span>
                        <span class="span2" style="display:none;"><em>7大数据中心，全国范围68个数据接入点，支持无限平滑扩容，满足分布式高冗余业务需求，遵循就近接入原则</em></span>
                        <span class="span3" style="display:none;"><em>通讯行业十年技术积累，为2亿终端用户提供底层语音技术服务，客户涵盖互联网、软件、硬件领域，根植企业服务</em></span>
                        <span class="span4" style="display:none;"><em>通讯服务到达全球230+个国家或地区，可基于IP互联网或运营商线路自由选择服务方式，利于产品国际化</em></span>
                    </p>         
                </div>
    		</div>
    	</div>

    	<div class="item_box box3">
            <div class="bg_attach1"></div>
            <div class="bg_attach2"></div>
    		<div class="item_box_wp">
    			<div class="index_3">
                    <p>
                        <img src="<%=path%>/front/images1/img9.png" />
                        <span class="span1"><em id="span1">0</em> 名开发者</span>
                        <span class="span2"><em id="span2">0</em>万次/天 接口请求</span>
                        <span class="span3">服务覆盖 <em id="span3">0</em>万终端用户</span>
                    </p>         
                </div>
    		</div>
    	</div>

        <div class="item_box box4">
            <div class="item_box_wp">
                <h3>为客户提供融合通讯服务</h3>
                <div class="index_4">
                    <ul>
                        <li><img src="<%=path%>/front/images1/logo1.png" /></li> 
                        <li><img src="<%=path%>/front/images1/logo2.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo3.png" /></li>
                        <li class="last"><img src="<%=path%>/front/images1/logo4.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo5.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo6.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo7.png" /></li>
                        <li class="last"><img src="<%=path%>/front/images1/logo8.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo9.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo10.png" /></li>
                        <li><img src="<%=path%>/front/images1/logo11.png" /></li>
                        <li class="last"><img src="<%=path%>/front/images1/logo12.png" /></li>
                        <li class="bottom"><img src="<%=path%>/front/images1/logo13.png" /></li>
                        <li class="bottom"><img src="<%=path%>/front/images1/logo14.png" /></li>
                        <li class="bottom"><img src="<%=path%>/front/images1/logo15.png" /></li>
                        <li class="last bottom"><img src="<%=path%>/front/images1/logo16.png" /></li>                   
                    </ul>
                </div>
            </div>
        </div>
	</div>   

	<!--主体部分 ft_content eof-->
	<!--语音通话-->
	<%@include file="/front/voice_new.jsp" %>
	<!--视频通话-->
	<%@include file="/front/video.jsp" %>
	<!--CAN-->
	<%@include file="/front/can.jsp" %>
	<!--解决方案-->
	<%@include file="/front/solution1_new.jsp" %>
	<!--SDK-->
	<%@include file="/front/sdk.jsp" %>
	<!--页脚-->
	<%-- <%@include file="/front/about.jsp" %> --%>
	<%@include file="/front/callus.jsp" %>
</body>
</html>