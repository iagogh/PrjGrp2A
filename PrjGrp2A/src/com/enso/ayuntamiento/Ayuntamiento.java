package com.enso.ayuntamiento;

import java.util.ArrayList;

import estadisticas.Estadistica;
import estadisticas.Estadisticas;
import estadisticas.IEstadisticas;
import incidencias.GestionIncidencias;
import incidencias.IGestionIncidencias;
import incidencias.Incidencia;
import incidencias.TipoIncidencia;
import procesos.GestionProcesos;
import procesos.IGestionProcesos;
import procesos.Proceso;
import procesos.ordenesTrabajo.Empresa;
import procesos.ordenesTrabajo.OrdenTrabajo;

public class Ayuntamiento {
	private static ArrayList<Ciudadano> vecinos;
	private static ArrayList<Concejal> concejales;
	private static IGestionIncidencias gestorIncidencias;
	private static IGestionProcesos gestorProcesos;
	private static IEstadisticas estadisticas;

	public static void main(String[] args) {
		System.out.println("Bienvenido al ayuntamiento");
		/*----ACTORES----*/
		Ciudadano ci1 = new Ciudadano("Jesus", "12345678A", "Rua da republica arxentina");
		vecinos.add(ci1);
		Ciudadano ci2 = new Ciudadano("Sergio", "12345678B", "Rua de Santiago de Chile");
		vecinos.add(ci2);
		Ciudadano ci3 = new Ciudadano("Pablo", "12345678C", "Rua Doutor Maceira");
		vecinos.add(ci3);
		
		Concejal co1 = new Concejal("Blas", "87654321A", "República do Salvador");
		concejales.add(co1);
		Concejal co2 = new Concejal("Alejandro", "87654321B", "Rua da Rosa");
		concejales.add(co2);
		
		/*----INTERFACES----*/
		gestorIncidencias = new GestionIncidencias();
		gestorProcesos = new GestionProcesos();
		estadisticas = new Estadisticas(gestorProcesos, gestorIncidencias);
		
		/*----INCIDENCIAS----*/
		gestorIncidencias.presentarIncidencia(ci1, "Arreglo de acera", TipoIncidencia.Desperfectos);
		gestorIncidencias.presentarIncidencia(ci1, "Retraso en los buses", TipoIncidencia.Transportes);
		gestorIncidencias.presentarIncidencia(ci2, "La acera está rota", TipoIncidencia.Desperfectos);
		gestorIncidencias.presentarIncidencia(ci2, "No hay recogida de basura", TipoIncidencia.Servicios);
		gestorIncidencias.presentarIncidencia(ci3, "Republica Arxentina", "Falta una señal", TipoIncidencia.Desperfectos);
		
		ArrayList<Incidencia> ai1 = gestorIncidencias.buscarIncidencias("12345678A", null, null, null, TipoIncidencia.Desperfectos);
		
		Incidencia i1 = gestorIncidencias.obtenerIncidencia("I2");
		
		ArrayList<Incidencia> ai2 = gestorIncidencias.incidenciasSinProceso();
		
		gestorIncidencias.finalizarIncidencia(i1);
		
		gestorIncidencias.valorarResolucion(i1, 5);
		
		/*----PROCESOS----*/
		Proceso p1 = gestorProcesos.crearNuevoProceso("Proceso 1", co2, "Este es el proceso 1", ai2);
		Proceso p2 = gestorProcesos.crearNuevoProceso("Proceso 2", co1, "Este es el proceso 2", ai1);
		
		gestorProcesos.vincularIncidencia(p1, ai2);
		
		gestorProcesos.finalizarProceso(p1);
		
		ArrayList<Proceso> ps1 = gestorProcesos.consultarProcesosSinOrdenesTrabajo(null, null, null, null, null);
		
		/*----ÓRDENES DE TRABAJO----*/
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo o1 = gestorProcesos.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		
		gestorProcesos.vincularOrdenTrabajo(p2, o1);
		
		/*----ESTADÍSTICAS----*/
		Estadistica est1 = estadisticas.numeroInccidencias(co2, e, null, null);
		Estadistica est2 = estadisticas.numeroOrdenes(null, null, null, null);
	}
	
}
