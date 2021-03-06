<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>开发者引导_快传融合通讯开放平台</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css" /><%@include file="/front/resource.jsp"%><script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
</head>

<body id="5">
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box doc_box">
  <div class="display_wrapper">
    <div class="doc_menu">
      <div class="menu_tit">
        <h1><span class="home"><a href="<%=path%>/doc/doc.jsp">文档首页</a></span></h1>
      </div>
       <ul>
        <li>
        	<span>新手指引<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_guide4-1.jsp">注册成为开发者</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide5-1.jsp">管理中心使用指南</a></dd>   
				<dd><a href="<%=path%>/doc/doc_guide6-1.jsp">应用创建流程</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide7-1.jsp">充值流程</a></dd>  
                <dd><a href="<%=path%>/doc/doc_guide3-1.jsp">应用审核规范</a></dd>				
                <dd><a href="<%=path%>/doc/doc_guide8-1.jsp">开发者资质审核规范</a></dd>                   
                <dd><a href="<%=path%>/doc/doc_guide2-1.jsp">开发者协议</a></dd>
        	</dl>
        </li>
        <li >
        	<span>REST API<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<dd><a href="<%=path%>/doc/doc_rest1-1.jsp">REST介绍</a></dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>账户管理</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest2-1.jsp">开发者账号信息查询</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-2.jsp">申请Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-3.jsp">释放Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-4.jsp">获取Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-5.jsp">Client信息查询(Client账号)</a></dd>
                        <dd><a href="<%=path%>/doc/doc_rest2-9.jsp">Client信息查询(手机号码)</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-6.jsp">应用话单下载</a></dd>
        				<!--<dd><a href="<%=path%>/doc/doc_rest2-7.jsp">Client话单下载</a></dd>-->
        				<dd class="last"><a href="<%=path%>/doc/doc_rest2-8.jsp">Client账号充值</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>呼叫</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></dd>
						<dd class="last"><a href="<%=path%>/doc/doc_rest3-3.jsp">语音通知</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>SMS</em></a>
        			<dl class="child">
        				<dd class="last"><a href="<%=path%>/doc/doc_rest4-1.jsp">短信验证码（模板短信）</a></dd>
        			</dl>
        		</dd>
        	</dl>
        </li>
        <li>
        	<span>Android SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_android6-1.jsp">Android开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android1-1.jsp">Android SDK介绍</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android2-1.jsp">初始化及配置接口</a></dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_android3-1.jsp">VoIP通话控制接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_android3-2.jsp">VoIP状态回调接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_android4-1.jsp">IM消息接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_android4-2.jsp">IM消息回调接口</a></dd>
        			</dl>
        		</dd>
                <dd><a href="<%=path%>/doc/doc_android7-1.jsp">视频通话控制接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_android8-1.jsp">用户在线查询能力接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_android9-1.jsp">智能验证接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_android5-1.jsp">注意事项</a></dd>
        	</dl>
        </li>
         <li>
        	<span>iOS SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_ios5-1.jsp">iOS开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_ios1-1.jsp">iOS SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>初始化及配置接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios2-1.jsp">初始化及配置</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios2-2.jsp">初始化及配置代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios3-2.jsp">VoIP能力代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios4-2.jsp">IM能力代理接口</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_ios6-1.jsp">视频通话能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios7-1.jsp">用户在线状态能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios8-1.jsp">用户在线状态能力代理接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_ios9-1.jsp">智能验证接口</a></dd>
        	</dl>
        </li>
        <li>
        	<span>Windows SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_windows5-1.jsp">Windows开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_windows1-1.jsp">Windows SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>初始化及配置接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows2-1.jsp">初始化及配置</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows2-2.jsp">初始化及配置回调函数</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows3-2.jsp">VoIP能力回调函数</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows4-2.jsp">IM能力回调函数</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_windows6-1.jsp">视频能力接口</a></dd>
        	</dl>
        </li>
        <li>
        	<span>应用服务器接口<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<!--<dd><a href="#">接收短信</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫鉴权请求接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_server7-1.jsp">语音外呼状态通知接口</a></dd>
        	</dl>
        </li>
        <!--<li><span onclick="location.href='<%=path%>/doc/doc_demo1-1.jsp'">DEMO<i class="parent">&nbsp;</i></span></li>-->
        <li><!--<span onclick="location.href='doc_errorcode1-1.html'" style="background:#fff; width:188px;">错误代码<i class="parent">&nbsp;</i></span>-->
		    <span>错误代码<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<dd><a href="<%=path%>/doc/doc_errorcode1-1.jsp">Android错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode2-1.jsp">iOS错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode3-1.jsp">Windows错误码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_errorcode4-1.jsp">REST错误码</a></dd>
        	</dl>
		</li>         
        <li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'">常见问题<i class="parent">&nbsp;</i></span></li>
		<li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'" style="background:#fff; width:203px;">开发者引导<i class="parent" style="margin-right:26px;">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt">
      <div class="pathbox"><p><a href="<%=path%>/doc/doc.jsp">帮助文档首页</a> > <span>开发者引导</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">开发者引导</span></h1>
      </div>
      <div class="display_ctn">
	    <p>初次接触快传平台，你想要了解的都在这里：</p>
        <div class="doc_tab">
          <ul class="tab_tit">
            <li class="active">平台能力</li>
            <li>价格表</li>
            <li>体验demo</li>
            <li>联系我们</li>
          </ul>
          <div class="tab_ctn">
            <ul>
              <li><a href="#" class="img"><img src="<%=path%>/doc/images/im_small_icon.png" alt="IM即时消息" />IM即时消息</a></li>
              <li><a href="<%=path%>/doc/smsCode" class="img"><img src="<%=path%>/doc/images/vcode_small_icon.png" alt="语音验证码" />语音验证码</a></li>
              <li><a href="<%=path%>/freetrial/VoIP" class="img"><img src="<%=path%>/doc/images/voip_small_icon.png" alt="VOIP通话" />VOIP通话</a></li>
              <li><a href="<%=path%>/doc/smsCode" class="img"><img src="<%=path%>/doc/images/sms_small_icon.png" alt="短信验证码" />短信验证码</a></li>
              <li><a href="<%=path%>/freetrial/VoIP" class="img"><img src="<%=path%>/doc/images/nvoice_small_icon.png" alt="互联网语音" />互联网语音</a></li>
            </ul>
            <ul style="display:none;">
              <li><a href="<%=path%>/price" class="img"><img src="<%=path%>/doc/images/price_small_icon.png" alt="我们的价格" />我们的价格</a></li>
            </ul>
            <ul style="display:none;">
              <li><a href="<%=path%>/product_service/download" class="img"><img src="<%=path%>/doc/images/download_small_icon.png" alt="下载中心" />下载中心</a></li>
            </ul>
            <ul style="display:none;">
              <li><a href="<%=path%>/about/cooperation" class="img"><img src="<%=path%>/doc/images/contact_small_icon.png" alt="联系方式" />联系方式</a></li>
              <li><a href="http://bbs.flypaas.com/forum.php" class="img"><img src="<%=path%>/doc/images/forum_small_icon.png" alt="论坛" />论坛</a></li>
            </ul>
          </div>
        </div>
        <h3 id="tgd1.1.1">开发者之旅<a href="<%=path%>/doc/doc_guide2-1.jsp">开发者协议</a></h3>
        <p>成为我们平台上的一名开发者，并且自己开发出一个具备某种通讯能力的应用，还需要以下几步： </p>
        <div class="step"> <img src="<%=path%>/doc/images/doc_step5.png" alt="开发者之旅" />
          <ul style="left:160px;">
            <li>
            <dl>
            <dd><a href="<%=path%>/user/toSign">注册</a></dd>
            <dl>
            </li>
            <li>
              <dl style="width:auto;">
                <dd>开发者中心 -> 认证信息 -> <a href="<%=path%>/doc/doc_guide8-1.jsp">开发者资质审核规范</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd>&nbsp;</dd>
              </dl>
            </li>
            <li>
              <dl style="width:auto;">
                <dd>开发者中心 -> 认证信息 -> 创建应用</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_guide3-1.jsp">应用审核规范</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <br />
        <br />
        <h3 id="tgd1.1.2">应用开发流程<a href="<%=path%>/document">文档索引</a></h3>
        <div class="step"> <img src="<%=path%>/doc/images/doc_step6.png" alt="应用开发流程" />
          <ul style="left:160px;">
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_android6-1.jsp">android SDK开发指南</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_ios5-1.jsp">ios SDK开发指南</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_windows5-1.jsp">windows SDK开发指南</a></dd>
              </dl>
            </li>
            <li style="margin-top:-12px;">
              <dl>
                <dd><a href="<%=path%>/doc/doc_android2-1.jsp">android 初始化及配置</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_ios2-1.jsp">ios 初始化及配置</a></dd>
                <dd><a href="<%=path%>/doc/doc_ios2-2.jsp">ios 初始化及配置代理接口</a></dd>
              </dl>
              <dl style="width:auto;">
                <dd><a href="<%=path%>/doc/doc_windows2-1.jsp">windows 初始化及配置</a></dd>
                <dd><a href="<%=path%>/doc/doc_windows2-2.jsp">windows 初始化及配置回调函数</a></dd>
              </dl>
            </li>
            <li style="margin-top:-18px;">
              <dl>
                <dd><a href="<%=path%>/doc/doc_android1-1.jsp">SDK接口</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest1-1.jsp">REST接口</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_server6-1.jsp">应用服务器接口</a></dd>
              </dl>
            </li>
            <li style="margin-top:-15px; margin-bottom:0px;">
              <dl>
                <dd><a href="<%=path%>/doc/doc_errorcode1-1.jsp">android错误码</a></dd>
                <dd><a href="<%=path%>/doc/doc_errorcode4-1.jsp">rest错误码</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_errorcode2-1.jsp">ios错误码</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_errorcode3-1.jsp">windows错误码</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <br />
        <br />
        <h3 id="tgd1.1.3">SDK能力浅析<a href="<%=path%>/doc/doc_windows1-1.jsp">windows SDK介绍</a><a href="<%=path%>/doc/doc_ios1-1.jsp">ios SDK介绍</a><a href="<%=path%>/doc/doc_android1-1.jsp">android SDK介绍</a></h3>
        <div class="step"> <img src="<%=path%>/doc/images/doc_step7.png" alt="SDK能力浅析" /> </div>
        <br />
        <br />
        <h3>REST接口浅析<a href="#">REST接口介绍</a></h3>
        <div class="step rest_step"> <img src="<%=path%>/doc/images/doc_step8.png" alt="SDK能力浅析" />
          <ul style="left:560px;">
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest2-1.jsp">查询开发者账号信息示例</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest2-2.jsp">申请client账号示例</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest2-3.jsp">释放client账号示例</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest2-4.jsp">获取client账号示例</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd style="margin-top:30px; margin-left:15px;"><a href="<%=path%>/doc/doc_rest2-9.jsp">根据手机查询示例</a></dd>
              </dl>
            </li>
            <li style="margin-bottom:-5px;">
              <dl>
                <dd style="margin-top:5px; margin-left:15px;"><a href="<%=path%>/doc/doc_rest2-5.jsp">根据client ID查询示例</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest2-6.jsp">下载应用话单示例</a></dd>
              </dl>
            </li>
            <li style="margin-bottom:32px;">
              <dl>
                <dd><a href="<%=path%>/doc/doc_rest2-8.jsp">client账户充值示例</a></dd>
              </dl>
            </li>
            <li style="margin-bottom:30px;">
              <dl>
                <dd><a href="<%=path%>/freetrial/callinout">双向回拨示例</a></dd>
              </dl>
            </li>
            <li style="margin-bottom:32px;">
              <dl>
                <dd><a href="<%=path%>/doc/smsCode">语音验证码示例</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/smsCode">短信验证码示例</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <br />
        <br />
        <h3 id="tgd1.1.4">应用服务器接口浅析</h3>
        <div class="step service_step">
          <ul>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></dd>
              </dl>
            </li>
            <li>
              <dl>
                <dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫请求接口</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
              </dl>
              <dl>
                <dd><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_server7-1.jsp">语音外呼状态通知接口</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <br />
        <br />
        <h3 id="tgd1.1.5">开发者常见问题<a href="<%=path%>/doc/doc_faq1-1.jsp">常见问题汇总</a></h3>
		<div class="faq_ctn doc_faq">
		<h2 class="question">Q：如何使用Demo？</h2>
		<ol class="answer">
		<b>A：</b><br/>
		<li>1. 官网注册账号，成为开发者<img src="<%=path%>/doc/images/doc_img29.png" /></li>
		<li>2. 登录后，查看测试demo信息<img src="<%=path%>/doc/images/doc_img56.png" /></li>
        <li>3. 查看测试demo下的client账号（测试client账号）<img src="<%=path%>/doc/images/doc_img57.png" /></li>
        <li>4. 为（测试）client绑定（测试）手机号码，<span style="color:#f00;">注：该测试手机号码在demo下不可重复绑定。</span><img src="<%=path%>/doc/images/doc_img58.png" /></li>
        <li>5. 下载demo客户端<img src="<%=path%>/doc/images/doc_img59.png" /></li>
        <li>6. 使用网站账号登录Demo，以Windows DEMO为例，其他版本一样<img src="<%=path%>/doc/images/doc_img60.png" /></li>
        <li>7. 选择Client账号体验相关能力，以Windows DEMO为例，其他版本一样<img src="<%=path%>/doc/images/doc_img61.png" style="margin-bottom:0px;" /><img src="<%=path%>/doc/images/doc_img62.png" /></li>		
		</ol>
		</div>
		<div class="faq_ctn">	
		<h2 class="question">Q：测试Client账号可以在自己创建的正式应用下使用吗？</h2>
		<p class="answer"><b>A：</b>不可以，分配的测试Client只是测试账号，不能做为正式Client使用，并且测试Client只可以在Demo下使用，或者在自己创建的应用下与分配的应用ID一起做测试用。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：我创建的应用要先审核，才能测试？</h2>
		<p class="answer"><b>A：</b>对，应用提交上线审核前，须提交资质认证，认证通过后，才能提交应用上线审核，审核通过后方可进行自主测试。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：关于开发过程中出现的错误问题如何解决？</h2>
		<p class="answer"><b>A：</b>可以根据返回的错误码，在官网上查找对应的错误码及描述。错误码详情请参考官网文档的错误代码部分，对Android、iOS、Windows以及REST的错误码分别做了详细描述。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：申请Client时，friendlyName和mobile两个参数是否可选？</h2>
		<p class="answer"><b>A：</b>可以，但双向回拨时，主叫Client必须绑定mobile。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：申请Client账号，如果账号friendlyName已经存在，会返回已经存在的号码ClientNumber么？</h2>
		<p class="answer"><b>A：</b>不会，会返回103104错误码，即同一应用下friendlyName重复。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：计费模式是怎样的？</h2>
		<ol class="answer">
		<b style="margin-bottom:10px;">A：</b>云平台支持对开发者账号进行计费，并且云平台支持代计费。<br />
		<li>1、若开发者采用云平台进行代计费方式，只需对Client进行虚拟充值即可，不需要对Client真实充值，云平台将对Client进行实时计费。</li>
		<li>2、若开发者采用自主计费方式，只需对开发者账号进行充值即可，开发者可以通过三个呼叫鉴权请求实现灵活的自主计费，须注意的是开发者账号余额不足将不能使用所有能力。</li>
		</ol>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：如何控制每一路的最大通话时长？</h2>
		<p class="answer"><b>A：</b>开发者可以在云平台发送的呼叫鉴权请求通知时，通过allowedcalltime参数返回每一路允许的最大通话时长，云平台将会按allowedcalltime的值控制通话时长。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：Client账号绑定的手机号码是否可以解绑？</h2>
		<p class="answer"><b>A：</b>目前不支持解绑，但是可以将该手机号码绑定的Client账号释放掉，再重新申请Client账号及绑定。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：Client账号绑定的手机号码是否唯一？</h2>
		<p class="answer"><b>A：</b>同一个应用下与Client绑定的friendlyName和手机号码必须唯一。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：发起回拨请求，fromClient一定要是我申请了的Client账号吗？</h2>
		<p class="answer"><b>A：</b>是的，并且fromClient必须是绑定了手机号码的Client账号。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：双向回拨可以不用客户端SDK接口去申请，而是由开发者的服务器将请求提交到云平台吗？</h2>
		<p class="answer"><b>A：</b>可以，调用REST的双向回拨接口即可。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：个人开发者和企业开发者有没有什么区别？</h2>
		<p class="answer"><b>A：</b>个人开发者和企业开发者权限方面会有限制，比如显号目前只对企业开发者开放。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：即时消息，支持图片不？</h2>
		<p class="answer"><b>A：</b>即时消息支持所有的消息类型以及各种应用场景需求，包括文本、图片、音频、视频、附件以及自定义的消息等。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：你们短信接口，支持上行不？</h2>
		<p class="answer"><b>A：</b>短信接口目前只支持下行，上行后续会提供。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：我们合作的话，有没有门槛费？</h2>
		<p class="answer"><b>A：</b>按需付费，0门槛费接入能力。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：如果我们现在对接一下你们的SDK，测试一下能力，我们需要怎么做？</h2>
		<p class="answer"><b>A：</b>首先，须在官网注册账号并填写测试手机号码，如果开发者用自己申请的Client账号测试能力时，须将创建的应用提交上线审核（提交之前会要求做资质审核），应用审核通过后，开发者申请的Client才能正常使用。按照sdk开发指南进行二次开发；</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：我们现在没有播放铃声的接口，是否来电的响铃需要自己做？</h2>
		<p class="answer"><b>A：</b>关于来电铃声，开发者可以通过“新电话呼入”回调来实现。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：那应用的上线和不上线有什么区别呢？</h2>
		<p class="answer"><b>A：</b>应用不上线只能用注册账号时分配的测试Client账号做其他能力测试，而应用通话审核上线后，开发者可以通过REST接口申请Client账号测试各种能力。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：申请Client接口中的clientType有0和1，我应该传入哪个？</h2>
		<p class="answer"><b>A：</b>那主要看你们是否需要我们云平台对Client做代计费，若clientType你传入0的话，就是说你们对Client自主计费 ，flypaas只对开发者账号计费; 若传1的话，就是我们平台给你们做代计费方式,flypaas对开发者账号、client同时计费，如果开发者账号或client余额不足时，呼叫失败。</p>
		</div>
		<div class="faq_ctn">
		<h2 class="question">Q：离线的消息是不是存在服务器？能存放多久？用户下次登录时就能自动收到？</h2>
		<p class="answer"><b>A：</b>能保存1个星期，用户登录就能收到（后台会去取）。</p>
		</div>
     </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgd1.1.1">1. 开发者之旅</a></li>
      <li><a href="#tgd1.1.2">2. 应用开发流程</a></li>  
      <li><a href="#tgd1.1.3">3. SDK能力浅析</a></li>  	 
      <li><a href="#tgd1.1.4">4. 应用服务器接口浅析</a></li>	
      <li><a href="#tgd1.1.5">5. 开发者常见问题</a></li>	  
    </ul>
  </div>
</div>
<div class="aside_fold" id="aside_fold" style="display:none;"><img src="<%=path%>/doc/images/aside_fold.png" />目录导航</div>
<a href="javascript:void(0);" id="go_top" style="display:none;">&nbsp;</a>
<style type="text/css">
.doc_txt .display_ctn .answer { display:none;}
</style>
<script type="text/javascript">
$(function(){

//引导tab
$(".doc_tab .tab_tit li").hover(function(){
				    var li_index = $(this).index();
					$(this).addClass("active").siblings("li").removeClass("active");
					$(".doc_tab .tab_ctn ul").eq(li_index).show().siblings("ul").hide();
				})

//点击问题，展示答案,点击其中一个，其余问题收缩
$("h2.question").click(function(){
$(this).next("p.answer").toggle();
$(this).next("ol.answer").toggle();
$(this).parent(".faq_ctn").siblings(".faq_ctn").find(".answer").hide();
})

})
</script>
<!--中间部分middle eof--> 

<!--公共底部bottom bof-->

<!--公共底部bottom eof--> 

<!--公共版权copyright bof-->
<%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->

<script type="text/javascript">
$(function(){
	$("#h_menu_5").css("color","#05c040");
})
</script>
</body>
</html>
