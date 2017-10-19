<select id="${id}" name ="${id}" class="${clazz}" style="${style}" onChange="${id}_onChange(this, false)">
	<#if (list?exists) && (list?size>0)  >
			<#list list as item>
				<#if item.value==value>
					<option value="${item.value}" selected="selected">${item.text}</option>
				<#else>
					<option value="${item.value}">${item.text}</option>
				</#if>
			</#list>
		</#if>
</select>
<input type="hidden" id="${id}_name" name="${id}_name" />

<script type="text/javascript">
$(function(){
	${id}_onChange($("#${id}")[0], true);
});

//选择下拉框
function ${id}_onChange(obj, isInit){
	var value = $(obj).val();
	var text = value=="" ? "所有" : $(obj).find("option:selected").text();
	$("#${id}_name").val(text);
	
	<#if callback??>
		${callback}(value, text, isInit);
	</#if>
}
</script>