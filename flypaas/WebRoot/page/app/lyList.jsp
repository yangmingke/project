<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——录音管理</title>
	<%@include file="/page/resource.jsp"%>
</head>
<body id="02-1">
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
					<li class="crumbs1"><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li><a href="<%=path %>/app/appManager">应用列表</a></li>
					<li class="active"><a href="javascript:void(0)">[${app.appName}]</a></li>
				</ul>
			</div>

			<div class="main_tab_tit">
				<ul>
					<a href="<%=path%>/app/edit?appSid=<u:des3 value='${appSid}' />"><li>应用信息</li></a>
					<a href="javascript:void(0)" onclick="nbrManager()"><li>号码管理</li></a>
					<li class="active">录音管理</li>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、默认录音文件免费存储7天，超过7天平台自动删除该文件，如有需要请提前下载保留</p>
				<p>2、您可配置录音文件存储周期，超过7天按相应的存储资费计费。</p>
			</div>
			<!--说明state_box eof-->

			<!--搜索search_box bof-->
			<div class="search_box">
				<div style="color: #666;display: inline;float: left;font-family: 微软雅黑; position: relative;">
					当前文件总大小 ：${map.lyFileSize>0?map.lyFileSize:0} G ，当前配置的存贮周期为：${app.fileTimeout }
				</div>
				<div class="search_link"><a href="#" class="submit float_link">配置存贮周期</a></div>
			</div>
			<!--搜索search_box eof-->

			<!--表格列表 table_box bof-->
			<div class="table_box table_box_lylist">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>录音文件</th>
							<th>入库日期</th>
							<th>有效期至</th>
							<th>大小</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="page.list.size()>0">
							<s:iterator value="page.list" var="l">
							<tr>
								<td class="td_date" id="${l.id }">${l.stat_date }</td>
								<td><s:date name="#l.create_date" format="yyyy-MM-dd"/></td>
								<td><s:date name="#l.exp_date" format="yyyy-MM-dd" /></td>
								<td>${l.file_size } G</td>
								<td><a href="javascript:void(0)" onclick="popupBox('提示','确定删除录音文件','delLy(\'${l.id}\')')">删除</a></td>
							</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
							<td>暂无数据！</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>

			<!--表格列表 table_box eof-->

			<!--分页pagebox bof-->
				 <form action="/app/lyList" id="Frm">
		         	 <input type="hidden" id="currentPage" name="page.currentPage" value="${page.currentPage }"/>
		         	 <input type="hidden" id="appSid" value="<u:des3 value='${appSid}' />" name="appSid"/>
		         </form>
		         <u:page page="${page}" formId="Frm" ></u:page>
			<!--分页pagebox eof-->
		</div>
		<!--右侧main bof-->

	</div>
	<!--弹层（重置） bof-->
	<div class="background_box">&nbsp;</div>
	<div class="float_box submitnum_box">
		<div class="float_tit">
			<h1>配置存贮周期</h1>
		</div>
		<form action="<%=path %>/app/appFileimeSet" method="post" id="addFrm" name="addFrm">
			<div class="float_ctn">
		      <div class="float_field">
		        <dl>
<!-- 		          <dt>输入周期</dt> -->
		          <dd>
		          	<input type="text" id="fileTimeout" value="${app.fileTimeout }" name="app.fileTimeout"/>&nbsp;天
		          	<input type="hidden" value="<u:des3 value='${appSid}' />" name="app.appSid"/><span id="error" style="display:none" class="error">请输入合法的数字</span>
		          </dd>
		        </dl>
		      </div>
		      <div class="float_btn">
		        <input type="button" value="取消" class="cancel_btn" />
		        <input type="submit" value="确定" class="confirm_btn" />
		    </div>
	    	</div>
    	</form>
	</div>
	<!--弹层（重置） eof-->
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
	
	<!--表格变色-->
	<script type="text/javascript">
		function delLy(id){
			var appSid ="<u:des3 value='${appSid}'/>";
			location.href="<%=path%>/app/lyDel?statId="+id+"&appSid="+appSid;
		}
		
		var addFrm = {
				fileTime:function(){
					if(!verifyNumber($("#fileTimeout").val())){
						$("#error").show();
						return false;
					}
					$("#error").hide();
					return true;
				}
		}
		$(function(){
			$("#addFrm").submit(addFrm.fileTime);

			//录音日期点击展开列表
			$(".td_date").click(function(){
				$(this).parent("tr").after("<tr class='tr_list'><td colspan='5' class='td_list' id='td_list'></td></tr>").siblings("tr").next(".tr_list").remove();
				$(this).parent("tr").next(".tr_list").next(".tr_list").remove();
				lyDetail($(this).attr("id"));
			})
		})
		
		function lyDetail(id){
			var ly_div= $("#td_list");
			var loading ="<div class='loading' style='text-align:center; padding-top:80px;'><img src='${ctx}/images/loading.gif'/></div>" ;
			var t = true;
			$.ajax({
				url:"<%=path%>/app/lyDetail",
				type:"post",
				data:"statId="+id,
				dataType: "text",
				success: function (html) {
					if(html.indexOf('ly_csm_div') > 0){
						ly_div.html(html);
					}else{
						window.location.reload();
					}
	            },
	            beforeSend:function(XMLHttpRequest){
	            	ly_div.html(loading);
              	},
	            error: function (msg) {
	            	window.location.reload();
	            }
			});
		}
		function nbrManager(){
			var userType = "${user.userType}";
			var oauthStatus = "${user.oauthStatus}";
			var st = "${app.status}";
			var isShowNbr = "${app.isShowNbr}";
			if(userType==2){
				if(oauthStatus=="3"){
					if(st=="1"){
						if(isShowNbr==1){
							location.href="<%=path %>/app/showNbrList?appSid=<u:des3 value='${app.appSid}' />";
						}else{
							popupBox('提示','应用未开启显号功能','',2);
						}
					}else{
						popupBox('提示','应用上线了，才能进行号码管理','',2);
					}
				}else{
					popupBox('提示','请先完成资质认证','',2);
				}
			}else{
				popupBox('提示','该功能暂时只针对企业客户开放，请先完成企业认证。','',2);
			}
			
		}
	</script>
</body>
</html>