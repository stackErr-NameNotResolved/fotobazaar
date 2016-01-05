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
            $(function () {
                $('[id^=acc_delete').on('click', function () {
                    var elem = $(this);
                    var acc_id = elem.attr('data-account-id');

                    $('#dialog-confirm-delete').dialog({
                        resizable: true,
                        height: 200,
                        width: 500,
                        modal: true,
                        buttons: {
                            '${admin_accountView_deleteAccount}': function () {
                                $(this).dialog('close');
                                $.ajax({
                                    type: 'POST',
                                    url: 'json/account/delete',
                                    data: {
                                        id: acc_id
                                    },
                                    success: function (data, status, xhr) {
                                        if (data.result == 'ROWS_UPDATED') {
                                            elem.closest('tr').fadeOut(300, function () {
                                                $(this).remove();
                                            });
                                        } else {
                                            alert('<fmt:message key="login.auth.messages.insufficientrights"/>');
                                        }
                                    }
                                });
                            },
                            '${lang_close}': function () {
                                $(this).dialog('close');
                            }
                        }
                    });
                });
            });
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
                <tr>
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
                        <input id="acc_delete_${acc.id}" data-account-id="${acc.id}" type="button" class="btn btn-white"
                               value="Verwijderen"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:MasterPageContent>
