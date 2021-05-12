package incidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import com.enso.ayuntamiento.Ciudadano;
import incidencias.TipoIncidencia;

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
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias((String) new Object(), idIncidencia,fechaInicio, fechaFin, tipo);
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
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia,fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de un tipo no String");
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
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia,fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de un tipo no String");
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
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia,fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de un tipo no String");
	}
	
	@DisplayName("CP06-Prueba1.1_buscarIncidencias no válido con parámetro idIncidencia incorrecto con 4 caracteres")
	@Test
	/*HAI QUE CAMBIAR NO DOC PORQUE SI TEN MENOS DE 4 CARACTERES É VÁLIDA*/
	void CP06_Prueba1_1_buscarIncidencias() {
		// Arrange
		GestionIncidencias gi = new GestionIncidencias();
		String dni = "12345678P";
		String idIncidencia = "I1234";
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
		ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia,fechaInicio, fechaFin, tipo);
		// Assert
		assertNull(resultadoObtenido, "Prueba fallida, permite parámetro dni de un tipo no String");
	}
	
	

}
