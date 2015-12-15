<%@page import="classes.domain.OrderItem"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="payment.title" /></c:set>

<%
    if (request.getParameter("toegang") == null) {
        response.sendRedirect("payment.jsp");
    }
    Cart cart = Cart.readCartFromCookies(request);
    if (cart != null) {
        request.setAttribute("orders", cart.getOverview());
        request.setAttribute("orderCount", cart.getOverview().length);
        request.setAttribute("cart", cart);
    } else {
        request.setAttribute("orders", new OrderItem[]{});
        request.setAttribute("orderCount", 0);
        request.setAttribute("cart", new Cart());
    }


%>

<t:EmptyMasterPage title="${title}">
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:if test="${orderCount > 0}">
            <div class="col-md-10 col-md-offset-1">
                <hr/>
                <h3 align="center"><fmt:message key="payment.customerlogin"/></h3>
            </div>

            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>


        </c:if>

        <div class="col-md-10 col-md-offset-1"><hr/></div>
        <div class="col-md-8"></div>
        <div class="col-md-4">
            <button class="btn btn-info col-md-5" onclick="GoNext('payment0.jsp')"><fmt:message key="payment.back"/></button>
            <div class="col-md-1"></div>
            <button class="btn btn-info col-md-5" onclick="GoNext('payment2.jsp')"><fmt:message key="payment.next"/></button>
        </div>
    </jsp:body>
</t:EmptyMasterPage>