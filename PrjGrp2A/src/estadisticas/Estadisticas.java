package estadisticas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.enso.ayuntamiento.Concejal;

import incidencias.IGestionIncidencias;
import incidencias.Incidencia;
import procesos.IGestionProcesos;
import procesos.Proceso;
import procesos.ordenesTrabajo.Empresa;
import procesos.ordenesTrabajo.OrdenTrabajo;

public class Estadisticas implements IEstadisticas {
	private IGestionProcesos gestorProcesos;
	private IGestionIncidencias gestorIncidencias;

	public Estadisticas() {
		super();
	}

	public Estadisticas(IGestionProcesos gestorProcesos, IGestionIncidencias gestorIncidencias) {
		super();
		this.gestorProcesos = gestorProcesos;
		this.gestorIncidencias = gestorIncidencias;
	}

	@Override
	public Estadistica<Incidencia> numeroIncidencias() {
		ArrayList<Incidencia> resultado = this.gestorIncidencias.obtenerTodasIncidencias();
		Double costemedio = this.costeMedio(resultado);

		Estadistica<Incidencia> est = new Estadistica<>(costemedio, resultado);
		return est;
	}

	@Override
	public Estadistica<Incidencia> numeroInccidencias(Concejal responsable, Empresa empresa, Date filtroFechaIni,
			Date filtroFechaFin) {
		
		ArrayList<Incidencia> resultado = this.gestorIncidencias.buscarIncidencias(null, null, filtroFechaIni, filtroFechaFin, null);
		Double costemedio = this.costeMedio(resultado);
		
		Estadistica<Incidencia> est = new Estadistica<>(costemedio, resultado);
		return est;
	}

	@Override
	public Estadistica<Proceso> numeroProcesos() {
		ArrayList<Proceso> procesos = this.gestorProcesos.consultarProcesos(null, null, null, null, null, null);
		
		Double costeProcesos = 0.0;
		
		for(Proceso p: procesos) {
			costeProcesos += p.calcularCoste();
		}
		
		costeProcesos = costeProcesos / procesos.size();
		
		Estadistica<Proceso> est = new Estadistica<>(costeProcesos, procesos);
		return est;
	}

	@Override
	public Estadistica<Proceso> numeroProcesos(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin) {
		ArrayList<Proceso> procesos = this.gestorProcesos.consultarProcesos(null, null, null, null, null, null);
		
		Double costeProcesos = 0.0;
		for(Proceso p: procesos) {
			costeProcesos += p.calcularCoste();
		}
		
		costeProcesos = costeProcesos / procesos.size();
		
		Estadistica<Proceso> est = new Estadistica<>(costeProcesos, procesos);
		return est;
	}

	@Override
	public Estadistica<OrdenTrabajo> numeroOrdenes() {
		ArrayList<OrdenTrabajo> ordenesTrabajo = this.gestorProcesos.devolverGestorOrdenesTrabajo().buscarOrdenes(null, null, null, null);
		
		Double costeOrdenes = 0.0;
		for(OrdenTrabajo o: ordenesTrabajo) {
			costeOrdenes += o.getCoste();
		}
		
		costeOrdenes = costeOrdenes / ordenesTrabajo.size();
		
		Estadistica<OrdenTrabajo> est = new Estadistica<>(costeOrdenes, ordenesTrabajo);
		return est;
	}

	@Override
	public Estadistica<OrdenTrabajo> numeroOrdenes(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin) {
		ArrayList<OrdenTrabajo> ordenesTrabajo = this.gestorProcesos.devolverGestorOrdenesTrabajo().buscarOrdenes(filtroFechaIni, filtroFechaFin, empresa, null);
		
		Double costeOrdenes = 0.0;
		for(OrdenTrabajo o: ordenesTrabajo) {
			costeOrdenes += o.getCoste();
		}
		
		costeOrdenes = costeOrdenes / ordenesTrabajo.size();
		
		Estadistica<OrdenTrabajo> est = new Estadistica<>(costeOrdenes, ordenesTrabajo);
		return est;
	}
	
	private Double costeMedio(ArrayList<Incidencia> incidencias) {
		ArrayList<Proceso> procesos = new ArrayList<>();
		Proceso proctmp;
		Double total = 0.0;
		
		for(Incidencia i : incidencias) {
			proctmp = i.getProceso();
			if(!procesos.contains(proctmp)) {
				procesos.add(proctmp);
			}
		}
		
		for(Proceso p : procesos) {
			total += p.calcularCoste();
		}
		
		return (total / incidencias.size());
	}

}
