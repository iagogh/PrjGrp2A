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
import org.junit.jupiter.api.Nested;
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

	@Nested
	@DisplayName("Casos de prueba: buscarIncidencias")
	class buscarIncidencias {
		@Nested
		@DisplayName("Caja Negra")
		class buscarIncidenciasCajaNegra {
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
				// Assert
				Object[] esperado = resultadoEsperado.toArray();
				Object[] obtenido = resultadoObtenido.toArray();
				assertArrayEquals(esperado, obtenido, "Resúltado de búsqueda incorrecto con parámetros válidos");
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias((String) new Object(), idIncidencia,
						fechaInicio, fechaFin, tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
				// Assert
				assertNull(resultadoObtenido,
						"Prueba fallida, permite parámetro idIncidencia incorrecto con 4 caracteres");
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
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
				ArrayList<Incidencia> resultadoObtenido = gi.buscarIncidencias(dni, idIncidencia, fechaInicio, fechaFin,
						tipo);
				// Assert
				assertNull(resultadoObtenido,
						"Prueba fallida, permite parámetro parámetro tipo distinto de tipoIncidencia");
			}
		}

		@Nested
		@DisplayName("Caja Blanca")
		class buscarIncidenciasCajaBalnca {
			@DisplayName("CP01-Prueba8.1_buscarIncidencias lista de incidencias almacenadas en el sistema vacía")
			@Test
			void CP01_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				ArrayList<Incidencia> obtenido = new ArrayList<>();
				// Act
				obtenido = gi.buscarIncidencias(null, null, null, null, null);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP02-Prueba8.1_buscarIncidencias parámetro fechaInicio != null y fechaInicio > fechaInicioIncidencia")
			@Test
			void CP02_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				String dni = "12345678P";
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2021 - 1900, 12, 31);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(dni, "I1", fechaIni, fechaFin,
						TipoIncidencia.Desperfectos);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP03-Prueba8.1_buscarIncidencias parámetro fechaInicio != null y fechaInicioInicidencia > fechaFin")
			@Test
			void CP03_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				String dni = "12345678P";
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2020 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(dni, "I1", fechaIni, fechaFin,
						TipoIncidencia.Desperfectos);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP04-Prueba8.1_buscarIncidencias con parámetros dni, idIncidencia y tipo distintos del dni, idIncidencia y tipo de las incidencias existentes en el sistema")
			@Test
			void CP04_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias("12345678A", "I2", fechaIni, fechaFin,
						TipoIncidencia.Transportes);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP05-Prueba8.1_buscarIncidencias con parámetros dni == null, id distinto y tipo distinto")
			@Test
			void CP05_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(null, "I2", fechaIni, fechaFin,
						TipoIncidencia.Transportes);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP06-Prueba8.1_buscarIncidencias con parámetros dni != null e igual al de la incidencia existente")
			@Test
			void CP06_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				String dni = "12345678P";
				Ciudadano vecino = new Ciudadano("Raul", dni, "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> esperado = new ArrayList<>();
				esperado.add(incidencia);
				vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
				incidencia = gi.presentarIncidencia(vecino, "Calle callejerao", "Descripcion",
						TipoIncidencia.Servicios);

				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(dni, "I2", fechaIni, fechaFin,
						TipoIncidencia.Transportes);
				// Assert
				assertAll(() -> {
					assertFalse(obtenido.isEmpty(), "Prueba fallida, no ha encontrado incidencias");
				}, () -> {
					assertEquals(obtenido, esperado, "Prueba fallida, no se ha obtenido la incidencia esperada");
				});
			}

			@DisplayName("CP07-Prueba8.1_buscarIncidencias con parámetros dni distinto, id == null y tipo distinto")
			@Test
			void CP07_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias("12345678A", null, fechaIni, fechaFin,
						TipoIncidencia.Transportes);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP08-Prueba8.1_buscarIncidencias con parámetros idIncidencia != null e igual al de la incidencia existente")
			@Test
			void CP08_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> esperado = new ArrayList<>();
				esperado.add(incidencia);
				vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
				incidencia = gi.presentarIncidencia(vecino, "Calle callejerao", "Descripcion",
						TipoIncidencia.Servicios);

				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);
				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias("12345678A", "I1", fechaIni, fechaFin,
						TipoIncidencia.Transportes);
				// Assert
				assertAll(() -> {
					assertFalse(obtenido.isEmpty(), "Prueba fallida, no ha encontrado incidencias");
				}, () -> {
					assertEquals(obtenido, esperado, "Prueba fallida, no se ha obtenido la incidencia esperada");
				});
			}

			@DisplayName("CP09-Prueba8.1_buscarIncidencias con parámetros dni == null e id igual a id de la incidencia existente")
			@Test
			void CP09_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				ArrayList<Incidencia> esperado = new ArrayList<>();
				esperado.add(incidencia);
				vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
				incidencia = gi.presentarIncidencia(vecino, "Calle callejerao", "Descripcion",
						TipoIncidencia.Servicios);

				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);
				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(null, "I1", fechaIni, fechaFin,
						TipoIncidencia.Transportes);
				// Assert
				assertAll(() -> {
					assertFalse(obtenido.isEmpty(), "Prueba fallida, no ha encontrado incidencias");
				}, () -> {
					assertEquals(obtenido, esperado, "Prueba fallida, no se ha obtenido la incidencia esperada");
				});
			}

			@DisplayName("CP10-Prueba8.1_buscarIncidencias con parámetros dni, idIncidencia distintos y tipo == null")
			@Test
			void CP10_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias("12345678A", "I2", fechaIni, fechaFin, null);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP11-Prueba8.1_buscarIncidencias con parámetros dni, idIncidencia distintos y tipo igual al de la incidencia")
			@Test
			void CP11_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias("12345678A", "I2", fechaIni, fechaFin,
						TipoIncidencia.Desperfectos);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP12-Prueba8.1_buscarIncidencias con parámetros dni == null, id distinto y tipo igual al de la incidencia existente")
			@Test
			void CP12_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(null, "I2", fechaIni, fechaFin,
						TipoIncidencia.Desperfectos);
				// Assert
				assertTrue(obtenido.isEmpty(), "Prueba fallida, ha encontrado incidencias");
			}

			@DisplayName("CP13-Prueba8.1_buscarIncidencias con parámetros dni == null, id == null y tipo igual al de la incidencia existente")
			@Test
			void CP13_Prueba8_1_buscarIncidencias() {
				// Arrange
				GestionProcesos gp = new GestionProcesos();
				GestionIncidencias gi = new GestionIncidencias();
				Ciudadano vecino = new Ciudadano("Raul", "12345678P", "Calle callejero", "666666666");
				Incidencia incidencia = gi.presentarIncidencia(vecino, "Calle Callejero", "Descripcion",
						TipoIncidencia.Desperfectos);
				Date fechaIni = new Date(2020 - 1900, 01, 01);
				Date fechaFin = new Date(2022 - 1900, 12, 31);
				ArrayList<Incidencia> esperado = new ArrayList<>();
				esperado.add(incidencia);
				vecino = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666555555");
				incidencia = gi.presentarIncidencia(vecino, "Calle callejerao", "Descripcion",
						TipoIncidencia.Servicios);

				// Act
				ArrayList<Incidencia> obtenido = gi.buscarIncidencias(null, null, fechaIni, fechaFin,
						TipoIncidencia.Desperfectos);
				// Assert
				// Assert
				assertAll(() -> {
					assertFalse(obtenido.isEmpty(), "Prueba fallida, no ha encontrado incidencias");
				}, () -> {
					assertEquals(obtenido, esperado, "Prueba fallida, no se ha obtenido la incidencia esperada");
				});
			}
		}
	}

}
