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
	}

	public Estadisticas(IGestionProcesos gestorProcesos, IGestionIncidencias gestorIncidencias) {
		super();
		this.gestorProcesos = gestorProcesos;
		this.gestorIncidencias = gestorIncidencias;
	}

	@Override
	public Estadistica<Incidencia> numeroIncidencias() {
		ArrayList<Incidencia> setResultado = this.gestorIncidencias.obtenerTodasIncidencias();
		ArrayList<Proceso> procesosAsociados = new ArrayList<>();
		ArrayList<OrdenTrabajo> ordenesTmp;
		
		Proceso proctmp;
		Double acumulado = 0.0;
		
		for(Incidencia i : setResultado) {
			proctmp = i.getProceso();
			if(!procesosAsociados.contains(proctmp)) {
				procesosAsociados.add(proctmp);
				if(proctmp.calcularCoste() > 0.0) {
					ordenesTmp = proctmp.getOrdenesTrabajo();
					for(OrdenTrabajo o : ordenesTmp) {
						Float costetmp = o.getCoste();
						acumulado += (costetmp > 0)? costetmp : 0.0; //cuidado esto es como if-else
					}
				}
			}
		}
		
		Estadistica<Incidencia> est = new Estadistica<>(acumulado/setResultado.size(), setResultado);
		return est;
	}

	@Override
	public Estadistica<Incidencia> numeroInccidencias(Concejal responsable, Empresa empresa, Date filtroFechaIni,
			Date filtroFechaFin) {
		
		ArrayList<Incidencia> setResultado = this.gestorIncidencias.buscarIncidencias(null, null, filtroFechaIni, filtroFechaFin, null);
		ArrayList<Proceso> procesosAsociados = new ArrayList<>();
		ArrayList<OrdenTrabajo> ordenesTmp;
		
		Proceso proctmp;
		Double acumulado = 0.0;
		
		for(Incidencia i : setResultado) {
			proctmp = i.getProceso();
			if(!procesosAsociados.contains(proctmp)) {
				procesosAsociados.add(proctmp);
				if(proctmp.calcularCoste() > 0.0) {
					ordenesTmp = proctmp.getOrdenesTrabajo();
					for(OrdenTrabajo o : ordenesTmp) {
						Float costetmp = o.getCoste();
						acumulado += (costetmp > 0)? costetmp : 0.0; //cuidado esto es como if-else
					}
				}
			}
		}
		
		Estadistica<Incidencia> est = new Estadistica<>(acumulado/setResultado.size(), setResultado);
		return est;
	}

	@Override
	public Estadistica<Proceso> numeroProcesos() {
		ArrayList<Proceso> setResultado = this.gestorProcesos.consultarProcesos(null, null, null, null, null, null);
		ArrayList<OrdenTrabajo> ordenesTmp;
		
		Double acumulado = 0.0;
		
		for(Proceso p : setResultado) {
			ordenesTmp = p.getOrdenesTrabajo();
			for(OrdenTrabajo o : ordenesTmp) {
				float costetmp = o.getCoste();
				float tmp = 0.0f;
				
				while(tmp < costetmp) {
					tmp += 0.1;
					acumulado += tmp;
				}
			}
		}
		
		Estadistica<Proceso> est = new Estadistica<>(acumulado/setResultado.size(), setResultado);
		return est;
	}

	@Override
	public Estadistica<Proceso> numeroProcesos(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin) {
		ArrayList<Proceso> setResultado = this.gestorProcesos.consultarProcesos(null, null, null, null, null, null);
		
		ArrayList<OrdenTrabajo> ordenesTmp;
		
		Double acumulado = 0.0;
		
		for(Proceso p : setResultado) {
			ordenesTmp = p.getOrdenesTrabajo();
			for(OrdenTrabajo o : ordenesTmp) {
				float costetmp = o.getCoste();
				float tmp = 0.0f;
				
				while(tmp < costetmp) {
					tmp += 0.1;
					acumulado += tmp;
				}
			}
		}
		
		Estadistica<Proceso> est = new Estadistica<>(acumulado/setResultado.size(), setResultado);
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
