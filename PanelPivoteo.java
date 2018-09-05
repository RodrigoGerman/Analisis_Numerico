package controlador.theme3.matrizvector;

import vista.panels.theme3.PanelMethodMatrizVector;
import modelo.theme3.Gauss;
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

public final class PanelPivoteo extends PanelMethodMatrizVector implements ActionListener{

	private Gauss pivoteo;

	private String metodo = "";

	private Matriz matriz= null;
	private ArrayList<Double> vector = null;
	private JFrame parent;

	public PanelPivoteo(JFrame parent,String metodo){
		super(new Color(76, 123, 171),"Método de Pivoteo "+metodo);
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
				+" por favor introduzca una matriz.","Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
			}

			else if(vector == null && matriz != null){
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido un vector"
				+" por favor introduzca un vector.","Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
			}

			else if(matriz != null && vector != null){
				if(matriz.getRenglons() == vector.size()){
					pivoteo = new Gauss(matriz,vector,metodo);
					pivoteo.eliminacionGauss();
					iteraciones.setText(pivoteo.getIteraciones());
					soluciones.setText(pivoteo.getSoluciones());
				}
				else{
					JOptionPane.showMessageDialog(null,"Error. \nLo sentimos pero su vector no tiene el mismo numero de renglones"
					+" que su matriz por favor introduzca de nuevo la matriz y su vector.","Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
					matriz = null;
					vector = null;
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una matriz ni un vector"
				+" por favor introduzca una matriz y su vector.","Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
			}
		}

		else if(e.getSource() == limpiar){
			renglones.setText(" Renglones");
			columnas.setText(" Columnas");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	private final void setInformacion(){
		info = "<html><body><h2 COLOR = red ALIGN = center>Estrategía Pivoteo</h2>"
		+"Los sistemas de ecuaciones son herramientas imprescindibles en la práctica de la Ingenierı́a. Se "
		+"utilizan para modelos diversos fenómenos fı́sicos que involucran una multitud de variables y que su "
		+"comportamiento implica una estrecha relación entre ellas."
		+"<br><br>La solución de circuitos a través de las Leyes de Kirchhoff, el análisis estructural, la investigación "
		+"de operaciones son sólo unos pocos ejemplos de la importancia que reviste el uso de los sistemas de "
		+"ecuaciones."
		+"<br><br>La solución de un sistema de ecuaciones lineales es un conjunto n de valores que satisfacen "
		+"simultáneamente a un grupo de ecuaciones. En la solución de dicho sistema se presentan tres casos:<br><br>"
		+"1. Que el sistema no tenga solución finita. Se dice entonces que el sistema es incompatible.<br><br>"
		+"2. Que el sistema tenga solución finita única. En tal caso se dice que el sistema es compatible determinado.<br><br>"
		+"3. Que tenga más de una solución. El sistema es entonces compatible indeterminado.<br><br>"
		+"La técnica fundamental para encontrar la solución "
		+"de un sistema de ecuaciones es el de la eliminación; dicho proceso consiste en transformar el sistema original en sistemas "
		+"equivalentes aplicando las tres operaciones fundamentales sobre las ecuaciones del sistema que son:<br>"
		+"1. Intercambiar dos ecuaciones del sistema.<br><br>"
		+"2. Multiplicar una ecuación del sistema por un escalar diferente de ceroAnálisis numérico.<br><br>"
		+"3. Multiplicar una ecuación del sistema por un escalar diferente de cero y sumar el resultado a otra ecuación del sistema.<br><br>"
		+"Con base en estas tres operaciones fundamentales se definen varios métodos de solución. Cabe indicar que en el desarrollo "
		+"de estos métodos y en la práctica de las operaciones se utilizan diversos recursos del álgebra matricial, entre ellos el "
		+"uso de la matriz ampliada o la trasnformación hacia la matriz identidad."
		+"<h2 COLOR = red>Método de Gauss</h2>"
		+"A través de las operaciones fundamentales aplicadas a la matriz de coeficientes A y al vector de términos independientes b̄  "
		+"(para mantener la igualdad en las ecuaciones), se convierte a la matriz A en una matriz tiangular superior donde la diagonal "
		+"principal la constituyen números 1. Esta transformación implica que en la ecuación n aparezca una sóla incógnita, dos en la "
		+"ecuación n − 1, tres en la n − 2 y ası́ consecutivamente para después realizar una sustitución hacia atrás."
		+"Sea el sistema de ecuaciones lineales Ax̄ = b̄, forma de la matriz 3 y el vector de términos ampliada del sistema es:"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = center>"
		+"<font size = 4.5 COLOR = red><br> A : </font>"
		+"<br>|&nbsp a<sub>1</sub><sub>1</sub> &nbsp a<sub>1</sub><sub>2</sub> &nbsp a<sub>1</sub><sub>3</sub> &nbsp .... &nbsp a<sub>1</sub><sub>n</sub> &nbsp | &nbsp b<sub>1</sub> &nbsp |"
		+"<br>|&nbsp a<sub>2</sub><sub>1</sub> &nbsp a<sub>2</sub><sub>2</sub> &nbsp a<sub>2</sub><sub>3</sub> &nbsp .... &nbsp a<sub>4</sub><sub>n</sub> &nbsp | &nbsp b<sub>2</sub> &nbsp |"
		+"<br>|&nbsp a<sub>3</sub><sub>1</sub> &nbsp a<sub>3</sub><sub>2</sub> &nbsp a<sub>3</sub><sub>3</sub> &nbsp .... &nbsp a<sub>3</sub><sub>n</sub> &nbsp | &nbsp b<sub>3</sub> &nbsp |"
		+"<br>|&nbsp ...... &nbsp ...... &nbsp ...... &nbsp ..... &nbsp ..... &nbsp | &nbsp .... &nbsp |"
		+"<br>|&nbsp a<sub>n</sub><sub>1</sub> &nbsp a<sub>n</sub><sub>2</sub> &nbsp a<sub>n</sub><sub>3</sub> &nbsp .... &nbsp a<sub>n</sub><sub>n</sub> &nbsp | &nbsp b<sub>n</sub> &nbsp |"
		+"<br><br>Por medio de las operaciones fundamentales debe transformarse en una matriz ampliada triangular superior de la forma:"
		+"<font size = 4.5 COLOR = red><br><br> A : </font>"
		+"<br>|&nbsp&nbsp 1 &nbsp&nbsp a'<sub>1</sub><sub>2</sub> &nbsp&nbsp a'<sub>1</sub><sub>3</sub> &nbsp&nbsp .... &nbsp&nbsp a'<sub>1</sub><sub>n</sub> &nbsp&nbsp | &nbsp b'<sub>1</sub> &nbsp |"
		+"<br>|&nbsp&nbsp 0 &nbsp&nbsp 1 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp a'<sub>2</sub><sub>3</sub> &nbsp&nbsp .... &nbsp&nbsp&nbsp a'<sub>2</sub><sub>n</sub> &nbsp&nbsp | &nbsp b'<sub>2</sub> &nbsp |"
		+"<br>|&nbsp&nbsp 0 &nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp a'<sub>3</sub><sub>n</sub> &nbsp&nbsp | &nbsp b'<sub>3</sub> &nbsp |"
		+"<br>|&nbsp&nbsp ..... &nbsp&nbsp ..... &nbsp&nbsp ..... &nbsp&nbsp ..... &nbsp&nbsp&nbsp ..... &nbsp&nbsp&nbsp&nbsp| &nbsp ..... &nbsp |"
		+"<br>|&nbsp&nbsp 0 &nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 0 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .... &nbsp&nbsp&nbsp a'<sub>n</sub><sub>n</sub> &nbsp&nbsp | &nbsp b'<sub>n</sub> &nbsp |"
		+"<br><br>donde los valores a a'<sub>i</sub><sub>j</sub> y b'<sub>i</sub> son los coeficientes modificados por la aplicación de las operaciones fundamentales.</P>"
		+"<br>Este procedimiento se logra tomando cada uno de los elementos de la diagonal principal de la matriz A original, al cual se le denomina pivote y después normalizando la ecuación con dicho valor."
		+"Posteriormente, a través de las operaciones fundamentales, se hace la eliminación de los elementos inferiores de la columna correspondiente al pivote. Una vez realizada la transformación se realiza "
		+"la sustitución hacia atrás obteniéndose el valor de cada una de  las incógnitas.";

		if(metodo.equals("Parcial")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: max |A(i,p)|"
			+"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp p &lt i &lt m </font><br><br>p = paso de eliminación<br>m = numero de renglones matriz";
			info += "<br><br><h2 COLOR = red ALIGN = center>Estrategía Pivoteo Parcial</h2>"
			+"Esta estrategia consiste en ue cada paso de "
			+"eliminación 'p' se elige como pivote la entrada Diagonal con índices (i,p) de tal manera  que el valor "
			+"máximo entre los valores absolutos de la columna p-éisima empezando con (p,p) hasta (m,p) donde m es el " 
			+"número de renglones de la matriz.<br><br></P></body></html>";
		}

		else if(metodo.equals("Parcial Escalado")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: max |A(i,j)|"
			+"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp p &lt j &lt m  </font><br><br>p = paso de eliminación<br>m = numero de renglones matriz";
			info += "<html><body><h2 COLOR = red ALIGN = center>Estrategía Pivoteo Parcial Escalado</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Esta estrategia consiste en que supongamos que "
			+"estamos en el paso número 'p'. Para cada renglón i,con p &lt i &lt m, calculamos el valor máximo absoluto en "
			+"la parte principal de la matriz.<br><br></P></body></html>";
		}

		else if(metodo.equals("Parcial Diagonal")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: A(p,p) </font><br><br>p = paso de eliminación";
			info += "<html><body><h2 COLOR = red ALIGN = center>Estrategía Pivoteo Diagonal</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Esta estrategia consiste en que cada paso de "
			+"eliminación 'p' se elige como pivote la entrada Diagonal con índices (p,p).<br><br></P></body></html>";
		}

		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}