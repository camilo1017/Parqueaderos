package com.parqueaderos.parqueadero.persistencia;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "Recibo")
@NamedQueries({
	
	@NamedQuery(name = "recibo.findAll", query = "SELECT recibo from Recibo recibo"),
	@NamedQuery(name = "recibo.findByMatricula", query = "SELECT recibo from Recibo recibo WHERE recibo.vehiculo.matricula= :matricula AND recibo.valor=0")
})
public class ReciboEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_VEHICULO", referencedColumnName = "id")
	private VehiculoEntity vehiculo;
	
	@Column//(name = "fecha_Entrada")
	private Calendar fechaEntrada;
	
	@Column//(name = "fecha_Salida")
	private Calendar fechaSalida;

	@Column//(name = "valor")
	private double valor;

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}	

}
