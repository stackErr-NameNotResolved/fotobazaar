<%@tag description="BaseMasterPage" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="style" fragment="true"
              description="All styles for the page." %>
<%@ attribute name="script" fragment="true"
              description="All the scripts to be added on the bottom of the page go here." %>

<t:EmptyMasterPage title="${title}">

    <jsp:attribute name="style">
        <jsp:invoke fragment="style"/>
    </jsp:attribute>

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
                            <jsp:include page="/LoginServletFragment" />
                        </li>
                        <li><a>
                            <form>
                                <select id="language" name="language" onchange="submit()">
                                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                                    <option value="nl" ${language == 'nl' ? 'selected' : ''}>Nederlands</option>
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

        <jsp:doBody/>

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