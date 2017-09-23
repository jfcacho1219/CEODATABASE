
package Controlador;

import Modelo.Alerta;
import Modelo.AlertaDAO;
import Modelo.EdicionesDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerNotifications", urlPatterns = {"/ControllerNotifications"})
public class ControllerNotifications extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Objeto = request.getParameter("Object");
        String ModificarAlerta = request.getParameter("UpdateAlert");
        if(ModificarAlerta == null) ModificarAlerta="NO APLICA";
        if(Objeto == null) Objeto ="NO APLICA";
        if(!Objeto.equals("NO APLICA"))
        {
            if(Objeto.equals("Notifications"))
            {
                String Cabecera = "[\"Edición N°\",\"Fecha(aaaa-mm-dd)\",\"Entidad\",\"Valor Anterior o de Referencia\",\"Nombre del Atributo\",\"Correo del Editor\",\"Nombre completo del Editor\"]";
                try {
                    String ScritpEdiciones = new EdicionesDAO().ScriptEdiciones();
                    request.setAttribute("Cabecera", Cabecera);
                    request.setAttribute("VectorScript", ScritpEdiciones);
                    request.getRequestDispatcher("ViewTableEditions.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error", "Ocurrió un error al consultar las notificaciones");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            }
            if(Objeto.equals("Alert"))
            {
                String Cabecera = "[\"Alerta N°\",\"Fecha(aaaa-mm-dd)\",\"NIT\",\"Empresa\",\"Descripción\",\"Editar\",\"Eliminar\"]";
                String ScriptAlerta;
                try {
                    ScriptAlerta = new AlertaDAO().ScriptAlerta();
                    request.setAttribute("Cabecera", Cabecera);
                    request.setAttribute("VectorScript", ScriptAlerta);
                    request.setAttribute("Objeto", "Alerta");
                    request.getRequestDispatcher("ViewTableEditions.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error", "Ocurrió un error al acceder a la base de datos");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            }
        }
        if(!ModificarAlerta.equals("NO APLICA"))
        {
            String IdAlerta = request.getParameter("Id");
            if(ModificarAlerta.equals("Edit"))
            {
                Alerta Alert;
                try {
                    Alert = new AlertaDAO().NuevaAlerta(IdAlerta);
                    request.setAttribute("Alerta",Alert);
                    request.getRequestDispatcher("AlertEditions.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error","Ocurrió un error inesperado al acceder a las Alertas");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            }
            if(ModificarAlerta.equals("Delete"))
            {
                String Cabecera = "[\"Alerta N°\",\"Fecha(aaaa-mm-dd)\",\"NIT\",\"Empresa\",\"Descripción\",\"Editar\",\"Eliminar\"]";
                try {
                    new AlertaDAO().Eliminar(new AlertaDAO().NuevaAlerta(IdAlerta));
                    String ScriptAlerta = new AlertaDAO().ScriptAlerta();
                    request.setAttribute("Cabecera", Cabecera);
                    request.setAttribute("VectorScript", ScriptAlerta);
                    request.setAttribute("Objeto", "Alerta");
                    request.getRequestDispatcher("ViewTableEditions.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error", "Ocurrió un error al eliimnar la Alerta");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            }
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Id = request.getParameter("txtAlertaId");
        String NIT = request.getParameter("txtNIT");
        String Descripcion = request.getParameter("txtDescripcion");
        AlertaDAO AlertDAO = new AlertaDAO();
        try {
            String Cabecera = "[\"Alerta N°\",\"Fecha(aaaa-mm-dd)\",\"NIT\",\"Empresa\",\"Descripción\",\"Editar\",\"Eliminar\"]";
            Empresa Company = new EmpresaDAO().NuevaEmpresa(NIT);
            AlertDAO.Editar(new Alerta(Id, Descripcion, Company));
            String ScriptAlerta = AlertDAO.ScriptAlerta();
            request.setAttribute("Cabecera", Cabecera);
            request.setAttribute("VectorScript", ScriptAlerta);
            request.setAttribute("Objeto", "Alerta");
            request.setAttribute("Mensaje", "Se editó con éxito la Alerta de la empresa "+Company.getNombre());
            request.getRequestDispatcher("ViewTableEditions.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
