package funciones;
import Clases.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.BufferedReader;
import java.io.IOException;


// @author Miguel

public class Asociar {
    
    
    public static void asociarAsignaturas (ObjectContainer baseDatos, C_Persona persona, BufferedReader leer) throws IOException {
        
        C_Asignatura asignatura;
        ObjectSet resultado;
        String nombre;
        byte op=1;
        
        
        while(op!=0){
            System.out.println("Asociar Asignaturas:"
                    + "\n1.Añadir asignatura"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());
            
            if(op==1){
                
                Consultas.verAsignaturas(baseDatos);
                
                System.out.println("Introducir nómbre de la asignatura a asociar:");
                nombre=leer.readLine();
                resultado=baseDatos.queryByExample(new C_Asignatura(nombre));

                if(!resultado.isEmpty() && !nombre.isEmpty()){

                    asignatura=(C_Asignatura)resultado.next();
                    if( persona instanceof C_Alumno && !persona.getAsignaturas().contains(asignatura) ){
                        persona.getAsignaturas().add(asignatura);
                        System.out.println("\n - Asignatura Asociada - \n");
                        
                    }else if ( persona instanceof C_Profesor && !persona.getAsignaturas().contains(asignatura)  ){
                        
                        if(asignatura.getProfesor()==null) {
                            persona.getAsignaturas().add(asignatura);
                            asignatura.setProfesor(((C_Profesor)persona));
                            System.out.println("\n - Asignatura Asociada - \n");
                        }
                        else
                            System.out.println("\n - Esta asignatura ya tiene un profesor asociado - \n");
                        
                    }else
                        System.out.println("\n - Esta asignatura ya se encuentra asociada - \n");

                }else
                    System.out.println("\n - Asignatura no encontrada - \n");

            }
        }
    }
    
    public static void asociarProfesor (ObjectContainer baseDatos, C_Asignatura asignatura, BufferedReader leer) throws IOException {
        
        C_Profesor profesor;
        ObjectSet resultado;
        String dni;
        byte op;
        
        
        System.out.println("¿Desea asociar un profesor nuevo o uno existente?"
                + "\n1.Nuevo"
                + "\n2.Existente"
                + "\n0.Finalizar");
        op=Byte.parseByte(leer.readLine());

        switch(op){
            case 1:
            profesor=(C_Profesor)Altas.nuevoProfesor(leer);
            
            if(baseDatos.queryByExample(new C_Profesor(profesor.getDni(), null, null)).isEmpty()){

                profesor.getAsignaturas().add(asignatura);
                asignatura.setProfesor(profesor);
                System.out.println("\n - Profesor Asociado - \n");

            }else
                System.out.println("\n - Profesor ya Existente - \n");
            break;
            case 2:
                System.out.println("Introducir dni del profesor a asociar:");
                dni=leer.readLine();
                resultado=baseDatos.queryByExample(new C_Profesor(dni, null,null));

                if(!resultado.isEmpty() && !dni.isEmpty()){

                    profesor=(C_Profesor)resultado.next();
                    if(!profesor.getAsignaturas().contains(asignatura) ){
                        if(asignatura.getProfesor()==null) 
                        {
                            profesor.getAsignaturas().add(asignatura);
                            asignatura.setProfesor(profesor);
                            System.out.println("\n - Profesor Asociado - \n");
                        }

                    }else
                        System.out.println("\n - Esta asignatura ya se encuentra asociada a este profesor - \n");

                }else
                    System.out.println("\n - Profesor no encontrado - \n");
                break;

        }

    }
    
    
    public static void asociarNotas (C_Alumno alumno, BufferedReader leer) throws IOException {
        
        Integer nota;
        
        for(int i=0;i<alumno.getAsignaturas().size();i++){
            
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
