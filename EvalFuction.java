package modelo;

import modelo.Posfija;
import java.util.Stack;

/**
	* La clase EvalFuction es una clase que se encarga de evaluar cualquier expresion matemática valida.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/

public class EvalFuction{
	/** * Variable privada de tipo Posfija que trasforma la expresion matemática en su forma posfija.*/
	private Posfija formula;
	/** * Arreglo de String privado que se encarga de guardar la función ingresada por el usuario en su forma Posfija. */
	private String[] funcion;
	/** * Variable privada de tipo double que sirve para almacenar el valor al evaluar la expresion matemática. */
	private double aux = 0;

	/** * Método Constructor sin argumentos. */
	public EvalFuction(){}

	/** * Método Constructor que recibe un String que representa la expresion matemática. */
	public EvalFuction(String expresion){
		formula = new Posfija(expresion);
		funcion = formula.getPostfixChar();
	}

	/** * Método publico setEvaluar que recibe un arreglo String el cual representa la expresion matemática en su forma posfija, el método se encarga de evaluar la función. */
	public void setEvaluar(String[] posfija){
		setEvaluar(posfija,0,0,0);
	}

	/** * Método publico setEvaluar que recibe un arreglo String el cual representa la expresion matemática en su forma posfija, el método se encarga de evaluar la función en la variable double recibida.*/
	public void setEvaluar(String[] posfija,double x){
		setEvaluar(posfija,x,0,0);
	}

	/** * Método publico setEvaluar que recibe un arreglo String el cual representa la expresion matemática en su forma posfija, el método se encarga de evaluar la función en las dos variables double recibidas.*/
	public void setEvaluar(String[] posfija,double x,double y){
		setEvaluar(posfija,x,y,0);
	}

	/** * Método publico setEvaluar que recibe un arreglo String el cual representa la expresion matemática en su forma posfija, el método se encarga de evaluar la función en las tres variables double recibidas.*/
	public void setEvaluar(String[] posfija,double x,double y,double z){
		Stack<Double> pila = new Stack<Double>();
		double val1 = 0,val2 = 0;
		boolean fracion_par = false;
		int i = 0;
	
		while(i < posfija.length){

			//Si es un numero
			if(posfija[i].matches("(-[0-9]|[0-9]|\\.?)+")){
				aux = Double.parseDouble(posfija[i]);
				pila.push(aux);
			}
			
			//Funciones
			else if(posfija[i].matches("([sinqrcotah])?")){
				String func = "";
				while(posfija[i].matches("([sinqrcotah])?")){	
					func += String.valueOf(posfija[i]);
					if(i < posfija.length-1)
						i++;
					else
						break;
				}

				if(func.length() >= 2){
					i--;
					aux = getFun(func,pila.pop());
					pila.push(aux);
				}
			}

			//Si es un operador
			else{
				switch(posfija[i]){
					case "+":
						val2 = pila.pop();
						val1 = pila.pop();
						aux = val1 + val2;
						pila.push(aux);
						break;

					case "-":
						if(pila.size() == 1){
							val2 = pila.pop();
							val1 = 0;
						}
						else{
							val2 = pila.pop();
							val1 = pila.pop();
						}
						aux = val1 - val2;
						pila.push(aux);
						break;

					case "*":
						val2 = pila.pop();
						val1 = pila.pop();
						aux = val1 * val2;
						pila.push(aux);
						break;

					case "/":
						val2 = pila.pop();
						val1 = pila.pop();
						if(val2 % 2 == 0)
							fracion_par = true;
						aux = val1 / val2;
						pila.push(aux);
						break;

					case "^":
						val2 = pila.pop();
						val1 = pila.pop();
						if(!fracion_par && val1 < 0){
							val1 = val1 * -1;
							aux = Math.pow(val1,val2);
							pila.push(aux*-1);
						}
						else{
							aux = Math.pow(val1,val2);
							pila.push(aux);
						}
						break;

					case "x":
						pila.push(x);
						break;

					case "y":
						pila.push(y);
						break;

					case "z":
						pila.push(z);
						break;

					case "e":
						aux = Math.E;
						pila.push(aux);
						break;

					case "pi":
						aux = Math.PI;
						pila.push(aux);
						break;

					default:
						break;
				}
			}
			i++;
		}

		if(!pila.empty())
			aux = pila.pop();
	}

	/** * Método publico setEvaluar que recibe un arreglo String el cual representa la expresion matemática en su forma posfija, el método se encarga de evaluar la función en las n variables double recibidas.*/
	public void setEvaluar(String[] posfija,double[] valores){
		Stack<Double> pila = new Stack<Double>();
		double val1 = 0,val2 = 0;
		boolean fracion_par = false;
		String var = "xyzuvwbdfgjklmp";
		int i = 0,j=0;
	
		while(i < posfija.length){

			//Si es un numero
			if(posfija[i].matches("(-[0-9]|[0-9]|\\.?)+")){
				aux = Double.parseDouble(posfija[i]);
				pila.push(aux);
			}
			
			//Funciones
			else if(posfija[i].matches("([sinqrcotah])?")){
				String func = "";
				while(posfija[i].matches("([sinqrcotah])?")){	
					func += String.valueOf(posfija[i]);
					if(i < posfija.length-1)
						i++;
					else
						break;
				}

				if(func.length() >= 2){
					i--;
					aux = getFun(func,pila.pop());
					pila.push(aux);
				}
			}

			else if(posfija[i].matches("([xyzuvwbdfgjklmp])?")){
				j = var.indexOf(posfija[i]);
				pila.push(valores[j]);
			}

			//Si es un operador
			else{
				switch(posfija[i]){
					case "+":
						val2 = pila.pop();
						val1 = pila.pop();
						aux = val1 + val2;
						pila.push(aux);
						break;

					case "-":
						if(pila.size() == 1){
							val2 = pila.pop();
							val1 = 0;
						}
						else{
							val2 = pila.pop();
							val1 = pila.pop();
						}
						aux = val1 - val2;
						pila.push(aux);
						break;

					case "*":
						val2 = pila.pop();
						val1 = pila.pop();
						aux = val1 * val2;
						pila.push(aux);
						break;

					case "/":
						val2 = pila.pop();
						val1 = pila.pop();
						if(val2 % 2 == 0)
							fracion_par = true;
						aux = val1 / val2;
						pila.push(aux);
						break;

					case "^":
						val2 = pila.pop();
						val1 = pila.pop();
						if(!fracion_par && val1 < 0){
							val1 = val1 * -1;
							aux = Math.pow(val1,val2);
							pila.push(aux*-1);
						}
						else{
							aux = Math.pow(val1,val2);
							pila.push(aux);
						}
						break;

					case "e":
						aux = Math.E;
						pila.push(aux);
						break;

					case "pi":
						aux = Math.PI;
						pila.push(aux);
						break;

					default:
						break;
				}
			}
			i++;
		}

		if(!pila.empty())
			aux = pila.pop();
	}

	/** * Método publico getResult que regresa un double que representa el valor al evaluar la expresion matemática en las variables enviadas.*/
	public double getResult(){
		return aux;
	}

	/** * Método publico getFuncion que regresa un arreglo String que representa la expresion matemática en su forma Posfija.*/
	public String[] getFuncion(){
		return funcion;
	}

	/** * Método privado getFun que recibe un String que representa la función a efectuar y un valor double que representa el valor a evaluar en dicha función.*/
	private double getFun(String funcion, double valor){
		double aux = 0;
		if(funcion.equals("in"))
			aux = Math.log(valor);
		
		else if(funcion.equals("abs"))
			aux = Math.abs(valor);

		else if(funcion.equals("sqrt")){
			if(valor < 0)
				System.out.println("Raiz Imaginaria");
			else
				aux = Math.sqrt(valor);
		}

		else if(funcion.length() == 3)
			aux = getFunTrig(funcion,valor);

		else if(funcion.length() == 4)
			aux = getArcFunTrig(funcion,valor);

		else if(funcion.length() == 5)
			aux = getArcFunTrigHip(funcion,valor);
		
		return aux;
	}

	/** * Método privado getFun que recibe un String que representa la función trigonométrica a efectuar y un valor double que representa el valor a evaluar en dicha función. */
	private double getFunTrig(String funcion,double valor){
		double aux = 0;
		double b = valor;//Math.toRadians(valor);
		//sen
		if(funcion.equals("sin"))
			aux = Math.sin(b);
		//cos
		else if(funcion.equals("cos"))
			aux = Math.cos(b);
		//tan
		else if(funcion.equals("tan"))
			aux = Math.tan(b);

		//sec
		else if(funcion.equals("sec"))
			aux = 1 / Math.cos(b);
		//csc
		else if(funcion.equals("csc"))
			aux = 1 / Math.sin(b);
		//cot
		else if(funcion.equals("cot"))
			aux = 1 / Math.tan(b);

		return aux;
	}

	/** * Método privado getFun que recibe un String que representa la función trigonométrica inversa a efectuar y un valor double que representa el valor a evaluar en dicha función.*/
	private double getArcFunTrig(String funcion,double b){
		double aux = 0;
		if(funcion.equals("asin"))
			aux = Math.asin(b);

		else if(funcion.equals("acos"))
			aux = Math.acos(b);

		else if(funcion.equals("atan"))
			aux = Math.atan(b);

		else if(funcion.equals("asec"))
			aux = Math.acos(1/b);

		else if(funcion.equals("acsc"))
			aux = Math.asin(1/b);

		else if(funcion.equals("acot"))
			aux = Math.atan(1/b);

		return aux;
	}

	/** * Método privado getFun que recibe un String que representa la función trigonométrica hiperbolica a efectuar y un valor double que representa el valor a evaluar en dicha función.*/
	private double getFunTrigHip(String funcion,double b){
		double aux = 0;
		if(funcion.equals("sinh"))
			aux = Math.sinh(b);

		else if(funcion.equals("cosh"))
			aux = Math.cosh(b);

		else if(funcion.equals("tanh"))
			aux = Math.tanh(b);

		else if(funcion.equals("sech"))
			aux = (2 * Math.cosh(b)) / (Math.cosh(2*b)+1);

		else if(funcion.equals("csch"))
			aux = -(2 * Math.sinh(b)) / (1 - Math.cosh(2*b));

		else if(funcion.equals("coth"))
			aux = -(Math.sinh(2*b)) / (1 - Math.cosh(2*b));

		return aux;
	}

	/** * Método privado getFun que recibe un String que representa la función trigonométrica hiperbólica inversa a efectuar y un valor double que representa el valor a evaluar en dicha función. */
	private double getArcFunTrigHip(String funcion,double b){
		double aux = 0;
		if(funcion.equals("asinh"))
			aux = Math.log(b + Math.sqrt(Math.pow(b,2) + 1));

		else if(funcion.equals("acosh")){
			if(b != 0)
				aux = Math.log(b + Math.sqrt(Math.pow(b,2) - 1));
		}

		else if(funcion.equals("atanh")){
			if(b > -1 && b < 1);
				aux =  (1/2) * Math.log((1+b)/(1-b));
		}

		else if(funcion.equals("asech")){
			if(b != 0)
				aux = Math.log(1/b + Math.sqrt(1/(Math.pow(b,2)) - 1));
		}

		else if(funcion.equals("acsch")){
			if(b != 0)
				aux = Math.log(1/b + Math.sqrt(1/(Math.pow(b,2)) + 1));
		}

		else if(funcion.equals("acoth")){
			if(b > -1 || b < 1);
				aux = (1/2)* Math.log((b+1)/(b-1));
		}

		return aux;
	}
}
