<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="picture editor">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript" src="/js/CamanJS/caman.full.js"></script>
        <script type="text/javascript">
            Caman("#editor", "ShowPicture?imageId=1&imageSize=small", function () {

                this.render();
            });

            $("#reset").click(function () {
                Caman("#editor", "ShowPicture?imageId=1&imageSize=small", function () {
                    this.revert(false);
                    this.render();
                });
            });

            $("#crop").click(function () {
                Caman("#editor", function () {
                    this.crop(200, 200);
                    this.render();
                });
            });

            $("#sepia").click(function () {
                Caman("#editor", function () {
                    this.resize({
                        width: 500,
                        height: 300
                    });
                    this.render();
                });
            });

        </script>
    </jsp:attribute>
    <jsp:body>
        <!-- Sub-Container for ui elements/text -->
        <div class="container">
            <div class="row mar-b-50">
                <div class="col-md-12">
                    <canvas id="editor"></canvas>
                    <button id="reset">Reset</button>                    
                    <button id="crop">crop</button>
                    <button id="sepia">sepia</button>
                    <button id="Reser">Reset</button>

                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>