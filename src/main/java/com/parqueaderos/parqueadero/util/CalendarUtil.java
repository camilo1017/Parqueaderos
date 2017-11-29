package com.parqueaderos.parqueadero.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtil {
	private static final int SEGUNDOS_EN_UNA_HORA= 3600;
	public int calcularHoras(String fechaEntradaS,String fechaSalidaS) {
		Date dateFechaEntrada=stringToDate(fechaEntradaS, "/");
		Date dateFechaSalida=stringToDate(fechaSalidaS, "/");
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
	public static Date stringToDate(String fecha,String caracter){
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
	public static String obtenerDiaDeLaSemana(String fechaEntrada){
		Date dateFechaEntrada=stringToDate(fechaEntrada, "/");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFechaEntrada);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			return "Domingo";
		case 2:
			return "Lunes";
		case 3:
			return "Martes";
		case 4:
			return "Miercoles";
		case 5:
			return "Jueves";
		case 6:
			return "Viernes";
		case 7:
			return "Sabado";
		default:
			return "ERROR";
		}	
	}
}
