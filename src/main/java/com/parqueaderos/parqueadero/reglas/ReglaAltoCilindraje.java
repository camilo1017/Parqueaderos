package com.parqueaderos.parqueadero.reglas;

public class ReglaAltoCilindraje {
	private static final int LIMITE_CILINDRAJE = 500;
	private static final int MONTO_ALTO_CILINDRAJE = 2000;

	public double cobrarCilindraje(int cilindraje) {
		if(cilindraje>LIMITE_CILINDRAJE) {
			return MONTO_ALTO_CILINDRAJE;
		}else {
			return 0;
		}		
	}
}
