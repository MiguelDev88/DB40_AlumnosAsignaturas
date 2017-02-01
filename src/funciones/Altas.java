package funciones;
import Clases.*;
import com.db4o.ObjectContainer;
import java.io.BufferedReader;
import java.io.IOException;


// @author Miguel

public class Altas {
    
    
    public static void altas(ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        byte op=1;
        

        while(op!=0){

            op=Menu.menuAltas(leer);

            switch (op){
                case 1:
                    altaAlumno(baseDatos, leer);
                    break;
                case 2:
                    altaProfesor(baseDatos, leer);
                    break;
                case 3:
                    altaAsignatura(baseDatos, leer);
                    break;
            }
        }  
    }
    
    public static void altaAlumno (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        C_Persona alumno;
        
        try{
            
            alumno=nuevoAlumno(leer);
            if(baseDatos.queryByExample(new C_Alumno(alumno.getDni(), null)).isEmpty())
            {
                Asociar.asociarAsignaturas(baseDatos, alumno, leer);
                Asociar.asociarNotas((C_Alumno)alumno, leer);
                baseDatos.store(alumno);
                baseDatos.commit();
                System.out.println("\n - Alumno Registrado - \n");
            }
            else
                System.out.println("\n - Alumno  ya Existente - \n");
            
        }catch(Exception e)
        {
            System.out.println("\n - Error en el alta del Alumno - \n");
        }
        
    }
    
    public static void altaProfesor (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        C_Persona profesor;
        
        try{
            
            profesor=nuevoProfesor(leer);
            if(baseDatos.queryByExample(new C_Profesor(profesor.getDni(), null, null)).isEmpty())
            {
                Asociar.asociarAsignaturas(baseDatos, profesor, leer);
                baseDatos.store(profesor);
                baseDatos.commit();
                System.out.println("\n - Profesor Registrado - \n");
            }
            else
                System.out.println("\n - Profesor  ya Existente - \n");
            
        }catch(Exception e)
        {
            System.out.println("\n - Error en el alta del Profesor - \n");
        }
        
    }
    
    public static void altaAsignatura (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        C_Asignatura asignatura;
        
        try{
            
            asignatura=nuevaAsignatura(leer);
            if(baseDatos.queryByExample(asignatura).isEmpty())
            {
                Asociar.asociarProfesor(baseDatos, asignatura, leer);
                baseDatos.store(asignatura);
                baseDatos.commit();
                System.out.println("\n - Asignatura Registrada - ");
            }
            else
                System.out.println("\n - Asignatura  ya Existente - ");
            
        }catch(Exception e)
        {
            System.out.println("\n - Error en el alta de la Asignatura - \n");
        }
        
    }
    
    public static C_Persona nuevoAlumno (BufferedReader leer) throws IOException{
        
        C_Persona persona;
        String dni, nombre;
        
        
        System.out.println("Introducir Dni:");
        dni=leer.readLine();

        System.out.println("Introducir Nombre:");
        nombre=leer.readLine();
        
        persona=new C_Alumno(dni, nombre);
        
        
        return persona;
        
    }
    
    public static C_Persona nuevoProfesor (BufferedReader leer) throws IOException{
        
        C_Persona persona;
        String dni, nombre, titulacion;
        
        
        System.out.println("Introducir Dni:");
        dni=leer.readLine();

        System.out.println("Introducir Nombre:");
        nombre=leer.readLine();
        
        System.out.println("Introducir Titulación:");
        titulacion=leer.readLine();
        
        persona=new C_Profesor(dni, nombre, titulacion);
        
        
        return persona;
        
    }
    
    public static C_Asignatura nuevaAsignatura (BufferedReader leer) throws IOException{
        
        C_Asignatura asignatura;
        String nombre;
       

        System.out.println("Introducir Nombre:");
        nombre=leer.readLine();
        
        asignatura = new C_Asignatura(nombre);
        
        
        return asignatura;
        
    }
    
}
