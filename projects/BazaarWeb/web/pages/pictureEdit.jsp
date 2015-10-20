<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="picture editor">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript" src="/BazaarWeb/js/CamanJS/caman.full.4.2.1.js"></script>        
        <script type="text/javascript" src="/BazaarWeb/js/JQueryUI/jquery-ui.js"></script>
        <script type="text/javascript">
            $(function () {
                var brightness;
                var sepia = 0;

                $("#slider-vertical").slider({
                    orientation: "vertical",
                    range: "min",
                    min: -100,
                    max: 100,
                    value: 0,
                    slide: function (event, ui) {
                        $("#amount").val(ui.value);
                    },
                    stop: function (event, ui) {
                        
                        var curVal = ui.value;
                        Caman("#editor", "../ShowPictureServlet?imageId=6&imageSize=big", function () {
                            this.revert(false);
                            this.brightness(curVal);
                            this.sepia(sepia);
                            this.render();
                        });
                    }
                });
                $("#amount").val();

            });

            Caman("#editor", "../ShowPictureServlet?imageId=6&imageSize=big", function () {
               
                this.render();
            });

            $("#reset").click(function () {
                Caman("#editor", "../ShowPictureServlet?imageId=6&imageSize=big", function () {
                    this.revert(false);
                    this.render();
                });
            });

            $("#brightness").click(function () {
                Caman("#editor", function () {
                    this.brightness(10);
                    this.render();
                });
            });

            $("#sepia").click(function () {
                Caman("#editor", function () {
                    sepia = sepia + 20;
                    alert(sepia);
                    this.sepia(20);
                    this.render();
                });
            });

            $("#contrast").click(function () {
                Caman("#editor", function () {
                    this.contrast(10);
                    this.render();
                });
            });

            $("#noise").click(function () {
                Caman("#editor", function () {
                    this.noise(10);
                    this.render();
                });
            });

        </script>
    </jsp:attribute>
    <jsp:body>
        <!-- Sub-Container for ui elements/text -->
        <div class="row">
            <div class="col-md-12">
                <button id="reset">Reset</button>   
                <button id="sepia">sepia</button>
                <button id="brightness">Brightness</button>                    
                <button id="contrast">contrast</button>
                <button id="noise">noise</button>    
                <p>
                    <label for="amount">Volume:</label>
                    <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
                </p>
                <div id="slider-vertical" style="height:200px;"></div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div style="text-align: center; background-color: #f8f8f8;">
                        <canvas id="editor" style="height:500px; max-width: 100%;"></canvas>
                    </div>
                </div>
            </div>


        </div>
    </jsp:body>
</t:MasterPageContent>