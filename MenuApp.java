package vista.accesories;

import javax.swing.JMenu;
import javax.swing.ImageIcon;

/**
    * La clase MenuApp es una clase que hereda de la clase JMenu debido a que es menú en el fondo, 
    * pero tiene un cierto comportamiento para la aplicación, se encarga de crear menú
    * con un comportamiento especifico.
    * @author German López Rodrigo
    * @version 2/12/2016/Final 
*/

public class MenuApp extends JMenu{
	/** * Método Constructor que crear un Menú sin ningún estilo o elementos en el.*/
	public MenuApp(){}

	/** * Método Constructor que crea un Menú, recibe un texto para colocárselo.*/
	public MenuApp(String texto){
		super(texto);
	}

	/** *Método Constructor que crea un Menú, recibe un texto para colocárselo y una ruta de la imagen que se le quiere agregar.*/
	public MenuApp(String texto,String icono){
		super(texto);
		ImageIcon im1 = new ImageIcon(icono);
   		setIcon(im1);
	}
}
