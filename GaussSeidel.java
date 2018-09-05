
package modelo.theme3;

import modelo.Matriz;
import java.util.ArrayList;

/**
	* La clase GaussSeidel es una clase que se encarga de realizar el método numérico de GaussSeidel
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class GaussSeidel{
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de GaussSeidel.*/
	private Matriz matriz = new Matriz();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores del vector recibido.*/
	private ArrayList<Double> vector = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar las soluciones del método de GaussSeidel.*/
	private ArrayList<Double> resultados = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar las soluciones anteriores del método de GaussSeidel.*/
	private ArrayList<Double> resultados1 = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de GaussSeidel.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de GaussSeidel.*/
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public GaussSeidel(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario y una ArrayList<Double> que representa el vector resultante. */
	public GaussSeidel(Matriz matriz,ArrayList<Double> vector){
		this.matriz = matriz;
		this.vector = vector;

		for(int i =0;i<matriz.getColumns();i++)
			resultados.add(0.0);

		setSolucion();

		for(int k = 0;k<resultados.size();k++){
			soluciones.append("\nX"+(k+1)+": "+resultados.get(k)+"\n");
		}
	}

	/** * Método publico que se encarga de resolver el método numérico GaussSeidel, no regresa ningún valor.*/
	public void setSolucion(){
		double aux = 0.0;
		for(int i =0;i<vector.size();i++){
			aux = 0.0;
			for(int k =0;k<matriz.getRenglons();k++)
				if(k != i)
					aux += matriz.getElement(i,k)*resultados.get(k);
			resultados.set(i,(vector.get(i)-aux)/matriz.getElement(i,i));
			if(i+2 == vector.size())
				resultados1 = (ArrayList<Double>)resultados.clone();
		}
		if(getNorma() > 0.00025){
			iteraciones.append("\n\nVector:\n"+resultados);
			setSolucion();
		}
	}

	/** * Método publico que regresa un double que representa la norma del método numérico GaussSeidel, el método se encarga de calcular la norma y regresarla. */
	public double getNorma(){
		double aux=0.0;
		for(int x = 0;x<vector.size();x++)
			aux+= Math.pow(resultados.get(x)-resultados1.get(x),2);
		
		return Math.sqrt(aux);
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método GaussSeidel.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las soluciones del método GaussSeidel.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}
