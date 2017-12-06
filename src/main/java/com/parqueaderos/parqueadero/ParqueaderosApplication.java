package com.parqueaderos.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.parqueaderos.parqueadero.dominio.Parqueadero;
import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.dominio.Vigilante;

@ComponentScan(basePackages={"com.parqueaderos.parqueadero.persistencia.repositorio","com.parqueaderos.parqueadero.rest"})
@EntityScan("com.parqueaderos.parqueadero.persistencia")
@SpringBootApplication
public class ParqueaderosApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ParqueaderosApplication.class, args);
	}
}
