
package controlador.theme5.ed;

import vista.panels.theme5.ed.PanelMethodED;
import modelo.theme5.MetodoEulerGaussED;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
    * La clase PanelEuler es una clase que hereda de PanelMethodED debido a que es un panel de método de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Euler-Gauss para solucionar ecuación diferencial, se encarga de crear un panel específico para la visualización
    * del método numérico de Euler-Gauss para solucionar ecuación diferencial.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelEulerGauss extends PanelMethodED implements ActionListener{
	/** * Variable tipo privada MetodoEulerGaussED que representa el método de Euler-Gauss para solucionar ecuación diferencial.*/
	private MetodoEulerGaussED euler;

	/** * Método constructor que crear un PanelEulerGauss pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelEulerGauss(){}

	/** * Método constructor que crea un PanelEulerGauss, recibe el color del fondo para el panel.*/
	public PanelEulerGauss(Color fondo){
		super(new Color(76, 123, 171),"Método de Euler Gauss ED");
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
				euler = new MetodoEulerGaussED((funcion.getText()).trim(),Integer.parseInt(n.getText().trim())
					,Double.parseDouble(y.getText().trim()),Double.parseDouble(val.getText().trim())
					,Double.parseDouble(h.getText().trim()),Double.parseDouble(x.getText().trim()));

				soluciones.setText("Solución:"+euler.getSolucion());
				iteraciones.setText(euler.getIteraciones());
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

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Euler-Gauss para solucionar ecuación diferencial en el PanelEulerGauss.*/
	private final void setInformacion(){
		str_formulas = "<font size = 4 face = Times New Roman>Y(X<sub>i+1</sub>) = Y(X<sub>i</sub>) +h/2[f(X<sub>i</sub>, Y<sub>i</sub>) + f(X<sub>i+1</sub>, Y<sub>i+1</sub>)]";
		info = "<html><body><h2 ALIGN = center COLOR = red>Método de Euler para Solucio&#769;n nume&#769;rica de ecuaciones diferenciales</h2>"
		+"<font size = 3.9 face = Times New Roman ><P ALIGN = JUSTIFY>"
		+"Solucio&#769;n de ecuaciones diferenciales con condiciones iniciales</p><p>Son varios "
		+"los me&#769;todos nume&#769;ricos que ofrecen la solucio&#769;n de estas ecuaciones diferenciales. Losme&#769;todos paso a paso se "
		+"fundamentan en los polinomios interpolantes que provienen de tablas dediferencias; en consecuencia sera&#769; necesario disponer "
		+"de un intervalo de solucio&#769;n de la variable independiente cuyo primer punto debe coincidir con la condicio&#769;n inicial. "
		+"El nu&#769;mero de puntos deseado debera&#769; determinarse con base en los resultados esperados y en todo caso debera&#769; "
		+"conservarse un paso h constante.</p>"
		+"<p>2.1. Me&#769;todos predictivo-correctivos</p><p>Los me&#769;todos predictivos correctivos se basan en el polinomio interpolante "
		+"de Newton-Gregory,mismo que representa a una funcio&#769;n tabular con variable independiente equiespaciada. Se utilizan para resolver "
		+"ecuaciones diferenciales ordinarias de primer orden, lineales o no y de cualquier grado.Estos me&#769;todos tambie&#769;n son conocidos "
		+"como me&#769;todos de pasos o paso a paso ya que resuelven a la ecuacio&#769;n en intervalos entre dos pares de puntos y repitiendo esta "
		+"solucio&#769;n en todos los puntos que conforman a la funcio&#769;n tabular.</p><p>El planteamiento inicial es resolver una ecuacio&#769;n "
		+"es el siguiente [1]:</p><p>Y<sup>'</sup> = f(X,Y) (1)</p><p>La solucio&#769;n se obtiene integrando ambos miembros de la ecuacio&#769;n entre "
		+"dos puntos consecutivos:</p><p>&#x222b<sub>Xi<sub><sup>Xi+1</sup> f(x) dx = &#x222b<sub>Xi<sub><sup>Xi+1</sup> f(X,Y) dx (2)</p>"
		+"<p>De la que se obtiene:</p><p>Y(Xi+1) &#8722; Y (Xi) = &#x222b<sub>Xi<sub><sup>Xi+1</sup> f(X,Y) dx</p><p>Y (Xi+1) = Y (Xi) + &#x222b<sub>Xi<sub><sup>Xi+1</sup> f(X,Y) dx (3)</p>"
		+"<p>La integral incluida en el segundo miembro de la ecuacio&#769;n puede resolverse a partir del polinomio interpolante de Newton-Gregory "
		+"que tiene la siguiente forma:</p><p>Yk = Y0 + k&#916;Y0 +k(k &#8722; 1)/2!&#916;<sup>2</sup>Y0 + k(k &#8722; 1)(k &#8722; 2)/3!&#916;<sup>3</sup>Y0 + ... (4)</p>"
		+"<p>A la ecuacio&#769;n (4) se le da el mismo tratamiento necesario para crear esquemas de integracio&#769;n nume&#769;rica, en este caso se le "
		+"integra a partir de un proceso de cambio de variable del cual resulta:</p>"
		+"<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h[nY0 +n<sup>2</sup>/2&#916;Y0 +(n<sup>3</sup></6&#8722; n<sup>2</sup>/4)&#916;<sup>2</sup>Y0) + (n<sup>4</sup></24&#8722; 3n<sup>3</sup>/18&#8722; 2n<sup>2</sup>/12)&#916;<sup>3</sup>Y0+....] (5)</p> "
		+"<p>Es necesario hacer algunas precisiones en esta ecuacio&#769;n. Toda vez que se trata de integrar entre dós puntos consecutivos Xi y Xi+1, e&#769;stos "
		+"debera&#769;n considerarse como l&#769;&#305;mites de la integral y enconsecuencia entre ambos so&#769;lo existe un paso h y  en consecuencia n = 1. "
		+"Por otra parte debe elegirse el orden de interpolacio&#769;n deseado; en este caso, se elige un primer orden de interpolacio&#769;n,es decir, truncar "
		+"al polinomio 5 en la primera diferencia. Como se estudio&#769; en su momento, esto originara&#769; un orden de error de O(h2).</p>"
		+"<p>&#x222b<sub>Xi<sub><sup>Xi+1</sup> f(x) dx = h[Y0 +1/2&#916;Y0 (6)</p><p>Sustituyendo los valores de las diferencias:</p>"
		+"<p>&#x222b<sub>Xi<sub><sup>Xi+1</sup> = h[Y0 + 1/2(Y1 &#8722; Y0) (7)</p><p>Una u&#769;ltima precisio&#769;n: En la ecuacio&#769;n (7) se utiliza "
		+"una notacio&#769;n que emana de las tablas de diferencias en donde al valor de f(Xi) se le denota como Yi. En todo caso, Yi = f(Xi). Dado lo anterior,"
		+"es pertinente unificar la notacio&#769;n con la mostrada en la ecuacio&#769;n (3). Cambiando la notacio&#769;n y sustituyendo 7 en 3:</p>"
		+"<p>Y (Xi+1) = Y (Xi) + h[f(Xi, Yi) +1/2(f(Xi+1, Yi+1)&#8722; f(Xi, Yi)] (8)</p><p>Esta ecuacio&#769;n es la que da origen a los me&#769;todos "
		+"predictivos-correctivos.</p><p>Inicialmente, se propone que la ecuacio&#769;n (8) sea de nuevo truncada (por un motivo que ma&#769;s adelantesera&#769; "
		+"evidente) hasta el uso de f(Xi). De esto resulta:</p><p>Y (Xi+1) = Y (Xi) + h f(Xi)</p><p>O en una notacio&#769;n ma&#769;s pra&#769;ctica:Yi+1 = Yi + h f(Xi) (9)</p>"
		+"<p>A la ecuacio&#769;n (9) se le denomina Me&#769;todo de Euler para resolver ecuaciones diferenciales de primer orden con condiciones iniciales en un intervalo"
		+"equiespaciado. Resulta obvia la percepcio&#769;n al gran orden de error que arroja el uso de este me&#769;todo. "
		+"No obstante, esta ecuacio&#769;n tiene una razo&#769;n de ser. Retomando la ecuacio&#769;n (8) sin truncar, haciendo las simplificaciones del caso:</p>"
		+"<p>Y (Xi+1) = Y (Xi) +h/2[f(Xi, Yi) + f(Xi+1, Yi+1)] (10)</p><p>Esta u&#769;ltima ecuacio&#769;n, de primera mano, resulta imposible de resolver,"
		+"toda vez que el miembro izquierdo esta&#769; presente en el miembro derecho. Dado esto, conforme a la filosof&#769;&#305;a de los me&#769;todosnume&#769;ricos "
		+"se propone un valor inicial de Y (Xi+1) obtenido a partir del me&#769;todo de Euler en la ecuacio&#769;n (9) que se denominara&#769; valor predictor ; este valor"
		+"se utilizara&#769; en la ecuacio&#769;n (10) con la cual el valor predictor sera&#769; corregido. De aqu&#769;&#305; el nombre de me&#769;todo "
		+"predictor-corrector. Denotando esta propuesta se obtienen dos ecuaciones que en conjunto constituyen el Me&#769;todo de Euler-Gauss1</p><p>o bien, "
		+"Me&#769;todo predictor-corrector.</p><p>Y (Xi+1p) = Y (Xi) + h f(Xi)</p><p>Y (Xi+1c) = Y (Xi) +h/2[f(Xi, Yi) + f(Xi+1, Yi+1p)]</p>"
		+"<p>Para i = 0, 1, 2, ...</p><p>De nuevo, en una notacio&#769;n ma&#769;s pra&#769;ctica:</p><p>Y (Xi+1p) = Y (Xi) + h f(Xi) (11)</p>"
		+"<p>Y (Xi+1c) = Y (Xi) +h</p><p>2[f(Xi, Yi) + f(Xi+1, Yi+1p)] (12)</p><p>Para i = 0, 1, 2, ...</p>"
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}
