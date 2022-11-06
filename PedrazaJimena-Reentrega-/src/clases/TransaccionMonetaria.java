package clases;

import ar.edu.unlam.pb2.Alertable;
import ar.edu.unlam.pb2.FraudeException;
import ar.edu.unlam.pb2.Monitoreable;
import ar.edu.unlam.pb2.Rechazable;

public class TransaccionMonetaria extends Transaccion implements Monitoreable{

	public TransaccionMonetaria(Cliente cliente, Dispositivo dispositivos, Sistema sistema) {
		super(cliente, dispositivos, sistema);
	}

	@Override
	public void monitorear() {
		this.getCliente().nuevoDispositivo(this.getDispositivos());
		this.getCliente().nuevaTransaccion(this);
	}

}
