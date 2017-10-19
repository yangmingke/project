<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>资费查询</title>
<%@include file="/front/resource.jsp"%>
<script type="text/javascript" src="<%=path%>/js/inputControl.js"></script>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp"%>
<!--公共头部header eof--> 

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="main_tit">
        <h1>资费查询</h1>
      </div>
   
      <div class="main_ctn fee_ctn">
        <div class="main_top">
          <p class="tips">( 国际长途请输入00+国家区号查询 )</p>
             <s:form namespace="/user" action="feeRate" id="feeRateFrm" theme="simple">
            <ul>
              <li>
                <input type="text" placeholder="请输入您要查询国家的区号或国家" id="prefix" name="prefix" value="${prefix}"/>
                <input type="submit" value="查 询" class="search" id="searchRate" />
                <input type="hidden" name="countryNum" id="countryNum" value="${countryNum}"/>
              </li>
            </ul>
            </s:form>
        </div>
        <h2>常用费率<span>请以该国家（或地区）英文名的首字母为标准（如中国，先“C”）</span></h2>
        <s:set var="str">${countryNum}</s:set>
        
        <p class="choose">完整费率：
        <a href="javascript:void(0)" onclick="namePrefix('A')" <s:if test="countryNum==\"A\"">style="text-decoration:underline;"</s:if>>A</a>
        <a href="javascript:void(0)" onclick="namePrefix('b')" <s:if test="countryNum==\"b\"">style="text-decoration:underline;"</s:if>>B</a>
        <a href="javascript:void(0)" onclick="namePrefix('c')" <s:if test="countryNum==\"c\"">style="text-decoration:underline;"</s:if>>C</a>
        <a href="javascript:void(0)" onclick="namePrefix('d')" <s:if test="countryNum==\"d\"">style="text-decoration:underline;"</s:if>>D</a>
        <a href="javascript:void(0)" onclick="namePrefix('e')" <s:if test="countryNum==\"e\"">style="text-decoration:underline;"</s:if>>E</a>
        <a href="javascript:void(0)" onclick="namePrefix('f')" <s:if test="countryNum==\"f\"">style="text-decoration:underline;"</s:if>>F</a>
        <a href="javascript:void(0)" onclick="namePrefix('g')" <s:if test="countryNum==\"g\"">style="text-decoration:underline;"</s:if>>G</a>
        <a href="javascript:void(0)" onclick="namePrefix('h')" <s:if test="countryNum==\"h\"">style="text-decoration:underline;"</s:if>>H</a>
        <a href="javascript:void(0)" onclick="namePrefix('i')" <s:if test="countryNum==\"i\"">style="text-decoration:underline;"</s:if>>I</a>
        <a href="javascript:void(0)" onclick="namePrefix('j')" <s:if test="countryNum==\"j\"">style="text-decoration:underline;"</s:if>>J</a>
        <a href="javascript:void(0)" onclick="namePrefix('k')" <s:if test="countryNum==\"k\"">style="text-decoration:underline;"</s:if>>K</a>
        <a href="javascript:void(0)" onclick="namePrefix('l')" <s:if test="countryNum==\"l\"">style="text-decoration:underline;"</s:if>>L</a>
        <a href="javascript:void(0)" onclick="namePrefix('m')" <s:if test="countryNum==\"m\"">style="text-decoration:underline;"</s:if>>M</a>
        <a href="javascript:void(0)" onclick="namePrefix('n')" <s:if test="countryNum==\"n\"">style="text-decoration:underline;"</s:if>>N</a>
        <a href="javascript:void(0)" onclick="namePrefix('o')" <s:if test="countryNum==\"o\"">style="text-decoration:underline;"</s:if>>O</a>
        <a href="javascript:void(0)" onclick="namePrefix('p')" <s:if test="countryNum==\"p\"">style="text-decoration:underline;"</s:if>>P</a>
        <a href="javascript:void(0)" onclick="namePrefix('q')" <s:if test="countryNum==\"q\"">style="text-decoration:underline;"</s:if>>Q</a>
        <a href="javascript:void(0)" onclick="namePrefix('r')" <s:if test="countryNum==\"r\"">style="text-decoration:underline;"</s:if>>R</a>
        <a href="javascript:void(0)" onclick="namePrefix('s')" <s:if test="countryNum==\"s\"">style="text-decoration:underline;"</s:if>>S</a>
        <a href="javascript:void(0)" onclick="namePrefix('t')" <s:if test="countryNum==\"t\"">style="text-decoration:underline;"</s:if>>T</a>
        <a href="javascript:void(0)" onclick="namePrefix('u')" <s:if test="countryNum==\"u\"">style="text-decoration:underline;"</s:if>>U</a>
        <a href="javascript:void(0)" onclick="namePrefix('v')" <s:if test="countryNum==\"v\"">style="text-decoration:underline;"</s:if>>V</a>
        <a href="javascript:void(0)" onclick="namePrefix('w')" <s:if test="countryNum==\"w\"">style="text-decoration:underline;"</s:if>>W</a>
        <a href="javascript:void(0)" onclick="namePrefix('x')" <s:if test="countryNum==\"x\"">style="text-decoration:underline;"</s:if>>X</a>
        <a href="javascript:void(0)" onclick="namePrefix('y')" <s:if test="countryNum==\"y\"">style="text-decoration:underline;"</s:if>>Y</a>
        <a href="javascript:void(0)" onclick="namePrefix('z')" <s:if test="countryNum==\"z\"">style="text-decoration:underline;"</s:if>>Z</a>
        </p>
        <div class="table_ctn">          
          <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
              <tr id="first">
                <th>国家（或区域）代码</th>
                <th>国家地区</th>
                <th>快传标准资费</th>
              </tr>
              <s:if test="page.list!=null">
              	<s:iterator value="page.list" id="r">
              			<tr>
              			<td><s:property value="#r.prefix"/></td>
              			<td><s:property value="#r.areaName"/></td>
              			<td>￥<s:property value="#r.unitFee"/>元/分钟</td>
              			</tr>
              	</s:iterator>
              </s:if>
            </tbody>
          </table>
          <u:page page="${page}" formId="feeRateFrm"></u:page>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	  $(function(){
		  $("#menu_1_2").addClass("active");
    	  $(".child p").eq(0).show().siblings("p").hide();
    	  
		  $("#searchRate").click(function(){
			  $("#feeRateFrm").submit();
		  });
// 		  rateListByName("","");
	  });
	  
	  function namePrefix(value){
		  $("#countryNum").val(value);
		  $("#feeRateFrm").submit();
	  }
//       function rateListByName(namePrefix,prefix){
//     	  $("#first").siblings("tr").remove();
//     	  $.ajax({
<%--   			url:"<%=path%>/user/feeRate", --%>
//   			type:"post",
//   			data:"namePrefix="+namePrefix+"&prefix="+prefix,
//   			dataType: "text",
//   			success: function (data) {
//   				var jsonStr = eval("("+data+")");
//   				var len = jsonStr.length;
//   				var str = "" ;
//   				if(jsonStr!=null && jsonStr!="" && jsonStr!="null"){
//       				for(i=0;i<len;i++){
//       					str = str+"<tr><td>"+jsonStr[i].prefix+"</td><td>"+jsonStr[i].areaName+"</td><td>￥"+jsonStr[i].unitFee+"元/分钟</td></tr>";
//       				}
//   					$("#first").after(str);
//   				}
//             },
//             error: function (msg) {
//             }
//   		});
//       } 
       
</script> 
<!--弹层(删除操作) eof--> 

<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp"%>
<!--公共底部footer bof-->

</body>
</html>
