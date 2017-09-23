
package Controlador;

import Modelo.Administrador;
import Modelo.Ediciones;
import Modelo.EdicionesDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.EmpresaDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.ProgramaPago;
import Modelo.ProgramaPagoDAO;
import Modelo.TipoPrograma;
import Modelo.TipoProgramaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerProgramRegistration", urlPatterns = {"/ControllerProgramRegistration"})
public class ControllerProgramRegistration extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Programa = request.getParameter("txtPrograma");
        String NIT = request.getParameter("txtNIT");
        String Email1 = request.getParameter("txtEmail1");
        String Email2 = request.getParameter("txtEmail1");
        String Telefono1 = request.getParameter("txtTel1");
        String Telefono2 = request.getParameter("txtTel2");
        String Pago = request.getParameter("txtPago");
        String Cargo = request.getParameter("txtCargoEmpresa");
        String Cedula = request.getParameter("txtCedula");
        String Nombre = request.getParameter("txtNombre");
        String Apellidos = request.getParameter("txtApellidos");
        String Cumple = request.getParameter("txtCumple");
        String Titulo = request.getParameter("txtTitulo");
        String ContinuarRegistro = request.getParameter("RadioContinuarRegistro");
        String RegistrarEmpresa = request.getParameter("RadioRegistrarEmpresa");
        String Descripcion = "";
        if(ContinuarRegistro == null) ContinuarRegistro = "NO";
        if(RegistrarEmpresa == null) RegistrarEmpresa = "NO";
        Boolean Persona = true;
        Boolean ProgramPagoValidar = true;
        ProgramaPagoDAO ProgramaPagoDAO = new ProgramaPagoDAO();
        if (Programa.equals("OTRO")) {
            Programa = request.getParameter("txtPrograma2");
            Descripcion = request.getParameter("txtDescripcion");
            try {
                new TipoProgramaDAO().Insertar(new TipoPrograma("", Programa,Descripcion ));
                String Edicion = Programa;
                Programa = new TipoProgramaDAO().ObtenerId(Programa);
                new EdicionesDAO().Insertar(new Ediciones("", "", "Tipo de Programa", "NUEVO", Edicion, (Administrador)request.getSession().getAttribute("Administrador")));
            } catch (SQLException ex) {
                request.setAttribute("Error", "No se ha podido Ingresar al sistema "+Nombre+" "+Apellidos+"y el nuevo Programa");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        }
        Persona Person;
        PersonaDAO PersonDAO  = new PersonaDAO();
        String PersonaId = null;
        ProgramaPago programaPago;
        EmpleadoDAO EmpleDAO = new EmpleadoDAO();
        ProgramaPagoDAO ProgramDAO = new ProgramaPagoDAO();
        Empleado Emple;
        try {
            PersonaId = new PersonaDAO().ObtenerIdMayor();
            Person = new Persona(PersonaId, Cedula, Nombre, Apellidos, Cumple, Titulo, Cargo);
            PersonDAO.Insertar(Person);
            Emple = new Empleado("", new EmpresaDAO().NuevaEmpresa(NIT), Email1, Email2, Telefono1, Telefono2, Person, null);
            int Id = (EmpleDAO.ObtenerIdMayor() + 1);
            Emple.setID(Integer.toString(Id));
            EmpleDAO.Insertar(Emple,true);
            programaPago = new ProgramaPago(Pago, new TipoPrograma(Programa, Programa, Descripcion), Emple);
            ProgramDAO.Insertar(programaPago);
            new EdicionesDAO().Insertar(new Ediciones("", "", "Programa y Encargado "+new TipoProgramaDAO().NuevoTipoPrograma(programaPago.getTipo().getNombre()).getNombre(), 
                    "NUEVO",programaPago.getEmple().getPersona().getNombre()+" "+programaPago.getEmple().getPersona().getApellidos(),
                    (Administrador)request.getSession().getAttribute("Administrador")));

        } catch (SQLException ex) {
            try {
                PersonDAO.Eliminar(PersonaId);
            } catch (SQLException ex1) {
            }
            request.setAttribute("Error", "No se ha podido Ingresar al sistema\" "+Nombre+" "+Apellidos+"\" Con su respectivo programa");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

        if (ContinuarRegistro.equals("YES")) 
        {
            request.setAttribute("Mensaje", "Programa, y su encargado "+Nombre+" "+"Registrados con éxito");
            request.getRequestDispatcher("ProgramRegistration.jsp").forward(request, response);    
        }
        if(RegistrarEmpresa.equals("YES"))
        {
            request.setAttribute("Mensaje", "Programa, y su encargado "+Nombre+" "+"Registrados con éxito");
            request.getRequestDispatcher("RegistroEmpresa.jsp").forward(request, response);    
        }
        if(!RegistrarEmpresa.equals("YES") && !ContinuarRegistro.equals("YES"))
        {
            request.setAttribute("Mensaje", "Programa, y su encargado "+Nombre+" "+"Registrados con éxito");
            request.getRequestDispatcher("Inicio.jsp").forward(request, response);    
        }
    }

}
