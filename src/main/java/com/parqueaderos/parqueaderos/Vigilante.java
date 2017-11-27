package com.parqueaderos.parqueaderos;
import com.parqueaderos.parqueaderos.util.CalendarUtil;

public class Vigilante {
	private static final String TIPO_MOTO="Moto";
	private static final String TIPO_CARRO="Carro";
	private static final double PRECIO_HORA_CARRO=1000;
	private static final double PRECIO_HORA_MOTO=500;
	private static final double PRECIO_DIA_CARRO=8000;
	private static final double PRECIO_DIA_MOTO=4000;
	public double cobrar(String fechaEntrada, String fechaSalida, Vehiculo vehiculo) {
		double cobro=0;
		Parqueadero parqueadero=new Parqueadero();
		CalendarUtil calendarUtil=new CalendarUtil();
		boolean ingresoEsValido=validacionPlaca(vehiculo, fechaEntrada);
		int horas=calendarUtil.calcularHoras(fechaEntrada, fechaSalida);
		switch (vehiculo.getTipoVehiculo()) {
        case TIPO_MOTO:
        	boolean hayCuposMoto=parqueadero.ingresarMoto();
        	if(hayCuposMoto && ingresoEsValido)
        		cobro=calcularCostoMoto(horas,vehiculo);
            break;
        case TIPO_CARRO:
        	boolean hayCuposCarro=parqueadero.ingresarCarro();
        	if(hayCuposCarro && ingresoEsValido)
        		cobro=calcularCostoCarro(horas,vehiculo);
            break;
         default:
        	 break;
		}
		return cobro;
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
	public boolean validarIngreso(Vehiculo vehiculo) {
		String[] arrayMatricula=vehiculo.getMatricula().split("");
		boolean validacion=false;
		if(!arrayMatricula[0].equals("A"))
			validacion=true;
		return validacion;
	}
	public boolean validacionPlaca(Vehiculo vehiculo,String fechaEntrada) {
		if(!validarIngreso(vehiculo) && (CalendarUtil.obtenerDiaDeLaSemana(fechaEntrada).equals("Domingo") || CalendarUtil.obtenerDiaDeLaSemana(fechaEntrada).equals("Lunes")) || validarIngreso(vehiculo)) {		
			return true;
		}
		return false;
	}
}
