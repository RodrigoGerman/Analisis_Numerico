package vista.panels;

import vista.panels.PanelEditorPane;
import vista.panels.PanelTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
/**
    * La clase PanelMethodsAnalisisNumerico es una clase abstracta que contienes ciertos componentes y variables que todos los paneles
    * de la aplicación deben de poseer.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public abstract class PanelMethodsAnalisisNumerico extends JPanel{
	/** *Variable tipo JButton que se encarga de ejecutar el método numérico cuando es presionado.*/
	protected final JButton calcular  = new JButton("Calcular");
	/** *Variable tipo JButton que se encarga de limpiar los campos a llenar en el panel cuando es presionado.*/
	protected final JButton limpiar= new JButton("Limpiar");
	/** *Variable tipo JPanel para almacenar que contiene los campos necesarios para cada método de análisis numérico. */
	protected final JPanel panel_datos = new JPanel();
	/** *Variable tipo Font que se encarga de asignar un tipo de letras especial para el texto que contiene el panel del método numérico. */
	protected final Font titulo = new Font("Times New Roman", 1, 18);
	/** *Variable tipo Font que se encarga de asignar un tipo de letras especial para el texto que contiene el panel del método numérico.*/
	protected final Color bordes = new Color(235, 235, 235),titulos = new Color(235, 235, 238);
    protected String str_formulas = "",info = "";
    protected PanelEditorPane informacion,formulas;
    protected PanelTextArea iteraciones;
    protected Color fondo;

	public PanelMethodsAnalisisNumerico(){}

	public PanelMethodsAnalisisNumerico(Color fondo){
		this.fondo = fondo;
		this.setBackground(fondo);
		this.setBorder(BorderFactory.createLineBorder(bordes,2));
	}

	protected abstract void setAllElements();
}
