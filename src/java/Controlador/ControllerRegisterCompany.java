
package Controlador;

import Modelo.Administrador;
import Modelo.Alerta;
import Modelo.AlertaDAO;
import Modelo.Ediciones;
import Modelo.EdicionesDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerRegisterCompany", urlPatterns = {"/ControllerRegisterCompany"})
public class ControllerRegisterCompany extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String NIT = request.getParameter("txtNIT");
        String Nombre = request.getParameter("txtNombre");
        String Sociedad = request.getParameter("txtSociedad");
        String AnoCreacion = request.getParameter("txtAnoCreacion");
        String AnoAfiliacion = request.getParameter("txtAnoAfiliacion");
        String SectorProductivo = request.getParameter("txtSectorProductivo");
        String NumeroEmpleados = request.getParameter("txtEmpleados");
        String TamanoEmpresa  = request.getParameter("txtTamano");
        String Descripcion = request.getParameter("txtDescripcion");
        String SostenimientoPesos = request.getParameter("txtSostenimientoPesos");
        String SostenimientoSMLV = request.getParameter("txtSostenimientoSMLV");
        String DireccionPlanta = request.getParameter("txtDireccionPlanta");
        String MunicipioPlanta = request.getParameter("txtMunicipioPlanta");
        String DireccionAdministrativa = request.getParameter("txtDireccionAdministrativa");
        String MunicipioAdministrativa = request.getParameter("txtMunicipioAdministracion");
        String Activa = request.getParameter("RadioActiva");
        String RegistroEmpleados = request.getParameter("RadioAdministrativos");
        String ActivarAlerta = request.getParameter("RadioAlerta");
        if(ActivarAlerta == null)ActivarAlerta="NO";
        if (Activa == null) Activa="NO";
        if (RegistroEmpleados == null) RegistroEmpleados="NO";
        Empresa Company = new Empresa(NIT, Nombre, Sociedad,AnoCreacion, AnoAfiliacion,
                SectorProductivo, NumeroEmpleados, TamanoEmpresa, Descripcion, SostenimientoPesos,
                SostenimientoSMLV, DireccionPlanta, DireccionAdministrativa, MunicipioPlanta,
                MunicipioAdministrativa, Activa);
        EmpresaDAO CompanyDAO = new EmpresaDAO();
        HttpSession Sesion = request.getSession();
        
        try {
            CompanyDAO.Insertar(Company);
            request.setAttribute("Mensaje", "Empresa registrada con éxito");
            String Edicion = "";
            if(ActivarAlerta.equals("YES"))
            {
                String DescripcionAlerta = request.getParameter("txtDescripcionAlerta");
                new AlertaDAO().Insertar(new Alerta("", DescripcionAlerta, Company));
                Edicion+="-Se agregó Alerta";
            }
            new EdicionesDAO().Insertar(new Ediciones("", "", "Empresa", "NUEVA"+Edicion, Company.getNombre(), (Administrador)request.getSession().getAttribute("Administrador")));
            if(RegistroEmpleados.equals("YES"))
            {
                Sesion.setAttribute("Empresa", Company);
                request.getRequestDispatcher("EmployeeRegistration.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
            }
        }catch (SQLException ex) {
            request.setAttribute("Error","Ocurrió un error al ingresar la empresa "+Company.getNombre()+" "+Company.getSociedad()+" a la base de datos");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
            System.out.println("Error en controllerregistercomapani linea 78");
        }
        
    }

}
