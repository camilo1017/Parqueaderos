package com.parqueaderos.parqueadero.persistencia.repositorio;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.persistencia.ReciboEntity;
import com.parqueaderos.parqueadero.persistencia.builder.ReciboBuilder;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;

@Repository
public class RepositorioReciboPersistencia implements RepositorioRecibo{
	
	private ReciboBuilder reciboBuilder=new ReciboBuilder();
	EntityManager entityManager;
	
	public RepositorioReciboPersistencia(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void insertar(Recibo recibo) {
		ReciboEntity reciboEntity = reciboBuilder.convertToEntity(recibo);
		entityManager.persist(reciboEntity);
	}

}
