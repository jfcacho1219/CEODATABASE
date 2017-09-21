/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SONY
 */
public class EdicionesDAO {
    Conexion conex;
    public EdicionesDAO()
    {
        conex = new Conexion();
    }
    
    public void Insertar(Ediciones Edicion) throws SQLException
    {
        String Query;
        if(Edicion.getAdmi() == null)
        {
            Query = "INSERT INTO `basededatosceo`.`ediciones` (`Edicion_Fecha`, `Edicion_Tabla`,"
                    + " `Edicion_Atributo`, `Edicion_Valor`) VALUES"
                    + " (current_date(), '"+Edicion.getTabla()+"', "
                    + "'"+Edicion.getAtributo()+"', '"+Edicion.getValorAnterior()+"');";
        }
        else
        {
            Query = "INSERT INTO `basededatosceo`.`ediciones` (`Edicion_Fecha`, `Edicion_Tabla`,"
                    + " `Edicion_Atributo`, `Edicion_Valor`, `Admi_Correo`) VALUES"
                    + " (current_date(), '"+Edicion.getTabla()+"', "
                    + "'"+Edicion.getAtributo()+"', '"+Edicion.getValorAnterior()+"', '"+Edicion.getAdmi().getCorreo()+"');";
        }
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    
    public String ScriptEdiciones() throws SQLException
    {
        String query = "select e.Edicion_ID, e.Edicion_Fecha,e.Edicion_Tabla,e.Edicion_Valor, "
                + "e.Edicion_Atributo, ifnull(e.Admi_Correo,'SIN USUARIO'),ifnull(concat(a.Admi_Nombre,' ',"
                + "a.Admi_Apellidos),'SIN USUARIO') from ediciones e left join administradores a on "
                + "e.Admi_Correo=a.Admi_Correo order by Edicion_ID; ";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        String retorno = "[";
        rs.next();
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 8; i++)
            {
                String Parcial = rs.getString(i);
                retorno = retorno+"\""+Parcial+"\",";
            }
            rs.next();
            retorno= retorno+"],";
        }
        retorno = retorno+"]";
        return retorno;
    }
    
    public int ObtenerNumeroRegistros() throws SQLException
    {
        String Query = "select count(*) from ediciones";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return Integer.parseInt(rs.getString(1));
    }
    public void EliminarTodo() throws SQLException
    {
        String Query = "truncate table alerta;";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
}
