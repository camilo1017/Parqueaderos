package com.parqueaderos.parqueadero.repositorio;

import java.util.List;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.persistencia.VehiculoEntity;

public interface RepositorioVehiculo {
	public void registrarVehiculo(Vehiculo vehiculo);
	public List<VehiculoEntity> listarVehiculosEntity();
	public List<Vehiculo> listarVehiculos();
}
