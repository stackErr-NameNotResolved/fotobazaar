<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : jip
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="itemEdit.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <form action="../PhotoChangePriceServlet" method="post">
                        <div style="text-align: center; background-color: #f8f8f8;"><img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getId()}" alt="" style="height: 500px; max-width: 100%; "></div><br><br>
                        <fmt:message key="pictureManage.header.price"/>:<input type="number" class="form-control" name="itemPrice"  id="photoPrice" min="0" max="99999" step="0.01" value="${item.getPrice()}"><br><br>
                        <input type="text" value="${item.toString()}" class="form-control" id="itemDescription"><br><br>
                        <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i><fmt:message key="cart.edit"/></button>
                        <hr>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>