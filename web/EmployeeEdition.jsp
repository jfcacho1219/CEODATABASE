<%-- 
    Document   : EmployeeRegistration
    Created on : 2/12/2016, 12:03:03 AM
    Author     : SONY
--%>

<%@page import="Modelo.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    
    Empleado Emple = (Empleado)request.getAttribute("Empleado");
    String Mensaje = (String)request.getAttribute("Mensaje");
    String Administrativos = (String)request.getSession().getAttribute("Comite");
    if(Administrativos == null) Administrativos = "[\"Error\",\"0\",\"Gerentes\",\"1\", \"Gestion Humana\",\"2\",\"Comercio Exterior\",\"3\",\"Técnico\",\"4\",\"Compras\",\"5\",\"Seguridad\",\"6\",\"Ambiental\",\"7\",\"TICS\",\"8\",\"MYPES\",\"9\"]";
    if(Mensaje==null) Mensaje =" ";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Check.css" type="text/css" rel="stylesheet" >
        <script type="text/javascript" src="Scripts/Letras.js"></script>
        <script type="text/javascript" src="Scripts/Avisos.js"></script>
        <script type="text/javascript" src="Scripts/Empleado.js"></script>
        <title>Registro de Personal Administrativo</title>
    </head>
    <body>
        <input type="text" id="txtMensaje" hidden="hidden" value="<%=Mensaje%>"/>
        <form action="ControllerEditions" method="Post">
            <input type="hidden" id="txtPersonaId" name="txtPersonaId" value="<%=Emple.getPersona().getId()%>" />
            <input type="hidden" id="txtEmpleadoId" name="txtEmpleadoId" value="<%=Emple.getID()%>" />
            <input type="hidden" id="txtComiteId" name="txtComiteId" value="<%=Emple.getComite().getId()%>" />
            <input type="hidden" id="txtObjeto" name="txtObjeto" value="Directivas" />
            <div class="login-form">
                <h1>Edición de Administrativos de la Empresa <%=Emple.getEmpresa().getNombre()%> <%=Emple.getEmpresa().getSociedad()%></h1>
                <div class="form-group">
                    <input type="checkbox" id="RadioEliminar" name="RadioEliminar" value="YES">
                    <label for="RadioEliminar">Eliminar</label>
                </div>
                <div class="form-group">
                    <a id="labelOriginal">Comité</a>
                    <select onchange="Activartxt()" class="form-control" name="txtCargo" id="txtCargo">
                        <script>
                            var VectorAdmi = <%=Administrativos%>;
                            var ComiteNombre = "<%=Emple.getComite().getNombre()%>";
                            var ComiteId = "<%=Emple.getComite().getId()%>";
                            document.write('<option value="'+ComiteId+'">'+ComiteNombre+'</option>');
                            for (var i = 0; i < VectorAdmi.length; i=i+2) {
                                if(ComiteId!==VectorAdmi[i])
                                {
                                    document.write('<option value="'+VectorAdmi[i+1]+'">'+VectorAdmi[i]+'</option>');
                                }
                            }
                        </script>
                        <option value="OTRO">Otro</option>
                    </select>
                </div>
                            
                <div id="Junta" hidden="hidden">
                    <div class="form-group">
                        <input onchange="Ocultar()" type="checkbox" id="RadioJunta" name="RadioJunta" value="YES" hidden="hidden">
                        <label hidden="hidden" id="labelJunta" for="RadioJunta">¿Hace Parte de la Junta Directiva?</label>
                        <fieldset>
                            <legend><strong>Nota:</strong></legend>
                            Si seleccionas aquí, cuando oprimas "Insertar" el sistema te redirigirá
                            a la página para registrar la junta directiva, de lo contrario continúa
                            normal.
                        </fieldset>
                    </div>     
                </div>
                            
                <div class="form-group ">
                    <a id="labelCual" hidden="hidden">Cual</a>
                    <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" hidden="hidden" class="form-control" placeholder="Comité" id="txtCargo2" name="txtCargo2">
                    <i class="fa fa-user"></i>
                </div>
                            
                <div class="form-group ">
                    <a>NIT de la Empresa</a>
                    <input  type="text" class="form-control" readonly="readonly" value="<%=Emple.getEmpresa().getNIT()%>" id="txtNIT" name="txtNIT">
                    <i class="fa fa-user"></i>
                </div>
                
                <div class="form-group ">
                    <a>Email 1</a>
                    <input type="email" class="form-control" placeholder="email" value="<%=Emple.getEmail1()%>" id="txtEmail1" name="txtEmail1">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Email 2 <strong>(No nocesario)</strong></a>
                    <input type="email" class="form-control" placeholder="email" value="<%=Emple.getEmail2()%>" id="txtEmail2" name="txtEmail2">
                    <i class="fa fa-user"></i>
                </div>

                <div class="form-group ">
                    <a>Teléfono 1</a>
                    <input type="text" class="form-control" placeholder="Número" value="<%=Emple.getTelefono1()%>" onkeypress="return Numeros(event)" id="txtTel1" name="txtTel1">
                    <i class="fa fa-user"></i>
                </div>
  
                <div class="form-group ">
                    <a>Telefono 2<strong>(No nocesario)</strong></a>
                    <input type="text" placeholder="Número" value="<%=Emple.getTelefono2()%>" onkeypress="return Numeros(event)" class="form-control" placeholder="Número"  id="txtTel2" name="txtTel2">
                    <i class="fa fa-user"></i>
                </div>
                <fieldset>
                    <legend><strong>Datos Personales:</strong></legend>    
                    <div class="form-group ">
                        <a>Cargo que desempeña en la Empresa</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" placeholder="Cargo" value="<%=Emple.getPersona().getCargo()%>" id="txtCargoEmpresa" name="txtCargoEmpresa">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Cédula</a><strong>(No nocesario)</strong>
                        <input type="text" class="form-control" placeholder="Cédula" value="<%=Emple.getPersona().getCedula()%>" onkeypress="return Numeros(event)" id="txtCedula" name="txtCedula">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Nombre</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" placeholder="Nombre" value="<%=Emple.getPersona().getNombre()%>" id="txtNombre" name="txtNombre">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Apellidos</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" placeholder="Apellidos" value="<%=Emple.getPersona().getApellidos()%>" id="txtApellidos" name="txtApellidos">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Fecha de Nacimiento</a>
                        <input type="date" class="form-control" value="<%=Emple.getPersona().getCumpleanos()%>" id="txtCumple" name="txtCumple">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Trato</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" placeholder="Trato" value="<%=Emple.getPersona().getTitulo()%>" class="form-control" id="txtTitulo" name="txtTitulo">
                        <i class="fa fa-user"></i>
                    </div>
                </fieldset>
                <br>
                <span class="alert">Invalid Credentials</span>
                <input type="submit" value="Ingresar" name="btnIngresar" class="log-btn"/>
            </div>
        </form>
        <script>
            function Activartxt()
            {
                var list = document.getElementById("txtCargo").value;
                if(list === "OTRO")
                {
                    document.getElementById("txtCargo").hidden = "hidden";
                    document.getElementById("labelOriginal").hidden = "hidden";
                    document.getElementById("txtCargo2").hidden = false;
                    document.getElementById("labelCual").hidden = false;
                }

                Activarcheck();
            }

            function Activarcheck()
            {
                var ID = "txtCargo";
                if (document.getElementById(ID).value==="1") {
                    document.getElementById("Junta").hidden=false;
                }
            }
            function Ocultar()
            {
                document.getElementById("Checkers").hidden="hidden";
                document.getElementById("Nota").hidden="hidden";
                document.getElementById("RadioRegistrarPrograma").checked = false;
                document.getElementById("RadioContinuar").checked = false;                        
            }

        </script>
        <script>
            if(document.getElementById("txtMensaje").value !==" ")alert(document.getElementById("txtMensaje").value);
            var txt = document.getElementById("txtMensaje");
            txt.value = " ";
        </script>
    </body>
</html>
