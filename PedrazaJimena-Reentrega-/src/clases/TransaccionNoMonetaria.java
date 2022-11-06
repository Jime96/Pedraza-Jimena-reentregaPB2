package clases;

import ar.edu.unlam.pb2.Monitoreable;

public class TransaccionNoMonetaria extends Transaccion implements Monitoreable{

	
	
	public TransaccionNoMonetaria(Cliente cliente, Dispositivo dispositivos, Sistema sistema) {
		super(cliente, dispositivos, sistema);
	}

	@Override
	public void monitorear() {
		this.getCliente().nuevoDispositivo(this.getDispositivos());
		this.getCliente().nuevaTransaccion(this);
	}

}
