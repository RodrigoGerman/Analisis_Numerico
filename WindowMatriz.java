package controlador;

import modelo.Matriz;
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

///JDialog ventana emergente 
public class WindowMatriz extends JDialog implements ActionListener{
	protected final JButton aceptar  = new JButton("Aceptar");
	protected final JButton limpiar= new JButton("Limpiar");
	private Matriz matriz = null;
	private JPanel panelmatriz = new JPanel(new BorderLayout());
	private ArrayList<ArrayList<JTextField>> valores = new ArrayList<ArrayList<JTextField>>();
	private ArrayList<ArrayList<Double>> matriz_list = new ArrayList<ArrayList<Double>>();
	private int renglones,columnas;
	private JPanel matrizpanel,buttonspanel;

	public WindowMatriz(){}

	public WindowMatriz(JFrame parent,boolean modal,int renglones,int columnas){
		super(parent,"Matriz",modal);
		matrizpanel = new JPanel(new GridLayout(renglones,columnas,10,10));
		buttonspanel = new JPanel();
		crearMatriz(renglones,columnas);
		buttonspanel.add(aceptar);
		buttonspanel.add(limpiar);
		showMatriz();
		panelmatriz.add(matrizpanel,BorderLayout.CENTER);
		panelmatriz.add(buttonspanel,BorderLayout.SOUTH);
		aceptar.addActionListener(this);
		limpiar.addActionListener(this);
		getContentPane().add(panelmatriz);
		setSize(250,250);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void crearMatriz(int renglones,int columnas){
		this.renglones = renglones;
		this.columnas = columnas;
		for(int i =0;i<renglones;i++){
			this.valores.add(new ArrayList<JTextField>());
			for(int j=0;j<columnas;j++){
				this.valores.get(i).add(new TextFieldNumber("-",Color.BLACK,1));
				this.valores.get(i).get(j).setHorizontalAlignment(JTextField.CENTER);
			}
		}
	}

	private void showMatriz(){
		for(int i =0;i<renglones;i++){
			for(int j=0;j<columnas;j++){
				matrizpanel.add(this.valores.get(i).get(j));
			}
		}
	}


	private void setMatriz(){
		for(int i =0;i<renglones;i++){
			matriz_list.add(new ArrayList<Double>());
			for(int j=0;j<columnas;j++){
				matriz_list.get(i).add(Double.parseDouble(((TextFieldNumber)(valores.get(i).get(j))).getText()));
			}
		}
		matriz = new Matriz(matriz_list);
	}

	private boolean getEmptyMatriz(){
		for(int i =0;i<renglones;i++){
			for(int j=0;j<columnas;j++){
				if(((TextFieldNumber)valores.get(i).get(j)).getText().equals("-"))
					return true;
			}
		}
		return false;
	}

	public Matriz getMatriz(){
		return matriz;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == aceptar){
			if(!getEmptyMatriz()){
				setMatriz();
				this.dispose();
			}
			else
				JOptionPane.showMessageDialog(null,"Porfavor llene toda la matriz, no puede tener huecos vacios"
			,"Advertencia",JOptionPane.WARNING_MESSAGE);	
		}
		else if(e.getSource() == limpiar){
			for(int i =0;i<columnas;i++){
				for(int j=0;j<renglones;j++){
					valores.get(i).get(j).setText("-");
				}
			}
		}
	}
}