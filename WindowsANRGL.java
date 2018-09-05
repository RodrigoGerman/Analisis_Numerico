package controlador;

import vista.WindowsApps;
import controlador.theme2.closedmethods.*;
import controlador.theme2.openmethods.*;
import controlador.theme3.matriz.*;
import controlador.theme3.matrizvector.*;
import controlador.theme4.tb.*;
import controlador.theme4.*;
import controlador.theme5.ed.*;
import controlador.theme5.*;
import controlador.theme6.*;
import vista.accesories.PanelTabApp;
import vista.accesories.MenuApp;
import vista.accesories.MenuBarApp;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;

/**
	* La clase WindowVector es una clase que hereda de la clase JDialog debido a que es una ventana 
	* emergente la cual tiene la funcion de registar el vector de resultados de una matriz para
	* que los metodos del tema 3 de analisis numerico se puedan resolver, se encarga de registrar
	* un vector y regresarlo a su ventana padre.
	* @author German Lopez Rodrigo
	* @version 11/04/2017/Final 
*/
public class WindowsANRGL extends WindowsApps implements ActionListener{
	/** *Variable tipo MenuBarGit, la cual posee un  estilo personalizado.*/
	MenuBarApp barra;
	/** *Variables tipo MenuGit, para mostrar los diferentes acciones que puede realizar el usuario.*/
	MenuApp tema1,tema2,tema3,tema4,tema5,tema6;
	MenuApp interpolacion,aproximacion,newton,factores,help,icono,pivoteogauss_jordan,pivoteo;
	MenuApp biseccion,lu,metododepotencias,krilov,gauss_seidel,jacobi,lagrange,interpolantes;
	MenuApp integracion,derivacion;
	MenuApp euler,euler_gauus,taylor,runge_kutta,solsed;
	MenuApp derivada_parcial;
	/** *Variables tipo JMenuItem, para mostrar las acciones que puede realizar el usuario.*/
	JMenuItem agregar_biseccion,agregar_interpolacion,agregar_aproximacion,agregar_proyecto3,agregar_proyecto4,crout;
	JMenuItem pivoteo_parcial,pivoteo_diagonal,pivoteo_completo,pivoteo_escalado,pivoteo_parcial_gj,pivoteo_diagonal_gj;
	JMenuItem agregar_jacobi,agregar_gaussseidel,agregar_krilov,agregar_potencias,pivoteo_escalado_gj,doolittle,info,inversa;
	JMenuItem agregar_Lagrange,agregar_Interpolantes,agregar_Derivacion,agregar_Integracion,agregar_Derivacion_tb,agregar_Integracion_tb;
	JMenuItem agregar_euler,agregar_eulergauus,agreagar_taylor,agregar_rungekutta,agregar_solsed;
	JMenuItem agregar_derivadaparcial;
	/** *Variable tipo JTabbedPane, para mostrar diferentes pestañas.*/
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
	/** *Variable byte para llevar conteo de proyectos de cada metodo*/
	byte cont_work_tabs = 1;
	/** *Variable tipo int que lleva la cantidad de pestañas total en el proyecto.*/
	int cantidad = 0;
	/** *Variable tipo JPanel para asiganar un panel establecido a cada metodo de una tema de analisis numerico.*/
	JPanel panel_metodo;

	/** *Constructor sin argumentos*/
	public WindowsANRGL(){}

	/** *Constructor que construye la ventana principal del programa,recibe una String que corresponder 
	 al titulo de la venta.*/
	public WindowsANRGL(String title){
		super(title,1035,660);
		addMenuBar(this);
		tabbedPane.setBackground(new Color(34, 98, 165));
		getContentPane().setBackground(new Color(235, 235, 235));
		getContentPane().add(tabbedPane);
		setVisible(true);
	}

