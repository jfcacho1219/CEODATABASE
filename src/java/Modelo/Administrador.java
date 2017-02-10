package Modelo;
/**
 *
 * @author JULIAN CARVAJAL MONTOYA
 */
public class Administrador {
    public String Correo;
    public String Nombre;
    public String Apellidos;
    public String Contrasena;

    public Administrador(String Correo, String Nombre, String Apellidos, String Contrasena) {
        this.Correo = Correo;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Contrasena = Contrasena;
    }
    
    

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
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

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
    public Boolean ContrasenasIguales(String ConfirmarContrasena)
    {
        if(ConfirmarContrasena.equals(this.getContrasena()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
 
    
    
        
}
