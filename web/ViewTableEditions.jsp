<%@page import="Modelo.Administrador"%>
<%@page import="Modelo.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String Objeto = (String)request.getAttribute("Objeto");
    String Cabecera = (String)request.getAttribute("Cabecera");
    String VectorScript = (String)request.getAttribute("VectorScript");
    String Mensaje = (String)request.getAttribute("Mensaje");
    Administrador Admi = (Administrador)request.getSession().getAttribute("Administrador");
    if(Admi == null)Admi= new Administrador("", "NO", "", "");
   
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="Cabecera.jsp"></jsp:include>
        <link href="CSS/Login.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Table.css" type="text/css" rel="stylesheet" >
        <link href="CSS/Modal.css" type="text/css" rel="stylesheet" >
        <title>Lista de Ediciones</title>
    </head>
    <body>
        <script>
            if("<%=Mensaje%>" !== "null") alert("<%=Mensaje%>");
        </script>
        <h1>Lista de 
            <script>
                var Objeto="<%=Objeto%>";
                if(Objeto === "Alerta" )
                {
                    document.write("Alertas");
                }
                else document.write("Ediciones");
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
                    var Objeto = "<%=Objeto%>";
                    var Contenido = <%=VectorScript%>
                    for (var i = 0; i < Contenido.length; i++) {
                        document.write("<tr>");
                        for (var j = 0; j < Contenido[i].length; j++) {
                            if(Objeto === "Alerta")
                            {
                                var AlertaId = Contenido[i][0];
                                var NIT = Contenido[i][2];
                                if(j===3 && Administrador!=="NO")
                                {
                                    document.write('<td> <a href="ControllerEditions?Object=Company&NIT='+NIT+'">'+Contenido[i][j]+'</a> </td>');
                                }
                                else document.write("<td>"+Contenido[i][j]+"</td>");
                            }
                            else document.write("<td>"+Contenido[i][j]+"</td>");
                        }
                        if(Objeto === "Alerta")
                        {
                            document.write("<td><a href=\"ControllerNotifications?UpdateAlert=Edit&Id="+AlertaId+"\"><img width=\"35\" src=\"Lapiz3.gif\"></a></td>");
                            document.write("<td><a href=\"ControllerNotifications?UpdateAlert=Delete&Id="+AlertaId+"\"><img width=\"35\" src=\"Eliminar.png\"></a></td>");                            
                        }    
                        document.write("</tr>");
                    }
                </script>                    
            </table>
        </div>
        <br><br><br>
        
                        
                        
    </body>
</html>
