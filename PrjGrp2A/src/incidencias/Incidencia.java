package incidencias;

import java.util.Date;

import com.enso.ayuntamiento.Ciudadano;

import procesos.Proceso;

public class Incidencia {
	private static int idGenerator = 0; // static para que se actualice entre todas las instancias
	private String id;
	private String nombreCiudadano;
	private String dniCiudadano;
	private String localizacion;
	private String descripcion;
	private TipoIncidencia tipo;
	private EstadoIncidencia estado; // nuevo atributo
	private Date fechaInicio;
	private Date fechaFin;
	private Integer valoracion;
	private Proceso proceso;

	public Incidencia(Ciudadano vecino, String descripcion, TipoIncidencia tipo) {
		this.fechaInicio = new Date(); // this shit pilla la hora del sistema?
		this.nombreCiudadano = vecino.getNombre();
		this.dniCiudadano = vecino.getDni();
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = EstadoIncidencia.Inicializada;
		// generacion de un id como ej I5
		idGenerator++;
		this.id = "I" + Integer.toString(idGenerator);
	}

	public Incidencia(Ciudadano vecino, String localizacion, String descripcion, TipoIncidencia tipo) {
		super();
		this.fechaInicio = new Date();
		this.nombreCiudadano = vecino.getNombre();
		this.dniCiudadano = vecino.getDni();
		this.localizacion = localizacion;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = EstadoIncidencia.Inicializada;
		// generacion de un id como ej I5
		idGenerator++;
		this.id = "I" + Integer.toString(idGenerator);
	}

	public String getId() {
		return id;
	}

	public String getNombreCiudadano() {
		return nombreCiudadano;
	}

	public String getDniCiudadano() {
		return dniCiudadano;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public TipoIncidencia getTipo() {
		return tipo;
	}

	public EstadoIncidencia getEstado() {
		return estado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public Proceso getProceso() {
		return proceso;
	}

	/* Los setters de valoracion y proceso van a ser los unicos empleados */

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	/**
	 * Metodo que comprueba si la incidencia esta asociada actualmente a algun
	 * proceso.
	 * 
	 * @return true de pertenecer a algun proceso, false en caso contrario.
	 */
	public boolean tieneProceso() {
		return (this.proceso != null);
	}

	public void iniciar() {
		this.estado = EstadoIncidencia.EnTramite;
	}

	public void finalizar() {
		this.estado = EstadoIncidencia.Finalizada;
		this.fechaFin = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object anObject) {
		if (anObject instanceof Incidencia) {
			Incidencia incidencia = (Incidencia) anObject;
			if (incidencia.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;

	}

	public String emitirNotificacion() {
		// TODO Determinar que se devuelve para notificar la resolucion de la
		// incidencia.
		return null;
	}
}
