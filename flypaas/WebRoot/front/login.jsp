<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<title>登录——快传通行证</title>
	<%@include file="/front/resource1.jsp"%>
	<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
		if(null != object){
			out.println((String)object);
		}
	%>
</head>
</head>
<body>
	<!--公共头部 ft_header bof-->
	<%@include file="/front/head2.jsp"%>
	<!--公共头部 ft_header eof-->

	<!--主体部分 ft_content bof-->
    <div class="ft_content ft_content_log">
    	<div class="ft_content_wp">
            <div class="login_info">
              <h1>没有账号？</h1>  
              <p class="text">
                  快传通讯开发平台为开发者和企业客户提供基于成熟的API 云通讯服务，使用帐号登录可在线免费体验所有开放服务、管理、开发应用，可参照文档将通信服务快速集成至现有平台，平台为新建帐号提供一定额度的开发调试费用，真正零成本、无障碍开发通信应用
              </p>
              <p class="link"><a href="<%=path%>/user/toSign"> 创建账号</a></p>
              <p class="mail">如还有其他帐号问题疑问请联系<a href="mailto:support@flypaas.com">support@flypaas.com</a></p>
            </div>
            <div class="login_box">
                <h1>快传通行证登录</h1>
                <p class="error"><span id="error" style="display: none;" ></span></p>
                <form>
                    <ul>
                        <li><label>账号</label><input type="text" name="userid" id="log_username" placeholder="邮箱" /></li>
                        <li><label>密码</label><input type="password" name="password" id="log_password"  placeholder="密码" /></li>
                        <li class="remember"><input type="checkbox" value="" /> 保存账号（公用电脑不建议使用）</li>
                        <li class="code" id="temp">
                        </li>
                        <li class="button"><input type="button" onclick="logSubmit('login')" id="log_btn" value="登 录" /><a href="<%=path%>/user/forgetPwd">忘记密码？</a></li>
<!--                         <li class="third"><label>第三方账号登录</label><a href="#" class="sina"></a><a href="#" class="in"></a><a href="#" class="baidu"></a><a href="#" class="qq"></a><a href="#" class="google"></a><a href="#" class="sina"></a></li> -->
                        <li>
                        <input type="hidden" id="remusername" />
      					<input type="hidden" id="checkcode"  />
      					<input id="fr" type="hidden" value="${param.fr}"/>
      					</li>
                    </ul>
                </form>
				<input id="path_fo_js" type="hidden" value="<%=path%>" />
            </div>
        </div>        
	</div>
	<!--主体部分 ft_content eof-->

	<!--公共底部 ft_footer bof--> 
	<%-- <%@include file="/front/callus.jsp"%> --%>
	
    <script type="text/javascript" src="<%=path%>/js/cookie.js"></script>
    <script type="text/javascript" src="<%=path%>/js/login.js"> </script>
    
    
    <script type="text/javascript">
	$(function(){
			$("input[type='checkbox']").click(function(){
				if($(this).attr("checked")){
					$(this).attr("checked",true);
					$(this).parent("span").addClass("checked");
					$("#remusername").val("1");
					}
				else{
					$(this).attr("checked",false);
					$(this).parent("span").removeClass("checked");
					$("#remusername").val("0");
					}
				});
		})
	</script>
</body>
</html>