<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
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
<style type="text/css">
	.hz_tip_table > tbody > tr > td,.hz_tip_table > tbody > tr > th  {
	  padding: 1px 4px;
	}
	h4{  
			margin-left: 20px;  
		}  
	p{  
		text-align: center;  
	}  
	.modal{  
		display: none;  
		width: 100%;  
		height: 100%;  
		position: fixed;  
		left: 0;  
		top: 0;  
		z-index: 1000;  
		background-color: rgba(0,0,0,0.5);  
	}  
	.modal-content{  
		display: flex;  
		flex-flow: column nowrap;  
		justify-content: space-between;  
		width: auto;  
		max-width: 1000px;  
		height: auto;  
		max-height: 1000px;  
		margin: 100px auto;  
		border-radius:10px;  
		background-color:#fff;  
		-webkit-animation: zoom 0.6s;  
		animation: zoom 0.6s;  
		resize: both;  
		overflow: auto;  
	}  
	@-webkit-keyframes zoom{  
		from {-webkit-transform: scale(0)}  
		to {-webkit-transform: scale(1)}  
	}  
	@keyframes zoom{  
		from {transform: scale(0)}  
		to {transform: scale(1)}  
	}  
	.modal-header{  
		box-sizing:border-box;  
		border-bottom:1px solid #ccc;  
		display: flex;  
		justify-content: space-between;  
		align-items: center;  
	}  
	.close{  
		color: #b7b7b7;  
		font-size: 30px;  
		font-weight: bold;  
		margin-right: 20px;  
		transition: all 0.3s;  
	}  
	.close:hover, .close:focus{  
		color: #95b4ed;  
		text-decoration: none;  
		cursor: pointer;  
	}  
	.modal-body{  
		padding: 10px;  
		font-size: 16px;  
		box-sizing:border-box;  
	}  
	@media only screen and (max-width: 700px){  
		.modal-content {  
			width: 80%;  
		}  
	}
