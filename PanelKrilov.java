

package controlador.theme3.matriz;


import vista.panels.theme3.PanelMethodMatriz;
import modelo.theme3.Krilov;
import controlador.WindowMatriz;
import controlador.WindowVector;
import modelo.Matriz;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;

/**
    * La clase PanelKrilov es una clase que hereda de PanelMethodMatriz debido a que es un panel de método de análisis numérico que utiliza una matriz e implementa
    * la interfaz ActionListener para ejecutar el método numérico Krilov, se encarga de crear un panel específico para la visualización
    * del método numérico Krilov.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelKrilov extends PanelMethodMatriz implements ActionListener{
	/** * Variable tipo Krilov privada que representa el método de Krilov.*/
	private Krilov krilov;
	/** * Variable tipo Matriz privada que representa la matriz ingresada por el usuario.*/
	private Matriz matriz= null;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelKrilov.*/
	private JFrame parent;

	/** * Método constructor que crear un PanelKrilov pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelKrilov(){}

	/** * Método constructor que crea un PanelKrilov, recibe la ventana padre que lo llamo.*/
	public PanelKrilov(JFrame parent){
		super(new Color(76, 123, 171),"Método de Krilov");
		titulo_matriz.setText("Matriz:");
		this.parent = parent;
		setInformacion();
		setPanelDatos();
		setAllElements();
		generar_matriz.addActionListener(this);
		calcular.addActionListener(this);
		limpiar.addActionListener(this);
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
	@Override
	public void actionPerformed(ActionEvent e){
		int reng = 0,colm = 0;
		if(e.getSource() == generar_matriz){
			if(!renglones.getText().equals(" Renglones") && !columnas.getText().equals(" Columnas")){
				reng = Integer.parseInt(renglones.getText().trim());
				colm = Integer.parseInt(columnas.getText().trim());

				if(reng <= 0 && colm <= 0 || (reng == 1 && colm == 1) || (reng == 0 || colm == 0) ){
					JOptionPane.showMessageDialog(null,"Advertencia. \nNo se puede crear una matriz de "+reng+"*"+colm+"."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(reng == colm){
						WindowMatriz proyect = new WindowMatriz(parent,true,reng,colm);
						proyect.addWindowListener(new WindowAdapter(){
							@Override
							public void windowClosed(WindowEvent e){
								matriz = proyect.getMatriz();
							}	
						});
					}
					else
						JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca una matriz cuadrada el método de krilov solo\n"
							+"funciona con matrices cuadradas.","Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
				JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca un numero de renglones y columnas."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
		}

		else if(e.getSource() == calcular){
			if(matriz == null){
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una matriz"
				+" por favor introduzca una matriz.","Error - App Análisis Numerico",JOptionPane.ERROR_MESSAGE);
			}

			else{
				krilov = new Krilov(matriz);
				iteraciones.setText(krilov.getIteraciones());
				soluciones.setText(krilov.getSoluciones());
			}
		}

		else if(e.getSource() == limpiar){
			renglones.setText(" Renglones");
			columnas.setText(" Columnas");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Krilov en el PanelKrilov.*/
	private final void setInformacion(){
		str_formulas = "<p>A<sup>n</sup>y&#772 + b<sub>1</sub>A<sup>n-1</sup>y&#772 + b<sub>2</sub>A<sup>n-2</sup>y&#772 + ...+ b<sub>n-1</sub>Ay&#772 + bn*Iy&#772 = 0</p>";
		info = "<html><body><h2 COLOR = red ALIGN = center>Me&#769;todo de Krilov</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>2. Definicio&#769;n del Me&#769;todo de Krilov</p><p>Este me&#769;todo se fundamenta en la aplicacio&#769;n del Teorema de Cayley-Hamilton [1],"
		+"mismo que establece que toda matriz A verifica su ecuacio&#769;n caracter&#769;&#305;stica:</p><p>F (A) = 0 (3)</p><p>Es decir, si sustituimos a "
		+"la matriz A en el polinomio 2, el resultado debera&#769; ser cero. Sin embargo,operativamente es necesario hacer algunos comentarios. De inicio, la "
		+"matriz A es de orden n, por lo cual la sustitucio&#769;n arrojara&#769; un sistema de n ecuaciones lineales; en consecuencia, el coeficiente a0 debera&#769; "
		+"ser diferente de cero. Resulta conveniente hacer que este coeficiente sea la unidad, por lo cual se divide el polinomio entero por a0, resultando:</p>"
		+"<p>&#955;<sup>n</sup> + b<sub>1</sub>&#955;<sup>n-1</sup> + b<sub>2</sub>&#955;<sup>n-2</sup> + ...+ b<sub>n-1</sub>&#955;+ bn = 0 (4)</p><p>Donde los coeficientes b<sub>i</sub> se obtienen como b<sub>i</sub> =ai/a0."
		+"Aplicando el teorema de Cayley-Hamilton en el polinomio 4:</p><p>F (A) = A<sup>n</sup> + b<sub>1</sub>A<sup>n-1</sup> + b<sub>2</sub>A<sup>n-2</sup> + ...+ b<sub>n-1</sub>A+ bn*I = 0 (5)</p>"
		+"<p>El polinomio 5 representa un sistema de ecuaciones lineales cuyas inco&#769;gnitas son los coeficientes bi.La solucio&#769;n de este sistema nos "
		+"proporciona los coeficientes bi que sustituidos en el polinomio 4 nos proporciona el polinomio caracter&#769;&#305;stico de A.</p><p>Una forma sencilla "
		+"de realizar este procedimiento es simplificar la elevacio&#769;n de la matriz A a laspotencias necesarias. Esto se logra multiplicando la matriz A por "
		+"un vector y&#772; compatible diferentede cero. Debe recordarse que la multiplicacio&#769;n de una matriz por un vector compatibles arroja unvector.</p>"
		+"<p>Este vector y&#772; puede ser libremente elegido, proponie&#769;ndose que su conformacio&#769;n permita realizar demejor forma las operaciones. "
		+"Una buena eleccio&#769;n es elegir al vector con la forma:</p><p>y&#772; = [1,0,0,...,0]<sup>T</sup></p>"
		+"<p>Ubicando al elemento 1 en una posicio&#769;n estrate&#769;gica de acuerdo con los coeficientes "
		+"de A de tal formaque se minimicen las operaciones.</p><p>Atendiendo a la anterior recomendacio&#769;n, el sistema que de la forma:</p>"
		+"<p>A<sup>n</sup>y&#772 + b<sub>1</sub>A<sup>n-1</sup>y&#772 + b<sub>2</sub>A<sup>n-2</sup>y&#772 + ...+ b<sub>n-1</sub>Ay&#772 + bn*Iy&#772 = 0 (6)</p><p>Finalmente, el sistema de ecuaciones puede ser "
		+"resuelto por el me&#769;todo de preferencia.</p>"
		+"<br><br></P></body></html>";

		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}
