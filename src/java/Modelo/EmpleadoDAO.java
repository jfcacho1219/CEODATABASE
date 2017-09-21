/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SONY
 */
public class EmpleadoDAO {
    Conexion conex;
    public EmpleadoDAO()
    {
        conex = new Conexion();
    }
    
    public int ObtenerIdMayor()
    {
        String Query = "select Empleado_Id from empleado order by Empleado_Id desc";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conex.getConnection().createStatement();
            rs = st.executeQuery(Query);
            rs.next();
            String retorno =(rs.getString(1));
            return Integer.parseInt(retorno);
        } catch (SQLException ex) {
            return 1;
        }
    }
    public void Insertar(Empleado Empleo) throws SQLException
    {
        int Id = (ObtenerIdMayor()+1);
        String IdS = Integer.toString(Id);
        String Query = "INSERT INTO `basededatosceo`.`empleado` (`Empleado_Id`, `NIT`, `Empleado_Email1`,"
                + " `Empleado_Email2`, `Empleado_Telefono1`, `Empleado_Telefono2`, `Persona_Id`,"
                + " `Comite_Id`) VALUES ('"+IdS+"', '"+Empleo.getEmpresa().getNIT()+"', '"+Empleo.getEmail1()+"', '"+Empleo.getEmail2()+"',"
                + " '"+Empleo.getTelefono1()+"', '"+Empleo.getTelefono2()+"', '"+Empleo.getPersona().getId()+"', '"+Empleo.getComite().getId()+"');";
        System.out.println(Query);
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    
    public void Insertar(Empleado Empleo,boolean Validar) throws SQLException
    {
        int Id = (ObtenerIdMayor()+1);
        String IdS = Integer.toString(Id);
        String Query = "INSERT INTO `basededatosceo`.`empleado` (`Empleado_Id`, `NIT`, `Empleado_Email1`,"
                + " `Empleado_Email2`, `Empleado_Telefono1`, `Empleado_Telefono2`, `Persona_Id`"
                + " ) VALUES ('"+IdS+"', '"+Empleo.getEmpresa().getNIT()+"', '"+Empleo.getEmail1()+"', '"+Empleo.getEmail2()+"',"
                + " '"+Empleo.getTelefono1()+"', '"+Empleo.getTelefono2()+"', '"+Empleo.getPersona().getId()+"');";
        System.out.println(Query);
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }

  public String ObtenerCargosYPersonas(String Comite) throws SQLException {
        int MaxFor = 17;
        String Query = "select p.Persona_Titulo,p.Persona_Nombre,p.Persona_Apellidos,p.Persona_Cargo,"
                + "e.Emp_Nombre,e.Emp_DireccionAdministrativa,em.Empleado_Telefono1,em.Empleado_Telefono2,"
                + "em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,p.Persona_Cedula,e.NIT,em.Empleado_Id,em.Persona_Id,em.Comite_Id "
                + "from empresa e natural join (select * from empleado natural join comite where "
                + "Comite_Nombre='"+Comite+"' ) as em natural join persona p where e.Emp_Activa = 'YES' order by e.Emp_Nombre";
        if(Comite.equals("Inactivos"))
        {
            Query = "select p.Persona_Titulo,p.Persona_Nombre,p.Persona_Apellidos,p.Persona_Cargo,"
                + "e.Emp_Nombre,e.Emp_DireccionAdministrativa,em.Empleado_Telefono1,em.Empleado_Telefono2,"
                + "em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,p.Persona_Cedula, em.Comite_Nombre,e.NIT,em.Empleado_Id,em.Persona_Id,em.Comite_Id "
                + "from empresa e natural join (select * from empleado natural join comite"
                + ") as em natural join persona p where e.Emp_Activa = 'NO' order by e.Emp_Nombre";
            MaxFor = 18;
        }
        if (Comite.equals("Otros")) {
            Query="select p.Persona_Titulo,p.Persona_Nombre,p.Persona_Apellidos,p.Persona_Cargo,e.Emp_Nombre,"
                    + "e.Emp_DireccionAdministrativa, em.Empleado_Telefono1,em.Empleado_Telefono2,"
                    + "em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,p.Persona_Cedula,em.Comite_Nombre,e.NIT,"
                    + "em.Empleado_Id,em.Persona_Id,em.Comite_Id from empresa e natural join "
                    + "(select * from empleado natural join comite where  Comite_Nombre not in"
                    + " (select Comite_Nombre from comite where Comite_Id<11)) "
                    + "as em natural join persona p where e.Emp_Activa = 'YES' order by e.Emp_Nombre";
            MaxFor = 18;
            //Query="select p.Persona_Titulo,p.Persona_Nombre,p.Persona_Apellidos,p.Persona_Cargo,e.Emp_Nombre,e.Emp_DireccionAdministrativa,em.Empleado_Telefono1,em.Empleado_Telefono2,em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,p.Persona_Cedula,e.NIT,em.Empleado_Id,em.Persona_Id,em.Comite_Id from empresa e natural join (select * from empleado natural join comite where  Comite_Nombre!='Gerentes' and Comite_Nombre!='Gestión Humana' and Comite_Nombre!='Comercio Exterior' and Comite_Nombre!='Técnico' and Comite_Nombre!='Compras' and Comite_Nombre!='Seguridad' and Comite_Nombre!='Ambiental' and Comite_Nombre!='Tics' and Comite_Nombre!='MYPES') as em natural join persona p order by e.Emp_Nombre";
        }
        Statement st  = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        rs.next();

        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < MaxFor; i++)
            {
                if (i!=3) {
                    String Parcial = rs.getString(i);
                    if (i+1 == 3) {
                        Parcial = Parcial+" "+rs.getString(i+1);
                    }
                    retorno = retorno+"\""+Parcial+"\",";
                }
            }
            retorno = retorno + "],";
            rs.next();
        }
        retorno = retorno+"]";
        return retorno;
    }

    public Empleado NuevoEmpleado(String NIT, String PersonaId, String EmpleadoId) throws SQLException {
        String Query = "select * from empleado where `NIT`='"+NIT+"' "
                + "and`Empleado_Id`='"+EmpleadoId+"' and`Persona_Id`='"+PersonaId+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        Comite Comite;
        String Hola = rs.getString("Comite_Id");
        if (rs.getString("Comite_Id") == null) {
            Comite = null;
        }
        else Comite =   new ComiteDAO().NuevoComite(rs.getString("Comite_Id"));
        return new Empleado(rs.getString("Empleado_Id"), new EmpresaDAO().NuevaEmpresa(NIT),
                rs.getString("Empleado_Email1"), rs.getString("Empleado_Email2"),
                rs.getString("Empleado_Telefono1"),rs.getString("Empleado_Telefono2"),
                new PersonaDAO().NuevaPersona(PersonaId),Comite);
    }

    public void Editar(Empleado empleado) throws SQLException {
        String Query = "";
        if (empleado.getComite()==null)
        {
            Query = "UPDATE `basededatosceo`.`empleado` SET `Empleado_Email1`='"+empleado.getEmail1()+"', "
                    + "`Empleado_Email2`='"+empleado.getEmail2()+"', `Empleado_Telefono1`='"+empleado.getTelefono1()+"',"
                    + " `Empleado_Telefono2`='"+empleado.getTelefono2()+"' WHERE `NIT`='"+empleado.getEmpresa().getNIT()+"'"
                    + " and`Empleado_Id`='"+empleado.getID()+"' and`Persona_Id`='"+empleado.getPersona().getId()+"';";
        }
        else
        {
            Query="UPDATE `basededatosceo`.`empleado` SET `Empleado_Email1`='"+empleado.getEmail1()+"',"
                + " `Empleado_Email2`='"+empleado.getEmail2()+"', `Empleado_Telefono1`='"+empleado.getTelefono1()+"',"
                + " `Empleado_Telefono2`='"+empleado.getTelefono2()+"', `Comite_Id`='"+empleado.getComite().getId()+"' "
                + "WHERE `NIT`='"+empleado.getEmpresa().getNIT()+"' and`Empleado_Id`='"+empleado.getID()+"'"
                    + " and`Persona_Id`='"+empleado.getPersona().getId()+"';";
        }
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }

    public String ObtenerCargosYPersonas(Empleado Emple) throws SQLException {
        String QueryCumpleanos = null;
        String Cumpleanos1;
        String Mes = Emple.getPersona().getCumpleanos().split("&")[2];
        try {
            Cumpleanos1 = Emple.getPersona().getCumpleanos().split("&")[0];
        } catch (Exception e) {
            Cumpleanos1 = "";
        }
        String Cumpleanos2;
        try {
            Cumpleanos2 = Emple.getPersona().getCumpleanos().split("&")[1];
        } catch (Exception e) {
            Cumpleanos2 = "";
        }
        if(Mes.equals("0"))
        {
            if(Cumpleanos1.equals("")) QueryCumpleanos = "p.Persona_Cumple >= '"+Cumpleanos2+"'";
            else if (Cumpleanos2.equals(""))QueryCumpleanos = "p.Persona_Cumple between '"+Cumpleanos2+"' and '"+Cumpleanos1+"'";
            else QueryCumpleanos = "p.Persona_Cumple between '"+Cumpleanos1+"' and '"+Cumpleanos2+"'";
        }
        else
        {
            QueryCumpleanos = "month(p.Persona_Cumple) = '"+Mes+"'";
        }
        String Query="select p.Persona_Titulo,concat(Persona_Nombre,' ',p.Persona_Apellidos),p.Persona_Cargo,"
                + "e.Emp_Nombre,e.Emp_DireccionAdministrativa,em.Empleado_Telefono1,em.Empleado_Telefono2,"
                + "em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,p.Persona_Cedula,em.Comite_Nombre,"
                + "e.NIT,em.Empleado_Id,em.Persona_Id,em.Comite_Id from empresa e natural join "
                + "(select * from empleado emple natural join comite com  where"
                + " emple.NIT like '%"+Emple.getEmpresa().getNIT()+"%' and emple.Empleado_Email1 like '%"+Emple.getEmail1()+"%'"
                + " and emple.Empleado_Email2 like '%"+Emple.getEmail2()+"%' and emple.Empleado_Telefono1 like '%"+Emple.getTelefono1()+"%'"
                + " and emple.Empleado_Telefono2 like '%"+Emple.getTelefono2()+"%' and com.Comite_Nombre like '%"+Emple.getComite().getNombre()+"%') as em"
                + " natural join persona p where e.Emp_Nombre like '%"+Emple.getEmpresa().getNombre()+"%' "
                + "and p.Persona_Cedula like '%"+Emple.getPersona().getCedula()+"%' and p.Persona_Nombre like '%"+Emple.getPersona().getNombre()+"%'"
                + " and p.Persona_Apellidos like '%"+Emple.getPersona().getApellidos()+"%' and p.Persona_Titulo like '%"+Emple.getPersona().getTitulo()+"%'"
                + " and p.Persona_Cargo like '%"+Emple.getPersona().getCargo()+"%' and "+QueryCumpleanos+" order by e.Emp_Nombre;";
        Statement st  = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        rs.next();
        
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 17; i++)
            {
                String Parcial = rs.getString(i);
                retorno = retorno+"\""+Parcial+"\",";
            }
            retorno = retorno + "],";
            rs.next();
        }
        retorno = retorno+"]";
        return retorno;
    

    }


}
