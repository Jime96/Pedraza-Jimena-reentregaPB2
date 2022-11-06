package TransaccionNoMonetarias;

import ar.edu.unlam.pb2.Monitoreable;
import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionNoMonetaria;

public class CambioDeContraseña extends TransaccionNoMonetaria implements Monitoreable {
	
	private String nombreDeusuario;
	private String contraseñaAnterior;
	private String contraseñaNueva;

	public CambioDeContraseña(Cliente cliente, Dispositivo dispositivos, Sistema sistema, String contraseñaAnterior, String contraseñaNueva, String nombreDeusuario) {
		super(cliente, dispositivos, sistema);
		this.nombreDeusuario=nombreDeusuario;
		this.contraseñaAnterior=contraseñaAnterior;
		this.contraseñaNueva=contraseñaNueva;
		
	}

	public String getNombreDeusuario() {
		return nombreDeusuario;
	}

	public String getContraseñaAnterior() {
		return contraseñaAnterior;
	}

	public String getContraseñaNueva() {
		return contraseñaNueva;
	}
	

}
