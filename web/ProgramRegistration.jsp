<%-- 
    Document   : ProgramRegistration
    Created on : 6/12/2016, 11:31:14 AM
    Author     : SONY
--%>

<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    String VectorJavaScript = (String)request.getSession().getAttribute("ScriptProgramas");
    if(VectorJavaScript==null)
    {
        VectorJavaScript = "[\"Ocurrio un error, se debe ingresar manualmente\",\"Error\"]";
    }
    Empresa Company = (Empresa)request.getSession().getAttribute("Empresa");
    String Mensaje = (String)request.getAttribute("Mensaje");
    if(Company==null) Company = new Empresa("","","","","","","","","","","","","","","","");
    if(Mensaje==null) Mensaje =" ";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <script type="text/javascript" src="Scripts/Programa.js"></script>
        <script type="text/javascript" src="Scripts/Letras.js"></script>
        <title>Registro de los Programas</title>
    </head>
    <body>
        <input type="text" id="txtMensaje" hidden="hidden" value="<%=Mensaje%>"/>
        <form action="ControllerProgramRegistration" method="Post">
            <div class="login-form">
                <h1>Registro de los Programas y Encargados a los cuales pertenece la Empresa <%=Company.getNombre()%> <%=Company.getSociedad()%></h1>
            
                <div class="form-group">
                    <a id="labelOriginal">Programa</a>
                    <select onchange="Activartxt()" class="form-control" name="txtPrograma" id="txtPrograma">
                        <script>     
                            var VectorScript = <%=VectorJavaScript%>;
                            for(var i=0; i<VectorScript.length; i=i+2){
                                document.write("<option value=\""+VectorScript[i]+"\">"+VectorScript[i+1]+"</option>");
                            }	
                        </script>
                        <option value="OTRO">Otro</option>
                    </select>
                </div>
                            
                <div class="form-group ">
                    <a id="labelCual" hidden="hidden">Cual</a>
                    <input type="text" hidden="hidden" class="form-control" placeholder="Nombre" id="txtPrograma2" name="txtPrograma2">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a id="labelCual2" hidden="hidden">Descripción <strong>(Campo no Obligatorio)</strong></a>
                    <input type="text" hidden="hidden" class="form-control" placeholder="Descrpción" id="txtDescripcion" name="txtDescripcion">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>NIT de la Empresa</a>
                    <input  type="text" class="form-control" placeholder="NIT" value="<%=Company.getNIT()%>" id="txtNIT" name="txtNIT">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Email 1</a>
                    <input type="email" class="form-control" placeholder="email"  id="txtEmail1" name="txtEmail1">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Email 2 <strong>(No nocesario)</strong></a>
                    <input type="email" class="form-control" placeholder="email"  id="txtEmail2" name="txtEmail2">
                    <i class="fa fa-user"></i>
                </div>

                <div class="form-group ">
                    <a>Teléfono 1</a>
                    <input type="text" class="form-control" placeholder="Número" onkeypress="return Numeros(event)" id="txtTel1" name="txtTel1">
                    <i class="fa fa-user"></i>
                </div>
  
                <div class="form-group ">
                    <a>Telefono 2<strong>(No nocesario)</strong></a>
                    <input type="text" placeholder="Número" onkeypress="return Numeros(event)" class="form-control" placeholder="Número"  id="txtTel2" name="txtTel2">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Pago</a>
                    <input type="text" class="form-control" placeholder="Pago"  id="txtPago" name="txtPago">
                    <i class="fa fa-user"></i>
                </div>
                
                <br>
                <fieldset>
                    <legend>Persona Encargada de este Programa</legend>
                    <br><br>
                    <div class="form-group ">
                        <a>Cargo que desempeña en la Empresa</a>
                        <input type="text" class="form-control" placeholder="Cargo"  id="txtCargoEmpresa" name="txtCargoEmpresa">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group ">
                        <a>Cedula <strong>(No nocesario)</strong></a>
                        <input type="text" class="form-control" placeholder="Cédula"  id="txtCedula" name="txtCedula">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Nombre</a>
                        <input type="text" class="form-control" placeholder="Nombre"  id="txtNombre" name="txtNombre">
                        <i class="fa fa-user"></i>
                    </div>


                    <div class="form-group ">
                        <a>Apellidos</a>
                        <input type="text" class="form-control" placeholder="Apellidos"  id="txtApellidos´" name="txtApellidos">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Fecha de Nacimiento</a>
                        <input type="date" class="form-control" id="txtCumple" name="txtCumple">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Trato</a>
                        <input type="text" class="form-control" placeholder="Trato" id="txtTitulo" name="txtTitulo">
                        <i class="fa fa-user"></i>
                    </div>
                </fieldset>
                <br>
                <fieldset>
                    <strong>NOTA:</strong>
                    <a>De las siguiente dos opciones solo puede elegir una. <br>
                        Si elije "Continuar Registrando" El sistema lo llevará a otro registro. <br>
                        Si elije "Registrar Otra Empresa" El sistema lo llevará a registrar otra Empresa.<br>
                        Si no elije ninguna, el sistema lo llevará a la página de Inicio.
                    </a>
                </fieldset>    
                <div class="form-group">
                    <input onchange="Deseleccionar('RadioRegistrarEmpresa')" type="checkbox" id="RadioContinuarRegistro" name="RadioContinuarRegistro" checked="checked" value="YES">
                    <label for="RadioContinuarRegistro">¿Continuar Registrando?</label>
                </div>
                    
                <div class="form-group">
                    <input onchange="Deseleccionar('RadioContinuarRegistro')" type="checkbox" id="RadioRegistrarEmpresa" name="RadioRegistrarEmpresa" value="YES">
                    <label for="RadioRegistrarEmpresa">Registrar Otra Empresa</label>
                </div>   
                
                <span class="alert">Invalid Credentials</span>
                <input type="submit" value="Ingresar" name="btnIngresar" class="log-btn"/>
            </div>
        </form>
        <script>
            if(document.getElementById("txtMensaje").value !==" ")alert(document.getElementById("txtMensaje").value);
            var txt = document.getElementById("txtMensaje");
            txt.value = " ";
        </script>
    </body>
</html>
