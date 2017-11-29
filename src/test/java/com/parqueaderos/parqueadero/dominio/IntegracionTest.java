package com.parqueaderos.parqueadero.dominio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParqueaderosApplication.class })
@DataJpaTest
public class IntegracionTest {
	
	@Autowired
	Vigilante vigilante;
	
	@Test
	public void testRegistrarVehiculo() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setCilindraje(2800);
		vehiculo.setMarca("Chevrolet");
		vehiculo.setMatricula("KAS902");
		vehiculo.setYearModel(2013);
		vehiculo.setTipoVehiculo("carro");
		vehiculo.setNombre("TrailBlazer");
		vigilante.registrarVehiculo(vehiculo);	
	}
}
