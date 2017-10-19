	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——测试号码白名单</title>
<%@include file="/page/resource.jsp"%>
<script type="text/javascript" src="<%=path %>/js/md5.js"></script>
</head>
<body id="02-5">
<!--头部header bof-->
<%@ include file="/page/head.jsp" %>
<!--头部header eof--> 

<!--主体content bof-->
<div class="content"> 
  
  <!--侧边side bof-->
  <%@ include file="/page/left.jsp" %>
  <!--侧边side bof--> 
  
  <!--右侧main bof-->
  <div class="main">
    <div class="breadcrumbs">
      <ul>
        <li><a href="<%=path %>/app/appManager">应用管理</a></li>
		<li class="active"><a href="javascript:void(0)">测试号码白名单</a></li>
      </ul>
    </div>
    <div class="main_tab_tit"> 
      <ul>
        <!--<li onclick="location.href='<%=path %>/app/testDemo'" style="cursor: pointer;">Demo应用信息</li>-->
        <li class="active">测试号码白名单</li>
      </ul>
    </div>
    
    <!--说明state_box bof-->
    <div class="state_box">
      <h1>提醒</h1>
      <p>1、测试号码为用户提交至平台，并通过平台验证的真实号码</p>
      <p>2、未上线应用只允许在测试号码之间进行拨测、通话测试，开发测试期间会产生正常消费话单</p>
      <p>3、通过身份认证并将应用申请上线的应用将可以正式商用，不受测试号码限制</p>
      <p>4、可提交最多6个号码，平台内同一个号码只允许绑定在一个开发者帐号下（例如A号码不同时间被C、D用户绑定，关联关系将以最新时间绑定为准，旧信息自动删除）</p>
    </div>
    <!--说明state_box eof-->
    
      <div class="table_box test_num">
      	<div class="table_tit">
      		<h1>测试号码</h1>
      		<div class="table_link">
      			<s:if test="testWhiteList.size<6">
      			<a href="javascript:void(0)" class="float_link">添加号码</a>
      			</s:if>
      		</div>
      	</div>
        <table cellpadding="0" cellspacing="0" border="0">
          <thead>
            <tr>
              <th>ID</th>
              <th>号码</th>
              <th>来源</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          	<s:if test="testWhiteList!=null">
          	<s:iterator value="testWhiteList" var="w">
	            <tr>
	              <td>${w.id }</td>
	              <td>${w.mobile }</td>
	              <td>
	              	<s:if test="#w.type==2">
	              		新增
	              	</s:if>
	              	<s:else>
	              		注册手机
	              	</s:else>
	              </td>
	              <s:if test="#w.type==2"><td class="blue" onclick="popupBox('提示','确认删除?','del(\'${w.mobile }\')',null)" style="cursor: pointer;">删除</td></s:if>
	              <s:else><td class="blue"></td></s:else>
	            </tr>
	         </s:iterator>
            </s:if>
            <s:else>
            	<tr>
	              <td>暂无数据！</td>
	              <td></td>
	              <td></td>
	              <td></td>
	            </tr>
            </s:else>
          </tbody>
        </table>
      </div>
  </div>  
  <!--右侧main bof-->   
</div>
<!--弹层（添加号码） bof-->
  <div class="background_box">&nbsp;</div>
  <div class="float_box addnum_box">
    <div class="float_tit">
      <h1>添加号码</h1>
    </div>
    <div class="float_ctn">
     <form  action="/app/testNumAdd" method="post" name="phoneForm" id="phone_form">
      <div class="float_field">
        <dl>
          <dt>输入真实号码</dt>
          <dd><input type="text" id="phone"  name="testWhiteListObj.mobile"/><span id="phone_error" class="error" style="display:none">格式错误</span></dd>
          <dd><span class="tips">输入格式例如：13800138000  或 075523456789</span></dd>
          <%-- <dd class="relate_link"><input onclick="smsCodeWh('smscodeinput','voicecodeinput')" id="smscodeinput" type="button" value="短信验证"/><span class="tips">或</span><input type="button" onclick="voiceCodeWh('voicecodeinput','smscodeinput')" id="voicecodeinput" value="语音验证" /></dd> by yangmingke--%> 
        </dl>
      </div>
      <div class="float_field">
        <dl>
          <%-- <dt>输入获取的验证码</dt>
          <dd><input type="text" id="inputmovecode"/><span class="error" id="move_phone_code_error" style="display:none">正确</span></dd> --%>
        </dl>
      </div>
      <div class="float_btn">
        <input type="button" value="取消" class="cancel_btn" />
        <input type="submit" value="确定" class="confirm_btn" />
        <input type="hidden" id="vmovecode"  />
        <input type="hidden" id="movecode"  />
        <input type="hidden" id="vp" />
      </div>
      </form>
    </div>
  </div>
  <!--弹层（添加号码） eof-->
<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->

<script type="text/javascript">
	

	function del(mobile){
		$.ajax({
			url:"<%=path%>/app/testNumUpdate",
			type:"post",
			dataType: "text",
			data:"mobile="+mobile,
			success: function (data) {
	         	location.href="<%=path%>/app/testMobile" ;
	        },
	        error: function (msg) {
	        }
		});
	}
	
	
	var voiceCodeWh = function(id1,id2){
		if(frmwh.phone(id1,id2)){
			isExistsPhoneWh(1,id2,id1);
		}
	};
	var smsCodeWh = function(id1,id2){
		if(frmwh.phone(id1,id2)){
			isExistsPhoneWh(2,id1,id2);
		}
	};
	
	$(function(){
		 disabl('smscodeinput','voicecodeinput');
		//绑定手机表单验证
		$("#phone_form").submit(function(){
			/* if(frmwh.phone()&&frmwh.phoneCode()){
				return true;
			}else{
				return false;
			} */ //by yangmingke
			return true;
			
		});
	});

	//验证白名单手机
	var isExistsPhoneWh = function(type,id1,id2){
		var value = $("#phone").val();
		$.ajax({
			url:"/app/testNumExits",
			type:"post",
			data:"mobile="+value,
			dataType: "text",
			success: function (data) {
	          if(data=="1"){
	        	  $("#phone_error").fadeIn();
	  			  $("#phone_error").html("手机号码已经被绑定");
	  			  $("#vp").val("2");
	  			  disabl(id1, id2);
	          }else if(data=="0"){
	        	  $("#phone_error").hide();
	        	  if(type==1){
	      					voiceCodeAjax(value);
	      					time1(id2,id1,"语音验证",value);
	        	  }else if(type==2){
	      					smsCodeAjax(value);
	      					time1(id1,id2,"短信验证",value);
	        	  }
	          }
	        },
	        error: function (msg) {
	        	 $("#phone_error").hide();
	        	 $("#vp").val("2");
	        }
		});
	};

	var frmwh = {
			phone:function(id1,id2){
				var phone = $("#phone").val();
				if(!verifyMobileAndFixPhone(phone)){
					  $("#phone_error").text("错误：手机号码非法");
				      $("#phone_error").fadeIn();
				      return false;
				}else{
					$("#phone_error").hide();
				}
				noDisabl(id1,id2);
				return true;
			},
			phoneCode:function(){
				var phone = $("#phone").val();
				var movecode = $("#movecode").val();
				var inputmovecode = $("#inputmovecode").val();
				value = hex_md5(phone+inputmovecode);
				if(movecode!=value){
					$("#move_phone_code_error").text("验证码错误");
					$("#move_phone_code_error").fadeIn();
					return false ;
				}
			    return true;
			}
	};
</script>
</body>
</html>