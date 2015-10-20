<%@tag description="MasterPageContent" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="script" fragment="true" description="All the scripts to be added on the bottom of the page go here." %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<<<<<<< HEAD
<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Fotobazaar">
        <meta name="author" content="StarFeed">
        <meta name="keywords" content="Fotobazaar, Foto">
        <link rel="shortcut icon" href="img/favicon.png">

        <title>
            Fotobazaar | Home
        </title>

        <!-- Bootstrap core CSS -->
        <link href="/BazaarWeb/css/bootstrap.min.css" rel="stylesheet">
        <link href="/BazaarWeb/css/theme.css" rel="stylesheet">
        <link href="/BazaarWeb/css/bootstrap-reset.css" rel="stylesheet">
        <!-- <link href="css/bootstrap.min.css" rel="stylesheet">-->

        <!--external css-->
        <link href="/BazaarWeb/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="/BazaarWeb/css/flexslider.css" rel="/stylesheet" />
        <link href="/BazaarWeb/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="/BazaarWeb/assets/bxslider/jquery.bxslider.css" rel="stylesheet" />
        <link href="/BazaarWeb/css/animate.css" rel="/stylesheet" >
        <link href="/BazaarWeb/assets/owlcarousel/owl.carousel.css" rel="/stylesheet" >
        <link href="/BazaarWeb/assets/owlcarousel/owl.theme.css" rel="/stylesheet" >
        <link href="/BazaarWeb/css/superfish.css" rel="stylesheet" media="screen">
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'> -->


        <!-- Custom styles for this template -->
        <link href="/BazaarWeb/css/component.css" rel="stylesheet" type="text/css" >
        <link href="/BazaarWeb/css/style.css" rel="stylesheet">
        <link href="/BazaarWeb/css/style-responsive.css" rel="stylesheet" />

        <link href="/BazaarWeb/css/parallax-slider/parallax-slider.css" rel="stylesheet" type="text/css"  />
        <script src="/BazaarWeb/js/parallax-slider/modernizr.custom.28468.js" type="text/javascript" >
        </script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js">
        </script>
        <script src="js/respond.min.js">
        </script>
        <![endif]-->
    </head>

    <body>
        <!--header start-->
        <header class="head-section">
            <div class="navbar navbar-default navbar-static-top container">
                <div class="navbar-header">
                    <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/BazaarWeb/index.jsp">Foto<span>bazaar</span></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="contact.html"><fmt:message key="master.menu.cart" /></a>
                        </li>
                        <li>
                            <a href="contact.html">???????</a>
                        </li>
                        <li>
                            <%
                                boolean account = false;
                                for(Cookie c : request.getCookies())
                                    if(c.getName().equals("username"))
                                    {
                                        account = true;
                                        break;
                                    }
                                
                                if(account) {
                            %>
                            <A HREF="javascript:document.submitForm.submit()">Uitloggen</A>
                            <form name="submitForm" method="POST" action="LogOutServlet">                            
                            </form>
                            <%
                            } else {
                            %>
                            <a href="/BazaarWeb/jsp/login.jsp"><fmt:message key="master.menu.login" /></a>
                            <% }%>
                        </li>
                        <li>
                            <form>
                                <select id="language" name="language" onchange="submit()">
                                    <option value="nl" ${language == 'nl' ? 'selected' : ''}>Nederlands</option>
                                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                                </select>
                            </form>
                        </li>
                        <!-- Search input
                                        <li><input class="form-control search" placeholder=" Search" type="text"></li> -->
                    </ul>
                </div>
            </div>
        </header>
        <!--header end-->

        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-sm-4">
                        <h1>${title}</h1>
                    </div>
                </div>
            </div>
        </div>

        <!-- Sub-Container for ui elements/text -->
=======
<t:BaseMasterPage title="${title}">
    <jsp:body>
>>>>>>> 4bc190e33cd08bc7eba7103c0dd710681ba73235
        <div class="container">
            <div class="row mar-b-50">
                <jsp:doBody/>
            </div>
        </div>
        <jsp:invoke fragment="script"/>
    </jsp:body>
</t:BaseMasterPage>

