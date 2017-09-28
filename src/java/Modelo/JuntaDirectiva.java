package Modelo;

public class JuntaDirectiva {
    private String ID;
    private String Calidad;
    private String NIT;
    private String EmpleadoId;
    private String PersonaId;

    public JuntaDirectiva(String ID, String Calidad, String NIT, String EmpleadoId, String PersonaId) {
        this.ID = ID;
        this.Calidad = Calidad;
        this.NIT = NIT;
        this.EmpleadoId = EmpleadoId;
        this.PersonaId = PersonaId;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCalidad() {
        return Calidad;
    }

    public void setCalidad(String Calidad) {
        this.Calidad = Calidad;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getEmpleadoId() {
        return EmpleadoId;
    }

    public void setEmpleadoId(String EmpleadoId) {
        this.EmpleadoId = EmpleadoId;
    }

    public String getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(String PersonaId) {
        this.PersonaId = PersonaId;
    }

    
    
    
}
