package estadisticas;

import java.util.Date;

import com.enso.ayuntamiento.Concejal;

import procesos.ordenesTrabajo.Empresa;

public interface IEstadisticas<T> {
	
	Estadistica<T> numeroIncidencias();
	Estadistica<T> numeroInccidencias(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin);
	
	Estadistica<T> numeroProcesos();
	Estadistica<T> numeroProcesos(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin);
	
	Estadistica<T> numeroOrdenes();
	Estadistica<T> numeroOrdenes(Concejal responsable, Empresa empresa, Date filtroFechaIni, Date filtroFechaFin);	
}
