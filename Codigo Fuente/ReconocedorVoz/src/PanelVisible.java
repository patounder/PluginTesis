
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Pato
 */
public class PanelVisible extends JPanel {

   public static final String LABEL_BIENVENIDA = "Bienvenido";
    
    /**
     * TEXTOS: Los texto asociados a cada boton
     */
    public static final String TEX_INICIO = "Este complemento permite la codificacion"
            + " del codigo fuente via voz, sin embargo otras tareas, como gestion de"
            + " codigo fuente o compilar, se realizan manualmente.\n\n A continuacion"
            + " es posible visualizar las posibles instrucciones de realizar con la"
            + " nueva aplicación y su respectivo lenguaje. En la barra a la derecha de"
            + " la ventana, se ubican distintos botones los cuales indican la sintaxis"
            + " y de cada instruccion con un ejemplo de ayuda";
    private String TEX_AV = "Avanzar:\n-----------\nSintaxis :\n\" avanzar MOTOR* numero* segundos \""
            + "\n\nEjemplo :\navanzar MOTOR_B 5 segundos\n\n\n\nMOTOR* : Los posibles valores para "
            + "motor son \" MOTOR_A \", \" MOTOR_B \" o \" MOTOR_C \"\n\nnumero* : se entiende por numero"
            + " a cualquier valor perteneciente a los numeros entero";
    private String TEX_REV = "Retroceder:\n-------------\nSintaxis:\n\" retroceder MOTOR* numero* segundos \""
            + "\n\nEjemplo :\nretroceder MOTOR_A 5 segundos\n\n\n\nMOTOR* : Los posibles valores para "
            + "motor son \" MOTOR_A \", \" MOTOR_B \" o \" MOTOR_C \"\n\nnumero* : se entiende por numero"
            + " a cualquier valor perteneciente a los numeros entero";
    private String TEX_GIRAR = "Girar:\n------------\nSintaxis:\n\" girar direccion* MOTOR* \""
            + "\n\nEjemplo:\n girar adelante MOTOR_A\n\n\ndireccion* : Los  posibles valores para direccion"
            + " son \" adelante \" o \" atras \"\n\nMOTOR* : Los posibles valores para motor son \" MOTOR_A \","
            + " \" MOTOR_B \" o \" MOTOR_C \"";
    private String TEX_APAGAR = "Apagar:\n-------\nSintaxis:\n\" apagar MOTOR* "
            + "\"\n\nEjemplo:\napagar MOTOR_A\n\n\nMOTOR* : Los posibles valores para motor son \" MOTOR_A \","
            + " \" MOTOR_B \" o \" MOTOR_C \"";
    private String TEX_IMPRIMIR = "Imprimir:\n-------\nSintaxis:\n \" imprimir \" texto* \" \""
            + "\n\nEjemplo :\n imprimir \" hola mundo \"\n\n\ntexto* : Se entiende por texto cualquier combinacion"
            + " de palabaras y numero que se deseen imprimir por pantalla. Para el reconocimiento de voz se reduce "
            + "el numero de palabras a las definidas por la gramatica de voz";
    private String TEX_SI = "Si :\n----\nSintaxis :\n\" si condicion* entonces\n...\n    "
            + "cuerpo**\n...\nfin si \"\n\nEjemplo :\nsi var1 < var2 entonces\n "
            + " avanzar MOTOR_B 5 segundos\n  girar atras MOTOR_A\nfin si\n\n\ncondicion* : Se entiende por "
            + "condicion a una expresion que realaciona uno o mas operandos atraves de operadores matematicos, "
            + "logicos o relacionales";
    private String TEX_SINO = "Si/Sino:\n-----------\nSintaxis : \n \" si condicion* entonces\n...\n  "
            + " cuerpo** \n...\nSino\n...\n cuerpo**\n...\nfin si \"\n\nEjemplo :\nsi var3 < var4 entonces\n   "
            + "avanzar MOTOR_B 5 segundos\nsino\n  retroceder MOTOR_B 5 segundos\nfin si\n\n\ncondicion* : Se entiende "
            + "por condicion a una expresion que realaciona uno o mas operandos atraves de operadores matematicos, logicos"
            + " o relacionales";
    private String TEX_REPETIR = "Repetir:\n-------\nSintaxis :\n\" repetir numero* veces \n"
            + "...\n cuerpo*\n...\nfin repetir \"\n\nEjemplo :\nrepetir 7 veces"
            + "\n   avanzar MOTOR_B MOTOR_A 5 segundos\n    girar adelante MOTOR_A\nfin repetir\n\n\nnumero* : se entiende"
            + " por numero a cualquier valor perteneciente a los numeros entero";
    private String TEX_MIENTRAS = "Mientras:\n-------\nSintaxis :\n\"mientras condicion* entonces"
            + "\n...\n cuerpo*\n...\nfin mientras\"\n\nEjemplo :\nmientras var1 < var3"
            + " entonces\n  avanzar MOTOR_B MOTOR_A 5 segundos\n   girar adelante MOTOR_A\nfin mientras\n\n\ncondicion* : "
            + "Se entiende por condicion a una expresion que realaciona uno o mas operandos atraves de operadores matematicos, "
            + "logicos o relacionales";
    private String TEX_VARIABLES = "Declaracion de variables:\n---------------------------------"
            + "\nSintaxis :\n\" tipo de dato* identificador \"\n\n"
            + "Ejemplo :\n real varReal\n\n\ntipo de dato* : Los posibles tipo de datos para las variables"
            + " son los tipos \" entero \" y \" real \"";
    
