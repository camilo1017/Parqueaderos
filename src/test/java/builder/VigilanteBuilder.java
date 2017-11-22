package builder;

import com.parqueaderos.parqueaderos.Vigilante;

public class VigilanteBuilder {
	private int nroMotos;
	private int nroCarros;
	public static VigilanteBuilder getInstance() {
		return new VigilanteBuilder();
	}
	public VigilanteBuilder withNroMotos(int nroMotos) {
		this.nroMotos=nroMotos;
		return this;
	}
	public VigilanteBuilder withNroCarros(int nroCarros) {
		this.nroCarros=nroCarros;
		return this;
	}
	public Vigilante build() {
		Vigilante vigilante=new Vigilante();
		vigilante.setNroCarros(nroCarros);
		vigilante.setNroMotos(nroMotos);
		return vigilante;
	}
}
