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
	* La clase TextFieldNumber es una clase que hereda de la clase JTextField debido a que es un campo de 
	* texto en el fondo, pero tiene un cierto comportamiento para la aplicacion GIT - RGL, 
	* se encarga de crear un campo de datos con un comportamiento especifico.
	* @author German Lopez Rodrigo
	* @version 1/12/2016/Final 
*/

public class TextFieldNumber extends JTextField implements FocusListener{
	/** * Variable privada tipo String  encargada de almacenar el texto que se le colocala de forma backgraund al campo.*/
	private String texto;
	/** * Variable privada tipo byte encargada de almacenar el tipo de validacion que se le colocala al campo.*/
	private boolean existe= false,existe2 = false;

	private int tipo=1;

	private byte cont = 0;


	/** * Metodo constructor que crear un TextFieldNumber pero sin ninguna comportamiento ni estilo especifico.*/
	public TextFieldNumber(){}

	/** *Metodo constructor que crea un TextFieldNumber, recibe el texto del backgraund y el color del borde del campo y
	un tipo de validacion que le da un cierto comportamiento al campo a crear.*/
	public TextFieldNumber(String texto,Color color,int tipo){
		super(texto);
		this.texto = texto;
		this.tipo = tipo;
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
		if(cont == 0)
			this.setBackground(Color.WHITE);
		
		if(this.getText().length() == 0){  
		    this.setText(this.texto);
		    this.setForeground(Color.GRAY);
		}
	}

	private void validSyntaxis(){
		char[] fun = (this.getText()).toCharArray();
		int pos = fun.length;
		boolean aux = false,aux2 = false;
		for(int i = 0; i < pos;i++){
			if(fun[i] == '.')
				aux = true;

			if(tipo == 1){

				if(fun[i] == '-'){
					if(i != 0)
						cont++;
					aux2 = true;
				}
				
			}
		}

		if(!aux)
			existe = false;
		if(!aux2)
			existe2 = false;

		if(cont > 0)
			this.setBackground(Color.RED);
		else 
			this.setBackground(Color.GREEN);
	}


	public boolean getCorrectNumber(){
		return cont == 0;
	}

	/** * Clase interna KeyEventPass que hereda de KeyAdapter, esta clase se encarga de crear objetos que son eventos,
	con el comportamiento que al presionar caracteres especiales no los escribe, si el tipo es 0 valida que sean solo letras
	y si es 1 valida que sean solo letras y digitos numericos y valida que no sea posible copiar,pegar ni cortar
	de los campos.*/
	protected class KeyEventText extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e){
			String caracter_valido = "-.1234560789";
			if(tipo == 0)
				caracter_valido = ".1234560789";

			if(tipo == 2)
				caracter_valido = "1234560789";

			char caracter = e.getKeyChar();

			if(caracter_valido.indexOf(caracter) == -1){
				e.consume();
			}

			else{
				if(existe && caracter == '.')
					e.consume();
				else if(existe2 && caracter == '-')
					e.consume();

				if(caracter == '.')
					existe = true;
				else if(caracter == '-')
					existe2 = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e){
			int caracter = e.getKeyCode();
			cont = 0;
			validSyntaxis();
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