
package controlador.theme4;

import vista.panels.theme4.PanelMethodsTheme4;
import modelo.theme4.DerivacionNumerica;
import vista.accesories.TextFieldNumber;

import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
    * La clase PanelDerivacion es una clase que hereda de PanelMethodsTheme4 debido a que es un panel de método de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Derivación Numérica, se encarga de crear un panel específico para la visualización
    * del método numérico de Derivación Numérica.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelDerivacion extends PanelMethodsTheme4 implements ActionListener{
    /** * Variable tipo JLabel privada y final, que representa el título para el grado de derivada en el panel. */ 
    private final JLabel titulo_grado = new JLabel("Grado Derivada:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor del espaciamiento de los valores evaluados en el panel.*/ 
    private final JLabel titulo_h = new JLabel("H:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de la x a evaluar la derivada en el panel.*/ 
    private final JLabel titulo_x = new JLabel("X a buscar:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el valor del grado de error en el panel.*/ 
    private final JLabel titulo_error = new JLabel("Error:");
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de x.*/
    private final TextFieldNumber x = new TextFieldNumber(" x",Color.BLACK,1);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del grado a derivar.*/
    private final TextFieldNumber grado = new TextFieldNumber(" Grado",Color.BLACK,2);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del grado de error.*/
    private final TextFieldNumber error = new TextFieldNumber(" Grado Error",Color.BLACK,2);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del espaciamiento de los valores.*/
    private final TextFieldNumber h = new TextFieldNumber(" h",Color.BLACK,1);
    /** * Variable tipo privada DerivacionNumerica que representa el método de Derivación Numérica.*/
    private DerivacionNumerica derivacion = null;

    /** * Método constructor que crear un PanelClosedMethods pero sin ninguna comportamiento ni estilo especifico.*/
    public PanelDerivacion(){}

    /** * Método constructor que crea un PanelDerivacion, recibe el color del fondo para el panel y una String que representa el método que va a contener.*/
    public PanelDerivacion(Color fondo,String metodo){
        super(new Color(76, 123, 171),metodo);
        setInformacion();
        setPanelDatos();
        setAllElements();
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    /** * Método final heredado de PanelMethodsTheme4 que se encarga de validar si los campos a llenar en el PanelDerivacion no están vacíos.*/ 
    protected final boolean getEmptyFields(){

        if((funcion.getText()).equals(" Función")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una Función por favor."
            ,"Error - Función",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((grado.getText()).equals(" Grado")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un grado de derivacion por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((x.getText()).equals(" x") || (x.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una x a buscar valida por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }


        if((metodo.getText()).equals(" Metodo")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún método, se utilizará uno por defecto."
            ,"Advertencia - Método",JOptionPane.WARNING_MESSAGE);
        }

        if((h.getText()).equals(" h")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún espaciamiento, se utilizará uno por defecto."
            ,"Advertencia - Espaciamiento",JOptionPane.WARNING_MESSAGE);
        }


        if((error.getText()).equals(" Error")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún grado de error, se utilizará uno por defecto."
            ,"Advertencia - Error",JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    /** * Método final heredado de PanelMethodsTheme4 que se encarga de validar si los campos a llenar en el PanelDerivacion son validos.*/ 
    protected final boolean getFieldsValids(){

        if(!funcion.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu funcion tiene errores sintacticos revisela por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!x.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido para x por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!h.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido en el intervalo h por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!(metodo.getText()).equals("atras") && !(metodo.getText()).equals("adelante") && !(metodo.getText()).equals("central") && !(metodo.getText()).equals(" Metodo")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un método valido por favor.\nEjemplo: atras,adelante o central."
            ,"Error - Método",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!(error.getText()).equals(" Error") && !(error.getText()).equals("2") && !(error.getText()).equals("4")) {
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un grado de error valido por favor\n, los disponibles son el grado 2 y el 4."
             ,"Error - Grado de Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Método privado setSolucion que se encarga de generar la solución del método de derivacion Numérica a partir de los datos proporcionados en los componentes del PanelDerivacion.*/
    private final void setSolucion(){
        String func = (funcion.getText()).trim();
        double x = Double.parseDouble(this.x.getText().trim());
        int grado = Integer.parseInt(this.grado.getText().trim());
        String error = this.error.getText().trim();
        String h = this.h.getText().trim();
        String metodo = this.metodo.getText().trim();

        if(error.equals("Error") && h.equals("h") && metodo.equals("Metodo"))
            derivacion = new DerivacionNumerica(func,x,grado);

        else if(error.equals("Error") && h.equals("h"))
            derivacion = new DerivacionNumerica(func,x,grado,metodo);

        else if(metodo.equals("Método") && h.equals("h"))
            derivacion = new DerivacionNumerica(func,x,grado,Integer.parseInt(error));

        else if(metodo.equals("Método") && error.equals("Error"))
            derivacion = new DerivacionNumerica(func,x,grado,Double.parseDouble(h));

        else if(metodo.equals("Método"))
            derivacion = new DerivacionNumerica(func,x,grado,Double.parseDouble(h),Integer.parseInt(error));

        else if(h.equals("h"))
            derivacion = new DerivacionNumerica(func,x,grado,metodo,Integer.parseInt(error));

        else if(error.equals("Error"))
            derivacion = new DerivacionNumerica(func,x,grado,Double.parseDouble(h),metodo);

        else
            derivacion = new DerivacionNumerica(func,x,grado,Double.parseDouble(h),metodo,Integer.parseInt(error));
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Derivación Numérica en el PanelDerivacion.*/
    private final void setInformacion(){
        str_formulas = "<p>Y<sup>'</sup>=1/h[ 1 , &#8722;1] + er </p><p>Y<sup>''</sup> =1/2h[ 1 , &#8722;2 , 1 ] + er </p><p>Y<sup>'''</sup>=1/h<sup>2<sup>[&#8722;1,4, &#8722;5 , 2 ] + er</p> ";
        info = "<html><body><h2 COLOR = red ALIGN = center>Derivacio&#769;n nume&#769;rica</h2>"
        +"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>Introduccio&#769;n</p><p>Las te&#769;cnicas de interpolacio&#769;n nume&#769;rica proporcionan las herramientas para obtener las funciones anal&#769;&#305;ticas "
        +"de funciones tabulares que comu&#769;nmente son la materia prima de los procesos propios delas pra&#769;cticas de la Ingenier&#769;&#305;a.</p><p>Sin embargo, cuando se dispone de una funcio&#769;n tabular "
        +"compuesta de un nu&#769;mero tal de puntos que hace poco pra&#769;ctica la obtencio&#769;n de la expresio&#769;n anal&#769;&#305;tica, no resulta sencillo obtener las derivadas(o las integrales) de dicha funcio&#769;n."
        +"Las siguientes herramientas de derivacio&#769;n nume&#769;rica permiten obtener la derivada de la funcio&#769;n en cualesquiera de los puntos seleccionados, sin necesidad derecurrir a la expresio&#769;n anal&#769;&#305;tica.</p>"
        +"<p>Por tratarse de una herramienta del ana&#769;lisis nume&#769;rico, los resultados obtenidos sera&#769;n los valores nume&#769;ricos de la derivada de una funcio&#769;n en un punto. Si se desea obtener como resultado una expresio&#769;n "
        +"anal&#769;&#305;tica, corresponde obtener inicialmente dicha funcio&#769;n, ya sea por algu&#769;n polinomio interpolante o por la interpolacio&#769;n de Lagrange y despue&#769;s derivarla.</p><p>"
        +"2. Derivacio&#769;n nume&#769;rica</p><p>Sea Y = f(X) una funcio&#769;n tabular continua con n puntos que puede aproximarse a un polinomio degrado n&#8722;1 que pasa por todos los puntos incluidos en su forma tabular y cuya variable independientees "
        +"equiespaciada con paso h = cte, el polinomio interpolante que la representa es:</p><p>Yk = Y0 + k&#916;Y0 +k(k &#8722; 1)/2!&#916;2Y0 + k(k &#8722; 1)(k &#8722; 2)/3! + &#916;3Y0 + ... (1)</p><br>"
        +"Donde: k = Xk&#8722;X0/h.<br>Se propone derivar la ecuacio&#769;n (1) con respecto a la variable independiente X. Dada la estructura de esta ecuacio&#769;n, es necesario aplicar la regla de la cadena:</p><p>dY/dX= dY*dk&#183;dk/dX (2)</p><p>dk/dX=1/h(3)</p>"
        +"<p>dY/dk = &#916;Y0 + (2k &#8722; 1)/2! + &#916;2Y0 + (3k2 &#8722; 6k + 2)/3!&#916;3Y0 + ... (4)</p>"
        +"<p>Sustituyendo las ecuaciones (3) y (4) en (2) se obtiene:</p><p>dY/dX=1/h*[&#916;Y0 + (2k &#8722; 1)/2!&#916;2Y0 +(3k2 &#8722; 6k + 2)/3!&#916;3Y0 + ...] (5)</p>"
        +"<p>Esta ultima ecuacio&#769;n (5) representa la primera derivada del polinomio interpolante que representa a la funcio&#769;n tabular.</p><p>Derivando a (5) y utilizando de nuevo la regla de la cadena mostrada en la ecuacio&#769;n (2):"
        +"</p><p>d<sup>2</sup>Y/dX<sup>2</sup>=1/h<sup>2</sup>[;&#916;2Y0 + (k &#8722; 1)&#916;3Y0 + ...] (6)</p>"
        +"<p>Derivando de nuevo:d<sup>3</sup>Y/dX<sup>3</sup>=1/h<sup>3</sup>[;&#916;3Y0 + ... ](7) </p><p>El proceso puede repetirse las veces que se considere necesario, haciendo notar que al aumentar el orden de la derivada deseada debe aumentarse el orden de la diferencia considerada en el polinomiointerpolante.</p>"
        +"<p>Retomando la ecuacio&#769;n (5). Si se trunca el polinomio interpolante hasta la primera diferencia y se sustituye a dicha diferencia por los puntos que la conforman en el orden en que aparecen en lafuncio&#769;n tabular se obtiene:</p><p>dY/dX =1/h [&#916;Y0] + er</p><p>= 1/h [(Y1 &#8722; Y0] er (8) </p><p> = 1/h [&#8722;Y0 + Y1] + er </p>"
        +"<p>Analizando esta u&#769;ltima ecuacio&#769;n se percibe que por provenir de un polinomio interpolante truncado a la primera diferencia resulta so&#769;lo funcio&#769;n de dos puntos provenientes de la funcio&#769;n tabular: (X0, Y0)y (X1, Y1). El polinomio que une a dos puntos es de grado n = 1, es decir, una l&#769;&#305;nea recta que so&#769;lo "
        +"puede ser derivada en una ocasio&#769;n (dado que el valor de la siguiente derivada ser&#769;&#305;a 0). Por otra parte, la interpretacio&#769;n que hace el Ca&#769;lculo diferencial de la derivada en el valor de la pendiente de la recta tangente en determinado punto de la curva a derivar."
        +"En este sentido, ya que nuestra curva a derivar es una l&#769;&#305;nea recta compuesta por dos puntos so&#769;lo puede obtenerse el valor de la derivada</p>"
        +"<p>en cada uno de esos puntos, pero en todo caso, la pendiente de la recta tangente sera&#769; igual en ambos puntos. </p><p>La consideracio&#769;n sobre la obtencio&#769;n de la derivada en un punto es importante "
        +"en este desarrollo, ya que el resultado del uso de las te&#769;cnicas de interpolacio&#769;n es obtener el valor de la pendiente de la recta tangente, no su expresio&#769;n anal&#769;&#305;tica.</p><p>Es por esto que de acuerdo al polinomio "
        +"interpolante seleccionado debe especificarse claramente su orden de interpolacio&#769;n y, en consecuencia, el punto en el cual desea obtenerse la derivada. Estepunto seleccionado se denomina pivote.</p><p>Para la ecuacio&#769;n (8) la derivada "
        +"puede obtenerse so&#769;lo en dos puntos, pero el resultado en ambos es el mismo. Como una convencio&#769;n se establece como pivote al punto (X0, Y0); para indicarlo sesubraya el coeficiente respectivo. Por otra parte, a manera de establecer una "
        +"notacio&#769;n ma&#769;s pra&#769;ctica,se eliminan los indicativos de las ordenadas de la ecuacio&#769;n deja&#769;ndose so&#769;lo los coeficientes, pero en todo caso, la ecuacio&#769;n de derivacio&#769;n nume&#769;rica contempla siempre iniciar "
        +"en la ordenada Y0.</p><p>dY/dX=1/h[&#8722;Y0 + Y1] + er</p><p>Utilizando una notacio&#769;n ma&#769;s convencional en la derivada e incluyendo el punto de derivacio&#769;n (pivote)se tiene:</p><p>Y<sup>'</sup>;X=X0 =1/h[&#8722;1 , 1 ] + er (9)</p>"
        +"<p>Finalmente, er representa el error que debe an&#771;adirse a la ecuacio&#769;n (9) para que el valor sea exacto.El ca&#769;lculo del error merece ser analizado en forma separada, pero se adelanta que se obtiene una aproximacio&#769;n a e&#769;l por medio del criterio del te&#769;rmino siguiente [?].</p>"
        +"<p>Ahora bien, si se desea obtener fo&#769;rmulas con menor error intr&#769;&#305;nseco se propone que la ecuacio&#769;n (5) sea truncada a partir de la segunda diferencia.</p><p>dY/dX=1/h[&#916;Y0 + (2k &#8722; 1)/2! &#916;<sub>2</sub>Y0]er (10)</p>"
        +"<p>Las ecuaciones que se obtengan de (10) se denominara&#769;n de segundo orden de interpolacio&#769;n. Al tomar en cuenta una segunda diferencia se consideran a los puntos (X0, Y0), (X1, Y1) y (X2, Y2)de la funcio&#769;n tabular. En consecuencia pueden ser varios tipos de curvas las que unan a "
        +"estos tres puntos y que la derivada obtenida en cada punto sea diferente. Dado lo anterior, deben calcularse tres ecuaciones particulares para obtener las derivadas valuadas en (X0, Y0), (X1, Y1) y (X2, Y2)respectivamente.</p><p>Para la derivada en (X0, Y0). El punto pivote se ubica en el primer "
        +"punto de la funcio&#769;n tabular.Esto tiene como consecuencia que en k = Xk&#8722;X0h el valor de referencia es el propio Xk = X0,por lo que k = 0. Sustituyendo este valor y los respectivos de las diferencias en la ecuacio&#769;n(10) se obtiene:</p><p>dY/dX=1/h[&#916;Y0 + (2k &#8722; 1)/2! &#916;<sub>2</sub>Y0] + er </p><p>1/h[(Y1 &#8722; Y0)&#8722; 1/2(Y2 &#8722; 2Y1 + Y0)]+ er</p>"
        +"<p>Factorizando se obtiene:dY/dX=1/2h[&#8722;3Y0 + 4Y1 &#8722; Y2] + er</p><p>Modificando la notacio&#769;n:</p><p>Y<sub>'</sub>X=X0 =1/2h[&#8722;3 , 4 , &#8722;1 ] + er (11)</p><p>Para la derivada en (X1, Y1)."
        +"El punto pivote se ubica en el segundo punto de la funcio&#769;ntabular. Esto tiene consecuencia que en k = (Xk&#8722;X0)/h el valor de referencia es Xk = X1, por lo que k = 1. Sustituyendo este valor y los respectivos de las diferencias "
        +"en la ecuacio&#769;n (10) se obtiene:</p><p>dY/dX =1/h[&#916;Y0 + (2k&#8722;1)/2! &#916;<sup>2</sup>Y0]+ er</p><p>1/h[(Y1 &#8722; Y0) + 1/2(Y2 &#8722; 2Y1 + Y0)]+ er</p>"
        +"<p>Factorizando se obtiene: dY/dX=1/2h[&#8722;Y0 + Y2] + er</p><p>Modificando la notacio&#769;n:</p><p>Y<sup>'</sup> X=X1 = 1/2h[&#8722;1 , 0 , 1 ] + er (12)</p><p>Para la derivada en (X2, Y2)."
        +"El punto pivote se ubica en el segundo punto de la funcio&#769;n tabular. Esto tiene consecuencia que en k = (Xk&#8722;X0)/h el valor de referencia es Xk = X2, por lo que k = 2. Sustituyendo este valor y los respectivos "
        +"de las diferencias en la ecuacio&#769;n (10) se obtiene:</p><p>dY/dX =1/h[&#916;Y0 + (2k&#8722;1)/2! &#916<sup>2</sup>Y0] + er</p><p>1/h[(Y1 &#8722; Y0) + 3/2(Y2 &#8722; 2Y1 + Y0)] + er</p>"
        +"<p>Factorizando se obtiene:dY/dX=1/2h[Y0 &#8722; 4Y1 + 3Y2] + er</p><p>Modificando la notacio&#769;n:</p><p>Y<sup>'</sup>X=X2 =1/2h[ 1 , &#8722;4 , 3 ] + er (13)</p>"
        +"<p>Este proceso debe seguirse para definir otras formas de derivacio&#769;n nume&#769;rica, incluso para derivadas de orden superior. No existe l&#769;&#305;mite en cuanto al orden de interpolacio&#769;n que pueda elegirse, "
        +"aunque por consideraciones del error el tercer orden de interpolacio&#769;n es el mas utilizado comu&#769;nmente."
        +"<p>Como una te&#769;cnica de comprobacio&#769;n de la correcta conformacio&#769;n de todas estas fo&#769;rmulas, los coeficientes que las forman siempre deben sumar 0.</p><p>Es necesario resaltar la relacio&#769;n que existe entre el orden de la derivada y el orden de interpolacio&#769;n(orden de la diferencia ma&#769;xima del polinomio interpolante) "
        +"de cada fo&#769;rmula. Si se desea obtenerla segunda derivada de una funcio&#769;n, para que este valor no sea 0 es necesario que el orden del</p>"
        +"polinomio del cual procede sea al menos de 2; para lograr esto, la funcio&#769;n debera&#769; ser aproximadaa trave&#769;s de un polinomio compuesto por 3 puntos por lo que debe considerar hasta la segunda diferencia."
        +"En consecuencia, el menor orden de interpolacio&#769;n disponible para obtener una fo&#769;rmulade segunda derivada debe ser, precisamente, de segundo orden de interpolacio&#769;n<br><br></P></body></html>";
        formulas.setInfo(str_formulas);
        informacion.setInfo(info);
    }

    /** * Método final que hereda de PanelMethodsTheme4 que se encarga de controlar la distribución de los compontes visuales del PanelDerivacion.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);

        titulo_x.setFont(titulo); 
        titulo_x.setForeground(titulos);

        titulo_grado.setFont(titulo); 
        titulo_grado.setForeground(titulos);
        titulo_error.setFont(titulo); 
        titulo_error.setForeground(titulos);
        titulo_metodo.setFont(titulo); 
        titulo_metodo.setForeground(titulos);
        titulo_h.setFont(titulo); 
        titulo_h.setForeground(titulos);


        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        h.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        error.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        grado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        metodo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        funcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);
        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(funcion, GroupLayout.Alignment.TRAILING)
                            .addComponent(formulas)
                            .addGroup(GroupLayout.Alignment.TRAILING, panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_x)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(x)
                                .addGap(26, 26, 26)))
                        .addContainerGap())
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_grado)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grado, GroupLayout.PREFERRED_SIZE,60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,10, Short.MAX_VALUE)
                        .addComponent(titulo_h)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h, GroupLayout.PREFERRED_SIZE,50, GroupLayout.PREFERRED_SIZE)
                        .addGap(5,5,5))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_metodo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_error, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(error, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_funcion)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_grado)
                    .addComponent(grado, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_h)
                    .addComponent(h, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_x)
                    .addComponent(x, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_metodo))
                .addGap(25, 25, 25)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(error, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_error))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
        );
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
                    soluciones.setText("Y: "+Double.toString(derivacion.getDerivada()));
                    iteraciones.setText(derivacion.getIteraciones());
                }
            }
        }

        else if(e.getSource() == limpiar){
            funcion.setText(" Función");
            metodo.setText(" Método");
            x.setText(" x");
            h.setText(" h");
            grado.setText(" Grado");
            error.setText(" Grado Error");
            soluciones.setClear("");
            iteraciones.setClear("");
        }
    }
}
