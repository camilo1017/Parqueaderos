package com.parqueaderos.parqueadero.builder;

import com.parqueaderos.parqueadero.dominio.Vigilante;

public class VigilanteBuilder {
	private int nroMotos;
	private int nroCarros;
	public static VigilanteBuilder getInstance() {
		return new VigilanteBuilder();
	}
	public Vigilante build() {
		Vigilante vigilante=new Vigilante();
		return vigilante;
	}
}
