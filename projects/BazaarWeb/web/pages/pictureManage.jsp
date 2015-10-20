<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent header="Picture view">
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
                <div class="col-lg-3">
                    <div class="title">
                        <h3>Price</h3>
                        <hr>
                    </div>
                    <p>
                    <form action="PhotoChangePriceServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="photoId" value="1"/>
                        <input type="number" class="form-control" name="photoPrice"  id="photoPrice" min="0" max="99999" step="0.01">
                        <br>
                        <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i>Save</button>
                    </form>
                    </p>
                </div>
            </div>
        </div>

    </jsp:body>

</t:MasterPageContent>