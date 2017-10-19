<#if (list?exists) && (list?size>0)  >
	<#list list as item>
		<label style="cursor:pointer; width:auto; margin-right:15px;">
			<input type="radio" name="${name}" value="${item.value}" <#if item.value==value>checked="checked"</#if> />${item.text}
		</label>
	</#list>
	<label id="${name}-error" for="${name}" class="error" style="display:none;"></label>
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