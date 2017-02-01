package funciones;
import java.io.*;
import Clases.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


// @author Miguel

public class Modificar {
    
    public static void modificar (ObjectContainer baseDatos,BufferedReader leer) throws IOException {
        
        ObjectSet resultado;
        String dni, nombre;
        C_Alumno alumno;
        C_Profesor profesor;
        C_Asignatura asignatura;
        byte op=1;
        
        
        while(op!=0){
            op=Menu.menuModificar(leer);
            switch(op){
                case 1:
                    System.out.println("Introducir dni del Alumno a Modificar:");
                    dni=leer.readLine();
                    resultado=baseDatos.queryByExample(new C_Alumno (dni, null));
                    
                    if(!resultado.isEmpty() && !dni.isEmpty())
                    {
                        alumno=(C_Alumno)resultado.next();
                        modificarAlumno(baseDatos, alumno, leer);
                        baseDatos.deactivate(alumno, 2);
                    }
                    else
                        System.out.println("\n Alumno no encontrado \n");
                    break;
                case 2:
                    System.out.println("Introducir dni del Profesor a modificar:");
                    dni=leer.readLine();
                    resultado=baseDatos.queryByExample(new C_Profesor (dni, null, null));

                    if(!resultado.isEmpty() && !dni.isEmpty())
                    {
                        profesor=(C_Profesor)resultado.next();
                        modificarProfesor(baseDatos, profesor, leer);
                        baseDatos.deactivate(profesor, 2);
                    }
                    else
                        System.out.println("\n Profesor no encontrado \n");
                    break;
                case 3:
                    System.out.println("Introducir nombre de la Asignatura a modificar:");
                    nombre=leer.readLine();
                    resultado=baseDatos.queryByExample(new C_Asignatura (nombre));

                    if(!resultado.isEmpty() && !nombre.isEmpty())
                    {
                        asignatura=(C_Asignatura)resultado.next();
                        modificarAsignatura(baseDatos, asignatura, leer);
                        baseDatos.deactivate(asignatura,1);
                    }
                    else
                        System.out.println("\n Alumno no encontrado \n");
                    break;
            }
        }
    }
    
    public static void modificarAlumno (ObjectContainer baseDatos, C_Alumno alumno, BufferedReader leer) throws IOException {
        
        byte op=1;
        
        
        while(op!=0){
            
            Visualizar.verAlumno(alumno);
            
            System.out.println("\n¿Qué desea modificar?"
                    + "\n1.Asignaturas"
                    + "\n2.Notas"
                    + "\n3.Guardar Cambios"
                    + "\n0.Cancelar");
            op=Byte.parseByte(leer.readLine());
            switch(op){
                case 1:
                    modificarAsignaturas(baseDatos, alumno, leer);
                    break;
                case 2:
                    modificarNotas(alumno, leer);
                    break;
                case 3:
                    baseDatos.store(alumno);
                    baseDatos.commit();
                    System.out.println("\n - Alumno modificado - \n");
                    op=0;
                    break;
                case 0:
                    System.out.println("\n - Operación Cancelada - \n");
                    break;
            }
        }
    }
    
    public static void modificarProfesor (ObjectContainer baseDatos, C_Profesor profesor, BufferedReader leer) throws IOException {
        
        String titulacion;
        byte op=1;
        
        
        while(op!=0){
            
            Visualizar.verProfesor(profesor);
            
            System.out.println("\n¿Qué desea modificar?"
                    + "\n1.Titulación"
                    + "\n2.Asignaturas"
                    + "\n3.Guardar Cambios"
                    + "\n0.Cancelar");
            op=Byte.parseByte(leer.readLine());
            switch(op){
                case 1:
                    System.out.println("Introducir nueva Titulación:");
                    titulacion=leer.readLine();
                    profesor.setTitulacion(titulacion);
                    break;
                case 2:
                    modificarAsignaturas(baseDatos, profesor, leer);
                    break;
                case 3:
                    baseDatos.store(profesor);
                    baseDatos.commit();
                    System.out.println("\n - Profesor modificado - \n");
                    op=0;
                    break;
                case 0:
                    System.out.println("\n - Operación Cancelada - \n");
                    break;
            }
        }
    }
    
