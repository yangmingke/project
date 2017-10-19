<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<!-- <script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script> -->
<title>SR节点信息</title>


<script type="text/javascript">
	function addNodeInfo() {
		location.href = "${ctx}/node/add";
	}

	function updateNodeInfo(sr_id) {
		location.href = "${ctx}/node/add?sr_id=" + encodeURIComponent(sr_id);
	}

	function deleteNodeInfo(sr_id) {
		if (confirm('你确认要删除' + sr_id + '节点吗?')) {
			$.ajax({
				type : 'post',
				url : '${ctx}/node/deleteNodeInfo',
				data : {
					sr_id : sr_id
				},
				datatype : 'json',
				error : function(data) {
				}, //非必须
				success : function(data) {
					alert(data.msg);
					location.href = '${ctx}/node/view';
				}//非必须
			});
		}
	}

	//反选

	function invertSelectType() {
		//这里重写反选和全选方法，因为再次使用原先的会导致页面上的选项也会被选  
		var ids = $("input[name='stu']");
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked == true) {
				ids[i].checked = "";
			} else {
				ids[i].checked = "checked";
			}
		}
	}
	
	//批量删除节点信息
	function deleteBatch(){
			var obj=document.getElementsByName('stu'); //选择所有name="'test'"的对象，返回数组 
			//取到对象数组后，我们来循环检测它是不是被选中 
			var ids=''; 
			for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked) ids+=obj[i].value+','; //如果选中，将value添加到变量s中 
			}
			if(confirm("确定批量删除选中节点吗?")){
				$.post("${ctx}/node/deleteBatch",{ids:ids},function(data){
					window.location.href=window.location.href;
				})
			}
	}
	
</script>
</head>
<body menuId="10">
	<!--Action boxes-->
	<div class="container-fluid">
		<hr>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="fa fa-th"></i> </span>
						<h5></h5>
						<div class="search">
							<form method="post" id="nodeForm" action="${ctx}/node/view">
								<ul>
								    <li><a href="javascript:void(0)" class="link"  onclick="deleteBatch()"/>批量删除SR节点</a></li>
									<li><input type="text"
										value="<s:property value="#parameters.sr_id"/>" name="sr_id"
										placeholder="节点ID/名称" style="width:150px;" /></li>
									<li><u:select id="level" value="${param.level}"
											placeholder="SR所属层面" dictionaryType="sr_level" /></li>
									<li><u:select id="mnos" value="${param.mnos}"
											placeholder="节点所属运营商" dictionaryType="sr_mnos" /></li>
									<li><u:select id="pageRowCount"
											value="${page.pageRowCount }"
											data="[{value:'30',text:'分页条数:默认(30)'},{value:'50',text:'分页条数:50'},{value:'100',text:'分页条数:100'}]"
											placeholder="分页条数"></u:select></li>
									<li><input type="submit" value="查 询" /></li>
									<li><a href="javascript:void(0)" onClick="addNodeInfo()"
										class="link">配置SR节点信息</a></li>
								</ul>
							</form>
						</div>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped"> <thead>
						
						<tr><th><input type="checkbox"  onclick="invertSelectType()"></th> <th width="50px">序号</th> <th>SR节点ID</th> <th>SR节点名称</th>
						<th>SR节点所属层</th> <th>SR节点所属运营商</th> <th>SR节点所属区域</th> <th>SR节点所属大区</th>
						<th>操作</th> </tr> </thead> <tbody> <s:iterator value="page.list">
							<tr><td><input type="checkbox" name="stu" id="a" value="${sr_id}"><td>${rownum}</td> <td> <s:property value="sr_id" /></td>
							<td> <s:property value="sr_name" /></td> <td> <u:ucparams
								key="${level}" type="sr_level"></u:ucparams> </td> <td> <u:ucparams
								key="${mnos}" type="sr_mnos"></u:ucparams> </td> <td>${province}${city}(${ireaCode})</td>
							<td>${area}</td> <td> <a href="javascript:;"
								onClick="updateNodeInfo('<s:property value="sr_id"/>')">修改</a>&nbsp;&nbsp;
							<a href="javascript:;"
								onClick="deleteNodeInfo('<s:property value="sr_id"/>')">删除</a> </td>
							</tr>
						</s:iterator> </tbody> </table>
					</div>
					<u:page page="${page}" formId="nodeForm"></u:page>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){

		//复选框选中未选中样式
		$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});
		$('select').chosen();
	})
	</script>
</body>
</html>