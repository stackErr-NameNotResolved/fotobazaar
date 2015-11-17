<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="tweede Index">
    <jsp:attribute name="script">
        <script>
            $(function () {
                $('input[id^="tag"]').click(function (event) {
                    $.ajax({
                        url: '../AjaxServlet',
                        data: {
                            userName: $('#naam').val()
                        },
                        success: function (responseText) {
                            $('#result').text(responseText);
                            
                        }
                    });

                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <!-- Sub-Container for ui elements/text -->
        <div class="container">
            <div class="row mar-b-50">
                <div class="col-md-12">
                    <span id="result"></span>
                    <form>
                        
                        naam : <input type="text" id="naam"/>
                        <input type="button" id="tag0" value="do some ajax magic"/>
                    
                    </form>


                </div>
            </div>
        </div>
        <!-- End Sub-Container -->
    </jsp:body>
</t:MasterPageContent>