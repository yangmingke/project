<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——个人认证信息</title>
	<%@include file="/page/resource.jsp" %>
</head>
<body id="04-2">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="#">开发者信息</a></li>
					<li class="active"><a href="#">基础信息</a></li>
				</ul>
			</div>			
			
			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、默认注册用户为：一般开发者，完成［个人开发者］认证或者［企业开发者］后通过平台开发的应用可申请上线商用</p>
				<p>2、通过［企业开发者］认证的企业可成为［协议用户］获得更多权限</p>
			</div>
			<!--说明state_box eof-->

				<!--基础信息 bof-->
				<div class="edit_box edit_basic edit_certification">
					<h1>认证信息</h1>
					<div class="edit_ctn">
						<s:if test="userPicList!=null||userPic!=null">
							<em <s:if test="user.oauthStatus==2">class="status"</s:if><s:elseif test="user.oauthStatus==3">class="status done"</s:elseif><s:elseif test="user.oauthStatus==4">class="status failed"</s:elseif>>&nbsp;</em>
						</s:if>
						<div class="edit_field">
							<dl>
								<dt>开发者账号</dt>
								<dd><span class="txt">${user.email }</span></dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>申请认证类型</dt>
								<dd>
									<span class="txt">
									<s:if test="user.userType==1">
										个人开发者
									</s:if>
									<s:elseif test="user.userType==2">
										企业开发者
									</s:elseif>
									</span>
								</dd>
							</dl>
						</div>
						<!-- 个人 -->
						<div id="cert_items_person" class="cert_items" <s:if test="(userPicList!=null && user.userType==2)">style="display:none;"</s:if>>
							<form action="<%=path %>/user/oAuthPersonal" method="post" name="personalForm" id="personalForm"  enctype="multipart/form-data">
							<div class="edit_field">
								<dl>
									<dt>真实姓名</dt>
									<dd>
									 <span class="txt">${user.realname }</span>
									</dd>
								</dl>
							</div>

							<div class="edit_field">
								<dl>
									<dt>证件类型</dt>
									<dd>
										<span class="txt">
										<s:if test="user.idType==1">身份证</s:if>
										<s:elseif test="user.idType==2">护照</s:elseif>
										</span>
									</dd>
								</dl>
							</div>
							
							<div class="edit_field">
								<dl>
									<dt>证件号码</dt>
									<dd>
									 <span class="txt">${user.idNbr }</span>
									</dd>
								</dl>
							</div>
							

							<div class="edit_field">
								<dl>
									<dt>证件</dt>
									<dd>
										<s:if test="user.idType==1">
											<span class="img"><img id="preview1" src="<%=path%>/file/view?FileName=<u:des3 value='${userPic.imgUrl}'/>" /></span>
										</s:if>
									</dd>
								</dl>
							</div>
							</form>
						</div>
						<!-- 组织 -->
						<div class="cert_items" id="cert_items_company" <s:if test="(userPicList!=null && user.userType==1)">style="display:none;"</s:if><s:else>style="display:none;"</s:else>>
							<form method="post" <s:if test="user.oauthStatus==null">action="<%=path %>/user/oAuthCompany"</s:if><s:else>action="<%=path %>/user/qlfcUpdate"</s:else>   enctype="multipart/form-data"   name="companyForm" id="companyForm" >
							<div class="edit_field">
								<dl>
									<dt>公司名称</dt>
									<dd>
									<span class="txt">${user.realname}</span>
									</dd>
								</dl>
							</div>
							<div class="edit_field">
								<dl>
									<dt>公司地址</dt>
									<dd>
									<span class="txt">${user.address }</span>
									</dd>
								</dl>
							</div>

							
							<s:set id="p1" value="1"></s:set>
				            <s:if test="userPicList!=null">
				              	<s:iterator value="userPicList" var="p">
				              		<s:if test="#p.idType==3">
				              			 <div class="edit_field">
											<dl>
												<dt>组织机构号</dt>
												<dd>
													<span class="txt">${p.idNbr }</span>
												</dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>组织机构证件</dt>
											</dl>
										</div>
							            <div class="edit_field">
											<dl>
												<dd><span class="img"><img src="<%=path%>/file/view?FileName=<u:des3 value='${p.imgUrl}'/>" /></span></dd>
											</dl>
										</div>
						                <s:set id="p1" value="2"></s:set>
				              		</s:if>
				              	</s:iterator>
				            </s:if>
				            
				            
				            
							 <s:set id="p2" value="1"></s:set>
				             <s:if test="userPicList!=null">
				              	<s:iterator value="userPicList" var="p">
				              		<s:if test="#p.idType==4">
				              		   <div class="edit_field">
											<dl>
												<dt>税务登记号</dt>
												<dd>
												<span class="txt">${p.idNbr }</span>
												</dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>税务登记证件</dt>
											</dl>
										</div>
						                <div class="edit_field">
											<dl>
												<dd><span class="img"><img src="<%=path%>/file/view?FileName=<u:des3 value='${p.imgUrl}'/>" /></span></dd>
											</dl>
										</div>
						              <s:set id="p2" value="2"></s:set>
				              		</s:if>
				              	</s:iterator>
				              </s:if>
				              
				              
							
							 <s:set id="p3" value="1"></s:set>
							 <s:if test="userPicList!=null">
				              	<s:iterator value="userPicList" var="p">
				              		<s:if test="#p.idType==5">
					              		<div class="edit_field">
											<dl>
												<dt>营业执照号</dt>
												<dd>
												<span class="txt">${p.idNbr }</span>
												</dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>营业执照证件</dt>
											</dl>
										</div>
						               <div class="edit_field">
											<dl>
												<dd><span class="img"><img src="<%=path%>/file/view?FileName=<u:des3 value='${p.imgUrl}'/>" /></span></dd>
											</dl>
										</div>
						               <s:set id="p3" value="2"></s:set>
				              		</s:if>
				              	</s:iterator>
				              </s:if>
				             
				             
							<div class="edit_field">
								<dl>
									<dt>法定代表人</dt>
									<dd>
									<span class="txt">${user.legalPerson }</span>
									</dd>
								</dl>
							</div>
							<div class="edit_field">
								<dl>
									<dt>公司电话</dt>
									<dd>
									<span class="txt">${user.companyNbr }</span>
									</dd>
								</dl>
							</div>
							</form>
						</div>
					</div>
				</div>
				<!--基础信息 eof-->
				
				<!--编辑提交按钮 bof-->
				<div class="edit_btn">
				<s:if test="user.userType==1">
					<s:if test="user.oauthStatus==3">
						<input type="button" value="升级企业认证" class="confirm_btn" onclick="href2url('<%=path %>/user/updatePerson')"/>
					</s:if>
					<s:elseif test="user.oauthStatus==4">
						<input type="button" value="编辑" class="confirm_btn" onclick="href2url('<%=path %>/user/updatePerson')"/>
					</s:elseif>
					<s:if test="user.revisability==1">
						<input type="button" value="编辑" class="confirm_btn" onclick="href2url('<%=path %>/user/updatePerson')"/>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="user.oauthStatus==4 || user.revisability==1">
						<input type="button" value="编辑" class="confirm_btn" onclick="href2url('<%=path %>/user/updateCompany')"/>
					</s:if>
				</s:else>
				</div>
				<!--编辑提交按钮 bof-->
		</div>

		<!--个人认证和企业认证对应项显示隐藏-->
		<script type="text/javascript">
		 $(function(){
			$("input[type='radio']").click(function(){
				//var radio_index = $(this).parent(".radio").index();
				var radio_index = 1;
				var radio_id = $(this).attr('id');
				if(radio_id == 'person'){
					radio_index = 0;
				}
				$(".cert_items").eq(radio_index).show().siblings(".cert_items").hide();
			});
			var list = "${userPicList.size()}";
			var type = "${user.userType}";
			if(list>0){
				if(type==2){
					$("#cert_items_person").remove();
					$('#cert_items_company').show();
				}
			}
			
		});
		</script>
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
</body>
</html>