<%-- 
    Document   : ViewTable
    Created on : 9/12/2016, 11:30:47 AM
    Author     : SONY
--%>

<%@page import="Modelo.Administrador"%>
<%@page import="Modelo.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Administrador Admi = (Administrador)request.getSession().getAttribute("Administrador");
    Empresa Company = (Empresa)request.getAttribute("Company");
    String Object = (String)request.getAttribute("Object");
    String ObjectDirectivas = (String)request.getAttribute("ObjectDirectivas");
    String NITPrincipal = (String)request.getSession().getAttribute("NITPrincipal");
    String Cabecera = (String)request.getAttribute("Cabecera");
    String VectorScript = (String)request.getAttribute("VectorScript");
    String VectorScriptEmpresas = (String)request.getSession().getAttribute("EmpresaYNIT");
    if (VectorScriptEmpresas == null) VectorScriptEmpresas = "[[SIN NIT,Inicia Sesión]]"; 
    if(Object==null) Object=" ";
    if(Company == null) Company = new Empresa("","","","","","","","","","","","","","","","");
    if(Admi==null) Admi = new Administrador("", "NO", "", "");
   
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Table.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Modal.css" type="text/css" rel="stylesheet" >
        
        <title>Lista de <%=Object%></title>
    
        
    </head>
    <body>
        <script>
            function desplegar(vari){
                document.getElementById("bgventana").hidden=vari;
            }
        </script>
        <script>
            var Directivas = "<%=Object%>";
            var TipoDirectivas = "<%=ObjectDirectivas%>";
            var VectorDirectivas = ["Gerentes","Manager","Gestión Humana","HumanManagement","Comercio Exterior","InternationalBusiness","Técnico","Technical","Compras","Purchases",
                "Seguridad","Security","Ambiental","Environmental","Tics","Tics","MYPES","Mypes","Opción Devbida","Opcion Devbida","Inactivos","Inactive","Otros","Other"];
            var VectorProgramas = ["Ambiental","Environmental","Seguridad","Security","MYPES","Mypes","Opción Devbida","Opcion Devbida","Inactivos","Inactive","Otros","Other"];
            if(Directivas === "Directivas")
            {
                for(var i = 0; i<24; i=i+2)
                {
                    if(TipoDirectivas !== VectorDirectivas[i])
                    {
                        document.write('<a style="font: 120% sans-serif; text-decoration: none" href="ControllerCommittee?Object='+VectorDirectivas[i+1]+'">'+VectorDirectivas[i]+'</a>   ||   ');
                    }
                    else
                    {
                        document.write('<strong><a style="text-decoration: none;font: 180%" href="#Estas Aquí">'+VectorDirectivas[i]+'</a></strong>   ||   ');
                    }
                }
            }
            if(Directivas === "Empresa")
            {
                if(TipoDirectivas==="AffiliatedCompany")
                {
                    document.write('<strong><a style="text-decoration: none;font: 180%" href="#Aquí Estás">Empresas Afiliadas</a></strong>   ||   ');
                    document.write('<a style="text-decoration: none;font: 180%" href="ControllerOfLink?Object=NoAffiliatedCompany">Empresas No Afiliadas</a>');
                }
                else
                {
                    document.write('<a style="text-decoration: none;font: 180%" href="ControllerOfLink?Object=AffiliatedCompany">Empresas Afiliadas</a>   ||   ');
                    document.write('<strong><a style="text-decoration: none;font: 180%" href="#Estás Aquí">Empresas No Afiliadas</a></strong>');
                }   
            }
            if(Directivas === "Programas y Encargados")
            {
                for(var i = 0; i<12; i=i+2)
                {
                    if(TipoDirectivas !== VectorProgramas[i])
                    {
                        document.write('<a style="font: 120% sans-serif; text-decoration: none" href="ControllerProgramLink?Object='+VectorProgramas[i+1]+'">'+VectorProgramas[i]+'</a>   ||   ');
                    }
                    else
                    {
                        document.write('<strong><a style="text-decoration: none;font: 180%" href="#Estas Aquí">'+VectorProgramas[i]+'</a></strong>   ||   ');
                    }
                }
            }
            
        </script>
        <br><br>
        <script>
            var Admi = "<%=Admi.getNombre()%>";
            if(Admi!=="NO")
            {
                if("<%=Object%>" === "Empresa") document.write('<a href="RegistroEmpresa.jsp"><img src="Nuevo.gif" width="50"/></a>');
                else document.write('<a href="javascript:desplegar(false)"><img src="Nuevo.gif" width="50"/></a>');
            }
            else document.write('<a href="Login.jsp"><img src="Nuevo.gif" width="50"/></a>');
            </script>

        <%--<form action="ControllerReportsCompany" method="post">
            <input type="submit" value="Generar Reporte"  id="btnReporte" name="btnReporte" class="log-btn"/>
        </form>--%>
        <form action="ControllerRegistration" method="post">
            <div id="bgventana" hidden="hidden">
                <div id="ventana">
                    <div class="login-form">
                        <div class="form-group ">
                            <a href="javascript:desplegar(true)" style="text-decoration: none"> <img src="X.png" width="20"/></a>
                            <input type="hidden" id="txtObjetoModal" name="txtObjetoModal" value="<%=Object%>" />
                            <h2>Ingrese NIT para nuevo<br><br>registro</h2>
                            <br><br><br>
                            <select class="form-control" id="txtNIT2" name="txtNIT2">
                                <option value="0">Seleccionar</option>
                                <script>
                                    var VectorEmpresayNit = <%=VectorScriptEmpresas%>;
                                    for (var i = 0; i < VectorEmpresayNit.length; i++) {
                                        document.write('<option value="'+VectorEmpresayNit[i][0]+'">'+VectorEmpresayNit[i][1]+'</option>');
                                    }
                                </script>
                            </select>
                            <br><br><br>
                            <input type="text" class="form-control" placeholder="NIT" id="txtNIT" name="txtNIT"/>
                            <br><br>
                            <input type="submit" value="IR"  id="btnRegistrar" name="btnRegistrar" class="log-btn"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <h1>
            <script> 
                if("<%=Object%>" === "Empresa y Pagos") 
                {
                    document.write("Programas de la empresa <%=Company.getNombre()%> <%=Company.getSociedad()%>");
                }
                else if("<%=Object%>" === "Directivas")
                {
                    document.write("<%=ObjectDirectivas%>");
                }else if("<%=Object%>" === "Programas y Encargados")
                {
                    document.write("Lista de los contactos que pertenecen al programa <%=ObjectDirectivas%>");
                }
                else document.write("Lista de <%=Object%>");
            </script> 
        </h1>
        <input type="hidden" name="txtValidar" id="txtValidar"/>
        <div id="content">
            <table cellspacing="0">
                <tr>
                    <script>
                        var Cab = <%=Cabecera%>
                        for (var i = 0; i<Cab.length; i++) {
                            document.write("<th>"+Cab[i]+"</th>");
                        }
                    </script>
                </tr>
                <script>
                    var Administrador = "<%=Admi.getNombre()%>"
                    var Contenido = <%=VectorScript%>;
                    var NITPrincipal = "<%=NITPrincipal%>";
                    var NIT;
                    var PersonaId;
                    var TipoProgramaID;
                    var EmpleadoId;
                    var JuntaId;
                    var EditaroCrear = false;
                    for (var i = 0; i < Contenido.length; i++) {
                        document.write("<tr>");
                        EditaroCrear = false;
                        for (var j = 0; j < Contenido[i].length; j++) {
                            if(("<%=Object%>"==="Empresa y Pagos") && j===Contenido[i].length-1)
                            {
                                EmpleadoId = Contenido[i][j];
                                TipoProgramaID = Contenido[i][j];
                            }else
                            {
                                if ("<%=Object%>"==="Directivas") {
                                    if("<%=ObjectDirectivas%>"==="Otros" ||"<%=ObjectDirectivas%>"==="Inactivos")
                                    {
                                        if(j===12)NIT=Contenido[i][j];
                                        if(j===13)EmpleadoId=Contenido[i][j];
                                        if(j===14) PersonaId= Contenido[i][j];
                                        if(j>11)continue;
                                    }
                                    else{
                                        if(j===11)NIT=Contenido[i][j];
                                        if(j===12)EmpleadoId=Contenido[i][j];
                                        if(j===13) PersonaId= Contenido[i][j];
                                        if(j>10)continue;
                                    }
                                }
                                if ("<%=Object%>"==="Directivas2") {
                                        if(j===12)NIT=Contenido[i][j];
                                        if(j===13)EmpleadoId=Contenido[i][j];
                                        if(j===14) PersonaId= Contenido[i][j];
                                        if(j>11)continue;
                                }
                                if ("<%=Object%>"==="Programas y Encargados") {
                                    if("<%=ObjectDirectivas%>"==="Otros" || "<%=ObjectDirectivas%>"==="Inactivos" )
                                    {
                                        if(j===13)TipoProgramaID=Contenido[i][j];
                                        if(j===14)NIT=Contenido[i][j];
                                        if(j===15) EmpleadoId= Contenido[i][j];
                                        if(j===16) PersonaId=Contenido[i][j];
                                        if(j>12)continue;
                                    }else
                                    {
                                        if(j===12)TipoProgramaID=Contenido[i][j];
                                        if(j===13)NIT=Contenido[i][j];
                                        if(j===14) EmpleadoId= Contenido[i][j];
                                        if(j===15) PersonaId=Contenido[i][j];
                                        if(j>11)continue;
                                    }
                                }
                                if("<%=Object%>"==="Junta Directiva")
                                {
                                    if(j===12)JuntaId=Contenido[i][j];
                                    if(j===13)NIT=Contenido[i][j];
                                    if(j===14)EmpleadoId= Contenido[i][j];
                                    if(j===15)PersonaId=Contenido[i][j];
                                    if(j>11)continue;
                                }
                                document.getElementById("txtValidar").value = Contenido[i][j];
                                if (document.getElementById("txtValidar").value === "null")
                                {
                                    document.write("<td>NO APLICA</td>");
                                    EditaroCrear = true;
                                }
                                else document.write("<td>"+Contenido[i][j]+"</td>");
                            }

                            if (j===0 && "<%=Object%>" === "Empresa") NIT=Contenido[i][j];
                            
                            
                        }
                        if ("<%=Object%>"==="Programas y Encargados") {
                            if (Administrador==="NO") {
                                document.write("<td><a href=\"Login.jsp\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                            }
                            else document.write("<td><a href=\"ControllerEditions?Object=ProgramPerson&NIT="+NIT+"&PersonaId="+PersonaId+"&EmpleadoId="+EmpleadoId+"&TipoProgramaId="+TipoProgramaID+"\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                        }
                        if("<%=Object%>"==="Junta Directiva")
                        {
                            if (Administrador==="NO") {
                                document.write("<td><a href=\"Login.jsp\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                            }
                            else document.write("<td><a href=\"ControllerEditions?Object=JuntaDirectiva&NIT="+NIT+"&PersonaId="+PersonaId+"&EmpleadoId="+EmpleadoId+"&JuntaId="+JuntaId+"\"><img width=\"30\" src=\"Lapiz3.gif\"></a></td>");
                        }
                        if("<%=Object%>"==="Directivas" || "<%=Object%>"==="Directivas2")
                        {
                            //ORIGINAL: TAMAÑO 20, IMAGEN: Lapiz.png
                            if (Administrador==="NO") {
                                document.write("<td><a href=\"Login.jsp\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                            }
                            else document.write("<td><a href=\"ControllerEditions?Object=Directive&NIT="+NIT+"&PersonaId="+PersonaId+"&EmpleadoId="+EmpleadoId+"\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                        }
                        if ("<%=Object%>" === "Empresa") {
                            document.write("<td><a href=\"ControllerOfLink?Object=ProgramCompany&NIT="+NIT+"\"><img width=\"20\" src=\"P1.ico\"></a></td>");
                            if(Administrador==="NO")
                            {
                                document.write("<td><a href=\"ControllerEditions?Object=Company&NIT="+NIT+"&ReadOnly=true\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                                //document.write("<td><a href=\"Login.jsp\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                            }
                            else document.write("<td><a href=\"ControllerEditions?Object=Company&NIT="+NIT+"&ReadOnly=false\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                        }
                        if("<%=Object%>"==="Empresa y Pagos")
                        {
                            if (Administrador==="NO") {
                                document.write("<td><a href=\"Login.jsp\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                            }
                            else
                            {
                                if(!EditaroCrear)
                                {   
                                    document.write("<td><a href=\"ControllerEditions?Object=SpecificPrograms&TipoProgramaId="+TipoProgramaID+"&NIT="+NITPrincipal+"\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                                }
                                else
                                {
                                    document.write("<td><a href=\"ControllerOfLink?Object=NEW&NIT="+NITPrincipal+"\"><img width=\"20\" src=\"Lapiz.png\"></a></td>");
                                }
                            }
                        }
                        document.write("</tr>");
                    }
                </script>                    
            </table>
        </div>
        <br><br><br>
        
                        
                        
    </body>
</html>
