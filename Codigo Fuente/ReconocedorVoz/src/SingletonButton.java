
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josue
 */
public class SingletonButton {
    
    private static SingletonButton singletonButton = null;
    
    private ReconocedorVoz superior; // Sera el padre al cual estaran asociados los botones
    
    private JButton butCompilar; // Boton compilar
    private JButton butGrabar; // Boton grabar
    private JButton butEnviar; // Boton enviar
    
    private ImageIcon imgPausado; // Icono del boton grabar cuando no esta grabando
    private ImageIcon imgGrabando; // Icono del boton grabar cuando esta grabando
    private ImageIcon imgCompilar; // Icono  del boton compilar
    private ImageIcon imgEnviar; // Icono del boton enviar
    
    //Imagen de iconos a escala 
    private ImageIcon imgPausadoEscala; // Icono escala del boton grabar cuando no esta grabando 
    private ImageIcon imgGrabandoEscala; // Icono escala del boton grabar cuando esta grabando 
    private ImageIcon imgCompEscala; // Icono escala del boton compilar
    private ImageIcon imgEnviarEscala; // Icono escala del boton enviar
    
    
    private SingletonButton(ReconocedorVoz superior) {
        
        this.superior = superior;
        
        imgPausado = new ImageIcon(getClass().getResource("/Imagenes/Pausado.png")); // Se crea el icono del boton grabar cuando no esta grabando
        imgPausadoEscala = new ImageIcon(imgPausado.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT)); // Se crea icono escala del boton grabar cuando no esta grabando
               
        imgGrabando = new ImageIcon(getClass().getResource("/Imagenes/Grabando.png")); // Se crea el icono del boton grabar cuando esta grabando
        imgGrabandoEscala = new ImageIcon(imgGrabando.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT)); // Se crea el icono escala del boton grabar cuando esta grabando
        
        imgCompilar = new ImageIcon(getClass().getResource("/Imagenes/Compilar.png")); // Se crea el icono del boton compilar
        imgCompEscala = new ImageIcon(imgCompilar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT)); // Se crea el icono escala del boton compilar
        
        imgEnviar = new ImageIcon(getClass().getResource("/Imagenes/Enviar.png")); // Se crea el icono del boton enviar
        imgEnviarEscala = new ImageIcon(imgEnviar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT)); // Se crea el icono escala del boton enviar
                
        
        // Se instancian los botones con sus respectivos iconos en escala
        butGrabar = new JButton(imgPausadoEscala);
        butCompilar = new JButton(imgCompEscala);
        butEnviar = new JButton(imgEnviarEscala);
        butEnviar.setVisible(false); // Cuanoo se inicia el plugin no estara visible el boton enviar porque aun no se compila
        
         //Mensaje al posicionar el mouse sobre el boton
        butGrabar.setToolTipText("Iniciar Codificacion Via Voz");
        butCompilar.setToolTipText("Compilar Codigo Under");
        butEnviar.setToolTipText("Enviar Codigo a Robot");
        
        //Se eliminan los bordes
        butGrabar.setBorderPainted(false);
        butCompilar.setBorderPainted(false);
        butEnviar.setBorderPainted(false);
        
        // Se señala la accion
        butCompilar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (SingletonButton.this.superior.getView().getBuffer().getName().contains("Untitled")) {
//                    javax.swing.JOptionPane.showMessageDialog(ReconocedorVoz.this.view, "Debe guardar el docuemento");
                    SingletonButton.this.superior.getView().getBuffer().saveAs(SingletonButton.this.superior.getView(), true);

                } else {
                    SingletonButton.this.superior.getView().getBuffer().save(SingletonButton.this.superior.getView(), SingletonButton.this.superior.getView().getBuffer().getPath(), true);
                    SingletonButton.this.superior.compilar();
                    //compilador = Compilar.getCompilador();

                }
            }
        });
        butEnviar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SingletonButton.this.superior.enviarViaUSB();
            }
        });
        butGrabar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SingletonButton.this.superior.grabar();
            }
        });
        
        // Se establecen los bordes
//        butCompilar.setBackground(superior.getView().getToolBar().getBackground());
        butEnviar.setBounds(superior.getView().getToolBar().getComponent(0).getBounds());
    }
    
    /**
     * Retorna el objeto singletonButton, el cual es unico y contiene a los tres
     * botones y sus respectivas acciones
     * @param superior
     * @return singletonButton
     */
    public static SingletonButton getInstance(ReconocedorVoz superior) {
        if(singletonButton==null)
            singletonButton = new SingletonButton(superior);
        return singletonButton;
    }
    /**
     * Retorna el botonCompilar
     * @return butCompilar
     */
    public JButton getButCompilar() {
        return butCompilar;
    }
    /**
     * Retorna el boton grabar
     * @return butGrabar
     */
    public JButton getButGrabar() {
        return butGrabar;
    }
    /**
     * Retorna el boton enviar
     * @return butEnviar
     */
    public JButton getButEnviar() {
        return butEnviar;
    }
    /**
     * Cambia el icono del boton grabar segun si esta grabando o no
     * @param cambia, true si se cambia a grabar, y false si se cambia a pausar
     */
    public void changeIconButGrabar(boolean cambia){
        if(cambia){
            butGrabar.setIcon(imgGrabandoEscala);
            butGrabar.setToolTipText("Pausar Codificacion Via Voz");
        }else{
            butGrabar.setIcon(imgPausadoEscala);
            butGrabar.setToolTipText("Iniciar Codificacion Via Voz");
        }
    }
    
}
