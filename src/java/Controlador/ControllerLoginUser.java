/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Modelo.AdministradorDAO;
import Modelo.ComiteDAO;
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
