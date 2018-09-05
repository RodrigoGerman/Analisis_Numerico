package modelo.theme4;

import modelo.FuncionTabular;
import modelo.EvalFuction;

/**
	* La clase IntegracionNumerica es una clase que se encarga de realizar el método numérico de Integración Numérica a partir de métodos como
	* Simpson y del trapecio métodos de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class IntegracionNumerica{
	/** * Variable privada de tipo EvalFuction que se encarga de guardar la función ingresada por el usuario.*/
	private EvalFuction eval_funcion;
	/** * Arreglo de String privado que se encarga de guardar la función ingresada por el usuario en su forma Posfija.*/
	private String[] funcion;
	/** * Variable privada de tipo FuncionTabular que se encarga de guardar la función tabular ingresada por el usuario.*/
	private FuncionTabular datos;
	/** * Variables privadas de tipo double donde y almacena el resultado de realizar la integral evaluada en los puntos recibidos, h almacena la separación entre cada punto a evaluar, a almacena el valor inferior y b el valor superior a integrar la función.*/
	private double y = 0.0,h = 0.0,a,b;
	/** * Variables privadas de tipo int donde a_pos se encarga de guardar la posición del valor inferior a evaluar la integral de la función tabular, b_pos e encarga de guardar la posición del valor superior a evaluar la integral de la función tabular y n almacena la separación entre los valores a evaluar la integración de la función. */	
	private int a_pos = 0,b_pos = 0,n = 0;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Integración Numérica. */
	private StringBuffer iteraciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public IntegracionNumerica(){}

	/** * Método Constructor que recibe una FuncionTabular la cual representa la función tabular y dos double que representan el intervalo de integración a evaluar.*/
	public IntegracionNumerica(FuncionTabular datos,double a,double b){
		this(datos,a,b,"general");
	}
	/** * Método Constructor que recibe una FuncionTabular la cual representa la función tabular, dos double que representan el intervalo de integración a evaluar y un String que representa el método de integración numérica a usar.*/
	public IntegracionNumerica(FuncionTabular datos,double a,double b,String metodo){
		this.datos = datos;
		setH();
		this.a_pos = setPosicion(a);
		this.b_pos = setPosicion(b);
		this.n = (this.b_pos-this.a_pos);
		setMetodoft(n,metodo);
	}

	/** * Método Constructor que recibe un String el cual representa la función, dos double que representan el intervalo de integración a evaluar.*/
	public IntegracionNumerica(String datos,double a,double b){
		this(datos,a,b,"general",1000000);
	}

	/** * Método Constructor que recibe un String el cual representa la función, dos double que representan el intervalo de integración a evaluar y un int que representa el numero de divisiones a usar para integrar.*/
	public IntegracionNumerica(String datos,double a,double b,int n){
		this(datos,a,b,"general",n);
	}

	/** * Método Constructor que recibe un String el cual representa la función, dos double que representan el intervalo de integración a evaluar y un String que representa el método de integración numérica a usar.*/
	public IntegracionNumerica(String datos,double a,double b,String metodo){
		this(datos,a,b,metodo,1000000);
	}

	/** * Método Constructor que recibe un String el cual representa la función, dos double que representan el intervalo de integración a evaluar, un String que representa el método de integración numérica a usar y un int que representa el numero de divisiones a usar para integrar.*/
	public IntegracionNumerica(String funcion,double a,double b,String metodo,int n){
		this.a = a;
		this.b = b;
		this.n = n;
		this.h = (b-a)/(double)n;
		eval_funcion = new EvalFuction(funcion.trim());
		this.funcion = eval_funcion.getFuncion();
		iteraciones.append("Integración Numérica\n");
		setMetodo(n,metodo);
	}

	/** * Método privado setH para calcular y asignar la separación entre cada punto de la función tabular recibida, no regresa ningún valor.*/
	private void setH(){
		double aux = 0.0;

		h = Math.ceil((datos.getElement(1,0)-datos.getElement(0,0))*1000000)/1000000;

		for(int i = 2;i < datos.getSize()-1;i++){

			aux = Math.ceil((datos.getElement(i+1,0) - datos.getElement(i,0))*1000000)/1000000;

			if((h-aux) > 0.00001){
				iteraciones.append("H no es constante entre los intervalos por lo que la integral calculada esta mál.");
				break;
			}
		}
	}

	/** * Método privado setPosicion para asignar la posición de la x a evaluar la integral de la función tabular recibida y regresa dicha posición.*/
	private int setPosicion(double a){
		int pos = 0;
		for(int i=0;i<datos.getSize();i++)
			if(a <= this.datos.getElement(i,0)){
				pos = i;
				break;
			}
		return pos;
	}

	/** * Método privado setMetodoFT que recibe un int que representa el numero de divisiones a usar y una String que representa el método a usar para integrar la función tabular, el método se encargar de escoger el método a realizar y realiza la integral, no regresa ningún valor.*/
	private void setMetodoft(int n,String tipo){
		int aux = n % 3;

		if(tipo.equals("general")){
			if(aux == 0 || aux == 1)
				TercerOrdenFt();
			else 
				SegundoOrdenFt();
		}

		if(tipo.equals("1/2"))
			PrimerOrdenFt();
		
		if(tipo.equals("1/3"))
			SegundoOrdenFt();
		
		if(tipo.equals("3/8"))
			TercerOrdenFt();

		iteraciones.append("Resutado: "+y);
	}

	/** * Método privado setMetodo que recibe un int que representa el numero de divisiones a usar y una String que representa el método a usar para integrar la función, el método se encargar de escoger el método a realizar y realiza la integral, no regresa ningún valor.*/
	private void setMetodo(int n,String tipo){
		int aux = n % 3;
		if(tipo.equals("general")){
			if(aux == 0 || aux == 1)
				TercerOrden();
			else 
				SegundoOrden();
		}

		if(tipo.equals("1/2"))
			PrimerOrden();
		
		if(tipo.equals("1/3"))
			SegundoOrden();
		
		if(tipo.equals("3/8"))
			TercerOrden();

		iteraciones.append("Resutado: "+y);
	}

	/** * Método privado f que recibe un double x con el cual evalúa la función y regresa el valor evaluado en dicha función.*/
	private double f(double x){
		eval_funcion.setEvaluar(this.funcion,x);
		return eval_funcion.getResult();
	}
	/** * Método privado ft que recibe un int el cual representa la posición de la x a usar y regresa el valor de dicha x.*/
	private double ft(int pos){
		return datos.getElement(pos,1);
	}

	/** * Método privado TercerOrden que se encarga de realizar el método de Simpson 3/8 para resolver la integral de la función.*/
	private void TercerOrden(){

		double suma= 0.0;

		for (int i = 1; i <= n-1;i++){
			if(i % 3 == 0)
				suma += 2*f(a+h*i); 
			else
				suma += 3*f(a+h*i);

			iteraciones.append(i+": "+suma+"\n");
		}

		y = ((3*h)/8)*(f(a) + f(b) + suma);
	}

	/** * Método privado SegundoOrden que se encarga de realizar el método de Simpson 1/3 para resolver la integral de la función.*/
	private void SegundoOrden(){

		double suma= 0.0;

		for (int i = 1; i <= n-1;i++){
			if(i % 2 == 0)
				suma += 2*f(a+h*i); 
			else
				suma += 4*f(a+h*i);

			iteraciones.append(i+": "+suma+"\n");
		}

		y = (h/3)*(f(a) + f(b) + suma);
	}

	/** * Método privado PrimerOrden que se encarga de realizar el método de Simpson 1/2 para resolver la integral de la función.*/
	private void PrimerOrden(){

		double suma= 0.0;

		for (int i = 1; i <= n-1;i++){
			suma += 2*f(a+h*i);
			iteraciones.append(i+": "+suma+"\n");
		}

		y = (h/2)*(f(a) + f(b) + suma);
	}

	/** * Método privado PrimerOrden que se encarga de realizar el método de Simpson 1/2 para resolver la integral de la función tabular.*/
	private void PrimerOrdenFt(){
		double suma= 0.0;

		for (int i = 1; i <= n-1;i++){
			suma += 2*ft(a_pos+i);
			iteraciones.append(i+": "+suma+"\n");
		}

		y = (h/2)*(ft(a_pos) + ft(b_pos) + suma);
	}

	/** * Método privado SegundoOrden que se encarga de realizar el método de Simpson 1/3 para resolver la integral de la función tabular.*/
	private void SegundoOrdenFt(){

		double suma= 0.0;

		for (int i = 1; i <= n-1;i++){
			if(i % 2 == 0)
				suma += 2*ft(a_pos+i); 
			else
				suma += 4*ft(a_pos+i);
			iteraciones.append(i+": "+suma+"\n");
		}

		y = (h/3)*(ft(a_pos) + ft(b_pos) + suma);
	}

	/** * Método privado TercerOrden que se encarga de realizar el método de Simpson 3/8 para resolver la integral de la función tabular.*/
	private void TercerOrdenFt(){

		double suma= 0.0;

		for (int i = 1; i <= n-1;i++){
			if(i % 3 == 0)
				suma += 2*ft(a_pos+i); 
			else
				suma += 3*ft(a_pos+i);
			iteraciones.append(i+": "+suma+"\n");
		}

		y = ((3*h)/8)*(ft(a_pos) + ft(b_pos) + suma);
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método de integración empleado.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getDerivada que es el encargado de regresar un double el cual representa el valor de la integración evaluada en los intervalos enviados.*/
	public double getIntegral(){
		return this.y;
	}
}