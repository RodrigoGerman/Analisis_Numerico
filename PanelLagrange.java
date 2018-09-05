package controlador. theme4.tb;

import vista.panels.theme4.tb.PanelMethodTB;
import modelo.theme4.PolinomioLagrange;
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
    * La clase PanelLagrange es una clase que hereda de PanelMethodTB debido a que es un panel de método de análisis numérico con una función tabular e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Lagrande en una función tabular, se encarga de crear un panel específico para la visualización
    * del método numérico de Lagrange en una función tabular.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelLagrange extends PanelMethodTB implements ActionListener{
	/** * Variable tipo privada PolinomioLagrange que representa el método de Lagrange.*/
	private PolinomioLagrange langrange;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelLagrange.*/
	private JFrame parent;

	/** * Método constructor que crear un PanelLagrange pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelLagrange(){}

	/** * Método constructor que crea un PanelLagrange, recibe la ventana padre que lo llamo.*/
	public PanelLagrange(JFrame parent){
		super(new Color(76, 123, 171),"Método de Lagrange ");
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
				JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca el numero de puntos de su función tabular."
					,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
		}

		else if(e.getSource() == calcular){

			if(!getEmptyFields()){
				soluciones.setClear("");
				iteraciones.setClear("");
				langrange = new PolinomioLagrange(new FuncionTabular(func_list),Double.parseDouble(x.getText().trim()));
				soluciones.setText("Y: "+Double.toString(langrange.getSolucion()));
				iteraciones.setText(langrange.getIteraciones());
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

	/** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Lagrange en el PanelLagrange.*/
	private final void setInformacion(){
		str_formulas = "<p>Y = &sum<sub>i=1</sub><sup>n</sup>[&#928;<sub>j=1,j!=i</sub><sup>n</sup>(X &#8722;Xj)/(Xi &#8722;Xj)]Yi</p>";
		info = "<html><body><h2 COLOR = red ALIGN = center>Interpolacio&#769;n de Lagrange</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>La interpolacio&#769;n de Lagrange es un me&#769;todo realmente sencillo para resolver el problema de lainterpolacio&#769;n con espaciamiento variable;"
		+"este me&#769;todo tambie&#769;n puede aplicarse a funciones tabulares equiespaciadas. Asimismo, su simplicidad se traslada a un algoritmo muy simple de "
		+"programacio&#769;n.</p>"
		+"<p>2. Interpolacio&#769;n de Lagrange</p><p>Sea la funcio&#769;n tabular: En donde la variable independiente no necesariamente tiene incrementos constantes."
		+"Se busca un polinomio que pase por cada uno de los puntos de la funcio&#769;n tabular. Si la tabla contiene n puntos, el polinomio sera&#769; de grado n&#8722;1 "
		+"o menor. A partir de un tipo de diferencias denominadas diferencias divididas [2] que, en general, tienen la siguiente forma:</p>"
		+"<p>f [Xi &#8722;Xi&#8722;1] =f(Xi)&#8722; f(Xi&#8722;1)/Xi &#8722;Xi&#8722;1</p><p>Se propone un polinomio de la forma:</p>"
		+"<p>Y = A1(X &#8722;X2)(X &#8722;X3)(X &#8722;X4)...(X &#8722;Xn)+A2(X &#8722;X1)(X &#8722;X3)(X &#8722;X4)...(X &#8722;Xn)+A3(X &#8722;X1)(X &#8722;X2)(X &#8722;X4)...(X &#8722;Xn)+...An(X &#8722;X1)(X &#8722;X2)(X &#8722;X3)...(X &#8722;Xn&#8722;1)</p>"
		+"<p>La ecuacio&#769;n (1) es un polinomio de grado n&#8722;1; los coeficientes Ai deben determinarse de tal maneraque el polinomio pase por todos y cada uno de los puntos"
		+"de la funcio&#769;n tabular. Se propone evaluarla ecuacio&#769;n (1) en el punto X = X1:</p><p>Y1 = A1(X1 &#8722;X2)(X1 &#8722;X3)(X1 &#8722;X4)...(X1 &#8722;Xn)</p>"
		+"<p>Despejando la inco&#769;gnita A1:</p><p>A1 =Y1(X1 &#8722;X2)(X1 &#8722;X3)(X1 &#8722;X4)...(X1 &#8722;Xn)(2)</p><p>Valuando a 1 ahora en el punto X = X2 y "
		+"despejando a la inco&#769;gnita:</p><p>A2 =Y2(X2 &#8722;X1)(X2 &#8722;X3)(X2 &#8722;X4)...(X2 &#8722;Xn)(3)</p><p>Repitiendo el proceso consecutivamente hasta "
		+"llegar al punto X = Xn:</p><p>An =Yn(Xn &#8722;X1)(Xn &#8722;X2)(Xn &#8722;X3)...(Xn &#8722;Xn&#8722;1)(4)</p><p>Sustituyendo todos estos resultados en la "
		+"ecuacio&#769;n original 1:</p><p>Y = (X&#8722;X2)(X&#8722;X3)(X&#8722;X4)...(X&#8722;Xn)(X1&#8722;X2)(X1&#8722;X3)(X1&#8722;X4)...(X1&#8722;Xn)Y1+</p><p>(X&#8722;X1)(X&#8722;X3)(X&#8722;X4)...(X&#8722;Xn)(X2&#8722;X1)(X2&#8722;X3)(X2&#8722;X4)...(X2&#8722;Xn)Y2+</p>"
		+"<p>(X&#8722;X1)(X&#8722;X2)(X&#8722;X4)...(X&#8722;Xn)(X3&#8722;X1)(X3&#8722;X2)(X3&#8722;X4)...(X3&#8722;Xn)Y3+</p><p>...</p><p>(X&#8722;X1)(X&#8722;X2)(X&#8722;X3)...(X&#8722;Xn&#8722;1)(Xn&#8722;X1)(Xn&#8722;X2)(Xn&#8722;X3)...(Xn&#8722;Xn&#8722;1)Yn</p><p>(5)</p>"
		+"<p>Esta u&#769;ltima ecuacio&#769;n recibe el nombre de interpolacio&#769;n de Lagrange, en ella los pares de puntos (Xi, Yi) pertenecen a la funcio&#769;n tabular, X es el valor de la variable independiente para la cual "
		+"se desea el valor interpolado de Y.Se recuerda que no es necesario que los valores de la variableindependiente X este&#769;n equiespaciados. La fo&#769;rmula 5 puede expresarse en forma de series como:</p>"
		+"<p>Y = &sum<sub>i=1</sub><sup>n</sup>[&#928;<sub>j=1,j!=i</sub><sup>n</sup>(X &#8722;Xj)/(Xi &#8722;Xj)]Yi (6)</p>"
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}