package com.parqueaderos.parqueadero.dominio;
import com.parqueaderos.parqueadero.reglas.ReglaPrimerLetraDeLaPlaca;
import com.parqueaderos.parqueadero.reglas.ReglaTiposDeCobro;
import com.parqueaderos.parqueadero.util.CalendarUtil;

public class Vigilante {
	private static final String TIPO_MOTO="Moto";
	private static final String TIPO_CARRO="Carro";
	ReglaPrimerLetraDeLaPlaca reglaPrimerLetraDeLaPlaca;
	ReglaTiposDeCobro reglaTiposDeCobro;
	
	public Recibo generarRecibo(String fechaEntrada, String fechaSalida, Vehiculo vehiculo) {
		double cobro=0;
		CalendarUtil calendarUtil=new CalendarUtil();
		int horas=calendarUtil.calcularHoras(fechaEntrada, fechaSalida);
		reglaTiposDeCobro=new ReglaTiposDeCobro();
		if(validarIngreso(vehiculo, fechaEntrada))
    		cobro=reglaTiposDeCobro.calcularCosto(horas,vehiculo);
		return new Recibo(fechaEntrada,fechaSalida,vehiculo,cobro);
	}
	public boolean validacionPlacaFecha(Vehiculo vehiculo,String fechaEntrada) {
		reglaPrimerLetraDeLaPlaca=new ReglaPrimerLetraDeLaPlaca();
		boolean validacionLetraDeLaPlaca=reglaPrimerLetraDeLaPlaca.validarLetraDeLaPlaca(vehiculo);
		boolean validacionDiaHabil=reglaPrimerLetraDeLaPlaca.validacionDiaHabil(fechaEntrada);
		return ((validacionLetraDeLaPlaca && validacionDiaHabil) || !validacionLetraDeLaPlaca);
	}
	public boolean validarIngreso(Vehiculo vehiculo, String fechaEntrada) {
		Parqueadero parqueadero=new Parqueadero();
		switch (vehiculo.getTipoVehiculo()) {
        case TIPO_MOTO:
        	return parqueadero.ingresarMoto() && validacionPlacaFecha(vehiculo, fechaEntrada);
        case TIPO_CARRO:
        	return parqueadero.ingresarCarro() && validacionPlacaFecha(vehiculo, fechaEntrada);
         default:
        	 return false;
		}
	}
}
