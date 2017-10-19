<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/1.0.8/iconfont.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/page.css" />
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>资源管理</title>
<style type="text/css">
	.view_token_box { 
		width: 300px;
		height: 200px;
	}
	.float_box { 
		border: 6px #6b6b6b solid; 
		border-radius: 6px;
		-moz-border-radius:6px;
		-webkit-border-radius:6px;
		-o-borer-radius:6px; 
		z-index: 111; 
		position: absolute; 
		background: #fff; 
		display: none;
	}
	.float_tit { 
		/*height: 40px; */
		/*line-height: 40px;*/
		background: #f3f4f5; 
		/*font-weight: bold;*/
	}
	.background_box { 
		background: #000; 
		opacity: 0.4; 
		filter:alpha(opacity=40); 
		height: 100%; 
		width: 100%; 
		position: fixed; 
		top: 0px; 
		left: 0px; 
		z-index: 11; 
		display: none;
	}
	.float_btn { 
		/*border-top: 1px #e8e8e8 solid; */
		padding-bottom: 0px; 
		text-align: center; 
		width: 100%;
	}
	.side { 
		width: 280px; 
		border-top: 1px #ccc solid;
		border-right: 1px #ccc solid; 
		float: left; 
		display: inline;
	}
	input[type='button'],input[type='submit'] { 
		font-family: 微软雅黑; cursor: pointer;
	}
	.cancel_btn { 
		border: 1px #ccc solid; 
		background: #fff; 
		width: 60px; 
		height: 30px; 
		line-height: 16px; 
		text-align: center; 
		color: #383838; 
		cursor: pointer;
	}
	.cancel_btn:hover { 
		background: #eeeeee;
	}
	.confirm_btn { 
		border: 1px #76b343 solid;
	 	background: /* #76b343 */rgb(92, 192, 237); 
	 	width: 60px; height: 30px; line-height: 16px; 
	 	text-align: center; 
	 	color: #fff; 
	 	cursor: pointer;
	 }
	.confirm_btn:hover { 
		background: /* #8bc55a */rgb(92, 192, 237); 
		border-color: #8bc55a;
	}
	.grey_btn,.grey_btn:hover { 
		border: 1px #ccc solid;
	 	background: #eeeeee; 
	 	width: 60px; 
	 	height: 30px; 
	 	line-height: 16px; 
	 	text-align: center; 
	 	color: #383838; 
	 	cursor: default;
	 }
	 .float_ctn{
	 	width: 300px;
	 	height: 150px;
	 }
	.float_ctn { 
		margin: 20px 20px 100px 10px;
	}
	.float_field { 
		margin-bottom: 60px;
		padding-left: 25%;
	}
</style>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 资源节点列表<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
<div class="background_box" style="display:none;" >&nbsp;</div>
<div class="float_box view_token_box" id="view_token_box" style="display:none; left: 651.5px; top: 356px;">
	      <div class="float_tit">
	        <h5>请选择节点状态</h5>
	      </div>
	      <div class="float_ctn">
          <form  action="##" method="post" name="statusForm" id="status_form">
              <div class="float_field"><input type="text" style="display: none;" id="ip"/>
             	<span id="init" style="display: none;"><input type="radio" name="status" value="0" id="init1"/>&nbsp;取消审核</span>
				<span id="Submitted" style="display: none;"><input type="radio" name="status" value="1" id="Submitted1"/>&nbsp;提交审核</span>
				<span id="reSubmitted" style="display: none;"><input type="radio" name="status" value="1" id="reSubmitted1"/>&nbsp;重新提交审核</span>
				<span id="stop" style="display: none;"><input type="radio" name="status" value="4" id="stop1"/>&nbsp;暂停</span>&nbsp;&nbsp;
				<span id="working" style="display: none;"><input type="radio" name="status" value="3" id="working1"/>&nbsp;正常工作</span>&nbsp;&nbsp;
		      </div>
		      <div class="float_btn">
		        <input type="button" class="cancel_btn" value="取消">&nbsp;&nbsp;
		        <input type="button" id="submit_verifyMobileForToken" class="confirm_btn" value="确定">
		        <input type="hidden" id="vmovecode"  />
             	<input type="hidden" id="movecode"  />
		      </div>             
          </form>
	      </div>
</div>
<form action="${ctx}/resourceController/queryAllResourceFenYe.action" method="post">
	<div class="text-c"> 
  	创建时间：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;" value="${dateMin}" name="dateMin">
    -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;" value="${dateMax}" name="dateMax"> 
    <input type="text" class="input-text" style="width:250px" placeholder="输入节点名称或IP" id="" name="ipOrName" value="${ipOrName }"><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜资源</button>

  </div><%-- ${ctx}/jspPage/resource/resource-add.jsp --%>
