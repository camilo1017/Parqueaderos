package com.parqueaderos.parqueadero.dominio;

public class Parqueadero {
	private int nroMotos;
	private int nroCarros;
	private static final int CAPACIDAD_CARROS=20;
	private static final int CAPACIDAD_MOTOS=10;
	public Parqueadero() {
		super();
	}
	public Parqueadero(int nroMotos, int nroCarros) {
		super();
		this.nroMotos = nroMotos;
		this.nroCarros = nroCarros;
	}
	public int getNroMotos() {
		return nroMotos;
	}
	public void setNroMotos(int nroMotos) {
		this.nroMotos = nroMotos;
	}
	public int getNroCarros() {
		return nroCarros;
	}
	public void setNroCarros(int nroCarros) {
		this.nroCarros = nroCarros;
	}
	public boolean ingresarCarro() {
		if(nroCarros<CAPACIDAD_CARROS) {
			nroCarros++;
			return true;
		}
		return false;
	}
	public boolean ingresarMoto() {
		if(nroMotos<CAPACIDAD_MOTOS) {
			nroMotos++;
			return true;
		}
		return false;
	}
}
