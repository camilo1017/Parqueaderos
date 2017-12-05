package com.parqueaderos.parqueadero.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.persistencia.builder.ReciboBuilder;
import com.parqueaderos.parqueadero.persistencia.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;

public class persistenciaTests {
	VehiculoEntity vehiculoEntity;
	VehiculoBuilder vehiculoBuilder;
	Vehiculo vehiculo;
	Recibo recibo;
	ReciboEntity reciboEntity;
	ReciboBuilder reciboBuilder;
	RepositorioRecibo repositorioRecibo;
	@Before
	public void initTest()
	{			
		vehiculo=new Vehiculo();
		vehiculoBuilder=new VehiculoBuilder();
		vehiculoEntity=new VehiculoEntity();
		recibo=new Recibo();
		reciboEntity=new ReciboEntity();
		reciboBuilder =new ReciboBuilder();
	}
	@Test
	public void testVehiculoBuilderToDomain() {
		vehiculoEntity.setMatricula("KAS353");
		vehiculo=vehiculoBuilder.convertToDomain(vehiculoEntity);
		String resultadoObtenido=vehiculo.getMatricula();
		String resultadoEsperado="KAS353";
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testVehiculoBuilderToEntity() {
		vehiculo.setMatricula("KAS353");
		vehiculoEntity=vehiculoBuilder.convertToEntity(vehiculo);
		String resultadoObtenido=vehiculoEntity.getMatricula();
		String resultadoEsperado="KAS353";
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testReciboBuilderToDomain() {
		reciboEntity.setValor(5000);
		vehiculoEntity.setMatricula("KAS353");
		reciboEntity.setVehiculo(vehiculoEntity);
		recibo=reciboBuilder.convertToDomain(reciboEntity);
		double resultadoObtenido=recibo.getCosto();
		double resultadoEsperado=5000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testReciboBuilderToEntity() {
		recibo.setCosto(5000);
		vehiculo.setMatricula("KAS353");
		recibo.setVehiculo(vehiculo);
		reciboEntity=reciboBuilder.convertToEntity(recibo);
		double resultadoObtenido=reciboEntity.getValor();
		double resultadoEsperado=5000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testObtenerReciboPorPlaca() {
		reciboEntity=repositorioRecibo.obtenerReciboEntityPorPlaca("KAS353");
		String resultadoEsperado=reciboEntity.getVehiculo().getMatricula();
		String resultadoObtenido="KAS353";
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
}
