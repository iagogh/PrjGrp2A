package com.enso.ayuntamiento;

public class Persona {
	private String nombre;
	private String dni;
	private String direccion;
	private String telefono;
	
	public Persona(String nombre, String dni, String direccion) {
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
	}

	public Persona(String nombre, String dni, String direccion, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}