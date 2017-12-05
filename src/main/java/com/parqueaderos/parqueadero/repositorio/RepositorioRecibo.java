package com.parqueaderos.parqueadero.repositorio;

import java.util.List;

import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.persistencia.ReciboEntity;

public interface RepositorioRecibo {
	void insertar(Recibo recibo);
	public List<ReciboEntity> listarRecibosEntity();
	public List<Recibo> listarRecibos();
	public ReciboEntity obtenerReciboEntityPorPlaca(String matricula);
	
}
