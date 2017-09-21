<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Cabecera
    Created on : 29/11/2016, 05:00:13 PM
    Author     : SONY
--%>

<%@page import="Modelo.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Administrador Admi = (Administrador)request.getSession().getAttribute("Administrador");
    String Url = "Login.jsp";
    String Validar = "Inicia Sesión";
    if(Admi != null)
    {
        Validar = "Bienvenid@ "+Admi.getNombre();
        Url = "Login.jsp";
    }
%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/IndexStyle.css" type="text/css" rel="stylesheet" >
        <link href="CSS/ListaDesplegable.css" type="text/css" rel="stylesheet" >

        <div id="barra_superior">
            <ul class="navegacion">
                <li><a href="Inicio.jsp">Inicio</a></li>
                <li><a href="RegistroAdministrador.jsp">Regístrate</a></li>
                <li><a href="#">Información</a></li> 
                <li><a href="ControllerNotifications?Object=Notifications">Notifiaciones</a></li>
                <li><a href="ControllerNotifications?Object=Alert">Alertas</a></li>
                <li><a href=<%=Url%>><%=Validar %></a></li>
                <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn" onclick="myFunction()">Recorrido</a>
                  <div class="dropdown-content" id="myDropdown">
                    <a href="CreateReadUpdateDelete.jsp">Crear Leer Editar Eliminar </a>
                    <a href="CompanyLink.jsp">Empresas</a>
                    <a href="CommitteeLink.jsp">Comités</a>
                    <a href="ControllerOfLink?Object=Directorate">Junta Directiva</a>
                    <a href="ProgramLink.jsp">Programas</a>
                    <a href="Search.jsp">Buscar</a>
                  </div>
                </li> 
            </ul>
        </div>
        <script>
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }
        // Close the dropdown if the user clicks outside of it
        window.onclick = function(e) {
          if (!e.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            for (var d = 0; d < dropdowns.length; d++) {
              var openDropdown = dropdowns[d];
              if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
              }
            }
          }
        }
    </script>  
        
</head>
    
    <body> <img src="LOGO CEO.png" align="right" width="20%"/> </body>

