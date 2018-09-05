package vista.panels.theme2;

import vista.panels.PanelMethodsAnalisisNumerico;
import vista.accesories.TextFieldNumber;
import vista.accesories.TextFieldFuncion;
import vista.panels.PanelEditorPane;
import vista.panels.PanelTextArea;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public abstract class PanelMethodsTheme2 extends PanelMethodsAnalisisNumerico{

    protected final JTextField raiz = new JTextField(" Raíz");
    protected final JPanel panel_raiz = new JPanel();
    protected final JLabel titulo_estimacion = new JLabel("Error de Estimación:");
    protected final JLabel titulo_funcion = new JLabel("Función:");
    protected final JLabel titulo_raiz = new JLabel("Raíz aproximada:");
    protected final TextFieldNumber estimacion = new TextFieldNumber(" Estimacion",Color.BLACK,0);
    protected TextFieldFuncion funcion = new TextFieldFuncion(" Función",Color.BLACK);

    public PanelMethodsTheme2(){}

    public PanelMethodsTheme2(Color fondo,String metodo){
        super(fondo);
        informacion = new PanelEditorPane(metodo,225,587,fondo,titulos,titulo);
        iteraciones = new PanelTextArea("Iteraciones",225,587,fondo,titulos,titulo);
        formulas = new PanelEditorPane("Formulas",87,260,fondo,titulos,titulo);
        iteraciones.setText("    Interacción\t    |\tValor aproximado\t|               Tolerancia\n\n");
        raiz.setEditable(false);
    }

    protected void setAllElements(){
        setPanelRaiz();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(informacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iteraciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_datos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_raiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24,24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_datos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_raiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(informacion, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iteraciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }

    protected final void setPanelRaiz(){
        panel_raiz.setBackground(fondo);
        panel_raiz.setBorder(BorderFactory.createTitledBorder(null,"Raiz",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,titulo,titulos));

        titulo_raiz.setFont(titulo);
        titulo_raiz.setForeground(titulos);
        raiz.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout panel_raizLayout = new GroupLayout(panel_raiz);
        panel_raiz.setLayout(panel_raizLayout);

        panel_raizLayout.setHorizontalGroup(
            panel_raizLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_raizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(raiz)
                .addContainerGap())
            .addGroup(panel_raizLayout.createSequentialGroup()
                .addComponent(titulo_raiz)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_raizLayout.setVerticalGroup(
            panel_raizLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_raizLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titulo_raiz, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(raiz, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    protected abstract boolean getEmptyFields();

    protected abstract boolean getFieldsValids();
}