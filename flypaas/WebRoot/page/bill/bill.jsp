<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——消费记录</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/theme/all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/demos.css" />
<%@include file="/page/resource.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
<script type="text/javascript" src="<%=path%>/js/datepicker.js"></script>
<script type="text/javascript">
function setAppSid(appSid){
    	$("#appSid").val(appSid);
    }
</script>
</head>

<body id="03-3">
<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->
	<!--主体content bof-->
	<div class="content">
	<!--侧边side bof-->
	<%@include file="/page/left.jsp" %>
	<!--侧边side bof-->
	<!--右侧main bof-->
	<div class="main">
		    <div class="breadcrumbs">
		      <ul>
		        <li><a href="#">财务管理</a></li>
		        <li class="active"><a href="#">消费记录</a></li>
		      </ul>
		    </div>
		    <div class="main_tab_tit">
		      <ul>
		        <li class="active" onclick="href2url('${path}/bill/bill')">消费记录</li>
		       <%--  <li onclick="href2url('${path}/bill/billMonth')">月结账单</li> --%>
		      </ul>
		    </div>

	 <div class="bill_search">
	        <form action="${path}/bill/appCsmReport" id="queryForm" onsubmit="appCsm();return false;">
	        <ul>
	          <li class="li1">
	          <div class="select_box select_app">
	          	<label>选择应用：</label>
		          <div input="appSid" tabindex="2" class="select" defaultValue="${appList[0].appSid}">	
					<span>请选择应用<i>&nbsp;</i></span>
						<ul>
							<s:if test="appList!=null">
		                		<s:iterator value="appList" var="app">
		                		  	<li val="${app.appSid}">${app.appName}</li>
		                		</s:iterator>
	               			</s:if>
	               		</ul>
				</div>
	          </div>
	            <br />
	            <span class="tips">* 默认提供45天内消费详单数据查询，如需要过往数据详单请前往 [月结账单] 页面下载当年历史详单</span>
	          </li>
	          <li>
	            <label>时间范围：</label>
               		<input type="text" id="beginDateLimit" name="beginDate" placeholder="开始时间" readonly="readonly" value="${beginDate}" /><span class="span_ie">至</span>
               		<input type="text" id="endDateLimit" name="endDate" placeholder="结束时间" readonly="readonly" value="${endDate}"/>
               		<input type="hidden" id="type" name="type" value="voice"/>
               		<input type="submit" value="查 询" class="org_btn"/>
	          </li>
	      </ul>
	      </form>
	    </div>
	
	    <!--消费记录 consumption_list bof-->
	    <div class="consumption_list">
	      <div class="csm_tit">
	        <ul id="li_type">
	          <li id='li_voice' onclick="$('#currentPage').val(1);appCsm('voice');">流量</li>
	          <!-- <li id='li_video' onclick="$('#currentPage').val(1);appCsm('voice');">视频</li>
              <li id='li_client' onclick="$('#currentPage').val(1);appCsm('client')">Client账号</li> -->
              <!-- <li id='li_sms' onclick="$('#currentPage').val(1);appCsm('sms')">短信</li> -->
              <!-- <li id='li_voicecode' onclick="$('#currentPage').val(1);appCsm('voicecode')">语音验证码</li>
              <li id='li_im' onclick="$('#currentPage').val(1);appCsm('im')">即时通讯</li>
              <li id='li_mid' onclick="$('#currentPage').val(1);appCsm('mid')">智能验证</li>
              <li id='li_voicenotify' onclick="$('#currentPage').val(1);appCsm('voicenotify')">语音通知</li>
              <li id='li_gjmy' onclick="$('#currentPage').val(1);appCsm('gjmy')">国际漫游</li> -->
	        </ul>
	      </div>
	      <div class="table_box"  id="bills_tab_div" style="overflow:inherit;">
	      </div>
	    </div>
	    <!--消费记录 consumption_list eof-->
	  </div>  
	  <!--右侧main bof-->   
	</div>
	
	<!--主体content eof--> 
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
	<script type="text/javascript">
	    $(function(){
	    	var appSid = $("#appSid").val();
	    	if(appSid!=""){
		    	appCsm("client");
	    	}
	    });
		function appCsm(type){
			$("#li_type li").removeClass("active");
			if(null != type && '' != type){
				 $("#type").val(type);
			}else{
				type = $("#type").val();
			}
			 $("#li_" +type).addClass("active");
			var queryForm = $("#queryForm");
			var bills_tab_div= $("#bills_tab_div");
			var loading ="<div class='loading' style='text-align:center; padding-top:80px;'><img src='${ctx}/images/loading.gif'/></div>" ;
			var t = true;
		 	setTimeout(function(){
				if(t){
					bills_tab_div.html(loading);
				}
			}, 300); 
			$.ajax({
				url:queryForm.attr("action"),
				type:"post",
				data:queryForm.serialize(),
				dataType: "text",
				success: function (html) {
					if(html.indexOf('bill_csm_div') > 0){
						t = false;
						bills_tab_div.html(html);
					}else if(html.indexOf('this_is_the_home_page') > 0){
						window.location.reload();
					}
	            },
	            error: function (msg) {
	            }
			});
		}
	</script>
</body>
</html>
