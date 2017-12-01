package com.parqueaderos.parqueadero.dominio;

import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueaderos.parqueadero.ParqueaderosApplication;
import com.parqueaderos.parqueadero.builder.VehiculoBuilder;
import com.parqueaderos.parqueadero.builder.VigilanteBuilder;
import com.parqueaderos.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;
import com.parqueaderos.parqueadero.reglas.ReglaPrimerLetraDeLaPlaca;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParqueaderosApplication.class })
@DataJpaTest
public class IntegracionTest {
	
	@Autowired
	Vigilante vigilante;
	private Vehiculo vehiculo;
	
	@Test
	public void testRegistrarVehiculo() {		
		//vigilante = new Vigilante(repositorioVehiculoPersistencia);
		vehiculo =VehiculoBuilder.getInstance().withCilindraje(1400).withMarca("Chevrolet")
		.withMatricula("KAS353").withNombre("Sail").build();
		vigilante.registrarVehiculo(vehiculo);	
		System.out.println("Hola");
	}
}
