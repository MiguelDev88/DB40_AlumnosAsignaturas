package Clases;
import com.db4o.collections.ArrayList4;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


 // @author Miguel
 
public abstract class C_Persona implements Serializable{
    
    private String dni;
    private String nombre;
    private List asignaturas;
    
    
    public C_Persona () {}
    
    public C_Persona (String dni, String nombre) {
        
        this.dni=dni;
        this.nombre=nombre;
        this.asignaturas=new ArrayList<>();
        
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List asignaturas) {
        this.asignaturas = asignaturas;
    }
    
}