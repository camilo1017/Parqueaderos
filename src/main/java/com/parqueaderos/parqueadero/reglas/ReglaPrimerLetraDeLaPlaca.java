package com.parqueaderos.parqueadero.reglas;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.util.CalendarUtil;

public class ReglaPrimerLetraDeLaPlaca {
	public boolean validarLetraDeLaPlaca(Vehiculo vehiculo) {
		String[] arrayMatricula=vehiculo.getMatricula().split("");
		return arrayMatricula[0].equals("A");
	}
	public boolean validacionDiaHabil(String fechaEntrada) {
		return CalendarUtil.obtenerDiaDeLaSemana(fechaEntrada).equals("Domingo") || CalendarUtil.obtenerDiaDeLaSemana(fechaEntrada).equals("Lunes");
	}
}
