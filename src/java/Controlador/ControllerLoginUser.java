
package Controlador;

import Modelo.Administrador;
import Modelo.AdministradorDAO;
import Modelo.ComiteDAO;
import Modelo.TipoProgramaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerLoginUser", urlPatterns = {"/ControllerLoginUser"})
public class ControllerLoginUser extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Correo = request.getParameter("txtCorreo");
        String Contrasena = request.getParameter("txtContrasena");
        AdministradorDAO AdmiDAO = new AdministradorDAO();
        Boolean Iniciar = false;
        Administrador Admi = null;
        try {
            Admi = AdmiDAO.IniciarSesion(Correo);
            Iniciar = Admi.ContrasenasIguales(Contrasena);
        } catch (SQLException ex) {
            request.setAttribute("Error", "No se ha podido Ingresar al sistema debido a que el usuario no existe");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        if (Iniciar) {
            HttpSession Sesion = request.getSession();
            try {
                String ComiteScript = new ComiteDAO().ScriptComite();
                Sesion.setAttribute("Comite", ComiteScript);
            } catch (SQLException ex) {
                request.setAttribute("Error", "Ocurrió un error al acceder a la base de datos");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
            String VectorJavaScript = new TipoProgramaDAO().ObtenerTipoPrograma();
            Sesion.setAttribute("ScriptProgramas", VectorJavaScript);
            Sesion.setAttribute("Administrador", Admi);
            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("ContraIncorrecta", "Contraseña Incorrecta");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
