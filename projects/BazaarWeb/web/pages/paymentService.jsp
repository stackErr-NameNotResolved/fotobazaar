<%@page import="classes.domain.Order"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/pages/langInclude.jsp" %>
<link rel="stylesheet" href="https://mijn.ing.nl/internetbankieren/css/ses_style_v7.css">
<html>
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
    <c:set var="buttontext"><fmt:message key="login.button.submit"/></c:set>
        <body>
        <c:if test="${pageContext.session.getAttribute('bankFlow') == 'choice'}">
            <div id="header-top">
                <img src="https://static.webshopapp.com/shops/084012/files/035267280/ideal-logo-png.png" width="75px" />
            </div>
            <form role="form" action="../PaymentServlet" method="POST">
                <select name="bankChoice">
                    <option>ABN Amro</option>
                    <option>ING</option>
                    <option>Rabobank</option>
                    <option>SNS Bank</option>
                </select>
                <input type="submit" value="${buttontext}">
            </form>
        </c:if>
        <c:if test="${pageContext.session.getAttribute('bankFlow') == 'login'}">
            <div class="header">
                <div class="header-top">
                    <a href="https://www.ing.nl/?bid=0035_Adm_Rest_Mass_inte_Mijn_ING_inlogpagina_logo_Link">
                        <img id="ing-logo" src="https://mijn.ing.nl/internetbankieren/gfx/SES_logo_ing.gif" alt="ING Logo" height="32" Width="128"/>
                    </a>

                    <div class="header-menu">
                        <a href="https://www.ing.nl/particulier/klantenservice/index.html" target="_blank" title="Klantenservice" >Klantenservice</a>
                        <a href="https://www.ing.nl/particulier/klantenservice/contact/index.html" target="_blank" title="Contact" >Contact</a>
                    </div>
                </div>
            </div>
            <div class="content">
                <form id="login" name="login" role="form" action="../PaymentServlet" method="POST">
                    <font style="color: red;">${login_message}</font>
                    <h2><fmt:message key="bank.login.header"/></h2>
                    <c:set var="username"><fmt:message key="login.placeholder.username" /></c:set>
                    <input type="text" placeholder="${username}" name="username" autofocus>
                    <c:set var="password"><fmt:message key="login.placeholder.password" /></c:set>
                    <input type="password" placeholder="${password}" name="password">
                    <input type="submit" value="${buttontext}">
                </form>
            </div>
        </c:if>
        <c:if test="${pageContext.session.getAttribute('bankFlow') == 'pay'}">
            <h2>Totaalbedrag: ${cart.getTotalPriceAndBTWFormat(21)}</h2>
            <form action="../PaymentServlet" method="POST">
                <input type="hidden" name="amount" value="${cart.getTotalPriceAndBTWFormat(21)}">
                <input type="hidden" name="pay" value="confirm">
                <input type="submit" value="${buttontext}">
            </form>
        </c:if>
    <body>
</html>

