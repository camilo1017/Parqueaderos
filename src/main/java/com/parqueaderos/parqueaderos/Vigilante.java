package com.parqueaderos.parqueaderos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Vigilante {
	private static final int NUMERO_CARROS=20;
	private static final int NUMERO_MOTOS=10;
	private static final String TIPO_MOTO="Moto";
	private static final String TIPO_CARRO="Carro";
	private static final double PRECIO_HORA_CARRO=1000;
	private static final double PRECIO_HORA_MOTO=500;
	private static final double PRECIO_DIA_CARRO=8000;
	private static final double PRECIO_DIA_MOTO=4000;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int nroMotos;
	private int nroCarros;
	private Parqueadero parqueadero;
	private static final int SEGUNDOS_EN_UNA_HORA= 3600;
	public int CalcularHoras(String fechaEntradaS,String fechaSalidaS) {
		Date dateFechaEntrada=StringToDate(fechaEntradaS, "/");
		Date dateFechaSalida=StringToDate(fechaSalidaS, "/");
		Calendar fechaEntrada=Calendar.getInstance();
		Calendar fechaSalida=Calendar.getInstance();
		fechaEntrada.setTime(dateFechaEntrada);
		fechaSalida.setTime(dateFechaSalida);
		long cantidadhoras= (ChronoUnit.SECONDS.between(fechaEntrada.toInstant(), fechaSalida.toInstant()));
		double horasDecimal = (double)cantidadhoras/SEGUNDOS_EN_UNA_HORA;				
		double horasEnMinutos =horasDecimal;
		horasEnMinutos = Math.ceil(horasEnMinutos);		
		return (int) horasEnMinutos;
	}
	public double cobrar(int horas, Vehiculo vehiculo) {
		double cobro=0;
		switch (vehiculo.getTipoVehiculo()) {
        case TIPO_MOTO:
        	boolean hayCuposMoto=ingresarMoto();
        	if(hayCuposMoto)
        		cobro=calcularCostoMoto(horas,vehiculo);
            break;
        case TIPO_CARRO:
        	boolean hayCuposCarro=ingresarCarro();
        	if(hayCuposCarro)
        		cobro=calcularCostoCarro(horas,vehiculo);
            break;
         default:
        	 break;
		}
		return cobro;
	}
	private double calcularCostoCarro(int horas, Vehiculo vehiculo) {
		double cobro=0;
		if(horas<9) {
    		cobro=horas*PRECIO_HORA_CARRO;
    	}else {
    		cobro=cobrarPorDia(horas, vehiculo);
    	}
		return cobro;
	}
	private double calcularCostoMoto(int horas, Vehiculo vehiculo) {
		double cobro=0;
		if(horas<9) {
    		cobro=horas*PRECIO_HORA_MOTO;
    	}else {
    		cobro+=cobrarPorDia(horas,vehiculo);  		
    	}
    	cobro+=cobrarCilindraje(vehiculo.getCilindraje());
    	return cobro;
	}
	
	private double cobrarPorDia(int horas, Vehiculo vehiculo) {
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
	private double validarCostoSegunVehiculo(Vehiculo vehiculo) {
		switch (vehiculo.getTipoVehiculo()) {
		case TIPO_CARRO:
			return PRECIO_DIA_CARRO;
		case TIPO_MOTO:
			return PRECIO_DIA_MOTO;
		default:
			return 0;
		}
		
	}
	public boolean ingresarCarro() {
		if(nroCarros<NUMERO_CARROS) {
			nroCarros++;
			return true;
		}else {
			return false;
		}
	}
	public double cobrarCilindraje(int cilindraje) {
		if(cilindraje>500) {
			return 2000;
		}else {
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
	public boolean ingresarMoto() {
		if(nroMotos<NUMERO_MOTOS) {
			nroMotos++;
			return true;
		}else {
			return false;
		}
	}
	public static Date StringToDate(String fecha,String caracter){
		String formatoHora=" HH:mm:ss";
		String formato="yyyy"+caracter+"MM"+caracter+"dd"+formatoHora;
		SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
		Date fechaFormato=null;
		try {
			sdf.setLenient(false);
			fechaFormato=sdf.parse(fecha);
		} catch (ParseException ex) {
		}
		return fechaFormato;
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
	public boolean validarIngreso(Vehiculo vehiculo) {
		String[] arrayMatricula=vehiculo.getMatricula().split("");
		boolean validacion=false;
		if(!arrayMatricula[0].equals("A"))
			validacion=true;
		return validacion;
	}
}
