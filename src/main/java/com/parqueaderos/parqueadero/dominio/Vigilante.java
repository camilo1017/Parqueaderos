package com.parqueaderos.parqueadero.dominio;

import java.util.Calendar;

import com.parqueaderos.parqueadero.excepciones.ParqueaderosException;
import com.parqueaderos.parqueadero.reglas.ReglaPrimerLetraDeLaPlaca;
import com.parqueaderos.parqueadero.reglas.ReglaTiposDeCobro;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;
import com.parqueaderos.parqueadero.util.CalendarUtil;

public class Vigilante {
	private static final String TIPO_MOTO="Moto";
	private static final String TIPO_CARRO="Carro";
	ReglaPrimerLetraDeLaPlaca reglaPrimerLetraDeLaPlaca;
	ReglaTiposDeCobro reglaTiposDeCobro;
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioRecibo repositorioRecibo;
	
	public Vigilante() {
		
	}
	
	public Vigilante(RepositorioVehiculo repositorioVehiculo, RepositorioRecibo repositorioRecibo) {
		
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioRecibo = repositorioRecibo;
	}
	
	public Recibo generarRecibo(Calendar fechaEntrada, Calendar fechaSalida, Vehiculo vehiculo) {
		double cobro=0;
		CalendarUtil calendarUtil=new CalendarUtil();
		int horas=calendarUtil.calcularHoras(fechaEntrada, fechaSalida);
		reglaTiposDeCobro=new ReglaTiposDeCobro();
		if(validarIngreso(vehiculo, fechaEntrada)){
    		cobro=reglaTiposDeCobro.calcularCosto(horas,vehiculo);
    		repositorioRecibo.insertar(new Recibo(fechaEntrada,fechaSalida,vehiculo,cobro));
			return new Recibo(fechaEntrada,fechaSalida,vehiculo,cobro);
		}	
		return new Recibo();
	}
	
	public boolean validacionPlacaFecha(Vehiculo vehiculo,Calendar fechaEntrada) {
		reglaPrimerLetraDeLaPlaca=new ReglaPrimerLetraDeLaPlaca();
		boolean validacionLetraDeLaPlaca=reglaPrimerLetraDeLaPlaca.validarLetraDeLaPlaca(vehiculo);
		boolean validacionDiaHabil=reglaPrimerLetraDeLaPlaca.validacionDiaHabil(fechaEntrada);
		return ((validacionLetraDeLaPlaca && validacionDiaHabil) || !validacionLetraDeLaPlaca);
	}
	
	public boolean validarIngreso(Vehiculo vehiculo, Calendar fechaEntrada) {
		Parqueadero parqueadero=new Parqueadero();
		switch (vehiculo.getTipoVehiculo()) {
        case TIPO_MOTO:
        	return parqueadero.ingresarMoto() && validacionPlacaFecha(vehiculo, fechaEntrada);
        case TIPO_CARRO:
        	return parqueadero.ingresarCarro() && validacionPlacaFecha(vehiculo, fechaEntrada);
         default:
        	 throw new ParqueaderosException("Solo es posible ingresar un Carro o una Moto al sistema");
		}
	}
	
	public void registrarVehiculo(Vehiculo vehiculo) {
		repositorioVehiculo.registrarVehiculo(vehiculo);
	}
}
