package vista.accesories;

import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JMenuBar;

/**
    * La clase MenuBarGit es una clase que hereda de la clase JMenuBar debido a que es una barra de menú en el fondo, 
    * pero tiene un cierto comportamiento para la aplicación GIT - RGL, se encarga de crear una barra de menú
    * con un comportamiento especifico.
    * @author German López Rodrigo
    * @version 2/12/2016/Final 
*/

public class MenuBarApp extends JMenuBar{
    /** * Objeto privado de tipo Color que asigna el color a la barra de menú de la aplicación.*/
    private Color bgColor = Color.WHITE;

    /** * Método Constructor que crear un MenuBarGit sin ninguno comportamiento en especial.*/
    public MenuBarApp(){}
    
    /** * Método Constructor que recibe una distribución de posiciones y un color que se colócala a la barra de menú.*/
    public MenuBarApp(Color color){
        super();
        setColor(color);
    }

    /** * Método setColor para asignar el color a la barra de menu.*/ 
    public void setColor(Color color) {
        this.bgColor = color;
    }

    /** *Método sobrescrito paintComponent que se encarga de asignar el color que va a tener la barra de menú. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fillRect(0,0,getWidth(),getHeight());
    }
}
