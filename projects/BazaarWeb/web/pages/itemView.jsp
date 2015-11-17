<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="itemView.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:forEach items="${items}" var="item">
                        <form action="../PhotoChangePriceServlet" method="post">
                            <fmt:message key="pictureManage.header.price"/>: ${item.getPrice()}<br><br>
                            ${item.toString()}<br><br>
                            <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i><fmt:message key="cart.edit"/></button>
                            <div style="text-align: center; background-color: #f8f8f8;"><img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getId()}" alt="" style="height: 500px; max-width: 100%; "></div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>