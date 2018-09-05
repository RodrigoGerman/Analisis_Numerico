package vista.panels.theme5.ed;

import vista.panels.theme5.PanelMethodsTheme5;

import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
    * La clase PanelMethodED es una clase que hereda de PanelMethodsTheme5 debido a que es un panel de métodos de análisis numérico e implementa
    * la interfaz ActionListener para ejecutar métodos numéricos sobre ecuaciones diferenciales se encarga de crear un panel específico para la visualización
    * para métodos que manejen ecuaciones diferenciales.
    * @author German López Rodrigo
    * @version 13/05/2017/Final 
*/
public class PanelMethodED extends PanelMethodsTheme5{

    /** * Método constructor que crear un PanelMethodED pero sin ninguna comportamiento ni estilo especifico. */
    public PanelMethodED(){}

    /** * Método constructor que crea un PanelMethodED, recibe el color del fondo para el panel y una String que representa el método que va a contener. */
    public PanelMethodED(Color fondo,String metodo){
        super(fondo,metodo);
    }

    /** * Método final heredado de PanelMethodsTheme5 que se encarga de validar si los campos a llenar en el PanelMethodED no están vacíos.*/
    protected final boolean getEmptyFields(){

        if((funcion.getText()).equals(" Ecuación Diferencial")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una Ecuación Diferencial por favor."
            ,"Error - Ecuación Diferencial",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((x.getText()).equals(" x") || (x.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Error. \nIngrese una x a buscar valida por favor."
            ,"Error - Grado",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if((h.getText()).equals(" h") || (h.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún espaciamiento por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((n.getText()).equals(" Divisiones")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido el numero de Divisiones por favor introduzcaras."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((y.getText()).equals(" y") || (y.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún valor inicial por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        else if((val.getText()).equals(" x") || (val.getText()).equals(".")){
            JOptionPane.showMessageDialog(null,"Advertencia.\nNo ha introducido ningún valor inicial por favor introduzca uno."
            ,"Advertencia - Espasiamento",JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }

    /** * Método final heredado de PanelMethodsTheme5 que se encarga de validar si los campos a llenar en el PanelMethodED son validos.*/
    protected final boolean getFieldsValids(){

        if(!funcion.getCorrectFuncion()){
            JOptionPane.showMessageDialog(null,"Error. \nSu función tiene errores sintácticos revisela por favor."
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
            JOptionPane.showMessageDialog(null,"Error. \nIngrese un numero valido de divisiones por favor."
            ,"Error - Diviciones",JOptionPane.ERROR_MESSAGE);
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

    /** * Metodo protected y final que se encarga de controlar la distribucion de los compontes visuales del PanelMethodED.*/
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

        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        funcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        h.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        n.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        y.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        val.setBorder(BorderFactory.createLineBorder(Color.BLACK));


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
                        .addComponent(titulo_x)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(x)
                        .addGap(36, 36, 36))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_h)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(h, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_funcion))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(titulo_condiciones, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(titulo_n, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(n, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addComponent(titulo_y)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(y, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titulo_val)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(val, GroupLayout.PREFERRED_SIZE,50, GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(titulo_funcion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funcion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(h, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(titulo_h))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_x)
                    .addComponent(x, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(n, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_n))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_condiciones)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(y, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo_y)
                    .addComponent(titulo_val)
                    .addComponent(val, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );
    }
}