
package Controlador;

import Modelo.Empresa;
import Modelo.EmpresaDAO;
import Modelo.JuntaDirectivaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerOfLink", urlPatterns = {"/ControllerOfLink"})
public class ControllerOfLink extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Objeto=request.getParameter("Object");
        try {
            request.getSession().setAttribute("EmpresaYNIT", new EmpresaDAO().ObtenerNITYNombre());
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error en el controlador \"ControllerOfLink\" linea 40");
        }

        if(Objeto.equals("NoAffiliatedCompany")||Objeto.equals("AffiliatedCompany"))
        {
            EmpresaDAO CompanyDAO = new EmpresaDAO();
            String Empresas;
            try {
                Empresas = CompanyDAO.ObtenerEmpresas(Objeto);
                String Cabecera = "[\"NIT\", \"Razón Social\", \"Tipo de Sociedad\","+
                    "\"Fecha de Creación\",\"Fecha de Afiliación\", \"Sector Productivo\",\"N° de Empleados\","+
                    "\"Tamaño\",\"Descripción\",\"Sostenimiento ($Pesos)\",\"Sostenimiento SMLV\",\"Dirección Planta\","+
                    "\"Direccion Administrativa\",\"Municipio Planta\",\"Municipio Administrativa\",\"Programas\",\"Editar\"]";
                request.setAttribute("Object","Empresa");
                request.setAttribute("ObjectDirectivas",Objeto);
                request.setAttribute("Cabecera",Cabecera);
                request.setAttribute("VectorScript", Empresas);
                request.getRequestDispatcher("ViewTable.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                Empresas = "[[\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",]]";
            }
        }
        else 
        if(Objeto.equals("Program"))
        {
            response.sendRedirect("ProgramLink.jsp");

        }
        else
        if(Objeto.equals("Directorate"))
        {
            JuntaDirectivaDAO JuntaDAO = new JuntaDirectivaDAO();
            String JuntaYEmpresa;
            try {
                JuntaYEmpresa = JuntaDAO.ObtenerJuntaYEmpresa();
                String Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Dirección\",\"Teléfono 1\","
                + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Tipo de Miembro:\",\"Editar\"];";
                request.setAttribute("Object","Junta Directiva");
                request.setAttribute("Cabecera",Cabecera);
                request.setAttribute("VectorScript", JuntaYEmpresa);
                request.getRequestDispatcher("ViewTable.jsp").forward(request, response); 
            
            } catch (SQLException ex) {
                JuntaYEmpresa = "[[\"error\",\"error\",\"error\",\"error\"]]";
 
            }
        }
        else
        if(Objeto.equals("ProgramCompany"))
        {
            String NIT = request.getParameter("NIT");
            JuntaDirectivaDAO JuntaDAO = new JuntaDirectivaDAO();
            String ProgramasYPrecios;
            HttpSession Sesion = request.getSession();
            Sesion.setAttribute("NITPrincipal", NIT);
            try {
                ProgramasYPrecios = JuntaDAO.ObtenerJuntaEmpresa(NIT);
                request.setAttribute("Company", new EmpresaDAO().NuevaEmpresa(NIT));
                String Cabecera = "[\"Pago\", \"Nombre\",\"Editar\"];";
                request.setAttribute("Object","Empresa y Pagos");
                request.setAttribute("Cabecera",Cabecera);
                request.setAttribute("VectorScript", ProgramasYPrecios);
                request.getRequestDispatcher("ViewTable.jsp").forward(request, response); 
            } catch (SQLException ex) {
                System.out.println("Error en controller of link linea 92");
            }
        }
        if (Objeto.equals("NEW")) {
            String NIT = request.getParameter("NIT");
            EmpresaDAO CompanyDAO = new EmpresaDAO();
            HttpSession Sesion = request.getSession();
            Empresa Company = null;

            try {
                Company = CompanyDAO.NuevaEmpresa(NIT);
                Sesion.setAttribute("Empresa", Company);
                request.getRequestDispatcher("ProgramRegistration.jsp").forward(request, response); 

            } catch (SQLException ex) {
                System.out.println("Error: "+ex);
                request.setAttribute("Error","Ocurrió un error al solicitar la empresa de NIT "+NIT);
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }       
        }
    }
}
