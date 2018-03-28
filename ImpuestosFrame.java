import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ImpuestosFrame extends JFrame {
	
	public ImpuestosFrame() {
		super("Deduccion de Impuestos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImpuestosDatos id=new ImpuestosDatos();
		Instrucciones instrucc=new Instrucciones();
		this.add(instrucc);
		this.add(id, BorderLayout.WEST);
		this.pack();
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		ImpuestosFrame programa=new ImpuestosFrame();
	}

}
