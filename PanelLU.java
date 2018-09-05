
package controlador.theme3.matrizvector;

import vista.panels.theme3.PanelMethodMatrizVector;
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
    * La clase PanelLU es una clase que hereda de PanelMethodMatrizVector debido a que es un panel de método de análisis numérico que utiliza una matriz y un vector, implementa
    * la interfaz ActionListener para ejecutar el método numérico LU, se encarga de crear un panel específico para la visualización
    * del método Numérico LU.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelLU extends PanelMethodMatrizVector implements ActionListener{
	/** * Variable tipo LU privada que representa el método de LU.*/
	private LU lu;
	/** * Variable tipo String privada que representa el método a usar para resolver el método.*/
	private String metodo = "";
	/** * Variable tipo Matriz privada que representa la matriz ingresada por el usuario.*/
	private Matriz matriz= null;
	/** * Variable tipo ArrayList<Double> privada que representa lel vector ingresado por el usuario.*/
	private ArrayList<Double> vector = null;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelGaussSeidel.*/
	private JFrame parent;

	/** * Metodo constructor que crear un PanelLU pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelLU(){}

	/** * Metodo constructor que crear un PanelLU , recibe la ventana padre que lo llamo.*/
	public PanelLU(JFrame parent,String metodo){
		super(new Color(76, 123, 171),"Metodo Descomposición LU");
		this.parent = parent;
		this.metodo = metodo;
		setInformacion();
		setPanelDatos();
		setAllElements();
		generar_matriz.addActionListener(this);
		generar_vector.addActionListener(this);
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
					WindowMatriz proyect = new WindowMatriz(parent,true,reng,colm);
					proyect.addWindowListener(new WindowAdapter(){
						@Override
						public void windowClosed(WindowEvent e){
							matriz = proyect.getMatriz();
						}	
					});
				}
			}
			else
				JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca un numero de renglones y columnas."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
		}

		else if(e.getSource() == generar_vector){
			if(!renglones.getText().equals(" Renglones") && !columnas.getText().equals(" Columnas")){
				reng = Integer.parseInt(renglones.getText().trim());

				WindowVector proyect = new WindowVector(parent,true,reng);
				proyect.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent e){
						vector = proyect.getVector();
					}
				});
			}
		}

		else if(e.getSource() == calcular){
			if(matriz == null && vector != null){
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una matriz"
				+" por favor introduzca una matriz.","Error - App Análisis Numérico", JOptionPane.ERROR_MESSAGE);
			}

			else if(vector == null && matriz != null){
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido un vector"
				+" por favor introduzca un vector.", "Error - App Análisis Numérico", JOptionPane.ERROR_MESSAGE);
			}

			else if(matriz != null && vector != null){
				if(matriz.getRenglons() == vector.size()){
					lu = new LU(matriz,vector,metodo);
					iteraciones.setText(lu.getIteraciones());
					soluciones.setText(lu.getSoluciones());
				}
				else{
					JOptionPane.showMessageDialog(null,"Error. \nLo sentimos pero su vector no tiene el mismo numero de renglones"
					+" que su matriz por favor introduzca de nuevo la matriz y su vector.", "Error - App Análisis Numérico", JOptionPane.ERROR_MESSAGE);
					matriz = null;
					vector = null;
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una matriz ni un vector"
				+" por favor introduzca una matriz y su vector.", "Error - App Análisis Numérico", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if(e.getSource() == limpiar){
			renglones.setText(" Renglones");
			columnas.setText(" Columnas");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de LU según cual se utilice.*/
	private final void setInformacion(){

		info = "<html><body><h2 ALIGN = JUSTIFY>Método LU</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>La descomposición LU es una forma de expresar "
			+"las transformaciones del método Gauss-Jordan por medio de ecuaciones matriciales, lo que implica una reducción " 
			+"notable en las operaciones propias y, naturalmente, en el diseño del algortimo. Asimismo, se reduce notablemente "
			+"el impacto de la producción de errores debidos al pivoteo; adicionalmente, la descomposición LU puede utilizarse "
			+"para otros procesos como la obtención de la matriz inversa.<br>"
			+"<font size = 5><P>Definición del Método</P></font><br>"
			+"La descomposición LU consiste en encontrar dos matrices, L y U construidas de tal forma que se cumpla que:<br>"
			+"<font size = 5 COLOR = red><br> A&equiv; L * U </font><br>"
			+"<br>Las caracterı́sticas de las matrices L y U dependen de cada una de las versiones definidas para la descomposición:<br><br></P>";

		if(metodo.equals("Crout")){
			str_formulas = "L<sub>i</sub><sub>j</sub> = a<sub>i</sub><sub>j</sub> - &#931<sub>k=1</sub><sup>j-1</sup> L<sub>ik</sub>*U<sub>kj</sub>"
			+"<br>U<sub>i</sub><sub>j</sub> = (a<sub>i</sub><sub>j</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*U<sub>kj</sub>)/L<sub>ii</sub>"
			+"<br>b = (b<sub>i</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*b<sub>k</sub>)/L<sub>ii</sub>"
			+"<br>X = b<sub>j</sub> - &#931<sub>k=j+1</sub><sup>n</sup> U<sub>jk</sub>*b<sub>k</sub>";
			
			info += "<font COLOR = red size = 5><P>Versión Crout</P></font>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>En la versión Crout, la matriz L es una matriz triangular inferior de la forma 2 y la matriz U una "
			+"matriz triangular superior con elementos unitarios en la diagonal principal, según la forma 3.<br>"
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
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp U<sub>n</sub><sub>n</sub> &nbsp&nbsp|";
		}

		else if(metodo.equals("Doolittle")){
			str_formulas = "L<sub>i</sub><sub>j</sub> = (a<sub>i</sub><sub>j</sub> - &#931<sub>k=1</sub><sup>j-1</sup> L<sub>ik</sub>*U<sub>kj</sub>)/U<sub>ii</sub>"
			+"<br>U<sub>i</sub><sub>j</sub> = (a<sub>i</sub><sub>j</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*U<sub>kj</sub>)"
			+"<br>b = (b<sub>i</sub> - &#931<sub>k=1</sub><sup>i-1</sup> L<sub>ik</sub>*b<sub>k</sub>)/L<sub>ii</sub>"
			+"<br>X = b<sub>j</sub> - &#931<sub>k=j+1</sub><sup>n</sup> U<sub>jk</sub>*b<sub>k</sub>";
			
			info += "<font  COLOR = red size = 5><P>Versión Doolittle</P></font>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>La versión Doolittle define a las matrices L y U a la manera inversa que" 
			+"Crout; la matriz U es una matriz triangular inferior de la forma 1 y la matriz L una matriz triangular superior con elementos unitarios"
			+" en la diagonal principal, según la forma 1<br>"
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
			+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp|";
		}

		info +="<P ALIGN = JUSTIFY><br>Una vez que la matriz de coeficientes A ha sido transformada en sus matrices L y U es fácil obtener el solución de un sistema de ecuaciones. Seguramente "
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