	/** *Método para añadir las pestañas de todos los metodos del tema 2 de analisis numerico.*/
	private final void addTema2(){
		tema2 = new MenuApp("Tema 2");
		biseccion = new MenuApp("Biseccion");
		agregar_biseccion = new JMenuItem("Agregar Proyecto");
		agregar_biseccion.addActionListener(this);
		biseccion.add(agregar_biseccion);
		tema2.add(biseccion);

		interpolacion = new MenuApp("Interpolacion");
		agregar_interpolacion = new JMenuItem("Agregar Proyecto");
		agregar_interpolacion.addActionListener(this);
		interpolacion.add(agregar_interpolacion);
		tema2.add(interpolacion);

		aproximacion = new MenuApp("Aproximacion");
		agregar_aproximacion = new JMenuItem("Agregar Proyecto");
		agregar_aproximacion.addActionListener(this);
		aproximacion.add(agregar_aproximacion);
		tema2.add(aproximacion);

		newton = new MenuApp("Newton");
		agregar_proyecto3 = new JMenuItem("Agregar Proyecto");
		agregar_proyecto3.addActionListener(this);
		newton.add(agregar_proyecto3);
		tema2.add(newton);

		factores = new MenuApp("Factores");
		agregar_proyecto4 = new JMenuItem("Agregar Proyecto");
		agregar_proyecto4.addActionListener(this);
		factores.add(agregar_proyecto4);
		tema2.add(factores);
	}

	/** *Método para añadir las pestañas de todos los metodos del tema 3 de analisis numerico.*/
	private final void addTema3(){
		tema3 = new MenuApp("Tema 3");
		pivoteo = new MenuApp("Pivoteo");
		pivoteo_parcial = new JMenuItem("Pivoteo Parcial");
		pivoteo_escalado = new JMenuItem("Pivoteo Escalado");
		pivoteo_diagonal = new JMenuItem("Pivoteo Diagonal");
		pivoteo_parcial.addActionListener(this);
		pivoteo_escalado.addActionListener(this);
		pivoteo_diagonal.addActionListener(this);
		pivoteo.add(pivoteo_parcial);
		pivoteo.add(pivoteo_escalado);
		pivoteo.add(pivoteo_diagonal);
		tema3.add(pivoteo);

		pivoteogauss_jordan = new MenuApp("Pivoteo Gauss Jordan");
		pivoteo_parcial_gj = new JMenuItem("Pivoteo Parcial");
		pivoteo_escalado_gj = new JMenuItem("Pivoteo Escalado");
		pivoteo_diagonal_gj = new JMenuItem("Pivoteo Diagonal");
		pivoteo_completo = new JMenuItem("Pivoteo Completo");
		pivoteo_parcial_gj.addActionListener(this);
		pivoteo_escalado_gj.addActionListener(this);
		pivoteo_diagonal_gj.addActionListener(this);
		pivoteo_completo.addActionListener(this);
		pivoteogauss_jordan.add(pivoteo_parcial_gj);
		pivoteogauss_jordan.add(pivoteo_escalado_gj);
		pivoteogauss_jordan.add(pivoteo_diagonal_gj);
		pivoteogauss_jordan.add(pivoteo_completo);
		tema3.add(pivoteogauss_jordan);

		lu = new MenuApp("Descomposicion LU");
		crout = new JMenuItem("Crout");
		doolittle = new JMenuItem("Doolittle");
		inversa = new JMenuItem("Matriz Inversa");
		crout.addActionListener(this);
		doolittle.addActionListener(this);
		inversa.addActionListener(this);
		lu.add(crout);
		lu.add(doolittle);
		lu.add(inversa);
		tema3.add(lu);

		jacobi = new MenuApp("Jacobi");
		agregar_jacobi = new JMenuItem("Agregar Proyecto");
		agregar_jacobi.addActionListener(this);
		jacobi.add(agregar_jacobi);
		tema3.add(jacobi);

		gauss_seidel = new MenuApp("Gauss Seidel");
		agregar_gaussseidel = new JMenuItem("Agregar Proyecto");
		agregar_gaussseidel.addActionListener(this);
		gauss_seidel.add(agregar_gaussseidel);
		tema3.add(gauss_seidel);


		krilov = new MenuApp("Krilov");
		agregar_krilov = new JMenuItem("Agregar Proyecto");
		agregar_krilov.addActionListener(this);
		krilov.add(agregar_krilov);
		tema3.add(krilov);

		metododepotencias = new MenuApp("Método Potencias");
		agregar_potencias = new JMenuItem("Agregar Proyecto");
		agregar_potencias.addActionListener(this);
		metododepotencias.add(agregar_potencias);
		tema3.add(metododepotencias);
	}

