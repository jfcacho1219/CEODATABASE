<%-- 
    Document   : RegistroAdministrador
    Created on : 30/11/2016, 12:39:20 PM
    Author     : SONY
--%>

<!DOCTYPE html>
<%
    String OtraContrasena = (String)request.getSession().getAttribute("OtraContrasena");
    if(OtraContrasena==null)OtraContrasena="";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        
        
        <title>Registro Administrador</title>
    </head>
    <body>
        <br>
        <div class="login-form">
            <br>
            <form action="ControllerRegister" method="Post">
                <h1>Regístrate</h1>
                <div class="form-group ">
                    <input class="form-control" placeholder="Correo" type="text" id="txtCorreo" name="txtCorreo" size="30"> <address>Correo</address> <br>
                  <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <input class="form-control" placeholder="Nombre" type="text" id="txtNombre" name="txtNombre" size="30"> <address>Nombre</address> <br>
                  <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <input class="form-control" placeholder="Apellidos" type="text" id="txtApellidos" name="txtApellidos" size="30" /><address>Apellidos</address><br>
                <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <input class="form-control" placeholder="Contraseña del Administrador" type="Password" id="txtContrasenaAdmi" name="txtContrasenaAdmi" size="30" /><address>Contraseña de Administrador</address> <br>
                <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <input class="form-control" placeholder="Contraseña" type="Password" id="txtContrasena" name="txtContrasena" size="30" /><address>Contraseña</address> <br>
                <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <input class="form-control" placeholder="Confirmar Contraseña" type="Password" id="txtConfirmarContrasena" name="txtConfirmarContrasena" size="30" /><address>Confirmar Contraseña</address><br>
                <i class="fa fa-user"></i>
                </div>
                <input class="log-btn" type="submit" value="Registrar" id="btnRegistrarr" name="btnRegistrarr" />
                   
            </form>
            <h5 id="Alerta"><%=OtraContrasena%></h5>
            <br>
        </div>
    </body>
</html>
