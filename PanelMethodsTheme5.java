package vista.panels.theme5;

import vista.panels.PanelMethodsAnalisisNumerico;
import vista.panels.PanelTextArea;
import vista.panels.PanelEditorPane;
import vista.accesories.TextFieldNumber;
import vista.accesories.TextFieldString;
import vista.accesories.TextFieldFuncion;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.JLabel;
import java.awt.Color;

public abstract class PanelMethodsTheme5 extends PanelMethodsAnalisisNumerico{

    protected final JLabel titulo_funcion = new JLabel("Ecuación Diferencial:");
    protected final JLabel titulo_x = new JLabel("X a buscar:");
    protected final JLabel titulo_h = new JLabel("H:");
    protected JLabel titulo_n = new JLabel("Numero de Divisiones:");
    protected final JLabel titulo_condiciones = new JLabel("Condiciones Iniciales:");
    protected final JLabel titulo_y = new JLabel("Y(x):");
    protected final JLabel titulo_val = new JLabel("X:");

    protected final TextFieldFuncion funcion= new TextFieldFuncion(" Ecuación Diferencial",Color.BLACK);
    protected final TextFieldNumber x = new TextFieldNumber(" x",Color.BLACK,1);
    protected final TextFieldNumber n = new TextFieldNumber(" Diviciones",Color.BLACK,1);
    protected final TextFieldNumber h = new TextFieldNumber(" h",Color.BLACK,1);
    protected final TextFieldNumber y = new TextFieldNumber(" y",Color.BLACK,1);
    protected final TextFieldNumber val = new TextFieldNumber(" x",Color.BLACK,1);

    protected PanelTextArea soluciones;

    public PanelMethodsTheme5(){}

    public PanelMethodsTheme5(Color fondo,String metodo){
        super(fondo);
        informacion = new PanelEditorPane(metodo,225,587,fondo,titulos,titulo);
        iteraciones = new PanelTextArea("Iteraciones",225,587,fondo,titulos,titulo);
        soluciones = new PanelTextArea("Soluciones",262,183,fondo,titulos,titulo);
        formulas = new PanelEditorPane("Formulas",100,260,fondo,titulos,titulo);
    }

    protected abstract boolean getFieldsValids();

    protected abstract boolean getEmptyFields();

    protected void setAllElements(){
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
                    .addComponent(soluciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(soluciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(informacion, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iteraciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }
}