<#if (list?exists) && (list?size>0)  >
	<#list list as item>
		<#if item.value==value>
			<span><input type="radio" name="${name}" value="${item.value}" checked="checked" />${item.text}</span>
		<#else>
			<span><input type="radio" name="${name}" value="${item.value}" />${item.text}</span>
		</#if>
	</#list>
</#if>

<#if callback!="">
	<script type="text/javascript">
	$(function(){
		var name = "${name}";
		
		$(":radio[name='" + name + "']").click(function(){
			if($(this).is(":checked")){
				${callback}($(this).val(), $(this).parent().parent().text());
			}
		});
	});
	</script>
</#if>