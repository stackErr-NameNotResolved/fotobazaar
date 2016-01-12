<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="username"><fmt:message key="admin.account.username"/></c:set>
<c:set var="username_description"><fmt:message key="admin.account.username.description"/></c:set>
<c:set var="password"><fmt:message key="admin.account.password"/></c:set>
<c:set var="password_description"><fmt:message key="admin.account.password.description"/></c:set>
<c:set var="passwordredo"><fmt:message key="admin.account.passwordredo"/></c:set>
<c:set var="passwordredo_description"><fmt:message key="admin.account.passwordredo.description"/></c:set>

<c:set var="title"><fmt:message key="admin.accountView.title"/></c:set>

<c:set var="lang_close"><fmt:message key="lang.close"/></c:set>
<c:set var="admin_accountView_deleteAccount"><fmt:message key="admin.accountView.deleteAccount"/></c:set>
<c:set var="admin_accountView_deleteAccount_confirm_title"><fmt:message
        key="admin.accountView.deleteAccount.confirm.title"/></c:set>
<c:set var="admin_accountView_deleteAccount_confirm_message"><fmt:message
        key="admin.accountView.deleteAccount.confirm.message"/></c:set>

<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <script>
            selectedId = 0;

            function delete_account(acc_id) {
                $.ajax({
                    type: 'POST',
                    url: '<c:out value="${pageContext.servletContext.contextPath}"/>/json/account/update',
                    data: {
                        id: acc_id,
                        access: 0
                    },
                    success: function (data, status, xhr) {
                        if (data.result == 'ROWS_UPDATED') {
                            localStorage.setItem("focusAccount", acc_id);
                            location.reload();
                        } else {
                            alert('<fmt:message key="login.auth.messages.insufficientrights"/>');
                        }
                    }
                });
            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div hidden id="dialog-confirm-delete" title="${admin_accountView_deleteAccount_confirm_title}">
            <p>

            </p>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="5%">Id</th>
                <th width="45%">Naam</th>
                <th width="20%">Rechten</th>
                <th width="30%"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accounts}" var="acc">
                <tr data-account-id="<c:out value="${acc.id}"/>">
                    <td>
                            ${acc.id}
                    </td>
                    <td>
                            ${acc.username}
                    </td>
                    <td>
                        <fmt:message key="rights.${fn:toLowerCase(acc.getRightName())}"/>
                    </td>
                    <td style="text-align: center;">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="selectedId = <c:out value="${acc.id}"/>">
                            Verwijderen
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Account definitief verwijderen</h4>
                    </div>
                    <div class="modal-body">
                        Weet u zeker dat u dit account wilt verwijderen?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" onclick="delete_account(selectedId)" data-dismiss="modal"><fmt:message
                                key="admin.accountView.deleteAccount"/></button>
                        <button type="button" class="btn btn-success" data-dismiss="modal"><fmt:message
                                key="lang.close"/></button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
