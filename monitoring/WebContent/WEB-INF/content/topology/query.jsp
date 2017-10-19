<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>SR节点邻居信息</title>
</head>
<body menuId="10">
<!--Action boxes-->
  <div class="container-fluid">
    <hr>
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
         	 <span class="icon">
         		 <i class="fa fa-th"></i>
         	 </span>
			 <h5></h5>
				<div class="search">
					<form method="post" id="nodeForm" action="${ctx}/neighbors/query">
						<ul> 
						    <li><u:select id="sr_id" value="${param.sr_id}" sqlId="sr_monitor" placeholder="监控点" style="width:180px" ></u:select></li>
						    <li><u:select id="pageRowCount" value="${page.pageRowCount }" data="[{value:'30',text:'分页条数:默认(30)'},{value:'50',text:'分页条数:50'},{value:'100',text:'分页条数:100'}]" placeholder="分页条数" ></u:select></li>
							<li><input type="submit" value="查 询" /></li>
						</ul>
				     </form>
				</div>
          </div>
          <div class="widget-content nopadding">
          	<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th width="50px">序号</th>
						<th>源SR节点名称</th>
						<th>源SR节点ID</th>
						<th>源接口名称</th>
						<th>源接口IP</th>
						<th>目的SR节点名称</th>
						<th>目的SR节点ID</th>
						<th>目的接口名称</th>
						<th>目的接口IP</th>
						<th>上报时间</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="page.list">
						<tr>
							<td>${rownum}</td>
							<td>${sr_name }</td>
							<td>${sr_id }</td>
							<td>${ifname}</td>
							<td>${ifip }</td>
							<td>${dst_name }</td>
					        <td>${nbrid }</td>
							<td>${ifname2 }</td>
					        <td>${nbrip }</td>
					        <td>${time }</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
          </div>
           <u:page page="${page}" formId="nodeForm"></u:page>
        </div>
      </div>
    </div>
  </div>
  <script>
  $(function(){
	  $('select#sr_id').chosen();
	  
	  //右侧iframe高度控制
	  var h = $(".widget-content table").height();
	  $(window.parent.document).find("#mainFrame").height(h+100);
  })
  </script>
</body>
</html>