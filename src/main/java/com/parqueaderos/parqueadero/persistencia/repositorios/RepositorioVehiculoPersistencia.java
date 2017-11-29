package com.parqueaderos.parqueadero.persistencia.repositorios;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.persistencia.VehiculoEntity;
import com.parqueaderos.parqueadero.persistencia.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;

@Repository
public class RepositorioVehiculoPersistencia implements RepositorioVehiculo{
	
	private VehiculoBuilder vehiculoBuilder=new VehiculoBuilder();
	EntityManager entityManager;
	
	public RepositorioVehiculoPersistencia(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void registrarVehiculo(Vehiculo vehiculo) {		
		VehiculoEntity vehiculoEntity = vehiculoBuilder.convertToEntity(vehiculo);
		entityManager.persist(vehiculoEntity);
	}

}
