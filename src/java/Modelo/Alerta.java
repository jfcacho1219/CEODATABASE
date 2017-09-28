package Modelo;

public class Alerta {
    private String Id;
    private String Descripcion;
    private Empresa Company;

    public Alerta(String Id, String Descripcion, Empresa Company) {
        this.Id = Id;
        this.Descripcion = Descripcion;
        this.Company = Company;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Empresa getCompany() {
        return Company;
    }

    public void setCompany(Empresa Company) {
        this.Company = Company;
    }
    
    
    
    
}
