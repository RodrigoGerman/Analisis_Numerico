package modelo.theme4;

import modelo.FuncionTabular;
import modelo.EvalFuction;

/**
	* La clase DerivacionNumerica es una clase que se encarga de realizar el método numérico de Derivación Numérica a partir de diferencias finitas
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class DerivacionNumerica{
	/** * Variable privada de tipo EvalFuction que se encarga de guardar la función ingresada por el usuario. */
	private EvalFuction eval_funcion;
	/** * Arreglo de String privado que se encarga de guardar la función ingresada por el usuario en su forma Posfija. */
	private String[] funcion;
	/** * Variable privada de tipo FuncionTabular que se encarga de guardar la función tabular ingresada por el usuario. */
	private FuncionTabular datos;
	/** * Variables privadas de tipo double donde fn almacena el resultado de evaluar la derivada, h almacena la separación entre cada punto a evaluar y la x almacena el valor a evaluar la derivada. */
	private double fn = 0.0,h = 0.0,x = 0.0;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Derivación Numérica. */
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variables privadas de tipo int donde pos se encarga de guardar la posición de la x a evaluar en una función tabular y error almacena el grado de error a utilizar para el método de diferencias finitas. */
	private int pos=0,error = 0;

	/** * Método Constructor sin argumentos. */
	public DerivacionNumerica(){}

	/** * Método Constructor que recibe una FuncionTabular la cual representa la función tabular, un double que representa la x a evaluar la derivada y un int que representa el grado de derivación. */
	public DerivacionNumerica(FuncionTabular datos,double x,int grado){
		this(datos,x,grado,4);
	}

	/** * Método Constructor que recibe una FuncionTabular la cual representa la función tabular, un double que representa la x a evaluar la derivada y dos int donde grado representa el grado de derivación y error representa el grado de error a utilizar. */
	public DerivacionNumerica(FuncionTabular datos,double x,int grado,int error){
		this.x = x;
		this.error = error;
		this.datos = datos;
		setPosicion();
		setH();
		iteraciones.append("Derivación Numérica\n");
		if(pos != 0)
			setMetodoFT(grado,pos-1);
		else
			setMetodoFT(grado,pos);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada y un int que representa el grado de derivación. */
	public DerivacionNumerica(String funcion,double x,int grado){
		this(funcion,x,grado,0.001,"central",4);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada, un int que representa el grado de derivación y un double que representa la separación entre cada punto a evaluar. */
	public DerivacionNumerica(String funcion,double x,int grado,double h){
		this(funcion,x,grado,h,"central",4);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada, un int que representa el grado de derivación y una String que representa el método de derivación a utilizar. */
	public DerivacionNumerica(String funcion,double x,int grado,String metodo){
		this(funcion,x,grado,0.001,metodo,4);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada y dos int donde grado representa el grado de derivación y error representa el grado de error a utilizar. */
	public DerivacionNumerica(String funcion,double x,int grado,int error){
		this(funcion,x,grado,0.001,"central",error);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada, un int donde grado representa el grado de derivación, un double que representa la separación entre cada punto a evaluar y un int que representa el grado de error a utilizar. */
	public DerivacionNumerica(String funcion,double x,int grado,double h,int error){
		this(funcion,x,grado,h,"central",error);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada, un int donde grado representa el grado de derivación, un double que representa la separación entre cada punto a evaluar y un int que representa el grado de error a utilizar. */
	public DerivacionNumerica(String funcion,double x,int grado,double h,String metodo){
		this(funcion,x,grado,h,metodo,4);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada, un int donde grado representa el grado de derivación, una String que representa el método de derivación a utilizar y un int que representa el grado de error a utilizar.*/
	public DerivacionNumerica(String funcion,double x,int grado,String metodo,int error){
		this(funcion,x,grado,0.001,metodo,error);
	}

	/** * Método Constructor que recibe un String el cual representa la función, un double que representa la x a evaluar la derivada, un int donde grado representa el grado de derivación, una String que representa el método de derivación a utilizar, un double que representa la separación entre cada punto a evaluar y un int que representa el grado de error a utilizar. */
	public DerivacionNumerica(String funcion,double x,int grado,double h,String metodo,int error){
		this.x = x;
		this.error = error;
		this.h = h;
		eval_funcion = new EvalFuction(funcion.trim());
		this.funcion = eval_funcion.getFuncion();
		iteraciones.append("Derivación Numérica\n");
		setMetodo(metodo,grado);
	}

	/** * Método setMetodo que recibe una String que representa el método a usar y un int que representa el grado de derivación, el método se encarga de escoger el método a realizar y el grado de derivación a seguir. */
	private void setMetodo(String metodo,int grado){
		if(metodo.equals("adelante")){
			iteraciones.append("Diferencia adelante\n");
			DerivatedAdelante(grado,x);
		}

		else if(metodo.equals("atras")){
			iteraciones.append("Diferencia atrás\n");
			DerivatedAtras(grado,x);
		}

		else{
			iteraciones.append("Diferencia central\n");
			DerivatedCentral(grado,x);
		}

		iteraciones.append("Resultado: "+fn);
	}

	/** * Método privado f que recibe un double x con el cual evalua la función y regresa el valor evaluado en dicha función. */
	private double f(double x){
		eval_funcion.setEvaluar(this.funcion,x);
		return eval_funcion.getResult();
	}

	/** * Método privado DerivatedCentral que es el encargado de realizar el método de diferencias finitas centrales, recibe un int que representa el grado de derivación y un double que representa la x a evaluar, regresa la derivada evaluada en dicha x enviada. */
	private double DerivatedCentral(int n,double x){

		if(n == 1){
			if(error == 2)
				fn = (f(x+h)-f(x-h))/(2*h);

			else if(error == 4)
				fn = (-f(x+2*h)+8*f(x+h)-8*f(x-h)+f(x-2*h))/(12*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedCentral(n-1,x+h)-DerivatedCentral(n-1,x-h))/(2*h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Método privado DerivatedAtras que es el encargado de realizar el método de diferencias finitas hacia atrás, recibe un int que representa el grado de derivación y un double que representa la x a evaluar, regresa la derivada evaluada en dicha x enviada. */
	private double DerivatedAtras(int n,double x){

		if(n == 1){
			if(error == 2)
				fn = (f(x)-f(x-h))/(h);

			else if(error == 4)
				fn = (3*f(x)-4*f(x-h)+f(x-2*h))/(2*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedAtras(n-1,x)-DerivatedAtras(n-1,x-h))/(h);
		else
			fn = 0.0;

		return fn;
	}

	/** * Método privado DerivatedAdelante que es el encargado de realizar el método de diferencias finitas hacia adelante, recibe un int que representa el grado de derivación y un double que representa la x a evaluar, regresa la derivada evaluada en dicha x enviada. */
	private double DerivatedAdelante(int n,double x){

		if(n == 1){
			if(error == 2)
				fn = (f(x+h)-f(x))/(h);

			else if(error == 4)
				fn = (-f(x+2*h)+4*f(x+h)-3*f(x))/(2*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedAdelante(n-1,x+h)-DerivatedAdelante(n-1,x))/(h);

		else
			fn = 0.0;

		iteraciones.append("Aprox: "+fn+"\n");
		
		return fn;
	}

	/** * Método privado setPosicion para asignar la posición de la x a derivar en la función tabular recibida, no regresa ningún valor. */
	private void setPosicion(){
		for(int i=0;i<datos.getSize();i++)
			if(x <= this.datos.getElement(i,0)){
				pos = i;
				break;
			}
	}

	/** * Método privado setH para calcular y asignar la separación entre cada punto de la función tabular recibida, no regresa ningún valor.*/
	private void setH(){
		double aux = 0.0;

		h = Math.ceil((datos.getElement(1,0)-datos.getElement(0,0))*1000000)/1000000;

		for(int i = 2;i < datos.getSize()-1;i++){

			aux = Math.ceil((datos.getElement(i+1,0) - datos.getElement(i,0))*1000000)/1000000;

			if((h-aux) > 0.00001){
				iteraciones.append("H no es constante entre los intervalos por lo que la derivada calculada esta mál.");
				break;
			}
		}
	}

	/** * Método privado setMetodoFT que recibe un int que representa el grado de derivación y un int que representa la posición de la x a derivar en función tabular, el método se encarga de escoger el método a realizar y el grado de derivación a seguir. */
	private void setMetodoFT(int grado,int pos){
		if(pos < ((datos.getSize())/2)){
			iteraciones.append("Diferencia adelante\n");
			DerivatedAdelanteFT(grado,pos);
		}

		else if(pos > ((datos.getSize())/2)){
			iteraciones.append("Diferencia atrás\n");
			DerivatedAtrasFT(grado,pos);
		}

		else{
			iteraciones.append("Diferencia central\n");
			DerivatedCentralFT(grado,pos);
		}
		
		iteraciones.append("Resultado: "+fn);
	}

	/** * Método privado ft que recibe un int el cual representa la posición de la x a usar y regresa el valor de dicha x.*/
	private double ft(int pos){
		return datos.getElement(pos,1);
	}

	/** * Método privado DerivatedCentral que es el encargado de realizar el método de diferencias finitas centrales, recibe dos int donde n representa el grado de derivación y pos representa la posición de la x a evaluar, regresa la derivada evaluada en dicha x.*/
	private double DerivatedCentralFT(int n,int pos){

		if(n == 1){
			if(error == 2)
				fn = (ft(pos+1)-ft(pos-1))/(2*h);

			else if(error == 4)
				fn = (-ft(pos+2)+8*ft(pos+1)-8*ft(pos-1)+ft(pos-2))/(12*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+" Posicion: "+pos+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedCentralFT(n-1,pos+1)-DerivatedCentralFT(n-1,pos-1))/(2*h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Método privado DerivatedCentral que es el encargado de realizar el método de diferencias finitas hacia atrás, recibe dos int donde n representa el grado de derivación y pos representa la posición de la x a evaluar, regresa la derivada evaluada en dicha x.*/
	private double DerivatedAtrasFT(int n,int pos){

		if(n == 1){

			if(error == 2)
				fn = (ft(pos)-ft(pos-1))/(h);

			else if(error == 4)
				fn = (3*ft(pos)-4*ft(pos-1)+ft(pos-2))/(2*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+" Posición: "+pos+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedAtrasFT(n-1,pos)-DerivatedAtrasFT(n-1,pos-1))/(h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Método privado DerivatedCentral que es el encargado de realizar el método de diferencias finitas hacia adelante, recibe dos int donde n representa el grado de derivación y pos representa la posición de la x a evaluar, regresa la derivada evaluada en dicha x.*/
	private double DerivatedAdelanteFT(int n,int pos){

		if(n == 1){
			if(error == 2)
				fn = (ft(pos+1)-ft(pos))/(h);

			else if(error == 4)
				fn = (-ft(pos+2)+4*ft(pos+1)-3*ft(pos))/(2*h);

			iteraciones.append("Aprox: "+fn+" Grado: "+n+" Posición: "+pos+"\n");
		}

		else 
			if(n > 1)
				fn = (DerivatedAdelanteFT(n-1,pos+1)-DerivatedAdelanteFT(n-1,pos))/(h);
			

		else
			fn = 0.0;
		
		return fn;
	}

	/** * Método publico getDerivada que es el encargado de regresar un double el cual representa la derivada evaluada en la x enviada. */
	public double getDerivada(){
		return this.fn;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método diferencias finitas empleado.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}
}
