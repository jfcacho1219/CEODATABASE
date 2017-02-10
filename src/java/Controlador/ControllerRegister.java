/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Modelo.AdministradorDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControllerRegister", urlPatterns = {"/ControllerRegister"})
public class ControllerRegister extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
