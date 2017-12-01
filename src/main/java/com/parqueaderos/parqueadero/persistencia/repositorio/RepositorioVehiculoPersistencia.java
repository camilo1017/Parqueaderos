package com.parqueaderos.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.persistencia.VehiculoEntity;
import com.parqueaderos.parqueadero.persistencia.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;

@Repository
public class RepositorioVehiculoPersistencia implements RepositorioVehiculo{
	private final static String VEHICULO_FIND_ALL = "vehiculo.findAll";
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

	@Override
	public List<Vehiculo> listarVehiculos() {
		List<VehiculoEntity> listaEntity = listarVehiculosEntity();
		List<Vehiculo> listaVehiculos = new ArrayList<>();				
		for (int i = 0; i < listaEntity.size(); ++i) {
			Vehiculo vehiculo = new VehiculoBuilder().convertToDomain(listaEntity.get(i));
			listaVehiculos.add(vehiculo);
		}
		return listaVehiculos;
	}

	@Override
	public List<VehiculoEntity> listarVehiculosEntity() {
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_ALL);		
		List<VehiculoEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList : null;
	}

}
