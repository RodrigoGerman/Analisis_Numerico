package controlador.theme5.ed;

import vista.panels.theme5.ed.PanelMethodED;
import modelo.theme5.MetodoRungeKutta;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;

public final class PanelRungeKutta extends PanelMethodED implements ActionListener{
	/** * Variable tipo privada MetodoRungeKutta que representa el método de Rungue Kutta para solucionar ecuación diferencial.*/
	private MetodoRungeKutta runge;

	/** * Metodo constructor que crear un PanelRungeKutta pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelRungeKutta(){}

	/** * Metodo constructor que crea un PanelRungeKutta, recibe el color del fondo para el panel.*/
	public PanelRungeKutta(Color fondo){
		super(new Color(76, 123, 171),"Método de Euler Gauss ED");
		titulo_n = new JLabel("Grado Rungue-Kutta:");
		setPanelDatos();
		setInformacion();
		setAllElements();
		calcular.addActionListener(this);
		limpiar.addActionListener(this);
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione. */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == calcular){
			if(!getEmptyFields() && getFieldsValids()){
				soluciones.setClear("");
				iteraciones.setClear("");

				if(Integer.parseInt(n.getText().trim()) != 4 && Integer.parseInt(n.getText().trim()) != 2 && Integer.parseInt(n.getText().trim()) != 1)
					JOptionPane.showMessageDialog(null,"Error. \nIngrese un grado valido de Runge-Kutta,los cuales pueden ser 1,2 ó 4."
            		,"Error - Espaciamiento",JOptionPane.ERROR_MESSAGE);
				else{
					runge = new MetodoRungeKutta((funcion.getText()).trim(),Integer.parseInt(n.getText().trim())
						,Double.parseDouble(y.getText().trim()),Double.parseDouble(val.getText().trim())
						,Double.parseDouble(h.getText().trim()),Double.parseDouble(x.getText().trim()));

					soluciones.setText("Solucion:"+runge.getSolucion());
					iteraciones.setText(runge.getIteraciones());
				}
			}
		}
		else if(e.getSource() == limpiar){
			funcion.setText(" Ecuación Diferencial");
			h.setText(" h");
			n.setText(" Divisiones");
			y.setText(" y");
			val.setText(" x");
			x.setText(" x");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

		/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Rungue Kutta para solucionar ecuación diferencial en el PanelRungeKutta.*/
	private final void setInformacion(){
		str_formulas = "k1 = hf(Xi, Yi)</p><p>k2 = hf(Xi +h/2 , Yi + k1/2)</p><p>k3 = hf(Xi + h/2 , Yi + k2/2)</p><p>k4 = hf(Xi + h, Yi + k3)";
		info = "<html><body><h2 ALIGN = center COLOR = red>Me&#769;todos de Rungue-Kutta</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>3. Me&#769;todos de Runge-Kutta</p><p>Los me&#769;todos Runge-Kutta son producto de un algoritmo muy eficaz para resolver ecuaciones diferenciales."
		+"Lo interesante de ellos es que se pueden obtener varios me&#769;todos de diferente aproximacio&#769;n.Se clasifican de acuerdo su orden. En general, "
		+"su obtencio&#769;n no es sencilla, pero se muestran susgeneralidades ma&#769;s importantes [2].</p><p>La forma general de la solucio&#769;n de una "
		+"ecuacio&#769;n diferencial tiene la forma:</p><p>Yi+1 = Yn + h&#934;(Xi, Yi) (13)</p><p>Donde i = 0, 1, 2, ...</p><p>Si se observan las ecuaciones "
		+"iterativas de los me&#769;todos de Euler (11) y de Euler-Gauss (12) respondena la forma mostrada en la ecuacio&#769;n (13); de hecho, el me&#769;todo"
		+"de Euler coincide absolutamente con el me&#769;todo de Runge-Kutta de primer orden.</p><p>En general, la ecuacio&#769;n recursiva se escribe como [3]:</p>"
		+"<p>Yi+1 = yi + (w1k1 + w2k2 + w3k3 + ...+ wnkn) (14)</p><p>donde:k1 = hf(Xi, Yi)</p><p>k2 = hf(Xi + &#945;1h, Yi&#946;1,1k1)</p>"
		+"<p>k3 = hf(Xi + &#945;2h, Yi&#946;2,1k1 + &#946;2,2k2)</p><p>k4 = hf(Xi + &#945;3h, Yi&#946;3,1k1 + &#946;3,2k2 + &#946;3,3k3)</p>"
		+"<p>...kn = hf(Xi + &#945;n&#8722;1h, Yi&#946;n&#8722;1,1k1 + &#946;n&#8722;1,2k2 + ...+ &#946;n&#8722;1,n&#8722;1kn&#8722;1) (15)</p>"
		+"<p>Donde los coeficientes wi,&#945;i y &#946;i,i son constantes que se obtienen a partir de una aproximacio&#769;n por el polinomio de Taylor.</p>"
		+"<p>El orden del me&#769;todo corresponde a la variable n.</p><p>Las ecuaciones del me&#769;todo de Rungue-Kutta de segundo orden son:</p>"
		+"<p>Yi+1 = Yi +1/2(k1 + k2) i = 0, 1, 2, 3, ... (16)</p><p>Donde:k1 = hf(Xi, Yi)</p><p>k2 = hf(Xi + h, Yi + k1)(17)</p><p>De la misma forma,"
		+"las ecuaciones del me&#769;todo de Rungue-Kutta de cuarto orden son:</p><p>Yi+1 = Yi +1/6(k1 + 2k2 + 2k3 + k4) i = 0, 1, 2, 3, ... (18)</p>"
		+"<p>Donde:k1 = hf(Xi, Yi)</p><p>k2 = hf(Xi +h/2 , Yi + k1/2)</p><p>k3 = hf(Xi + h/2 , Yi + k2/2)</p><p>k4 = hf (Xi + h, Yi + k3) (19)</p>"
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}