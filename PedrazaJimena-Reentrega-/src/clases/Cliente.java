package clases;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unlam.pb2.Denunciable;

public class Cliente implements Denunciable{
	
	private int cuit ;
	private String nombre;
	private List <Dispositivo>dispositivos;
	private List <Transaccion>transacciones;
	private int score;
	private int saldo;
	
	public Cliente(int cuit, String nombre) {
		// TODO Auto-generated constructor stub
		this.cuit =cuit;
		this.nombre= nombre;
		this.dispositivos=new ArrayList<>();
		this.transacciones=new ArrayList<>();
		this.score=score;
	}

	
	public void setScore(Integer score) {
		this.score = score;
	}


	public Integer getSaldo() {
		return saldo;
	}

	public Integer getCuit() {
		return cuit;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

//----TRANSACCIONES----
	
	//Registrar transaccion
	public void nuevaTransaccion(Transaccion transaccion) {
		this.transacciones.add(transaccion);
	}
	
	
	public Boolean transaccionesRealizadas() { //Hay transacciones, no está vacio
		return this.transacciones.isEmpty();
	}

	public int transaccionesActuales() { //Tamaño del array transaccion
		return this.transacciones.size();
	}
	
	//Ultimas transacciones realizada
	public Transaccion ultimasTransaccionesRealizadas() {
		int tamanoDeTransaccion=transaccionesActuales();
		return this.transacciones.get(tamanoDeTransaccion -1);
	}
	
	
	public int getScore() {
		return score;
	}
	
	
	
//------DISPOSITIVOS-------
	
	//RegistrarDispositivo
	public void nuevoDispositivo(Dispositivo dispositivo) {
		this.dispositivos.add(dispositivo);
	}
	
	
	//Hay dispositivos, no está vacío
	public boolean dispositivosExistentes() {
		return this.dispositivos.isEmpty();
	}

	//Dispositivos del cliente encontrados
	public boolean dispositivoEncontrado(Denunciable denunciable) {
		for(Dispositivo dispositivo:dispositivos) {
			if(dispositivo.equals(denunciable)) {
				return true;
			}
		}
		return false;
	}
	

}
