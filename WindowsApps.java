package vista;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public abstract class WindowsApps extends JFrame{
	/** *Constructor sin argumentos para crear una ventana cualquiera.*/
	public WindowsApps(){}

	/** *Contructor que recibe dos enteros que correponden al tamaño de la ventana, le colaca un borde especial, el icono 
	de la aplicacionn GIT - RGL y que se encuentre a la mitad de la pantalla del cliente.*/
	public WindowsApps(String title,int x,int y){
		super(title);
		setBorder();
		setIconGit();
		setSize(x,y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	/** *Metodo setBorder para colocar un estilo tipo MetalLookAndFeel a la ventana.*/
	private final void setBorder(){
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}catch(Exception e){
			System.out.println("LookAndFeel not supported");
		}
	}

	/** *Metodo que coloca el icono de la aplicacion GIT - RGL .*/
	private final void setIconGit(){
		ImageIcon ii = new ImageIcon("./Logos/RGL.png");
		setIconImage(ii.getImage());
	}
}