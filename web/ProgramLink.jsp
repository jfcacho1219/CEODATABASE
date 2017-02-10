<%-- 
    Document   : ProgramLink
    Created on : 3/01/2017, 01:40:17 PM
    Author     : SONY
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Enlaces.css" type="text/css" rel="stylesheet" >
        <title>Programas</title>
    </head>
    <body>
        <h1>Programas</h1>
        <br><br><br><br><br><br><br><br><br><br><br>
        <center>
            <div class="cuatro">
                <a href="ControllerProgramLink?Object=Environmental">Ambiental</a>  <a href="ControllerProgramLink?Object=Security">Seguridad</a>  <a href="ControllerProgramLink?Object=Mypes">MYPES</a>
                <a href="ControllerProgramLink?Object=Opcion Devbida">Opción Devbida</a>  <a href="ControllerProgramLink?Object=Inactive">Inactivos</a>  <a href="ControllerProgramLink?Object=Other">Otros</a>
            </div>
        </center>
    </body>
</html>
