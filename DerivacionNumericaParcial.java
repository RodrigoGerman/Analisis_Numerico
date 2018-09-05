package modelo.theme6;	

import modelo.EvalFuction;

/**
	* La clase DerivacionNumericaParcial es una clase que se encarga de realizar el método numérico de Derivación Numérica Parcial a partir de diferencias finitas parciales
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class DerivacionNumericaParcial{
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Derivación Numérica Parcial. */
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo EvalFuction que se encarga de guardar la función multivarible ingresada por el usuario. */
	private EvalFuction eval_funcion;
	/** * Arreglo de tipo int privado que se encarga de guardar los grados de derivación respecto a cada variable de la función recibida. */
	private int[] grados;
	/** * Arreglos de tipo double donde h1 se encarga de guardar la separación entre los puntos evaluados de cada variable y num representa los valores de cada variable de la función recibida. */
	private double[] h1,num;
	/** * Arreglo de String privado que se encarga de guardar la función ingresada por el usuario en su forma Posfija. */
	private String[] funcion;
	/** * Variables privadas de tipo double donde fn almacena el resultado de evaluar la derivada parcial y h almacena la separación entre cada punto a evaluar general. */
	private double fn ,h;
	/** * Variables privadas de tipo int donde error almacena el grado de error a utilizar para el método de diferencias finitas, tam almacena el tamaño del número de puntos a utiliza y aux almacena el número a dividir las derivadas parciales. */	
	private int error,tam,aux;
	/** * Variable privada de tipo byte la cual almacena la cantidad de variables a derivar y sus grados de derivación. */
	private byte band = 0;

	/** * Método Constructor sin argumentos.*/
	public DerivacionNumericaParcial(){}

	/** * Método Constructor que recibe un String el cual representa la función, un arreglo double que representa los valores de las variables a evaluar la derivada parcial, un int donde grado representa el grado de derivación general, 
	un arreglo de int que representan los grados de derivación respecto a cada variable, un arreglo de double que representa la separación en cada punto a evaluar respecto a cada variable, una String que representa el método de derivación a utilizar y un int que representa el grado de error a utilizar. */
	public DerivacionNumericaParcial(String funcion,double[] num,int grado,int[] grados,double[] h,String metodo,int error){
		this.error = error;
		this.h1 = h;
		this.num = num;
		this.grados = grados;
		this.tam = num.length;
		eval_funcion = new EvalFuction(funcion.trim());
		this.funcion = eval_funcion.getFuncion();
		iteraciones.append("Derivación Numérica Parcial\n");
		setTipo(grado);
		setMetodo(metodo,grado);
	}

	/** * Método privado setTipo que recibe un int que representa el grado de derivación, el método se encarga de calcular y asignar la separación general de cada punto a evaluar y el grado de derivación respecto a cada variable. */
	private void setTipo(int grado){
		for(int i = 0;i < grado;i++){
			this.h *= Math.pow(h1[i],grados[i]);
			if(grados[i] != 0)
				band++;
		}
	}

	/** * Método setMetodo que recibe una String que representa el método a usar y un int que representa el grado de derivación, el método se encarga de escoger el método a realizar y el grado de derivación a seguir. */
	private void setMetodo(String metodo,int grado){
		if(metodo.equals("adelante")){
			if(band == grado)
				aux = 2;
			else
				aux = 6;
			DerivatedAdelanteParcial(grado,num);
		}

		else if(metodo.equals("atras")){
			if(band == grado)
				aux = 2;
			else
				aux = 6;

			DerivatedAtrasParcial(grado,num);
		}

		else{
			if(band == grado)
				aux = 6;
			else
				aux = 2;
			DerivatedCentralParcial(grado,num);
		}
			
		iteraciones.append("Resultado: "+fn);
	}

	/** * Método privado f que recibe un arreglo double num el cual representa los valores de las variables a evaluar la función y regresa el valor evaluado en dicha función. */
	private double f(double[] num){
		eval_funcion.setEvaluar(this.funcion,num);
		return eval_funcion.getResult();
	}

	/** * Método privado DerivatedCentralParcial que es el encargado de realizar el método de diferencias finitas parciales centrales, recibe un int que representa el grado de derivación y un arreglo double num el cual 
	representa los valores de las variables a evaluar la función, regresa la derivada evaluada en dichas variables enviadas. */
	private double DerivatedCentralParcial(int n,double[] num){
		double[] aux1 = new double[tam];
		double[] aux2 = new double[tam];
		double[] aux3 = new double[tam];
		double[] aux4 = new double[tam];

		for(int i = 0;i < tam;i++){
			if(grados[i] != 0){
				aux1[i] = num[i]+2*h;
				aux2[i] = num[i]+h;
				aux3[i] = num[i]-h;
				aux4[i] = num[i]-2*h;
			}
			else{
				aux1[i] = num[i];
				aux2[i] = num[i];
				aux3[i] = num[i];
				aux4[i] = num[i];
			}
		}

		if(n == 1){
			if(error == 2)
				fn = (f(aux2)-f(aux3))/(2*h);

			else if(error == 4)
				fn = (-f(aux1)+8*f(aux2)-8*f(aux3)+f(aux4))/(12*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedCentralParcial(n-1,aux2)-DerivatedCentralParcial(n-1,aux3))/(aux*h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Método privado DerivatedAtrasParcial que es el encargado de realizar el método de diferencias finitas parciales hacia atrás, recibe un int que representa el grado de derivación y un arreglo double num el cual 
	representa los valores de las variables a evaluar la función, regresa la derivada evaluada en dichas variables enviadas. */
	private double DerivatedAtrasParcial(int n,double[] num){
		double[] aux1 = new double[tam];
		double[] aux2 = new double[tam];

		for(int i = 0;i < num.length;i++){
			aux1[i] = num[i]-h;
			aux2[i] = num[i]-2*h;
		}

		if(n == 1){
			if(error == 2)
				fn = (f(num)-f(aux1))/(h);

			else if(error == 4)
				fn = (3*f(num)-4*f(aux1)+f(aux2))/(2*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedAtrasParcial(n-1,num)-DerivatedAtrasParcial(n-1,aux1))/(aux*h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Método privado DerivatedCentral que es el encargado de realizar el método de diferencias finitas parciales hacia adelante, recibe un int que representa el grado de derivación y un arreglo double num el cual 
	representa los valores de las variables a evaluar la función, regresa la derivada evaluada en dichas variables enviadas. */
	private double DerivatedAdelanteParcial(int n,double[] num){

		double[] aux1 = new double[tam];
		double[] aux2 = new double[tam];

		for(int i = 0;i < num.length;i++){
			aux1[i] = num[i]+2*h;
			aux2[i] = num[i]+h;
		}


		if(n == 1){
			if(error == 2)
				fn = (f(aux2)-f(num))/(h);

			else if(error == 4)
				fn = (-f(aux1)+4*f(aux2)-3*f(num))/(2*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedAdelanteParcial(n-1,aux2)-DerivatedAdelanteParcial(n-1,num))/(aux*h);

		else
			fn = 0.0;
		
		return fn;
	}

	/** * Método publico getDerivada que es el encargado de regresar un double el cual representa la derivada parcial evaluada en las variables enviadas. */
	public double getDerivada(){
		return this.fn;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método diferencias finitas parciales empleado. */
	public String getIteraciones(){
		return iteraciones.toString();
	}
}
