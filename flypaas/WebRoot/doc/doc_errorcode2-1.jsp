<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>iOS错误码</title>
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
        		<dd><a href="<%=path%>/doc/doc_errorcode1-1.jsp">Android错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode2-1.jsp" class="active">iOS错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode3-1.jsp">Windows错误码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_errorcode4-1.jsp">REST错误码</a></dd>
        	</dl>
		</li>           
        <li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'">常见问题<i class="parent">&nbsp;</i></span></li>
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt">
	  <div class="pathbox"><p><a href="<%=path%>/doc/doc.jsp">帮助文档首页</a> > <a href="<%=path%>/doc/doc_errorcode1-1.jsp">错误代码</a> > <span>iOS错误码</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">iOS错误码</span></h1>
      </div>
      <div class="display_ctn">
        <h2>iOS错误码定义</h2>
        <h3 id="tge2.1.1">1. 登录错误</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>401001</span></td><td><span>参数错误</span></td>
                </tr>
                <tr>
                    <td><span>401002</span></td><td><span>用户名密码错误</span></td>
                </tr>
                <tr>
                    <td><span>401003</span></td><td><span>用户不存在</span></td>
                </tr>
                <tr>
                    <td><span>401004</span></td><td><span>账号状态非法</span></td>
                </tr>
                <tr>
                    <td><span>401007</span></td><td><span>登录被拒绝</span></td>
                </tr>
                <tr>
                    <td><span>401008</span></td><td><span>密文登录时Token错误</span></td>
                </tr>
                <tr>
                    <td><span>401014</span></td><td><span>账号在其它地方登录,服务器强制下线</span></td>
                </tr>                                 
                <tr>
                    <td><span>401005、401006、401009~401013、401015、401021、401022</span></td><td><span>平台服务器内部错误</span></td>
                </tr> 
            </tbody>
        </table>
        <h3 id="tge2.1.2">2. 呼叫错误</h3>
        <b id="tge2.1.2.1">2.1 onCallFailed回调</b>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>402001</span></td><td><span>余额不足</span></td>
                </tr>
                <tr>
                    <td><span>402002</span></td><td><span>被叫不在线或者不存在</span></td>
                </tr>
                <tr>
                    <td><span>402003</span></td><td><span>呼叫超时（网络连接问题）</span></td>
                </tr>
                <tr>
                    <td><span>402004</span></td><td><span>服务不受理（媒体协商失败）</span></td>
                </tr>
                <tr>
                    <td><span>402005</span></td><td><span>账号已冻结（频繁呼叫/黑名单）</span></td>
                </tr>
                <tr>
                    <td><span>402006</span></td><td><span>账号已过期（长期未使用）</span></td>
                </tr>
                <tr>
                    <td><span>402007</span></td><td><span>未知的呼叫错误</span></td>
                </tr>                                 
                <tr>
                    <td><span>402008</span></td><td><span>呼叫号码非法</span></td>
                </tr>
                <tr>
                    <td><span>402009</span></td><td><span>呼叫失败（超时）</span></td>
                </tr>
                <tr>
                    <td><span>402010</span></td><td><span>会话已不存在</span></td>
                </tr>
                <tr>
                    <td><span>402011</span></td><td><span>无网络</span></td>
                </tr> 
            </tbody>
        </table>
        <b id="tge2.1.2.2">2.2 onCallEnded回调</b>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>402012</span></td><td><span>对方拒绝接听</span></td>
                </tr>
                <tr>
                    <td><span>402013</span></td><td><span>对方忙（正在通话中）</span></td>
                </tr>
                <tr>
                    <td><span>402014</span></td><td><span>主动取消呼叫</span></td>
                </tr>
                <tr>
                    <td><span>402015</span></td><td><span>已挂机</span></td>
                </tr>
                <tr>
                    <td><span>402016</span></td><td><span>对方已挂机</span></td>
                </tr>
                <tr>
                    <td><span>402017</span></td><td><span>媒体数据接收超时</span></td>
                </tr>
                <tr>
                    <td><span>402018</span></td><td><span>余额不足</span></td>
                </tr>                                 
                <tr>
                    <td><span>402019</span></td><td><span>呼叫超时</span></td>
                </tr>
                <tr>
                    <td><span>402020</span></td><td><span>不能拨打自己</span></td>
                </tr>
                <tr>
                    <td><span>402021</span></td><td><span>未知错误</span></td>
                </tr>
                <tr>
                    <td><span>402022</span></td><td><span>呼叫绑定号码</span></td>
                </tr>
            </tbody>
        </table>
        <h3 id="tge2.1.3">3. IM错误</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>403001</span></td><td><span>消息发送失败</span></td>
                </tr>
                <tr>
                    <td><span>403002</span></td><td><span>附件下载失败</span></td>
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
        <li><a href="#tge2.1.1">1. 登录错误</a></li>
        <li><a href="#tge2.1.2">2. 呼叫错误</a>
		    <ul>
              <li><a href="#tge2.1.2.1">2.1 onCallFailed回调</a></li>
              <li><a href="#tge2.1.2.2">2.2 onCallEnded回调</a></li>
           </ul>
		</li>
		<li><a href="#tge2.1.3">3. IM错误</a></li>
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
