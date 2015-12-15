<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="login.title" /></c:set>
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

    <jsp:attribute name="script">
        <c:if test="${not empty authmessage}">
            <script>
                $('#redirectMessage').modal('show');
            </script>
        </c:if>
        <c:remove var="authresult" />
        <c:remove var="authmessage" />
    </jsp:attribute>

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


                            <button class="btn btn-lg btn-login btn-block"><fmt:message key="login.button.login" /></button>

                            <div class="registration">
                                <fmt:message key="login.text.registration" />
                                <a class="" href="registration.jsp">
                                    <fmt:message key="login.link.registration" />
                                </a>
                            </div>

                        </div>
                    </form>

                    <!-- Message to user -->
                    <c:set var="authmessage">
                        <c:choose>
                            <c:when test="${authresult.id == 1}">
                                <fmt:message key="login.auth.messages.insufficientrights" />
                            </c:when>
                            <c:when test="${authresult.id == 2}">
                                <fmt:message key="login.auth.messages.notloggedin" />
                            </c:when>
                        </c:choose>
                    </c:set>

                    <div id="redirectMessage" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title"><fmt:message key="login.auth.header" /></h4>
                                </div>
                                <div class="modal-body">
                                    <p>${authmessage}</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="lang.close" /></button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- End message to user -->
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
