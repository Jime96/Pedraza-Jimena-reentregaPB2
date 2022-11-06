package Dispositivos;

import clases.Dispositivo;

public class Celu extends Dispositivo{
	private Integer IMEI;
	private Biometria tipo;
	private Boolean esFraudulento ;

	public Celu(String SO , Integer IP, String localidad ,Integer IMEI, Biometria tipo) {
		super(SO,IP,localidad) ;
		this.IMEI= IMEI ;
		this.tipo = tipo;
		this.esFraudulento=esFraudulento;	
	}

	public Boolean getEsFraudulento() {
		return esFraudulento;
	}

	public void setEsFraudulento(Boolean esFraudulento) {
		this.esFraudulento = esFraudulento;
	}

	public Integer getIMEI() {
		return IMEI;
	}

	public void setIMEI(Integer iMEI) {
		IMEI = iMEI;
	}

	public Biometria getTipo() {
		return tipo;
	}

	public void setTipo(Biometria tipo) {
		this.tipo = tipo;
	}
}


