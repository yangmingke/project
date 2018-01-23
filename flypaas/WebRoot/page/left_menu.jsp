	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<div class="menu">
				<div class="tab">
					<ul>
						<li id="m-01">
							<a href="javascript:void(0)">
								<i class="i1">&nbsp;</i>
								控制台
							</a>
						</li>
						<li id="m-02">
							<a href="javascript:void(0)">
								<i class="i2">&nbsp;</i>
								应用管理
							</a>
						</li>
						<li id="m-03">
							<a href="javascript:void(0)">
								<i class="i3">&nbsp;</i>
								财务管理
							</a>
						</li>
						<li id="m-04">
							<a href="javascript:void(0)">
								<i class="i4">&nbsp;</i>
								开发者信息
							</a>
						</li>
					</ul>
				</div>
				<div class="tab_ctn">
					<dl>
						<dd id="m-011"><a href="<%=path %>/user/account"><i class="i11">&nbsp;</i>控制台</a></dd>
						<dd id="m-012"><a href="<%=path %>/user/querySessionPacketLoss"><i class="i12">&nbsp;</i>会话丢包</a></dd>
					</dl>
					<dl style="display:none;">
						<dd id="m-021"><a href="<%=path %>/app/appManager"><i class="i21">&nbsp;</i>应用列表</a></dd>
						<c:if test="${user.superSid == null}">
							<dd id="m-025"><a href="<%=path %>/app/testMobile"><i class="i25">&nbsp;</i>测试号码白名单</a></dd>
							<dd id="m-022"><a href="<%=path %>/app/testDemo"><i class="i22">&nbsp;</i>测试DEMO</a></dd>
						</c:if>
						<%-- <dd id="m-023"><a href="<%=path %>/app/smsTemplate/query"><i class="i23">&nbsp;</i>短信管理</a></dd>
						<dd id="m-024"><a href="<%=path %>/app/ring/query"><i class="i24">&nbsp;</i>语音库管理</a></dd> --%>
					</dl>
					<dl style="display:none;">
						<dd id="m-031"><a href="<%=path %>/finance/index"><i class="i31">&nbsp;</i>财务总览</a></dd>
						<dd id="m-032"><a href="<%=path %>/bill/chargeList"><i class="i32">&nbsp;</i>账户充值</a></dd>
						<%-- <dd id="m-033"><a href="<%=path %>/bill/bill"><i class="i33">&nbsp;</i>消费账单</a></dd> --%>
						<dd id="m-033"><a href="<%=path %>/bill/feeTime"><i class="i33">&nbsp;</i>消费记录</a></dd>
						<dd id="m-034"><a href="<%=path %>/bill/invoice"><i class="i34">&nbsp;</i>发票管理</a></dd>
					</dl>
					<dl style="display:none;">
						<dd id="m-041"><a href="<%=path %>/user/userCenter"><i class="i41">&nbsp;</i>基础信息</a></dd>
						<dd id="m-042"><a href="<%=path %>/user/oAuthDispather"><i class="i42">&nbsp;</i>账号认证</a></dd>
						<dd id="m-043"><a href="<%=path %>/agreenment/addAgreenmetPage"><i class="i43">&nbsp;</i>协议认证</a></dd>
					</dl>
				</div>
			</div>
			<script type="text/javascript">
				var bid = $("body").attr("id");
				var menus = bid.split("-");
				var mainMn = menus[0];
				var sonMn = menus[1];
				$("#m-"+mainMn).addClass("current");
				$("#m-"+mainMn+sonMn).parent("dl").show().siblings("dl").hide();
				$("#m-"+mainMn+sonMn).addClass("current").parent("dl").siblings("dl").find("dd").removeClass("current");
			</script>