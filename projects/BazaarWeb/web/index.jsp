<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<c:set var="hoi"> <fmt:message key="index.title" /></c:set>
<t:MasterPageContent title="${hoi}">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript">


        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="col-md-12">
            <div class="breadcrumbs" style="height: 300px;">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-sm-4">
                            <h1>Fotobazaar</h1>
                            <form class="form-horizontal" role="form" action="PhotoUploadServlet" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="code" class="col-sm-10 control-label">Voer uw fotocode in:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="fotoCode"  id="fotoCode">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-default btn-lg">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Bekijken
                                </button>
                            </form>
                        </div>
                        <div class="col-lg-8 col-sm-8">
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">Home</a></li>
                                <li><a href="#">Features</a></li>
                                <li class="active">Form</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row mar-b-50">
                    <div class="col-md-12">
                        <div class="text-center feature-head wow fadeInDown">
                            <h1 class="">
                                Welkom bij de fotobazaar!
                            </h1>

                        </div>


                        <div class="feature-box">
                            <div class="col-md-4 col-sm-4 text-center wow fadeInUp">
                                <div class="feature-box-heading">
                                    <em>
                                        <img src="img/1.png" alt="" width="100" height="100">

                                    </em>
                                    <h4>
                                        <b>Multipurpose Template</b>
                                    </h4>
                                </div>
                                <p class="text-center">
                                    Lorem ipsum dolor sit amet, dolore eiusmod quis tempor incididunt ut et dolore Ut veniam unde nostrudlaboris.
                                </p>
                            </div>
                            <div class="col-md-4 col-sm-4 text-center wow fadeInUp">
                                <div class="feature-box-heading">
                                    <em>
                                        <img src="img/2.png" alt="" width="100" height="100">
                                    </em>
                                    <h4>
                                        <b>Well Documented</b>
                                    </h4>
                                </div>
                                <p class="text-center">
                                    Lorem ipsum dolor sit amet, dolore eiusmod quis tempor incididunt ut et dolore Ut veniam unde nostrudlaboris.
                                </p>
                            </div>
                            <div class="col-md-4 col-sm-4 text-center wow fadeInUp">
                                <div class="feature-box-heading">
                                    <em>
                                        <img src="img/3.png" alt="" width="100" height="100">
                                    </em>
                                    <h4>
                                        <b>Responsive Design</b>
                                    </h4>
                                </div>
                                <p class="text-center">
                                    Lorem ipsum dolor sit amet, dolore eiusmod quis tempor incididunt ut et dolore Ut veniam unde nostrudlaboris.
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
        </div>
    </jsp:body>
</t:MasterPageContent> 
