
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class ProgramaPagoDAO {
    Conexion conex;
    public ProgramaPagoDAO(){
        conex = new Conexion();
    }
    
    public void Insertar(ProgramaPago Program) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`programapago` (`TipPrograma_Id`, `ProgramaPago_Pago`, "
                + "`NIT`, `Empleado_Id`, `Persona_Id`) VALUES"
                + " (?, ?, ?," 
                + " ?, ?);";         
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, Program.getTipo().getId());
        pr.setString(2, Program.getPago());
        pr.setString(3, Program.getEmple().getEmpresa().getNIT());
        pr.setString(4, Program.getEmple().getID());
        pr.setString(5, Program.getEmple().getPersona().getId());
        pr.executeUpdate();
        pr.close();
    }

    public String ObtenerProgramasYEncargados(String Programa) throws SQLException {
        int maxfor = 17;
        String Query = "select p.Persona_Titulo, concat(Persona_Nombre,' ',p.Persona_Apellidos),"
                + " p.Persona_Cargo,e.Emp_Nombre,pg.ProgramaPago_Pago,e.Emp_DireccionAdministrativa, em.Empleado_Telefono1,"
                + "em.Empleado_Telefono2,em.Empleado_Email1,em.Empleado_Email2,p.Persona_Cumple,"
                + "p.Persona_Cedula,pg.TipPrograma_Id,pg.NIT,pg.Empleado_Id,pg.Persona_Id "
                + "from empresa e natural join programapago pg natural join tipoprograma tp "
                + " natural join persona p natural join empleado em "
                + "where tp.TipoPrograma_Nombre = '"+Programa+"' and e.Emp_Activa = 'YES' order by e.Emp_Nombre;";
        if (Programa.equals("Otros") ||Programa.equals("Inactivos") ) {
            String QueryOtros = "where tp.TipoPrograma_Nombre not in (select TipoPrograma_Nombre from tipoprograma where TipPrograma_Id <= 4 ) and e.Emp_Activa = 'YES'";
            if(Programa.equals("Inactivos")) QueryOtros="where e.Emp_Activa = 'NO' ";
            Query= "select p.Persona_Titulo, concat(Persona_Nombre,' ',p.Persona_Apellidos),"
                    + " p.Persona_Cargo,e.Emp_Nombre,pg.ProgramaPago_Pago,tp.TipoPrograma_Nombre,Emp_DireccionAdministrativa,"
                    + " em.Empleado_Telefono1,em.Empleado_Telefono2,em.Empleado_Email1,em.Empleado_Email2,"
                    + "p.Persona_Cumple,p.Persona_Cedula,pg.TipPrograma_Id,pg.NIT,pg.Empleado_Id,pg.Persona_Id"
                    + " from empresa e natural join programapago pg natural join tipoprograma tp  "
                    + "natural join persona p natural join empleado em "+QueryOtros+"  order by e.Emp_Nombre;";
            maxfor=18;
        }
        
        
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        rs.next();
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < maxfor; i++)
            {
                String Parcial = rs.getString(i);
                if (i==5) {
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

    public ProgramaPago NuevoProgramaPago(String TipoProgramaId, String NIT) throws SQLException {
        String Query = "select * from programapago where TipPrograma_Id = ? and NIT=?;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, TipoProgramaId);
        pr.setString(2, NIT);
        ResultSet rs = pr.executeQuery();
        rs.next();
        return new ProgramaPago(rs.getString("ProgramaPago_Pago"),
                new TipoProgramaDAO().NuevoTipoPrograma(TipoProgramaId),
                new EmpleadoDAO().NuevoEmpleado(NIT,rs.getString("Persona_Id"),rs.getString("Empleado_Id")));
    }
    
    public void Editar(ProgramaPago Program, String TipoIdViejo) throws SQLException
    {
        String Query ="UPDATE `basededatosceo`.`programapago` SET `TipPrograma_Id`=?,"
            + " `ProgramaPago_Pago`=?, `Persona_Id`=?"
            + "WHERE `TipPrograma_Id`=? and `NIT`=?;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, Program.getTipo().getId());
        pr.setString(2, Program.getPago());
        pr.setString(3, Program.getEmple().getPersona().getId());
        pr.setString(4, TipoIdViejo);
        pr.setString(5, Program.getEmple().getEmpresa().getNIT());
        pr.executeUpdate();
    }

    public String ObtenerProgramasYEncargados(ProgramaPago Program) throws SQLException {
        String Cumpleanos1;
        String QueryCumpleanos;
        String QueryPago;
        String Pago = Program.getPago();
        String Pago1 = Program.getPago().split("-")[0];
        String Pago2;
        String Mes = Program.getEmple().getPersona().getCumpleanos().split("&")[2];
        try {
            if(Pago1.equals(""))Pago1 ="0";
            if(!Pago.split("-")[1].equals(""))Pago2 = Pago.split("-")[1];
            else Pago2 = "0";
        } catch (Exception e) {
            Pago2 = "0";
        }
        if(Pago2.equals("0"))QueryPago = "pg.ProgramaPago_Pago >= "+Pago1+"";
        else QueryPago = "pg.ProgramaPago_Pago between "+Pago1+" and "+Pago2+"";
        
        try {
            Cumpleanos1 = Program.getEmple().getPersona().getCumpleanos().split("&")[0];
        } catch (Exception e) {
            Cumpleanos1 = "";
        }
        String Cumpleanos2;
        try {
            Cumpleanos2 = Program.getEmple().getPersona().getCumpleanos().split("&")[1];
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
        
        String Query= "select p.Persona_Titulo, concat(Persona_Nombre,' ',p.Persona_Apellidos),"
                + " p.Persona_Cargo,e.Emp_Nombre,pg.ProgramaPago_Pago,tp.TipoPrograma_Nombre,"
                + "Emp_DireccionAdministrativa, em.Empleado_Telefono1,em.Empleado_Telefono2,em.Empleado_Email1,"
                + "em.Empleado_Email2, p.Persona_Cumple, p.Persona_Cedula,pg.TipPrograma_Id,pg.NIT,"
                + "pg.Empleado_Id,pg.Persona_Id from empresa e natural join programapago pg natural join "
                + "tipoprograma tp natural join persona p natural join empleado em"
                + " where tp.TipoPrograma_Nombre like ? and e.NIT like ?"
                + " and e.Emp_Nombre like ? and "
                + "em.Empleado_Email1 like ? and em.Empleado_Email2 like ? "
                + "and em.Empleado_Telefono1 like ? and em.Empleado_Telefono2 like ?"
                + " and "+QueryPago+" and p.Persona_Cedula like ? and p.Persona_Nombre like ?"
                + " and p.Persona_Apellidos like ? and p.Persona_Titulo like ? "
                + " and p.Persona_Cargo like ? and "+QueryCumpleanos+""
                + " order by e.Emp_Nombre;";
        
        PreparedStatement pr = conex.getConnection().prepareStatement(Query);
        pr.setString(1, Program.getTipo().getNombre() );
        pr.setString(2, Program.getEmple().getEmpresa().getNIT() );
        pr.setString(3, Program.getEmple().getEmpresa().getNombre());
        pr.setString(4, Program.getEmple().getEmail1());
        pr.setString(5, Program.getEmple().getEmail2());
        pr.setString(6, Program.getEmple().getTelefono1());
        pr.setString(7, Program.getEmple().getTelefono2());
        pr.setString(8, Program.getEmple().getPersona().getCedula());
        pr.setString(9, Program.getEmple().getPersona().getNombre());
        pr.setString(10, Program.getEmple().getPersona().getApellidos());
        pr.setString(11, Program.getEmple().getPersona().getTitulo());
        pr.setString(12, Program.getEmple().getPersona().getCargo());
     
        ResultSet rs = pr.executeQuery();
        String retorno = "[";
        rs.next();
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 18; i++)
            {
                String Parcial = rs.getString(i);
                if (i==5) {
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
    
}
