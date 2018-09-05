
package modelo.theme3;

import modelo.Matriz;
import java.util.ArrayList;

/**
	* La clase LU es una clase que se encarga de realizar el método numérico de LU
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class LU{
	/** * Variable privada tipo String que representa el método a emplear al resolver el método numérico de LU.*/
	private String metodo = "";
	/** * Variable privada tipo Matriz que almacena la matriz recibida y para ser utilizada en el método de LU.*/
	private Matriz matriz = new Matriz();
	/** * Variables privadas tipo Matriz que almacena la matrizes necesarias para ser utilizadas en el método de LU.*/
	private Matriz l,u,inversa;
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores del vector recibido.*/
	private ArrayList<Double> vector = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores modificados del vector recibido.*/
	private ArrayList<Double> vector1 = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores del vector recibido.*/
	private ArrayList<Double> resultados = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de LU.*/
	private StringBuffer iteraciones = new StringBuffer();
		/** * Variable privada de tipo StringBuffer que se encarga de guardar las soluciones que va calculando el método de LU.*/
	private StringBuffer soluciones = new StringBuffer();

	/** * Método Constructor sin argumentos.*/
	public LU(){}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario.*/
	public LU(Matriz matriz){
		this.matriz = matriz;
		for(int i =0;i<matriz.getColumns();i++){
			vector1.add(0.0);
			resultados.add(0.0);
		}

		l = new Matriz(matriz.getRenglons(),matriz.getColumns());
		u = new Matriz(matriz.getRenglons(),matriz.getColumns());
		setMetodo("Inversa");
	}

	/** * Método Constructor que recibe una Matriz la cual representa la matriz ingresada por el usuario,.*/
	public LU(Matriz matriz,ArrayList<Double> vector,String metodo){
		this.matriz = matriz;
		this.vector = vector;
		this.metodo = metodo;

		for(int i =0;i<matriz.getColumns();i++){
			vector1.add(0.0);
			resultados.add(0.0);
		}

		l = new Matriz(matriz.getRenglons(),matriz.getColumns());
		u = new Matriz(matriz.getRenglons(),matriz.getColumns());
		setMetodo(metodo);
	}

	/** * Método privado setMetodo que recibe una String que representa el método a utilizar y resolver el método numérico LU.*/
	private void setMetodo(String metodo){
		if(metodo.equals("Crout")){
			getMatrizLUCrout();
			getVectorCrout();
			sustitucionRegresiveCrout();
		}

		else if(metodo.equals("Doolittle")){
			getMatrizLUDoolittle();
			getVectorDoolittle();
			sustitucionRegresiveDoolittle();
		}

		else if(metodo.equals("Inversa")){
			inversa = new Matriz(matriz.getRenglons(),matriz.getColumns());
			getMatrizLUCrout();
			inversa = getInversa();
		}
	}

	/** * Método publico getMatrizLUDoolittle que se encarga de calcular y almacenar las matriz L y U para la resolución del método atreves de la estrategia Doolittle.*/
	public void getMatrizLUDoolittle(){
		double aux=0.0,aux2 = 0.0;
		for(int i = 0;i < this.matriz.getRenglons();i++){
			for(int j =0;j<this.matriz.getColumns();j++){
				aux = 0.0;
				aux2 = 0.0;
				if(j == 0){
					u.setElement(i,0,matriz.getElement(i,0));
				}
				else{
					for(int k = 0;k < j;k++){
						aux += u.getElement(i,k)*l.getElement(k,j);		
					}
					if(j<=i){
						aux = this.matriz.getElement(i,j) - aux;
						u.setElement(i,j,aux);
					}
				}

				if(i == 0){
					l.setElement(0,j,matriz.getElement(0,j)/matriz.getElement(0,0));
				}
				else{
					for(int k = 0;k < i;k++){
						aux2 += u.getElement(i,k)*l.getElement(k,j);		
					}

					if(i<=j){
						aux2 = (this.matriz.getElement(i,j) - aux2)/ u.getElement(i,i);
						l.setElement(i,j,aux2);
					}
				}
			}
		}
		iteraciones.append("\n\nMatriz L:\n"+l);
		iteraciones.append("\n\nMatriz U:\n"+u);
	}

	/** * Método publico getVectorDoolittle que se encarga de calcular y almacenar el vector solución para la resolución del método atreves de la estrategia Doolittle.*/
	private void getVectorDoolittle(){
		double aux = 0.0;
		vector1.set(0,vector.get(0)/u.getElement(0,0));
		for(int i = 1;i < this.matriz.getRenglons();i++){
			aux = 0.0;
			for(int k = 0;k < i;k++){
				aux += u.getElement(i,k)*vector1.get(k);		
			}
			vector1.set(i,(vector.get(i)-aux)/u.getElement(i,i));
		}
	}

	/** * Método publico sustitucionRegresiveDoolittle que se encarga de hacer una sustitución regresiva para resolver el método atreves de la estrategia Doolittle.*/
	public void sustitucionRegresiveDoolittle(){
		double suma = 0.0;
		for(int i= vector.size()-1;i >= 0;i--){
			suma = 0.0;
			for(int j=i+1;j<vector.size();j++)
				suma += l.getElement(i,j)*resultados.get(j);
			resultados.set(i,(vector1.get(i) - suma));
		}

		for(int k = 0;k<resultados.size();k++){
			soluciones.append("\nX"+(k+1)+": "+resultados.get(k)+"\n");
		}
	}

	/** * Método publico getMatrizLUCroutque se encarga de calcular y almacenar las matriz L y U para la resolución del método atreves de la estrategia Crout.*/
	public void getMatrizLUCrout(){
		double aux=0.0,aux2 = 0.0;
		for(int i = 0;i < this.matriz.getRenglons();i++){
			for(int j =0;j<this.matriz.getColumns();j++){
				aux = 0.0;
				aux2 = 0.0;
				if(j == 0){
					l.setElement(i,0,this.matriz.getElement(i,0));
				}
				else{
					for(int k = 0;k < j;k++){
						aux += l.getElement(i,k)*u.getElement(k,j);		
					}
					if(j<=i){
						aux = this.matriz.getElement(i,j) - aux;
						l.setElement(i,j,aux);
					}
				}

				if(i == 0){
					u.setElement(0,j,this.matriz.getElement(0,j)/matriz.getElement(0,0));
				}
				else{
					for(int k = 0;k < i;k++){
						aux2 += l.getElement(i,k)*u.getElement(k,j);		
					}

					if(i<=j){
						aux2 = (this.matriz.getElement(i,j) - aux2)/ l.getElement(i,i);
						u.setElement(i,j,aux2);
					}
				}
			}
		}
		iteraciones.append("\n\nMatriz L:\n"+l);
		iteraciones.append("\n\nMatriz U:\n"+u);
	}

	/** * Método publico getVectorDoolittle que se encarga de calcular y almacenar el vector solución para la resolución del método atreves de la estrategia Crout.*/
	private void getVectorCrout(){
		double aux = 0.0;
		vector1.set(0,vector.get(0)/l.getElement(0,0));
		for(int i = 1;i < this.matriz.getRenglons();i++){
			aux = 0.0;
			for(int k = 0;k < i;k++){
				aux += l.getElement(i,k)*vector1.get(k);		
			}
			vector1.set(i,(vector.get(i)-aux)/l.getElement(i,i));
		}
	}

	/** * Método publico sustitucionRegresiveDoolittle que se encarga de hacer una sustitución regresiva para resolver el método atreves de la estrategia Crout.*/
	public void sustitucionRegresiveCrout(){
		double suma = 0.0;
		for(int i= vector.size()-1;i >= 0;i--){
			suma = 0.0;
			for(int j=i+1;j<vector.size();j++)
				suma += u.getElement(i,j)*resultados.get(j);
			resultados.set(i,(vector1.get(i) - suma));
		}

		for(int k = 0;k<resultados.size();k++){
			soluciones.append("\nX"+(k+1)+": "+resultados.get(k)+"\n");
		}
	}

	/** * Método publico getInversa que se encarga de calcular y almacenar la matriz inversa atreves de las matrices L y U de la matriz ingresada por el usuario.*/
	public Matriz getInversa(){
		double suma = 0.0;
		ArrayList<Double> vector = new ArrayList<Double>();

		for(int k = 0;k<l.getRenglons();k++){
			vector.add(0.0);
		}

		for(int x = 0;x<l.getRenglons();x++){
			for(int k = 0;k<l.getRenglons();k++)
				vector.set(k,0.0);

			vector.set(x,1.0);

			for(int i= 0 ;i < l.getRenglons();i++){
				suma = 0.0;
				for(int j=0;j<i;j++)
					suma += l.getElement(i,j)*vector.get(j);
				vector.set(i,(vector.get(i) - suma)/ l.getElement(i,i));
			}

			iteraciones.append("\n\nVector:\n"+vector);

			for(int i= u.getRenglons()-1;i >= 0;i--){
				suma = 0.0;
				for(int j=i+1;j<u.getColumns();j++)
					suma += u.getElement(i,j)*resultados.get(j);
				this.resultados.set(i,(vector.get(i) - suma)/ u.getElement(i,i));
			}

			iteraciones.append("\n\nVector Resultados:\n"+resultados);

			for(int l = 0;l<resultados.size();l++){
				inversa.setElement(l,x,resultados.get(l));
			}

			iteraciones.append("\n\nMatriz:\n"+inversa);
		}
		return inversa;
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método LU.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}

	/** * Método publico getSoluciones que se encarga de regresar un String con todas las soluciones del método LU.*/
	public String getSoluciones(){
		return soluciones.toString();
	}
}