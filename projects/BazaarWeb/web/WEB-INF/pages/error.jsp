<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page isErrorPage="true"%>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="error.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <fmt:message key="error.message" /><br/>
        <a href="/BazaarWeb/"><fmt:message key="error.link.home" /></a>
    </jsp:body>
</t:MasterPageContent>