package vista.accesories;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
	* La clase TextFieldFuncion es una clase que hereda de la clase JTextField debido a que es un campo de 
	* texto en el fondo, pero tiene un cierto comportamiento para la aplicacion GIT - RGL, 
	* se encarga de crear un campo de datos con un comportamiento especifico.
	* @author German Lopez Rodrigo
	* @version 1/12/2016/Final 
*/

public class TextFieldString extends JTextField implements FocusListener{
	/** * Variable privada tipo String  encargada de almacenar el texto que se le colocala de forma backgraund al campo.*/
	private String texto;

	/** * Metodo constructor que crear un TextFieldFuncion pero sin ninguna comportamiento ni estilo especifico.*/
	public TextFieldString(){}

	/** *Metodo constructor que crea un TextFieldFuncion, recibe el texto del backgraund y el color del borde del campo y
	un tipo de validacion que le da un cierto comportamiento al campo a crear.*/
	public TextFieldString(String texto,Color color){
		super(texto);
		this.texto = texto;
		setForeground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(color,1));
		addFocusListener(this);
		addKeyListener(new KeyEventText());
	}

	/** * Metodo sobrescrito de la clase FocusListener el cual limpia el campo cuando el cliente lo este seleccionando.*/
	@Override  
	public void focusGained(FocusEvent e){
		if(this.getText().equals(this.texto)){
			this.setText("");
			this.setForeground(Color.BLACK);
		}
	}

	/** * Metodo sobrescrito de la clase FocusListener el cual coloca un texto al campo cuando el cliente no lo este seleccionando.*/
	@Override  
	public void focusLost(FocusEvent e){

		if(this.getText().length() == 0){  
		    this.setText(this.texto);
		    this.setForeground(Color.GRAY);
		}
	}


		/** * Clase interna KeyEventPass que hereda de KeyAdapter, esta clase se encarga de crear objetos que son eventos,
	con el comportamiento que al presionar caracteres especiales no los escribe, si el tipo es 0 valida que sean solo letras
	y si es 1 valida que sean solo letras y digitos numericos y valida que no sea posible copiar,pegar ni cortar
	de los campos.*/
	protected class KeyEventText extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e){
			String caracter_valido = "abcdefghijklmnñopqrustvwxyz123456789/";
			char caracter = e.getKeyChar();
			if(caracter_valido.indexOf(caracter) == -1)
				e.consume();
		}


		@Override
		//con enter se cambia el foco.
		public void keyPressed(KeyEvent e){
			int caracter = e.getKeyCode();
			if(caracter == KeyEvent.VK_ENTER){
				if(e.getModifiers() > 0)
					transferFocusBackward();
				else
					transferFocus();
				e.consume();
			}
		}
	}
}