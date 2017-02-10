<%-- 
    Document   : ProgramRegistration
    Created on : 6/12/2016, 11:31:14 AM
    Author     : SONY
--%>

<%@page import="Modelo.Empleado"%>
<%@page import="java.lang.String"%>
<%@page import="Modelo.ProgramaPago"%>
<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    String VectorJavaScript = (String)request.getSession().getAttribute("ScriptProgramas");
    if(VectorJavaScript==null)
    {
        VectorJavaScript = "[\"Ocurrio un error, se debe ingresar manualmente\",\"Error\"]";
    }
    ProgramaPago Programa = (ProgramaPago)request.getAttribute("Empleado");
    ProgramaPago Program = (ProgramaPago)request.getAttribute("ProgramaPago");
    String Mensaje = (String)request.getAttribute("Mensaje");
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
        <title>Edición de Programas</title>
    </head>
    <body>
        <input type="text" id="txtMensaje" hidden="hidden" value="<%=Mensaje%>"/>
        <form action="ControllerEditions" method="Post">
            <input type="text" value="Programas y Encargados" id="txtObjeto" name="txtObjeto" hidden="hidden" value="<%=Mensaje%>"/>
            <input type="text" id="txtPersonaId" name="txtPersonaId" hidden="hidden" value="<%=Program.getEmple().getPersona().getId()%>"/>
            <input type="text" id="txtProgramaOriginal" name="txtProgramaOriginal" hidden="hidden" value="<%=Program.getTipo().getId()%>"/>
            <input type="text" id="txtEmpleadoId" name="txtEmpleadoId" hidden="hidden" value="<%=Program.getEmple().getID()%>"/>
            <input type="text" id="txtProgramaNombre" name="txtProgramaNombre" hidden="hidden" value="<%=Program.getTipo().getNombre()%>"/>


            <div class="login-form">
                
                <h1>Edición de los Programas y Encargados a los cuales pertenece la Empresa <%=Program.getEmple().getEmpresa().getNombre()%> <%=Program.getEmple().getEmpresa().getSociedad()%></h1>
                
                <div class="form-group">
                    <input type="checkbox" id="RadioEliminar" name="RadioEliminar" value="YES">
                    <label for="RadioEliminar">Eliminar</label>
                </div>
                
                
                
                <div class="form-group">
                    <a id="labelOriginal">Programa</a>
                    <select onchange="Activartxt()" class="form-control" name="txtPrograma" id="txtPrograma">
                        <script>     
                            var VectorScript = <%=VectorJavaScript%>;
                            var ProgramaNombre = "<%=Program.getTipo().getNombre()%>"
                            var ProgramaId = "<%=Program.getTipo().getId()%>"
                            document.write("<option value=\""+ProgramaId+"\">"+ProgramaNombre+"</option>");
                            for(var i=0; i<VectorScript.length; i=i+2){
                                if (VectorScript[i]!==ProgramaId) {
                                    document.write("<option value=\""+VectorScript[i]+"\">"+VectorScript[i+1]+"</option>");    
                                }
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
                    <input readonly="readonly" type="text" class="form-control" value="<%=Program.getEmple().getEmpresa().getNIT()%>" id="txtNIT" name="txtNIT">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Email 1</a>
                    <input type="email" class="form-control" placeholder="email" value="<%=Program.getEmple().getEmail1()%>"  id="txtEmail1" name="txtEmail1">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Email 2 <strong>(No nocesario)</strong></a>
                    <input type="email" class="form-control" placeholder="email" value="<%=Program.getEmple().getEmail2()%>" id="txtEmail2" name="txtEmail2">
                    <i class="fa fa-user"></i>
                </div>

                <div class="form-group ">
                    <a>Teléfono 1</a>
                    <input type="text" class="form-control" placeholder="Número" onkeypress="return Numeros(event)" value="<%=Program.getEmple().getTelefono1()%>" id="txtTel1" name="txtTel1">
                    <i class="fa fa-user"></i>
                </div>
  
                <div class="form-group ">
                    <a>Telefono 2<strong>(No nocesario)</strong></a>
                    <input type="text" placeholder="Número" onkeypress="return Numeros(event)" class="form-control" placeholder="Número" value="<%=Program.getEmple().getTelefono2()%>" id="txtTel2" name="txtTel2">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Pago</a>
                    <input type="text" class="form-control" placeholder="Pago" value="<%=Program.getPago()%>" id="txtPago" name="txtPago">
                    <i class="fa fa-user"></i>
                </div>
                
                <br>
                <fieldset>
                    <legend>Persona Encargada de este Programa</legend>
                    <br><br>
                    <div class="form-group ">
                        <a>Cargo que desempeña en la Empresa</a>
                        <input type="text" class="form-control" placeholder="Cargo" value="<%=Program.getEmple().getPersona().getCargo()%>" id="txtCargoEmpresa" name="txtCargoEmpresa">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group ">
                        <a>Cedula <strong>(No nocesario)</strong></a>
                        <input type="text" class="form-control" placeholder="Cédula" value="<%=Program.getEmple().getPersona().getCedula()%>"  id="txtCedula" name="txtCedula">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Nombre</a>
                        <input type="text" class="form-control" placeholder="Nombre" value="<%=Program.getEmple().getPersona().getNombre()%>" id="txtNombre" name="txtNombre">
                        <i class="fa fa-user"></i>
                    </div>


                    <div class="form-group ">
                        <a>Apellidos</a>
                        <input type="text" class="form-control" placeholder="Apellidos"  value="<%=Program.getEmple().getPersona().getApellidos()%>" id="txtApellidos´" name="txtApellidos">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Fecha de Nacimiento</a>
                        <input type="date" class="form-control" value="<%=Program.getEmple().getPersona().getCumpleanos()%>" id="txtCumple" name="txtCumple">
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group ">
                        <a>Trato</a>
                        <input type="text" class="form-control" placeholder="Trato" value="<%=Program.getEmple().getPersona().getTitulo()%>" id="txtTitulo" name="txtTitulo">
                        <i class="fa fa-user"></i>
                    </div>
                </fieldset>
                <br>  
                <span class="alert">Invalid Credentials</span>
                <input type="submit" value="Editar" name="btnIngresar" class="log-btn"/>
            </div>
        </form>
        <script>
            if(document.getElementById("txtMensaje").value !==" ")alert(document.getElementById("txtMensaje").value);
            var txt = document.getElementById("txtMensaje");
            txt.value = " ";
        </script>
    </body>
</html>
