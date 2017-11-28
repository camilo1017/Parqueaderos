package com.parqueaderos.parqueaderos.builder;

import com.parqueaderos.parqueaderos.dominio.Vigilante;

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
