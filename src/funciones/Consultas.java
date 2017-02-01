package funciones;
import Clases.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.io.BufferedReader;
import java.io.IOException;


// @author Miguel

public class Consultas {
    
    public static void consultas (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        byte op=1;
        

        while(op!=0){
            op=Menu.menuConsultas(leer);
            switch(op){
                case 1:
                    consultaAlumno(baseDatos, leer);
                    break;
                case 2:
                    consultaProfesor(baseDatos, leer);
                    break;
                case 3:
                    verAlumnos(baseDatos);
                    verProfesores(baseDatos);
                    verAsignaturas(baseDatos);
                    break;
            }
        }
    }
    
    public static void consultaAlumno (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        Query consulta=baseDatos.query();
        ObjectSet resultado;
        String dni;
        
        System.out.println("Introducir dni del alumno a buscar:");
        dni=leer.readLine();
        consulta.constrain(C_Alumno.class);
        consulta.descend("dni").constrain(dni);
        resultado=consulta.execute();
        Visualizar.visualizarAlumnos(resultado);
        
    }
    
    public static void consultaProfesor (ObjectContainer baseDatos, BufferedReader leer) throws IOException {
        
        Query consulta=baseDatos.query();
        ObjectSet resultado;
        String dni;
        
        System.out.println("Introducir dni del Profesor a buscar:");
        dni=leer.readLine();
        consulta.constrain(C_Profesor.class);
        consulta.descend("dni").constrain(dni);
        resultado=consulta.execute();
        Visualizar.visualizarProfesores(resultado);
        
    }
    
    
    public static void verAlumnos(ObjectContainer baseDatos) {
        
        ObjectSet resultado;
        
        System.out.println("\n\n --- ALUMNOS REGISTRADOS");
        C_Alumno alumno=new C_Alumno();
        resultado=baseDatos.queryByExample(alumno);
        Visualizar.visualizarAlumnos(resultado);
        
    }
    
    public static void verProfesores (ObjectContainer baseDatos) {
        
        ObjectSet resultado;
        
        System.out.println(" \n\n--- PROFESORES REGISTRADOS");
        C_Profesor profesor=new C_Profesor();
        resultado=baseDatos.queryByExample(profesor);
        Visualizar.visualizarProfesores(resultado);
        
    }
    
    public static void verAsignaturas (ObjectContainer baseDatos) {
        
        ObjectSet resultado;
        
        System.out.println(" \n\n --- ASIGNATURAS REGISTRADAS");
        C_Asignatura asignatura=new C_Asignatura();
        resultado=baseDatos.queryByExample(asignatura);
        Visualizar.visualizarAsignaturas(resultado);
        
    }

}
