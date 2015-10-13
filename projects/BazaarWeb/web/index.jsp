<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="eerst Index">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript">


        </script>
    </jsp:attribute>
    <jsp:body>

        <div class="col-md-12">
            <p>
            <form action="PhotoUploadServlet" method="post" enctype="multipart/form-data">
            Select image to upload:
            <input type="file" name="PictureControlId" id="imageControlId" multiple accept='image/*'>
            <input type="submit" value="Upload Image" id="submit" name="submit1">
        </form>
            </p>
        </div>

    </jsp:body>
</t:MasterPageContent> 