    private String TEX_CONSTANTES = "Declaracion de constantes:\n---------------------------------"
            + "\nSinatxis :\n\" tipo de dato* identificador valor* \"\n\n"
            + "Ejemplo :\n entero const1 12\n\n\ntipo de dato* : Los posibles tipo de datos para las "
            + "constantes son los tipos \" entero \" y \" real \"";
    
    private String TEX_ASIGN = "Asignacion:\n-------------\nSintaxis :\n\" identificador = (identificador"
            + " | numero | expresion* ) \"\n\nEjemplo :\n var1 = var2 + 12\n\n\nexpresion* = En este caso se"
            + " entiende como expresion a las posibles combinaciones de operandos relacionados atraves de operadores matematicos";
    
    private String TIPO_DE_DATO = "tipo de dato* : Los posibles tipo de datos para las variables son los tipos \" entero \" y \" real \"";
    private String VALOR    =   "valor* : Los posibles valores deben ser acorde al tipo de dato definido";
    private String CUERPO   = "cuerpo* : Se entiende por cuerpo cualquier instruccion de movimiento, salida de texto, condicional, ciclos, asignacion o declaracion de variables";
    private String CONDICION = "condicion* : Se entiende por condicion a una expresion que relaciona uno o mas operandos atraves de operadores matematicos, logicos o relacionales";
    private String NUMERO   = "numero* : se entiende por numero a cualquier valor perteneciente a los numeros entero o real";
    private String DIRECCION ="direccion* : Los  posibles valores para direccion son \" adelante \" o \" atras \" ";
    private String MOTOR    =   "MOTOR* : Los posibles valores para motor son \" MOTOR_A \", \" MOTOR_B \" o \" MOTOR_C \"";
    private String TEXTO    = "texto* : Se entiende por texto cualquier combinacion de palabaras y numero que se deseen imprimir por pantalla. Para el reconocimiento de voz se reduce el numero de palabras a las definidas por la gramatica de voz";
    private String EXPRESION = "expresion* = En este caso se etiende como expresion a las posibles combinaciones de operandos relacionados atraves de operadores matematicos";
    /**
     * SECCIONES: Nombre de secciones
     */
    private JLabel seccionEspacio = new JLabel("    ");//Lo utilizo para saltar un espacio
    private JLabel seccionDeclaraciones = new JLabel("Declaraciones :");
    private JLabel seccionMovimientos = new JLabel("Movimientos :");
    private JLabel seccionSalidaTexto = new JLabel("Salida Texto :");
    private JLabel seccionCondicional = new JLabel("Condicionales :");
    private JLabel seccionCiclos = new JLabel("Ciclos :");
    private JLabel seccionAsign = new JLabel("Asignacion :");
    private final Dimension preferida = new Dimension(400, 600);
    private final Font fontTitulo = new Font(null, Font.BOLD, 25);
    private JLabel labTitulo;
    private JTextArea texInfo;
    
    /**
     * BOTONES: Botones de informacion
     */
    private JButton butInicio;
    private JButton butVariables = new JButton("Variables");
    private JButton butConstantes = new JButton("Constantes");
    private JButton butAvanzar = new JButton("Avanzar");
    private JButton butRetroceder = new JButton("Retroceder");
    private JButton butGirar = new JButton("Girar");
    private JButton butApagar = new JButton("Apagar");
    private JButton butImprimir = new JButton("Imprimir");
    private JButton butSi = new JButton("Si");
    private JButton butSino = new JButton("Sino");
    private JButton butRepetir = new JButton("Repetir");
    private JButton butMientras = new JButton("Mientras");
    private JButton butAsignacion = new JButton("Asignacion");
    
