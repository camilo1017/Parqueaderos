package com.parqueaderos.parqueadero.rest;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parqueaderos.parqueadero.dominio.Recibo;
import com.parqueaderos.parqueadero.dominio.Vehiculo;
import com.parqueaderos.parqueadero.dominio.Vigilante;
import com.parqueaderos.parqueadero.excepciones.ParqueaderosException;
import com.parqueaderos.parqueadero.persistencia.ReciboEntity;
import com.parqueaderos.parqueadero.repositorio.RepositorioRecibo;
import com.parqueaderos.parqueadero.repositorio.RepositorioVehiculo;
import com.parqueaderos.parqueadero.util.CalendarUtil;

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
	
	@CrossOrigin
	@RequestMapping(value = "/ingreso/vehiculo", method = RequestMethod.POST)
	@ResponseBody
	public Recibo ingresarVehiculo(@RequestBody Vehiculo vehiculo) {
		ReciboEntity reciboEncontrado= repositorioRecibo.obtenerReciboEntityPorPlaca(vehiculo.getMatricula());
		if(reciboEncontrado!=null) {
			throw new ParqueaderosException("Vehiculo ya ingresado al parqueadero");
		}
		Recibo recibo=new Recibo(Calendar.getInstance(),vehiculo);
		recibo=vigilante.generarRecibo(recibo.getFechaEntrada(), Calendar.getInstance(), vehiculo);
		return recibo;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/ingreso/recibo", method = RequestMethod.POST)
	@ResponseBody
	public Recibo ingresarRecibo(@RequestBody Vehiculo vehiculo) {			
		ReciboEntity reciboEncontrado= repositorioRecibo.obtenerReciboEntityPorPlaca(vehiculo.getMatricula());
		Calendar fechaEntrada = reciboEncontrado.getFechaEntrada();
		Calendar fechaSalida = Calendar.getInstance();
		return vigilante.generarRecibo(fechaEntrada, fechaSalida, vehiculo);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/listarVehiculos", method = RequestMethod.GET)
	@ResponseBody
	public List<Vehiculo> consultarListaDeVehiculos() {	
		return repositorioVehiculo.listarVehiculos();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/listarRecibos", method = RequestMethod.GET)
	@ResponseBody
	public List<Recibo> consultarListaDeRecibos() {	
		return repositorioRecibo.listarRecibos();
	}
}
