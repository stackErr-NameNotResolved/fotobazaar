<%@page import="classes.domain.Order"%>
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
        request.setAttribute("orders", new Order[]{});
        request.setAttribute("orderCount", 0);
        request.setAttribute("cart", new Cart());
    }
%>

<t:EmptyMasterPage title="${title}">
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

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
            <h2><fmt:message key="payment.check"/></h2>
            <br/>
            <br/>
            <br/>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th width="44%"><fmt:message key="cart.product"/></th>
                        <th width="9%"><fmt:message key="cart.productp"/></th>
                        <th width="9%"><fmt:message key="cart.fotop"/></th>
                        <th width="9%"><fmt:message key="cart.amount"/></th>
                        <th width="9%"><fmt:message key="cart.total"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orders}" var="order">
                        <c:set var="id">amount${order.getId()}</c:set>
                        <c:set var="place"><fmt:message key="cart.amount"/></c:set>
                        <c:set var="amount">${order.getAmount()}</c:set>

                            <tr>
                                <td style="vertical-align: middle">
                                ${order.getItem().toString()} + <fmt:message key="cart.foto"/>
                            </td>
                            <td style="vertical-align: middle">
                                € ${order.getItem().getPriceFormat()}
                            </td>
                            <td style="vertical-align: middle">
                                € ${order.getPicture().getPriceFormat()}
                            </td>
                            <td style="vertical-align: middle">
                                ${amount}
                            </td>
                            <td class="active" style="vertical-align: middle; text-align: right">
                                € <span id="total${order.getId()}">${order.getTotalPriceFormat()}</span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td  style="text-align: right">
                            <b><fmt:message key="cart.subtotal"/></b><br/>
                            <fmt:message key="cart.btw"/><br/>
                            <b><fmt:message key="cart.finaltotal"/></b>
                        </td>
                        <td class="active" style="text-align: right">
                            € <span id="subtotal">${cart.getTotalPriceFormat()}</span> <br/>
                            € <span id="vat">${cart.getBTWFormat(21)}</span></br>
                            € <span id="final_total">${cart.getTotalPriceAndBTWFormat(21)}</span>
                        </td>
                    </tr>
                </tfoot>
            </table>

            <br/>
            <br/>

            <div class="col-md-9"><fmt:message key="payment.youcangoback"/></div>
            <div class="col-md-3">
                <div class="col-md-2"></div>
                <a href="cart.jsp">
                    <div class="btn btn-info col-md-10">
                        <fmt:message key="payment.gobacktocart"/>
                    </div>
                </a>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>

            <div class="col-md-10 col-md-offset-1"><hr/></div>
            <div class="col-md-8"></div>
            <div class="col-md-4">
                <button class="btn btn-info disabled col-md-5" onclick="GoNext('payment0.jsp')">Vorige</button>
                <div class="col-md-1"></div>
                <button class="btn btn-info col-md-5" onclick="GoNext('payment1.jsp')">Volgende</button>
            </div>

        </c:if>
    </jsp:body>
</t:EmptyMasterPage>

