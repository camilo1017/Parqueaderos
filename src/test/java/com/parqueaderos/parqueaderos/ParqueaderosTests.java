package com.parqueaderos.parqueaderos;


import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.awt.geom.RectangularShape;

import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueaderos.parqueaderos.Parqueadero;
import com.parqueaderos.parqueaderos.Vehiculo;
import com.parqueaderos.parqueaderos.Vigilante;
import com.parqueaderos.parqueaderos.reglas.ReglaPrimerLetraDeLaPlaca;
import com.parqueaderos.parqueaderos.util.CalendarUtil;

import builder.VigilanteBuilder;
import builder.ParqueaderoBuilder;
import builder.VehiculoBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderosTests {
	private Vehiculo moto;
	private Vehiculo vehiculoObject;
	private Vigilante vigilanteObject;
	private Vehiculo carro;
	private Vigilante vigilante;
	private Parqueadero parqueadero;
	private ReglaPrimerLetraDeLaPlaca reglaPrimerLetraDeLaPlaca;
	@Before
	public void initTest()
	{			
		vehiculoObject=mock(Vehiculo.class);
		vigilanteObject=mock(Vigilante.class);	
		moto=VehiculoBuilder.getInstance().withTipoVehiculo("Moto").build();
		carro=VehiculoBuilder.getInstance().withTipoVehiculo("Carro").build();
		vigilante=VigilanteBuilder.getInstance().build();
		parqueadero=new Parqueadero();
		reglaPrimerLetraDeLaPlaca=new ReglaPrimerLetraDeLaPlaca();
	}
	@Test
	public void testCobrarMotoMenosDe9Horas() {
		moto.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/10 12:59:49", moto);
		double resultadoEsperado=1500;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCilindraje() {
		moto.setMatricula("BSS345");
		moto.setCilindraje(600);
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/10 22:59:49", moto);
		double resultadoEsperado=6000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarMotoMasDe24Horas() {
		moto.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/11 11:59:49", moto);
		double resultadoEsperado=5000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarMotoMenosDe24Horas() {
		moto.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/10 23:59:49", moto);
		double resultadoEsperado=4000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarMotoMasDe9HorasDespues() {
		moto.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/11 22:59:49", moto);
		double resultadoEsperado=8000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testNoHayCuposMotos() {
		Parqueadero parqueadero=ParqueaderoBuilder.getInstance().withNroMotos(10).build();		
		boolean resultadoObtenido=parqueadero.ingresarMoto();
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	
	@Test
	public void testCobrarCarroMasDe24Horas() {
		carro.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/11 12:59:49", carro);
		double resultadoEsperado=11000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCarroMenosDe9Horas() {
		carro.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/10 16:59:49", carro);
		double resultadoEsperado=7000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCarroMenosDe24Horas() {
		carro.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/10 10:59:44", "2014/10/10 19:59:49", carro);
		double resultadoEsperado=8000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testCobrarCarroMasDe9HorasDespues() {
		carro.setMatricula("BSS345");
		double resultadoObtenido=vigilante.cobrar("2014/10/09 10:59:44", "2014/10/10 21:59:49", carro);
		double resultadoEsperado=16000;
		assertEquals(resultadoEsperado, resultadoObtenido,0);
	}
	@Test
	public void testNoHayCuposCarros() {
		Parqueadero parqueadero=ParqueaderoBuilder.getInstance().withNroCarros(20).build();
		boolean resultadoObtenido=parqueadero.ingresarCarro();
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testIngresarCarro() {
		boolean resultadoObtenido=parqueadero.ingresarCarro();
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testIngresarMoto() {
		boolean resultadoObtenido=parqueadero.ingresarMoto();
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarMatriculaQueNoEmpiecePorA() {
		carro.setMatricula("BSS345");
		boolean resultadoObtenido=reglaPrimerLetraDeLaPlaca.validarLetraDeLaPlaca(carro);
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarMatriculaQueEmpiecePorA() {
		carro.setMatricula("ASS345");
		boolean resultadoObtenido=reglaPrimerLetraDeLaPlaca.validarLetraDeLaPlaca(carro);
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testCalcularNumeroDeHoras() {
		CalendarUtil calendarUtil = new CalendarUtil();
		int resultadoObtenido=calendarUtil.calcularHoras("2014/10/09 10:59:44", "2014/10/11 14:59:49");
		int resultadoEsperado=53;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testObtenerDiaDeLaSemana() {
		CalendarUtil calendarUtil = new CalendarUtil();
		String resultadoObtenido=CalendarUtil.obtenerDiaDeLaSemana("2017/12/01 10:59:44");
		String resultadoEsperado="Viernes";
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarPlacaEspecial() {
		moto=VehiculoBuilder.getInstance().withMatricula("ASS456").build();
		boolean resultadoObtenido=vigilante.validacionPlacaFecha(moto,"2017/11/27 10:59:44");
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarPlacaNoEspecial() {
		moto=VehiculoBuilder.getInstance().withMatricula("BSS456").build();
		boolean resultadoObtenido=vigilante.validacionPlacaFecha(moto,"2017/11/28 10:59:44");
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarPlacaDiaNoHabil() {
		moto=VehiculoBuilder.getInstance().withMatricula("ASS456").build();
		boolean resultadoObtenido=vigilante.validacionPlacaFecha(moto,"2017/11/28 10:59:44");
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
}
