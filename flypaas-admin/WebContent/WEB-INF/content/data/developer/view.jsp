<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>开发者资料</title>
</head>

<body>
    <h1><a href="${ctx}/developer/query" class="back">返 回</a>开发者管理/开发者资料</h1>
    <div class="main_ctn1 developer_material">
    <ul class="ul_left">
    
    <li><label>用户编码</label><span>${data.sid}</span></li>
    <li><label>用户账号</label><span>${data.email}</span></li>
    <li><label>用户昵称</label><span>${data.username}</span></li>
    <li><label>联系手机</label><span>${data.mobile}</span></li>
    <li><label>所属渠道</label><span>${data.channel_id}</span></li>
  	<li><label>销售经理</label><span>${data.sale_name}</span></li>
   	<li><label>销售经理手机</label><span>${data.sale_mobile}</span></li>
   	<li><label>国际漫游折扣率</label><span>${data.intern_rate}</span></li>
    <li><label>提醒金额（元）</label><span>${data.remind_money}</span></li>
    <%-- <li>
	    <label>提醒管理员</label>
	    <span>
	    	<u:selectMultiple id="remind_admin" sqlId="adminMobile" value="${data.remind_admin}" showSelectAll="false" disabled="true" />
	    </span>
    </li> --%>
    </ul>
    <ul class="ul_right">
    <li><label>注册时间</label><span>${data.create_date}</span></li>
    <li><label>是否为大客户</label><span>${data.is_heavybuyer==1 ? "是" : "否"}</span></li>
    <li><label>是否为代理商</label><span>${data.is_proxy==1 ? "是" : "否"}</span></li>
    <li><label>钱包状态</label><span><u:ucparams key="${data.enable_flag}" type="wallet_status"/></span></li>
    <li><label>钱包编号</label><span>${data.wallet_id}</span></li>
    <li><label>开发者状态</label><span><u:ucparams key="${data.status}" type="user_status"/></span></li>
    <li><label>认证状态</label><span><u:ucparams key="${data.oauth_status}" type="oauth_status"/></span></li>
    <li><label>真实姓名</label><span>${data.realname}</span></li>
    <li>
	    <label>证件类型</label>
	    <span>
	    	<s:iterator value="data.pics" var="v" status="s">
	    		<s:if test="#s.index>0">/</s:if>
	    		<u:ucparams key="${v.id_type}" type="id_type"/>
	    	</s:iterator>
	    </span>
    </li>
    <li>
	    <label>证件编号</label>
	    <span>
	    	<s:iterator value="data.pics" var="v" status="s">
	    		<s:if test="#s.index>0">/</s:if>
	    		${v.id_nbr}
	    	</s:iterator>
	    </span>
    </li>
    <li>
	    <label>证件图片</label>
	    <dl class="small">
	    	<s:iterator value="data.pics" var="v">
		    	<dd>
		    		<img src="${ctx}/file/view?encode_path=<u:des3 value="${v.img_url}" />" width="36px" height="20px" style="cursor:pointer;" title="点击查看大图" onclick="viewFile(this)"/>
		    	</dd>
	    	</s:iterator>
	    </dl>
    </li>
    <li><label>联系地址</label><span>${data.address}</span></li>
    </ul>
    <div class="clear"></div>
 	<br/>
 	 <hr class="hr" />
    <p class="btn">
    	<u:authority menuId="43">
	    	<%-- <s:if test="data.revisability==0">
			    <input type="button" value="安全校正" class="grey_btn correct" onclick="securityCorrection(this, '${data.sid}')"/>
	    	</s:if> --%>
    	</u:authority>
    	<u:authority menuId="44">
	    	<input type="button" value="重新提交资料" class="grey_btn" onclick="edit('${data.sid}')"/>
    	</u:authority>
    </p>
    </div>

<script type="text/javascript">
//安全校正
function securityCorrection(btn, sid){
	if(confirm("确定要安全校正吗？")){
		$(btn).attr("disabled", true);
		
		$.ajax({
			type: "post",
			url: "${ctx}/developer/securityCorrection",
			data:{
				sid:sid
			},
			success: function(data){
				$(btn).attr("disabled", false);
				
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(btn).remove();
				}
				alert(data.msg);
			}
		});
	}
}

//重新提交资料
function edit(sid){
	location.href="${ctx}/developer/edit?sid=" + sid;
}

function saveSale(btn,sid){
	
	var sale_name = $("#sale_name").val();
	var sale_mobile = $("#sale_mobile").val();
	if($.isEmptyObject(sale_name)){
		alert("销售经理不能为空");
		return false;
	}
	if(!/^1[0-9]{10}$/.test(sale_mobile)){
		alert("请填写合法的手机号");
		return false;
	}
	if(confirm("确定要维护销售经理吗？")){
		$(btn).attr("disabled", true);
		$.ajax({
			type: "post",
			url: "${ctx}/developer/saveSale",
			data:{
				'sid':sid,
				'sale_name':sale_name,
				'sale_mobile':sale_mobile
			},
			success: function(data){
				$(btn).attr("disabled", false);
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				alert(data.msg);
			}
		});
	}
}
</script>
</body>
</html>