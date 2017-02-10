<%-- 
    Document   : RegistroJuntaDirectiva
    Created on : 1/12/2016, 02:22:06 PM
    Author     : SONY
--%>

<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.Persona"%>
<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    Empresa Company = (Empresa)request.getSession().getAttribute("Empresa");
    Empleado Emple = (Empleado)request.getAttribute("Encargado");
    if(Company == null)
    {
        Company = new Empresa("","","","","","","","","","","","","","","","");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >

        <title>Registro de la Junta Directiva</title>
    </head>
    <body>
        <form action="ControllerRegisterDirectors" method="Post">
            <input type="hidden" id="txtPersonaId" name="txtPersonaId" value="<%=Emple.getPersona().getId()%>" />
            <input type="hidden" id="txtEmpleadoId" name="txtEmpleadoId" value="<%=Emple.getID()%>" />

            <div class="login-form">
                <h1>Registro de la Junta Directiva de la Empresa <%=Company.getNombre()%></h1>
                <div class="form-group ">
                    <a>NIT de la Empresa</a>
                    <input readonly="readonly" value="<%=Emple.getEmpresa().getNIT()%>" type="text" class="form-control" placeholder="NIT de la empresa" id="txtNIT" name="txtNIT">
                  <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group">
                    <a id="labelOriginal">Miembro:</a>
                    <select onchange="Activartxt()" class="form-control" name="txtCalidad" id="txtCalidad">
                        <option value="Principal">Principal</option> 
                        <option value="Suplente">Suplente</option> 
                        <option value="Institucional">Institucional</option>
                        <option value="Invitado Permanente">Invitado Permanente</option> 
                        <option value="Consejero Permanente">Consejero Permanente</option>
                        <option value="OTRO">Otro</option>
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
                </fieldset>    
                        
                <address><strong>NOTA:</strong> de las siguientes opciones solo elija una.
                Si elije la primera opción, la página recargará para que registre de nuevo un comité y su encargado.
                Si elije la segunda, la página recargará para que registre los programas a los que está afiliada la empresa <%=Company.getNombre()%></address>
                <div class="form-group">
                    <input onchange="Deseleccionar('RadioProgramas')" type="checkbox" id="RadioAdministrativos" name="RadioAdministrativos" checked="checked" value="YES">
                    <label for="RadioAdministrativos">Continuar Registrando Administrativos</label>
                </div>
                
                <div class="form-group">
                    <input onchange="Deseleccionar('RadioAdministrativos')" type="checkbox" id="RadioProgramas" name="RadioProgramas"  value="YES">
                    <label for="RadioProgramas">Registrar Programas a los que pertenece la empresa</label>
                </div>
                
                <span class="alert">Invalid Credentials</span>
                <input type="submit" value="Registrar" name="btnRegistrar" class="log-btn"/>
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
