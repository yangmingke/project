<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script type="text/javascript" src="<%=path%>/page/js/oauth.js"></script>
	
	<style type="text/css">
	#kk {
		width: 550px;
		height: 550px;
	}
	
	#preview_wrapper {
		/*width: 643px;
		height: 318px;
		background-color: #CCC;*/
		overflow: hidden;
	}
	
	.preview_fake { /* 该对象用于在IE下显示预览图片 */
		filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale
			);
		/*width: 643px;*/
		overflow: hidden;
	}
	
	.preview_size_fake { /* 该对象只用来在IE下获得图片的原始尺寸，无其它用途*/
		filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image
			);
		width: 50px;
		visibility: hidden;
		overflow: hidden;
	}
	
	.preview { /* 该对象用于在FF下显示预览图片*/
		/*width: 643px;
		height: 318px;*/
		overflow: hidden;
	}
	.spanerror {
	    background: url("<%=path%>/images/error.png") no-repeat scroll left center rgba(0, 0, 0, 0);
	    color: #E76200;
	    display: inline-block;
	    font-family: 黑体;
	    font-size: 12px;
	    height: 16px;
	    padding-left: 25px;
	}
	</style>
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
					<li><a href="#">用户信息</a></li>
					<li class="active"><a href="#">基础信息</a></li>
				</ul>
			</div>			
			
			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、默认注册用户为：注册用户，完成<c:if test="${user.superSid }">［个人用户］认证或者</c:if>［企业用户］认证后通过平台开发的应用可申请上线商用</p>
				<p>2、通过［企业用户］认证的企业可成为［协议用户］获得更多权限</p>
			</div>
			<!--说明state_box eof-->

				<!--基础信息 bof-->
				<div class="edit_box edit_basic edit_certification">
					<h1>认证信息采集</h1>
					<div class="edit_ctn">
						<div class="edit_field">
							<dl>
								<dt>用户账号</dt>
								<dd><span class="txt">${user.email }</span></dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>申请认证类型</dt>
								<dd>
								<s:if test="user.oauthStatus==null">
									<c:if test="${user.superSid == null}">
										<%-- <span class="radio">
											<input type="radio" name="o_type" checked="checked" id="person"/>个人用户
										</span> --%>
									</c:if>
									<span class="radio">
										<input type="radio" name="o_type" id="company"/>企业用户
									</span>
								</s:if>
								<s:else>
									<s:if test="user.userType==1">
										<s:if test="user.oauthStatus==3 && user.revisability!=1">
											<span class="radio">
												<input type="radio" name="o_type" id="company" checked="checked"/>企业用户
											</span>
										</s:if>
										<s:else>
											<%-- <span class="radio">
												<input checked="checked" type="radio" name="o_type" checked="checked" id="person"/>个人用户
											</span> --%>
										</s:else>
									</s:if>
									<s:elseif test="user.userType==2">
										<span class="radio">
											<input checked="checked" type="radio" name="o_type" id="company"/>企业用户
										</span>
									</s:elseif>
								</s:else>
								</dd>
							</dl>
						</div>
						<!-- 个人 -->
						<div id="cert_items_person" class="cert_items" <s:if test="(user.userType==1 && user.oauthStatus==3 && user.revisability!=1)||user.userType==2">style="display:none;"</s:if>>
							<form action="<%=path %>/user/oAuthPersonal" method="post" name="personalForm" id="personalForm"  enctype="multipart/form-data">
							<div class="edit_field">
								<dl>
									<dt>真实姓名</dt>
									<dd>
									<input type="text" value="${user.realname }" id="real_name" name="user.realname" onkeyup="if(this.value.length>60)this.value=this.value.substring(0,60)"/>
									<span class="error" style='display:none' id="nameSpanError">错误：不合法</span>
									</dd>
								</dl>
							</div>

							<div class="edit_field">
								<dl>
									<dt>证件类型</dt>
									<dd class="select_type">
										<div class="select">
											<span>
											<s:if test="user.idType==1">身份证</s:if>
											<s:elseif test="user.idType==2">护照</s:elseif>
											<s:else>请选择证件</s:else>
											<i>&nbsp;</i></span>
											<ul>
												<li onclick="setIdType('1')" <s:if test="user.idType==1">class="selected"</s:if>>身份证</li>
												<li onclick="setIdType('2')" <s:if test="user.idType==2">class="selected"</s:if>>护照</li>
											</ul>
										</div>
										<span class="error" style='display:none' id="idTypeSpanError">错误：请选择证件类型</span>
									</dd>
								</dl>
							</div>
							
							<div class="edit_field">
								<dl>
									<dt>证件号码</dt>
									<dd>
									<input type="text" value="${user.idNbr }" id="id_num" name="user.idNbr" class="cert_num" onblur="idNumExists(this.value,'ID','${user.idNbr }')"/>
									<span class="error" style='display:none' id="idNumSpanError">错误：请输入正确的证件号码</span>
									</dd>
								</dl>
							</div>
							
							<div class="edit_field">
								<dl>
									<dt>证件照片</dt>
									<dd><input type="file" value="" class="file_1" id="idCardFile" name="idCardFile" onchange="onUploadImgChange(this,1)"/><span class="error" style='display:none' >请选择正确证件照片</span></dd>
									<dd><span class="tips">请上传手持真实有效的身份证及护照扫描件、照片的正面,确保证件内容和脸部清晰可见，控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span></dd>
								</dl>
							</div>

							<div class="edit_field">
								<dl>
									<dt>样例</dt>
									<dd>
