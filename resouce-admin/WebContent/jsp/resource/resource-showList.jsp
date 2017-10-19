<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
    <script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
    
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 资源列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <select id="status" class="input" style="width:150px; line-height:17px;">
            <option value="">状态：所有</option>
            <option value="0" <c:if test="${page.parameter.status == 0}">selected="selected"</c:if>>初始状态</option>
            <option value="1" <c:if test="${page.parameter.status == 1}">selected="selected"</c:if>>审核中</option>
            <option value="2" <c:if test="${page.parameter.status == 2}">selected="selected"</c:if>>审核失败</option>
            <option value="3" <c:if test="${page.parameter.status == 3}">selected="selected"</c:if>>就绪状态</option>
            <option value="4" <c:if test="${page.parameter.status == 4}">selected="selected"</c:if>>暂停使用</option>
          </select>
        </li>
        <li>
	        <input type="text" placeholder="输入关键字搜索资源" id="keyword" class="input" style="width:250px; line-height:17px;display:inline-block" value="${page.parameter.keyword}"/>&nbsp;&nbsp;&nbsp;
	        <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" title="查询"> </a>
        </li>
         <li>
	        <a href="/resourceController/addResourcePage" class="button border-main icon-plus" title="添加资源节点">添加资源节点 </a>
        </li>
        <span class="pageCount">
          	每页显示
	          <select id="pageRowCount" onchange="jumpTo()">
	       		<option value="10" <c:if test="${page.pageRowCount == 10}">selected="selected"</c:if> >10</option>
	       		<option value="30" <c:if test="${page.pageRowCount == 30}">selected="selected"</c:if> >30</option>
	       		<option value="50" <c:if test="${page.pageRowCount == 50}">selected="selected"</c:if> >50</option>
	       		<option value="100" <c:if test="${page.pageRowCount == 100}">selected="selected"</c:if> >100</option>
		      </select>条
	      </span>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr class="text-c">
        <th >序号</th>
        <th >资源方ID</th>
        <th >资源方公司简称</th>
        <th >路由区域</th>
        <th >节点IP地址</th>
        <th >节点名称</th>
        <th >资源所属地区</th>
        <th >流量单价(分/GB)</th>
        <th >当前状态</th>
        <th >操作</th>
      </tr>
      <%int i = 1; %> 
      <c:forEach var="resourceList" items="${page.resultMap}"> 
      <tr>
        <td ><%=i++%></td>
        <td >${resourceList.net_sid}</td>
        <td >${resourceList.username}</td>
       	<td >${resourceList.routeArea}</td>
        <td >${resourceList.ip}</td>
        <td >${resourceList.name}</td>
        <td >${resourceList.country}  ${resourceList.city}</td>
        <td >${resourceList.price}</td>
       <%--  <td ><fmt:formatDate value="${resourceList.audit_date}" pattern="yyyy-MM-dd" /></td> --%>
        <td >
	        <c:if test="${resourceList.status == 0}">
	        	<c:out value="初始状态"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 1}">
	        	<c:out value="审核中..."></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 2}">
	        	<c:out value="审核失败"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 3}">
	        	<c:out value="就绪状态"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 4}">
	        	<c:out value="暂停使用"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 5}">
	        	<c:out value="离线状态"></c:out>
	        </c:if> 
	        <c:if test="${resourceList.status == 5}">
	        	<c:out value="在线状态"></c:out>
	        </c:if> 
        </td>
        <td>
        	<c:if test="${resourceList.status == 2 || resourceList.status == 4}">
	        	<div class="button-group"> 
	        		<a class="button border-red" href="javascript:void(0)" onclick="del(this,${resourceList.rnode_id},${resourceList.status})" title="删除"><span class="icon-trash-o"></span></a> 
	        	</div>
        	</c:if>
        	<div class="button-group"> 
        		<a class="button border-green" href="javascript:void(0)" onclick="showResource(${resourceList.rnode_id})" title="编辑信息"><span class="icon-list-alt" ></span></a>
        	</div>
        	<div class="button-group"> 
        		<a class="button border-blue" href="javascript:void(0)" onclick="showStatusToPic('${resourceList.ip}')"  title="查看实时状态图"><span class="icon-inbox"></span> </a> 
        	</div>
        	<%-- <div class="button-group"> 
        		<a class="button border-blue" href="javascript:void(0)" onclick="showNeighbor('${resourceList.rnode_id}')"  title="配置邻居节点"><span class="icon-group"></span> </a> 
        	</div> --%>
        </td>   
      </tr>
     </c:forEach>
      <tr>
        <td colspan="9">
        	<div class="pagelist">
        		<c:if test="${page.currentPage != 1}">
        	 		<a onclick="jumpToPage(${page.currentPage-1})">上一页</a>
        	 	</c:if>
        	 	<c:forEach items="${page.pageList}" var="item">
				<c:if test="${page.currentPage != item}">
					<a onclick="jumpToPage(${item})">${item}</a>
				</c:if>
				<c:if test="${page.currentPage == item}">
					<a class="current">${item}</a>
				</c:if>
				</c:forEach>
				<c:if test="${page.currentPage != page.totalPage}">
	        	 	<a onclick="jumpToPage(${page.currentPage+1})">下一页</a>
				</c:if>
         		 <label for="sitename">共&nbsp; ${page.totalPage}&nbsp; 页</label>
        	 </div>
         </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">
