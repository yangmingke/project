<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>智能验证接口</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css"/>
<%@include file="/front/resource.jsp"%>
<script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
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
        <li>
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
         <li class="active">
        	<span>iOS SDK<i class="parent">&nbsp;</i></span>
        	<dl>
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
                <dd><a href="<%=path%>/doc/doc_ios9-1.jsp" class="active">智能验证接口</a></dd>
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
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt doc_guide">
      <div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_ios5-1.jsp">iOS SDK</a> > <span>智能验证接口</span></p></div>
      <div class="display_tit">
        <h1 style="width:300px"><span class="intro">智能验证接口</span></h1>
      </div>
      <div class="display_ctn">
        <h2 id="tgi9.1.1">1 文档说明</h2>
        <h3 id="tgi9.1.1.1">1.1 功能描述</h3>
        <p>智能验证SDK为开发者提供一个完全透明、方便、高效的验证服务，智能验证平台聚合了稳定的短信、语音验证码服务，开发者无需再开发自己的短信通道、语音网关，而且基于公共云的强大服务，使得验证能逐步避免下发短信、语音手机号的方式验证，从而更快速，更准确快速验证，解决APP验证的验证难题、提升产品体验。</p>
        <h3 id="tgi9.1.1.2">1.2 阅读对象</h3>
        <p> 本文档主要阅读对象为开发人员，包括客户端和服务端。</p>
        <h3 id="tgi9.1.1.3">1.3 业务术语</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
                <tr>
                    <th style="width:25%">术语</th>
                    <th style="width:75%">解释</th>
                </tr>
                <tr>
                    <td>下发</td>
                    <td><span>智能验证平台使用自有的聚合SMS、语音能力发送短信或者发起呼叫告知用户验证码</span></td>
                </tr>
                <tr>
                    <td>异步通知</td>
                    <td><span>在智能验证平台正确验证过手机号后，在完成返回给APP通知的同时，执行异步线程通知开发者服务器验证结果</span></td>
                </tr>
                <tr>
                    <td>签名</td>
                    <td><span>本文档中涉及的签名都是以MD5的32位小写格式，签名字符串(开发者主账号+应用ID+手机号+应用配置的密钥)</span></td>
                </tr>
            </tbody>
        </table>
        <h2 id="tgi9.1.2">2 开发前准备</h2>
        <h3 id="tgi9.1.2.1">2.1 注册flypaas用户账号</h3>
        <p>登录www.flypaas.com官网，注册（已经有账号的跳过）</p>
        <p><img src="<%=path%>/doc/images/doc_img78.png"></p>
        <p>flypaas运营人员审核后，可执行下一步创建应用</p>
        <h3 id="tgi9.1.2.2">2.2 创建应用</h3>
        <p>登录后管理中心，点击“应用中心”—》“创建应用”</p>
        <p><img src="<%=path%>/doc/images/doc_img79.png"></p>
        <p>配置项目:</p>
        <p><img src="<%=path%>/doc/images/doc_img80.png"></p>
        <p>指定智能验证配置项：</p>
        <p><img src="<%=path%>/doc/images/doc_img81.png"></p>
        <h3 id="tgi9.1.2.3">2.3 创建智能验证短信模板</h3>
        <p>每个应用下可创建一个（只能有一个）智能验证短信的模板</p>
        <p><img src="<%=path%>/doc/images/doc_img82.png"></p>
        <p><img src="<%=path%>/doc/images/doc_img83.png" ></p>
        <h3 id="tgi9.1.2.4">2.4 开发环境准备</h3>
        <b id="tgi9.1.2.4.1">2.4.1 新建工程</b>
        <p>创建一个iPhone工程需要在Mac系统下面安装Xcode的软件，Xcode安装完成后可以在桌面的快速启动栏中的"Launchpad"中或者应用程序中找到一个图标<img src="<%=path%>/doc/images/doc_img6.png" style="width:auto;border:none;margin:0px;padding:0px;" />看到后单击或者双击即可打开软件，Xcode版本最小需要4.2。</p>
        <p>打开Xcode后的界面可以看到几个选项，创建工程需要选择"Create a newXcode project",如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img7.png" style="width:auto;" /></p>
        <p>也可以根据菜单创建工程，依次选择XCode菜单栏中的FiLe->New->Project，如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img8.png" style="width:auto;" />
        </p>
        <p>选择完后弹出的界面中选择左边的"iOS"下面的"Application",然后选择右边的"SingLeViewAppLication"进行双击或者点击右下角"Next", 如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img9.png" width="500" />
        </p>
        <p>在完成后弹出的界面中的"ProductName"中填写你的工程名，如下图所示:  <br />
        <img src="<%=path%>/doc/images/doc_img10.png" width="500" />
        </p>
        <p>添加完成后点击右下角的”Next”，在弹出的对话框中选择你需要把工程放置的位置。这样一个工程就初步的创建完成。</p>
        <b id="tgi9.1.2.4.2">2.4.2 导入UCSSDK</b>
        <p>创建完成工程后，把SDK包里面的ucssdk文件夹拷贝到新创建的工程路径下面，然后在工程目录结构中，右键选择Add Files to “UCSVoipDemo”(工程名称，在弹出的对话框中选择新创建的工程文件夹下的sdk这个文件夹，即前面拷贝的文件夹)。或者将这个文件夹拖入XCode 工程目录结构中，<br />
        <img src="<%=path%>/doc/images/doc_img11.png" style="width:auto;" /></p>
        <p>在弹出的界面中勾选Copy items into destination group’s folder(ifneeded),并确保Add To Targets勾选相应的target。 相应的操作请看下图中所示：<br />
        <img src="<%=path%>/doc/images/doc_img12.png" style="width:auto;" />
        </p>
        <b id="tgi9.1.2.4.3">2.4.3 配置工程信息</b>
        <p><b>• 添加依赖框架(Frameworks)</b><br/>
        UCSSDK的实现，依赖了一些系统框架，在开发应用时，要在工程里加入这些框架。开发者首先点击工程右边的工程名,然后在工程名右边 依次选择TARGETS->BuiLd Phases->Link Binary With Libraries,展开LinkBinary With Libraries后点击展开后下面的"+"来添加下面的依赖项:<br />
        <img src="<%=path%>/doc/images/doc_img13.png" />
        </p>
        <p>添加步骤如图所示:<br />
        <img src="<%=path%>/doc/images/doc_img14.png" /><br /><br />
        </p>
        <p><b>• 编译器设置</b><br/>
        在xcode5.1环境下，首先点击工程右边的工程名,然后在工程名右边依次选择TARGETS、Build Settings LLVM 5.1- Language C++”中的”C++ Standard Library".存后面的选择框中选择libstdc++(GNU C++ standard Library)”选项，如果默认是则不需要修改。步骤如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img16.png" />
        </p>
        <p><b>• 编译器链接设置</b><br/>
        在xcode5.1环境下，首先点击工程右边的工程名,然后在工程名右边依次选择TARGETS、Build Settings Linking中的Other Linker Flags. 中填入-ObjC。 如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img63.png" style="width:auto;" /><br />
        <img src="<%=path%>/doc/images/doc_img15.png" style="width:auto;" />
        </p>
        <b id="tgi9.1.2.4.4">2.4.4 编写代码</b>
        <p>介绍代码的实现过程，也可参考Demo的代码实现。</p>
        <h2 id="tgi9.1.3">3 功能演示</h2>
        <h3 id="tgi9.1.3.1">3.1 下发验证码场景</h3>
        <p>当用户手机未通过智能验证时，需要下发短信/语音验证码的方式验证手机号的有效性。</p>
        <p><img src="<%=path%>/doc/images/doc_img87.png"></p>
        <b>流程说明：</b>
        <p>1) 请求1的流程是发生在开发者自己的客户端和服务器，主要检测手机是否已经注册，如果未注册，则返回签名作为请求2中调用智能验证SDK的参数；</p>
        <p>2) 请求2 执行下发验证码的操作，具体接口参数详见“接口调用“中的”请求验证“接口说明；</p>
        <p>3) 请求3为提交验证码验证结果，检查用户的手机有效性；</p>
        <p>4) 异步通知结果详见“5 服务器异步通知说明”；</p>
        <h3 id="tgi9.1.3.2">3.2 智能验证通过场景</h3>
        <p><img src="<%=path%>/doc/images/doc_img88.png"></p>
        <b>流程说明：</b>
        <p>1) 请求1的流程是发生在开发者自己的客户端和服务器，主要检测手机是否已经注册，如果未注册，则返回签名作为请求2中调用智能验证SDK的参数；</p>
        <p>2) 请求2 执行下发验证码的操作，具体接口参数详见“接口调用“中的”请求验证“接口说明；</p>
        <p>3) 请求3为提交验证码验证结果，检查用户的手机有效性；</p>
        <p>4) 异步通知结果详见“5 服务器异步通知说明”；</p>
        <h2 id="tgi9.1.4">4 接口调用</h2>
        <h3 id="tgi9.1.4.1">4.1 初始化</h3>
        <pre>
          <code>
