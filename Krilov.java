

package modelo.theme3;

import modelo.Matriz;
import modelo.theme2.openmethods.FactoresCuadraticos;
import java.util.ArrayList;

/**
	* La clase Krilov es una clase que se encarga de realizar el método numérico de Krilov
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class Krilov{
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de Krilov.*/
	private Matriz matriz = new Matriz();
	/** * Variable privada tipo Matriz que almacena la matriz elvada y para ser utilizada en el método de Krilov.*/
	private Matriz matriz_elevada = new Matriz();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar la soluciones del método de Krilov.*/
	private ArrayList<Double> resultados = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar la soluciones de utilizar el método Krilov.*/
	private ArrayList<Double> resultados1 = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Krilov.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de Krilov.*/
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public Krilov(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario.*/
	public Krilov(Matriz matriz){
		this.matriz = matriz;

		for(int i =0;i<matriz.getColumns();i++){
			resultados.add(0.0);
		}

		matriz_elevada = new Matriz(matriz.getRenglons(),matriz.getColumns()+1);

		setSolucion();
	}

	/** * Método publico que se encarga de resolver el método numérico Krilov, no regresa ningún valor.*/
	public void setSolucion(){
		double aux = 0.0;
		resultados.set(0,1.0);
		for(int k = 0;k<matriz.getColumns();k++){
			for(int l = 0;l<resultados.size();l++)
				matriz_elevada.setElement(l,matriz.getColumns()-(k+1),resultados.get(l));
			resultados = matriz.getMultiplicacionVector(resultados);
		}

		for(int l = 0;l<resultados.size();l++)
			matriz_elevada.setElement(l,matriz.getColumns(),resultados.get(l)*-1);
		
		iteraciones.append("\n\nMatriz:\n"+matriz_elevada);

		GaussJordan result = new GaussJordan(matriz_elevada,"Parcial");
		result.eliminacionGaussJordan();
		resultados1 = result.getResult();

		String ecuacion_caracterictica = "x^"+Integer.toString(matriz.getRenglons());
		int cont=0;
		aux =0.0;
		for(int k = matriz.getRenglons()-1;k>=0;k--){
			aux = resultados1.get(cont);

			if(aux > 0)
				ecuacion_caracterictica += "+";

			if(aux != 0.0 && k > 1)
				ecuacion_caracterictica += Double.toString(resultados1.get(cont))+"x^"+Integer.toString(k);
			if(k == 1)
				ecuacion_caracterictica += Double.toString(resultados1.get(cont))+"x";
			if(k==0)
				ecuacion_caracterictica += Double.toString(resultados1.get(cont));
			cont++;
		}

		ecuacion_caracterictica += "=0";

		iteraciones.append("\n\n"+ecuacion_caracterictica);

		soluciones.append(ecuacion_caracterictica);

		FactoresCuadraticos raices = new FactoresCuadraticos(ecuacion_caracterictica,0.00000001);

		soluciones.append(raices.getRaices());
		
		for(int l = 0;l < resultados1.size();l++)
			soluciones.append("\n\nx"+l+1+":"+resultados1.get(l));
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Krilov.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getSoluciones que se encarga de regresar un String con todas las soluciones del método Krilov.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}
