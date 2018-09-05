package controlador.theme3.matrizvector;

import vista.panels.theme3.PanelMethodMatrizVector;
import modelo.theme3.Jacobi;
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
    * La clase PanelJacobi es una clase que hereda de PanelMethodMatrizVector debido a que es un panel de método de análisis numérico que utiliza una matriz y un vector, implementa
    * la interfaz ActionListener para ejecutar el método numérico Jacobi, se encarga de crear un panel específico para la visualización
    * del método numérico Jacobi.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelJacobi extends PanelMethodMatrizVector implements ActionListener{
	/** * Variable tipo Jacobi privada que representa el método de Jacobi.*/
	private Jacobi jacobi;
	/** * Variable tipo Matriz privada que representa la matriz ingresada por el usuario.*/
	private Matriz matriz = null;
	/** * Variable tipo ArrayList<Double> privada que representa el vector ingresado por el usuario.*/
	private ArrayList<Double> vector = null;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelJacobi.*/
	private JFrame parent;

	/** * Método constructor que crear un PanelJacobi pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelJacobi(){}

	/** * Método constructor que crea un PanelJacobi, recibe la ventana padre que lo llamo.*/
	public PanelJacobi(JFrame parent){
		super(new Color(76, 123, 171),"Método Jacobi");
		this.parent = parent;
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
			else
				JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca una matriz primero."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
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
					jacobi = new Jacobi(matriz,vector);
					iteraciones.setText(jacobi.getIteraciones());
					soluciones.setText(jacobi.getSoluciones());
				}
				else{
					JOptionPane.showMessageDialog(null,"Error. \nLo sentimos, pero su vector no tiene el mismo numero de renglones"
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

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Jacobi en el PanelJacobi.*/
	private final void setInformacion(){
		str_formulas = "<font size = 4 COLOR = red><br>X̄^(k+1)= (b<sub>n</sub> −(a<sub>n</sub><sub>1</sub>*X^(k<sub>2</sub>)+a<sub>n</sub><sub>2</sub>*X^(k<sub>3</sub>) +...+a<sub>n</sub><sub>n-1</sub>*X<sub>n-1</sub>^(k))/a<sub>n</sub><sub>n</sub>"
		+"<br><br>θ = sqrt((X̄<sub>1</sub>^(k+1)-X̄<sub>1</sub>^(k))^2+(X̄<sub>2</sub>^(k+1)-X̄<sub>2</sub>^(k))^2+(X̄<sub>3</sub>^(k+1)-X̄<sub>3</sub>^(k))^2+....(X̄<sub>n</sub>^(k+1)-X̄<sub>n</sub>^(k))^2)</font><br>";

		info = "<html><body><h2 ALIGN = JUSTIFY>Método Jacobi</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY> Los métodos de Jácobi y de Gauss-Seidel son los equivalentes en la solución "
		+"de sistemas de ecuaciones lineales al método de aproximaciones sucesivas en la solución de ecuaciones algebraicas y trascendentes. "
		+"En realidad, estos métodos representan una adaptación vectorial de un proceso escalar, lo queimplica la necesidad de ádaptar los conceptos necesarios: "
		+"los procesos iterativos se detienen cuando entre dos aproximaciones consecutivas se cumple con determinado error preestablecido. En este caso, deberá "
		+"medirse la norma entre dos vectores para reconocer el momento en que se satisface la cota de error. Por otra parte, resta el hecho de tener que evaluar "
		+"un criterio de equivalencia el cual, naturalmente, tendrá caracter vectorial.<br>"
		+"<font size = 5><P>Definición del Método</P></font><br>"
		+"Sea el sistema de ecuaciones lineales AX̄ = b̄, donde A es la matriz de coeficientes, X̄ es el vector de incógnitas y b̄ el vector de términos independientes.<br>"
		+"<font size = 4.5 COLOR = red><br> AX̄ = b &nbsp&nbsp&nbsp&nbsp (1) </font><br>"
		+"<br>En la ecuación 1 se puede sustituir a la matriz A por la suma de dos matrices:<br>"
		+"<font size = 4.5 COLOR = red><br> A = D + R &nbsp&nbsp&nbsp&nbsp (2) </font><br>"
		+"<br>En donde la matriz D es una matriz cuyos elementos son cero excepto los elementos de la diagonal que corresponden a los elementos de la matriz A y R que "
		+"es una matriz con ceros en la diagonal y sus restantes elementos coindicen con los respectivos de A.<br></P>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<font size = 4.5 COLOR = red><br> A : </font><br>"
		+"|&nbsp a<sub>1</sub><sub>1</sub> &nbsp&nbsp 0 &nbsp&nbsp&nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
		+"<br>|&nbsp L<sub>2</sub><sub>1</sub> &nbsp L<sub>2</sub><sub>2</sub> &nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
		+"<br>|&nbsp L<sub>3</sub><sub>1</sub> &nbsp L<sub>3</sub><sub>2</sub> &nbsp L<sub>3</sub><sub>3</sub> &nbsp .... &nbsp 0 &nbsp&nbsp|"
		+"<br>|&nbsp&nbsp ..... &nbsp&nbsp .... &nbsp&nbsp .... &nbsp .... &nbsp .... &nbsp&nbsp|"
		+"<br>|&nbsp L<sub>n</sub><sub>1</sub> &nbsp L<sub>n</sub><sub>2</sub> &nbsp L<sub>n</sub><sub>3</sub> &nbsp .... &nbsp L<sub>n</sub><sub>n</sub>|<br>"
		+"<font size = 4.5 COLOR = red><br> R : </font><br>"
		+"|&nbsp a<sub>1</sub><sub>1</sub> &nbsp&nbsp 0 &nbsp&nbsp&nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
		+"<br>|&nbsp L<sub>2</sub><sub>1</sub> &nbsp L<sub>2</sub><sub>2</sub> &nbsp 0 &nbsp &nbsp .... &nbsp&nbsp 0 &nbsp&nbsp|"
		+"<br>|&nbsp L<sub>3</sub><sub>1</sub> &nbsp L<sub>3</sub><sub>2</sub> &nbsp L<sub>3</sub><sub>3</sub> &nbsp .... &nbsp 0 &nbsp&nbsp|"
		+"<br>|&nbsp&nbsp ..... &nbsp&nbsp .... &nbsp&nbsp .... &nbsp .... &nbsp .... &nbsp&nbsp|"
		+"<br>|&nbsp L<sub>n</sub><sub>1</sub> &nbsp L<sub>n</sub><sub>2</sub> &nbsp L<sub>n</sub><sub>3</sub> &nbsp .... &nbsp L<sub>n</sub><sub>n</sub>|<br>"
		+"<font size = 4.5 COLOR = red><br> D : </font><br>"
		+"|&nbsp 1 &nbsp&nbsp U<sub>1</sub><sub>2</sub> &nbsp&nbsp U<sub>1</sub><sub>3</sub> &nbsp &nbsp .... &nbsp&nbsp U<sub>1</sub><sub>n</sub> &nbsp&nbsp|"
		+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp U<sub>2</sub><sub>3</sub> &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp U<sub>2</sub><sub>n</sub> &nbsp&nbsp|"
		+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp U<sub>3</sub><sub>n</sub> &nbsp&nbsp|"
		+"<br>|&nbsp&nbsp&nbsp ..... &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp .... &nbsp&nbsp .... &nbsp&nbsp|"
		+"<br>|&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp&nbsp U<sub>n</sub><sub>n</sub> &nbsp&nbsp|"
		+"<br><br><br> Sustituyendo la ecuación 2 en la ecuación 1:<br>"
		+"<font size = 4.5 COLOR = red><br> (D + R) * X̄ = b̄ <br><br> DX̄ + RX̄ = b̄ </font><br>"
		+"<br>despejando el término DX̄:<br>"
		+"<font size = 4.5 COLOR = red><br> DX̄ = b̄ − RX̄̄ </font><br>"
		+"<br>Premultiplicando por la matriz D^-1<br>"
		+"<font size = 4.5 COLOR = red><br> D^-1 * DX̄ = D^-1 * ( b̄ − RX̄) </font><br>"
		+"<br>Resulta:<br>"
		+"<font size = 4.5 COLOR = red><br> X̄ = D^-1 * ( b̄ − RX̄) </font><br>"
		+"<br>La ecuación anterior no aporta una solución por sı́ misma, si se observa desde la óptica del álgebra matricial. Sin embargo, si se aplica desde "
		+"una forma recursiva:<br>"
		+"<font size = 4.5 COLOR = red><br> X̄^(k+1) = D^-1 * ( b̄ − RX̄^(k))</font>"
		+"<br><br> A despejar a la incógnita de ubicada en la diagonal principal de cada una de las ecuaciones que conforman el sistema, de la siguiente forma:<br>"
		+"<font size = 4.5 COLOR = red><br>X̄^(k+1)= b<sub>n</sub> −(a<sub>n</sub><sub>1</sub>*X^(k<sub>2</sub>)+a<sub>n</sub><sub>2</sub>*X^(k<sub>3</sub>) +...+a<sub>n</sub><sub>n-1</sub>*X<sub>n-1</sub>^(k)/a<sub>n</sub><sub>n</sub>  (8)</font>"
		+"<br><br>El método de Jácobi propone que el vector inicial X̄ 0 sea igual a cero. A partir de esta propuesta, el vector siguiente será X̄^1 = b<sub>i</sub>i/aa<sub>i</sub><sub>i</sub>, "
		+"es decir, el elemento independiente entre el coeficiente de la diagonal principal para cada ecuación."
		+"<font size = 4.5 COLOR = red><br><br> X̄^(1) : </font>"
		+"<br>|&nbsp b<sub>1</sub>/a<sub>1</sub><sub>1</sub> &nbsp|"
		+"<br>|&nbsp b<sub>2</sub>/a<sub>2</sub><sub>2</sub> &nbsp|"
		+"<br>|&nbsp b<sub>3</sub>/a<sub>3</sub><sub>3</sub> &nbsp|"
		+"<br>|&nbsp .......... &nbsp&nbsp |"
		+"<br>|&nbsp b<sub>n</sub>/a<sub>n</sub><sub>n</sub> &nbsp|"
		+"<br><br> Este vector X̄^(1) se sustituye en las ecuación 8 obteniéndose el siguiente vector X^(2) . El proceso se realiza consecutivamente hasta que"
		+"la norma entre dos vectores consecutivos es menor que cierta tolerancia preestablecida."
		+"La norma θ se calcula como:<br>"
		+"<font size = 4.5 COLOR = red><br>θ = sqrt((X̄<sub>1</sub>^(k+1)-X̄<sub>1</sub>^(k))^2+(X̄<sub>2</sub>^(k+1)-X̄<sub>2</sub>^(k))^2+(X̄<sub>3</sub>^(k+1)-X̄<sub>3</sub>^(k))^2+....(X̄<sub>n</sub>^(k+1)-X̄<sub>n</sub>^(k))^2)</font>"
		+"</font><br><br><br></P></body></html>";
	
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}
