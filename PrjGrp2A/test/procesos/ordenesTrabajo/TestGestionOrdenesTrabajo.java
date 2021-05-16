package procesos.ordenesTrabajo;

import static org.junit.jupiter.api.Assertions.*;

import procesos.GestionProcesos;
import procesos.Proceso;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestGestionOrdenesTrabajo {

	
	@Nested
	@DisplayName("Pruebas de caja Negra sobre el módulo de gestión de Ordenes de Trabajo")
	class cajaNegra {
		@Nested
		@DisplayName("Prueba9.3:CrearOrdenTrabajo, Caso de prueba asociado a probar las clases de equivalencia válidas")
		class crearOrdenTrabajo {
			@DisplayName("CP01-P9.3_crearOrdenTrabajo en el que se comprueba el funcionamiento de la creacion para unos parametros validos")
			@Test
			void CP01_Prueba9_3_crearOrdenTrabajo() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa responsable = new Empresa("Electricas Pepe", "pepe@gmail.com");
				Date fechaIni = new Date(2021-1900, 01, 01) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				
				//Act
				OrdenTrabajo creada = got.crearOrdenTrabajo(responsable);
				ArrayList<OrdenTrabajo> registradas = got.buscarOrdenes(null, null, null, null); 
				
				//Assert
				assertAll(
						() -> {assertNotNull(creada,"Fallo al crearIncidencia se ha recibido un nulo");},
						() -> {assertTrue(creada.getResponsable()==null, "Responsable distinto de null en el momento de la creacion");},
						() -> {assertTrue(registradas.size()==1, "Fallo al PresentarIncidencia se ha recibido un array de registradas vacío o con mas elementos de los esperados");},
						() -> {assertSame(creada, registradas.get(0), "Fallo al presentarIncidencia el elemento recibido no se corresponde con el esperado");}
						);
			}
		}
		
		@Nested
		@DisplayName("Prueba5.1:BuscarOrdenes, Conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja negra")
		class prueba5_1 {
			@DisplayName("CP01-P5.1-buscarOrdenes caso de prueba valido con parametros validos.")
			@Test
			void CP01_Prueba5_1_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = null;
				Proceso pro = null;
				ArrayList<OrdenTrabajo> esperado = new ArrayList();
				esperado.add(ot);
			
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertEquals(esperado,real, "Fallo al buscarOrdenes con parámetros de búsqueda válidos");
			}
		
			@DisplayName("CP02-P5.1-buscarOrdenes caso de prueba no valido parametro fechaIni de tipo incorrecto.")
			@Test
			void CP02_Prueba5_1_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				Date fechaFin = new Date(2021-1900, 01, 02) ;
				Empresa res = null;
				Proceso pro = null;
			
				//Act

				
				//Assert
				assertThrows(IllegalArgumentException.class,() -> {Date fechaIni = new Date("mala");got.buscarOrdenes(fechaIni, fechaFin, res, pro);},"Se ha aceptado un tipo de parametro incorrecto para fechaIni" );

			}
			
			@DisplayName("CP03-P5.1-buscarOrdenes caso de prueba no valido parametro fechaIni posterior a fechaFin --> ArrayVacio")
			@Test
			void CP03_Prueba5_1_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				Date fechaIni = new Date(2021-1900, 03, 03) ;
				Date fechaFin = new Date(2021-1900, 01, 02) ;
				Empresa res = null;
				Proceso pro = null;
			
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al buscarOrdenes con parámetro fechaIni > fechaFin --> ArrayVacio");
			}
			
			@DisplayName("CP04-P5.1-buscarOrdenes caso de prueba no valido parametro fechaFin Incorrecta")
			@Test
			void CP04_Prueba5_1_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				Date fechaIni = new Date(2021-1900, 03, 03) ;
				Empresa res = null;
				Proceso pro = null;
			
				//Act

				
				//Assert
				assertThrows(IllegalArgumentException.class,() -> {Date fechaFin = new Date("mala");got.buscarOrdenes(fechaIni, fechaFin, res, pro);},"Se ha aceptado un tipo de parametro incorrecto para fechaIni" );
			}
			
			@DisplayName("CP05-P5.1-buscarOrdenes caso de prueba no valido parametro responsable vacio")
			@Test
			void CP05_Prueba5_1_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = new Empresa("","");
				Proceso pro = null;
				ArrayList<OrdenTrabajo> esperado = new ArrayList();
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertEquals(esperado,real,"Fallo al buscarOrdenes con parámetro responsable vacio, el array devuelto contiene elementos");
			}
			
			@DisplayName("CP06-P5.1-buscarOrdenes caso de prueba no valido parametro proceso vacio")
			@Test
			void CP06_Prueba5_1_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = new Empresa("Electricas Pepe","pepe@gmail.com");
				Proceso pro = new Proceso("",null,"");
				ArrayList<OrdenTrabajo> esperado = new ArrayList();
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertEquals(esperado,real,"Fallo al buscarOrdenes con parámetro proceso vacio");
			}
		}
		
		@Nested
		@DisplayName("Prueba6.1:asignarResponsable, Conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja negra")
		class prueba6_1 {
			//A PARTIR DE AQU� NADA TEN XEITO, PERO POR CULPA DO SEU C�DIGO, NO MI�A
			//Para aclarar, no creador/constructor de ot pasan unha empresa responsable
			//Peeero, sen embargo, te�en un asignar responsable, as� que estar�an facendo d�as veces un set de responsable
			//NADA TEN XEITO, TODO DA ASCO
			@DisplayName("CP01-P6.1-asignarResponsable caso de prueba valido con parametros validos.")
			@Test
			void CP01_Prueba6_1_asignarResponsable() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa responsable=new Empresa("Electricas Pepe", "pepe@gmail.com");
				
				OrdenTrabajo ot = got.crearOrdenTrabajo(responsable);
				
				//Act
				got.asignarResponsable(ot, responsable);
				
				//Assert
				assertEquals(ot.getResponsable(),responsable,"Fallo al asignarResponsable, mal asignado responsable");
			}
			
			@DisplayName("CP02-P6.1-asignarResponsable caso de prueba no valido con ordenTrabajo vacia.")
			@Test
			void CP02_Prueba6_1_asignarResponsable() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa responsable=new Empresa("Electricas Pepe", "pepe@gmail.com");
						
				OrdenTrabajo ot = got.crearOrdenTrabajo(responsable);
						
				//Act
				got.asignarResponsable(null, responsable);
				
				//E agora qu�
			}
			
			@DisplayName("CP03-P6.1-asignarResponsable caso de prueba no valido con responsable no valido.")
			@Test
			void CP03_Prueba6_1_asignarResponsable() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa responsable=new Empresa("Electricas Pepe", "pepe@gmail.com");
						
				OrdenTrabajo ot = got.crearOrdenTrabajo(responsable);
						
				//Act
				got.asignarResponsable(ot, null);
				
				//E agora qu�
			}
		}
		
	}
	
	@Nested
	@DisplayName("Pruebas de caja Blanca sobre el módulo de gestión de Ordenes de Trabajo")
	class cajaBlanca {
		@Nested
		@DisplayName("Prueba8.4:BuscarOrdenes, Conjunto de casos de prueba fruto de las tecnicas aplicadas para obtencion de pruebas de caja blanca")
		class prueba8_4 {
			@DisplayName("CB_CP01-P8.4-buscarOrdenes caso de prueba en el que no hay ordenes de trabajo almacenadas con anterioridad en el sistema")
			@Test
			void CB_CP01_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				
				Date fechaIni = new Date(2021-1900, 01, 02);
				Date fechaFin = null ;
				Empresa res = null;
				Proceso pro = null;
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(),"Fallo al buscarOrdenes sin ots almacenadas");
			}
			
			@Disabled("El sistema autoasigna las fechas a las OTs de forma que nunca podemos crear una OT con fecha de inicio nula")
			@DisplayName("CB_CP02-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con fecha de inicio a null")
			@Test
			void CB_CP02_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = null ;
				Empresa res = null;
				Proceso pro = null;
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(),"Fallo al buscarOrdenes con Ot sin fecha de Inicio almacenada");
			}
			
			@DisplayName("CB_CP03-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con fecha de inicio posterior al parametro fechaFin")
			@Test
			void CB_CP03_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
		
				Date fechaIni = new Date(2020-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 01, 02) ;
				Empresa res = null;
				Proceso pro = null;
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(),"Fallo al buscarOrdenes con Ot con parametro fecha de fin posterior a todas las registradas");
			}
			
			@DisplayName("CB_CP04-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con responsable y proceso igual a parametros pasados como filtro")
			@Test
			void CB_CP04_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa e = new Empresa("Electricas Pepe", "pepe@gmail.com");
				OrdenTrabajo ot = got.crearOrdenTrabajo(e);
				GestionProcesos gp = new GestionProcesos();
				Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
				gp.vincularOrdenTrabajo(p, ot);
				
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = e;
				Proceso pro = p;
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertAll(
						() -> {assertNotNull(real,"Fallo al buscarOrdenes se ha recibido un nulo");},
						() -> {assertTrue(real.size()==1, "Fallo al buscarOrdenes con OT se ha recibido un array vacio o con mas elementos de los esperados");},
						() -> {assertSame(ot, real.get(0), "Fallo al buscarOrdenes el elemento recibido no se corresponde con el esperado");}
						);
			}
			
			@DisplayName("CB_CP05-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con responsable igual a parametro pasado como filtro y parametro proceso a null")
			@Test
			void CB_CP05_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa e = new Empresa("Electricas Pepe", "pepe@gmail.com");
				OrdenTrabajo ot = got.crearOrdenTrabajo(e);
				GestionProcesos gp = new GestionProcesos();
				Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
				gp.vincularOrdenTrabajo(p, ot);
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = e;
				Proceso pro = null;
				
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertAll(
						() -> {assertNotNull(real,"Fallo al buscarOrdenes se ha recibido un nulo");},
						() -> {assertTrue(real.size()==1, "Fallo al buscarOrdenes con OT se ha recibido un array vacio o con mas elementos de los esperados");},
						() -> {assertSame(ot, real.get(0), "Fallo al buscarOrdenes el elemento recibido no se corresponde con el esperado");}
						);
			}
			
			@DisplayName("CB_CP06-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con responsable igual a parametro pasado como filtro y proceso distinto al pasado")
			@Test
			void CB_CP06_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				Empresa e = new Empresa("Electricas Pepe", "pepe@gmail.com");
				OrdenTrabajo ot = got.crearOrdenTrabajo(e);
				GestionProcesos gp = new GestionProcesos();
				Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
				gp.vincularOrdenTrabajo(p, ot);
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = e;
				Proceso pro = new Proceso("Ptest2",null,"test");
				
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al buscar Ordenes de Trabajo el resultado no es vacio");
			}
			
			@DisplayName("CB_CP07-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con responsable distinto a parametro pasado como filtro siendo este nulo y proceso distinto al pasado como filtro")
			@Test
			void CB_CP07_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				GestionProcesos gp = new GestionProcesos();
				Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
				gp.vincularOrdenTrabajo(p, ot);
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = null;
				Proceso pro = new Proceso("Ptest2",null,"test");
				
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al buscar Ordenes de Trabajo el resultado no es vacio");
			}
			
			@DisplayName("CB_CP08-P8.4-buscarOrdenes caso de prueba en el que hay una OT almacenada previamente con responsable distinto a parametro pasado como filtro")
			@Test
			void CB_CP08_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				GestionProcesos gp = new GestionProcesos();
				Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
				gp.vincularOrdenTrabajo(p, ot);
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = new Empresa("Distinto", "distinto@gmail.com");
				Proceso pro = null;
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al buscar Ordenes de Trabajo el resultado no es vacio");
			}
			
			@DisplayName("CB_CP09-P8.4-buscarOrdenes caso de prueba en el que hay 2 OTs almacenadas previamente la primera con responsable distinto a parametro pasado como filtro y la segunda el responsable coincide pero el proceso distinto del parametro pasado")
			@Test
			void CB_CP09_Prueba8_4_buscarOrdenes() {
				//Arrange
				GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
				
				OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
				GestionProcesos gp = new GestionProcesos();
				Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
				gp.vincularOrdenTrabajo(p, ot);
				
				Proceso p2 = gp.crearNuevoProceso("Ptest2", null, "test2", new ArrayList());
				OrdenTrabajo ot2 = got.crearOrdenTrabajo(new Empresa("Distinto", "distinto@gmail.com"));
				gp.vincularOrdenTrabajo(p2, ot2);
				
				Date fechaIni = new Date(2021-1900, 01, 02) ;
				Date fechaFin = new Date(2021-1900, 12, 12) ;
				Empresa res = new Empresa("Distinto", "distinto@gmail.com");
				Proceso pro = p;
				
				//Act
				ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
				
				//Assert
				assertTrue(real.isEmpty(), "Fallo al buscar Ordenes de Trabajo el resultado no es vacio");
			}
		}
	
	}
	
	
	
}
