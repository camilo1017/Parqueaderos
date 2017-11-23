package com.parqueaderos.parqueaderos;


import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueaderos.parqueaderos.Vehiculo;
import com.parqueaderos.parqueaderos.Vigilante;

import builder.VigilanteBuilder;
import builder.VehiculoBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderosTests {
	private Vehiculo moto;
	private Vehiculo vehiculoObject;
	private Vigilante vigilanteObject;
	private Vehiculo carro;
	private Vigilante vigilante;
	@Before
	public void initTest()
	{			
		vehiculoObject=mock(Vehiculo.class);
		vigilanteObject=mock(Vigilante.class);	
		moto=VehiculoBuilder.getInstance().withTipoVehiculo("Moto").build();
		carro=VehiculoBuilder.getInstance().withTipoVehiculo("Carro").build();
		vigilante=VigilanteBuilder.getInstance().build();
	}
	@Test
	public void testCobrarMotoMenosDe9Horas() {
		double resultadoObtenido=vigilante.cobrar(8, moto);
		double resultadoEsperado=4000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCilindraje() {
		moto.setCilindraje(600);
		double resultadoObtenido=vigilante.cobrar(8, moto);
		double resultadoEsperado=6000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarMotoMasDe24Horas() {
		double resultadoObtenido=vigilante.cobrar(50, moto);
		double resultadoEsperado=9000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarMotoMenosDe24Horas() {
		double resultadoObtenido=vigilante.cobrar(20, moto);
		double resultadoEsperado=4000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarMotoMasDe9HorasDespues() {
		double resultadoObtenido=vigilante.cobrar(35, moto);
		double resultadoEsperado=8000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testNoHayCuposMotos() {
		Vigilante vigilante=VigilanteBuilder.getInstance().withNroMotos(10).build();
		boolean resultadoObtenido=vigilante.ingresarMoto();
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	
	@Test
	public void testCobrarCarroMasDe24Horas() {
		double resultadoObtenido=vigilante.cobrar(50, carro);
		double resultadoEsperado=18000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCarroMenosDe9Horas() {
		double resultadoObtenido=vigilante.cobrar(7, carro);
		double resultadoEsperado=7000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCarroMenosDe24Horas() {
		double resultadoObtenido=vigilante.cobrar(20, carro);
		double resultadoEsperado=8000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCarroMasDe9HorasDespues() {
		double resultadoObtenido=vigilante.cobrar(35, carro);
		double resultadoEsperado=16000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testNoHayCuposCarros() {
		Vigilante vigilante=VigilanteBuilder.getInstance().withNroCarros(20).build();
		boolean resultadoObtenido=vigilante.ingresarCarro();
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testIngresarCarro() {
		boolean resultadoObtenido=vigilante.ingresarCarro();
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testIngresarMoto() {
		boolean resultadoObtenido=vigilante.ingresarMoto();
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarMatriculaQueNoEmpiecePorA() {
		carro.setMatricula("BSS345");
		boolean resultadoObtenido=vigilante.validarIngreso(carro);
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarMatriculaQueEmpiecePorA() {
		carro.setMatricula("ASS345");
		boolean resultadoObtenido=vigilante.validarIngreso(carro);
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testCalcularNumeroDeHoras() {
		int resultadoObtenido=vigilante.CalcularHoras("2014/10/09 10:59:44", "2014/11/09 17:59:49");
		int resultadoEsperado=32;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
}
