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

	@Test
	void testConsultarProcesos() {
		fail("Not yet implemented");
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
		p.setFechaInicio(new Date(2020-1900, 03, 03));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite);
		
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
		p.setFechaInicio(new Date(2020-1900, 03, 03));
		Date fecha = new Date("mala") ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite);
		
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
		p.setFechaInicio(new Date(2020-1900, 12, 12));
		Date fecha = new Date(2020-1900, 12, 12) ;
		Date fecha2 = new Date(2020-1900, 01, 02) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite);
		
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
		Incidencia incidencia2 = new Incidencia(new Ciudadano("","",""),null,null);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 03, 03));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia2,responsable, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con incidencia vacia");
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
		Concejal responsable2 = new Concejal("","","","");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 03, 03));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable2, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty());
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
		p.setFechaInicio(new Date(2020-1900, 03, 03));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, null);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con estadoAvance null");
	}
	
	@DisplayName("CP01-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con lista de procesos almacenada en el sistema esta vacia.")
	@Test
	void CP01_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 03, 03));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		//esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con lista de procesos vacia");
	}
	
	@DisplayName("CP02-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fechaFin distinto de null y fechaInicioProceso mayor que fechaFin.")
	@Test
	void CP02_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 30));
		Date fecha = null ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con fechaProcesoFinal mayor que fechaFinal");
	}
	
	@DisplayName("CP03-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con incidencia inexistente.")
	@Test
	void CP03_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		Incidencia incidencia2 = new Incidencia(new Ciudadano("Malago","4534534g","jajuja"),null,null);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 30));
		Date fecha = null;
		Date fecha2 = null;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia2 ,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con incidencia inexistente");
	}
	
	@DisplayName("CP04-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con responsable inexistente.")
	@Test
	void CP04_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Concejal responsable2=new Concejal("Javo", "45956101H", "Santiago", "666666667");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 30));
		Date fecha = null ;
		Date fecha2 = null ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, null ,responsable2, EstadoAvance.Iniciado);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con responsable inexistente");
	}
	
	@DisplayName("CP05-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con estadoAvanzado inexistente.")
	@Test
	void CP05_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 30));
		Date fecha = null;
		Date fecha2 = null;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, null ,null, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con estadoAvanzado inexistente");
	}
	
	@DisplayName("CP06-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba valido con todos los parametros null")
	@Test
	void CP06_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 30));
		Date fecha = null;
		Date fecha2 = null;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, null ,null, null);
		
		//Assert
		assertEquals(esperado, real, "Fallo al consultarProcesosSinOrdenesTrabajo con todos los filtros a null");
	}
	
	@DisplayName("CP07-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fecha del proceso que el rango del filtro")
	@Test
	void CP07_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 01, 05));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fecha del proceso menor que el rango del filtro");
	}
	
	@DisplayName("CP08-P8.3-consultarProcesosSinOrdenesTrabajo con fecha del proceso mayor que el rango del filtro")
	@Test
	void CP08_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 12, 30));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con fecha del proceso mayor que el rango del filtro");
	}
	
	@DisplayName("CP09-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con incidencia inexistente")
	@Test
	void CP09_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		Incidencia incidencia2 = new Incidencia(new Ciudadano("Manu","45345356g","jeuja"),null,null);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 10, 10));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia2 ,responsable, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con incidencia incorrecta");
	}
	
	@DisplayName("CP10-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con responsable inexistente")
	@Test
	void CP10_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		Concejal responsable2=new Concejal("Javichu", "45959131H", "Santiago", "566666666");
		p.setFechaInicio(new Date(2020-1900, 10, 10));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable2, EstadoAvance.EnTramite);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con responsable inexistente");
	}
	
	@DisplayName("CP11-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con EstadoAvanzado inexistente")
	@Test
	void CP11_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 10, 10));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.Iniciado);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con estadoAvanzado inexistente");
	}
	
	@DisplayName("CP12-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba valido con todos los parametros correctos")
	@Test
	void CP12_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
		//Arrange
		ArrayList<Incidencia> incidencias= new ArrayList<>();
		Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
		incidencias.add(incidencia);
		GestionProcesos gp = new GestionProcesos();
		Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
		p.setFechaInicio(new Date(2020-1900, 10, 10));
		Date fecha = new Date(2020-1900, 01, 02) ;
		Date fecha2 = new Date(2020-1900, 12, 12) ;
		ArrayList<Proceso> esperado = new ArrayList();
		esperado.add(p);
	
		//Act
		ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite);
		
		//Assert
		assertEquals(esperado, real, "Fallo al consultarProcesosSinOrdenesTrabajo con todos los parametros correctos");
	}
}
