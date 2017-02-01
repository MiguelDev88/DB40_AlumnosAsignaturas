package funciones;
import Clases.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.BufferedReader;
import java.io.IOException;


// @author Miguel

public class Bajas {
    
    public static void bajas (ObjectContainer baseDatos,BufferedReader leer) throws IOException {
        
        byte op=1;
        
        while(op!=0){
            op=Menu.menuBajas(leer);
            switch(op){
                case 1:
                    bajaAlumno(baseDatos, leer);
                    break;
                case 2:
                    bajaProfesor(baseDatos, leer);
                    break;
                case 3:
                    bajaAsignatura(baseDatos, leer);
                    break;
            }
        }
    }
    
    public static void bajaAlumno (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        ObjectSet resultado;
        C_Alumno alumno;
        String dni;
        

        System.out.println("Introducir dni del Alumno a eliminar:");
        dni=leer.readLine();
        resultado=baseDatos.queryByExample(new C_Alumno (dni, null));

        if(!resultado.isEmpty() && !dni.isEmpty())
        {
            alumno=(C_Alumno)resultado.next();
            if(Menu.menuConfirmar(leer)==1)
            {
                baseDatos.delete(alumno);
                System.out.println("\n - Alumno Eliminado - \n");
            }
        }
        else
            System.out.println("\n Alumno no encontrado \n");
        
    }
    
    public static void bajaProfesor (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        ObjectSet resultado;
        C_Profesor profesor;
        String dni;
        

        System.out.println("Introducir dni del Profesor a eliminar:");
        dni=leer.readLine();
        resultado=baseDatos.queryByExample(new C_Profesor (dni, null, null));

        if(!resultado.isEmpty() && !dni.isEmpty())
        {
            profesor=(C_Profesor)resultado.next();
            if(Menu.menuConfirmar(leer)==1)
            {
                baseDatos.delete(profesor);
                limpiarAsignatura(baseDatos, profesor);
                System.out.println("\n - Profesor Eliminado - \n");
            }
        }
        else
            System.out.println("\n Profesor no encontrado \n");
        
    }
    
    public static void bajaAsignatura (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        ObjectSet resultado;
        C_Asignatura asignatura;
        String nombre;
        

        System.out.println("Introducir nombre de la Asignatura a eliminar:");
        nombre=leer.readLine();
        resultado=baseDatos.queryByExample(new C_Asignatura (nombre));

        if(!resultado.isEmpty() && !nombre.isEmpty())
        {
            asignatura=(C_Asignatura)resultado.next();
            if(Menu.menuConfirmar(leer)==1)
            {
                baseDatos.delete(asignatura);
                limpiarPersona(baseDatos, asignatura);
                System.out.println("\n - Asignatura Eliminada - \n");
            }
        }
        else
            System.out.println("\n Asignatura no encontrada \n");
        
    }
    
    public static void limpiarPersona (ObjectContainer baseDatos, C_Asignatura asignatura) throws IOException {
        
        ObjectSet resultado;
        C_Persona persona=null;
        
        resultado=baseDatos.queryByExample(persona);

        while(resultado.hasNext())
        {
            persona=(C_Persona)resultado.next();
            if(persona instanceof C_Alumno && ((C_Alumno)persona).getAsignaturas().contains(asignatura))
            {
                int i=((C_Alumno)persona).getAsignaturas().indexOf(asignatura);
                ((C_Alumno)persona).getAsignaturas().remove(asignatura);
                ((C_Alumno)persona).getNotas().remove(i);

            }else if (persona instanceof C_Profesor && ((C_Profesor)persona).getAsignaturas().contains(asignatura) )
                ((C_Profesor)persona).getAsignaturas().remove(asignatura);
        }
    }
    
    public static void limpiarAsignatura (ObjectContainer baseDatos, C_Profesor profesor) throws IOException {
        
        ObjectSet resultado;
        C_Asignatura asignatura;
        
        resultado=baseDatos.queryByExample(new C_Asignatura());

        while(resultado.hasNext())
        {
            asignatura=(C_Asignatura)resultado.next();
            
            if(asignatura.getProfesor() == profesor){
                asignatura.setProfesor(null);
            }
        }
    }
}
