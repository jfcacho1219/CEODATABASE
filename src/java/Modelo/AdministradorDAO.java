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

/**
 *
 * @author SONY
 */
public class AdministradorDAO {
    Conexion conex;
    public AdministradorDAO(){
        conex= new Conexion();
    } 
    
    public String ValidarContrasena(String ContrasenaAdministrador)
    {
        String Query = "SELECT Admi_Contrasena FROM `basededatosceo`.administradores;";
        try {
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(Query);
            rs.next();
            while (rs.getRow() != 0) {
                String ContraDB= rs.getString(1);
                if(ContraDB.equals(ContrasenaAdministrador))
                {
                    return "YES";
                }
                rs.next();
            }
            rs.close();
            st.close();
            return "NO";
        } catch (Exception e) {
            return "Ocurrió un error al consultar las contraseñas de la base de datos";
        }
        
    }
    
    public void IngresarAdministrador(Administrador Admi) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`administradores` (`Admi_Correo`, `Admi_Nombre`,"
                + " `Admi_Apellidos`, `Admi_Contrasena`) VALUES ('"+Admi.getCorreo()+"', '"+Admi.getNombre()+""
                + "', '"+Admi.getApellidos()+"', '"+Admi.getContrasena()+"');";
      
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);   
        st.close();
    }
    
    public Administrador IniciarSesion(String Correo) throws SQLException
    {
        String Query = "SELECT * FROM basededatosceo.administradores where Admi_Correo='"+Correo+"';";
        String Query2 = "SELECT * FROM basededatosceo.administradores where Admi_Correo=?;";
        Statement st = conex.getConnection().createStatement();
        PreparedStatement pr = (PreparedStatement)conex.getConnection().prepareStatement(Query2);
        pr.setString(1, Correo);
        ResultSet rs = st.executeQuery(Query);
        rs = pr.executeQuery();
        rs.next();
        String Correos = rs.getString("Admi_Correo");
        String Nombres = rs.getString("Admi_Nombre");
        String Apellidos = rs.getString("Admi_Apellidos");
        String Contrasena = rs.getString("Admi_Contrasena");
        Administrador Admi = new Administrador(rs.getString("Admi_Correo"), rs.getString("Admi_Nombre"), rs.getString("Admi_Apellidos"), rs.getString("Admi_Contrasena"));
        rs.close();
        st.close();
        return Admi;
    }
    
    
}
