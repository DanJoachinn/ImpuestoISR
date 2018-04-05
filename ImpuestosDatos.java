import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
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
						tfNivelEducativo,
						tfColegiaturaTotal;
	
	

	public ImpuestosDatos() {
		super();
		this.setLayout(new GridLayout(14,2,10,10));
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
		this.tfNivelEducativo=new JTextField(10);
		this.tfColegiaturaTotal=new JTextField(10);
		
		this.add(new JLabel("Nombre: "));
		this.add(this.tfNombre);
		this.add(new JLabel("RFC"));
		this.add(this.tfRFC);
		this.add(new JLabel("Sueldo Mensual"));
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
		this.add(this.tfNivelEducativo);
		this.add(new JLabel("Colegiatura Total: "));
		this.add(this.tfColegiaturaTotal);
		
	}
	
}
