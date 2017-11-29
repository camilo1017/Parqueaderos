package com.parqueaderos.parqueadero.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.parqueaderos.parqueadero.dominio.Vehiculo;

import javassist.expr.NewArray;

public class ParqueaderoEntity {
	
	@OneToMany
	private List<VehiculoEntity> vehiculos=new ArrayList<VehiculoEntity>();

	public List<VehiculoEntity> getVehiculo() {
		return vehiculos;
	}

	public void setVehiculo(List<VehiculoEntity> vehiculos) {
		this.vehiculos = vehiculos;
	}
}