/**
 * 初始化实例及代理设置
 */
<span class="key_label">- (UCSVerifyService *)initWithDelegate:(id<UCSVerifyEventDelegate>)delegate;</span></code>
        </pre>
        <h3 id="tgi9.1.4.2">4.2 请求验证</h3>
        <b id="tgi9.1.4.2.1">4.2.1 功能描述</b>
        <p>用户在收到短信或语音下发的验证码后，输入提交验证，APP调用SDK来完成验证码的校验</p>
        <b id="tgi9.1.4.2.2">4.2.2 接口说明</b>
        <pre>
          <code>
<span class="key_label">-(void)getVerificationCode:(NSString *)sid withAppid:(NSString *)appid withAppName:(NSString *)appName withCodetype:(int)codetype withPhone:(NSString *)phone withSeconds:(int)seconds withBusiness:(int)business withSign:(NSString *)sign;</span></code>
        </pre>
        <p>参数说明：</p>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
                <tr>
                    <th style="width:35%">名称</th>
                    <th style="width:65%">说明</th>
                </tr>
                <tr>
                    <td>sid</td>
                    <td><span>开发者主账号</span></td>
                </tr>
                <tr>
                    <td>appid</td>
                    <td><span>开发者应用ID</span></td>
                </tr>
                <tr>
                    <td>appName</td>
                    <td><span>APP包名</span></td>
                </tr>
                <tr>
                    <td>codetype</td>
                    <td><span>保留字段（固定为1）</span></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><span>手机号</span></td>
                </tr>
                <tr>
                    <td>seconds</td>
                    <td><span>替换模板中的“有效时间”参数，单位在模板里自定义</span><span>如模板：你注册的验证码{1}，请在{2}（秒/分钟）内输入。此处参数会自动替换{2}参数</span></td>
                </tr>
                <tr>
                    <td>business</td>
                    <td><span>验证业务类型，当前智能验证业务参数值为1</span></td>
                </tr>
                <tr>
                    <td>sign</td>
                    <td><span>签名</span></td>
                </tr>
            </tbody>
        </table>
        <b id="tgi9.1.4.2.3">4.2.3 调用示例</b>
        <pre>
          <code>
