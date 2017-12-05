package com.parqueaderos.parqueadero.dominio;

import java.util.Calendar;

public class Recibo {
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	private Vehiculo vehiculo;
	private double costo;
	
	public Recibo() {		
	}
	public Recibo(Calendar fechaEntrada,  Vehiculo vehiculo) {
		this.fechaEntrada = fechaEntrada;
		this.vehiculo = vehiculo;
		
	}
	
	public Recibo(Calendar fechaEntrada, Calendar fechaSalida, Vehiculo vehiculo, double costo) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.vehiculo = vehiculo;
		this.costo = costo;
	}

	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public Calendar getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(Calendar fechaSalida) {
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
