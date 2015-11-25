<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="username"><fmt:message key="admin.account.username"/></c:set>
<c:set var="username_description"><fmt:message key="admin.account.username.description"/></c:set>
<c:set var="password"><fmt:message key="admin.account.password"/></c:set>
<c:set var="password_description"><fmt:message key="admin.account.password.description"/></c:set>
<c:set var="passwordredo"><fmt:message key="admin.account.passwordredo"/></c:set>
<c:set var="passwordredo_description"><fmt:message key="admin.account.passwordredo.description"/></c:set>

<c:set var="title"><fmt:message key="admin.account.title"/></c:set>

<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <script>
            $('#formCreateAccount').each(function () {
                $(this).bootstrapValidator({
                    fields: {
                        inputUsername: {
                            validators: {
                                notEmpty: {
                                    message: 'Gebruikersnaam is verplicht.'
                                },
                                stringLength: {
                                    min: 3,
                                    max: 50,
                                    message: 'Gebruikersnaam moet tussen de 5 en 50 karakters lang zijn.'
                                }
                            }
                        },
                        inputPassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Wachtwoord is verplicht.'
                                },
                                identical: {
                                    field: 'inputPasswordRedo',
                                    message: 'Wachtwoord bevestiging komt niet overeen.'
                                },
                                stringLength: {
                                    min: 5,
                                    max: 50,
                                    message: 'Wachtwoord moet tussen de 5 en 50 karakters lang zijn.'
                                }
                            }
                        },
                        inputPasswordRedo: {
                            validators: {
                                identical: {
                                    field: 'inputPassword',
                                    message: 'Wachtwoord moet overeenkomen met bevestiging.'
                                }
                            }
                        }
                    }
                })
            }).on('success.form.bv', function(e) {
                // Prevent submit form
                e.preventDefault();

                var infoDiv = $('#info');

                $.ajax({
                    type: 'POST',
                    url: '${pageContext.servletContext.contextPath}/AdminCreateAccountServlet',
                    data: $(this).serialize(),
                    success: function (data) {
                        infoDiv.removeClass('alert-danger');
                        infoDiv.addClass('alert-success');
                        infoDiv.html(data.response);

                        infoDiv.fadeIn();
                    },
                    error: function (xhr, status, error) {
                        infoDiv.removeClass('alert-success');
                        infoDiv.addClass('alert-danger');
                        infoDiv.html(xhr.responseText);

                        infoDiv.fadeIn();
                    }
                });
            });;
        </script>
    </jsp:attribute>

    <jsp:body>
        <form id="formCreateAccount" role="form" action="#">
            <div class="form-group">
                <label for="inputUsername">${username}</label>
                <input type="text" class="form-control" id="inputUsername" name="inputUsername"
                       placeholder="${username_description}">
                <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
                <label for="inputPassword">${password}</label>
                <input type="password" class="form-control" id="inputPassword" name="inputPassword"
                       placeholder="${password_description}">
                <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
                <label for="inputPasswordRedo">${passwordredo}</label>
                <input type="password" class="form-control" id="inputPasswordRedo" name="inputPasswordRedo"
                       placeholder="${passwordredo_description}">
                <div class="help-block with-errors"></div>
            </div>
            <div id="info" class="alert alert-danger" hidden></div>
            <button type="submit" class="btn btn-default"><fmt:message key="master.menu.admin.createAccount"/></button>
        </form>
    </jsp:body>
</t:MasterPageContent>
