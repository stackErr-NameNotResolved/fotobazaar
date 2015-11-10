<%@page import="classes.servlets.fragments.CartServletFragment"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="cart.title" /></c:set>

<c:set var="page">
    <%=CartServletFragment.generateCart(request, response)%>
</c:set>

<t:MasterPageContent title="${title}">
    
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>
        
    <jsp:body>
        <!--<jsp:include page="/CartServletFragment"></jsp:include>-->
        ${page}
    </jsp:body>
</t:MasterPageContent>
