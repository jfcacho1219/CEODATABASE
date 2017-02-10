/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author SONY
 */
public class ProgramaPago {
    private String Pago;
    private TipoPrograma Tipo;
    private Empleado Emple;

    public ProgramaPago(String Pago, TipoPrograma Tipo, Empleado Emple) {
        this.Pago = Pago;
        this.Tipo = Tipo;
        this.Emple = Emple;
    }

    public String getPago() {
        return Pago;
    }

    public void setPago(String Pago) {
        this.Pago = Pago;
    }

    public TipoPrograma getTipo() {
        return Tipo;
    }

    public void setTipo(TipoPrograma Tipo) {
        this.Tipo = Tipo;
    }

    public Empleado getEmple() {
        return Emple;
    }

    public void setEmple(Empleado Emple) {
        this.Emple = Emple;
    }
    
    
    
}
