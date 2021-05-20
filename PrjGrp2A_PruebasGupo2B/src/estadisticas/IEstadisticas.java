package estadisticas;

import java.util.Date;
import com.enso.ayuntamiento.Concejal;
import incidencias.Incidencia;
import procesos.Proceso;
import procesos.ordenesTrabajo.Empresa;
import procesos.ordenesTrabajo.OrdenTrabajo;

public interface IEstadisticas {
	
	Estadistica<Incidencia> numeroIncidencias();
	Estadistica<Incidencia> numeroInccidencias(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin);
	
	Estadistica<Proceso> numeroProcesos();
	Estadistica<Proceso> numeroProcesos(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin);
	
	Estadistica<OrdenTrabajo> numeroOrdenes();
	Estadistica<OrdenTrabajo> numeroOrdenes(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin);	
}
