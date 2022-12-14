package TransaccionMonetaria;

import java.util.Set;
import java.util.PrimitiveIterator.OfDouble;

import ar.edu.unlam.pb2.Alertable;
import ar.edu.unlam.pb2.Denunciable;
import ar.edu.unlam.pb2.FraudeException;
import ar.edu.unlam.pb2.Monitoreable;
import ar.edu.unlam.pb2.Rechazable;
import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionMonetaria;

public class Extraccion extends TransaccionMonetaria implements Rechazable, Monitoreable, Alertable{

	private Integer ordenDeExtraccion;//Numero de extraccion
	private double montoAExtraer;
	
	
	public Extraccion(Cliente cliente, Dispositivo dispositivos, Sistema sistema, Integer ordenDeExtraccion, double montoAExtraer) {
		super(cliente, dispositivos, sistema);
		this.ordenDeExtraccion=ordenDeExtraccion;
		this.montoAExtraer=montoAExtraer;	
	}


	@Override
	public void marcarComoCasoSospechoso() {
		//Si el SCORE da entre 60 y 79, se debe generar un caso de análisis para estudiar la transacción, pero la misma no es bloqueada y debe seguir su curso
		if(this.getCliente().getScore()>60 && this.getCliente().getScore()<79) {
			this.getSistema().registrarUnAlertable(this);
			System.out.println("Estudiando transaccion");
		}
		System.out.println("Aprobada");
		monitorear();

	}

	//Si el SCORE da mas de 80, la transacción se debe lanzar un evento de FRAUDE que bloquee la transacción.
	@Override
	public void confirmarSiFueFraude() {
		try {
			if(this.getCliente().getScore()>=80) {
				throw new FraudeException("Transaccion fraudulenta");
			}
		}catch(Exception e) {
			this.getSistema().registrarFraude(this.getCliente());
			System.out.println("Cotrolar lista para confirmar fraude");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Boolean monitorear(int i) throws FraudeException {
		confirmarSiFueFraude();
		marcarComoCasoSospechoso();
		monitorear();
		return false;
	}
	
	
	
		



}
