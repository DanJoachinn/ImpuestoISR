import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Instrucciones extends JPanel implements ActionListener {
	
	private JButton btArchivo;
	private JFileChooser fcSubir;
	private File datos;
	private CalculoImpuestos calculacion;
	
	
	public Instrucciones() {
		super();
		this.btArchivo=new JButton("Subir archivo");
		this.btArchivo.setSize(new Dimension(70,800));
		this.setPreferredSize(new Dimension(800,600));
		
		this.fcSubir=new JFileChooser();
		this.fcSubir.setAcceptAllFileFilterUsed(false);
		this.fcSubir.setFileFilter(new FileNameExtensionFilter("CSV File","csv","comma separated value"));
		this.fcSubir.setCurrentDirectory(new java.io.File("."));
		
		this.add(btArchivo);
		this.btArchivo.addActionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Este programa calcula los impuestos que se deben de pagar despues de la deducción de impuestos." ,50,50);
		g.drawString("Del lado izquierdo puedes introducir los datos necesarios uno por uno o en la parte de arriba esta la opción de seleccionar el",50,70);
		g.drawString("archivo csv que contiene de los datos", 50, 90);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fcSubir.showOpenDialog(this);
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.datos = fcSubir.getSelectedFile();
            CalculoImpuestos ci=new CalculoImpuestos(this.datos);
        }
	}
}
