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
                    containment: "#container"


                });
            });
        </script>
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
                    <div id="container">
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