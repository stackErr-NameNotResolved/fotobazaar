<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="username"><fmt:message key="admin.account.username" /></c:set>
<c:set var="username_description"><fmt:message key="admin.account.username.description" /></c:set>
<c:set var="password"><fmt:message key="admin.account.password" /></c:set>
<c:set var="password_description"><fmt:message key="admin.account.password.description" /></c:set>

<c:set var="title"><fmt:message key="admin.account.title" /></c:set>
<t:MasterPageContent title="${title}">
    <form role="form" method="post" action="${pageContext.servletContext.contextPath}/AdminCreateAccountServlet">
        <div class="form-group">
            <label for="inputUsername">${username}</label>
            <input type="text" class="form-control" id="inputUsername" name="inputUsername" placeholder="${username_description}">
        </div>
        <div class="form-group">
            <label for="inputUsername">${password}</label>
            <input type="text" class="form-control" id="inputPassword" name="inputPassword" placeholder="${password_description}">
        </div>
        <button type="submit" class="btn btn-default">Account aanmaken</button>
    </form>
</t:MasterPageContent>
