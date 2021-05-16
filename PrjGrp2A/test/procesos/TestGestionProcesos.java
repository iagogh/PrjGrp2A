package procesos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.enso.ayuntamiento.Ciudadano;
import com.enso.ayuntamiento.Concejal;

import incidencias.GestionIncidencias;
import incidencias.Incidencia;
import incidencias.TipoIncidencia;
import procesos.ordenesTrabajo.Empresa;
import procesos.ordenesTrabajo.GestionOrdenesTrabajo;
import procesos.ordenesTrabajo.OrdenTrabajo;

class TestGestionProcesos {

	@Nested
	@DisplayName("Pruebas de caja Negra sobre el m�dulo de gesti�n de Procesos")
	class cajaNegra {
		@Nested
		@DisplayName("Prueba9.2:CrearNuevoProceso, Caso de prueba asociado a probar las clases de equivalencia válidas")
		class crearProceso {
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
		}
		
		@Nested
		@DisplayName("Prueba4.1:vincularOrdenTrabajo, conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja negra")
		class prueba4_1 {
			
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
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
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
			
			@DisplayName("CP02-P4.1-vincularOrdenTrabajo caso de prueba no valido con proceso no valido")
			@Test
			void CP02_Prueba4_1_vincularOrdenTrabajo() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();

				/*ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);*/

				//Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				//Un proceso � incorrecto cando alg�n dos campos como incidencias � nulo
				Proceso proceso = new Proceso("Proceso Incorrecto", null, null);
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				
				// Act
				gp.vincularOrdenTrabajo(proceso, ot);
				// Assert
				assertNull(ot.getProceso(), "Prueba fallida, a la orden de trabajo se le ha asignado un proceso");	
			}
			
			@DisplayName("CP03-P4.1-vincularOrdenTrabajo caso de prueba no valido con ordenTrabajo no valida")
			@Test
			void CP03_Prueba4_1_vincularOrdenTrabajo() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();

				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
						
				//Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				//Unha orden de traballo e non valida se non se lle pasa un responsable e ten mais parametros nulos
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(null);
				ot.setEstado(null);
				ot.setMaterial(null);
						
				// Act
				gp.vincularOrdenTrabajo(proceso, ot);
				// Assert
				assertNull(ot.getProceso(), "Prueba fallida, a la orden de trabajo se le ha asignado un proceso");	
			}
			
			@DisplayName("CP04-P4.1-vincularOrdenTrabajo caso de prueba no valido con una orden de trabajo doblemente asignada")
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
				
