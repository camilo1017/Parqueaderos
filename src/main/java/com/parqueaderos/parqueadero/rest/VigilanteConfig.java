package com.parqueaderos.parqueadero.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.parqueaderos.parqueadero.dominio.Vigilante;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;

@Configuration
public class VigilanteConfig {
	
	@Bean
	public Vigilante inicializarVigilante(RepositorioVehiculo repositorioVehiculo, RepositorioRecibo repositorioRecibo) {
		return new Vigilante(repositorioVehiculo, repositorioRecibo);
	}
}


