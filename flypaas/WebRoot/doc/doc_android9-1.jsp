<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>智能验证接口</title>
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
        <li class="active">
        	<span>Android SDK<i class="parent">&nbsp;</i></span>
        	<dl>
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
                <dd><a href="<%=path%>/doc/doc_android9-1.jsp" class="active">智能验证接口</a></dd>
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
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt">
      <div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_android6-1.jsp">Android SDK</a> > <span>智能验证接口</span></p></div>
      <div class="display_tit">
        <h1 style="width:280px;"><span class="intro">智能验证接口</span></h1>
      </div>
      <div class="display_ctn">
        <h2 id="tga9.1.1">1 文档说明</h2>
        <h3 id="tga9.1.1.1">1.1 功能描述</h3>
        <p>智能验证SDK为开发者提供一个完全透明、方便、高效的验证服务，智能验证平台聚合了稳定的短信、语音验证码服务，开发者无需再开发自己的短信通道、语音网关，而且基于公共云的强大服务，使得验证能逐步避免下发短信、语音手机号的方式验证，从而更快速，更准确快速验证，解决APP验证的验证难题、提升产品体验。</p>
        <h3 id="tga9.1.1.2">1.2 阅读对象</h3>
        <p> 本文档主要阅读对象为开发人员，包括客户端和服务端。</p>
        <h3 id="tga9.1.1.3">1.3 业务术语</h3>
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
        <h2 id="tga9.1.2">2 开发前准备</h2>
        <h3 id="tga9.1.2.1">2.1 注册flypaas用户账号</h3>
        <p>登录www.flypaas.com官网，注册（已经有账号的跳过）</p>
        <p><img src="<%=path%>/doc/images/doc_img78.png" width="100%"></p>
        <p>flypaas运营人员审核后，可执行下一步创建应用</p>
        <h3 id="tga9.1.2.2">2.2 创建应用</h3>
        <p>登录后管理中心，点击“应用中心”—》“创建应用”</p>
        <p><img src="<%=path%>/doc/images/doc_img79.png" width="100%"></p><br />
        <p>配置项目:</p>
        <p><img src="<%=path%>/doc/images/doc_img80.png" width="100%"></p><br />
        <p>指定智能验证配置项：</p>
        <p><img src="<%=path%>/doc/images/doc_img81.png" width="100%"></p>
        <h3 id="tga9.1.2.3">2.3 创建智能验证短信模板</h3>
        <p>每个应用下可创建一个（只能有一个）智能验证短信的模板</p>
        <p><img src="<%=path%>/doc/images/doc_img82.png" width="100%"></p>
        <p><img src="<%=path%>/doc/images/doc_img83.png" width="100%"></p>
        <p>注意: 模板里只能有两个参数，例如：<br />
        您注册的xx应用验证码为{1}，请于{2}分钟内正确输入验证码<br />
        参数{1}会根据生成规则自动生成、参数{2}是开发者自定义的时间值</p>
        <h3 id="tga9.1.2.4">2.4 开发环境准备</h3>
        <b>2.4.1 复制SDK文件到工程</b>
        <p>将sdk文件复制到工程libs目录下。</p>
        <p><img src="<%=path%>/doc/images/doc_img84.png"></p>
        <b>2.4.2 导入SDK到工程</b>
        <p>在您的工程所在的工作空间下，导入sdk到工程，右键点击工程选择properties，如图：</p>
        <p><img src="<%=path%>/doc/images/doc_img85.png" width="100%"></p>
        <p>选择Add JARs，在工程libs目录下找到SDK，点击OK按钮</p>
        <p><img src="<%=path%>/doc/images/doc_img86.png"></p>
        <b>2.4.3 配置AndroidManifest.xml</b>
        <p>将demo工程中AndroidManifest.xml的相关配置，复制到您的工程目录AndroidManifest.xml文件中（含权限配置）。</p>
        <h2 id="tga9.1.3">3 功能演示</h2>
        <h3>3.1 下发验证码场景</h3>
        <p>当用户手机未通过智能验证时，需要下发短信/语音验证码的方式验证手机号的有效性。</p>
        <p><img src="<%=path%>/doc/images/doc_img87.png" width="100%"></p>
        <b>流程说明：</b>
        <p>1) 请求1的流程是发生在开发者自己的客户端和服务器，主要检测手机是否已经注册，如果未注册，则返回签名作为请求2中调用智能验证SDK的参数；</p>
        <p>2) 请求2 执行下发验证码的操作，具体接口参数详见“接口调用“中的”请求验证“接口说明；</p>
        <p>3) 请求3为提交验证码验证结果，检查用户的手机有效性；</p>
        <p>4) 异步通知结果详见“5 服务器异步通知说明”；</p>
        <h3>3.2 智能验证通过场景</h3>
        <p><img src="<%=path%>/doc/images/doc_img88.png" width="100%"></p>
        <b>流程说明：</b>
        <p>1) 请求1的流程是发生在开发者自己的客户端和服务器，主要检测手机是否已经注册，如果未注册，则返回签名作为请求2中调用智能验证SDK的参数；</p>
        <p>2) 请求2 执行下发验证码的操作，具体接口参数详见“接口调用“中的”请求验证“接口说明；</p>
        <p>3) 请求3为提交验证码验证结果，检查用户的手机有效性；</p>
        <p>4) 4)异步通知结果详见“5 服务器异步通知说明”；</p>
        <h2 id="tga9.1.4">4 接口调用</h2>
        <h3 id="tga9.1.4.1">4.1 请求验证</h3>
        <b>4.1.1 功能描述</b>
        <p>    提交验证请求，根据用户手机号在智能验证的云端服务返回验证结果，可以分短信
