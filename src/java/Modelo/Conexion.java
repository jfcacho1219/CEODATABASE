package Modelo;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    public Conexion(){
    }
    
    Connection conex = null;
    Statement stm = null;
    public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conex = DriverManager.getConnection("jdbc:mysql://localhost/basededatosceo","DBCEO","root");            
        } 
        catch (Exception e) {
            
        }
        return conex;     
    }
    
    
    public void desconectar(){
      conex = null;
   }
}
