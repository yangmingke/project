<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
	<title>SR节点间延时曲线图</title>
</head>
<body menuId="8">
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
					<form method="post" id="qualityForm" action="${ctx}/packet/delay">
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
						    <li>
						    	<u:date id="start_time" value="${data.start_time}" placeholder="开始时间" params="minDate:'%y-%M-{%d-45} 00:00:00', maxDate:'#F{$dp.$D(\\'end_time\\')||\\'%y-%M-%d %H:%m:%s\\'}', isShowClear:false" />
								<span>至</span>
            					<u:date id="end_time" value="${data.end_time}" placeholder="结束时间" params="minDate:'#F{$dp.$D(\\'start_time\\')||\\'%y-%M-{%d-45} 00:00:00\\'}', maxDate:'%y-%M-%d %H:%m:%s', isShowClear:false" />
							</li>
							<li><input type="submit" value="查 询" /></li>
						</ul>
				     </form>
				</div>
          </div>
          
          <!-- 曲线图 -->
          <div class="widget-content nopadding">
				<u:highstock highstock="${data.chart}" id="chart" onLoad="chartOnLoad" />
			</div>
					
           
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
	    $('select#sr_id').chosen();
 })
  
	$("#sr_id").change(function() {
	    $.post("${ctx}/link/dst_sr_id",{sr_id:$("#sr_id").val()},function(data){
	    	data=eval(data);
	    	$("#dst_sr_id").empty();
	    	for(var o in data){
	    		$("#dst_sr_id").append("<option value='"+data[o].nbrid+"'>"+data[o].dst_name+"</option>");
	    	}
	     })
		})
	function chartOnLoad(){
		var sr_id = $('#sr_id').val();
		var dst_sr_id = $('#dst_sr_id').val();
		if(sr_id && dst_sr_id){
			setInterval(function () {
				$.ajax({
					type : "post",
					url : "${ctx}/packet/realDelay",
					data : $('#qualityForm').serialize(),
					success : function(data) {
						if (data.chart != null) {
							chartFunction.redraw("chart", data.chart);
							//$("#end_time").val(data.end_time);
						}
					}
				});
			
			}, 5000);
		}
	 }
	</script>
</body>
</html>