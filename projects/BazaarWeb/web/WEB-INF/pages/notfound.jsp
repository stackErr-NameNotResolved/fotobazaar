<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page isErrorPage="true"%>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="notfound.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <fmt:message key="notfound.message" /><br/>
        <a href="/BazaarWeb/index.jsp"><fmt:message key="notfound.link.home" /></a>
    </jsp:body>
</t:MasterPageContent>