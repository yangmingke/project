<#if showSelectAll>
	<label style="width:auto;margin-right:0;">
		<input type="checkbox" onclick="${id}_selectAll(this)" />全选
	</label>
</#if>
<select id="${id}" name="${id}" multiple="multiple" size="${size}" <#if disabled> disabled="disabled"</#if> >
	<#if (list?exists) && (list?size>0) >
		<#list list as item>
			<option value="${item.value}">${item.text}</option>
		</#list>
	</#if>
</select>

<script type="text/javascript">
$(function(){
	var id = "${id}";
	var value = "${value}";
	
	if(value!=""){
		value = value.split(",");
		$.each(value, function(){
			$("#"+id+" option[value='"+this+"']").prop("selected", true);
		});
	}
});

//全选
function ${id}_selectAll(box){
	$("#${id} option").prop("selected", $(box).is(":checked"));
}
</script>