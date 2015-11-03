$(function () {
    var Brightness = 0;
    var Saturation = 0;
    var Sepia = 0;
    var Clip = 0;
    var Blur = 0;
    var Noise = 0;
    var Hue = 0;


    Caman("#editor", "../ShowPictureServlet?imageCode=LSBG0SPBSBG07G7&imageSize=small", function () {
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

