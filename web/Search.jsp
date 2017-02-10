<%-- 
    Document   : Search
    Created on : 6/01/2017, 04:36:24 PM
    Author     : SONY
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Enlaces.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <title>Buscar...</title>
    </head>
    <body>
        
        <form action="ControllerSearch" method="Post">
            
            <div class="login-form">
                <h1>Buscar por</h1>
                <select onchange="FiltroObjeto(this.value)" class="form-control" required="required" name="txtObjeto" id="txtObjeto">
                    <option>Elegir</option>
                    <option value="Empresa">Empresas</option>
                    <option value="Comite">Comites</option>
                    <option value="Programas">Programas</option>
                </select>
                <br><br>
                <input type="submit" value="Buscar"  id="btnBuscar" name="btnBuscar"  hidden="hidden" class="log-btn"/>
            </div>
            <div id="Empresa" hidden="hidden">
                <div class="login-form">
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
                    <fieldset>
                        <legend>Fechas de Creación</legend>

                        <div class="form-group ">
                            <a>Creación de la Empresa <strong>(Primer Rango)</strong></a>
                            <input type="date" class="form-control" placeholder="Año de Creación de la Empresa" id="txtAnoCreacion1" name="txtAnoCreacion1">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group ">
                            <a>Creación de la Empresa <strong>(Segundo Rango)</strong></a>
                            <input type="date" class="form-control" placeholder="Año de Creación de la Empresa" id="txtAnoCreacion2" name="txtAnoCreacion2">
                            <i class="fa fa-user"></i>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Fechas de Afiliación</legend>
                        <div class="form-group ">
                            <a>Afliación a la CEO<strong>(Primer Rango)</strong></a>
                            <input type="date" class="form-control" placeholder="Año de Afiliación a la CEO" id="txtAnoAfiliacion1" name="txtAnoAfiliacion1">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group ">
                            <a>Afliación a la CEO<strong>(Segundo Rango)</strong></a>
                            <input type="date" class="form-control" placeholder="Año de Afiliación a la CEO" id="txtAnoAfiliacion2" name="txtAnoAfiliacion2">
                            <i class="fa fa-user"></i>
                        </div>
                    </fieldset>
                    <div class="form-group ">
                        <a>Sector Productivo</a>
                        <input type="text" class="form-control" placeholder="Sector Prodcutivo" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" id="txtSectorProductivo" name="txtSectorProductivo">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Numero de Empleados <strong>Formato:"# - #"</strong></a></a>
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
                        <a>Sostenimiento en Pesos<strong> Formato:"# - #"</strong></a>
                        <input onchange="AutollenarSMLV()" type="text" onkeypress="return Numeros(event)" class="form-control" placeholder="Cuota de Sostenimiento $(Pesos)" id="txtSostenimientoPesos" name="txtSostenimientoPesos">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Sostenimiento SMLV <strong>Formato:"# - #"</strong></a>
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
                    </div>
                </div>
            </div>
            
            <div id="Programas" hidden="hidden">
                <div class="login-form">
            
                    <div class="form-group">
                        <a id="labelOriginal">Programa</a>
                        <input type="text" class="form-control" placeholder="Programa" id="txtPrograma" name="txtPrograma">
                        <i class="fa fa-user"></i>
                    </div>
                
                    <div class="form-group ">
                        <a>NIT de la Empresa</a>
                        <input  type="text" class="form-control" placeholder="NIT" id="txtNITPrograma" name="txtNITPrograma">
                        <i class="fa fa-user"></i>
                    </div>

                    
                    <div class="form-group ">
                        <a>Nombre de la Empresa</a>
                        <input  type="text" class="form-control" placeholder="Empresa" id="txtNombreEmpresaPrograma" name="txtNombreEmpresaPrograma">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Email <strong>Nota: Este email aplica para el principal y el secundario</strong></a>
                        <input type="email" class="form-control" placeholder="email" id="txtEmailPrograma" name="txtEmailPrograma">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Teléfono <strong>Nota: Este número aplica para el principal y el secundario</strong></a>
                        <input type="text" class="form-control" placeholder="Número" onkeypress="return Numeros(event)" id="txtTelPrograma" name="txtTelPrograma">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Pago <strong>Formato: "# - #"</strong></a>
                        <input type="text" class="form-control" placeholder="Pago" onkeypress="return Numeros(event)" id="txtPagoPrograma" name="txtPagoPrograma">
                        <i class="fa fa-user"></i>
                    </div>

                    <fieldset>
                        <legend><strong>Datos Personales:</strong></legend>    
                        <div class="form-group ">
                            <a>Cargo que desempeña en la Empresa</a>
                            <input type="text" class="form-control" placeholder="Cargo" id="txtCargoEmpresaPrograma" name="txtCargoEmpresaPrograma">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Cédula</a>
                            <input type="text" class="form-control" placeholder="Cédula" onkeypress="return Numeros(event)" id="txtCedulaPrograma" name="txtCedulaPrograma">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Nombre</a>
                            <input type="text" class="form-control" placeholder="Nombre" id="txtNombrePersonaPrograma" name="txtNombrePersonaPrograma">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Apellidos</a>
                            <input type="text" class="form-control" placeholder="Apellidos"  id="txtApellidosPrograma" name="txtApellidosPrograma">
                            <i class="fa fa-user"></i>
                        </div>

                        <fieldset>
                            <legend>Fecha de Cumpleaños</legend>
                            <div class="form-group ">
                                <a>Fecha <strong>Primer Valor</strong></a>
                                <input type="date" class="form-control" id="txtCumplePrograma" name="txtCumplePrograma">
                                <i class="fa fa-user"></i>
                            </div>

                            <div class="form-group ">
                                <a>Fecha <strong>Segundo Valor</strong> </a>
                                <input type="date" class="form-control" id="txtCumplePrograma2" name="txtCumplePrograma2">
                                <i class="fa fa-user"></i>
                            </div>
                            <div class="form-group ">
                                <select onchange="Avisar()" class="form-control" name="txtMesPrograma" id="txtMesPrograma">
                                    <option value="0">BUSCAR POR MES</option>
                                    <option value="1">Enero</option>
                                    <option value="2">Febrero</option>
                                    <option value="3">Marzo</option>
                                    <option value="4">Abril</option>
                                    <option value="5">Mayo</option>
                                    <option value="6">Junio</option>
                                    <option value="7">Julio</option>
                                    <option value="8">Agosto</option>
                                    <option value="9">Septiembre</option>
                                    <option value="10">Octubre</option>
                                    <option value="11">Noviembre</option>
                                    <option value="12">Diciembre</option>
                                </select>
                            </div>
                        </fieldset>
                        <div class="form-group ">
                            <a>Trato</a>
                            <input type="text" placeholder="Trato" class="form-control" id="txtTituloPrograma" name="txtTituloPrograma">
                            <i class="fa fa-user"></i>
                        </div>
                    </fieldset>
                    <br>
                </div>
                
            </div>
            
            <div id="Comite" hidden="hidden">
                <div class="login-form">
                    
                    <div class="form-group">
                        <a id="labelOriginal">Comité</a>
                        <input  type="text" class="form-control" placeholder="Comité" id="txtComite" name="txtComite">                  
                    </div>

                    <div class="form-group ">
                        <a>NIT de la Empresa</a>
                        <input  type="text" class="form-control" placeholder="NIT" id="txtNITComite" name="txtNITComite">
                        <i class="fa fa-user"></i>
                    </div>

                    
                    <div class="form-group ">
                        <a>Nombre de la Empresa</a>
                        <input  type="text" class="form-control" placeholder="Empresa" id="txtNombreEmpresa" name="txtNombreEmpresa">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Email <strong>Nota: Este email aplica para el principal y el secundario</strong></a>
                        <input type="email" class="form-control" placeholder="email" id="txtEmailComite" name="txtEmailComite">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Teléfono <strong>Nota: Este número aplica para el principal y el secundario</strong></a>
                        <input type="text" class="form-control" placeholder="Número" onkeypress="return Numeros(event)" id="txtTelComite" name="txtTelComite">
                        <i class="fa fa-user"></i>
                    </div>

                    <fieldset>
                        <legend><strong>Datos Personales:</strong></legend>    
                        <div class="form-group ">
                            <a>Cargo que desempeña en la Empresa</a>
                            <input type="text" class="form-control" placeholder="Cargo" id="txtCargoEmpresaComite" name="txtCargoEmpresaComite">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Cédula</a>
                            <input type="text" class="form-control" placeholder="Cédula" onkeypress="return Numeros(event)" id="txtCedulaComite" name="txtCedulaComite">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Nombre</a>
                            <input type="text" class="form-control" placeholder="Nombre" id="txtNombrePersonaComite" name="txtNombrePersonaComite">
                            <i class="fa fa-user"></i>
                        </div>

                        <div class="form-group ">
                            <a>Apellidos</a>
                            <input type="text" class="form-control" placeholder="Apellidos"  id="txtApellidosComite" name="txtApellidosComite">
                            <i class="fa fa-user"></i>
                        </div>

                        <fieldset>
                            <legend>Fecha de Cumpleaños</legend>
                            <div class="form-group ">
                                <a>Nacimiento <strong>Primer Valor</strong></a>
                                <input type="date" class="form-control" id="txtCumpleComite" name="txtCumpleComite">
                                <i class="fa fa-user"></i>
                            </div>

                            <div class="form-group ">
                                <a>Nacimiento <strong>Segundo Valor</strong> </a>
                                <input type="date" class="form-control" id="txtCumpleComite2" name="txtCumpleComite2">
                                <i class="fa fa-user"></i>
                            </div>
                                <div class="form-group ">
                                    <select onchange="Avisar()" class="form-control" name="txtMesComite" id="txtMes">
                                        <option value="0">BUSCAR POR MES</option>
                                        <option value="1">Enero</option>
                                        <option value="2">Febrero</option>
                                        <option value="3">Marzo</option>
                                        <option value="4">Abril</option>
                                        <option value="5">Mayo</option>
                                        <option value="6">Junio</option>
                                        <option value="7">Julio</option>
                                        <option value="8">Agosto</option>
                                        <option value="9">Septiembre</option>
                                        <option value="10">Octubre</option>
                                        <option value="11">Noviembre</option>
                                        <option value="12">Diciembre</option>
                                    </select>
                            </div>
                        </fieldset>
                        <div class="form-group ">
                            <a>Trato</a>
                            <input type="text" placeholder="Trato" class="form-control" id="txtTituloComite" name="txtTituloComite">
                            <i class="fa fa-user"></i>
                        </div>
                    </fieldset>
                    <br>
                </div>
            </div>
        </form>
    <script>
        function FiltroObjeto(Valor)
        {
            var Vector = ["Empresa","Comite","Programas"];
            document.getElementById("btnBuscar").hidden=false;
            document.getElementById(Valor).hidden=false;
            for (var i = 0; i < Vector.length; i++) {
                if(Vector[i]!==Valor)
                {
                    document.getElementById(Vector[i]).hidden="hidden";
                }
            }
        }
        function Avisar()
        {
            alert("Recuerda que si seleccionas algún mes, el rango de fechas no funcionará");
        }
    </script>
    </body> 
</html>
