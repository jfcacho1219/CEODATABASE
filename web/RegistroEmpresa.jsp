<%-- 
    Document   : RegistroEmpresa
    Created on : 30/11/2016, 11:10:06 PM
    Author     : SONY
--%>

<!DOCTYPE html>
<%
    String Mensaje = (String)request.getAttribute("Mensaje");
    if(Mensaje==null)
    {
        Mensaje=" ";
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <script type="text/javascript" src="Scripts/Empresa.js"></script>
        <script type="text/javascript" src="Scripts/Letras.js"></script>
        
        <title>Registrar Empresas</title>
    </head>
    <body>
        <input type="text" id="txtMensaje" hidden="hidden" value="<%=Mensaje%>"/>
        <form action="ControllerRegisterCompany" method="Post">
            <div class="login-form">
                <h1>Registro de Empresa</h1>
                <div class="form-group ">
                    <a>NIT</a>
                    <input onchange="ValidarNIT()" type="text" class="form-control" placeholder="NIT " id="txtNIT" name="txtNIT">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Razón Social</a>
                    <input type="text" class="form-control" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" placeholder="Nombre " id="txtNombre" name="txtNombre">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Tipo de Sociedad</a>
                    <input type="text" class="form-control" placeholder="Sociedad" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" id="txtSociedad" name="txtSociedad">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Fecha de Creación de la Empresa</a>
                    <input type="date" class="form-control" placeholder="Año de Creación de la Empresa" id="txtAnoCreacion" name="txtAnoCreacion">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Fecha de Afliación a la CEO</a>
                    <input type="date" class="form-control" placeholder="Año de Afiliación a la CEO" id="txtAnoAfiliacion" name="txtAnoAfiliacion">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Sector Productivo</a>
                    <input type="text" class="form-control" placeholder="Sector Prodcutivo" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" id="txtSectorProductivo" name="txtSectorProductivo">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Numero de Empleados</a>
                    <input onchange="AutollenarTamano()" type="text" onkeypress="return Numeros(event)" class="form-control" placeholder="Número de Empleados" id="txtEmpleados" name="txtEmpleados">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Tamaño de la Empresa</a>
                    <input type="text" class="form-control" placeholder="Tamaño de la Empresa" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" id="txtTamano" name="txtTamano">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Actividad Económica</a>
                    <input type="text" class="form-control" placeholder="Descripción" id="txtDescripcion" name="txtDescripcion" maxlength="299" size="30">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Cuota de Sostenimiento en Pesos</a>
                    <input onchange="AutollenarSMLV()" type="text" onkeypress="return Numeros(event)" class="form-control" placeholder="Cuota de Sostenimiento $(Pesos)" id="txtSostenimientoPesos" name="txtSostenimientoPesos">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Cuota de Sostenimiento SMLV</a>
                    <input type="text" class="form-control" placeholder="Cuota de Sostenimiento SMLV" id="txtSostenimientoSMLV" name="txtSostenimientoSMLV">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Dirección de Planta</a>
                    <input onchange="AutoLlenarDireccion()" type="text" class="form-control" placeholder="Direccion de Planta" id="txtDireccionPlanta" name="txtDireccionPlanta">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Municipio donde Opera la Planta</a>
                    <input onchange="AutollenarMunicipio()" type="text" class="form-control" placeholder="Municipio donde Opera la Planta" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" id="txtMunicipioPlanta" name="txtMunicipioPlanta">
                    <i class="fa fa-user"></i>
                </div>

                <div class="form-group ">
                    <a>Dirección del Area Administrativa</a>
                    <input type="text" class="form-control" placeholder="Direccion Administrativa"  id="txtDireccionAdministrativa" name="txtDireccionAdministrativa">
                    <i class="fa fa-user"></i>
                </div>

                <div class="form-group ">
                    <a>Municipio del Area Administrativa</a>
                    <input type="text" class="form-control" placeholder="Municipio donde Opera la Administracion" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" id="txtMunicipioAdministracion" name="txtMunicipioAdministracion">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="checkbox" id="RadioActiva" name="RadioActiva" checked="checked" value="YES">
                    <label for="RadioActiva">Afiliada</label>
                    <fieldset><strong>Aclaración: </strong>Si lo único que desea es registrar la empresa
                        deseleccione "Registrar Gerentes y Administrativos", si por el contrario desea
                        registrar mas datos (Comités o Programas), deje seleccionada la opción siguiente y presione
                        el botón.
                    </fieldset>
                    <input type="checkbox" id="RadioAdministrativos" name="RadioAdministrativos" checked="checked" value="YES">
                    <label for="RadioAdministrativos">Registrar Gerentes y Administrativos</label>
                    <input onchange="VerDescripcion(this.checked)" type="checkbox" id="RadioAlerta" name="RadioAlerta" value="YES">
                    <label for="RadioAlerta">hacer que la empresa tenga una alerta</label>
                    <div id="Alerta" hidden="hidden">
                        <div class="form-group ">
                            <a>Descripción de la Alerta</a>
                            <input type="text" class="form-control" placeholder="Descripción" id="txtDescripcionAlerta" name="txtDescripcionAlerta">
                        </div>
                    </div>
                </div>
                <br>
                <span class="alert">Invalid Credentials</span>
                <input onclick="Validar()" type="button" value="Validar"  id="btnValidar" name="btnValidar" class="log-btn"/>
                <script>
                    function VerDescripcion(Valor)
                    {
                        document.getElementById("Alerta").hidden=!Valor;
                    }
                </script>
                <br>
                <br>
  
        <input hidden="hidden" type="submit" value="Registrar"  id="btnRegistrar" name="btnRegistrar" class="log-btn"/>
            </div>
            
        </form>

        <script>
            if(document.getElementById("txtMensaje").value !==" ")alert(document.getElementById("txtMensaje").value);
            var txt = document.getElementById("txtMensaje");
            txt.value = " ";
        </script>
    </body>
</html>
