package com.parqueaderos.parqueadero.persistencia.builder;

import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.persistencia.ReciboEntity;

public class ReciboBuilder {
	public ReciboEntity convertToEntity(Recibo recibo) {
		VehiculoBuilder vehiculoBuilder=new VehiculoBuilder();
		ReciboEntity reciboEntity=new ReciboEntity();
		reciboEntity.setFechaEntrada(recibo.getFechaEntrada());
		reciboEntity.setFechaSalida(recibo.getFechaSalida());
		reciboEntity.setValor(recibo.getCosto());
		reciboEntity.setVehiculo(vehiculoBuilder.convertToEntity(recibo.getVehiculo()));
		return reciboEntity;
	}
	public Recibo convertToDomain(ReciboEntity reciboEntity) {
		VehiculoBuilder vehiculoBuilder=new VehiculoBuilder();
		Recibo recibo = new Recibo();
		recibo.setCosto(reciboEntity.getValor());
		recibo.setFechaEntrada(reciboEntity.getFechaEntrada());
		recibo.setFechaSalida(reciboEntity.getFechaSalida());
		recibo.setVehiculo(vehiculoBuilder.convertToDomain(reciboEntity.getVehiculo()));
		return recibo;
	}
}
