/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.io.Serializable;

/**
 *
 * @author SONY
 */
public class Empresa implements Serializable{

    private String razonSocial;
    private String nit;

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    
    
    

}
