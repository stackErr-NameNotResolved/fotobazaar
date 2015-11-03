<%@tag description="EmptyMasterPage" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="style" fragment="true"
              description="All styles for the page." %>
<%@ attribute name="script" fragment="true"
              description="All the scripts to be added on the bottom of the page go here." %>
<%@include file="/pages/langInclude.jsp" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%-- <%@attribute name="message"%> -->

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Fotobazaar">
    <meta name="author" content="StarFeed">
    <meta name="keywords" content="Fotobazaar, Foto">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>
        Fotobazaar | Home
    </title>

    <!-- Bootstrap core CSS -->
    <link href="/BazaarWeb/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BazaarWeb/css/theme.css" rel="stylesheet">
    <link href="/BazaarWeb/css/bootstrap-reset.css" rel="stylesheet">
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet">-->

    <!--external css-->
    <link href="/BazaarWeb/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="/BazaarWeb/css/flexslider.css" rel="/stylesheet"/>
    <link href="/BazaarWeb/assets/bxslider/jquery.bxslider.css" rel="stylesheet"/>
    <link href="/BazaarWeb/css/animate.css" rel="/stylesheet">
    <link href="/BazaarWeb/assets/owlcarousel/owl.carousel.css" rel="/stylesheet">
    <link href="/BazaarWeb/assets/owlcarousel/owl.theme.css" rel="/stylesheet">
    <link href="/BazaarWeb/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="/BazaarWeb/css/superfish.css" rel="stylesheet" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'> -->


    <!-- Custom styles for this template -->
    <link href="/BazaarWeb/css/component.css" rel="stylesheet" type="text/css">
    <link href="/BazaarWeb/css/style.css" rel="stylesheet">
    <link href="/BazaarWeb/css/style-responsive.css" rel="stylesheet"/>

    <link href="/BazaarWeb/css/parallax-slider/parallax-slider.css" rel="stylesheet" type="text/css"/>

    <jsp:invoke fragment="style"/>

    <script src="/BazaarWeb/js/parallax-slider/modernizr.custom.28468.js" type="text/javascript">
    </script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js">
    </script>
    <script src="js/respond.min.js">
    </script>
    <![endif]-->
</head>

<body>
<jsp:doBody/>


<!-- js placed at the end of the document so the pages load faster
<script src="js/jquery.js">
</script>
-->
<script src="/BazaarWeb/js/jquery-1.8.3.min.js">
</script>
<script src="/BazaarWeb/js/bootstrap.min.js">
</script>
<script type="text/javascript" src="/BazaarWeb/js/hover-dropdown.js">
</script>
<script defer src="/BazaarWeb/js/jquery.flexslider.js">
</script>
<script type="text/javascript" src="/BazaarWeb/assets/bxslider/jquery.bxslider.js">
</script>

<script type="text/javascript" src="/BazaarWeb/js/jquery.parallax-1.1.3.js">
</script>
<script src="/BazaarWeb/js/wow.min.js">
</script>
<script src="/BazaarWeb/assets/owlcarousel/owl.carousel.js">
</script>

<script src="/BazaarWeb/js/jquery.easing.min.js">
</script>
<script src="/BazaarWeb/js/link-hover.js">
</script>
<script src="/BazaarWeb/js/superfish.js">
</script>
<script type="text/javascript" src="/BazaarWeb/js/parallax-slider/jquery.cslider.js">
</script>
<script type="text/javascript">
    $(function () {

        $('#da-slider').cslider({
            autoplay: true,
            bgincrement: 100
        });

    });
</script>


<!--common script for all pages-->
<script src="/BazaarWeb/js/common-scripts.js">
</script>

<script type="text/javascript">
    jQuery(document).ready(function () {


        $('.bxslider1').bxSlider({
            minSlides: 5,
            maxSlides: 6,
            slideWidth: 360,
            slideMargin: 2,
            moveSlides: 1,
            responsive: true,
            nextSelector: '#slider-next',
            prevSelector: '#slider-prev',
            nextText: 'Onward →',
            prevText: '← Go back'
        });

    });


</script>


<script>
    $('a.info').tooltip();

    $(window).load(function () {
        $('.flexslider').flexslider({
            animation: "slide",
            start: function (slider) {
                $('body').removeClass('loading');
            }
        });
    });

    $(document).ready(function () {

        $("#owl-demo").owlCarousel({
            items: 4

        });

    });

    jQuery(document).ready(function () {
        jQuery('ul.superfish').superfish();
    });

    new WOW().init();


</script>

<%-- Javascript from each view are loaded after whole page building is done. --%>
<jsp:invoke fragment="script"/>
</body>
</html>