	/** *Método para añadir las pestañas de todos los metodos del tema 4 de analisis numerico.*/
	private final void addTema4(){
		tema4 = new MenuApp("Tema 4");
		lagrange = new MenuApp("Polinomio Lagrange");
		agregar_Lagrange = new JMenuItem("Agregar Proyecto");
		agregar_Lagrange.addActionListener(this);
		lagrange.add(agregar_Lagrange);
		tema4.add(lagrange);

		interpolantes = new MenuApp("Polinomios Interpolantes");
		agregar_Interpolantes = new JMenuItem("Agregar Proyecto");
		agregar_Interpolantes.addActionListener(this);
		interpolantes.add(agregar_Interpolantes);
		tema4.add(interpolantes);


		derivacion = new MenuApp("Derivacion Numerica");
		agregar_Derivacion_tb = new JMenuItem("Agregar Proyecto Tb");
		agregar_Derivacion = new JMenuItem("Agregar Proyecto");
		agregar_Derivacion_tb.addActionListener(this);
		agregar_Derivacion.addActionListener(this);
		derivacion.add(agregar_Derivacion_tb);
		derivacion.add(agregar_Derivacion);
		tema4.add(derivacion);


		integracion = new MenuApp("Integracion Numerica");
		agregar_Integracion_tb = new JMenuItem("Agregar Proyecto Tb");
		agregar_Integracion = new JMenuItem("Agregar Proyecto");
		agregar_Integracion_tb.addActionListener(this);
		agregar_Integracion.addActionListener(this);
		integracion.add(agregar_Integracion_tb);
		integracion.add(agregar_Integracion);
		tema4.add(integracion);
	}

	/** *Método para añadir las pestañas de todos los metodos del tema 5 de analisis numerico.*/
	private final void addTema5(){
		tema5 = new MenuApp("Tema 5");
		euler = new MenuApp("Euler");
		agregar_euler = new JMenuItem("Agregar Proyecto");
		agregar_euler.addActionListener(this);
		euler.add(agregar_euler);
		tema5.add(euler);

		euler_gauus = new MenuApp("Euler- Gauss");
		agregar_eulergauus = new JMenuItem("Agregar Proyecto");
		agregar_eulergauus.addActionListener(this);
		euler_gauus.add(agregar_eulergauus);
		tema5.add(euler_gauus);


		taylor = new MenuApp("Taylor");
		agreagar_taylor = new JMenuItem("Agregar Proyecto");
		agreagar_taylor.addActionListener(this);
		taylor.add(agreagar_taylor);
		tema5.add(taylor);


		runge_kutta = new MenuApp("Runge-Kutta");
		agregar_rungekutta = new JMenuItem("Agregar Proyecto");
		agregar_rungekutta.addActionListener(this);
		runge_kutta.add(agregar_rungekutta);
		tema5.add(runge_kutta);


		solsed = new MenuApp("Sistema ED");
		agregar_solsed = new JMenuItem("Agregar Proyecto");
		agregar_solsed.addActionListener(this);
		solsed.add(agregar_solsed);
		tema5.add(solsed);
	}

	/** *Método para añadir las pestañas de todos los metodos del tema 6 de analisis numerico.*/
	private final void addTema6(){
		tema6 = new MenuApp("Tema 6");
		derivada_parcial = new MenuApp("Derivacion Parcial");
		agregar_derivadaparcial = new JMenuItem("Agregar Proyecto");
		agregar_derivadaparcial.addActionListener(this);
		derivada_parcial.add(agregar_derivadaparcial);
		tema6.add(derivada_parcial);
	}

	/** * Método para añadir una barra de menu a la ventana con datos establecidos.*/
	private final void addMenuBar(JFrame ventana){
		barra = new MenuBarApp(new Color(34, 98, 165));
		ventana.setJMenuBar(barra);

		icono = new MenuApp("","./Logos/RGL-Mini.png");

		addTema2();
		addTema3();
		addTema4();
		addTema5();
		addTema6();

		help = new MenuApp("Ayuda");
		info = new JMenuItem("Informacion");
		help.add(info);

		barra.add(icono);
		//barra.add(tema1);
		barra.add(tema2);
		barra.add(tema3);
		barra.add(tema4);
		barra.add(tema5);
		barra.add(tema6);
		barra.add(Box.createHorizontalGlue());
		barra.add(help);
	}

