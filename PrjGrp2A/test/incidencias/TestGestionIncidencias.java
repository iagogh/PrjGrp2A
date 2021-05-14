package incidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import com.enso.ayuntamiento.Ciudadano;
import com.enso.ayuntamiento.Concejal;

import incidencias.TipoIncidencia;
import procesos.GestionProcesos;
import procesos.Proceso;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestGestionIncidencias {

	@DisplayName("CP01-P9.1_presentarIncidencia en el que se comprueba el funcionamiento de la creacion para unos parametros validos: Vecino, Localizacion, Descripcion y Tipo")
	@Test
	void CP01_Prueba9_1_presentarIncidencia() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		Ciudadano v = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666666666");
		String Localizacion = "Calle Callejero";
		String Descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		// Act
		Incidencia creada = gi.presentarIncidencia(v, Localizacion, Descripcion, tipo);
		ArrayList<Incidencia> registradas = gi.getIncidencias();

		// Assert
		assertAll(() -> {
			assertNotNull(creada, "Fallo al presentarIncidencia se ha recibido un nulo");
		}, () -> {
			assertTrue(registradas.size() == 1,
					"Fallo al PresentarIncidencia se ha recibido un array de registradas vacío o con mas elementos de los esperados");
		}, () -> {
			assertSame(creada, registradas.get(0),
					"Fallo al presentarIncidencia el elemento recibido no se corresponde con el esperado");
		});
	}

	@DisplayName("CP01-Prueba1.1_buscarIncidencias con todos los argumentos correctos")
	@Test
	void CP01_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
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
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);
		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		Object[] esperado = resultadoEsperado.toArray();
		Object[] obtenido = resultadoObtenido.toArray();
		assertArrayEquals(esperado, obtenido, "Búsqueda incorrecta con parámetros válidos");
	}

	@DisplayName("CP02-Prueba1.1_buscarIncidencias no válido con parámetro dni incorrecto")
	@Test
	void CP02_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 01, 01);
		Date fechaFin = new Date(2021 - 1900, 12, 31);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias((String) new Object(), idIncidencia, fechaInicio,
				fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de un tipo no String");
	}

	@DisplayName("CP03-Prueba1.1_buscarIncidencias no válido con parámetro dni incorrecto con 8 caracteres")
	@Test
	void CP03_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "1234567F";
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 01, 01);
		Date fechaFin = new Date(2021 - 1900, 12, 31);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de menos de 9 caracteres");
	}

	@DisplayName("CP04-Prueba1.1_buscarIncidencias no válido con parámetro dni incorrecto con 10 caracteres")
	@Test
	void CP04_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "123456789F";
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 01, 01);
		Date fechaFin = new Date(2021 - 1900, 12, 31);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de más de 9 caracteres");
	}

	@DisplayName("CP05-Prueba1.1_buscarIncidencias no válido con parámetro idIncidencia incorrecto con 6 caracteres")
	@Test
	void CP05_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I123456";
		Date fechaInicio = new Date(2021 - 1900, 01, 01);
		Date fechaFin = new Date(2021 - 1900, 12, 31);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro idIncidencia con más de 5 caracteres");
	}

	@DisplayName("CP06-Prueba1.1_buscarIncidencias no válido con parámetro idIncidencia incorrecto con 4 caracteres")
	@Test
	/* HAI QUE CAMBIAR NO DOC PORQUE SI TEN MENOS DE 4 CARACTERES É VÁLIDA */
	void CP06_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 01, 01);
		Date fechaFin = new Date(2021 - 1900, 12, 31);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro idIncidencia incorrecto con 4 caracteres");
	}

	@DisplayName("CP07-Prueba1.1_buscarIncidencias no válido con fechaInicio distintia de tipo Date")
	@Test
	void CP07_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I1";
		Date fechaInicio = new Date("mala");
		Date fechaFin = new Date(2021 - 1900, 12, 31);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro fechaInicio distinto de tipo Date");
	}

	@DisplayName("CP08-Prueba1.1_buscarIncidencias no válido con fechaFin distintia de tipo Date")
	@Test
	void CP08_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 01, 01);
		Date fechaFin = new Date("mala");
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro fechaFin distinto de tipo Date");
	}

	@DisplayName("CP09-Prueba1.1_buscarIncidencias no válido con fechaIni > fechaFin")
	@Test
	void CP09_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 12, 31);
		Date fechaFin = new Date(2021 - 1900, 01, 01);
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite fechaInicio > fechaFin");
	}

	@DisplayName("CP10-Prueba1.1_buscarIncidencias no válido con parámetro tipo distinto de tipoIncidencia")
	@Test
	void CP10_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I1";
		Date fechaInicio = new Date(2021 - 1900, 12, 31);
		Date fechaFin = new Date(2021 - 1900, 01, 01);
		TipoIncidencia tipo = TipoIncidencia.valueOf("Otra");

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, tipo);
		vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
		incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Servicios);
		vecino = new Ciudadano("Jose", "66666666F", "Calle callejero", "666444555");
		incidencia = gi.presentarIncidencia(vecino, "Otra Calle", descripcion, TipoIncidencia.Transportes);

		// Act
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro parámetro tipo distinto de tipoIncidencia");
	}

	@DisplayName("CP01-Prueba3.1_vincularIncidencia válido que incluye todas las clases válidas")
	@Test
	void CP01_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
		ArrayList<Incidencia> incidencias = gi.incidenciasSinProceso();

		Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());

		// Act
		gp.vincularIncidencia(proceso, incidencias);
		// Assert
		assertAll(() -> {
			assertTrue(!proceso.getIncidencias().isEmpty(),
					"Prueba fallida, no hay ninguna incidencia asignada al proceso");
		}, () -> {
			assertNotNull(incidencia.getProceso(), "Prueba fallida, la incidencia no tiene ningún proceso asignado");
		}, () -> {
			assertEquals(proceso, incidencia.getProceso(),
					"Prueba fallida, la incidencia no está asignada al proceso indicado");
		});
	}

	@DisplayName("CP02-Prueba3.1_vincularIncidencia no válido con parámetro proceso incorrecto")
	@Test
	void CP02_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
		ArrayList<Incidencia> incidencias = gi.incidenciasSinProceso();

		Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso proceso = gp.crearNuevoProceso(null, null, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());

		// Act
		gp.vincularIncidencia(proceso, incidencias);
		// Assert
		assertNull(incidencia.getProceso(), "Prueba fallida, a la incidencia se le ha asignado un proceso");
	}

	@DisplayName("CP03-Prueba3.1_vincularIncidencia no válido con parámetro incidencias incorrecto")
	@Test
	void CP03_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(null, localizacion, descripcion, TipoIncidencia.Desperfectos);
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(incidencia);

		Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());
		ArrayList<Incidencia> incidenciasAntes = proceso.getIncidencias();
		// Act
		gp.vincularIncidencia(proceso, incidencias);
		ArrayList<Incidencia> incidenciasDespues = proceso.getIncidencias();
		// Assert
		assertEquals(incidenciasAntes, incidenciasDespues,
				"Prueba fallida, se han modificado las incidencias asignadas al prceso despues de vincular incidencias incorrectas");
	}

	@DisplayName("CP04-Prueba3.1_vincularIncidencia válido donde el primer y el último elemento del parámetro incidencias son correctos")
	@Test
	void CP04_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		ArrayList<Incidencia> esperado = new ArrayList<>();
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
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
		Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());

		// Act
		gp.vincularIncidencia(proceso, incidencias);
		ArrayList<Incidencia> obtenido = proceso.getIncidencias();
		// Assert
		assertAll(
			() -> {assertTrue(obtenido.contains(incidencias.get(0)),"Prueba fallida, no se ha asignado el primer elemento");}, 
			() -> {assertTrue(obtenido.contains(incidencias.get(incidencias.size() - 1)), "Prueba Fallida, no se ha asignado el último elemento");}, 
			() -> {assertEquals(esperado, obtenido, "Prueba fallida, se han vinculado al proceso incidencias incorrectas");
		});
		
	}
	
	@DisplayName("CP05-Prueba3.1_vincularIncidencia no válido donde el primer elemento del parámetro incidencias es incorrecto")
	@Test
	void CP05_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		ArrayList<Incidencia> esperado = new ArrayList<>();
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
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
		Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());

		// Act
		gp.vincularIncidencia(proceso, incidencias);
		ArrayList<Incidencia> obtenido = proceso.getIncidencias();
		// Assert
		assertAll(
			() -> {assertFalse(obtenido.contains(incidencias.get(0)),"Prueba fallida, se ha vinculado la primera incidencia como incorrecta");}, 
			() -> {assertEquals(esperado, obtenido, "Prueba fallida, se han vinculado al proceso incidencias incorrectas");
		});	
	}
	
	@DisplayName("CP06-Prueba3.1_vincularIncidencia no válido con parámetro incidencias vacío")
	@Test
	void CP06_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
		Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());
		
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		ArrayList<Incidencia> incidenciasAntes = proceso.getIncidencias();
		// Act
		gp.vincularIncidencia(proceso, incidencias);
		ArrayList<Incidencia> incidenciasDespues = proceso.getIncidencias();
		// Assert
		assertAll(
		() -> {assertNull(incidencia.getProceso(), "Prueba fallida, a la incidencia existente se le ha asignado un proceso");}, 
		() -> {assertEquals(incidenciasAntes,incidenciasDespues, "Prueba fallida, las incidencias asignadas al proceso indicado se han modificado");}
		);
	}
	
	@DisplayName("CP07-Prueba3.1_vincularIncidencia no válido con  incidencias repetidas iguales")
	@Test
	void CP07_Prueba3_3_vincularIncidencia() {
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(incidencia);
		incidencias.add(incidencia);
		ArrayList<Incidencia> esperado = new ArrayList<>();
		esperado.add(incidencia);

		Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso proceso = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());

		// Act
		gp.vincularIncidencia(proceso, incidencias);
		ArrayList<Incidencia> obtenido = proceso.getIncidencias();
		// Assert
		assertAll(
				() -> {assertEquals(proceso, incidencia.getProceso(), "Prueba fallida, a la incidencia se le ha asignado otro proceso");},
				() -> {assertEquals(obtenido, esperado, "Prueba fallida, no se han obtenido las incidencias vinculadas al proceso esperadas");}
		);
	}
	
	@DisplayName("CP08-Prueba3.1_vincularIncidencia válido, parámetro incidencias contiene incidencia ya asginada")
	@Test
	void CP08_Prueba3_3_vincularIncidencia() {
		/*CAMBIAR NO DOC A VÁLIDO PARA QUE SI SE VINCULA UNHA INCIDENCIA XA VINCULADA SE REASIGNE*/
		// Arrange
		GestionProcesos gp = new GestionProcesos();
		GestionIncidencias gi = new GestionIncidencias();

		String localizacion = "Calle Callejero";
		String descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";

		Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
		Incidencia incidencia = gi.presentarIncidencia(vecino, localizacion, descripcion, TipoIncidencia.Desperfectos);
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(incidencia);

		Concejal responsable = new Concejal("Javier", "45959101H", "Santiago", "666666666");
		Proceso procesoAntiguo = gp.crearNuevoProceso("Cambiar bombillas", responsable, "Hay que cambiar las bombillas",
				new ArrayList<Incidencia>());
		gp.vincularIncidencia(procesoAntiguo, incidencias);
		
		Proceso procesoNuevo = gp.crearNuevoProceso("Volver a cambiar las bombillas", responsable, "Volver a cambiar las bombillas cambidasas la úlima vez",
				new ArrayList<Incidencia>());
		
		// Act
		gp.vincularIncidencia(procesoNuevo, incidencias);
		// Assert
		assertAll(
				() -> {assertEquals(procesoNuevo, incidencia.getProceso(), "Prueba fallida, no se ha resignado el proceso");},
				() -> {assertFalse(procesoAntiguo.getIncidencias().contains(incidencia), "Prueba fallida, el antiguo proceso contnúa con la incidencia asignada");},
				() -> {assertEquals(incidencias, procesoNuevo.getIncidencias(), "Prueba fallida, al nuevo proceso no se la asignado la incidencia");}
		);
	}
	

}
