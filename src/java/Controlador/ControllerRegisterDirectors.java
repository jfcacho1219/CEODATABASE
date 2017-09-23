
package Controlador;

import Modelo.Administrador;
import Modelo.Ediciones;
import Modelo.EdicionesDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import Modelo.JuntaDirectiva;
import Modelo.JuntaDirectivaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerRegisterDirectors", urlPatterns = {"/ControllerRegisterDirectors"})
public class ControllerRegisterDirectors extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Empresa Company = (Empresa) request.getSession().getAttribute("Company");
        String NIT = request.getParameter("txtNIT");
        String Calidad = request.getParameter("txtCalidad");
        String PersonaId = request.getParameter("txtPersonaId");
        String EmpleadoId = request.getParameter("txtEmpleadoId");
        String Continuar = request.getParameter("RadioAdministrativos");
        String Programas = request.getParameter("RadioProgramas");
        if (Continuar == null) 
            Continuar="NO";
        if (Programas == null) 
            Programas = "NO";
        
        if(Calidad.equals("OTRO"))
        {
            Calidad = request.getParameter("txtCalidad2");
        }
        JuntaDirectiva Junta = new JuntaDirectiva("", Calidad, NIT, EmpleadoId, PersonaId);
        JuntaDirectivaDAO JuntaDAO = new JuntaDirectivaDAO();
        HttpSession Sesion = request.getSession();
        try {
            JuntaDAO.InsertarJunta(Junta);
            new EdicionesDAO().Insertar(new Ediciones("", "", "Junta Directiva", "NUEVA", Company.getNombre(), (Administrador)request.getSession().getAttribute("Administrador")));

            Sesion.setAttribute("Empresa",new EmpresaDAO().NuevaEmpresa(NIT));
            request.setAttribute("Mensaje", "Todos los datos de de la empresa y su vínculo con la Junta Directiva han sido guardados correctamente");
            if(Continuar.equals("YES"))
            {
                request.getRequestDispatcher("EmployeeRegistration.jsp").forward(request, response);
            }
            else if(Programas.equals("YES"))
            {
                request.getRequestDispatcher("ProgramRegistration.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("CreateReadUpdateDelete").forward(request, response);
            }
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1452)
            {
                request.setAttribute("Error", "La empresa con NIT: "+Junta.getNIT()+" no se encuentra en el sistema");
            }
            else
            {
                request.setAttribute("Error", "ocurrió un error al acceder a la base de datos");
            }
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }
}
