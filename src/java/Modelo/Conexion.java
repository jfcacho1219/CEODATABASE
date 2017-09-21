package Modelo;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class Conexion {
    public Conexion(){
    }
    
    Connection conex = null;
    Statement stm = null;
    public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conex = DriverManager.getConnection("jdbc:mysql://localhost/basededatosceo","root","root");            
        } 
        catch (Exception e) {
            
        }
        return conex;     
    }
    
    public Connection getConex()
    {
        return conex;
    }
    
    public void desconectar(){
      conex = null;
   }
}
