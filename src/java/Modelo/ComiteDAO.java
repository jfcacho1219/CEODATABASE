/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SONY
 */
public class ComiteDAO {
    Conexion conex;
    
    public ComiteDAO()
    {
        conex = new Conexion();
    }
    
    public Comite NuevoComite(String Id) throws SQLException
    {
        //nd
        //String Query = "select * from comite where Comite_Id = '"+Id+"';";
        String Query2 = "select * from comite where Comite_Id = ? ;";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query2);
        pr.setString(1, Id);
        ResultSet rs = pr.executeQuery();
        rs.next();
        return new Comite(rs.getString("Comite_Id"), rs.getString("Comite_Nombre"));
    }
    
    public void Insertar(Comite Comite) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`comite` (`Comite_Nombre`)"
                + " VALUES ('"+Comite.getNombre()+"');";
        String Query2 = "INSERT INTO `basededatosceo`.`comite` (`Comite_Nombre`)"
                + " VALUES (?);";
        PreparedStatement pr = conex.getConnection().prepareStatement(Query2);
        pr.setString(1, Comite.getNombre());
        pr.executeUpdate(Query);
    }
    
    public String ScriptComite() throws SQLException
    {
        String Query = "select Comite_Nombre,Comite_Id from comite;";
        Statement st =  conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        rs.next();
        
        while (rs.getRow() != 0)
        {
            for (int i = 1; i < 3; i++)
            {
                String Parcial = rs.getString(i);
                retorno = retorno+"\""+Parcial+"\",";
            }
            rs.next();
        }
        retorno = retorno+"]";
        return retorno;
    }

    public String ObtenerId(String Comite) throws SQLException {
        String Query = "select Comite_Id from comite where Comite_Nombre = '"+Comite+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return rs.getString(1);
    }
    
    
}
