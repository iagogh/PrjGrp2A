package incidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.enso.ayuntamiento.Ciudadano;
import incidencias.TipoIncidencia;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestGestionIncidencias {

	@DisplayName("CP01-P9.1_presentarIncidencia en el que se comprueba el funcionamiento de la creacion para unos parametros validos: Vecino, Localizacion, Descripcion y Tipo")
	@Test
	void CP01_Prueba8_1_presentarIncidencia() {
		//Arrange
		GestionIncidencias gi = new GestionIncidencias();
		Ciudadano v = new Ciudadano("Pepe", "77777777Z", "Calle callejero", "666666666");
		String Localizacion = "Calle Callejero";
		String Descripcion = "Descripcion de prueba para la creacion de una incidencia de prueba";
		TipoIncidencia tipo = TipoIncidencia.Desperfectos;
		
		
		
		//Act
		Incidencia creada = gi.presentarIncidencia(v, Localizacion, Descripcion, tipo);
		ArrayList<Incidencia> registradas = gi.getIncidencias();
		
		//Assert
		assertAll(
				() -> {assertNotNull(creada,"Fallo al presentarIncidencia se ha recibido un nulo");},
				() -> {assertTrue(registradas.size()==1, "Fallo al PresentarIncidencia se ha recibido un array de registradas vacío o con mas elementos de los esperados");},
				() -> {assertSame(creada, registradas.get(0), "Fallo al presentarIncidencia el elemento recibido no se corresponde con el esperado");}
				);
	}

}
