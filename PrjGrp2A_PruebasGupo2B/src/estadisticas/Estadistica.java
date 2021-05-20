package estadisticas;

import java.util.ArrayList;

/**
 * Clase que encapsula el resultado de una solicitud de estadisticas. Permitiendo tener el set resultado
 * de una consulta, numero de items encontrados y el coste medio.
 *
 * @param <T> Tipo variable para poder ser usado por Incidencia, Procesos y OrdTrabajo de la misma forma.
 */
public class Estadistica<T> {
	private Double costeMedio;
	private ArrayList<T> listaResultado;
	private int numero; //numero de items del arraylist o sea lista resultado
	
	public Estadistica() {
		
	}

	public Estadistica(Double coste, ArrayList<T> listaResultado) {
		this.costeMedio = coste;
		this.listaResultado = listaResultado;
		
		if(listaResultado != null) {
			numero = listaResultado.size();
		} else {
			numero = 0;
		}
	}

	public Double getCoste() {
		return costeMedio;
	}

	public ArrayList<T> getListaResultado() {
		return listaResultado;
	}
	
	public int getNumeroItems(){
		return numero;
	}
	
}
