<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/highcharts.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/modules/exporting.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
		if(null != object){
			out.println((String)object);
		}
	%>
</head>
<body id="01-1">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<input type="hidden" value="<%=path%>" id="path_fo_js" />
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<!-- <div class="breadcrumbs">
				<ul>
					<li><a href="#">开发者控制台</a></li>
					<li class="active"><a href="#">控制台</a></li>
				</ul>
			</div> -->
			<div class="note_box">
				<p>
				 <s:if test="userMsg!=null">
	      		   <span>
		       	  		<s:if test="userMsg.msgType==2">
		       	  			资费变更
		       	  		</s:if>
		       	  		<s:elseif test="userMsg.msgType==3">
		       	  			充值确认
		       	  		</s:elseif>
		       	  		<s:elseif test="userMsg.msgType==4">
		       	  			余额提醒
		       	  		</s:elseif>
		       	  		<s:else>
		       	  			公告
		       	  		</s:else>
			       	 </span>
		       	  	&nbsp;&nbsp;${userMsg.msgTitle }
		       	 </s:if>
		       	 <s:else>
		       	 	公告&nbsp;&nbsp;flypaas运营平台正式上线啦！
		       	 </s:else>
		         <a href="<%=path%>/user/notice">查看详情</a>
		        </p>
			</div>

			<div class="item_box">
				<div class="item_left">
					<div class="box box1">
						<div class="box11">
							<h1>用户信息</h1>
							<div class="list">
								<p>
									<b>账户  Sid：</b>
									<span>${user.sid}</span>
								</p>
								<p>
									<b>认证  Token：</b>
									 <s:if test="'true'==#session.view_token_auth">
							             <span id="span_token">${user.token}</span><!-- <a href="#" class="reset float_link">重 置</a> -->
							         </s:if>
							         <s:else>
							           	 <span id="span_token">********</span><a href="#" id="view_link" class="reset float_link">查看</a>
							         </s:else>
								</p>
								<p>
									<b>Rest URL：</b>
									<span>
									<s:iterator value="paramsList" var="p">
						              <s:if test="#p.paramKey==1">
						              	${p.paramValue}
						              </s:if>
						             </s:iterator>
						             </span>
								</p>
							</div>
						</div>
						<div class="box12">
							<p>
								<b>用户身份：</b>
								
								<s:if test="user.userType==1">
			                		<span>
			                			<s:if test="user.oauthStatus==3">
			                				个人用户
			                			</s:if>
			                			<s:else>
			                				注册用户
			                			</s:else>
			                		</span>
			                		<s:if test="user.oauthStatus==3">
			                			<a href="<%=path %>/user/oAuthDispather">可升级</a>
			                		</s:if>
			                	</s:if>
			                	<s:elseif test="user.userType==2">
			                		<span>
			                			<s:if test="user.oauthStatus==3">
	 										企业用户
	 									</s:if>
	 									<s:else>
			                				个人用户
			                			</s:else>
			                		</span>
			                	</s:elseif>
			                	
								<span class="tips">注：完成身份认证可正式上线应用对外服务，非认证帐号只可开发调试</span>
							</p>
							<p>
								<b>资费类型：</b><span>${pck.packageName }</span><%-- <a href="<%=path %>/price">查 看</a> by yangmingke --%>
							</p>
						</div>
					</div>
					<div class="box box2">
						<!-- <h1>应用发布步骤</h1> -->
						<h1>用户测试DEMO</h1>
						<div class="step">
							<span class="tips">注：每个用户注册后赠送10元额度，可用于测试DEMO，以快捷体验快传融合通讯开放平台</span>
								<c:if test="${user.superSid == null}">
									<a href="<%=path %>/app/testDemo" style="float: right;">查 看</a>
								</c:if>
							<ul>
								<!-- <li class="step1">&nbsp;</li>
								<li class="step_space">&nbsp;</li>
								<li class="step2">&nbsp;</li>
								<li class="step_space">&nbsp;</li>
								<li class="step3">&nbsp;</li>
								<li class="step_space">&nbsp;</li>
								<li class="step4">&nbsp;</li>
								<li class="step_space">&nbsp;</li>
								<li class="step5">&nbsp;</li> -->
							</ul>
						</div>
					</div>
				</div>
				<div class="item_right">
					<div class="box box3">
						<p><span>账户余额</span><a href="<%=path %>/pay/newOrder">在线充值</a></p>
						<p><span><b>
						<s:if test="acctBalance!=null">
		                	￥<s:property value="acctBalance.balance"/>
	                	</s:if>
	                	<s:else>
	                		￥0.0000
	                	</s:else>
	                	</b></span></p>
						<p><span class="small">昨日消费</span></p>
						<p><span class="small"><b>￥<s:property value="ysdConsume"/></b></span></p>
					</div>
					<div class="box box4">
						<p><span>上线应用：<b>${onLineAppCount}</b>个</span><a href="<%=path %>/app/appManager">管理应用</a></p>
					</div>
					<div class="box box5">
						<p><span>Client 活跃数：<b>${empty activeCount?0:activeCount.active_client_num}</b></span></p>
					</div>
				</div>
			</div>

			<!--24小时消费数据bof -->
			<div class="chart_box">
		          <div class="tit">
		          <h1>24小时消费数据</h1>
		            <ul>
		              <li id="client" class="active" onclick="getTodayConsumeInfo('traffic')">流量</li>
		              <c:if test="${user.superSid == null}">
			              <li id="voice" onclick="getTodayConsumeInfo('voice')">语音</li>
			              <li id="video" onclick="getTodayConsumeInfo('video')">视频</li>
			              <li id="client" onclick="getTodayConsumeInfo('client')">Client账号</li>
		              </c:if>
		              <!-- <li id="im" onclick="getTodayConsumeInfo('im')">IM</li>
		              <li id="voicecheckcode" onclick="getTodayConsumeInfo('voicecheckcode')">语音验证码</li>
		              <li id="yunsms" onclick="getTodayConsumeInfo('yunsms')">智能验证</li> -->
		            </ul>
		          </div>
		          <div class="ctn">
		            <div id="drawDiv"></div>
		          </div>
			</div>
			<!--24小时消费数据eof -->
			
			<!--消费数据tab-->
			
		</div>
		<!--右侧main bof-->

	</div>
	
	<!--弹层(短信号码) eof--> 
    <s:if test="'true'==#session.view_token_auth">
	    <!--弹层(开发者账号重置) bof-->
	    <div class="float_box reset_box" id="reset_box" style="display:none;">
	      <div class="float_tit">
	        <h1>重置token</h1>
	      </div>
	      <div class="float_ctn">
	        <s:form method="post" name="resetForm" namespace="/user" action="resetToken" theme="simple">
	          <p>Auth token是主账户的唯一密码，进行重置token后，旧的token将不可用，故在重置前请慎重考虑是否进行此项动作。快传团队建议只有在发现您的账户存在被盗用的可能下才进行重置操作，并使用新的tokne进行更新。</p>
	          <p>
	          	<span id="new_token"></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="###" onclick="renewToken(2);">重新生成</a>
	          </p>
	          <p class="checkbox">
	            <input type="checkbox" />
	            立即生效（旧token将不可再用）</p>
	          <div class="float_btn">
	            <input type="submit" value="确 定" disabled="disabled" class="confirm_btn"/>
	            <input type="button" value="取 消" class="cancel_btn" />
	          </div>
	        </s:form>
	      </div>
	    </div>
    </s:if>
    <s:else>
	    <!--弹层(查看token) bof-->
	    <div class="float_box view_token_box" id="view_token_box" style="display:none;">
	      <div class="float_tit">
	        <!-- <h1>手机验证</h1> -->
	        <h1>查看token</h1>
	      </div>
	      <div class="float_ctn">
          <form  action="/user/verifyMobileForToken" method="post" name="phoneForm" id="phone_form">
            <div class="float_field">
		        <dl>
		         <%--  <dt>手机验证</dt>
		          <dd class="relate_link"><input type="button" value="获取短信验证码" onclick="codeAjax('sms','smscodeinput','voicecodeinput','获取短信验证码')" id="smscodeinput" /> 或
            		<input type="button" onclick="codeAjax('voicecode','voicecodeinput','smscodeinput','获取语音验证码');" id="voicecodeinput" value="获取语音验证码" /></dd>
		          <dd><input type="text" id="inputmovecode" name="viewTokenCode" placeholder="请输入短信验证码"/><span class="error" id="move_phone_code_error" style="display:none"></span></dd> by yangmingke--%>
		          <span><h1 style="color: #529b2c;text-align: center;">是否查看token,将token明文显示？</h1></span>
		        </dl>
		      </div>
		      <div class="float_btn">
		        <input type="button" class="cancel_btn" value="取消">
		        <input type="submit" id="submit_verifyMobileForToken" class="confirm_btn" value="确定">
		        <input type="hidden" id="vmovecode"  />
             	<input type="hidden" id="movecode"  />
		      </div>             
          </form>
	      </div>
	    </div>
    </s:else>
	<!--主体content eof-->
	
	<script type="text/javascript">
			$(function(){
				  $(".chart_box .tit ul li").click(function(){
					var li_index = $(this).index();
					$(".chart_box .ctn").find("div").eq(li_index).show().siblings("div").hide();
					$(this).addClass("active").siblings("li").removeClass("active");
				  });
				 //整点刷新
	         	  self.setInterval("fresh()",1000);
	         	  //今日消费
	  			  getTodayConsumeInfo("traffic");
	         	  
	  			  $(".reset_box input[type='checkbox']").click(function(){
		                 if($(this).attr("checked")){
		     			     $(".float_btn .confirm_btn").removeAttr("disabled");
		     			     $(".float_btn .confirm_btn").css("cursor","pointer");
		     			     $(".float_btn .confirm_btn").removeClass("disabled_btn");
		                 }else{
		                     $(".float_btn .confirm_btn").attr("disabled","disabled");
		                     $(".float_btn .confirm_btn").css("cursor","default");
		                     $(".float_btn .confirm_btn").addClass("disabled_btn");
		                 }
	     			});
			});
			
 		   //获取今天的消费信息，指定格式数据
 		   function getTodayConsumeInfo(type){
 			   var title = "";
 			   var url = "" ;
 			   var typeTitile = "";
 			   if(type=="voice"){
 				   url = "todayVoiceCs"; 
 				   title="今天的语音消费" ;
 				   typeTitile = "元" ;
 			  }else if(type=="sms"){
 				   url = "todaySmsCs"; 
 				   title="今天的SMS消息发送" ;
 				   typeTitile = "条" ; 
 			  }else if(type=="video"){
				   url = "todayVideoCs"; 
				   title="今天的视频消费" ;
				   typeTitile = "元" ;
 			   }else if(type=="im"){
 				   url = "todayImCs"; 
 				   title="今天的IM消息发送" ;
 				   typeTitile = "条" ;
 			   }else if(type=="voicecheckcode"){
 				   url = "todayVoicecheckcodeCs"; 
 				   title="今天的语音验证码发送" ;
 				   typeTitile = "条" ;
 			   }else if(type=="client"){
 				   url = "todayClientCs";
 				   title="今天的client活跃" ;
 				   typeTitile = "个" ;
 			   }else if(type=="traffic"){
 				   url = "todayTrafficCs";
 				   title="今天的流量消耗" ;
 				   typeTitile = "KB" ;
 			   }
 			   else{
 				   url = "todayCloudCs";
 				   title="今天的智能验证消费" ;
 				   typeTitile = "元" ;
 			   }
 			   $.ajax({
     				url:"<%=path%>/user/"+url,
     				type:"post",
     				dataType: "text",
     				success: function (data) {
							data = eval("("+data+")");
     					drawTodayConsumeInfo(data,title,typeTitile);
     		        }
     			});
 			   
 		   }
 		   var chart ;
 		   function drawTodayConsumeInfo(data,text,typeTitile){
 				chart = new Highcharts.Chart({
					chart: {
			            renderTo: 'drawDiv',
			            defaultSeriesType: 'spline',
			            events: {
			                //load: requestData
			            }
			        },
		            title: {
		                text: text,
		                x: -20 //center
		            },
		            subtitle: {
		                text: '',
		                x: -20
		            },
		            xAxis: {
		                categories: ['0', '1', '2', '3', '4',
		                             '5','6','7',
		                             '8', '9', '10', '11', '12', '13',
		                             '14', '15', '16', '17', '18', '19',
		                             '20', '21', '22', '23'
		                             ],
		                gridLineWidth: 1, //设置网格宽度为1 
		                lineWidth: 1,  //基线宽度 
		                labels:{y:20}  //x轴标签位置：距X轴下方26像素
	   		            
		            },
		            yAxis: {
		                title: {
		                    text: '' //左侧边栏
		                },
		                min:0,
		                lineWidth: 1, //基线宽度 
		                plotLines: [{
		                    value: 0,
		                    width: 1,
		                    color: '#808080'
		                }]
		            },
		       		//去掉线上的点
	   		        plotOptions:{ 
	   		            series:{
	   		                marker:{
	   		                    enabled:false
	   		                }
	   		            }
	   		        },
		            tooltip: {
		                valueSuffix: typeTitile
		            },
		            //设置图例
		            legend: {
		            	enabled:false //去掉图例
		            },
		       		//右下角不显示LOGO 
	   		        credits: { 
	   		            enabled: false   
	   		        },
		            series:data
		        });
 		   }
 		   
		  	 function fresh(){
				  var myDate = new Date();
				  var min = myDate.getMinutes();     //获取当前分钟数(0-59)
				  var sec = myDate.getSeconds();     //获取当前秒数(0-59)
				  if(min==0 && sec==30){
					  //今日消费
		  			  getTodayConsumeInfo("voice");
				  }
			  }
		  	 /*-----------重置弹层---------------*/
		  $("#reset_link").click(function(){
			  renewToken(1);
			  $("#reset_box").show();
			  $(".background_box").show();
		  });
		  $("#view_link").click(function(){
			  $("#view_token_box").show();
			  $(".background_box").show();
		  });
			  
	       	$(".reset_box .btn input:eq(0)").css("cursor","default");
	        $(".reset_box input[type='checkbox']").click(function(){
	               if($(this).attr("checked")){
	                 $(".reset_box .btn input:eq(0)").addClass("blue_btn");
	   			  	$(".reset_box .btn input:eq(0)").removeAttr("disabled");
	                 $(".reset_box .btn input:eq(0)").css("cursor","pointer");
	               }else{
	                 $(".reset_box .btn input:eq(0)").removeClass("blue_btn");
	                 $(".reset_box .btn input:eq(0)").attr("disabled","disabled");
	                 $(".reset_box .btn input:eq(0)").css("cursor","default");
	               }
	   		});
	
            //绑定手机
            $("#voicecodeinput").removeAttr("disabled");
            $("#smscodeinput").removeAttr("disabled");
	        //绑定手机表单验证
	         $("#phone_form").submit(function(){
	           	/* if(!compareMoveCode($("#inputmovecode").val())){
	           		 $("#move_phone_code_error").fadeIn();
	                    $("#move_phone_code_error").html("验证码不合法");
	                    return false;
	           	} */ //by yangmingke 
	               $("#move_phone_code_error").hide();
	               return true;
	         });
	         
	         var wait = 120;
	         var time1 = function(id,id1,cc) {
	             if (wait == 0) {
	             	$("#"+id).removeAttr("disabled");
	             	$("#"+id1).removeAttr("disabled");
	             	$("#"+id).val(cc);
	                 wait = 120;
	                 $("#movecode").val("");
	             } else {
	                 $("#"+id).attr("cursor", "pointer");
	                 $("#"+id).val( wait + "秒");
	                 wait--;
	                 setTimeout(function () {
	                     time1(id,id1,cc);
	                 },
	                 1000);
	             }
	         };
	         
	         function codeAjax(type,id,id2,msg){
	           $("#voicecodeinput").attr("disabled", true);
	       	   $("#smscodeinput").attr("disabled", true);
	           $.ajax({
	             url:"<%=path%>/checkcode/check4token",
	             type:"post",
	             dataType: "text",
	             data:{'expType':type},
	             success: function (data) {
	           	  	var code = data.substring(0,1);
	           	  	if("0" == code){
	           	  		$("#movecode").val(data.substring(1));
	           	  	    time1(id,id2,msg);
	           	  	}else{
	           	  		 $("#move_phone_code_error").text("发送失败");
	                        $("#move_phone_code_error").fadeIn();
	                        $("#vmovecode").val(2);
	           	  	}
	                 },
	                 error: function (msg) {
	                    $("#movecode").val("");
	                 }
	           });
	         }
	         function compareMoveCode(value){
	           var movecode = $("#movecode").val();
	           value = hex_md5('${user.sid}'+value);
	           if(movecode!=value){
	             $("#move_phone_code_error").text("验证码不合法");
	             $("#move_phone_code_error").fadeIn();
	             return false;
	           }
	           $("#vmovecode").val(1);
	           return true;
	         }
	         function disabl(){
	           $("#smscodeinput").attr("disabled", true);
	           $("#voicecodeinput").attr("disabled", true);
	         }
	         function renewToken(type){
	       	  $.ajax({
	                 url:"<%=path%>/user/renewToken",
	                 type:"post",
	                 dataType: "text",
	                 data:{'resetTokenType':type},
	                 success: function (data) {
	               	  	$("#new_token").html(data);
	                     }
	               });
	         }
		  	 
			</script>

	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	<script type="text/javascript" src="<%=path%>/js/cookie.js"></script>
	<script type="text/javascript" src="<%=path%>/js/login.js"></script>
</body>
</html>