package modelo.theme2.openmethods;

import modelo.EvalFuction;

/**
	* La clase AproximacionesSucesivas es una clase que se encarga de realizar el método numérico 
	* de Aproximaciones Sucesivas de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class AproximacionesSucesivas{
	/** * Variables privadas de tipo EvalFuction que se encargan de guardar la función y la derivada de la función ingresadas por el usuario respectivamente. */
	private EvalFuction eval_funcion,eval_funcion_derived;
	/** * Arreglos de String privados que se encargan de guardar la función y la derivada de la función ingresadas por el usuario respectivamente en su forma Posfija. */
	private String[] funcion,derivada;
	/** * Variable privada de tipo double que se encarga de guardar la solución que nos regresa el método de Aproximaciones Sucesivas. */
	private double x0 = 0;
	/** * Variable privada de tipo double que se encarga de asignar un valor especifico a la estimación de error con el que se realizara el método de Aproximaciones Sucesivas. */
	private double estimacion = 0.000000000000001;
	/** * Variable privada de tipo boolean que se encarga de asignar si el método de Aproximaciones Sucesivas va divergiendo o va convergiendo. */	
	private boolean diverge = false;
	/** * Variable privada de tipo StringBuffer que se encargas de guardar las iteraciones que va realizando el método de Aproximaciones Sucesivas. */	
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo byte que se encarga de llevar un conteo del número de iteraciones que va realizando el método de Aproximaciones Sucesivas. */	
	private byte cont = 0;

	/** * Método Constructor sin argumentos. */
	public AproximacionesSucesivas(){}

	/** * Método Constructor que recibe dos Strings donde una representa a la función otra que representa la derivada y un double que representa la estimación de error para el método de Aproximaciones Sucesivas. */
	public AproximacionesSucesivas(String expresion,String derivada,double estimacion){
		setFunction(expresion);
		setDerivedFunction(derivada);
		setExtimacion(estimacion);
	}

	/** * Método privado setExtimacion que tiene como función validar si la estimación recibida es válida en caso verdadero asignar y en caso contrario asignar un valor establecido. */
	private void setExtimacion(double estimacion){
		if(estimacion > 0 && estimacion > 0.000000000000001)
			this.estimacion = estimacion;
	}

	/** * Método publico setAproximacion que es el encargado de realizar el método de Aproximaciones Sucesivas, no regresa ningún valor. */
	public void setAproximacion(double a){
		x0 = f(a);
		if(getConvergencia(a)){
			if(Math.abs(x0 - a) > estimacion){
				setIteraciones(++cont,x0,a);
				setAproximacion(x0);
			}
		}
		else{
			x0=0;
			diverge = true;
		}
	}

	/** * Método publico setFunction que es el encargado de crear el objeto EvalFuction para la función a partir de la String de la función recibida por el usuario, no regresa ningún valor. */
	public void setFunction(String expresion){
		eval_funcion = new EvalFuction(expresion.trim());
		this.funcion = eval_funcion.getFuncion();
	}

	/** * Método publico setDerivedFunction que es el encargado de crear el objeto EvalFuction para la derivada a partir de la String derivada recibida por el usuario, no regresa ningún valor. */
	public void setDerivedFunction(String expresion){
		eval_funcion_derived = new EvalFuction(expresion.trim());
		this.derivada = eval_funcion_derived.getFuncion();
	}

	/** * Método privado setIteraciones que es el encargado de ir agregando las interacciones que va realizando el método Aproximaciones Sucesivas, no regresa ningún valor. */
	private void setIteraciones(int cont,double x0,double a){
		int tam = (String.valueOf(x0)).length();
		if(tam <= 11)
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t\t|        " + Math.abs(x0-a) + "\n");
		else if(tam <= 30)
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t|        " + Math.abs(x0-a) + "\n");
		else
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t|        " + Math.abs(x0-a) + "\n");
	}

	/** * Método privado f que recibe un double x con el cual evalúa la función y regresa el valor evaluado en dicha función. */
	private double f(double x){
		eval_funcion.setEvaluar(this.funcion,x);
		return eval_funcion.getResult();
	}

	/** * Método privado df que recibe un double x con el cual evalúa la derivada de la función y regresa el valor evaluado en dicha derivada.*/
	private double df(double x){
		eval_funcion_derived.setEvaluar(this.derivada,x);
		return eval_funcion_derived.getResult();
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Aproximaciones Sucesivas. */
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getRaiz que se encarga de regresar un double con la solución del método Aproximaciones Sucesivas. */
	public double getRaiz(){
		return x0;
	}

	/** * Método publico getDiverge que se encarga de regresar un boolean el cual representa si diverge el método Aproximaciones Sucesivas.*/
	public boolean getDiverge(){
		return diverge;
	}

	/** * Método privado getConvergencia que se encarga de regresar un boolean el cual representa si converge el método Aproximaciones Sucesivas. */
	private boolean getConvergencia(double x0){
		return Math.abs(df(x0)) < 1.5;
	}
}
