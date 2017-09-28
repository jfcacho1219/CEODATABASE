package Modelo;

import java.sql.*;

public class Conexion {
    public Conexion(){
    }
    
    Connection conex = null;
    Statement stm = null;
    public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("llega por lo menos hasta acá");
            conex = DriverManager.getConnection("jdbc:mysql://localhost/basededatosceo","root","root");
            System.out.println("Excelent mafren");            
        } 
        catch (Exception e) {
           
        }
        return conex; 
        
    }
    
    
    public void desconectar(){
      conex = null;
   }
}
