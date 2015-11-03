<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="index.title" /></c:set>
<t:BaseMasterPage title="${title}">
    <jsp:body>
        <div class="breadcrumbs" style="height: 250px;">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1" style="margin-top:37px;">

                    <h1><fmt:message key="index.label.code" /></h1>
                    </br>
                    <form class="form-horizontal" role="form" action="ShowPictureServlet" method="POST">
                        <div class="input-group margin">
                            <input type="text" class="form-control input-lg" name="imageCode" placeholder="code"  id="fotoCode">
                            <span class="input-group-btn"><button class="btn btn-info btn-lg" type="submit" style="padding-top: 12px;"><fmt:message key="index.label.view" /></button></span>
                        </div>
                    </form>
                    <label style="color:red;visibility:hidden${visibility}" class="col-sm-10 control-label"><fmt:message key="index.failCode" /></label>
                </div>
            </div>  
        </div>

        <div class="container">
            <div class="row mar-b-50">
                <div class="col-md-12">
                    <div class="text-center feature-head wow fadeInDown">
                        <h1  >
                            <fmt:message key="index.label.welcome" />
                        </h1>
                    </div>
                    <div class="feature-box">
                        <div class="col-md-4 col-sm-4 text-center wow fadeInUp">
                            <div class="feature-box-heading">
                                <em>
                                    <img src="img/1.png" alt="" width="100" height="100">

                                </em>
                                <h4>
                                    <b><fmt:message key="index.label.titel1" /></b>
                                </h4>
                            </div>
                            <p class="text-center">
                                <b><fmt:message key="index.label.subtitel1" /></b>
                            </p>
                        </div>
                        <div class="col-md-4 col-sm-4 text-center wow fadeInUp">
                            <div class="feature-box-heading">
                                <em>
                                    <img src="img/2.png" alt="" width="100" height="100">
                                </em>
                                <h4>
                                    <b><b><fmt:message key="index.label.titel2" /></b></b>
                                </h4>
                            </div>
                            <p class="text-center">
                                <b><fmt:message key="index.label.subtitel2" /></b>
                            </p>
                        </div>
                        <div class="col-md-4 col-sm-4 text-center wow fadeInUp">
                            <div class="feature-box-heading">
                                <em>
                                    <img src="img/3.png" alt="" width="100" height="100">
                                </em>
                                <h4>
                                    <b><b><fmt:message key="index.label.titel3" /></b></b>
                                </h4>
                            </div>
                            <p class="text-center">
                                <b><fmt:message key="index.label.subtitel3" /></b>
                            </p>
                        </div>
                    </div>

                    <!--feature end-->
                </div>
            </div>
        </div>
        <div class="hr">
            <span class="hr-inner"></span>
        </div>
    </jsp:body>
</t:BaseMasterPage>
