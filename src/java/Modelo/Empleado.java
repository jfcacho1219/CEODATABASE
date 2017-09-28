
package Modelo;

public class Empleado {
    private String ID;
    private Empresa Empresa;
    private String Email1;
    private String Email2;
    private String Telefono1;
    private String Telefono2;
    private Persona Persona;
    private Comite Comite;

    public Empleado(String ID, Empresa Empresa, String Email1, String Email2, String Telefono1, String Telefono2, Persona Persona, Comite Comite) {
        this.ID = ID;
        this.Empresa = Empresa;
        this.Email1 = Email1;
        this.Email2 = Email2;
        this.Telefono1 = Telefono1;
        this.Telefono2 = Telefono2;
        this.Persona = Persona;
        this.Comite = Comite;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa) {
        this.Empresa = Empresa;
    }

    public String getEmail1() {
        return Email1;
    }

    public void setEmail1(String Email1) {
        this.Email1 = Email1;
    }

    public String getEmail2() {
        return Email2;
    }

    public void setEmail2(String Email2) {
        this.Email2 = Email2;
    }

    public String getTelefono1() {
        return Telefono1;
    }

    public void setTelefono1(String Telefono1) {
        this.Telefono1 = Telefono1;
    }

    public String getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(String Telefono2) {
        this.Telefono2 = Telefono2;
    }

    public Persona getPersona() {
        return Persona;
    }

    public void setPersona(Persona Persona) {
        this.Persona = Persona;
    }

    public Comite getComite() {
        return Comite;
    }

    public void setComite(Comite Comite) {
        this.Comite = Comite;
    }
    
    
    
    

    
    
}
