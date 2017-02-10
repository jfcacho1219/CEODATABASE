<%-- 
    Document   : ProgramMoneyEdition
    Created on : 5/01/2017, 12:44:16 PM
    Author     : SONY
--%>

<%@page import="Modelo.ProgramaPago"%>
<%@page import="Modelo.Empresa"%>
<!DOCTYPE html>
<%
    String VectorJavaScript = (String)request.getSession().getAttribute("ScriptProgramas");
    if(VectorJavaScript==null)
    {
        VectorJavaScript = "[\"Ocurrio un error, se debe ingresar manualmente\",\"Error\"]";
    }
    ProgramaPago Program = (ProgramaPago)request.getAttribute("Programa");
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
        <title>Edición del Programa y su respectivo pago</title>
    </head>
    <body>
        <form action="ControllerEditions" method="Post">
            
            <div class="login-form">
                <input type="text" hidden="hidden" id="txtObjeto" name="txtObjeto" value="Programa y Precio">
                <input type="text" hidden="hidden" id="txtTipoIdViejo" name="txtTipoIdViejo" value="<%=Program.getTipo().getId()%>">
                <h1>Registro del programa <%=Program.getTipo().getNombre()%> al cuale pertenece la Empresa <%=Program.getEmple().getEmpresa().getNombre()%> <%=Program.getEmple().getEmpresa().getSociedad()%></h1>
                <div class="form-group">
                    <input type="checkbox" id="RadioEliminar" name="RadioEliminar" value="YES">
                    <label for="RadioEliminar">Eliminar</label>
                </div>   

                <div class="form-group">
                    <a id="labelOriginal">Programa</a>
                    <select onchange="Activartxt()" class="form-control" name="txtPrograma" id="txtPrograma">
                        <script>     
                            var VectorScript = <%=VectorJavaScript%>;
                            var TipoPrograma = "<%=Program.getTipo().getNombre()%>";
                            var IdTipo ="<%=Program.getTipo().getId()%>";
                            document.write("<option value=\""+IdTipo+"\">"+TipoPrograma+"</option>");
                            for(var i=0; i<VectorScript.length; i=i+2){
                                if(IdTipo!==VectorScript[i])
                                {
                                    document.write("<option value=\""+VectorScript[i]+"\">"+VectorScript[i+1]+"</option>");
                                }
                            }	
                        </script>
                        
                    </select>
                </div>
                      
                <div class="form-group ">
                    <a>NIT de la Empresa</a>
                    <input readonly="readonly" type="text" class="form-control" value="<%=Program.getEmple().getEmpresa().getNIT()%>" id="txtNIT" name="txtNIT">
                    <i class="fa fa-user"></i>
                </div>
                    
                <div class="form-group ">
                    <a>Pago</a>
                    <input type="text" class="form-control" placeholder="Pago" value="<%=Program.getPago()%>"  id="txtPago" name="txtPago">
                    <i class="fa fa-user"></i>
                </div>
                
                <br>
                <fieldset>
                    <legend>Persona Encargada de este Programa</legend>
                    <br><br>
                    <div class="form-group ">
                        <a>Cedula </a>
                        <input type="text" class="form-control" readonly="readonly" value="<%=Program.getEmple().getPersona().getCedula()%>"  id="txtCedula" name="txtCedula">
                        <i class="fa fa-user"></i>
                    </div>

                    <div class="form-group ">
                        <a>Nombre y Apellidos</a>
                        <input type="text" class="form-control" readonly="readonly" value="<%=Program.getEmple().getPersona().getNombre()%> <%=Program.getEmple().getPersona().getApellidos()%>"  id="txtNombre" name="txtNombre">
                        <i class="fa fa-user"></i>
                    </div>

                </fieldset>
                <br>                    
                <span class="alert">Invalid Credentials</span>
                <input type="submit" value="Editar" name="btnIngresar" class="log-btn"/>
            </div>
        </form>
    </body>
</html>
