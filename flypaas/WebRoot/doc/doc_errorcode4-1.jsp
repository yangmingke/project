<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>rest错误码</title>
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
        		<dd><a href="doc_errorcode1-1.jsp">Android错误码</a></dd>
        		<dd><a href="doc_errorcode2-1.jsp">iOS错误码</a></dd>
        		<dd><a href="doc_errorcode3-1.jsp">Windows错误码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_errorcode4-1.jsp" class="active">REST错误码</a></dd>
        	</dl>
		</li>            
        <li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'">常见问题<i class="parent">&nbsp;</i></span></li>
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt">
	  <div class="pathbox"><p><a href="<%=path%>/doc/doc.jsp">帮助文档首页</a> > <a href="<%=path%>/doc/doc_errorcode1-1.jsp">错误代码</a> > <span>REST错误码</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">REST错误码</span></h1>
      </div>
      <div class="display_ctn">
        <h2>REST错误码定义</h2>
        <h3 id="tge4.1.1">1. 公共错误</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
				<tr>
                    <td><span>000000</span></td><td><span>正确</span></td>
                </tr>
                <tr>
                    <td><span>100000</span></td><td><span>金额不为整数</span></td>
                </tr>
                <tr>
                    <td><span>100001</span></td><td><span>余额不足</span></td>
                </tr>
                <tr>
                    <td><span>100002</span></td><td><span>数字非法</span></td>
                </tr>
                <tr>
                    <td><span>100003</span></td><td><span>不允许有空值</span></td>
                </tr>
                <tr>
                    <td><span>100004</span></td><td><span>枚举类型取值错误</span></td>
                </tr>
                <tr>
                    <td><span>100005</span></td><td><span>访问IP不合法</span></td>
                </tr>
                <tr>
                    <td><span>100006</span></td><td><span>手机号不合法</span></td>
                </tr>
                <tr>
                    <td><span>100500</span></td><td><span>HTTP状态码不等于200</span></td>
                </tr>
                <tr>
                    <td><span>100007</span></td><td><span>查无数据</span></td>
                </tr>
				<tr>
                    <td><span>100008</span></td><td><span>手机号码为空</span></td>
                </tr>
                <tr>
                    <td><span>100009</span></td><td><span>手机号为受保护的号码</span></td>
                </tr>
				<tr>
                    <td><span>100010</span></td><td><span>登录邮箱或手机号为空</span></td>
                </tr>
				<tr>
                    <td><span>100011</span></td><td><span>邮箱不合法</span></td>
                </tr>
				<tr>
                    <td><span>100012</span></td><td><span>密码不能为空</span></td>
                </tr>
				<tr>
                    <td><span>100013</span></td><td><span>没有测试子账号</span></td>
                </tr>
				<tr>
                    <td><span>100014</span></td><td><span>金额过大,不要超过12位数字</span></td>
                </tr>
				<tr>
                    <td><span>100015</span></td><td><span>号码不合法</span></td>
                </tr>
                <tr>
                    <td><span>100016</span></td><td><span>余额被冻结</span></td>
                </tr>
                <tr>
                    <td><span>100017</span></td><td><span>余额已注销</span></td>
                </tr>
                <tr>
                    <td><span>100018</span></td><td><span>通话时长需大于60秒</span></td>
                </tr>
                <tr>
                    <td><span>100019</span></td><td><span>应用余额不足</span></td>
                </tr>
                <tr>
                    <td><span>100699</span></td><td><span>系统内部错误</span></td>
                </tr>
            </tbody>
        </table>
        <h3 id="tge4.1.2">2. 开发者账号资源</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>101100</span></td><td><span>请求包头Authorization参数为空</span></td>
                </tr>
                <tr>
                    <td><span>101101</span></td><td><span>请求包头Authorization参数Base64解码失败</span></td>
                </tr>
                <tr>
                    <td><span>101102</span></td><td><span>请求包头Authorization参数解码后账户ID为空</span></td>
                </tr>
                <tr>
                    <td><span>101103</span></td><td><span>请求包头Authorization参数解码后时间戳为空</span></td>
                </tr>
                <tr>
                    <td><span>101104</span></td><td><span>请求包头Authorization参数解码后格式有误</span></td>
                </tr>
                <tr>
                    <td><span>101105</span></td><td><span>主账户ID存在非法字符</span></td>
                </tr>
                <tr>
                    <td><span>101106</span></td><td><span>请求包头Authorization参数解码后时间戳过期</span></td>
                </tr>
                <tr>
                    <td><span>101107</span></td><td><span>请求地址SoftVersion参数有误</span></td>
                </tr>
                <tr>
                    <td><span>101108</span></td><td><span>主账户已关闭</span></td>
                </tr>
                <tr>
                    <td><span>101109</span></td><td><span>主账户未激活</span></td>
                </tr>
                <tr>
                    <td><span>101110</span></td><td><span>主账户已暂停</span></td>
                </tr>
                <tr>
                    <td><span>101111</span></td><td><span>主账户不存在</span></td>
                </tr>
                <tr>
                    <td><span>101112</span></td><td><span>主账户ID为空</span></td>
                </tr>
                <tr>
                    <td><span>101113</span></td><td><span>请求包头Authorization参数中账户ID跟请求地址中的账户ID不一致</span></td>
                </tr>
                <tr>
                    <td><span>101114</span></td><td><span>请求地址Sig参数为空</span></td>
                </tr>
                <tr>
                    <td><span>101115</span></td><td><span>请求token校验失败</span></td>
                </tr>
                <tr>
                    <td><span>101116</span></td><td><span>开发者账号sig加密串不匹配</span></td>
                </tr>
                <tr>
                    <td><span>101117</span></td><td><span>开发者账号token不存在</span></td>
                </tr>
            </tbody>
        </table>
		<h3 id="tge4.1.3">3. Client资源</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>103100</span></td><td><span>Client昵称为空</span></td>
                </tr>
                <tr>
                    <td><span>103101</span></td><td><span>Client名称存在非法字符</span></td>
                </tr>
                <tr>
                    <td><span>103102</span></td><td><span>Client昵称长度有误</span></td>
                </tr>
                <tr>
                    <td><span>103103</span></td><td><span>Client账号为空</span></td>
                </tr>
				<tr>
                    <td><span>103104</span></td><td><span>同一应用下，friendlyname重复</span></td>
                </tr>
                <tr>
                    <td><span>103105</span></td><td><span>Client账号的friendlyname只能包含数字和字母和下划线</span></td>
                </tr>
                <tr>
                    <td><span>103106</span></td><td><span>Client账号长度有误</span></td>
                </tr>
                <tr>
                    <td><span>103107</span></td><td><span>Client账号不存在或不属于该开发者账号</span></td>
                </tr>
                <tr>
                    <td><span>103108</span></td><td><span>Client账号已经关闭</span></td>
                </tr>
				<tr>
                    <td><span>103109</span></td><td><span>Client账号充值失败</span></td>
                </tr>
                <tr>
                    <td><span>103110</span></td><td><span>Client类型（clientType）为空</span></td>
                </tr>
                <tr>
                    <td><span>103111</span></td><td><span>Client类型（clientType）只能取值0,1</span></td>
                </tr>
                <tr>
                    <td><span>103112</span></td><td><span>Client类型（clientType）为1时，charge不能为空</span></td>
                </tr>
                <tr>
                    <td><span>103113</span></td><td><span>Client账号未绑定手机号</span></td>
                </tr>
				<tr>
                    <td><span>103114</span></td><td><span>同一应用下同一手机号只能绑定一次</span></td>
                </tr>
				<tr>
                    <td><span>103115</span></td><td><span>单次查询记录数不能超过100</span></td>
                </tr>
				<tr>
                    <td><span>103116</span></td><td><span>绑定手机号失败</span></td>
                </tr>
                <tr>
                    <td><span>103117</span></td><td><span>子账号是否显号(display)不能为空</span></td>
                </tr>
                <tr>
                    <td><span>103118</span></td><td><span>子账号是否显号(display)取值只能是0(不显号)和1(显号) </span></td>
                </tr>
                <tr>
                    <td><span>103119</span></td><td><span>应用下该子账号不存在</span></td>
                </tr>
                <tr>
                    <td><span>103120</span></td><td><span>friendlyname不能为空</span></td>
                </tr>
                <tr>
                    <td><span>103121</span></td><td><span>查询client参数不能为空</span></td>
                </tr>
            </tbody>
        </table>
        <h3 id="tge4.1.4">4. 应用资源</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>102100</span></td><td><span>应用ID为空</span></td>
                </tr>
                <tr>
                    <td><span>102101</span></td><td><span>应用ID存在非法字符</span></td>
                </tr>
                <tr>
                    <td><span>102102</span></td><td><span>应用不存在</span></td>
                </tr>
                <tr>
                    <td><span>102103</span></td><td><span>应用未审核通过</span></td>
                </tr>
				<tr>
                    <td><span>102104</span></td><td><span>测试应用不允许创建client</span></td>
                </tr>
				<tr>
                    <td><span>102105</span></td><td><span>应用不属于该开发者账号</span></td>
                </tr>
            </tbody>
        </table>
		<h3 id="tge4.1.5">5. 回拨</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>104100</span></td><td><span>主叫clientNumber为空</span></td>
                </tr>
                <tr>
                    <td><span>104101</span></td><td><span>主叫clientNumber未绑定手机号</span></td>
                </tr>
            </tbody>
        </table>
		<h3 id="tge4.1.6">6. 语音验证码</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>104102</span></td><td><span>验证码为空</span></td>
                </tr>
                <tr>
                    <td><span>104103</span></td><td><span>显示号码不合法</span></td>
                </tr>
				<tr>
                    <td><span>104104</span></td><td><span>语音验证码位4-8位数字</span></td>
                </tr>
                <tr>
                    <td><span>104106</span></td><td><span>语音通知类型错误</span></td>
                </tr>
                <tr>
                    <td><span>104107</span></td><td><span>语音通知内容为空</span></td>
                </tr>
                <tr>
                    <td><span>104108</span></td><td><span>语音ID非法</span></td>
                </tr>
                <tr>
                    <td><span>104109</span></td><td><span>文本内容存储失败</span></td>
                </tr>
                <tr>
                    <td><span>104110</span></td><td><span>语音文件不存在或未审核</span></td>
                </tr>
            </tbody>
        </table>
		<h3 id="tge4.1.7">7. 短信验证码</h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%"  class="code_table">
            <tbody>
                <tr>
                    <th><span>错误码</span></th><th><span>说明</span></th>
                </tr>
                <tr>
                    <td><span>105100</span></td><td><span>短信服务请求异常</span></td>
                </tr>
                <tr>
                    <td><span>105101</span></td><td><span>101：url关键参数为空</span></td>
                </tr>
				<tr>
                    <td><span>105102</span></td><td><span>2：号码不合法</span></td>
                </tr>
				<tr>
                    <td><span>105103</span></td><td><span>3：没有通道类别</span></td>
                </tr>
				<tr>
                    <td><span>105104</span></td><td><span>4：该类别为冻结状态</span></td>
                </tr>
				<tr>
                    <td><span>105105</span></td><td><span>5：没有足够金额</span></td>
                </tr>
				<tr>
                    <td><span>105106</span></td><td><span>6：不是国内手机号码并且不是国际电话</span></td>
                </tr>
				<tr>
                    <td><span>105107</span></td><td><span>7：黑名单</span></td>
                </tr>
				<tr>
                    <td><span>105108</span></td><td><span>8：含非法关键字</span></td>
                </tr>
				<tr>
                    <td><span>105109</span></td><td><span>9：该通道类型没有第三方通道</span></td>
                </tr>
				<tr>
                    <td><span>105110</span></td><td><span>短信模板ID不存在</span></td>
                </tr>
				<tr>
                    <td><span>105111</span></td><td><span>短信模板未审核通过</span></td>
                </tr>
				<tr>
                    <td><span>105112</span></td><td><span>短信模板替换个数与实际参数个数不匹配</span></td>
                </tr>
				<tr>
                    <td><span>105113</span></td><td><span>短信模板ID为空</span></td>
                </tr>
				<tr>
                    <td><span>105114</span></td><td><span>短信内容为空</span></td>
                </tr>
				<tr>
                    <td><span>105115</span></td><td><span>短信类型长度应为1</span></td>
                </tr>
				<tr>
                    <td><span>105116</span></td><td><span>同一天同一用户不能发超过3条相同的短信</span></td>
                </tr>
				<tr>
                    <td><span>105117</span></td><td><span>模板ID含非法字符</span></td>
                </tr>
				<tr>
                    <td><span>105118</span></td><td><span>短信模板有替换内容，但参数为空</span></td>
                </tr>
				<tr>
                    <td><span>105119</span></td><td><span>短信模板替换内容过长，不能超过70个字符</span></td>
                </tr>
                <tr>
                    <td><span>105120</span></td><td><span>手机号码不能超过100个</span></td>
                </tr>
                <tr>
                    <td><span>105121</span></td><td><span>短信模板已删除</span></td>
                </tr>
                <tr>
                    <td><span>105122</span></td><td><span>同一天同一用户不能发超过N条验证码(n为用户自己配置)</span></td>
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
        <li><a href="#tge4.1.1">1. 公共错误</a></li>
        <li><a href="#tge4.1.2">2. 开发者账号资源</a></li>
		<li><a href="#tge4.1.3">3. Client资源</a></li>
		<li><a href="#tge4.1.4">4. 应用资源</a></li>
		<li><a href="#tge4.1.5">5. 回拨</a></li>
        <li><a href="#tge4.1.6">6. 语音验证码</a></li>
		<li><a href="#tge4.1.7">7. 短信验证码</a></li>
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
