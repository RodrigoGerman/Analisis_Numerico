package vista;

import vista.accesories.ButtonApp;
import vista.accesories.PanelApp;
import controlador.WindowsANRGL;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

/**
	* La clase MainWindow es una clase que se encarga de generar la primera ventana que vera el usuario al correr el programa
	* en su computadora.
	* @author German López Rodrigo
	* @version 13/05/2017/Final 
*/
public class MainWindow extends WindowsApps implements ActionListener{
	/** *Objetos privados tipo ButtonGit los cuales son dos botones que tienen un comportamiento en especifico.*/
	private ButtonApp button1,button2;

	/** *Constructor sin argumentos para crear una ventana cualquiera.*/
	public MainWindow(){}

	/** *Constructor que recibe un String que corresponden al título de la ventana a crear, le coloca un borde especial, el icono 
	de la aplicación y que se encuentre a la mitad de la pantalla del cliente. */
	public MainWindow(String title){
		super(title,350,350);

		Color letras =new Color(23,34,66);
		Color fondo = new Color(230,230,230);
		Color borde_buttons = new Color(4,36,93);

		PanelApp main = new PanelApp(null,fondo);
		main.setLogo("./Logos/RGL.png","AN - RGL",127,10,90,95);
		main.setLabel("Métodos de Análisis",letras,80,125,200,70,new Font("Times New Roman",13,20));
		main.setLabel("Numérico",letras,130,155,300,70,new Font("Times New Roman",13,20));

		button1 = new ButtonApp("Iniciar");
		button1.setBackground(fondo);
		button1.setBorder(new LineBorder(borde_buttons,1));
		main.setButton(button1,50,265,100,25);
		button1.addActionListener(this);

		button2 = new ButtonApp("Salir");
		button2.setBackground(fondo);
		button2.setBorder(new LineBorder(borde_buttons,1));
		main.setButton(button2,180,265,120,25);
		button2.addActionListener(this);

		getContentPane().add(main);
		setResizable(false);
		setVisible(true);
	}

	/** *Método sobrescrito de la interfaz ActionListener, el cual se encarga de asignar una tarea específica a los dos 
	ButtonGit que debe contener la ventana.*/
	@Override
	public void actionPerformed(ActionEvent e){
		int r=0;
		if(e.getSource() == button1){
			new WindowsANRGL("Metodos de Análisis Numérico");
			this.dispose();
		}
		else if(e.getSource() == button2)
			this.dispose();
	}
}
