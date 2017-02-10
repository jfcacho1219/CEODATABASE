<%-- 
    Document   : CommitteeLink
    Created on : 2/01/2017, 04:57:48 PM
    Author     : SONY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Enlaces.css" type="text/css" rel="stylesheet" >
        <title>Comités</title>
    </head>
    <body>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <center>
            <div class="cuatro">
                <a href="ControllerCommittee?Object=Manager">Gerentes</a>  <a href="ControllerCommittee?Object=HumanManagement">Gestión Humana</a>  <a href="ControllerCommittee?Object=InternationalBusiness">Comercio Exterior</a>  <a href="ControllerCommittee?Object=Technical">Técnico</a>  <a href="ControllerCommittee?Object=Purchases">Compras</a><br><br><br><br><br>
                <a href="ControllerCommittee?Object=Security">Seguridad</a>  <a href="ControllerCommittee?Object=Environmental">Ambiental</a> <a href="ControllerCommittee?Object=Tics">TICS</a> <a href="ControllerCommittee?Object=Mypes">MYPES</a> <a href="ControllerCommittee?Object=Opcion Devbida">Opción Devbida</a>  <a href="ControllerCommittee?Object=Inactive">Inactivos</a> <a href="ControllerCommittee?Object=Other">Otros</a>
            </div>
        </center>
    </body>
</html>
