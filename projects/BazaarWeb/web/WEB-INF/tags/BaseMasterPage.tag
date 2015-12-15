<%@tag description="BaseMasterPage" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="style" fragment="true"
              description="All styles for the page." %>
<%@ attribute name="script" fragment="true"
              description="All the scripts to be added on the bottom of the page go here." %>
<%@include file="/pages/langInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--Custom EL functions.--%>
<%@ taglib prefix="sf" uri="/WEB-INF/tld/SessionLibrary.tld" %>
<%@ taglib prefix="tr" uri="/WEB-INF/tld/TranslateLibrary.tld" %>
<%@ taglib prefix="domain_cart" uri="/WEB-INF/tld/Cart.tld" %>

<%--Session data--%>
<c:set var="order_count">${domain_cart:readCartItemCountFromCookies(pageContext.request)}</c:set>

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
                    <a class="navbar-brand"
                       href="${pageContext.servletContext.contextPath}/index.jsp">Foto<span>bazaar</span></a>
                </div>

                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.servletContext.contextPath}/index.jsp">Home</a></li>
                            <c:if test="${order_count > 0}">
                            <li><a href="${pageContext.servletContext.contextPath}/pages/cart.jsp"><fmt:message
                                        key="master.menu.cart"/> [${order_count}]</a></li>
                                </c:if>
                        <li>
                            <%--Check if user is logged in.--%>
                            <c:choose>
                                <c:when test="${account != null}">
                                    <c:set var="loginButtonText"><fmt:message key="master.menu.logout"/></c:set>
                                    <c:set var="loginButtonAction">${pageContext.servletContext.contextPath}/LogOutServlet</c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="loginButtonText"><fmt:message key="master.menu.login"/></c:set>
                                    <c:set var="loginButtonAction">${pageContext.servletContext.contextPath}/pages/login.jsp</c:set>
                                </c:otherwise>
                            </c:choose>

                            <a href="javascript:document.submitForm.submit()">${loginButtonText}</a>

                            <form name="submitForm" method="post" action="${loginButtonAction}"></form>
                        </li>
                        <c:if test="${account != null}">
                            <c:if test="${account.getRight() == 1}">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-close-others="false" data-delay="0"
                                       data-hover="dropdown"
                                       data-toggle="dropdown" href="#"><fmt:message key="master.menu.admin"/>
                                        <i class="fa fa-angle-down"></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="${pageContext.servletContext.contextPath}/pages/admin/createAccount.jsp"><fmt:message
                                                    key="master.menu.admin.createAccount"/></a>
                                        </li>
                                        <li>
                                            <a href="${pageContext.servletContext.contextPath}/AccountViewServlet"><fmt:message
                                                    key="master.menu.admin.accountView"/></a>
                                        </li>
                                        <li>
                                            <a href="ItemViewServlet"><fmt:message
                                                    key="master.menu.admin.editProducts"/></a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${account.getRight() == 2}">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-close-others="false" data-delay="0"
                                       data-hover="dropdown"
                                       data-toggle="dropdown" href="#"><fmt:message key="master.menu.photographer"/>
                                        <i class="fa fa-angle-down"></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="${pageContext.servletContext.contextPath}/pages/pictureUpload.jsp"><fmt:message
                                                    key="master.menu.photographer.addPhoto"/></a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                        </c:if>
                        <li>
                            <a>
                                <form action="${requestScope['javax.servlet.forward.query_string']}">
                                    <select id="language" name="language" onchange="submit()">
                                        <c:set var="langStr">${fn:substring(language.class.name.equals('Locale') ? language.language : language, 0, 3)}</c:set>
                                        ${langStr}
                                        <option value="en" <c:if test="${langStr eq 'en'}">selected</c:if>>
                                                English
                                            </option>
                                            <option value="nl" <c:if test="${langStr eq 'nl'}">selected</c:if>>
                                                Nederlands
                                        </option>
                                        <option value="tr" <c:if test="${langStr eq 'tr'}">selected</c:if>>
                                            Türkçe
                                            </option>
                                        </select>
                                    </form>
                                </a>
                            </li>
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
                            <br/>

                            <p><a href="http://translate.yandex.com">Powered by Yandex.Translator</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!--small footer end-->
    </jsp:body>
</t:EmptyMasterPage>