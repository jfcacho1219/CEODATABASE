/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Comite;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import Modelo.Persona;
import Modelo.ProgramaPago;
import Modelo.ProgramaPagoDAO;
import Modelo.TipoPrograma;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SONY
 */
@WebServlet(name = "ControllerSearch", urlPatterns = {"/ControllerSearch"})
public class ControllerSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Objeto = request.getParameter("txtObjeto");
        try {
            request.getSession().setAttribute("EmpresaYNIT", new EmpresaDAO().ObtenerNITYNombre());
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error en la clase \"ControllerSearch linea 47\"");
        }

        if (Objeto.equals("Empresa")) {
            String NIT = request.getParameter("txtNIT");
            String Nombre = request.getParameter("txtNombre");
            String Sociedad = request.getParameter("txtSociedad");
            String AnoCreacion1 = request.getParameter("txtAnoCreacion1");
            String AnoCreacion2= request.getParameter("txtAnoCreacion2");
            String AnoAfiliacion1 = request.getParameter("txtAnoAfiliacion1");
            String AnoAfiliacion2 = request.getParameter("txtAnoAfiliacion2");
            String SectorProductivo = request.getParameter("txtSectorProductivo");
            String NumeroEmpleados = request.getParameter("txtEmpleados");
            String NumeroEmpleadosPrimerValor = NumeroEmpleados.split("-")[0];
            String NumeroEmpleadoSegundoValor;
            try {
                if (NumeroEmpleadosPrimerValor.equals("")) NumeroEmpleadosPrimerValor="0";
                if(!NumeroEmpleados.split("-")[1].equals("")) NumeroEmpleadoSegundoValor = NumeroEmpleados.split("-")[1];
                else NumeroEmpleadoSegundoValor ="0";
            } catch (Exception e) {
                NumeroEmpleadoSegundoValor = "0";
            }
            String TamanoEmpresa  = request.getParameter("txtTamano");
            String Descripcion = request.getParameter("txtDescripcion");
            String SostenimientoPesos = request.getParameter("txtSostenimientoPesos");
            String SostenimientoPesosPrimerValor = SostenimientoPesos.split("-")[0];
            String SostenimientoPesosSegundoValor;
            try {
                if(SostenimientoPesosPrimerValor.equals("")) SostenimientoPesosPrimerValor="0";
                if(!SostenimientoPesos.split("-")[1].equals("")) SostenimientoPesosSegundoValor = SostenimientoPesos.split("-")[1];
                else SostenimientoPesosSegundoValor = "0";
            } catch (Exception e) {
                SostenimientoPesosSegundoValor = "0";
            }
            String SostenimientoSMLV = request.getParameter("txtSostenimientoSMLV");
            String SostenimientoSMLVPrimverValor = SostenimientoSMLV.split("-")[0];
            String SostenimientoSMLVSegundoValor;
            try {
                if(SostenimientoSMLVPrimverValor.equals(""))SostenimientoSMLVPrimverValor ="0";
                if(!SostenimientoSMLV.split("-")[1].equals(""))SostenimientoSMLVSegundoValor = SostenimientoSMLV.split("-")[1];
                else SostenimientoSMLVSegundoValor = "0";
            } catch (Exception e) {
                SostenimientoSMLVSegundoValor = "0";
            }
            String DireccionPlanta = request.getParameter("txtDireccionPlanta");
            String MunicipioPlanta = request.getParameter("txtMunicipioPlanta");
            String DireccionAdministrativa = request.getParameter("txtDireccionAdministrativa");
            String MunicipioAdministrativa = request.getParameter("txtMunicipioAdministracion");
            String Activa = request.getParameter("RadioActiva");
            if(Activa==null) Activa="NO";
            String VariablesNumericas [] = {NumeroEmpleadosPrimerValor,NumeroEmpleadoSegundoValor,
                SostenimientoPesosPrimerValor,SostenimientoPesosSegundoValor,
                SostenimientoSMLVPrimverValor,SostenimientoSMLVSegundoValor};
            String VariablesFecha [] = {AnoAfiliacion1,AnoAfiliacion2,AnoCreacion1,AnoCreacion2};
            String VariablesEmpresa[] = {NIT,Nombre,Sociedad,TamanoEmpresa,Descripcion,DireccionPlanta,
                MunicipioPlanta,DireccionAdministrativa,MunicipioAdministrativa,Activa,SectorProductivo};
            EmpresaDAO CompanyDAO = new EmpresaDAO();
            String Empresas = null;
            try {
                Empresas = CompanyDAO.ObtenerEmpresas(VariablesEmpresa,VariablesFecha,VariablesNumericas);
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al buscar las empresas, ten cuidado con la forma en la que se ingresan los datos,puede ser la causa del error ");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
            String Cabecera = "[\"NIT\", \"Razón Social\", \"Tipo de Sociedad\","+
                "\"Fecha de Creación\",\"Fecha de Afiliación\", \"Sector Productivo\",\"N° de Empleados\","+
                "\"Tamaño\",\"Descripción\",\"Sostenimiento ($Pesos)\",\"Sostenimiento SMLV\",\"Dirección Planta\","+
                "\"Direccion Administrativa\",\"Municipio Planta\",\"Municipio Administrativa\",\"Programas\",\"Editar\"]";
            request.setAttribute("Object","Empresa");
            request.setAttribute("Cabecera",Cabecera);
            request.setAttribute("VectorScript", Empresas);
            request.getRequestDispatcher("ViewTable.jsp").forward(request, response);

            
        }
        else if(Objeto.equals("Comite"))
        {
            String EmpleosYPersonas = null;
            String Comite = request.getParameter("txtComite");
            String NIT = request.getParameter("txtNITComite");
            String NombreEmpresa = request.getParameter("txtNombreEmpresa");
            String Email1 = request.getParameter("txtEmailComite");
            String Tel1 = request.getParameter("txtTelComite");
            String CargoEmpresa = request.getParameter("txtCargoEmpresaComite");
            String Cedula = request.getParameter("txtCedulaComite");
            String Nombre = request.getParameter("txtNombrePersonaComite");
            String Apellidos = request.getParameter("txtApellidosComite");
            String FechaNacimiento = request.getParameter("txtCumpleComite");
            String FechaNacimiento2 = request.getParameter("txtCumpleComite2");
            String Mes = request.getParameter("txtMesComite");
            String Titulo = request.getParameter("txtTituloComite");

            Empleado Emple = new Empleado("", new Empresa(NIT, NombreEmpresa,"","","","","","","","","","","","","",""), 
                    Email1, Email1, Tel1, Tel1,
                    new Persona("", Cedula, Nombre, Apellidos,FechaNacimiento+"&"+FechaNacimiento2+"&"+Mes, Titulo,CargoEmpresa),
                    new Comite("", Comite));

            String Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Dirección\",\"Teléfono 1\","
            + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Comité\",\"Editar\"];";
            request.setAttribute("Object","Directivas2");
            request.setAttribute("Cabecera",Cabecera);
            try {
                EmpleosYPersonas = new EmpleadoDAO().ObtenerCargosYPersonas(Emple);
                request.setAttribute("VectorScript", EmpleosYPersonas);
                request.getRequestDispatcher("ViewTable.jsp").forward(request, response); 
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al buscar los comites, ten cuidado con la forma en la que se ingresan los datos,puede ser la causa del error ");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        }else if(Objeto.equals("Programas"))
        {
            String Programa = request.getParameter("txtPrograma");
            String NIT = request.getParameter("txtNITPrograma");
            String NombreEmpresa = request.getParameter("txtNombreEmpresaPrograma");
            String Email1 = request.getParameter("txtEmailPrograma");
            String Tel1 = request.getParameter("txtTelPrograma");
            String CargoEmpresa = request.getParameter("txtCargoEmpresaPrograma");
            String Cedula = request.getParameter("txtCedulaPrograma");
            String Nombre = request.getParameter("txtNombrePersonaPrograma");
            String Apellidos = request.getParameter("txtApellidosPrograma");
            String FechaNacimiento = request.getParameter("txtCumplePrograma");
            String FechaNacimiento2 = request.getParameter("txtCumplePrograma2");
            String Mes = request.getParameter("txtMesPrograma");
            String Titulo = request.getParameter("txtTituloPrograma");
            String Pago = request.getParameter("txtPagoPrograma");
 
            String Cabecera;
            Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Pago\",\"Programa\",\"Dirección\",\"Teléfono 1\","
                + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Editar\"];";
            ProgramaPagoDAO ProgramaDAO = new ProgramaPagoDAO();
            String ProgramasYPagos;
            Empleado Emple = new Empleado("", new Empresa(NIT, NombreEmpresa,"","","","","","","","","","","","","",""), Email1, Email1, Tel1, Tel1, new Persona("", Cedula, Nombre, Apellidos,FechaNacimiento+"&"+FechaNacimiento2+"&"+Mes, Titulo,CargoEmpresa), null);
            ProgramaPago Program = new ProgramaPago(Pago, new TipoPrograma("", Programa, ""), Emple);
            try {
                ProgramasYPagos = ProgramaDAO.ObtenerProgramasYEncargados(Program);
                request.setAttribute("Object","Programas y Encargados");
                request.setAttribute("ObjectDirectivas","Otros");
                request.setAttribute("Cabecera",Cabecera);
                request.setAttribute("VectorScript", ProgramasYPagos);
                request.getRequestDispatcher("ViewTable.jsp").forward(request, response); 
            } catch (SQLException ex) {
                ProgramasYPagos = "[[\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\"]]";
            }
            
        }else
        {
            request.getRequestDispatcher("Search.jsp").forward(request, response); 
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
