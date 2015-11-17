<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="picture editor">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript" src="/BazaarWeb/js/CamanJS/caman.full.4.2.1.js"></script>        
        <script type="text/javascript" src="/BazaarWeb/js/JQueryUI/jquery-ui.js"></script>      
        <script type="text/javascript" src="/BazaarWeb/js/jquery.steps.js"></script>
        <script>
            $(function () {
                var Brightness = 0;
                var Saturation = 0;
                var Sepia = 0;
                var Clip = 0;
                var Blur = 0;
                var Noise = 0;
                var Hue = 0;


                Caman("#editor", "../ShowPictureServlet?imageCode=${param.imageCode}&imageSize=small", function () {
                    this.render();
                });

                $("#croptool").draggable({
                    containment: "#parent"
                });
                $("#croptool").resizable({
                    containment: "#parent",
                    handles: {
                        'nw': '#nwgrip',
                        'ne': '#negrip',
                        'sw': '#swgrip',
                        'se': '#segrip',
                        'n': '#ngrip',
                        'e': '#egrip',
                        's': '#sgrip',
                        'w': '#wgrip'
                    }
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
                        $("#Brightness").val(ui.value);
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
                        $("#Saturation").val(ui.value);
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
                        $("#Sepia").val(ui.value);
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
                        $("#Clip").val(ui.value);
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
                        $("#Blur").val(ui.value);
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
                        $("#Noise").val(ui.value);
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
                        $("#Hue").val(ui.value);
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
                    $("#slider-Brightness").slider('value', 0);

                    $("#Saturation-level").val(Saturation);
                    $("#Saturation").val(Saturation);
                    $("#slider-Saturation").slider('value', 0);

                    $("#Sepia-level").val(Sepia);
                    $("#Sepia").val(Sepia);
                    $("#slider-Sepia").slider('value', 0);

                    $("#Clip-level").val(Clip);
                    $("#Clip").val(Clip);
                    $("#slider-Clip").slider('value', 0);

                    $("#Blur-level").val(Blur);
                    $("#Blur").val(Blur);
                    $("#slider-Blur").slider('value', 0);

                    $("#Noise-level").val(Noise);
                    $("#Noise").val(Noise);
                    $("#slider-Noise").slider('value', 0);

                    $("#Hue-level").val(Hue);
                    $("#Hue").val(Hue);
                    $("#slider-Hue").slider('value', 0);
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
        <script>
            $(function () {
                $("#overlay").draggable({
                    containment: "#container",
                    drag: function (event, ui) {
                        var parentX = $("#container").offset().left;
                        var parentY = $("#container").offset().top;
                        var parentW = $("#container").width();
                        var parentH = $("#container").height();

                        var childX = $("#overlay").offset().left;
                        var childY = $("#overlay").offset().top;
                        var childW = $("#overlay").width();
                        var childH = $("#overlay").height();

                        childX = childX - parentX;
                        childY = childY - parentY;

                        var procentX = childX / parentW * 100;
                        var procentY = childY / parentH * 100;

                        var procentWidth = childW / parentW * 100;
                        var procentHeight = childH / parentH * 100;

                        $("#pictureX").val(procentX);
                        $("#pictureY").val(procentY);
                        $("#pictureWidth").val(procentWidth);
                        $("#pictureHeight").val(procentHeight);


                        $("#info").text("X:" + childX + " Y:" + childY + " | width:" + childW + "/" + parentW + " height:" + childH + "/" + parentH);
                    }
                });
                $("#overlay").resizable({
                    minHeight: 50,
                    minWidth: 50,
                    containment: "#container",
                    handles: {
                        'nw': '#nwgrip',
                        'ne': '#negrip',
                        'sw': '#swgrip',
                        'se': '#segrip',
                        'n': '#ngrip',
                        'e': '#egrip',
                        's': '#sgrip',
                        'w': '#wgrip'
                    },
                    resize: function (event, ui) {
                        var parentX = $("#container").offset().left;
                        var parentY = $("#container").offset().top;
                        var parentW = $("#container").width();
                        var parentH = $("#container").height();

                        var childX = $("#overlay").offset().left;
                        var childY = $("#overlay").offset().top;
                        var childW = $("#overlay").width();
                        var childH = $("#overlay").height();

                        childX = childX - parentX;
                        childY = childY - parentY;

                        var procentX = childX / parentW * 100;
                        var procentY = childY / parentH * 100;

                        var procentWidth = childW / parentW * 100;
                        var procentHeight = childH / parentH * 100;

                        $("#pictureX").val(procentX);
                        $("#pictureY").val(procentY);
                        $("#pictureWidth").val(procentWidth);
                        $("#pictureHeight").val(procentHeight);

                        $("#info").text("X:" + childX + " Y:" + childY + " | width:" + childW + "/" + parentW + " height:" + childH + "/" + parentH);
                    }
                });
            });
        </script>
        <script>

            var form = $("#example-form");
            form.children("div").steps({
                headerTag: "h3",
                bodyTag: "section",
                contentContainerTag: "div",
                transitionEffect: "slideLeft",
                onStepChanging: function (event, currentIndex, newIndex)
                {
                    var canvas = document.getElementById("editor");
                    var dataURL = canvas.toDataURL();
                    var height = $("#editor").height();
                    var width = $("#editor").width();

                    $("#container").css("height", height);
                    $("#container").css("width", width);

                    document.getElementById('container').style.background = 'url(' + dataURL + ') no-repeat';
                    $("#container").css("background-size", "100% 100%");
                    return true;
                },
                onFinished: function (event, currentIndex)
                {

                    alert("Submitted!");
                    form.submit();
                }
            });


        </script>
    </jsp:attribute>
    <jsp:attribute name="style">
        <link href="/BazaarWeb/css/jquery.steps.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:body>
        <!-- Sub-Container for ui elements/text -->
        <style>
            .picture {
                overflow:hidden;
                width:500px;
                height:500px;
                background-repeat:no-repeat;
            }
            #overlay {
                opacity: 1; 
                width:100%;
                height:100%;
                position: relative; 
                left: 0px; 
                top: 0px; 
                box-shadow: rgba(0, 0, 0, 0.498039) 0px 0px 0px 10000px; 
                background: rgba(255, 0, 0, 0);
            }
            #elementResizable {
                border: 1px solid #000000;
                width: 300px;
                height: 40px;
                overflow: hidden;
            }
            #nwgrip, #negrip, #swgrip, #segrip, #ngrip, #egrip, #sgrip, #wgrip {
                width: 10px;
                height: 10px;
                background-color: #ffffff;
                border: 1px solid #000000;
            }
            #nwgrip {
                left: -5px;
                top: -5px;
            }
            #negrip{
                top: -5px;
                right: -5px;
            }
            #swgrip{
                bottom: -5px;
                left: -5px;
            }
            #segrip{
                bottom: -5px;
                right:-5px;
            }
            #ngrip{
                top: -5px;
                left:50%;
            }
            #sgrip{
                bottom: -5px;
                left: 50%;
            }
            #wgrip{
                left:-5px;
                top:50%;
            }
            #egrip{
                right:-5px;
                top:50%;
            }

        </style>

        <form id="example-form" action="../FinishEditServlet" method="POST">
            <input type="hidden" name="ImageCode" value="${param.imageCode}"/>
            <div>
                <h3>Filters</h3>
                <section>
                    <div class="row" style="text-align:center;">
                        <input type="hidden" id="Brightness" name="Brightness">
                        <input type="hidden" id="Saturation" name="Saturation">
                        <input type="hidden" id="Sepia" name="Sepia">
                        <input type="hidden" id="Clip" name="Clip">
                        <input type="hidden" id="Blur" name="Blur">
                        <input type="hidden" id="Noise" name="Noise">
                        <input type="hidden" id="Hue" name="Hue">

                        <canvas id="editor" style="height:500px; max-width: 100%;"></canvas>
                        <a class="btn btn-app" >
                            <i class="fa fa-repeat" id="reset"></i> Reset
                        </a> 
                    </div>
                    <div class="row">

                        <table>
                            <tr>
                                <td><label>Brightness</label> </td>
                                <td><div id="slider-Brightness" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Brightness-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>

                                <td><label>Saturation</label> </td>
                                <td><div id="slider-Saturation" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Saturation-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                            </tr>
                            <tr>
                                <td><label>Sepia</label> </td>
                                <td><div id="slider-Sepia" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Sepia-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>

                                <td><label>Clip</label> </td>
                                <td><div id="slider-Clip" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Clip-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                            </tr>
                            <tr>
                                <td><label>Noise</label> </td>
                                <td><div id="slider-Noise" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Noise-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>

                                <td><label>Blur</label> </td>
                                <td><div id="slider-Blur" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Blur-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                            </tr>
                            <tr>
                                <td><label>Hue</label> </td>
                                <td><div id="slider-Hue" style="width:300px; margin: 0px 10px 0px 10px;"></div></td>
                                <td><input type="text" id="Hue-level" readonly style="border:0; color:#f6931f; font-weight:bold;"></td>
                            </tr>
                        </table>

                    </div>
                </section>
                <h3>Resize</h3>
                <section>
                    <div class="row" style="text-align:center; ;">

                        <div id="container" class="picture">

                            <div id="overlay">
                                <div class="ui-resizable-handle ui-resizable-nw" id="nwgrip"></div>
                                <div class="ui-resizable-handle ui-resizable-ne" id="negrip"></div>
                                <div class="ui-resizable-handle ui-resizable-sw" id="swgrip"></div>
                                <div class="ui-resizable-handle ui-resizable-se" id="segrip"></div>
                                <div class="ui-resizable-handle ui-resizable-n" id="ngrip"></div>
                                <div class="ui-resizable-handle ui-resizable-s" id="sgrip"></div>
                                <div class="ui-resizable-handle ui-resizable-e" id="egrip"></div>
                                <div class="ui-resizable-handle ui-resizable-w" id="wgrip"></div>
                            </div>    
                        </div>

                        <p id="info" style="visibility: hidden">ratio</p>
                        <input type="hidden" id="pictureX" name="pictureX" value="0"/>
                        <input type="hidden" id="pictureY" name="pictureY" value="0"/>
                        <input type="hidden" id="pictureWidth" name="pictureWidth" value="100"/>
                        <input type="hidden" id="pictureHeight" name="pictureHeight"  value="100"/>

                    </div>
                </section>
            </div>
        </form>

    </jsp:body>
</t:MasterPageContent>