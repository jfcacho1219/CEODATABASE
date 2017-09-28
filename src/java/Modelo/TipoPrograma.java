package Modelo;

public class TipoPrograma {
    private String Id;
    private String Nombre;
    private String Descripcion;

    public TipoPrograma(String Id, String Nombre, String Descripcion) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    
}
