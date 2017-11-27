package builder;

import com.parqueaderos.parqueaderos.Parqueadero;
import com.parqueaderos.parqueaderos.Vigilante;

public class ParqueaderoBuilder {
	private int nroMotos;
	private int nroCarros;
	public static ParqueaderoBuilder getInstance() {
		return new ParqueaderoBuilder();
	}
	public ParqueaderoBuilder withNroMotos(int nroMotos) {
		this.nroMotos=nroMotos;
		return this;
	}
	public ParqueaderoBuilder withNroCarros(int nroCarros) {
		this.nroCarros=nroCarros;
		return this;
	}
	public Parqueadero build() {
		Parqueadero parqueadero=new Parqueadero();
		parqueadero.setNroCarros(nroCarros);
		parqueadero.setNroMotos(nroMotos);
		return parqueadero;
	}
}
