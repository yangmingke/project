<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<%-- <u:authority menuId="38">
	<s:set var="menuId_38" value="true"/>
</u:authority>
<u:authority menuId="39">
	<s:set var="menuId_39" value="true"/>
</u:authority>
<u:authority menuId="40">
	<s:set var="menuId_40" value="true"/>
</u:authority>
<u:authority menuId="41">
	<s:set var="menuId_41" value="true"/>
</u:authority>
<u:authority menuId="42">
	<s:set var="menuId_42" value="true"/>
</u:authority>
<u:authority menuId="131">
	<s:set var="menuId_131" value="true"/>
</u:authority>
<u:authority menuId="132">
	<s:set var="menuId_132" value="true"/>
</u:authority> --%>

<html>
<head>
	<title>代理商管理</title>
</head>

<body>
      <div class="main_search">
      <form method="post" id="mainForm" action="${ctx}/agent/query">
      	<ul>
            <li>
				<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="代理商ID/用户名/手机号/昵称/认证名称" class="txt_250" />
			</li>
            <li class="time">
            	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
			</li>
            <li>
				<u:select id="user_status" value="${param.user_status}" placeholder="代理商状态" dictionaryType="user_status" />
			</li>
            <li>
				<u:select id="oauth_status" value="${param.oauth_status}" placeholder="认证状态" dictionaryType="oauth_status" />
			</li>
			<%-- 
            <li>
				<u:select id="wallet_status" value="${param.wallet_status}" placeholder="钱包状态" dictionaryType="wallet_status" />
			</li>
			 --%>
			 
			<li>
				<input type="text" name="sale_name" value="<s:property value="#parameters.sale_name"/>" maxlength="40" placeholder="销售经理" class="txt_127" />
			</li>
            <li>
				<input type="submit" value="查 询" class="search"/>
			</li>
           </ul>
      </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
      <table cellpadding="0" cellspacing="0" border="0">
	      <tbody>
		      <tr>
			      <th width="50px">序号</th>
			      <th>代理商ID</th>
			      <th>用户名</th>
			      <th>手机号</th>
			      <th>昵称</th>
			      <th>认证名称</th>
			      <th>代理商状态</th>
			      <th>认证状态</th>
			      <th>钱包状态</th>
			      <th>注册时间</th>
			      <th>销售经理</th>
			      <th>开发者数量</th>
			      <th width="180px">操作</th>
		      </tr>
		      <s:iterator value="page.list">
			      <tr>
				      <td>${rownum}</td>
				      <td>${sid}</td>
				      <td>${email}</td>
				      <td>${mobile}</td>
				      <td>${username}</td>
				      <td>${realname}</td>
				      <td name="user_status"><u:ucparams key="${status}" type="user_status"/></td>
				      <td><u:ucparams key="${oauth_status}" type="oauth_status"/></td>
				      <td name="wallet_status"><u:ucparams key="${enable_flag}" type="wallet_status"/></td>
				      <td>${create_date}</td>
				      <td>${sale_name}</td>
				      <td style="text-align: center;">${user_count}</td>
				      <td>
				      	<%-- <s:if test="menuId_38"> --%>
				      		<a href="javascript:;" onclick="view('${sid}')">查看</a>
				    	<%-- </s:if> --%>
				      	
				      	<span>
					      	<span name="operate_1" ${status==1 ? "" : "style='display:none;'"}>
					      		<%-- <s:if test="menuId_40"> --%>
									| <a href="javascript:;" onclick="updateStatus(this, '${sid}', 5)">锁定</a>
								<%-- </s:if>
					      		<s:if test="menuId_41"> --%>
									| <a href="javascript:;" onclick="notice('${sid}')">通知</a>
								<%-- </s:if>
					      		<s:if test="menuId_42"> --%>
									| <a href="javascript:;" onclick="mainAccount('${sid}')">主账号</a>
								<%-- </s:if> --%>
					      	</span>
					      	<span name="operate_5" ${status==5 ? "" : "style='display:none;'"}>
					      		<%-- <s:if test="menuId_39"> --%>
									 <a href="javascript:;" onclick="updateStatus(this, '${sid}', 1)">解锁</a>
								<%-- </s:if>
					      		<s:if test="menuId_40"> --%>
									<%-- | <a href="javascript:;" onclick="updateStatus(this, '${sid}', 6)">关闭</a> --%>
								<%-- </s:if>
					      		<s:if test="menuId_41"> --%>
									| <a href="javascript:;" onclick="notice('${sid}')">通知</a>
								<%-- </s:if>
					      		<s:if test="menuId_42"> --%>
									| <a href="javascript:;" onclick="mainAccount('${sid}')">主账号</a>
								<%-- </s:if> --%>
					      	</span>
					      	<span name="operate_6" ${status==6 ? "" : "style='display:none;'"}>
					      		<%-- 
					      	 	| <a href="javascript:;" onclick="updateStatus(this, '${sid}', 1)">重新激活</a>
					      	 	--%>
					      	</span>
				      	</span>
				      	
				    <%--   <span>
					      	<span name="heavybuyer_0" ${is_heavybuyer==0 ? "" : "style='display:none;'"}>
						      	<s:if test="menuId_131">
									| <a href="javascript:;" onclick="setHeavybuyer(this, '${sid}', 1)">设置为大客户</a>
						      	</s:if>
						    </span>
					      	<span name="heavybuyer_1" ${is_heavybuyer==1 ? "" : "style='display:none;'"}>
						      	<s:if test="menuId_132">
									| <a href="javascript:;" onclick="setHeavybuyer(this, '${sid}', 0)">取消为大客户</a>
						      	</s:if>
						   	</span>
				      	</span> --%>
				      	
				      	<span>
					      	<span name="isProxy_0" ${is_proxy==0 ? "" : "style='display:none;'"}>
						      	<%-- <s:if test="menuId_131"> --%>
									| <a href="javascript:;" onclick="setProxy(this, '${sid}', 1)">设置为代理商</a>
						      	<%-- </s:if> --%>
						    </span>
					      	<span name="isProxy_1" ${is_proxy==1 ? "" : "style='display:none;'"}>
						      	<%-- <s:if test="menuId_132"> --%>
									| <a href="javascript:;" onclick="setProxy(this, '${sid}', 0)">降级</a>
						      	<%-- </s:if> --%>
						   	</span>
				      	</span>
					</td>
			      </tr>
		      </s:iterator>
	      </tbody>
      </table>
      
      <u:page page="${page}" formId="mainForm" />
      </div>

