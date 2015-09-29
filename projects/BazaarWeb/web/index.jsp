<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPage>
    	<!-- Sub-Container for ui elements/text -->
	<div class="container">
      <div class="row mar-b-50">
        <div class="col-md-12">
            <p>
            <form action="fotoUpload" method="post" enctype="multipart/form-data">
            Select image to upload:
            <input type="file" name="imageControlId" id="imageControlId" multiple accept='image/*'>
            <input type="submit" value="Upload Image" id="submit" name="submit1">
        </form>
            </p>
        </div>
      </div>
    </div>
	<!-- End Sub-Container -->
</t:MasterPage>