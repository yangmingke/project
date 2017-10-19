<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SR节点间质量统计</title>
</head>
<body menuId="4">
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
							<form method="post" id="qualityForm" action="${ctx}/quality/view">
							<input type="hidden" id="hiddenId" value="${param.dst_sr_id}"/>
								<ul>
									<li>监控点<select id="sr_id" name="sr_id">
											<c:forEach items="${data.map}" var="var">
												<c:if test="${var.text!=null && param.sr_id==var.value}">
													<option value="${var.value}" selected="selected">${var.text}</option>
												</c:if>
												<c:if test="${param.sr_id!=var.value}">
													<option value="${var.value}">${var.text}</option>
												</c:if>
											</c:forEach>
									</select></li>
									<li>监控目标<select id="dst_sr_id" name="dst_sr_id">									
									</select></li>
									<li><u:date id="start_time" value="${data.start_time}"
											placeholder="开始时间"
											params="minDate:'%y-%M-{%d-45} 00:00:00', maxDate:'#F{$dp.$D(\\'end_time\\')||\\'%y-%M-%d %H:%m:%s\\'}', isShowClear:false" />
										<span>至</span> <u:date id="end_time" value="${data.end_time}"
											placeholder="结束时间"
											params="minDate:'#F{$dp.$D(\\'start_time\\')||\\'%y-%M-{%d-45} 00:00:00\\'}', maxDate:'%y-%M-%d %H:%m:%s', isShowClear:false" />
									</li>
									<li><u:select id="pageRowCount"
											value="${page.pageRowCount }"
											data="[{value:'30',text:'分页条数:默认(30)'},{value:'50',text:'分页条数:50'},{value:'100',text:'分页条数:100'}]"
											placeholder="分页条数" style="width:155px"></u:select></li>
									<li><input type="submit" value="查 询" /></li>
								</ul>
							</form>
						</div>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
								   <th>监控点名称</th>
								    <th>监控点接口名称</th>
									<th>监控点接口IP</th>
									<th>目标点名称</th>
									<th>目标接口名称</th>
									<th>目标点接口IP</th>
									<th>标本数</th>
									<th>ping包个数</th>
									<th>丢包个数</th>
									<th>平均延时</th>
									<th>丢包率</th>
									<th>链路矢量值</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="page.list">
									<tr>
									    <td>${sr_name}</td>
									    <td>${ifname}</td>
										<td>${ifip}</td>
										<th>${sr_name2}</th>
										<td>${ifname2 }</td>
										<td>${dst_ip }</td>
										<td>${total }</td>
										<td>${ping_num }</td>
										<td>${lost_num }</td>
										<td><fmt:formatNumber type="number"
												value=" ${average_delay}" maxFractionDigits="2" />ms</td>
										<td><fmt:formatNumber type="number" value=" ${lost_rate}"
												maxFractionDigits="2" />%</td>
												<td>${metric}</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<u:page page="${page }" formId="qualityForm"></u:page>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	  $(function(){
		   $.post("${ctx}/link/dst_sr_id",{sr_id:$("#sr_id").val()},function(data){
		    	data=eval(data);
		    	$("#dst_sr_id").empty();
		    	for(var o in data){
		    		if($("#hiddenId").val()==data[o].nbrid){
		    			$("#dst_sr_id").append("<option value='"+data[o].nbrid+"' selected='selected'>"+data[o].dst_name+"</option>");
		    		}else{
		    			$("#dst_sr_id").append("<option value='"+data[o].nbrid+"'>"+data[o].dst_name+"</option>");
		    		}
		    	}
		    })

	  })
	   $('select#sr_id').chosen();
		$("#sr_id").change(function() {
		    $.post("${ctx}/link/dst_sr_id",{sr_id:$("#sr_id").val()},function(data){
		    	data=eval(data);
		    	$("#dst_sr_id").empty();
		    	for(var o in data){
		    		$("#dst_sr_id").append("<option value='"+data[o].nbrid+"'>"+data[o].dst_name+"</option>");
		    	}
		    	
		     })
			})
	</script>
</body>
</html>