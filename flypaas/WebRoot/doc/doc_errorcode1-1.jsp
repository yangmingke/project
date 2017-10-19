<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Android错误码</title>
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
        <li class="active"><!--<span onclick="location.href='doc_errorcode1-1.html'" style="background:#fff; width:188px;">错误代码<i class="parent">&nbsp;</i></span>-->
		    <span>错误代码<i class="parent">&nbsp;</i></span>
        	<dl style="display:block;">
        		<dd><a href="doc_errorcode1-1.jsp" class="active">Android错误码</a></dd>
        		<dd><a href="doc_errorcode2-1.jsp">iOS错误码</a></dd>
        		<dd><a href="doc_errorcode3-1.jsp">Windows错误码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_errorcode4-1.jsp">REST错误码</a></dd>
        	</dl>
		</li>       
        <li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'">常见问题<i class="parent">&nbsp;</i></span></li>
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt">
	  <div class="pathbox"><p><a href="<%=path%>/doc/doc.jsp">帮助文档首页</a> > <a href="<%=path%>/doc/doc_errorcode1-1.jsp">错误代码</a> > <span>Android错误码</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">Android错误码</span></h1>
      </div>
      <div class="display_ctn">
        <h2>Android错误码定义</h2>
        <h3 id="tge1.1.1">1. 业务逻辑错误</h3>
        <b id="tge1.1.1.1">1.1 连接错误</b>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>300201</span></td><td><span>连接的ConnectConfig参数没有初始化</span></td>
                </tr>
                <tr>
                    <td><span>300202</span></td><td><span>accountSid不能为空</span></td>
                </tr>
                <tr>
                    <td><span>300203</span></td><td><span>accountToken不能为空</span></td>
                </tr>
                <tr>
                    <td><span>300204</span></td><td><span>ClientId不能为空</span></td>
                </tr>
                <tr>
                    <td><span>300205</span></td><td><span>ClientPwd不能为空</span></td>
                </tr>
            </tbody>
        </table>
        <b id="tge1.1.1.2">1.2 VoIP错误</b>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>300210</span></td><td><span class="red">平台服务器错误</span></td>
                </tr>
                <tr>
                    <td><span>300211</span></td><td><span>余额不足</span></td>
                </tr>
                <tr>
                    <td><span>300212</span></td><td><span>对方正忙</span></td>
                </tr>
                <tr>
                    <td><span>300213</span></td><td><span>对方拒绝接听</span></td>
                </tr>
                <tr>
                    <td><span>300214</span></td><td><span>该用户不在线</span></td>
                </tr>
                <tr>
                    <td><span>300215</span></td><td><span class="red">被叫号码错误</span></td>
                </tr>
                <tr>
                    <td><span>300216</span></td><td><span>被叫号码冻结</span></td>
                </tr>
                <tr>
                    <td><span>300217</span></td><td><span>主叫号码冻结</span></td>
                </tr>
                <tr>
                    <td><span>300218</span></td><td><span>主叫账号过期</span></td>
                </tr>
                <tr>
                    <td><span>300219</span></td><td><span>不能拨打自己绑定号码</span></td>
                </tr>
                <tr>
                    <td><span>300220</span></td><td><span>呼叫请求超时</span></td>
                </tr>
                <tr>
                    <td><span>300221</span></td><td><span>对方无人应答</span></td>
                </tr>
                <tr>
                    <td><span>300222</span></td><td><span>对方不在线转直拨</span></td>
                </tr>
                <tr>
                    <td><span>300223</span></td><td><span class="red">鉴权失败，需要重新登录</span></td>
                </tr>
                <tr>
                    <td><span>300224</span></td><td><span>未知错误</span></td>
                </tr>
                <tr>
                    <td><span>300225</span></td><td><span class="red">主叫挂断电话</span></td>
                </tr>
                <tr>
                    <td><span>300226</span></td><td><span>被叫挂断电话</span></td>
                </tr>
                <tr>
                    <td><span>300233</span></td><td><span>回拨主叫没有绑定手机号码</span></td>
                </tr>
                <tr>
                    <td><span>300234</span></td><td><span>回拨绑定手机号码异常</span></td>
                </tr>
                <tr>
                    <td><span>300235</span></td><td><span>回拨鉴权错误，需要重新登录</span></td>
                </tr>
                <tr>
                    <td><span>300236</span></td><td><span>回拨IO错误</span></td>
                </tr>
                <tr>
                    <td><span>300237</span></td><td><span>回拨请求成功但返回JSON错误</span></td>
                </tr>
                <tr>
                    <td><span>300238</span></td><td><span>回拨请求超时</span></td>
                </tr>
                <tr>
                    <td><span>300239</span></td><td><span class="red">回拨平台服务器繁忙</span></td>
                </tr>
                <tr>
                    <td><span>300240</span></td><td><span class="red">回拨平台服务器内部错误</span></td>
                </tr>
                <tr>
                    <td><span>300241</span></td><td><span>回拨被叫号码错误</span></td>
                </tr>
                <tr>
                    <td><span>300242</span></td><td><span>充值后才可以拨打国际电话</span></td>
                </tr>               
                <tr>
                    <td><span>300243</span></td><td><span>回拨未知错误</span></td>
                </tr> 
				<tr>
                    <td><span>300247</span></td><td><span>对方正在响铃</span></td>
                </tr> 
				<tr>
                    <td><span>300248</span></td><td><span>自己拒绝接听</span></td>
                </tr> 
				<tr>
                    <td><span>300249</span></td><td><span>该机器不支持视频通话</span></td>
                </tr> 
            </tbody>
        </table>
        <b id="tge1.1.1.3">1.3 附件错误</b>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>300227</span></td><td><span>发送文件不能大于100M</span></td>
                </tr>
                <tr>
                    <td><span>300228</span></td><td><span>发送文件超时</span></td>
                </tr>
                <tr>
                    <td><span>300229</span></td><td><span>发送文件成功但反回JSON错误</span></td>
                </tr>
                <tr>
                    <td><span>300230</span></td><td><span>网络超时,下载文件失败</span></td>
                </tr>
                <tr>
                    <td><span>300231</span></td><td><span>消息接收者或者消息类型不能为空</span></td>
                </tr>
                <tr>
                    <td><span>300232</span></td><td><span>消息接收者只能为数字</span></td>
                </tr>
                <tr>
                    <td><span>300244</span></td><td><span>消息类型冲突或不存在(自定义类型在10-29之间)</span></td>
                </tr>
                <tr>
                    <td><span>300245</span></td><td><span>发送文件不存在或者文件不能为中文</span></td>
                </tr>
                <tr>
                    <td><span>300246</span></td><td><span>发送消息文本过长,不能大于500</span></td>
                </tr>                
            </tbody>
        </table>
        <h3 id="tge1.1.2">2. 登录接口错误</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>300001~ 300004</span></td><td><span class="red">平台服务器内部错误</span></td>
                </tr>
                <tr>
                    <td><span>300005</span></td><td><span>密码错误重试次数太多</span></td>
                </tr>
                <tr>
                    <td><span>300006</span></td><td><span>参数不能为空</span></td>
                </tr>
                <tr>
                    <td><span>300007</span></td><td><span>参数格式错误</span></td>
                </tr>
                <tr>
                    <td><span>300008</span></td><td><span class="red">平台服务器内部错误</span></td>
                </tr>
                <tr>
                    <td><span>300009</span></td><td><span>用户名/密码错误</span></td>
                </tr>
                <tr>
                    <td><span>300010~300012</span></td><td><span class="red">平台服务器内部错误</span></td>
                </tr>
                <tr>
                    <td><span>300013</span></td><td><span>不支持该文件格式发送</span></td>
                </tr>
                <tr>
                    <td><span>300014</span></td><td><span>用户ID不存在</span></td>
                </tr>
                <tr>
                    <td><span>300015</span></td><td><span>Client状态非法</span></td>
                </tr> 
                <tr>
                    <td><span>300016</span></td><td><span>此次登录被拒绝</span></td>
                </tr> 
                <tr>
                    <td><span>300017</span></td><td><span>密文登录时Token错误</span></td>
                </tr>                 
            </tbody>
        </table>
        <h3 id="tge1.1.3">3. 系统内部错误</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>300501、300502</span></td><td><span>网络数据读取异常</span></td>
                </tr>
                <tr>
                    <td><span>300503、300504</span></td><td><span>网络数据写入异常</span></td>
                </tr>
                <tr>
                    <td><span>300505</span></td><td><span>服务器内部错误</span></td>
                </tr>
                <tr>
                    <td><span>300207</span></td><td><span>账号在其它地方登录,服务器强制下线</span></td>
                </tr>
                <tr>
                    <td><span>300506</span></td><td><span>连接服务器地址错误</span></td>
                </tr>
                <tr>
                    <td><span>300507</span></td><td><span>连接服务器IO错误</span></td>
                </tr>
                <tr>
                    <td><span>300508</span></td><td><span>连接服务器未知错误</span></td>
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
        <li><a href="#tge1.1.1">1. 业务逻辑错误</a>
           <ul>
              <li><a href="#tge1.1.1.1">1.1 连接错误</a></li>
              <li><a href="#tge1.1.1.2">1.2 VoIP错误</a></li>
			  <li><a href="#tge1.1.1.3">1.3 附件错误</a></li>
           </ul>
        </li>
        <li><a href="#tge1.1.2">2. 登录接口错误</a></li>
		<li><a href="#tge1.1.3">3. 系统内部错误</a></li>
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
