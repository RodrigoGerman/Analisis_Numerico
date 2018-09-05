package vista.accesories;

import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
	* La clase ButtonGit es una clase que hereda de la clase JButton debido a que es un botón en el fondo, pero tiene un
	* cierto comportamiento para la aplicación, se encarga de crear un botón con un comportamiento especifico.
	* @author German López Rodrigo
	* @version 1/12/2016/Final 
*/

public class ButtonApp extends JButton{

	/** *Método Constructor que crear un ButtonApp sin ningún comportamiento. */
	public ButtonApp(){}

	/** *Método Constructor que recibe un texto el cual será el texto del botón a crear y se le da un comportamiento en 
	especifico. */
	public ButtonApp(String texto){
		super(texto);
		this.addKeyListener(new KeyEventButton());
	}

	/** * Clase interna KeyEventButton que hereda de KeyAdapter, esta clase se encarga de crear objetos que son eventos 
		* con un cierto comportamiento, que al presionar la tecla enter lo que realizara es transferir el foco al siguiente componente. */
	protected class KeyEventButton extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			int caracter = e.getKeyCode();
			if(caracter == KeyEvent.VK_ENTER){
				doClick();
				if(e.getModifiers() > 0)
					transferFocusBackward();
				else
					transferFocus();
				e.consume();
			}
		}
	}
}
