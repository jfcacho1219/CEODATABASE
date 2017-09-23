/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Reportes.Reportes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import net.sf.jasperreports.engine.JRException;
/**
 *
 * @author SONY
 */
public class EmpresaDAO {
    Conexion conex;

    public EmpresaDAO() {
        conex = new Conexion();
    }
    
    public void Insertar(Empresa Empresa) throws SQLException
    {
        String Query = "INSERT INTO `basededatosceo`.`empresa` (`NIT`, `Emp_Nombre`, `Emp_TipoDeSociedad`, "
                + "`Emp_AnoCreacion`, `Emp_AnoAfiliacion`, `Emp_SectorProductivo`,"
                + " `Emp_NumEmpleados`, `Emp_TamanoEmpresa`, `Emp_Descripcion`, `Emp_SostenimientoPesos`, "
                + "`Emp_SostenimientoSalarios`, `Emp_DireccionPlanta`, `Emp_DireccionAdministrativa`, "
                + "`Emp_MunicipioPlanta`, `Emp_MunicipioAdministrativa`, `Emp_Activa`) "
                + "VALUES ('"+Empresa.getNIT()+"', '"+Empresa.getNombre()+"', '"+Empresa.getSociedad()+""
                + "', '"+Empresa.getAnoCreacion()+"', '"+Empresa.getAnoAfiliacion()+"',"
                + " '"+Empresa.getSectorProductivo()+"', '"+Empresa.getNumeroEmpleados()+"', '"+Empresa.getTamanoEmpresa()+"'"
                + ", '"+Empresa.getDescripcion()+"', '"+Empresa.getSostenimientoPesos()+"', '"+Empresa.getSostemientoSMLV()+"'"
                + ", '"+Empresa.getDireccionPlanta()+"',"
                + " '"+Empresa.getDireccionAdministrativa()+"', '"+Empresa.getMunicipioPlanta()+"'"
                + ", '"+Empresa.getMunicipioAdminitrativa()+"', '"+Empresa.getActiva()+"');";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
        System.out.println(Query);
       
    }

