import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class CalculoImpuestos {
	
	private BufferedReader br;
	private PrintWriter pw;
	private String[] datosStr;
	
	private String nombre,
	               RFC,
	               nivelEscolar,
	               linea;
	
	private float sueldoMensual,
	            sueldoAnual,
	            aguinaldo,
	            aguinaldoExento,
	            aguinaldoGravado,
	            primaVacacional,
	            primaVacacionalExenta,
	            primaVacacionalGravada,
	            totalIngresosGravados,
	            medicosHospitales,
	            funerarios,
	            SGMM,
	            hipoteca,
	            donativos,
	            retiro,
	            transporteEscolar,
	            colegiatura,
	            maximoDeducibleColegiatura,
	            totalDeducciones,
	            deduccionPermitida,
	            totalPagar,
	            sueldoConDeduccion,
	            cuotaFija,
	            excedente,
	            porcentajeExcendete;

	public CalculoImpuestos(File expediente) {
		try {
			this.br=new BufferedReader(new FileReader(expediente));
			this.pw=new PrintWriter(new FileWriter("resultadosISR.csv"));
			this.linea=this.br.readLine();
			do{
				System.out.println(this.linea);
				this.datosStr=this.linea.split(",");
				Calcular(this.datosStr);
				total();
				crearArchivo();
				this.linea=this.br.readLine();
			}while(this.linea!=null);
			this.pw.close();
			this.br.close();
			JOptionPane.showMessageDialog(null, "El archivo se ha creado exitosamente");
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"No se pudo encontar el archivo");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Hubo un error con el archivo");
			
		}
	}
	
	public CalculoImpuestos(String [] datos) {
		try {
			this.pw=new PrintWriter(new FileWriter("resultadosISR.csv"));
			this.Calcular(datos);
			this.total();
			this.hacerResumen();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Hubo un error imprimiedo el documento.");
		}
	}
	
	public void Calcular(String[] datos) {
		this.nombre=datos[0];
		this.RFC=datos[1];
		this.sueldoMensual=Float.parseFloat(datos[2]);
		this.sueldoAnual=this.sueldoMensual*12;
		this.aguinaldo=Float.parseFloat(datos[3]);
		if(this.aguinaldo>(this.sueldoMensual/2)) {
			this.aguinaldoExento=this.sueldoMensual/2f;
			this.aguinaldoGravado=this.aguinaldo-this.aguinaldoExento;
		}
		else {
			this.aguinaldoExento=this.aguinaldo;
			this.aguinaldoGravado=0;
		}
		
		this.primaVacacional=Float.parseFloat(datos[4]);
		if(this.primaVacacional>1209f) {
			this.primaVacacionalExenta=1209f;
			this.primaVacacionalGravada=this.primaVacacional-this.primaVacacionalExenta;
		}
		else {
			this.primaVacacionalExenta=this.primaVacacional;
			this.primaVacacionalGravada=0;
		}
		
		this.totalIngresosGravados=this.sueldoAnual+this.aguinaldoGravado+this.primaVacacionalGravada;
		this.medicosHospitales=Float.parseFloat(datos[5]);
		this.funerarios=Float.parseFloat(datos[6]);
		this.SGMM=Float.parseFloat(datos[7]);
		this.hipoteca=Float.parseFloat(datos[8]);
		this.donativos=Float.parseFloat(datos[9]);
		this.retiro=Float.parseFloat(datos[10]);
		this.transporteEscolar=Float.parseFloat(datos[11]);
		this.nivelEscolar=datos[12].toLowerCase();
		this.colegiatura=Float.parseFloat(datos[13]);
		
		float colegiaturadeducida;
		if(this.nivelEscolar.equals("preescolar")) {
			this.maximoDeducibleColegiatura=14200f;
			colegiaturadeducida=14200f;
		}
		else if(this.nivelEscolar.equals("primaria")) {
			this.maximoDeducibleColegiatura=12900f;
			colegiaturadeducida=12900f;
		}
		else if(this.nivelEscolar.equals("secundaria")) {
			this.maximoDeducibleColegiatura=19900f;
			colegiaturadeducida=19900f;
		}
		else if(this.nivelEscolar.equals("profesional t�cnico")) {
			this.maximoDeducibleColegiatura=17100f;
			colegiaturadeducida=17100f;
		}
		else if(this.nivelEscolar.equals("bachillerato")||this.nivelEscolar.equals("preparatoria")) {
			this.maximoDeducibleColegiatura=24500f;
			colegiaturadeducida=24500f;
		}
		else {
			this.maximoDeducibleColegiatura=0f;
			colegiaturadeducida=0f;
		}
		
		if(this.colegiatura<this.maximoDeducibleColegiatura) {
			colegiaturadeducida=this.colegiatura;
		}
		
		this.totalDeducciones=this.medicosHospitales+this.funerarios+this.SGMM+this.hipoteca+this.donativos+this.transporteEscolar+colegiaturadeducida;
		
		float retiroDeducible;
		if(this.retiro<(this.aguinaldo+this.sueldoAnual+this.primaVacacional)/10f) {
			retiroDeducible=this.retiro;
		}
		else {
			retiroDeducible=(this.aguinaldo+this.sueldoAnual+this.primaVacacional)/10f;
		}
		
		this.deduccionPermitida=this.totalDeducciones+retiroDeducible;
		this.sueldoConDeduccion=this.totalIngresosGravados-this.deduccionPermitida;
	}
	
	public void crearArchivo() {
			this.pw.println("Nombre,RFC,Sueldo mensual,Ingreso anual,Aguinaldo,Aguinaldo exento,Aguinaldo gravado,Prima vacacional,Prima vacacional excenta,Prima vacacional gravada,Total ingresos gravan,Medicos y hospitales,Gastos funerarios,SGMM,Hipotecarios,Donativos,Subcuenta retiro,Transporte escolar,Nivel educativo,Maximo a deducir colegiatura,Colegiatura pagada,Total deducciones (sin retiro),Deduccion permitida (10% + 10% retiro),Monto calculo de ISR,Cuota fija,Porcentaje excedente,Pago excedente,Total a pagar");
			this.pw.println(this.nombre+","+this.RFC+","+this.sueldoMensual+","+this.sueldoAnual+","+this.aguinaldo+","+this.aguinaldoExento+","+this.aguinaldoGravado+","+this.primaVacacional+","+this.primaVacacionalExenta+","+this.primaVacacionalGravada+","+this.totalIngresosGravados+","+this.medicosHospitales+","+this.funerarios+","+this.SGMM+","+this.hipoteca+","+this.donativos+","+this.retiro+","+this.transporteEscolar+","+this.nivelEscolar+","+this.maximoDeducibleColegiatura+","+this.colegiatura+","+this.totalDeducciones+","+this.deduccionPermitida+","+this.sueldoConDeduccion+","+this.cuotaFija+","+this.porcentajeExcendete+","+this.excedente+","+this.totalPagar);
	}
	
	public void total() {
		if(this.sueldoConDeduccion>=.01 && this.sueldoConDeduccion<=5952.84) {
			this.cuotaFija=0;
			this.excedente=(this.sueldoConDeduccion-.01f)*.0192f;
			this.porcentajeExcendete=1.92f;
		}
		else if(this.sueldoConDeduccion>=5952.85 && this.sueldoConDeduccion<=50524.92) {
			this.cuotaFija=114.29f;
			excedente=(this.sueldoConDeduccion-5952.85f)*.064f;
			this.porcentajeExcendete=6.4f;
		}
		else if(this.sueldoConDeduccion>=50524.93 && this.sueldoConDeduccion<=88793.04) {
			this.cuotaFija=2966.91f;
			this.excedente=(this.sueldoConDeduccion-50524.93f)*.1088f;
			this.porcentajeExcendete=10.88f;
		}
		else if(this.sueldoConDeduccion>=88793.05 && this.sueldoConDeduccion<=103218) {
			this.cuotaFija=7130.48f;
			this.excedente=(this.sueldoConDeduccion-88793.05f)*.16f;
			this.porcentajeExcendete=16f;
		}
		else if(this.sueldoConDeduccion>=103218.01 && this.sueldoConDeduccion<=123580.20) {
			this.cuotaFija=9438.47f;
			this.excedente=(this.sueldoConDeduccion-103218f)*.1792f;
			this.porcentajeExcendete=17.92f;
		}
		else if(this.sueldoConDeduccion>=123580.21 && this.sueldoConDeduccion<=249243.48) {
			this.cuotaFija=13087.37f;
			this.excedente=(this.sueldoConDeduccion-123580.21f)*.2136f;
			this.porcentajeExcendete=21.36f;
		}
		else if(this.sueldoConDeduccion>=249243.49 && this.sueldoConDeduccion<=392841.96) {
			this.cuotaFija=39929.05f;
			this.excedente=(this.sueldoConDeduccion-249243.49f)*.2352f;
			this.porcentajeExcendete=23.52f;
		}
		else if(this.sueldoConDeduccion>=392841.97 && this.sueldoConDeduccion<=750000) {
			this.cuotaFija=73703.41f;
			this.excedente=(this.sueldoConDeduccion-392841.97f)*.30f;
			this.porcentajeExcendete=30f;
		}
		else if(this.sueldoConDeduccion>=750000.01 && this.sueldoConDeduccion<=1000000) {
			this.cuotaFija=180850.82f;
			this.excedente=(this.sueldoConDeduccion-750000.01f)*.32f;
			this.porcentajeExcendete=32f;
		}
		else if(this.sueldoConDeduccion>=1000000.01 && this.sueldoConDeduccion<=3000000) {
			this.cuotaFija=260850.81f;
			this.excedente=(this.sueldoConDeduccion-1000000.01f)*.34f;
			this.porcentajeExcendete=34f;
		}
		else{
			this.cuotaFija=940850.81f;
			this.excedente=(this.sueldoConDeduccion-3000000.01f)*.35f;
			this.porcentajeExcendete=35f;
		}
		
		this.totalPagar=this.cuotaFija+this.excedente;
	}
	
	public void hacerResumen() {
		JOptionPane.showMessageDialog(null, "Nombre: "+this.nombre+"\n"+
											"RFC: "+this.RFC+"\n"+
											"Sueldo Mensual: "+this.sueldoMensual+"\n"+
											"Ingreso Anual: "+this.sueldoAnual+"\n"+
											"Aguinaldo: "+this.aguinaldo+"\n"+
											"Aguinaldo Excento: "+this.aguinaldoExento+"\n"+
											"Aguinaldo Gravado: "+this.aguinaldoGravado+"\n"+
											"Prima Vacacional: "+this.primaVacacional+"\n"+
											"Prima Vacacional Excenta: "+this.primaVacacionalExenta+"\n"+
											"Prima Vacacional Gravada: "+this.primaVacacionalGravada+"\n"+
											"Total Ingresos Gravados: "+this.totalIngresosGravados+"\n"+
											"Medicos y Hospitales: "+this.medicosHospitales+"\n"+
											"Gastos funerarios: "+this.funerarios+"\n"+
											"Seguro de gastos Medicos: "+this.SGMM+"\n"+
											"Hipotecarios: "+this.hipoteca+"\n"+
											"Donativos: "+this.donativos+"\n"+
											"Subcuenta Retiros: "+this.retiro+"\n"+
											"Transporte Escolar: "+this.transporteEscolar+"\n"+
											"Nivel Educativo: "+this.nivelEscolar+"\n"+
											"Maximo a Deducir Colegiatura: "+this.maximoDeducibleColegiatura+"\n"+
											"Colegiatura Pagada: "+this.colegiatura+"\n"+
											"Total Deducciones(Sin Retiro): "+this.totalDeducciones+"\n"+
											"Deducciones Permitidas: "+this.deduccionPermitida+"\n"+
											"Monto Calculo de ISR: "+this.sueldoConDeduccion+"\n"+
											"Cuota Fija: "+this.cuotaFija+"\n"+
											"Porcentaje Excendete: "+this.porcentajeExcendete+"\n"+
											"Pago Excedente: "+this.excedente+"\n"+
											"Total a Pagar: "+this.totalPagar);
	}
}