    public static void modificarAsignatura (ObjectContainer baseDatos, C_Asignatura asignatura, BufferedReader leer) throws IOException {
        
        String nombre;
        byte op=1;
        
        
        while(op!=0){
            
            Visualizar.verAsignatura(asignatura);
            
            System.out.println("\n¿Qué desea modificar?"
                    + "\n1.Nombre"
                    + "\n2.Profesor"
                    + "\n3.Cancelar Cambios"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());
            switch(op){
                case 1:
                    System.out.println("Introducir nuevo Nombre:");
                    nombre=leer.readLine();
                    asignatura.setNombre(nombre);
                    break;
                case 2:
                    if(asignatura.getProfesor()!=null)
                    {
                        System.out.println("Esta asignatura ya tiene un profesor asociado, ¿desea sobreescribirlo?"
                                + "\n1.Si"
                                + "\n0.No");
                        if(Byte.parseByte(leer.readLine())==1)
                        {
                            asignatura.setProfesor(null);
                            Asociar.asociarProfesor(baseDatos, asignatura, leer);
                        }
                    }
                    else
                        Asociar.asociarProfesor(baseDatos, asignatura, leer);
                    break;
                case 3:
                    baseDatos.store(asignatura);
                    baseDatos.commit();
                    System.out.println("\n - Asignatura modificada - \n");
                    return;
                case 0:
                    System.out.println("\n - Operación Cancelada - \n");
                    break;
            }
        }
    }
    
    public static void modificarAsignaturas (ObjectContainer baseDatos, C_Persona persona, BufferedReader leer) throws IOException {
        
        C_Asignatura asignatura;
        ObjectSet resultado;
        String nombre;
        byte op=1;
        
        
        while(op!=0){
            System.out.println("\nAsociar Asignaturas:"
                    + "\n1.Añadir asignaturas"
                    + "\n2.Eliminar asignaturas"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());
            
            switch(op){
                case 1:
                    Asociar.asociarAsignaturas(baseDatos,persona, leer);
                    if(persona instanceof C_Alumno)
                        actualizarNotas(baseDatos, (C_Alumno)persona, leer);
                    break;
                case 2:
                    System.out.println("Introducir nombre de la Asignatura a desvincular");
                    nombre=leer.readLine();
                    resultado=baseDatos.queryByExample(new C_Asignatura(nombre));

                    if(!resultado.isEmpty() && !nombre.isEmpty()){
                        
                        asignatura=(C_Asignatura)resultado.next();
                        
                        if(persona instanceof C_Alumno && ((C_Alumno)persona).getAsignaturas().contains(asignatura))
                        {
                            int i=((C_Alumno)persona).getAsignaturas().indexOf(asignatura);
                            ((C_Alumno)persona).getAsignaturas().remove(asignatura);
                            ((C_Alumno)persona).getNotas().remove(i);
                            System.out.println("\n - Asignatura Desvinculada - \n");
                            
                        }else if (persona instanceof C_Profesor && ((C_Profesor)persona).getAsignaturas().contains(asignatura) ){
                            ((C_Profesor)persona).getAsignaturas().remove(asignatura);
                            System.out.println("\n - Asignatura Desvinculada - \n");
                        }
                    }else
                        System.out.println("\n Asignatura no encontrada");
                    break;
            }
        }
    } 
    
    public static void modificarNotas (C_Alumno alumno, BufferedReader leer) throws IOException {
        
        Integer nota;
        String nombre;
        
        System.out.println("Introduzca el nombre de la asignatura a modificar la nota:");
        nombre=leer.readLine();
        
        for(int i=0;i<alumno.getAsignaturas().size();i++){
            
            if( ((C_Asignatura)alumno.getAsignaturas().get(i)).getNombre().compareToIgnoreCase(nombre)==0 )
                try{
                    System.out.println("Introduzca la Nota en "+((C_Asignatura)alumno.getAsignaturas().get(i)).getNombre()+":");
                    nota=Integer.parseInt(leer.readLine());
                    if(nota >= 0 && nota <= 10)
                        alumno.getNotas().add(i, nota);
                    else
                        throw new NumberFormatException();


                }catch(NumberFormatException e){
                    System.out.println("Error, introducir un valor correcto");
                    i--;
                }
        }
    }
    
    
    
    public static void actualizarNotas (ObjectContainer baseDatos, C_Alumno alumno, BufferedReader leer) throws IOException {
        
        Integer nota;
        
        
        for(int i=alumno.getNotas().size();i<alumno.getAsignaturas().size();i++){
            
            try{
                System.out.println("Introduzca la Nota en "+((C_Asignatura)alumno.getAsignaturas().get(i)).getNombre()+":");
                nota=Integer.parseInt(leer.readLine());
                if(nota >= 0 && nota <= 10)
                    alumno.getNotas().add(i, nota);
                else
                    throw new NumberFormatException();
                
                
            }catch(NumberFormatException e){
                System.out.println("Error, introducir un valor correcto");
                i--;
            }
        }
    }
}
