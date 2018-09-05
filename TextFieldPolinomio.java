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

public class TextFieldPolinomio extends TextFieldFuncion implements FocusListener{

	/** * Metodo constructor que crear un TextFieldFuncion pero sin ninguna comportamiento ni estilo especifico.*/
	public TextFieldPolinomio(){}

	/** *Metodo constructor que crea un TextFieldFuncion, recibe el texto del backgraund y el color del borde del campo y
	un tipo de validacion que le da un cierto comportamiento al campo a crear.*/
	public TextFieldPolinomio(String texto,Color color){
		super(texto,color);
		addKeyListener(new KeyEventText());
	}

	private void validExpresion(){
		String valores ="+-^";
		char[] fun = (this.getText()).toCharArray();
		int tam = fun.length-1;
		if(tam > 0){
			if(valores.indexOf(fun[tam]) != -1){
				cont++;
				this.setBackground(Color.RED);
			}
		}
	}

	/** * Clase interna KeyEventPass que hereda de KeyAdapter, esta clase se encarga de crear objetos que son eventos,
	con el comportamiento que al presionar caracteres especiales no los escribe, si el tipo es 0 valida que sean solo letras
	y si es 1 valida que sean solo letras y digitos numericos y valida que no sea posible copiar,pegar ni cortar
	de los campos.*/
	protected class KeyEventText extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e){
			String caracter_valido = "x1234560789+-^.*";
			char caracter = e.getKeyChar();
			if(caracter_valido.indexOf(caracter) == -1)
				e.consume();
		}

		@Override
		public void keyReleased(KeyEvent e){
			cont = 0;
			cont2 = 0;
			errorSyntaxis();
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