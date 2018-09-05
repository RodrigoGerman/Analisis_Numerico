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
	* @version 11/04/2017/Final 
*/
public class TextFieldFuncion extends JTextField implements FocusListener{
	/** * Variable privada tipo String  encargada de almacenar el texto que se le colocala de forma backgraund al campo.*/
	protected String texto;
	/** * Variable privada tipo byte encargada de almacenar el tipo de validacion que se le colocala al campo.*/
	protected byte cont = 0,cont2 =0;

	/** * Metodo constructor que crear un TextFieldFuncion pero sin ninguna comportamiento ni estilo especifico.*/
	public TextFieldFuncion(){}

	/** *Metodo constructor que crea un TextFieldFuncion, recibe el texto del backgraund y el color del borde del campo y
	un tipo de validacion que le da un cierto comportamiento al campo a crear.*/
	public TextFieldFuncion(String texto,Color color){
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

	/** *Metodo sobrescrito de la clase FocusListener el cual coloca un texto al campo cuando el cliente no lo este seleccionando.*/
	@Override  
	public void focusLost(FocusEvent e){
		validExpresion();

		if(cont == 0 && cont2 == 0)
			this.setBackground(Color.WHITE);

		if(this.getText().length() == 0){  
		    this.setText(this.texto);
		    this.setForeground(Color.GRAY);
		}
	}

	/** *Metodo que sirve para validar que la expresion escrita en el textfiel es valida.*/
	private void validExpresion(){
		String valores ="+/*-^sinbqrcotah.";
		char[] fun = (this.getText()).toCharArray();
		int tam = fun.length-1;
		if(tam > 0){
			if(valores.indexOf(fun[tam]) != -1){
				cont++;
				this.setBackground(Color.RED);
			}
		}

		if(this.getText().equals("+") || this.getText().equals("-") || this.getText().equals(".")){
				cont++;
				this.setBackground(Color.RED);
		}

		else if(this.getText().equals("s") || this.getText().equals("c") || this.getText().equals("t")){
				cont++;
				this.setBackground(Color.RED);
		}

		else if(this.getText().equals("(") || this.getText().equals("a") || this.getText().equals("i")){
				cont++;
				this.setBackground(Color.RED);
		}
	}

	/** *Metodo que sirve para validar que la expresion no tenga un error sitactico.*/
	protected void errorSyntaxis(){
		char[] fun = (this.getText()).toCharArray();
		int pos = fun.length;
		for(int i = pos; i > 0;i--)
			validSyntaxis(Arrays.copyOfRange(fun,0,i));
	}

	/** *Metodo que sirve para validar que la expresion se valida con la sintaxis de una funcion.*/
	private void validSyntaxis(char[] fun){
		String valores = "(a+/*-^";
		String valores1 ="(+/*-^";
		String operadores ="+/*-^";
		String valores2 ="(+-yzxuvwlmnjkpstc123456789e0ai";
		//String valores2 ="(+-xyzstc123456789e0ai";
		//String valores3 ="x1234567890";
		String valores3 ="x1234567890";
		String valores4 ="1234567890";
		int pos = fun.length-1;

		if(pos == 0){
			if(valores2.indexOf(fun[pos]) == -1)
				cont++;
			else
				if(valores2.indexOf(fun[pos]) == 0)
					cont2++;

		}

		else if(pos >= 1){
			char aux = fun[pos];
			char aux2 = fun[pos-1];

			//Caracter 'a','A'
			if(aux == 'a'){
				if(valores1.indexOf(aux2) == -1 && aux2 != 't')
					cont++;
			}

			else if(aux == 'i' || aux == 'e'){
				if(aux2 != 's' && valores1.indexOf(aux2) == -1)
					cont++;
			}

			else if(aux == 's'){
				if(aux2 != 'c' && valores.indexOf(aux2) == -1 && aux2 != 'b' && aux2 != 'o')
					cont++;
			}

			else if(aux == 'c'){
				if(aux2 != 's' && valores.indexOf(aux2) == -1 && aux2 != 'e')
					cont++;
			}

			else if(aux == 't'){
				if(aux2 != 'r' && valores.indexOf(aux2) == -1 && aux2 != 'o')
					cont++;
			}

			else if(aux == 'o'){
				if(aux2 != 'c')
					cont++;
			}

			else if(aux == 'b'){
				if(aux2 != 'a')
					cont++;
			}

			else if(aux == 'n'){
				if(aux2 != 'i' && aux2 != 'a')
					cont++;
			}

			else if(aux == 'x'){
				if(valores4.indexOf(aux2) != -1)
					cont++;

			}


			else if(aux == 'h'){
				if(aux2 != 'n' && aux2 != 's' && aux2 != 'c' && aux2 != 't')
					cont++;
			}

			else if(aux == 'r'){
				if(aux2 != 'q')
					cont++;
			}

			else if(aux == '.'){
				
				if(valores4.indexOf(aux2) == -1)
					cont++;

				boolean existe = false;
				int i = pos;
				while(operadores.indexOf(fun[i]) == -1 && i > 0){
					if(existe && fun[i] == '.'){
						cont++;
						break;
					}
					if(fun[i] == '.')
						existe = true;
					i--;
				}
			}

			else if(aux == 'q'){
				if(aux2 != 's')
					cont++;
				if(pos > 1)
					if(fun[pos-2] == 'o' || fun[pos-2] == 'b' || fun[pos-2] == 'c' || fun[pos-2] == 'a')
						cont++;
			}
		
			else if(valores3.indexOf(aux) != -1){
				if(valores1.indexOf(aux2) == -1 && aux2 != '(' && valores4.indexOf(aux2) == -1 && aux2 != '.')
					cont++;
			}

			else if(aux == '('){
				cont2++;
				if(aux2 != 'n' && aux2 != 's' && aux2 != 'c' && aux2 != 't' && aux2 != 'h' && valores1.indexOf(aux2) == -1)
					cont++;
			}

			else if(aux == ')'){
				cont2--;
				if(valores3.indexOf(aux2) == -1 && aux2 != ')')
					cont++;
			}

			else if(aux == '+' || aux == '-'){
				if(valores3.indexOf(aux2) == -1 && aux2 != ')' && aux2 != '(' && aux2 != '^')
					cont++;
			}

			else if(aux == '/' || aux == '*' || aux == '^'){
				if(valores3.indexOf(aux2) == -1 && aux2 != ')')
					cont++;
			}

			if((aux2 == '/' || aux2 == '*' || aux2 == '^') && pos == 1)
				cont++;
		}

		if(cont > 0 || cont2 != 0)
			this.setBackground(Color.RED);
		else
			this.setBackground(Color.GREEN);
	}

	/** * Clase interna KeyEventPass que hereda de KeyAdapter, esta clase se encarga de crear objetos que son eventos,
	con el comportamiento que al presionar caracteres especiales no los escribe, si el tipo es 0 valida que sean solo letras
	y si es 1 valida que sean solo letras y digitos numericos y valida que no sea posible copiar,pegar ni cortar
	de los campos.*/
	protected class KeyEventText extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e){
			//String caracter_valido = "sinbqrcoetahyzx.1234560789+/*-^()";
			String caracter_valido = "sinbqrcoetahyzxuvwlmnjkp.1234560789+/*-^()";
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

	/** * Metodo que sirve para validar que la funcion ingresada no tenga ningun error de sintaxis.*/
	public boolean getCorrectFuncion(){
		if(this.getBackground().equals(Color.RED))
			return false;
		return true;
	}
}