function showStatusToPic(ip){
	//$('body').load('/jsp/resource/resource-showResourceStatusToPic.jsp?ip=' + ip);
	window.location.href="/jsp/resource/resource-showResourceStatusToPic.jsp?ip=" + ip;
}


function del(obj,id,status){
	//状态为正在工作,暂停(并发量为0)是可以删除
	if(status != 2 && status != 4){
		window.wxc.xcConfirm("节点在非“暂停状态或者审核失败状态”下，不能删除", window.wxc.xcConfirm.typeEnum.info);
	}else if(status == 4){
		$.post("/resourceController/queryResourceById.action",{"rtppId":id},function(data){
			//var json = eval("(" +data+ ")");
			if(data == 1){
				//节点处于暂停状态  但仍在使用
				window.wxc.xcConfirm("该节点正在使用,不能删除...", window.wxc.xcConfirm.typeEnum.info);
			}else{
				delResource(obj,id);
			}
		})
	}else{
		delResource(obj,id);
	}
}

function delResource(obj,id){
	window.wxc.xcConfirm("您确定要删除吗?", window.wxc.xcConfirm.typeEnum.confirm,{
		onOk:function(v){
			$.post("/resourceController/delResource",{"rtppSid":id},function(data){
				//节点处于非使用状态
				if(data == 1){
					$(obj).parents("tr").remove();
				}else{
					window.wxc.xcConfirm("Sorry.服务器出错", window.wxc.xcConfirm.typeEnum.info);
				}
			})
		}
	});
}


function showResource(id){
	$('body').load('/resourceController/queryResourceById?rtppId=' + id);
	//$('body').load('/jsp/resource/resource-showResource.jsp?rtppId=' + id);
}

function showNeighbor(id){
	window.location.href="/resourceController/showNeighbor?rtppId=" + id;
}


function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var netSid = $('#netSid').val();
	var status = $('#status').val();
	var keyword = $('#keyword').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/resourceController/queryResourceFenYe',{"page":page,"status":status,"netSid":netSid,"keyword":keyword,"pageRowCount":pageRowCount});
}

//页码跳转
function jumpToPage(page){
	jumpTo(page);
}

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

$("#delSelect").click(function(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		window.wxc.xcConfirm("您确认要删除选中的内容吗？", window.wxc.xcConfirm.typeEnum.confirm,{
			onOk:function(v){
				// 获取所有选中的checked框  
				var option = $('input[name="id[]"]:checked');  
				var checkedId = "";  
				var boo=true;  
				//拼接除全选框外,所有选中的id,  
				for (var i = 0, len = option.length; i < len; i++) {  
				    if (boo) {  
				        if (option[i].batchDel=='allSelect') {  
				            boo=true;  
				        }else {  
				            boo=false;  
				            checkedId += option[i].value;  
				        }  
				    }else{  
				        checkedId += ","+option[i].value;  
				    }  
				}
				$.ajax({  
			        type: "post",  
			        url:'/resourceController/batchDelResource.action',  
			        data:{  
			        "checkedRTPPIP":checkedId
			        },  
			        dataType:"json",  
			        success : function(data) { 
					    var json = eval("("+data+")");
					    if(json == 0){
					    	window.wxc.xcConfirm("删除失败...", window.wxc.xcConfirm.typeEnum.info);
					    }
					    if(json == 1){
					    	window.wxc.xcConfirm("删除成功...", window.wxc.xcConfirm.typeEnum.info);
					    }
				    },
				    error:function(code){   
				        window.wxc.xcConfirm("Sorry.服务器出错", window.wxc.xcConfirm.typeEnum.info);
				    }   
			     });
			}
		});
	}
	else{
		window.wxc.xcConfirm("请选择您要删除的内容!", window.wxc.xcConfirm.typeEnum.info);
		return false;
	}
})

</script>
</body></html>