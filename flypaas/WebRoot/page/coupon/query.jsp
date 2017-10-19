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
<title>代金券查询</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/theme/all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/demos.css" />
<%@include file="/front/resource.jsp" %>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
<script type="text/javascript" src="<%=path%>/js/datepicker.js"></script>
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
<style type="text/css">
.query_ctn,.query_ctn .main_top { overflow:inherit; margin-bottom:0px;}
.query_ctn .select_box ul li { margin-right:0px;}
</style>
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="main_ctn query_ctn">
      <s:if test="#session.user.email=='chenxijun@flypaas.com'">
      <div class="main_top">
      <s:form theme="simple" namespace="/coupon" action="query" method="post" name="queryForm" id="queryForm">
      <ul>
      	<li>
      		<div class="select_box">
	          <label>展会名称</label>
	          <div class="select"><span>${meetId }</span>
	            <ul style="display:none;">
	              <s:iterator value="list" id="l">
		              <li onclick="setMeetId('${l.meet_id}')"><s:property value="#l.meet_id"/></li>
	              </s:iterator>
	            </ul>
	          </div>
	         <input type="hidden" value="${meetId }" id="meetId" name="meetId"/>
	       </div>
      	</li>
        <li class="time">
        	<input type="text" placeholder="金额" name="couponMoney" value="${couponMoney }"/>
        </li>
        <li class="txt">
	      	<input type="text" placeholder="创建时间" name="createDate" id="beginDate" value="${createDate }"/>
        </li>
        <li><input type="submit" value="查 询" class="search" /></li>
      </ul>
      </s:form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
	      <table cellpadding="0" cellspacing="0" border="0" width="100%">
		      <tbody>
		      <tr><th>展会名称</th><th>代金券编号</th><th>面值</th><th>状态</th><th>创建时间</th><th>过期时间</th><th>金额过期时间</th></tr>
		      <s:if test="date!=null&&date.size()>0">
			      <s:iterator value="date" id="d">
				      <tr>	
					      <td><s:property value="#d.meet_id" /></td>
					      <td><s:property value="#d.couponnum" /></td>
					      <td><s:property value="#d.money" /></td>
					      <td>
					      <s:if test="#d.sended==1">
					     	 已发放
					      </s:if>
					      <s:else>
					      	未发放
					      </s:else>
					      </td>
					      <td><s:date name="#d.create_date" format="yyyy-MM-dd"/></td>
					      <td><s:date name="#d.end_date" format="yyyy-MM-dd"/></td>
					      <td><s:date name="#d.exp_date" format="yyyy-MM-dd"/></td>
					    </tr>
			      </s:iterator>
		      </s:if>
		      </tbody>
	      </table>
      </div>
      </s:if>
      </div>
    </div>
  </div>
</div>


<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

<script type="text/javascript">
	function setMeetId(meet){
		$("#meetId").val(meet);
	}
</script>
</body>
</html>
