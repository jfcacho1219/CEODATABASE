<%-- 
    Document   : EmployeeRegistration
    Created on : 2/12/2016, 12:03:03 AM
    Author     : SONY
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    
    Empresa Company = (Empresa)request.getSession().getAttribute("Empresa");
    String Mensaje = (String)request.getAttribute("Mensaje");
    String Administrativos = (String)request.getSession().getAttribute("Comite");
    if(Administrativos == null) Administrativos = "[\"Error\",\"0\",\"Gerentes\",\"1\", \"Gestion Humana\",\"2\",\"Comercio Exterior\",\"3\",\"Técnico\",\"4\",\"Compras\",\"5\",\"Seguridad\",\"6\",\"Ambiental\",\"7\",\"TICS\",\"8\",\"MYPES\",\"9\"]";
    if(Company==null) Company = new Empresa("","","","","","","","","","","","","","","","");
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
        <form action="ControllerEmployeeRegistration" method="Post">
            <div class="login-form">
                <h1>Registro de Administrativos de la Empresa <%=Company.getNombre()%> <%=Company.getSociedad()%></h1>
            
            
                <div class="form-group">
                    <a id="labelOriginal">Comité</a>
                    <select onchange="Activartxt()" class="form-control" name="txtCargo" id="txtCargo">
                        <script>
                            var VectorAdmi = <%=Administrativos%>;
                            for (var i = 0; i < VectorAdmi.length; i=i+2) {
                                document.write('<option value="'+VectorAdmi[i+1]+'">'+VectorAdmi[i]+'</option>');
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
                <fieldset>
                    <legend><strong>Datos Personales:</strong></legend>    
                    <div class="form-group ">
                        <a>Cargo que desempeña en la Empresa</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" placeholder="Cargo"  id="txtCargoEmpresa" name="txtCargoEmpresa">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Cédula</a><strong>(No nocesario)</strong>
                        <input type="text" class="form-control" placeholder="Cédula"  onkeypress="return Numeros(event)" id="txtCedula" name="txtCedula">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Nombre</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" placeholder="Nombre"  id="txtNombre" name="txtNombre">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Apellidos</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" class="form-control" placeholder="Apellidos"  id="txtApellidos" name="txtApellidos">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Fecha de Nacimiento</a>
                        <input type="date" class="form-control" id="txtCumple" name="txtCumple">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Trato</a>
                        <input type="text" style="text-transform: capitalize" onkeyup="javascript:this.value = this.value.replace(/\b\w/g, l => l.toUpperCase())" placeholder="Trato" class="form-control" id="txtTitulo" name="txtTitulo">
                        <i class="fa fa-user"></i>
                    </div>
                </fieldset>
                <br><br>
                <fieldset id="Nota">
                    <legend><strong>NOTA:</strong></legend>    
                    <a>De las siguiente dos opciones solo puede elegir una. <br>
                        Si elige "¿Continuar Registrando?" la página recargará de 
                        nuevo para que empiece el nuevo proceso de registro. Si
                        elige "¿Hace parte de algún programa?" el sistema la llevará
                        al registro del programa al cual está vinculado la empresa.
                        Si no selecciona ninguna el proceso habrá terminado.
                    </a>

                </fieldset>    
                <div id="Checkers">    
                    <div class="form-group">
                        <input onchange="Deseleccionar('RadioRegistrarPrograma')" type="checkbox" id="RadioContinuar" name="RadioContinuar" checked="checked" value="YES">
                        <label for="RadioContinuar">¿Continuar Registrando?</label>
                    </div>

                    <div class="form-group">
                        <input onchange="Deseleccionar('RadioContinuar')" type="checkbox" id="RadioRegistrarPrograma" name="RadioRegistrarPrograma" value="YES">
                        <label for="RadioRegistrarPrograma">¿Hace parte de algún Programa?</label>
                    </div>   
                </div>    
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
