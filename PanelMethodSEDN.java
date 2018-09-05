package controlador.theme5;

import vista.panels.theme5.PanelMethodsTheme5;
import vista.accesories.TextFieldNumber;
import vista.accesories.TextFieldString;
import modelo.theme5.SolucionSistemaEDN;

import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
    * La clase PanelDerivacion es una clase que hereda de PanelMethodsTheme debido a que es un panel de metodo de analisis numerico y implementa
    * la interfaz ActionListener para ejecutar el metodo numerico de Derivacion Numerica en una funcion tabular, se encarga  de crear un panel especifico para la visualizacion
    * del metodo numerico de Derivacion Numerica en una funcion tabular .
    * @author German Lopez Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelMethodSEDN extends PanelMethodsTheme5 implements ActionListener{
    /** * Variable tipo JButton privada y final, que representa un boton que se encarga de agregar los datos de las ecuaciones diferenciales cuando es presionado.*/
    private final JButton agregar = new JButton("Agregar");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el Numero de variables del sistema de ecuaciones diferenciales en el panel.*/ 
    private final JLabel titulo_variables = new JLabel("Num. de variables SED:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el metodo en el panel.*/ 
    private final JLabel titulo_metodo = new JLabel("Metodo:");
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el numero de variables que componen el sistema de ecuaciones.*/
    private final TextFieldNumber variables = new TextFieldNumber(" variables",Color.BLACK,2);
    /** * Variable tipo TextFieldString privada y final, que representa el campo para que el usuario pueda introducir el metodo a usar.*/
    private final TextFieldString metodo = new TextFieldString(" Metodo",Color.BLACK);
    /** * Variable tipo privada ArrayList<String> la cual almacena las ecuaciones diferenciales del sistema de ecuaciones.*/
    private ArrayList<String> funciones = new ArrayList<String>();
    /** * Variable tipo privada ArrayList<Double> la cual almacena los valores evaluados de cada una de las ecuaciones diferenciales del sistema.*/
    private ArrayList<Double> valores_evaluados = new ArrayList<Double>();
    /** * Variable tipo privada ArrayList<Double> la cual almacena los valores iniciales de cada una de las ecuaciones diferenciales del sistema.*/
    private ArrayList<Double> valores_iniciales = new ArrayList<Double>();
    /** * Variable tipo privada SolucionSistemaEDN que representa el metodo de Solucion de Sistemas de ecuaciones diferenciales.*/
    private SolucionSistemaEDN sistema = null;

    /** * Metodo constructor que crear un PanelMethodSEDN pero sin ninguna comportamiento ni estilo especifico.*/
    public PanelMethodSEDN(){}

    /** * Metodo constructor que crea un PanelMethodSEDN, recibe el color del fondo para el panel.*/
    public PanelMethodSEDN(Color fondo){
        super(fondo,"Solucion Sistemas ED");
        titulo_n = new JLabel("Num. de Div:");
        setPanelDatos();
        setInformacion();
        setAllElements();
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
        agregar.addActionListener(this);
    }

    /** * Método final heredado de PanelMethodsTheme5 que se encarga de validar si los campos a llenar en el PanelMethodSEDN no estan vacios.*/ 
    protected final boolean getEmptyFields(){

        if(funciones.size() < 1){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese mas de una Ecuación Diferencial por favor para resolver el sistema."
            ,"Error - Ecuación Diferencial",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((x.getText()).equals(" x") || (x.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una x a buscar valida por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((h.getText()).equals(" h") || (h.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido ningun espasiamento por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((n.getText()).equals(" Diviciones")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido el numero de Diviciones por favor introduzcaras."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if(valores_evaluados.size() == 0){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido ningun valor inicial por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if(valores_iniciales.size() == 0){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido ningun valor inicial por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((metodo.getText()).equals(" Metodo")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido ningun metodo, por favor introduzca uno."
            ,"Advertencia - Metodo",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((variables.getText()).equals(" variables")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido el numero de variables por favor introduzcalo."
            ,"Advertencia - Metodo",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }

    /** * Método final heredado de PanelMethodsTheme5 que se encarga de validar si los campos a llenar en el PanelMethodSEDN no estan vacios respecto a los datos de la ecuacion diferencial.*/ 
    protected final boolean getEmptyFields2(){

        if((funcion.getText()).equals(" Ecuación Diferencial")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una Ecuación Diferencial por favor."
            ,"Error - Ecuación Diferencial",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((y.getText()).equals(" y") || (y.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido ningun valor inicial por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((val.getText()).equals(" x") || (val.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introduzido ningun valor inicial por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }
    /** * Método final heredado de PanelMethodsTheme5 que se encarga de validar si los campos a llenar en el PanelMethodSEDN son validos.*/
    protected final boolean getFieldsValids(){

        if(funciones.size() != valores_iniciales.size() && funciones.size() != valores_evaluados.size()){
            JOptionPane.showMessageDialog(null,"Error. \nEl sistema no se puede resolver debido a que faltan valores iniciales."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!x.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido para x por favor."
            ,"Error - X",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!h.getCorrectNumber() || Double.parseDouble(h.getText()) == 0){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido en el intervalo h por favor."
            ,"Error - Espasiamento",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(!n.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido de diviciones por favor."
            ,"Error - Diviciones",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!variables.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido de variables por favor."
            ,"Error - Diviciones",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(valores_evaluados.size() != valores_iniciales.size()){
            JOptionPane.showMessageDialog(null,"Error. \nLos valores iniciales no concuerda intentelo de nuevo por favor."
            ,"Error - Valor inicial",JOptionPane.ERROR_MESSAGE);
            return false;
        }
                
        if(!(metodo.getText()).equals("euler") && !(metodo.getText()).equals("eulergauss") && !(metodo.getText()).equals("taylor") && !(metodo.getText()).equals("rungekutta")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un metodo valido por favor.\nEjemplo: euler,eulergauss,taylor o rungekutta."
            ,"Error - Metodo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Método final heredado de PanelMethodsTheme5 que se encarga de validar si los campos a llenar en el PanelMethodSEDN son validos respecto a los datos de la ecuacion diferencial.*/
    protected final boolean getFieldsValids2(){

        if(!funcion.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu funcion tiene errores sintacticos revisela por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!val.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido en el valor incial x por favor."
            ,"Error - Valor inicial",JOptionPane.ERROR_MESSAGE);
            return false;
        }
                
        if(!y.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido en el valor incial y por favor."
            ,"Error - Valor inicial",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Metodo final que hereda de PanelMethodsTheme5 que se encarga de controlar la distribucion de los compontes visuales del PanelMethodSEDN.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);
        titulo_h.setFont(titulo); 
        titulo_h.setForeground(titulos);
        titulo_x.setFont(titulo); 
        titulo_x.setForeground(titulos);
        titulo_n.setFont(titulo); 
        titulo_n.setForeground(titulos);
        titulo_condiciones.setFont(titulo); 
        titulo_condiciones.setForeground(titulos);
        titulo_y.setFont(titulo); 
        titulo_y.setForeground(titulos);
        titulo_val.setFont(titulo); 
        titulo_val.setForeground(titulos);
        titulo_metodo.setFont(titulo); 
        titulo_metodo.setForeground(titulos);

        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        agregar.setBackground(titulos);
        agregar.setBorder(BorderFactory.createLineBorder(fondo,2));

        funcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        h.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        n.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        y.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        val.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        variables.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);

        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_funcion))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_metodo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_n)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(n, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_x)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(x, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_h)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(h, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(agregar,GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_y)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(y, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(titulo_val)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(val, GroupLayout.PREFERRED_SIZE,50, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(funcion)
                        .addContainerGap())
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_condiciones)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_variables, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(variables, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_variables)
                    .addComponent(variables, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo_condiciones)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(titulo_y)
                    .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(y, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addComponent(titulo_val)
                        .addComponent(val, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                .addGap(11,11,11)
                .addComponent(agregar,GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(titulo_h)
                    .addComponent(h, GroupLayout.PREFERRED_SIZE,22, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(titulo_x)
                    .addComponent(x, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(titulo_n)
                    .addComponent(n, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(titulo_metodo)
                    .addComponent(metodo, GroupLayout.PREFERRED_SIZE,24, GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de informacion sobre el metodo de solución de sistema de Ecuaciónes diferenciales en el PanelMethodSEDN.*/
    private final void setInformacion(){
        info = "<html><body><h2 COLOR = red ALIGN = center>Solucio&#769;n nume&#769;rica de sistemas de ecuaciones diferenciales</h2>"
        +"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
        +"<p>1. Introduccio&#769;n</p><p>La sencillez de los me&#769;todos nume&#769;ricos para resolver ecuaciones diferenciales no ser&#769;&#305;a de utilidad si "
        +"no hubiere la forma de resolver ecuaciones diferenciales de orden superior a uno. Dentro de la teor&#769;&#305;a de la solucio&#769;n de ecuaciones diferenciales "
        +"se dispone de la herramienta que permite utilizar a losme&#769;todos de Euler, a los de Runge-Kutta y dema&#769;s en problemas ma&#769;s reales.</p>"
        +"<p>2. Solucio&#769;n de sistemas de ecuaciones diferenciales con condiciones iniciales</p><p>El punto de partida para lograr potencializar los me&#769;todos "
        +"nume&#769;ricos para resolver ecuaciones diferenciales consiste en resolver sistemas de ecuaciones diferenciales. El requisito inicial es que la ecuaciones "
        +"diferenciales que componen el sistema sean todas de primer orden.</p><p>En general, un sistema de ecuaciones diferenciales (en este caso de dos ecuaciones) "
        +"tendra&#769; la siguiente forma [1]:</p><p>Y<sup>'</sup>; = f(X,Y,Z)</p><p>Z<sup>'</sup>; = f(X,Y,Z) (1)</p><p>En necesario conocer las condiciones iniciales de cada "
        +"ecuacio&#769;n: Y (X0) y Z(X0). Adicionalmente,debera&#769; establecerse un intervalo solucio&#769;n que inicie en X0 y el taman&#771;o del paso h.</p>"
        +"<p>Cualquiera de los me&#769;todos conocidos puede utilizarse sin mayores requerimientos. Ilustrando loanterior se resolvera&#769; el siguiente sistema con el "
        +"me&#769;todo de Euler-Gauss, aclara&#769;ndose que cualquierade los me&#769;todos analizados puede utilizarse.</p><p>Sea el sistema de ecuaciones diferenciales "
        +"de primer orden:</p><p>Y<sup>'</sup>; = 4X &#8722; Y + 1</p><p>Z<sup>'</sup>; = 2Z &#8722; Y</p><p>Con condiciones iniciales Y (0) = 1 y Z(0) = 1 en el intervalo [0, 0,5] "
        +"con un paso h = 0,1.</p><p>De acuerdo a las ecuaciones recursivas del me&#769;todo Euler-Gauss:</p><p>Y (Xi+1p) = Y (Xi) + h f(Xi) (2)</p><p>Y (Xi+1c) = Y (Xi) +h</p>"
        +"<p>2[f(Xi, Yi) + f(Xi+1, Yi+1p)] (3)</p><p>Para i = 0, 1, 2, ...</p><p>Las ecuaciones recursivas del sistema son:</p><p>Yi+1p = Yi + h(4Xi &#8722; Yi + 1)</p>"
        +"<p>Zi+1p = zi + h(2Zi &#8722; Yi)</p><p>Yi+1c = Yi +h2 [(4Xi &#8722; Yi + 1) + (4Xi+1 &#8722; Yi+1p + 1)]</p><p>Zi+1c = Zi +h2 [(2Zi &#8722; Yi) + (2Zi+1p &#8722; Yi+1c)]</p>"
        +"<p>La primera iteracio&#769;n cuando i = 0:</p><p>Y1p = Y0 + h(4X0 &#8722; Y0 + 1)</p><p>Z1p = Z0 + h(2Z0 &#8722; Y0)</p><p>Y1c = Y0 +h2 [(4X0 &#8722; Y0 + 1) + (4X1 &#8722; Y1p + 1)]</p>"
        +"<p>Z1c = Z0 +h2 [(2Z0 &#8722; Y0) + (2Z1p &#8722; Y1c)]</p><p>Sustituyendo valores:</p><p>La primera iteracio&#769;n cuando i = 0:</p><p>Y1p = 1 + (0,1)[4(0)&#8722; 1 + 1] = 1</p>"
        +"<p>Z1p = 1 + (0,1) &#8727; [2(1)&#8722; 1] = 1,1</p><p>Y1c = 1 +0,12 [4(0)&#8722; (1) + 1) + 4(0,1)&#8722; 1 + 1)] = 1,02</p><p>Z1c = 1 +0,12 [2(0)&#8722; (1) + 2(1,1)&#8722; 1,02] = 1,109</p>"
        +"<p>Y2p = Y1 + h(4X1 &#8722; Y1 + 1)</p><p>Z2p = Z1 + h(2Z1 &#8722; Y1)</p><p>Y2c = Y1 +h2 [(4X1 &#8722; Y1 + 1) + (4X2 &#8722; Y2p + 1)]</p><p>Z2c = Z1 +h2 [(2Z1 &#8722; Y1) + (2Z2p &#8722; Y2c)]</p>"
        +"<p>Sustituyendo valores:</p><p>Y2p = 1,02 + (0,1)[4(0,1)&#8722; 1,02 + 1] = 1,058</p><p>Z2p = 1,1 + (0,1)[2(1,109&#8722; 1,02] = 1,21288</p><p>Y2c = 1,02 +0,12 [4(0,1)&#8722; (1,02) + 1 + 4(0,2)&#8722; 1,058 + 1)] = 1,0761</p>"
        +"<p>Z2c = 1,1 +0,12 [2(0,1)&#8722; (1,02) + 2(1,0761)&#8722; 1,0761] = 1,23798</p>"
        +"<p>Como puede observarse el punto cr&#769;&#305;tico en la solucio&#769;n de un sistema de ecuaciones es direccionar correctamente los puntos i y los i+1. Esta recomendacio&#769;n es va&#769;lida "
        +"para cualquiera de los me&#769;todos que se desee usar.</p><br><br></P></body></html>";
        informacion.setInfo(info);
    }

    /** * Método privado setSolucion que se encarga de generar la solucion del metodo de solución de sistema de Ecuaciónes diferenciales apartir de los datos proporcionados en los componentes del PanelMethodSEDN.*/
    private final void setSolucion(){
        String metodo = this.metodo.getText();

        if(metodo.equals("rungekutta")){
            if(Integer.parseInt(n.getText().trim()) != 4 && Integer.parseInt(n.getText().trim()) != 2 && Integer.parseInt(n.getText().trim()) != 1)
                JOptionPane.showMessageDialog(null,"Error. \nIngrese un grado valido de Runge-Kutta,los cuales pueden ser 1,2 ó 4."
                ,"Error - Espasiamento",JOptionPane.ERROR_MESSAGE);
            else
                sistema = new SolucionSistemaEDN(funciones,valores_evaluados,valores_iniciales,Integer.parseInt(n.getText().trim())
                ,Double.parseDouble(h.getText().trim()),Double.parseDouble(x.getText().trim()),metodo);
        }
        else
            sistema = new SolucionSistemaEDN(funciones,valores_evaluados,valores_iniciales,Integer.parseInt(n.getText().trim())
                ,Double.parseDouble(h.getText().trim()),Double.parseDouble(x.getText().trim()),metodo);
    }

    /** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == agregar){
            if(!getEmptyFields2() && getFieldsValids2()){
                funciones.add((funcion.getText()).trim());
                valores_iniciales.add(Double.parseDouble(y.getText().trim()));
                valores_evaluados.add(Double.parseDouble(val.getText().trim()));
                funcion.setText(" Ecuación Diferencial");
                y.setText(" y");
                val.setText(" x");
            }
        }

        if(e.getSource() == calcular){
            if(!getEmptyFields() && getFieldsValids()){
                if(Integer.parseInt(variables.getText()) == funciones.size()){
                    soluciones.setClear("");
                    iteraciones.setClear("");
                    setSolucion();
                    if(sistema != null){
                        soluciones.setText("Solucion:\n"+sistema.getSolucion());
                        iteraciones.setText(sistema.getIteraciones());
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Error. \nEl numero de variables no concuerda con el numero de funciones ingresadas."
                ,"Error - X",JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(e.getSource() == limpiar){
            funcion.setText(" Ecuación Diferencial");
            h.setText(" h");
            n.setText(" Diviciones");
            y.setText(" y");
            val.setText(" x");
            x.setText(" x");
            metodo.setText(" Metodo");
            variables.setText(" variables");
            funciones.clear();
            valores_iniciales.clear();
            valores_evaluados.clear();
            soluciones.setClear("");
            iteraciones.setClear("");
            sistema = null;
        }
    }
}