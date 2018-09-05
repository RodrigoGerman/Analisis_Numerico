package modelo.theme2.closedmethods;

import modelo.EvalFuction;

/**
	* La clase Bisección es una clase que se encarga de realizar el método numérico de Bisección
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class Biseccion{
	/** * Variable privada de tipo EvalFuction que se encarga de guardar la función ingresada por el usuario. */
	private EvalFuction eval_funcion;
	/** * Arreglo de String privado que se encarga de guardar la función ingresada por el usuario en su forma Posfija. */
	private String[] funcion;
	/** * Variable privada de tipo double que se encarga de guardar la solución que nos regresa el método de Bisección. */
	private double x0 = 0;
	/** * Variable privada de tipo double que se encarga de asignar un valor especifico a la estimación de error con el que se realizara el método de Bisección. */
	private double estimacion;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Bisección. */	
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo byte que se encarga de llevar un conteo del número de iteraciones que va realizando el método de Bisección. */
	private byte cont = 0;

	/** * Método Constructor sin argumentos. */
	public Biseccion(){}

	/** * Método Constructor que recibe un String el cual representa a la función y un double que representa la estimación de error para el método de Bisección. */
	public Biseccion(String expresion,double estimacion){
		eval_funcion = new EvalFuction(expresion.trim());
		this.funcion = eval_funcion.getFuncion();
		this.estimacion = estimacion;
	}

	/** * Método publico setAproximacion que es el encargado de realizar el método de Bisección, no regresa ningún valor. */
	public void setAproximacion(double a,double b){
		double fa,fb,fx0;

		this.x0 = (a + b) / 2;

		fx0 = f(x0);
		fa = f(a);
		fb = f(b);

		if(fx0 * fa < 0)
			b = this.x0;

		else if(fx0 * fb <  0)
				a = this.x0;

		if(Math.abs(fx0) > estimacion){
			setIteraciones(++cont,x0,fx0);
			setAproximacion(a,b);
		}
	}

	/** * Método privado f que recibe un double x con el cual evalúa la función y regresa el valor evaluado en dicha función. */
	private double f(double x){
		eval_funcion.setEvaluar(this.funcion,x);
		return eval_funcion.getResult();
	}

	/** * Método privado setIteraciones que es el encargado de ir agregando las interacciones que va realizando el método Bisección, no regresa ningún valor. */
	private void setIteraciones(int cont,double x0,double fx0){
		int tam = (String.valueOf(x0)).length();
		if(tam <= 11)
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t\t|        " + Math.abs(fx0) + "\n");
		else if(tam <= 30)
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t|        " + Math.abs(fx0) + "\n");
		else
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t|        " + Math.abs(fx0) + "\n");
	}

	/** * Método publico getRaiz que se encarga de regresar un double con la solución del método Bisección.*/
	public double getRaiz(){
		return x0;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Bisección. */
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getConvergencia que se encarga de regresar un boolean el cual representa si converge el método Bisección.*/
	public boolean getConvergencia(double a,double b){
		return f(a) * f(b) < 0;
	}
}
