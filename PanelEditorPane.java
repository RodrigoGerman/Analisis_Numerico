package vista.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;

/**
	* La clase PanelApp es una clase que hereda de JPanel debido a que es un panel, se encarga de crear un panel con un título, borde color y fuente específico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/

public final class PanelEditorPane extends JPanel{
	/** * Variable tipo Color privada que almacena un color específico para los títulos de la aplicación. */ 
	private static Color color_titulos = new Color(235, 235, 238);
	/** * Variable tipo Font privada que almacena un tipo de letras específico para el texto de la aplicación. */ 
	private static Font font_titulos = new Font("Times New Roman", 1, 18);
	/** * Variable tipo Color privada que almacena un color específico para los bordes de la aplicación. */ 
	private static Color color_bordes = new Color(235, 235, 235);
	/** * Variable tipo Color privada que almacena un color específico para el borde de la aplicación.*/ 
	private static Color color_fondo = new Color(76, 123, 171);
	/** * Variable tipo JEditorPane para crear el área de texto donde se introduce el texto.*/ 
	private JEditorPane textArea = new JEditorPane();
	/** * Variable tipo JScrollPane privada que almacena al panel para que tenga una barra de desplazamiento. */ 
	private JScrollPane scroll_testArea = new JScrollPane();

	/** * Método constructor que crear un PanelEditorPane pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelEditorPane(){}

	/** * Método constructor que crear un PanelEditorPane, recibe una String que representa el título, dos int que representa el ancho y la altura respectivamente.*/
	public PanelEditorPane(String titulo,int height,int width){
		this(titulo,height,width,color_fondo,color_bordes,font_titulos);
	}

	/** * Método constructor que crear un PanelEditorPane, recibe una String que representa el título, dos int que representa el ancho y la altura respectivamente,
	 dos variables Color donde fondo indica el color del fondo y bordes indica el color de los bordes y una variable Font que representa el tipo de letras del PanelEditorPane.*/
	public PanelEditorPane(String titulo,int height,int width,Color fondo,Color bordes,Font texto){
		this.setBackground(fondo);
		this.setBorder(BorderFactory.createTitledBorder(null,titulo,TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,texto,bordes));
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textArea.setEditable(false);
		textArea.setContentType("text/html");
		scroll_testArea.setViewportView(textArea);

		GroupLayout panel_Layout = new GroupLayout(this);
		this.setLayout(panel_Layout);

		panel_Layout.setHorizontalGroup(
		panel_Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING, panel_Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scroll_testArea, GroupLayout.DEFAULT_SIZE,width, Short.MAX_VALUE)
			.addContainerGap())
		);

		panel_Layout.setVerticalGroup(
		panel_Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING,panel_Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scroll_testArea, GroupLayout.DEFAULT_SIZE,height, Short.MAX_VALUE)
			.addContainerGap())
		);
	}

	/** * Método publico que se encarga de insertar texto al PanelEditorPane, recibe una String la cual introduce en dicho panel.*/
	public void setInfo(String contenido){
		textArea.setText(contenido);
		textArea.setCaretPosition(0);
	}
}