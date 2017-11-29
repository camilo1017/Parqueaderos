package com.parqueaderos.parqueadero.dominio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"persistencia.repositorios","rest"})
@EntityScan("persistencia")
@SpringBootApplication
public class ParqueaderosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderosApplication.class, args);
	}
}
