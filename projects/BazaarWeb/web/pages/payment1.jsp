<%@page import="classes.domain.EGender"%>
<%@page import="classes.domain.Customer"%>
<%@page import="classes.domain.models.Account"%>
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

    request.setAttribute("initials_error", session.getAttribute("initials_error"));
    session.removeAttribute("initials_error");
    request.setAttribute("lastname_error", session.getAttribute("lastname_error"));
    session.removeAttribute("lastname_error");
    request.setAttribute("gender_error", session.getAttribute("gender_error"));
    session.removeAttribute("gender_error");
    request.setAttribute("address_error", session.getAttribute("address_error"));
    session.removeAttribute("address_error");
    request.setAttribute("number_error", session.getAttribute("number_error"));
    session.removeAttribute("number_error");
    request.setAttribute("zip_error", session.getAttribute("zip_error"));
    session.removeAttribute("zip_error");
    request.setAttribute("city_error", session.getAttribute("city_error"));
    session.removeAttribute("city_error");
    request.setAttribute("country_error", session.getAttribute("country_error"));
    session.removeAttribute("country_error");
    request.setAttribute("email_error", session.getAttribute("email_error"));
    session.removeAttribute("email_error");

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

    Account acc = (Account) session.getAttribute("account");
    if (acc != null) {
        Customer c = Customer.fromUsername(acc.getUsername());
        request.setAttribute("customer", c);
        
        request.setAttribute("gender_m", c.getGender() == EGender.MALE ? "checked" : "");
        request.setAttribute("gender_f", c.getGender() == EGender.FEMALE ? "checked" : "");
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

    <c:remove var="login_message" scope="session" />
</c:set>

<t:EmptyMasterPage title="${title}">
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/custom.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:set var="ph_username"><fmt:message key="login.placeholder.username"/></c:set>
        <c:set var="ph_password"><fmt:message key="login.placeholder.password"/></c:set>
        <c:if test="${orderCount > 0}">
            <div class="col-md-10 col-md-offset-1">
                <hr/>
            </div>

            <c:if test="${account != null}">
                <form role="form" action="${pageContext.servletContext.contextPath}/CustomerServlet" method="GET">
                    <div class="col-md-10 col-md-offset-1">
                        <center>
                            <h3>
                                <c:if test="${customer.getLastname() != null}">
                                    <fmt:message key="payment1.welkomterug"/>
                                </c:if>
                                <c:if test="${customer.getLastname() == null}">
                                    <fmt:message key="payment1.welkom"/>
                                </c:if>
                                <c:if test="${customer.getGender().toString() == 'MALE'}">
                                    <fmt:message key="payment1.man"/>
                                </c:if>
                                <c:if test="${customer.getGender().toString() == 'FEMALE'}">
                                    <fmt:message key="payment1.vrouw"/>
                                </c:if>
                                ${customer.getInitials()} ${customer.getLastname()}
                            </h3>
                        </center>
                            
                        <br/>
                        <br/>

                        <c:if test="${customer.getLastname() != null}">
                            <fmt:message key="payment1.controleer"/>
                        </c:if>
                        <c:if test="${customer.getLastname() == null}">
                            <fmt:message key="payment1.vulin"/>
                        </c:if>

                        <br/>
                        <br/>


                        <div class="row">
                            <div class="form-group col-xs-4 ${initials_error}">
                                <label class="control-label"><fmt:message key="payment1.initials"/></label>
                                <input class="form-control" type="text" name="initials" value="${customer.getInitials()}"/>
                            </div>
                            <div class="form-group col-xs-8 ${lastname_error}">
                                <label class="control-label"><fmt:message key="payment1.achternaam"/></label>
                                <input class="form-control" type="text" name="lastname" value="${customer.getLastname()}"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-4 ${gender_error}">
                                <table width="100%">
                                    <tr>
                                        <td width="30%">
                                            <label class="control-label"><fmt:message key="payment1.geslacht"/></label>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td width="30%">
                                            <label class="radio-inline"><input type="radio" name="gender" value="man" ${gender_m}/><fmt:message key="payment1.male"/></label>
                                        </td>
                                        <td width="30%">
                                            <label class="radio-inline"><input type="radio" name="gender" value="vrouw" ${gender_f}/><fmt:message key="payment1.female"/></label>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-9 ${address_error}">
                                <label class="control-label"><fmt:message key="payment1.adres"/></label>
                                <input class="form-control" name="address" value="${customer.getAddress()}"/>
                            </div>
                            <div class="form-group col-xs-3 ${number_error}">
                                <label class="control-label"><fmt:message key="payment1.huisnummer"/></label>
                                <input class="form-control" name="number" value="${customer.getNumber()}"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-3 ${zip_error}">
                                <label class="control-label"><fmt:message key="payment1.postcode"/></label>
                                <input class="form-control" name="zip" value="${customer.getZipcode()}"/>
                            </div>
                            <div class="form-group col-xs-4 ${city_error}">
                                <label class="control-label"><fmt:message key="payment1.stad"/></label>
                                <input class="form-control" name="city" value="${customer.getCity()}"/>
                            </div>
                            <div class="form-group col-xs-5 ${country_error}">
                                <label class="control-label"><fmt:message key="payment1.land"/></label>
                                <input class="form-control" name="country" value="${customer.getCountry()}"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-12 ${email_error}">
                                <label class="control-label"><fmt:message key="payment1.email"/></label>
                                <input class="form-control" name="email" value="${customer.getEmail()}"/>
                            </div>
                        </div>


                        <br/>
                        <br/>

                        <fmt:message key="payment1.next"/>
                    </div>

                    <div class="col-md-10 col-md-offset-1"><hr/></div>
                    <div class="col-md-8"></div>
                    <div class="col-md-4">
                        <button class="btn btn-info col-md-5" type="button" onclick="GoNext('payment0.jsp')"><fmt:message key="payment.back"/></button>
                        <div class="col-md-1"></div>
                        <!--<button class="btn btn-info col-md-5" type="submit">Volgende</button>-->
                        <button class="btn btn-info col-md-5" type="submit" onclick="GoNext('payment2.jsp')"><fmt:message key="payment.next"/></button>
                    </div>
                </form>
            </c:if>
            <c:if test="${account == null}">
                <div class="col-md-6">
                    <center><h4><fmt:message key="payment1.create"/></h4></center>

                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <center>
                            <br/>
                            <fmt:message key="payment1.geen_account"/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <button class="btn btn-info" onclick="GoNext('registration.jsp')">
                                <fmt:message key="payment1.new"/>
                            </button>
                        </center>
                    </div>
                    <div class="col-md-3"></div>
                </div>
                <div class="col-md-6">
                    <center><h4><fmt:message key="login.title"/></h4></center>
                    <br/>
                    <div class="col-md-8 col-md-offset-2">
                        <center>
                            <fmt:message key="payment1.existing"/>
                        </center>
                        <form class="form-signin" role="form" action="${pageContext.servletContext.contextPath}/LoginServlet?payment" method="post" enctype="multipart/form-data">
                            <center><font style="color: red;">${login_message}</font></center>
                            <br/>
                            <input class="form-control" type="text" placeholder="${ph_username}" name="Username"/>
                            <input class="form-control" type="password" placeholder="${ph_password}" name="Password"/>
                            <button class="col-md-12 btn btn-info"><fmt:message key="login.title"/></button>
                        </form>

                    </div>
                </div>

                <div class="col-md-10 col-md-offset-1"><hr/></div>
                <div class="col-md-8"></div>
                <div class="col-md-4">
                    <button class="btn btn-info col-md-5" onclick="GoNext('payment0.jsp')"><fmt:message key="payment.back"/></button>
                    <div class="col-md-1"></div>
                    <button class="btn btn-info col-md-5 disabled" onclick="GoNext('payment1.jsp')"><fmt:message key="payment.next"/></button>
                </div>
            </c:if>
        </c:if>


    </jsp:body>
</t:EmptyMasterPage>