import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImpuestosDatos extends JPanel {
	
	private JTextField tfNombre,
						tfRFC,
						tfSueldoMensual,
						tfAguinaldo,
						tfPrimaVacacional,
						tfMedicosHospitales,
						tfFunerarios,
						tfSGMM,
						tfDonativos,
						tfHipoteca,
						tfRetiro,
						tfTransporteEscolar,
						tfColegiaturaTotal;
	
	private JButton btCalcular;
	
	private JComboBox cbNivelEscolar;
	
	private String[] datos =new String[14];
	
	

	public ImpuestosDatos() {
		super();
		String[] educacion= {"Ninguno","Preescolar","Primaria","Secundaria","Profesional técnico","Preparatoria"};
		this.setLayout(new GridLayout(15,2,10,10));
		this.setPreferredSize(new Dimension(400,600));
		this.tfNombre=new JTextField(10);
		this.tfRFC=new JTextField(10);
		this.tfSueldoMensual=new JTextField(10);
		this.tfAguinaldo=new JTextField(10);
		this.tfPrimaVacacional=new JTextField(10);
		this.tfMedicosHospitales=new JTextField(10);
		this.tfFunerarios=new JTextField(10);
		this.tfSGMM=new JTextField(10);
		this.tfHipoteca=new JTextField(10);
		this.tfDonativos=new JTextField(10);
		this.tfRetiro=new JTextField(10);
		this.tfTransporteEscolar=new JTextField(10);
		this.tfColegiaturaTotal=new JTextField(10);
		this.btCalcular=new JButton("Calcular");
		
		this.cbNivelEscolar=new JComboBox(educacion);
		
		this.btCalcular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validacion();
					hacerArray();
					CalculoImpuestos ci=new CalculoImpuestos(datos);
				}
				catch(Exception arg0) {
					JOptionPane.showMessageDialog(null, "Los valores estan mal ingresados. Ingreselos de nuevo");
				}
			}
			
		});
		
		this.add(new JLabel("Nombre: "));
		this.add(this.tfNombre);
		this.add(new JLabel("RFC: "));
		this.add(this.tfRFC);
		this.add(new JLabel("Sueldo Mensual: "));
		this.add(this.tfSueldoMensual);
		this.add(new JLabel("Aguinal Recibido: "));
		this.add(this.tfAguinaldo);
		this.add(new JLabel("Prima Vacacional Recibida: "));
		this.add(this.tfPrimaVacacional);
		this.add(new JLabel("Gastos Medicos y Hospitales: "));
		this.add(this.tfMedicosHospitales);
		this.add(new JLabel("Gastos Funerarios: "));
		this.add(this.tfFunerarios);
		this.add(new JLabel("Gastos Medicos Mayores: "));
		this.add(this.tfSGMM);
		this.add(new JLabel("Hipoteca: "));
		this.add(this.tfHipoteca);
		this.add(new JLabel("Donativos: "));
		this.add(this.tfDonativos);
		this.add(new JLabel("Aporto al Retiro: "));
		this.add(this.tfRetiro);
		this.add(new JLabel("Transporte Escolar: "));
		this.add(this.tfTransporteEscolar);
		this.add(new JLabel("Nivel Educativo: "));
		this.add(this.cbNivelEscolar);
		this.add(new JLabel("Colegiatura Total: "));
		this.add(this.tfColegiaturaTotal);
		this.add(this.btCalcular);
		
	}
	
	public void validacion() throws Exception {
		int validacion;
		if(!this.tfSueldoMensual.getText().equals("")) {
			validacion=Integer.parseInt(this.tfSueldoMensual.getText());
		}
			
		if(!this.tfAguinaldo.getText().equals("")) {
			validacion=Integer.parseInt(this.tfAguinaldo.getText());
		}
		if(!this.tfPrimaVacacional.getText().equals("")) {
			validacion=Integer.parseInt(this.tfPrimaVacacional.getText());
		}
		
		if(!this.tfMedicosHospitales.getText().equals("")) {
			validacion=Integer.parseInt(this.tfMedicosHospitales.getText());
		}
		if(!this.tfFunerarios.getText().equals("")) {
			validacion=Integer.parseInt(this.tfFunerarios.getText());
		}
		if(!this.tfSGMM.getText().equals("")) {
			validacion=Integer.parseInt(this.tfSGMM.getText());
		}
		
		if(!this.tfHipoteca.getText().equals("")) {
			validacion=Integer.parseInt(this.tfHipoteca.getText());
		}
		if(!this.tfDonativos.getText().equals("")) {
			validacion=Integer.parseInt(this.tfDonativos.getText());
		}
		if(!this.tfRetiro.getText().equals("")) {
			validacion=Integer.parseInt(this.tfRetiro.getText());
		}
		if(!this.tfTransporteEscolar.getText().equals("")) {
			validacion=Integer.parseInt(this.tfTransporteEscolar.getText());
		}
		if(!this.tfColegiaturaTotal.getText().equals("")) {
			validacion=Integer.parseInt(this.tfColegiaturaTotal.getText());
		}
	}
	
	public void hacerArray() {
		this.datos[0]=this.tfNombre.getText();
		this.datos[1]=this.tfRFC.getText();
		this.datos[2]=this.tfSueldoMensual.getText();
		if(this.datos[2].equals("")||this.datos[2]==null) {
			this.datos[2]="0";
		}
		this.datos[3]=this.tfAguinaldo.getText();
		if(this.datos[3].equals("")||this.datos[3]==null) {
			this.datos[3]="0";
		}
		this.datos[4]=this.tfPrimaVacacional.getText();
		if(this.datos[4].equals("")||this.datos[4]==null) {
			this.datos[4]="0";
		}
		this.datos[5]=this.tfMedicosHospitales.getText();
		if(this.datos[5].equals("")||this.datos[5]==null) {
			this.datos[5]="0";
		}
		this.datos[6]=this.tfFunerarios.getText();
		if(this.datos[6].equals("")||this.datos[6]==null) {
			this.datos[6]="0";
		}
		this.datos[7]=this.tfSGMM.getText();
		if(this.datos[7].equals("")||this.datos[7]==null) {
			this.datos[7]="0";
		}
		this.datos[8]=this.tfHipoteca.getText();
		if(this.datos[8].equals("")||this.datos[8]==null) {
			this.datos[8]="0";
		}
		this.datos[9]=this.tfDonativos.getText();
		if(this.datos[9].equals("")||this.datos[9]==null) {
			this.datos[9]="0";
		}
		this.datos[10]=this.tfRetiro.getText();
		if(this.datos[10].equals("")||this.datos[10]==null) {
			this.datos[10]="0";
		}
		this.datos[11]=this.tfTransporteEscolar.getText();
		if(this.datos[11].equals("")||this.datos[11]==null) {
			this.datos[11]="0";
		}
		this.datos[12]=(String) this.cbNivelEscolar.getSelectedItem();
		
		this.datos[13]=this.tfColegiaturaTotal.getText();
		if(this.datos[13].equals("")||this.datos[13]==null) {
			this.datos[13]="0";
		}
		
	}
	
}