</style>
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
		        <li class="active">消费记录</li>
		      </ul>
		    </div>

	 <div class="bill_search">
	        <form action="${path}/bill/feeTime" id="appform"  name="appform">
	        <ul>
	          <li class="li1">
	          <div class="select_box select_app">
	          	<label>选择应用：</label>
		          <div input="appSid" tabindex="2" class="select"  defaultValue="${appSid}">	
					<span>所有应用<i>&nbsp;</i></span>
						<ul>
							<li val="">所有应用</li>
							<s:if test="appList!=null">
		                		<s:iterator value="appList" var="app">
		                		  	<li val="${app.appSid}">${app.appName}</li>
		                		</s:iterator>
	               			</s:if>
	               		</ul>
				</div>
	          </div>
	          <div class="select_box select_app">
	          	<label>开始时间段：</label>
		          <div input="time" tabindex="2" class="select"  defaultValue="${time}">	
					<span>00:00-24:00<i>&nbsp;</i></span>
						<ul>
							<li val="">00:00-24:00</li>
							<li val="00:00-02:00">00:00-02:00</li>
							<li val="02:00-04:00">02:00-04:00</li>
							<li val="04:00-06:00">04:00-06:00</li>
							<li val="08:00-10:00">08:00-10:00</li>
							<li val="10:00-12:00">10:00-12:00</li>
							<li val="12:00-14:00">12:00-14:00</li>
							<li val="14:00-16:00">14:00-16:00</li>
							<li val="16:00-18:00">16:00-18:00</li>
							<li val="18:00-20:00">18:00-20:00</li>
							<li val="20:00-22:00">20:00-22:00</li>
							<li val="22:00-24:00">22:00-24:00</li>
	               		</ul>
				</div>
	          </div>
	          <div style="float: left;">
	            <label>日期：</label><input type="text" name="datetime" id="datetime" value="${datetime}"/><span>
	            <label>关键字：</label><input type="text" name="keyword" id="keyword" value="${keyword}" placeholder="（自定义）会话ID"/><span>  
               		<input type="submit" value="查 询" class="org_btn"/>
	          </div>
	            <br />
	            <span class="tips"> </span>
	          </li>
	      </ul>
	      </form>
	    </div>
	    
		<div style="font-size: 14px;">日总消费：<font color="green" style="font-size: 18px">${dayTotalFee}</font> 元</div>
	    <!--消费记录 consumption_list bof-->
		<div class="table_box">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="50px">序号</th>
						<th>应用</th>
		                <th>时长（S）</th>
		                <th>流量（MB）</th>
		                <c:if test="${user.feeType==101}" >
		              		<th>费率（分/G）</th>
		                </c:if>
		                <c:if test="${user.feeType==102}" >
		              		<th>费率（分/min）</th>
		                </c:if>
		                <th>费用（分）</th>
		                <!-- <th>会话ID</th> -->
		                <th>自定义会话ID</th>
		                <th>时段</th>
		                <th>查看</th>
					</tr>
				</thead>
				<tbody>
					 <s:if test="page!=null&&page.list.size()>0">
			     		 <s:iterator value="page.list" var="fee">
							<tr>
						      	<td>${fee.rownum}</td>
						      	<td>${fee.appName}</td>
	            				<td>${fee.feetime}</td>
				            	<td>${fee.total_traffic_in+fee.total_traffic_out}</td>
				            	<td>${fee.userfeerate}</td>
				            	<td>${fee.userfee}</td>
				            	<%-- <td>${fee.cookie}</td> --%>
				            	<td>${fee.aliasSessionId}</td>
				            	<td>${fee.starttime} - ${fee.endtime}</td>
				            	<td>
				            		<a onclick="showSessionId('${fee.cookie}')" href="javascript:void(0)" style="color: rgba(0,102,204,1)">会话ID</a>
				            		 | 
				            		<a href="/user/querySessionPacketLoss?cookieId=${fee.cookie}&isJump=true&datetime=${datetime}&appSid=${fee.appid}&aliasSessionId=${fee.aliasSessionId}" style="color: rgba(0,102,204,1)">丢包率</a>
				            	</td>
					      	</tr>
						</s:iterator>
					</s:if>
					<s:else>
				      	<tr>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td colspan="2">暂无数据！</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
				      	</tr>
			      </s:else>
				</tbody>
			</table>
		</div>
		<u:page page="${page}" formId="appform" ></u:page>
	    <!--消费记录 consumption_list eof-->
	  </div>  
	  <!--右侧main bof-->   
	</div>
	<!--主体content eof--> 
	
	<!--弹框--> 
	<div id="modal" class="modal">  
	    <div class="modal-content">  
	        <header class="modal-header">  
	            <h4><span id="title"></span> </h4>  
	            <span class="close" >x</span>  
	        </header>  
	        <div class="modal-body">  
	            <table style="font-size:14px;" border="1" cellspacing="0" cellpadding="0">
     				<tr>
     					<th width="10%">会话ID</td>
     					<td id="cookie"></td>
     				</tr>
     			</table>	
	        </div>  
	    </div>  
	</div>  
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
	<script type="text/javascript">
		$(function(){
			//表格隔行变色
			$(".table_box table tr:even").addClass("even");
		});
		
		function appCsm(){
			$('appform').submit();
		}		
		
		$("#datetime").datepicker({
			 dateFormat:"yy-mm-dd",
			 maxDate:0
		});
		
		if($("#datetime").val() == ''){
			$("#datetime").datepicker( 'setDate' , new Date());
		}
		
		//弹框
		var close = document.getElementsByClassName('close')[1];  
		var modal = document.getElementById('modal');  
		close.addEventListener('click', function(){  
		    modal.style.display = "none";  
		});  
		
		function showSessionId(cookie){
	    	$('#cookie').text(cookie);
	    	var modal = document.getElementById('modal');  
	        modal.style.display = "block";  
	    }
		
		//按ESC关闭弹框
		$(document).keyup(function(e){
	        var key =  e.which;
	        if(key == 27){
	        	var modal = document.getElementById('modal');  
		        modal.style.display = "none";  
	        }
	    });
	</script>
</body>
</html>
