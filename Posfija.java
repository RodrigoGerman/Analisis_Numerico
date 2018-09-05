package modelo;

import java.util.Stack;

/**
	* La clase Posfija es una clase que se encarga de convertir cualquier expresion matematica en su forma posfija.
	* @author German Lopez Rodrigo
	* @version 13/05/2017/Final 
*/
public class Posfija{
	/** * Stack de tipo Character privada que se encarga de almacenar los operandos de las expresion matematica.*/
	private Stack<Character> operandos = new Stack<Character>();
	/** * Stack de tipo String privada que se encarga de almacenar los operadores de las expresion matematica.*/
	private Stack<String> posfija = new Stack<String>();
	/** * Varible tipo String privada que se encarga de guardar la expresion matematica en su forma Posfija.*/
	private String str_posfija = "";
	/** * Arreglo de String privado que se encarga de guardar la expresion matematica en su forma Posfija.*/
	private String[] char_posfija;
	
	/** * Metodo Constructor sin argumentos.*/
	public Posfija(){}

	/** * Metodo Constructor que recibe un String que representa la expresion matematica.*/
	public Posfija(String expresion){
		setPostfixProcessing(expresion);
		setPostfixExpression(posfija);
	}

	/** * Metodo privado setPostfixProcessing que se encarga de calcular la forma posfija de la String que recibe.*/
	private void setPostfixProcessing(String formula){
		int i=0,valInfija=0;
		char[] infija = (formula+" ").toCharArray();
		int tam = infija.length;
		while(i<tam){
			valInfija = charVal(infija[i]);
			switch(valInfija){
				case 0:// )
					while(operandos.peek() != '(')
						posfija.push(String.valueOf(operandos.pop()));
					operandos.pop();
					break;
				case 1:// (
					operandos.push(infija[i]);
					break;
				case 2://numeros
					String numero = "";

					while(charVal(infija[i]) == 2){
						numero += infija[i];

						if(i < tam)
							i++;
					}
					i--;

					posfija.push(numero);
					break;
				case 3:// + -
					//Potencia negativa y multiplicacion por un negativo
					if(i > 0){
						if(charVal(infija[i-1]) == 4 || charVal(infija[i-1]) == 5 || charVal(infija[i-1]) == 1){
							operandos.push('*');
							posfija.push("-1");
							break;
						}
					}
					precedencia(infija[i],valInfija);
					break;
				case 4:// * /
					precedencia(infija[i],valInfija);
					break;
				case 5:// ^
					precedencia(infija[i],valInfija);
					break;
				case 6:// x,y,z
					posfija.push(String.valueOf(infija[i]));
					break;
				case 7:// funciones
					String func = "";

					while(charVal(infija[i]) == 7){

						func += String.valueOf(infija[i]);

						if(i < tam)
							i++;
					}
					i--;

					char[] fun = func.toCharArray();
 
					for(int j=func.length()-1;j>=0;j--)
						operandos.push(fun[j]);

					break;
				default:
					break;
			}
			i++;
		}

		while(!operandos.empty()){
			posfija.push(String.valueOf(operandos.pop()));
		}
	}

	/** * Metodo privado setPostfixExpression que se encarga de asignar la forma posfija en una string y un arreglo de caracteres de la Stack de Strings que recibe.*/
	private void setPostfixExpression(Stack<String> posfija){

		str_posfija = (posfija.toString()).replaceAll("[\\]\\[,]","");

		char_posfija = (str_posfija.trim()).split(" ");
	}

	/** * Metodo privado precedencia que se encarga de seleccionar la precedencia de un numero o un operador y ingresarlo a la pila de operando o operadores segun sea el caso,
	 recibe un caracter que representa el caracter a checar la precedencia y un int que representa la precedencia del caracter anterior, no regresa ningun valor.*/
	private void precedencia(char infija,int valInfija){
		if(operandos.empty())
			operandos.push(infija);

		else if(valInfija == charVal(operandos.peek())){//se compara la operacion con la operacion anterior para ver si son iguales y si lo son se vacia la pila
			posfija.push(String.valueOf(operandos.pop()));
			operandos.push(infija);
		}

		else if(valInfija > charVal(operandos.peek())){
			operandos.push(infija);
		}

		else if(valInfija < charVal(operandos.peek())){
			posfija.push(String.valueOf(operandos.pop()));
			precedencia(infija,valInfija);
		}
	}

	/** * Metodo privado que se encarga de validar si es un caracter valido en una expresion matematica.*/
	private int charVal(char caracter){
	 	//revisa que se parentesis )
	 	if(caracter == 41)
			return 0;
		//revisa que sea parentesis (
		else if(caracter == 40)
			return 1;
		//revisa que sea numero
		else if(caracter >= 48 && caracter <= 57 || caracter == 46 || caracter == 101)
			return 2;
		//revisa que sea +/-
		else if(caracter == 43 || caracter == 45)
			return 3;
		//revisa que sea * /
		else if(caracter == 42 || caracter == 47)
			return 4;
		//revisa que sea ^
		else if(caracter == 94)
			return 5;
		//revisa que sea n x,y,z
		else if(caracter >= 120 && caracter <= 122)
			return 6;
		//si es un funcion cualquier carcter exepto el espacio
		else if(caracter != 32)
			return 7;
		else
			return 8;
	}	

	/** * Metodo publico getPostfixString que se encarga de regresar un String que representa la expresion matematica en su forma posfija.*/
	public String getPostfixString(){
		return str_posfija;
	}

	/** * Metodo publico getPostfixChar que se encarga de regresar un arreglo de Strings que representan la expresion matematica en su forma posfija.*/
	public final String[] getPostfixChar(){
		return char_posfija;
	}
}