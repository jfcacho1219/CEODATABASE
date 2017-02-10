package Controlador;

import Modelo.Administrador;
import Modelo.Alerta;
import Modelo.AlertaDAO;
import Modelo.Comite;
import Modelo.ComiteDAO;
import Modelo.Ediciones;
import Modelo.EdicionesDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Empresa;
import Modelo.EmpresaDAO;
import Modelo.JuntaDirectiva;
import Modelo.JuntaDirectivaDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.ProgramaPago;
import Modelo.ProgramaPagoDAO;
import Modelo.TipoPrograma;
import Modelo.TipoProgramaDAO;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ControllerEditions", urlPatterns = {"/ControllerEditions"})
public class ControllerEditions extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Objeto = request.getParameter("Object");
        if(Objeto.equals("SpecificPrograms"))
        {
            String TipoProgramaId = request.getParameter("TipoProgramaId");
            String NIT = request.getParameter("NIT");
            ProgramaPagoDAO ProgramDAO = new ProgramaPagoDAO();
            try {
                ProgramaPago Program = ProgramDAO.NuevoProgramaPago(TipoProgramaId, NIT);
                request.setAttribute("Programa",Program);
                request.getRequestDispatcher("ProgramMoneyEdition.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al editar el programa");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
            
        }
        if (Objeto.equals("Company")) {
            String NIT = request.getParameter("NIT");
            EmpresaDAO CompanyDAO = new EmpresaDAO();
            Empresa Company = new Empresa("",":(","","","","","","","","","","","","","","");
            try {
                Company = CompanyDAO.NuevaEmpresa(NIT);
                String Read = request.getParameter("ReadOnly");
                request.setAttribute("Company", Company);
                if(Read.equals("false"))Read="";
                else Read="hidden=\"false\"";
                request.setAttribute("ReadOnly", Read);
                request.getRequestDispatcher("CompanyEdition.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al editar la Empresa: "+Company.getNombre()+" "+Company.getSociedad());
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        }
        if (Objeto.equals("Directive")) {
            String NIT = request.getParameter("NIT");
            String PersonaId = request.getParameter("PersonaId");
            String EmpleadoId = request.getParameter("EmpleadoId");
            EmpleadoDAO EmpleDAO = new EmpleadoDAO();
            Empleado Emple = null;
            try {
                
                Emple = EmpleDAO.NuevoEmpleado(NIT,PersonaId,EmpleadoId);
                request.setAttribute("Empleado", Emple);
                request.getRequestDispatcher("EmployeeEdition.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println(ex);
                request.setAttribute("Error","Ocurrió un error al editar los administrativos de la Empresa");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        }
        if (Objeto.equals("ProgramPerson")) {
            String Cedula = request.getParameter("Cedula");
            String NIT = request.getParameter("NIT");
            String TipoProgramaId = request.getParameter("TipoProgramaId");
            
            try {
                ProgramaPagoDAO ProgramaDAO= new ProgramaPagoDAO();
                ProgramaPago program = ProgramaDAO.NuevoProgramaPago(TipoProgramaId,NIT);
                EmpresaDAO CompanyDAO = new EmpresaDAO();
                //Empresa Company = CompanyDAO.NuevaEmpresa(NIT);
                //request.setAttribute("Empresa", Company);
                request.setAttribute("ProgramaPago", program);
                request.getRequestDispatcher("ProgramEdition.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al editar los programas y encargados de la empresa.");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }   
        }
        if (Objeto.equals("JuntaDirectiva")) {
            String NIT = request.getParameter("NIT");
            String JuntaId = request.getParameter("JuntaId");
            String EmpleadoId= request.getParameter("EmpleadoId");
            String PersonaId = request.getParameter("PersonaId");
            String Eliminar = request.getParameter("RadioEliminar");
            Empleado Emple = null;
            try {
                Emple = new EmpleadoDAO().NuevoEmpleado(NIT, PersonaId, EmpleadoId);
                request.setAttribute("Empleado",Emple);
            } catch (SQLException ex) {
                request.setAttribute("Error", "No se ha podido acceder a la Empresa de NIT "+NIT);
                request.getRequestDispatcher("Error.jsp").forward(request, response); 
            }
            JuntaDirectiva Junta = null;
            try {
                Junta  = new JuntaDirectivaDAO().NuevaJuntaDirectiva(JuntaId);
                request.setAttribute("Junta", Junta);
            } catch (SQLException ex) {
                request.setAttribute("Error", "No se ha podido acceder a la Junta Directiva de la empresa "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad());
                request.getRequestDispatcher("Error.jsp").forward(request, response); 
            }
            request.getRequestDispatcher("JuntaEdition.jsp").forward(request, response);
            
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Administrador Admi = (Administrador)request.getSession().getAttribute("Administrador");
        String txt = request.getParameter("txtObjeto");
        if (txt.equals("Empresa")) 
        {
            String NITOriginal = request.getParameter("txtNITOriginal");
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
            String Eliminar = request.getParameter("RadioEliminar");
            String ActivarAlerta = request.getParameter("RadioAlerta");
            if(ActivarAlerta == null)ActivarAlerta = "NO";
            if (Activa == null) Activa = "NO";
            if(Eliminar == null) Eliminar = "NO";
            String Edicion="";
            String Valor="";
            Empresa Empre = null;
            try {
                Empre = new EmpresaDAO().NuevaEmpresa(NITOriginal);
            } catch (SQLException ex) {
                System.out.println("Ocurrió un error al obtener el valor de la empresa en la clase \"Controller"
                        + "Editios\" en la linea 172");
            }
            if(Eliminar.equals("YES"))
            {
                Edicion = "Eliminado";
                Valor = Nombre;
            }
            else
            {
                try {
                    if(!NIT.equals(Empre.getNIT()))
                    {
                        Valor+=Empre.getNIT()+"-";
                        Edicion += "NIT-";
                    }
                    if(!Nombre.equals(Empre.getNombre()))
                    {
                        Valor+=Empre.getNombre()+" ";
                        Edicion += "Nombre-";
                    }
                    if(!Sociedad.equals(Empre.getSociedad()))
                    {
                        Valor+=Empre.getSociedad()+"-";
                        Edicion += "Sociedad-";
                    }
                    if(!AnoCreacion.equals(Empre.getAnoCreacion()))
                    {
                        Valor+=Empre.getAnoCreacion()+"-";
                        Edicion += "Año de Creación-";
                    }
                    if(!AnoAfiliacion.equals(Empre.getAnoAfiliacion()))
                    {
                        Valor+=Empre.getAnoAfiliacion()+"-";
                        Edicion += "Año de Afiliación-";
                    }
                    if(!SectorProductivo.equals(Empre.getSectorProductivo()))
                    {
                        Valor+=Empre.getSectorProductivo()+"-";
                        Edicion += "Sector Productivo-";
                    }
                    if(!NumeroEmpleados.equals(Empre.getNumeroEmpleados()))
                    {
                        Valor+=Empre.getNumeroEmpleados()+"-";
                        Edicion += "Numero de  Empleados-";
                    }
                    if(!TamanoEmpresa.equals(Empre.getTamanoEmpresa()))
                    {
                        Valor+=Empre.getTamanoEmpresa()+"-";
                        Edicion += "Tamaño de la Empresa-";
                    }
                    if(!Descripcion.equals(Empre.getDescripcion()))
                    {
                        //PARA EVITAR QUE SE LLENE EL RANGO MÁXIMO EN LA BASE DE DATOS
                        Valor+="Descripción-";
                        Edicion += "Descripción-";
                    }
                    if(!SostenimientoPesos.equals(Empre.getSostenimientoPesos())|| !SostenimientoSMLV.equals(Empre.getSostemientoSMLV()))
                    {
                        Valor+=Empre.getSostemientoSMLV()+"_"+Empre.getSostenimientoPesos();
                        Edicion += "Sostenimiento-";
                    }
                    if(!DireccionPlanta.equals(Empre.getDireccionPlanta()) || !DireccionAdministrativa.equals(Empre.getDireccionAdministrativa()))
                    {
                        Valor+=Empre.getDireccionAdministrativa()+"_"+Empre.getDireccionAdministrativa();
                        Edicion += "Dirección-";
                    }
                    if(!MunicipioPlanta.equals(Empre.getMunicipioPlanta()) || !MunicipioAdministrativa.equals(Empre.getMunicipioAdminitrativa()))
                    {
                        Valor+=Empre.getMunicipioAdminitrativa()+"_"+Empre.getMunicipioPlanta();
                        Edicion += "Municipio-";
                    }
                    if(!Activa.equals(Empre.getActiva()))
                    {
                        if(Empre.getActiva().equals("YES")) Valor+="Activa-";
                        else Valor+="No Activa-";
                        Edicion +="Activa-";
                    }
                    if(ActivarAlerta.equals("YES"))
                    {
                        Edicion+="Se Agregó Alerta";
                    }
                } catch (Exception ex) {
                    Edicion = "Error";
                }
            }
            try {
                new EdicionesDAO().Insertar(new Ediciones("","","Empresa "+Empre.getNombre(), 
                        Edicion, Valor, Admi));
            } catch (SQLException ex) {
                System.out.println("Ocurrió un Error para insertar la edición en la clase ControllerEditions linea 244");
            }
            Empresa Company = new Empresa(NIT, Nombre, Sociedad, AnoCreacion, AnoAfiliacion, SectorProductivo, NumeroEmpleados, TamanoEmpresa, Descripcion, SostenimientoPesos, SostenimientoSMLV, DireccionPlanta, DireccionAdministrativa, MunicipioPlanta, MunicipioAdministrativa, Activa);
            EmpresaDAO CompanyDAO = new EmpresaDAO();
            try {
                if (Eliminar.equals("YES")) {
                    CompanyDAO.Eliminar(NIT);
                    request.setAttribute("Mensaje","Se ha eliminado correctamente la empresa "+Company.getNombre()+" "+Company.getSociedad());          
                }else
                {
                    CompanyDAO.Editar(Company,NITOriginal);
                    if(ActivarAlerta.equals("YES"))
                    {
                        Company.setNIT(NITOriginal);
                        String DescripcionAlerta = request.getParameter("txtDescripcionAlerta");
                        new AlertaDAO().Insertar(new Alerta("", DescripcionAlerta, Company));
                    }
                    request.setAttribute("Mensaje","Se actualizó correctamente la Empresa: "+Company.getNombre()+" "+Company.getSociedad());
                    
                }
                request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al editar la Empresa: "+Company.getNombre()+" "+Company.getSociedad());
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        }
        if (txt.equals("Directivas")) {
            String Comite = request.getParameter("txtCargo");
            String NIT = request.getParameter("txtNIT");
            String Email1 = request.getParameter("txtEmail1");
            String Email2 = request.getParameter("txtEmail2");
            String Tel1 = request.getParameter("txtTel1");
            String Tel2 = request.getParameter("txtTel2");
            String CargoEmpresa = request.getParameter("txtCargoEmpresa");
            String Cedula = request.getParameter("txtCedula");
            String Nombre = request.getParameter("txtNombre");
            String Apellidos = request.getParameter("txtApellidos");
            String FechaNacimiento = request.getParameter("txtCumple");
            String Titulo = request.getParameter("txtTitulo");
            String Eliminar = request.getParameter("RadioEliminar");
            if (Eliminar == null) Eliminar="NO";
            String EmpleadoId = request.getParameter("txtEmpleadoId");
            String PersonaId = request.getParameter("txtPersonaId");
            String ComiteId = request.getParameter("txtComiteId");
            Persona Person = new Persona(PersonaId, Cedula, Nombre, Apellidos, FechaNacimiento, Titulo, CargoEmpresa);
            try {
                PersonaDAO PersonDAO = new PersonaDAO();
                if (Eliminar.equals("YES")) {
                    PersonDAO.Eliminar(PersonaId);
                    new EdicionesDAO().Insertar(new Ediciones("", "", "Comite "+new ComiteDAO().NuevoComite(ComiteId).getNombre(),new EmpresaDAO().NuevaEmpresa(NIT).getNombre() ,
                            "Eliminar "+PersonDAO.NuevaPersona(PersonaId), (Administrador)request.getSession().getAttribute("Administrador")));
                    request.setAttribute("Mensaje", "Todos los datos de "+Person.getNombre()+" "+Person.getApellidos()+" incluida su vinculación con la empresa ha sido eliminada");
                }
                else
                {
                    Comite Comit = null;
                    if(Comite.equals("OTRO"))
                    {
                        Comite = request.getParameter("txtCargo2");
                        String ComiteNombre = request.getParameter("txtCargo2");
                        Comit = new Comite("", ComiteNombre);
                        new ComiteDAO().Insertar(Comit);
                        Comit.setId(new ComiteDAO().ObtenerId(ComiteNombre));
                        HttpSession Sesion = request.getSession();
                        String VectorJavaScript = new ComiteDAO().ScriptComite();
                        Sesion.setAttribute("Comite", VectorJavaScript);
                        new EdicionesDAO().Insertar(new Ediciones("", "", "Comite",Comite, "NUEVO", (Administrador)request.getSession().getAttribute("Administrador")));
                    }
                    else
                    {
                        Comit = new ComiteDAO().NuevoComite(Comite);
                    }
                    Empleado Emple = new EmpleadoDAO().NuevoEmpleado(NIT, PersonaId, EmpleadoId);
                    String NombreAtributo = "";
                    String ValoresAnteriores="";
                    if(!Emple.getComite().getNombre().equals(Comit.getNombre()))
                    {
                        NombreAtributo += "Comité-";
                        ValoresAnteriores +=Emple.getComite().getNombre()+"-";
                    }
                    if(!Emple.getEmail1().equals(Email1) || !Emple.getEmail2().equals(Email2))
                    {
                        NombreAtributo += "Email's-";
                        ValoresAnteriores += Emple.getEmail1()+"_"+Emple.getEmail2()+"-";
                    }
                    if(!Emple.getTelefono1().equals(Tel1) || !Emple.getTelefono2().equals(Tel2))
                    {
                        NombreAtributo +="Teléfonos-";
                        ValoresAnteriores += Emple.getTelefono1()+"_"+Emple.getTelefono2()+"-";
                    }
                    if(!Emple.getPersona().getNombre().equals(Person.getNombre()))
                    {
                        NombreAtributo +="Nombre-";
                        ValoresAnteriores += Emple.getPersona().getNombre()+"-";
                    }
                    if(!Emple.getPersona().getApellidos().equals(Person.getApellidos()))
                    {
                        NombreAtributo +="Apellidos-";
                        ValoresAnteriores += Emple.getPersona().getApellidos()+"-";
                    }
                    if(!Emple.getPersona().getCargo().equals(Person.getCargo()))
                    {
                        NombreAtributo +="Cargo-";
                        ValoresAnteriores += Emple.getPersona().getCargo()+"-";
                    }
                    if(!Emple.getPersona().getCedula().equals(Person.getCedula()))
                    {
                        NombreAtributo +="Cédula-";
                        ValoresAnteriores += Emple.getPersona().getCedula()+"-";
                    }
                    if(!Emple.getPersona().getCumpleanos().equals(Person.getCumpleanos()))
                    {
                        NombreAtributo +="Cumpleaños-";
                        ValoresAnteriores += Emple.getPersona().getCumpleanos()+"-";
                    }
                    if(!Emple.getPersona().getTitulo().equals(Person.getTitulo()))
                    {
                        NombreAtributo += "Trato";
                        ValoresAnteriores += Emple.getPersona().getTitulo();
                    }
                    new EdicionesDAO().Insertar(new Ediciones("", "", "Comité "+Comit.getNombre()+" "+Emple.getEmpresa().getNombre(),
                            NombreAtributo,ValoresAnteriores, (Administrador)request.getSession().getAttribute("Administrador")));
                    Emple.setComite(Comit);
                    Emple.setPersona(Person);
                    Emple.setEmail1(Email1);
                    Emple.setEmail2(Email2);
                    Emple.setTelefono1(Tel1);
                    Emple.setTelefono2(Tel2);
                    new EmpleadoDAO().Editar(Emple);
                    new PersonaDAO().Editar(Person);
                    request.setAttribute("Mensaje", "Los datos de "+Person.getNombre()+" "+Person.getApellidos()+" incluida su vinculación con la empresa han  sido actualizados");
                }
                request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("Error","Ocurrió un error al editar o eliminar los datos de "+Person.getNombre()+" "+Person.getApellidos());
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }   
        }
        if (txt.equals("Programas y Encargados"))
        {
            String Programa = request.getParameter("txtPrograma");
            String NIT = request.getParameter("txtNIT");
            String Email1 = request.getParameter("txtEmail1");
            String Email2 = request.getParameter("txtEmail2");
            String Telefono1 = request.getParameter("txtTel1");
            String Telefono2 = request.getParameter("txtTel2");
            String Pago = request.getParameter("txtPago");
            String Cargo = request.getParameter("txtCargoEmpresa");
            String Cedula = request.getParameter("txtCedula");
            String Nombre = request.getParameter("txtNombre");
            String Apellidos = request.getParameter("txtApellidos");
            String Cumple = request.getParameter("txtCumple");
            String Titulo = request.getParameter("txtTitulo");
            String ProgramaNombre = request.getParameter("txtProgramaNombre");
            String Eliminar = request.getParameter("RadioEliminar");
            String PersonaId = request.getParameter("txtPersonaId");
            String ProgramaOriginal = request.getParameter("txtProgramaOriginal");
            String EmpleadoId = request.getParameter("txtEmpleadoId");
            if(Eliminar == null) Eliminar = "NO";
            String CargoRegistrar = null;
            int TipoProgramaId = 100;
            Persona Person = new Persona(PersonaId, Cedula, Nombre, Apellidos, Cumple, Titulo, Cargo);

            if (Eliminar.equals("YES"))
            {
                try {
                    new PersonaDAO().Eliminar(PersonaId);
                    request.setAttribute("Mensaje", "Todos los datos de "+Person.getNombre()+" "+Person.getApellidos()+" incluida su vinculación con la empresa y el programa han sido eliminados");
                    request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error", "No se han podido eliminar los datos de "+Person.getNombre()+" "+Person.getApellidos()+" debido a un error interno");
                }               
            }
            else
            {
                Empleado Emple = null;
                ProgramaPago ProgramPago = null;
                TipoPrograma Tipo=null;
                if(Programa.equals("OTRO"))
                {
                    CargoRegistrar = request.getParameter("txtPrograma2");
                    String Descripcion = request.getParameter("txtDescripcion");
                    Tipo = new TipoPrograma("", CargoRegistrar, Descripcion);
                    TipoProgramaDAO TipoDAO = new TipoProgramaDAO();
                    try {
                        TipoDAO.Insertar(Tipo);
                        HttpSession Sesion = request.getSession();
                        String VectorJavaScript = TipoDAO.ObtenerTipoPrograma();
                        Sesion.setAttribute("ScriptProgramas", VectorJavaScript);
                        TipoProgramaId = new TipoProgramaDAO().ObtenerIdMayor();
                        Cargo = Integer.toString(TipoProgramaId);
                        Tipo.setId(Cargo);
                    } catch (SQLException ex) {
                        request.setAttribute("Error", "No se ha podido editar el  programa "+Cargo+" por "+CargoRegistrar+"  debido a un error interno ");
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                }
                else 
                {
                    //El Programa Nombre queda con el mismo con el que entró, es decir si en la opción
                    //cambia de programa, lo único que ccambiará es de id.
                    Tipo = new TipoPrograma(Programa, ProgramaNombre, null);
                }

                try {
                    Emple = new EmpleadoDAO().NuevoEmpleado(NIT, PersonaId, EmpleadoId);
                    String NombreAtributo = "";
                    String ValoresAnteriores="";
                    ProgramaPago Original = new ProgramaPagoDAO().NuevoProgramaPago(Programa, NIT);
                    ProgramPago = new ProgramaPago(Pago, Tipo, Emple);
                    if(!ProgramPago.getTipo().getId().equals(ProgramaOriginal))
                    {
                        NombreAtributo += "Programa-";
                        ValoresAnteriores += new TipoProgramaDAO().NuevoTipoPrograma(ProgramaOriginal).getNombre()+"-";
                    }
                    if(!ProgramPago.getPago().equals(Original.getPago()))
                    {
                        NombreAtributo += "Pago-";
                        ValoresAnteriores += Original.getPago()+"-";
                    }
                    if(!Emple.getEmail1().equals(Email1) || !Emple.getEmail2().equals(Email2))
                    {
                        NombreAtributo += "Email's-";
                        ValoresAnteriores += Emple.getEmail1()+"_"+Emple.getEmail2()+"-";
                    }
                    if(!Emple.getTelefono1().equals(Telefono1) || !Emple.getTelefono2().equals(Telefono2))
                    {
                        NombreAtributo +="Teléfonos-";
                        ValoresAnteriores += Emple.getTelefono1()+"_"+Emple.getTelefono2()+"-";
                    }
                    if(!Emple.getPersona().getNombre().equals(Person.getNombre()))
                    {
                        NombreAtributo +="Nombre-";
                        ValoresAnteriores += Emple.getPersona().getNombre()+"-";
                    }
                    if(!Emple.getPersona().getApellidos().equals(Person.getApellidos()))
                    {
                        NombreAtributo +="Apellidos-";
                        ValoresAnteriores += Emple.getPersona().getApellidos()+"-";
                    }
                    if(!Emple.getPersona().getCargo().equals(Person.getCargo()))
                    {
                        NombreAtributo +="Cargo-";
                        ValoresAnteriores += Emple.getPersona().getCargo()+"-";
                    }
                    if(!Emple.getPersona().getCedula().equals(Person.getCedula()))
                    {
                        NombreAtributo +="Cédula-";
                        ValoresAnteriores += Emple.getPersona().getCedula()+"-";
                    }
                    if(!Emple.getPersona().getCumpleanos().equals(Person.getCumpleanos()))
                    {
                        NombreAtributo +="Cumpleaños-";
                        ValoresAnteriores += Emple.getPersona().getCumpleanos()+"-";
                    }
                    if(!Emple.getPersona().getTitulo().equals(Person.getTitulo()))
                    {
                        NombreAtributo += "Trato";
                        ValoresAnteriores += Emple.getPersona().getTitulo();
                    }
                    Emple.setPersona(Person);
                    Emple.setEmail1(Email1);
                    Emple.setEmail2(Email2);
                    Emple.setTelefono1(Telefono1);
                    Emple.setTelefono2(Telefono2);
                    new ProgramaPagoDAO().Editar(ProgramPago,ProgramaOriginal );
                    new PersonaDAO().Editar(Person);
                    new EmpleadoDAO().Editar(Emple);
                    
                    new EdicionesDAO().Insertar(new Ediciones("", "", "Programa "+ProgramPago.getTipo().getNombre()+" "+Emple.getEmpresa().getNombre(),
                            NombreAtributo,ValoresAnteriores, (Administrador)request.getSession().getAttribute("Administrador")));
                    
                    request.setAttribute("Mensaje", "Todos los datos de "+Person.getNombre()+" "+Person.getApellidos()+" incluida su vinculación con la empresa y el programa han sido Editados");
                    request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);

                } catch (SQLException ex) {
                    request.setAttribute("Error", "No se ha podido editar el  programa "+Cargo+" por "+CargoRegistrar+"  debido a un error interno ");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            }
        }
        if (txt.equals("Junta Directiva")) {
            String NIT = request.getParameter("txtNIT");
            String Calidad = request.getParameter("txtCalidad");
            String EmpleadoId = request.getParameter("txtEmpleadoId");
            String PersonaId = request.getParameter("txtPersonaId");
            if (Calidad.equals("OTRO")) {
                Calidad = request.getParameter("txtCalidad2");
            }
            String JuntaId = request.getParameter("txtJuntaId");
            String Eliminar = request.getParameter("RadioEliminar");
            JuntaDirectiva Junta= null;
            Empleado Emple = null;
            try {
                Emple = new EmpleadoDAO().NuevoEmpleado(NIT, PersonaId, EmpleadoId);
                Junta= new JuntaDirectivaDAO().NuevaJuntaDirectiva(JuntaId);
            } catch (SQLException ex) {
                request.setAttribute("Error", "No se ha podido acceder a la Junta Directiva ");
                request.getRequestDispatcher("Error.jsp").forward(request, response); 
            }
            if (Eliminar == null)Eliminar="NO";
            if(Calidad.equals("OTRO"))
            {
                Calidad = request.getParameter("txtCalidad2");
                String Anterior = Junta.getCalidad();
                Junta.setCalidad(Calidad);
                try {
                    new JuntaDirectivaDAO().Editar(Junta);
                    new EdicionesDAO().Insertar(new Ediciones("", "", "Junta Directiva "+new EmpresaDAO().NuevaEmpresa(NIT).getNombre(),
                            Anterior,"Miembro", Admi));
                    
                    request.setAttribute("Mensaje", "La vinculación de la empresa "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad()+"  a la Junta Directiva ha sido editada correctamente ");
                    request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error", "No se ha podido Editar la Junta Directiva de "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad());
                    request.getRequestDispatcher("Error.jsp").forward(request, response);  
                }
            }
            
            try {
                String Anterior = Junta.getCalidad();
                Junta.setCalidad(Calidad);
                new JuntaDirectivaDAO().Editar(Junta);
                new EdicionesDAO().Insertar(new Ediciones("", "", "Junta Directiva "+new EmpresaDAO().NuevaEmpresa(NIT).getNombre(),
                            "Miembro",Anterior, Admi));
                    
                request.setAttribute("Mensaje", "La vinculación de la empresa "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad()+"  a la Junta Directiva ha sido editada correctamente ");
                request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("Error", "No se ha podido Editar la Junta Directiva de "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad());
                request.getRequestDispatcher("Error.jsp").forward(request, response);  
            }

            if (Eliminar.equals("YES")) {
                try {
                    new JuntaDirectivaDAO().Eliminar(JuntaId);
                    request.setAttribute("Mensaje", "La vinculación de la empresa "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad()+"  a la Junta Directiva ha sido eliminada con éxito ");
                    request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("Error", "No se ha podido eliminar la Junta Directiva de "+Emple.getEmpresa().getNombre()+" "+Emple.getEmpresa().getSociedad());
                    request.getRequestDispatcher("Error.jsp").forward(request, response);    
                }
                
            }
        }
        if (txt.equals("Programa y Precio")) {
            try {
                String IdViejo = request.getParameter("txtTipoIdViejo");
                String Eliminar = request.getParameter("RadioEliminar");
                String NIT = request.getParameter("txtNIT");
                String Pago = request.getParameter("txtPago");
                String Programa = request.getParameter("txtPrograma");
                ProgramaPago Program = new ProgramaPagoDAO().NuevoProgramaPago(IdViejo, NIT);
                Program.getTipo().setId(Programa);
                Program.setPago(Pago);
                new ProgramaPagoDAO().Editar(Program, IdViejo);
                request.setAttribute("Mensaje", "El programa de la empresa "+Program.getEmple().getEmpresa().getNombre()+" "+Program.getEmple().getEmpresa().getSociedad()+" ha sido editada correctamente");
                request.getRequestDispatcher("CreateReadUpdateDelete.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                request.setAttribute("Error", "Ocurrio un error al editar la empresa y su programa");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
 
            }
            
        }
        
    }


}
