<%@ tag import="classes.domain.Session" %>
<%@tag description="BaseMasterPage" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="script" fragment="true"
              description="All the scripts to be added on the bottom of the page go here." %>

<%
    // TODO: FIX DIS SHIT SO THAT VARIABLES CAN BE SET JUST-IN-TIME! They are now 0 by default and aren't set.
    String username = (String) session.getAttribute("username");
    String encrypted = (String) session.getAttribute("username-encrypted");

    if (Session.checkSessionData(username, encrypted, request.getRemoteAddr())) {
        request.setAttribute("login-href", "javascript:document.submitForm.submit()");
        request.setAttribute("login-text", "master.menu.logout");
    } else {
        session.removeAttribute("username");
        session.removeAttribute("username-encrypted");

        request.setAttribute("login-href", "/BazaarWeb/pages/login.jsp");
        request.setAttribute("login-text", "master.menu.login");
    }
%>

<t:EmptyMasterPage title="${title}">

    <jsp:attribute name="script">
        <jsp:invoke fragment="script"/>
    </jsp:attribute>

    <jsp:body>
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
                            <a href="contact.html"><fmt:message key="master.menu.cart"/></a>
                        </li>
                        <li>
                            <a href="contact.html">???????</a>
                        </li>
                        <li>
                            <!-- TODO: Fix this -->
                            <A href="${login-href}"><fmt:message key="${login-text}"/></A>

                            <form name="submitForm" method="POST" action="LogOutServlet">
                            </form>
                        </li>
                        <li><a>
                            <form>
                                <select id="language" name="language" onchange="submit()">
                                    <option value="nl" ${language == 'nl' ? 'selected' : ''}>Nederlands</option>
                                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                                </select>
                            </form>
                        </a>
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

        <div class="container">
            <div class="row mar-b-50">
                <jsp:doBody/>
            </div>
        </div>

        <!--small footer start -->
        <footer class="footer-small" style="height:63px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="copyright">
                            <p>&copy; Copyright - Fotobazaar</p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!--small footer end-->
    </jsp:body>
</t:EmptyMasterPage>