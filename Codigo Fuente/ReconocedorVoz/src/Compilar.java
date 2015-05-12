
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pato
 */
public class Compilar {
   private static final Compilar compilador= new Compilar();
  
   private Compilar(){
   }
   
   public static Compilar getInstance(){
        
        return compilador;
    }

    public String ejecutar(String rutaCom,String rutaFileUnder){
    
        String s;
        String out = "";
        String err;
        String [] comando = new String[2];
     
        try {   // Determinar en que SO estamos
            String so = System.getProperty("os.name");
            
            // Comando para Linux
            if (so.equals("Linux")) {
                comando [0]= "java -jar "+rutaCom+File.separator+"comp.jar "+rutaFileUnder;
            } // Comando para Windows
            else {
                comando [0]= "cmd /c java -jar \""+rutaCom+File.separator+"comp.jar\" \""+rutaFileUnder+"\"";
            }

            // Ejcutamos el comando
            Process p = Runtime.getRuntime().exec(comando[0]);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));

            // Leemos la salida del comando
            while ((s = stdInput.readLine()) != null) {
                out = out + s + "\n";
            }
            
            if (!out.contains("Compilado")) {
                
                err = "Se encontraron los siguientes errores:\n";
                while ((s = stdError.readLine()) != null) {
                    err = err + s + "\n";
                }
                //javax.swing.JOptionPane.showMessageDialog(view, err);
                out = err;
               
            }
            
        } catch (IOException e) {
            System.out.println("Excepcion: ");
            System.exit(-1);
        }
        
        return out;
    }
}
