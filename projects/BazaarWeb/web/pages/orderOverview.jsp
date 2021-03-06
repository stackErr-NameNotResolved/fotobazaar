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
<c:set var="tableIsDone"><fmt:message key="orderOverview.tableIsDone" /></c:set>
<c:set var="btnDetails"><fmt:message key="orderOverview.btnDetails" /></c:set>
<c:set var="isPaidTrue"><fmt:message key="orderOverview.isPaidTrue" /></c:set>
<c:set var="isPaidFalse"><fmt:message key="orderOverview.isPaidFalse" /></c:set>
<c:set var="isDoneTrue"><fmt:message key="orderOverview.isDoneTrue" /></c:set>
<c:set var="isDoneFalse"><fmt:message key="orderOverview.isDoneFalse" /></c:set>



<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <table class="table table-hover ">
                    <thead>
                        <tr>
                            <th width="7%">${tableID}</th>
                            <th width="60%">${tableOrderDate}</th>
                            <th width="10%">${tableIsPaid}</th>
                            <th width="10%">${tableIsDone}</th>
                            <th width="13%"></th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${orders}" var="order">
                            <c:choose>
                                <c:when test="${order.isPaid()}">
                                        <c:choose>
                                            <c:when test="${order.isDone()}">
                                                <tr class="success">
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="warning">
                                            </c:otherwise>
                                        </c:choose>
                                </c:when>                                
                                    <c:otherwise>
                                    <tr class="danger">
                                    </c:otherwise>
                                </c:choose>

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
                                <td style="vertical-align: middle;">
                                    <c:choose>
                                        <c:when test="${order.isDone()}">
                                            ${isDoneTrue}
                                        </c:when>
                                        <c:otherwise>
                                            ${isDoneFalse}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="vertical-align: middle;">
                                    <form role="form" action="../OrderDetailOverviewServlet" method="GET" enctype="multipart/form-data" >
                                        <input type="hidden" name="orderId" value="${order.getId()}"/> 
                                        <button class="btn bg-blue btn-block"><i class="fa fa-info-circle"></i> ${btnDetails}</button>
                                    </form>
                                </td>
                            </tr>                        
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
