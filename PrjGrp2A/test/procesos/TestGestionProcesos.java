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

	//////////////CAIXA NEGRA
	@DisplayName("CP01-P9.2_crearNuevoProceso en el que se comprueba el funcionamiento de la creacion para unos parametros validos")
	@Test
	void CP01_Prueba9_2_crearNuevoProceso() {
		//Arrange
		GestionProcesos gp = new GestionProcesos();
		String nombre = "Proceso de Prueba";
		Concejal responsable = new Concejal("Pepe", "77777777Z", "Calle callejero", "666666666");
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		ArrayList<Incidencia> incidencias = new ArrayList();
		//Act
		Proceso creado = gp.crearNuevoProceso(nombre, responsable, descripcion, incidencias);
		ArrayList<Proceso> registrados = gp.procesos;
		
		//Assert
		assertAll(
				() -> {assertNotNull(creado,"Fallo al presentarIncidencia se ha recibido un nulo");},
				() -> {assertTrue(registrados.size()==1, "Fallo al PresentarIncidencia se ha recibido un array de registradas vacío o con mas elementos de los esperados");},
				() -> {assertSame(creado, registrados.get(0), "Fallo al presentarIncidencia el elemento recibido no se corresponde con el esperado");}
				);
	}

	@Test
	void testVincularIncidencia() {
		fail("Not yet implemented");
	}

	@DisplayName("CP01-P4.1-vincularOrdenTrabajo caso de prueba valido con parametros validos")
	@Test
	void CP01_Prueba4_1_vincularOrdenTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
						
		GestionProcesos gp = new GestionProcesos();
						
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
						
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		
		//VINCULO ORDEN
		gp.vincularOrdenTrabajo(p,ot);
						
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
						
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
						
		//Act
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite,ot);
								
		//Assert
		assertEquals(esperado.get(0).getOrdenesTrabajo(),real.get(0).getOrdenesTrabajo(), "Fallo al vincularOrdenTrabajo con parametros validos.");
	}
	
	@DisplayName("CP02-P4.1-vincularOrdenTrabajo caso de prueba no valido con proceso vacio")
	@Test
	void CP02_Prueba4_1_vincularOrdenTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
						
		GestionProcesos gp = new GestionProcesos();
						
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);				
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		
		//VINCULO ORDEN?�?�?�?
		gp.vincularOrdenTrabajo(null,ot);
						
	}
	
	@DisplayName("CP03-P4.1-vincularOrdenTrabajo caso de prueba no valido con ")
	@Test
	void CP03_Prueba4_1_vincularOrdenTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
						
		GestionProcesos gp = new GestionProcesos();
						
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);				
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		
		//VINCULO ORDEN?�?�?�?
		gp.vincularOrdenTrabajo(p,null);
	}
	
	@DisplayName("CP04-P4.1-vincularOrdenTrabajo caso de prueba no valido ")
	@Test
	void CP04_Prueba4_1_vincularOrdenTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
						
		GestionProcesos gp = new GestionProcesos();
						
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);				
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		
		//ASIGNO UNHA VEZ
		gp.vincularOrdenTrabajo(p,ot);
		
		Proceso p_doble_asignado = gp.crearNuevoProceso("Cambiar luces", responsable, "Hay que cambiar las luces", incidencias);				
		
		//ASIGNO DUAS VECES
		gp.vincularOrdenTrabajo(p_doble_asignado, ot);
		
		//E QUE PROBO?�?� AJAJAJAJAJAJA
		//Vale a ver, o caso � que, se son iguais-->problema(que da sempre xdd)
		//Ent�n not equals
		assertNotEquals(p.getOrdenesTrabajo(),p_doble_asignado.getOrdenesTrabajo(),"Fallo al vincularOrdenTrabajo con la misma orden en dos procesos.");
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
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite,ot);
								
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
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite,ot);
										
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
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite,ot);
												
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
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, null,responsable, EstadoAvance.EnTramite,ot);
														
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
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia, null, EstadoAvance.EnTramite,ot);
																
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
		ArrayList<Proceso> real = gp.consultarProcesos(fecha, fecha2, incidencia, responsable, EstadoAvance.EnTramite, null);
																
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
	
	
	///////////////////CAIXA BLANCA
	@DisplayName("CB-CP01-P2.1-consultarProcesos caso de prueba valido con ning�n proceso en el sistema.")
	@Test
	void CB_CP01_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
	
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite,ot);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con sistema sin procesos");
	}
	
	@DisplayName("CB-CP02-P2.1-consultarProcesos caso de prueba valido con todos los parametros nulos.")
	@Test
	void CB_CP02_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
	
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
		
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(null, null, null ,null, null, null);
		
		//Assert
		assertEquals(esperado,resultado, "Fallo al consultarProcesos con parametros nulos");
	}
	
	@DisplayName("CB-CP03-P2.1-consultarProcesos caso de prueba valido con todos los parametros coincidentes.")
	@Test
	void CB_CP03_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		gp.vincularOrdenTrabajo(p, ot);
	
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
		
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite, ot);
		
		//Assert
		assertEquals(esperado,resultado, "Fallo al consultarProcesos con parametros nulos");
	}
	
	
	/////CAMBIAR OS DE FECHA QUE OS FIXEN MAL
	@DisplayName("CB-CP04-P2.1-consultarProcesos caso de prueba valido con fechaIni posterior a fechaIni_proceso.")
	@Test
	void CB_CP04_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 12));
		
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		gp.vincularOrdenTrabajo(p, ot);
			
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite, ot);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con fechaIni posterior a fechaIni_proceso");
	}
	
	//ESTO � EXACTAMENTE IGUAL AO CASO ANTERIOR-->ABSURDO NON? NON, CAMBIALO
	@DisplayName("CB-CP05-P2.1-consultarProcesos caso de prueba valido con fechaFin anterior a fechaFin_proceso.")
	@Test
	void CB_CP05_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		//NON HAI FECHA FIN, QUE HAGO? ME MATO?
		
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		gp.vincularOrdenTrabajo(p, ot);
			
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite, ot);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con fechaFin anterior a fechaFin_proceso");
	}
	
	@DisplayName("CB-CP06-P2.1-consultarProcesos caso de prueba valido con incidencia no coincidente.")
	@Test
	void CB_CP06_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		Incidencia incidencia_proba = new Incidencia(new Ciudadano("Jos�","4534535r","jaja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		gp.vincularOrdenTrabajo(p, ot);
			
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia_proba ,responsable, EstadoAvance.EnTramite, ot);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con incidencia no correspondiente");
	}
	
	@DisplayName("CB-CP07-P2.1-consultarProcesos caso de prueba valido con responsable no coincidente.")
	@Test
	void CB_CP07_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Concejal responsable_proba=new Concejal("Pepe", "45959101F", "A Coru�a", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		gp.vincularOrdenTrabajo(p, ot);
			
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable_proba, EstadoAvance.EnTramite, ot);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con responsable no correspondiente");
	}
	
	@DisplayName("CB-CP08-P2.1-consultarProcesos caso de prueba valido con estadoAvance no coincidente.")
	@Test
	void CB_CP08_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		gp.vincularOrdenTrabajo(p, ot);
			
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.Iniciado, ot);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con estadoAvance no correspondiente");
	}
	
	@DisplayName("CB-CP09-P2.1-consultarProcesos caso de prueba valido con ordenTrabajo no coincidente.")
	@Test
	void CB_CP09_Prueba2_1_consultarProcesos() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Concejal responsable_proba=new Concejal("Pepe", "45959101F", "A Coru�a", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02) ;
		
		Empresa e = new Empresa("Hotusa", "email@hotusa.com");
		OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
		Empresa e_proba = new Empresa("Trivago", "email@trivago.com");
		OrdenTrabajo ot_proba = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e_proba);
		gp.vincularOrdenTrabajo(p, ot);
			
		//Act
		ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable_proba, EstadoAvance.EnTramite, ot_proba);
		
		//Assert
		assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con ordenTrabajo no correspondiente");
	}
	
	@DisplayName("CB-CP010-P2.1-consultarProcesos caso de prueba valido con fechaIni null y fechaFin anterior a fechaFin_proceso.")
	@Test
	void CB_CP010_Prueba2_1_consultarProcesos() {
		//NON TE�O FECHA FIN
	}
	
	@DisplayName("CB-CP011-P2.1-consultarProcesos caso de prueba valido con fechas nulas e incidencias no coincidentes.")
	@Test
	void CB_CP011_Prueba2_1_consultarProcesos() {
		
	}
	
	@DisplayName("CB-CP012-P2.1-consultarProcesos caso de prueba valido con fechas e incidencias nulas y responsable no coincidente.")
	@Test
	void CB_CP012_Prueba2_1_consultarProcesos() {
		
	}
	
	@DisplayName("CB-CP013-P2.1-consultarProcesos caso de prueba valido con fechas, incidencias y responsable nulos y estadoAvance no coincidente.")
	@Test
	void CB_CP013_Prueba2_1_consultarProcesos() {
		
	}
	
	@DisplayName("CB-CP014-P2.1-consultarProcesos caso de prueba valido con fechas, incidencias, responsable y estadoAvance nulos y ordenTrabajo no coincidente.")
	@Test
	void CB_CP014_Prueba2_1_consultarProcesos() {
		
	}
}
