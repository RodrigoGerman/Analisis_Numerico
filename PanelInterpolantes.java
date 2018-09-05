package controlador.theme4.tb;

import vista.panels.theme4.tb.PanelMethodTB;
import modelo.theme4.PolinomiosInterpolantes;
import controlador.WindowFuncionTabular;
import modelo.FuncionTabular; 

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;

/**
    * La clase PanelInterpolantes es una clase que hereda de PanelMethodTB debido a que es un panel de método de análisis numérico con una función tabular e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Polinomios Interpelantes en una función tabular, se encarga de crear un panel específico para la visualización
    * del método numérico de Polinomios Interpelantes en una función tabular.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/

public final class PanelInterpolantes extends PanelMethodTB implements ActionListener{
	/** * Variable tipo privada PolinomiosInterpolantes que representa el método de Interpelantes. */
	private PolinomiosInterpolantes interpolantes;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelLagrange.*/
	private JFrame parent;

	/** * Método constructor que crear un PanelLagrange pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelInterpolantes(){}

	/** * Método constructor que crea un PanelInterpolantes, recibe la ventana padre que lo llamo.*/
	public PanelInterpolantes(JFrame parent){
		super(new Color(76, 123, 171),"Método de Interpelantes");
		this.parent = parent;
		setInformacion();
		setPanelDatos();
		setAllElements();
		generar_funciontb.addActionListener(this);
		calcular.addActionListener(this);
		limpiar.addActionListener(this);
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
	@Override
	public void actionPerformed(ActionEvent e){
		int puntos = 0;
		if(e.getSource() == generar_funciontb){
			if(!num_puntos.getText().equals(" Puntos")){
				puntos = Integer.parseInt(num_puntos.getText().trim());

				if(puntos == 0 || puntos < 2){
					JOptionPane.showMessageDialog(null,"Advertencia. \nPor favor introduzca más de dos puntos."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
				}
				else{
					WindowFuncionTabular proyect = new WindowFuncionTabular(parent,true,puntos);
					proyect.addWindowListener(new WindowAdapter(){
						@Override
						public void windowClosed(WindowEvent e){
							func_list = proyect.getfunciontb();
						}	
					});
				}
			}
			else
				JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca el número de puntos de su función tabular."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
		}

		else if(e.getSource() == calcular){

			if(!getEmptyFields()){
				soluciones.setClear("");
				iteraciones.setClear("");
				interpolantes = new PolinomiosInterpolantes(new FuncionTabular(func_list),Double.parseDouble(x.getText().trim()));
				soluciones.setText("Y: "+Double.toString(interpolantes.getSolucion()));
				iteraciones.setText(interpolantes.getIteraciones());
			}
		}

		else if(e.getSource() == limpiar){
			num_puntos.setText(" Puntos");
			x.setText(" x");
			func_list = null;
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Lagrange en el PanelInterpolantes.*/
	private final void setInformacion(){
		str_formulas = "<p>Yk = Y0 + k&#916;Y0 + k(k &#8722; 1)/2!&#916<sup>2</sup>Y0 + k(k &#8722; 1)(k &#8722; 2)/3!&#916;<sup>3</sup>Y0 + ...</p>"
		+"k =(Xk &#8722;X0)/h"; 
		info = "<html><body><h2 COLOR = red ALIGN = center>Me&#769;todos de Interpolacio&#769n</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>1. Introduccio&#769;n</p><p>Los me&#769;todos de interpolacio&#769;n permiten procesar a las funciones tabulares sin la necesidad "
		+"de con-tar con su modelo anal&#769;&#305;tico, aunque tambie&#769;n permiten la obtencio&#769;n del modelo anal&#769;&#305;tico a partir de dichas "
		+"funciones, tomando en cuenta el comportamiento natural del feno&#769;meno y por lo tanto,permitiendo conocer una aproximacio&#769;n al error cometido.</p>"
		+"<p>Para el ana&#769;lisis nume&#769;rico, la interpolacio&#769;n consiste en construccio&#769;n de nuevos puntos partiendo de un conjunto de puntos en "
		+"forma de funcio&#769;n tabular; la interpolacio&#769;n es un proceso propio de lamatema&#769;tica discreta.</p>"
		+"<p>2. Tablas de diferencias finitas</p><p>Sea una funcio&#769;n Y = f(X) definida en forma tabular [3]. Esta funcio&#769;n tabular debe tener a su variable "
		+"independiente X equiespaciada, es decir, la diferencia entre cada valor consecutivo debeser constante: X0, X1 = X0 + h, X2 = X0 + 2h, X3 = X0 + 3h, ... , "
		+"Xn = X0 + nh, donde h es el espacio, comu&#769;nmente denominado paso y en todo caso, h = cte. Para cada uno de los puntos Xi se conoce el correspondiente "
		+"valor de la variable independiente Yi, de acuerdo al arreglo tabularmostrado en el cuadro 1.</p>"
		+"<p>Se les llama primeras diferencias hacia adelante a las diferencias entre dos valores consecutivos de Y y se denotan gene&#769;ricamente por &#916;Y"
		+"<p>A las diferencias de las primeras diferencias se les denomina segundas diferencias hacia adelante yse denotan gene&#769;ricamente por &#916;<sup>2</sup>Y</p>"
		+"<p>Ana&#769;logamente, a las diferencias de las segundas diferencias se les denomina terceras diferenciashacia adelante y se  denotan por &#916;<sup>3</sup>Y.</p>"
		+"<p>Siguiendo el mismo procedimiento se pueden calcular para los n puntos las n&#8722; 1 diferencias hacia adelante. Al nu&#769;mero de la diferencia se le "
		+"denomina orden de la diferencia. Al arreglo de la funcio&#769;ntabular y de sus diferencias se le llama tabla de diferencias.</p>"
		+"<p>3. Interpolacio&#769;n con incrementos constantes: polinomios interpolantes</p><p>La interpolacio&#769;n consiste en encontrar el valor de la funcio&#769;n "
		+"Y = f(X) para un valor ubicado entre dos valores consecutivos de X. Una manera de realizar la interpolacio&#769;n es admitir que f(X) se aproxima a un polinomio"
		+"que pasa por todos los puntos de la funcio&#769;n tabular. En consecuencia, el grado ma&#769;ximo del polinomio que pasa por n puntos es siempre n&#8722; 1."
		+"De acuerdo con lo anterior, a partir de la tabla de diferencias hacia adelante podemos definir lo siguiente:</p>"
		+"<p>Y1 = Y0 + a0 (1)</p><p>Y2 = Y1 + a1</p><p>sustituyendo los valores para Y1 de la ecuacio&#769;n (1) y de b0 = a1 &#8722; a0:</p><p>Y2 = Y0 + a0 + a0 + b0</p>"
		+"<p>Y2 = Y0 + 2a0 + b0 (2)</p><p>Y3 = Y2 + a2</p><p>sustituyendo en esta u&#769;ltima expresio&#769;n el resultado de la ecuacio&#769;n (2) y de b1 = a2&#8722; a1,"
		+"c0 = b1&#8722; b0y b0 = a1 &#8722; a0 :</p><p>Y3 = Y0 + 2a0 + b0 + b1 + a1Y3 = Y0 + 2a0 + b0 + c0 + b0 + b0 + a0</p><p>Y3 = Y0 + 3a0 + 3b0 + c0 (3)</p>"
		+"<p>Repitiendo el proceso:Y4 = Y0 + 4a0 + 6b0 + 4c0 + d0 (4)</p><p>Se observa en las ecuaciones (1) a (4) co&#769;mo aparecen las primeras diferencias de o&#769;rdenes"
		+"sucesivos afectadas por los coeficientes del desarrollo del binomio de Newton, por lo que para el valor de Yk:</p><p>Yk = Y0 + <sub>k</sub>C<sub>1</sub>a0 "
		+"+<sub>k</sub>C<sub>2</sub>b0 + <sub>k</sub>C<sub>3</sub>c0 + <sub>k</sub>C<sub>4</sub>d0 + ... (5)</p><p>Donde: a0 = &#916;Y0, "
		+"b0 = &#916;<sup>2</sup>Y0, c0 = &#916;<sup>3</sup>Y0 y as&#769;&#305; consecutivamente, y donde por definicio&#769;n:</p><p><sub>k</sub>C<sub>i</sub>=k!"
		+"/(k &#8722; i)!*i! = k(k &#8722; 1)(k &#8722; 2)...(k &#8722; i+ 1)/i!(6)</p><p>Resultando entonces:</p><p>Yk = Y0 + k&#916;Y0 +k(k &#8722; 1)/2!&#916;<sup>2</sup>Y0 + "
		+"k(k &#8722; 1)(k &#8722; 2/3!&#916<sup>3</sup>Y0 + ... (7)</p><p>Las ecuaciones (5) y (6) son conocidas como el Polinomio interpolante (o fo&#769;rmula de avance) de "
		+"Newton-Gregory. El valor de Yk es un valor aproximado de la funcio&#769;n valuada en Xk; Y0 es el valor inicial considerado el inmediato anterior a donde se estima esta&#769;"
		+"el valor a interpolar; &#916;Y0, &#916;<sup>2</sup>Y0,&#916;<sup>3</sup>Y0, etc.<br>Son las diferencias hacia adelante correspondientes al valor Y0 seleccionado de la tabla de diferencias.</p>"
		+"<p>Resta obtener el valor de la variable k. Para ello se analizara&#769; la funcio&#769;n tabular u&#769;nicamente en sus dos puntos iniciales.</p>"
		+"<p>El proceso de interpolacio&#769;n consiste en encontrar el valor de Yk para un valor Xk ubicado entre los dos puntos anteriores."
		+"Considerando estos dos puntos, la u&#769;nica forma geome&#769;trica que puede formarse con ellos es una l&#769;&#305;nea recta que se obtiene de la "
		+"ecuacio&#769;n (6) trunca&#769;ndola a la primera diferencia</p><p>Yk = Y0 + k&#916;Y0 (8)</p><p>Xk = X0 + kh</p><p>Finalmente, despejando a k:</p><p>k =(Xk &#8722;X0)/h (9)</p>"
		+"<p>Donde Xk es el valor de X para el que se desea interpolar, X0 el el valor de X correspondiente a Y0 y h es el paso que siempre es constante.</p>"
		+"<p>Siguiendo procedimientos similares, pero considerando incluso alternativas tales como definir diferencias hacia atra&#769;s, pueden definirse polinomios "
		+"interpolantes similares."
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}