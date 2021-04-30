package procesos.ordenesTrabajo;

import java.util.ArrayList;
import java.util.Date;

import procesos.EstadoAvance;
import procesos.Proceso;

public class OrdenTrabajo {
	private static int idGenerator = 0; //static para que se actualice entre todas las ordTrabajo
	private String id;
	private ArrayList<String> material;
	private ArrayList<Float> presupuestos;
	private Float coste;
	private Empresa responsable;
	private Integer personal;
	private Date fechaInicio;
	private Proceso proceso;
	//private Date duracion; 
	private EstadoAvance estado;
	
	public OrdenTrabajo() {
		super();
		material = new ArrayList<>();
		presupuestos = new ArrayList<>();
		fechaInicio = new Date();
		//generacion de un id como ej OT5
		idGenerator++;
		this.id = "OT" + Integer.toString(idGenerator);
	}
	
	public OrdenTrabajo(Empresa responsable) {
		super();
		material = new ArrayList<>();
		presupuestos = new ArrayList<>();
		fechaInicio = new Date();
		this.responsable = responsable;
		//generacion de un id como ej OT5
		idGenerator++;
		this.id = "OT" + Integer.toString(idGenerator);
	}
	
	public void asignarResponsable(Empresa responsable) {
		this.responsable = responsable;
	}
	
	//me tiene mas sentido utilizar las empresas que los mails, asi sepuede generar mas info por peticion
	//pero como que esto da bastante igual porque no se va a implementar
	public void solicitarPresupuesto(ArrayList<Empresa> listaEmpresas) {
		
	}

	public String getId() {
		return id;
	}

	public ArrayList<String> getMaterial() {
		return material;
	}

	public ArrayList<Float> getPresupuestos() {
		return presupuestos;
	}

	public Float getCoste() {
		return coste;
	}

	public Empresa getResponsable() {
		return responsable;
	}

	public Integer getPersonal() {
		return personal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public EstadoAvance getEstado() {
		return estado;
	}
	
	public Proceso getProceso() {
		return this.proceso;
	}

	public void setMaterial(ArrayList<String> material) {
		this.material = material;
	}

	public void setPresupuestos(ArrayList<Float> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public void setCoste(Float coste) {
		this.coste = coste;
	}

	public void setResponsable(Empresa responsable) {
		this.responsable = responsable;
	}

	public void setPersonal(Integer personal) {
		this.personal = personal;
	}

	public void setEstado(EstadoAvance estado) {
		this.estado = estado;
	}
	
	public void setProceso(Proceso p) {
		this.proceso = p;
	}
	
}
