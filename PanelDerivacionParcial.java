
package controlador.theme6;

import vista.panels.theme6.PanelMethodsTheme6;
import modelo.theme6.DerivacionNumericaParcial;
import vista.accesories.TextFieldNumber;
import vista.accesories.TextFieldString;
import vista.accesories.TextFieldFuncion;

import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
    * La clase PanelDerivacionParcial es una clase que hereda de PanelMethodsTheme6 debido a que es un panel de metodo de analisis numerico y implementa
    * la interfaz ActionListener para ejecutar el método numérico de Derivación Numérica Parcial, se encarga de crear un panel específico para la visualización
    * del método numérico de Derivación Numérica Parcial.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelDerivacionParcial extends PanelMethodsTheme6 implements ActionListener{
    /** * Variable tipo JButton privada y final, que representa un botón que se encarga de agregar los datos de las variables cuando es presionado.*/
    private final JButton agregar = new JButton("Agregar");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el grado de derivada princiapl en el panel.*/ 
    private final JLabel titulo_grado = new JLabel("Grado Derivada:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el grado de derivada respecto a cada variable en el panel.*/ 
    private final JLabel titulo_grados = new JLabel("Grado:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para los datos de variable en el panel.*/ 
    private final JLabel titulo_datos = new JLabel("Datos de Variable:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para la separacion entre los puntos evaluados en el panel.*/ 
    private final JLabel titulo_h = new JLabel("H:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el valor de la variable en el panel.*/ 
    private final JLabel titulo_x = new JLabel("Valor:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el grado de error en el panel.*/ 
    private final JLabel titulo_error = new JLabel("Error:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el método a utilizar en el panel.*/ 
    private final JLabel titulo_metodo = new JLabel("Metodo:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para el numero de variables en el panel.*/ 
    private final JLabel titulo_variables = new JLabel("Num. de variables:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para la función en el panel.*/ 
    private final JLabel titulo_funcion = new JLabel("Funcion:");
    /** * Variable tipo TextFieldFuncion privada y final, que representa el campo para que el usuario pueda introducir la derivada parcial.*/
    private final TextFieldFuncion funcion= new TextFieldFuncion(" Función",Color.BLACK);
    /** * Variable tipo TextFieldString privada y final, que representa el campo para que el usuario pueda introducir el metodo a utilizar.*/
    private final TextFieldString metodo = new TextFieldString(" Método",Color.BLACK);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor a evaluar la derivada parcial.*/
    private final TextFieldNumber x = new TextFieldNumber(" x",Color.BLACK,1);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el numero de variables que componen la derivada parcial.*/
    private final TextFieldNumber variables = new TextFieldNumber(" variables",Color.BLACK,2);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del grado principal a derivar.*/
    private final TextFieldNumber grado = new TextFieldNumber(" Grado",Color.BLACK,2);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del grado de cada variable a derivar.*/
    private final TextFieldNumber grados = new TextFieldNumber(" Grado",Color.BLACK,2);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor del grado de error a utilizar.*/
    private final TextFieldNumber error = new TextFieldNumber(" Error",Color.BLACK,2);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de la separación entre los puntos evaluados.*/
    private final TextFieldNumber h = new TextFieldNumber(" h",Color.BLACK,1);
    /** * Variable tipo DerivacionNumericaParcial privada que representa el método de Derivación Numérica Parcial.*/
    private DerivacionNumericaParcial derivacion = null;
    /** * Variable tipo byte privada a que se encarga de llevar un conteo sobre el numero de variables ingresadas.*/
    private byte band = 0;
    /** * Arreglo de int privado a que se encarga de almacenar los grados de derivacion sobre cada una de las variables ingresadas.*/
    private int[] grado1;
    /** * Arreglos de doubles privados que se encarga de almacenar la separación entre cada punto y el valor a evaluar de cada una de las variables ingresadas respectivamente.*/
    private double[] h1,num;
    /** * Variables tipo int privadas a que se encarga de almacenar constantes necesarias para la resolución del método de derivacion Numérica Parcial.*/
    private int aux=1,aux1;

    /** * Metodo constructor que crear un PanelDerivacionParcial pero sin ninguna comportamiento ni estilo especifico.*/
    public PanelDerivacionParcial(){}

    /** * Metodo constructor que crea un PanelDerivacionParcial, recibe el color del fondo para el panel.*/
    public PanelDerivacionParcial(Color fondo){
        super(new Color(76, 123, 171),"Derivacion Parcial");
        setInformacion();
        setPanelDatos();
        setAllElements();
        agregar.addActionListener(this);
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    /** * Método final heredado de PanelMethodsTheme6 que se encarga de validar si los campos a llenar en el PanelDerivacionParcial no están vacíos.*/ 
    protected final boolean getEmptyFields(){

        if((funcion.getText()).equals(" Función")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una Función por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if((grado.getText()).equals(" Grado")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese el grado de la derivada parcial porfavor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return true;
        }


        else if(grado1.length == 0){
            JOptionPane.showMessageDialog(null,"Error. \nNo ha introducido ningún grado de derivacion por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if(num.length == 0){
            JOptionPane.showMessageDialog(null,"Error. \nNo ha introducido ningún valor de x."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if(h1.length == 0){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún espaciamiento."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((variables.getText()).equals(" variables")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido el numero de variables que tiene la función."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        if((metodo.getText()).equals(" Método")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún método, se utilizara uno por defecto."
            ,"Advertencia - Metodo",JOptionPane.WARNING_MESSAGE);
        }


        if((error.getText()).equals(" Error")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún grado de error, se utilizara uno por defecto."
            ,"Advertencia - Error",JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    /** * Método final heredado de PanelMethodsTheme6 que se encarga de validar si los campos a llenar en el PanelDerivacionParcial son validos.*/ 
    protected final boolean getFieldsValids(){

        if(!funcion.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu función tiene errores sintacticos revisela por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(num.length != Integer.parseInt((variables.getText()))){
            JOptionPane.showMessageDialog(null,"Error. \nEl numero de variables no corresponde al número de valores que ha introducido."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(h1.length != Integer.parseInt((variables.getText()))){
            JOptionPane.showMessageDialog(null,"Error. \nEl numero de variables no corresponde al número de valores que ha introducido."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(grado1.length != Integer.parseInt((variables.getText()))){
            JOptionPane.showMessageDialog(null,"Error. \nEl numero de variables no corresponde al número de valores que ha introducido."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!(metodo.getText()).equals("atras") && !(metodo.getText()).equals("adelante") && !(metodo.getText()).equals("central") && !(metodo.getText()).equals(" Método")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un método valido por favor.\nEjemplo: atras,adelante o central."
            ,"Error - Metodo",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!(error.getText()).equals(" Error") && !(error.getText()).equals("2") && !(error.getText()).equals("4")) {
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un grado de error valido por favor\n, los disponibles son el grado 2 y el 4."
             ,"Error - Grado de Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Método final heredado de PanelMethodsTheme6 que se encarga de validar si los campos a llenar en el PanelDerivacionParcial no están vacíos respecto a los datos de variable.*/ 
    protected final boolean getEmptyFields1(){

        if((grados.getText()).equals(" Grado")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un grado de derivacion por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((x.getText()).equals(" x") || (x.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una x a buscar valida por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((h.getText()).equals(" h")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún espaciamiento para la variable actual."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((variables.getText()).equals(" variables")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido el numero de variables que tiene la función."
            ,"Advertencia - Espaciamiento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }

    /** * Método final heredado de PanelMethodsTheme6 que se encarga de validar si los campos a llenar en el PanelDerivacionParcial son validos respecto a los datos de variable.*/ 
    protected final boolean getFieldsValids1(){

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

        if(!grados.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido en el intervalo h por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!variables.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido en el numero de variables por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Método privado y final que se encarga de validar que los grados individuales no sean menores o mayores al grado principal de derivacion parcial.*/ 
    private final boolean getValidGrado(){
        int grado = Integer.parseInt(this.grado.getText());
        int sum = 0;
        for(int i = 0;i< aux;i++)
            sum+=grado1[i];

        if(sum > grado){
            JOptionPane.showMessageDialog(null,"Error. \nNo se puede efectuar el método debido a que la suma de los grados de las variables ingresadas supera al grado general."
                    ,"Error - Variables", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(sum < grado){
            JOptionPane.showMessageDialog(null,"Error. \nNo se puede efectuar el método debido a que la suma de los grados de las variables ingresadas no alcanza al grado general."
            ,"Error - Variables", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    /** * Método privado setSolucion que se encarga de generar la solución del método de derivacion Numérica Parcial a partir de los datos proporcionados en los componentes del PanelDerivacionParcial.*/
    private final void setSolucion(){
        String func = (funcion.getText()).trim();
        int grado = Integer.parseInt(this.grado.getText().trim());
        String error = this.error.getText().trim();
        String metodo = this.metodo.getText().trim();


        if(error.equals("Error") && metodo.equals("Método"))
            derivacion = new DerivacionNumericaParcial(func,num,grado,grado1,h1,"central",4);

        else if(metodo.equals("Método"))
            derivacion = new DerivacionNumericaParcial(func,num,grado,grado1,h1,"central",Integer.parseInt(error));

        else if(error.equals("Error"))
            derivacion = new DerivacionNumericaParcial(func,num,grado,grado1,h1,metodo,4);

        else
            derivacion = new DerivacionNumericaParcial(func,num,grado,grado1,h1,metodo,Integer.parseInt(error));
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de Derivación Numérica Parcial en el PanelDerivacionParcial.*/
    private final void setInformacion(){
        info = "<html><body><h2 COLOR = red ALIGN = center>Derivación Parcial</h2>"
        +"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>"
        +"<p>Ecuaciones nume&#769;ricas para derivadas parciales</p><p>El me&#769;todo utilizado en la solucio&#769;n de ecuaciones en derivadas parciales "
        +"es diferencias finitas, mismo que consiste en sustituir en la ecuacio&#769;n a resolver las ecuaciones de derivacio&#769;n nume&#769;rica necesarias"
        +"Resulta entonces necesario disponer de ecuaciones nume&#769;ricas para derivadas parciales [1].</p><p>La obtencio&#769;n de las mismas puede hacerse "
        +"por varios caminos; se logra a trave&#769;s del polinomio de Taylor para la funcio&#769;n f(X,Y) o bien, a partir de las ecuaciones de derivacio&#769;n "
        +"nume&#769;ricas para lafuncio&#769;n Y = f(X) respetando todas sus condiciones. Sea cual sea el camino elegido, deben hacerse un par de  consideraciones:</p>"
        +"<p>Se requiere de una funcio&#769;n U(X,Y); esto implica que debera&#769; hacer un paso constante para la variable X y otro tambie&#769;n constante para Y que "
        +"no necesariamente deben ser iguales entre s&#769;&#305;.</p><p>La solucio&#769;n de la ecuacio&#769;n en derivadas parciales es una  matriz, es decir, "
        +"un arreglo de dos dimensiones.</p><p>La caracter&#769;&#305;stica de la derivada parcial de una funcio&#769;n U(X,Y) es que se aplica una derivada ordinaria "
        +"a una de las variables considera&#769;ndose constante a la otra. Bajo este principio es como se obtiene a partir de una ecuacio&#769;n nume&#769;rica para una "
        +"derivada ordinaria una para derivar parcialmente.</p><p>A partir de la ecuacio&#769;n ordinaria de primer orden de interpolacio&#769;n:</p><p>"
        +"Y<sup>'</sup>;X=X0 =1/h[&#8722;Y0 , Y1]+O(h) (2)</p><p>Si consideramos en lugar de la funcio&#769;n f(X) a la funcio&#769;n f(X,Y ) y si adema&#769;s observamos "
        +"que la ecuacio&#769;n (2) utiliza un punto pivote y otro punto posterior, se puede plantear la siguiente ecuacio&#769;nen derivadas parciales:</p>"
        +"<p>&#8706;U(X,Y)/&#8706;X=1/&#916;X[&#8722;U(X0, Y0) + U(X1, Y0)]+O(h) (3)</p><p>En la ecuacio&#769;n (3) se plantea derivar parcialmente con respecto a X, "
        +"por lo que Y debera&#769; permanecer constante. En consecuencia, se utiliza como pivote al punto U(X0, Y0) y al punto siguiente U(X1, Y0).El espaciamiento para "
        +"la variable X es &#916;X y debera&#769; ser constante. La ecuacio&#769;n resultante conserva su orden de error [3] y se escribe en forma general y coloquialmente como:"
        +"</p><p>&#8706;U/&#8706;X<sub>i,j</sub>=1/&#916;X[;&#8722;U<sub>i,j</sub> + U<sub>i+1,j</sub>]+O(h) (4)</p><p>Aplicando los mismos criterios de definición:</p>"
        +"<p>&#8706;U/&#8706;Y<sub>i,j</sub>=1/&#916;Y[&#8722;U<sub>i,j</sub> + U<sub>i,j+1</sub>]+O(h) (5)</p><p>Si se sigue este criterio respectando las reglas de "
        +"derivacio&#769;n pueden obtenerse otras fo&#769;rmulas adicionales:</p><p>&#8706;U/&#8706;X<sub>i,j</sub>,=1/2&#916;X[;&#8722;U<sub>i-1,j</sub> + U<sub>i+1,j</sub>]+O(h<sup>2</sup>) (6)</p>"
        +"<p>&#8706;U/&#8706;X<sub>i,j</sub>=1/2&#916;Y[&#8722;U<sub>i,j-1</sub> + U<sub>i,j+1</sub>]+O(h<sup>2</sup>) (7)"
        +"<p>&#8706;<sup>2</sup>U/&#8706;X<sup>2</sup><sub>i,j</sub>=1/&#916;X<sup>2</sup>[&#8722;U<sub>i-1,j</sub> &#8722;2U<sub>i,j</sub> + U<sub>i+1,j</sub>]+O(h<sup>2</sup>) (8)</p>"
        +"<p>&#8706;<sup>2</sup>U/&#8706;Y<sup>2</sup><sub>i,j</sub>=1/&#916;Y<sup>2</sup>[&#8722;U<sub>i,j-1</sub> &#8722;2U<sub>i,j</sub> + U<sub>i,j+1</sub>]+O(h<sup>2</sup>) (9)</p>"
        +"<p>&#8706;<sup>2</sup>U/&#8706;Y&#8706;Xsub>i,j</sub>=1/4&#8706;X&#916;Y[&#8722;U<sub>i-1,j-1</sub> &#8722;U<sub>i-1,j+1</sub> &#8722;U<sub>i+1,j-1</sub> + U<sub>i+1,j+1</sub>]+O(h<sup>2</sup>) (10)</p>"
        +"<p>Conclusiones</p><p>La aplicacio&#769;n del me&#769;todo de diferencias finitas es ide&#769;ntico cuando se  aplica a funciones de una variable que cuando se hace con "
        +"funciones de dos variables; el pivoteo se aplica en igual forma e incluso cuando los argumentos de la funcio&#769;n U(X,Y) representan dimensiones de longitud se admite algu&#769;n "
        +"proceso gra&#769;fico, situacio&#769;n que no es muy factible si alguno de los argumentos es el tiempo.</P></body></html>";
        informacion.setInfo(info);
    }

    /** * Método final que hereda de PanelMethodsTheme6 que se encarga de controlar la distribución de los compontes visuales del PanelDerivacionParcial.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);

        titulo_x.setFont(titulo); 
        titulo_x.setForeground(titulos);

        titulo_grado.setFont(titulo); 
        titulo_grado.setForeground(titulos);
        titulo_grados.setFont(titulo); 
        titulo_grados.setForeground(titulos);
        titulo_error.setFont(titulo); 
        titulo_error.setForeground(titulos);
        titulo_metodo.setFont(titulo); 
        titulo_metodo.setForeground(titulos);
        titulo_datos.setFont(titulo); 
        titulo_datos.setForeground(titulos);
        titulo_h.setFont(titulo); 
        titulo_h.setForeground(titulos);
        titulo_variables.setFont(titulo); 
        titulo_variables.setForeground(titulos);


        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        agregar.setBackground(titulos);
        agregar.setBorder(BorderFactory.createLineBorder(fondo,2));

        x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        h.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        error.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        grado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        grados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        metodo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        funcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
                        .addComponent(titulo_grado)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grado, GroupLayout.PREFERRED_SIZE,80,GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_datos)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_grados)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grados, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(agregar,GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(funcion)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_variables)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(variables, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_h)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(h, GroupLayout.PREFERRED_SIZE,70, GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(titulo_x)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(x, GroupLayout.PREFERRED_SIZE,70, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(GroupLayout.Alignment.LEADING, panel_datosLayout.createSequentialGroup()
                                    .addComponent(titulo_error, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(error, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.LEADING, panel_datosLayout.createSequentialGroup()
                                    .addComponent(titulo_metodo)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(metodo, GroupLayout.PREFERRED_SIZE,124, GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_grado)
                    .addComponent(grado, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_variables)
                    .addComponent(variables, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(titulo_datos)
                .addGap(15, 15, 15)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(h, GroupLayout.PREFERRED_SIZE,24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_x)
                    .addComponent(titulo_h)
                    .addComponent(x,GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_grados)
                    .addComponent(grados, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregar,GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_metodo)
                    .addComponent(metodo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_error)
                    .addComponent(error, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
    }

    /** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del componente que las ocasione.*/
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == agregar){
            if(band <= aux-1){
                if(!getEmptyFields1() && getFieldsValids1()){
                    if(band == 0){
                        aux = Integer.parseInt(variables.getText());

                        num = new double[aux];
                        grado1 = new int[aux];
                        h1 = new double[aux];
                    }

                    num[band] = Double.parseDouble(x.getText());
                    h1[band] = Double.parseDouble(h.getText());
                    grado1[band] = Integer.parseInt(grados.getText());
                    aux1 -= grado1[band];

                    band++;

                    x.setText(" x");
                    grados.setText(" Grado");
                    h.setText(" h");
                }
            }
            else
                JOptionPane.showMessageDialog(null,"Error. \nNo se pueden agregar más variables debido a que ya se ingresaron el numero especificado."
                    ,"Error - Variables",JOptionPane.ERROR_MESSAGE);

        }

        else if(e.getSource() == calcular){
            if(num != null){
                if(!getEmptyFields() && getFieldsValids()){
                    if(band != aux-1){
                        if(getValidGrado()){
                            soluciones.setClear("");
                            iteraciones.setClear("");
                            setSolucion();
                            soluciones.setText("Y: "+Double.toString(derivacion.getDerivada()));
                            iteraciones.setText(derivacion.getIteraciones());
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Error. \nEl numero de variables ingresadas no corresponde al numero ingresado."
                        ,"Error - Función",JOptionPane.ERROR_MESSAGE);
                }
            }
            else
                JOptionPane.showMessageDialog(null,"Error. \nNo se puede calcular nada debido a que no has introducido ningún dato de las variables."
                ,"Error - Función",JOptionPane.ERROR_MESSAGE);

        }

        else if(e.getSource() == limpiar){
            funcion.setText(" Función");
            grado.setText(" Grado");
            variables.setText(" variables");
            metodo.setText(" Método");
            x.setText(" x");
            h.setText(" h");
            num = null;
            h1 = null;
            grado1 = null;
            band = 0;
            grados.setText(" Grado");
            error.setText(" Error");
            soluciones.setClear("");
            iteraciones.setClear("");
        }
    }
}
