package Modelo;

public class Persona {
    private String Id;
    private String Cedula;
    private String Nombre;
    private String Apellidos;
    private String Cumpleanos;
    private String Titulo;
    private String Cargo;

    public Persona(String Id, String Cedula, String Nombre, String Apellidos, String Cumpleanos, String Titulo, String Cargo) {
        this.Id = Id;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Cumpleanos = Cumpleanos;
        this.Titulo = Titulo;
        this.Cargo = Cargo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getCumpleanos() {
        return Cumpleanos;
    }

    public void setCumpleanos(String Cumpleanos) {
        this.Cumpleanos = Cumpleanos;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    
    
    
    
        
}
