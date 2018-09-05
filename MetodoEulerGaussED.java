package modelo.theme5;

import modelo.EvalFuction;

/**
	* La clase MetodoEulerGaussED es una clase que se encarga de realizar el metodo numerico de Euler-Gauss para resolver ecuaciones diferenciales
	* de la materia de analisis numerico.
	* @author German Lopez Rodrigo
	* @version 13/05/2017/Final 
*/
public class MetodoEulerGaussED{
	/** * Variable privada de tipo EvalFuction que se encarga de guardar la funcion ingresada por el usuario.*/
	private EvalFuction eval_funcion;
	/** * Arreglo de String privado que se encarga de guardar la funcion ingresada por el usuario en su forma Posfija.*/
	private String[] funcion;
	/** * Variables privadas de tipo double donde x representa la x a evaluar la solucion de la ecuacion diferencial,donde y representa la y a evaluar la solucion de la ecuacion diferencial
	 h representa la separacion entre cada punto al evaluarlo en la ecuacion diferencial, a representa el valor inicial evaluado en la variable ax.*/
	private double x,y,h,a,ax;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el metodo de Biseccion.*/
	private StringBuffer iteraciones = new StringBuffer();

	private int n;

	/** * Metodo Constructor sin argumentos.*/
	public MetodoEulerGaussED(){}

	/** * Metodo Constructor que recibe un String el cual representa a la funcion, cuatro double donde a representa el valor inicial evaluado en la variable ax, donde 
	h representa la separacion entre cada punto al evaluarlo en la ecuacion diferencial y x representa la x a evaluar la solucion de la ecuacion diferencial.*/
	public MetodoEulerGaussED(String funcion,int n,double a,double ax,double h,double x){
		this.n = n;
		this.x = x;
		this.h = h;
		//valor inicial Y0
		this.a = a;
		//valor inicial x0
		this.ax = ax;

		eval_funcion = new EvalFuction(funcion.trim());
		this.funcion = eval_funcion.getFuncion();

		this.y = fy(x);
		iteraciones.append("Solucion: "+y+"\n");
	}

	/** * Metodo privado f que recibe dos double x,y con el cual evalua la ecuacion diferencial en dichas variables y regresa el valor evaluado en dicha ecuacion diferencial.*/
	private double f(double x,double y){
		eval_funcion.setEvaluar(this.funcion,x,y);
		return eval_funcion.getResult();
	}

	/** * Metodo publico fy que es el encargado de realizar el metodo de Euler, recibe un double que representa el valor a evaluar, regresa un double que representa el valor evaluado en la solucion de la ecuacion diferencial.*/
	public double fy(double x){
		double aprox = 0.0,aux;

		if(x == ax)
			aprox = a;

		else{

			aux = fy((Math.ceil((x-h)*100000)/100000));

			aprox = aux + h*(f(x-h,aux));

			aprox = aux + h/2*(f(x-h,aux)+f(x+h,aprox));

			iteraciones.append("Aprox: "+aprox+"\n");

		}

		return aprox;
	}

	/** * Metodo publico getSolucion que se encarga de regresar un double con la solucion del metodo de Euler-Gauss.*/
	public double getSolucion(){
		return this.y;
	}
	
	/** * Metodo publico getIteraciones que se encarga de regresar un String con todas las iteraciones del metodo Euler-Gauss.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}
}