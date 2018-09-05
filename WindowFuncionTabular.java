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

///JDialog ventana emergente 
public class WindowFuncionTabular extends JDialog implements ActionListener{
	protected final JButton aceptar  = new JButton("Aceptar");
	protected final JButton limpiar= new JButton("Limpiar");
	private JPanel panelfunciontb = new JPanel(new BorderLayout());
	private ArrayList<ArrayList<JTextField>> valores = new ArrayList<ArrayList<JTextField>>();
	private ArrayList<ArrayList<Double>> func_list = new ArrayList<ArrayList<Double>>();
	private int renglones,columnas;
	private JPanel funciontbpanel,buttonspanel;

	public WindowFuncionTabular(){}

	public WindowFuncionTabular(JFrame parent,boolean modal,int renglones){
		super(parent,"Funcion Tabular",modal);
		funciontbpanel = new JPanel(new GridLayout(renglones+1,2,10,10));
		buttonspanel = new JPanel();
		crearfunciontb(renglones+1,2);
		buttonspanel.add(aceptar);
		buttonspanel.add(limpiar);
		showfunciontb();
		panelfunciontb.add(funciontbpanel,BorderLayout.CENTER);
		panelfunciontb.add(buttonspanel,BorderLayout.SOUTH);
		aceptar.addActionListener(this);
		limpiar.addActionListener(this);
		getContentPane().add(panelfunciontb);
		setSize(250,250);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void crearfunciontb(int renglones,int columnas){
		this.renglones = renglones;
		this.columnas = columnas;
		for(int i =0;i<renglones;i++){
			this.valores.add(new ArrayList<JTextField>());
			for(int j=0;j<columnas;j++){
				if(i == 0){
					this.valores.get(0).add(new JTextField("X"));
					this.valores.get(0).get(0).setEditable(false);
					this.valores.get(0).get(0).setHorizontalAlignment(JTextField.CENTER);

					this.valores.get(0).add(new JTextField("Y"));
					this.valores.get(0).get(1).setEditable(false);
					this.valores.get(0).get(1).setHorizontalAlignment(JTextField.CENTER);
				}
				else{
					this.valores.get(i).add(new TextFieldNumber("-",Color.BLACK,1));
					this.valores.get(i).get(j).setHorizontalAlignment(JTextField.CENTER);
				}
			}
		}
	}

	private void showfunciontb(){
		for(int i =0;i<renglones;i++){
			for(int j=0;j<columnas;j++){
				funciontbpanel.add(this.valores.get(i).get(j));
			}
		}
	}

	private void setfunciontb(){
		for(int i =1;i<renglones;i++){
			func_list.add(new ArrayList<Double>());
			for(int j=0;j<columnas;j++){
				func_list.get(i-1).add(Double.parseDouble(((TextFieldNumber)(valores.get(i).get(j))).getText()));
			}
		}
	}

	private boolean getEmptyfunciontb(){
		for(int i =0;i<renglones;i++){
			for(int j=0;j<columnas;j++){
				if(i != 0)
					if(((TextFieldNumber)valores.get(i).get(j)).getText().equals("-") || ((TextFieldNumber)valores.get(i).get(j)).getText().equals("."))
						return true;
			}
		}
		return false;
	}

	public ArrayList<ArrayList<Double>> getfunciontb(){
		return func_list;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == aceptar){
			if(!getEmptyfunciontb()){
				setfunciontb();
				this.dispose();
			}
			else
				JOptionPane.showMessageDialog(null,"Porfavor llene toda la funcion tabular, no puede tener huecos vacios."
			,"Advertencia",JOptionPane.WARNING_MESSAGE);	
		}
		else if(e.getSource() == limpiar){
			for(int i =0;i<renglones;i++){
				for(int j=0;j<columnas;j++){
					if(i != 0)
						valores.get(i).get(j).setText("-");
				}
			}
		}
	}
}