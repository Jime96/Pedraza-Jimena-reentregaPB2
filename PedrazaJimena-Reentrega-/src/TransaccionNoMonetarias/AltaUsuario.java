package TransaccionNoMonetarias;

import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionNoMonetaria;

public class AltaUsuario extends TransaccionNoMonetaria{
	
	private String nombreDeUsuario;
	private String contraseña;

	public AltaUsuario(Cliente cliente, Dispositivo dispositivos, Sistema sistema, String nombreDeUsuario,String contraseña ) {
		super(cliente, dispositivos, sistema);
		this.contraseña=contraseña;
		this.nombreDeUsuario=nombreDeUsuario;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}
	

}
