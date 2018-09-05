package modelo.theme2.openmethods;

import modelo.EvalFuction;

/**
	* La clase NewtonRaphson es una clase que se encarga de realizar el método numérico de Newton Raphson
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class NewtonRaphson{
	/** * Variables privadas de tipo EvalFuction que se encargan de guardar la función, la primera derivada y la segunda derivada de la función ingresadas por el usuario respectivamente.*/
	private EvalFuction eval_funcion,eval_funcion_derived,eval_funcion_derived2;
	/** * Arreglos de String privados que se encargan de guardar la función, primera la derivada y la segunda derivada de la función ingresadas por el usuario respectivamente en su forma Posfija.*/
	private String[] funcion,derivada,derivada2;
	/** * Variable privada de tipo double que se encarga de guardar la solución que nos regresa el método de Newton Raphson*/
	private double x0 = 0;
	/** * Variable privada de tipo double que se encarga de asignar un valor especifico a la estimación de error con el que se realizara el método de Newton Raphson.*/
	private double estimacion;
	/** * Variable privada de tipo StringBuffer que se encargas de guardar las iteraciones que va realizando el método de Newton Raphson.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo byte que se encarga de llevar un conteo del numero de iteraciones que va realizando el método de Newton Raphson.*/	
	private byte cont = 0;

	/** * Método Constructor sin argumentos.*/
	public NewtonRaphson(){}

	/** * Método Constructor que recibe tres Strings donde una representa a la función otra que representa la primera derivada y otra que representa la segunda derivada y un double que representa la estimación de error para el método de Aproximaciones Sucesivas.*/
	public NewtonRaphson(String expresion,String derivada,String derivada2,double estimacion){
		setFunction(expresion);
		setDerivedFunction(derivada);
		setDerived2Function(derivada2);
		this.estimacion = estimacion;
	}

	/** * Método publico setAproximacion que es el encargado de realizar el método de Newton Raphson, no regresa ningún valor.*/
	public void setAproximacion(double a){
		double fa,dfa;
		fa = f(a);
		dfa = df(a);
		x0 = a - (fa/dfa);
		if(getConvergencia(x0)){
			if(Math.abs(x0 - a) > estimacion){
				setIteraciones(++cont,x0,a);
				setAproximacion(x0);
			}
		}
		else{
			x0=0;
			iteraciones.append("\n\nEl método diverge la raiz calculada no correponde a ninguna valida.\n");
		}
	}

	/** * Método publico setFunction que es el encargado de crear el objeto EvalFuction para la función a partir de la String de la función recibida por el usuario, no regresa ningún valor.*/
	public void setFunction(String expresion){
		eval_funcion = new EvalFuction(expresion.trim());
		this.funcion = eval_funcion.getFuncion();
	}

	/** * Método publico setDerivedFunction que es el encargado de crear el objeto EvalFuction para la primera derivada de la función a partir de la String de la primera derivada de la función recibida por el usuario, no regresa ningún valor.*/
	public void setDerivedFunction(String expresion){
		eval_funcion_derived = new EvalFuction(expresion.trim());
		this.derivada = eval_funcion_derived.getFuncion();
	}

	/** * Método publico setDerivedFunction que es el encargado de crear el objeto EvalFuction para la segunda derivada de la función a partir de la String de la segunda derivada de la función recibida por el usuario, no regresa ningún valor.*/
	public void setDerived2Function(String expresion){
		eval_funcion_derived2 = new EvalFuction(expresion.trim());
		this.derivada2 = eval_funcion_derived2.getFuncion();
	}

	/** * Método privado setIteraciones que es el encargado de ir agregando las interacciones que va realizando el método Newton Raphson, no regresa ningún valor.*/
	private void setIteraciones(int cont,double x0,double a){
		int tam = (String.valueOf(x0)).length();
		if(tam <= 11)
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t\t|        " + Math.abs(x0-a) + "\n");
		else if(tam <= 30)
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t|        " + Math.abs(x0-a) + "\n");
		else
			iteraciones.append("           " + cont + "\t    |\t" + x0 + "\t|        " + Math.abs(x0-a) + "\n");
	}

	/** * Método privado f que recibe un double x con el cual evalúa la función y regresa el valor evaluado en dicha función.*/
	private double f(double x){
		eval_funcion.setEvaluar(this.funcion,x);
		return eval_funcion.getResult();
	}

	/** * Método privado df que recibe un double x con el cual evalúa la primera derivada de la función y regresa el valor evaluado en dicha derivada.*/
	private double df(double x){
		eval_funcion_derived.setEvaluar(this.derivada,x);
		return eval_funcion_derived.getResult();
	}

	/** * Método privado df que recibe un double x con el cual evalúa la segunda derivada de la función y regresa el valor evaluado en dicha derivada.*/
	private double d2f(double x){
		eval_funcion_derived2.setEvaluar(this.derivada2,x);
		return eval_funcion_derived2.getResult();
	}

	/** * Método publico getRaiz que se encarga de regresar un double con la solución del método Newton Raphson.*/
	public double getRaiz(){
		return x0;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Newton Raphson.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método privado getConvergencia que se encarga de regresar un boolean el cual representa si converge el método Newton Raphson.*/
	public boolean getConvergencia(double a){
		return Math.abs((f(a)*d2f(a))/(Math.pow(df(a),2))) < 1;
	}
}