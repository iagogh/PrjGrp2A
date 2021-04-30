package incidencias;

/**
 * Estados en los que se puede encontrar una incidencia creada.
 * {@link #Inicializada}
 * {@link #EnTramite}
 * {@link #Finalizada}
 */
public enum EstadoIncidencia {
	/**
	 * Incidencia inicializada que todavia no ha sido asociada a ningun Proceso.
	 */
	Inicializada,
	/**
	 * Incidencia que esta siendo resuelta por un determinado Proceso-Orden de trabajo
	 */
	EnTramite,
	/**
	 * Incidencia cuya actividad asociada ha terminado y resuelta.
	 */
	Finalizada
}
