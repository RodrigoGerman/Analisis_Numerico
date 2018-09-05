package controlador.theme2.closedmethods;

import vista.panels.theme2.PanelClosedMethods;
import modelo.theme2.closedmethods.InterpolacionLineal;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
    * La clase PanelInterpolacionLineal es una clase que hereda de PanelClosedMethods debido a que es un panel de métodos cerrados de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Interpolación Lineal, se encarga de crear un panel específico para la visualización
    * del método numérico de Interpolación Lineal.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelInterpolacionLineal extends PanelClosedMethods implements ActionListener{
    /** * Variable tipo privada InterpolacionLineal que representa el método de Interpolación Lineal.*/
    private InterpolacionLineal interpolacion; 

    /** * Método constructor que crea un PanelInterpolacionLineal.*/
    public PanelInterpolacionLineal(){
        super(new Color(145,50,93),"Método de Interpolación Lineal");
        setPanelDatos();
        setInformacion();
        setAllElements();
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    /** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del
    componente que las ocasione.*/
    @Override
    public void actionPerformed(ActionEvent e){
        int r=0;
        double valor_estimacion = 0,valor_a = 0,valor_b = 0; 
        if(e.getSource() == calcular){
            if(getFieldsValids() && getEmptyFields()){
                valor_estimacion = Double.parseDouble(estimacion.getText().trim());
                valor_a = Double.parseDouble(a.getText().trim());
                valor_b = Double.parseDouble(b.getText().trim());
                if(valor_estimacion != 0.0 && valor_estimacion > 0.000000000000001){
                    interpolacion = new InterpolacionLineal((funcion.getText()).trim(),valor_estimacion);
                    if(interpolacion.getConvergencia(valor_a,valor_b)){
                        iteraciones.setClear("    Interacción\t    |\tValor aproximado\t|               Tolerancia\n\n");
                        interpolacion.setAproximacion(valor_a,valor_b);
                        iteraciones.setText(interpolacion.getIteraciones());
                        iteraciones.setText("\nRaiz:  " + String.valueOf(interpolacion.getRaiz()));
                        raiz.setText(" " + String.valueOf(interpolacion.getRaiz()));
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Error. El intervalo no converge introduzca otro por favor."
                        ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null,"Error. Lo sentimos pero la estimacion es demasiada pequeña, el programa"+
                    "\nno soporta esa cantidad de puntos decimales, por favor introduzca una mayor a 0.000000000000001."
                    ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == limpiar){
            funcion.setText(" Función");
            estimacion.setText(" Estimacion");
            a.setText(" a");
            b.setText(" b");
            raiz.setText("");
            iteraciones.setClear("Interacion\t|\tValor aproximado\t|   Tolerancia\n\n");
        }
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Interpolación Lineal en el PanelInterpolacionLineal.*/
    private final void setInformacion(){
        str_formulas = "<font size = 5 COLOR = red>Convergencia: f(a) * f(b) &#60 0 <br>Aprox: x0=a+(a-b)*f(a)/f(b)-f(a) </font><br>";
        formulas.setInfo(str_formulas);
        String info = "<html><body><h2 ALIGN = center>Método de Interpolación Lineal</h2>"
        +"<font size = 5><P>Definición del Método</P></font>"
        +"<br>El método propone unidr los puntos <B>f(a)</B> y <B>f(b)</B> con una línea recta de tal forma que se construya "
        +"la cuerda de la función. Esta línea recta deberá cortar al eje de las abscisas en un punto al que llamaremos X0 "
        +"por que será la primera aproximación a la raíz buscada. El paso siguiente será determinar cuál de los extremos del "
        +"intervalo <B>[a,b]</B> es sustituido por X0 de tal forma que cumpla aún con el criterio de convergencia.<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> f(a) * f(b) &#60 0 </P></font><br>"
        +"<br>Se traza de nuevo una nueva recta,ahora entre <B>f(a)</B> y <B>f(X0)</B> la cual nuevamente deberá cortar al "
        +"eje horizontal, más cerca de la raíz de la función. Vigilando el criterio de la convergencia, este proceso se repetirá "
        +"el número de veces que sea necesario hasta que la diferencia entre las aproximaciones <B>f(Xi)</B> y <B>f(Xi-1)</B> sea "
        +"menor que una Tolerancia preestablecida.<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> x0 = a + ( a - b ) * f(a) / f(b) - f(a) </P></font><br>"
        +"<br>Una vez obtenida la aproximación X0 deberá evaluarse <B>f(X0)</B> para poder decidir cual de los extremos del intervalo "
        +"<B>[a,b]</B> se va a sustituir.<br>"
        +"<br><br>&nbsp &nbsp &nbsp .-Si f(x0) &#60 0 entonces x0 sustituye a 'a'."
        +"<br><br>&nbsp &nbsp &nbsp .-Si f(x0) >  0, entonces x0 sustituye a 'b'.<br><br></P></body></html>";
        informacion.setInfo(info);
    }
}