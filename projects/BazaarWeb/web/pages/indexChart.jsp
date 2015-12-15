<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="title"> <fmt:message key="indexChart.title" /></c:set>
<t:MasterPageContent title="${title}">

    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>  
        <script>
            function plotDiv(divName) {
                var printContents = document.getElementById(divName).innerHTML;
                var originalContents = document.body.innerHTML;

                document.body.innerHTML = printContents;

                window.print();

                document.body.innerHTML = originalContents;
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="row">
                <button onclick = "plotDiv('indexChart')" class="btn bg-green margin btn pull-right"><i class="fa fa-save pr-5"></i><fmt:message key="indexChart.print"/></button>
            </div>
            <div class="row">
                <div id="indexChart">
                    <c:forEach items="${items}" var="item">
                        <div class="col-md-6" style = "border:1px solid black;">
                            <fmt:message key="indexChart.name" />: ${item.getName()}<br>
                            <fmt:message key="indexChart.address" />: ${item.getAddress()}<br>
                            <fmt:message key="indexChart.zipcode" />: ${item.getZipcode()}<br>
                            <fmt:message key="indexChart.city" />: ${item.getCity()}<br>
                            <fmt:message key="indexChart.email" />: ${item.getEmail()}<br>
                            <fmt:message key="indexChart.image" />: <div style="text-align: center; background-color: #f8f8f8;"><img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getPictureCode()}" alt="" style="height: 100px; max-width: 100%; "></div><br>
                            <fmt:message key="indexChart.product" />: <div style="text-align: center; background-color: #f8f8f8;"><img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getItemId()}" alt="" style="height: 100px; max-width: 100%; "></div><br>
                            <fmt:message key="indexChart.amount" />: ${item.getAmount()}<br>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>