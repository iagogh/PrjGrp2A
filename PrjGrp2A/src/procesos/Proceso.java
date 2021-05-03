package procesos;

import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import com.enso.ayuntamiento.Concejal;

import incidencias.Incidencia;
import procesos.ordenesTrabajo.OrdenTrabajo;

public class Proceso {
	private static int idGenerator = 0; //static para que se actualice entre todos los procesos
	private String nombre;
	private String id;
	private Float coste; //si se calcula en un metodo y esta implicitamente en las OT no se por que lo guardamos
	private ArrayList<Incidencia> incidencias;
	private Date fechaInicio;
	private Period tiempoEstimado;
	private Concejal responsable;
	private EstadoAvance estado;
	private String descripcion;
	private String servicio;
	private PrioridadProceso prioridad;
	private ArrayList<OrdenTrabajo> ordenesTrabajo;
	
	public Proceso(String nombre, Concejal responsable, String descripcion) {
		this.nombre = nombre;
		this.coste = 0.0f;
		this.responsable = responsable;
		this.descripcion = descripcion;
		this.incidencias = new ArrayList<>();
		this.ordenesTrabajo = new ArrayList<>();
		this.estado = EstadoAvance.EnTramite;
		//generacion de un id como ej P5
		idGenerator++;
		this.id = "P" + Integer.toString(idGenerator);
	}
	
	public void vincularIncidencia(ArrayList<Incidencia> incidencias) {
		for(Incidencia i: incidencias) {
			i.setProceso(this);
			this.incidencias.add(i);
		}
	}
	
	public void vincularOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		ordenTrabajo.setProceso(this);
		ordenesTrabajo.add(ordenTrabajo);
		this.coste += ordenTrabajo.getCoste();
	}
	
	public Float calcularCoste() {
		Float costeTotal = 0.0f;
		for(OrdenTrabajo o: this.ordenesTrabajo) {
			costeTotal += o.getCoste();
		}
		return costeTotal;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Float getCoste() {
		return coste;
	}
	public void setCoste(Float coste) {
		this.coste = coste;
	}
	public ArrayList<Incidencia> getIncidencias() {
		return incidencias;
	}
	public void setIncidencias(ArrayList<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Period getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(Period tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public Concejal getResponsable() {
		return responsable;
	}
	public void setResponsable(Concejal responsable) {
		this.responsable = responsable;
	}
	public EstadoAvance getEstado() {
		return estado;
	}
	public void setEstado(EstadoAvance estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public PrioridadProceso getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(PrioridadProceso prioridad) {
		this.prioridad = prioridad;
	}
	public ArrayList<OrdenTrabajo> getOrdenesTrabajo() {
		return ordenesTrabajo;
	}
	public void setOrdenesTrabajo(ArrayList<OrdenTrabajo> ordenesTrabajo) {
		this.ordenesTrabajo = ordenesTrabajo;
	}
	
	
}
