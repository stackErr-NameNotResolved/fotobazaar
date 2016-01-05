<%-- 
    Document   : orderOverview
    Created on : 8-dec-2015, 12:36:33
    Author     : sjorsvanmierlo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="orderDetailOverview.title" /></c:set>


<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <script>
            $("button").click(function (event) {
                if ($("#orderItem" + event.target.id).css('display') === 'none') {
                    $("#orderItem" + event.target.id).show();
                }
                else {
                    $("#orderItem" + event.target.id).hide();
                }
            });
        </script>

    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row">
                <form role="form" action="../IndexChartServlet" method="GET" enctype="multipart/form-data" >
                    <input type="hidden" name="orderId" value="${orderId}"/>
                    <button type="submit" class="btn btn-success btn-lg pull-right">Indexkaart</button>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th width="7%">OrderId</th>
                            <th width="15%">Foto code</th>
                            <th width="61%">Item</th>
                            <th width="7%">Aantal</th>
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
                                <td><button class="btn btn-primary btn-block btn-sm" id="${theCount.count}">Meer info</button></td>
                            </tr>

                            <tr id="orderItem${theCount.count}" style="display:none;" class="active">
                                <td style="vertical-align: middle;"></td>                                
                                <td style="vertical-align: middle;">
                                    <form action="../ImageDownloadServlet" method="GET"> 
                                        <input type="hidden" name="imageCode" value="${orderItem.getPicture().getCode()}"/>
                                        <input type="hidden" name="imageSize" value="big"/>
                                        <button type="submit" class="btn btn-primary btn-lg btn-block">Download</button>
                                    </form>                                    
                                </td>
                                <td style="vertical-align: middle;">
                                    <table class="table-bordered">
                                        <tr>
                                            <td>Start X: ${orderItem.getPicture().getStartX()}</td>
                                            <td>Start Y: ${orderItem.getPicture().getStartY()}</td>
                                            <td>End X: ${orderItem.getPicture().getEndX()}</td>
                                            <td>End Y: ${orderItem.getPicture().getEndY()}</td>
                                        </tr>
                                        <tr>
                                            <td>Brightness: ${orderItem.getPicture().getBrightness()}</td>
                                            <td>Sepia: ${orderItem.getPicture().getSepia()}</td>
                                            <td>Noise: ${orderItem.getPicture().getNoise()}</td>
                                            <td>Blur: ${orderItem.getPicture().getBlur()}</td>
                                            <td>Saturation: ${orderItem.getPicture().getSaturation()}</td>
                                            <td>Hue: ${orderItem.getPicture().getHue()}</td>
                                            <td>Clip: ${orderItem.getPicture().getClip()}</td>
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
