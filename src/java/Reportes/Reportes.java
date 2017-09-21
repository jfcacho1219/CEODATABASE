/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author SONY
 */
public class Reportes {

    public Reportes() throws SQLException, ClassNotFoundException, JRException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/basededatosceo","DBCEO","root");
        
        JasperReport reporte = (JasperReport)JRLoader.loadObject("C:\\Users\\SONY\\Documents\\NetBeansProjects\\CEODATABASE\\src\\java\\Reportes\\Empresas.jasper");
        //JasperReport reporte = JasperCompileManager.compileReport("C:\\Users\\SONY\\Documents\\NetBeansProjects\\CEODATABASE\\src\\java\\Reportes\\Empresas.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

        JRExporter exporter = new JRHtmlExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("reporte.html"));
        exporter.exportReport();
       
    }
    
    
    
}
