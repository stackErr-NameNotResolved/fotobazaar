<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="index.title" /></c:set>
<t:MasterPageContent title="${title}">
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
                            <form class="form-horizontal" role="form" action="ShowPictureServlet" method="POST">
                                <div class="form-group">
                                    <label for="code" class="col-sm-10 control-label"><fmt:message key="index.label.code" /></label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="imageCode"  id="fotoCode">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-default btn-lg">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span><fmt:message key="index.label.view" />
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
