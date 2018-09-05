package controlador.theme4;

import vista.panels.theme4.PanelMethodsTheme4;
import modelo.theme4.IntegracionNumerica;
import vista.accesories.TextFieldNumber;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;

/**
    * La clase PanelIntegracion es una clase que hereda de PanelMethodsTheme4 debido a que es un panel de método de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Integración Numérica, se encarga  de crear un panel específico para la visualización
    * del método numérico de Integración Numérica.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelIntegracion extends PanelMethodsTheme4 implements ActionListener{
    /** * Variable tipo JLabel privada y final, que representa el título para el intervalo mostrado en el panel. */ 
    private final JLabel titulo_n = new JLabel("número de Divisiones:");
    /** * Variable tipo JLabel privada y final, que representa el título para el intervalo en el panel.*/ 
    private final JLabel titulo_intervalo = new JLabel("Intervalo:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de ha mostrado en el panel. */ 
    private final JLabel titulo_a = new JLabel("a:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de b mostrado en el panel. */ 
    private final JLabel titulo_b = new JLabel("b:");
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de a del intervalo.*/
    private final TextFieldNumber a = new TextFieldNumber(" a",Color.BLACK,1);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de b del intervalo.*/
    private final TextFieldNumber b = new TextFieldNumber(" b",Color.BLACK,1);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del número de divisiones.*/
    private final TextFieldNumber n = new TextFieldNumber(" Diviciones",Color.BLACK,1);
    /** * Variable tipo privada IntegracionNumerica que representa el método de Integración Numérica.*/
    private IntegracionNumerica integracion = null;


    /** * Método constructor que crear un PanelIntegracion pero sin ninguna comportamiento ni estilo especifico.*/
    public PanelIntegracion(){}

    /** * Método constructor que crea un PanelIntegracion, recibe el color del fondo para el panel y una String que representa el método que va a contener.*/
    public PanelIntegracion(Color fondo,String método){
        super(new Color(76, 123, 171),método);
        setInformacion();
        setPanelDatos();
        setAllElements();
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    /** * Método final heredado de PanelMethodsTheme4 que se encarga de validar si los campos a llenar en el PanelIntegracion no están vacíos.*/ 
    protected final boolean getEmptyFields(){

        if((funcion.getText()).equals(" Función")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una Función por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if((a.getText()).equals(" a") || (b.getText()).equals(" b")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un intervalo por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if((metodo.getText()).equals(" Método")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún método, se utilizara uno por defecto."
            ,"Advertencia - Metodo",JOptionPane.WARNING_MESSAGE);
        }

        if((n.getText()).equals(" Divisiones")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido el número de Divisiones, se utilizara uno por defecto."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    /** * Método final heredado de PanelMethodsTheme4 que se encarga de validar si los campos a llenar en el PanelIntegracion son validos.*/ 
    protected final boolean getFieldsValids(){

        if(!funcion.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu función tiene errores sintácticos revisela por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!a.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un número valido para a por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!b.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un número valido para b por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!n.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un número valido de divisiones por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!(metodo.getText()).equals("1/2") && !(metodo.getText()).equals("1/3") && !(metodo.getText()).equals("3/8") && !(metodo.getText()).equals(" Método") && !(metodo.getText()).equals("general")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un método valido por favor.\nEjemplo: 1/2,1/3,3/8 o general."
            ,"Error - Metodo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(Double.parseDouble(a.getText()) == Double.parseDouble(b.getText())){
             JOptionPane.showMessageDialog(null,"Error. \nIngrese un intervalo valido por favor."
            ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Método privado setSolucion que se encarga de generar la solución del método de integración numérica a partir de los datos proporcionados en los componentes del PanelIntegracion.*/
    private final void setSolucion(){
        String func = (funcion.getText()).trim();
        double a = Double.parseDouble(this.a.getText().trim());
        double b = Double.parseDouble(this.b.getText().trim());
        String n = this.n.getText().trim();
        String metodo = this.metodo.getText().trim();

        if(n.equals("Divisiones") && metodo.equals("Método"))
            integracion = new IntegracionNumerica(func,a,b);

        else if(n.equals("Divisiones"))
            integracion = new IntegracionNumerica(func,a,b,metodo);

        else if(metodo.equals("Método"))
            integracion = new IntegracionNumerica(func,a,b,Integer.parseInt(n));

        else
            integracion = new IntegracionNumerica(func,a,b,metodo,Integer.parseInt(n));
    }

    /** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == calcular){
            if(!getEmptyFields()){
                if(getFieldsValids()){
                    soluciones.setClear("");
                    iteraciones.setClear("");
                    setSolucion();
                    soluciones.setText("Y: "+Double.toString(integracion.getIntegral()));
                    iteraciones.setText(integracion.getIteraciones());
                }
            }
        }

        else if(e.getSource() == limpiar){
            funcion.setText(" Función");
            a.setText(" a");
            b.setText(" b");
            n.setText(" Divisiones");
            metodo.setText(" Método");
            soluciones.setClear("");
            iteraciones.setClear("");
        }
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Integración Numérica en el PanelIntegracion.*/
    private final void setInformacion(){
        str_formulas = "<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h/2[Y0 + Yn + &sum(resto de ordenadas)]</p>"
        +"<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h/3[Y0 + Yn + 2*&sum(ordenadas de orden par) + 4*&sum(ordenadas de orden impar)]</p>"
        +"<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = 3h/8[Y0 + Yn + 2*&sum(ordenadas de orden multiplo de tres) + 3*&sum(resto de ordenadas)]</p>";
        info = "<html><body><h2 COLOR = red ALIGN = center>Integracio&#769;n nume&#769;rica</h2>"
        +"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
        +"Introduccio&#769;n</p><p>En la exploracio&#769;n de las te&#769;cnicas de derivacio&#769;n nume&#769;rica quedo&#769; de manifiesto la enorme ventaja "
        +"queresulta de la aplicacio&#769;n de los principios de la interpolacio&#769;n cuando el profesional de la Ingenier&#769;&#305;a se enfrenta a problema "
        +"reales donde el feno&#769;meno f&#769;&#305;sico se manifiesta a trave&#769;s de una funcio&#769;n en formatabular.</p><p>En este sentido, de la misma "
        +"forma en que es posible operar con una funcio&#769;n tabular sin la necesidadde obtener primeramente su forma anal&#769;&#305;tica, en este caso se "
        +"presentara&#769;n las herramientas paraintegrar dicha funcio&#769;n; tales herramientas requieren de los considerandos ba&#769;sicos del ca&#769;lculo "
        +"integraly por tratarse de te&#769;cnicas nume&#769;ricas su resultado es el valor del a&#769;rea bajo la curva de la funcio&#769;n; enconsecuencia, es "
        +"indispensable contar con el intervalo de integracio&#769;n respectivo.</p><p>Adicionalmente a las ventajas intr&#769;&#305;nsecas que ofrecen las "
        +"aplicaciones nume&#769;ricas a trave&#769;s de herra-mientas de co&#769;mputo, la integracio&#769;n nume&#769;rica permite obtener resultados muy precisos"
        +"para aquellasintegrales denominadas impropias o para aquellas que por su complejidad rebasan a las te&#769;cnicasanal&#769;&#305;ticas.</p>"
        +"<p>Es necesario resaltar que para la buena aplicacio&#769;n de las herramientas de integracio&#769;n nume&#769;ricadeben aplicarse conceptos ba&#769;sicos"
        +"de interpolacio&#769;n nume&#769;rica, tales como el orden de interpolacio&#769;ny el orden de error en funcio&#769;n del paso h, que debe ser constante.</p>"
        +"<p>2. Integración&#769;n nume&#769;rica</p><p>El punto de partida para el desarrollo de las herramientas de integracio&#769;n nume&#769;rica [1], en este caso,"
        +"es el polinomio interpolante de Newton-Gregory expresado en la ecuacio&#769;n (1) y que se aplica a una</p><p>funcio&#769;n tabular equiespaciada.</p>"
        +"<p>f(X) = Y0 + k&#916;Y0 +k(k &#8722; 1)/2!&#916;<sup>2</sup>Y0 + k(k &#8722; 1)(k &#8722; 2)/3!&#916;<sup>3</sup>Y0 + ... (1)</p>"
        +"<p>Donde h = cte y k = Xk&#8722;X0h . Se plantea entonces obtener:</p><p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx (2)</p>"
        +"<p>Dado que el polinomio interpolante de la ecuacio&#769;n (1) representa de buena manera a la funcio&#769;nanal&#769;&#305;tica f(X) es va&#769;lida la siguiente igualdad:</p>"
        +"<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = &#x222b<sub>X0<sub><sup>Xn</sup> (Y0 + k&#916;Y0 +k(k &#8722; 1)/2!&#916;2Y0 + k(k &#8722; 1)(k &#8722; 2)/3!&#916;3Y0 + ...) dx (3)</p>"
        +"<p>Para proceder a su solucio&#769;n es necesario realizar un cambio de variable de X a k de la siguiente forma:</p>"
        +"<p>Si k = (Xk&#8722;X0)/h , entonces:</p><p>Si X = X0 =&#8658; K = 0</p> <p>Si X = Xn =&#8658; K = n. n representa el nu&#769;mero de pares de puntos que conforman la funcio&#769;n tabular.</p>"
        +"<p>dk/dX = 1/h =&#8658; dX = h dk</p>"
        +"<p>Incluyendo estas consideraciones en la ecuacio&#769;n (3):</p><p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = &#x222b<sub>0<sub><sup>n</sup>(Y0 + k&#916;Y0 + k(k &#8722; 1)/2!&#916;<sup>2</sup>Y0 +k(k &#8722; 1)(k &#8722; 2)/3!&#916;<sup>3</sup>Y0 + ...)h dx (4)</p>"
        +"<p>Realizando la integral:</p><p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h[kY0 +k<sup>2</sup>/2&#916;Y0 + (k<sup>3</sup>/6&#8722;k<sup>2</sup>/4)&#916;<sup>2</sup>Y0 +(k<sup>4</sup>/24&#8722;3k<sup>3</sup>/18&#8722;2k<sup>2</sup>/12)&#916;<sup>3</sup>Y0+ ...)</p><p>|<sup>n</sup><sub>0</sub></p>"
        +"<p>Valuando sus l&#769;&#305;mites:<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h[nY0 +n<sup>2</sup>/2&#916;Y0 + (n<sup>3</sup>/6&#8722;n<sup>2</sup>/4)&#916;<sup>2</sup>Y0 +(n<sup>4</sup>/24&#8722;3n<sup>3</sup>/18&#8722;2n<sup>2</sup>/12)&#916;<sup>3</sup>Y0+ ...)] (5)</p>"
        +"<p>La ecuacio&#769;n (5) representa la integral de la funcio&#769;n tabular f(X) con n puntos equiespaciada. Apartir de ella es posible obtener diferentes o&#769;rdenes de integracio&#769;n de acuerdo al orden de interpolacio&#769;ndeseado.</p><br><br></P></body></html>";

        formulas.setInfo(str_formulas);
        informacion.setInfo(info);
    }

    /** * Método final que hereda de PanelMethodsTheme4 que se encarga de controlar la distribución de los compontes visuales del PanelIntegracion.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);

        titulo_b.setFont(titulo); 
        titulo_b.setForeground(titulos);
        titulo_a.setFont(titulo); 
        titulo_a.setForeground(titulos);
        titulo_metodo.setFont(titulo); 
        titulo_metodo.setForeground(titulos);
        titulo_n.setFont(titulo); 
        titulo_n.setForeground(titulos);
        titulo_intervalo.setFont(titulo); 
        titulo_intervalo.setForeground(titulos);


        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        n.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        metodo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        funcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);
        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(funcion, GroupLayout.Alignment.TRAILING)
                    .addComponent(formulas)
                    .addGroup(GroupLayout.Alignment.TRAILING, panel_datosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(titulo_a)
                        .addGap(18, 18, 18)
                        .addComponent(a, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(titulo_b)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(titulo_funcion)
                            .addComponent(titulo_intervalo)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_metodo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_n, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(n, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titulo_intervalo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_a)
                    .addComponent(a, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_b)
                    .addComponent(b, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_metodo)
                    .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_n)
                    .addComponent(n, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
    }
}
