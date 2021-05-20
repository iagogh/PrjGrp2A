package incidencias;

import java.util.ArrayList;
import java.util.Date;

import com.enso.ayuntamiento.Ciudadano;

public interface IGestionIncidencias {
	
	Incidencia presentarIncidencia(Ciudadano vecino, String localizacion, String descripcion, TipoIncidencia tipo);
	Incidencia presentarIncidencia(Ciudadano vecino, String descripcion, TipoIncidencia tipo);
	
	ArrayList<Incidencia> buscarIncidencias(String dni, String idIncidencia, Date fechaInci, Date fechaFin, TipoIncidencia tipo);
	
	Incidencia obtenerIncidencia(String idIncidencia);
	
	ArrayList<Incidencia> obtenerTodasIncidencias();
	
	ArrayList<Incidencia> incidenciasSinProceso();
	
	void finalizarIncidencia(Incidencia incidencia);
	
	void valorarResolucion(Incidencia incidencia, Integer puntuacion);
	
	String notificarResolucion(String idIncidencia);
}