或者语音下发验证码，也可能</p>
        <b>4.1.2 接口说明</b>
        <pre>
          <code>
<span class="key_label">public static void getVerificationCode(Context mContext,String sign,String sid,String appid,
String appName,int seconds,int business,String phone,
VerificationCodeListener verificationCodeListener)</span></code>
        </pre>
        <p>请求参数说明：</p>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
                <tr>
                    <th style="width:35%">名称</th>
                    <th style="width:65%">说明</th>
                </tr>
                <tr>
                    <td>mContext</td>
                    <td><span>当前程序上下文</span></td>
                </tr>
                <tr>
                    <td>sign</td>
                    <td><span>签名</span></td>
                </tr>
                <tr>
                    <td>sid</td>
                    <td><span>开发者主账号</span></td>
                </tr>
                <tr>
                    <td>appid</td>
                    <td><span>应用ID</span></td>
                </tr>
                <tr>
                    <td>appName</td>
                    <td><span>应用描述</span></td>
                </tr>
                <tr>
                    <td>seconds</td>
                    <td><span>替换模板中的“有效时间”参数，单位秒</span><span>如模板：你注册的验证码{1}，请在{2}秒内输入。此处参数会自动替换{2}参数</span></td>
                </tr>
                <tr>
                    <td>business</td>
                    <td><span>验证业务类型，当前智能验证业务参数值为1</span></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><span>用户手机号</span></td>
                </tr>
                <tr>
                    <td>verificationCodeListener</td>
                    <td><span>回调监听器</span></td>
                </tr>
            </tbody>
        </table>
        <b>4.1.3 调用示例</b>
        <pre>
          <code>
/**
 * 
 * 获得验证码  (demo 源码)
 * @author: xiongjijian
 * 
 */
<span class="key_label">
private void getVerificationCode(String phone , String sign){
    VerificationCode.getVerificationCode(RegisterActivity.this, sign, "4c1990a5c1ad2674bc94bc39a6fd0699", "efb7e1de9da649fa83881afea2841cd7", "com.yzx.yunyanzhengdemo", 60, 1, phone, new VerificationCodeListener() {
        @Override
        public void onVerificationCode(int arg0, UcsReason arg1) {
            vfc=true;
            if(arg0==1){
                Message msg = new Message();
                msg.obj = "短信";
                msg.what = 4;
                mUiHandler.sendMessage(msg);
            }else{
                Message msg = new Message();
                msg.obj = "语音";
                msg.what = 4;
                mUiHandler.sendMessage(msg);
            }
            if(arg1.getReason() == 300250){
                if(arg0 == 0){
                    numpage++;
                    mUiHandler.sendEmptyMessage(2);
                }else {
                    vfc_result=true;
                    mUiHandler.sendEmptyMessage(1);
                }
            }else{
                numpage--;
            }
        }
    });
}
</span></code>
        </pre>
        <h3 id="tga9.1.4.2">4.2 提交验证</h3>
        <b>4.2.1 功能描述</b>
        <p>用户在收到短信或语音下发的验证码后，输入提交验证，APP调用SDK来完成验证码的校验</p>
        <b>4.2.2 接口说明</b>
        <pre>
          <code>
<span class="key_label">
public static void doVerificationCode(Context mContext,String phone,String verifycode, String sid, String appid, VerificationCodeListener verificationCodeListener)
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
                    <td>mContext</td>
                    <td><span>当前程序上下文</span></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><span>用户手机号</span></td>
                </tr>
                <tr>
                    <td>verifycode</td>
                    <td><span>验证码</span></td>
                </tr>
                <tr>
                    <td>sid</td>
                    <td><span>开发者主账号</span></td>
                </tr>
                <tr>
                    <td>appid</td>
                    <td><span>应用ID</span></td>
                </tr>
                <tr>
                    <td>verificationCodeListener</td>
                    <td><span>回调监听器</span></td>
                </tr>
            </tbody>
        </table>
        <b>4.2.3 调用示例</b>
        <pre>
          <code>
