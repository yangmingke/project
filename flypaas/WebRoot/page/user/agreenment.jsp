<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——协议认证</title>
	<%@include file="/page/resource.jsp"%>
</head>
<body id="04-3">
	<!--头部header bof-->
	<%@include file="/page/head.jsp"%>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp"%>
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="#">用户信息</a></li>
					<li class="active"><a href="#">协议认证</a></li>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、协议用户是与平台签署合作协议，并缴纳一定保证金达成合作的用户，协议用户目前支持显示认证手机号码（非法号码除外）</p>
				<p>2、保证金缴纳之后，保证金金额将冻结在您的平台保证金账户中，这部分资金不可使用</p>
				<p>3、如果企业将不想成为平台协议用户，可申请解冻保证金，保证金将返回用户的平台账户或银行卡账户</p>
				<p>4、保证金不能开具发票</p>
				<p>5、用户成为平台协议用户需下载《企业申请合作用户协议》，上传加盖企业公章的原件照片或扫描件</p>
			</div>
			<!--说明state_box eof-->
					<s:if test="security==null||(security.status==0&&applyFor==null)||security.status==3||(security.status==0&&securityBalance.relieveType==4)">
						 <s:if test="user.userType==1||(user.userType==2&&user.oauthStatus!=3)">
								<!--基础配置 bof-->
									<div class="edit_box edit_basic">
										<h1>协议认证信息</h1>
										<div class="edit_field">
											<dl>
												<dt>用户账号</dt>
												<dd><span class="txt">${user.email }</span></dd>
											</dl>
										</div>
										<div class="edit_field">
											<dl>
												<dt>用户认证</dt>
												<dd>
													<span class="mark">
															个人用户
													</span>
												</dd>
											</dl>
										</div>
				
										<div class="edit_field">
											<p class="mark">请先认证为【企业用户】</p>
										</div>
									</div>
									<!--基础配置 eof-->
					
									<!--编辑提交按钮 bof-->
									<div class="edit_btn">
										<input type="button" value="认证企业用户" class="confirm_btn" onclick="location.href='<%=path %>/user/oAuthDispather'"/>
									</div>
									<!--编辑提交按钮 bof-->
							</s:if>
							<s:else>
									<div class="edit_box edit_basic">
										<h1>协议认证信息</h1>
										<div class="edit_ctn">
											<div class="edit_field">
												<dl>
													<dt>用户账号</dt>
													<dd><span class="txt">${user.email }</span></dd>
												</dl>
											</div>
											<div class="edit_field">
												<dl>
													<dt>用户认证</dt>
													<dd><span class="txt">企业用户</span></dd>
												</dl>
											</div>
											<form action="<%=path %>/agreenment/addAgreenmet" method="post" enctype="multipart/form-data" id="agreenFrm" name="agreenFrm">
											<div class="edit_field">
												<dl>
													<dt>签署协议文本</dt>
													<%-- <dd><span class="txt">请下载</span><a href="http://www.flypaas.com/useragreement/useragreement.docx">《企业申请合作用户协议》</a></dd> --%>
													<dd><span class="txt">请下载</span><a href="/file/useragreement">《企业申请合作用户协议》</a></dd>
													<dd><span class="step">1.打印</span><span class="step">2.签署盖章</span><span class="step">3.扫描或拍照［最后一页］</span></dd>
												</dl>
											</div>
											<div class="edit_field">
												<dl>
													<dt>上传</dt>
													<dd><input type="file" class="file_1" name="agreenmentFile" id="agreenmentFile" /><span id="fileName">&nbsp;</span></dd>
													<dd><span class="tips">上传加盖企业公章的原件照片或扫描件<br />支持jpg，jpeg，png，gif格式照片，大小不超过2M</span></dd>
													<dd><span id="error_span" class="error"></span></dd>
												</dl>
											</div>
											</form>
										</div>
									</div>
								<!--基础配置 eof-->
				
								<!--编辑提交按钮 bof-->
								<div class="edit_btn">
									<p><input type="checkbox" id="ckb"/>我司已阅读并认可签署<a href="#">《合作用户协议》</a></p>
									<input type="button" value="提交审核" class="confirm_btn" id="next"/>
								</div>
								<!--编辑提交按钮 bof-->
							</s:else>
					</s:if>
					<s:else>
							<div class="edit_box edit_basic">
								<h1>协议认证信息</h1>
								<div class="edit_field">
									<dl>
										<dt>用户账号</dt>
										<dd><span class="txt">${user.email }</span></dd>
									</dl>
								</div>
								<div class="edit_field">
									<dl>
										<dt>用户认证</dt>
										<dd>
											<span class="mark">
													企业用户
											</span>
										</dd>
									</dl>
								</div>
								<div class="edit_field">
									<dl>
										<dt>协议认证</dt>
										<dd>
											<s:if test="security.status==1">
												<span class="mark mark_wait">待审核</span>
											</s:if>
											<s:elseif test="security.status==2">
												<span class="mark mark_done">待支付</span>
											</s:elseif>
											<s:elseif test="security.status==3">
												<span class="mark mark_cancel">审核不通过</span>
											</s:elseif>
											<s:elseif test="security.status==4">
												<span class="mark mark_done">已完成</span>
											</s:elseif>
											<s:elseif test="security.status==5">
												<span class="mark mark_cancel">锁定</span>
											</s:elseif>
											<s:elseif test="security.status==0">
												<span class="mark mark_cancel">已解除</span>
											</s:elseif>
										</dd>
									</dl>
								</div>
								<div class="edit_field">
									<dl>
										<dt>保证金金额</dt>
										<dd><span class="txt">￥${securityBalance.balance}</span></dd>
										<s:if test="security.status==2">
											<dd><span class="go" onclick="location.href='<%=path %>/pay/newOrder?accountType=<u:des3 value="1"/>'">去支付</span><span class="tips">点击跳转至系统支付选择页面，支付完成后自动开通</span></dd>
										</s:if>
										<s:if test="security.status==0">
											<s:if test="applyFor!=null">
												<dd><span class="refund">退款待审核</span></dd>
											</s:if>
										</s:if>
									</dl>
								</div>
								<s:if test="security.status==1">
									<div class="edit_field">
										<dl>
											<dt>&nbsp;</dt>
											<dd>
												<span class="tips">审核通过后请在此页面完成支付保证金流程。</span>
											</dd>
										</dl>
									</div>
								</s:if>
								<s:if test="applyFor!=null">
									<div class="edit_field">
										<dl>
											<dt>撤销日期</dt>
											<dd><span class="txt"><s:date name="applyFor.createDate" format="yyyy-MM-dd hh:mm:ss"/></span></dd>
										</dl>
									</div>
									<div class="edit_field">
										<dl>
											<dt>退款信息</dt>
											<dd><span class="txt">银行账户：${applyFor.banknum }</span></dd>
											<dd><span class="txt">开户行：${applyFor.bankaddr }</span></dd>
										</dl>
									</div>
								</s:if>
							</div>
					</s:else>
					<s:if test="security.status==4">
						<div class="edit_btn">
							<input type="button" class="confirm_btn" value="取消该服务" id="unfrzz"/>
						</div>
					</s:if>
			</div>
		</div>

		

		<!--右侧main bof-->

	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
	

	  <!--弹层（取消保证金服务） bof-->
	  <div class="background_box">&nbsp;</div>
	  <div class="float_box1 service_box" id="unbalance">
	    <div class="float_tit">
	      <h1>取消保证金服务</h1>
	    </div>
	    <div class="float_ctn">
	       <form method="post" name="unfzForm" id="unfzForm" action="<%=path %>/agreenment/unfreezeBalance">
		      <p><span class="tips">1、取消保证金服务通过审核后相关签署的协议自动失效，关联功能权限也将关闭</span><br /><span class="tips">2、保证金在7-15个工作日通过审核后将退至指定账户</span></p>
		      <div class="account">
		      	  <p>保证金退款至</p>
			      <p><input type="radio" name="type" checked="checked" id="yzxac"/>用户账户</p>
			      <p><input type="radio" name="type" class="bank" id="extac"/>银行账户</p>
			      <div class="float_field">
			        <dl>
			          <dt>银行账户</dt>
			          <dd><input type="text" name="bankNum" id="bankNum"/><span class="error" id="num_error" style="display:none">错误：不合法</span></dd>
			        </dl>
			      </div>
			      <div class="float_field">
			        <dl>
			          <dt>开户行</dt>
			          <dd><input type="text" name="bankAddr" id="bankAddr"/><span class="error" id="addr_error" style="display:none">错误：不合法</span></dd>	          
			        </dl>
			      </div>
		      </div>
		      <div class="float_btn">
		        <input type="hidden" name="unfreeze" id="unfreeze"/>
		        <input type="button" value="取消" class="cancel_btn" onclick="location.href=location.href"/>
		        <input type="button" value="确定" class="confirm_btn" id="acConfirm_btn"/>
		      </div>
	      </form>
	    </div>
	  </div>
	
	<script type="text/javascript">
	$(function(){
		//选择银行账户显示隐藏
	  		if($("input[type='radio'].bank").attr("checked")){
          	  $(".account .float_field").slideDown();
         	};

         	$("input[type='radio']").click(function(){
	            if($(this).hasClass("bank")){
	              $(".account .float_field").slideDown();
	            }else{
	              $(".account .float_field").slideUp();
	            }
         	});
          
			$("#agreenmentFile").change(function(){
				$(this).siblings(".file_txt").val(this.value);
			});
		
			$("#next").click(function(){
				var file = $("#agreenmentFile").val();
				$("#error_span").hide();
				if(file==""){
					$("#error_span").text("错误：请选择文件");
					$("#error_span").show();
					return;
				}
				var ckb = $("#ckb").attr("checked");
				if(ckb!="checked"){
					$("#error_span").text("错误：申请成为协议用户必须同意《企业申请合作用户协议》");
					$("#error_span").show();
					return;
				}
				$("#agreenFrm").submit();
			});
			
			$("#unfrzz").click(function(){
				var money = "${securityBalance.balance}";
				if(money==0){
					popupBox('提示','您当前的保证金为：特殊状态<br/>点击确定后完成取消服务','unfezz1()');
					return;
				}
				showBox();
			});
			
			$("#acConfirm_btn").click(function(){
				if(ck.rd()){
					unfrzz();
					$("#unfzForm").submit();
				}
			});
		});
		function showBox(){
			$(".background_box").show();
			$("#popup_box").hide();
			$("#unbalance").show();
		}
		function unfezz1(){
			unfrzz();
		}
		function unfrzz(){
			$.ajax({
				url:"<%=path%>/agreenment/relieveSecurity",
				type:"post",
				dataType: "text",
				success: function (data) {
					//参数true表示不从缓存中取数据
					window.location.reload(true);
		        },
		        error: function (msg) {
		        }
			});
		}
	
		var unfzForm = {
				bankNum:function(){
					var num = $("#bankNum").val();
					if(!verifyBankNum(num)){
						$("#num_error").show();
						return false;
					}
					$("#num_error").hide();
					return true;
				},
				bankAddr:function(){
					var addr = $("#bankAddr").val();
					if(addr==""){
						$("#addr_error").show();
						return false;
					}
					$("#addr_error").hide();
					return true;
				}
		};
		
		var ck = {
			rd:function(){
				var boo = false ;
				if($("#yzxac").attr("checked")=="checked"){
					$("#unfreeze").val("<u:des3 value='1' />");
					boo = true ;
				}else{
					$("#unfreeze").val("<u:des3 value='2' />");
					if(unfzForm.bankNum()&&unfzForm.bankAddr()){
						boo = true ;
					}
				}
				return boo;
			}
		};

	</script>
</body>
</html>