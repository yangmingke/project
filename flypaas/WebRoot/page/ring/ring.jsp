<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——铃声</title>
<script type="text/javascript" src="<%=path %>/js/jplay/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jplay/jquery.jplayer.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/page/css/form.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/page/css/style.css">
<script type="text/javascript" src="<%=path %>/page/js/form.js"></script>
<script type="text/javascript" src="<%=path %>/page/js/function.js"></script>
<script type="text/javascript" src="<%=path%>/js/form.js"></script>
<script type="text/javascript">
$(function(){
	$("#menu_2_4").addClass("active");
	$(".child p").eq(1).show().siblings("p").hide();
	var obj = $("#jquery_jplayer");
	obj.jPlayer({ready: function () {
		            $(this).jPlayer("setMedia", {
		                mp3: ""
		            });
		        },
		        swfPath: "swf",
		        supplied: "mp3"
		    });
	play_fun  = function (url){
		obj.jPlayer("setMedia", { mp3: url});
		obj.jPlayer("play");
	};
});
</script>
</head>

<body id="02-4">
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
				<li><a href="#">应用管理</a></li>
				<li class="active"><a href="#">语音库管理</a></li>
			</ul>
		</div>

		<div class="main_tab_tit">
			<ul>
				<li class="active" onclick="href2url('<%=path%>/app/ring/query')">铃声</li>
				<li onclick="href2url('<%=path%>/app/cloudNotice')">语音通知</li>
			</ul>
		</div>
		<!--说明state_box bof-->
		<div class="state_box">
			<h1>说明</h1>
			<p>1、选择回拨铃声、语音验证码欢迎音类型，每个应用只能上传一个欢迎语音，若重复上传则覆盖上一个语音。选择语音通知类型不限制。</p>
		</div>
		<!--说明state_box eof-->

		<!--搜索search_box bof-->
		<div class="search_box">
			<div class="search app_search">
				<s:form namespace="/app/ring" theme="simple"  action="query" method="post" name="queryForm" id="queryForm"  >
			      	<input type="text" name="text" placeholder="应用名称/ID" value="<s:property value="#parameters.text"/>" />
			      	<input type="submit" value="" class="search" />
			      </s:form>
			</div>
			<div class="search_link"><a  href="<%=path%>/app/ring/edit" class="voice">添加语音</a></div>
		</div>
		<!--搜索search_box eof-->
	    <!--表格列表 table_box bof-->
		<div class="table_box">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>语音文件ID</th>
						<th>应用名称</th>
						<th>铃声名称</th>
						<th>类型</th>
						<th>创建时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
	     <s:if test="page.list!=null&&page.list.size()>0">
		     <s:iterator value="page.list" var="r">
			      <tr>
			       <td>
			      <s:property value="#r.id" />
			      </td>
			      <td>
			      <s:property value="#r.app_name" />
			      </td>
			       <td>
			      <s:if test="#r.content_type==1">
				      <s:property value="#r.user_file_name" />
			      </s:if>
			      <s:else>
			      	 <s:property value="#r.content" />
			      </s:else>
			      </td>
			      <td>
			      <s:if test="#r.type==1">
			      	回拨铃声
			      </s:if>
			      <s:elseif test="#r.type==2">
			      	语音验证码铃声
			      </s:elseif>
			      </td>
			      <td>
			      <s:date name="#r.create_date"  format="yyyy-MM-dd HH:mm:ss" />
			      </td>
			      <td>
			      	<s:if test="#r.audit_status==1">
			      		待审核
			     	</s:if>
			     	<s:elseif test="#r.audit_status==2">
			     		审核通过
			     	</s:elseif>
			     	<s:elseif test="#r.audit_status==3">
			     		审核不通过
			     	</s:elseif>
			      </td>
			      <td>
				     	<a href="<%=path %>/app/ring/edit?id=<u:des3 value='${r.id}'/>">编辑</a>
				      	<s:if test="#r.content_type==1">
				      		<a href="javascript:void(0);" onclick="play_fun('<%=path%>/file/view?fileName=<s:property value="#r.fileFullPath" />');">播放</a>
				      	</s:if>
			      </td>
			      </tr>
		      </s:iterator>
	      </s:if>
	      <s:else>
	      	<tr>
	      	<td>暂无数据! &nbsp;&nbsp;<a href="<%=path%>/app/ring/edit" style="color:#4981bf">去上传语音</a></td>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      	</tr>
	      </s:else>
	      </tbody>
      </table>
      <u:page page="${page}" formId="queryForm"></u:page>
      <div style="display:none"><div id="jquery_jplayer"></div></div>
     </div>
    </div>
</div>
	<%@include file="/page/foot.jsp" %>
<!--主体部分content eof--> 
</body>
</html>
