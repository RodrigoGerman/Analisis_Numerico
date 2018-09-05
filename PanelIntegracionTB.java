package controlador.theme4.tb;

import vista.panels.theme4.tb.PanelMethodsTheme4TB;
import controlador.WindowFuncionTabular;
import modelo.FuncionTabular;
import modelo.theme4.IntegracionNumerica;
import vista.accesories.TextFieldNumber;
import vista.accesories.TextFieldString;

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
    * La clase PanelIntegracionTB es una clase que hereda de PanelMethodsTheme4TB debido a que es un panel de método de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Integración Numérica en una función tabular, se encarga de crear un panel específico para la visualización
    * del método numérico de integración Numérica en una función tabular .
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelIntegracionTB extends PanelMethodsTheme4TB implements ActionListener{
    /** * Variable tipo JLabel privada y final, que representa el título para el intervalo en el panel.*/ 
    private final JLabel titulo_intervalo = new JLabel("Intervalo:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de a mostrado en el panel.*/ 
    private final JLabel titulo_a = new JLabel("a:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de b mostrado en el panel.*/ 
    private final JLabel titulo_b = new JLabel("b:");   
    /** * Variable tipo JLabel privada y final, que representa el título para el método en el panel.*/ 
    private final JLabel titulo_metodo = new JLabel("Método:");
    /** * Variable tipo TextFieldString privada y final, que representa el campo para que el usuario pueda introducir el método a usar.*/
    private final TextFieldString metodo = new TextFieldString(" Método",Color.BLACK);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de a del intervalo.*/
    private final TextFieldNumber a = new TextFieldNumber(" a",Color.BLACK,1);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de b del intervalo.*/
    private final TextFieldNumber b = new TextFieldNumber(" b",Color.BLACK,1);
    /** * Variable tipo privada ArrayList<ArrayList<Double>> que representa el la función tabular.*/
    private ArrayList<ArrayList<Double>> func_list = null;
    /** * Variable tipo privada IntegracionNumerica que representa el método de Integración Numérica.*/
    private IntegracionNumerica integracion = null;
    /** * Variable tipo privada JFrame que representa la ventana padre que llamo al PanelIntegracionTB.*/
    private JFrame parent;

    /** * Método constructor que crear un PanelIntegracionTB pero sin ninguna comportamiento ni estilo especifico.*/
    public PanelIntegracionTB(){}

    /** * Método constructor que crea un PanelIntegracionTB, recibe la ventana padre que lo llamo y una String que representa el método que va a contener.*/
    public PanelIntegracionTB(JFrame fondo,String metodo){
        super(new Color(76, 123, 171),metodo);
        setInformacion();
        setPanelDatos();
        setAllElements();
        generar_funciontb.addActionListener(this);
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    /** * Método final heredado de PanelMethodsTheme4 que se encarga de validar si los campos a llenar en el PanelIntegracionTB no están vacíos.*/ 
    protected final boolean getEmptyFields(){

        if(func_list == null || num_puntos.getText().trim().equals(" Puntos")){
            JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no ha introducido una función tabular"
            +" por favor introduzca una función tabular.", "Error - App Análisis Numérico", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((a.getText()).equals(" a") || (b.getText()).equals(" b")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un intervalo por favor."
            ,"Error - Función",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

     /** * Método final heredado de PanelMethodsTheme4 que se encarga de validar si los campos a llenar en el PanelIntegracionTB son validos.*/
    protected final boolean getFieldsValids(){

        if(!a.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido para a por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!b.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido para b por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(Double.parseDouble(a.getText()) == Double.parseDouble(b.getText())){
             JOptionPane.showMessageDialog(null,"Error. \nIngrese un intervalo valido por favor."
             ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!metodo.getText().equals("1/2"))
            if(!metodo.getText().equals("1/3"))
                if(!metodo.getText().equals("3/8"))
                    if(!metodo.getText().equals("general")){
                        JOptionPane.showMessageDialog(null,"Error. \nIngrese un método valido, 1/2,1/3,3/8 o general."
                        ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
                         return false;
                    }

        return true;
    }

    /** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
    @Override
    public void actionPerformed(ActionEvent e){
        int puntos = 0;
        double a,b;
        if(e.getSource() == generar_funciontb){
            if(!num_puntos.getText().equals(" Puntos")){
                puntos = Integer.parseInt(num_puntos.getText().trim());

                if(puntos == 0 || puntos < 2){
                    JOptionPane.showMessageDialog(null,"Advertencia. \nPor favor introduzca más de dos puntos."
                    ,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    WindowFuncionTabular proyect = new WindowFuncionTabular(parent,true,puntos);
                    proyect.addWindowListener(new WindowAdapter(){
                        @Override
                        public void windowClosed(WindowEvent e){
                            func_list = proyect.getfunciontb();
                        } 
                    });
                }
            }
            else
                JOptionPane.showMessageDialog(null,"Advertencia. \nPorfavor introduzca el numero de puntos de su función tabular."
                    ,"Advertencia - App RGL",JOptionPane.WARNING_MESSAGE);
        }

        else if(e.getSource() == calcular){

            if(!getEmptyFields()){

                if(getFieldsValids()){

                    soluciones.setClear("");
                    iteraciones.setClear("");
                    a = Double.parseDouble(this.a.getText());
                    b = Double.parseDouble(this.b.getText());

                    if(metodo.getText().trim().equals(" Método"))
                        integracion = new IntegracionNumerica(new FuncionTabular(func_list),a,b);
                    else
                        integracion = new IntegracionNumerica(new FuncionTabular(func_list),a,b,metodo.getText().trim());

                    soluciones.setText("Y: "+Double.toString(integracion.getIntegral()));

                    iteraciones.setText(integracion.getIteraciones());
                }
           }
        }

        else if(e.getSource() == limpiar){
            num_puntos.setText(" Puntos");
            this.x.setText(" x");
            this.a.setText(" a");
            this.b.setText(" b");
            this.metodo.setText(" Método");
            func_list = null;
            soluciones.setClear("");
            iteraciones.setClear("");
        }
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Integración Numérica en el PanelIntegracionTB.*/
    private final void setInformacion(){
        str_formulas = "<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h/2[Y0 + Yn + &sum(resto de ordenadas)]</p>"
        +"<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h/3[Y0 + Yn + 2*&sum(ordenadas de orden par) + 4*&sum(ordenadas de orden impar)]</p>"
        +"<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = 3h/8[Y0 + Yn + 2*&sum(ordenadas de orden multiplo de tres) + 3*&sum(resto de ordenadas)]</p>";
        info = "<html><body><h2 COLOR = red ALIGN = center>Integración&#769;n nume&#769;rica</h2>"
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
        +"<p>Para proceder a su solucio&#769;n es necesario realizar un cambio de variable de X a k de la siguienteforma:</p>"
        +"<p>Si k = (Xk&#8722;X0)/h , entonces:</p><p>Si X = X0 =&#8658; K = 0</p> <p>Si X = Xn =&#8658; K = n. n representa el nu&#769;mero de pares de puntos que conforman la funcio&#769;n tabular.</p>"
        +"<p>dk/dX = 1/h =&#8658; dX = h dk</p>"
        +"<p>Incluyendo estas consideraciones en la ecuacio&#769;n (3):</p><p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = &#x222b<sub>0<sub><sup>n</sup>(Y0 + k&#916;Y0 + k(k &#8722; 1)/2!&#916;<sup>2</sup>Y0 +k(k &#8722; 1)(k &#8722; 2)/3!&#916;<sup>3</sup>Y0 + ...)h dx (4)</p>"
        +"<p>Realizando la integral:</p><p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h[kY0 +k<sup>2</sup>/2&#916;Y0 + (k<sup>3</sup>/6&#8722;k<sup>2</sup>/4)&#916;<sup>2</sup>Y0 +(k<sup>4</sup>/24&#8722;3k<sup>3</sup>/18&#8722;2k<sup>2</sup>/12)&#916;<sup>3</sup>Y0+ ...)</p><p>|<sup>n</sup><sub>0</sub></p>"
        +"<p>Valuando sus l&#769;&#305;mites:<p>&#x222b<sub>X0<sub><sup>Xn</sup> f(x) dx = h[nY0 +n<sup>2</sup>/2&#916;Y0 + (n<sup>3</sup>/6&#8722;n<sup>2</sup>/4)&#916;<sup>2</sup>Y0 +(n<sup>4</sup>/24&#8722;3n<sup>3</sup>/18&#8722;2n<sup>2</sup>/12)&#916;<sup>3</sup>Y0+ ...)] (5)</p>"
        +"<p>La ecuacio&#769;n (5) representa la integral de la funcio&#769;n tabular f(X) con n puntos equiespaciada. Apartir de ella es posible obtener diferentes o&#769;rdenes de integracio&#769;n de acuerdo al orden de interpolacio&#769;ndeseado.</p><br><br></P></body></html>";

        formulas.setInfo(str_formulas);
        informacion.setInfo(info);
    }

    /** * Metodo final que hereda de PanelMethodsTheme4 que se encarga de controlar la distribucion de los compontes visuales del PanelIntegracionTB.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funciontb.setFont(titulo); 
        titulo_funciontb.setForeground(titulos);

        titulo_puntos.setFont(titulo); 
        titulo_puntos.setForeground(titulos);
        titulo_x.setFont(titulo); 
        titulo_x.setForeground(titulos);

        titulo_metodo.setFont(titulo); 
        titulo_metodo.setForeground(titulos);
        titulo_intervalo.setFont(titulo); 
        titulo_intervalo.setForeground(titulos);

        titulo_a.setFont(titulo); 
        titulo_a.setForeground(titulos);
        titulo_b.setFont(titulo); 
        titulo_b.setForeground(titulos);

        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        generar_funciontb.setBackground(titulos);
        generar_funciontb.setBorder(BorderFactory.createLineBorder(fondo,2));

        x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        num_puntos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        metodo.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);

        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_intervalo)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(formulas)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(generar_funciontb,GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                            .addComponent(titulo_puntos, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(num_puntos, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_funciontb)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_datosLayout.createSequentialGroup()
                                        .addComponent(titulo_a)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(a, GroupLayout.PREFERRED_SIZE,80, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(titulo_b)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(b,GroupLayout.PREFERRED_SIZE,80, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_metodo)
                        .addGap(18, 18, 18)
                        .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_funciontb, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_puntos)
                    .addComponent(num_puntos, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generar_funciontb,GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(titulo_intervalo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_a)
                    .addComponent(a, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_b)
                    .addComponent(b, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_metodo)
                    .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }
}