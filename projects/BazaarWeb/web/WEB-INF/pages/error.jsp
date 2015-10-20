<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page isErrorPage="true" %>

<h1><%=exception.getMessage()%></h1>
<%
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    exception.printStackTrace(printWriter);
    out.println(stringWriter);
    printWriter.close();
    stringWriter.close();
%>