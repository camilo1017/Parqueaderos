package com.parqueaderos.parqueadero.excepciones;

public class ParqueaderosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ParqueaderosException(String message) {
		super(message);
	}
}
