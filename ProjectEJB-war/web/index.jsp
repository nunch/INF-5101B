<%-- 
    Document   : index
    Created on : 27 mars 2015, 13:04:53
    Author     : yo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    request.getRequestDispatcher("web/index").forward(request, response);
%>