	/** * Método para darle una accion pedeterminada a cada metodo del menu del tema 2 de analisis numerico.*/
	private void actionPerformedTema2(Object campo){
		String titulo="";

		if(campo == agregar_biseccion){
			titulo = "Biseccion " + cont_work_tabs++;
			panel_metodo = new PanelBiseccion();
		}

		else if(campo == agregar_interpolacion){
			titulo = "Interpolacion " + cont_work_tabs++;
			panel_metodo = new PanelInterpolacionLineal();
		}

		else if(campo == agregar_aproximacion){
			titulo = "Aproximacion " + cont_work_tabs++;
			panel_metodo = new PanelAproxSucesivas();
		}

		else if(campo == agregar_proyecto3){
			titulo = "Newton " + cont_work_tabs++;
			panel_metodo = new PanelNewthonRaphson();
		}

		else if(campo == agregar_proyecto4){
			titulo = "Factores Cuadraticos " + cont_work_tabs++;
			panel_metodo = new PanelFactoresCuadraticos(new Color(76, 123, 171));

		}

		if(!titulo.equals("")){
			tabbedPane.addTab(titulo,panel_metodo);
			tabbedPane.setTabComponentAt(cantidad+1,new PanelTabApp(tabbedPane));
		}
	}

	/** * Método para darle una accion pedeterminada a cada metodo del menu del tema 3 de analisis numerico.*/
	private void actionPerformedTema3(Object campo){
		String titulo="";

		if(campo == pivoteo_parcial){
			titulo = "Pivoteo Parcial " + cont_work_tabs++;
			panel_metodo = new PanelPivoteo(this,"Parcial");
		}

		else if(campo == pivoteo_escalado){
			titulo = "Pivoteo Escalado " + cont_work_tabs++;
			panel_metodo = new PanelPivoteo(this,"Parcial Escalado");
		}

		else if(campo == pivoteo_diagonal){
			titulo = "Pivoteo Diagonal " + cont_work_tabs++;
			panel_metodo = new PanelPivoteo(this,"Parcial Diagonal");
		}

		else if(campo == crout){
			titulo = "Descomposición LU Crout " + cont_work_tabs++;
			panel_metodo = new PanelLU(this,"Crout");
		}

		else if(campo == doolittle){
			titulo = "Descomposición LU Doolittle " + cont_work_tabs++;
			panel_metodo = new PanelLU(this,"Doolittle");
		}

		else if(campo == agregar_jacobi){
			titulo = "Método de Jacobi " + cont_work_tabs++;
			panel_metodo = new PanelJacobi(this);
		}

		else if(campo == agregar_gaussseidel){
			titulo = "Método de Gauss Seidel " + cont_work_tabs++;
			panel_metodo = new PanelGaussSeidel(this);
		}

		else if(campo == pivoteo_completo){
			titulo = "Pivoteo Completo " + cont_work_tabs++;
			panel_metodo = new PanelPivoteoGJ(this,"Completo");
		}

		else if(campo == pivoteo_parcial_gj){
			titulo = "Pivoteo Parcial GJ " + cont_work_tabs++;
			panel_metodo = new PanelPivoteoGJ(this,"Parcial");
		}

		else if(campo == pivoteo_escalado_gj){
			titulo = "Pivoteo Parcial Escalado GJ " + cont_work_tabs++;
			panel_metodo = new PanelPivoteoGJ(this,"Parcial Escalado");
		}

		else if(campo == pivoteo_diagonal_gj){
			titulo = "Pivoteo Diagonal " + cont_work_tabs++;
			panel_metodo = new PanelPivoteoGJ(this,"Parcial Diagonal");
		}

		else if(campo == agregar_krilov){
			titulo = "Krilov " + cont_work_tabs++;
			panel_metodo = new PanelKrilov(this);
		}

		else if(campo == agregar_potencias){
			titulo = "Potencias " + cont_work_tabs++;
			panel_metodo = new PanelMetodoPotencias(this);
		}

		else if(campo == inversa){
			titulo = "Inversa " + cont_work_tabs++;
			panel_metodo = new PanelInversaLu(this);
		}

		if(!titulo.equals("")){
			tabbedPane.addTab(titulo,panel_metodo);
			tabbedPane.setTabComponentAt(cantidad+1,new PanelTabApp(tabbedPane));
		}
	}

