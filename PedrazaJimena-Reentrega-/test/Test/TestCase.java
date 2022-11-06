package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Dispositivos.Biometria;
import Dispositivos.Celu;
import Dispositivos.PC;
import TransaccionMonetaria.Extraccion;
import TransaccionMonetaria.PagoConQr;
import TransaccionMonetaria.PagoServicios;
import TransaccionMonetaria.Transferencia;
import TransaccionNoMonetarias.AltaUsuario;
import TransaccionNoMonetarias.CambioDeContraseña;
import ar.edu.unlam.pb2.Alertable;
import ar.edu.unlam.pb2.FraudeException;
import ar.edu.unlam.pb2.Monitoreable;
import ar.edu.unlam.pb2.Rechazable;
import clases.Cliente;
import clases.Dispositivo;
import clases.Sistema;
import clases.TransaccionMonetaria;



public class TestCase {

	@Test
	public void queSePuedaCrearUnCliente() {
		Cliente nuevo = new Cliente(27343546, "Carla");

		assertNotNull(nuevo);
	}

	@Test
	public void queSePuedaCrearUnDispositivo() {
		Dispositivo dipositivo = new Celu("Android", 54839489, "Casanova", 65656, Biometria.HUELLA);

		assertNotNull(dipositivo);
	}

	@Test
	public void queSePuedaMonitorearUnaExtraccion() {

		// Elementos necesarios para realizar la extraccion:Sistema, cliente y
		// dispositivo
		Sistema sistema = new Sistema("Cyber Segurity");
		Cliente clientes = new Cliente(454754, "");
		Dispositivo dispositivos = new PC("Windows", 44356, "San Justo");

		// Nuevo objeto
		TransaccionMonetaria extraccion = new Extraccion(clientes, dispositivos, sistema, 454545, 500.00);

		// MonitorearExtraccion
		extraccion.monitorear();

		// Devolver si se realizaron transacciones o está vacío
		assertFalse(clientes.transaccionesRealizadas());
	}

	@Test
	public void queSePuedaMonitorearUnaTransferencia() {

		Cliente clientes = new Cliente(29343546, "Juan");
		Dispositivo dispositivos = new Celu("Android", 4345546, "Moron", 4345465, Biometria.ROSTRO);
		Sistema sistema = new Sistema("Cyber Segurity");

		Monitoreable transferir = new Transferencia(clientes, dispositivos, sistema, 4343436, "Cuenta001", 15000.00);

		transferir.monitorear();

		assertFalse(clientes.transaccionesRealizadas());
		assertEquals(1,clientes.transaccionesActuales());

	}

	@Test
	public void queSePuedaMonitorearUnPagoConQR() {

		Dispositivo dispositivos = new PC("windows", 1921681, "Haedo");
		Cliente cliente = new Cliente(2042455, "Alejandro");
		Sistema sistema = new Sistema("Cyber Segurity");
		Monitoreable pagoQR = new PagoConQr(cliente, dispositivos, sistema,456575, 600.0);

		pagoQR.monitorear();

		assertFalse(cliente.transaccionesRealizadas());
		assertEquals(1,cliente.transaccionesActuales());
	}

	@Test
	public void queSePuedaMonitorearUnPagoDeServicio() {
		
		Dispositivo dispositivo = new Celu("Apple", 3345466, "Laferrere", 454545, Biometria.HUELLA);
		Cliente cliente = new Cliente(253545463, "Maria");
		Sistema sistema = new Sistema("Cyber segurity");
		Monitoreable pagoDeServicio = new PagoServicios(cliente, dispositivo, sistema);

		pagoDeServicio.monitorear();

		assertFalse(cliente.transaccionesRealizadas());
		assertEquals(1,cliente.transaccionesActuales());
	}

	@Test
	public void queSePuedaMonitorearUnAltaDeUsuario() {

		Dispositivo dispositivo = new Celu("Android", 65656456 , "San Justo" , 33343554, Biometria.HUELLA);
		Cliente cliente = new Cliente(2045684455, "Lisa");
		Sistema sistema = new Sistema("Cyber segurity");
		Monitoreable nuevoUsuario = new AltaUsuario(cliente, dispositivo, sistema, "madelaine", "contrasenia");
//Cliente cliente, Dispositivo dispositivos, Sistema sistema, String nombreDeUsuario,String contraseña
		nuevoUsuario.monitorear();

		assertFalse(cliente.transaccionesRealizadas());
		assertEquals(1,cliente.transaccionesActuales());
	}

