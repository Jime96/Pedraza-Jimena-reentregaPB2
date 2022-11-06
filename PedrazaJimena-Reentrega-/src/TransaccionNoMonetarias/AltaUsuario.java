package TransaccionNoMonetarias;

import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionNoMonetaria;

public class AltaUsuario extends TransaccionNoMonetaria{
	
	private String nombreDeUsuario;
	private String contrase�a;

	public AltaUsuario(Cliente cliente, Dispositivo dispositivos, Sistema sistema, String nombreDeUsuario,String contrase�a ) {
		super(cliente, dispositivos, sistema);
		this.contrase�a=contrase�a;
		this.nombreDeUsuario=nombreDeUsuario;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public String getContrase�a() {
		return contrase�a;
	}
	

}
