package modelo.theme5;

import modelo.EvalFuction;
import java.util.ArrayList;

/**
	* La clase MetodoRungeKutta es una clase que se encarga de realizar el metodo numerico de Solucion de Sistema de Ecuaciones diferenciales
	* de la materia de analisis numerico.
	* @author German Lopez Rodrigo
	* @version 13/05/2017/Final 
*/
public class SolucionSistemaEDN{
	/** * ArrayList<EvalFuction> de EvalFuction privada que se encarga de guardar los objetos funcion del sistema de ecuaciones diferenciales.*/
	private ArrayList<EvalFuction> funciones = new ArrayList<EvalFuction>();
	/** * ArrayList<String []> de Arreglos de String privada que se encarga de guardar los arreglos las funciones ingresadas por el usuario en su forma Posfija.*/
	private ArrayList<String []> funciones_str = new ArrayList<String []>();
	/** * ArrayList<Double> de double privada que se encarga de guardar los valores evaluados de las funciones ingresadas por el usuario.*/
	private ArrayList<Double> valores_evaluados = new ArrayList<Double>();
	/** * ArrayList<Double> de double privada que se encarga de guardar los valores solucion de las funciones ingresadas por el usuario.*/
	private ArrayList<Double> soluciones = new ArrayList<Double>();
	/** * ArrayList<Double> de double privada que se encarga de guardar los valores iniciales de las funciones ingresadas por el usuario.*/
	private ArrayList<Double> valores_iniciales = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el metodo de Solucion de Sistema de ecuaciones.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el metodo de Solucion de Sistema de ecuaciones.*/
	private StringBuffer solucion = new StringBuffer();
	/** * Variable privada de tipo double que se encarga de almacenar la separacion entre los puntos evaluados en el sistema de ecuaciones diferenciales.*/
	private double h;
	/** * Variables privadas de tipo int donde n representa el numero de diviciones a usar, pos indica la posicion de la variable a utilizar y tam es el tamaño del sistema de ecuaciones.*/
	private int n,pos,tam;
	/** * Variable privada de tipo String que se encarga de almacenar el metodo a utilizar para resolver las ecuaciones diferenciales.*/
	private String metodo;

	/** * Metodo Constructor sin argumentos.*/
	public SolucionSistemaEDN(){}

	/** * Metodo Constructor que recibe una ArrayList de String que representan los ecuaciones diferenciales que componen al sistema, dos ArrayList de doubles que representa los valores evaluados correspondientes
	a cada ecuacion diferencial y sus valores iniciales de dichas ecuaciones diferenciales, un int que representa el numero de diviciones, un double que representa la separacion entre cada punto evaluado de la solucion
	del sistema de ecuaciones, un double que representa el valor a evaluar la solucion del sistema de ecuaciones y una String que represneta el metodo a utilizar para resolver las ecuaciones diferenciales.*/
	public SolucionSistemaEDN(ArrayList<String> funciones,ArrayList<Double> valores_evaluados,ArrayList<Double> valores_iniciales,int n,double h,double x,String metodo){
		
		this.valores_evaluados = valores_evaluados;
		this.valores_iniciales = valores_iniciales;
		this.n = n;
		this.h = h;
		this.tam = funciones.size();
		this.metodo = metodo;

		iteraciones.append("funciones: "+funciones+"\n");
		iteraciones.append("Valores evaluados: "+valores_evaluados+"\n");
		iteraciones.append("Valores iniciales: "+valores_iniciales+"\n");

		for(int i = 0;i < funciones.size();i++){
			this.funciones.add(new EvalFuction(funciones.get(i)));
			funciones_str.add((this.funciones.get(i)).getFuncion());
		}

		setSolucion(x,metodo);
	}

