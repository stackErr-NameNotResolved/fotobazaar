<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="login.title" /></c:set>
<c:set var="login_message">
    <%
        String message = (String) session.getAttribute("login_message"); 
        if(message == "1")
        { %>
        <fmt:message key="login.response.invalid"/>
    <% } else if(message == "2")
    { %>
    <fmt:message key="login.response.disabled"/>
    <% } else if(message == "3")
    { %>
    <fmt:message key="login.response.empty"/>
    <% } %>
</c:set>

<t:MasterPageContent title="${title}">
    <jsp:body>

        <div class="login-bg">
            <div class="container">
                <div class="form-wrapper">
                    <form class="form-signin" role="form" action="../LoginServlet" method="post" enctype="multipart/form-data">
                        <h2 class="form-signin-heading"><fmt:message key="login.label.signIn" /></h2>
                        <div class="login-wrap">
                            <font style="color: red;">${login_message}</font>

                            <c:set var="username"><fmt:message key="login.placeholder.username" /></c:set>
                            <input type="text" class="form-control" placeholder="${username}" name="Username" autofocus>
                            <c:set var="password"><fmt:message key="login.placeholder.password" /></c:set>
                            <input type="password" class="form-control" placeholder="${password}" name="Password">
                            <label class="checkbox">
                                <span class="pull-right">
                                    <a data-toggle="modal" href="#myModal"><fmt:message key="login.link.forgotten" /></a>
                                </span>
                            </label>


                            <button class="btn btn-lg btn-login btn-block"><fmt:message key="login.button.login" /></button>

                            <div class="registration">
                                <fmt:message key="login.text.registration" />
                                <a class="" href="registration.jsp">
                                    <fmt:message key="login.link.registration" />
                                </a>
                            </div>

                        </div>

                        <!-- Modal -->
                        <div aria-hidden="true" aria-labelledby="myModal" role="dialog" tabindex="-1" id="myModal" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><fmt:message key="login.link.forgotten" /></h4>
                                    </div>
                                    <div class="modal-body">
                                        <p><fmt:message key="login.text.forgotten" /></p>
                                        <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                                    </div>
                                    <div class="modal-footer">
                                        <button data-dismiss="modal" class="btn btn-default" type="button"><fmt:message key="login.button.cancel" /></button>
                                        <button class="btn btn-success" type="button"><fmt:message key="login.button.submit" /></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- modal -->

                    </form>
                </div>
            </div>
        </div>

    </jsp:body>
</t:MasterPageContent>
