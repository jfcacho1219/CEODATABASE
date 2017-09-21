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
public class AlertaDAO {
    Conexion conex;
    public AlertaDAO()
    {
        conex = new Conexion();
    }
    
    public void Insertar(Alerta Alert) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`alerta` (`Alerta_Descripcion`, `NIT`)"
                + " VALUES ('"+Alert.getDescripcion()+"', '"+Alert.getCompany().getNIT()+"');";
        Query = "INSERT INTO `basededatosceo`.`alerta` "
                + "(`Alerta_Fecha`, `Alerta_Descripcion`, `NIT`)"
                + " VALUES (current_date(), '"+Alert.getDescripcion()+"', '"+Alert.getCompany().getNIT()+"');";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    public void Editar(Alerta Alert) throws SQLException
    {
        String Query = "UPDATE `basededatosceo`.`alerta` SET `Alerta_Descripcion`='"+Alert.getDescripcion()+"' "
                + "WHERE `Alerta_Id`='"+Alert.getId()+"';";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    public void Eliminar(Alerta Alert) throws SQLException
    {
        String Query = "DELETE FROM `basededatosceo`.`alerta` WHERE `Alerta_Id`='"+Alert.getId()+"'";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    public Alerta Obtener(String Id) throws SQLException
    {
        String Query = "select * from alerta where Alerta_Id='"+Id+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        return new Alerta(rs.getString("Alerta_Id"), rs.getString("Alerta_Descripcion"), new EmpresaDAO().NuevaEmpresa(rs.getString("NIT")));
    }
    public Alerta ObtenerId(Alerta Alert) throws SQLException
    {
        String Query = "select * from alerta where Alerta_Descripcion='"+Alert.getDescripcion()+"' "
                + "and NIT='"+Alert.getCompany().getNIT()+"'";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        return new Alerta(rs.getString("Alerta_Id"), rs.getString("Alerta_Descripcion"), new EmpresaDAO().NuevaEmpresa(rs.getString("NIT")));

    }

    public String ScriptAlerta() throws SQLException {
        String Query = "select a.Alerta_Id,a.Alerta_Fecha,a.NIT,e.Emp_Nombre,a.Alerta_Descripcion"
                + " from alerta a natural join Empresa e";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        rs.next();
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 6; i++)
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

    public Alerta NuevaAlerta(String IdAlerta) throws SQLException {
        String Query = "select * from alerta where Alerta_Id='"+IdAlerta+"' order by Alerta_Fecha";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return new Alerta(rs.getString("Alerta_Id"),rs.getString("Alerta_Descripcion"), new EmpresaDAO().NuevaEmpresa(rs.getString("NIT")));
    }
}
