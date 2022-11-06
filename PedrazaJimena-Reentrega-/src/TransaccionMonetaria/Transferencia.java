package TransaccionMonetaria;

import ar.edu.unlam.pb2.Alertable;
import ar.edu.unlam.pb2.FraudeException;
import ar.edu.unlam.pb2.Rechazable;
import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionMonetaria;

public class Transferencia extends TransaccionMonetaria implements Rechazable, Alertable {

	private Integer numeroDeTranferencia;
	private String destinatario;
	private double montoATransferir;

	public Transferencia(Cliente cliente, Dispositivo dispositivos, Sistema sistema, Integer numeroDeTransferencia,
			String destinatario, double montoATransferir) {
		super(cliente, dispositivos, sistema);
		this.numeroDeTranferencia = numeroDeTransferencia;
		this.destinatario = destinatario;
		this.montoATransferir = montoATransferir;
	}

	public Integer getNumeroDeTranferencia() {
		return numeroDeTranferencia;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public double getMontoATransferir() {
		return montoATransferir;
	}

	@Override
	public void marcarComoCasoSospechoso() {
		// Si el SCORE da entre 60 y 79, se debe generar un caso de an�lisis para
		// estudiar la transacci�n, pero la misma no es bloqueada y debe seguir su curso
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

	@Override
	public Boolean monitorear(int i) throws FraudeException {
		confirmarSiFueFraude();
		marcarComoCasoSospechoso();
		monitorear();
		return false;
	}

	}

