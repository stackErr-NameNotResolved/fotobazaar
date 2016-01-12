<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="photoUpload.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript">
            function readURL(input) {
                if (input.files && input.files[0] && input.files[1] == null) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#preview').attr('src', e.target.result);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#PictureControlId").change(function () {
                readURL(this);
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="col-md-6">
            <div class="bs-example">
                <form class="form-horizontal" role="form" action="../PhotoUploadServlet" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="prijs" class="col-sm-2 control-label"><fmt:message key="photoUpload.price"/></label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name="PicturePrice"  id="PicturePriceID" min="0" max="1000" step="0.01" value="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="foto" class="col-sm-2 control-label"><fmt:message key="photoUpload.photo"/></label>
                        <div class="col-sm-10">
                            <input type="file" name="PictureControl" id="PictureControlId" multiple accept='image/*' class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default"><fmt:message key="photoUpload.upload"/></button>
                        </div>
                    </div>
                    <c:if test="${not empty status}">
                        <c:choose>
                            <c:when test="${status}">
                                <fmt:message key="photoUpload.uploadSuccessful"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="photoUpload.uploadFailed"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </form>
            </div>
        </div>
        <div class="col-md-6">
            <c:set var ="description" />
            <fmt:message key="photoUpload.noImage" var="description"/>
            <img id="preview" src="#" alt="${description}" style="width: 100%;"/>
        </div>
    </jsp:body>
</t:MasterPageContent>
