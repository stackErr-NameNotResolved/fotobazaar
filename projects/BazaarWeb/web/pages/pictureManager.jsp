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
    <jsp:attribute name="script">
        <script>
            (function ($) {
                $('.spinner .btn:first-of-type').on('click', function () {
                    $('.spinner input').val(parseInt($('.spinner input').val(), 10) + 1);
                });
                $('.spinner .btn:last-of-type').on('click', function () {
                    $('.spinner input').val(parseInt($('.spinner input').val(), 10) - 1);
                });
            })(jQuery);
        </script>
    </jsp:attribute>


    <jsp:body>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div style="text-align: center; background-color: #f8f8f8;">
                        <img src="../ShowPictureServlet?imageId=${param.imageId}&imageSize=big" alt="" style="height: 500px; max-width: 100%; ">
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-9 ">
                    <div class="title">
                        <h3><fmt:message key="pictureManage.header.preview" /></h3>
                        <hr>
                    </div>
                    <p>
                    <form action="../DeletePictureServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="${param.imageId}" name="photoId"/>
                        <button class="btn bg-maroon margin"><i class="fa fa-trash-o pr-5"></i><fmt:message key="pictureManage.button.delete" /></button> 
                    </form>
                    </p>
                    
                </div>
                <div class="col-lg-3">
                    <div class="title">
                        <h3><fmt:message key="pictureManage.header.price" /></h3>
                        <hr>
                    </div>
                    <p>
                    <form action="../PhotoChangePriceServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="photoId" value="${param.imageId}"/>
                        <input type="number" class="form-control" name="photoPrice"  id="photoPrice" min="0" max="99999" step="0.01" value="${imagePrice}">
                        <br>
                        <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i><fmt:message key="pictureManage.button.save" /></button>
                    </form>
                    </p>
                </div>
            </div>
        </div>

    </jsp:body>

</t:MasterPageContent>