</form>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <!-- <span class="l"><a href="javascript:;" onclick="resourceBatchDel()" class="btn btn-danger radius"><i class="icon-trash"></i> 批量删除</a> -->
    <a href="javascript:;" onclick="resource_add('600','','添加资源','${ctx}/cityController/queryAllCountry.action')" class="btn btn-primary radius"><i class="icon-plus"></i> 添加资源</a></span>
    <span class="r">共有数据：<strong>${resourceCount}</strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 3%"></th>
        <th style="width: 10%">节点名称</th>
        <th style="width: 10%">节点IP地址</th>
        <th style="width: 10%">节点所属地区</th>
        <th style="width: 7%">流量单价(分/GB)</th>
        <th style="width: 10%">带宽上限(MB)</th>
        <th style="width: 10%">创建时间</th>
        <th style="width: 10%">审核时间</th>
        <th style="width: 10%">当前状态</th>
        <th style="width: 10%">操作</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach var="resourceList" items="${resourceList}">
      <tr class="text-c">
        <td style="width: 3%"><%= i++ %></td>
        <td style="width: 10%">${resourceList.name}</td>
        <td style="width: 10%">${resourceList.ip}</td>
        <td style="width: 10%">${resourceList.country}  ${resourceList.city}</td>
        <td style="width: 7%">${resourceList.price}</td>
        <td style="width: 10%">${resourceList.bandwidth_limit}</td>
        <td style="width: 10%"><fmt:formatDate value="${resourceList.create_date}" pattern="yyyy-MM-dd" /></td>
        <td style="width: 10%"><fmt:formatDate value="${resourceList.audit_date}" pattern="yyyy-MM-dd" /></td>
        <c:if test="${resourceList.status == 0}">
        	<td class="user-status" style="width: 10%"><span class="label label-success">初始状态</span></td>
        </c:if>
        <c:if test="${resourceList.status == 1}">
        	<td class="user-status" style="width: 10%"><span class="label label-warning">审核中</span></td>
        </c:if>
        <c:if test="${resourceList.status == 2}">
        	<td class="user-status" style="width: 10%"><span class="label label-secondary">审核失败</span></td>
        </c:if>
        <c:if test="${resourceList.status == 3}">
        	<td class="user-status" style="width: 10%"><span class="label label-danger">就绪状态</span></td>
        </c:if>
        <c:if test="${resourceList.status == 4}">
        	<td class="user-status" style="width: 10%"><span class="label label-info">暂停使用</span></td>
        </c:if>
        <c:if test="${resourceList.status == 5}">
        	<td class="user-status" style="width: 10%"><span class="label label-inverse">离线状态</span></td>
        </c:if>
        <c:if test="${resourceList.status == 6}">
        	<td class="user-status" style="width: 10%"><span class="label label-inverse">在线状态</span></td>
        </c:if>
        <%-- <c:if test="${resourceList.status == 0}">
        	<td class="user-status" style="width: 10%"><span class="label label-success">正常工作</span></td>
        </c:if>
        <c:if test="${resourceList.status == 1}">
        	<td class="user-status" style="width: 10%"><span class="label label-warning">审核中...</span></td>
        </c:if>
        <c:if test="${resourceList.status == 2}">
        	<td class="user-status" style="width: 10%"><span class="label label-secondary">不通过</span></td>
        </c:if>
        <c:if test="${resourceList.status == 3}">
        	<td class="user-status" style="width: 10%"><span class="label label-danger">故障</span></td>
        </c:if>
        <c:if test="${resourceList.status == 4}">
        	<td class="user-status" style="width: 10%"><span class="label label-info">暂停</span></td>
        </c:if>
        <c:if test="${resourceList.status == 5}">
        	<td class="user-status" style="width: 10%"><span class="label label-inverse">下线</span></td>
        </c:if> --%>
        <td class="f-14 user-manage" style="width: 10%">
        	<a style="text-decoration:none" onclick="editNextStatus('${resourceList.rnode_id}','${resourceList.status}','${resourceList.ip}')" id="editNextStatus" title="修改节点状态" href="javascript:;"  class="ml-5" ><i class="Hui-iconfont">&#xe61d;</i></a> 
        	<%-- &nbsp;&nbsp;<a style="text-decoration:none" onclick="resource_show('${resourceList.rnode_id}','650','','查看资源信息','${ctx}/jspPage/resource/resource-show.jsp','${resourceList.status}','${resourceList.ip}','${resourceList.countryid}','${resourceList.provinceid}')"title="查看资源信息" href="javascript:;"  class="ml-5" ><i class="Hui-iconfont">&#xe695;</i></a> --%> 
	        &nbsp;&nbsp;<a style="text-decoration:none" onclick="resource_edit1('${resourceList.rnode_id}','650','','管理资源信息','${ctx}/jspPage/resource/resource-edit.jsp','${resourceList.status}','${resourceList.ip}','${resourceList.countryid}','${resourceList.provinceid}')"title="管理资源信息" href="javascript:;"  class="ml-5" ><i class="Hui-iconfont">&#xe60c;</i></a> 
	        &nbsp;&nbsp;<a style="text-decoration:none" onclick="resource_del(this,'${resourceList.rnode_id}','${resourceList.status}','${resourceList.ip}')" title="删除资源" href="javascript:;"  class="ml-5" ><i class="Hui-iconfont">&#xe6e2;</i></a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div id="pageControl" style="float: right">
		<!-- 上一页 按钮 -->
		<c:if test="${page.currentPage != 1}">
			<a href="/resourceController/queryAllResourceFenYe?page=${page.currentPage-1}"><input type="button" name="lastPage" value="上一页" /></a>
		</c:if>
		<!-- 页数列表 -->
		<c:forEach items="${page.pageList}" var="item">
			<c:if test="${page.currentPage != item}">
			<a href="/resourceController/queryAllResourceFenYe?page=${item}" >${item}</a>
			</c:if>
			<c:if test="${page.currentPage == item}">
				<a class="currentPage">${item}</a>
			</c:if>
		</c:forEach>
		<!-- 下一页 按钮 -->
		<c:if test="${page.currentPage != page.totalPage}">
			<a href="/resourceController/queryAllResourceFenYe?page=${page.currentPage + 1}"><input type="button" name="nextPage" value="下一页" /></a>&nbsp;
		</c:if>
		<!-- 直接跳转 -->
		共&nbsp;${page.totalPage}&nbsp;页  第<input type="text" id="jumpTo" />页 <input type="button" value="跳转" onclick="jumpToResource(${page.totalPage})" />
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagenav.cn.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
/* window.onload = (function(){
    // optional set
    pageNav.pre="&lt;上一页";
    pageNav.next="下一页&gt;";
    // p,当前页码,pn,总页面
    pageNav.fn = function(p,pn){$("#pageinfo").text("当前页:"+p+" 总页: "+pn);};
    //重写分页状态,跳到第三页,总页33页
    pageNav.go(1,5);
});
$('.table-sort').dataTable({
	//"lengthMenu":false,//显示数量选择 
	"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": false,//数量信息
	"aaSorting": [[ 1, "asc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  {"bVisible": true, "aTargets": [ 5 ]}, //控制列的隐藏显示
	  {"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
}); */



