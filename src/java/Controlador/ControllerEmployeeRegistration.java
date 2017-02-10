/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Modelo.Comite;
import Modelo.ComiteDAO;
import Modelo.Ediciones;
import Modelo.EdicionesDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.TipoProgramaDAO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author SONY
 */
@WebServlet(name = "ControllerEmployeeRegistration", urlPatterns = {"/ControllerEmployeeRegistration"})
public class ControllerEmployeeRegistration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean ValidarPersona = false;
        boolean ValidarEmple = false;
        String Comite = request.getParameter("txtCargo");
        String NIT = request.getParameter("txtNIT");
        String Email1 = request.getParameter("txtEmail1");
        String Email2 = request.getParameter("txtEmail2");
        String Tel1 = request.getParameter("txtTel1");
        String Tel2 = request.getParameter("txtTel2");
        String CargoEmpresa = request.getParameter("txtCargoEmpresa");
        String Cedula = request.getParameter("txtCedula");
        String Nombre = request.getParameter("txtNombre");
        String Apellidos = request.getParameter("txtApellidos");
        String FechaNacimiento = request.getParameter("txtCumple");
        String Titulo = request.getParameter("txtTitulo");
        String Continuar = request.getParameter("RadioContinuar");
        String RegistrarPrograma = request.getParameter("RadioRegistrarPrograma");
        String Junta = request.getParameter("RadioJunta");
        Persona Person = null;
        Empleado Emple = null;
        try {
            if (Junta == null) Junta = "NO";
            else
            {
                Continuar = "NO";
                RegistrarPrograma="NO";
            }
            if(Continuar == null) Continuar = "NO";
            if(RegistrarPrograma ==  null) RegistrarPrograma="NO";
            //
            
            HttpSession Sesion = request.getSession();
            PersonaDAO PersonDAO = new PersonaDAO();            
            Person = new Persona(PersonDAO.ObtenerIdMayor(), Cedula, Nombre, Apellidos, FechaNacimiento, Titulo,CargoEmpresa);
            Comite Comit = new Comite(Comite, Comite);
            EmpleadoDAO EmpleDAO = new EmpleadoDAO();
            if(Comite.equals("OTRO"))
            {
                Comite = request.getParameter("txtCargo2");
                String Edicion = Comite;
                Comit = new Comite(Comite, Comite);
                ComiteDAO ComDAO = new ComiteDAO();
                ComDAO.Insertar(Comit);
                Comit = new Comite(new ComiteDAO().ObtenerId(Comite),Comite);
                String ScriptComite = ComDAO.ScriptComite();
                Sesion.setAttribute("Comite", ScriptComite);
                new EdicionesDAO().Insertar(new Ediciones("", "", "Comite", "NUEVO", Edicion, (Administrador)request.getSession().getAttribute("Administrador")));

            }
            Emple = new Empleado(Integer.toString(EmpleDAO.ObtenerIdMayor()+1), 
                    new EmpresaDAO().NuevaEmpresa(NIT),
                    Email1, Email2, Tel1, Tel2, Person, Comit);
            PersonDAO.Insertar(Person);
            ValidarPersona = true;
            EmpleDAO.Insertar(Emple);
            ValidarEmple = true;
            new EdicionesDAO().Insertar(new Ediciones("", "", "Comite "+new ComiteDAO().NuevoComite(Comit.getId()).getNombre(), "NUEVO",
                    Emple.getPersona().getNombre()+" "+Emple.getPersona().getApellidos() , 
                    (Administrador)request.getSession().getAttribute("Administrador")));
            if (Junta.equals("YES")) {
                request.setAttribute("Encargado",Emple);
                request.getRequestDispatcher("RegistroJuntaDirectiva.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("Mensaje", Person.getNombre()+" "+Person.getApellidos()+" ha sido registrado con éxito");
                if (Continuar.equals("YES")) {
                    request.getRequestDispatcher("EmployeeRegistration.jsp").forward(request, response);
                }
                else
                {
                    if(RegistrarPrograma.equals("YES"))request.getRequestDispatcher("ProgramRegistration.jsp").forward(request, response);
                    else request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            System.out.println("oeeeeeeeee"+ex);
            if (ValidarPersona || ValidarEmple) {
                try {
                    new PersonaDAO().Eliminar(Person.getId());
                } catch (SQLException ex1) {
                }
            }
            request.setAttribute("Error", "Ocurrió un error al ingresar a "+Person.getNombre()+" "+Person.getApellidos()+". Por favor intenta de nuevo");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
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
