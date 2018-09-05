package modelo.theme3;

import modelo.Matriz;
import java.util.ArrayList;

/**
	* La clase Gauss es una clase que se encarga de realizar el método numérico de Gauss
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class Gauss{
	/** * Variable privada tipo String que almacena el tipo de estrategia de pivoteo a utilizar en el método de Gauss.*/
	private String estrategia = "";
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de Gauss.*/
	private Matriz matriz = new Matriz();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores del vector recibido.*/
	private ArrayList<Double> vector = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar la soluciones del método de Gauss.*/
	private ArrayList<Double> resultados = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Gauss.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de Gauss.*/	
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public Gauss(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario, una ArrayList<Double> que representa el vector resultantes y una String que representa la estrategia de pivoteo.*/
	public Gauss(Matriz matriz,ArrayList<Double> vector,String pivote){
		this.matriz = matriz;
		this.vector = vector;
		this.estrategia = pivote;
		iteraciones.append("\nEstrategia Pivoteo "+pivote);
		for(int i =0;i<matriz.getColumns();i++)
			resultados.add(0.0);
	}

	/** * Método privado strategiesPivotal que recibe un int que representa el renglón y una String que representa la estrategia de pivoteo, se encarga de realizar la estrategia enviada, no regresa ningún valor. */
	private void strategiesPivotal(int renglon,String estrategia){
		if(estrategia.equals("Parcial"))
			pivoteParcial(renglon);
		else if(estrategia.equals("Parcial Escalado"))
			pivoteParcialEscalado(renglon);
		else if(!estrategia.equals("Parcial Diagonal"))
			iteraciones.append("Estrategia invalida");
	}

	/** * Método privado que recibe un int que representa el renglón, se encarga de realizar el método de gauss atreves de la estrategia pivoteo parcial, no regresa ningún valor.*/
	private void pivoteParcial(int renglon){
		double max =0.0;
		double aux = 0.0;
		int pos = renglon;
		ArrayList<Double> fila;
		for(int i = renglon+1;i < matriz.getRenglons();i++){
			aux = Math.abs(matriz.getElement(i,renglon));
			if(aux > max){
				max = aux;
				pos = i;
			}
		}

		if(pos != renglon){
			fila = (ArrayList<Double>)this.matriz.getRenglon(renglon);
			this.matriz.setRenglon(renglon,(ArrayList<Double>)this.matriz.getRenglon(pos));
			this.matriz.setRenglon(pos,fila);
			aux = vector.get(renglon);
			vector.set(renglon,vector.get(pos));
			vector.set(pos,aux);
		}
	}

	/** * Método privado que recibe un int que representa el renglón, se encarga de realizar el método de gauss atreves de la estrategia pivoteo parcial escalado, no regresa ningún valor.*/
	private void pivoteParcialEscalado(int renglon){
		double max =0.0;
		double aux = 0.0;
		int pos = renglon;
		double[] maxs = new double[matriz.getRenglons()];
		int cont = 0; 
		ArrayList<Double> fila;
		for(int i = renglon;i < matriz.getRenglons();i++){
			for(int j= renglon ;j < matriz.getColumns();j++){
				aux = Math.abs(matriz.getElement(i,j));
				if(aux > max)
					max = aux;
			}
			maxs[cont] = aux;
			cont++;
		}

		cont = 0;
		max = 0.0;
		for(int k = renglon;k < matriz.getRenglons();k++){
			aux = Math.abs(matriz.getElement(k,renglon));
			aux = aux / maxs[cont];
			if(aux > max){
				max = aux;
				pos = k;
			}
			cont++;
		}

		if(pos != renglon){
			fila = (ArrayList<Double>)this.matriz.getRenglon(renglon);
			this.matriz.setRenglon(renglon,(ArrayList<Double>)this.matriz.getRenglon(pos));
			this.matriz.setRenglon(pos,fila);
			aux = vector.get(renglon);
			vector.set(renglon,vector.get(pos));
			vector.set(pos,aux);
		}
	}

	/** * Método público que se encarga de realizar una sustitución regresiva para resolver el método Gauss, no regresa ningún valor. */
	public void sustitucionRegresive(){
		double suma = 0.0;
		int cont=0;
		for(int i= matriz.getRenglons()-1;i>=0;i--){
			suma = 0.0;
			for(int j=i+1;j<matriz.getColumns();j++)
				suma += matriz.getElement(i,j)*resultados.get(j);
			resultados.set(i,(vector.get(i) - suma)/ matriz.getElement(i,i));
		}

		for(int k = 0;k<resultados.size();k++){
			soluciones.append("\nX"+(k+1)+": "+resultados.get(k)+"\n");
		}
	}

	/** * Método público que se encarga de resolver la matriz enviada atreves del método Gauss, no regresa ningún valor.*/
	public void eliminacionGauss(){
		double cte,cte2,cte3;
		for(int i = 0;i < matriz.getRenglons();i++){
			iteraciones.append("\n\nMatriz:\n"+matriz);
			strategiesPivotal(i,this.estrategia);
			iteraciones.append("\n\nMatriz con Pivote:\n"+matriz);
			iteraciones.append("\n\nVector:\n"+vector);
			for(int j = i+1;j < matriz.getRenglons();j++){
				cte = matriz.getElement(j,i)/matriz.getElement(i,i);
				for(int k = i;k < matriz.getColumns();k++){
					cte2 = matriz.getElement(j,k);
					cte3 = matriz.getElement(i,k);
					matriz.setElement(j,k,cte2 - cte*cte3);
				}
				cte2 = vector.get(j) - cte*vector.get(i);
				vector.set(j,cte2);
			}
		}
		iteraciones.append("\n\nMatriz Final:\n"+matriz);
		iteraciones.append("\n\nVector Final:\n"+vector);
		sustitucionRegresive();
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Gauss.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las soluciones del método Gauss.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}
