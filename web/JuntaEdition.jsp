<%-- 
    Document   : JuntaEdition
    Created on : 13/12/2016, 06:59:45 PM
    Author     : SONY
--%>

<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.JuntaDirectiva"%>
<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    Empleado Emple = (Empleado)request.getAttribute("Empleado");
    JuntaDirectiva Junta = (JuntaDirectiva)request.getAttribute("Junta");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >

        <title>Edición de la Junta Directiva</title>
    </head>
    <body>
        <form action="ControllerEditions" method="Post">
            <input type="hidden" id="txtPersonaId" name="txtPersonaId" value="<%=Emple.getPersona().getId()%>" />
            <input type="hidden" id="txtEmpleadoId" name="txtEmpleadoId" value="<%=Emple.getID()%>" />
            <input type="hidden" id="txtJuntaId" name="txtJuntaId" value="<%=Junta.getID()%>" />
            <input type="hidden" id="txtObjeto" name="txtObjeto" value="Junta Directiva"/>

            <div class="login-form">
                
                <h1>Edición de la Junta Directiva de la Empresa <%=Emple.getEmpresa().getNombre()%></h1>
                
                <div class="form-group">
                    <input type="checkbox" id="RadioEliminar" name="RadioEliminar" value="YES">
                    <label for="RadioEliminar">Eliminar</label>
                </div>
                
                <div class="form-group ">
                    <a>NIT de la Empresa</a>
                    <input readonly="readonly" value="<%=Emple.getEmpresa().getNIT()%>" type="text" class="form-control" placeholder="NIT de la empresa" id="txtNIT" name="txtNIT">
                  <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group">
                    <a id="labelOriginal">Miembro:</a>
                    <select onchange="Activartxt()" class="form-control" name="txtCalidad" id="txtCalidad">
                        <script>
                            var VectorJunta = ["Principal","Suplente","Institucional","Invitado Permanente","Consejero Permanente","OTRO"];
                            var JuntaNombre = "<%=Junta.getCalidad()%>";
                            document.write('<option value="'+JuntaNombre+'">'+JuntaNombre+'</option>');
                            for (var i = 0; i < VectorJunta.length; i++) {
                                if(JuntaNombre!==VectorJunta[i])
                                {
                                    document.write('<option value="'+VectorJunta[i]+'">'+VectorJunta[i]+'</option>');
                                }
                            }
                        </script>
                    </select>
                </div>
                <script>
                    function Activartxt()
                    {
                        var list = document.getElementById("txtCalidad").value;
                        if(list === "OTRO")
                        {
                            document.getElementById("txtCalidad").hidden = "hidden";
                            document.getElementById("labelOriginal").hidden = "hidden";
                            document.getElementById("txtCalidad2").hidden = false;
                            document.getElementById("labelCual").hidden = false;
                        }  
                    }
                </script>
                <div class="form-group ">
                    <a id="labelCual" hidden="hidden">Cual</a>
                    <input type="text" hidden="hidden" class="form-control" placeholder="En calidad de:" id="txtCalidad2" name="txtCalidad2">
                    <i class="fa fa-user"></i>
                </div>
                
                <fieldset>
                    <legend><strong>Encargado:</strong></legend>    
                    <div class="form-group ">
                        <a>Nombre</a>
                        <input readonly="readonly" type="text" value="<%=Emple.getPersona().getNombre()%> <%=Emple.getPersona().getApellidos()%>" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" id="txtNombreCompleto" name="txtNombreCompleto">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group ">
                        <a>Email</a>
                        <input readonly="readonly" type="text" value="<%=Emple.getEmail1()%>" id="txtEmail" class="form-control" name="txtEmail">
                        <i class="fa fa-user"></i>
                    </div>
                    <script>
                        var NIT= "<%=Emple.getEmpresa().getNIT()%>";
                        var PersonaId ="<%=Emple.getPersona().getId()%>";
                        var EmpleadoId = "<%=Emple.getID()%>";
                        var PersonaNombreyApellidos = "<%=Emple.getPersona().getNombre()%> <%=Emple.getPersona().getApellidos()%>";
                        document.write("<a href=\"ControllerEditions?Object=Directive&NIT="+NIT+"&PersonaId="+PersonaId+"&EmpleadoId="+EmpleadoId+"\">Editar a "+PersonaNombreyApellidos+" <img width=\"50\" src=\"Lapiz3.gif\"></a>");
                    </script>
                </fieldset>    
                        
                <span class="alert">Invalid Credentials</span>
                <input type="submit" value="Editar" name="btnEditar" class="log-btn"/>
            </div>
        </form>
        <script>
            function Deseleccionar(ID)
            {
                var radio = document.getElementById(ID);
                radio.checked = false;
            }
        </script>
    </body>
</html>
