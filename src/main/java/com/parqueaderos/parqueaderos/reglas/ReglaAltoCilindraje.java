package com.parqueaderos.parqueaderos.reglas;

public class ReglaAltoCilindraje {
	public double cobrarCilindraje(int cilindraje) {
		if(cilindraje>500) {
			return 2000;
		}else {
			return 0;
		}		
	}
}
