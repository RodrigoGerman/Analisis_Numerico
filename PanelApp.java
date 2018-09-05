package vista.accesories;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
	* La clase PanelApp es una clase que hereda de JPanel debido a que es un panel, se encarga de crear un panel con métodos específicos para agregar ciertos 
	* componentes al panel.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/

public class PanelApp extends JPanel{
	/** * Método constructor que crear un PanelGit pero sin ninguna comportamiento ni estilo especifico.*/
	public PanelApp(){}

	/** * Método constructor que crear un PanelGit, recibe un distribución de posiciones y un color para 
	colocar al panel.*/
	public PanelApp(LayoutManager posicion,Color color){
		setLayout(posicion);
		setBackground(color);
	}

	/** * Método para agregar una imagen al panel, recibe una string que especifica la ruta de la imagen.*/
	public final void setImage(String ruta){
		JLabel label1 = new JLabel(new ImageIcon(ruta));
		this.add(label1);
	}

	/** * Método para agregar una etiqueta de texto al panel de la ventana, recibe un String especificando el texto que
	contendrá y un Color del color de las letras*/
	public final void setLabel(String texto,Color color){
		JLabel label1 = new JLabel(texto);
		label1.setForeground(color);
		this.add(label1);
	}


	/** * Método para agregar una etiqueta de texto al panel de la ventana, recibe un String especificando el texto que
	contendrá y un Color del color de las letras*/
	public final void setLabel(String texto,Color color,Font fuente){
		JLabel label1 = new JLabel(texto);
		label1.setForeground(color);
		label1.setFont(fuente);
		this.add(label1);
	}

	/** * Método para agregar al panel la imagen del logo de la aplicación Git-RGL.*/
	public final void setLogo(String ruta,String titulo){
		setImage(ruta);
		setLabel(titulo,Color.BLACK);
	}

	/** * Método para agregar una imagen al panel en una determinada posición, recibe un String que es la ruta 
	de la imagen, recibe cuatro enteros donde, "x" se refiere a cuantos pixeles estará retirado del eje "x", la y 
	se refiere a cuantos pixeles estará retirado del eje "y", el "lon" se refiere el ancho que ocupara la imagen
	y "width" se refiere a la altura de la imagen.*/
	public final void setImage(String ruta,int x,int y,int lon,int width){
		JLabel label1 = new JLabel(new ImageIcon(ruta));
		label1.setBounds(x,y,lon,width);
		this.add(label1);
	}

	/** * Método para agregar una etiqueta de texto al panel en una determinada posición, recibe un String que 
	es el texto que contendrá la etiqueta y un Color con el color que serán las letras, también recibe cuatro enteros
	donde, "x" se refiere a cuantos pixeles estará retirado del eje "x", la "y" se refiere a cuantos pixeles estará 
	retirado del eje "y", el lon se refiere el ancho que ocupara la etiqueta y "width" se refiere a la altura de la etiqueta.*/
	public final void setLabel(String texto,Color color,int x,int y,int lon,int width){
		JLabel label1 = new JLabel(texto);
		label1.setBounds(x,y,lon,width);
		label1.setForeground(color);
		this.add(label1);
	}


	/** * Método para agregar una etiqueta de texto al panel en una determinada posición, recibe un String que 
	es el texto que contendrá la etiqueta y un Color con el color que serán las letras, también recibe cuatro enteros
	donde, "x" se refiere a cuantos pixeles estará retirado del eje "x", la "y" se refiere a cuantos pixeles estará 
	retirado del eje "y", el lon se refiere el ancho que ocupara la etiqueta y "width" se refiere a la altura de la etiqueta.*/
	public final void setLabel(String texto,Color color,int x,int y,int lon,int width,Font fuente){
		JLabel label1 = new JLabel(texto);
		label1.setBounds(x,y,lon,width);
		label1.setForeground(color);
		label1.setFont(fuente);
		this.add(label1);
	}

	/** * Método para agregar en el panel la imagen de logo de la aplicación Git-RGL en una determinada posición,
	recibe cuatro enteros donde, "x" se refiere a cuantos pixeles estará retirado del eje "x", la "y" se refiere a 
	cuantos pixeles estará retirado del eje "y", el lon se refiere el ancho que ocupara el logo y 
	"width" se refiere a la altura que tendrá el logo.*/
	public final void setLogo(String ruta,String titulo,int x,int y,int lon,int width){
		setImage(ruta,x,y,lon,width);
		setLabel(titulo,Color.BLACK,x+28,y+90,100,15);
	}

	/** * Método para agregar un botón en una posición especifica al panel.*/
	public void setButton(JButton button,int x,int y,int lon,int width){
		button.setBounds(x,y,lon,width);
		this.add(button);
	}

	/** * Método para agregar un campo de texto en una posición especifica al panel.*/
	public void setTextField(JTextField campo,int x,int y,int lon,int width){
		campo.setBounds(x,y,lon,width);
		this.add(campo);
	}
}