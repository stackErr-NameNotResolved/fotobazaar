<%@tag description="EmptyMasterPage" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" description="Header of the page to be shown in the theme." %>
<%@ attribute name="style" fragment="true"
              description="All styles for the page." %>
<%@ attribute name="script" fragment="true"
              description="All the scripts to be added on the bottom of the page go here." %>
<%@include file="/pages/langInclude.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-reset.css" rel="stylesheet">
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet">-->

    <!--external css-->
    <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/flexslider.css" rel="/stylesheet"/>
    <link href="${pageContext.request.contextPath}/assets/bxslider/jquery.bxslider.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="/stylesheet">
    <link href="${pageContext.request.contextPath}/assets/owlcarousel/owl.carousel.css" rel="/stylesheet">
    <link href="${pageContext.request.contextPath}/assets/owlcarousel/owl.theme.css" rel="/stylesheet">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link href="${pageContext.request.contextPath}/css/superfish.css" rel="stylesheet" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'> -->


    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/component.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet"/>

    <link href="${pageContext.request.contextPath}/css/parallax-slider/parallax-slider.css" rel="stylesheet" type="text/css"/>

    <jsp:invoke fragment="style"/>

    <script src="${pageContext.request.contextPath}/js/parallax-slider/modernizr.custom.28468.js" type="text/javascript">
    </script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/js/html5shiv.js">
    </script>
    <script src="${pageContext.request.contextPath}js/respond.min.js">
    </script>
    <![endif]-->
</head>

<body>
<jsp:doBody/>


<!-- js placed at the end of the document so the pages load faster
<script src="js/jquery.js">
</script>
-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hover-dropdown.js">
</script>
<script defer src="${pageContext.request.contextPath}/js/jquery.flexslider.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bxslider/jquery.bxslider.js">
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.parallax-1.1.3.js">
</script>
<script src="${pageContext.request.contextPath}/js/wow.min.js">
</script>
<script src="${pageContext.request.contextPath}/assets/owlcarousel/owl.carousel.js">
</script>

<script src="${pageContext.request.contextPath}/js/jquery.easing.min.js">
</script>
<script src="${pageContext.request.contextPath}/js/link-hover.js">
</script>
<script src="${pageContext.request.contextPath}/js/superfish.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/parallax-slider/jquery.cslider.js">
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
<script src="${pageContext.request.contextPath}/js/common-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

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
<script src="${pageContext.servletContext.contextPath}/js/disableContextMenu.js"></script>

<%-- Javascript from each view are loaded after whole page building is done. --%>
<jsp:invoke fragment="script"/>
</body>
</html>