//弹层关闭
$(".float_box .cancel_btn").click(function(){
	$(".background_box").fadeOut();
	$(".float_box").fadeOut();
	$("body").removeClass("float_body");
});

/*----------显示节点的下一个状态-----------*/
function editNextStatus(rtppSid,status,ip){
	if(status == 0){
		$('.background_box').show();
		$('#view_token_box').show();
		$('#init').hide();
		$('#Submitted').show();
		$('#reSubmitted').hide();
		$('#stop').hide();
		$('#working').hide();
		$('#Submitted1').attr('checked', 'true');
	}else if(status == 1){
		$('.background_box').show();
		$('#view_token_box').show();
		$('#init').show();
		$('#Submitted').hide();
		$('#reSubmitted').hide();
		$('#stop').hide();
		$('#working').hide();
		$('#init1').attr('checked', 'true');
	}else if(status == 2){
		$('.background_box').show();
		$('#view_token_box').show();
		$('#init').hide();
		$('#Submitted').hide();
		$('#reSubmitted').show();
		$('#stop').hide();
		$('#working').hide();
		$('#reSubmitted1').attr('checked', 'true');
	}else if(status == 3){
		$('.background_box').show();
		$('#view_token_box').show();
		$('#init').hide();
		$('#Submitted').hide();
		$('#reSubmitted').hide();
		$('#stop').show();
		$('#working').hide();
		$('#stop1').attr('checked', 'true');
	}else if(status == 4){
		/* $('.background_box').show();
		$('#view_token_box').show();
		$('#working').show();
		$('#working1').attr('checked', 'true');
		//查询当前节点的最新并发量   如果并发量为0 才可以选择下线  否则不能做下线操作
		$.post("/resourceStatusController/queryStatusByIpNewData.action",{"ip":ip},function(data1){
			var data = eval(data1);
			if(data ==1){
				$('#offLine').show();
			}else{
				$('#offLine').hide();
			}
		});
		$('#Submitted').hide();
		$('#stop').hide(); */
		
		$('.background_box').show();
		$('#view_token_box').show();
		$('#init').hide();
		$('#Submitted').hide();
		$('#reSubmitted').hide();
		$('#stop').hide();
		$('#working').show();
		$('#working1').attr('checked', 'true');
	}else{
		$('.background_box').show();
		$('#view_token_box').show();
		$('#init').hide();
		$('#Submitted').hide();
		$('#reSubmitted').hide();
		$('#stop').show();
		$('#working').hide();
		$('#stop1').attr('checked', 'true');
	}
	$('#ip').val(rtppSid);
}
/* -----------  修改节点状态-----------*/
$('#submit_verifyMobileForToken').click(function(){
	var rtppSid = $('#ip').val();
	var newStatus = $("input[name='status']:checked").val();
	$.post("/resourceController/editStatus.action",{"rtppSid":rtppSid,"status":newStatus},function(data1){
		var data = eval(data1);
		if(data == 1){
			layer.msg('修改成功!',2,1);
			location.replace(location.href);
		}else if(data == 0){
			layer.msg('操作失败!',2,1);
		}else if(data == 2){
			layer.msg('节点正在使用中，暂停失败!',2,1);
		}
	})
});
</script>
</body>
</html>