<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:MasterPageContent title="picture editor">
    <jsp:attribute name="script">
        <%-- Include your Javascript here specific for this view only ( including the <script> tags ) --%>
        <script type="text/javascript" src="/BazaarWeb/js/CamanJS/caman.full.4.2.1.js"></script>        
        <script type="text/javascript" src="/BazaarWeb/js/JQueryUI/jquery-ui.js"></script>        
        <script type="text/javascript" src="/BazaarWeb/js/pictureEdit.js"></script>        
        <script type="text/javascript" src="/BazaarWeb/js/jquery.steps.js"></script>

        <script>
            $(function () {
                $("#overlay").draggable({
                    containment: "#container"
                });
                $("#overlay").resizable({
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
                    
                    $("#container").css("height",height);                    
                    $("#container").css("width",width);        

                    document.getElementById('container').style.background='url('+dataURL+') no-repeat';
                    $("#container").css("background-size","100% 100%");
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

        <div class="container">
            <div class="row">
                <div class="col-md-12 text-right">
                    <input type="hidden" id="Brightness">
                    <input type="hidden" id="Saturation">
                    <input type="hidden" id="Sepia">
                    <input type="hidden" id="Clip">
                    <input type="hidden" id="Blur">
                    <input type="hidden" id="Noise">
                    <input type="hidden" id="Hue">

                    <input type="submit" class="btn btn-success btn-lg" value="Volgende">

                </div>
            </div>
        </div>
        <form id="example-form" action="#">
            <div>
                <h3>Filters</h3>
                <section>
                    <div class="row" style="text-align:center;">
                        <input type="hidden" id="Brightness">
                        <input type="hidden" id="Saturation">
                        <input type="hidden" id="Sepia">
                        <input type="hidden" id="Clip">
                        <input type="hidden" id="Blur">
                        <input type="hidden" id="Noise">
                        <input type="hidden" id="Hue">

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

                        <div id="container" style="background:blue;overflow:hidden;width:500px;height:500px;background-repeat:no-repeat;">
                                            
                            <div id="overlay" style="opacity: 1; width:100px;height:100px;position: relative; left: 249px; top: 105px; box-shadow: rgba(0, 0, 0, 0.498039) 0px 0px 0px 10000px; background: rgba(255, 0, 0, 0);">
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

                            <img id="CropImage" src="" style="height:500px;"/>   






                    </div>
                </section>
            </div>
        </form>

    </jsp:body>
</t:MasterPageContent>