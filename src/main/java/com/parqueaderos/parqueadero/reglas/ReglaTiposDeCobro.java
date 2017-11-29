package com.parqueaderos.parqueadero.reglas;

import com.parqueaderos.parqueadero.dominio.Vehiculo;

public class ReglaTiposDeCobro {
	private static final String TIPO_MOTO="Moto";
	private static final String TIPO_CARRO="Carro";
	private static final double PRECIO_HORA_CARRO=1000;
	private static final double PRECIO_HORA_MOTO=500;
	private static final double PRECIO_DIA_CARRO=8000;
	private static final double PRECIO_DIA_MOTO=4000;
	ReglaAltoCilindraje reglaAltoCilindraje;
	
	public double calcularCosto(int horas, Vehiculo vehiculo) {	
		switch (vehiculo.getTipoVehiculo()) {
		case TIPO_CARRO:
			return calcularCostoCarro(horas, vehiculo);
		case TIPO_MOTO:
			return calcularCostoMoto(horas, vehiculo);
		default:
			return 0;
		}	
	}
	
	public double calcularCostoCarro(int horas, Vehiculo vehiculo) {
		double cobro=0;
		if(horas<9) {
    		cobro=horas*PRECIO_HORA_CARRO;
    	}else {
    		cobro=cobrarPorDia(horas, vehiculo);
    	}
		return cobro;
	}
	public double calcularCostoMoto(int horas, Vehiculo vehiculo) {
		double cobro=0;
		if(horas<9) {
    		cobro=horas*PRECIO_HORA_MOTO;
    	}else {
    		cobro+=cobrarPorDia(horas,vehiculo);  		
    	}
		reglaAltoCilindraje=new ReglaAltoCilindraje();
    	cobro+=reglaAltoCilindraje.cobrarCilindraje(vehiculo.getCilindraje());
    	return cobro;
	}
	public double cobrarPorDia(int horas, Vehiculo vehiculo) {
		double cobro=0;
		if(horas>24)
		{
			int dias=(horas/24);
			int horasNuevas=horas%24;
			cobro=cobrarDias(dias,horasNuevas,vehiculo);
		}
		else {
			cobro=validarCostoSegunVehiculo(vehiculo);
		}
		return cobro;
	}
	
	public double validarCostoSegunVehiculo(Vehiculo vehiculo) {
		switch (vehiculo.getTipoVehiculo()) {
		case TIPO_CARRO:
			return PRECIO_DIA_CARRO;
		case TIPO_MOTO:
			return PRECIO_DIA_MOTO;
		default:
			return 0;
		}		
	}
	
	public double cobrarDias(int dias, int horas,Vehiculo vehiculo) {
		double cobro=0;
		switch(vehiculo.getTipoVehiculo()){
			case TIPO_MOTO:
				if(horas<9)
				{
					cobro=(dias*PRECIO_DIA_MOTO)+(horas*PRECIO_HORA_MOTO);
				}else {
					cobro=((dias+1)*PRECIO_DIA_MOTO);
				}
				break;
			case TIPO_CARRO:
				if(horas<9)
				{
					cobro=(dias*PRECIO_DIA_CARRO)+(horas*PRECIO_HORA_CARRO);
				}else {
					cobro=((dias+1)*PRECIO_DIA_CARRO);
				}
				break;
			default:
				break;
		}
		
		return cobro;
	}
}
