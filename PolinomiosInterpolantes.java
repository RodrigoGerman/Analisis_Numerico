package modelo.theme4;
import modelo.FuncionTabular;
import java.util.ArrayList;

public class PolinomiosInterpolantes{
	/** * Variable privada de tipo ArrayList<ArrayList<Double>> que se encarga de simular una tabla de diferencias.*/
	private ArrayList<ArrayList<Double>> tabladiferencias = new ArrayList<ArrayList<Double>>();
	/** * Variable privada de tipo FuncionTabular que se encarga de guardar la funcion tabular ingresada por el usuario.*/
	private FuncionTabular datos;
	/** * Variables privadas de tipo double donde y almacena el resultado de realizar el metodo de Polinomio Lagrange, la x almacena el valor a evaluar
	la k almacena una constante del metodo de Polinomios Interpolantes y h almacena la separacion entre cada punto evaluado de la funcion tabular.*/
	private double x = 0.0,y = 0.0,k = 0.0,h=0.0;
	/** * Variables privadas de tipo int donde pos almacena la posicion de una x en la funcion tabular y aux el tamaño de la funcion tabular.*/
	private int pos=0,aux=0;
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el metodo de Polinomios Interpolantes.*/
	private StringBuffer iteraciones = new StringBuffer();

	/** * Metodo Constructor sin argumentos.*/
	public PolinomiosInterpolantes(){}

	/** * Metodo Constructor que recibe una FuncionTabular la cual representa la funcion tabular Y  un double que representa la x a evaluar.*/
	public PolinomiosInterpolantes(FuncionTabular datos,double x){
		this.datos = datos;
		this.x = x;
		setFuncionTabular(x);
		createTableDiferences();
		setH();
		setK();
		setTablaDiferencias();
		printTabla();
		setSolucion();
	}

	/** * Metodo privado setFuncionTabular que es el encargado de calcular y asignar la posicion de la x a utilizar de la funcion tabular, no regresa ningun valor.*/
	private void setFuncionTabular(double x){
		int aux = datos.getSize();

		if(x < this.datos.getElement(0,0)){
			datos.setInvertirTabla();
			iteraciones.append("Función Tabular Invertida:\n"+datos+"\n");
			for(int i=aux-1;i > 0;i--)
				if(x <= this.datos.getElement(i,0)){
					pos = i;
					break;
				}
		}

		else if(x > this.datos.getElement(aux-1,0)){
			iteraciones.append("Función Tabular:\n"+datos+"\n");
			pos = aux-1;
		}

		else{
			iteraciones.append("Función Tabular:\n"+datos+"\n");
			for(int i=0;i<datos.getSize();i++)
				if(x <= this.datos.getElement(i,0)){
					System.out.println(i);
					pos = i;
					break;
				}
		}
	}

	/** * Metodo privado createTableDiferences que es el encargado de crear la tabla de diferencias para el metodo de Polinomios Interpolantes.*/
	private void createTableDiferences(){
		if(pos >= datos.getSize()-1)
			aux = 3;

		for(int i =0;i<datos.getSize()+aux;i++){
			this.tabladiferencias.add(new ArrayList<Double>());
			for(int j=0;j<(datos.getSize()+1)/2;j++){
				this.tabladiferencias.get(i).add(0.0);
			}
		}
	}

	/** * Metodo privado setH para calcular y asignar la separacion entre cada punto de la funcion tabular recibida, no regresa ningun valor.*/
	private void setH(){
		double aux = 0.0;

		h = Math.ceil((datos.getElement(1,0)-datos.getElement(0,0))*1000000)/1000000;

		for(int i = 2;i < datos.getSize()-1;i++){

			aux = Math.ceil((datos.getElement(i+1,0) - datos.getElement(i,0))*1000000)/1000000;

			if((h-aux) > 0.00001){
				iteraciones.append("H no es constante entre los intervalos por lo que la aproximacion calculada esta mál.");
				break;
			}
		}
	}

	/** * Metodo privado setK para calcular y asignar la onstante del metodo de Polinomios Interpolantes, no regresa ningun valor.*/
	private void setK(){
		k = (x-this.datos.getElement(pos-1,0))/h;
	}

	/** * Metodo privado f que recibe un int el cual representa la posicion de la x a usar y regresa el valor de dicha x.*/
	private double f(int pos){
		return datos.getElement(pos,1);
	}

	/** * Metodo privado fTD que recibe dos int donde i y j representan la posicion de la x a usar y regresa el valor de dicha x.*/
	private double fTD(int i,int j){
		return this.tabladiferencias.get(i).get(j);
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

	/** * Metodo privado getCombinacion que es el encargado de calcular y regresar la combinacion del numero double que recibe respecto al int que recibe.*/
	private double getCombinacion(double k,int i){
		double combinacion = 0.0;
		double sum = 1.0;

		for(int x = 0;x < i;x++)
			sum *= (k-(x+1));

		combinacion = (k*sum)/getFactorial(i+1);

		return combinacion;
	}

	/** * Metodo privado setTablaDiferencias que es el encargado de almacenar y calcular la tabla de diferancias del metodo de Polinomios Interpolantes, no regresa ningun valor.*/
	private void setTablaDiferencias(){

		for(int i = 0;i<(datos.getSize()+1)/2;i++){
			for(int j = i;j<datos.getSize();j++){
				if(i == 0 && j!=0)
					this.tabladiferencias.get(j).set(0,f(j)-f(j-1));

				if(j != 0 && i !=0 && j < datos.getSize()-1)
					this.tabladiferencias.get(j+1).set(i,fTD(j+1,i-1)-fTD(j,i-1));
			}
		}

		if(pos >= datos.getSize()-1){
			for(int i = 1;i<(datos.getSize()+1)/2;i++){
				for(int j = pos ;j < pos+2;j++){
					if(i == (datos.getSize()+1)/2-1)
						this.tabladiferencias.get(j+1).set(i,fTD(j,i));
					else
						this.tabladiferencias.get(j+1).set(i,fTD(j,i)+fTD(j,i+1));
					
				}
			}
		}
	}

	/** * Metodo privado printTabla que es el encargado de realizar el metodo de Polinomios Interpolantes, no regresa ningun valor.*/
	public void printTabla(){
		String mat = "";
		double aux2 = 0.0;
		for(int i = 0;i <datos.getSize()+aux;i++){
			for(int j =0;j < (datos.getSize()+1)/2;j++){
				aux2 = this.tabladiferencias.get(i).get(j);
				if(aux2 >= 0)
					mat+=" ";
				mat += String.valueOf(aux2) + "\t";
			}
			mat += "\n";
		}
		iteraciones.append("Tabla de Diferencias:\n"+mat+"\n");
	}
	
	/** * Metodo privado setSolucion que es el encargado de realizar el metodo de Polinomios Interpolantes, no regresa ningun valor.*/
	private void setSolucion(){
		this.y = this.datos.getElement(pos-1,1);
		iteraciones.append("1: "+y+"\n");
		for(int i =0;i < (datos.getSize()+1)/2;i++){
			this.y += getCombinacion(k,i)*fTD(pos++,i);
			iteraciones.append((i+1)+": "+y+"\n");
		}
		iteraciones.append("\n Y : "+y+"\n");
	}

	/** * Metodo publico getSolucion que se encarga de regresar un double con la solucion del metodo de Polinomios Interpolantes.*/
	public double getSolucion(){
		return this.y;
	}

	/** * Metodo publico getIteraciones que se encarga de regresar un String con todas las iteraciones del metodo Polinomios Interpolantes.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}
}