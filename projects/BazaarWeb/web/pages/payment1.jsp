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
    
    Object acc = request.getAttribute("account");
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
    
    <c:remove var="login_message" scope="session" />
</c:set>

<t:EmptyMasterPage title="${title}">
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:if test="${orderCount > 0}">
            <div class="col-md-10 col-md-offset-1">
                <hr/>
                <!--<h3 align="center"><fmt:message key="payment.customerlogin"/></h3>-->
            </div>

            <c:if test="${account != null}">
                <div class="col-md-12">
                    <h3>Welkom terug, 
                </div>
            </c:if>
            <c:if test="${account == null}">
                <div class="col-md-6">
                    <center><h4>Account aanmaken</h4></center>

                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <center>
                            <br/>
                            Als u nog geen account heeft aangemaakt op deze website dan kunt u die aanmaken via de onderstaande knop
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <button class="btn btn-info" onclick="GoNext('registration.jsp')">
                                Maak een nieuw account aan
                            </button>
                        </center>
                    </div>
                    <div class="col-md-3"></div>
                </div>
                <div class="col-md-6">
                    <center><h4>Inloggen</h4></center>
                    <br/>
                    <div class="col-md-8 col-md-offset-2">
                        <center>
                            Log hier in met uw account als u al eerder een bestelling heeft geplaatst of als u al op een eerder moment een account heeft aangemaakt op deze website
                        </center>
                        <form class="form-signin" role="form" action="${pageContext.servletContext.contextPath}/LoginServlet?payment" method="post" enctype="multipart/form-data">
                            <center><font style="color: red;">${login_message}</font></center>
                            <br/>
                            <input class="form-control" type="text" placeholder="Gebruikersnaam" name="Username"/>
                            <input class="form-control" type="password" placeholder="Wachtwoord" name="Password"/>
                            <button class="col-md-12 btn btn-info">Inloggen</button>
                        </form>

                    </div>
                </div>

            </c:if>
        </c:if>

        <div class="col-md-10 col-md-offset-1"><hr/></div>
        <div class="col-md-8"></div>
        <div class="col-md-4">
            <button class="btn btn-info col-md-5" onclick="GoNext('payment0.jsp')"><fmt:message key="payment.back"/></button>
            <div class="col-md-1"></div>
            <c:if test="${account == null}">
                <button class="btn btn-info col-md-5 disabled" onclick="GoNext('payment1.jsp')"><fmt:message key="payment.next"/></button>
            </c:if>
            <c:if test="${account != null}">
                <button class="btn btn-info col-md-5" onclick="GoNext('payment2.jsp')"><fmt:message key="payment.next"/></button>
            </c:if>
        </div>
    </jsp:body>
</t:EmptyMasterPage>