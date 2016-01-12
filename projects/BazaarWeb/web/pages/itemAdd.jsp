<%-- 
    Document   : pictureView
    Created on : 13-okt-2015, 11:40:16
    Author     : jip
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/pages/langInclude.jsp" %>

<c:set var="title"><fmt:message key="itemAdd.title" /></c:set>
<t:MasterPageContent title="${title}">
    <jsp:attribute name="script">
        <script>
            $('#addItem').each(function () {
                $(this).bootstrapValidator({
                    fields: {
                        itemDescription: {
                            validators: {
                                notEmpty: {
                                    message: '<fmt:message key="itemAdd.noDescription"/>'
                                }
                            }
                        },
                        PictureControlId: {
                            validators: {
                                notEmpty: {
                                    message: '<fmt:message key="itemAdd.noImage"/>'
                                }
                            }
                        }
                    }
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <form action="${pageContext.servletContext.contextPath}/ItemAddServlet" method="POST" enctype="multipart/form-data" id="addItem">
                        <input type="file" name="PictureControl" id="PictureControlId" accept='image/*' class="form-control"><br>
                        <fmt:message key="item.active"/>
                        <input type="checkbox" name="active"
                               <c:if test ="${item.getActive()}">
                                   checked="checked" 
                               </c:if>><br>
                        <fmt:message key="pictureManage.header.price"/>:<input type="number" class="form-control" name="itemPrice"  id="photoPrice" min="0" max="99999" step="0.01" value="0"><br>
                        <fmt:message key="item.description"/>: <input type="text" value="" class="form-control" name="itemDescription"><br>
                        <button class="btn bg-blue margin" type="submit"><i class="fa fa-save pr-5"></i><fmt:message key="pictureManage.button.save"/></button>                     
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPageContent>