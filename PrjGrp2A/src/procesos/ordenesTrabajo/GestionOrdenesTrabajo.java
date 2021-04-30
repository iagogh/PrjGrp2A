package procesos.ordenesTrabajo;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import procesos.EstadoAvance;
import procesos.Proceso;

public class GestionOrdenesTrabajo implements IGestionOrdenesTrabajo {
	private ArrayList<OrdenTrabajo> ordenes;
	private ArrayList<Empresa> empresas;
	
	public GestionOrdenesTrabajo() {
		this.ordenes = new ArrayList<>();
		this.empresas = new ArrayList<>();
	}

	@Override
	public OrdenTrabajo crearOrdenTrabajo(Empresa responsable) {
		OrdenTrabajo orden = new OrdenTrabajo(responsable);
		this.ordenes.add(orden);
		return orden;
	}

	@Override
	public void solicitarPresupuesto(ArrayList<Empresa> listaEmpresas, OrdenTrabajo orden) {
		orden.solicitarPresupuesto(listaEmpresas);		
	}

	@Override
	public void asignarResponsable(OrdenTrabajo ordenTrabajo, Empresa responsable) {
		ordenTrabajo.asignarResponsable(responsable);		
	}

	@Override
	public ArrayList<OrdenTrabajo> buscarOrdenes(Date fechaIni, Date fechaFin, Empresa res, Proceso pro) {
		return this.ordenes.stream().filter(o ->
		(fechaIni == null || o.getFechaInicio().after(fechaIni) &&
		(fechaFin == null || o.getFechaInicio().before(fechaFin)) &&
		(res == null || o.getResponsable().equals(res)) &&
		(pro == null || o.getProceso() == pro)
		)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Float calcularCoste(Proceso proceso) {
		return proceso.calcularCoste();
	}

	@Override
	public Float calcularCoste(Empresa responsable) {
		Float costeTotal = 0.0f;
		for(OrdenTrabajo o: this.ordenes) {
			if(o.getResponsable().getId().equals(responsable.getId())) {
				costeTotal += o.getCoste();
			}
		}
		return costeTotal;
	}

	@Override
	public void modificarCoste(OrdenTrabajo ordenTrabajo, Float coste) {
		ordenTrabajo.setCoste(coste);		
	}

	@Override
	public void modificarOrden(OrdenTrabajo ordentrabajo, ArrayList<String> material, ArrayList<Float> presupuestos, Float coste,
			Empresa responsable, Integer personal, EstadoAvance estado) {
				ordentrabajo.setMaterial(material);
				ordentrabajo.setPresupuestos(presupuestos);
				ordentrabajo.setCoste(coste);
				ordentrabajo.asignarResponsable(responsable);
				ordentrabajo.setPersonal(personal);
				ordentrabajo.setEstado(estado);
	}

	@Override
	public Empresa obtenerEmpresa(String idEmpresa) {
		for(Empresa e: this.empresas) {
			if(e.getId().equals(idEmpresa))	return e;
		}
		return null;
	}
	
	
}
