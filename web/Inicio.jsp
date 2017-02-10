<%-- 
    Document   : Inicio
    Created on : 29/11/2016, 05:16:23 PM
    Author     : SONY
--%>

<!DOCTYPE html>
<%
    String Mensaje = (String)request.getAttribute("Mensaje");
    if(Mensaje==null) Mensaje =" ";
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Enlaces.css" type="text/css" rel="stylesheet" >
        <title>Inicio CEO</title>
    </head>
    <body>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <center>
        <div class="cuatro">
            <a href="Search.jsp"> Buscar </a>  <a href="CreateReadUpdateDelete.jsp"> Crear, Leer, Editar y Eliminar </a>
	</div>
    </center>
    <script>
        var Mensaje = "<%=Mensaje%>";
        if(Mensaje !==" ")alert(Mensaje);
    </script>
        
    </body>
</html>
