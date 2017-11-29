package com.parqueaderos.parqueadero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.dominio.Vigilante;

@EnableAutoConfiguration
@RestController
@Transactional
@RequestMapping(value="/parqueadero")
public class ControladorVigilante {
	
	@Autowired
	Vigilante vigilante;
	
	@RequestMapping(value = "/ingreso/carro", method = RequestMethod.GET)
	@ResponseBody
	public String ingresarVehiculo() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setCilindraje(2800);
		vehiculo.setMarca("Chevrolet");
		vehiculo.setMatricula("KAS902");
		vehiculo.setYearModel(2013);
		vehiculo.setTipoVehiculo("carro");
		vehiculo.setNombre("TrailBlazer");
		
		vigilante.registrarVehiculo(vehiculo);
		return "OK";
	}
}
