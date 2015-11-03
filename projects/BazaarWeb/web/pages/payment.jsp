<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="payment.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <form method="POST">
                <table>
                    <tr>
                        <th>Photo ID</th>
                        <th>Product Type</th>
                        <th>Amount</th>
                        <th>Price</th>
                    </tr>
                    <tr>
                        <td>1234</td>
                        <td>Picture</td>
                        <td>3</td>
                        <td>4.50</td>
                    </tr>
                    <tr>
                        <th></th><th></th><th></th><th>Total</th>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td>4.50</td>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td><button class="btn">Pay</button></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
