<%@page import="classes.domain.Picture"%>
<%@page import="classes.domain.Item"%>
<%@page import="classes.domain.Order"%>
<%@page import="classes.servlets.fragments.CartServletFragment"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="cart.title" /></c:set>

<%
    Cart cart = Cart.readCartFromCookies(request);
    if (cart != null) {
        request.setAttribute("orders", cart.getOverview());
        request.setAttribute("orderCount", cart.getOverview().length);
    } else {
        request.setAttribute("orders", new Order[]{});
        request.setAttribute("orderCount", 0);
    }

    request.setAttribute("cart", cart);
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
                        <fmt:message key="cart.empty" />
                    </td>
                </tr>
            </table>
        </c:if>

        <c:if test="${orderCount > 0}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th width="44%"><fmt:message key="cart.product"/></th>
                        <th width="9%"><fmt:message key="cart.productp"/></th>
                        <th width="9%"><fmt:message key="cart.fotop"/></th>
                        <th width="9%"><fmt:message key="cart.amount"/></th>
                        <th width="9%"><fmt:message key="cart.total"/></th>
                        <th width="10%"></th>
                        <th width="10%"></th>
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
                                <input class="form-control input-sm" id="${id}" placeholder="${place}" type="number" min="1" value="${amount}" />
                            </td>
                            <td class="active" style="text-align: right; vertical-align: middle">
                                € ${order.getTotalPriceFormat()}
                            </td>
                            <td>
                                <button type="submit" class="btn btn-primary col-md-12" style="font-size:8pt; vertical-align: middle">
                                    <i class="glyphicon glyphicon-edit" style="color: white;"></i>&nbsp;&nbsp;&nbsp;<fmt:message key="cart.edit"/>
                                </button>
                            </td>
                            <td>
                                <form action="/BazaarWeb/CartServletFragment" method="POST">
                                    <input type="hidden" value="${order.getId()}" name="id" id="id"></input>
                                    <button type="submit" class="btn btn-danger col-md-12" style="font-size:8pt; vertical-align: middle">
                                        <i class="glyphicon glyphicon-remove" style="color: white;"></i>&nbsp;&nbsp;&nbsp;<fmt:message key="cart.delete"/>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <b><fmt:message key="cart.subtotal"/></b><br/>
                            <fmt:message key="cart.btw"/><br/>
                            <b><fmt:message key="cart.finaltotal"/></b>
                        </td>
                        <td class="active" style="text-align: right">
                            € ${cart.getTotalPriceFormat()} <br/>
                            € ${cart.getBTWFormat(21)}</br>
                            € ${cart.getTotalPriceAndBTWFormat(21)}
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </tfoot>
            </table>

            <br/>
            <br/>

            <div class="col-md-9"><fmt:message key="cart.empty"/></div>
            <div class="col-md-3">
                <div class="col-md-2"></div>
                <a href="#" onclick="$(this).closest('form').submit()">
                    <div class="btn btn-info col-md-4">
                        <fmt:message key="cart.save"/>
                    </div>
                </a>
                <div class="col-md-1"></div>
                <a href="payment.jsp">
                    <div class="btn btn-info col-md-4">
                        <fmt:message key="cart.pay"/>
                    </div>
                </a>
            </div>
        </c:if>
    </jsp:body>
</t:MasterPageContent>
