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

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div style="text-align: center; background-color: #f8f8f8;">
                        <img src="../ShowPictureServlet?imageCode=${param.imageCode}&imageSize=small" alt="" style="height: 500px; max-width: 100%; ">
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-9 ">
                    <div class="title">
                        <h3><fmt:message key="pictureView.title" /></h3>
                        <hr>
                    </div> 
                        <p>
                    <form action="../PictureEditServlet" method="post">
                        <input type="hidden" value="${param.imageCode}" name="photoId"/>
                        <button class="btn bg-blue margin"><i class="fa fa-gift pr-5"></i><fmt:message key="pictureManage.button.order" /></button> 
                    </form>
                    </p>
                </div>
                
            </div>
        </div>

    </jsp:body>

</t:MasterPageContent>