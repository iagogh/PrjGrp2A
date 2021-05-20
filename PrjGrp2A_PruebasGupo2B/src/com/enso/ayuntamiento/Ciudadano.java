package com.enso.ayuntamiento;

public class Ciudadano extends Persona {

	public Ciudadano(String nombre, String dni, String direccion, String telefono) {
		super(nombre, dni, direccion, telefono);
	}

	public Ciudadano(String nombre, String dni, String direccion) {
		super(nombre, dni, direccion);
	}
	
}
