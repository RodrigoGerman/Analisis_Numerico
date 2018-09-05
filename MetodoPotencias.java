package modelo.theme3;
import modelo.Matriz;
import java.util.ArrayList;

/**
	* La clase MetodoPotencias es una clase que se encarga de realizar el método numérico de Potencias
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class MetodoPotencias{
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de Potencias.*/
	private Matriz matriz = new Matriz();
	/** * Variable privada tipo Matriz que almacena la matriz inversa que se utiliza en el método de Potencias.*/
	private Matriz matriz_inversa= new Matriz();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar el vector mínimo del Método de Potencias.*/
	private ArrayList<Double> vector_minimo = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar el vector maximo del Método de Potencias.*/
	private ArrayList<Double> vector_maximo = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar la soluciones del método de Potencias.*/
	private ArrayList<Double> resultados = new ArrayList<Double>();
	/** * Variables privadas de tipo double que sirve como auxiliares para ir almacenando y modificando los valores máximos y mínimos respectivamente del método de Potencias*/
	private double maximo,minimo;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Potencias.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de Potencias.*/
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public MetodoPotencias(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario.*/
	public MetodoPotencias(Matriz matriz){
		this.matriz = matriz;

		for(int i =0;i<matriz.getColumns();i++){
			resultados.add(0.0);
		}

		LU m = new LU(matriz);
		matriz_inversa = m.getInversa();
		resultados.set(0,1.0);

		setSolucion("Maximo");
		iteraciones.append("\n\nVector Maximo Final:\n"+vector_maximo);
		iteraciones.append("\n\nValor Maximo Final:\n"+maximo);

		for(int i =0;i<matriz.getColumns();i++)
			resultados.set(i,0.0);
		resultados.set(0,1.0);

		setSolucion("Minimo");
		iteraciones.append("\n\nVector Minimo Final:\n"+vector_minimo);
		iteraciones.append("\n\nValor Minimo Final:\n"+minimo);

		soluciones.append("\n\nVector Maximo Final:\n"+vector_maximo);
		soluciones.append("\n\nValor Maximo Final:\n"+maximo);
		soluciones.append("\n\nVector Minimo Final:\n"+vector_minimo);
		soluciones.append("\n\nValor Minimo Final:\n"+minimo);
	}

	/** * Método publico que se encarga de resolver el método numérico de Potencias, recibe una string que representa la estrategia a utilizar para resolver el método, no regresa ningún valor.*/
	public void setSolucion(String valor){
		double aux = 0.0;
		double max = 0.0;

		for(int l = 0;l<resultados.size();l++){
			aux = resultados.get(l);
			if(aux > max)
				max = aux;
		}

		for(int k = 0;k<resultados.size();k++)
			resultados.set(k,resultados.get(k)/max);
			
		if(valor.equals("Minimo")){
			vector_minimo = (ArrayList<Double>)resultados.clone();
			resultados = matriz_inversa.getMultiplicacionVector(resultados);
		}
		else if(valor.equals("Maximo")){
			vector_maximo = (ArrayList<Double>)resultados.clone();
			resultados = matriz.getMultiplicacionVector(resultados);
		}

		if(valor.equals("Maximo")){
			if(Math.abs(max - maximo) > 0.0000001){
				iteraciones.append("\n\nVector Maximo:\n"+vector_maximo);
				iteraciones.append("\n\nMaximo:\n"+max);
				maximo = max;
				setSolucion(valor);
			}
		}
		else if(valor.equals("Minimo")){
			if(Math.abs(max - minimo) > 0.0000001){
				iteraciones.append("\n\nVector Minimo:\n"+vector_maximo);
				iteraciones.append("\n\nMinimo:\n"+minimo);
				minimo = max;
				setSolucion(valor);
			}
			minimo = 1/minimo;
		}
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método de Potencias.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getSoluciones que se encarga de regresar un String con todas las soluciones del método de Potencias.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}