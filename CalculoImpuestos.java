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
	private String[] datosStr;
	
	private String nombre,
	               RFC,
	               nivelEscolar;
	
	private float sueldoMensual,
	            sueldoAnual,
	            aguinaldo,
	            aguinaldoExento,
	            aguinaldoGravado,
	            primaVacacional,
	            primaVacionalExenta,
	            primaVacacionalGravada,
	            totalIngresisGravados,
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
	            TotalPagar;
	        
	            
	            
	            

	public CalculoImpuestos(File datos) {
		try {
			this.br=new BufferedReader(new FileReader(datos));
			this.datosStr=this.br.readLine().split(",");
			Calcular(this.datosStr);
			crearArchivo();
			this.br.close();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, this, "No se pudo encontar el archivo", 0);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, this, "Hubo un error con el archivo", 0);
			
		}
	}
	
	public void Calcular(String[] datos) {
		this.nombre=datos[0];
		this.RFC=datos[1];
		this.sueldoMensual=Integer.parseInt(datos[2]);
		this.sueldoAnual=this.sueldoMensual*12;
		this.aguinaldo=Integer.parseInt(datos[3]);
		this.aguinaldoExento=this.sueldoMensual/2;
		this.aguinaldoGravado=this.aguinaldo-this.aguinaldoExento;
		this.primaVacacional=Integer.parseInt(datos[4]);
		this.primaVacionalExenta=1209f;
		this.primaVacacionalGravada=this.primaVacacional-this.primaVacionalExenta;
		this.totalIngresisGravados=this.sueldoAnual+this.aguinaldoGravado+this.primaVacacionalGravada;
		this.medicosHospitales=Integer.parseInt(datos[5]);
		this.funerarios=Integer.parseInt(datos[6]);
		this.SGMM=Integer.parseInt(datos[7]);
		this.hipoteca=Integer.parseInt(datos[8]);
		this.donativos=Integer.parseInt(datos[9]);
		this.retiro=Integer.parseInt(datos[10]);
		this.transporteEscolar=Integer.parseInt(datos[11]);
		this.nivelEscolar=datos[12];
		this.colegiatura=Integer.parseInt(datos[13]);
		
		if(this.nivelEscolar.equals("Preescolar")) {
			this.maximoDeducibleColegiatura=14200f;
		}
		else if(this.nivelEscolar.equals("Primaria")) {
			this.maximoDeducibleColegiatura=12900f;
		}
		else if(this.nivelEscolar.equals("Secundaria")) {
			this.maximoDeducibleColegiatura=19900f;
		}
		else if(this.nivelEscolar.equals("Profesional técnico")) {
			this.maximoDeducibleColegiatura=17100f;
		}
		else if(this.nivelEscolar.equals("Bachillerato")||this.nivelEscolar.equals("Preparatoria")) {
			this.maximoDeducibleColegiatura=24500f;
		}
		
		if(this.maximoDeducibleColegiatura<this.colegiatura) {
			this.colegiatura=this.maximoDeducibleColegiatura;
		}
		
		this.totalDeducciones=this.medicosHospitales+this.funerarios+this.SGMM+this.hipoteca+this.donativos+this.transporteEscolar+this.colegiatura;
		this.deduccionPermitida=(this.sueldoAnual+this.aguinaldo+this.primaVacacional)/10;
		
		if(this.totalDeducciones>this.deduccionPermitida) {
			this.totalDeducciones=this.deduccionPermitida;
		}
		if(this.retiro>this.deduccionPermitida) {
			this.retiro=this.deduccionPermitida;
		}
	}
	
	public void crearArchivo() {
		try {
			PrintWriter pw=new PrintWriter(new FileWriter("resultados.csv"));
			pw.println("Nombre,RFC,Sueldo mensual,Ingreso anual,Aguinaldo,Aguinaldo exento,Aguinaldo gravado,Prima vacacional,Prima vacacional excenta,Prima vacacional gravada,Total ingresos gravan,Medicos y hospitales,Gastos funerarios,SGMM,Hipotecarios,Donativos,Subcuenta retiro,Transporte escolar,Nivel educativo,Maximo a deducir colegiatura,Colegiatura pagada,Total deducciones (sin retiro),Deduccion permitida 10%,Motal a pagar");
			pw.println(this.nombre+","+this.RFC+","+this.sueldoMensual+","+this.sueldoAnual+","+this.aguinaldo+","+this.aguinaldoExento+","+this.aguinaldoGravado+","+this.primaVacacional+","+this.primaVacionalExenta+","+this.primaVacacionalGravada+","+this.totalIngresisGravados+","+this.medicosHospitales+","+this.funerarios+","+this.SGMM+","+this.hipoteca+","+this.donativos+","+this.retiro+","+this.transporteEscolar+","+this.nivelEscolar+","+this.maximoDeducibleColegiatura+","+this.colegiatura+","+this.totalDeducciones+","+this.deduccionPermitida+","+this.TotalPagar);
			pw.close();
		}
		catch(IOException e) {
			System.out.println("No se pudo escribir en el archivo");
		}	}
	
}
