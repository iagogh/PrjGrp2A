package procesos;

import java.util.ArrayList;
import java.util.Date;

import com.enso.ayuntamiento.Concejal;

import incidencias.Incidencia;
import procesos.ordenesTrabajo.IGestionOrdenesTrabajo;
import procesos.ordenesTrabajo.OrdenTrabajo;

public interface IGestionProcesos {
	Proceso crearNuevoProceso(String nombre, Concejal responsable, String descripcion, ArrayList<Incidencia> incidencias);
	
	void vincularIncidencia(Proceso proceso, ArrayList<Incidencia> incidencias);
	
	void vincularOrdenTrabajo(Proceso proceso, OrdenTrabajo ordenTrabajo);
	
	void finalizarProceso(Proceso proceso);
	
	ArrayList<Proceso> consultarProcesos(Date fechaIni, Date fechaFin, Incidencia inc, Concejal responsable, EstadoAvance s, OrdenTrabajo o);
	
	ArrayList<Proceso> consultarProcesosSinOrdenesTrabajo(Date fechaIni, Date fechaFin, Incidencia inc, Concejal responsable, EstadoAvance s);

	Float calcularCosteProceso(Proceso proceso);
	
	IGestionOrdenesTrabajo devolverGestorOrdenesTrabajo();
}
