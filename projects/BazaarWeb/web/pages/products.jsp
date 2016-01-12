<%-- 
    Document   : products
    Created on : 1-dec-2015, 15:30:10
    Author     : Fatih
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>
<%@taglib prefix="tr" uri="/WEB-INF/tld/TranslateLibrary.tld" %>
<%@page import="classes.domain.Translate"%>

<c:set var="title"> <fmt:message key="cart.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:attribute name="style">

    </jsp:attribute>
    <jsp:attribute name="script">
    </jsp:attribute>

    <jsp:body>

        <c:forEach items="${items}" var="item">
            <div class="col-md-4" style="border: 1px solid #f8f8f8;">
                <form action="${pageContext.servletContext.contextPath}/ProductsServlet" method="GET">
                    <br>
                    <h4>${tr:translate(item.getId(),language)}</h4>
                    <br>
                    <br>
                    <img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getId()}" alt="" style="height: 350px; max-width: 100%; ">
                    <br>
                    <br>
                    <fmt:message key="pictureManage.header.price"/>: <b>${item.getPrice()}</b>
                    <button class="btn bg-blue margin pull-right"><i class="fa fa-save pr-5"></i><fmt:message key="cart.choose"/></button>
                    <input type="hidden" value="${item.getId()}" name="ProductId"/>                    
                    <input type="hidden" value="${orderId}" name="OrderId"/>

                </form>
            </div>
        </c:forEach>

    </jsp:body>
</t:MasterPageContent>