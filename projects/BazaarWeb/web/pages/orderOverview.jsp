<%-- 
    Document   : orderOverview
    Created on : 8-dec-2015, 12:36:33
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="orderOverview.title" /></c:set>
<c:set var="tableID"><fmt:message key="orderOverview.tableID" /></c:set>
<c:set var="tableOrderDate"><fmt:message key="orderOverview.tableOrderDate" /></c:set>
<c:set var="tableIsPaid"><fmt:message key="orderOverview.tableIsPaid" /></c:set>
<c:set var="btnDetails"><fmt:message key="orderOverview.btnDetails" /></c:set>
<c:set var="isPaidTrue"><fmt:message key="orderOverview.isPaidTrue" /></c:set>
<c:set var="isPaidFalse"><fmt:message key="orderOverview.isPaidFalse" /></c:set>

<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th width="7%">${tableID}</th>
                            <th width="70%">${tableOrderDate}</th>
                            <th width="10%">${tableIsPaid}</th>
                            <th width="13%"></th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td style="vertical-align: middle;">${order.getId()}</td>
                                <td style="vertical-align: middle;">${order.getOrderDateString()}</td>
                                <td style="vertical-align: middle;">
                                    <c:choose>
                                        <c:when test="${order.isPaid()}">
                                            ${isPaidTrue}
                                        </c:when>
                                        <c:otherwise>
                                            ${isPaidFalse}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="vertical-align: middle;"><button class="btn bg-blue btn-block"><i class="fa fa-info-circle"></i> ${btnDetails}</button></td>
                            </tr>                        
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
