<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="picture editor">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript" src="/BazaarWeb/js/CamanJS/caman.full.4.2.1.js"></script>        
        <script type="text/javascript" src="/BazaarWeb/js/JQueryUI/jquery-ui.js"></script>
        <script type="text/javascript">
            $(function () {
                var Brightness = 0;
                var Saturation = 0;
                var Sepia = 0;
                var Clip = 0;
                var Blur = 0;
                var Noise = 0;
                var Hue = 0;

                
                Caman("#editor", "../ShowPictureServlet?imageId=4&imageSize=small", function () {
                    this.render();
                });


                // Slider implementation //

                // Brightness
                $("#slider-Brightness").slider({
                    orientation: "horizontal", range: "min",
                    min: -100, max: 100, value: 0,
                    slide: function (event, ui) {
                        $("#Brightness-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Brightness = ui.value;
                        renderCanvas();
                    }
                });

                //Saturnation
                $("#slider-Saturation").slider({
                    orientation: "horizontal", range: "min",
                    min: -100, max: 100, value: 0,
                    slide: function (event, ui) {
                        $("#Saturation-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Saturation = ui.value;
                        renderCanvas();
                    }
                });

                //Sepia
                $("#slider-Sepia").slider({
                    orientation: "horizontal", range: "min",
                    min: 0, max: 100, value: 0,
                    slide: function (event, ui) {
                        $("#Sepia-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Sepia = ui.value;
                        renderCanvas();
                    }
                });

                //Clip
                $("#slider-Clip").slider({
                    orientation: "horizontal", range: "min",
                    min: 0, max: 100, value: 0,
                    slide: function (event, ui) {
                        $("#Clip-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Clip = ui.value;
                        renderCanvas();
                    }
                });

                //Blur
                $("#slider-Blur").slider({
                    orientation: "horizontal", range: "min",
                    min: 0, max: 20, value: 0,
                    slide: function (event, ui) {
                        $("#Blur-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Blur = ui.value;
                        renderCanvas();
                    }
                });

                //Noise
                $("#slider-Noise").slider({
                    orientation: "horizontal", range: "min",
                    min: 0, max: 100, value: 0,
                    slide: function (event, ui) {
                        $("#Noise-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Noise = ui.value;
                        renderCanvas();
                    }
                });

                //Hue
                $("#slider-Hue").slider({
                    orientation: "horizontal", range: "min",
                    min: 0, max: 100, value: 0,
                    slide: function (event, ui) {
                        $("#Hue-level").val(ui.value);
                    },
                    stop: function (event, ui) {
                        Hue = ui.value;
                        renderCanvas();
                    }
                });
                resetFilterValues();
                // Render Canvas image
                function renderCanvas()
                {
                    Caman("#editor", function () {
                        this.revert(false);
                        this.brightness(Brightness);
                        this.saturation(Saturation);
                        this.sepia(Sepia);
                        this.clip(Clip);
                        this.stackBlur(Blur);
                        this.noise(Noise);
                        this.hue(Hue);
                        this.render();
                    });
                }

                function setFilterValues()
                {
                    $("#Brightness-level").val(Brightness);
                    $("#Brightness").val(Brightness);
                    $("#slider-Brightness").slider('value',0);
                    
                    $("#Saturation-level").val(Saturation);
                    $("#Saturation").val(Saturation);
                    $("#slider-Saturation").slider('value',0);
                    
                    $("#Sepia-level").val(Sepia);
                    $("#Sepia").val(Sepia);
                    $("#slider-Sepia").slider('value',0);
                    
                    $("#Clip-level").val(Clip);
                    $("#Clip").val(Clip);
                    $("#slider-Clip").slider('value',0);
                    
                    $("#Blur-level").val(Blur);
                    $("#Blur").val(Blur);
                    $("#slider-Blur").slider('value',0);
                    
                    $("#Noise-level").val(Noise);
                    $("#Noise").val(Noise);
                    $("#slider-Noise").slider('value',0);
                    
                    $("#Hue-level").val(Hue);
                    $("#Hue").val(Hue);
                    $("#slider-Hue").slider('value',0);
                }

                function resetFilterValues()
                {
                    Brightness = 0;
                    Saturation = 0;
                    Sepia = 0;
                    Clip = 0;
                    Blur = 0;
                    Noise = 0;
                    Hue = 0;
                    setFilterValues();
                }

                $("#reset").click(function () {
                    resetFilterValues();
                    Caman("#editor", function () {
                        this.revert(false);
                        this.render();
                    });
                });

            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <!-- Sub-Container for ui elements/text -->

        <div class="container">
            <div class="row">
                <div class="col-md-12 text-right">
                    <form action="" method="POST">
                        <input type="hidden" id="Brightness">
                        <input type="hidden" id="Saturation">
                        <input type="hidden" id="Sepia">
                        <input type="hidden" id="Clip">
                        <input type="hidden" id="Blur">
                        <input type="hidden" id="Noise">
                        <input type="hidden" id="Hue">

                        <input type="submit" class="btn btn-success btn-lg" value="Volgende">
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div style="text-align: center; background-color: #f8f8f8;">
                        <canvas id="editor" style="height:500px; max-width: 100%;"></canvas>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 text-right">
                    <a class="btn btn-app" >
                        <i class="fa fa-repeat" id="reset"></i> Reset
                    </a> 
                </div>
                <div class="col-md-8">
                    <table>
                        <tr>
                            <td><label>Brightness :</label> </td>
                            <td><div id="slider-Brightness" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Brightness-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                        <tr>
                            <td><label>Saturation :</label> </td>
                            <td><div id="slider-Saturation" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Saturation-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                        <tr>
                            <td><label>Sepia :</label> </td>
                            <td><div id="slider-Sepia" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Sepia-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                        <tr>
                            <td><label>Clip :</label> </td>
                            <td><div id="slider-Clip" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Clip-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                        <tr>
                            <td><label>Noise :</label> </td>
                            <td><div id="slider-Noise" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Noise-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                        <tr>
                            <td><label>Blur :</label> </td>
                            <td><div id="slider-Blur" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Blur-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                        <tr>
                            <td><label>Hue :</label> </td>
                            <td><div id="slider-Hue" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                            <td><input type="text" id="Hue-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>


    </jsp:body>
</t:MasterPageContent>