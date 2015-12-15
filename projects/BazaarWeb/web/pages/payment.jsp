<%@page import="classes.domain.Order"%>
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
    } else {
        request.setAttribute("orders", new Order[]{});
        request.setAttribute("orderCount", 0);
        request.setAttribute("cart", new Cart());
    }
%>

<t:MasterPageContent title="${title}">
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="script">
        <script>
            function GoNext(pagina) {
                $("#test1").load(pagina + "?toegang");
            }
            $(function () {
                $("#test1").load("payment0.jsp?toegang");
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${pageContext.session.getAttribute('payment_message') == 1}">
            <c:set var="paymentMessage"><fmt:message key="payment.message"/></c:set>
            <c:set var="payment_message" value="${0}" scope="session"></c:set>
        </c:if>
        <font style="color: red;">${paymentMessage}</font>
        <c:if test="${orderCount < 1}">
            <table width="100%">
                <tr>
                    <td align="center">
                        <i style="font-size:50pt;" class="glyphicon glyphicon-shopping-cart"></i>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <fmt:message key="payment.empty" />
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br/>
                        <a class="btn btn-info" href="cart.jsp"><fmt:message key="payment.gotocart"/></a>
                    </td>
                </tr>
            </table>
        </c:if>

        <c:if test="${orderCount > 0}">
            <input type="hidden" onload="GoNext('payment0.jsp')"/>
            <div class="col-md-12" id="test1">
            </div>
        </c:if>
    </jsp:body>
</t:MasterPageContent>
<%
    session.setAttribute("payment_message", 0);
%>