<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——兑换码</title>
<%@include file="/page/resource.jsp"%>
</head>
<body id="03-2">
<!--头部header bof-->
<%@include file="/page/head.jsp"%>
<!--头部header eof--> 

<!--主体content bof-->
<div class="content"> 
  
  <!--侧边side bof-->
  <%@include file="/page/left.jsp"%>
  <!--侧边side bof--> 
  
  <!--右侧main bof-->
  <div class="main">
    <div class="breadcrumbs">
      <ul>
        <li><a href="#">财务管理</a></li>
        <li class="active"><a href="#">我的充值</a></li>
      </ul>
    </div>
    <div class="main_tab_tit">
      <ul>
        <li onclick="location.href='<%=path %>/bill/chargeList'">充值账单</li>
        <li onclick="location.href='<%=path %>/pay/newOrder'" >在线充值</li>
        <li class="active">兑换码</li>
      </ul>
    </div>
    
    <div class="code_box">
      <form method="post" action="<%=path %>/pay/coupon" id="frm">
        <dl>
          <dt>兑换码：</dt>
          <dd>
          <input type="text" id="couponNum" name="couponNum" placeholder="请输入" />
          <span id="ts" class="error" style="display:none">已失效，请重新输入</span>
          </dd>
          <dd><span class="tips">* 同一期活动只能使用一张兑换码</span></dd>
          <dd><span class="tips">* 请关注官方网站、官方微信公众账号即时获取每期活动信息</span></dd>
          <dd class="code_btn">
            <input type="submit" value="充值" id="confirm_btn"/>
          </dd>
        </dl>
      </form>
    </div>
    
  </div>  
  <!--右侧main bof-->   
</div>

<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->


<script type="text/javascript">
	$(function(){
		$("#frm").submit(function(){
			if(!frm.text()){
				return error();
			}
			return true;
		});
		
		$("#couponNum").keyup(function(){
			if(!frm.text()){
				return error();
			}
			var couponNum = $("#couponNum").val();
			$.ajax({
				url:"<%=path%>/coupon/queryCouponMoney",
				type:"post",
				data:"couponNum="+couponNum,
				dataType: "text",
				success: callback
			});
		});
		
	});
	
	function error(){
		$("#ts").removeClass("success");
		$("#ts").addClass("error");
		$("#ts").text("兑换码非法");
        $("#ts").show();
        return false;
	}
	
	var callback = function (data) {
		 if(data!="-1"){
			 $("#ts").removeClass("error");
			 $("#ts").addClass("success");
	         $("#ts").text("￥"+data);
	         $("#ts").show();
		 }else{
			 $("#ts").hide();
		 }
       };
	
	var frm = {
			text:function(){
				var couponNum = $("#couponNum").val();
				if(couponNum==""){
					return false;
				}else if(!verifyStr(couponNum)){
					return false;
				}
				return true;
			}
	};
</script>
</body>
</html>