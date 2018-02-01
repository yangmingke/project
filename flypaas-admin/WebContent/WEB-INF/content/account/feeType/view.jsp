<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<html>
<head>
	<title>计费项目</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/feeType/feeTypeConfig" >
          <ul>
            <li>
           		<a href="javascript:;" onclick="add('${super_id}')">添加计费项目</a>
            </li>
            <li>
           		<a href="javascript:;" onclick="back()">返回</a>
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table id="tb" class="noEdge">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>计费编号</th>
              <th>类型名称</th>
              <th>计费价格(分)</th>
              <th>计费类型</th>
              <th>创建时间</th>
              <th>修改时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr id="tr_${rownum}">
	            	<td>${rownum}</td>
	            	<td>${fee_type_id}</td>
	            	<td>${fee_type_name}</td>
	            	<td>${fee_rate}</td>
	            	<td><u:ucparams key="${super_id}" type="fee_type"/></td>
	            	<td>${create_time}</td>
	            	<td>${update_time}</td>
	            	<td>
						<a href="javascript:;" onclick="show('${rownum}','edit')">编辑</a>
				   	 |  <a href="javascript:;" onclick="del('${fee_type_id}','${fee_type_name}','${fn:length(page.list)}')">删除</a>
					</td>
	            </tr>
	            <tr id="tr_${rownum}_edit" style="display: none;" class="unhover">
	            	<td >${rownum}</td>
	            	<td ><input id="fee_type_id_${fee_type_id}" value='${fee_type_id}' onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
	            	<td ><input id="fee_type_name_${fee_type_id}" value='${fee_type_name}'></td>
	            	<td ><input id="fee_rate_${fee_type_id}" value='${fee_rate}' onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
	            	<td ><u:ucparams key="${super_id}" type="fee_type"/></td>
	            	<td >${create_time}</td>
	            	<td >${update_time}</td>
					<td>
						<a href="javascript:;" onclick="update_commit('${super_id}','${fee_type_id}')">确定</a>
					 |  <a href="javascript:;" onclick="show('${rownum}')">取消</a>
					</td>
	            </tr>
	        </s:iterator>
	        	<tr id="tr_add" style="display: none;" class="unhover">
	            	<td tit="v_rownum">-</td>
	            	<td tit="v_fee_type_id"><input id="fee_type_id" value='' onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
	            	<td tit="v_fee_type_name"><input id="fee_type_name" value=''></td>
	            	<td tit="v_fee_rate"><input id="fee_rate" value='' onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
	            	<td tit="v_super_id"><u:ucparams key="${page.list[0].super_id}" type="fee_type"/></td>
	            	<td tit="v_create_time">-</td>
	            	<td tit="v_update_time">-</td>
					<td>
						<a href="javascript:;" onclick="add_commit('${page.list[0].super_id}')">确定</a>
					 |  <a href="javascript:;" onclick="add('cancel')">取消</a>
					</td>
	            </tr>
          </tbody>
        </table>
		<u:page page="${page}" formId="mainForm" />
      </div>

<script type="text/javascript">
	function show(rownum,operate){
		if(operate =='edit'){
			$('#tr_'+rownum+'_edit').show();
			$('#tr_'+rownum).hide();
		}else{
			$('#tr_'+rownum+'_edit').hide();
			$('#tr_'+rownum).show();
		}
	}
	function add(operate){
		if(operate =='cancel'){
			$('#tr_add').hide();
		}else{
			$('#tr_add').show();
		}
	}
	function add_commit(super_id){
		if(super_id == "" || super_id == undefined){
			alert("系统发生错误，请联系管理员");
			return false;
		}
		var fee_type_id = $('#fee_type_id').val();
		var fee_type_name = $('#fee_type_name').val().trim;
		var fee_rate = $('#fee_rate').val();
		if(fee_type_id == "" || fee_type_name == "" || fee_rate == ""){
			alert("请完善计费项目内容");
			return false;
		}
		$.post("${ctx}/feeType/addFeeTypeItem",{feeTypeId:fee_type_id,superId:super_id,feeRate:fee_rate,feeTypeName:fee_type_name},function(data){
			if(data.result =="success"){
				window.location.reload();
			}else{
				alert('计费项目添加失败');
			}
		});
	}
	function update_commit(super_id,pre_fee_type_id){
		if(super_id == "" || super_id == undefined){
			alert("系统发生错误，请联系管理员");
			return false;
		}
		var fee_type_id = $('#fee_type_id_'+pre_fee_type_id).val();
		var fee_type_name = $('#fee_type_name_'+pre_fee_type_id).val().trim();
		var fee_rate = $('#fee_rate_'+pre_fee_type_id).val();
		if(fee_type_id == "" || fee_type_name == "" || fee_rate == ""){
			alert("请完善计费项目内容");
			return false;
		}
		$.post("${ctx}/feeType/updateFeeTypeItem",{feeTypeId:fee_type_id,superId:super_id,feeRate:fee_rate,feeTypeName:fee_type_name,preFeeTypeId:pre_fee_type_id},function(data){
			if(data.result =="success"){
				window.location.reload();
			}else{
				alert('收费项目修改失败（可能原因：计费编号重复）');
			}
		});
	}
	
	function del(fee_type_id,fee_type_name,length){
		if(Number(length) <= 1){
			alert('该计费项目不能删除，至少保留一个计费项目');
			return false;
		}
		if(confirm("确定要删除“"+fee_type_name+"”计费项目吗？")){
			$.post("${ctx}/feeType/delFeeTypeItem",{feeTypeId:fee_type_id},function(data){
				if(data.result =="success"){
					window.location.reload();
				}else{
					alert('收费项目删除失败，请联系管理员');
				}
			});
		}
	}
</script>
</body>
</html>