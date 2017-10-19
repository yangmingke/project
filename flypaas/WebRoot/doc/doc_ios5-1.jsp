<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>iOS开发指南</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css" /><%@include file="/front/resource.jsp"%><script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
</head>

<body id="5">
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box doc_box">
  <div class="doc_search">
      <!--<div class="doc_search">
    <div class="serach_wrapper">
      <form method="post">
        <input type="text" value="" placeholder="请简单输入您的关键词，如'快传'" />
        <input type="submit" value="" />
      </form>
    </div>
  </div>-->
  </div>
  <div class="display_wrapper">
    <div class="doc_menu">
      <div class="menu_tit">
        <h1><span class="home" ><a href="<%=path%>/doc/doc.jsp">文档首页</a></span></h1>
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
        		<dd><a href="doc_rest1-1.jsp">REST介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>账户管理</em></a>
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
        			<a href="#"><em>呼叫</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></dd>
						<dd class="last"><a href="<%=path%>/doc/doc_rest3-3.jsp">语音通知</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>SMS</em></a>
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
                <dd><a href="<%=path%>/doc/doc_ios5-1.jsp" class="active">iOS开发指南</a></dd>
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
        		<!--<dd><a href="<%=path%>/doc/doc_server1-1.jsp">接收短信</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server2-1.jsp">语音验证码状态通知</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫请求</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd class="last"><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
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
      <div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_ios5-1.jsp">iOS SDK</a> > <span>iOS开发指南</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">iOS开发指南</span></h1>
      </div>
      <div class="display_ctn">
        <h2 id="tgi5.1.1">1. 快速体验</h2>
        <p>在快传融合通讯开放平台注册账号，创建Demo账号，并下载获取UCSVoipDemo程序（具体过程请参考以下内容）。</p>
        <p>在Demo程序中，演示了快传融合通讯开放平台提供的网络通话、落地电话、回拨、即时消息。</p>
        <h3 id="tgi5.1.1.1">1.1 申请测试账号</h3>
        <p>在快传融合通讯开放平台上获取Demo账号信息，须注册后创建Demo，即可获得开发VoIP所需的测试账号信息。</p>
        <h3 id="tgi5.1.1.2">1.2 环境搭建</h3>
        <p>Mac OS X 10.7 (Lion)及以上版本; XCode4.2及以上版本。</p>
        <h3 id="tgi5.1.1.3">1.3 Demo介绍</h3>
        <p>下载：在Demo账号信息页面，提供了Android和iOS平台下的Demo下载，请选择iOS版下载。</p>
        <p>UCSVoipDemo功能介绍，Demo演示了UCSSDK的API接口调用，主要实现的功能：<br />
        • 免费电话：需要对方的Client账号，免费通话<br />
        • 电话直拨：需要对方的手机号，主叫接入网络电话，被叫接入普通电话的网络通话 <br />
        • 回拨呼叫：需要对方的手机号，双方都会接入普通电话网络进行通话
        </p>
        <p>UCSVoipDemo工程文件结构说明：<br />
        • Product：应用生成的app;<br />
        • Framework：包含工程需要依赖的资源项，主要是系统和需要依赖的库资源;<br />
        • UCSVoipDemo：包含整个工程需要完成的代码文件：视图控制类的实现文件、resource文件夹(账号文件，图片资源文件等)、sdk文件夹(SDK库文件)、Supportting Files文件夹。</p>
        <h3 id="tgi5.1.1.4">1.4 导入DEMO工程</h3>
        <p>解压下载UCS_PHONE_DEMO_iOS.rar(此名字取决于官网的)文件，在解压缩的文件夹中，双击UCSVoipDemo.xcodeproj文件，在XCode中打开工程，即可对Demo进行 其他操作。</p>
        <!--<h3 id="tgi5.1.1.5">1.5 配置账号信息</h3>
        <p>打开resource\ucsinfoconfig.plist文件，将申请测试账号时获取的Demo账号信息，依次输入配置文件中，如图所示:<br />
        <img src="<%=path%>/doc/images/doc_img5.png" width="100%" />
        </p>-->
		<h3 id="tgi5.1.1.5">1.5 DEMO登录方式</h3>
        <p>体验Demo前，请先在flypaas.com官网上注册账号，使用注册的邮箱账号登录相关Demo，并体验Demo相关能力。</p>
        <h2 id="tgi5.1.2">2. 创建自己的VoIP应用</h2>
        <p>这一节是为了让开发者能够用最少的代码量和时间，来实现基本的VoIP通话功能。</p>
        <h3 id="tgi5.1.2.1">2.1 SDK介绍</h3>
        <p>SDK下载：从快传融合通讯开放平台下载VoIP的iOSSDK</p>
        <p>SDK文件说明：SDK文件放在文件夹ucpsdk中，其中包含三个文件：<br />
        • UCSService.h：为应用调用的函数头文件<br />
        • UCSEvent.h：为SDK的代理函数头文件 <br />
        • libucsapisdk.a：为整个SDK库文件
        </p>
        <h3 id="tgi5.1.2.2">2.2 创建工程</h3>
        <b id="tgi5.1.2.2.1">2.2.1 新建工程</b>
        <p>创建一个iPhone工程需要在Mac系统下面安装Xcode的软件，Xcode安装完成后可以在桌面的快速启动栏中的"Launchpad"中或者应用程序中找到一个图标<img src="<%=path%>/doc/images/doc_img6.png" />看到后单击或者双击即可打开软件，Xcode版本最小需要4.2。</p>
        <p>打开Xcode后的界面可以看到几个选项，创建工程需要选择"Create a newXcode project",如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img7.png" /></p>
        <p>也可以根据菜单创建工程，依次选择XCode菜单栏中的FiLe->New->Project，如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img8.png" />
        </p>
        <p>选择完后弹出的界面中选择左边的"iOS"下面的"Application",然后选择右边的"SingLeViewAppLication"进行双击或者点击右下角"Next", 如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img9.png" width="500" />
        </p>
        <p>在完成后弹出的界面中的"ProductName"中填写你的工程名，如下图所示:  <br />
        <img src="<%=path%>/doc/images/doc_img10.png" width="500" />
        </p>
        <p>添加完成后点击右下角的”Next”，在弹出的对话框中选择你需要把工程放置的位置。这样一个工程就初步的创建完成。</p>
        <b id="tgi5.1.2.2.2">2.2.2 导入UCSSDK</b>
        <p>创建完成工程后，把SDK包里面的ucssdk文件夹拷贝到新创建的工程路径下面，然后在工程目录结构中，右键选择Add Files to “UCSVoipDemo”(工程名称，在弹出的对话框中选择新创建的工程文件夹下的sdk这个文件夹，即前面拷贝的文件夹)。或者将这个文件夹拖入XCode 工程目录结构中，<br />
        <img src="<%=path%>/doc/images/doc_img11.png" /></p>
        <p>在弹出的界面中勾选Copy items into destination group’s folder(ifneeded),并确保Add To Targets勾选相应的target。 相应的操作请看下图中所示：<br />
        <img src="<%=path%>/doc/images/doc_img12.png" width="500" />
        </p>
        <b id="tgi5.1.2.2.3">2.2.3 配置工程信息</b>
        <p><b>• 添加依赖框架(Frameworks)</b><br/>
        UCSSDK的实现，依赖了一些系统框架，在开发应用时，要在工程里加入这些框架。开发者首先点击工程右边的工程名,然后在工程名右边 依次选择TARGETS->BuiLd Phases->Link Binary With Libraries,展开LinkBinary With Libraries后点击展开后下面的"+"来添加下面的依赖项:<br />
        <img src="<%=path%>/doc/images/doc_img13.png" />
        </p>
        <p>添加步骤如图所示:<br />
        <img src="<%=path%>/doc/images/doc_img14.png" width="100%" /><br /><br />
        <img src="<%=path%>/doc/images/doc_img15.png" />
        </p>
        <p><b>• 编译器设置</b><br/>
        在xcode5.1环境下，首先点击工程右边的工程名,然后在工程名右边依次选择TARGETS、Build Settings LLVM 5.1- Language C++”中的”C++ Standard Library".存后面的选择框中选择libstdc++(GNU C++ standard Library)”选项，如果默认是则不需要修改。步骤如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img16.png" width="100%" />
        </p>
		<p><b>• 编译器链接设置</b><br/>
        在xcode5.1环境下，首先点击工程右边的工程名,然后在工程名右边依次选择TARGETS、Build Settings Linking中的Other Linker Flags. 中填入-ObjC。 如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img63.png" />
        </p>
        <p><b>• 工程属性设置</b><br/>
        一般的iOS程序进入后台后会被系统挂起，就会停止执行，不能执行任何操作。</p>
        <p>A. 从iOS4开始，苹果增加了特性，很好的支持了VoIP功能：<br />
        &nbsp;&nbsp;&nbsp;&nbsp;i. 苹果支持应用可以在后台播放和录制声音；<br />
        &nbsp;&nbsp;&nbsp;&nbsp;ii. 苹果支持网络托管，保证应用在后台时，还能保持网络连接，能接收到来电；<br />
        &nbsp;&nbsp;&nbsp;&nbsp;iii. 应用可以设置一个超时处理，程序在后台运行时，周期性地唤醒应用，保证客户端和服务器有长连接，使网络不断开。<br />
        B. UCSSDK封装了这些特性，保证了在iOS平台上。<br />
        C. 开发者需要修改配置文件，这样iOS工程才能支持这些特性。</p>
        <p>在工程名的文件夹下面的Supporting Files文件夹中找到并且选择(工程名）lnfo.pList在右边出现的窗口中添加Key: Required background modes，在下面添加两个项：App plays audio和App provides Voice over IP services。<br />
        <img src="<%=path%>/doc/images/doc_img17.png" width="100%" />
        </p>
        <h3 id="tgi5.1.2.3">2.3 编写代码</h3>
        <p>介绍代码的实现过程，也可参考Demo的代码实现。</p>
        <p>注意事项：调用SDK文件要以.mm为后缀,要包含头文件 UCSService.h和UCSEvent.h。</p>
        <b id="tgi5.1.2.3.1">2.3.1 SDK初始化</b>
        <p>初始化SDK的代码:<br />
        <pre>
        	<code class="code_fn">
{
    <span class="fn_cmt">//初始化SDK，并传入代理实现的类实例</span>
    UCSService* ucsService = [[UCSService alloc] initWithDelegate:self];

    <span class="fn_cmt">//另一种初始化方式</span>
    UCSService* ucsService = [[UCSService alloc] init];
    [ucsService setDelegate:self];
}            </code><br />注意事项：必须要设置代理
        </pre>
        </p>
        <b id="tgi5.1.2.3.2">2.3.2 VoIP账号连接云平台</b>
        <p>A. VoIP登录<br />
        <p class="code"><span>[ucsCallService&nbsp;&nbsp;&nbsp;&nbsp;connect:accountSid&nbsp;&nbsp;&nbsp;&nbsp;withAccountToken:accountToken&nbsp;&nbsp;&nbsp;&nbsp;withClientNumber:ClientNumber&nbsp;&nbsp;&nbsp;&nbsp;withClientPwd:ClientPwd]</span></p>
        <p>B. 注册事件相关代理<br />
        <pre>
        	<code class="code_fn">
<span class="fn_cmt">//与快传融合通讯开放平台连接成功</span>
- (void)onConnectionSuccessful:(NSInteger)result
{
    <span class="fn_cmt">//连接云平台成功后的处理代码</span>
    <span class="fn_cmt">//TODO</span>
}

<span class="fn_cmt">//与快传融合通讯开放平台连接失败或连接断开</span>
-(void)onConnectionFailed:(NSInteger)reason
{
    <span class="fn_cmt">//连接云平台失败后的处理代码</span>
    <span class="fn_cmt">//TODO</span>
}            </code>
        </pre>
        </p>
        <b id="tgi5.1.2.3.3">2.3.3 创建VoIP免费通话(或电话直拨)连接</b>
        <p>A. 创建呼出代码<br />
        <pre>
        	<code class="code_fn">
{
    <span class="fn_cmt">//拨打免费通话(对方VoIP账号)或电话直拨(对方电话号码）</span>
    [ucsCallService dial:callType andCalled:calledNumber];
}          	</code>
        </pre>
        </p>
        <p>B. 创建成功并连接被叫过程中代理函数<br />
        <pre>
        	<code class="code_fn">
- (void) onAlerting:(NSString*)callid;</code>
        </pre>
        </p>
        <p>C. 对方接听的代理函数<br />
        <pre>
        	<code class="code_fn">
-(void) onAnswer:(NSString*) callid;   	
<span class="fn_cmt">//对方已接听 //TODO</span></code>
        </pre>
        </p>
        <p>D. 呼叫失败(被叫拒接，被叫忙等原因)的代理函数，可参考错误码查找失败原因<br />
        <pre>
        	<code class="code_fn">
- (void) onDialFailed:(NSString*)callid  withReason:(UCSReason*)reason; 
<span class="fn_cmt">//呼叫失败，可根据reason查找错误原因 </span><span class="fn_cmt">//TODO</span></code>
        </pre>
        </p>
        <p>E. 通话过程中，对方挂断的代理函数<br />
        <pre>
        	<code class="code_fn">
- (void) onHangUp:(NSString*)callid withReason:(UCSReason*)reason;
<span class="fn_cmt">//通话过程中，对方挂断电话 </span><span class="fn_cmt">//TODO</span></code>
        </pre>
        </p>
        <b id="tgi5.1.2.3.4">2.3.4 创建VoIP回拨呼叫连接</b>
        <p>A. 创建VoIP回拨呼叫<br />
        <pre>
        	<code class="code_fn">
<span class="fn_cmt">//通过FromPhone（绑定的手机号）回拨呼叫到toPhone</span>
[ucsCallService callBack:phoneNumber];
        	</code><br/>说明：回拨呼叫成功后，FromPhone首先接到VoIP落地电话，接听后，dest才接到VoIP落地电话。
        </pre>
        </p>
        <p>B. 回拨结果代理函数<br />
        <pre>
        	<code class="code_fn">
回拨回调：原因可参考错误码<br />
- (void)onCallBackWithReason:(UCSReason*)reason
{
if(reason.reason == 0)
{
    <span class="fn_cmt">//回拨成功</span>
}
else
{
    <span class="fn_cmt">//回拨失败错误原因可以看错误码定义</span>
}
}        	</code>
        </pre>
        </p>
        <b id="tgi5.1.2.3.5">2.3.5 接收VoIP通话呼入</b>
        <pre>
        	<code class="code_fn">
实现代理函数<br />
-(void)onIncomingCall:(NSString*)callid&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;withcalltype:(UCSCallTypeEnum)&nbsp;&nbsp;&nbsp;&nbsp;callType withcallerNumber:(NSString*)callerNumber;
{
    <span class="fn_cmt">//有VoIP电话呼入处理</span> <span class="fn_cmt">//TODO</span>
}        	</code>
        </pre>
        </p>
        <b id="tgi5.1.2.3.6">2.3.6 接听VoIP通话 </b>
        <p>A. 接听VoIP电话，使用callid参数标识接听某个具体的VoIP电话 </p>
        <pre>
        	<code class="code_fn">
- (void) answer: (NSString*)callid;
{
    if(callid)
    {
        <span class="fn_cmt">//接听成功 //T0D0</span>
    }
    else
    {
        <span class="fn_cmt">//接听失败 //TODO</span>
    }
}        	</code>
        </pre>
        </p>
        <p>B. 接听成功的代理函数</p>
        <pre>
        	<code class="code_fn">
-(void) onAnswer:(NSString*)callid;
{
    <span class="fn_cmt">//接听成功 //T0D0</span>
}        	</code>
        </pre>
        </p>
        <b id="tgi5.1.2.3.7">2.3.7 挂断(或被叫拒接)VolP通话</b>
        <pre>
        	<code class="code_fn">
来电拒接，通话过程中的挂断函数：
[ucsCallService hangUp:callid];</code>
        </pre>
        </p>
        <h3 id="tgi5.1.2.4">2.4 编译运行和测试</h3>
        <p>在用Xcode打开工程后，看是需要用真机还是用模拟器运行应用，默认是模拟器下编译运行的，单击左上角的三角符号就可以进行应用运行， 如果是真机则需要你插上真机连接电脑，然后在最上面有单击"iPhone6.1SimuLator"(6.1为模拟器的版本)后可以看到显示你iPhone的机器 名字，并且选择后点击左上角的三角符号进行运行即可。如下图所示：<br />
        <img src="<%=path%>/doc/images/doc_img18.png" />
        </p>
        <h3 id="tgi5.1.2.5">2.5 查看日志</h3>
        <p>查看编译器日志。<br />选择xcode右上角区的的按钮<br />
        <img src="<%=path%>/doc/images/doc_img19.png" />
        </p>
        <h3 id="tgi5.1.2.6">2.6 打包</h3>
        <p>首先要从弹出的列表中选择第一项<br />
        <img src="<%=path%>/doc/images/doc_img20.png" /></p>
        <p>
        然后选择xcode的菜单Product->Archive项<br />
        <img src="<%=path%>/doc/images/doc_img21.png" />
        </p>
        <p>
        单击Distribute...按钮<br />
        <img src="<%=path%>/doc/images/doc_img22.png" width="100%" />
        </p>
        <p>
        根据需要选择，测试请选择第二项<br />
        <img src="<%=path%>/doc/images/doc_img23.png" width="500" />
        </p>
        <p>
        在下列窗口中请选择程序的签名证书<br />
        <img src="<%=path%>/doc/images/doc_img24.png" width="500" />
        </p>
        <p>
        重命名包名和选择要保持的路径<br />
        <img src="<%=path%>/doc/images/doc_img25.png" />
        </p>
        <p>
        点击"Save"按钮打包结束。
        </p>
        <h2 id="tgi5.1.3">3. 错误码</h2>
        <p>错误码请参考平台文档说明。</p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgi5.1.1">1. 快速体验</a>
        <ul>
              <li><a href="#tgi5.1.1.1">1.1 申请测试账号</a></li>
              <li><a href="#tgi5.1.1.2">1.2 环境搭建</a></li>
              <li><a href="#tgi5.1.1.3">1.3 Demo介绍</a></li>
              <li><a href="#tgi5.1.1.4">1.4 导入DEMO工程</a></li>
              <li><a href="#tgi5.1.1.5">1.5 DEMO登录方式</a></li>	  
	  </ul>
	 </li>
      <li><a href="#tgi5.1.2">2. 创建自己的VoIP应用</a>
      <ul>
        <li><a href="#tgi5.1.2.1">2.1 SDK介绍</a></li>
		<li><a href="#tgi5.1.2.2">2.2 创建工程</a>
           <ul>
              <li><a href="#tgi5.1.2.2.1">2.2.1 新建工程</a></li>
              <li><a href="#tgi5.1.2.2.2">2.2.2 导入UCSSDK</a></li>
			  <li><a href="#tgi5.1.2.2.3">2.2.3 配置工程信息</a></li>
           </ul>
        </li>
        <li><a href="#tgi5.1.2.3">2.3 编写代码</a>
           <ul>
              <li><a href="#tgi5.1.2.3.1">2.3.1 SDK初始化</a></li>
              <li><a href="#tgi5.1.2.3.2">2.3.2 VoIP账号连接云平台</a></li>
			  <li><a href="#tgi5.1.2.3.4">2.3.3 创建VoIP免费通话(或电话直拨)连接</a></li>
			  <li><a href="#tgi5.1.2.3.5">2.3.4 创建VoIP回拨呼叫连接</a></li>
			  <li><a href="#tgi5.1.2.3.6">2.3.5 接收VoIP通话呼入</a></li>
			  <li><a href="#tgi5.1.2.3.7">2.3.6 接听VoIP通话</a></li>
			  <li><a href="#tgi5.1.2.3.8">2.3.7 挂断(或被叫拒接)VolP通话</a></li>
           </ul>
        </li>
		<li><a href="#tgi5.1.2.4">2.4 编译运行和测试</a></li>
		<li><a href="#tgi5.1.2.5">2.5 查看日志</a></li>
		<li><a href="#tgi5.1.2.6">2.6 打包</a></li>
      </ul>
      </li>
	  <li><a href="#tgi5.1.3">3. 错误码</a></li>
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
