package com.parqueaderos.parqueadero.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.dominio.Vigilante;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;

@EnableAutoConfiguration
@RestController
@Transactional
@RequestMapping(value="/parqueadero")
public class ControladorVigilante {
	
	@Autowired
	Vigilante vigilante;
	@Autowired
	RepositorioRecibo repositorioRecibo;
	@Autowired
	RepositorioVehiculo repositorioVehiculo;
	@RequestMapping(value = "/ingreso/vehiculo", method = RequestMethod.GET)
	@ResponseBody
	public String ingresarVehiculo() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setCilindraje(1800);
		vehiculo.setMarca("Volkswagen");
		vehiculo.setMatricula("TCA662");
		vehiculo.setYearModel(2012);
		vehiculo.setTipoVehiculo("Carro");
		vehiculo.setNombre("Golf");
		vigilante.registrarVehiculo(vehiculo);
		return "Vehiculo ingresado: " + vehiculo.getMarca()+" "+vehiculo.getNombre();
	}
	
	@RequestMapping(value = "/ingreso/recibo", method = RequestMethod.GET)
	@ResponseBody
	public String ingresarRecibo() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setCilindraje(1600);
		vehiculo.setMarca("Auteco");
		vehiculo.setMatricula("BDW33D");
		vehiculo.setYearModel(2014);
		vehiculo.setTipoVehiculo("Moto");
		vehiculo.setNombre("Pulsar");
		String fechaEntrada="2014/10/10 10:59:44";
		String fechaSalida="2014/10/11 09:59:49";
		Recibo recibo=vigilante.generarRecibo(fechaEntrada, fechaSalida, vehiculo);
		return "Recibo generado: Fecha de entrada: \n" + recibo.getFechaEntrada()+"\n"+"Fecha de salida: "+recibo.getFechaSalida()+"\n"+"Costo: "+recibo.getCosto();
	}
	
	@RequestMapping(value = "/ingreso/listarVehiculos", method = RequestMethod.GET)
	@ResponseBody
	public List<Vehiculo> consultarListaDeVehiculos() {	
		return repositorioVehiculo.listarVehiculos();
	}
	
	@RequestMapping(value = "/ingreso/listarRecibos", method = RequestMethod.GET)
	@ResponseBody
	public List<Recibo> consultarListaDeRecibos() {	
		return repositorioRecibo.listarRecibos();
	}
}
