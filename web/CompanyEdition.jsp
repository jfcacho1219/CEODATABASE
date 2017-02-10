<%-- 
    Document   : RegistroEmpresa
    Created on : 30/11/2016, 11:10:06 PM
    Author     : SONY
--%>

<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    Empresa Company = (Empresa)request.getAttribute("Company");
    String Read = (String)request.getAttribute("ReadOnly");
    if(Read == null) Read = "text";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <script type="text/javascript" src="Scripts/Empresa.js"></script>
        <script type="text/javascript" src="Scripts/Letras.js"></script>
        <script type="text/javascript" src="Scripts/Avisos.js"></script>
        <title>Editar Empresa: "<%=Company.getNombre()%> <%=Company.getSociedad()%>"</title>
    </head>
    <body>
        
        <form action="ControllerEditions" method="Post">
            <input type="text" id="txtObjeto" name="txtObjeto" hidden="hidden" value="Empresa"/>
            <input type="text" id="txtNITOriginal" name="txtNITOriginal" hidden="hidden" value="<%=Company.getNIT()%>"/>
            <div class="login-form" hidden="!<%=Read%>" >
                <h1>Editar Empresa: "<%=Company.getNombre()%> <%=Company.getSociedad()%>"</h1>
            </div>
            
                <div class="login-form">
                    <h1>Editar Empresa: "<%=Company.getNombre()%> <%=Company.getSociedad()%>"</h1>
                    
                    <div <%=Read%>>
                        <div class="form-group">
                            <input onchange="Aviso()" type="checkbox" id="RadioEliminar" name="RadioEliminar"  value="YES">
                            <label for="RadioEliminar">¿Eliminar?</label>
                        </div>
                        <div class="form-group ">
                            <a>NIT</a>
                            <input onchange="ValidarNIT()" value="<%=Company.getNIT()%>" type="text" class="form-control" placeholder="NIT " id="txtNIT" name="txtNIT">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Razón Social</a>
                            <input  value="<%=Company.getNombre()%>" type="text" class="form-control" placeholder="Nombre " id="txtNombre" name="txtNombre">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Tipo de Sociedad</a>
                            <input value="<%=Company.getSociedad()%>" type="text" class="form-control" placeholder="Sociedad" id="txtSociedad" name="txtSociedad">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Fecha de Creación de la Empresa</a>
                            <input value="<%=Company.getAnoCreacion()%>" type="date" class="form-control" placeholder="Año de Creación de la Empresa" id="txtAnoCreacion" name="txtAnoCreacion">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Fecha de Afliación a la CEO</a>
                            <input value="<%=Company.getAnoAfiliacion()%>" type="date" class="form-control" placeholder="Año de Afiliación a la CEO" id="txtAnoAfiliacion" name="txtAnoAfiliacion">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Sector Productivo</a>
                            <input value="<%=Company.getSectorProductivo()%>" type="text" class="form-control" placeholder="Sector Prodcutivo" id="txtSectorProductivo" name="txtSectorProductivo">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Numero de Empleados</a>
                            <input onchange="AutollenarTamano()" value="<%=Company.getNumeroEmpleados()%>" type="text" onkeypress="return Numeros(event)" class="form-control" placeholder="Número de Empleados" id="txtEmpleados" name="txtEmpleados">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Tamaño de la Empresa</a>
                            <input value="<%=Company.getTamanoEmpresa()%>" type="text" class="form-control" placeholder="Tamaño de la Empresa" id="txtTamano" name="txtTamano">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Descripción</a>
                            <input value="<%=Company.getDescripcion()%>" type="text" class="form-control" placeholder="Descripción" id="txtDescripcion" name="txtDescripcion" maxlength="299" size="30">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Cuota de Sostenimiento en Pesos</a>
                            <input onchange="AutollenarSMLV()" value="<%=Company.getSostenimientoPesos()%>" onkeypress="return Numeros(event)" type="text" class="form-control" placeholder="Cuota de Sostenimiento $(Pesos)" id="txtSostenimientoPesos" name="txtSostenimientoPesos">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Cuota de Sostenimiento SMLV</a>
                            <input value="<%=Company.getSostemientoSMLV()%>" onkeypress="return Numeros(event)" type="text" class="form-control" placeholder="Cuota de Sostenimiento SMLV" id="txtSostenimientoSMLV" name="txtSostenimientoSMLV">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Dirección de Planta</a>
                            <input onchange="AutoLlenarDireccion()" value="<%=Company.getDireccionPlanta()%>" type="text" class="form-control" placeholder="Direccion de Planta" id="txtDireccionPlanta" name="txtDireccionPlanta">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Municipio donde Opera la Planta</a>
                            <input onchange="AutollenarMunicipio()" value="<%=Company.getMunicipioPlanta()%>" type="text" class="form-control" placeholder="Municipio donde Opera la Planta" id="txtMunicipioPlanta" name="txtMunicipioPlanta">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Dirección del Area Administrativa</a>
                            <input value="<%=Company.getDireccionAdministrativa()%>" type="text" class="form-control" placeholder="Direccion Administrativa" id="txtDireccionAdministrativa" name="txtDireccionAdministrativa">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Municipio del Area Administrativa</a>
                            <input value="<%=Company.getMunicipioAdminitrativa()%>" type="text" class="form-control" placeholder="Municipio donde Opera la Administracion" id="txtMunicipioAdministracion" name="txtMunicipioAdministracion">
                            <i class="fa fa-user"></i>
                        </div>

                            <div class="form-group">
                                <input type="checkbox" id="RadioActiva" name="RadioActiva" checked="checked" value="YES">
                                <label for="RadioActiva">¿La Empresa es Activa?</label>
                            </div>
                    </div>
                    <div class="form-group">
                        <input onchange="VerDescripcion(this.checked)" type="checkbox" id="RadioAlerta" name="RadioAlerta" value="YES">
                        <label for="RadioAlerta">hacer que la empresa tenga una alerta</label>

                    </div>
                    <div id="Alerta" hidden="hidden">
                            <div class="form-group ">
                                <a>Descripción de la Alerta</a>
                                <input type="text" class="form-control" placeholder="Descripción" id="txtDescripcionAlerta" name="txtDescripcionAlerta">
                            </div>
                    </div>
                    <script>
                            function VerDescripcion(Valor)
                            {
                                document.getElementById("Alerta").hidden=!Valor;
                            }
                    </script>
                    <input type="submit" value="Confirmar Edición"  id="btnEditar" name="btnEditar" class="log-btn"/>
                </div>
        </form>

        
        <script>
            if(document.getElementById("txtMensaje").value !==" ")alert(document.getElementById("txtMensaje").value);
            var txt = document.getElementById("txtMensaje");
            txt.value = " ";
        </script>
    </body>
</html>
