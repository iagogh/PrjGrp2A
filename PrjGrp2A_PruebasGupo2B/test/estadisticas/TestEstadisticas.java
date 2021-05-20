package estadisticas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.enso.ayuntamiento.Ciudadano;
import com.enso.ayuntamiento.Concejal;

import incidencias.*;
import procesos.*;
import procesos.ordenesTrabajo.Empresa;
import procesos.ordenesTrabajo.OrdenTrabajo;

class TestEstadisticas {
	
	@Nested
	class TestEstadisticasCajaNegra{
		IGestionIncidencias gi;
		IGestionProcesos gp;
		Estadisticas e;
		
		@BeforeEach
		void setUp() throws Exception {
			gi = new GestionIncidencias();
			gp = new GestionProcesos();
			e = new Estadisticas(gp,gi);
		}
		
		@AfterEach
		void tearDown() throws Exception {
			e = null;
			gi = null;
			gp = null;
		}
		
		@Nested
		class TestNumeroIncidenciasSinArgumentos{
			//Caja negra
			@DisplayName("CP01-P7.1-numeroIncidencias(sin argumentos) caso de prueba valido con parametros validos.")
			@Test
			void CP01_Prueba7_1_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroIncidencias();
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
		
			@DisplayName("CP02-P7.1-numeroIncidencias(sin argumentos) caso de prueba no valido que incluye una clase no valida")
			@Test
			@Disabled
			void CP02_Prueba7_1_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroIncidencias();
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
		}
		
		@Nested
		class TestNumeroIncidenciasConArgumentos{
			@DisplayName("CP01-P7.2-numeroIncidencias(con argumentos) caso de prueba valido con parametros validos.")
			@Test
			void CP01_Prueba7_2_numeroIncidencias() {
				//Arrange
						String dni = "12345678P";
						String idIncidencia = "I1";
						Date fechaInicio = new Date(2021 - 1900, 01, 01);
						Date fechaFin = new Date(2021 - 1900, 12, 31);
						TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
						Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
						String localizacion = "Calle Callejero";
						String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
						ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
						Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
						resultadoEsperado.add(incidencia);
						
						Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
						Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
						
						p.setFechaInicio(new Date(2020-1900, 03, 03));				
						
						Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
						OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
						gp.vincularOrdenTrabajo(p,ot);
						//Act
						Estadistica<Incidencia> est = e.numeroInccidencias(responsable, emp, fechaInicio, fechaFin);
						//Assert
						Object[] esperado = resultadoEsperado.toArray();
						Object[] obtenido = est.getListaResultado().toArray();
						assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
			
			@DisplayName("CP02-P7.2-numeroIncidencias(con argumentos) caso de prueba valido con responsable nulo.")
			@Test
			void CP02_Prueba7_2_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroInccidencias(null, emp, fechaInicio, fechaFin);
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
			
			@DisplayName("CP03-P7.2-numeroIncidencias(con argumentos) caso de prueba valido con empresa nula.")
			@Test
			void CP03_Prueba7_2_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroInccidencias(responsable, null, fechaInicio, fechaFin);
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
			
			@DisplayName("CP04-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con filtroFechaIni no valido.")
			@Test
			void CP04_Prueba7_2_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroInccidencias(responsable, emp, null, fechaFin);
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
			
			@DisplayName("CP05-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con filtroFechaFin no valido.")
			@Test
			void CP05_Prueba7_2_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroInccidencias(responsable, emp, fechaInicio, null);
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
			
			@DisplayName("CP06-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con un elemento no perteneciente a la clase incidencias")
			@Test
			@Disabled
			void CP06_Prueba7_2_numeroIncidencias() {
				//Arrange
				String dni = "12345678P";
				String idIncidencia = "I1";
				Date fechaInicio = new Date(2021 - 1900, 01, 01);
				Date fechaFin = new Date(2021 - 1900, 12, 31);
				TipoIncidencia tipo = TipoIncidencia.Desperfectos;
	
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				String localizacion = "Calle Callejero";
				String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
	
				ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
				Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
				resultadoEsperado.add(incidencia);
				
				Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
				Proceso p = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas", resultadoEsperado);
				
				p.setFechaInicio(new Date(2020-1900, 03, 03));				
				
				Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
				OrdenTrabajo ot = gp.devolverGestorOrdenesTrabajo().crearOrdenTrabajo(emp);
				gp.vincularOrdenTrabajo(p,ot);
				//Act
				Estadistica<Incidencia> est = e.numeroInccidencias(responsable, emp, fechaInicio, fechaFin);
				//Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = est.getListaResultado().toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
			}
		}
	}
	@Nested
	class TestEstadisticasCajaBlanca{
		IGestionIncidencias mockGi;
		IGestionProcesos mockGp;
		