	/** * Metodo privado getSolucion que se encarga de calcular la solucion del Sistema de ecuaciones, recibe un double que la x a evaluar en la solucion del sistema y una string que representa el metodo a utilizar para resolver las ecuaciones diferenciales.*/
	private void setSolucion(double x,String metodo){

		if(metodo.equals("euler"))
			for(int i = 0;i < tam;i++)
				soluciones.add(fk1(x,i));

		if(metodo.equals("eulergauss"))
			for(int i = 0;i < tam;i++)
				soluciones.add(fk(x,i));

		if(metodo.equals("taylor"))
			for(int i = 0;i < tam;i++)
				soluciones.add(fyT(x,i));

		if(metodo.equals("rungekutta"))
			for(int i = 0;i < tam;i++)
				soluciones.add(fyRk(x,i));

		for(int j = 0;j < soluciones.size();j++){
			iteraciones.append("Solucion: "+soluciones.get(j)+"\n");
			solucion.append(j+": "+soluciones.get(j)+"\n");
		}
	}

	/** * Metodo privado f el cual recibe un arreglo de doubles el cual representa los valores a evaluar una ecuacion diferencial, un int que representa que ecuacion diferencial se va a ocupar y regresa el valor evaluado en dicha ecuacion diferencial.*/
	private double f(double[] num,int pos){
		EvalFuction eval_funcion = funciones.get(pos);
		eval_funcion.setEvaluar(funciones_str.get(pos),num);
		return eval_funcion.getResult();
	}

	/** * Metodo publico fk1 que es el encargado de realizar el metodo de Euler, recibe un double que representa el valor a evaluar y un int que representa la posicion de la ecuacion diferencial a usar, regresa un double que representa el valor evaluado en la solucion de la ecuacion diferencial seleccionada.*/
	public double fk1(double x,int pos){
		double[] aux4 = new double[tam];
		double aprox = 0.0,aux=0.0;

		if(x == valores_evaluados.get(pos) || x-valores_evaluados.get(pos) < 0.001)
			aprox = valores_iniciales.get(pos);

		else{
			aux = (Math.ceil((x-h)*100000)/100000);

			for(int i = 0;i< tam;i++)
				aux4[i] = fk1(aux,i);

			aprox = fk1(aux,pos) + h*(f(aux4,pos));

			iteraciones.append("Aprox: "+aprox+"\n");
		}

		return aprox;
	}

	/** * Metodo publico fk1 que es el encargado de realizar el metodo de Euler-Gauss, recibe un double que representa el valor a evaluar y un int que representa la posicion de la ecuacion diferencial a usar, regresa un double que representa el valor evaluado en la solucion de la ecuacion diferencial seleccionada.*/
	public double fk(double x,int pos){
		double aprox = 0.0,aux=0.0;
		double[] num = new double[tam];
		double[] num2 = new double[tam];

		if(x == valores_evaluados.get(pos) || x-valores_evaluados.get(pos) < 0.001)
			aprox = valores_iniciales.get(pos);

		else{

			aux = (Math.ceil((x-h)*100000)/100000);

			for(int i = 0;i < tam;i++)
				num[i] = fk(aux,i);
			
			for(int i = 0;i < tam;i++)
				num2[i] = fk1(x,i);
			

			aprox = fk(aux,pos) + (h/2)*(f(num,pos)+f(num2,pos));

			iteraciones.append("Aprox: "+aprox+"\n");
		}

		return aprox;
	}

	/** * Metodo privado dfk que recibe un arreglo de doubles el cual representa los valores a evaluar la derivada de ecuacion diferencial, dos int donde n representa el grado de derivada y pos representa la posicion de la ecuacion diferencial a ocupar, regresa el valor evaluado de la derivada de la ecuacion diferencial.*/
	private double dfk(int n,double[] num,int pos){
		double[] aux1 = new double[tam];
		double[] aux2 = new double[tam];
		double[] aux3 = new double[tam];
		double[] aux4 = new double[tam];
		double fn = 0.0;

		for(int i = 0;i < num.length;i++){
			aux1[i] = num[i]+2*h;
			aux2[i] = num[i]+h;
			aux3[i] = num[i]-h;
			aux4[i] = num[i]-2*h;
		}

		if(n == 1)
			fn = (-f(aux1,pos)+8*f(aux2,pos)-8*f(aux3,pos)+f(aux4,pos))/(12*h);

		else 
			if(n > 1)
				fn = (dfk(n-1,aux2,pos)-dfk(n-1,aux3,pos))/(2*h);

		else
			fn = 0.0;

		return fn;
	}

