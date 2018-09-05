
package vista.panels.theme2;

import vista.accesories.TextFieldNumber;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

/**
    * La clase PanelBiseccion es una clase que hereda de PanelMethodsTheme2 debido a que es un panel de métodos del tema 2 de análisis numérico, 
    * se encarga de crear un panel específico para la visualización de los métodos cerrados del tema 2 de análisis numérico.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelClosedMethods extends PanelMethodsTheme2{
    /** * Variable tipo JLabel privada y final, que representa el título para el intervalo mostrado en el panel.*/ 
    protected final JLabel titulo_intervalo = new JLabel("Intervalo:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de a mostrado en el panel. */ 
    protected final JLabel titulo_a = new JLabel("a:");
    /** * Variable tipo JLabel privada y final, que representa el título para el valor de b mostrado en el panel.*/ 
    protected final JLabel titulo_b = new JLabel("b:");
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de a del intervalo.*/
    protected final TextFieldNumber a = new TextFieldNumber(" a",Color.BLACK,1);
    /** * Variable tipo TextFieldNumber privada y final, que representa el campo para que el usuario pueda introducir el valor de b del intervalo.*/
    protected final TextFieldNumber b = new TextFieldNumber(" b",Color.BLACK,1);

    /** * Método constructor que crear un PanelClosedMethods pero sin ninguna comportamiento ni estilo especifico.*/
    public PanelClosedMethods(){}

    /** * Método constructor que crea un PanelClosedMethods, recibe el color del fondo para el panel y una String que representa el método que va a contener.*/
    public PanelClosedMethods(Color fondo,String metodo){
        super(fondo,metodo);
    }

    /** * Método protected y final que se encarga de controlar la distribución de los compontes visuales del PanelClosedMethods.*/
    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funcion.setFont(titulo); 
        titulo_funcion.setForeground(titulos);
        funcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        titulo_intervalo.setFont(titulo); 
        titulo_intervalo.setForeground(titulos);
        titulo_a.setFont(titulo); 
        titulo_a.setForeground(titulos);
        titulo_b.setFont(titulo); 
        titulo_b.setForeground(titulos);
        a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        titulo_estimacion.setFont(titulo); 
        titulo_estimacion.setForeground(titulos);
        estimacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));
        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);

        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(estimacion)
                        .addComponent(funcion,GroupLayout.Alignment.TRAILING)
                        .addComponent(formulas)
                        .addGroup(panel_datosLayout.createSequentialGroup()
                            .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titulo_funcion)
                                .addComponent(titulo_intervalo)
                                .addComponent(titulo_estimacion))
                            .addGap(0,0,Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING,panel_datosLayout.createSequentialGroup()
                            .addGap(0,0,Short.MAX_VALUE)
                            .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING,false)
                                .addGroup(panel_datosLayout.createSequentialGroup()
                                    .addComponent(titulo_a)
                                    .addGap(18, 18, 18)
                                    .addComponent(a))
                                .addGroup(panel_datosLayout.createSequentialGroup()
                                    .addComponent(titulo_b)
                                    .addGap(18, 18, 18)
                                    .addComponent(b,GroupLayout.DEFAULT_SIZE,87,Short.MAX_VALUE)))
                            .addGap(148,148,148))))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap(56,Short.MAX_VALUE)
                        .addComponent(calcular,GroupLayout.PREFERRED_SIZE,81,GroupLayout.PREFERRED_SIZE)
                        .addGap(34,34,34)
                        .addComponent(limpiar,GroupLayout.PREFERRED_SIZE,81,GroupLayout.PREFERRED_SIZE)
                        .addGap(33,33,33)))
                .addContainerGap())
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(funcion,GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titulo_intervalo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,11,Short.MAX_VALUE)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titulo_a)
                    .addComponent(a, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,26,Short.MAX_VALUE)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titulo_b)
                    .addComponent(b, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_estimacion)
                .addGap(18, 18, 18)
                .addComponent(estimacion, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(22,22,22))
        );
    }
       
    /** * Método final heredado de PanelMethodsTheme2 que se encarga de validar si los campos a llenar en el PanelClosedMethods no están vacíos.*/   
    protected final boolean getEmptyFields(){

        if((funcion.getText()).equals(" Función")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una Función por favor."
            ,"Error - Funcion",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if((a.getText()).equals(" a") || (b.getText()).equals(" b")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un intervalo por favor."
            ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if((estimacion.getText()).equals(" Estimación")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una tolerancia por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /** * Método final heredado de PanelMethodsTheme2 que se encarga de validar si los campos a llenar en el PanelClosedMethods son validos.*/
    protected final boolean getFieldsValids(){
        if(!funcion.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu funcion tiene errores sintacticos revisela por favor."
            ,"Error - Función",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!estimacion.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una estimación valida por favor."
            ,"Error - Tolerancia",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!a.getCorrectNumber() || !b.getCorrectNumber()){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un valor valido por favor."
            ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
            if(Double.parseDouble(a.getText()) == Double.parseDouble(b.getText())){
                JOptionPane.showMessageDialog(null,"Error. \nIngrese un intervalo valido por favor."
                ,"Error - Intervalo",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
    }
}
