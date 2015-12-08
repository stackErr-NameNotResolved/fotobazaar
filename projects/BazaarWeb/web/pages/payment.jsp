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

            <div class="col-md-1"></div>
            <div class="col-md-10">
                <hr/>
                <h3 align="center"><fmt:message key="payment.select"/></h3>

            </div>
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
                        <a href="#" style="text-decoration:none">
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
                        <a href="#" style="text-decoration:none;">
                            <h4><b><fmt:message key="payment.ideal"/></b></h4>
                        </a>
                    </td>
                </tr>
            </table>
        </c:if>
    </jsp:body>
</t:MasterPageContent>

