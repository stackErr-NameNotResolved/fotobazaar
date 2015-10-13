<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="Picture view">
    <jsp:body>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="pf-img">
                        <img src="http://digitalhint.net/wp-content/uploads/2015/01/earth-from-space-wallpaper.jpg" alt="" height="500">
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-9 ">
                    <div class="title">
                        <h3>Preview</h3>
                        <hr>
                    </div>
                    <p>
                    <form action="DeletePictureServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="1" name="photoId"/>
                        <button class="btn bg-maroon margin"><i class="fa fa-trash-o pr-5"></i>Delete</button> 
                    </form>
                    </p>
                </div>
            </div>
        </div>

    </jsp:body>

</t:MasterPageContent>