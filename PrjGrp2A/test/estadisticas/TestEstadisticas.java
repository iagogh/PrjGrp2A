package estadisticas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import incidencias.*;

class TestEstadisticas {
	IGestionIncidencias gi;
	
	@BeforeEach
	void setUp() throws Exception {
		gi = new GestionIncidencias();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		gi = null;
	}
	
	//Caja negra
	@DisplayName("CP01-P7.1-numeroIncidencias(sin argumentos) caso de prueba valido con parametros validos.")
	@Test
	void CP01_Prueba7_1_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}

	@DisplayName("CP01-P7.1-numeroIncidencias(sin argumentos) caso de prueba no valido que incluye una clase no valida")
	@Test
	void CP02_Prueba7_1_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	@DisplayName("CP01-P7.2-numeroIncidencias(con argumentos) caso de prueba valido con parametros validos.")
	@Test
	void CP01_Prueba7_2_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	@DisplayName("CP02-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con responsable no valido.")
	@Test
	void CP02_Prueba7_2_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	@DisplayName("CP03-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con empresa no valida.")
	@Test
	void CP03_Prueba7_2_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	@DisplayName("CP04-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con filtroFechaIni no valido.")
	@Test
	void CP04_Prueba7_2_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	@DisplayName("CP05-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con filtroFechaFin no valido.")
	@Test
	void CP05_Prueba7_2_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	@DisplayName("CP06-P7.2-numeroIncidencias(con argumentos) caso de prueba no valido con un elemento no perteneciente a la clase incidencias")
	@Test
	void CP06_Prueba7_2_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	
	//Caja blanca
	@DisplayName("CP01-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido referido a que la lista de incidencias está vacia")
	@Test
	void CP01_Prueba8_5_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	@DisplayName("CP02-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias almacena una incidencia con un proceso asociado con coste 0")
	@Test
	void CP02_Prueba8_5_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	@DisplayName("CP03-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena una incidencia con un proceso asociado con coste > 0 sin OT")
	@Test
	void CP03_Prueba8_5_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	@DisplayName("CP04-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena una incidencia con un proceso asociado con coste mayor que 0 con OT coste > 0")	
	@Test
	void CP04_Prueba8_5_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	@DisplayName("CP05-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena una incidencia con un proceso asociado con coste mayor que 0 con OT coste <= 0")
	@Test
	void CP05_Prueba8_5_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
	@DisplayName("CP06-P8.5-numeroIncidencias(sin argumentos) caso de prueba valido lista de incidencias alamacena dos incidencias con un proceso asociado con coste mayor que 0 con OT coste > 0")
	@Test
	void CP06_Prueba8_5_numeroIncidencias() {
		//Arrange
		//Act
		//Assert
	}
}