		@InjectMocks
		Estadisticas e;
		
		@BeforeEach
		void setUp() throws Exception {
			mockGi = Mockito.mock(IGestionIncidencias.class);
			mockGp = Mockito.mock(IGestionProcesos.class);
			
			e = new Estadisticas(mockGp,mockGi);
			
			MockitoAnnotations.initMocks(e);
		}
		
		@AfterEach
		void tearDown() throws Exception {
			e = null;
			mockGi = null;
			mockGp = null;
		}
		//Caja blanca
		@DisplayName("CP01-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido referido a que la lista de incidencias est� vacia")
		@Test
		void CP01_Prueba8_5_numeroIncidencias() {
			//Arrange
			ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
			Mockito.when(mockGi.obtenerTodasIncidencias()).thenReturn(resultadoEsperado);
			//Act
			Estadistica<Incidencia> est = e.numeroIncidencias();
			//Assert
			Object[] esperado = resultadoEsperado.toArray();
			Object[] obtenido = est.getListaResultado().toArray();
			assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
		}
		@DisplayName("CP02-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias almacena una incidencia con un proceso asociado con coste 0")
		@Test
		void CP02_Prueba8_5_numeroIncidencias() {
			//Arrange
			String dni = "12345678P";
			String idIncidencia = "I1";
			Date fechaInicio = new Date(2021 - 1900, 01, 01);
			Date fechaFin = new Date(2021 - 1900, 12, 31);
			TipoIncidencia tipo = TipoIncidencia.Desperfectos;
	
			Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
			String localizacion = "Calle Callejero";
			String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
	
			ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
			Incidencia incidencia = new Incidencia(vecino, localizacion, descripcion, tipo);
			resultadoEsperado.add(incidencia);
			
			Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
			Proceso p = new Proceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas");
			p.vincularIncidencia(resultadoEsperado);
			
			p.setFechaInicio(new Date(2020-1900, 03, 03));				
			p.setCoste((float)0.0);
			
			Mockito.when(mockGi.obtenerTodasIncidencias()).thenReturn(resultadoEsperado);
			//Act
			Estadistica<Incidencia> est = e.numeroIncidencias();
			//Assert
			Object[] esperado = resultadoEsperado.toArray();
			Object[] obtenido = est.getListaResultado().toArray();
			assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
		}
		@DisplayName("CP03-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena una incidencia con un proceso asociado con coste > 0 sin OT")
		@Test
		void CP03_Prueba8_5_numeroIncidencias() {
			//Arrange
			String dni = "12345678P";
			String idIncidencia = "I1";
			Date fechaInicio = new Date(2021 - 1900, 01, 01);
			Date fechaFin = new Date(2021 - 1900, 12, 31);
			TipoIncidencia tipo = TipoIncidencia.Desperfectos;
	
			Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
			String localizacion = "Calle Callejero";
			String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
	
			ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
			Incidencia incidencia = new Incidencia(vecino, localizacion, descripcion, tipo);
			resultadoEsperado.add(incidencia);
			
			Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
			Proceso p = new Proceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas");
			p.vincularIncidencia(resultadoEsperado);
			
			p.setFechaInicio(new Date(2020-1900, 03, 03));				
			p.setCoste((float)0.1);

			Mockito.when(mockGi.obtenerTodasIncidencias()).thenReturn(resultadoEsperado);
			//Act
			Estadistica<Incidencia> est = e.numeroIncidencias();
			//Assert
			Object[] esperado = resultadoEsperado.toArray();
			Object[] obtenido = est.getListaResultado().toArray();
			assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
		}
		@DisplayName("CP04-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena una incidencia con un proceso asociado con coste mayor que 0 con OT coste > 0")	
		@Test
		void CP04_Prueba8_5_numeroIncidencias() {
			//Arrange
					String dni = "12345678P";
					String idIncidencia = "I1";
					Date fechaInicio = new Date(2021 - 1900, 01, 01);
					Date fechaFin = new Date(2021 - 1900, 12, 31);
					TipoIncidencia tipo = TipoIncidencia.Desperfectos;
	
					Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
					String localizacion = "Calle Callejero";
					String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
	
					ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
					Incidencia incidencia = new Incidencia(vecino, localizacion, descripcion, tipo);
					resultadoEsperado.add(incidencia);
					
					Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
					Proceso p = new Proceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas");
					p.vincularIncidencia(resultadoEsperado);
					
					p.setFechaInicio(new Date(2020-1900, 03, 03));				
					
					Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
					OrdenTrabajo ot = new OrdenTrabajo(emp);
					ot.setCoste((float)1.5);
					p.vincularOrdenTrabajo(ot);
					
					Mockito.when(mockGi.obtenerTodasIncidencias()).thenReturn(resultadoEsperado);
					//Act
					Estadistica<Incidencia> est = e.numeroIncidencias();
					//Assert
					Object[] esperado = resultadoEsperado.toArray();
					Object[] obtenido = est.getListaResultado().toArray();
					assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
		}
		@DisplayName("CP05-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena una incidencia con un proceso asociado con coste mayor que 0 con OT coste <= 0")
		@Test
		void CP05_Prueba8_5_numeroIncidencias() {
			//Arrange
					String dni = "12345678P";
					String idIncidencia = "I1";
					Date fechaInicio = new Date(2021 - 1900, 01, 01);
					Date fechaFin = new Date(2021 - 1900, 12, 31);
					TipoIncidencia tipo = TipoIncidencia.Desperfectos;
	
					Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
					String localizacion = "Calle Callejero";
					String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
	
					ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
					Incidencia incidencia = new Incidencia(vecino, localizacion, descripcion, tipo);
					resultadoEsperado.add(incidencia);
					
					Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
					Proceso p = new Proceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas");
					p.vincularIncidencia(resultadoEsperado);
					
					p.setFechaInicio(new Date(2020-1900, 03, 03));				
					
					Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
					OrdenTrabajo ot = new OrdenTrabajo(emp);
					ot.setCoste((float)0.0);
					p.vincularOrdenTrabajo(ot);
					p.setCoste((float)0.1);
					
					Mockito.when(mockGi.obtenerTodasIncidencias()).thenReturn(resultadoEsperado);
					//Act
					Estadistica<Incidencia> est = e.numeroIncidencias();
					//Assert
					Object[] esperado = resultadoEsperado.toArray();
					Object[] obtenido = est.getListaResultado().toArray();
					assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
		}
		@DisplayName("CP06-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena dos incidencias con un proceso asociado con coste mayor que 0 con OT coste > 0")
		@Test
		void CP06_Prueba8_5_numeroIncidencias() {
			//Arrange
					String dni = "12345678P";
					String idIncidencia = "I1";
					Date fechaInicio = new Date(2021 - 1900, 01, 01);
					Date fechaFin = new Date(2021 - 1900, 12, 31);
					TipoIncidencia tipo = TipoIncidencia.Desperfectos;
	
					Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
					String localizacion = "Calle Callejero";
					String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
					
					String dni2 = "12345687P";
	
					Ciudadano vecino2 = new Ciudadano("Paco", dni, "Calle callej�n", "666666678");
					String localizacion2 = "Calle Callejero";
					String descripcion2 = "Descripcion2 de prueba para la creacion de una incidencia de prueba";
	
					ArrayList<Incidencia> resultadoEsperado = new ArrayList<>();
					Incidencia incidencia = new Incidencia(vecino, localizacion, descripcion, tipo);
					resultadoEsperado.add(incidencia);
					
					Incidencia incidencia2 = new Incidencia(vecino2, localizacion2, descripcion2, tipo);
					resultadoEsperado.add(incidencia2);
					
					Concejal responsable=new Concejal("Javier", "45959101H", "Santiago", "666666666");
					Proceso p = new Proceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas");
					p.vincularIncidencia(resultadoEsperado);
					
					p.setFechaInicio(new Date(2020-1900, 03, 03));				
					
					Empresa emp = new Empresa("Hotusa", "email@hotusa.com");
					OrdenTrabajo ot = new OrdenTrabajo(emp);
					ot.setCoste((float)1.5);
					p.vincularOrdenTrabajo(ot);
					
					Mockito.when(mockGi.obtenerTodasIncidencias()).thenReturn(resultadoEsperado);
					//Act
					Estadistica<Incidencia> est = e.numeroIncidencias();
					//Assert
					Object[] esperado = resultadoEsperado.toArray();
					Object[] obtenido = est.getListaResultado().toArray();
					assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
		}
	}
}
