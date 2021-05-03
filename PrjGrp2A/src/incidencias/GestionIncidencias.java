package incidencias;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Date;

import com.enso.ayuntamiento.Ciudadano;

import procesos.ordenesTrabajo.OrdenTrabajo;

public class GestionIncidencias implements IGestionIncidencias {
	private ArrayList<Incidencia> incidencias;

	
	public GestionIncidencias() {
		this.incidencias = new ArrayList<>();
	}


	public GestionIncidencias(ArrayList<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	public ArrayList<Incidencia> getIncidencias() {
		return this.incidencias;
	}

	@Override
	public Incidencia presentarIncidencia(Ciudadano vecino, String localizacion, String descripcion,
			TipoIncidencia tipo) {
		Incidencia incidencia = new Incidencia(vecino, localizacion, descripcion, tipo);
		this.incidencias.add(incidencia);
		return incidencia;
	}


	@Override
	public Incidencia presentarIncidencia(Ciudadano vecino, String descripcion, TipoIncidencia tipo) {
		Incidencia incidencia = new Incidencia(vecino, descripcion, tipo);
		this.incidencias.add(incidencia);
		return incidencia;
	}

	/**
	 * Busca entre las incidencias almacenadas filtrando a traves de los parametros dados.
	 * 
	 * @param dni cadena de texto para filtrar por dni del autor de la incidencia.
	 * @param idIncidencia cadena de texto para filtrar por el id de la incidencia.
	 * @param fechaInci fecha para filtrar las incidencias estrictamente posteriores a ella.
	 * @param fechaFin fecha para filtrar las incidencias estrictamente anteriores a ella.
	 * @param tipo tipoIncidencia para filtrar segun la naturaleza de la incidencia.
	 * 
	 * @return Lista con las incidencias que cumplen los criterios (puede ser vacia).
	 */
	@Override
	public ArrayList<Incidencia> buscarIncidencias(String dni, String idIncidencia, Date fechaIni, Date fechaFin,
			TipoIncidencia tipo) {
		
		ArrayList<Incidencia> incidenciasFinales = new ArrayList<>();
		Incidencia incidenciaAux;
		Date fechaInicioIncidencia;
		int i;
		
		//Recordar que para esta funci�n los par�metros a null significan que simplemente no buscamos por ese par�metro
		
		/*Comprobaci�n de fechas*/
		for(i=0; i<this.incidencias.size(); i++) {
			incidenciaAux = this.incidencias.get(i);
			fechaInicioIncidencia = incidenciaAux.getFechaInicio();
			
			if(fechaInicioIncidencia != null) {	//comprobamos que la fecha de la orden no sea null
				if(fechaIni.before(fechaFin) || fechaIni == null) {	//comprobamos que las fechas introducidas est�n en orden correcto
					if(fechaIni.before(fechaInicioIncidencia) || fechaIni == null) {	//que la inicial es anterior a la de la orden
						if(fechaInicioIncidencia.before(fechaFin) || fechaFin == null) {	//que la final es posterior a la de la orden
							incidenciasFinales.add(incidenciaAux);
						}
					}
				}
			}
		}
		
		/*Comprobaci�n de dni del ciudadano, id de la incidencia y tipo de la incidencia, si no coinciden, se eliminan*/
		for(i = 0; i<incidenciasFinales.size(); i++) {
			incidenciaAux = incidenciasFinales.get(i);
			if(!incidenciaAux.getDniCiudadano().equals(dni) && dni != null) {
				incidenciasFinales.remove(i);
			}else if(!incidenciaAux.getId().equals(idIncidencia) && idIncidencia != null) {
				incidenciasFinales.remove(i);
			}else if(!incidenciaAux.getTipo().equals(tipo) && tipo != null) {
				incidenciasFinales.remove(i);
			}
		}
		
		return incidenciasFinales;
	}
	
	
	
	/**
	 * Busca en las incidencias almacenadas actualmente la incidencia solicitada por id y la devuelve
	 * en caso de ser encontrada.
	 * 
	 * @param idIncidencia cadena de texto con el id de la incidencia que se quiere obtener.
	 * @return Incidencia referenciada por el id o null si no se halla.
	 */
	@Override
	public Incidencia obtenerIncidencia(String idIncidencia) {
		for(Incidencia inc : this.incidencias) {
			if(inc.getId().equals(idIncidencia)) {
				return inc;
			}
		}
		return null;
	}

	public ArrayList<Incidencia> obtenerTodasIncidencias() {
		return this.incidencias;
	}
	
	/**
	 * Busca en las incidencias almacenadas actualmente aquellas que no tienen un proceso asignado y devuelve
	 * el conjunto de ellas.
	 * 
	 * @return lista con todas las incidencias sin Proceso.
	 */
	@Override
	public ArrayList<Incidencia> incidenciasSinProceso() {
		ArrayList<Incidencia> returnSet = new ArrayList<Incidencia>();
		  
		for(Incidencia inc : this.incidencias) {
			if(!inc.tieneProceso()) {
				returnSet.add(inc);
			}
		}
		
		return returnSet;
	}


	@Override
	public void finalizarIncidencia(Incidencia incidencia) {
		incidencia.finalizar();
	}


	@Override
	public void valorarResolucion(Incidencia incidencia, Integer puntuacion) {
		this.incidencias.get(this.incidencias.indexOf(incidencia)).setValoracion(puntuacion);
	}


	@Override
	public String notificarResolucion(String idIncidencia) {
		// TODO Determinar que se devuelve para notificar la resolucion de la incidencia.
		return null;
	}

	/**
	 * Metodo que devuelve si una incidencia fue creada dentro de un rango de fechas determinado. Ha de estar estrictamente
	 * dentro i.e no puede coincidir con un valor los extremos pra ser considerada dentro del rango.
	 * 
	 * @param fechaIni fecha limite inferior del rango.
	 * @param fechaFin fecha limite superior del rango.
	 * @param incidencia incidencia a comprobar.
	 * @return true si la fecha de incio de la Incidencia esta estrictamente dentro del rango o false en caso contrario.
	 */
	boolean dentroDelRango(Date fechaIni, Date fechaFin, Incidencia incidencia){
		return ((incidencia.getFechaInicio().after(fechaIni)) && (incidencia.getFechaInicio().before(fechaFin)));
	 }
	
}