//获取验证码
<span class="key_label">
-(void)requestCode
{ <span style="color:#aaa; font-style:italic; display:block;">//@"d5580902cab4d8853b06644a55ef720a" </span>withAppid:@"6f06242e1fea4c699dcc9124ad4421a5"
    [self.ucsCallService getVerificationCode:@"4c1990a5c1ad2674bc94bc39a6fd0699" withAppid:@"efb7e1de9da649fa83881afea2841cd7" withAppName:@"测试DEMO" withCodetype:1 withPhone:self.txt_codePhone.text withSeconds:60 withBusiness:1 withSign:signString];
    
}
</span></code>
        </pre>
        <h3 id="tgi9.1.4.3">4.3 提交验证码验证</h3>
        <b id="tgi9.1.4.3.1">4.3.1 功能描述</b>
        <p>用户在收到短信或语音下发的验证码后，输入提交验证，APP调用SDK来完成验证码的校验</p>
        <b id="tgi9.1.4.3.2">4.3.2 接口说明</b>
        <pre>
          <code>
<span class="key_label">-(void)doVerificationCode:(NSString *)sid withAppid:(NSString *)appid withPhone:(NSString *)phone withVerifycode:(NSString *)verifycode;
</span></code>
        </pre>
        <p>参数说明：</p>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
                <tr>
                    <th style="width:35%">名称</th>
                    <th style="width:65%">说明</th>
                </tr>
                <tr>
                    <td>sid</td>
                    <td><span>开发者主账号</span></td>
                </tr>
                <tr>
                    <td>appid</td>
                    <td><span>开发者应用ID</span></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><span>用户手机号</span></td>
                </tr>
                <tr>
                    <td>verifycode</td>
                    <td><span>用户输入的验证码</span></td>
                </tr>
            </tbody>
        </table>
        <b id="tgi9.1.4.3.3">4.3.3 调用示例</b>
        <pre>
          <code>
