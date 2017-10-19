<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——应用列表</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/page/js/app.js"></script>
</head>
<body id="02-1">
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
					<li><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li class="active"><a href="javascript:void(0)">应用列表</a></li>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<c:if test="${user.superSid==null}">
					<p>1、未上线应用只可在配置的号码之间开发调试</p>
					<p>2、上线应用前请先完成身份认证，可选个人用户、企业用户认证，个人用户认证可升级为企业用户。</p>
					<p>3、企业用户可通过控制台［财务管理］申请企业增值税发票</p>
					<p>4、可为每个应用配置［可用余额］</p>
				</c:if>
				<c:if test="${user.superSid!=null}">
					<p>1、未上线应用只可在配置的号码之间开发调试</p>
					<p>2、上线应用前请先完成身份认证</p>
					<p>3、企业用户可通过控制台［财务管理］申请企业增值税发票</p>
					<p>4、可为每个应用配置［可用余额］</p>
				</c:if>
			</div>
			<!--说明state_box eof-->

			<!--搜索search_box bof-->
			<div class="search_box">
				<div class="search app_search">
					  <s:form namespace="/app" theme="simple"  action="appManager" method="post" name="appform" id="appform"  >
						<input type="text" placeholder="应用名称" name="app.appName" value="${app.appName }"/>
						<input type="hidden" id="currentPage" name="page.currentPage"/>
						<input type="submit" value=""/>
					</s:form>
				</div>
				<div class="search_link"><a href="<%=path %>/app/addPage">创建应用</a></div>
			</div>
			<!--搜索search_box eof-->

			<!--表格列表 table_box bof-->
			<div class="table_box">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>应用名称</th>
							<th>应用类型</th>
							<th>可用余额</th>
							<th>创建时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						 <s:if test="page!=null&&page.list.size()>0">
			     		 <s:iterator value="page.list" var="app1">
							<tr>
							<td class="blue"><a href="<%=path%>/app/appInfo?appSid=<u:des3 value='${app1.app_sid}' />" class="blue"><s:property value="#app1.app_name" /></a></td>
							<td>
								  <s:if test="#app1.app_kind==1">
							      	网站
							      </s:if>
							      <s:elseif test="#app1.app_kind==2">
							     	 移动应用
							      </s:elseif>
							      <s:else>
							      	其他
							      </s:else>
							</td>
							<td>
								<s:if test="#app1.blcStatus==null || #app1.blcStatus==2">
								「与主账号共享」
								</s:if>
								<s:else>
								￥${app1.balance}
								</s:else>
							</td>
							<td><s:date name="#app1.create_date"  format="yyyy-MM-dd HH:mm:ss" /></td>
							
							  <s:if test="#app1.status==0">
							 	 <td>
						      	未上线
						      	</td>
						      </s:if>
						      <s:elseif test="#app1.status==1">
						      	<td class="green">
						     	 已上线
						     	 </td>
						      </s:elseif>
						      <s:elseif test="#app1.status==2">
						        <td class="red">
						     	 审核不通过
						     	</td>
						      </s:elseif>
						      <s:elseif test="#app1.status==3">
						      	<td class="red">
						     	已删除
						     	</td>
						      </s:elseif>
						      <s:elseif test="#app1.status==4 || #app1.status==6">
						      	<td class="orange">
						     	待审核
						     	</td>
						      </s:elseif>
						      <s:elseif test="#app1.status==5">
						        <td class="red">
						     	强制下线
						     	</td>
						      </s:elseif>
							
							<td class="blue">
								  <input type="hidden" value="${app1.app_name}" id="appName" />
							      <%-- <a href="javascript:void(0);" class="recharge_link" onclick="recharge('${app1.app_sid}','${app1.blcStatus }','${app1.balance }')">充值</a> | --%>
							      <s:if test="#app1.status!=4 && #app1.status!=5">
							      <a href="<%=path%>/app/edit?appSid=<u:des3 value='${app1.app_sid}' />">管理</a> |
							      </s:if>
							      <s:if test="#app1.status==0 || #app1.status==2">
							      	<a href="javascript:void(0)"  onclick="onLineApp('${app1.app_sid}')">申请上线   |</a>
							      </s:if>
							      <s:if test="#app1.status==1">
								      <s:if test="#app1.cli_num > 0">
								      	<a href="javascript:void(0)"  onclick="SDKIDList('${app1.app_sid}')">SDKID列表   |</a>
								      </s:if>
								      <s:elseif test="1 == 1">
								      	<%-- <a href="javascript:void(0)"  onclick="createSDKID('${app1.app_sid}')" id="createSDKID">创建SDKID   |</a> --%>
								      	<a href="javascript:void(0)" onclick="popupBox('提示','是否创建SDKID？','createSDKID(\'${app1.app_sid}\')',this)"  mame="createSDKID">创建SDKID   |</a>
								      </s:elseif>
							      </s:if>
							      <a href="javascript:void(0)" onclick="popupBox('提示','是否删除应用？','deleteApp(\'${app1.app_sid}\')')">删除</a>
							</td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
				      	<tr>
				      	<td>暂无数据！ &nbsp;&nbsp;<a href="<%=path%>/app/addPage" style="color:#4981bf">去创建应用</a></td>
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
		<!--弹层(充值) bof-->
		<div class="background_box">&nbsp;</div>
       	 <div class="float_box app_recharge_box" id="recharge_box">
          <div class="float_tit">
            <h3>&nbsp;&nbsp;应用充值</h3>
            <span class="close"></span>
          </div>
            <div class="float_ctn">
              <form method="post" name="msgForm" id="msgForm">
              <div class="float_field">
	              <dl>
	              	<dt><input type="radio" id="yzx" name="app_recharge" checked="checked" value="yzx"/></dt>
	              	<dd>
						与主账户共享（默认）
	              	</dd>
	              </dl>
              </div>
              <div class="float_field">
	              <dl>
	              	<dt><input type="radio" id="app" name="app_recharge" value="app" class="setting_radio"/></dt>
	              	<dd>
						独立设置
	              	</dd>
	              	<dd class="setting">
	              		<dl>
			              <dt>分配额度</dt><dd><input type="text" name="appBalance.balance" id="charge"/>
			              <input type="hidden" name="appBalance.appSid" id="chargeAppSid" />
			              <em>元</em>
			              <span id="charge_error"  class="error" style="display:none;"></span></dd>
			              <dd><span class="tips">设定的余额不可超过主账户余额，当应用可用余额为0时自动停止服务。当前剩余可用余额：${acctBalance.rechargeBalance }</span></dd>
			              </dl>
	              	</dd>
	              </dl>
              </div>
              <div class="float_btn">
                <input type="button" value="取消" class="cancel_btn"/>
                <input type="button" value="确 定" class="confirm_btn" id="rechargeSubt"/>
              </div>
              </form>
            </div>
          </div>

			
			<!--表格列表 table_box eof-->
		</div>
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
	
	<script type="text/javascript" >
	$(function(){
		//表格隔行变色
		$(".table_box table tr:even").addClass("even");
		
		$("#rechargeSubt").click(function(){
			var vl = $("input[name='app_recharge']:checked").val();
			if(vl=='yzx'){
				$("#msgForm").attr("action","<%=path %>/app/unBindAppBalance");
				$("#msgForm").submit();
			}else if(vl=='app'){
				if(rechargeFrm.money()){
					$("#msgForm").attr("action","<%=path %>/app/appBalance");
					$("#msgForm").submit();
				}
			}
		});


		$(".float_ctn input[type='radio']").click(function(){
			if($(this).hasClass("setting_radio")){
				$(".setting").slideDown();
			}else{
				$(".setting").slideUp();
			}
		})




		
		
	});
	
	var rechargeFrm = {
			money:function(){
				var charge = $("#charge").val();
				if(!verfiyMoney(charge)){
					$("#charge_error").text("充值金额只能为正整数");
					$("#charge_error").show();
					return false;
				}
				$("#charge_error").hide();
				return true;
			}
	}
	
    function recharge(appSid,status,balance){
			  if(status=="2"){
				  $("#yzx").attr("checked","checked");
				  $("#charge").val("");
				  $(".setting").hide();
			  }else if(status=="1"){
				  $("#app").attr("checked","checked");
				  $("#charge").val(balance);
				  $(".setting").show();
			  }else{
				  $("#yzx").attr("checked","checked");
				  $("#charge").val("");
				  $(".setting").hide();
			  }
			  $("#chargeAppSid").val(appSid);
			  $("#recharge_box").show();
			  $(".background_box").show();
	}
	function onLineApp(appSid){
			$.ajax({
				url:"<%=path%>/app/isAuth",
				type:"post",
				dataType: "text",
				success: function (data) {
		          if(data=="3"){
		        	  location.href="<%=path%>/app/onLine?appSid="+appSid;
		          }else{
		        	  popupBox("认证提示","请先认证信息或认证信息是否已通过","toOauth()");
		          }
		        },
		        error: function (msg) {
		        }
			});
		}
	function toOauth(){
		var a= "${session.user.oauthStatus}" ;
		var hf = "" ;
		if(a!=null && a!="" && a!="null"){
			var userType = "${session.user.userType}" ;
			if(userType==1){
				hf="<%=path%>/user/oAuthPersonalInfo";
			}else{
				hf="<%=path%>/user/oAuthCompanyInfo";
			}
		}else{
			hf="<%=path%>/user/oAuthDispather";
		}
		window.location.href=hf;
	}
	var deleteApp = function(appsid){
	  	location.href="<%=path%>/app/delete?appSid="+appsid;
	};
	var SDKIDList = function(appsid){
	 	 location.href="<%=path%>/app/clientList?appSid="+appsid;
	};
	var createSDKID = function(appsid){
		 $("input").attr("disabled", true);
		 $('#popup_smt').val("正在创建中……");
	 	 location.href="<%=path%>/app/createClient?appSid="+appsid;
	};
</script>
</body>
</html>