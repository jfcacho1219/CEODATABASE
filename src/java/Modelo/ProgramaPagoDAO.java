/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 *
 * @author SONY
 */
public class ProgramaPagoDAO {
    Conexion conex;
    public ProgramaPagoDAO(){
        conex = new Conexion();
    }
    
    public void Insertar(ProgramaPago Program) throws SQLException
    {
        /*String Query = "INSERT INTO `basededatosceo`.`programapago` (`TipPrograma_Id`, `ProgramaPago_Pago`,"
                + " `NIT`, `Empleado_Id`) VALUES ('"+Program.getTipo().getId()+"', '"+Program.getPago()+"',"
                + " '"+Program.getEmple().getEmpresa().getNIT()+"', '"+Program.getEmple().getPersona().getId()+"');";
        */
        String Query = "INSERT INTO `basededatosceo`.`programapago` (`TipPrograma_Id`, `ProgramaPago_Pago`, "
                + "`NIT`, `Empleado_Id`, `Persona_Id`) VALUES"
                + " ('"+Program.getTipo().getId()+"', '"+Program.getPago()+"', '"+Program.getEmple().getEmpresa().getNIT()+"',"
                + " '"+Program.getEmple().getID()+"', '"+Program.getEmple().getPersona().getId()+"');";
        System.out.println(Query);
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
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
        String Query = "select * from programapago where TipPrograma_Id = '"+TipoProgramaId+"' and NIT='"+NIT+"';";
        Statement st  = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return new ProgramaPago(rs.getString("ProgramaPago_Pago"),
                new TipoProgramaDAO().NuevoTipoPrograma(TipoProgramaId),
                new EmpleadoDAO().NuevoEmpleado(NIT,rs.getString("Persona_Id"),rs.getString("Empleado_Id")));
    }
    
    public void Editar(ProgramaPago Program, String TipoIdViejo) throws SQLException
    {
        String Query ="UPDATE `basededatosceo`.`programapago` SET `TipPrograma_Id`='"+Program.getTipo().getId()+"',"
            + " `ProgramaPago_Pago`='"+Program.getPago()+"', `Persona_Id`='"+Program.getEmple().getPersona().getId()+"'"
            + "WHERE `TipPrograma_Id`='"+TipoIdViejo+"' and `NIT`='"+Program.getEmple().getEmpresa().getNIT()+"';";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
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
                + " where tp.TipoPrograma_Nombre like '%"+Program.getTipo().getNombre()+"%' and e.NIT like '%"+Program.getEmple().getEmpresa().getNIT()+"%'"
                + " and e.Emp_Nombre like '%"+Program.getEmple().getEmpresa().getNombre()+"%' and "
                + "em.Empleado_Email1 like '%"+Program.getEmple().getEmail1()+"%' and em.Empleado_Email2 like '%"+Program.getEmple().getEmail2()+"%' "
                + "and em.Empleado_Telefono1 like '%"+Program.getEmple().getTelefono1()+"%' and em.Empleado_Telefono2 like '%"+Program.getEmple().getTelefono2()+"%'"
                + " and "+QueryPago+" and p.Persona_Cedula like '%"+Program.getEmple().getPersona().getCedula()+"%' and p.Persona_Nombre like '%"+Program.getEmple().getPersona().getNombre()+"%'"
                + " and p.Persona_Apellidos like '%"+Program.getEmple().getPersona().getApellidos()+"%' and p.Persona_Titulo like '%"+Program.getEmple().getPersona().getTitulo()+"%' "
                + " and p.Persona_Cargo like '%"+Program.getEmple().getPersona().getCargo()+"%' and "+QueryCumpleanos+""
                + " order by e.Emp_Nombre;";
        
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
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
