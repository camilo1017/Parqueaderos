package com.parqueaderos.parqueadero.persistencia.builder;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.persistencia.VehiculoEntity;

public class VehiculoBuilder {
	public VehiculoEntity convertToEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity=new VehiculoEntity();
		vehiculoEntity.setTipoVehiculo(vehiculo.getTipoVehiculo());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		vehiculoEntity.setMatricula(vehiculo.getMatricula());
		vehiculoEntity.setNombre(vehiculo.getNombre());
		return vehiculoEntity;
	}
	public Vehiculo convertToDomain(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo=new Vehiculo();
		vehiculo.setTipoVehiculo(vehiculoEntity.getTipoVehiculo());
		vehiculo.setCilindraje(vehiculoEntity.getCilindraje());
		vehiculo.setMatricula(vehiculoEntity.getMatricula());
		vehiculo.setNombre(vehiculoEntity.getNombre());
		return vehiculo;
	}
}
