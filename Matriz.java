package modelo;

import java.util.ArrayList;

/**
	* La clase Matriz es una clase que se encarga de funcionar como una Matriz para los métodos numéricos 
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class Matriz{
	/** * Variable privada de tipo ArrayList<ArrayList<Double>> que se encarga de simular una Matriz.*/
	private ArrayList<ArrayList<Double>> matriz = new ArrayList<ArrayList<Double>>();
	/** * Variables privadas de tipo int donde renglón representa el numero de renglones y columna el numero de columnas que tendrá la matriz a crear.*/
	private int renglon,columna;

	/** * Método Constructor sin argumentos.*/
	public Matriz(){}

	/** * Método Constructor que recibe dos Variables int donde renglón representa el numero de renglones que tendrá la matriz y columna representa el numero de columnas que tendrá la matriz.*/
	public Matriz(int renglon,int columna){
		this.renglon = renglon;
		this.columna = columna;
		for(int i =0;i<renglon;i++){
			this.matriz.add(new ArrayList<Double>());
			for(int j=0;j<columna;j++){
				this.matriz.get(i).add(0.0);
			}
		}
	}

	/** * Método Constructor que recibe un arreglo bidimensional para crear una Matriz a partir de dicho arreglo.*/
	public Matriz(double[][] matriz){
		renglon = matriz.length;
		columna = matriz[0].length;
		for(int i =0;i<renglon;i++){
			this.matriz.add(new ArrayList<Double>());
			for(int j=0;j<columna;j++){
				this.matriz.get(i).add(matriz[i][j]);
			}
		}
	}

	/** * Método Constructor que recibe una lista de listas para crear una matriz a partir de dichas listas.*/
	public Matriz(ArrayList<ArrayList<Double>> matriz){
		this.matriz = matriz;
		columna = matriz.get(0).size();
		renglon = matriz.size();
	}

	/** * Método publico getRenglons que se encarga de regresar un int que representa el numero de renglones de la Matriz.*/
	public int getRenglons(){
		return renglon;
	}

	/** * Método publico getRenglons que se encarga de regresar un int que representa el numero de columnas de la Matriz.*/
	public int getColumns(){
		return columna;
	}

	/** * Método publico getMultiplicacion que se encarga de regresar el resultado de multiplicar una matriz con otra matriz.*/
	public Matriz getMultiplicacion(Matriz b){
		Matriz resultado = null;
		if(this.getColumns() == b.getRenglons()){
			resultado = new Matriz(this.getRenglons(),b.getColumns());
			double aux =0.0;
			for(int i= 0;i<this.getRenglons();i++){
				for(int j=0;j<b.getColumns();j++){
					aux = 0.0;
					for(int k=0;k<b.getRenglons();k++){
						aux+= this.getElement(i,k)*b.getElement(k,j);
					}
					resultado.setElement(i,j,aux);
				}
			}
		}
		return resultado;
	}

	/** * Método publico getMultiplicacion que se encarga de regresar el resultado de multiplicar una matriz con un vector.*/
	public ArrayList<Double> getMultiplicacionVector(ArrayList<Double> b){
		ArrayList<Double> resultado = new ArrayList<Double>();
		double aux =0.0;
		for(int j=0;j<b.size();j++){
			aux = 0.0;
			for(int k=0;k<b.size();k++){
				aux+= this.getElement(j,k)*b.get(k);
			}
			resultado.add(aux);
		}
		return resultado;
	}

	/** * Método publico getRenglon que se encarga de regresar un renglon de la matriz, recibe un int que representa la posición de dicho renglon.*/
	public ArrayList<Double> getRenglon(int renglon){
		return this.matriz.get(renglon);
	}

	/** * Método publico getRenglon que se encarga de asignar un renglón a la matriz, recibe un int que representa la posición de dicho renglón y un ArrayList<Double> con los valores del renglón a introducir.*/
	public void setRenglon(int pos,ArrayList<Double> renglon){
		this.matriz.set(pos,renglon);
	}

	/** * Método publico getElement que se encarga de regresar el valor de una cierta posición de la matriz, recibe un int renglón que representa la posición de dicho renglón y otro int columna que representa la posición de dicha columna.*/
	public double getElement(int renglon,int columna){
		return this.matriz.get(renglon).get(columna);
	}

	/** * Método publico setElement que se encarga de asignar un valor en una cierta posición de la matriz, recibe un int renglón que representa la posición de dicho renglón y otro int columna que representa la posición de dicha columna y un double que representa el valor a introducir.*/
	public void setElement(int renglon,int columna,double valor){
		this.matriz.get(renglon).set(columna,valor);
	}

	/** * Método publico setNormalizarRenglon que se encarga de normalizar un vector respecto a un valor, recibe un double que representa el valor a utilizar para normalizar y un int que representa la posición del renglón a normalizar.*/
	public void setNormalizarRenglon(double valor,int renglon){
		double aux = 0.0;
		for(int i = 0;i < this.columna;i++){
			aux = this.matriz.get(renglon).get(i) / valor;
			this.matriz.get(renglon).set(i,aux);
		}
	}

	/** * Método sobrescrito para modificar la manera es que se visualiza el objeto Matriz.*/
	@Override
	public String toString(){
		String mat = "";
		double aux = 0.0;
		for(int i = 0;i < renglon;i++){
			for(int j =0;j < columna;j++){
				aux = this.matriz.get(i).get(j);
				if(aux >= 0)
					mat+=" ";
				mat += String.valueOf(aux) + "\t";
			}
			mat += "\n";
		}
		return mat;
	}
}