
package controlador.theme2.openmethods;

import vista.panels.theme2.PanelOpenMethods;
import modelo.theme2.openmethods.AproximacionesSucesivas;
import vista.accesories.TextFieldFuncion;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
    * La clase PanelAproxSucesivas es una clase que hereda de PanelOpenMethods debido a que es un panel de métodos abiertos de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar el método numérico de Aproximaciones sucesivas, se encarga de crear un panel específico para la visualización
    * del método numérico de Aproximaciones Sucesivas.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public final class PanelAproxSucesivas extends PanelOpenMethods implements ActionListener{
    /** * Variable tipo privada AproximacionesSucesivas que representa el método de Aproximaciones Sucesivas.*/
    private AproximacionesSucesivas aprox_sucesivas;
    /** * Variable tipo JLabel privada y final, que representa el titulo para el valor aproximado mostrado en el panel.*/ 
    private final JLabel titulo_valor_aprox = new JLabel("Valor Aproximado:");
    /** * Variable tipo JLabel privada y final, que representa el titulo para la derivada de la función mostrado en el panel.*/ 
    private final JLabel titulo_firts_derived = new JLabel("Derivada Función:");
    /** * Variable tipo TextFieldFuncion privada y final, que representa el campo para que el usuario pueda introducir la derivada de la función.*/
    private final TextFieldFuncion firts_derived = new TextFieldFuncion(" Derivada",Color.BLACK);

    /** * Método constructor que crea un PanelAproxSucesivas.*/
    public PanelAproxSucesivas(){
        super(new Color(98,33,47),"Método de Aproximaciones Sucesivas");
        setPanelDatos();
        setInformacion();
        setAllElements();
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    /** * Método heredado de PanelOpenMethods que se encarga de controlar la distribución de los compontes visuales del PanelAproxSucesivas.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));
        panel_datos.setForeground(new Color(235, 235, 238));
        panel_datos.setToolTipText("");

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);

        titulo_estimacion.setFont(titulo); 
        titulo_estimacion.setForeground(titulos);
       
        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));
        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        titulo_firts_derived.setFont(titulo); 
        titulo_firts_derived.setForeground(titulos);

        titulo_valor_aprox.setFont(titulo); 
        titulo_valor_aprox.setForeground(titulos);

        firts_derived.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        valor_aprox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);
        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(formulas)
                    .addComponent(funcion)
                    .addComponent(firts_derived)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(titulo_funcion)
                            .addComponent(titulo_firts_derived)
                            .addComponent(titulo_valor_aprox)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                            .addComponent(titulo_estimacion))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(estimacion)
                    .addComponent(valor_aprox))
                .addContainerGap())
        );
        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(titulo_firts_derived)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firts_derived, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_valor_aprox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valor_aprox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo_estimacion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(estimacion, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }

    /** * Método que se implementa de la interfaz ActionListener para realizar diferentes acciones dependiendo del
    componente que las ocasione.*/
    @Override
    public void actionPerformed(ActionEvent e){
        double valor_estimacion,aprox;
        String func="";
        if(e.getSource() == calcular){
            if(getEmptyFields() && getFieldsValids()){
                aprox = Double.parseDouble(valor_aprox.getText());
                valor_estimacion = Double.parseDouble(estimacion.getText());
                func = (funcion.getText()).trim();
                aprox_sucesivas = new AproximacionesSucesivas(func,(firts_derived.getText()).trim(),valor_estimacion);
                if(valor_estimacion != 0.0 && valor_estimacion > 0.000000000000001){
                    aprox_sucesivas.setAproximacion(aprox);
                    if(aprox_sucesivas.getDiverge())
                        JOptionPane.showMessageDialog(null,"Error. \nLa función " + func + " con el valor aproximado "
                        + aprox + " diverge\n, por favor introduzca otra función G(x) o otro valor de aproximación."
                            ,"Error - Divergencia",JOptionPane.ERROR_MESSAGE);
                    else{
                        iteraciones.setClear("    Interacción\t    |\tValor aproximado\t|               Tolerancia\n\n");
                        iteraciones.setText(aprox_sucesivas.getIteraciones());
                        iteraciones.setText("\nRaiz:  " + String.valueOf(aprox_sucesivas.getRaiz()));
                        raiz.setText(" " + String.valueOf(aprox_sucesivas.getRaiz()));
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Error. Lo sentimos pero la estimación es demasiada pequeña, el programa"+
                    "\nno soporta esa cantidad de puntos decimales, por favor introduzca una mayor a 0.000000000000001."
                    ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == limpiar){
            funcion.setText(" Función");
            estimacion.setText(" Estimación");
            valor_aprox.setText(" Valor Aproximado");
            firts_derived.setText(" Derivada");
            raiz.setText("");
            iteraciones.setClear("    Interacción\t    |\tValor aproximado\t|               Tolerancia\n\n");
        }
    }

    /** * Método final heredado de PanelMethodsTheme2 que se encarga de validar si los campos a llenar en el PanelAproxSucesivas son validos.*/
    protected boolean getFieldsValids(){
        if(!super.getFieldsValids())
            return false;

        if(!valor_aprox.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un valor aproximado valido por favor."
            ,"Error - Función",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(!firts_derived.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu derivada tiene errores sintacticos revisela por favor."
            ,"Error - Derivada",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /** * Método final heredado de PanelMethodsTheme2 que se encarga de validar si los campos a llenar en el PanelAproxSucesivas no están vacíos.*/
    protected boolean getEmptyFields(){
        if(!super.getEmptyFields())
            return false;

        if((valor_aprox.getText()).equals(" Valor Aproximado")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un valor aproximado por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if((firts_derived.getText()).equals(" Derivada")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese la derivada de la función por favor."
            ,"Error - Derivada",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /** * Método privado setInformacion que se encarga de insertar el texto de información sobre el método de aproximaciones Sucesivas en el PanelAproxSucesivas.*/
    private final void setInformacion(){
        str_formulas = "<font size = 4>Convergencia: dG(x0) &#60 1 <br>Aproximación: x0 = G(x0) </font><br>";
        formulas.setInfo(str_formulas);
        info = "<html><body><h2 ALIGN = center>Método de Aproximaciones Sucesivas</h2>"
        +"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>El método de Aproximaciones Sucesivas representa "
        +"la esencia de los procesos iterativos ya que se permite definir una ecuación de recurrencia que, en apariencia, " 
        +"no tiene sentido desde el punto de vista algebraico, pero que resulta muy atinada si se toma un valor inicial " 
        +"y se mejora a través de las iteraciones.<br>"
        +"<font size = 5><P>Definición del Método</P></font>"
        +"<br>Aproximaciones Sucesivas es un método abierto, es decir, no necesita de un intervalo que atrape la raíz, sino "
        +"que requiere de un valor <B>x0</B> que representa un valor aproximado a la raíz y cuya cercanía al valor real "
        +"dependerá la velocidad en que se cumpla con una Tolerancia preestablecida.<br>Una forma sencilla de definir el "
        +"método de Aproximaciones Sucesivas consiste en despejar de una ecuación a la variable independiente; esto se aplica "
        +"particularmente en ecuaciones que por su forma no permiten despejar fácilmente a la incógnita.<br>"
        +"<font size = 5><P>Ejemplo:</P></font>"
        +"<br>En la ecuación <B COLOR = red > x^2+7*x-e^x </B> no puedo lograrse un despeje sencillo, algebraicamente hablando."
        +"Desde un punto de vista iterativo, la ecuación puede expresarse como:<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> x^2+7*x-e^x= 0 => x=(e^x-x^2)/7 </P></font><br>"
        +"<br>En efecto, algebraicamente hablando, el despeje anterior no aporta mejora en la solución de la ecuación. Sin embargo "
        +",si se define en forma iterativa:<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> Xi+1=(e^Xi-Xi^2)/7 </P></font><br>"
        +"<br>Donde <B>Xi</B> es un valor inicial y <B>Xi+1</B> es un valor corregido que , en un escenario favorable, tendrá una "
        +"cantidad de error menor con respecto a la raíz de la ecuación. El proceso interativo se detendrá cuando entre dos "
        +"aproximaciones sucesivas se satisfaga la tolerancia preestablecida.<br><br></P></body></html>";
        informacion.setInfo(info);
    }
}
