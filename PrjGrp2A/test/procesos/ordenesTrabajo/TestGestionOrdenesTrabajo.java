package procesos.ordenesTrabajo;

import static org.junit.jupiter.api.Assertions.*;

import procesos.GestionProcesos;
import procesos.Proceso;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestGestionOrdenesTrabajo {

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
		Date fechaIni = new Date("mala") ;
		Date fechaFin = new Date(2021-1900, 01, 02) ;
		Empresa res = null;
		Proceso pro = null;
	
		//Act
		ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al buscarOrdenes con parámetro fechaIni Incorrecto");
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
		Date fechaFin = new Date("mala") ;
		Empresa res = null;
		Proceso pro = null;
	
		//Act
		ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
		
		//Assert
		assertTrue(real.isEmpty(), "Fallo al buscarOrdenes con parámetro fechaIni > fechaFin --> ArrayVacio");
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
		esperado.add(ot);
		
		//Act
		ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
		
		//Assert
		assertEquals(esperado,real,"Fallo al buscarOrdenes con parámetro responsable vacio");
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
		esperado.add(ot);
		
		//Act
		ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
		
		//Assert
		assertEquals(esperado,real,"Fallo al buscarOrdenes con parámetro proceso vacio");
	}
	
	@DisplayName("CB_CP01-P8.4-buscarOrdenes caso de prueba en el que no hay ordenes de trabajo almacenadas con anterioridad en el sistema")
	@Test
	void CB_CP01_Prueba8_4_buscarOrdenes() {
		//Arrange
		GestionOrdenesTrabajo got = new GestionOrdenesTrabajo();
		
		Date fechaIni = new Date(2021-1900, 01, 02) ;
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
		OrdenTrabajo ot = got.crearOrdenTrabajo(new Empresa("Electricas Pepe", "pepe@gmail.com"));
		GestionProcesos gp = new GestionProcesos();
		Proceso p = gp.crearNuevoProceso("Ptest", null, "test", new ArrayList());
		gp.vincularOrdenTrabajo(p, ot);
		System.out.println(ot);
		Date fechaIni = new Date(2021-1900, 01, 02) ;
		Date fechaFin = new Date(2021-1900, 12, 12) ;
		Empresa res = new Empresa("Electricas Pepe", "pepe@gmail.com");
		Proceso pro = new Proceso("Ptest", null, "test");
		
		//Act
		ArrayList<OrdenTrabajo> real = got.buscarOrdenes(fechaIni, fechaFin, res, pro);
		
		//Assert
		assertAll(
				() -> {assertNotNull(real,"Fallo al buscarOrdenes se ha recibido un nulo");},
				() -> {assertTrue(real.size()==1, "Fallo al buscarOrdenes con OT se ha recibido un array vacío o con mas elementos de los esperados");},
				() -> {assertSame(ot, real.get(0), "Fallo al buscarOrdenes el elemento recibido no se corresponde con el esperado");}
				);
	}
	
	
	
}