/**
 * 对验证码验证 (demo 源码)
 * @author: xiongjijian
 * 
 */
<span class="key_label">
private void StartVerificationCode() {
    if(vfc_result){
        <span class="fn_cmt">//stopCallTimer();</span>
        vfc_result=false;
        VerificationCode.doVerificationCode(RegisterActivity.this, phonenum, et_data_verification.getText().toString(), "4c1990a5c1ad2674bc94bc39a6fd0699", "efb7e1de9da649fa83881afea2841cd7", new VerificationCodeListener() {
            @Override
            public void onVerificationCode(int arg0, UcsReason arg1) {
                switch (arg1.getReason()) {
                    case 300250:
                        mRequestHandler.sendEmptyMessage(0);
                        mUiHandler.sendEmptyMessage(2);
                        break;
                    case 300251:
                        mRequestHandler.sendEmptyMessage(1);
                        break;
                    case 300252:
                        mRequestHandler.sendEmptyMessage(2);
                        break;
                    ….
                    default:
                        System.out.println("BBB");
                        mRequestHandler.sendEmptyMessage(99);
                        break;
                    }
                vfc_result=true;
            }
        });
    }
}
</span></code>
        </pre>
        <h2 id="tga9.1.5">5 服务器异步通知参数说明</h2>
        <h3 id="tga9.1.5.1">5.1 描述</h3>
        <p>当用户手机号在智能验证平台通过验证后，在返回SDK的HTTP结果时，执行异步线程通知开发者服务器的成功验证结果。开发者服务器在收到通知后，请注意做签名校验防止恶意请求。</p>
        <h3 id="tga9.1.5.2">5.2 列表</h3>
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
        <h3 id="tga9.1.5.3">5.3 样例</h3>
        <p class="code">
            <span>http://www.xxx.com/callback/?appid=00e1d21234567891024e1a123456cfa3&phone=13800038000&sign=9d801affd5eadd325b8217430ec809e6</span>
        </p>
        <p>签名字符串: (开发者主账号+应用ID+手机号+应用配置的密钥)</p>
        <p>回调请求方式： POST</p>
        <p>例如：<br />
            开发者主账号：0316894c88d389035113e4bf01c30e2f<br />
            应用ID：00e1d21234567891024e1a123456cfa3<br />
            手机号：13800038000<br />
            秘钥：private_key<br />
            MD5(0316894c88d389035113e4bf01c30e2f00e1d21234567891024e1a123456cfa313800038000private_key)<br />
            MD5后sign = 9d801affd5eadd325b8217430ec809e6 (注意是小写)</p>
            <p>开发者服务器收到请求后返回： {“result”:”success”}</p>
        <h2>6 错误码</h2>
        <p style="text-align:center;">-表- 客户端错误码表</p>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>参数</span></th><th><span>参数名称</span></th>
                </tr>
                <tr>
                    <td><span>300250</span></td><td><span>验证成功</span></td>
                </tr>
                <tr>
                    <td><span>300251</span></td><td><span>开发者账号无效</span></td>
                </tr>
                <tr>
                    <td><span>300252</span></td><td><span>验证码错误</span></td>
                </tr>
                <tr>
                    <td><span>300253</span></td><td><span>验证码过期</span></td>
                </tr>
                <tr>
                    <td><span>300254</span></td><td><span>30S内重复请求</span></td>
                </tr>
                <tr>
                    <td><span>300255</span></td><td><span>签名错误</span></td>
                </tr>
                <tr>
                    <td><span>300256</span></td><td><span>手机号码无效</span></td>
                </tr> 
                <tr>
                    <td><span>300257</span></td><td><span>验证码参数错误</span></td>
                </tr> 
                <tr>
                    <td><span>300258</span></td><td><span>获取验证码失败</span></td>
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
      <li><a href="#tga9.1.1">1 文档说明态</a>
          <ul>
            <li><a href="#tga9.1.1.1">1.1 功能描述</a></li>
            <li><a href="#tga9.1.1.2">1.2 阅读对象</a></li>
            <li><a href="#tga9.1.1.3">1.3 业务术语</a></li>
          </ul>
      </li>
      <li><a href="#tga9.1.2">2 功能演示</a></li>
      <li><a href="#tga9.1.3">3 数据交互</a></li>
      <li><a href="#tga9.1.4">4 接口调用</a>
          <ul>
            <li><a href="#tga9.1.4.1">4.1 Android</a></li>
          </ul>
      </li>
      <li><a href="#tga9.1.5">5 服务器异步通知参数说明</a>
          <ul>
            <li><a href="#tga9.1.5.1">5.1 描述</a></li>
            <li><a href="#tga9.1.5.2">5.2 列表</a></li>
            <li><a href="#tga9.1.5.3">5.3 样例</a></li>
          </ul>
      </li>
      <li><a href="#tga9.1.6">6 错误码</a></li>
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
