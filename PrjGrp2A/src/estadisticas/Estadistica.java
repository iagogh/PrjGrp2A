package estadisticas;

import java.util.ArrayList;

public class Estadistica<T> {
	private Double coste;
	private ArrayList<T> listaResultado;
	
	public Estadistica() {
		super();
	}

	public Estadistica(Double coste, ArrayList<T> listaResultado) {
		super();
		this.coste = coste;
		this.listaResultado = listaResultado;
	}

	public Double getCoste() {
		return coste;
	}

	public ArrayList<T> getListaResultado() {
		return listaResultado;
	}
	
	
}
