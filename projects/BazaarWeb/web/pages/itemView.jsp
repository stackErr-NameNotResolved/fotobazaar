<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : jip
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="itemView.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    
                     <div class="row">
                    <form action="${pageContext.servletContext.contextPath}/ItemAdd" method="GET">
                        <button class="btn bg-green margin btn pull-right"><i class="fa fa-save pr-5"></i><fmt:message key="itemView.new"/></button>
                    </form>
                     </div>
                    
                    <c:forEach items="${items}" var="item">
                        <form action="${pageContext.servletContext.contextPath}/ItemEdit" method="POST">
                            <div style="text-align: center; background-color: #f8f8f8;"><img src="${pageContext.servletContext.contextPath}/ShowPictureServlet?imageCode=${item.getId()}" alt="" style="height: 500px; max-width: 100%; "></div><br>
                            <fmt:message key="item.active"/>:
                            <input type="checkbox" name="active" disabled="disabled"
                                   <c:if test ="${item.getActive()}">
                                       checked="checked" 
                                   </c:if>><br>
                            <fmt:message key="pictureManage.header.price"/>: ${item.getPrice()}<br>
                            <fmt:message key="item.description"/>: ${item.toString()}<br>
                            <input type="hidden" value="${item.getId()}" name="redirectItem"/>
                            <button class="btn bg-blue margin"><i class="fa fa-save pr-5"></i><fmt:message key="cart.edit"/></button>
                            <hr>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>