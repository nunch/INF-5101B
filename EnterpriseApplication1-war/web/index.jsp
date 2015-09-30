<%-- 
    Document   : index
    Created on : 29 sept. 2015, 19:49:07
    Author     : yo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:useBean id="bean"  class="EJB.Hello" />
        Valeur accumulateur = ${bean.number}<br/>
        <form action="index" method="post">
            Value : <input type="text" name ="add"/> <br/>
            <input type="submit" value="Ajouter">
        </form>
    </body>
</html>
