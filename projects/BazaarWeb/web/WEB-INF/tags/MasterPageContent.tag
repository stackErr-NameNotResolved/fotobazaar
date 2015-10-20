<%@tag description="MasterPageContent" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="script" fragment="true" description="All the scripts to be added on the bottom of the page go here." %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:BaseMasterPage title="${title}">
    <jsp:attribute name="script">
        <jsp:invoke fragment="script"/>
    </jsp:attribute>
    <jsp:body>
        
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
    </jsp:body>
</t:BaseMasterPage>

