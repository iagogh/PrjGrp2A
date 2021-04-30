package procesos.ordenesTrabajo;

public class Empresa {
	private String nombre;
	private String id;
	private String email;
	
	public Empresa(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
		//generar id, realmente hace falta para Empresa?
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
