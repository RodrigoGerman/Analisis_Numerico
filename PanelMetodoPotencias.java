package controlador.theme3.matriz;


import vista.panels.theme3.PanelMethodMatriz;
import modelo.theme3.MetodoPotencias;
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
    * La clase PanelMetodoPotencias es una clase que hereda de PanelMethodMatriz debido a que es un panel de método de análisis numérico que utiliza una matriz e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Potencias, se encarga de crear un panel específico para la visualización
    * del método numérico de Potencias.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelMetodoPotencias extends PanelMethodMatriz implements ActionListener{
	/** * Variable tipo MetodoPotencias privada que representa el método de Potencias. */
	private MetodoPotencias potencias;
	/** * Variable tipo Matriz privada que representa la matriz ingresada por el usuario. */
	private Matriz matriz= null;
	/** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelMetodoPotencias. */
	private JFrame parent;

	/** * Método constructor que crear un PanelMetodoPotencias pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelMetodoPotencias(){}

	/** * Método constructor que crea un PanelMetodoPotencias, recibe la ventana padre que lo llamo.*/
	public PanelMetodoPotencias(JFrame parent){
		super(new Color(76, 123, 171),"Método de Potencias");
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
						JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca una matriz cuadrada el método de potencias solo\n"
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
				potencias = new MetodoPotencias(matriz);
				iteraciones.setText(potencias.getIteraciones());
				soluciones.setText(potencias.getSoluciones());
			}
		}

		else if(e.getSource() == limpiar){
			renglones.setText(" Renglones");
			columnas.setText(" Columnas");
			soluciones.setClear("");
			iteraciones.setClear("");
		}
	}

	/** * Método privado setInformacion que se encarga de insertar el texto de informacion sobre el método de Potencias en el PanelMetodoPotencias.*/
	private final void setInformacion(){
		str_formulas = "<p>A<sup>-1</sup>X&#772;(k) = 1/&#955;(k+1)X&#772;(k+1) </p>";
		info = "<html><body><h2 COLOR = red ALIGN = center>Me&#769;todo de las Potencias</h2>"
		+"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
		+"<p>1. Introduccio&#769;n</p><p>La manera ortodoxa de obtener los valores caracter&#769;&#305;sticos de la una matriz [1] A es obtener lasra&#769;&#305;ces"
		+"de su polinomio caracter&#769;&#305;stico. El me&#769;todo de las potencias ofrece una opcio&#769;n para obtener elmayor y el menor valor caracter&#769;&#305;stico "
		+"de la matriz A de orden nxn sin la necesidad de disponer dela ecuacio&#769;n caracter&#769;&#305;stica [2].</p>"
		+"<p>El me&#769;todo de las potencias es un me&#769;todo iterativo de aproximaciones sucesivas, por lo cual, adema&#769;sde la matriz A debera&#769; conocerse una "
		+"tolerancia preestablecia.</p>"
		+"<p>2. Definicio&#769;n del me&#769;todo de las potencias</p><p>Sea el sistema homoge&#769;neo de ecuaciones lineanes:</p>"
		+"<p>(a11 &#8722; &#955;)X1 + a12X2 + a13X3 + ... + a1nXn = b1</p><p>a21X1 + (a22 &#8722; &#955;)X2 + a23X3 + ... + a2nXn = b2</p><p>a31X1 + a32X2 + (a33 &#8722; &#955;)X3 + ... + a3nXn = b3</p>"
		+"<p>...... ...... ......</p><p>an1X1 + an2X2 + an3X3 + ... + (ann &#8722; &#955;)Xn = bn (1)</p><p>Que puede representarse como:</p><p>|A&#8722; &#955;I|X&#772; = 0 (2)</p>"
		+"<p>A partir de la expresio&#769;n (2) pueden obtenerse los valores caracter&#769;&#305;sticos, ya que representa a la ecuacio&#769;n caracter&#769;&#305;stica de la matriz A; el "
		+"tratamiento para obtener el valor mayor o el menor es ligeramente diferente, pero en ambos casos proporciona el vector caracter&#769;&#305;stico respectivo.</p>"
		+"<p>3. Mayor valor caracter&#769;&#305;stico</p><p>La expresio&#769;n (2) puede acomodarse de la siguiente forma:</p><p>AX&#772; &#8722; &#955;X&#772; = 0</p><p>AX&#772; = &#955;X&#772; (3)</p>"
		+"<p>Para la expresio&#769;n (3) se percibe la forma caracter&#769;&#305;stica de un me&#769;todo de aproximaciones sucesivas.El me&#769;todo propone utilizar un vector inicial X&#772;(0) != 0 "
		+"compatible y multiplicarlo por la matriz A.El resultado sera&#769; un nuevo vector X&#772;(1) el cual sera&#769; normalizado utilizando su elemento mayor; esteelemento utilizado para la normalizacio&#769;n sera&#769; "
		+"una aproximacio&#769;n al mayor valor caracter&#769;&#305;stico &#955;i yel vector nomarlizado sera&#769; su vector asociado. El proceso se repetira&#769; hasta que la diferencia entredos aproximaciones cumpla con una "
		+"tolerancia preestablecida.</p><p>Para completar el me&#769;todo, la ecuacio&#769;n (3) debe expresarse en forma iterativa:</p><p>AX&#772;(k) = &#955;(k+1)X&#772;(k+1) (4)</p><p>donde k es el nu&#769;mero de la iteracio&#769;n y "
		+"k = 1, 2, 3, ...</p><p>4. Menor valor caracter&#769;&#305;stico</p><p>Retomamos la ecuacio&#769;n (2), misma que se premultiplica por la matriz inversa de A, que es A<sup>-1</sup></p><p>A<sup>-1</sup>AX&#772; = A<sup>-1</sup>&#955;X&#772;</p>"
		+"<p>La que resulta en:X&#772; = A<sup>-1</sup>&#955;X&#772;</p><p>Dividiendo entre &#955;:</p><p>1/&#955;X&#772; = A<sup>-1</sup>X&#772; (5)</p><p>Para completar el proceso, haciendo a (5) una ecuacio&#769;n iterativa:</p><p>A<sup>-1</sup>X&#772;(k) ="
		+"1/&#955;(k+1)X&#772;(k+1) (6)</p><p>donde k es el nu&#769;mero de la iteracio&#769;n y k = 1, 2, 3, ...</p><p>El tratamiento para obtener el menor valor caracter&#769;&#305;stico es similar al utilizado para obtener el mayorvalor, a diferencia "
		+"del uso de la matriz inversa A<sup>-1</sup> y que el factor de normalizacio&#769;n representa alrec&#769;&#305;proco del menor valor caracter&#769;&#305;stico de la matriz A. El proceso se repetira&#769; hasta que la diferenciaentre dos aproximaciones "
		+"cumpla con una tolerancia preestablecida.</p>"
		+"<br><br></P></body></html>";
		formulas.setInfo(str_formulas);
		informacion.setInfo(info);
	}
}
