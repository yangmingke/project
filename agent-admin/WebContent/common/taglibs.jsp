<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<%@ taglib prefix="cache" uri="http://www.opensymphony.com/oscache" %>


<s:set var="locale"><%=org.springframework.context.i18n.LocaleContextHolder.getLocale().toString()%></s:set>
<s:set var="lang"><%=org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage()%></s:set>