package procesos;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import com.enso.ayuntamiento.Concejal;

import incidencias.Incidencia;
import procesos.ordenesTrabajo.GestionOrdenesTrabajo;
import procesos.ordenesTrabajo.IGestionOrdenesTrabajo;
import procesos.ordenesTrabajo.OrdenTrabajo;

public class GestionProcesos implements IGestionProcesos {
	ArrayList<Proceso> procesos;
	IGestionOrdenesTrabajo gestorOrdenes;
	
	public GestionProcesos() {
		procesos = new ArrayList<>();
		gestorOrdenes = new GestionOrdenesTrabajo();
	}

	@Override
	public Proceso crearNuevoProceso(String nombre, Concejal responsable, String descripcion,
			ArrayList<Incidencia> incidencias) {
		Proceso p = new Proceso(nombre, responsable, descripcion);
		p.vincularIncidencia(incidencias);
		procesos.add(p);
		return p;
	}

	@Override
	public void vincularIncidencia(Proceso proceso, ArrayList<Incidencia> incidencias) {
		proceso.vincularIncidencia(incidencias);
	}

	@Override
	public void vincularOrdenTrabajo(Proceso proceso, OrdenTrabajo ordenTrabajo) {
		proceso.vincularOrdenTrabajo(ordenTrabajo);
	}

	@Override
	public void finalizarProceso(Proceso proceso) {
		proceso.setEstado(EstadoAvance.Finalizado);
	}

	@Override
	public ArrayList<Proceso> consultarProcesos(Date fechaIni, Date fechaFin, Incidencia inc, Concejal responsable,
			EstadoAvance s, OrdenTrabajo o) {
		return procesos.stream().filter(p -> 
		(fechaIni == null || p.getFechaInicio().after(fechaIni) &&
		(fechaFin == null || p.getFechaInicio().before(fechaFin)) &&
		(inc == null || p.getIncidencias().contains(inc)) &&
		(responsable == null || p.getResponsable().equals(responsable)) &&
		(s == null || p.getEstado() == s) &&
		(o == null || p.getOrdenesTrabajo().contains(o))
		)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<Proceso> consultarProcesosSinOrdenesTrabajo(Date fechaIni, Date fechaFin, Incidencia inc,
			Concejal responsable, EstadoAvance s) {
		return procesos.stream().filter(p -> 
		p.getOrdenesTrabajo().isEmpty() &&
		(fechaIni == null || p.getFechaInicio().after(fechaIni) &&
		(fechaFin == null || p.getFechaInicio().before(fechaFin)) &&
		(inc == null || p.getIncidencias().contains(inc)) &&
		(responsable == null || p.getResponsable().equals(responsable)) &&
		(s == null || p.getEstado() == s)
		)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Float calcularCosteProceso(Proceso proceso) {
		return proceso.calcularCoste();
	}

	@Override
	public IGestionOrdenesTrabajo devolverGestorOrdenesTrabajo() {
		return this.gestorOrdenes;
	}

}
