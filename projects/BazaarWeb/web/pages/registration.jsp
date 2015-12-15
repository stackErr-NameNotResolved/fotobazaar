<%-- 
    Document   : registration
    Created on : 3-nov-2015, 10:01:46
    Author     : sjorsvanmierlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="registration.title" /></c:set>
<%
    Object o = request.getParameter("toegang");
    if(o != null)
    {
        request.setAttribute("redirect", "true");
    }
%>

<t:EmptyMasterPage title="${title}">
    <c:set var="head"><fmt:message key="registration.head" /></c:set>
    <c:set var="details"><fmt:message key="registration.details" /></c:set>
    <c:set var="username"><fmt:message key="registration.username" /></c:set>
    <c:set var="password"><fmt:message key="registration.password" /></c:set>
    <c:set var="retypepassword"><fmt:message key="registration.retypepassword" /></c:set>
    <c:set var="submit"><fmt:message key="registration.submit" /></c:set>
    <c:set var="alreadyregistered"><fmt:message key="registration.alreadyregistered" /></c:set>
    <c:set var="login"><fmt:message key="registration.login" /></c:set>
        <div class="registration-bg">
            <div class="container">

                <form class="form-signin wow fadeInUp" role="form" action="../RegisterAccountServlet" method="post" enctype="multipart/form-data">
                    <h2 class="form-signin-heading">${head}</h2>
                <div class="login-wrap">
                    <p> ${details}</p>
                    <input type="text" name="username" class="form-control" placeholder="${username}" autofocus="">
                    <input type="password" name="password1" class="form-control" placeholder="${password}">
                    <input type="password" name="password2" class="form-control" placeholder="${retypepassword}">
                    <input type="hidden" name="redirect" value="${redirect}">
                    <button class="btn btn-lg btn-login btn-block" type="submit">${submit}</button>

                    <div class="registration">
                        ${alreadyregistered} 
                        <a class="" href="login.jsp">
                            ${login}
                        </a>
                    </div>
                </div>
            </form>

        </div>
    </div>

</t:EmptyMasterPage> 