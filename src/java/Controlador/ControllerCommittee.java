
package Controlador;

import Modelo.EmpleadoDAO;
import Modelo.EmpresaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControllerCommittee", urlPatterns = {"/ControllerCommittee"})
public class ControllerCommittee extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String Objeto=request.getParameter("Object");
        String Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Dirección\",\"Teléfono 1\","
            + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Editar\"];";
        
        if(Objeto.equals("Other")||Objeto.equals("Inactive"))
        {
            Cabecera = "[\"Trato\",\"Nombre Completo\",\"Cargo\",\"Empresa\",\"Dirección\",\"Teléfono 1\","
            + "\"Teléfono 2\",\"Correo Electrónico 1\",\"Correo Electrónico 2\",\"Fecha de Cumpleaños\",\"Cédula\",\"Comite\",\"Editar\"];"; 
        }    
            
        EmpleadoDAO empleadodao = new EmpleadoDAO();
        String Comite = null;
        String EmpleosYPersonas;
        if(Objeto.equals("Manager")) Comite = "Gerentes";
        if(Objeto.equals("HumanManagement")) Comite = "Gestión Humana"; 
        if(Objeto.equals("InternationalBusiness")) Comite = "Comercio Exterior";                  
        if(Objeto.equals("Technical")) Comite = "Técnico";            
        if(Objeto.equals("Purchases")) Comite = "Compras";            
        if(Objeto.equals("Security")) Comite = "Seguridad";            
        if(Objeto.equals("Environmental")) Comite = "Ambiental";            
        if(Objeto.equals("Tics")) Comite = "Tics";            
        if(Objeto.equals("Other")) Comite = "Otros";  
        if(Objeto.equals("Inactive")) Comite = "Inactivos"; 
        if(Objeto.equals("Mypes")) Comite = "MYPES";  
        if(Objeto.equals("Opcion Devbida"))Comite = "Opción Devbida";
        try {
            request.getSession().setAttribute("EmpresaYNIT", new EmpresaDAO().ObtenerNITYNombre());
            request.setAttribute("Object","Directivas");
            request.setAttribute("ObjectDirectivas",Comite);
            request.setAttribute("Cabecera",Cabecera);
            EmpleosYPersonas = empleadodao.ObtenerCargosYPersonas(Comite);
        } catch (SQLException ex) {
            EmpleosYPersonas = "[[\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\",\"error\"]]";
        }
        request.setAttribute("VectorScript", EmpleosYPersonas);
        request.getRequestDispatcher("ViewTable.jsp").forward(request, response); 

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
