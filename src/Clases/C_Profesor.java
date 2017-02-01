package Clases;


 // @author Miguel
 
public class C_Profesor extends C_Persona {
    
    private String titulacion;

    
    public C_Profesor () {}
    
    public C_Profesor (String dni, String nombre, String titulacion) {
        
        super(dni,nombre);
        this.titulacion=titulacion;

    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
     
}
