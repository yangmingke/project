<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="data.is_view"><%--查看 --%>
	<s:set var="title" value="'查看'"/>
</s:if>
<s:else><%--添加、修改 --%>
	<s:set var="title" value="#parameters.package_id==null ? '创建' : '修改'"/>
	<s:set var="title_btn" value="#parameters.package_id==null ? '创 建' : '修 改'"/>
</s:else>
<html>
<head>
	<title>${title}套餐</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="cancel()">返 回</a>资费套餐/${title}套餐</h1>
	<form method="post" id="mainForm">
	    <div class="main_ctn1 create_package">
		    <ul class="ul_left">
			    <li>
				    <label>套餐编号</label>
				    <span>${data.package_id}&nbsp;</span>
			    </li>
			    <li>
				    <label>套餐名称</label>
				    <span>
						<input type="text" name="package_name" value="${data.package_name}" class="txt_227" maxlength="30"/>
				    </span>
			    </li>
			    <s:iterator value="data.fee_list" status="s">
			    	<s:if test="#s.index == data.fee_list.size/2">
			    		</ul>
					    <ul class="ul_right">
					    	<li>
						    	<label>套餐类型</label>
						    	<u:radio name="package_type" value="1" dictionaryType="package_type" excludeValue="2" />
					    	</li>
					    	<li id="li_charge_type">
						    	<label>付费类型</label>
						    	<u:radio name="charge_type" value="1" dictionaryType="pay_type" excludeValue="2" />
					    	</li>
			    	</s:if>
			    	
				    <li>
					    <label>${event_name}</label>
					    <s:if test="event_id.toString().startsWith(data.event_id_1003) || event_id.toString().startsWith(data.event_id_1004)"><%--1003(国际)(运营商)单向外呼、1004(国际)运营商接入语音费(回拨) --%>
						    <span>
						    	<a href="${data.fee_rate_url}" target="_blank">查看费率</a>
						    	<input type="hidden" name="fee_${event_id}_${is_show_nbr}_${fee_id}_${fee}" value="0.0000"/>
						    </span>
					    </s:if>
					    <s:else>
						    <span>
						    	<input type="text" name="fee_${event_id}_${is_show_nbr}_${fee_id}_${fee}" value="${fee}" class="txt_227" onfocus="inputControl.setNumber(this, 6, 4, false)" data-rule-required="true" data-msg-required="请输入金额"/>
						    </span>
					    </s:else>元
				    </li>
			    </s:iterator>
		    </ul>
		    
		    <s:if test="!data.is_view">
			    <div class="clear"></div>
			    <span id="msg" class="error" style="display:none;"></span>
			    <hr class="hr" />
			    <p class="btn">
			    	<input type="hidden" name="package_id" value="${data.package_id}"/>
				    <input type="button" value="${title_btn}" class="org_btn" onclick="save(this)"/>
				    <input type="button" value="取 消" class="grey_btn" onclick="cancel()"/>
			    </p>
		    </s:if>
		    
	    </div>
	</form>

<script type="text/javascript">
var validate;
//$(function(){
	//<s:if test="data.is_view"><%--查看 --%>
		//$(":text").each(function(){
			//$(this).replaceWith($(this).val());
		//});
		//$(":radio").attr("disabled", true);
	//</s:if>
	
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			package_name: "required",
			package_type: "required",
			charge_type: "required"
		},
		messages: {
			package_name: "请输入套餐名称",
			package_type: "请选择套餐类型",
			charge_type: "请选择付费类型"
		}
	});
//});

function save(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success : function(data) {
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}else if(data.result=="success"){
				setTimeout(function(){
					location.reload(true);
				}, 1000);
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/package/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}

//取消
function cancel(){
	location.href="${ctx}/package/query";
}
</script>
</body>
</html>