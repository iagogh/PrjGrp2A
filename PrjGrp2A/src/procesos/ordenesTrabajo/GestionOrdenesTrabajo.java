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

		ArrayList<OrdenTrabajo> ordenesFinales = new ArrayList<>();
		OrdenTrabajo ordenAux;
		Date fechaInicioOrden;
		int i;
		
		//Recordar que para esta función los parámetros a null significan que simplemente no buscamos por ese parámetro
		
		/*Comprobación de fechas*/
		for(i=0; i<this.ordenes.size(); i++) {
			ordenAux = this.ordenes.get(i);
			fechaInicioOrden = ordenAux.getFechaInicio();
			
			if(fechaInicioOrden != null) {	//comprobamos que la fecha de la orden no sea null
				if(fechaIni.before(fechaFin) || fechaIni == null) {	//comprobamos que las fechas introducidas están en orden correcto
					if(fechaIni.before(fechaInicioOrden) || fechaIni == null) {	//que la inicial es anterior a la de la orden
						if(fechaInicioOrden.before(fechaFin) || fechaFin == null) {	//que la final es posterior a la de la orden
							ordenesFinales.add(ordenAux);
						}
					}
				}
			}
		}
		
		/*Comprobación de responsable de proceso y de orden, si no coinciden, se eliminan*/
		for(i = 0; i<ordenesFinales.size(); i++) {
			ordenAux = ordenesFinales.get(i);
			if(!ordenAux.getResponsable().equals(res) && res != null) {
				ordenesFinales.remove(i);
			}else if(!ordenAux.getProceso().equals(pro) && pro != null) {
				ordenesFinales.remove(i);
			}
		}
		
		return ordenesFinales;
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