    private ImageIcon imgInicio, iconoEscala;

    public PanelVisible() {
        super(new BorderLayout(10, 10));

        JPanel toolBar = new JPanel(new java.awt.GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        imgInicio = new ImageIcon(getClass().getResource("/Imagenes/icon_home.png"));
        iconoEscala = new ImageIcon(imgInicio.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_DEFAULT));
        
        // Se crean, inician y añaden los botones a la barra
        butInicio = new JButton(iconoEscala);
        alignTextBut(); // Se alinean el tecto de los botones a la izquierda
        
        toolBar.add(butInicio, constraints);
        constraints.gridy = 1;
        toolBar.add(seccionDeclaraciones, constraints);
        constraints.gridy = 2;
        toolBar.add(butVariables, constraints);
        constraints.gridy = 3;
        toolBar.add(butConstantes, constraints);
        constraints.gridy = 4;
        toolBar.add(seccionMovimientos, constraints);     
        constraints.gridy = 5;
        toolBar.add(butAvanzar, constraints);
        constraints.gridy = 6;
        toolBar.add(butRetroceder, constraints);
        constraints.gridy = 7;
        toolBar.add(butGirar, constraints);
        constraints.gridy = 8;
        toolBar.add(butApagar, constraints);
        constraints.gridy = 9;
        toolBar.add(seccionSalidaTexto, constraints);
        constraints.gridy = 10;
        toolBar.add(butImprimir, constraints);
        constraints.gridy = 11;
        toolBar.add(seccionCondicional, constraints);
        constraints.gridy = 12;
        toolBar.add(butSi, constraints);
        constraints.gridy = 13;
        toolBar.add(butSino, constraints);
        constraints.gridy = 14;
        toolBar.add(seccionCiclos, constraints);
        constraints.gridy = 15;
        toolBar.add(butRepetir, constraints);
        constraints.gridy = 16; 
        toolBar.add(butMientras, constraints);
        constraints.gridy = 17; 
        toolBar.add(seccionAsign, constraints);
        constraints.gridy = 18; 
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        toolBar.add(butAsignacion, constraints);
        
        // Se añade la barra al --- del panel
        add(toolBar, BorderLayout.WEST);

        // Se establece la accion de cada boton
        butInicio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_INICIO);
            }
        });        
        butAvanzar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_AV);
            }
        });
        butRetroceder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_REV);
            }
        });
        butGirar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_GIRAR);
            }
        });
        butApagar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_APAGAR);
            }
        });
        butImprimir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_IMPRIMIR);
            }
        });
        butSi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_SI);
            }
        });
        butSino.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_SINO);
            }
        });
        butRepetir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_REPETIR);
            }
        });
        butMientras.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_MIENTRAS);
            }
        });
        butVariables.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_VARIABLES);
            }
        });
        
        butConstantes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_CONSTANTES);
            }
        });
        
        butAsignacion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texInfo.setText(TEX_ASIGN);
            }
        });
        // FIN CODIGO NUEVO
        labTitulo = new JLabel(LABEL_BIENVENIDA);
        labTitulo.setFont(fontTitulo);
        texInfo = new JTextArea();
        texInfo.setText(TEX_INICIO);
        texInfo.setBorder(null);
        texInfo.setBackground(null);
        texInfo.setEditable(false);
        texInfo.setLineWrap(true);
        add(labTitulo, BorderLayout.NORTH);
        add(new JScrollPane(texInfo), BorderLayout.CENTER);

        setPreferredSize(preferida);
    }
    
    public final void alignTextBut(){
        butInicio.setHorizontalAlignment(JButton.LEFT);
        butVariables.setHorizontalAlignment(JButton.LEFT);
        butConstantes.setHorizontalAlignment(JButton.LEFT);
        butAvanzar.setHorizontalAlignment(JButton.LEFT);
        butRetroceder.setHorizontalAlignment(JButton.LEFT);
        butGirar.setHorizontalAlignment(JButton.LEFT);
        butApagar.setHorizontalAlignment(JButton.LEFT);
        butImprimir.setHorizontalAlignment(JButton.LEFT);
        butSi.setHorizontalAlignment(JButton.LEFT);
        butSi.setHorizontalAlignment(JButton.LEFT);
        butSino.setHorizontalAlignment(JButton.LEFT);
        butRepetir.setHorizontalAlignment(JButton.LEFT);
        butMientras.setHorizontalAlignment(JButton.LEFT);
        butAsignacion.setHorizontalAlignment(JButton.LEFT);
    }
    
    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("Plugin Reconocedor Voz");
        frame.add(new PanelVisible());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}