	/** * Metodo privado getFactorial que es el encargado de calcular y regresar el factorial del numero double que recibe.*/
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

	/** * Metodo publico fyT que es el encargado de realizar el metodo de Taylor, recibe un double que representa el valor a evaluar y un int que representa la posicion de la ecuacion diferencial a usar, regresa un double que representa el valor evaluado en la solucion de la ecuacion diferencial seleccionada.*/
	public double fyT(double x,int pos){

		double aprox = 0.0,aux=0.0;
		double[] num = new double[tam];

		if(x == valores_evaluados.get(pos) || x-valores_evaluados.get(pos) < 0.001)
			aprox = valores_iniciales.get(pos);

		else{

			aux = (Math.ceil((x-h)*100000)/100000);

			for(int i = 0;i < tam;i++)
				num[i] = fk(aux,i);


			aprox = fk(aux,pos) + h*(f(num,pos));

			for(int i = 2;i <= n;i++)
				aprox += (Math.pow(h,i)/getFactorial(i+1))*(dfk(i-1,num,pos));

			iteraciones.append("Aprox: "+aprox+"\n");
		}

		return aprox;
	}

	/** * Metodo publico fyT que es el encargado de realizar el metodo de Rungue Kutta, recibe un double que representa el valor a evaluar y un int que representa la posicion de la ecuacion diferencial a usar, regresa un double que representa el valor evaluado en la solucion de la ecuacion diferencial seleccionada.*/
	public double fyRk(double x,int pos){
		double[] num = new double[tam];
		double[] num2 = new double[tam];
		double[] k1 = new double[tam];
		double[] k2 = new double[tam];
		double[] k3 = new double[tam];
		double[] k4 = new double[tam];

		double aprox = 0.0,aux=0.0;

		if(x == valores_evaluados.get(pos) || x-valores_evaluados.get(pos) < 0.001)
			aprox = valores_iniciales.get(pos);

		else{

			aux = (Math.ceil((x-h)*100000)/100000);

			for(int i = 0;i < tam;i++)
				num[i] = fyRk(aux,i);

			for(int i = 0;i < tam;i++)
				k1[i] = h*f(num,i);

			iteraciones.append("k1: "+k1[pos]+"\n");
				
			for(int i = 0;i < tam;i++)
				num2[i] = num[i] + (k1[i]/2);

			for(int i = 0;i < tam;i++)
				k2[i] = h*f(num2,i);

			iteraciones.append("k2: "+k2[pos]+"\n");

			for(int i = 0;i < tam;i++)
				num2[i] = num[i] + (k2[i]/2);
			

			for(int i = 0;i < tam;i++)
				k3[i] = h*f(num2,i);

			iteraciones.append("k2: "+k3[pos]+"\n");

			for(int i = 0;i < tam;i++)
				num2[i] = num[i] + k3[i];
			

			for(int i = 0;i< tam;i++)
				k4[i] = h*f(num2,i);

			iteraciones.append("k4: "+k4[pos]+"\n");

			aprox = num[pos] + (k1[pos]+2*k2[pos]+2*k3[pos]+k4[pos])/6.0;

			iteraciones.append("Aprox: "+aprox+"\n");
		}


		return aprox;
	}

	/** * Metodo publico getSolucion que se encarga de regresar un double con la solucion del metodo del sistema de ecuaciones.*/
	public String getSolucion(){
		return solucion.toString();
	}

	/** * Metodo publico getIteraciones que se encarga de regresar un String con todas las iteraciones del metodo que se haya ocupado para solucionar el sistema.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}
}