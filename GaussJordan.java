
package modelo.theme3;

import modelo.Matriz;
import java.util.ArrayList;

/**
	* La clase GaussJordan es una clase que se encarga de realizar el método numérico de Gauss Jordán
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class GaussJordan{
	/** * Variable privada tipo String que almacena el tipo de estrategia de pivoteo a utilizar en el método de Gauss Jordán.*/
	private String estrategia = "";
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de Gauss Jordán.*/
	private Matriz matriz = new Matriz();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Gauss Jordán.*/
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de Gauss Jordán. */	
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos. */
	public GaussJordan(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ampliada ingresada por el usuario y una String que representa la estrategia de pivoteo. */
	public GaussJordan(Matriz matriz,String pivote){
		this.matriz = matriz;
		this.estrategia = pivote;
	}

	/** * Método privado strategiesPivotal que recibe un int que representa el renglón y una String que representa la estrategia de pivoteo, se encarga de realizar la estrategia enviada, no regresa ningún valor.*/
	private void strategiesPivotal(int renglon,String estrategia){
		if(estrategia.equals("Parcial"))
			pivoteParcial(renglon);
		else if(estrategia.equals("Parcial Escalado"))
			pivoteParcialEscalado(renglon);
		else if(estrategia.equals("Completo"))
			pivoteCompleto(renglon);
		else if(!estrategia.equals("Parcial Diagonal"))
			System.out.println("Estrategia invalida");
	}

	/** * Método privado que recibe un int que representa el renglón, se encarga de realizar el método de GaussJordan atreves de la estrategia pivoteo parcial, no regresa ningún valor.*/
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
		}
	}

	/** * Método privado que recibe un int que representa el renglón, se encarga de realizar el método de GaussJordan atreves de la estrategia pivoteo parcial escalado, no regresa ningún valor.*/
	private void pivoteParcialEscalado(int renglon){
		double max =0.0;
		double aux = 0.0;
		int pos = renglon;
		double[] maxs = new double[matriz.getRenglons()];
		int cont = 0; 
		ArrayList<Double> fila;
		for(int i = renglon;i < matriz.getRenglons();i++){
			for(int j= renglon ;j < matriz.getColumns()-1;j++){
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
		}
	}

	/** * Método privado que recibe un int que representa el renglón, se encarga de realizar el método de gauss atreves de la estrategia pivoteo completo, no regresa ningún valor.*/
	private void pivoteCompleto(int renglon){
		double max = 0.0,aux;
		int pos_fila = 0,pos_columna=0,columna = renglon;
		ArrayList<Double> fila;

		for(int i = renglon;i < matriz.getRenglons();i++){
			for(int j= renglon;j < matriz.getColumns()-1;j++){
				aux = Math.abs(matriz.getElement(i,j));
				if(aux > max){
					max = aux;
					pos_fila = i;
					pos_columna = j;
				}
			}
		}

		if(pos_fila != renglon){
			fila = (ArrayList<Double>)this.matriz.getRenglon(renglon);
			this.matriz.setRenglon(renglon,(ArrayList<Double>)this.matriz.getRenglon(pos_fila));
			this.matriz.setRenglon(pos_fila,fila);
		}

		if(pos_columna != columna){
			for(int k = 0;k < matriz.getRenglons();k++){
				aux = this.matriz.getElement(k,columna);
				this.matriz.setElement(k,columna,this.matriz.getElement(k,pos_columna));
				this.matriz.setElement(k,pos_columna,aux);
			}
		}
	}

	/** * Método publico que se encarga de resolver la matriz enviada atreves del método Gauss Jordán, no regresa ningún valor.*/
	public void eliminacionGaussJordan(){
		double cte,cte2,cte3;
		for(int i = 0;i < matriz.getRenglons();i++){
			iteraciones.append("\n\nMatriz:\n"+matriz);
			strategiesPivotal(i,this.estrategia);
			iteraciones.append("\n\nMatriz con Pivote:\n"+matriz);
			cte = matriz.getElement(i,i);
			matriz.setNormalizarRenglon(cte,i);
			for(int j = 0;j < matriz.getRenglons();j++){
				if(j!=i){
					cte = matriz.getElement(j,i);
					for(int k = i;k < matriz.getColumns();k++){
						cte2 = matriz.getElement(j,k);
						cte3 = matriz.getElement(i,k);
						matriz.setElement(j,k,cte2 - cte*cte3);
					}
				}
			}
		}
		iteraciones.append("\n\nMatriz Final:\n"+matriz);

		for(int k =0;k<matriz.getRenglons();k++){
			soluciones.append("\nX"+(k+1)+": "+matriz.getElement(k,matriz.getRenglons())+"\n");
		}
	}

	/** * Método publico que se encarga de regresar una ArrayList<Double> con los valores de la solucion del sistema atreves del método Gauss Jordán.*/
	public ArrayList<Double> getResult(){
		ArrayList<Double> resultados = new ArrayList<Double>();
		for(int k =0;k<matriz.getRenglons();k++){
			resultados.add(matriz.getElement(k,matriz.getRenglons()));
		}
		return resultados;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Gauss Jordán.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las soluciones del método Gauss Jordán.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}
