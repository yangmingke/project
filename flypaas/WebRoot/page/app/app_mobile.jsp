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
	<title>flypaas开放平台管理中心——号码管理</title>
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
					<li><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li><a href="<%=path %>/app/appManager">应用列表</a></li>
					<li class="active"><a href="javascript:void(0)">[${app.appName}]</a></li>
				</ul>
			</div>

			<div class="main_tab_tit">
				<ul>
					<a href="<%=path%>/app/edit?appSid=<u:des3 value='${appSid}' />"><li>应用信息</li></a>
					<li class="active">号码管理</li>
					<a href="javascript:void(0)" onclick="lyManager()"><li>录音管理</li></a>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、已上线应用可以通过号码管理，在线提交号码进行审核，审核后可正式使用。</p>
				<p>2、未上线应用请使用「测试Demo」---「测试号码」内号码进行应用开发调试。</p>
			</div>
			<!--说明state_box eof-->

			<!--搜索search_box bof-->
			<div class="search_box">
				<div class="search app_search">
					<form method="post" action="<%=path %>/app/showNbrList">
						<input type="text"  name="nbr" value="${nbr }"/>
						<input type="hidden" id="appSid" value="<u:des3 value='${appSid}' />" name="appSid"/>
						<input type="submit" value="" />
					</form>
				</div>
				<div class="search_link"><a href="#" class="submit float_link">提交号码</a></div>
			</div>
			<!--搜索search_box eof-->

			<!--表格列表 table_box bof-->
			<div class="table_box">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>号码</th>
							<th>入库日期</th>
							<th>有效期至</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="page.list.size()>0">
							<s:iterator value="page.list" id="l">
							<tr>
								<td>${l.nbr }</td>
								<td><s:date name="#l.create_date" format="yyyy-MM-dd"/></td>
								<td><s:date name="#l.exp_date" format="yyyy-MM-dd"/></td>
								<s:if  test="#l.exp_status==2">
										<td class="red">
										已过期
										</td>
								</s:if>
								<s:else>
									<s:if test="#l.status==2">
										<td class="orange">
										待审核
										</td>
									</s:if>
									<s:elseif test="#l.status==3">
										<td>
										审核通过
										</td>
									</s:elseif>
									<s:elseif test="#l.status==4">
										<td class="red">
										审核不通过
										</td>
									</s:elseif>
								</s:else>
								<td class="blue"><s:if test="#l.exp_status==2"><a href="<%=path %>/app/updateShowNbr?appShowNbrs.appSid=<u:des3 value='${l.app_sid}' />&&appShowNbrs.nbr=${l.nbr }">重新提交</a></s:if><a href="javascript:void(0)" onclick="popupBox('提示','是否删除应用？','delNbr(\'${l.nbr }\')')">删除</a></td>
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
				 <s:form namespace="/app" action="showNbrList" id="Frm" theme="simple">
		         	 <input type="hidden" id="currentPage" name="page.currentPage" value="${page.currentPage }"/>
		         	 <input type="hidden" id="appSid" value="<u:des3 value='${appSid}' />" name="appSid"/>
		         </s:form>
		         <u:page page="${page}" formId="Frm" ></u:page>
			<!--分页pagebox eof-->
		</div>
		<!--右侧main bof-->

	</div>
	<!--弹层（重置） bof-->
	<div class="background_box">&nbsp;</div>
	<div class="float_box submitnum_box">
		<div class="float_tit">
			<h1>提交号码</h1>
		</div>
		<form action="<%=path %>/app/addShowNbr" method="post" id="addFrm" name="addFrm">
			<div class="float_ctn">
		      <div class="float_field">
		        <dl>
		          <dt>输入号码</dt>
		          <dd>
		          	<textarea name="nbrList"></textarea><br />
		          	<input type="hidden" id="appSid" value="<u:des3 value='${appSid}' />" name="appSid"/>
		          	<span class="tips">可批量添加，一行一条</span></dd>
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
		$(function(){
			$(".table_box table tr:even").addClass("even");
		});
		function delNbr(nbr){
			var appSid ="<u:des3 value='${appSid}'/>";
			location.href="<%=path%>/app/delShowNbr?appShowNbrs.nbr="+nbr+"&appShowNbrs.appSid="+appSid;
		}
		function lyManager(){
			location.href="<%=path %>/app/lyList?appSid=<u:des3 value='${app.appSid}' />";
		}
	</script>
</body>
</html>