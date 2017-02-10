/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SONY
 */
public class TipoProgramaDAO {
    Conexion conex;
    public TipoProgramaDAO(){
        conex = new Conexion();
    }
    
    public String ObtenerTipoPrograma()
    {
        String Query = "select * from tipoprograma";
        String retorno = "[";
        try {
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(Query);
            rs.next();
            while (rs.getRow() != 0) {
                String ID= rs.getString("TipPrograma_Id");
                String Nombre = rs.getString("TipoPrograma_Nombre");
                retorno = retorno + "\""+ID+"\",\""+Nombre+"\",";
                rs.next();
            }
            retorno = retorno+"]";
            return retorno;
        } catch (Exception e) {
            return "[\"Ocurrio un error, se debe ingresar manualmente]";
        }
    }
    
    public int ObtenerIdMayor()
    {
        String Query = "select TipPrograma_Id from tipoprograma order by TipPrograma_Id desc";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conex.getConnection().createStatement();
            rs = st.executeQuery(Query);
            rs.next();
            String retorno =(rs.getString(1));
            return Integer.parseInt(retorno);
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public int ObtenerNumerodeRegistros()
    {
        String Query = "select count(*) from tipoprograma";
        try {
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(Query);
            rs.next();
            String retorno =(rs.getString(1));
            return Integer.parseInt(retorno);
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public void Insertar(TipoPrograma Tipo) throws SQLException
    {
        int Id = ObtenerIdMayor() +1;
        String Query = "INSERT INTO `basededatosceo`.`tipoprograma` (`TipPrograma_Id`,"
                + " `TipoPrograma_Nombre`, `Descripciòn`) VALUES "
                + "('"+Id+"', '"+Tipo.getNombre()+"', '"+Tipo.getDescripcion()+"');";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    
    public TipoPrograma NuevoTipoPrograma(String TipoProgramaId) throws SQLException
    {
        String Query = "select * from tipoprograma where TipPrograma_Id='"+TipoProgramaId+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return new TipoPrograma(TipoProgramaId, rs.getString("TipoPrograma_Nombre"), rs.getString("Descripciòn"));
    }

    public String ObtenerId(String Programa){
        String Query = "select TipPrograma_Id from tipoprograma where TipoPrograma_Nombre='"+Programa+"';";
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
}
