package com.parqueaderos.parqueadero.dominio;

public class Recibo {
	private String fechaEntrada;
	private String fechaSalida;
	private Vehiculo vehiculo;
	private double costo;
	
	public Recibo() {		
	}
	public Recibo(String fechaEntrada, String fechaSalida, Vehiculo vehiculo, double costo) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.vehiculo = vehiculo;
		this.costo = costo;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public String getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
}
