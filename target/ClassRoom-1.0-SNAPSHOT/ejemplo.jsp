<%-- 
    Document   : ejemplo
    Created on : 22/09/2020, 10:40:01 a. m.
    Author     : jairoarmando
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
        <h1>Ejemplo con JSP</h1>
        <label for="age">Edad</label>
        <select id="age">
        <%
          for ( int age = 12 ; age <= 120 ; age++ ){
                out.print("<option>" + age + "</option>");
            }  
        %>
        </select>
    </body>
</html>
