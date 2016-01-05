<%-- 
    Document   : orderOverview
    Created on : 8-dec-2015, 12:36:33
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="orderDetailOverview.title" /></c:set>
<c:set var="moreInfo"><fmt:message key="orderDetailOverview.moreInfo" /></c:set>
<c:set var="lessInfo"><fmt:message key="orderDetailOverview.lessInfo" /></c:set>
<c:set var="goBack"><fmt:message key="orderDetailOverview.goBack" /></c:set>
<c:set var="indexChart"><fmt:message key="orderDetailOverview.indexChart" /></c:set>
<c:set var="orderId"><fmt:message key="orderDetailOverview.orderId" /></c:set>
<c:set var="pictureCode"><fmt:message key="orderDetailOverview.pictureCode" /></c:set>
<c:set var="item"><fmt:message key="orderDetailOverview.item" /></c:set>
<c:set var="amount"><fmt:message key="orderDetailOverview.amount" /></c:set>
<c:set var="download"><fmt:message key="orderDetailOverview.download" /></c:set>
<c:set var="startX"><fmt:message key="orderDetailOverview.startX" /></c:set>
<c:set var="startY"><fmt:message key="orderDetailOverview.startY" /></c:set>
<c:set var="endX"><fmt:message key="orderDetailOverview.endX" /></c:set>
<c:set var="endY"><fmt:message key="orderDetailOverview.endY" /></c:set>
<c:set var="brightness"><fmt:message key="orderDetailOverview.brightness" /></c:set>
<c:set var="sepia"><fmt:message key="orderDetailOverview.sepia" /></c:set>
<c:set var="noise"><fmt:message key="orderDetailOverview.noise" /></c:set>
<c:set var="blur"><fmt:message key="orderDetailOverview.blur" /></c:set>
<c:set var="saturation"><fmt:message key="orderDetailOverview.saturation" /></c:set>
<c:set var="hue"><fmt:message key="orderDetailOverview.hue" /></c:set>
<c:set var="clip"><fmt:message key="orderDetailOverview.clip" /></c:set>

<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <script>
            $("button").click(function (event) {
                if ($("#orderItem" + event.target.id).css('display') === 'none') {
                    $("#orderItem" + event.target.id).show();
                    $("#"+event.target.id).html('<c:out value="${lessInfo}"/>');
                    
                }
                else {
                    $("#orderItem" + event.target.id).hide();
                    $("#"+event.target.id).html('<c:out value="${moreInfo}"/>');
                    
                }
            });
        </script>

    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row">
                <form role="form" action="../OrderOverviewServlet" method="GET" enctype="multipart/form-data">
                    <button type="submit" class="btn btn-primary btn-md pull-left">${goBack}</button>
                </form>                
                <form role="form" action="../IndexChartServlet" method="GET" enctype="multipart/form-data" >
                    <input type="hidden" name="orderId" value="${orderId}"/>
                    <button type="submit" class="btn btn-success btn-lg pull-right">${indexChart}</button>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th width="7%">${orderId}</th>
                            <th width="15%">${pictureCode}</th>
                            <th width="61%">${item}</th>
                            <th width="7%">${amount}</th>
                            <th width="10%"></th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${orderItems}" var="orderItem" varStatus="theCount">
                            <tr>
                                <td style="vertical-align: middle;">${orderItem.getId()}</td>
                                <td style="vertical-align: middle;">${orderItem.getPicture().getCode()}</td>
                                <td style="vertical-align: middle;">${orderItem.getItem().getDescription()}</td>
                                <td style="vertical-align: middle;">${orderItem.getAmount()}</td>
                                <td><button class="btn btn-primary btn-block btn-sm" id="${theCount.count}">${moreInfo}</button></td>
                            </tr>

                            <tr id="orderItem${theCount.count}" style="display:none;" class="active">
                                <td style="vertical-align: middle;"></td>                                
                                <td style="vertical-align: middle;">
                                    <form action="../ImageDownloadServlet" method="GET"> 
                                        <input type="hidden" name="imageCode" value="${orderItem.getPicture().getCode()}"/>
                                        <input type="hidden" name="imageSize" value="big"/>
                                        <button type="submit" class="btn btn-primary btn-lg btn-block">${download}</button>
                                    </form>                                    
                                </td>
                                <td style="vertical-align: middle;">
                                    <table class="table-bordered">
                                        <tr>
                                            <td>${startX}: ${orderItem.getPicture().getStartX()}</td>
                                            <td>${startY}: ${orderItem.getPicture().getStartY()}</td>
                                            <td>${endX}: ${orderItem.getPicture().getEndX()}</td>
                                            <td>${endY}: ${orderItem.getPicture().getEndY()}</td>
                                        </tr>
                                        <tr>
                                            <td>${brightness}: ${orderItem.getPicture().getBrightness()}</td>
                                            <td>${sepia}: ${orderItem.getPicture().getSepia()}</td>
                                            <td>${noise}: ${orderItem.getPicture().getNoise()}</td>
                                            <td>${blur}: ${orderItem.getPicture().getBlur()}</td>
                                            <td>${saturation}: ${orderItem.getPicture().getSaturation()}</td>
                                            <td>${hue}: ${orderItem.getPicture().getHue()}</td>
                                            <td>${clip}: ${orderItem.getPicture().getClip()}</td>
                                        </tr>
                                    </table>
                                </td>
                                <td style="vertical-align: middle;"></td>
                                <td></td>
                            </tr>                            

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>
