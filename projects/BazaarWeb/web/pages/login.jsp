<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/jsp/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="login.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>

        <div class="login-bg">
            <div class="container">
                <div class="form-wrapper">
                    <form class="form-signin" role="form" action="LoginServlet" method="post" enctype="multipart/form-data">
                        <h2 class="form-signin-heading"><fmt:message key="login.label.signIn" /></h2>
                        <div class="login-wrap">
                            <input type="text" class="form-control" placeholder="User ID" name="Username" autofocus>
                            <input type="password" class="form-control" placeholder="Password" name="Password">
                            <label class="checkbox">
                                <span class="pull-right">
                                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>
                                </span>
                            </label>


                            <button class="btn btn-lg btn-login btn-block">Login</button>

                            <div class="registration">
                                Don't have an account yet?
                                <a class="" href="registration.html">
                                    Create an account
                                </a>
                            </div>

                        </div>

                        <!-- Modal -->
                        <div aria-hidden="true" aria-labelledby="myModal" role="dialog" tabindex="-1" id="myModal" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title">Forgot Password ?</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>Enter your e-mail address below to reset your password.</p>
                                        <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                                    </div>
                                    <div class="modal-footer">
                                        <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                                        <button class="btn btn-success" type="button">Submit</button>
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
