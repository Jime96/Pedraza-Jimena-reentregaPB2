package TransaccionNoMonetarias;

import ar.edu.unlam.pb2.Monitoreable;
import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionNoMonetaria;

public class CambioDeContrase�a extends TransaccionNoMonetaria implements Monitoreable {
	
	private String nombreDeusuario;
	private String contrase�aAnterior;
	private String contrase�aNueva;

	public CambioDeContrase�a(Cliente cliente, Dispositivo dispositivos, Sistema sistema, String contrase�aAnterior, String contrase�aNueva, String nombreDeusuario) {
		super(cliente, dispositivos, sistema);
		this.nombreDeusuario=nombreDeusuario;
		this.contrase�aAnterior=contrase�aAnterior;
		this.contrase�aNueva=contrase�aNueva;
		
	}

	public String getNombreDeusuario() {
		return nombreDeusuario;
	}

	public String getContrase�aAnterior() {
		return contrase�aAnterior;
	}

	public String getContrase�aNueva() {
		return contrase�aNueva;
	}
	

}
