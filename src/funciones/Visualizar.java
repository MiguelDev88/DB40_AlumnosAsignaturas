package funciones;
import Clases.*;
import com.db4o.ObjectSet;
import java.util.Iterator;


// @author Miguel

public class Visualizar {
    
    
    public static void visualizarAlumnos (ObjectSet resultado) {
        
        C_Alumno alumno;
        C_Asignatura asignatura;
        Integer nota;
        
        
        if(resultado.isEmpty())
            System.out.println("\n\n --- No hay datos que mostrar --- \n\n");
        
        else
        {
        
            while(resultado.hasNext())
            {
                alumno=(C_Alumno)resultado.next();
                System.out.println("-----------------------------------------");
                System.out.println("       DNI         NOMBRE       ");
                System.out.printf("%12s   %11s%n",alumno.getDni(),alumno.getNombre());
                
                Iterator asignaturas=alumno.getAsignaturas().iterator();
                Iterator notas=alumno.getNotas().iterator();
                System.out.println("\n          - Asignaturas del alumno: - ");
                System.out.println("            ASIGNATURA         NOTA     ");
                while(asignaturas.hasNext())
                {
                    asignatura=(C_Asignatura) asignaturas.next(); if(asignatura==null) System.out.println("mal"); else{
                    nota=(Integer)notas.next();
                    System.out.printf("         %15s%15s%n",asignatura.getNombre(),nota);      }
                }
                
            }
        }
    }
    
    public static void visualizarProfesores (ObjectSet resultado) {
        
        C_Profesor profesor;
        C_Asignatura asignatura;
        
        
        if(resultado.isEmpty())
            System.out.println("\n\n --- No hay datos que mostrar --- \n\n");
        
        else
        {
        
            while(resultado.hasNext())
            {
                profesor=(C_Profesor)resultado.next();
                System.out.println("\n------------------------------------------");
                System.out.println("       DNI         NOMBRE      TITULACION     ");
                System.out.printf("%12s%12s%14s%n",profesor.getDni(),profesor.getNombre(),profesor.getTitulacion());
                
                Iterator asignaturas=profesor.getAsignaturas().iterator();
                System.out.println("\n          - Asignaturas del profesor: - ");
                while(asignaturas.hasNext())
                {
                    asignatura=(C_Asignatura) asignaturas.next();
                    System.out.printf("         %15s%n",asignatura.getNombre());
                }
                
            }
        }
         
    }
    
    public static void visualizarAsignaturas (ObjectSet resultado) {
        
        C_Asignatura asignatura;
        
        
        if(resultado.isEmpty())
            System.out.println("\n\n --- No hay datos que mostrar --- \n\n");
        
        else
        {
            System.out.println("-----------------------");

            while(resultado.hasNext())
            {
                asignatura=(C_Asignatura)resultado.next();
                System.out.printf("%12s%n",asignatura.getNombre() );
            }

            System.out.println("-----------------------");
        }
         
    }
    
    public static void verAlumno (C_Alumno alumno) {
        
        C_Asignatura asignatura;
        Integer nota;
        
        
        System.out.println("-----------------------------------------");
        System.out.println("       DNI         NOMBRE       ");
        System.out.printf("%12s   %11s%n",alumno.getDni(),alumno.getNombre());

        Iterator asignaturas=alumno.getAsignaturas().iterator();
        Iterator notas=alumno.getNotas().iterator();
        System.out.println("\n          - Asignaturas del alumno: - ");
        System.out.println("            ASIGNATURA         NOTA     ");
        while(asignaturas.hasNext())
        {
            asignatura=(C_Asignatura) asignaturas.next(); if(asignatura==null) System.out.println("mal"); else{
            nota=(Integer)notas.next();
            System.out.printf("         %15s%15s%n",asignatura.getNombre(),nota);      }
        }
         
    }
    
    public static void verProfesor (C_Profesor profesor) {
        
        C_Asignatura asignatura;
        Integer nota;
        
        
        System.out.println("\n------------------------------------------");
        System.out.println("       DNI         NOMBRE      TITULACION     ");
        System.out.printf("%12s%12s%14s%n",profesor.getDni(),profesor.getNombre(),profesor.getTitulacion());

        Iterator asignaturas=profesor.getAsignaturas().iterator();
        System.out.println("\n          - Asignaturas del profesor: - ");
        while(asignaturas.hasNext())
        {
            asignatura=(C_Asignatura) asignaturas.next();
            System.out.printf("         %15s%n",asignatura.getNombre());
        }
         
    }
    
    public static void verAsignatura (C_Asignatura asignatura) {
        
        
        System.out.println("\n----------------------------------------");
        System.out.println("       Nombre         Profesor           ");
        System.out.printf("%12s%12s%n",asignatura.getNombre(),asignatura.getProfesor().getNombre() );

         
    }
    
    

}
