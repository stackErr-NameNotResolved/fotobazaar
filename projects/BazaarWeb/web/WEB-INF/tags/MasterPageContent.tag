<%@tag description="MasterPageContent" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="script" fragment="true" description="All the scripts to be added on the bottom of the page go here." %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:BaseMasterPage title="${title}">
    <jsp:attribute name="script">
        <jsp:invoke fragment="script"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:BaseMasterPage>

