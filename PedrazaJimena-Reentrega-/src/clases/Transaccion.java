package clases;

public class Transaccion {

	private Cliente cliente;
	private Dispositivo dispositivos;
	private Sistema sistema;
	
	public Transaccion(Cliente cliente, Dispositivo dispositivos,Sistema sistema) {
		this.cliente=cliente;
		this.dispositivos=dispositivos;
		this.sistema=sistema;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Dispositivo getDispositivos() {
		return dispositivos;
	}

	public Sistema getSistema() {
		return sistema;
	}
	
	
}
