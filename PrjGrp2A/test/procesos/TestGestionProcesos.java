package procesos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.enso.ayuntamiento.Ciudadano;
import com.enso.ayuntamiento.Concejal;

import incidencias.Incidencia;
import procesos.ordenesTrabajo.Empresa;
import procesos.ordenesTrabajo.GestionOrdenesTrabajo;
import procesos.ordenesTrabajo.OrdenTrabajo;

class TestGestionProcesos {

	@Test
	void testCrearNuevoProceso() {
		fail("Not yet implemented");
	}

	@Test
	void testVincularIncidencia() {
		fail("Not yet implemented");
	}

	@Test
	void testVincularOrdenTrabajo() {
		fail("Not yet implemented");
	}

	@DisplayName("CP01-P2.1-consultarProcesos caso de prueba valido con parametros validos.")
	@Test
	void CP01_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
						
		GestionProcesos gp = new GestionProcesos();
						
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
						
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
						
		gp.vincularOrdenTrabajo(p,ot);
						
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
						
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
						
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.Iniciado,ot);
								
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesos con parametro fechaIni incorrecto.");
		
	}
	
	@DisplayName("CP02-P2.1-consultarProcesos caso de prueba no valido con fechaIni no valida.")
	@Test
	void CP02_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
								
		GestionProcesos gp = new GestionProcesos();
								
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
								
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
								
		gp.vincularOrdenTrabajo(p,ot);
								
		Date fecha = new Date("mala") ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
										
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.Iniciado,ot);
										
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesos con parametro fechaIni incorrecto.");
	}
	
	@DisplayName("CP03-P2.1-consultarProcesos caso de prueba no valido con fechaIni posterior a fechaFin.")
	@Test
	void CP03_Prueba2_2_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
										
		GestionProcesos gp = new GestionProcesos();
										
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
										
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
										
		gp.vincularOrdenTrabajo(p,ot);
										
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02);
												
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.Iniciado,ot);
												
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesos con parametro fechaIni posterior a fechaFin.");
		
	}
	
	@DisplayName("CP04-P2.1-consultarProcesos caso de prueba no valido con incidencia vacio.")
	@Test
	void CP04_Prueba2_2_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
												
		GestionProcesos gp = new GestionProcesos();
												
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
												
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
												
		gp.vincularOrdenTrabajo(p,ot);
												
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12);
														
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, null,responsable, EstadoAvance.Iniciado,ot);
														
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesos con parametro incidencia vacio.");
	}
	
	@DisplayName("CP05-P2.1-consultarProcesos caso de prueba no valido con responsable vacio.")
	@Test
	void CP05_Prueba2_2_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
														
		GestionProcesos gp = new GestionProcesos();
														
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
														
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
														
		gp.vincularOrdenTrabajo(p,ot);
														
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12);
																
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia, null, EstadoAvance.Iniciado,ot);
																
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesos con parametro responsable vacio.");
	}
	
	@DisplayName("CP06-P2.1-consultarProcesos caso de prueba no valido con estadoAvance valido.")
	@Test
	void CP06_Prueba2_2_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
														
		GestionProcesos gp = new GestionProcesos();
														
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
														
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
														
		gp.vincularOrdenTrabajo(p,ot);
														
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12);
																
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia, responsable, null, ot);
																
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesos con parametro estadoAvance vacio.");
	}
	
	@DisplayName("CP07-P2.1-consultarProcesos caso de prueba no valido con OrdenTrabajo vacia.")
	@Test
	void CP07_Prueba2_2_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
														
		GestionProcesos gp = new GestionProcesos();
														
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
														
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
														
		gp.vincularOrdenTrabajo(p,ot);
														
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12);
																
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia, responsable, EstadoAvance.Iniciado, null);
																
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesos con parametro OrdenTrabajo vacio.");
	}

	@DisplayName("CP01-P2.2-consultarProcesosSinOrdenesTrabajo caso de prueba valido con parametros validos.")
	@Test
	void CP01_Prueba2_2_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesosSinOrdenesTrabajo con parametros de busqueda validos");
	}
	
	@DisplayName("CP02-P2.2-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fechaIni incorrecto.")
	@Test
	void CP02_Prueba2_2_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Date fecha = new Date("mala") ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesosSinOrdenesTrabajo con fechaIni incorrecto");
	}
	
	@DisplayName("CP03-P2.2-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fechaIni posterior a fechaFin.")
	@Test
	void CP03_Prueba2_2_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesosSinOrdenesTrabajo con fechaIni mayor que fechaFinal");
	}

	@DisplayName("CP04-P2.2-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con incidencia vacio.")
	@Test
	void CP04_Prueba2_2_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, null,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesosSinOrdenesTrabajo con incidencia vacia");
	}
	
	@DisplayName("CP05-P2.2-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con responsable vacio.")
	@Test
	void CP05_Prueba2_2_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,null, EstadoAvance.Iniciado);
		
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesosSinOrdenesTrabajo con responsable vacio");
	}
	
	@DisplayName("CP06-P2.2-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con estadoAvance null.")
	@Test
	void CP06_Prueba2_2_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, null);
		
		//Assert
		assertEquals(esperado,real, "Fallo al consultarProcesosSinOrdenesTrabajo con estadoAvance null");
	}
}
