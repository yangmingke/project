<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" uri="/ucpaas-tags" %>
<%@ taglib prefix="cache" uri="http://www.opensymphony.com/oscache" %>
<s:set var="locale"><%=org.springframework.context.i18n.LocaleContextHolder.getLocale().toString()%></s:set>
<s:set var="lang"><%=org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage()%></s:set>
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/fullcalendar.css" />
<link rel="stylesheet" href="${ctx}/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/css/uniform.css" />
<link rel="stylesheet" href="${ctx}/css/select2.css" /> 

<script language="javascript" type="text/javascript">
if ((screen.height > 768) && (screen.width > 1280)) { 
  document.write("<link rel='stylesheet' type='text/css' href='${ctx}/css/matrix-style.css' />") 
} 
else { 
  document.write("<link rel='stylesheet' type='text/css' href='${ctx}/css/matrix-style-1024.css' />") 
} 
</script>
 <link rel="stylesheet" href="${ctx}/css/matrix-media.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-wysihtml5.css" />
<link href="${ctx}/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link rel="stylesheet" href="${ctx}/css/public.css" /> 

<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/public.js"></script>
<%--zTree树插件 --%>
<script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<link rel="stylesheet" href="${ctx}/js/zTree/css/zTreeStyle/zTreeStyle.css" />
<!-- 日期选择器 -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/datepicker/skin/WdatePicker.css" />


<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
<!-- Highcharts、Highstock图表-->
<script type="text/javascript" src="${ctx}/js/Highstock/highstock.js"></script>
<script type="text/javascript" src="${ctx}/js/Highstock/modules/exporting.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>


<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/jquery.ui.custom.js"></script> 
<script src="${ctx}/js/bootstrap.min.js"></script> 
<script src="${ctx}/js/jquery.uniform.js"></script> 
<script src="${ctx}/js/select2.min.js"></script>

<script src="${ctx}/js/validate/jquery.validate.min.js"></script>
<script src="${ctx}/js/validate/additional-methods.min.js"></script>
<script src="${ctx}/js/validate/ucpaas-methods.js"></script>
<script src="${ctx}/js/validate/messages_zh.min.js"></script>

<link rel="stylesheet" href="${ctx}/js/chosen/css/chosen.min.css" />
<script src="${ctx}/js/chosen/js/chosen.jquery.min.js"></script>