/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EmpresaDAO;
import Modelo.ProgramaPagoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SONY
 */
@WebServlet(name = "ControllerProgramLink", urlPatterns = {"/ControllerProgramLink"})
public class ControllerProgramLink extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Objeto=request.getParameter("Object");
        String Programa = null;
        if(Objeto.equals("Environmental")) Programa="Ambiental";
        if(Objeto.equals("Security")) Programa="Seguridad";
        if(Objeto.equals("Mypes")) Programa="MYPES";
        if(Objeto.equals("Other"))Programa="Otros";
        if(Objeto.equals("Inactive")) Programa = "Inactivos";
        if(Objeto.equals("Opcion Devbida")) Programa = "Opción Devbida";
        String Cabecera= null;
        if(Objeto.equals("Other") || Objeto.equals("Inactive")) 
        {
            Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Pago\",\"Programa\",\"Dirección\",\"Teléfono 1\","
                + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Editar\"];";

        }else
        {
            Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Pago\",\"Dirección\",\"Teléfono 1\","
                + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Editar\"];";
        }
        ProgramaPagoDAO ProgramaDAO = new ProgramaPagoDAO();
        String ProgramasYPagos;
        try {
            request.getSession().setAttribute("EmpresaYNIT", new EmpresaDAO().ObtenerNITYNombre());
            ProgramasYPagos = ProgramaDAO.ObtenerProgramasYEncargados(Programa);
            request.setAttribute("Object","Programas y Encargados");
            request.setAttribute("ObjectDirectivas",Programa);
            request.setAttribute("Cabecera",Cabecera);
            request.setAttribute("VectorScript", ProgramasYPagos);
            request.getRequestDispatcher("ViewTable.jsp").forward(request, response); 
        } catch (SQLException ex) {
            ProgramasYPagos = "[[\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\"]]";
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
