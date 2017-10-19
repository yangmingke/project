<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!--公共头部header bof-->
<script type="text/javascript" src="<%=path%>/js/cookie.js"></script>
<div class="header">
  <div class="header_wrapper">
    <div class="logo"><a href="<%=path%>/" title="快传 UNIFIED COMMUNICATION PLATFORM-AS-A-SERVICE"><img src="<%=path%>/images/logo.png" /></a></div>
    <!-- Main Menu bof -->
    <ol id="menu">
      <li class="active_menu_item"><a href="<%=path%>/" id="h_menu_1">首页</a> 
      <li><a href="javascript:void(0)" id="h_menu_2">产品</a> 
        
        <!-- sub menu -->
        <ol>
          <li><a href="<%=path%>/product_service/im">即时消息</a></li>
          <li><a href="<%=path%>/product_service/voice">语音通话</a></li>
          <li><a href="<%=path%>/doc/verifyCode">验证码平台</a></li>
        </ol>
      </li>
      <!-- END sub menu -->
      
      <li>
      	<a href="javascript:void(0)" id="h_menu_3">解决方案</a>
      	 <ol>
          <li><a href="<%=path%>/solution/freeVoice">免费通话</a></li>
          <li><a href="<%=path%>/solution/ip">IP客服</a></li>
          <li><a href="<%=path%>/solution/corporateCommt">企业通讯</a></li>
          <li><a href="<%=path%>/solution/interRoaming">国际漫游</a></li>
          <li><a href="<%=path%>/solution/secureCommt">安全通讯</a></li>
          <li><a href="<%=path%>/solution/hiddenNum">隐号匿名</a></li>
        </ol>		
      </li>
      <li><a href="javascript:void(0)" id="h_menu_6">服务</a>
      	<ol>
          <li><a href="<%=path%>/product_service/freetrial">体验</a></li>
          <li><a href="<%=path%>/product_service/download">下载</a></li>
           <li><a target="_blank" href="<%=com.flypaas.utils.SysConfig.getInstance().getProperty("bbs_host")%>">社区</a></li>
        </ol>
      </li>
      <li><a href="<%=path%>/price" id="h_menu_4">价格</a></li>
      <li><a href="http://docs.flypaas.com/doku.php" id="h_menu_5">文档</a></li>
    </ol>
    <!-- Main Menu eof -->

    <div class="top_right">
	    <s:if test="#session.user!=null">
	    <div class="top_link">
	    <s:property value="#session.user.email" />，<a href="<%=path%>/user/notice">消息<i>(<s:property value="#session.user.countMsg" />)</i></a><span>|</span><a href="javascript:void(0)" id="logout">退出</a></div>
	    <div class="log_link" style="margin-top:52px;"><a href="<%=path%>/user/account" title="管理中心" class="admin_center" style="padding:10px 20px;">管理中心</a></div>
	    </s:if>
	    <s:else>
	    <div class="log_link"><a class="log" id="log" title="登录" href="javascript:void(0)">登录</a><a class="reg" onclick="location.href='<%=path%>/user/toSign'" title="注册" id="reg">注册</a></div>
	    </s:else>
    </div>
   </div>
</div>

<!--弹层(登录) bof-->
<div id="background_box" style="display:none;"></div>
<div class="float_box log_box" style="display:none;">
  <div class="float_tit">
    <h3>登录</h3>
    <span class="close">&nbsp;</span>
  </div>
  <div class="float_ctn">
    <form  name="logForm"  id="login">
      <p class="uname">
        <input type="text" name="userid" id="log_username" placeholder="手机号或邮箱" />
      </p>
      <p class="passwd">
        <input type="password" name="password" id="log_password"  placeholder="请输入密码"/>
      </p>
	  <p class="code" id="temp">
	  </p>
	  <p>
        <input type="checkbox" value="" />
        		记住用户名 <a href="<%=path%>/user/forgetPwd" class="blue">忘记密码</a>
      </p>
      <input type="hidden" id="remusername" />
      <input type="hidden" id="checkcode"  />
      <p class="form_tips" style="height:12px;visibility:visible; margin-bottom: 0px;">
      	<span class="error" id="error" style="display: none;" ></span>
      </p>
      <p class="btn">
        <input type="button" value="登 录" onclick="logSubmit('login')" class="log" id="log_btn" />
        <input type="button" value="立即注册" onclick="location.href='<%=path%>/user/toSign'" class="reg" id="reg_btn" />
      </p>
      <%-- 
      <p class="other_reg">
        <span>其他方式登录：</span>
        <input type="button" value="" onclick="location.href='<%=path%>/auth/mingdao'" />
      </p>
       --%>
    </form>
    <input id="path_fo_js" type="hidden" value="<%=path%>" />
  </div>
</div>
<!--popupBox-->
<div class="background_box" style="display:none;">&nbsp;</div>
<div class="float_box float_box1 app_box" id="popup_box" style="display:none;">
  <div class="float_tit">
    <h3></h3>
    <h1></h1>
    <span class="close"></span>
  </div>
  <div class="float_ctn">
    <p class="float_field"></p>
    <p class="btn">
      <input type="button" value="确 定" class="blue_btn" id="popup_smt" />
      <input type="button" value="取 消" class="grey_btn cancel" id="cancel"/>
    </p>
  </div>
</div>
<script type="text/javascript" src="<%=path%>/js/login.js"> </script>
<script type="text/javascript">
$(function(){
	 //复选框处理
	 	$("input[type='checkbox']").wrap("<span class='checkbox'></span>");
		
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
		
		$("input[type='checkbox']").each(function(){
			if($(this).attr("checked")){
				$(this).parent("span").addClass("checked");
				}
			})
	})
</script>

<!--公共头部header eof--> 
