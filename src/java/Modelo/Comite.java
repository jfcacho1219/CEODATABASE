package Modelo;

public class Comite {
    private String Id;
    private String Nombre;

    public Comite(String Id, String Nombre) {
        this.Id = Id;
        this.Nombre = Nombre;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
