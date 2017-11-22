package builder;

import com.parqueaderos.parqueaderos.Vehiculo;

public class VehiculoBuilder {
	private String matricula;
	private String nombre;
	private int yearModel;
	private int cilindraje;
	private String marca;
	private String tipoVehiculo;
	public static VehiculoBuilder getInstance() {
		return new VehiculoBuilder();
	}
	public VehiculoBuilder withMatricula(String matricula) {
		this.matricula=matricula;
		return this;
	}
	public VehiculoBuilder withNombre(String nombre) {
		this.nombre=nombre;
		return this;
	}
	public VehiculoBuilder withYearModel(int yearModel) {
		this.yearModel=yearModel;
		return this;
	}
	public VehiculoBuilder withCilindraje(int cilindraje) {
		this.cilindraje=cilindraje;
		return this;
	}
	public VehiculoBuilder withMarca(String marca) {
		this.marca=marca;
		return this;
	}
	public VehiculoBuilder withTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo=tipoVehiculo;
		return this;
	}
	public Vehiculo build() {
		Vehiculo vehiculo=new Vehiculo();
		vehiculo.setCilindraje(cilindraje);
		vehiculo.setMarca(marca);
		vehiculo.setMatricula(matricula);
		vehiculo.setNombre(nombre);
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculo.setYearModel(yearModel);
		return vehiculo;
	}
}
