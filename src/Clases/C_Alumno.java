package Clases;
import java.util.ArrayList;
import java.util.List;


 // @author Miguel
 
public class C_Alumno extends C_Persona {
    
    private List notas;
    
    
    public C_Alumno () {}
    
    public C_Alumno (String dni, String nombre) {
        
        super(dni,nombre);
        this.notas=new ArrayList<>();
        
    }

    public List getNotas() {
        return notas;
    }

    public void setNotas(List notas) {
        this.notas = notas;
    }
    
}
