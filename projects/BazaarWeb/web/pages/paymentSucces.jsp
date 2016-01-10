<%@page import="classes.domain.OrderItem"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="payment.title" /></c:set>

<%
    Cart cart = Cart.readCartFromCookies(request);
    if (cart != null) {
        request.setAttribute("orders", cart.getOverview());
        request.setAttribute("orderCount", cart.getOverview().length);
        request.setAttribute("cart", cart);
        request.setAttribute("orders", new OrderItem[]{});
        request.setAttribute("orderCount", 0);
        request.setAttribute("cart", new Cart());
    } else {
        request.setAttribute("orders", new OrderItem[]{});
        request.setAttribute("orderCount", 0);
        request.setAttribute("cart", new Cart());
    }
%>

<t:MasterPageContent title="${title}">
    <jsp:body>
        <h1><fmt:message key="payment.success"></fmt:message></h1>
    </jsp:body>
</t:MasterPageContent>
