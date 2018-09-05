package controlador;

import vista.accesories.TextFieldNumber;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Color;

/**
	* La clase WindowVector es una clase que hereda de la clase JDialog debido a que es una ventana 
	* emergente la cual tiene la funcion de registar el vector de resultados de una matriz para
	* que los metodos del tema 3 de analisis numerico se puedan resolver, se encarga de registrar
	* un vector y regresarlo a su ventana padre.
	* @author German Lopez Rodrigo
	* @version 11/04/2017/Final 
*/
public class WindowVector extends JDialog implements ActionListener{
	/** * Variable tipo JButton que tiene de funcion almacenar los datos del vector en una lista.*/
	protected final JButton aceptar  = new JButton("Aceptar");
	/** * Variable tipo JButton que tiene de funcion de limpiar los campos de la ventana emergente JDialog.*/
	protected final JButton limpiar= new JButton("Limpiar");
	/** * Variable tipo JPanel para almacenar los campos visuales que ve el usuario en la ventana emergente.*/
	private JPanel panel = new JPanel(new BorderLayout());
	/** * Variable tipo ArrayList de JTextField que almacena los valores ingresados por el usuario.*/
	private ArrayList<JTextField> valores = new ArrayList<JTextField>();
	/** * Variable tipo ArrayList de Double que almacena el vector ingresado por el usuario.*/
	private ArrayList<Double> vector = new ArrayList<Double>();
	/** * Variable tipo int que almacena el tamaño del vector ingresado por el usuario.*/
	private int renglones;
	/** * VariableS tipo JPanel que almacena el panel que contiene al vector y al panel que contiene los botones que interactua el usuario.*/
	private JPanel vectorpanel,buttonspanel;

	/** * Metodo constructor que crea una WindowVector pero sin ningun comportamiento ni estilo especifico.*/
	public WindowVector(){}

	/** * Metodo constructor que crea una WindowVector pero con un comportamiento y estilo especifico, recibe la ventana padre
	que la manda a llamar, un booleano que indica el modelo y el numero de renglones que tiene el vector.*/
	public WindowVector(JFrame parent,boolean modal,int renglones){
		super(parent,"Matriz",modal);
		vectorpanel = new JPanel(new GridLayout(renglones,1,10,10));
		buttonspanel = new JPanel();
		crearVector(renglones);
		buttonspanel.add(aceptar);
		buttonspanel.add(limpiar);
		showVector();
		panel.add(vectorpanel,BorderLayout.CENTER);
		panel.add(buttonspanel,BorderLayout.SOUTH);
		aceptar.addActionListener(this);
		limpiar.addActionListener(this);
		getContentPane().add(panel);
		setResizable(false);
		setSize(100,100*renglones);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/** * Metodo que recibe el numero de renglones con el cual construye el vector visual con el numero de renglones.*/
	private void crearVector(int renglones){
		this.renglones = renglones;
		for(int i =0;i<renglones;i++){
			this.valores.add(new TextFieldNumber("-",Color.BLACK,1));
			this.valores.get(i).setHorizontalAlignment(JTextField.CENTER);
		}
	}

	/** * Metodo que tiene como funcion constuir en el panel vectorpanel el vector visual para el usuario.*/
	private void showVector(){
		for(int i =0;i<renglones;i++){
			vectorpanel.add(this.valores.get(i));
		}
	}

	/** * Metodo que tiene como funcion contruir el vector logico atras ves de los datos ingresados en el vector visual.*/
	private void setVector(){
		for(int i =0;i<renglones;i++)
			vector.add(Double.parseDouble(((TextFieldNumber)valores.get(i)).getText()));
	}

	/** * Metodo que tiene como funcion validar que el vector logico visual no este vacio.*/
	private boolean getEmptyVector(){
		for(int i =0;i<renglones;i++){
			if(((TextFieldNumber)valores.get(i)).getText().equals("-"))
				return true;
		}
		return false;
	}

	/** * Metodo que tiene como funcion regresar el vector logico.*/
	public ArrayList<Double> getVector(){
		return vector;
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del
	componente que las ocasione.*/
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == aceptar){
			if(!getEmptyVector()){
				setVector();
				this.dispose();
			}
			else
				JOptionPane.showMessageDialog(null,"Porfavor llene toda el vector, no puede tener huecos vacios"
			,"Advertencia",JOptionPane.WARNING_MESSAGE);	
		}
		else if(e.getSource() == limpiar){
			for(int i =0;i<renglones;i++){
				valores.get(i).setText("-");
			}
		}
	}
}