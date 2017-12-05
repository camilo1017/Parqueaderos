package com.parqueaderos.parqueadero.dominio;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.awt.geom.RectangularShape;
import java.util.Calendar;

import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueaderos.parqueadero.builder.ParqueaderoBuilder;
import com.parqueaderos.parqueadero.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.builder.VigilanteBuilder;
import com.parqueaderos.parqueadero.excepciones.ParqueaderosException;
import com.parqueaderos.parqueadero.reglas.ReglaPrimerLetraDeLaPlaca;
import com.parqueaderos.parqueadero.reglas.ReglaTiposDeCobro;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;
import com.parqueaderos.parqueadero.util.CalendarUtil;

@SpringBootTest
public class ParqueaderosTests {
	private Vehiculo moto;
	private Vehiculo vehiculoObject;
	private Vigilante vigilanteObject;
	private Vehiculo carro;
	private Vigilante vigilante;
	private Parqueadero parqueadero;
	private ReglaPrimerLetraDeLaPlaca reglaPrimerLetraDeLaPlaca;
	private ReglaTiposDeCobro reglaTiposDeCobro = new ReglaTiposDeCobro();
	private Calendar fechaEntrada =Calendar.getInstance();
	private Calendar fechaSalida=Calendar.getInstance();
	private RepositorioRecibo repositorioRecibo;
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
		moto.setCilindraje(100);
		assertEquals(1500, reglaTiposDeCobro.calcularCosto(3,moto),0);
	}
	@Test
	public void testCobrarCilindraje() {
		moto.setMatricula("BSS345");
		moto.setCilindraje(600);
		assertEquals(3500, reglaTiposDeCobro.calcularCosto(3,moto),0);
	}
	@Test
	public void testCobrarMotoMasDe24Horas() {
		moto.setMatricula("BSS345");
		moto.setCilindraje(100);
		assertEquals(4500, reglaTiposDeCobro.calcularCosto(25,moto),0);
	}
	@Test
	public void testCobrarMotoMenosDe24Horas() {
		moto.setMatricula("BSS345");
		moto.setCilindraje(100);
		assertEquals(4000, reglaTiposDeCobro.calcularCosto(11,moto),0);
	}
	@Test
	public void testCobrarMotoMasDe9HorasDespues() {
		moto.setMatricula("BSS345");
		moto.setCilindraje(100);
		assertEquals(8000, reglaTiposDeCobro.calcularCosto(34,moto),0);
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
		carro.setCilindraje(1000);
		assertEquals(11000, reglaTiposDeCobro.calcularCosto(27,carro),0);
	}
	@Test
	public void testCobrarCarroMenosDe9Horas() {
		carro.setMatricula("BSS345");
		carro.setCilindraje(1000);
		assertEquals(7000, reglaTiposDeCobro.calcularCosto(7,carro),0);
	}
	@Test
	public void testCobrarCarroMenosDe24Horas() {
		carro.setMatricula("BSS345");
		carro.setCilindraje(1000);
		assertEquals(8000, reglaTiposDeCobro.calcularCosto(12,carro),0);
	}
	@Test
	public void testCobrarCarroMasDe9HorasDespues() {
		carro.setMatricula("BSS345");
		carro.setCilindraje(1000);
		assertEquals(16000, reglaTiposDeCobro.calcularCosto(35,carro),0);
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
		fechaEntrada.set(2010, Calendar.NOVEMBER, 12, 11, 55, 0);
		fechaSalida.set(2010, Calendar.NOVEMBER, 12, 22, 56, 0);
		int resultadoObtenido=calendarUtil.calcularHoras(fechaEntrada, fechaSalida);
		int resultadoEsperado=12;
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
		fechaEntrada.set(2017, Calendar.NOVEMBER, 27, 10, 59, 44);
		boolean resultadoObtenido=vigilante.validacionPlacaFecha(moto,fechaEntrada);
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarPlacaNoEspecial() {
		moto=VehiculoBuilder.getInstance().withMatricula("BSS456").build();
		fechaEntrada.set(2017, Calendar.NOVEMBER, 27, 10, 59, 44);
		boolean resultadoObtenido=vigilante.validacionPlacaFecha(moto,fechaEntrada);
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	@Test
	public void testValidarPlacaDiaNoHabil() {
		moto=VehiculoBuilder.getInstance().withMatricula("ASS456").build();
		fechaEntrada.set(2017, Calendar.NOVEMBER, 28, 10, 59, 44);
		boolean resultadoObtenido=vigilante.validacionPlacaFecha(moto,fechaEntrada);
		boolean resultadoEsperado=false;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	@Test
	public void testValidarIngresoCarroYMoto() {
		carro=VehiculoBuilder.getInstance().withMatricula("BSS456").withTipoVehiculo("Carro").withCilindraje(1200).build();
		moto=VehiculoBuilder.getInstance().withMatricula("BSS656").withTipoVehiculo("Moto").withCilindraje(1200).build();
		fechaEntrada.set(2017, Calendar.NOVEMBER, 28, 10, 59, 44);
		boolean resultadoObtenido1=vigilante.validarIngreso(carro, fechaEntrada);
		boolean resultadoObtenido2=vigilante.validarIngreso(moto, fechaEntrada);
		boolean resultadoEsperado=true;
		assertEquals(resultadoEsperado, resultadoObtenido1);
		assertEquals(resultadoEsperado, resultadoObtenido2);
	}
	@Test
	public void testValidarIngresoException() {
		carro=VehiculoBuilder.getInstance().withMatricula("BSS456").withTipoVehiculo("Cicla").build();
		fechaEntrada.set(2017, Calendar.NOVEMBER, 28, 10, 59, 44);
		try {
			boolean resultadoObtenido=vigilante.validarIngreso(carro, fechaEntrada);
			fail();

		} catch (ParqueaderosException e) {			
			Assert.assertEquals("Solo es posible ingresar un Carro o una Moto al sistema", e.getMessage());
		}
	}

}
