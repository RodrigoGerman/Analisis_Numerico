package controlador.theme5.ed;

import vista.panels.theme5.ed.PanelMethodED;
import modelo.theme5.MetodoSerieTaylorED;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
    * La clase PanelTaylor es una clase que hereda de PanelMethodED debido a que es un panel de metodo de analisis numerico y implementa
    * la interfaz ActionListener para ejecutar el metodo numerico de Series de Taylor para solucionar ecuacion diferencial, se encarga  de crear un panel especifico para la visualizacion
    * del metodo numerico de Series de Taylor para solucionar ecuacion diferencial.
    * @author German Lopez Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelTaylor extends PanelMethodED implements ActionListener{
	/** * Variable tipo privada MetodoSerieTaylorED que representa el metodo de Series de Taylor para solucionar ecuacion diferencial.*/
	private MetodoSerieTaylorED taylor;

	/** * Metodo constructor que crear un PanelTaylor pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelTaylor(){}

	/** * Metodo constructor que crea un PanelTaylor, recibe el color del fondo para el panel.*/
	public PanelTaylor(Color fondo){
		super(new Color(76, 123, 171),"Metodo de Euler Gauss ED");
		setPanelDatos();
		setInformacion();
		setAllElements();
		calcular.addActionListener(this);
		limpiar.addActionListener(this);
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
	@Override
	public void actionPerformed(ActionEvent e){
		double valor_estimacion = 0,valor_a = 0,valor_b = 0; 
		if(e.getSource() == calcular){
			if(!getEmptyFields() && getFieldsValids()){
				soluciones.setClear("");
				iteraciones.setClear("");
				taylor = new MetodoSerieTaylorED((funcion.getText()).trim(),Integer.parseInt(n.getText().trim())
					,Double.parseDouble(y.getText().trim()),Double.parseDouble(val.getText().trim())
					,Double.parseDouble(h.getText().trim()),Double.parseDouble(x.getText().trim()));

				soluciones.setText("Solucion:"+taylor.getSolucion());
				iteraciones.setText(taylor.getIteraciones());
			}
		}
		else if(e.getSource() == limpiar){
			funcion.setText(" Ecuación Diferencial");
			h.setText(" h");
			n.setText(" Diviciones");
			y.setText(" y");
			val.setText(" x");
			x.setText(" x");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de informacion sobre el metodo de Series de Taylor para solucionar ecuacion diferencial en el PanelTaylor.*/
	private final void setInformacion(){
		str_formulas = "<font size = 4 COLOR = red>Y (X) = Y (X0) + (X &#8722;X0)Y<sup>'</sup>(X0) +(X &#8722;X0)<sub>2</sub>/2!Y<sup>''</sup>;(X0) + (X &#8722;X0)<sup>3</sup>/3!Y<sup>'''</sup>(X0) + ... </font><br>";
		info = "<html><body><h2 ALIGN = center COLOR = red >Me&#769;todo de la serie de Taylor</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>El desarrollo por serie de Taylor corresponde a la siguiente expresio&#769;n:</p>"
		+"<p>Y (X) = Y (X0) + (X &#8722;X0)Y<sup>'</sup>(X0) +(X &#8722;X0)<sub>2</sub>/2!Y<sup>''</sup>;(X0) + (X &#8722;X0)<sup>3</sup>/3!Y<sup>'''</sup>(X0) + ...</p>"
		+"<p>Al utilizar esta expresio&#769;n se comete un error debido a que se trata de una serie infinita; adicionalmente,para que se considere correcto su uso "
		+"debe evaluarse en un entorno muy cercano a X0; el resultado pierde validez si la evaluacio&#769;n se aleja de este punto.</p><p>Su uso consiste en "
		+"construir a la solucio&#769;n de la ecuacio&#769;n diferencial Y (X) a partir de la ecuacio&#769;n diferencial, en este caso, de primer orden "
		+"Y<sup>'</sup>(X) = f(X,Y) y su condicio&#769;n inicial Y (X0). De hecho,si se contrasta la ecuacio&#769;n (20) y estas u&#769;ltimas dos definiciones "
		+"se observa que se conocen los dos primeros sumandos de la serie de Taylor. So&#769;lo es necesario derivar a la ecuacio&#769;n diferencial tantas veces"
		+"como te&#769;rminos se desee que conformen al polinomio solucio&#769;n. El valor X0 en el que se evalu&#769;a a la serie corresponde a la condicio&#769;n"
		+"inicial de la ecuacio&#769;n diferencial.</p><p>Este me&#769;todo estrictamente hablando no corresponde a un me&#769;todo nume&#769;rico ya que no es "
		+"necesario realizar iteraciones y proporciona un polinomio; no obstante se considera como tal por las precisionesque deben tomarse en cuenta por los "
		+"aspectos de precisio&#769;n debidos al truncamiento de una serie infinita.</p>"
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}