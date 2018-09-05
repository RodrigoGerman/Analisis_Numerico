package vista.panels;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;

public final class PanelTextArea extends JPanel{

	private static Color color_titulos = new Color(235, 235, 238);
	private static Font font_titulos = new Font("Times New Roman", 1, 18);
	private static Color color_bordes = new Color(235, 235, 235);
	private static Color color_fondo = new Color(76, 123, 171);

	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll_testArea = new JScrollPane();

	public PanelTextArea(){}

	public PanelTextArea(String titulo,int height,int width){
		this(titulo,height,width,color_fondo,color_bordes,font_titulos);
	}

	public PanelTextArea(String titulo,int height,int width,Color fondo,Color bordes,Font texto){
		this.setBackground(fondo);
		this.setBorder(BorderFactory.createTitledBorder(null,titulo,TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,texto,bordes));
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textArea.setEditable(false);
		scroll_testArea.setViewportView(textArea);

		GroupLayout panel_Layout = new GroupLayout(this);
		this.setLayout(panel_Layout);

		panel_Layout.setHorizontalGroup(
		panel_Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING, panel_Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scroll_testArea, GroupLayout.DEFAULT_SIZE,width, Short.MAX_VALUE)
			.addContainerGap())
		);

		panel_Layout.setVerticalGroup(
		panel_Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING,panel_Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scroll_testArea, GroupLayout.DEFAULT_SIZE,height, Short.MAX_VALUE)
			.addContainerGap())
		);
	}

	public void setText(String texto){
		textArea.append(texto);
	}

	public void setClear(String texto){
		textArea.setText(texto);
	}
}