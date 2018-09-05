package modelo.theme3;

import modelo.Matriz;
import java.util.ArrayList;

/**
	* La clase Jacobi es una clase que se encarga de realizar el método numérico de Jacobi
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class Jacobi{
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de Jacobi.*/
	private Matriz matriz = new Matriz();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores del vector recibido.*/
	private ArrayList<Double> vector = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores modificados del vector recibido.*/
	private ArrayList<Double> vector1 = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar la soluciones del método de Jacobi.*/
	private ArrayList<Double> resultados = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar la soluciones anteriores  del método de Jacobi.*/
	private ArrayList<Double> resultados1 = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Jacobi.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de Jacobi.*/
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public Jacobi(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario y una ArrayList<Double> que representa el vector resultantes.*/
	public Jacobi(Matriz matriz,ArrayList<Double> vector){
		this.matriz = matriz;
		this.vector = vector;

		for(int i =0;i<matriz.getColumns();i++){
			vector1.add(0.0);
			resultados.add(0.0);
		}

		setSolucion();

		for(int k = 0;k<resultados.size();k++){
			soluciones.append("\nX"+(k+1)+": "+resultados.get(k)+"\n");
		}
	}

	/** * Método publico que se encarga de resolver el método numérico Jacobi, no regresa ningún valor.*/
	public void setSolucion(){
		double aux = 0.0;
		for(int i =0;i<vector.size();i++){
			aux = 0.0;
			for(int k =0;k<matriz.getRenglons();k++)
				if(k != i)
					aux += matriz.getElement(i,k)*resultados.get(k);
			vector1.set(i,(vector.get(i)-aux)/matriz.getElement(i,i));
			if(i+2 == vector.size())
				resultados1 = (ArrayList<Double>)vector1.clone();
		}
		resultados = (ArrayList<Double>)vector1.clone();

		if(getNorma() > 0.000000000000001){
			iteraciones.append("\n\nVector:\n"+resultados);
			setSolucion();
		}
	}

	/** * Método publico que regresa un double que representa la norma del método numérico Jacobi, el método se encarga de calcular la norma y regresarla.*/
	public double getNorma(){
		double aux=0.0;
		for(int x = 0;x<vector.size();x++){
			aux+= Math.pow(resultados.get(x)-resultados1.get(x),2);
		}
		return Math.sqrt(aux);
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Jacobi.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getSoluciones que se encarga de regresar un String con todas las soluciones del método Jacobi.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}