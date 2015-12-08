<%@page import="classes.domain.Order"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="bank.login.title" /></c:set>

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

<c:set var="login_message">
    <c:choose>
        <c:when test="${pageContext.session.getAttribute('login_message') == 1}">
            <fmt:message key="login.response.invalid"/>
        </c:when>
        <c:when test="${pageContext.session.getAttribute('login_message') == 2}">
            <fmt:message key="login.response.disabled"/>
        </c:when>
        <c:when test="${pageContext.session.getAttribute('login_message') == 3}">
            <fmt:message key="login.response.empty"/>
        </c:when>
    </c:choose>
</c:set>


<t:MasterPageContent title="${title}">
    <jsp:body>
        <c:if test="${pageContext.session.getAttribute('bank_confirmed') == false}">
            <div class="login-bg">
                <div class="container">
                    <form class="form-signin" role="form" action="../PaymentServlet" method="POST">
                        <div class="login-wrap">
                            <font style="color: red;">${login_message}</font>
                            <h2 class="form-signin-heading"><fmt:message key="bank.login.header"/></h2>
                            <c:set var="username"><fmt:message key="login.placeholder.username" /></c:set>
                            <input type="text" class="form-control" placeholder="${username}" name="Username" autofocus>
                            <c:set var="password"><fmt:message key="login.placeholder.password" /></c:set>
                            <input type="password" class="form-control" placeholder="${password}" name="Password">
                            <c:set var="buttontext"><fmt:message key="login.button.submit"/></c:set>
                            <input type="submit" class="btn btn-lg btn-login btn-block" value="${buttontext}">
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${pageContext.session.getAttribute('bank_confirmed') == true}">
            <h2>Totaalbedrag: ${cart.getTotalPriceAndBTWFormat(21)}</h2>
            <form action="../PaymentServlet" method="POST">
                <input type="hidden" name="amount" value="${cart.getTotalPriceAndBTWFormat(21)}">
                <input type="submit" value="Pay">
            </form>
        </c:if>
    </jsp:body>
</t:MasterPageContent>

