package alumnosasignaturasmiguel;
import Clases.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import funciones.*;


// @author Miguel
 
public class AlumnosAsignaturasMiguel {

    
    public static void main(String[] args) {
        
        
        BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(C_Alumno.class).cascadeOnUpdate(true);
        config.common().objectClass(C_Profesor.class).cascadeOnUpdate(true);
        config.common().objectClass(C_Asignatura.class).cascadeOnUpdate(true);
        ObjectContainer baseDatos=Db4oEmbedded.openFile(config, "BBDD_Alumnos");
        byte op=0;
        
        do{
            try{
                op=Menu.menuPrincipal(leer);

                switch(op) {
                    case 1:
                        Altas.altas(baseDatos, leer);
                        break;
                    case 2:
                        Bajas.bajas(baseDatos, leer);
                        break;
                    case 3:
                        Modificar.modificar(baseDatos, leer);
                        break;
                    case 4:
                        Consultas.consultas(baseDatos, leer);
                        break;
                    case 0:
                        System.out.println("--- FIN DEL PROGRAMA ---");
                        baseDatos.close();
                        System.exit(0);
                        break;
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
                }
            
            }while(op!=0);
        
    }
}
