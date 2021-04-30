package procesos.ordenesTrabajo;

import java.util.ArrayList;
import java.util.Date;

import procesos.EstadoAvance;
import procesos.Proceso;

public interface IGestionOrdenesTrabajo {
	OrdenTrabajo crearOrdenTrabajo(Empresa responsable);
	
	void solicitarPresupuesto(ArrayList<Empresa> listaEmpresas, OrdenTrabajo orden);
	
	void asignarResponsable(OrdenTrabajo ordenTrabajo, Empresa responsable);
	
	ArrayList<OrdenTrabajo> buscarOrdenes(Date fechaIni, Date fechaFin, Empresa res, Proceso pro);
	
	Float calcularCoste(Proceso proceso);
	Float calcularCoste(Empresa responsable);
	
	void modificarCoste(OrdenTrabajo ordenTrabajo, Float coste);
	
	public void modificarOrden(OrdenTrabajo ordentrabajo, ArrayList<String> material, ArrayList<Float> presupuestos, Float coste,
			Empresa responsable, Integer personal, EstadoAvance estado);
	
	Empresa obtenerEmpresa(String idEmpresa);
}