	/** * Método para darle una accion pedeterminada a cada metodo del menu del tema 4.*/
	private void actionPerformedTema4(Object campo){
		String titulo="";

		if(campo == agregar_Lagrange){
			titulo = "Polinomio Lagrange" + cont_work_tabs++;
			panel_metodo = new PanelLagrange(this);
		}

		else if(campo == agregar_Interpolantes){
			titulo = "Polinomio Interpolante " + cont_work_tabs++;
			panel_metodo = new PanelInterpolantes(this);
		}

		else if(campo == agregar_Derivacion){
			titulo = "Derivacion Numerica " + cont_work_tabs++;
			panel_metodo = new PanelDerivacion(new Color(34, 98, 165),"Derivacion Numerica");
		}

		else if(campo == agregar_Derivacion_tb){
			titulo = "Derivacion Numerica Tabular " + cont_work_tabs++;
			panel_metodo = new PanelDerivacionTB(this,"Derivacion Numerica");
		}

		else if(campo == agregar_Integracion){
			titulo = "Integracion Numerica " + cont_work_tabs++;
			panel_metodo = new PanelIntegracion(new Color(34, 98, 165),"Integracion Numerica");
		}

		else if(campo == agregar_Integracion_tb){
			titulo = "Integracion Numerica Tabular " + cont_work_tabs++;
			panel_metodo = new PanelIntegracionTB(this,"Integracion Numerica");
		}

		if(!titulo.equals("")){
			tabbedPane.addTab(titulo,panel_metodo);
			tabbedPane.setTabComponentAt(cantidad+1,new PanelTabApp(tabbedPane));
		}
	}

	/** * Método para darle una accion pedeterminada a cada metodo del menu del tema 5.*/
	private void actionPerformedTema5(Object campo){
		String titulo="";

		if(campo == agregar_euler){
			titulo = "Euler ED " + cont_work_tabs++;
			panel_metodo = new PanelEuler(new Color(34, 98, 165));
		}

		else if(campo == agregar_eulergauus){
			titulo = "Euler-Gauss ED " + cont_work_tabs++;
			panel_metodo = new PanelEulerGauss(new Color(34, 98, 165));
		}

		else if(campo == agreagar_taylor){
			titulo = "Taylor ED " + cont_work_tabs++;
			panel_metodo = new PanelTaylor(new Color(34, 98, 165));
		}

		else if(campo == agregar_rungekutta){
			titulo = "Runge-Kutta ED " + cont_work_tabs++;
			panel_metodo = new PanelRungeKutta(new Color(34, 98, 165));
		}

		else if(campo == agregar_solsed){
			titulo = "Sistema ED " + cont_work_tabs++;
			panel_metodo = new PanelMethodSEDN(new Color(34, 98, 165));
		}

		if(!titulo.equals("")){
			tabbedPane.addTab(titulo,panel_metodo);
			tabbedPane.setTabComponentAt(cantidad+1,new PanelTabApp(tabbedPane));
		}
	}

	/** * Método para darle una accion pedeterminada a cada metodo del menu del tema 6.*/
	private void actionPerformedTema6(Object campo){
		String titulo="";

		if(campo == agregar_derivadaparcial){
			titulo = "Derivacion Parcial " + cont_work_tabs++;
			panel_metodo = new PanelDerivacionParcial(new Color(34, 98, 165));
		}

		if(!titulo.equals("")){
			tabbedPane.addTab(titulo,panel_metodo);
			tabbedPane.setTabComponentAt(cantidad+1,new PanelTabApp(tabbedPane));
		}
	}

	/** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del
	componente que las ocasione.*/
	@Override
	public void actionPerformed(ActionEvent e){
		Object campo = e.getSource();
		JPopupMenu jpm = (JPopupMenu)((MenuApp)((JPopupMenu)((JMenuItem)e.getSource()).getParent()).getInvoker()).getParent();
		MenuApp menu = (MenuApp)jpm.getInvoker(); 
		cantidad = tabbedPane.getTabCount() - 1;

		if(cantidad < 9){
			if(menu == tema2)
				actionPerformedTema2(campo);
			else if(menu == tema3)
				actionPerformedTema3(campo);
			else if(menu == tema4)
				actionPerformedTema4(campo);
			else if(menu == tema5)
				actionPerformedTema5(campo);
			else
				actionPerformedTema6(campo);
		}
		else
			JOptionPane.showMessageDialog(null,"Advertencia. \nUstede tiene  10 proyectos activos porfavor cierre alguno de ellos."
			,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
    }
}