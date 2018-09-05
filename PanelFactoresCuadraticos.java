
package controlador.theme2.openmethods;

import vista.panels.theme2.PanelMethodsTheme2;
import modelo.theme2.openmethods.FactoresCuadraticos;
import vista.accesories.TextFieldPolinomio;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

/**
    * La clase PanelFactoresCuadraticos es una clase que hereda de PanelMethodsTheme2 debido a que es un panel de métodos de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Factores Cuadráticos, se encarga de crear un panel específico para la visualización
    * del método numérico de Factores Cuadráticos.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelFactoresCuadraticos extends PanelMethodsTheme2 implements ActionListener{
    /** * Variable tipo privada FactoresCuadraticos que representa el método de Factores Cuadráticos.*/
    FactoresCuadraticos cuadraticos = null;
    /** * Variable tipo TextFieldFuncion privada y final, que representa el campo para que el usuario pueda introducir el polinomio.*/
    TextFieldPolinomio polinomio = new TextFieldPolinomio(" Polinomio",Color.BLACK);

    /** * Método constructor que crear un PanelFactoresCuadraticos pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelFactoresCuadraticos(){}

	/** * Método constructor que crea un PanelFactoresCuadraticos, recibe el color del fondo del panel.*/
	public PanelFactoresCuadraticos(Color fondo){
		super(fondo,"Factores Cuadráticos");
		setPanelDatos();
		setInformacion();
		setAllElements();
		calcular.addActionListener(this);
		limpiar.addActionListener(this);
	}

	/** * Método final heredado de PanelMethodsTheme2 que se encarga de validar si los campos a llenar en el PanelFactoresCuadraticos no están vacíos.*/
	protected boolean getEmptyFields(){
		if((polinomio.getText()).equals(" Polinomio")){
			JOptionPane.showMessageDialog(null,"Error. \nIngrese un Polinomio por favor."
			,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if((estimacion.getText()).equals(" Estimación")){
			JOptionPane.showMessageDialog(null,"Error. \nIngrese un error de estimación por favor."
			,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	 /** * Método final heredado de PanelMethodsTheme2 que se encarga de validar si los campos a llenar en el PanelFactoresCuadraticos son validos.*/
	protected boolean getFieldsValids(){
		if(!polinomio.getCorrectFuncion()){
			JOptionPane.showMessageDialog(null,"Error. \nSu Polinomio tiene errores sintácticos revíselo por favor."
			,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(!estimacion.getCorrectNumber()){
			JOptionPane.showMessageDialog(null,"Error. \nIngrese una estimación valida por favor."
			,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/** * Método protected y final que se encarga de controlar la distribución de los compontes visuales del PanelFactoresCuadraticos.*/
	protected final void setPanelDatos(){
		panel_datos.setBackground(fondo);
		panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

		titulo_funcion.setFont(titulo); 
		titulo_funcion.setForeground(titulos);
		polinomio.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		titulo_estimacion.setFont(titulo); 
		titulo_estimacion.setForeground(titulos);
		estimacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		calcular.setBackground(titulos);
		calcular.setBorder(BorderFactory.createLineBorder(fondo,2));
		limpiar.setBackground(titulos);
		limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);
       	panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(estimacion)
                        .addComponent(polinomio,GroupLayout.Alignment.TRAILING)
                        .addComponent(formulas)
                        .addGroup(panel_datosLayout.createSequentialGroup()
                            .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titulo_funcion)
                                .addComponent(titulo_estimacion))
                            .addGap(0,0,Short.MAX_VALUE))))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap(56,Short.MAX_VALUE)
                        .addComponent(calcular,GroupLayout.PREFERRED_SIZE,81,GroupLayout.PREFERRED_SIZE)
                        .addGap(34,34,34)
                        .addComponent(limpiar,GroupLayout.PREFERRED_SIZE,81,GroupLayout.PREFERRED_SIZE)
                        .addGap(33,33,33)))
                .addContainerGap())
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(polinomio,GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_estimacion)
                .addGap(18, 18, 18)
                .addComponent(estimacion, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(22,22,22))
        );
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
	@Override
	public void actionPerformed(ActionEvent e){
		double valor_estimacion = 0,valor_a = 0,valor_b = 0; 
		if(e.getSource() == calcular){
			if(getEmptyFields() && getFieldsValids()){
				valor_estimacion = Double.parseDouble(estimacion.getText().trim());
				if(valor_estimacion > 0.000000000000001){
					cuadraticos = new FactoresCuadraticos((polinomio.getText()).trim(),valor_estimacion);
					iteraciones.setClear("");
					iteraciones.setText(cuadraticos.getIteraciones());
					raiz.setText(cuadraticos.getRaices());
				}
				else
					JOptionPane.showMessageDialog(null,"Error. Lo sentimos pero la estimacion es demasiada pequeña, el programa"+
					"\nno soporta esa cantidad de puntos decimales, por favor introduzca una mayor a 0.000000000000001."
					,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == limpiar){
			polinomio.setText(" Función");
			estimacion.setText(" Estimación");
			raiz.setText("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de informacion sobre el metodo de Factores Cuadraticos en el PanelFactoresCuadraticos.*/
	private final void setInformacion(){
		str_formulas = "<p>b<sub>k</sub>= a<sub>k</sub> - Qb<sub>k-1</sub> - Pb<sub>k-2</sub><p>R = a<sub>n-1</sub> - Pb<sub>n-2</sub>  -Qb<sub>n-3</sub></p>"
		+"<p>S = a<sub>n</sub> - Qb<sub>n-2</sub>(7)</p><p>&#916;Q =S/b<sub>n-2</sub></p><p>&#916;P =R/b<sub>n-2</sub></p>";
		formulas.setInfo(str_formulas);
		info = "<html><body><h2 ALIGN = center>Me&#769;todo de Factores cuadra&#769;ticos</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>1. Introduccio&#769;n</p><p>Los me&#769;todos de factores cuadra&#769;ticos son una alternativa ma&#769;s eficiente que la popular divisio&#769;n "
		+"sinte&#769;tica para obtener ra&#769;&#305;ces de polinimios. En cierta forma, la divisio&#769;n sinte&#769;tica es un me&#769;todo basado en el "
		+"tanteo, ya que la seleccio&#769;n de la posible ra&#769;&#305;z del polinomio se hace buscando que el residuo de la divisio&#769;n tienda, o en el "
		+"mejor de los casos, sea cero. Asimismo, si las capacidades algebraicas lo permiten, es posible obtener ra&#769;&#305;ces complejas.</p>"
		+"<p>En cambio, los me&#769;todos de factores cuadra&#769;ticos no requieren de estimaciones emp&#769;&#305;ricas; consistenen extraer de polinomios "
		+"de grado mayor a dos pares de ra&#769;&#305;ces en la forma de factores del tipo de x<sup>2</sup> + Px + Q; estos factores pueden resolverse por la "
		+"fo&#769;rmula general para ecuaciones de segundo grado, lo que permite fa&#769;cilmente obtener las ra&#769;&#305;ces complejas.</p>"
		+"<p>Si el polinomio es de grado n, debera&#769;n extrae&#769;rsele ra&#769;&#305;ces de dos en dos hasta que se agote el procedimiento. Como se percibe, "
		+"este me&#769;todo so&#769;lo se aplica a polinomios y proporciona ra&#769;&#305;ces reales o complejas.</p><p>En este caso, se desarrolla el me&#769;todo "
		+"de factores cuadra&#769;ticos denominado Me&#769;todo de Lin.</p>"
		+"<p>2. Definicio&#769;n del me&#769;todo</p><p>Sea el polinomio:</p><p>P (x) = a<sub>0</sub>x<sup>n</sup> + a<sub>1</sub><sup>n-1</sup> + a<sub>2</sub><sup>n-2</sup>"
		+"a<sub>3</sub><sup>n-3</sup> + ...+ a<sub>n-1</sub>x + a<sub>n</sub> (1)</p>"
		+"<p>El me&#769;todo consiste en dividir el polinomio P (x) entre el factor x<sup>2</sup> + Px + Q, a diferencia de la divisio&#769;n sinte&#769;tica que lo "
		+"hace entre el factor x&#8722;a. Al llevarse a cabo la divisio&#769;n propuesta se obtiene un polinomio de la forma:</p>"
		+"<p> b<sub>0</sub>x<sup>n-2</sup> + b<sub>1</sub><sup>n-3</sup> + b<sub>2</sub><sup>n-4</sup> + b<sub>3</sub><sup>n-5</sup> + ...+ b<sub>n-3</sub>x + b<sub>n-2</sub> (2)</p>"
		+"<p>b0xn&#8722;2 + b1xn&#8722;3 + b3xn&#8722;4 + ...+ bn&#8722;3x+ bn&#8722;2 (2)</p><p>Asimismo, en consecuencia existira&#769; un residuo de la forma Rx + S."
		+"En resumen, podemos afirmar que:</p>"
		+"<p>P (x).= (x<sup>2</sup> + px + q)(b<sub>0</sub>x<sup>n-2</sup> + b<sub>1</sub><sup>n-3</sup> + b<sub>2</sub><sup>n-4</sup> + b<sub>3</sub><sup>n-5</sup> + ...+ b<sub>n-3</sub>x + b<sub>n-2</sub>) + Rx + S (3)</p>"
		+"<p>Realizando las operaciones planteadas en la ecuacio&#769;n (3):</p>"
		+"<p>P (x).= b<sub>0</sub>x<sup>n</sup> + Pb<sub>0</sub><sup>n-1</sup> + Qb<sub>0</sub><sup>n-2</sup> + b<sub>1</sub><sup>n-1</sup> + Pb<sub>1</sub><sup>n-2</sup> + Qb<sub>1</sub><sup>n-2</sup> + ...+ b<sub>n-3</sub>x "
		+"Pb<sub>n-2</sub>x + Qb<sub>n-2</sub> Rx + S (4)</p><p>Resulta que las ecuaciones y corresponden a polinomios de grado n, por lo que es pertinente utilizarla "
		+"propiedad de igualdad de polinomios.</p><p>a<sub>0</sub> = b<sub>0</sub></p><p> a<sub>1</sub>= Pb<sub>0</sub> + b<sub>1</sub></p><p>a<sub>2</sub>= Qb<sub>0</sub> + Pb<sub>1</sub> + b<sub>2</sub></p>"
		+"<p>a<sub>3</sub>= Qb<sub>1</sub> + Pb<sub>2</sub> + b<sub>3</sub></p><p>...</p><p>a<sub>n-1</sub>= R + Pb<sub>n-2</sub> +Qb<sub>n-3</sub></p>"
		+"<p>a<sub>n</sub>=  S + Qb<sub>n-2</sub>(5)</p><p>En las ecuaciones (5) las inco&#769;gnitas son los coeficientes b<sub>i</sub> del polinomio reducido as&#769;&#305; "
		+"como los factores R y S del residuo. Despejando dichas inco&#769;gnitas:</p>"
		+"<p>b<sub>0</sub> = a<sub>0</sub></p><p> b<sub>1</sub>= a<sub>1</sub> - Pb<sub>0</sub> </p><p>b<sub>2</sub>= a<sub>2</sub> - Qb<sub>0</sub> - Pb<sub>1</sub> + </p>"
		+"<p>b<sub>3</sub>= a<sub>3</sub> - Qb<sub>1</sub> - Pb<sub>2</sub></p><p>...</p><p>R = a<sub>n-1</sub> - Pb<sub>n-2</sub>  -Qb<sub>n-3</sub></p>"
		+"<p>S = a<sub>n</sub> - Qb<sub>n-2</sub>(6)</p>"
		+"<p>Las ecuaciones (6) se expresan en forma general como:</p>"
		+"<p>b<sub>k</sub>= a<sub>k</sub> - Qb<sub>k-1</sub> - Pb<sub>k-2</sub>"
		+"<p>R = a<sub>n-1</sub> - Pb<sub>n-2</sub>  -Qb<sub>n-3</sub></p>"
		+"<p>S = a<sub>n</sub> - Qb<sub>n-2</sub>(7)</p>"
		+"<p>donde: k = 0, 1, 2, 3, ..., n &#8722; 2 y n es el grado del polinomio original. Finalmente, para que lasecuaciones (7) sean realmente generales, "
		+"como condicio&#769;n de disen&#771;o debe considerarse:</p><p>b<sub>-1</sub> = b<sub>-2</sub> = 0 (8)</p><p>Para que las ra&#769;&#305;ces del factor "
		+"cuadra&#769;tico x<sub>2</sub> + Px + Q sean tambie&#769;n ra&#769;&#305;ces del polinomio originalse requiere que el residuo Rx + S sea cero o muy "
		+"cercano a cero, desde el punto de vista de una aproximacio&#769;n nume&#769;rica. De tal forma, de la ecuacio&#769;n (5):</p>"
		+"<p>a<sub>n-1</sub> &#8722; Pb<sub>n-2</sub> &#8722;Qb<sub>n-3</sub> = 0 (9)</p>"
		+"<p>a<sub>n</sub> &#8722;Qb<sub>n-2</sub> = 0 (10)</p>"
		+"<p>Despejando las inco&#769;gnitas P y Q de (9) y (10), respectivamente:</p><p>P = (a<sub>n-1</sub>-Qb<sub>n-3</sub>)/b<sub>n-2</sub> (11) </p>"
		+"<p>Q =a<sub>n</sub>/b<sub>n-2</sub> (12) </p><p>Conocidos estos valores podra&#769;n determinarse los coeficientes b<sub>i</sub> del polinomio reducido."
		+"Se percibe que resulta complicado disponer de los valores de P y Q que satisfagan los supuestos de este me&#769;todo.Sin embargo, si se utiliza un criterio "
		+"iterativo es posible obtener la solucio&#769;n deseada.</p><p>A partir de valores iniciales para P y Q y aplicando un proceso iterativo se pueden llegar a "
		+"determinarnuevos valores para dichas variables. De tal forma, se define a:</p>"
		+"<p>&#916;P = P &#8727; &#8722; P (13)</p><p>&#916;Q = Q&#8727; &#8722;Q (14)</p><p>Las expresiones (13) y (14) representan los incrementos entre dos valores "
		+"consecutivos de P y Q.</p><p>Conside&#769;rese a P &#8727; y a Q&#8727; como los valores corregidos de P y Q, mismos que se calculan con las expresiones (11) y (12) "
		+"respectivamente.</p><p>&#916;P =(a<sub>n-1</sub> &#8722;Qb<sub>n-3)/b<sub>n-2</sub></sub>&#8722; P = (a<sub>n-1</sub> &#8722;Qb<sub>n-3</sub> &#8722; Pb<sub>n-2</sub>)/b<sub>n-2</sub>(15)</p>"
		+"<p>Sustituyendo la ecuacio&#769;n particular (7) se obtiene:</p><p>&#916;P =R/b<sub>n-2</sub>(16)</p><p>Ana&#769;logamente para la ecuacio&#769;n (14):</p>"
		+"<p>&#916;Q =a<sub>n-1</sub>/b<sub>n-2</sub>&#8722;Q = (a<sub>n</sub> &#8722;Qb<sub>n-2</sub>)/b<sub>n-2</sub> (17)</p><p>&#916;Q =S/b<sub>n-2</sub>(18)</p>"
		+"<p>En conjunto, las ecuaciones (7), (16) y (18) conforman el me&#769;todo completo. Como valores inicialesse propone que P = Q = 0 en la primera iteracio&#769;n, "
		+"de tal forma que en la ecuacio&#769;n (13):</p><p>&#916;P =a<sub>n-1</sub>/b<sub>n-2</sub></p><p>(19)</p><p>Ahora bien, u&#769;nicamente para la primera iteracio&#769;n "
		+"en la ecuacio&#769;n (7), si P = Q = 0:</p><p>b<sub>k</sub> = a<sub>k</sub> &#8722; Pb<sub>k-1</sub> &#8722;Qb<sub>k-2</sub> &#8658; b<sub>k</sub> = a<sub>k</sub> (20)</p>"
		+"<p>En consecuencia, b<sub>n-2</sub> = a<sub>n-2</sub>, por lo que en (19):</p><p>&#916;P =a<sub>n-1</sub>/a<sub>n-2</sub> (21) </p>"
		+"<p>Ana&#769;logamente en (17) , si P = Q = 0 y con el criterio de (20):</p>"
		+"<p>&#916;Q = (a<sub>n</sub> &#8722;Qb<sub>n-2</sub>)/b<sub>n-2</sub> = a<sub>n</sub>/b<sub>n</sub> (22) </p><p>&#916;Q =a<sub>n</sub>/a<sub>n-2</sub></p>"
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}