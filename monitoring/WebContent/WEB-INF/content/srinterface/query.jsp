<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>SR节点接口信息</title>
</head>
<body menuId="14">
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
					<form method="post" id="nodeForm" action="${ctx}/interface/query">
						<ul> 
						    <li><u:select id="sr_id" value="${param.sr_id}" sqlId="sr_monitor" placeholder="监控点" style="width:180px" ></u:select></li>
						    <li><input type="text" value="${param.ifname }" name="ifname" placeholder="本地接口IP/名称" style="width:150px;" /></li>
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
						<th>SR节点名称</th>
						<th>SR节点ID</th>
						<th>本地接口IP</th>
						<th>本地接口名称</th>
						<th>上报时间</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="page.list">
						<tr>
							<td>${rownum}</td>
							<td>${sr_name }</td>
							<td>${sr_id }</td>
							<td>${ifip }</td>
							<td>${ifname }</td>
					        <td>${time }</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
          </div>
           <u:page page="${page }" formId="nodeForm"></u:page>
        </div>
      </div>
    </div>
  </div>
</body>
</html>