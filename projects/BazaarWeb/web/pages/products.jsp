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

        <div class="col-md-4">
                <form action="${pageContext.servletContext.contextPath}/ItemEdit" method="POST">
                    <div style="text-align: center; background-color: #f8f8f8;"><img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getId()}" alt="" style="height: 350px; max-width: 100%; "></div><br>

                    <fmt:message key="pictureManage.header.price"/>: ${item.getPrice()}<br>
                    <fmt:message key="item.description"/>: ${tr:translate(item.getId(),language)}<br>


                    <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i><fmt:message key="cart.edit"/></button>

                </form>
                    
        </div>
            </c:forEach>


    </jsp:body>
</t:MasterPageContent>