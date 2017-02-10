<%-- 
    Document   : Login
    Created on : 30/11/2016, 05:42:12 PM
    Author     : SONY
--%>

<%@page import="java.lang.String"%>
<!DOCTYPE html>
<%
    String Aviso = (String)request.getAttribute("ContraIncorrecta");
    if (Aviso == null)Aviso = " ";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >

        <title>Inicia Sesión</title>
    </head>
    <body>
        <form action="ControllerLoginUser" method="Post">
            <div class="login-form">
                <h1>Inicio de Sesión</h1>
                <h1><%=Aviso%></h1>
                <div class="form-group ">
                    <input type="text" class="form-control" placeholder="Correo" id="UserName" name="txtCorreo">
                  <i class="fa fa-user"></i>
                </div>
                <div class="form-group log-status">
                  <input type="password" class="form-control" placeholder="Contraseña" id="Passwod" name="txtContrasena">
                  <i class="fa fa-lock"></i>
                </div>
                 <span class="alert">Invalid Credentials</span>
                 <input type="submit" value="Ingresar" name="btnIngresar" class="log-btn"/>
            </div>
        </form>
    </body>
</html>
