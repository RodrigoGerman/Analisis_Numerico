package modelo.theme2.openmethods;

import modelo.EvalFuction;
import java.util.Stack;
import java.util.ArrayList;

/**
	* La clase FactoresCuadraticos es una clase que se encarga de realizar el método numérico de Factores Cuadráticos
	* de la materia de análisis numérico.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/

public class FactoresCuadraticos{
	/** * Variable privada de tipo double que se encarga de asignar un valor especifico a la estimación de error con el que se realizara el método de Factores Cuadráticos. */
	private double estimacion;
	/** * Variables privadas de tipo int donde grado se encarga de almacenar el grado de cada componente del polinomio y el grado_principal se encargar de almacenar el grado mayor del polinomio. */
	private int grado = 0,grado_principal = 0;
	/** * Variables privadas de tipo donde p y q se encargan de almacenar las aproximaciones a las raíces, vq y vp se encargar de almacenar las aproximaciones para los valores de p y q.*/
	private double p=0.0,q=0.0,vp=0.0,vq=0.0;
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores nuevos del polinomio al realizar el método Factores Cuadráticos. */
	private ArrayList<Double> b = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores de las derivadas de forma aproximada. */
	private ArrayList<Double> c = new ArrayList<Double>();
	/** * Variable privada de tipo ArrayList<Double> que se encarga de guardar los valores del polinomio ingresado. */
	private ArrayList<Double> valores = new ArrayList<Double>();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar las iteraciones que va realizando el método de Factores Cuadráticos.*/	
	private StringBuffer iteraciones = new StringBuffer();
	/** * Variable privada de tipo StringBuffer que se encarga de guardar los resultados que va realizando el método de Factores Cuadráticos. */	
	private StringBuffer resultados = new StringBuffer();
	/** * Variable privada de tipo ArrayList<String> que se encarga de guardar los valores de las raíces que va calculando el método de Factores Cuadráticos. */
	private ArrayList<String> raices = new ArrayList<String>();

	/** * Método Constructor sin argumentos. */
	public FactoresCuadraticos(){}

	/** * Método Constructor que recibe un String el cual representa el polinomio y un double que representa la estimación de error para el método de Factores Cuadráticos. */
	public FactoresCuadraticos(String expresion,double estimacion){
		this.estimacion = estimacion;

		setFunction(expresion);

		iteraciones.append(valores+"\n");

		for(int i =0;i < grado_principal+1;i++){
			b.add(0.0);
			c.add(0.0);
		}

		setRaices(grado_principal);

		for(int i = 0;i < raices.size();i++)
			resultados.append("X"+(i+1)+": "+raices.get(i)+"\n");

		iteraciones.append("\nSolución:\n"+resultados+"\n");
	}

	/** * Método privado formulaGeneral que se encarga de realiza la formula general, recibe tres valores double donde representan a,b y c de la formula general. */
	private void formulaGeneral(double a,double b, double c){
		double x1,x2,aux=0.0;
		aux = Math.pow(b,2)-(4*a*c);
		if(aux >= 0){
			x1 = (-b + Math.sqrt(aux))/(2*a);
			x2 = (-b - Math.sqrt(aux))/(2*a);
			raices.add(String.valueOf(x1));
			raices.add(String.valueOf(x2));
		}
		else{
			x1 = -b/(2*a);
			x2 = Math.sqrt(-aux)/(2*a);
			if(Math.abs(x1) < estimacion)
				x1 = 0.0;
			raices.add(String.valueOf(x1)+" + "+String.valueOf(x2)+" i");
			raices.add(String.valueOf(x1)+" - "+String.valueOf(x2)+" i");
		}
	}

	/** * Método privado setGrado que se encarga de buscar y asignar el grado a acuda componente del polinomio. */
	private void setGrado(char[] fun,int i){
		int aux=0;
		String grado_str = "";
		grado = 0;
		fuera:
		while(i < fun.length){
			if(fun[i] == '^'){
				while(true){
					aux = (int)fun[++i];
					if(aux > 48 && aux < 58)
						grado_str += fun[i];
					else if(aux != 43 || aux != 45)
						break fuera;
				}
			}
			i++;
		}

		if(!grado_str.equals(""))
			grado = Integer.parseInt(grado_str);

		i = 0;
		if(grado == 0){
			fuera:
			while(i < fun.length){
				aux = (int)fun[i];
				if(aux == 120)
					grado = 1;
				i++;
			}
		}
	}

	/** * Método privado setFunction que se encarga de buscar y asignar los valores a acuda componente del polinomio.*/
	private void setFunction(String expresion){
		char[] fun = (expresion).toCharArray();
		double aux2,aux3;
		int i =0,aux = 0;
		String valor = "",grado_str = "";

		setGrado(fun,0);
		grado_principal = grado;

		for(int x=0;x < grado+1;x++)
			valores.add(0.0);

		if(grado > 0){
			while(i < fun.length){

				aux = (int)fun[i];

				if(aux == 43)
					valor = "";

				else if(aux == 45)
					valor = "-";
			
				else if(aux > 47 && aux < 58 || aux == 46)
					valor += fun[i];

				else if(aux == 120){
					setGrado(fun,i);
					if(valor.equals("-")){
						valores.set(valores.size()-(grado+1),Double.parseDouble("-1"));
						valor="";
					}

					else if(valor.equals("")){
						valores.set(valores.size()-(grado+1),Double.parseDouble("1"));
						valor="";
					}
					else
						valores.set(valores.size()-(grado+1),Double.parseDouble(valor));
				}

				i++;
			}
			valores.set(valores.size()-1,Double.parseDouble(valor));
		}

		aux = valores.size()-1;
		for(int j = 0;j< valores.size()/2;j++){
			aux2 = valores.get(j);
			aux3 = valores.get(aux-j);
			valores.set(j,aux3);
			valores.set(aux-j,aux2);
		}

	}

	/** * Método privado setRaices que se encarga de calcular y asignar las raíces del polinomio a través del método Factores Cuadráticos. */
	private final void setRaices(int grado){
		int pos = b.size()-1;
		double aux=0;
		double error = 1.0;
		while(grado >= 3){
			while(error > estimacion){

				b.set(grado,valores.get(grado));
				b.set(grado-1,valores.get(grado-1)+(p*b.get(grado)));
				for(int i = grado-2;i >=0;i--)
					b.set(i,valores.get(i)+(p*b.get(i+1))+(q*b.get(i+2)));

				iteraciones.append("B:"+b+"\n");

				c.set(grado,b.get(grado));
				c.set(grado-1,b.get(grado-1)+(p*c.get(grado)));
				for(int i = grado-2;i >=0;i--)
					c.set(i,b.get(i)+(p*c.get(i+1))+(q*c.get(i+2)));

				iteraciones.append("C:"+b+"\n");
				
				vq = (b.get(0)*c.get(2)-b.get(1)*c.get(1))/(c.get(1)*c.get(3)-c.get(2)*c.get(2));
				vp = -(b.get(0)+(c.get(2)*vq))/c.get(1);

				iteraciones.append("VQ:"+vq+"\n");
				iteraciones.append("VP:"+vp+"\n");

				p = vp+p;
				q = vq+q;

				iteraciones.append("P:"+p+"\n");
				iteraciones.append("Q:"+q+"\n");

				error = Math.abs(b.get(0)) + Math.abs(b.get(1));
			}

			formulaGeneral(1,-p,-q);

			grado = grado-2;
			for(int i = 0;i<=grado;i++)
				valores.set(i,b.get(i+2));
		}
		
		if(grado == 2)
			formulaGeneral(valores.get(2),valores.get(1),valores.get(0));
		else{
			aux = -valores.get(0)/ valores.get(1);
			raices.add(String.valueOf(aux));
		}
	}

	/** * Método publico getRaices que se encarga de regresar una ArrayList<String> con todas las raíces del polinomio.*/
	public ArrayList<String> getValorRaices(){
		return this.raices;
	}

	/** * Método publico getRaizes que se encarga de regresar una String con todas las raíces del polinomio.*/
	public String getRaices(){
		return resultados.toString();
	}

	/** * Método publico getIteraciones que se encarga de regresar un String con todas las iteraciones del método Factores Cuadráticos.*/
	public String getIteraciones(){
		return iteraciones.toString();
	}
}