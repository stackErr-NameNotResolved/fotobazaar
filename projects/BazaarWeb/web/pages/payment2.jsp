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

    session.setAttribute("bankFlow", "choice");
%>

<t:EmptyMasterPage title="${title}">
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <hr/>
                <h3 align="center"><fmt:message key="payment.select"/></h3>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <table align="center">
                    <tr align="center">
                        <td>
                            <a href="#" style="text-decoration:none">
                                <img src="http://nitrographic.com/wp-content/themes/nitrographic/images/paypal-grey.png" width="150px" />
                            </a>
                        </td>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            <a href="paymentService.jsp" style="text-decoration:none">
                                <img src="https://static.webshopapp.com/shops/084012/files/035267280/ideal-logo-png.png" width="75px" />
                            </a>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="30%">
                            <a href="#" style="text-decoration:none;">
                                <h4><b><fmt:message key="payment.paypal"/></b></h4>
                            </a>
                        </td>
                        <td width="30%">
                            &nbsp;
                        </td>
                        <td width="40%">
                            <a href="paymentProcess.jsp" style="text-decoration:none;">
                                <h4><b><fmt:message key="payment.ideal"/></b></h4>
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="col-md-10 col-md-offset-1"><hr/></div>
        <div class="col-md-8"></div>
        <div class="col-md-4">
            <button class="btn btn-info col-md-5" onclick="GoNext('payment1.jsp')"><fmt:message key="payment.back"/></button>
            <div class="col-md-1"></div>
            <button class="btn btn-info col-md-5 disabled" onclick="GoNext('paymentProcess.jsp')"><fmt:message key="payment.next"/></button>
        </div>
    </jsp:body>
</t:EmptyMasterPage>