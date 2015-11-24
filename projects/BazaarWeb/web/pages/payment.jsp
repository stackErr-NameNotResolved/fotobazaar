<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="payment.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <form method=post action=https://api-3t.sandbox.paypal.com>
                    <input type=hidden name=USER value=test2_api1.testemail.nl>
                    <input type=hidden name=PWD value=9WH8HKQTD6WU2Z4Z>
                    <input type=hidden name=SIGNATURE value=An5ns1Kso7MWUdW4ErQKJJJ4qi4-A4.bZdp4H4uchYV6inx1cTkbj0pG>
                    <input type=hidden name=VERSION value=124.0>
                    <input type=hidden name=METHOD value=SetExpressCheckout>
                    <input type=hidden name=PAYMENTREQUEST_0_PAYMENTACTION
                            value=Sale>
                    <input name=PAYMENTREQUEST_0_AMT value=19.95>
                    <input type=hidden name=RETURNURL
                            value=http://localhost:8080/BazaarWeb/pages/paymentSucces.jsp>
                    <input type=hidden name=CANCELURL
                            value=http://localhost:8080/BazaarWeb/pages/payment.jsp>
                    <input type=submit value=Checkout>
                </form>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
