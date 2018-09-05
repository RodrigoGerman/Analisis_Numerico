package controlador.theme2.openmethods;

import vista.panels.theme2.PanelOpenMethods;
import modelo.theme2.openmethods.NewtonRaphson;
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

public final class PanelNewthonRaphson extends PanelOpenMethods implements ActionListener{

    private NewtonRaphson newton_raphson;

    private final JLabel titulo_valor_aprox = new JLabel("Valor Aproximado:");
    private final JLabel titulo_firts_derived = new JLabel("Primera Derivada:");
    private final JLabel titulo_second_derived = new JLabel("Segunda Derivada:");
    private final TextFieldFuncion firts_derived = new TextFieldFuncion(" Primera Derivada",Color.BLACK);
    private final TextFieldFuncion second_derived = new TextFieldFuncion(" Segunda Derivada",Color.BLACK);

    public PanelNewthonRaphson(){
        super(new Color(96, 186, 107),"Método de Newthon-Raphson");
        setPanelDatos();
        setAllElements();
        setInformacion();
        calcular.addActionListener(this);
        limpiar.addActionListener(this);
    }

    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));
        panel_datos.setForeground(new Color(235, 235, 238));
        panel_datos.setToolTipText("");

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);

        titulo_estimacion.setFont(titulo); 
        titulo_estimacion.setForeground(titulos);

        titulo_valor_aprox.setFont(titulo); 
        titulo_valor_aprox.setForeground(titulos);

        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));
        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        titulo_firts_derived.setFont(titulo); 
        titulo_firts_derived.setForeground(titulos);

        titulo_second_derived.setFont(titulo); 
        titulo_second_derived.setForeground(titulos);

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
                    .addComponent(estimacion)
                    .addComponent(second_derived)
                    .addComponent(valor_aprox)

                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(titulo_funcion)
                            .addComponent(titulo_firts_derived)
                            .addComponent(titulo_second_derived)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                            .addComponent(titulo_valor_aprox)
                            .addComponent(titulo_estimacion))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(titulo_firts_derived)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firts_derived, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo_second_derived)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(second_derived, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_valor_aprox)
                .addGap(10, 10, 10)
                .addComponent(valor_aprox,GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo_estimacion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(estimacion, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }


    @Override
    public void actionPerformed(ActionEvent e){ 
        double valor_estimacion,aprox;
        String func="",der="",der2="";
        if(e.getSource() == calcular){
            //validar campos
            if(getEmptyFields() && getFieldsValids()){
                aprox = Double.parseDouble(valor_aprox.getText());
                valor_estimacion = Double.parseDouble(estimacion.getText());
                func = (funcion.getText()).trim();
                der = (firts_derived.getText()).trim();
                der2 = (second_derived.getText()).trim();
                newton_raphson = new NewtonRaphson(func,der,der2,valor_estimacion);
                if(valor_estimacion != 0.0 && valor_estimacion > 0.000000000000001){
                    newton_raphson.setAproximacion(aprox);
                    iteraciones.setClear("    Interacion\t    |\tValor aproximado\t|               Tolerancia\n\n");
                    iteraciones.setText(newton_raphson.getIteraciones());
                    iteraciones.setText("\nRaiz:  " + String.valueOf(newton_raphson.getRaiz()));
                    raiz.setText(" " + String.valueOf(newton_raphson.getRaiz()));
                }
                else
                    JOptionPane.showMessageDialog(null,"Error. Lo sentimos pero la estimacion es demasiada pequeña, el programa"+
                    "\nno soporta esa cantidad de puntos decimales, por favor introduzca una mayor a 0.000000000000001."
                    ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == limpiar){
            funcion.setText(" Función");
            firts_derived.setText(" Primera Derivada");
            second_derived.setText(" Segunda Derivada ");
            estimacion.setText(" Estimacion");
            valor_aprox.setText(" Valor Aproximado");
            raiz.setText("");
            iteraciones.setClear("    Interacion\t    |\tValor aproximado\t|               Tolerancia\n\n");
        }
    }


    public boolean getFieldsValids(){
        if(!super.getFieldsValids())
            return false;

        if(!valor_aprox.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un valor aproximado valido por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!firts_derived.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu primera derivada tiene errores sintacticos revisela por favor."
            ,"Error - Derivada",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!second_derived.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu segunda derivada tiene errores sintacticos revisela por favor."
            ,"Error - Derivada",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean getEmptyFields(){
        if(!super.getEmptyFields())
            return false;

        if((valor_aprox.getText()).equals(" Valor Aproximado")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un valor aproximado por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if((firts_derived.getText()).equals(" Primera Derivada")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese la primera derivada de la función por favor."
            ,"Error - Derivada",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if((second_derived.getText()).equals(" Segunda Derivada")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese la segunda derivada de la función por favor."
            ,"Error - Derivada",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private final void setInformacion(){
        str_formulas = "<font size = 5 COLOR = red>Convergencia: (f(x)*f''(x))/[f'(x)]^2 &#60 1 <br>Aproximación: x0 = x - f(x)/f'(x) </font><br>";
        formulas.setInfo(str_formulas);
        info = "<html><body><h2 ALIGN = center>Metodo de Newthon-Raphson</h2>"
        +"<font size = 3.9 face = Times New Roman><P ALIGN = JUSTIFY>El metodo de Newthon-Raphson(N-R) es, junto con"
        +"bisección, uno de los más populares. Su preferencia radica en su robustez y velocidad al encontrar la raíz. " 
        +"Por ser un método abierto o de punto fijo, debe verificarse su criterio de equivalencia. Quizá el único punto "
        +"que pudiera tener en contra radica en la necesidad de contar con la primera y segunda derivada de la ecuación a "
        +"resolver. Se aplica a ecuaciones algebraicas y trascendentes y proporciona raíces reales.<br>"
        +"<font size = 5><P>Definición del Método</P></font>"
        +"<br>A partir de una función algebraica o trascendente y de un intervalo <B>[a,b]</B> que pertenece al "
        +"dominio de la función y para el cual:<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> f(a) * f(b) &#60 0 </P></font><br>"
        +"<br>lo que implica que en el intervalo <B>[a,b]</B> existe al menos una raíz. El metodo consiste en bisectar "
        +"el intervalo <B>[a,b]</B>:<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> x0 = ( a + b ) / 2</P></font><br>"
        +"<br>Obteniendo una aproximación a la raíz x0; la función se valúa en este nuevo valor y de acuerdo al signo de"
        +"la funcion valuada en este punto,debera sustituirse uno de los extremos del intervalo de busqueda, de tal forma"
        +"que se conserve que:<br>"
        +"<font size = 5 COLOR = red><P ALIGN = center> f(a) * f(b) &#60 0 </P></font><br>"
        +"<br>De acuerdo a la geometría de la figura, la sustitución de los intervalos deberá hacerce de la siguiente forma:<br>"
        +"<br>Sea a tal que f(a) > 0 y b tal que f(b) &#60 0:"
        +"<br><br>&nbsp &nbsp &nbsp .-Si f(x0) > 0 entonces x0 sustituye a 'a'."
        +"<br><br>&nbsp &nbsp &nbsp .-Si f(x0) &#60  0, entonces x0 sustituye a 'b'."
        +"<br><br>En cada intervalo debera sustituirse alguno de los limites del intervalo que contiene a la raíz. Repitiendo "
        +"este proceso, el intervalo se reduce paulativamente hasta que alguna de las aproximaciones coincide razonablemente "
        +"con la raiz de la función.El proceso se detiene cuando la aproximación <B>xi</B> y la aproximación <B>xi-1</B> "
        +"se satisface un nivel de error preestablecido.<br><br></P></body></html>";
        informacion.setInfo(info);
    }
}