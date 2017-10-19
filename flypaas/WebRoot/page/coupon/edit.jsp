<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<title>代金券生成</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/theme/all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/demos.css" />
<%@include file="/front/resource.jsp" %>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
<script type="text/javascript" src="<%=path%>/js/datepicker.js"></script>
<style type="text/css">
.coupon_add { text-align:left; width:610px; padding: 30px 20px;}
.coupon_add ul li { margin-bottom:20px;}
.coupon_add ul li .select { background-position:-225px -401px; width:222px;}
.coupon_add ul li .select li { margin-bottom:0px;}
.coupon_add ul li.btn { text-align:center;}
.coupon_add ul li.btn a { margin-left:10px; display:inline-block;}
</style>

<script type="text/javascript">
$(function(){
	 //取消
	$(".cancel").click(function(){
	      $(".float_box").hide();
	      $(".background_box").hide();
	});
	//文本框处理
    $("input[type='text'],input[type='password']").focus(function(){
		$(this).css("color","#333");
	});

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
	});
});
</script>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 


<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper"> 
    <div class="reg_box">
      <div class="reg_info coupon_add">
      		<s:if test="#session.user.email=='chenxijun@flypaas.com'">
	        <form  action="<%=path %>/coupon/add"  method="post" id="couponFrm">
	        <ul>
	          <li>
	            <label for="reg_email" style="width: 150px">展会标示(中/英文缩写)</label>
	            <input type="text" id="meetId"  name="meetId"/>
	          </li>
	          <li>
	            <label for="reg_email" style="width: 150px">代金券面值</label>
	            <input type="text" id="couponMoney"  name="couponMoney"/>
	          </li>
	          <li>
	            <label for="reg_email" style="width: 150px">张数</label>
	            <input type="text" id="count" name="count"/>
	          </li>
	          <li>
	            <label for="reg_email" style="width: 150px">过期时间</label>
	            <input type="text" id="endDate" name="endDate"/>
	          </li>
	          <li>
	            <label for="reg_email" style="width: 150px">是否是展会</label>
	            <input type="checkbox" id="ck"/>
	            <input type="hidden" id="isMeet" name="isMeet" value="1"/>
	           	 如果是展会则兑换码不会立即生效(用户手动触发)，线下活动兑换码会立即生效
	          </li>
	          <li>
		      <div class="select_box">
		          <label style="width: 150px">业务类型</label>
		          <div class="select"><span>请选择业务</span>
		            <ul style="display:none;">
		              <s:iterator value="paramList" var="l">
			              <li onclick="getParamValue('${l.event_id }')">${l.event_name }</li>
		              </s:iterator>
		            </ul>
		          </div>
		         <input type="hidden" id="paramValue" name="paramValue"/>
		       </div>
		       <div class="clear"></div>
		       </li>
		       <li>
	            <label for="reg_email" style="width: 150px">金额失效时间</label>
	            <input type="text" id="endDate1" name="expDate"/>
	          </li>
		       <li class="btn">
	           <input type="button" id="subBtn" value="生成" /><a href="<%=path%>/coupon/query">查看已生成的优惠券</a>
	           </li>
	         </ul>
	        </form>
	        </s:if>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>
</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

<script type="text/javascript">
	function cSubmit(){
		$("#popup_smt").hide();
		$("#couponFrm").submit();
	}
	$("#subBtn").click(function(){
		var meetId = $("#meetId").val();
		var couponMoney = $("#couponMoney").val();
		var count = $("#count").val();
		var endDate = $("#endDate").val();
		var content = "您将要为展会【"+meetId+"】生成【"+count+"】张面值为【"+couponMoney+"】的代金券，过期时间为【"+endDate+"】";
		popupBox("提示",content,"cSubmit()");
	});
	$("#ck").click(function(){
		var ck = $(this).attr("checked");
		if(ck=="checked"){
			$("#isMeet").val(2);
		}else{
			$("#isMeet").val(1);
		}
	});
	function getParamValue(value){
		$("#paramValue").val(value);
	}
	$("#paramValue").val("");
</script>
</body>
</html>