<%-- 									<span class="img"><img src="<%=path %>/page/images/id_img.png" /></span> --%>
										<s:if test="user.idType==1">
											<div id="preview_wrapper" class="id_img">
												<div id="preview_fake1" class="preview_fake">
													<span class="img">
														<img id="preview1"
														<s:if test='userPic.imgUrl!=null'>src="<%=path%>/file/view?FileName=<u:des3 value='${userPic.imgUrl}'/>"</s:if>
														<s:else>src="<%=path%>/page/images/id_img.png"</s:else>
														onload="onPreviewLoad(this)" />
													</spa>
												</div>
											</div>
										</s:if>
										<br />
										<img id="preview_size_fake1" class="preview_size_fake" />
									</dd>
								</dl>
							</div>
							<div>
								<dl>
									<dt>&nbsp;</dt>
									<dd><span class="error" style='display:none' >错误：请选择正确证件照片</span></dd>
								</dl>
								
							</div>
							 <input type="hidden" id="pidType" name="user.idType" value="${user.idType }"/>
							<input type="hidden" id="existsID"/>
							</form>
						</div>
						<!-- 组织 -->
						<div class="cert_items" id="cert_items_company" <s:if test="(user.userType==1 && user.oauthStatus!=3) || (user.userType==1 && user.oauthStatus==3 && user.revisability==1)">style="display:none;"</s:if><s:else>style="display:none;"</s:else>>
							<form method="post" <s:if test="(user.userType==1 && user.oauthStatus==null)">action="<%=path %>/user/oAuthCompany"</s:if><s:else>action="<%=path %>/user/qlfcUpdate"</s:else><s:else>action="<%=path %>/user/oAuthCompany"</s:else>  enctype="multipart/form-data"   name="companyForm" id="companyForm" >
							<div class="edit_field">
								<dl>
									<dt>公司名称</dt>
									<dd>
									<input type="text"  value="${user.realname}" id="company_name" name="user.realname" onkeyup="if(this.value.length>60)this.value=this.value.subString(0,60)"/>
									<span class="error" style='display:none' id="companyNameSpanError">错误：请输入正确的公司名称</span>
									</dd>
								</dl>
							</div>
							<div class="edit_field">
								<dl>
									<dt>公司地址</dt>
									<dd>
									<input type="text" value="${user.address }" id="company_address" name="user.address" class="cert_num" onkeyup="if(this.value.length>200)this.value=this.value.subString(0,60)"/>
									<span class="error" style='display:none' id="addressSpanError">错误：请输入正确的公司地址</span>
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
												<dd><input type="text" value="${p.idNbr }" id="org_num" name="user.orgId" onblur="idNumExists(this.value,'ORGNUM','${p.idNbr }')"/>
												<span class="tips tips1">例如：12365678-9</span>
												<span class="error" style='display:none' id="orgNumSpanError">错误：请输入正确的组织机构号</span>
												</dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>组织机构证件</dt>
												<dd><input type="file" class="file_1" name="taxEnrolCertificateFile"  onchange="verifyPic(this,1)" id="orgIdFile"/><span class="error" style='display:none' id="isOrgIdFileError">错误：请选择正确的组织机构图片</span></dd>
												<dd>
												<span class="tips">请上传控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span>
												</dd>
											</dl>
										</div>
							            <div class="edit_field">
											<dl>
												<dt>证件</dt>
												<dd><span class="img"><img src="<%=path%>/file/view?FileName=<u:des3 value='${p.imgUrl}'/>" /></span></dd>
											</dl>
										</div>
						                <s:set id="p1" value="2"></s:set>
				              		</s:if>
				              	</s:iterator>
				            </s:if>
				            <s:if test="#p1==1">
				              		<div class="edit_field">
										<dl>
											<dt>组织机构号</dt>
											<dd><input type="text" id="org_num"  name="user.orgId" onblur="idNumExists(this.value,'ORGNUM','')"/>
											<span class="tips tips1">例如：12365678-9</span>
											<span class="error" style='display:none' id="orgNumSpanError">错误：请输入正确的组织机构号</span>
											</dd>
										</dl>
									</div>
									<div class="edit_field">
										<dl>
											<dt>组织机构证件</dt>
											<dd><input type="file" class="file_1" name="taxEnrolCertificateFile"  onchange="verifyPic(this,1)" id="orgIdFile"/><span class="error" style='display:none' id="isOrgIdFileError">错误：请选择正确的组织机构图片</span></dd>
											<dd>
											<span class="tips">请上传控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span>
											</dd>
										</dl>
									</div>
				            </s:if>
				            
				            
				            
							 <s:set id="p2" value="1"></s:set>
				             <s:if test="userPicList!=null">
				              	<s:iterator value="userPicList" var="p">
				              		<s:if test="#p.idType==4">
				              		   <div class="edit_field">
											<dl>
												<dt>税务登记号</dt>
												<dd>
												<input type="text" value="${p.idNbr }" id="ittId_num" name="ittid" onblur="idNumExists(this.value,'ITTNUM','${p.idNbr }')"/>
												<span class="tips tips1">例如：15位数字或字母</span>
												<span class="error" style='display:none' id="ittidSpanError">错误：请输入正确的税务登记号</span>
												</dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>税务登记证件</dt>
												<dd><input type="file" class="file_1" name="institutionalFile" onchange="verifyPic(this,2)"  id="ittIdFile"/><span class="error" style='display:none' id="ittIdFileError">错误：请选择正确的税务登记图片</span></dd>
												<dd>
												<span class="tips">请上传控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span>
												</dd>
											</dl>
										</div>
						                <div class="edit_field">
											<dl>
												<dt>证件</dt>
												<dd><span class="img"><img src="<%=path%>/file/view?FileName=<u:des3 value='${p.imgUrl}'/>" /></span></dd>
											</dl>
										</div>
						              <s:set id="p2" value="2"></s:set>
				              		</s:if>
				              	</s:iterator>
				              </s:if>
				              <s:if test="#p2==1">
				              	<div class="edit_field">
									<dl>
										<dt>税务登记号</dt>
										<dd>
										<input type="text" id="ittId_num" name="ittid" onblur="idNumExists(this.value,'ITTNUM','')"/>
										<span class="tips tips1">例如：15位数字或字母</span>
										<span class="error" style='display:none' id="ittidSpanError">错误：请输入正确的税务登记号</span>
										</dd>
									</dl>
								</div>
								<div class="edit_field">
									<dl>
										<dt>税务登记证件</dt>
										<dd><input type="file" class="file_1" name="institutionalFile" onchange="verifyPic(this,2)"  id="ittIdFile"/><span class="error" style='display:none' id="ittIdFileError">错误：请选择正确的税务登记图片</span></dd>
										<dd>
										<span class="tips">请上传控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span>
										</dd>
									</dl>
								</div>
				              </s:if>
				              
				              
							
							 <s:set id="p3" value="1"></s:set>
							 <s:if test="userPicList!=null">
				              	<s:iterator value="userPicList" var="p">
				              		<s:if test="#p.idType==5">
					              		<div class="edit_field">
											<dl>
												<dt>营业执照号</dt>
												<dd><input type="text" value="${p.idNbr }" id="bs_num" name="bsnumid" onblur="idNumExists(this.value,'BSNUM','${p.idNbr }')"/>
												<span class="tips tips1">例如：15位数</span>
												<span class="error" style='display:none' id="bsnumSpanError">错误：请输入正确的营业执照号</span>
												</dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>营业执照证件</dt>
												<dd><input type="file" class="file_1" name="bsnumFile" onchange="verifyPic(this,3)"  id="bsnumFile"/><span class="error" style='display:none' id="bsnumFileError">错误：请选择正确的营业执照图片</span></dd>
												<dd>
												<span class="tips">请上传控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span>
												</dd>
											</dl>
										</div>
						               <div class="edit_field">
											<dl>
												<dt>证件</dt>
												<dd><span class="img"><img src="<%=path%>/file/view?FileName=<u:des3 value='${p.imgUrl}'/>" /></span></dd>
											</dl>
										</div>
						               <s:set id="p3" value="2"></s:set>
				              		</s:if>
				              	</s:iterator>
				              </s:if>
				             <s:if test="#p3==1">
				             	<div class="edit_field">
									<dl>
										<dt>营业执照号</dt>
										<dd><input type="text" id="bs_num" name="bsnumid" onblur="idNumExists(this.value,'BSNUM','')"/>
										<span class="tips tips1">例如：15位数</span>
										<span class="error" style='display:none' id="bsnumSpanError">错误：请输入正确的营业执照号</span>
										</dd>
									</dl>
								</div>
								<div class="edit_field">
									<dl>
										<dt>营业执照证件</dt>
										<dd><input type="file" class="file_1" name="bsnumFile" onchange="verifyPic(this,3)"  id="bsnumFile"/><span class="error" style='display:none' id="bsnumFileError">错误：请选择正确的营业执照图片</span></dd>
										<dd>
										<span class="tips">请上传控制图片大小在2M以内，格式支持jpg，jpeg，png，gif。Mac OSX用户请使用firefox浏览器进行上传</span>
										</dd>
									</dl>
								</div>
				             </s:if>
				             
				             
							<div class="edit_field">
								<dl>
									<dt>法定代表人</dt>
									<dd><input type="text" value="${user.legalPerson }" id="legalPerson" name="user.legalPerson"/>
									<span class="error" style='display:none' id="legalPersonError">错误：请输入正确的法人名称</span>
									</dd>
								</dl>
							</div>
							<div class="edit_field">
								<dl>
									<dt>公司电话</dt>
									<dd>
									<input type="text" value="${user.companyNbr }" id="companyNbr" name="user.companyNbr" />
									<span class="error" style='display:none' id="companyNbrError">错误：请输入正确的公司电话</span>
									</dd>
								</dl>
							</div>
							<input type="hidden" id="existsID"/>
							<input type="hidden" id="existsORGNUM"/>
							<input type="hidden" id="existsITTNUM"/>
							<input type="hidden" id="existsBSNUM"/>
							<input type="hidden" id="picType1"/>
				            <input type="hidden" id="picType2"/>
				            <input type="hidden" id="picType3"/>
							</form>
						</div>
					</div>
				</div>
				<!--基础信息 eof-->
				

				<!--编辑提交按钮 bof-->
				<div class="edit_btn">
					<input type="submit" value="提交审核" class="confirm_btn" id="oauth_confirm_btn"/>
					<input type="button" value="取消" class="cancel_btn" id="oauth_cancel_btn" onclick="href2url('<%=path %>/user/oAuthDispather')" />
				</div>
				<!--编辑提交按钮 bof-->
		</div>

		<!--个人认证和企业认证对应项显示隐藏-->
		<script type="text/javascript">
		 $(function(){
			$("input[type='radio']").click(function(){
				var radio_index = $(this).parent(".radio").index();
				var length = $('.radio').length;
				if(length != 1){
					$(".cert_items").eq(radio_index).show().siblings(".cert_items").hide();
				}
			});
			/* var list = "${userPicList.size()}";
			var type = "${user.userType}";
			if(list>0){
				if(type==2){
					$("#cert_items_person").remove();
					$('#cert_items_company').show();
				}
			} */
			var length = $('.radio').length;
			if(length == 1){
				$(".cert_items").eq(length).show().siblings(".cert_items").hide();
			}
			if("${user.superSid}" != ""){
				$('#company').click();
				/* $("#cert_items_person").hide();
				$('#cert_items_company').show(); */
			}
			$('#company').click();
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