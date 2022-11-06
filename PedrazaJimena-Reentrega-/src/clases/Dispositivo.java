package clases;

import ar.edu.unlam.pb2.Denunciable;

public class Dispositivo implements Denunciable{
	private String SO;
	private Integer IP;
	private String localidad;
	
	public Dispositivo(String SO , Integer IP, String localidad) {
	this.SO=SO;
	this.IP=IP;
	this.localidad=localidad;
	
	}

	public String getSO() {
		return SO;
	}

	public Integer getIP() {
		return IP;
	}

	public String getLocalidad() {
		return localidad;
	}
	



 


}
