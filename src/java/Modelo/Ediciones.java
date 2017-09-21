
package Modelo;

import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author SONY
 */
public class Ediciones {
    private String ID;
    private String FechaEdicion;
    private String Tabla;
    private String Atributo;
    private String ValorAnterior;
    private Administrador Admi;

    public Ediciones(String ID, String FechaEdicion, String Tabla, String Atributo, String ValorAnterior, Administrador Admi) {
        this.ID = ID;
        this.FechaEdicion = FechaEdicion;
        this.Tabla = Tabla;
        this.Atributo = Atributo;
        this.ValorAnterior = ValorAnterior;
        this.Admi = Admi;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFechaEdicion() {
        return FechaEdicion;
    }

    public void setFechaEdicion(String FechaEdicion) {
        this.FechaEdicion = FechaEdicion;
    }

    public String getTabla() {
        return Tabla;
    }

    public void setTabla(String Tabla) {
        this.Tabla = Tabla;
    }

    public String getAtributo() {
        return Atributo;
    }

    public void setAtributo(String Atributo) {
        this.Atributo = Atributo;
    }

    public String getValorAnterior() {
        return ValorAnterior;
    }

    public void setValorAnterior(String ValorAnterior) {
        this.ValorAnterior = ValorAnterior;
    }

    public Administrador getAdmi() {
        return Admi;
    }

    public void setAdmi(Administrador Admi) {
        this.Admi = Admi;
    }
    

    
    
}
