package controlador.theme2.closedmethods;

import vista.panels.theme2.PanelClosedMethods;
import modelo.theme2.closedmethods.Biseccion;

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
    * La clase PanelBiseccion es una clase que hereda de PanelClosedMethods debido a que es un panel de métodos cerrados de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Bisección, se encarga de crear un panel específico para la visualización
    * del método numérico de Bisección.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelBiseccion extends PanelClosedMethods implements ActionListener{
	 /** * Variable tipo privada AproximacionesSucesivas que representa el método de Bisección.*/
	private Biseccion biseccion;

	/** * Método constructor que crea un PanelBiseccion.*/
	public PanelBiseccion(){
		super(new Color(76, 123, 171),"Método de Bisección");
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
		double valor_estimacion = 0,valor_a = 0,valor_b = 0; 
		if(e.getSource() == calcular){
			if(getEmptyFields() && getFieldsValids()){
				valor_estimacion = Double.parseDouble(estimacion.getText().trim());
				valor_a = Double.parseDouble(a.getText().trim());
				valor_b = Double.parseDouble(b.getText().trim());
				if(valor_estimacion != 0.0 && valor_estimacion > 0.000000000000001){
					biseccion = new Biseccion((funcion.getText()).trim(),valor_estimacion);
					if(biseccion.getConvergencia(valor_a,valor_b)){
						iteraciones.setClear("Interacción\t|\tValor aproximado\t|   Tolerancia\n\n");
						biseccion.setAproximacion(valor_a,valor_b);
						iteraciones.setText(biseccion.getIteraciones());
						iteraciones.setText("\nRaíz:  " + String.valueOf(biseccion.getRaiz()));
						raiz.setText(" " + String.valueOf(biseccion.getRaiz()));
					}
					else
						JOptionPane.showMessageDialog(null,"Error. El intervalo no converge introduzca otro por favor."
						,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
					}
				else
					JOptionPane.showMessageDialog(null,"Error. Lo sentimos, pero la estimación es demasiada pequeña, el programa"+
					"\nno soporta esa cantidad de puntos decimales, por favor introduzca una mayor a 0.000000000000001."
					,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == limpiar){
			funcion.setText(" Función");
			estimacion.setText(" Estimación");
			a.setText(" a");
			b.setText(" b");
			raiz.setText("");
			iteraciones.setClear("Interacción\t|\tValor aproximado\t|   Tolerancia\n\n");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Bisección en el PanelBiseccion.*/
	private final void setInformacion(){
		str_formulas = "<font size = 5 COLOR = red>Convergencia: f(a) * f(b) &#60 0 <br>Aproximación: x0 = ( a + b ) / 2 </font><br>";
		formulas.setInfo(str_formulas);
		info = "<html><body><h2 ALIGN = center>Método de Bisección</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>El método de Bisección se aplica a funciones algebraicas "
		+"o trascendentes y proporciona únicamente raíces reales. Es un método cerrado, es decir, requiere de un " 
		+"intervalo en el cual esté atrapada la raíz. Básicamente, consiste en cortar el intervalo en dos justos " 
		+"por la mitad(bisectar) y considerando a este punto como una aproximación de la raíz de la función.<br>"
		+"<font size = 5><P>Definición del Método</P></font>"
		+"<br>A partir de una función algebraica o trascendente y de un intervalo <B>[a,b]</B> que pertenece al "
		+"dominio de la función y para el cual:<br>"
		+"<font size = 5 COLOR = red><P ALIGN = center> f(a) * f(b) &#60 0 </P></font><br>"
		+"<br>lo que implica que en el intervalo <B>[a,b]</B> existe al menos una raíz. El método consiste en bisectar "
		+"el intervalo <B>[a,b]</B>:<br>"
		+"<font size = 5 COLOR = red><P ALIGN = center> x0 = ( a + b ) / 2</P></font><br>"
		+"<br>Obteniendo una aproximación a la raíz x0; la función se valúa en este nuevo valor y de acuerdo al signo de"
		+"la función valuada en este punto, deberá sustituirse uno de los extremos del intervalo de búsqueda, de tal forma"
		+"que se conserve que:<br>"
		+"<font size = 5 COLOR = red><P ALIGN = center> f(a) * f(b) &#60 0 </P></font><br>"
		+"<br>De acuerdo a la geometría de la figura, la sustitución de los intervalos deberá hacerse de la siguiente forma:<br>"
		+"<br>Sea a tal que f(a) > 0 y b tal que f(b) &#60 0:"
		+"<br><br>&nbsp &nbsp &nbsp .-Si f(x0) > 0 entonces x0 sustituye a 'a'."
		+"<br><br>&nbsp &nbsp &nbsp .-Si f(x0) &#60  0, entonces x0 sustituye a 'b'."
		+"<br><br>En cada intervalo debera sustituirse alguno de los limites del intervalo que contiene a la raíz. Repitiendo "
		+"este proceso, el intervalo se reduce paulatinamente hasta que alguna de las aproximaciones coincide razonablemente "
		+"con la raíz de la función. El proceso se detiene cuando la aproximación <B>xi</B> y la aproximación <B>xi-1</B> "
		+"se satisface un nivel de error preestablecido.<br><br></P></body></html>";
		informacion.setInfo(info);
	}
}