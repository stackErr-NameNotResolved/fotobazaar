<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/jsp/langInclude.jsp" %>

<c:set var="title"> <fmt:message key="index.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript">


        </script>
    </jsp:attribute>
    <jsp:body>

        <div class="col-md-12">
            <p>
            <form action="PhotoUploadServlet" method="post" enctype="multipart/form-data">
                <label for="selectImage"><fmt:message key="index.label.selectImage" /></label>
                <input type="file" name="PictureControlId" id="imageControlId" multiple accept='image/*'>
                <c:set var="UploadImageButton"><fmt:message key="index.button.uploadImage" /></c:set>
                <input type="submit" value="${UploadImageButton}" id="submit" name="submit1">
            </form>
            </p>
        </div>

    </jsp:body>
</t:MasterPageContent> 
