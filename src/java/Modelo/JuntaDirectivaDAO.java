package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class JuntaDirectivaDAO {
    Conexion conex;
    public JuntaDirectivaDAO()
    {
        conex = new Conexion();
    }
    
    public void InsertarJunta(JuntaDirectiva Junta) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`juntadirectiva` (`Junta_Tipo`, `NIT`, `Empleado_Id`"
                + ") VALUES (?, ?, ?);";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, Junta.getCalidad());
        pr.setString(2, Junta.getNIT());
        pr.setString(3, Junta.getEmpleadoId());
        System.out.println(Query);
        pr.executeUpdate();
    }

    public String ObtenerJuntaYEmpresa() throws SQLException {
        String Query = "select p.Persona_Titulo, concat(p.Persona_Nombre,' ',p.Persona_Apellidos),"
                + "p.Persona_Cargo,e.Emp_Nombre,e.Emp_DireccionAdministrativa,em.Empleado_Telefono1,"
                + "em.Empleado_Telefono2,em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,p.Persona_Cedula,"
                + " junta.Junta_Tipo,junta.Junta_Id,e.NIT,junta.Empleado_Id,p.Persona_Id from "
                + "(select jd.Junta_Id,jd.Junta_Tipo,jd.NIT,jd.Empleado_Id from juntadirectiva  jd) as junta "
                + "inner join empleado em on em.Empleado_Id=junta.Empleado_Id natural join persona p"
                + " inner join empresa e on junta.NIT=e.NIT";
        Statement st = conex.getConnection().createStatement();
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

    public String ObtenerJuntaEmpresa(String NIT) throws SQLException {
        String Query = "select a.NIT,a.ProgramaPago_Pago, tp2.TipoPrograma_Nombre,tp2.TipPrograma_Id "
                + "from (select pg.NIT,pg.ProgramaPago_Pago,pg.TipPrograma_Id as TIPO "
                + "from programapago pg left join tipoprograma tp on"
                + " pg.TipPrograma_Id = tp.TipPrograma_Id where pg.NIT = ?) as a "
                + "right join tipoprograma tp2 on a.TIPO = tp2.TipPrograma_Id;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, NIT);
        ResultSet rs = pr.executeQuery();
        String retorno = "[";
        rs.next();
        
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 2; i < 5; i++)
            {
                
                String Parcial = rs.getString(i);
                if (i==2 && Parcial!=null) {
                    DecimalFormat formato = new DecimalFormat("#,###");
                    int numero = Integer.parseInt(Parcial);
                    String num = formato.format(numero);
                    retorno = retorno+"\"$"+num+"\",";
                }
                else retorno = retorno+"\""+Parcial+"\",";
            }
            retorno = retorno + "],";
            rs.next();
        }
        retorno = retorno+"]";
        return retorno;
    }
    
    public void Editar(JuntaDirectiva Junta) throws SQLException
    {
        String Query  = "UPDATE `basededatosceo`.`juntadirectiva` SET `Junta_Tipo`=?"
                + " WHERE `Junta_Id`=?;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, Junta.getCalidad());
        pr.setString(2, Junta.getID());
        pr.executeUpdate();
    }
    
    public void Eliminar(String Id) throws SQLException
    {
        String Query = "DELETE FROM `basededatosceo`.`juntadirectiva` WHERE `Junta_Id`=?;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, Id);
        pr.executeUpdate();
    }
    
    public JuntaDirectiva NuevaJuntaDirectiva(String IdJunta) throws SQLException
    {
        String Query = "select * from juntadirectiva where Junta_Id=?;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, IdJunta);
        ResultSet rs = pr.executeQuery();
        rs.next();
        return new JuntaDirectiva(rs.getString("Junta_Id"), rs.getString("Junta_Tipo"), rs.getString("NIT"),
            rs.getString("Empleado_Id"),rs.getString("Persona_Id")); 
    }
}