//点击“下一步” 验证
<span class="key_label">
- (IBAction)bt_Verfy:(id)sender {
    [self.txt_codePhone resignFirstResponder];
    [self.txt_code resignFirstResponder];
    
    if (self.txt_code.text.length==0) {
        [self showtips:@"请输入验证码"];
        return;
    }
    
    [self.ucsCallService doVerificationCode:@"4c1990a5c1ad2674bc94bc39a6fd0699" withAppid:@"efb7e1de9da649fa83881afea2841cd7" withPhone:self.txt_codePhone.text withVerifycode:self.txt_code.text];
    
}
</span></code>
        </pre>
        <h2 id="tgi9.1.5">5 服务器异步通知参数说明</h2>
        <h3 id="tgi9.1.5.1">5.1 描述</h3>
        <p>当用户手机号在智能验证平台通过验证后，在返回SDK的HTTP结果时，执行异步线程通知开发者服务器的成功验证结果。开发者服务器在收到通知后，请注意做签名校验防止恶意请求。</p>
        <h3 id="tgi9.1.5.2">5.2 列表</h3>
        <p style="text-align:center;">-表- 回调验证成功参数</p>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
                <tr>
                    <th style="width:15%;">参数</th>
                    <th style="width:20%">参数名称</th>
                    <th style="width:15%;">类型</th>
                    <th style="width:50%;">说明</th>
                </tr>
                <tr>
                    <td>appid</td>
                    <td>开发者id</td>
                    <td>String</td>
                    <td><span>开发者在我方注册的应用id</span></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td>手机号</td>
                    <td>String</td>
                    <td><span>用户手机号</span></td>
                </tr>
                <tr>
                    <td>sign</td>
                    <td>签名</td>
                    <td>String</td>
                    <td><span>签名</span></td>
                </tr>
            </tbody>
        </table>
        <h3 id="tgi9.1.5.3">5.3 样例</h3>
        <p class="code">
            <span>http://www.xxx.com/callback/?appid=00e1d21234567891024e1a123456cfa3&phone=13800038000&sign=9d801affd5eadd325b8217430ec809e6</span>
        </p>
        <p>签名字符串: (开发者主账号+应用ID+手机号+应用配置的密钥)</p>
        <p>例如：<br />
            开发者主账号：0316894c88d389035113e4bf01c30e2f<br />
            应用ID：00e1d21234567891024e1a123456cfa3<br />
            手机号：13800038000<br />
            秘钥：private_key<br />
            MD5(0316894c88d389035113e4bf01c30e2f00e1d21234567891024e1a123456cfa313800038000private_key)<br />
            MD5后sign = 9d801affd5eadd325b8217430ec809e6 (注意是小写)</p>
            <p>开发者服务器收到请求后返回： {“result”:”success”}</p>
        <h2 id="tgi9.1.6">6 错误码</h2>
        <p style="text-align:center;">Ios SDK 错误码</p>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>参数</span></th><th><span>参数名称</span></th>
                </tr>
                <tr>
                    <td><span>401061</span></td><td><span>开发者号无效</span></td>
                </tr>
                <tr>
                    <td><span>401062</span></td><td><span>验证码错误</span></td>
                </tr>
                <tr>
                    <td><span>401063</span></td><td><span>验证码过期</span></td>
                </tr>
                <tr>
                    <td><span>401064</span></td><td><span>30s内重复请求</span></td>
                </tr> 
                <tr>
                    <td><span>401065</span></td><td><span>签名错误</span></td>
                </tr> 
                <tr>
                    <td><span>401066</span></td><td><span>手机号码无效</span></td>
                </tr>
                <tr>
                    <td><span>401067</span></td><td><span>已经注册过</span></td>
                </tr>  
                <tr>
                    <td><span>401068</span></td><td><span>其他错误</span></td>
                </tr>                                  
            </tbody>
        </table>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgi9.1.1">1 文档说明</a>
          <ul>
            <li><a href="#tgi9.1.1.1">1.1 功能描述</a></li>
            <li><a href="#tgi9.1.1.2">1.2 阅读对象</a></li>
            <li><a href="#tgi9.1.1.3">1.3 业务术语</a></li>
          </ul>
      </li>
      <li><a href="#tgi9.1.2">2 开发前准备</a>
          <ul>
            <li><a href="#tgi9.1.2.1">2.1 注册flypaas用户账号</a></li>
            <li><a href="#tgi9.1.2.2">2.2 创建应用</a></li>
            <li><a href="#tgi9.1.2.3">2.3 创建智能验证短信模板</a></li>
            <li><a href="#tgi9.1.2.4">2.4 开发环境准备</a>
                <ul>
                    <li><a href="#tgi9.1.2.4.1">2.4.1 新建工程</a></li>
                    <li><a href="#tgi9.1.2.4.2">2.4.2 导入SDK</a></li>
                    <li><a href="#tgi9.1.2.4.3">2.4.3 配置工程信息</a></li>
                    <li><a href="#tgi9.1.2.4.4">2.4.4 编写代码</a></li>
                </ul>
            </li>
          </ul>
      </li>
      <li><a href="#tgi9.1.3">3 功能演示</a>
          <ul>
            <li><a href="#tgi9.1.3.1">3.1 下发验证码场景</a></li>
            <li><a href="#tgi9.1.3.2">3.2 智能验证通过场景</a></li>
          </ul>
      </li>
      <li><a href="#tgi9.1.4">4 接口调用</a>
          <ul>
            <li><a href="#tgi9.1.4.1">4.1 初始化</a></li>
            <li><a href="#tgi9.1.4.2">4.2 请求验证</a>
                <ul>
                    <li><a href="#tgi9.1.4.2.1">4.2.1 功能描述</a></li>
                    <li><a href="#tgi9.1.4.2.2">4.2.2 接口说明</a></li>
                    <li><a href="#tgi9.1.4.2.3">4.2.3 调用示例</a></li>
                </ul>
            </li>
            <li><a href="#tgi9.1.4.3">4.3 提交验证码验证</a>
                <ul>
                    <li><a href="#tgi9.1.4.3.1">4.3.1 功能描述</a></li>
                    <li><a href="#tgi9.1.4.3.2">4.3.2 接口说明</a></li>
                    <li><a href="#tgi9.1.4.3.3">4.3.3 调用示例</a></li>
                </ul>
            </li>
          </ul>
      </li>
      <li><a href="#tgi9.1.5">5 服务器异步通知参数说明</a>
          <ul>
            <li><a href="#tgi9.1.5.1">5.1 描述</a></li>
            <li><a href="#tgi9.1.5.2">5.2 列表</a></li>
            <li><a href="#tgi9.1.5.3">5.3 样例</a></li>
          </ul>
      </li>
      <li><a href="#tgi9.1.6">6 错误码</a></li>
    </ul>
  </div>
</div>
<div class="aside_fold" id="aside_fold" style="display:none;"><img src="<%=path%>/doc/images/aside_fold.png" />目录导航</div>
<a href="javascript:void(0);" id="go_top" style="display:none;">&nbsp;</a>
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
