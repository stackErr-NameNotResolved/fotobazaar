<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="pictureManage.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <c:forEach items="${items}" var="item">
            <div class="col-md-4" style="border: 1px solid #E5E2E2;">
                <img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.code}" alt="" style="height: 350px; max-width: 100%; ">        
                <form action="PhotoChangePriceServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="photoId" value="${item.getId()}"/>
                    <fmt:message key="pictureManage.header.price"/>: <input type="number" class="form-control" name="photoPrice"  id="photoPrice" min="0" max="99999" step="0.01" value="${item.getPrice()}">
                    <br>
                    <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i><fmt:message key="pictureManage.button.save" /></button>
                </form>
                <form action="DeletePictureServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" value="${item.getId()}" name="photoId"/>
                    <button class="btn bg-maroon margin"><i class="fa fa-trash-o pr-5"></i><fmt:message key="pictureManage.button.delete" /></button> 
                </form>
            </div>
        </c:forEach>

    </jsp:body>

</t:MasterPageContent>