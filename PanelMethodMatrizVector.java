package vista.panels.theme3;

import vista.panels.theme3.PanelMethodsTheme3;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class PanelMethodMatrizVector extends PanelMethodsTheme3{
	
	protected final JLabel titulo_matriz = new JLabel("Matriz:");
    protected final JLabel titulo_vector = new JLabel("Vector de T.I:");
    protected final JButton generar_vector = new JButton("Generar");;

	public PanelMethodMatrizVector(){}

	public PanelMethodMatrizVector(Color fondo,String metodo){
		super(fondo,metodo);
	}

	protected final void setPanelDatos(){
		panel_datos.setBackground(fondo);
		panel_datos.setBorder(BorderFactory.createTitledBorder(null,"Datos",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, titulo, titulos));

		titulo_matriz.setFont(titulo); 
		titulo_matriz.setForeground(titulos);
        titulo_vector.setFont(titulo); 
        titulo_vector.setForeground(titulos);

		titulo_renglones.setFont(titulo); 
		titulo_renglones.setForeground(titulos);
		titulo_columnas.setFont(titulo); 
		titulo_columnas.setForeground(titulos);

		calcular.setBackground(titulos);
		calcular.setBorder(BorderFactory.createLineBorder(fondo,2));

		limpiar.setBackground(titulos);
		limpiar.setBorder(BorderFactory.createLineBorder(fondo,2));

        generar_matriz.setBackground(titulos);
        generar_matriz.setBorder(BorderFactory.createLineBorder(fondo,2));

        generar_vector.setBackground(titulos);
        generar_vector.setBorder(BorderFactory.createLineBorder(fondo,2));

        renglones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        columnas.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_datosLayout = new GroupLayout(panel_datos);
        panel_datos.setLayout(panel_datosLayout);
         panel_datosLayout.setHorizontalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(formulas)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titulo_matriz)
                                .addGroup(panel_datosLayout.createSequentialGroup()
                                    .addComponent(titulo_renglones)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(renglones,GroupLayout.PREFERRED_SIZE, 88,GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_datosLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(titulo_columnas)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(columnas,GroupLayout.PREFERRED_SIZE, 87,GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generar_matriz))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addComponent(titulo_vector)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING,panel_datosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(generar_vector)
                .addGap(132, 132, 132))
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(calcular,GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panel_datosLayout.setVerticalGroup(
            panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_datosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(formulas,GroupLayout.PREFERRED_SIZE,150,GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo_matriz)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo_renglones)
                            .addComponent(renglones,GroupLayout.PREFERRED_SIZE, 22,GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo_columnas)
                            .addComponent(columnas,GroupLayout.PREFERRED_SIZE, 24,GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_datosLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(generar_matriz,GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(titulo_vector)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generar_vector,GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panel_datosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calcular,GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar,GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
        );
	}
}