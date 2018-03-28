import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Instrucciones extends JPanel {
	
	public Instrucciones() {
		super();
		this.setPreferredSize(new Dimension(800,600));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Este programa calcula los impuestos que se deben de pagar despues de la deducción de impuestos" ,50,50);
		g.drawString("\\nDel lado izquierdo puedes los datos necesarios uno por uno o en la parte de abajo esta la opción de seleccionar el archivo csv contenedor de los datos",50,60);
	}
	
}
