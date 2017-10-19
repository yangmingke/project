<select id="${id}" name="${id}" multiple="multiple" draggable="true">
	<#if (list?exists) && (list?size>0)  >
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
</script>