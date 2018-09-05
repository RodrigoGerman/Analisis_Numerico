package vista.panels.theme4.tb;

import vista.panels.theme4.tb.PanelMethodsTheme4TB;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class PanelMethodTB extends PanelMethodsTheme4TB{

    protected ArrayList<ArrayList<Double>> func_list = null;
    
    public PanelMethodTB(){}

    public PanelMethodTB(Color fondo,String metodo){
        super(fondo,metodo);
    }

    protected final boolean getEmptyFields(){

        if(func_list == null){
            JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una funcion tabular"
            +" por favor introduzca una función tabular.", "Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        else if(x.getText().trim().equals(" x")){
            JOptionPane.showMessageDialog(null,"Error. \n No se puede calcular nada debido a que no a introducido una x a buscar"
            +" por favor introduzca una x a buscar.","Error - App Análisis Numérico",JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    protected final void setPanelDatos(){
        panel_datos.setBackground(fondo);
        panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

        titulo_funciontb.setFont(titulo); 
        titulo_funciontb.setForeground(titulos);

        titulo_puntos.setFont(titulo); 
        titulo_puntos.setForeground(titulos);
        titulo_x.setFont(titulo); 
        titulo_x.setForeground(titulos);

        calcular.setBackground(titulos);
        calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

        limpiar.setBackground(titulos);
        limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        generar_funciontb.setBackground(titulos);
        generar_funciontb.setBorder(BorderFactory.createLineBorder(fondo,2));

        x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        num_puntos.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);
        panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(formulas)
                        .addContainerGap())
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(titulo_funciontb))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_puntos, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num_puntos, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(calcular, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(limpiar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo_x)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(generar_funciontb,GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                            .addComponent(x, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(formulas, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_funciontb)
                .addGap(23, 23, 23)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_puntos)
                    .addComponent(num_puntos, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(generar_funciontb,GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo_x)
                    .addComponent(x, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular,GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar,GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}