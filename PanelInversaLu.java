package controlador.theme3.matriz;


import vista.panels.theme3.PanelMethodMatriz;
import modelo.theme3.LU;
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
    * La clase PanelInversaLu es una clase que hereda de PanelMethodMatriz debido a que es un panel de método de análisis numérico que utiliza una matriz e implementa
    * la interfaz ActionListener para ejecutar el método numérico LU, se encarga de crear un panel específico para la visualización
    * del método numérico LU para el cálculo de una matriz inversa.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelInversaLu extends PanelMethodMatriz implements ActionListener{
	/** * Variable tipo LU privada que representa el método de LU para el cálculo de un inversa.*/
	private LU inversa;
	/** * Variable tipo Matriz privada que representa la matriz ingresada por el usuario.*/
	private Matriz matriz= null;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelInversaLu.*/
	private JFrame parent;

	/** * Método constructor que crear un PanelMetodoPotencias pero sin ninguna comportamiento ni estilo específico. */
	public PanelInversaLu(){}

	/** * Método constructor que crea un PanelMetodoPotencias, recibe la ventana padre que lo llamo.*/
	public PanelInversaLu(JFrame parent){
		super(new Color(76, 123, 171),"Método LU Inversa");
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
						JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca una matriz cuadrada el método de LU solo\n"
							+"funciona con matrices cuadradas.","Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
				JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca un numero de renglones y columnas."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
		}

		else if(e.getSource() == calcular){
			if(matriz == null){
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no ha introducido una matriz"
				+" por favor introduzca una matriz.","Error - App Análisis Numérico", JOptionPane.ERROR_MESSAGE);
			}

			else{
				inversa = new LU(matriz);
				iteraciones.setText(inversa.getIteraciones());
				soluciones.setText(inversa.getSoluciones());
			}
		}

		else if(e.getSource() == limpiar){
			renglones.setText(" Renglones");
			columnas.setText(" Columnas");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de LU  en el PanelInversaLu.*/
	private final void setInformacion(){
		str_formulas += "L<sub>i</sub><sub>j</sub> = (a<sub>i</sub><sub>j</sub> - &#931<sub>k=1</sub><sup>j-1</sup> L<sub>ik</sub>*U<sub>kj</sub>)/U<sub>ii</sub>"
			+"<br>U<sub>i</sub><sub>j</sub> = (a<sub>i</sub><sub>j</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*U<sub>kj</sub>)"
			+"<br>b = (b<sub>i</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*b<sub>k</sub>)/L<sub>ii</sub>"
			+"<br>X = b<sub>j</sub> - &#931<sub>k=j+1</sub><sup>n</sup> U<sub>jk</sub>*b<sub>k</sub>";

		info = "<html><body><h2 ALIGN = JUSTIFY>Método LU</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>La descomposición LU es una forma de expresar "
			+"las transformaciones del método Gauss-Jordan por medio de ecuaciones matriciales, lo que implica una reducción " 
			+"notable en las operaciones propias y, naturalmente, en el diseño del algortimo. Asimismo, se reduce notablemente "
			+"el impacto de la producción de errores debidos al pivoteo; adicionalmente, la descomposición LU puede utilizarse "
			+"para otros procesos como la obtención de la matriz inversa.<br>"
			+"<font size = 5><P>Definición del Método</P></font><br>"
			+"La descomposición LU consiste en encontrar dos matrices, L y U construidas de tal forma que se cumpla que:<br>"
			+"<font size = 5 COLOR = red><br> A&equiv; L * U </font><br>"
			+"<br>Las caracterı́sticas de las matrices L y U dependen de cada una de las versiones definidas para la descomposición:<br><br></P>"
			+"<font COLOR = red size = 5><P>Versión Crout</P></font>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>En la versión Crout, la matriz L es una matriz triangular inferior de la forma 2 y la matriz U una "
			+"matriz trianguilar superior con elementos unitarios en la diagonal principal, segun la forma 3.<br>"
			+"<br> Forma 2:<br>"
			+"<br>|&nbsp L<sub>1</sub><sub>1</sub> &nbsp&nbsp 0 &nbsp&nbsp&nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
			+"<br>|&nbsp L<sub>2</sub><sub>1</sub> &nbsp L<sub>2</sub><sub>2</sub> &nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
			+"<br>|&nbsp L<sub>3</sub><sub>1</sub> &nbsp L<sub>3</sub><sub>2</sub> &nbsp L<sub>3</sub><sub>3</sub> &nbsp .... &nbsp 0 &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp ..... &nbsp&nbsp .... &nbsp&nbsp .... &nbsp .... &nbsp .... &nbsp&nbsp|"
			+"<br>|&nbsp L<sub>n</sub><sub>1</sub> &nbsp L<sub>n</sub><sub>2</sub> &nbsp L<sub>n</sub><sub>3</sub> &nbsp .... &nbsp L<sub>n</sub><sub>n</sub>|"
			+"<br><br><br> Forma 3:<br>"
			+"<br>|&nbsp 1 &nbsp&nbsp U<sub>1</sub><sub>2</sub> &nbsp&nbsp U<sub>1</sub><sub>3</sub> &nbsp &nbsp .... &nbsp&nbsp U<sub>1</sub><sub>n</sub> &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp U<sub>2</sub><sub>3</sub> &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp U<sub>2</sub><sub>n</sub> &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp U<sub>3</sub><sub>n</sub> &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp&nbsp ..... &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp .... &nbsp&nbsp .... &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp U<sub>n</sub><sub>n</sub> &nbsp&nbsp|</P></font>"
			+"<font COLOR = red size = 5><P>Versión Doolittle</P></font>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>La versión Doolittle define a las matrices L y U a la manera inversa que " 
			+"Crout; la matriz U es una matriz triangular inferior de la forma 1 y la matriz L una matriz trianguilar superior con elementos unitarios "
			+"en la diagonal principal, segun la forma 1.<br>"
			+"<br> Forma 1:<br>"
			+"<br>|&nbsp U<sub>1</sub><sub>1</sub> &nbsp&nbsp 0 &nbsp&nbsp&nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
			+"<br>|&nbsp U<sub>2</sub><sub>1</sub> &nbsp U<sub>2</sub><sub>2</sub> &nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
			+"<br>|&nbsp U<sub>3</sub><sub>1</sub> &nbsp U<sub>3</sub><sub>2</sub> &nbsp U<sub>3</sub><sub>3</sub> &nbsp .... &nbsp 0 &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp ..... &nbsp&nbsp .... &nbsp&nbsp .... &nbsp .... &nbsp .... &nbsp&nbsp|"
			+"<br>|&nbsp U<sub>n</sub><sub>1</sub> &nbsp U<sub>n</sub><sub>2</sub> &nbsp U<sub>n</sub><sub>3</sub> &nbsp .... &nbsp U<sub>n</sub><sub>n</sub>|"
			+"<br><br><br> Forma 2:<br>"
			+"<br>|&nbsp 1 &nbsp&nbsp L<sub>1</sub><sub>2</sub> &nbsp&nbsp L<sub>1</sub><sub>3</sub> &nbsp &nbsp .... &nbsp&nbsp L<sub>1</sub><sub>n</sub> &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp L<sub>2</sub><sub>3</sub> &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp L<sub>2</sub><sub>n</sub> &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp L<sub>3</sub><sub>n</sub> &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp&nbsp ..... &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp .... &nbsp&nbsp .... &nbsp&nbsp|"
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp|</P></font>"
			+"<P ALIGN = JUSTIFY><br>Una vez que la matriz de coeficientes A ha sido transformada en sus matrices L y U es fácil obtener la solución de un sistema de ecuaciones. Seguramente "
			+"se percibe que la matriz U tiene la forma análoga que se busca con el uso del método de Gauss, es decir, es una matriz triangular superior con los elementos de "
			+"su diagonal principal iguales a uno. En efecto, esta afirmación es correcta. Para tener un método de solución completo, si el sistema de ecuaciones ha sido modificado "
			+"en su miembro izquierdo, para no alterarle, debe ser también modificado en su miembro derecho. De hecho, la matriz L es un registro de las operaciones que se "
			+"realizan en la transformación, de tal forma, si se aplican las mismas operaciones en el vector de términos independientes b̄ y posteriormente se hace una sustitución "
			+"hacia atrás, el sistema queda resuelto. Queda entonces la necesidad de aplicar las mismas operaciones a b̄ que fueron realizadas para obtener a U ."
			+"Por lo tanto, se define al vector modificado de términos independientes b̄' como:"
			+"<br><br>b = (b<sub>i</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*b<sub>k</sub>)/L<sub>ii</sub>"
			+"<br><br>Para completar el proceso, las ecuaciones que permiten la sustitución hacia atrás son:"
			+"<br><br>X = b<sub>j</sub> - &#931<sub>k=j+1</sub><sup>n</sup> U<sub>jk</sub>*b<sub>k</sub><br><br></P></body></html>";

		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}
