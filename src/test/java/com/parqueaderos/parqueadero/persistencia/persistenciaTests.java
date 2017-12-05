package com.parqueaderos.parqueadero.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.parqueaderos.parqueadero.builder.VigilanteBuilder;
import com.parqueaderos.parqueadero.dominio.Parqueadero;
import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.dominio.Vigilante;
import com.parqueaderos.parqueadero.persistencia.builder.ReciboBuilder;
import com.parqueaderos.parqueadero.persistencia.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.reglas.ReglaPrimerLetraDeLaPlaca;
import com.parqueaderos.parqueadero.reglas.ReglaTiposDeCobro;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;

public class persistenciaTests {
	VehiculoEntity vehiculoEntity;
	VehiculoBuilder vehiculoBuilder;
	Vehiculo vehiculo;
	@Before
	public void initTest()
	{			
		vehiculo=new Vehiculo();
		vehiculoBuilder=new VehiculoBuilder();
		vehiculoEntity=new VehiculoEntity();
	}
	@Test
	public void testVehiculoBuilderToDomain() {
		vehiculoEntity.setMatricula("KAS353");
		vehiculo=vehiculoBuilder.convertToDomain(vehiculoEntity);
		String resultadoObtenido=vehiculo.getMatricula();
		String resultadoEsperado="KAS353";
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
}
