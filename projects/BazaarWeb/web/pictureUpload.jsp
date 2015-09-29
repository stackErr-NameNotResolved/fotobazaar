<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="Foto Uploaden">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript">
                function readURL(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('#preview').attr('src', e.target.result);
                        }

                        reader.readAsDataURL(input.files[0]);
                    }
                }

                $("#picUpload").change(function(){
                    readURL(this);
                });
        </script>
    </jsp:attribute>
    <jsp:body>

        <div class="col-md-6">

            <div class="bs-example">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="prijs" class="col-sm-2 control-label">Prijs</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="" min="0" max="1000" step="0.01">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="foto" class="col-sm-2 control-label">Foto</label>
                        <div class="col-sm-10">
                            <input type="file" id="picUpload" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Uploaden</button>
                        </div>
                    </div>
                </form>
            </div>


        </div>

        <div class="col-md-6">
            <img id="preview" src="#" alt="No image found." />
        </div>
        
        
    </jsp:body>
</t:MasterPageContent> 
