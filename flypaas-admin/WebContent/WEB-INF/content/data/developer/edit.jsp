<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>开发者资料 - 修改</title>
</head>

<body>
    <h1><a href="javascript:;" class="back" onclick="back()">返 回</a>开发者管理/开发者资料</h1>
    <div class="main_ctn1 material_modify">
    <form method="post" id="mainForm">
	    <ul class="ul_left">
	    <li><label>用户编码</label><span>${data.sid}</span></li>
	    <li><label>用户账号</label><span><input type="text" name="email" value="${data.email}" maxlength="50" class="txt_227" /></span></li>
	    <li><label>用户昵称</label><span><input type="text" name="username" value="${data.username}" maxlength="20" class="txt_227" /></span></li>
	    <li><label>联系手机</label><span><input type="text" name="mobile" value="${data.mobile}" maxlength="11" class="txt_227" /></span></li>
	    <li style="padding-bottom:41px;"><label>所属渠道</label>
	    	<u:select id="channel_id" value="${data.channel_id}" placeholder="所属渠道" sqlId="findAllChannel" clazz="sel_221"></u:select>
        </li>
         <li><label>销售经理</label><span><input name="sale_name" type="text" maxlength="20" class="txt_227" value="${data.sale_name}"/></span></li>
	     <li><label>销售经理手机</label><span><input name="sale_mobile" type="text" maxlength="11" class="txt_227" value="${data.sale_mobile}"/></span></li>
	    <li><label>国际漫游折扣率</label><span><input type="text" name="internRate" value="${data.intern_rate}" maxlength="50" class="txt_227" /></span></li>
	    <li>
		    <label>提醒金额（元）</label>
		    <span>
		    	<input type="text" name="remind_money" value="${data.remind_money}" class="txt_227" onfocus="inputControl.setNumber(this, 10, 4, false)" />
		    </span>
	    </li>
	    <%-- <li>
		    <label>提醒管理员</label>
		    <span>
		    	<u:selectMultiple id="remind_admin" sqlId="adminMobile" value="${data.remind_admin}"/>
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
		    <s:iterator value="data.pics" var="v">
		    		<img src="${ctx}/file/view?encode_path=<u:des3 value="${v.img_url}" />" width="36px" height="20px" style="cursor:pointer;" title="点击查看大图" onclick="viewFile(this)"/>
	    	</s:iterator>
	    </li>
	    <li><label>联系地址</label><span>${data.address}</span></li>
	    </ul>
	    <div class="clear"></div>
		<p><span id="msg" class="error" style="display:none;"></span></p>
	    <hr class="hr" />
	    <p class="btn">
            <input type="hidden" name="sid" value="${data.sid}"/>
		    <input type="button" value="修 改" class="org_btn modify" onclick="save(this)"/>
		    <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
	    </p>
    	
    </form>
    </div>

<script type="text/javascript">
var validate;

$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			email: {
				required: true,
				email2: true
			},
			username: "required",
			mobile: "mobile",
			sale_mobile: "mobile"
		},
		messages: {
			email: {
				required:"请输入开发者账号"
			},
			username: "请输入开发者昵称",
			mobile:  "请输入有效的联系手机",
			sale_mobile : "请输入有效的手机号"
		}
	});
});

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
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/developer/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>