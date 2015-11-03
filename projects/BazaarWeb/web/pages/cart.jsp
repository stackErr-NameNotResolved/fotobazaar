<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="login.title" /></c:set>


<t:MasterPageContent title="${title}">
    <jsp:body>
        <jsp:include page="/CartServletFragment"></jsp:include>
    </jsp:body>
</t:MasterPageContent>
