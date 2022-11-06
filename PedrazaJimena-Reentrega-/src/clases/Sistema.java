package clases;

import java.util.ArrayList;
import java.util.List;

import TransaccionMonetaria.Transferencia;
import TransaccionNoMonetarias.CambioDeContraseña;
import ar.edu.unlam.pb2.Alertable;
import ar.edu.unlam.pb2.Denunciable;
import ar.edu.unlam.pb2.Rechazable;

public class Sistema {

	private List<Denunciable> fraudeAlto; // informa datos que hayan formado parte de un evento de fraude.
	private List<Alertable> fraudeMedio;// reportar para que un analista las revise, si se detecta un riesgo de fraude
										// medio
	private String nombre;

	public Sistema(String nombre) {
		this.nombre = nombre;
		this.fraudeMedio = new ArrayList<>();
		this.fraudeAlto = new ArrayList<>();
	}

	// Fraudes de alto nivel registrados en el sistema:
	// ----No está vacío--
	public boolean fraudesEncontrados() {
		return this.fraudeAlto.isEmpty();
	}

	// Nuevos fraudes ingresados al sistema
	public void registrarFraude(Denunciable denunciable) {
		this.fraudeAlto.add(denunciable);
	}

	// Fraudes de medio nivel en el sistema
	public void registrarUnAlertable(Alertable alertable) {
		this.fraudeMedio.add(alertable);
	}

	// Buscar clientes denunciables, mediante su cuit
	private boolean BuscarClienteEnFraude(Cliente cliente) {
		for (Denunciable denunciables : fraudeAlto) {
			if (denunciables instanceof Cliente) {// Si un cliente es intanciado como denunciable...
				if (((Cliente) denunciables).getCuit().equals(cliente.getCuit())) {
					return true;
				}
			}
		}
		return false;
	}

	// Buscar clientes denunciables a traves del dipositivo utilizado(IP)
	private boolean dispositivoEnFraude(Cliente cliente) {
		for (Denunciable denunciable : fraudeAlto) {
			if (cliente.dispositivoEncontrado(denunciable)) {
				return true;
			}
		}
		return false;
	}
	// Ultimo cambio de contraseña realizado por el cliente

	private boolean ultimoCambioDeContraseña(Transaccion transaccion) {
	
		Cliente cliente=transaccion.getCliente();
		if(!cliente.transaccionesRealizadas()) {
			Transaccion ultimaTransaccion=cliente.ultimasTransaccionesRealizadas();
			if(ultimaTransaccion instanceof CambioDeContraseña && transaccion!=null) {
				return true;
			}
		}
	return false;
	}
	
	private boolean transferenciaACuentaConMismoFondo(Transaccion transaccion) {
		
		Cliente cliente=transaccion.getCliente();
		if(transaccion instanceof Transferencia && transaccion instanceof Rechazable) {
			Transferencia transferenciaFinal=(Transferencia)transaccion;
			if(transferenciaFinal.getMontoATransferir() ==cliente.getSaldo()){
				return true;
			}
		}
		return false;
	}

	public boolean nuevoDispositivo(Transaccion transacciones) {
		Cliente cliente=transacciones.getCliente();
		if(cliente.dispositivosExistentes()) {
		return false;
		}
		return !cliente.dispositivoEncontrado(transacciones.getDispositivos());
}

// Calcular Score

	public void calcularScore(Transaccion transacciones) {

		Cliente clientes = transacciones.getCliente();// transacciones que realiza el cliente
		Integer score = 0;

		if (BuscarClienteEnFraude(clientes)) { // 1. Si el CUIT del cliente de la transacción formó parte de un fraude
												// se suma 80 puntos al SCORE.
			score += 80;
		}
		if (dispositivoEnFraude(clientes)) {// 2. Si la dirección IP o IMEI del dispositivo estuvo involucrado en un
											// evento de FRAUDE, se suma 80 puntos al SCORE.
			score += 80;
		}
		if(ultimoCambioDeContraseña(transacciones)) {//3.	Si el cliente hizo un cambio de contraseña inmediatamente antes (última operación realizada) a realizar una transacción RECHAZABLE, suma 20 puntos al SCORE.
			score+=20;
		}
		if(transferenciaACuentaConMismoFondo(transacciones)) {//4.	Si el monto de la TRANSFERENCIA coincide con el total de fondos del cliente (su saldo quedaría en 0) suma 40 puntos al SCORE.
			score+=40;
		}
		if(nuevoDispositivo(transacciones)) {//5.	Si el cliente está operando desde un dispositivo que nunca utilizópreviamente, suma 20 puntos al SCORE.
			score=score+20;
		}
		
	clientes.setScore(score);
	System.out.println("Su score actual es del" + "" + score);
}
}