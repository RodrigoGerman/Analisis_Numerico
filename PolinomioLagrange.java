package modelo.theme4;

import modelo.FuncionTabular;
import java.util.ArrayList;

/**
	* La clase PolinomioLagrange es una clase que se encarga de realizar el metodo numerico de Polinomio Lagrange
	* de la materia de analisis numerico.
	* @author German Lopez Rodrigo
	* @version 13/05/2017/Final 
*/
public class PolinomioLagrange{
	/** * Variable privada de tipo FuncionTabular que se encarga de guardar la funcion tabular ingresada por el usuario.*/
	private FuncionTabular datos;
	/** * Variables privadas de tipo double donde y almacena el resultado de realizar el metodo de Polinomio Lagrange y la x almacena el valor a evaluar.*/
	private double x = 0.0,y = 0.0;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el metodo de Polinomio Lagrange.*/
	private StringBuffer iteraciones = new StringBuffer();

	/** * Metodo Constructor sin argumentos.*/
	public PolinomioLagrange(){}

	/** * Metodo Constructor que recibe una FuncionTabular la cual representa la funcion tabular Y  un double que representa la x a evaluar.*/
	public PolinomioLagrange(FuncionTabular datos,double x){
		this.datos = datos;
		this.x = x;
		iteraciones.append("Función Tabular:\n"+datos+"\n");
		setSolucion();
	}

	/** * Metodo privado setSolucion que es el encargado de realizar el metodo de Polinomio Lagrange, no regresa ningun valor.*/
	private void setSolucion(){
		double aux = 1.0;
		for(int i =0;i < datos.getSize();i++){
			aux = 1.0;
			for(int j =0;j < datos.getSize();j++)
				if(i != j)
					aux *= (this.x-this.datos.getElement(j,0))/(this.datos.getElement(i,0)-this.datos.getElement(j,0));
			this.y += aux*this.datos.getElement(i,1);
			iteraciones.append(i+" : "+y+"\n");
		}

		iteraciones.append("Y : "+y+"\n");
	}

	/** * Metodo publico getIteraciones que se encarga de regresar un String con todas las iteraciones del metodo Polinomio Lagrange.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Metodo publico getSolucion que se encarga de regresar un double con la solucion del metodo de Polinomio Lagrange.*/
	public double getSolucion(){
		return this.y;
	}
}