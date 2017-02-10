/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * BreadCrumb
 */
@WebServlet(name = "ControllerReports", urlPatterns = {"/ControllerReports"})
public class ControllerReports extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String tituloReporte = "REPORTE PERSONAS";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/report/persona.jrxml");
            Map parameter = new  HashMap();
            //JasperDesign jasperDesign = JXmlLoader();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }

    
}
