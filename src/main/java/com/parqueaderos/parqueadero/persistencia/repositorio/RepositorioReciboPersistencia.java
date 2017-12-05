package com.parqueaderos.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.excepciones.ParqueaderosException;
import com.parqueaderos.parqueadero.persistencia.ReciboEntity;
import com.parqueaderos.parqueadero.persistencia.VehiculoEntity;
import com.parqueaderos.parqueadero.persistencia.builder.ReciboBuilder;
import com.parqueaderos.parqueadero.persistencia.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;

@Repository
public class RepositorioReciboPersistencia implements RepositorioRecibo{
	
	private final static String RECIBO_FIND_BY_MATRICULA = "recibo.findByMatricula";
	private final static String MATRICULA = "matricula";
	private final static String RECIBO_FIND_ALL = "recibo.findAll";
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

	@Override
	public List<ReciboEntity> listarRecibosEntity() {
		Query query = entityManager.createNamedQuery(RECIBO_FIND_ALL);		
		List<ReciboEntity> resultList = query.getResultList();
		if(resultList.isEmpty())
			throw new ParqueaderosException("No hay recibos que mostrar");
		return resultList;
	}

	@Override
	public List<Recibo> listarRecibos() {
		List<ReciboEntity> listaEntity = listarRecibosEntity();
		List<Recibo> listaRecibos = new ArrayList<>();				
		for (int i = 0; i < listaEntity.size(); ++i) {
			Recibo recibo = new ReciboBuilder().convertToDomain(listaEntity.get(i));
			listaRecibos.add(recibo);
		}
		return listaRecibos;
	}
	
	@SuppressWarnings("rawtypes")
	public ReciboEntity obtenerReciboEntityPorPlaca(String matricula) {
		Query query = entityManager.createNamedQuery(RECIBO_FIND_BY_MATRICULA);
		query.setParameter(MATRICULA, matricula);		
		List listarecibos = query.getResultList();
		return !listarecibos.isEmpty() ? (ReciboEntity) listarecibos.get(0) : null;
	}
}
