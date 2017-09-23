
package Controlador;

import Modelo.Administrador;
import Modelo.AdministradorDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerRegister", urlPatterns = {"/ControllerRegister"})
public class ControllerRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Correo = request.getParameter("txtCorreo");
        String Nombre= request.getParameter("txtNombre");
        String Apellidos = request.getParameter("txtApellidos");
        String ContrasenaAdmi = request.getParameter("txtContrasenaAdmi");
        String Contrasena = request.getParameter("txtContrasena");
        String ConfirmarContrasena = request.getParameter("txtConfirmarContrasena");
        Administrador Admi = new Administrador(Correo, Nombre, Apellidos, Contrasena);
        AdministradorDAO AdmiDAO = new AdministradorDAO();
        String Autorizacion = AdmiDAO.ValidarContrasena(ContrasenaAdmi);
        HttpSession Sesion = request.getSession();

        if(Autorizacion.equals("YES") && Admi.ContrasenasIguales(ConfirmarContrasena))
        {
            try {
                AdmiDAO.IngresarAdministrador(Admi);
                Sesion.setAttribute("Administrador", Admi);
                request.getRequestDispatcher("Inicio.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("Error","Ocurrio un error al Ingresar "+Admi.getNombre()+" "+Admi.getApellidos()+" a la base de datos");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
            
        }
        else
        {
            if (Autorizacion.equals("NO")) {
                Sesion.setAttribute("OtraContrasena","Contraseña de otro Administrador no existente");
                request.getRequestDispatcher("RegistroAdministrador.jsp").forward(request, response);
            }
            else
            {
                if(!Admi.ContrasenasIguales(ConfirmarContrasena))
                {
                    Sesion.setAttribute("OtraContrasena","Campo Contraseña y Confirmar Contraseña Incorrectos");
                    request.getRequestDispatcher("RegistroAdministrador.jsp").forward(request, response);
                    return;
                }
                else
                {
                    request.setAttribute("Error","Ocurrio un error al conectarse con la base de datos");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            }
            
        }        
    }


}
