package TransaccionMonetaria;

import ar.edu.unlam.pb2.Alertable;
import ar.edu.unlam.pb2.FraudeException;
import ar.edu.unlam.pb2.Monitoreable;
import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionMonetaria;

public class PagoServicios extends TransaccionMonetaria implements Monitoreable, Alertable  {
	
	public PagoServicios(Cliente cliente, Dispositivo dispositivos, Sistema sistema) {
		super(cliente, dispositivos, sistema);
		
	}

	@Override
	public void marcarComoCasoSospechoso() {
		// Si el SCORE da entre 60 y 79, se debe generar un caso de análisis para
		// estudiar la transacción, pero la misma no es bloqueada y debe seguir su curso
		if (this.getCliente().getScore() > 60 && this.getCliente().getScore() < 79) {
			this.getSistema().registrarUnAlertable(this);
			System.out.println("Estudiando transaccion");
		}
		System.out.println("Aprobada");
		monitorear();
		
	}

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
}