<script type="text/javascript">
//查看
function view(sid){
	location.href="${ctx}/agent/view?sid=" + sid;
}

//锁定、重新激活
function updateStatus(a, sid, status){
	var user_status="";
	switch(status){
	case 1:
		user_status="邮箱已激活";
		break;
	case 5:
		user_status="锁定";
		break;
	}
	
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/agent/updateStatus",
			data:{
				sid:sid,
				status:status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(a).parents("td").find("[name='operate_"+status+"']").show().siblings("span").hide();
					$(a).parents("tr").find("[name='user_status']").text(user_status);
					if(status==6){
						$(a).parents("tr").find("[name='wallet_status']").text("已注销");
					}
				}
				alert(data.msg);
			}
		});
	}
}

//设置或取消为代理商
function setProxy(a, sid, isProxy){
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/agent/setProxy",
			data:{
				sid : sid,
				isProxy : isProxy
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					//$(a).parents("td").find("[name='isProxy_"+isProxy+"']").show().siblings("span").hide();
					window.location.href="/agent/query";//刷新当前页面.
				}
				alert(data.msg);
			}
		});
	}
}

//设置或取消为大客户
/* function setHeavybuyer(a, sid, is_heavybuyer){
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/developer/setHeavybuyer",
			data:{
				sid : sid,
				is_heavybuyer : is_heavybuyer
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(a).parents("td").find("[name='heavybuyer_"+is_heavybuyer+"']").show().siblings("span").hide();
				}
				alert(data.msg);
			}
		});
	}
}
 */
//通知
function notice(sid){
	location.href="${ctx}/developer/notice?sid=" + sid;
}

//主账号
function mainAccount(sid){
	location.href="${ctx}/developer/mainAccount?sid=" + sid;
}
</script>
</body>
</html>