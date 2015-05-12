/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.io.FileReader;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.EngineStateError;
import javax.speech.recognition.*;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.textarea.JEditTextArea;

public class ReconocimientoVoz extends ResultAdapter {

    private static Recognizer recognizer;
    private static View view;
    private boolean escribir = false;
    private static ReconocimientoVoz recVoz = null;

    public void iniciarEscucha() {
        escribir = true;
    }

    public void pausarEscucha() {
        escribir = false;
    }

    public void detenerEscucha() {
        try {
            recognizer.deallocate();
        } catch (EngineException ex) {
        } catch (EngineStateError ex) {
        }
    }

    /**
     * Metodo que se utiliza cada vez que se produce un evento de sonido
     * @param re 
     */
    
    @Override
    public void resultAccepted(ResultEvent re) {
        
        try {
            Result res = (Result) (re.getSource()); //El resultado de lo escuhcado
            ResultToken tokens[] = res.getBestTokens(); //La cadena que mejor se asocia a lo dicho
//            JEditTextArea editor = ReconocimientoVoz.view.getEditPane().getTextArea(); // El textArea del buffer actual en jEdit
            int posicionCursor,posiDes,largoUltimaCad=0; // Representa la posicion del cursor en el textArea 
            String token = ""; // Representa a un token de la cadena escuchada 
            String cadena = "";// Cadena "limpia", compuesta por todos los tokens escuchados y escritos. Esta cadena se añadirá al texto
            String texto = null; // Representa a todo el texto en el textArea
            boolean flag=true;
            
            
            //Se ingresa cuando la persona activa el reconocimiento de voz
            if (escribir) {
                for (int i = 0; i < tokens.length; i++) {
                    token = tokens[i].getSpokenText();

                    if (token.contains("motor")) {
                        if (token.contains("motora")) {
                            token = "MOTOR_A";
                        } else {
                            if (token.contains("motorbe")) {
                                token = "MOTOR_B";
                            } else {
                                token = "MOTOR_C";
                            }
                        }

                    }
                    //Cuando se pronuncie la palabra des-hacer
                    
                    if(token.equals("igual")) token = "=";
                    if(token.equals("comillas")) token = "\"";
                    //==============
                    if(token.equals("summa")) token = "+";
                    if(token.equals("menos")) token = "-";
                    if(token.equals("por")) token = "*";
                    if(token.equals("dividir")) token = "/";
                    if(token.equals("igualque")) token = "==";
                    if(token.equals("menorestricto")) token = "<";
                    if(token.equals("mayorestricto")) token = ">";
                    if(token.equals("menoroigual")) token = "<=";
                    if(token.equals("mayoroigual")) token = ">=";
                    //if(token.equals("punto")) token = ".";
                    
                                        //=============
                    
                    if(!token.equals("ar")){
                    
                    
                    if (token.equals("nuevalinea")||token.equals("tabular")) {
                        
                        if(token.equals("nuevalinea")){
                            cadena += "\n";
                        }else{
                            cadena += "\t";
                        }
                    
                    } else { // nombre de variables con numero ej: var1
                        if(token.equals("var") || token.equals("cont") || token.equals("contador") || token.equals("incremento")) cadena += token;
                        else {
                            cadena += token + " ";
                           
                        }
                    }   
                    
                    }//Fin comprobador ruido
                }
                 cadena = cadena.replaceAll(" punto ", ".");

                //            recognizer.suspend();
//                texto = view.getEditPane().getTextArea().getText() + cadena;
////                view.getEditPane().getTextArea().getCaretLine();
////                view.getEditPane().getTextArea().getCaretPosition();
//                view.getEditPane().getTextArea().setText(texto);
                JEditTextArea editor = view.getEditPane().getTextArea();
                int caretPosition = editor.getCaretPosition();
                if (editor.getSelectedText() == null) {
                    texto = editor.getText().substring(0, caretPosition) + cadena + editor.getText().substring(caretPosition, editor.getText().length());
                } else {
                    texto = editor.getText().substring(0, editor.getSelection()[0].getStart()) + cadena + editor.getText().substring(editor.getSelection()[0].getEnd(), editor.getText().length());
                }
                editor.setText(texto);
                editor.setCaretPosition(caretPosition + cadena.length());
               
            }
//            recognizer.resume();
        } catch (Exception ex) {
            System.out.println("Ha ocurrido algo inesperado " + ex);
        }
    }

    private ReconocimientoVoz(View view, String rutaGram) {
        try {
         
            ReconocimientoVoz.view = view; //Se setea el valor de la vista a la vista del reconocimiento de voz

            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT)); // Se crea el reconocedor con el idioma local
            recognizer.allocate(); // Empieza la escucha
            FileReader grammar1 = new FileReader(rutaGram);// Se crea el archivo a leer 
            RuleGrammar rg = recognizer.loadJSGF(grammar1); // Se crea rg el encargado de reconocer las reglas definidas en la gramatica
            rg.setEnabled(true);

            recognizer.addResultListener(this);

            recognizer.commitChanges();
            recognizer.requestFocus();
            recognizer.resume();
        } catch (Exception e) {
//            System.out.println("Exception en " + e.toString());
            System.exit(0);
        }
    }

    public static ReconocimientoVoz getRecVoz(View view, String rutaGram) {

        if (recVoz == null) {
            recVoz = new ReconocimientoVoz(view,rutaGram);
        }
        return recVoz;
    }
}
