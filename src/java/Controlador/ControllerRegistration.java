package Controlador;

import Modelo.ComiteDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import Modelo.TipoProgramaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerRegistration", urlPatterns = {"/ControllerRegistration"})
public class ControllerRegistration extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Objeto = request.getParameter("txtObjetoModal");
        String NITLista = request.getParameter("txtNIT2");
        String NIT = request.getParameter("txtNIT");
        if(!NITLista.equals("0")) NIT = NITLista;
        HttpSession Sesion = request.getSession();

        try {
            Empresa Company = new EmpresaDAO().NuevaEmpresa(NIT);
            String VectorJavaScript = new TipoProgramaDAO().ObtenerTipoPrograma();
            String ComiteScript = new ComiteDAO().ScriptComite();
            Sesion.setAttribute("Comite", ComiteScript);
            Sesion.setAttribute("ScriptProgramas", VectorJavaScript);
            Sesion.setAttribute("Empresa", Company);

        } catch (SQLException ex) {
            request.setAttribute("Error","Ocurrió un error al acceder a la base de datos por la empresa de NIT: "+NIT);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        if (Objeto.equals("Directivas") || Objeto.equals("Directivas2") )
        {
            request.getRequestDispatcher("EmployeeRegistration.jsp").forward(request, response);
        }
        else if(Objeto.equals("Programas y Encargados") || Objeto.equals("Junta Directiva"))
        {
            request.getRequestDispatcher("ProgramRegistration.jsp").forward(request, response);
        }
    }

}
