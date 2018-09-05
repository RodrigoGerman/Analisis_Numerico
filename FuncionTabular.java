package modelo;

import java.util.ArrayList;

/**
	* La clase FuncionTabular es una clase que se encarga de funcionar como una función tabular para los métodos numéricos 
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class FuncionTabular{
	/** * Variable privada de tipo ArrayList<ArrayList<Double>> que se encarga de simular una función tabular. */
	private ArrayList<ArrayList<Double>> funciontabular = new ArrayList<ArrayList<Double>>();
	/** * Variable privada de tipo int que se encarga de almacenar el número de puntos que almacenará la función tabular.*/
	private int size = 0;

	/** * Método Constructor sin argumentos. */
	public FuncionTabular(){}

	/** * Método Constructor que recibe una Variable int que representa el número de puntos que tendrá la función tabular.*/
	public FuncionTabular(int puntos){
		size = puntos;
		for(int i =0;i<size;i++){
			this.funciontabular.add(new ArrayList<Double>());
			for(int j=0;j<2;j++){
				this.funciontabular.get(i).add(0.0);
			}
		}
	}

	/** * Método Constructor que recibe un arreglo bidimencional para crear una función tabular apartir de dicho arreglo.*/
	public FuncionTabular(double[][] funciontabular){
		size = funciontabular[0].length;
		for(int i =0;i<size;i++){
			this.funciontabular.add(new ArrayList<Double>());
			for(int j=0;j<2;j++){
				this.funciontabular.get(i).add(funciontabular[j][i]);
			}
		}
	}

	/** * Método Constructor que recibe una lista de listas para crear una función tabular a partir de dichas listas.*/
	public FuncionTabular(ArrayList<ArrayList<Double>> funciontabular){
		this.funciontabular = funciontabular;
		size = funciontabular.size();
	}

	/** * Método publico setInvertirTabla que se encarga de invertir los datos de la función tabular, no regresa nigun valor.*/
	public void setInvertirTabla(){
		ArrayList<Double> aux,aux2;
		for(int j = 0;j < size/2;j++){
			aux = funciontabular.get(j);
			aux2 = funciontabular.get((size-1)-j);

			funciontabular.set(j,aux2);
			funciontabular.set((size-1)-j,aux);
		}
	}

	/** * Método publico getSize que se encarga de regresar un int que representa el número de puntos de la función tabular.*/
	public int getSize(){
		return size;
	}

	/** * Método publico getElement que recibe el renglón y la columna de la función tabular del valor el cual regresa la función. */
	public double getElement(int renglon,int columna){
		return this.funciontabular.get(renglon).get(columna);
	}

	/** * Método sobrescrito para modificar la manera es que se visualiza el objeto FuncionTabular.*/
	@Override
	public String toString(){
		String mat = "";
		double aux = 0.0;
		for(int i = 0;i < size;i++){
			for(int j =0;j < 2;j++){
				aux = this.funciontabular.get(i).get(j);
				if(aux >= 0)
					mat+=" ";
				mat += String.valueOf(aux) + "\t";
			}
			mat += "\n";
		}
		return mat;
	}
}
