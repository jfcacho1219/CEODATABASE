<%-- 
    Document   : CreateReadUpdateDelete
    Created on : 7/12/2016, 09:46:34 AM
    Author     : SONY
--%>

<!DOCTYPE html>
<%
    String Mensaje = (String)request.getAttribute("Mensaje");
    if(Mensaje==null) Mensaje = " ";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Enlaces.css" type="text/css" rel="stylesheet" >
        <title>Crear, Leer, Editar y Eliminar</title>
    </head>
    <body>
        <input type="hidden" name="txtMensaje" id="txtMensaje" value="<%=Mensaje%>" />
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <center>
            <div class="cuatro">
                <a href="CompanyLink.jsp">Empresas</a>  <a href="CommitteeLink.jsp">Comités</a> <br><br><br><br><br>
                <a href="ControllerOfLink?Object=Program">Programas</a>  <a href="ControllerOfLink?Object=Directorate">Junta Directiva</a>
            </div>
        </center>
        <script>
            if(document.getElementById("txtMensaje").value!==" ")alert("<%=Mensaje%>");
            document.getElementById("txtMensaje").value = " ";
        </script>
    </body>
</html>
