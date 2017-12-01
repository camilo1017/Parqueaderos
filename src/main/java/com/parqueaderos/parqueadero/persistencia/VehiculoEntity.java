package com.parqueaderos.parqueadero.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity(name="Vehiculo")
@NamedQuery(name = "vehiculo.findAll", query = "SELECT vehiculo from Vehiculo vehiculo")
public class VehiculoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column//(name="vehiculo_matricula")
	private String matricula;
	
	@Column//(name="vehiculo_nombre")
	private String nombre;	
	
	@Column//(name="vehiculo_cilindraje")
	private int cilindraje;

	@Column//(name="vehiculo_tipo")
	private String tipoVehiculo;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
}
