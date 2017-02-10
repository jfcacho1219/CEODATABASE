<%-- 
    Document   : AlertEditions
    Created on : 25/01/2017, 06:12:01 PM
    Author     : SONY
--%>

<%@page import="Modelo.Alerta"%>
<!DOCTYPE html>
<%
    Alerta Alert = (Alerta)request.getAttribute("Alerta");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <title>Ediciones de Alertas</title>
    </head>
    <body>
        <form action="ControllerNotifications" method="Post">
            <div class="login-form">
                <h1>Edición de la Alerta de la empresa <%=Alert.getCompany().getNombre()%> </h1>
                <div class="form-group ">
                    <a>Edición N°</a>
                    <input type="text" readonly="readonly" class="form-control" value="<%=Alert.getId()%>" id="txtAlertaId" name="txtAlertaId">
                  <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <a>Descripción</a>
                    <input type="text" class="form-control" value="<%=Alert.getDescripcion()%>" placeholder="Descripción" id="txtDescripcion" name="txtDescripcion">
                  <i class="fa fa-user"></i>
                </div>
                <div class="form-group ">
                    <fieldset>
                        <legend>Empresa</legend>
                        <div class="form-group log-status">
                            <a>Nombre</a>
                            <input type="text" readonly="readonly" value="<%=Alert.getCompany().getNombre()%>" class="form-control" id="txtEmpresa" name="txtEmpresa">
                          <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group log-status">
                            <a>NIT</a>
                            <input type="text" readonly="readonly" value="<%=Alert.getCompany().getNIT()%>" class="form-control" id="txtNIT" name="txtNIT">
                          <i class="fa fa-lock"></i>
                        </div>
                    </fieldset>
                </div>
                 <span class="alert">Invalid Credentials</span>
                 <input type="submit" value="Editar" name="btnEditar" class="log-btn"/>
            </div>
        </form>
    </body>
</html>
