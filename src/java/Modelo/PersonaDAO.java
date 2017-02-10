/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SONY
 */
public class PersonaDAO {
    Conexion conex;
    public PersonaDAO()
    {
        conex = new Conexion();
    }
    
    public void Insertar(Persona Person) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`persona` (`Persona_Id`,`Persona_Cedula`, "
                + "`Persona_Nombre`, `Persona_Apellidos`, `Persona_Cumple`, `Persona_Titulo`, "
                + "`Persona_Cargo`) VALUES ('"+Person.getId()+"','"+Person.getCedula()+"', '"+Person.getNombre()+"',"
                + " '"+Person.getApellidos()+"', '"+Person.getCumpleanos()+"', '"+Person.getTitulo()+"', '"+Person.getCargo()+"');";
        Statement st = conex.getConnection().createStatement();
        System.out.println(Query);
        st.executeUpdate(Query);
    }

    public Persona NuevaPersona(String PersonaID) throws SQLException {
        String Query = "select * from persona where Persona_Id='"+PersonaID+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return new Persona(rs.getString("Persona_Id"),rs.getString("Persona_Cedula"), rs.getString("Persona_Nombre"),
                rs.getString("Persona_Apellidos"), rs.getString("Persona_Cumple"), rs.getString("Persona_Titulo"),rs.getString("Persona_Cargo"));
    }

    public void Editar(Persona Person) throws SQLException {
        String Query = "UPDATE `basededatosceo`.`persona` SET `Persona_Cedula`='"+Person.getCedula()+"', "
                + "`Persona_Nombre`='"+Person.getNombre()+"', `Persona_Apellidos`='"+Person.getApellidos()+"',"
                + " `Persona_Cumple`='"+Person.getCumpleanos()+"', `Persona_Titulo`='"+Person.getTitulo()+"',"
                + " `Persona_Cargo`='"+Person.getCargo()+"' WHERE `Persona_Id`='"+Person.getId()+"';";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }

    public void Eliminar(String Id) throws SQLException {
        String Query = "DELETE FROM `basededatosceo`.`persona` WHERE `Persona_Id`='"+Id+"';";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    
    public String ObtenerIdMayor()
    {
        String Query = "select Persona_Id + 1 from persona order by Persona_Id desc";
        Statement st;
        try {
            st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(Query);
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            return "1";
        }   
    }
    
    public String ObtenerId(Persona Person) throws SQLException
    {
        String Query = "select * from persona where Persona_Cedula='"+Person.getCedula()+"' and Persona_Nombre='"+Person.getNombre()+"'"
                + " and Persona_Apellidos='"+Person.getApellidos()+"' and Persona_Cumple='"+Person.getCumpleanos()+"'"
                + " and Persona_Titulo='"+Person.getTitulo()+"' and Persona_Cargo='"+Person.getCargo()+"' "
                + "order by Persona_Id desc";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return rs.getString(1);
    }
    
                
    
}
