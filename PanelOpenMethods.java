package vista.panels.theme2;

import vista.accesories.TextFieldNumber;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class PanelOpenMethods extends PanelMethodsTheme2{

    protected final TextFieldNumber valor_aprox = new TextFieldNumber(" Valor Aproximado",Color.BLACK,1);
    
	
	public PanelOpenMethods(){}

	public PanelOpenMethods(Color fondo,String metodo){
		super(fondo,metodo);
	}

	protected boolean getEmptyFields(){
		if((funcion.getText()).equals(" Función")){
			JOptionPane.showMessageDialog(null,"Error. \nIngrese una Función por favor."
			,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if((estimacion.getText()).equals(" Estimación")){
			JOptionPane.showMessageDialog(null,"Error. \nIngrese un error de estimación por favor."
			,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}


	protected boolean getFieldsValids(){
		if(!funcion.getCorrectFuncion()){
			JOptionPane.showMessageDialog(null,"Error. \nSu funcion tiene errores sintacticos revisela por favor."
			,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(!estimacion.getCorrectNumber()){
			JOptionPane.showMessageDialog(null,"Error. \nIngrese una estimacion valida por favor."
			,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
