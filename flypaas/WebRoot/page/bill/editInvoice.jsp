<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>开具发票</title>
<%@include file="/page/resource.jsp"%>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/page/head.jsp"%>
<!--公共头部header eof--> 

<!--公共导航菜单 bof-->
<%@include file="/page/menu.jsp"%>
<!--公共导航菜单 eof--> 

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="main_tit">
        <h1>开具发票</h1>
      </div>
      <div class="main_ctn">
        <div class="invoice_edit">
          <p><label>可索取发票总额</label><span class="money">${invoiceMoney }</span> 元</p>
          <form action="/bill/modifyInvoice" method="post" name="invoiceForm" id="invoiceForm" class="app_form" enctype="multipart/form-data">
            <input type="hidden" name="invoiceId" value="<u:des3 value='${invoice.id }'/>" />
            <input type="hidden" name="invoiceMoney" value="${invoiceMoney }" />
            <div class="select_box invoice_unit">
              <label>开具类型</label>
                  <s:if test="user.userType==2">
                  	企业
                  </s:if>
                  <s:else>
                  	个人
                  </s:else>
                  <input type="hidden" value="${user.userType }" name="invoice.opentype" />
            </div>
            <div class="clear"></div>
            <p><label>开具发票金额</label><input type="text" id="money" name="invoice.money" value="${invoice.money}" /> 元&nbsp;&nbsp;<span class="error" id="moneyError" style="display:none"></span></p>
            <p><label>发票抬头信息</label><input type="text" id="title" name="invoice.title"  value="${invoice.title}"/> <span class="error" id="titleError" style="display:none"></span></p>
            <div class="select_box invoice_type">
              <label>发票类型</label>
              <div class="select">
              <span>
              	<s:if test="invoice.type==1">增值税专用发票</s:if>
              	<s:else>
              		增值税普通发票
              	</s:else>
              </span>
              <input type="hidden"name="invoice.type" id="type" value="${invoice.type}"/>
                <ul style="display:none;">
                  <s:if test="user.userType==2">
                  <li onclick="setType('1')" <s:if test="invoice.type==1"> class="selected"</s:if>>增值税专用发票</li>
                  </s:if>
                  <li onclick="setType('2')" <s:if test="invoice.type==2"> class="selected"</s:if>>增值税普通发票</li>
                </ul>
              </div>
            </div>
            <div class="clear">&nbsp;</div>
            <s:if test="user.userType==2">
            	<div id="zhp" <s:if test="invoice.type==2">style="display:none"</s:if>>
		            <div class="clear"></div>
		            <p><label>纳税人识别号</label><input type="text" id="identificationnum" name="invoice.identificationnum"  value="${invoice.identificationnum}"/> <span class="error" id="idenError" style="display:none"></span></p>
		            <div class="file_box">
		              <label>一般纳税人资格认证</label><input type="file" id="identificationimg" name="identificationimg" class="file_1" /><span class="error" id="idenImgError" style="display:none"></span>
		            </div>
		            <div class="clear"></div>
		            <p><label>银行账号</label><input type="text" id="bankaccount" name="invoice.bankaccount" <s:if test="invoice.type==1">value="${invoice.bankaccount}"</s:if>/> <span class="error" id="bankacctError" style="display:none"></span></p>
		            <p><label>开户行</label><input type="text" id="bankaddr" name="invoice.bankaddr" value="${invoice.bankaddr}"/> <span class="error" id="bankaddrError" style="display:none"></span></p>
	            </div>
	       </s:if>
	            <hr class="hr" />
	            <div class="select_box">
	              <label>邮寄地址</label>
	              <div class="select parent">
	              <span>${addr.provinceName }</span>
	              <input type="hidden" name="addr.province" id="pov" value="${addr.province }"/>
	                <ul style="display:none;">
	                  <s:if test="provinceList!=null">
	                  	<s:iterator value="provinceList" var="pov">
	                  		<li onclick="getCitys('${pov.id}')">${pov.name }</li>
	                  	</s:iterator>
	                  </s:if>
	                </ul>
	              </div>
	              <div class="select child"><span>${addr.cityName }</span><input type="hidden" value="${addr.city }" name="addr.city" id="city"/>
	                <ul style="display:none;" id="ulCity">
	                </ul>
	              </div>
	              <span class="error" id="addrError" style="display:none"></span>
	              <span class="tips">目前只支持中国大陆的邮寄</span>
	            </div>
	            <div class="clear"></div>
            <p>
              <label>街道详细地址</label><input type="text" id="street" name="addr.street" value="${addr.street }"/> <span class="error" id="streetError" style="display:none"></span>
              <span class="tips">请填写邮寄的街道地址，如：“南山区科技园高新南四道8号创维半导体设计大厦东座18楼”</span>
            </p>
            <p><label>邮政编码</label><input type="text" id="postnum" name="addr.postnum" value="${addr.postnum }"/> <span class="error" id="postnumError" style="display:none"></span></p>
            <p><label>联系人</label><input type="text" id="contacts" name="addr.contacts" value="${addr.contacts }"/> <span class="error" id="contactsError" style="display:none"></span></p>
            <p><label>联系电话</label><input type="text" id="contactmobile" name="addr.contactmobile" value="${addr.contactmobile}"/> <span class="error" id="mobileError" style="display:none"></span></p>
            <p class="btn" id="btn">
              <input type="submit" value="修 改" class="blue_btn" />
              <input type="button" value="取 消" onclick="history.go(-1)" class="grey_btn" />
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">
	
	$(function(){
		$(".child p").eq(2).show().siblings("p").hide();
		$("#menu_3_4").addClass("active");
		
		$(".file_1").change(function(){
			$(this).siblings("input[name='file_txt']").val($(this).val());
		});
		
		$("#invoiceForm").submit(function(){
			var title=$("#title").val();
			var identificationimg = $("#identificationimg").val();
			var bankaddr = $("#bankaddr").val();
			var pov = $("#pov").val();
			var city = $("#city").val();
			var contacts = $("#contacts").val();
			var money = $("#money").val();
			var feeMoney = ${invoiceMoney};
			var identificationnum = $("#identificationnum").val();
			var bankaccount = $("#bankaccount").val();
			var postnum = $("#postnum").val();
			var contactmobile = $("#contactmobile").val();
			var street = $("#street").val();
			if(!verfiyMoney(money)){
				$("#moneyError").text("金额输入错误，请检查后重试.");
				$("#moneyError").show();
				return false;
			}else if(money>feeMoney){
				$("#moneyError").text("可开发票余额不足，请检查后重试.");
				$("#moneyError").show();
				return false;
			}else if(money<100){
				$("#moneyError").text("发票最低开票限额是100元，请检查后重试.");
				$("#moneyError").show();
				return false;
			}else{
				$("#moneyError").hide();
			}
			if(title==""){
				$("#titleError").text("发票抬头不能为空");
				$("#titleError").show();
				return false;
			}else{
				$("#titleError").hide();
			}
			var type = $("#type").val();
			if(type=="1"){
				if(!verifyIdentificationnum(identificationnum)){
					$("#idenError").text("纳税人识别号输入错误，请检查后重试.");
					$("#idenError").show();
					return false;
				}else{
					$("#idenError").hide();
				}
				if(identificationimg==""){
					$("#idenImgError").text("资格认证图片不能为空");
					$("#idenImgError").show();
					return false;
				}else{
					$("#idenImgError").hide();
				}
				if(!verifyBankNum(bankaccount)){
					$("#bankacctError").text("银行卡号输入错误，请检查后重试.");
					$("#bankacctError").show();
					return false;
				}else{
					$("#bankacctError").hide();
				}
				if(bankaddr==""){
					$("#bankaddrError").text("开户地址不能为空");
					$("#bankaddrError").show();
					return false;
				}else{
					$("#bankaddrError").hide();
				}
			}
			if(pov=="0"||city=="0"){
				$("#addrError").text("请选择邮寄的省市");
				$("#addrError").show();
				return false;
			}else{
				$("#addrError").hide();
			}
			if(street==""){
				$("#streetError").text("邮编输入错误，请检查后重试.");
				$("#streetError").show();
				return false;
			}else{
				$("#streetError").hide();
			}
			if(!verifyPostNum(postnum)){
				$("#postnumError").text("邮编输入错误，请检查后重试.");
				$("#postnumError").show();
				return false;
			}else{
				$("#postnumError").hide();
			}
			if(contacts==""){
				$("#contactsError").text("联系人不能为空");
				$("#contactsError").show();
				return false;
			}else{
				$("#contactsError").hide();
			}
			if(!verifyMobileAndFixPhone(contactmobile)){
				$("#mobileError").text("联系人号码输入错误，请检查后重试.");
				$("#mobileError").show();
				return false;
			}else{
				$("#mobileError").hide();
			}
			return true;
		});
	});
	
	function getCitys(povid){
		$("#pov").val(povid);
		$.ajax({
			url:"<%=path%>/bill/printCitys",
			type:"post",
			data:"province.id="+povid,
			dataType: "text",
			success: function (data) {
	         	var htmlStr ="" ;
	         	var obj =  eval('(' + data + ')');
	         	for(i=0;i<obj.length;i++){
	         		htmlStr = htmlStr+"<li onclick='setCityId(\""+obj[i].id+"\",\""+obj[i].name+"\")'>"+obj[i].name+"</li>";
	         	}
	         	$("#ulCity").empty();
	         	$("#ulCity").append(htmlStr);
	        }
		});
	}
	function setCityId(cityId,name){
		$("#city").val(cityId);
		$("#city").siblings("span").text(name);
	}
	function setType(type){
		$("#type").val(type);
		if(type=="1"){
			$("#zhp").show();	
		}else{
			$("#zhp").hide();
			clearValue();
		}
			
	}
	function clearValue(){
		$("#identificationimg").val("");
		$("#identificationnum").val("");
		$("#bankaccount").val("");
		$("#bankaddr").val("");
	}
</script> 


<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp"%>
<!--公共底部footer bof--> 

</body>
</html>