	@Test
	public void queSePuedaMonitorearUnCambioDeContraseña() {
		
		Dispositivo dispositivo = new PC("windows", 26545465, "Ramos Mejia");
		Cliente cliente = new Cliente(2045684455, "Lisa");
		Sistema sistema = new Sistema("Cyber segurity");
		Monitoreable cambio = new CambioDeContraseña(cliente, dispositivo, sistema, "HolaMundo1!", "HM01!", "Romina");

		cambio.monitorear();

		assertFalse(cliente.transaccionesRealizadas());
	}
	
	@Test
	 public void queElScoreDeUnaTransaccionRechazableSinAntecedentesDeCero() throws FraudeException{
		
		Dispositivo dispositivos=new PC("Windows",45456546,"Castelar");
		Cliente clientes=new Cliente(22345656,"Laura");
		Sistema sistema=new Sistema("Cyber Segurity");
		Rechazable transaccion=new Transferencia(clientes,dispositivos,sistema,00454,"Cuenta009",1500.0);
		
		transaccion.monitorear(1);
		
		assertEquals(0,clientes.getScore());
		
	}
	@Test
	 public void queUnaTransaccionAlertablePuedaSerMarcadaComoFraudulenta() {
		 
		 Sistema sistema=new Sistema("Cyber segurity");
		 Cliente cliente=new Cliente(203445902, "German");
		 Dispositivo dispositivos=new Celu("Apple",454545645,"Gonzalez Catan", 6557676,Biometria.HUELLA);
		 Monitoreable actualizacion=new CambioDeContraseña(cliente,dispositivos, sistema,"AbC12*","eYY45!","User001");
		 actualizacion.monitorear();
		 Dispositivo nuevo=new Celu("Android",45466,"San Justo",545845,Biometria.HUELLA);
		 Alertable transferencia=new Transferencia(cliente, dispositivos,sistema,5455,"Cuenta0039",6000.0);
		
		 transferencia.confirmarSiFueFraude();
		
		 assertFalse(sistema.fraudesEncontrados());
	 }
	@Test
	 public void queElScoreDeUnaTransaccionRechazableConNuevoDispositivoDe20Puntos()throws FraudeException {
		 Sistema sistema=new Sistema("Cyber segurity");
		 Cliente cliente=new Cliente(22313435, "Pablo");
		 Dispositivo dispositivos=new Celu("Android",5454667,"San Justo", 4564574,Biometria.ROSTRO);
		 Monitoreable nuevo=new AltaUsuario(cliente, dispositivos, sistema, "User99","999YY-");
		 nuevo.monitorear();
		 Dispositivo dispositivoNuevo=new Celu("Apple",54467,"Casanova", 6457,Biometria.ROSTRO);
		 Rechazable extraccion=new Extraccion(cliente,dispositivos,sistema,0043,2000.0);
		 
		 extraccion.monitorear(1);
		 
		 assertEquals(0,cliente.getScore());
		 
	 }
	 @Test
	 public void QueUnaTransaccionDeMasDe60PuntosYMenosDeOchentaSeaAlertadaPeroAprobada()throws FraudeException {
		 Dispositivo dispositivo = new PC("Linux", 454654, "Casanova");
			Sistema sistema = new Sistema("Cyber segurity");
			Cliente cliente = new Cliente(4000, "Pablo");
			cliente.setScore(5);
			Monitoreable actualizacion = new CambioDeContraseña(cliente,dispositivo,sistema,"Frkr98","YYPP43$","User555");
			actualizacion.monitorear();
			Rechazable transferencia = new Transferencia(cliente,dispositivo,sistema,9495,"Cuenta003",1000.0);
			transferencia.monitorear(1);
			
			assertEquals(65, cliente.getScore());		
			assertTrue(sistema.fraudesEncontrados());
	 }

	  @Test 
	  public void queUnaTransaccionDeMasDe80PuntosLanceLaExcepcionFraudeException() {
	  
		  Dispositivo dispositivo = new Celu("Android", 45945, "Casanova",493843, Biometria.HUELLA);
			Sistema sistema = new Sistema("Cyber segurity");
			Cliente cliente = new Cliente(41540, "Jose");
			cliente.setScore(5);
			Monitoreable actualizacion = new CambioDeContraseña(cliente,dispositivo,sistema,"e994Y","Yes45","Cuenta003");
			actualizacion.monitorear();
			Dispositivo dispositivoNuevo = new Celu("Apple",3434, "San Justo",44454, Biometria.ROSTRO);
			Alertable transferencia = new Transferencia(cliente,dispositivo,sistema,34546,"Cuenta435",500.0);
			transferencia.confirmarSiFueFraude();
			
			assertEquals(85, cliente.getScore());		
			assertFalse(sistema.fraudesEncontrados());

}
}