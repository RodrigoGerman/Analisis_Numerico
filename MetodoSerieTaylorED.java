package modelo.theme5;

import modelo.EvalFuction;

/**
	* La clase MetodoRungeKutta es una clase que se encarga de realizar el método numérico de Taylor para resolver ecuaciones diferenciales
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class MetodoSerieTaylorED{
	/** * Variable privada de tipo EvalFuction que se encarga de guardar la función ingresada por el usuario.*/
	private EvalFuction eval_funcion;
	/** * Arreglo de String privado que se encarga de guardar la función ingresada por el usuario en su forma Posfija.*/
	private String[] funcion;
	/** * Variables privadas de tipo double donde x representa la x a evaluar la solución de la ecuación diferencial, donde y representa la y a evaluar la solución de la ecuación diferencial
	 h representa la separación entre cada punto al evaluarlo en la ecuación diferencial, a representa el valor inicial evaluado en la variable ax.*/
	private double x,y,h,a,ax;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Taylor.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo int que representa el numero de divisiones a utilizar.*/
	private int n;

	/** * Método Constructor sin argumentos.*/
	public MetodoSerieTaylorED(){}

	/** * Método Constructor que recibe un String el cual representa a la función, un int que representa el numero de divisiones a utilizar y cuatro double donde a representa el valor inicial evaluado en la variable ax, donde 
	h representa la separación entre cada punto al evaluarlo en la ecuación diferencial y x representa la x a evaluar la solución de la ecuación diferencial.*/
	public MetodoSerieTaylorED(String funcion,int n,double a,double ax,double h,double x){
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
		iteraciones.append("Solución: "+y+"\n");
	}

	/** * Método privado f que recibe dos double x,y con el cual evalúa la ecuación diferencial en dichas variables y regresa el valor evaluado en dicha ecuación diferencial.*/
	private double f(double x,double y){
		eval_funcion.setEvaluar(this.funcion,x,y);
		return eval_funcion.getResult();
	}

	/** * Método privado df que recibe dos double x,y con el cual evalúa la derivada de la ecuación diferencial en dichas variables y regresa el valor evaluado en dicha ecuación diferencial.*/
	private double df(int n,double x,double y){
		double fn = 0.0;

		if(n == 1)
			fn = (-f(x+2*h,y+2*h)+8*f(x+h,y+h)-8*f(x-h,y-h)+f(x-2*h,y-2*h))/(12*h);

		else 
			if(n > 1)
				fn = (df(n-1,x+h,y+h)-df(n-1,x-h,y-h))/(2*h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Método publico fy que es el encargado de realizar el método de Taylor, recibe un double que representa el valor a evaluar, regresa un double que representa el valor evaluado en la solución de la ecuación diferencial.*/
	private double fy(double x){
		double aprox = 0.0,aux;

		if(x == ax)
			aprox = a;

		else{

			aux = fy((Math.ceil((x-h)*100000)/100000));

			aprox = aux + h*(f(x-h,aux));

			for(int i = 2;i <= n;i++)
				aprox += (Math.pow(h,i)/getFactorial(i+1))*(df(i-1,ax,aux));

			iteraciones.append("Aprox: "+aprox+"\n");

		}

		return aprox;
	}

	/** * Método privado getFactorial que es el encargado de calcular y regresar el factorial del numero double que recibe.*/
	private double getFactorial(double k){
		double factorial = 1.0;
		while(true){
			if(k <= 1)
				break;
			factorial *= k;
			k -= 1;
		}
		return factorial;
	}

	/** * Método publico getSolucion que se encarga de regresar un double con la solución del método de Taylor.*/
	public double getSolucion(){
		return this.y;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método de Taylor.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}
}
