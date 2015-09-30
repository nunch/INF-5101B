<%-- 
    Document   : index
    Created on : 29 sept. 2015, 19:49:07
    Author     : yo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:useBean id="bean" class="EJB.Hello" />
        ${bean.sayHello("yo")}
    </body>
</html>