				//Vale a ver, o caso e que, se son iguais--> problema(que da sempre, falla)
				//Enton not equals
				assertNotEquals(p.getOrdenesTrabajo(),p_doble_asignado.getOrdenesTrabajo(),"Fallo al vincularOrdenTrabajo con la misma orden en dos procesos.");
			}
		}
	
		@Nested
		@DisplayName("Prueba2.1:consultarProcesos, conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja negra")
		class prueba2_1 {
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
								
				p.setFechaInicio(new Date(2020-1900, 03, 03));
				
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
				assertEquals(esperado,real, "Fallo al consultarProcesos con parametros correctos.");
				
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
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
										
				gp.vincularOrdenTrabajo(p,ot);
										
				Date fecha2 = new Date(2020-1900, 12, 12) ;
												
				//Act
				
				//Assert
				assertThrows(IllegalArgumentException.class,() -> {Date fechaIni = new Date("mala");gp.consultarProcesos(fechaIni, fecha2,incidencia,responsable, EstadoAvance.EnTramite,ot);},"Se ha aceptado un tipo de parametro incorrecto para fechaIni" );
			}
			
			@DisplayName("CP03-P2.1-consultarProcesos caso de prueba no valido con fechaIni posterior a fechaFin.")
			@Test
			void CP03_Prueba2_1_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
												
				GestionProcesos gp = new GestionProcesos();
												
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));							
				
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
			void CP04_Prueba2_1_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
														
				GestionProcesos gp = new GestionProcesos();
														
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));										
				
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
			void CP05_Prueba2_1_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
																
				GestionProcesos gp = new GestionProcesos();
																
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));												
				
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
			void CP06_Prueba2_1_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
																
				GestionProcesos gp = new GestionProcesos();
																
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));												
				
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
			void CP07_Prueba2_1_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
																
				GestionProcesos gp = new GestionProcesos();
																
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));											
				
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
		}
	
		@Nested
		@DisplayName("Prueba2.2:consultarProcesosSinOrdenesTrabajo, conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja negra")
		class prueba2_2 {
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
				//Date fecha = new Date("mala") ; 
				Date fecha2 = new Date(2020-1900, 12, 12) ;
				ArrayList<Proceso> esperado = new ArrayList();
				esperado.add(p);
			
				//Act
				
				//Assert
				assertThrows(IllegalArgumentException.class,() -> {Date fechaIni = new Date("mala");gp.consultarProcesosSinOrdenesTrabajo(fechaIni, fecha2,incidencia,responsable, EstadoAvance.EnTramite);},"Se ha aceptado un tipo de parametro incorrecto para fechaIni" );
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
				p.setFechaInicio(new Date(2020-1900, 12, 30));
				Date fecha = new Date(2020-1900, 12, 12) ;
				Date fecha2 = new Date(2020-1900, 01, 02) ;
				ArrayList<Proceso> esperado = new ArrayList();
				esperado.add(p);
			
				//Act
				ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia,responsable, EstadoAvance.EnTramite);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo con fechaIni mayor que fechaFinal");
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
		}
	
		@Nested
		@DisplayName("Prueba3.1: vincularIncidencia, conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja negra")
		class prueba3_1 {
			@DisplayName("CP01-P3.1_vincularIncidencia valido que incluye todas las clases validas")
			@Test
			void CP01_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> incidencias = gi.incidenciasSinProceso();

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());

				// Act
				gp.vincularIncidencia(proceso, incidencias);
				// Assert
				assertAll(() -> {
					assertTrue(!proceso.getIncidencias().isEmpty(),
							"Prueba fallida, no hay ninguna incidencia asignada al proceso");
				}, () -> {
					assertNotNull(incidencia.getProceso(),
							"Prueba fallida, la incidencia no tiene ningún proceso asignado");
				}, () -> {
					assertEquals(proceso, incidencia.getProceso(),
							"Prueba fallida, no se ha asignado al proceso la incidencia correcta");
				});
			}

			@DisplayName("CP02-P3.1_vincularIncidencia no válido con parámetro proceso incorrecto")
			@Test
			void CP02_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> incidencias = gi.incidenciasSinProceso();

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = new Proceso("Proceso Incorrecto", null, null);
				// Act
				gp.vincularIncidencia(proceso, incidencias);
				// Assert
				assertNull(incidencia.getProceso(), "Prueba fallida, a la incidencia se le ha asignado un proceso");
			}

			@DisplayName("CP03-P3.1_vincularIncidencia no válido con parámetro incidencias incorrecto")
			@Test
			@Disabled
			void CP03_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				ArrayList<Incidencia> incidencias = new ArrayList<>();
				incidencias.add(new Incidencia(null, "Incidencia Incorrecta", null));
				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");

				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());
				ArrayList<Incidencia> incidenciasAntes = proceso.getIncidencias();
				// Act
				gp.vincularIncidencia(proceso, incidencias);
				ArrayList<Incidencia> incidenciasDespues = proceso.getIncidencias();
				// Assert
				assertEquals(incidenciasAntes, incidenciasDespues,
						"Prueba fallida, se han modificado las incidencias asignadas al proceso al vincular incidencias incorrectas");
			}

			@DisplayName("CP04-P3.1_vincularIncidencia válido donde el primer y el último elemento del parámetro incidencias son correctos")
			@Test
			@Disabled
			void CP04_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				ArrayList<Incidencia> esperado = new ArrayList<>();
				ArrayList<Incidencia> incidencias = new ArrayList<>();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				incidencias.add(incidencia);
				esperado.add(incidencia);
				vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
				incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
				incidencias.add(null);
				vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
				incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);
				incidencias.add(incidencia);
				esperado.add(incidencia);

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());
				// Act
				gp.vincularIncidencia(proceso, incidencias);
				ArrayList<Incidencia> obtenido = proceso.getIncidencias();
				// Assert
				assertAll(() -> {
					assertTrue(obtenido.contains(incidencias.get(0)),
							"Prueba fallida, no se ha asignado el primer elemento");
				}, () -> {
					assertTrue(obtenido.contains(incidencias.get(incidencias.size() - 1)),
							"Prueba Fallida, no se ha asignado el último elemento");
				}, () -> {
					assertEquals(esperado, obtenido,
							"Prueba fallida, se han vinculado al proceso incidencias incorrectas");
				});

			}

			@DisplayName("CP05-P3.1_vincularIncidencia no válido donde el primer elemento del parámetro incidencias es incorrecto")
			@Test
			@Disabled
			void CP05_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				ArrayList<Incidencia> esperado = new ArrayList<>();
				ArrayList<Incidencia> incidencias = new ArrayList<>();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				incidencias.add(null);
				vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
				incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
				incidencias.add(incidencia);
				esperado.add(incidencia);
				vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
				incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);
				incidencias.add(incidencia);
				esperado.add(incidencia);

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());

				// Act
				gp.vincularIncidencia(proceso, incidencias);
				ArrayList<Incidencia> obtenido = proceso.getIncidencias();
				// Assert
				assertEquals(esperado, obtenido, "Prueba fallida, se han vinculado al proceso incidencias incorrectas");

			}

			@DisplayName("CP06-P3.1_vincularIncidencia no válido con parámetro incidencias vacío")
			@Test
			void CP06_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());

				ArrayList<Incidencia> incidencias = new ArrayList<>();
				ArrayList<Incidencia> incidenciasAntes = proceso.getIncidencias();
				// Act
				gp.vincularIncidencia(proceso, incidencias);
				ArrayList<Incidencia> incidenciasDespues = proceso.getIncidencias();
				// Assert
				assertAll(() -> {
					assertNull(incidencia.getProceso(),
							"Prueba fallida, a la incidencia existente se le ha asignado un proceso");
				}, () -> {
					assertEquals(incidenciasAntes, incidenciasDespues,
							"Prueba fallida, las incidencias asignadas al proceso indicado se han modificado");
				});
			}

			@DisplayName("CP07-P3.1_vincularIncidencia no válido con  incidencias repetidas iguales")
			@Test
			void CP07_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> incidencias = new ArrayList<>();
				incidencias.add(incidencia);
				incidencias.add(incidencia);
				ArrayList<Incidencia> esperado = new ArrayList<>();
				esperado.add(incidencia);

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());

				// Act
				gp.vincularIncidencia(proceso, incidencias);
				ArrayList<Incidencia> obtenido = proceso.getIncidencias();
				// Assert
				assertAll(() -> {
					assertEquals(proceso, incidencia.getProceso(),
							"Prueba fallida, a la incidencia se le ha asignado otro proceso");
				}, () -> {
					assertEquals(obtenido, esperado,
							"Prueba fallida, no se han obtenido las incidencias vinculadas al proceso esperadas");
				});
			}

			@DisplayName("CP08-P3.1_vincularIncidencia no válido, parámetro incidencias contiene incidencia ya asginada")
			@Test
			void CP08_P3_1_vincularIncidencia() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();

				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion,
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> incidencias = new ArrayList<>();
				incidencias.add(incidencia);

				Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso procesoAntiguo = gp.crearNuevoProceso("Cambiar bombillas", responsable,
						"Hay que cambiar las bombillas", new ArrayList<Incidencia>());
				gp.vincularIncidencia(procesoAntiguo, incidencias);

				Proceso procesoNuevo = gp.crearNuevoProceso("Volver a cambiar las bombillas", responsable,
						"Volver a cambiar las bombillas cambidasas la úlima vez", new ArrayList<Incidencia>());

				// Act
				gp.vincularIncidencia(procesoNuevo, incidencias);
				// Assert
				assertAll(() -> {
					assertNotEquals(procesoAntiguo.getIncidencias(), procesoNuevo.getIncidencias(),
							"Prueba fallida, las incidencias están vinculadas a más de un proceso");
				}, () -> {
					assertNotEquals(procesoNuevo, incidencia.getProceso(),
							"Prueba fallida, se ha asignado la incidencia al nuevo proceso");
				});
			}
		}
	}
	
	@Nested
	@DisplayName("Pruebas de caja Blanca sobre el m�dulo de gesti�n de Procesos")
	class cajaBlanca {
		@Nested
		@DisplayName("Prueba8.2: consultarProcesos, conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja blanca")
		class prueba8_2 {
			
			@DisplayName("CB-CP01-P8.2-consultarProcesos caso de prueba valido con ningun proceso en el sistema.")
			@Test
			void CB_CP01_Prueba8_2_consultarProcesos() {
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
			
			@DisplayName("CB-CP02-P8.2-consultarProcesos caso de prueba valido con todos los parametros nulos.")
			@Test
			void CB_CP02_Prueba8_2_consultarProcesos() {
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
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
			
				ArrayList<Proceso> esperado = new ArrayList();
				esperado.add(p);
				
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(null, null, null ,null, null, null);
				
				//Assert
				assertEquals(esperado,resultado, "Fallo al consultarProcesos con parametros nulos");
			}
			
			@DisplayName("CB-CP03-P8.2-consultarProcesos caso de prueba valido con todos los parametros coincidentes.")
			@Test
			void CB_CP03_Prueba8_2_consultarProcesos() {
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
			@DisplayName("CB-CP04-P8.2-consultarProcesos caso de prueba valido con fechaIni posterior a fechaIni_proceso.")
			@Test
			void CB_CP04_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 01, 01));
				
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
			
			//FECHA FIN
			@DisplayName("CB-CP05-P8.2-consultarProcesos caso de prueba valido con fechaFin anterior a fechaFin_proceso.")
			@Test
			void CB_CP05_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				//Fecha Fin hace de Fecha Inicio, porque no hay fechaFin en proceso
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha = new Date(2020-1900, 01, 02) ;
				Date fecha2 = new Date(2020-1900, 01, 03) ;
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite, ot);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con fechaFin anterior a fechaFin_proceso");
			}
			
			@DisplayName("CB-CP06-P8.2-consultarProcesos caso de prueba valido con incidencia no coincidente.")
			@Test
			void CB_CP06_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				Incidencia incidencia_proba = new Incidencia(new Ciudadano("Jos�","4534535r","jaja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
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
			
			@DisplayName("CB-CP07-P8.2-consultarProcesos caso de prueba valido con responsable no coincidente.")
			@Test
			void CB_CP07_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Concejal responsable_proba=new Concejal("Pepe", "45959101F", "A Coru�a", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
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
			
			@DisplayName("CB-CP08-P8.2-consultarProcesos caso de prueba valido con estadoAvance no coincidente.")
			@Test
			void CB_CP08_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
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
			
			@DisplayName("CB-CP09-P8.2-consultarProcesos caso de prueba valido con ordenTrabajo no coincidente.")
			@Test
			void CB_CP09_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha = new Date(2020-1900, 12, 12) ;
				Date fecha2 = new Date(2020-1900, 01, 02) ;
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				Empresa e_proba = new Empresa("Trivago", "email@trivago.com");
				OrdenTrabajo ot_proba = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e_proba);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite, ot_proba);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con ordenTrabajo no correspondiente");
			}
			
			@DisplayName("CB-CP010-P8.2-consultarProcesos caso de prueba valido con fechaIni null y fechaFin anterior a fechaFin_proceso.")
			@Test
			void CB_CP010_Prueba8_2_consultarProcesos() {
				//NO TENGO FECHA FIN, USO FECHA INICIO
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				Incidencia incidencia_proba = new Incidencia(new Ciudadano("Jos�","4534535r","jaja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha2 = new Date(2020-1900, 01, 02);
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(null, fecha2, incidencia_proba ,responsable, EstadoAvance.EnTramite, ot);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con fechaIni nula y fechaFin anterior a fechaFin_proceso");
			
			}
			
			@DisplayName("CB-CP011-P8.2-consultarProcesos caso de prueba valido con fechas nulas e incidencias no coincidentes.")
			@Test
			void CB_CP011_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				Incidencia incidencia_proba = new Incidencia(new Ciudadano("Jos�","4534535r","jaja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha = new Date(2020-1900, 12, 12) ;
				Date fecha2 = new Date(2020-1900, 01, 02) ;
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(null, null, incidencia_proba ,responsable, EstadoAvance.EnTramite, ot);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con fechas nulas e incidencia no coincidente");
			}
			
			@DisplayName("CB-CP012-P8.2-consultarProcesos caso de prueba valido con fechas e incidencias nulas y responsable no coincidente.")
			@Test
			void CB_CP012_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Concejal responsable_proba=new Concejal("Pepe", "45959101F", "A Coru�a", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha = new Date(2020-1900, 12, 12) ;
				Date fecha2 = new Date(2020-1900, 01, 02) ;
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(null, null, null ,responsable_proba, EstadoAvance.EnTramite, ot);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con responsable no correspondiente");
			}
			
			@DisplayName("CB-CP013-P8.2-consultarProcesos caso de prueba valido con fechas, incidencias y responsable nulos y estadoAvance no coincidente.")
			@Test
			void CB_CP013_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha = new Date(2020-1900, 12, 12) ;
				Date fecha2 = new Date(2020-1900, 01, 02) ;
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(null, null, null ,null, EstadoAvance.Iniciado, ot);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con estadoAvance no correspondiente");
			}
			
			@DisplayName("CB-CP014-P8.2-consultarProcesos caso de prueba valido con fechas, incidencias, responsable y estadoAvance nulos y ordenTrabajo no coincidente.")
			@Test
			void CB_CP014_Prueba8_2_consultarProcesos() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Concejal responsable_proba=new Concejal("Pepe", "45959101F", "A Coru�a", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));	
				
				Date fecha = new Date(2020-1900, 12, 12) ;
				Date fecha2 = new Date(2020-1900, 01, 02) ;
				
				Empresa e = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e);
				Empresa e_proba = new Empresa("Trivago", "email@trivago.com");
				OrdenTrabajo ot_proba = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(e_proba);
				gp.vincularOrdenTrabajo(p, ot);
					
				//Act
				ArrayList<Proceso> resultado = gp.consultarProcesos(null, null, null ,null, null, ot_proba);
				
				//Assert
				assertTrue(resultado.isEmpty(), "Fallo al consultarProcesos con todo nulo excepto OT, y no coincidente");
			}
		}
	
		@Nested
		@DisplayName("Prueba8.3: consultarProcesosSinOrdenesTrabajo, conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja blanca")
		class prueba8_3 {
			@DisplayName("CB-CP01-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con lista de procesos almacenada en el sistema esta vacia.")
			@Test
			void CB_CP01_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP02-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fechaFin distinto de null y fechaInicioProceso mayor que fechaFin.")
			@Test
			void CB_CP02_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP03-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con incidencia inexistente.")
			@Test
			void CB_CP03_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP04-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con responsable inexistente.")
			@Test
			void CB_CP04_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP05-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con estadoAvanzado inexistente.")
			@Test
			void CB_CP05_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP06-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba valido con todos los parametros null")
			@Test
			void CB_CP06_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP07-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fecha del proceso menor que el rango del filtro")
			@Test
			void CB_CP07_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
				//Arrange
				ArrayList<Incidencia> incidencias= new ArrayList<>();
				Incidencia incidencia = new Incidencia(new Ciudadano("Manuel","4534535g","jauja"),null,null);
				incidencias.add(incidencia);
				GestionProcesos gp = new GestionProcesos();
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", incidencias);
				p.setFechaInicio(new Date(2020-1900, 01, 01));
				Date fecha = new Date(2020-1900, 01, 02) ;
				Date fecha2 = new Date(2020-1900, 12, 12) ;
				ArrayList<Proceso> esperado = new ArrayList();
				esperado.add(p);
			
				//Act
				ArrayList<Proceso> real = gp.consultarProcesosSinOrdenesTrabajo(fecha, fecha2, incidencia ,responsable, EstadoAvance.EnTramite);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al consultarProcesosSinOrdenesTrabajo caso de prueba no valido con fecha del proceso menor que el rango del filtro");
			}
			
			@DisplayName("CB-CP08-P8.3-consultarProcesosSinOrdenesTrabajo con fecha del proceso mayor que el rango del filtro")
			@Test
			void CB_CP08_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP09-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con incidencia inexistente")
			@Test
			void CB_CP09_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP10-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con responsable inexistente")
			@Test
			void CB_CP10_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP11-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba no valido con EstadoAvanzado inexistente")
			@Test
			void CB_CP11_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
			
			@DisplayName("CB-CP12-P8.3-consultarProcesosSinOrdenesTrabajo caso de prueba valido con todos los parametros correctos")
			@Test
			void CB_CP12_Prueba8_3_consultarProcesosSinOrdenesTrabajo() {
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
	}
	
	

	

	
	
	
	

	
	
}