    public String ObtenerEmpresas(String Afiliada) throws SQLException{
        String Activa = "YES";
        if (Afiliada.equals("NoAffiliatedCompany")) {
            Activa="NO";
        }
        String Query = "select * from empresa where Emp_Activa='"+Activa+"' order by Emp_Nombre";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        
        rs.next();
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 16; i++)
            {
                String Parcial = rs.getString(i);
                if (i==7 || i==10) {
                    DecimalFormat formato = new DecimalFormat("#,###");
                    int numero = Integer.parseInt(Parcial);
                    String num = formato.format(numero);
                    if(i==7)retorno = retorno+"\""+num+"\",";
                    else retorno = retorno+"\"$"+num+"\",";
                }
                else retorno = retorno+"\""+Parcial+"\",";
            }
            retorno = retorno + "],";
            rs.next();
        }
        retorno = retorno+"]";
        rs.close();
        st.close();
        return retorno;
    }
    
    public Empresa NuevaEmpresa(String NIT) throws SQLException {
        String Query = "select * from empresa where NIT = '"+NIT+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        return new Empresa(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                rs.getString(15),rs.getString(16));
    }

    public void Editar(Empresa Company,String NIT) throws SQLException {
        //falta poner NIT //`NIT`='Company.getNIT()'
        String Query = "UPDATE `basededatosceo`.`empresa` SET `Emp_Nombre`='"+Company.getNombre()+"',"
                + "`NIT`='"+Company.getNIT()+"', `Emp_TipoDeSociedad`='"+Company.getSociedad()+"',"
                + " `Emp_AnoCreacion`='"+Company.getAnoCreacion()+"', `Emp_AnoAfiliacion`='"+Company.getAnoAfiliacion()+"',"
                + "`Emp_SectorProductivo`='"+Company.getSectorProductivo()+"', `Emp_NumEmpleados`='"+Company.getNumeroEmpleados()+"', "
                + "`Emp_TamanoEmpresa`='"+Company.getTamanoEmpresa()+"', `Emp_Descripcion`='"+Company.getDescripcion()+"', "
                + "`Emp_SostenimientoPesos`='"+Company.getSostenimientoPesos()+"', `Emp_SostenimientoSalarios`='"+Company.getSostemientoSMLV()+"',"
                + " `Emp_DireccionPlanta`='"+Company.getDireccionPlanta()+"', `Emp_DireccionAdministrativa`='"+Company.getDireccionAdministrativa()+"',"
                + " `Emp_MunicipioPlanta`='"+Company.getMunicipioPlanta()+"', `Emp_MunicipioAdministrativa`='"+Company.getMunicipioAdminitrativa()+"',"
                + " `Emp_Activa`='"+Company.getActiva()+"' WHERE `NIT`='"+NIT+"';";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    
    public void Eliminar(String NIT) throws SQLException
    {
        String Query = "DELETE FROM `basededatosceo`.`empresa` WHERE `NIT`='"+NIT+"';";
        Statement st = conex.getConnection().createStatement();
        st.executeUpdate(Query);
    }
    
    public String ObtenerNITYNombre() throws SQLException
    {
        String Query = "select NIT,Emp_Nombre from  empresa order by Emp_Nombre;";
        Statement st  = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        String retorno = "[";
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 3; i++)
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

    public String ObtenerEmpresas(String[] VariablesEmpresa, String[] VariablesFecha, String[] VariablesNumericas) throws SQLException {
        String QueryNumeroEmpleados;
        String QuerySostenimientoPesos;
        String QuerySostenimientosSMLV;
        if(VariablesNumericas[1].equals("0")) QueryNumeroEmpleados ="Emp_NumEmpleados >= "+VariablesNumericas[0]+"";
        else QueryNumeroEmpleados = "Emp_NumEmpleados between "+VariablesNumericas[0]+" and "+VariablesNumericas[1]+"";
        if(VariablesNumericas[3].equals("0")) QuerySostenimientoPesos="Emp_SostenimientoPesos >= "+VariablesNumericas[2]+"";
        else QuerySostenimientoPesos="Emp_SostenimientoPesos between "+VariablesNumericas[2]+" and "+VariablesNumericas[3]+"";
        if(VariablesNumericas[5].equals("0")) QuerySostenimientosSMLV = "Emp_SostenimientoSalarios >= "+VariablesNumericas[4]+"";
        else QuerySostenimientosSMLV = "Emp_SostenimientoSalarios between "+VariablesNumericas[4]+" and "+VariablesNumericas[5]+"";
        
        String QueryFechaAfiliacion;
        String QueryFechaCreación;
        if(VariablesFecha[0].equals(""))QueryFechaAfiliacion = "Emp_AnoAfiliacion >= '"+VariablesFecha[1]+"'";
        else if(!VariablesFecha[1].equals("")) QueryFechaAfiliacion = "Emp_AnoAfiliacion between '"+VariablesFecha[0]+"' and '"+VariablesFecha[1]+"'";
        else  QueryFechaAfiliacion = "Emp_AnoAfiliacion between '"+VariablesFecha[1]+"' and '"+VariablesFecha[0]+"'";
        if(VariablesFecha[2].equals("")) QueryFechaCreación =  "Emp_AnoCreacion >= '"+VariablesFecha[3]+"'";
        else if(!VariablesFecha[3].equals("")) QueryFechaCreación= "Emp_AnoCreacion between '"+VariablesFecha[2]+"' and '"+VariablesFecha[3]+"'";
        else QueryFechaCreación= "Emp_AnoCreacion between '"+VariablesFecha[3]+"' and '"+VariablesFecha[2]+"'";
        
        String Query = "select* from empresa where NIT like '%"+VariablesEmpresa[0]+"%' and Emp_Nombre like '%"+VariablesEmpresa[1]+"%' and"
                + " Emp_TipoDeSociedad like '%"+VariablesEmpresa[2]+"%' and "+QueryFechaCreación+" and "+QueryFechaAfiliacion+" and "
                + "Emp_SectorProductivo like '%"+VariablesEmpresa[10]+"%' and "+QueryNumeroEmpleados+" and"
                + " Emp_TamanoEmpresa like '%"+VariablesEmpresa[3]+"%' and Emp_Descripcion like '%"+VariablesEmpresa[4]+"%' and "
                + ""+QuerySostenimientoPesos+" and "+QuerySostenimientosSMLV+" and Emp_DireccionPlanta like '%"+VariablesEmpresa[5]+"%' and "
                + "Emp_DireccionAdministrativa like '%"+VariablesEmpresa[7]+"%' "
                + "and Emp_MunicipioPlanta like '%"+VariablesEmpresa[6]+"%' and Emp_MunicipioAdministrativa "
                + "like '%"+VariablesEmpresa[8]+"%' and Emp_Activa='"+VariablesEmpresa[9]+"';";
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery(Query);
        String retorno = "[";
        rs.next();
        while (rs.getRow() != 0)
        {
            retorno = retorno+"[";
            for (int i = 1; i < 16; i++)
            {
                String Parcial = rs.getString(i);
                if (i==7 || i==10) {
                    DecimalFormat formato = new DecimalFormat("#,###");
                    int numero = Integer.parseInt(Parcial);
                    String num = formato.format(numero);
                    if(i==7)retorno = retorno+"\""+num+"\",";
                    else retorno = retorno+"\"$"+num+"\",";
                }
                else retorno = retorno+"\""+Parcial+"\",";
            }
            retorno = retorno + "],";
            rs.next();
        }
        retorno = retorno+"]";
        return retorno;
    }

    /*public void getReports(String ruta)throws  ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conexion2;
        conexion2 = DriverManager.getConnection("jdbc:mysql://localhost/basededatosceo","DBCEO","root"); 
        Map Parameter = new HashMap();
        try {
             File file = new File(ruta);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.addHeader("Content-Disposition", "attachment; filename="+"OutStanding_DC_Report"+".xlsx");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, Parameter, conexion2);

            JRExporter jrExporter = null;                      
            jrExporter = new JRXlsExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());

            try {
                jrExporter.exportReport();
            } catch (JRException e) {
                e.printStackTrace();
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(conex.getConnection()!=null)
            {
                try {
                    conex.getConnection().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }*/
}
