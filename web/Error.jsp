<%-- 
    Document   : Error
    Created on : 30/11/2016, 02:33:21 PM
    Author     : SONY
--%>

<!DOCTYPE html>
<%
    String Error = (String)request.getAttribute("Error");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <title>Error <%=Error%></title>
    </head>
    <body>
        <h1><%=Error%></h1>
    </body>
</html>
