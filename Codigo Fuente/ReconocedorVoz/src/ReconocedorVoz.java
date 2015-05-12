
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JPanel;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;

public class ReconocedorVoz extends JPanel {

    private View view;  //Objeto al cual estara enlazado el plugin
    private ReconocimientoVoz reconocimientoVoz; // Encargado de reconocer voz y escribir en el TextArea de jdit
    private Compilar compilador;    // Objeto encargado de compilar el codigo  que actualmente esta en el textarea
    private boolean grabando = true; //Se utiliza para gestionar el boton que graba
    
    SingletonButton singletonButton;
    
    private String rutaGlobal,rutaGram;
       
    // {{{ Constructor
    public ReconocedorVoz(View view, String position) {
        super(new BorderLayout());
        this.view = view;

        add(new PanelVisible());

        singletonButton = SingletonButton.getInstance(this);

        view.getToolBar().add(singletonButton.getButGrabar());
        view.getToolBar().add(singletonButton.getButCompilar());
        view.getToolBar().add(singletonButton.getButEnviar());

        rutaGram = jEdit.getSettingsDirectory() + File.separator + "GramaticaVoz.txt";
        reconocimientoVoz = ReconocimientoVoz.getRecVoz(view, rutaGram); // Instancia el reconocedor de voz
        compilador = Compilar.getInstance(); // Instancia el compilador
    }

    public void grabar() {
        //Acciones de grabando
        if (grabando) {
            reconocimientoVoz.iniciarEscucha();
            singletonButton.changeIconButGrabar(grabando);
        } else {
            reconocimientoVoz.pausarEscucha();
            singletonButton.changeIconButGrabar(grabando);
       }
        grabando = !grabando;
    }

    public void compilar() {
        String salida;
        
        String rutaFileUnder = view.getBuffer().getPath();
        String rutaComp = jEdit.getSettingsDirectory();

        salida = compilador.ejecutar(rutaComp, rutaFileUnder);

        if (salida.contains("Compilado") && isConectRobot()) {
            setRutaGlobal(rutaFileUnder);
            singletonButton.getButEnviar().setVisible(true);
            view.getToolBar().repaint();
        } else {
            singletonButton.getButEnviar().setVisible(false);
        }
        javax.swing.JOptionPane.showMessageDialog(view, salida);
    }

    public void enviarViaUSB() {

        String rutaFileNXC;
        rutaFileNXC = getRutaGlobal().replace(".under", ".nxc");
        String s;
        String out = "";
        String err;
        String [] comando = new String[2];
        

        try {   // Determinar en que SO estamos
            String so = System.getProperty("os.name");

            // Comando para Linux
            if (so.equals("Linux")) {
//                comando = "java -jar "+rutaCom+File.separator+"comp.jar "+rutaFileUnder;
            } // Comando para Windows
            else {
                comando[0] = "cmd /c nbc.exe \""+rutaFileNXC + "\" -d";
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

        } catch (IOException e) {
            System.out.println("Excepcion: ");
            System.exit(-1);
        }
    }

    public String getRutaGlobal() {
        return rutaGlobal;
    }

    public void setRutaGlobal(String rutaGlobal) {
        this.rutaGlobal = rutaGlobal;
    }

    public View getView() {
        return view;
    }
    /**
     * Este metodo se ocupa para conocer si hay algun robot conectado via USB
     * (Aun no esta implementado)
     * @return true si hay algun robot NXT conectado
     */
    public boolean isConectRobot(){
               
        return true;
    }
}
