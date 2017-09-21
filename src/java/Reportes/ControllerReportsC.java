/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author SONY
 */
@WebServlet(name = "ControllerReportsC", urlPatterns = {"/ControllerReportsC"})
public class ControllerReportsC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/basededatosceo","DBCEO","root");
        } catch (Exception e) {
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte.xlsx\";");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("application/xlsx");
        
    /*    List<Empresa> listaEempresas = new ArrayList<>();
        Empresa  empresa = new Empresa();
        empresa.setRazonSocial("Riotex");
        empresa.setNit("001-1");
        listaEempresas.add(empresa);
        empresa = new Empresa();
        empresa.setRazonSocial("Coltejer");
        empresa.setNit("002-2");
        listaEempresas.add(empresa);
        
      */  
         

        ServletOutputStream out = response.getOutputStream();
        try
        {
            //String Url = "C:\\Users\\SONY\\Documents\\NetBeansProjects\\CEODATABASE\\src\\java\\Reportes\\Empresas2.jasper";
            String Url = "C:\\Users\\SONY\\Documents\\NetBeansProjects\\CEODATABASE\\src\\java\\Reportes\\Empresas2.jasper";
            JasperReport reporte = (JasperReport)JRLoader.loadObject("C:\\Users\\SONY\\Documents\\NetBeansProjects\\CEODATABASE\\src\\java\\Reportes\\NIT.jasper");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
            System.out.println("se estalló");
            JRExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
            
        }
        catch (Exception e)
        {
            System.out.println("oeeeeeeeeee"+e);
        }
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
