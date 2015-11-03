<%-- 
    Document   : registration
    Created on : 3-nov-2015, 10:01:46
    Author     : sjorsvanmierlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="registration.title" /></c:set>
<t:MasterPageContent title="${title}">
    
    <div class="registration-bg">
        <div class="container">

            <form class="form-signin wow fadeInUp" role="form" action="../RegisterAccountServlet" method="post" enctype="multipart/form-data">
                <h2 class="form-signin-heading">Register now</h2>
                <div class="login-wrap">
                    <p> Enter account details below</p>
                    <input type="text" name="username" class="form-control" placeholder="User Name" autofocus="">
                    <input type="password" name="password1" class="form-control" placeholder="Password">
                    <input type="password" name="password2" class="form-control" placeholder="Re-type Password">
                    <button class="btn btn-lg btn-login btn-block" type="submit">Submit</button>

                    <div class="registration">
                        Already Registered ?
                        <a class="" href="login.jsp">
                            Login
                        </a>
                    </div>
                </div>
            </form>

        </div>
     </div>
    
</t:MasterPageContent> 