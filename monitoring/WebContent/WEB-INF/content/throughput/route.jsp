<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script> -->
<title>路由监控</title>



</head>
<body menuId="10">
	<!--Action boxes-->
	<div class="container-fluid">
		<hr>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="fa fa-th"></i>
						</span>
						<h5></h5>
						<div class="search">
							<form method="post" id="nodeForm"
								action="${ctx}/throughput/route">
								<input type="hidden" id="hiddenSrIp" name="hiddenSrIp"
									value="${param.sr_ip}" /> <input type="hidden" id="hiddenDstIp"
									name="hiddenDstIp" value="${param.dst_ip}" />
								<ul>
									<li>源节点:<u:select id="sr_id" value="${param.sr_id}"
											sqlId="sr_monitor" placeholder="源节点" style="width:180px"></u:select></li>
									<li>源节点ID:<input id="sr_ip" name="sr_ip_id" value="${param.sr_id}"/>
									</li>
									<li>目的节点:<u:select id="dst_sr_id"
											value="${param.dst_sr_id}" sqlId="sr_monitor"
											placeholder="目的节点" style="width:180px"></u:select></li>
									  <li>目的节点IP:<select id="dst_ip" name="dst_ip">
                                                                        </select></li>
                                                                        <li><input type="submit" value="查 询" /></li>
							
								</ul>
							</form>
						</div>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped">
							<thead>

								<tr>
									<th>源节点名称</th>
									<th>源节点ID</th>
									<th>中转路径</th>
									<th>目的节点名称</th>
									<th>目的节点IP</th>
									<th>路由矢量值</th>
								</tr>
							</thead>
							<tbody>
								   <c:forEach items="${data.list}" var="list" >
									<tr>
										<td>${param.sr_id_name}</td>
										<td>${param.sr_id}</td>
										<td>${list.transferRoad}</td>
										<td>${param.dst_sr_id_name}</td>
										<td>${param.dst_ip}</td>
										<td>${list.metric}</td>
									</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$.post("${ctx}/throughput/srIp", {
				sr_id : $("#sr_id").val()
			}, function(data) {
				data = eval(data);
				$("#sr_ip").empty();
				for ( var o in data) {
					if ($("#hiddenSrIp").val() == data[o].srip) {
						$("#sr_ip").append(
								"<option value='"+data[o].srip+"' selected='selected'>"
										+ data[o].srip + "</option>");
					} else {
						$("#sr_ip").append(
								"<option value='"+data[o].srip+"'>"
										+ data[o].srip + "</option>");
					}
				}
			})
			$.post("${ctx}/throughput/dstIp", {
				dst_id : $("#dst_sr_id").val()
			}, function(data) {
				data = eval(data);
				$("#dst_ip").empty();
				for ( var o in data) {
					if ($("#hiddenDstIp").val() == data[o].dst_ip) {
						$("#dst_ip").append(
								"<option value='"+data[o].dst_ip+"' selected='selected'>"
										+ data[o].dst_ip + "</option>");
					} else {
						$("#dst_ip").append(
								"<option value='"+data[o].dst_ip+"'>"
										+ data[o].dst_ip + "</option>");
					}
				}
			})
			//根据源节点来获取源节点ip
			$("#sr_id").change(function(){
					$("#sr_ip").val($("#sr_id").val());
					
					})
		

		
		
		//根据目的节点来获取目的节点的ip
                        $("#dst_sr_id").change(
                                        function() {
                                                $.post("${ctx}/throughput/dstIp", {
                                                        dst_id : $("#dst_sr_id").val()
                                                },
                                                                function(data) {
                                                                        data = eval(data);
                                                                        $("#dst_ip").empty();
                                                                        for ( var o in data) {
                                                                                $("#dst_ip").append(
                                                                                                "<option value='"+data[o].dst_ip+"'>"
                                                                                                                + data[o].dst_ip
                                                                                                                + "</option>");
                                                                        }

                                                                })
                                        })

                        $('select#sr_id').chosen();
                        $('select#dst_sr_id').chosen();
		})
	</script>
	</div>
	
</body>
</html>