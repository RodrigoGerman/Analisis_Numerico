package controlador.theme3.matriz;

import vista.panels.theme3.PanelMethodMatriz;
import modelo.theme3.GaussJordan;
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

public final class PanelPivoteoGJ extends PanelMethodMatriz implements ActionListener{

	private GaussJordan pivoteo;

	private String metodo = "";

	private Matriz matriz= null;
	private ArrayList<Double> vector = null;
	private JFrame parent;

	public PanelPivoteoGJ(JFrame parent,String metodo){
		super(new Color(76, 123, 171),"Método de Pivoteo "+metodo);
		titulo_matriz.setText("Matriz Ampliada:");
		this.parent = parent;
		this.metodo = metodo;
		setInformacion();
		setPanelDatos();
		setAllElements();
		generar_matriz.addActionListener(this);
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

		else if(e.getSource() == calcular){
			if(matriz == null){
				JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una matriz"
				+" por favor introduzca una matriz.","Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
			}

			else{
				pivoteo = new GaussJordan(matriz,metodo);
				pivoteo.eliminacionGaussJordan();
				iteraciones.setText(pivoteo.getIteraciones());
				soluciones.setText(pivoteo.getSoluciones());
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
		+"<h2 COLOR = red>Método de Gauss-Jordan</h2>"
		+"Es una ampliación del método de Gauss con la diferencia que la matriz de coeficientes A se trasforma en la matriz identidad de tal "
		+"forma que se conserva una única incógnita por ecuación eliminando la sustición hacia atrás. Sea el sistema de ecuaciones lineales "
		+"Ax̄ = b̄, donde la matriz de coerficientes A corresponde a la forma de la matriz 3 y el vector de términos independientes b̄ corresponde "
		+"al vector 5 y la matriz ampliada corresponde al arreglo 6, por medio de las operaciones fundamentales debe trasnformarse en la matriz identidad de la forma:"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = center>"
		+"<font size = 4.5 COLOR = red> A : </font>"
		+"<br>|&nbsp 1 &nbsp 0 &nbsp 0 &nbsp .... &nbsp 0 &nbsp | &nbsp b'<sub>1</sub> &nbsp |"
		+"<br>|&nbsp 0 &nbsp 1 &nbsp 0 &nbsp .... &nbsp 0 &nbsp | &nbsp b'<sub>2</sub> &nbsp |"
		+"<br>|&nbsp 0 &nbsp 0 &nbsp 1 &nbsp .... &nbsp 0 &nbsp | &nbsp b'<sub>3</sub> &nbsp |"
		+"<br>|&nbsp ... &nbsp ... &nbsp ... &nbsp ... &nbsp ... &nbsp| &nbsp .... &nbsp |"
		+"<br>|&nbsp 0 &nbsp 0 &nbsp 0 &nbsp .... &nbsp 0 &nbsp | &nbsp b'<sub>n</sub> &nbsp |"
		+"<br><br>donde los valores b'<sub>i</sub> son los coeficientes modificados por la aplicación de las operaciones fundamentales. De nuevo, el procedimento utilizado es la "
		+"elección del pivote, la normalización de las ecuaciones y la eliminación de los elementos superiores e inferiores de la columna del pivote correspondiente.</P>";


		if(metodo.equals("Parcial")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: max |A(i,p)|"
			+"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp p &lt i &lt m </font><br><br>p = paso de eliminación<br>m = numero de renglones matriz";
			info += "<h2 COLOR = red ALIGN = center>Estrategía Pivoteo Parcial</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Esta estrategia consiste en ue cada paso de "
			+"eliminación 'p' se elige como pivote la entrada Diagonal con índices (i,p) de tal manera  que el valor "
			+"máximo entre los valores absolutos de la columna p-éisima empezando con (p,p) hasta (m,p) donde m es el " 
			+"número de renglones de la matriz.<br><br></P></body></html>";
		}

		else if(metodo.equals("Parcial Escalado")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: max |A(i,j)|"
			+"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp p &lt j &lt m  </font><br><br>p = paso de eliminación<br>m = numero de renglones matriz";
			info += "<h2 COLOR = red ALIGN = center>Estrategía Pivoteo Parcial Escalado</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Esta estrategia consiste en que supongamos que "
			+"estamos en el paso número 'p'. Para cada renglón i,con p &lt i &lt m, calculamos el valor máximo absoluto en "
			+"la parte principal de la matriz.<br><br></P></body></html>";
		}

		else if(metodo.equals("Parcial Diagonal")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: A(p,p) </font><br><br>p = paso de eliminación";
			info += "<h2 COLOR = red ALIGN = center>Estrategía Pivoteo Diagonal</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Esta estrategia consiste en que cada paso de "
			+"eliminación 'p' se elige como pivote la entrada Diagonal con índices (p,p).<br><br></P></body></html>";
		}

		else if(metodo.equals("Completo")){
			str_formulas = "<font size = 5 COLOR = red><br>Pivote: max |A(i,j)|"
			+"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp p-1 &lt i &lt m+1"
			+"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp p-1 &lt j &lt m+1</font>"
			+"<br><br>p = paso de eliminación<br>m = numero de renglones matriz";
			info += "<h2  COLOR = red ALIGN = center>Estrategía Pivoteo Completo</h2>"
			+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Esta estrategia consiste en que en el k-ésimo paso "
			+"de eliminación 'p' se buscan los índices p,q &#8712 {k,.....,m} tales que |A<sub>r</sub><sub>s</sub>| = max |A<sub>i</sub><sub>j</sub>|"
			+"donde (p-1) &lt i &lt (m+1) y (p-1) &lt j &lt (m+1), en otras palabras, se busca el máximo entrelos numeros |A<sub>i</sub><sub>j</sub>| con (p-1) &lt j &lt (m+1)"
			+"y (p-1) &lt j &lt (m+1). Si al obtener las coordenadas del pivote (r,s) son diferentes a las de (p,p), entonces se intercambian los renglones y las"
			+"columnas de tal manera que la entrada A<sub>r</sub><sub>s</sub> se coloque en la posición (p,p).<br><br></P></body></html>";
		}

		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}