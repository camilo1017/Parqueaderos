package com.parqueaderos.parqueadero.reglas;

import java.util.Calendar;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.util.CalendarUtil;

public class ReglaPrimerLetraDeLaPlaca {
	public boolean validarLetraDeLaPlaca(Vehiculo vehiculo) {
		String[] arrayMatricula=vehiculo.getMatricula().split("");
		return arrayMatricula[0].equals("A");
	}
	public boolean validacionDiaHabil(Calendar fechaEntrada) {
		return fechaEntrada.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY ||fechaEntrada.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY;
	}
}
