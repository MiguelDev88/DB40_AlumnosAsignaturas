package Clases;
import java.io.Serializable;


 // @author Miguel

public class C_Asignatura implements Serializable {
    
    private String nombre;
    private C_Profesor profesor;
    
    
    public C_Asignatura () {}
    
    public C_Asignatura (String nombre) {
        
        this.nombre=nombre;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public C_Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(C_Profesor profesor) {
        this.profesor = profesor;
    }

}
